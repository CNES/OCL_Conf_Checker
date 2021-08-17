/******************************************************************************************
 * Copyright (c) 2005 AIRBUS FRANCE.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Christophe Le Camus (CS), Marion Feau (CS), Guillaume Jolly (CS), S�bastien Gabel (CS)
 *    Petre Bazavan (AEIC), Vincent Combet (CS) - initial API and implementation
 *********************************************************************************************/
package org.topcased.editors.ocleditor.wizard;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.topcased.editors.ocleditor.Messages;
import org.topcased.editors.ocleditor.OCLEditorPlugin;
import org.topcased.editors.ocleditor.editor.builders.OCLEditorConstant;
import org.topcased.editors.ocleditor.utils.OCLResourceUtil;

/**
 * Wizard allowing the user to create a new OCL rule file.
 * 
 * @author <a href="mailto:sebastien.gabel@c-s.fr">Sebastien GABEL</a>
 */
public class NewRuleFileWizard extends Wizard implements INewWizard
{
    /** The only page contributing to the wizard */
    private NewRuleFileWizardPage page;

    /**
     * Constructor
     */
    public NewRuleFileWizard()
    {
        super();
        setWindowTitle(Messages.getString("NewRuleFileWizard.0")); //$NON-NLS-1$
        setDefaultPageImageDescriptor(OCLEditorPlugin.getImageDescriptor("icons/editor/ocl.gif"));
    }

    /**
     * Performs finish action of this wizard.
     * 
     * @see org.eclipse.jface.wizard.Wizard#performFinish()
     */
    public boolean performFinish()
    {

        IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
        IResource resource = root.findMember(page.getLocation());
        final IFile file = root.getFile(page.getLocation().append(page.getFileName()));

        try
        {
            setProjectNature(resource);
            setInitialContent(file);
            IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
            IDE.openEditor(page, file );
        }
        catch (PartInitException e)
        {
            OCLEditorPlugin.log(e);
            return false;
        }
        catch (CoreException e)
        {
            OCLEditorPlugin.log(e);
            return false;
        }
        catch (IOException ioe)
        {
            OCLEditorPlugin.log(ioe);
            return false;
        }

        return true;
    }

    /**
     * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench,
     *      org.eclipse.jface.viewers.IStructuredSelection)
     */
    public void init(IWorkbench workbench, IStructuredSelection selection)
    {
        page = new NewRuleFileWizardPage(selection);
        addPage(page);
    }

    /**
     * Gets the initial content of a new OCL rule file.
     * 
     * @return the initial content of a new OCL resource.
     */
    private String getOCLFileContent()
    {
    	EPackage p = EPackage.Registry.INSTANCE.getEPackage(page.getMetamodelUri());
    	String metamodelUri = p.getNsURI();
        String line = new String("MainModel : "); //$NON-NLS-1$
        // TODO find another solution for the end of line.
        // Try to check if the ResolveAll Resources cannot be used here.
        if (page.getMetaModelFromFile() != null)
        {
            line = line.concat(page.getMetaModelFromFile()).concat("\n"); //$NON-NLS-1$
            IPath pathFile = new Path(page.getMetamodelUri());
            Resource loaded = OCLResourceUtil.loadResource(pathFile);
            EPackage packageTemp = (EPackage) loaded.getContents().get(0);
            OCLResourceUtil.registerResolvedResources(packageTemp);
        }
        else
        {
            // selected from the URI
            line = line.concat(metamodelUri);
            EPackage packageTemp = EPackage.Registry.INSTANCE.getEPackage(metamodelUri);
            OCLResourceUtil.registerResolvedResources(packageTemp);
            if (!page.getMetamodelUri().equals(metamodelUri))
            {
                line = line.concat("\n").concat("SubModel : ").concat(page.getMetamodelUri()); //$NON-NLS-1$ //$NON-NLS-2$
                packageTemp = EPackage.Registry.INSTANCE.getEPackage(page.getMetamodelUri());
                OCLResourceUtil.registerResolvedResources(packageTemp);
            }
        }
        line = line + "\n\ncontext "; //$NON-NLS-1$
        return line;
    }

    /**
     * Sets the project nature if required.
     * 
     * @param resource The IResource corresponding to the project
     */
    private void setProjectNature(IResource resource) throws CoreException
    {
        IProjectDescription description = resource.getProject().getDescription();
        List<String> newNatures = new ArrayList<String>(Arrays.asList(description.getNatureIds()));

        if (!newNatures.contains(OCLEditorConstant.PROJECT_NATURE))
        {
            newNatures.add(OCLEditorConstant.PROJECT_NATURE);
            description.setNatureIds((String[]) newNatures.toArray(new String[newNatures.size()]));
            resource.getProject().setDescription(description, null);
        }
    }

    /**
     * Sets the initial content of the new OCL rule file.
     * 
     * @param file The file to create and initialize
     * @throws IOException if the resource can not be closed
     * @throws CoreException if the initialization step failed
     */
    private void setInitialContent(IFile file) throws CoreException, IOException
    {
        InputStream stream = new ByteArrayInputStream(getOCLFileContent().getBytes());
        if (file.exists())
        {
            file.setContents(stream, true, true, null);
        }
        else
        {
            file.create(stream, true, null);
        }
        stream.close();
    }

}
