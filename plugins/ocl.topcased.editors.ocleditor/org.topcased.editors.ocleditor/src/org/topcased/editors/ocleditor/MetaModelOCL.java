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
package org.topcased.editors.ocleditor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;

public class MetaModelOCL
{

    private static final String OCL_TYPES_URI = "http://www.eclipse.org/ocl/1.1.0/OCL/Types";

    public static final List<String> KEY_WORDS = new ArrayList<String>();

    public static final String INV = "inv";

    public static final String PRE = "pre";

    public static final String POST = "post";

    public static final String BODY = "body";

    public static final String INIT = "init";

    public static final String DERIVE = "derive";

    public static final String DEFINITION = "def";

    public static final String PACKAGE = "package";

    public static final String CONTEXT = "context";
    
    //public static final Map<String, EOperation> OCLOperations = MetaModelOCL.getOCLOperations();

    private static EPackage oclModel = EPackage.Registry.INSTANCE.getEPackage(OCL_TYPES_URI);

    static
    {
        KEY_WORDS.add("and");
// Add following one
        KEY_WORDS.add("attr");
        KEY_WORDS.add("context");
        KEY_WORDS.add("def");
        KEY_WORDS.add("else");
        KEY_WORDS.add("endif");
        KEY_WORDS.add("endpackage");
        KEY_WORDS.add("if");
        KEY_WORDS.add("implies");
        KEY_WORDS.add("in");
        KEY_WORDS.add("inv");
        KEY_WORDS.add("let");
        KEY_WORDS.add("not");
// Add following one
        KEY_WORDS.add("oper");
        KEY_WORDS.add("or");
        KEY_WORDS.add("package");
        KEY_WORDS.add("post");
        KEY_WORDS.add("pre");
        KEY_WORDS.add("then");
        KEY_WORDS.add("xor");
        KEY_WORDS.add("MainModel");
        KEY_WORDS.add("SubModel");
        KEY_WORDS.add("init");
        KEY_WORDS.add("derive");
        // add to distinguish an error of the grammar
        KEY_WORDS.add("body");
        
    }

    public static boolean isDefiningRule(String word)
    {
        if (word != null)
        {
            if (word.equals("inv"))
            {
                return true;
            }
            if (word.equals("pre"))
            {
                return true;
            }
            if (word.equals("post"))
            {
                return true;
            }
            if (word.equals("init"))
            {
                return true;
            }
            if (word.equals("derive"))
            {
                return true;
            }
            if (word.equals("def"))
            {
                return true;
            }
        }
        return false;
    }

    public static boolean endOfDefiningRule(String word)
    {
        if (word != null)
        {
            if (word.equals("inv"))
            {
                return true;
            }
            if (word.equals("pre"))
            {
                return true;
            }
            if (word.equals("post"))
            {
                return true;
            }
            if (word.equals("init"))
            {
                return true;
            }
            if (word.equals("derive"))
            {
                return true;
            }
            if (word.equals("package"))
            {
                return true;
            }
            if (word.equals("endpackage"))
            {
                return true;
            }
            if (word.equals("context"))
            {
                return true;
            }
            if (word.equals("def"))
            {
                return true;
            }
        }
        return false;
    }

    public static Map<String, EOperation> getOCLOperations()
    {
        Map<String, EOperation> operations = new HashMap<String, EOperation>();
        List<EPackage> allPackages = getAllSubPackages(oclModel, new ArrayList<EPackage>());
        for (EPackage currentPackage : allPackages)
        {
            for (EClassifier classifier : currentPackage.getEClassifiers())
            {
                if (classifier instanceof EClass)
                {
                    EClass myClass = (EClass) classifier;
                    for (EOperation operation : myClass.getEOperations())
                    {
                        operations.put(new String(operation.getName()), operation);
                    }
                }
            }
        }
        return operations;
    }

    /**
     * Gets all subpackages
     * 
     * @param currentPackage
     * @param allSubPackages
     * @return
     */
    public static List<EPackage> getAllSubPackages(EPackage currentPackage, List<EPackage> allSubPackages)
    {
        allSubPackages.add(currentPackage);
        for (EPackage subPackage : currentPackage.getESubpackages())
        {
            allSubPackages.addAll(getAllSubPackages(subPackage, allSubPackages));
        }
        return allSubPackages;
    }

    /**
     * Gets the OCL Package
     * 
     * @return the OCL meta model
     */
    public static EPackage getOclPackage()
    {
        return oclModel;
    }

    /**
     * Sets the OCL package
     * 
     * @param oclPackage : the OCL meta model
     */
    public void setOCLPackage(EPackage oclPackage)
    {
        oclModel = oclPackage;
    }
}
