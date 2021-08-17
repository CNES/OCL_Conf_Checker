/**
 * Copyright (c) 2021 CNES. All rights reserved 
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributor : OCL Checker team - Atos
 * 
 */
package com.cnes.checktool.oclrules.oclRules.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.ocl.OCL;
import org.eclipse.ocl.OCLInput;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.Query;
import org.eclipse.ocl.ecore.Constraint;
import org.eclipse.ocl.ecore.EcoreEnvironmentFactory;
import org.eclipse.ocl.expressions.OCLExpression;
import org.eclipse.ocl.helper.OCLHelper;

import com.cnes.checktool.Log;

public class OCLCheckerEngineDelegate
{

    /** Reference to the OCL helper */
    protected static OCLHelper<EClassifier, ? , ? , Constraint> oclHelper;

    /** The OCL environment */
    protected static OCL<EPackage, EClassifier, ? , ? , ? , ? , ? , ? , ? , Constraint, EClass, EObject> ocl;

    /** Represents the model loaded */
    protected static Resource loadedModel;

    /** Represents the resource set on which the check and evaluate operation will be performed. */
    protected static ResourceSet rscSet;

    /** Singular static instance **/
    private static OCLCheckerEngineDelegate instance;
    
    
    /**
     * Reads, loads and evaluates the rules coming either from workspace or from file system.
     * 
     * @param path The path to the EMF model to evaluate.
     * @param selected The list of selected OCL resource.
     * @param createMarkers Boolean indicating if markers have to be created or not.
     * @param monitor The progress monitor to use.
     * @return The OCL log model corresponding to the check operation.
     */
    public void evaluateOCLRules(Resource resource, List<OCLResource> selected)
    {
        // Load the model
        initRscSet(resource);
        loadedModel = resource;

        
        EcoreUtil.resolveAll(rscSet);

        for (OCLResource oclRsc : selected)
		{
		    // Get the OCL file content and load the rules and evaluate them
		    List<Constraint> constraints = getRulesFromFile(oclRsc);
		    evaluateRules(oclRsc, constraints);
		}
    }


