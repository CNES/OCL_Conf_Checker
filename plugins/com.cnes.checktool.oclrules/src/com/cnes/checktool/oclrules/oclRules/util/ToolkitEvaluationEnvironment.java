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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.ocl.EvaluationEnvironment;
import org.eclipse.ocl.ecore.EcoreEvaluationEnvironment;
import org.eclipse.ocl.ecore.EcoreOCLStandardLibrary;

/**
 * Implementation of the {@link EvaluationEnvironment} for evaluation of OCL expressions on instances of Ecore models
 * (i.e., on M0 models).
 * 
 * @author Tim Klinger (tklinger)
 * @author Christian W. Damus (cdamus)
 */
public class ToolkitEvaluationEnvironment extends EcoreEvaluationEnvironment
{

    private EcoreOCLStandardLibrary ecoreLibrary;
    
    /**
     * Initializes me.
     */
    public ToolkitEvaluationEnvironment()
    {
        super();
        ecoreLibrary = new EcoreOCLStandardLibrary();
    }

    /**
     * Initializes me with my parent evaluation environment (nesting scope).
     * 
     * @param parent my parent (nesting scope); must not be <code>null</code>
     */
    public ToolkitEvaluationEnvironment(EvaluationEnvironment<EClassifier, EOperation, EStructuralFeature, EClass, EObject> parent)
    {
        super(parent);
    }

    /**
     * TODO : Add and Define here your own method body.
     * 
     * @see org.eclipse.ocl.AbstractEvaluationEnvironment#callOperation(java.lang.Object, int, java.lang.Object,
     *      java.lang.Object[])
     */
    @Override
    public Object callOperation(EOperation operation, int opcode, Object source, Object[] args) throws IllegalArgumentException
    {
        if (operation.getEAnnotation("ToolKitOCLEnvironment") != null)
        {
            // For the SAM Environment some additional methods have been defined
            // Here are listed the implementation of each added method.
            if ("oclType".equals(operation.getName()))
            {
                // the type of null is OclVoid
                if (source == null)
                {
                    return ecoreLibrary.getOclVoid();
                }

                // the type of OclInvalid is Invalid
                if (source == ecoreLibrary.getInvalid())
                {
                    return ecoreLibrary.getInvalid();
                }

                // non-EObject source
                if (source instanceof EObject)
                {
                    return ((EObject) source).eClass();
                }
                else
                {
                    // get the actual (runtime) java type of the source object
                    // the actual source type must exactly match the argument type
                    return source.getClass();
                }
            }
            else if ("regexMatch".equals(operation.getName()))
            {
                Pattern pattern = Pattern.compile((String) args[0]);
                String sourceArg = "";
                Matcher matcher = null;
                List<String> result = new ArrayList<String>();

                if (source != null)
                {
                    sourceArg = (String) source;
                }
                matcher = pattern.matcher(sourceArg);

                if (matcher.matches())
                {
                    for (int i = 1; i <= matcher.groupCount(); i++)
                    {
                        result.add(matcher.group(i));
                    }
                }

                return result;
            }
            else if ("split".equals(operation.getName()))
            {
                Pattern pattern = Pattern.compile((String) args[0]);
                String sourceArg = "";
                String[] result = null;

                if (source != null)
                {
                    sourceArg = (String) source;
                }
                result = pattern.split(sourceArg);

                return Arrays.<String> asList(result);
            }
            else if ("hexStringToInt".equals(operation.getName()))
            {
            	String str[]=((String) source).toLowerCase().split("x");
        		return Long.valueOf(str[1],16);
            }
            else if ("trim".equals(operation.getName()))
            {
                return ((String) source).trim();
            }
            else if ("charAt".equals(operation.getName()))
            {
                return String.valueOf((((String) source).charAt(((Integer) args[0]).intValue())));
            }
            else if ("startsWith".equals(operation.getName()))
            {
                return ((String) source).startsWith((((String) args[0])));
            }
            else if ("endsWith".equals(operation.getName()))
            {
                return ((String) source).endsWith((((String) args[0])));
            }
        }
        return super.callOperation(operation, opcode, source, args);
    }
}