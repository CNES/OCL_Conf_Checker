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

import java.util.List;
import java.util.Vector;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.rules.IToken;
import org.topcased.editors.ocleditor.editor.assistant.OCLContentScanner;

/**
 * Utilities class specific to OCL Editor needs.
 * 
 * @author <a href="mailto:sebastien.gabel@c-s.fr">Sebastien GABEL</a>
 */
public class OCLEditorUtil
{

    /**
     * @param document
     * @param modelPackage
     * @param region
     * @param packageRegion
     * @return
     */
    public static ETypedElement computeTypedContext(IDocument document, EPackage modelPackage, ITypedRegion region, ITypedRegion packageRegion)
    {
        OCLContentScanner oclScanner = new OCLContentScanner();
        oclScanner.setDocument(document);

        IToken tokenTemp = oclScanner.lookFor("context", region.getOffset(), region.getLength());
        if (tokenTemp != null)
        {
            tokenTemp = oclScanner.nextToken();
            String contextClass = "";
            if (tokenTemp != null)
            {
                contextClass = oclScanner.getTokenValue();
                EClass context = (EClass) modelPackage.getEClassifier(contextClass);
                tokenTemp = oclScanner.nextToken();// oclScanner.getTokenValue()
                if (tokenTemp.getData() != null && tokenTemp.getData().equals(OCLContentScanner.DOUBLE_COLON))
                {
                    tokenTemp = oclScanner.nextToken();
                }
                else
                {
                    // The separator must be' ::' else we cannot compute an operation context so return null
                    return null;
                }
                if (context == null) return null;
                // Search inside the operations List
                EList<EOperation> operationsList = context.getEAllOperations();
                boolean notFound = true;
                int i = 0;
                EOperation myOp = null;
                while (i < operationsList.size() && notFound)
                {
                    if ((operationsList.get(i)).getName().equals(oclScanner.getTokenValue()))
                    {
                        myOp = operationsList.get(i);
                    }
                    i++;
                }
                if (myOp != null)
                {
                    return myOp;
                }
                // Search inside the operations List
                EList<EStructuralFeature> structuralFeaturesList = context.getEAllStructuralFeatures();
                notFound = true;
                i = 0;
                EStructuralFeature myFeature = null;
                while (i < structuralFeaturesList.size() && notFound)
                {
                    if ((structuralFeaturesList.get(i)).getName().equals(oclScanner.getTokenValue()))
                    {
                        myFeature = structuralFeaturesList.get(i);
                    }
                    i++;
                }
                return myFeature;
            }
        }
        return null;
    }

    /**
     * Computes the context of the given region in the document
     * 
     * @param document
     * @param modelPackage
     * @param region
     * @return
     */
    public static EClassifier computeContextClassifier(IDocument document, EPackage modelPackage, ITypedRegion region, ITypedRegion packageRegion)
    {
        OCLContentScanner oclScanner = new OCLContentScanner();
        oclScanner.setDocument(document);

        IToken tokenTemp = oclScanner.lookFor("package", packageRegion.getOffset(), packageRegion.getLength());
        if (tokenTemp != null)
        {
            tokenTemp = oclScanner.nextToken();
            if (tokenTemp != null)
            {
                String contextPack = oclScanner.getTokenValue();
                for (EPackage p : modelPackage.getESubpackages())
                {
                    if (p.getName().equals(contextPack))
                    {
                        modelPackage = p;
                        break;
                    }
                }
            }
        }

        tokenTemp = oclScanner.lookFor("context", region.getOffset(), region.getLength());
        int lastPosition = oclScanner.getTokenOffset() + "context".length();
        if (tokenTemp != null)
        {
        	tokenTemp = oclScanner.nextToken();
        	String contextClass = oclScanner.getTokenValue();
        	lastPosition = oclScanner.getTokenOffset() + contextClass.length();
        	tokenTemp = oclScanner.lookFor("::", lastPosition, region.getOffset() + region.getLength() - lastPosition);
            while (tokenTemp != null)
            {
                lastPosition = oclScanner.getTokenOffset() + "::".length();
                tokenTemp = oclScanner.nextToken();
                contextClass = oclScanner.getTokenValue();
                lastPosition = oclScanner.getTokenOffset() + contextClass.length();
                tokenTemp = oclScanner.lookFor("::", lastPosition, region.getOffset() + region.getLength() - lastPosition);
            }
            
            if (contextClass != null && contextClass != "")
            {
//            	oclScanner.setRange(lastPosition, region.getOffset() + region.getLength() - lastPosition);
//            	tokenTemp = oclScanner.nextToken();
//                contextClass = oclScanner.getTokenValue();
                return modelPackage.getEClassifier(contextClass);
            }
        }
        return null;
    }
    
