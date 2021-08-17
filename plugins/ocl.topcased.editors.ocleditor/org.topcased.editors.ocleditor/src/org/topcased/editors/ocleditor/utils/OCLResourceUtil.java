/***********************************************************************************************************************
 * Copyright (c) 2008,2009 Communication & Systems.
 * 
 * All rights reserved. This program and the accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sebastien GABEL (CS) - initial API and implementation
 * 
 **********************************************************************************************************************/
package org.topcased.editors.ocleditor.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.topcased.editors.ocleditor.OCLEditorPlugin;
import com.cnes.checktool.oclrules.oclRules.util.ToolkitOCL;

/**
 * Set of convenient methods which deals with EMF resources (load, save, etc.).<br>
 * 
 * Created 05 november 2009<br>
 * 
 * @author <a href="mailto:christophe.le-camus@c-s.fr">Christophe LE CAMUS</a>
 * @author <a href="mailto:sebastien.gabel@c-s.fr">Sebastien GABEL</a>
 * @since Topcased 2.7.0
 * @since Topcased 3.2.0
 */
public final class OCLResourceUtil
{

    /**
     * Private constructor
     */
    private OCLResourceUtil()
    {
        // prevent from instanciation
    }

    /**
     * Loads an EMF resource from a {@link IFile}
     * 
     * @param file The resource to load given as a {@link IFile}
     * @return EObject of the model
     */
    public static Resource loadResource(IFile file)
    {
        return loadResource(file.getFullPath(), null);
    }

    /**
     * Loads an EMF resource from a {@link IFile}
     * 
     * @param file The resource to load given as a {@link IFile}
     * @return EObject of the model
     */
    public static Resource loadResource(IFile file, ResourceSet set)
    {
        return loadResource(file.getFullPath(), set);
    }

    /**
     * Loads an EMF resource and returns it.
     * 
     * @param path The resource to load given as a {@link IPath}
     * @return The loaded resource
     */
    public static Resource loadResource(IPath path)
    {
        return loadResource(path, null);
    }

    /**
     * Loads an EMF resource and returns it.
     * 
     * @param path The resource to load given as a {@link IPath}
     * @return The loaded resource
     */
    public static Resource loadResource(IPath path, ResourceSet set)
    {
        URI uri = null;
        if (isRelativePath(path))
        {
            uri = URI.createPlatformResourceURI(path.toString(), true);
        }
        else
        {
            uri = URI.createFileURI(path.toString());
        }
        if (set == null)
        {
            set = ToolkitOCL.createConfiguredResourceSet();
        }
        return set.getResource(uri, true);
    }

    /**
     * Tests to know if the path is relative or absolute.
     * 
     * @param path A path
     * @return
     */
    private static boolean isRelativePath(IPath path)
    {
        IResource resource = ResourcesPlugin.getWorkspace().getRoot().findMember(path);
        return resource != null && resource.exists();
    }

    /**
     * Saves a resource
     * 
     * @param root The root model object.
     * @param uri An URI representing either a platform resource or a external resource.
     */
    private static void save(EObject root, URI uri)
    {
        try
        {
            Map<String, String> options = new HashMap<String, String>();
            options.put(XMLResource.OPTION_PROCESS_DANGLING_HREF, XMLResource.OPTION_PROCESS_DANGLING_HREF_DISCARD);
            options.put(XMLResource.OPTION_ENCODING, "UTF-8"); //$NON-NLS-1$
            options.put(Resource.OPTION_SAVE_ONLY_IF_CHANGED, Resource.OPTION_SAVE_ONLY_IF_CHANGED_MEMORY_BUFFER);
            Resource rsc = new ResourceSetImpl().createResource(uri);
            rsc.getContents().add(root);
            rsc.save(options);
        }
        catch (IOException e)
        {
            OCLEditorPlugin.log(e);
        }
    }

    /**
     * Saves model in the current workspace (platform:/).
     * 
     * @param root The root model object
     * @param toSave Path where the file must be created/written.
     */
    public static void platformSave(EObject root, String toSave)
    {
        URI uri = URI.createPlatformResourceURI(toSave, true);
        save(root, uri);
    }

    /**
     * Saves model in an external resource (file:/)
     * 
     * @param root The root model object
     * @param toSave Path where the file must be created/written.
     */
    public static void fileSave(EObject root, String toSave)
    {
        URI uri = URI.createFileURI(toSave);
        save(root, uri);
    }

    /**
     * Gets the model path
     * 
     * @param rsc An object belonging to this model
     * @return a path to this resource
     */
    public static IPath getModelPath(Resource rsc)
    {
        String path = null;
        URI uri = rsc.getURI();
        if (uri.isPlatform())
        {
            path = uri.toPlatformString(true);
        }
        else
        {
            path = uri.toFileString();
        }
        return new Path(path);
    }

    /**
     * Tries to resolve all resources
     * 
     * @param model
     * @return
     */
    public static EList<Resource> registerResolvedResources(EObject model)
    {
        EcoreUtil.resolveAll(model);
        EList<Resource> allResources = null;
        if (model.eResource().getResourceSet() != null)
        {
            allResources = model.eResource().getResourceSet().getResources();
            for (Iterator<Resource> it = allResources.iterator(); it.hasNext();)
            {
                Resource aResrc = (Resource) it.next();
                EList<EObject> allPackages = aResrc.getContents();
                for (Iterator<EObject> itP = allPackages.iterator(); itP.hasNext();)
                {
                    EPackage aPackage = (EPackage) itP.next();
                    MetamodelUtils.registerMetaModel(aPackage);
                }
            }
        }
        return allResources;
    }
}
