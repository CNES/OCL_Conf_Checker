/***********************************************************************************************************************
 * Copyright (c) 2008 Communication and Systems.
 * 
 * All rights reserved. This program and the accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sebastien GABEL (CS) - initial API and implementation
 * 
 **********************************************************************************************************************/
package org.topcased.editors.ocleditor.export;

import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.pde.core.plugin.IExtensionsModelFactory;
import org.eclipse.pde.core.plugin.IPluginElement;
import org.eclipse.pde.core.plugin.IPluginExtension;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.topcased.editors.ocleditor.OCLEditorPlugin;
import org.topcased.editors.ocleditor.editor.builders.OCLEditorConstant;

import com.cnes.checktool.oclrules.oclRules.util.OCLResource;

/**
 * This class is responsible for generating the plugin.xml content.<br>
 * For each selected OCL resources in the previous wizard, the resource is copied to the destination plug-in (the new
 * one). For each copied OCL files, an extension <b>org.topcased.validation.ocl.metamodels</b> is contributed in the
 * plugin.xml file.<br>
 * 
 * @author <a href="mailto:sebastien.gabel@c-s.fr">Sebastien GABEL</a>
 */
public final class TopcasedOCLPluginGenerator
{
    /** Represents the <i>id</i> attribute */
    private static final String ID = "id";

    /** Represents the <i>file</i> attribute */
    private static final String FILE = "file";

    /** Represents the <i>uri</i> attribute */
    private static final String URI = "uri";

    /** Represents the <i>level</i> attribute */
    private static final String LEVEL = "level";

    /** Represents the name of the directory to create */
    private static final String MODEL_DIRECTORY = "model";

    /** Represents the path to the directory where will be stored all the OCL resources */
    private static final IPath MODEL_DIRECTORY_PATH = new Path(MODEL_DIRECTORY);

     /**
     * Constructor
     */
    private TopcasedOCLPluginGenerator()
    {
        // prevent from instanciation
    }

    /**
     * Generates all required information to make a Topcased OCL plug-in.
     * 
     * @param project The generated project representing our new plug-in.
     * @param model The plug-in model base information
     * @param map The map of the selected OCL resource by URI
     */
    public static void createOCLPluginContent(IProject project, IPluginModelBase model, Map<URI, List<OCLResource>> map)
    {
        // Step 1 - the directory 'model' is created
        try
        {
            createModelDirectory(project.getFullPath());
        }
        catch (CoreException e)
        {
            OCLEditorPlugin.log("Error while generating the model directory", IStatus.ERROR, e);
        }

        // Step 2 - create extensions of the plugin.xml file
        try
        {
            createExtensions(project, model, map);
        }
        catch (CoreException e)
        {
            OCLEditorPlugin.log("Error while generating extension for this plug-in", IStatus.ERROR, e);
        }

        // Step 3 - the OCL nature and the OCL builder are added to the project
        try
        {
            addNatureAndBuilder(project);
        }
        catch (CoreException e)
        {
            OCLEditorPlugin.log("Error while adding nature to the new plug-in", IStatus.ERROR, e);
        }

    }

    /**
     * Generates the model directory into the"" new created plug-in.
     * 
     * @param projectPath the path of the new project
     * @throws CoreException if the folder generation failed
     */
    private static void createModelDirectory(IPath projectPath) throws CoreException
    {
        IPath dirPath = projectPath.append(MODEL_DIRECTORY_PATH);
        IFolder folder = ResourcesPlugin.getWorkspace().getRoot().getFolder(dirPath);
        if (!folder.exists())
        {
            folder.create(true, true, null);
        }
    }

    /**
     * Generates the plugin.xml file with the extension points, dependencies and so on...
     * 
     * @param project The current project
     * @param model The current model representing the structure of the plugin.xml file
     * @param map
     */
    private static void createExtensions(IProject project, IPluginModelBase model, Map<URI, List<OCLResource>> map) throws CoreException
    {
        if (model.isEditable() && !map.keySet().isEmpty())
        {
            IExtensionsModelFactory factory = model.getFactory();

            // Elements to define the extension point
            IPluginExtension extension = factory.createExtension();
            extension.setPoint("org.topcased.validation.ocl.metamodels");
            model.getExtensions().add(extension);

            int i = 1;
            for (URI uri : map.keySet())
            {
                for (OCLResource file : map.get(uri))
                {
                    String oclResource = file.getRelativePath().lastSegment();
                    IPath pathFromDirectory = MODEL_DIRECTORY_PATH.append(oclResource);
                    IPath pathFromProject = project.getFullPath().append(pathFromDirectory);
                    // the OCL resource is first copied in the generated plug-in.
                    if (copyOCLResource(file, pathFromProject))
                    {
                        // the extension is fill in with its various information only if the resource was correctly
                        // copied
                        IPluginElement ruleElement = factory.createElement(extension);
                        ruleElement.setName("rules");
                        ruleElement.setAttribute(FILE, pathFromDirectory.makeRelative().toString());
                        ruleElement.setAttribute(URI, uri.toString());
                        ruleElement.setAttribute(ID, model.getPluginBase().getId().concat(".rules") + i++);
                        ruleElement.setAttribute(LEVEL, "ERROR"); // by default, all is error
                        extension.add(ruleElement);
                    }
                }
            }
        }
    }

    /**
     * Copies an OCL resource in the new generated plug-in.
     * 
     * @param source The OCL resource to copy
     * @param dest The destination represented as an IPath
     * @return <code>true</code> if the resource has been copied, <code>false</code> otherwise.
     */
    private static boolean copyOCLResource(OCLResource source, IPath dest)
    {
        try
        {
            // TODO : check the existence before copying resource
            source.getFile().copy(dest, true, new NullProgressMonitor());
        }
        catch (CoreException e)
        {
            OCLEditorPlugin.log("Impossible to copy " + source.getRelativePath().toString(), IStatus.WARNING, e);
            return false;
        }
        return true;
    }

    /**
     * Adds the nature and the builder to the generated project 
     * 
     * @param project The created project
     * @throws CoreException 
     */
    private static void addNatureAndBuilder(IProject project) throws CoreException
    {
        IProjectDescription description = project.getDescription();
        String[] natures = description.getNatureIds();
        String[] newNatures = new String[natures.length + 1];
        System.arraycopy(natures, 0, newNatures, 0, natures.length);
        newNatures[natures.length] = OCLEditorConstant.PROJECT_NATURE;
        // add it to the project description
        description.setNatureIds(newNatures);
        project.setDescription(description, new NullProgressMonitor());
    }
}