    /**
     * Compute the context of a specific partition
     * 
     * @param document the whole document this function should work on
     * @param region the region where the context should be found
     * @return An EClassifier corresponding to the ocntext of the region
     */
    public static EClassifier computeContextClassifierForPartition(IDocument document, ITypedRegion region)
    {
        URI rootModelURI = MetamodelUtils.getModelURI(document);
        EPackage metamodelPackage = MetamodelUtils.findMetamodelPackage(rootModelURI);
        
        OCLContentScanner oclScanner = new OCLContentScanner();
        oclScanner.setDocument(document);
        
        oclScanner.setDocument(document);
        List<String> packagePrefixes = new Vector<String>();
        IToken tokenTemp = oclScanner.lookFor("package", 0, region.getOffset());
        if (tokenTemp != null)
        {
            if (!oclScanner.endOfScanner())
            {
                tokenTemp = oclScanner.nextToken();
                String tokenValue = oclScanner.getTokenValue();
                while (!oclScanner.endOfScanner() && tokenTemp.getData() != null && tokenTemp.getData().equals(OCLContentScanner.WORD))
                {
                    packagePrefixes.add(tokenValue);
                    tokenTemp = oclScanner.nextToken();
                    tokenValue = oclScanner.getTokenValue();
                    if (!oclScanner.endOfScanner() && tokenTemp.getData() != null && tokenTemp.getData().equals(OCLContentScanner.DOUBLE_COLON))
                    {
                        tokenTemp = oclScanner.nextToken();
                        tokenValue = oclScanner.getTokenValue();
                    }
                }
            }
        }

        tokenTemp = null;
        int lastPosition = 0;
        tokenTemp = oclScanner.lookFor("context", 0, region.getOffset());
        while (tokenTemp != null)
        {
            lastPosition = oclScanner.getTokenOffset() + "context".length();
            tokenTemp = oclScanner.lookFor("context", lastPosition, region.getOffset() - lastPosition);

        }

        tokenTemp = oclScanner.lookFor("::", lastPosition, region.getOffset() - lastPosition);
        while (tokenTemp != null)
        {
            lastPosition = oclScanner.getTokenOffset() + "::".length();
            tokenTemp = oclScanner.lookFor("::", lastPosition, region.getOffset() - lastPosition);

        }

        if (lastPosition != 0)
        {
            oclScanner.setRange(lastPosition, region.getOffset() - lastPosition);
            tokenTemp = oclScanner.nextToken();
            String contextClass = "";
            if (!tokenTemp.isEOF())
            {
                contextClass = oclScanner.getTokenValue();
            }
            if (packagePrefixes.size() == 0)
            {
                return metamodelPackage.getEClassifier(contextClass);
            }
            else
            {
                if (metamodelPackage.getNsPrefix().equals(packagePrefixes.get(0)))
                {
                    EPackage tempPackage = metamodelPackage;
                    boolean packageError = false;
                    for (int i = 1; i < packagePrefixes.size() && !packageError; i++)
                    {
                        List<EPackage> subPackages = tempPackage.getESubpackages();
                        boolean packageFound = false;
                        for (int j = 0; j < subPackages.size() && !packageFound; j++)
                        {
                            EPackage subi = subPackages.get(j);
                            if (subi.getNsPrefix().equals(packagePrefixes.get(i)))
                            {
                                packageFound = true;
                                tempPackage = subi;
                            }
                        }
                        if (!packageFound)
                        {
                            packageError = true;
                        }
                    }
                    if (!packageError)
                    {
                        return (EClass) tempPackage.getEClassifier(contextClass);
                        // currentPackage = tempPackage;
                    }
                }
            }
        }
        return null;
    }
    
    
}
