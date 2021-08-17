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
package xsdRules.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test suite for the '<em><b>xsdRules</b></em>' package.
 * <!-- end-user-doc -->
 * @generated
 */
public class XsdRulesTests extends TestSuite
{

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static void main(String[] args)
    {
        TestRunner.run(suite());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static Test suite()
    {
        TestSuite suite = new XsdRulesTests("xsdRules Tests");
        suite.addTestSuite(XSDResultTest.class);
        return suite;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XsdRulesTests(String name)
    {
        super(name);
    }

} //XsdRulesTests
