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
package org.topcased.editors.ocleditor.editor.builders;

import java.util.Map;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.ocl.OCL;
import org.eclipse.ocl.OCLInput;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.ecore.Constraint;
import org.eclipse.ocl.options.EvaluationOptions;
import org.eclipse.ocl.uml.options.EvaluationMode;
import org.eclipse.ocl.uml.options.UMLEvaluationOptions;
import org.topcased.editors.ocleditor.OCLEditorPlugin;
import org.topcased.editors.ocleditor.utils.MetamodelUtils;
import org.topcased.editors.ocleditor.utils.OCLDocumentUtil;
import com.cnes.checktool.oclrules.oclRules.util.ToolkitOCL;


/**
 * Builder for the OCL Editor
 * 
 * @author <a href="mailto:sebastien.gabel@c-s.fr">Sebastien GABEL</a>
 * @author <a href="mailto:christophe.le-camus@c-s.fr">Christophe LE CAMUS</a>
 */
public class OCLEditorBuilder extends IncrementalProjectBuilder
{

    /**
     * @see org.eclipse.core.resources.IncrementalProjectBuilder#build(int, java.util.Map,
     *      org.eclipse.core.runtime.IProgressMonitor)
     */
    @SuppressWarnings("rawtypes")
    protected IProject[] build(int kind, Map args, IProgressMonitor monitor) throws CoreException
    {
        IResource[] resources = getProject().members();
        if ((kind & INCREMENTAL_BUILD) != 0)
        {
            IResourceDelta projectDelta = getDelta(getProject());
            IResourceDelta[] delta = new IResourceDelta[0];
            if (projectDelta != null)
            {
                delta = projectDelta.getAffectedChildren(IResourceDelta.ADDED | IResourceDelta.CHANGED);
            }
            resources = new IResource[delta.length];
            for (int i = 0; i < delta.length; i++)
            {
                resources[i] = delta[i].getResource();
            }
        }
        buildResources(resources);
        return null;
    }

    /**
     * Builds the resources recursively. It is recursive when a folder is found.
     * 
     * @param resources an array of the resources to build
     * @throws CoreException If there is a problem with a marker or the content of a file resource
     */
    private void buildResources(IResource[] resources) throws CoreException
    {
        for (int i = 0; i < resources.length; i++)
        {
            if (resources[i] instanceof IFile)
            {
                IFile resourceFile = (IFile) resources[i];
                if ("ocl".equals(resourceFile.getFileExtension()))
                {
                    int addLines = OCLDocumentUtil.getNumberOfLinesToAddToDocument(resourceFile.getContents());
                    resourceFile.deleteMarkers(null, true, IResource.DEPTH_INFINITE);
                    OCLInput document = OCLDocumentUtil.getOCLDocument(resourceFile.getContents());
                    URI rootURI = OCLDocumentUtil.getDocumentURI();
                    try
                    {
                        EPackage modelPackage = MetamodelUtils.findMetamodelPackage(rootURI);

                        // initializes the Environment and get the Helper
                        OCL< EPackage , EClassifier, ?, ?, ? , ? , ? , ? , ? , Constraint, EClass , EObject > ocl = ToolkitOCL.getOCL(modelPackage);
                        if (modelPackage.getName().equals("uml"))
                        {
                            EvaluationOptions.setOption(ocl.getEvaluationEnvironment(), UMLEvaluationOptions.EVALUATION_MODE, EvaluationMode.INSTANCE_MODEL);
                        }
                        // parse the document
                        ocl.parse(document);
                    }
                    catch (ParserException e)
                    {
                        IMarker marker = resourceFile.createMarker(IMarker.PROBLEM);
                        marker.setAttribute(IMarker.MESSAGE, "Parsing Exception : " + e.getMessage());
                        marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_ERROR);
                        String message = e.getDiagnostic().getMessage();
                        Integer line = new Integer(2);
                        try
                        {
                            if (message.contains(":"))
                            {
                                line = new Integer(message.substring(0, message.indexOf(":", 0)));
                            }
                            else
                            {
                                message = message.concat(" -- error location not published by the parser");
                                marker.setAttribute(IMarker.MESSAGE, "Parsing Exception : " + message);
                            }
                            marker.setAttribute(IMarker.LINE_NUMBER, line.intValue() + addLines);
                        }
                        catch (NumberFormatException nfe)
                        {
                            // marker.setAttribute(IMarker.LINE_NUMBER, e.getDiagnostic().getCode() + addLines);
                            // FIXME : handle this kind of exception when it will be possible.
                            // Force the marker creation on line 2
                            message = message.concat(" -- error location not published by the parser");
                            marker.setAttribute(IMarker.MESSAGE, "Parsing Exception : " + message);
                            marker.setAttribute(IMarker.LINE_NUMBER, line.intValue());
                        }
                        catch (IndexOutOfBoundsException b)
                        {
                            // FIXME : handle this kind of exception when it will be possible.
                        }
                    }
                    catch (Exception e)
                    {
                        OCLEditorPlugin.log(e);
                    }
                }
            }
            else if (resources[i] instanceof IContainer)
            {
                IContainer container = (IContainer) resources[i];
                buildResources(container.members());
            }
        }
    }
}