    public List<Constraint> getRulesFromFile(OCLResource file)
    {
        try
        {
            InputStream inStream = file.getContent();
            OCLInput document = new OCLInput(inStream);
            URI mainNsURI = file.getURI();
            EPackage metamodelPackage = EPackage.Registry.INSTANCE.getEPackage(mainNsURI.toString());
            initOCLEnvironment(metamodelPackage, rscSet);

            try
            {
                List<Constraint> constraints = ocl.parse(document);
                return constraints;
            }
            catch (ParserException e)
            {
                System.out.println(e);
            }
            finally
            {
                inStream.close();
            }
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
        return Collections.emptyList();
    }

    /**
     * Reads, extracts and evaluates the rules in the list of rules.
     * 
     * @param file The OCL resource to load
     */
    public void evaluateRules(OCLResource file, List<Constraint> constraints)
    {
        // Create a new set of results for this resource
        try
        {
            InputStream inStream = file.getContent();
            OCLInput document = new OCLInput(inStream);
            URI mainNsURI = file.getURI();
            EPackage metamodelPackage = EPackage.Registry.INSTANCE.getEPackage(mainNsURI.toString());
            // init is handled in getRulesFromFile, but this cannot hurt
            initOCLEnvironment(metamodelPackage, rscSet);

            for (Constraint constraint : constraints)
            {
                OCLExpression<EClassifier> oclExp = constraint.getSpecification().getBodyExpression();
                EModelElement namedElt = constraint.getConstrainedElements().get(0);
                EPackage currentMetamodelPackage = (EPackage) namedElt.eContainer();
                if (metamodelPackage != currentMetamodelPackage)
                {
                    initOCLEnvironment(currentMetamodelPackage);
                    metamodelPackage = currentMetamodelPackage;
                }

                // avoid to process 'def' that might be found in OCL rules....
                if (!"definition".equals(constraint.getStereotype())) //$NON-NLS-1$
                {
                    processOCLConstraint(constraint);
                }
            }
        }
        catch (IOException e)
        {
            Log.errorLog(e.getMessage());
        }
    }

    public void initOCLEnvironment(EPackage metamodelPackage)
    {
        initOCLEnvironment(metamodelPackage, null);
    }
    
    /**
     * Returns from the extent map all the model objects matching the given context.
     * 
     * @param query The OCL query checked/evaluated
     * @param context The context on which the rule has been applied.
     * @return An array of Object
     */
    public EObject[] getEObjectArray(Query<EClassifier, EClass, EObject> query, EClassifier context)
    {
        Set< ? extends EObject> objs = query.getExtentMap().get(context);
        return objs != null ? objs.toArray(new EObject[0]) : new EObject[0];
    }
    
    /**
     * Evaluates an OCL Query
     * 
     * @param context The current context
     * @param query rule
     * @return result of evaluation (Object)
     */
    public List< ? > evaluate(Query<EClassifier, EClass, EObject> query, EClassifier context)
    {
        List<EObject> toEvaluate = Arrays.asList(getEObjectArray(query, context));
        return query.evaluate(toEvaluate);
    }


    /**
     * Initializes the OCL environment in order to evaluate rules.
     * 
     * @param metamodelPackage The metamodel package deduced from the extracted URI
     */
    protected void initOCLEnvironment(EPackage metamodelPackage, ResourceSet set)
    {
        // Gets the OCL object
        ocl = ToolkitOCL.getOCL(metamodelPackage, set);
        oclHelper = ocl.createOCLHelper();

        // Build the extent map
        Map<EClass, Set<EObject>> extents = new HashMap<EClass, Set<EObject>>();
        ocl.setExtentMap(extents);

        if (rscSet == null)
        {
            initRscSet(null);
        }
        for (TreeIterator<Notifier> i = EcoreUtil.getAllContents(rscSet, true); i.hasNext();)
        {
            Notifier notifier = i.next();
            if (notifier instanceof EObject)
            {
                EObject eobject = (EObject) notifier;
                URI uri = eobject.eResource().getURI();
                if (uri.isFile() || uri.isPlatform())
                {
                    EClass classifier = eobject.eClass();
                    addToExtentMap(extents, classifier, eobject);

                    for (EClass superClass : classifier.getEAllSuperTypes())
                    {
                        addToExtentMap(extents, superClass, eobject);
                    }
                }
            }
        }
    }

    private void initRscSet(Resource rsc)
    {
        if (rsc == null)
        {
            rscSet = ToolkitOCL.createConfiguredResourceSet();
        }
        else
        {
            rscSet = rsc.getResourceSet();
        }
    }

    /**
     * Contribute to the build of the OCL extent map.
     * 
     * @param map The extent map
     * @param key The {@link EClass} key
     * @param value The {@link EObject} value
     */
    private void addToExtentMap(Map<EClass, Set<EObject>> map, EClass key, EObject value)
    {
        Set<EObject> s;
        if (!map.containsKey(key))
        {
            s = new HashSet<EObject>();
            map.put(key, s);
        }
        else
        {
            s = map.get(key);
        }
        s.add(value);
    }

    /**
     * Adds a OCL constraint in the result model after evaluating it.
     * 
     * @param constraint The OCL {@link Constraint}.
     * @param customMsg The eventual custom error/warning message.
     */
    protected void processOCLConstraint(Constraint constraint)
    {
        EModelElement namedElt = constraint.getConstrainedElements().get(0);
        if (namedElt instanceof EClassifier)
        {
            oclHelper.setContext((EClassifier) namedElt);
            Query<EClassifier, EClass, EObject> query = ocl.createQuery(constraint);

            // create the handler for annotations


            if (isBooleanType(query))
            {
                // it means it is a check rule
                setBooleanResult(constraint, query);
            }
            else if (isIntegerType(query))
            {
                // it means it is a statistic rule
                Log.warningLog("Rules that does not return a boolean are not supported");
            }
        }
    }

    /**
     * Creates a Boolean result
     * 
     * @param constraint The current evaluated constraint
     * @param query The OCL query
     * @param handler The OCL annotations handler for custom message integrated from the OCL Editor
     */
    protected void setBooleanResult(Constraint constraint, Query<EClassifier, EClass, EObject> query)
    {
    }



    /**
     * Tests whether the result is a boolean type
     * 
     * @return <b>true</b> if the result type is Boolean, <b>false</b> otherwise.
     */
    private boolean isBooleanType(Query<EClassifier, EClass, EObject> query)
    {
        EClassifier booleanType = EcoreEnvironmentFactory.INSTANCE.createEnvironment().getOCLStandardLibrary().getBoolean();
        return query.resultType().equals(booleanType);
    }

    /**
     * Tests whether the result is an integer type
     * 
     * @return <b>true</b> if the result type is Integer, <b>false</b> otherwise.
     */
    private boolean isIntegerType(Query<EClassifier, EClass, EObject> query)
    {
        EClassifier integerType = EcoreEnvironmentFactory.INSTANCE.createEnvironment().getOCLStandardLibrary().getInteger();
        return query.resultType().equals(integerType);
    }

    public static OCLCheckerEngineDelegate getInstance()
    {
        if (instance == null)
        {
            instance = new OCLCheckerEngineDelegate();
        }
        return instance;
    }

}
