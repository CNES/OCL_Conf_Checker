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
package com.cnes.checktool.oclrules;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.ocl.OCLInput;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.Query;
import org.eclipse.ocl.ecore.Constraint;

import com.cnes.checktool.Log;
import com.cnes.checktool.oclrules.oclRules.OCLRule;
import com.cnes.checktool.oclrules.oclRules.util.OCLCheckerEngineDelegate;
import com.cnes.checktool.oclrules.oclRules.util.OCLResource;
import com.cnes.checktool.oclrules.oclRules.util.QueryTypeExtentMap;
import com.cnes.checktool.oclrules.oclRules.util.ToolkitOCL;
import com.cnes.checktool.results.Result;
import com.cnes.checktool.results.ResultsFactory;
import com.cnes.checktool.results.Rule;
import com.cnes.checktool.results.RuleFileResult;

public class ChecktoolOCLCheckerEngine extends OCLCheckerEngineDelegate
{
    private static ChecktoolOCLCheckerEngine instance;

    private RuleFileResult rfResult;

    @Override
    protected void setBooleanResult(Constraint constraint, Query<EClassifier, EClass, EObject> query)
    {
        // This is called by the OCL Checker after a constraint has been evaluated
        // We add our hook here to add to the result model that was set during our first call
        Result result = null;
        // Get all the rules in the ruleFile and find the one matching this constraint
        for (Rule rule : rfResult.getRuleFile().getRules())
        {
            if (rule instanceof OCLRule && ((OCLRule) rule).getConstraintName().equals(constraint.getName()) && rule.isIsActive())
            {
                // This is the right rule. Try to find a corresponding rule or create it.
                for (Result testedResult : rfResult.getRuleResults())
                {
                    if (rule.equals(testedResult.getRule()))
                    {
                        result = testedResult;
                        break;
                    }
                }
                if (result == null)
                {
                    // Creation should only happen on first evaluation of a given constraint
                    result = ResultsFactory.eINSTANCE.createResult();
                    result.setRule(rule);
                    rfResult.getRuleResults().add(result);
                    break;
                }
            }
        }

        if (result != null)
        {
            Object[] objectsToEvaluate = getEObjectArray(query, oclHelper.getContextClassifier());
            Object[] evaluations = evaluate(query, oclHelper.getContextClassifier()).toArray();

            if (evaluations.length > 0)
            {
                if (objectsToEvaluate[0] instanceof EObject)
                {
                    result.setResourceName(((EObject) objectsToEvaluate[0]).eResource().getURI().lastSegment());
                }
            }

            for (int i = 0; i < evaluations.length; i++)
            {
                if (objectsToEvaluate[i] instanceof EObject)
                {
                    if (!(evaluations[i] instanceof Boolean) || "false".equals(evaluations[i].toString()))
                    {
                        EObject eObject = (EObject) objectsToEvaluate[i];
                        result.getFailedItems().add(getString(eObject));
                    }
                }
            }
            result.setResult(result.getFailedItems().isEmpty());
        }
    }

    private String getString(EObject eObject)
    {
        EClass clazz = eObject.eClass();
        if (clazz instanceof EDataType || clazz instanceof EEnumLiteral)
        {
            return eObject.toString();
        }
        else
        {
            StringBuilder builder = new StringBuilder();
            builder.append(clazz.getName());
            for (EStructuralFeature feature : clazz.getEAllStructuralFeatures())
            {
                EClassifier type = feature.getEType();
                if ((type instanceof EDataType || clazz instanceof EEnumLiteral) && !feature.isMany())
                {
                    Object value = eObject.eGet(feature);
                    builder.append("\n");
                    String valueString = (value == null ? "null" : value.toString());
                    builder.append(feature.getName() + " - " + valueString);
                }
            }
            return builder.toString();
        }
    }

    public static ChecktoolOCLCheckerEngine getInstance()
    {
        if (instance == null)
        {
            instance = new ChecktoolOCLCheckerEngine();
        }
        return instance;
    }

    public void evaluateOCLRules(Resource resource, List<OCLResource> singletonList, RuleFileResult rfResult)
    {
        if (!(ocl.getExtentMap() instanceof QueryTypeExtentMap)){
            ocl.setExtentMap(new QueryTypeExtentMap(resource.getResourceSet()));
        }
        this.rfResult = rfResult;
        evaluateOCLRules(resource, singletonList);
    }

    @Override
    protected void initOCLEnvironment(EPackage metamodelPackage, ResourceSet set)
    {
        // Gets the OCL object
        ocl = ToolkitOCL.getOCL(metamodelPackage, set);
        oclHelper = ocl.createOCLHelper();

        // Build the extent map
        Map<EClass, Set< ? extends EObject>> extents = new QueryTypeExtentMap(set);
        ocl.setExtentMap(extents);
    }

    public List<Constraint> getRulesFromFile(List<Resource> processedResources, OCLResource file, String name)
    {
        try
        {
            InputStream inStream = file.getContent();
            URI mainNsURI = file.getURI();
            if (mainNsURI == null) {
            	Log.errorLog("Metamodel URI not found");
            	return Collections.emptyList();
            }
            EPackage metamodelPackage = EPackage.Registry.INSTANCE.getEPackage(mainNsURI.toString());
            if (metamodelPackage == null)
            {
                Log.warningLog("Could not find metamodel " + mainNsURI.toString() + ". OCL File " + name
                        + "could not be parsed.");
                return Collections.emptyList();
            }

            ResourceSet resourceSet = processedResources.iterator().next().getResourceSet();
//            Map<String, Boolean> options = new HashMap<String, Boolean>();
//            options.put(XMLResource.OPTION_SCHEMA_LOCATION, Boolean.TRUE);
            resourceSet.getLoadOptions().put(XMLResource.OPTION_SCHEMA_LOCATION, Boolean.TRUE);
            initOCLEnvironment(metamodelPackage,resourceSet);
            ocl.setExtentMap(new QueryTypeExtentMap(resourceSet));

            try
            {
            	OCLInput document = new OCLInput(inStream);
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

}
