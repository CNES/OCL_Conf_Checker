/***********************************************************************************************************************
 * Copyright (c) 2008-2009 Communication & Systems.
 * 
 * All rights reserved. This program and the accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sebastien GABEL (CS) - initial API and implementation
 * 
 **********************************************************************************************************************/
package org.topcased.editors.ocleditor.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.topcased.editors.ocleditor.OCLEditorPlugin;

/**
 * Set of utility methods helping to register/consult metamodels.<br>
 * 
 * Update : 10 February 2009<br>
 * 
 * @author <a href="mailto:christophe.le-camus@c-s.fr">Christophe LE CAMUS</a>
 * @author <a href="mailto:sebastien.gabel@c-s.fr">Sebastien GABEL</a>
 * 
 */
public final class MetamodelUtils
{

    /**
     * Private constructor
     */
    private MetamodelUtils()
    {
        // cannot be instanciated
    }


    /**
     * 
     * Looks for the sub package of a package. 
     * 
     * @param currentPackage
     * @param packagePrefixes
     * @return
     */
    private static EPackage findSubPackage(EPackage currentPackage, List<String> packagePrefixes)
    {

        EPackage tempPackage = currentPackage;

        if (currentPackage.getName() != null)
        {
            boolean packageError = false;
            for (int i = 0; i < packagePrefixes.size() && !packageError; i++)
            {
                List<EPackage> subPackages = currentPackage.getESubpackages();
                boolean packageFound = false;
                for (int j = 0; j < subPackages.size() && !packageFound; j++)
                {
                    EPackage subi = subPackages.get(j);
                    // change getPrefix by getName()
                    if (subi.getName() != null)
                    {
                        if (subi.getName().equals(packagePrefixes.get(i)))
                        {
                            packageFound = true;
                            tempPackage = subi;
                        }
                    }
                    else
                    {
                        OCLEditorPlugin.log("Sub package name null. Check the meta model.", IStatus.ERROR); 
                    }
                }
                if (!packageFound)
                {
                    packageError = true;
                }
            }
        }
        else
        {
        	OCLEditorPlugin.log("Metamodel package name null.: Check the meta model.", IStatus.ERROR); 
        }
        return tempPackage;
    }

    /**
     * Register the meta model if it has not been registered before. This is necessary to take into account local meta
     * models that have not been prepared as new plugin.
     * 
     * @param theModel
     */
    public static void registerMetaModel(EObject theModel)
    {
        if (theModel instanceof EPackage)
        {
            EPackage modelPackage = (EPackage) theModel;
            List<EPackage> packages = new ArrayList<EPackage>();
            packages.addAll(modelPackage.getESubpackages());
            packages.add(0, modelPackage);
            for (Iterator<EPackage> it = packages.iterator(); it.hasNext();)
            {
                EPackage p = it.next();
                String nsURI = p.getNsURI();
                if (nsURI == null)
                {
                    nsURI = p.getName();
                    p.setNsURI(nsURI);
                }
                if (!EPackage.Registry.INSTANCE.containsKey(nsURI))
                {
                    EPackage.Registry.INSTANCE.put(nsURI, p);
                    OCLEditorPlugin.log("Model loaded : ".concat(nsURI), IStatus.INFO); //$NON-NLS-1$
                }
            }
        }
    }
    
    /**
     * Returns the main model URI defined in an OCL document
     * 
     * @param document The OCL JFace document
     * @return an URI read from the document
     */
    public static URI getModelURI(IDocument document)
    {
        try
        {
            String firstLine = document.get(0, document.getLineLength(0));
            if (firstLine.startsWith("MainModel :")) //$NON-NLS-1$
            {
                return URI.createURI(firstLine.substring("MainModel :".length()).trim()); //$NON-NLS-1$
            }
        }
        catch (BadLocationException e)
        {
            OCLEditorPlugin.log("Cannot retrieve URI : " + e.getMessage(), IStatus.ERROR, e); //$NON-NLS-1$
        }
        return null;
    }
    
    /**
     * Gets the highest package from a meta model
     * 
     * @param mmUri
     * @return URI from the container package at the highest level
     */
    public static String getESuperPackage(String mmUri)
    {

        EPackage packageTemp = EPackage.Registry.INSTANCE.getEPackage(mmUri);
        if (packageTemp == null)
        {
           return null;
        }
        // Calling register on superPackage
        if (packageTemp != null)
        {
            EPackage eSuperPackage = packageTemp.getESuperPackage();
            if (eSuperPackage != null)
            {
                while (eSuperPackage != null && eSuperPackage.getESuperPackage() != null)
                {
                    eSuperPackage = eSuperPackage.getESuperPackage();
                    // if the current package is not known, we register it
                    registerMetaModel(eSuperPackage);
                }
                return eSuperPackage.getNsURI();
            }
            else
            {
                return packageTemp.getNsURI();
            }
        }
        return "";
    }
    
    public static EPackage getEPackage(String name)
    {
        Collection<Object> allPackage = EPackage.Registry.INSTANCE.values();
        for (Object p : allPackage)
        {
            EPackage pkg = null;
            if (p instanceof EPackage)
            {
                pkg = (EPackage) p;
            }
            else if (p instanceof EPackage.Descriptor)
            {
                pkg = ((EPackage.Descriptor) p).getEPackage();
            }
            
            if (pkg != null && name.equals(pkg.getName()))
            {
                return pkg;
            }
        }
        return null;
    }
    
    public static EPackage findMetamodelPackage(URI uri) {
    	if( uri !=null) {
    		return EPackage.Registry.INSTANCE.getEPackage(uri.toString());
    	}
    	return null;
    	
    }
}
