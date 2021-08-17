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
package oclRules.tests;

import com.cnes.checktool.oclrules.oclRules.OCLRule;
import com.cnes.checktool.oclrules.oclRules.OclRulesFactory;

import junit.framework.TestCase;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>OCL Rule</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class OCLRuleTest extends TestCase
{

    /**
     * The fixture for this OCL Rule test case.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected OCLRule fixture = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static void main(String[] args)
    {
        TestRunner.run(OCLRuleTest.class);
    }

    /**
     * Constructs a new OCL Rule test case with the given name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public OCLRuleTest(String name)
    {
        super(name);
    }

    /**
     * Sets the fixture for this OCL Rule test case.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void setFixture(OCLRule fixture)
    {
        this.fixture = fixture;
    }

    /**
     * Returns the fixture for this OCL Rule test case.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private OCLRule getFixture()
    {
        return fixture;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see junit.framework.TestCase#setUp()
     * @generated
     */
    protected void setUp() throws Exception
    {
        setFixture(OclRulesFactory.eINSTANCE.createOCLRule());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see junit.framework.TestCase#tearDown()
     * @generated
     */
    protected void tearDown() throws Exception
    {
        setFixture(null);
    }

} //OCLRuleTest
