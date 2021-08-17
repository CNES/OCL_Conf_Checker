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

import com.cnes.checktool.oclrules.oclRules.OCLRuleFile;
import com.cnes.checktool.oclrules.oclRules.OclRulesFactory;

import junit.framework.TestCase;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>OCL Rule File</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class OCLRuleFileTest extends TestCase
{

    /**
     * The fixture for this OCL Rule File test case.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected OCLRuleFile fixture = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static void main(String[] args)
    {
        TestRunner.run(OCLRuleFileTest.class);
    }

    /**
     * Constructs a new OCL Rule File test case with the given name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public OCLRuleFileTest(String name)
    {
        super(name);
    }

    /**
     * Sets the fixture for this OCL Rule File test case.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void setFixture(OCLRuleFile fixture)
    {
        this.fixture = fixture;
    }

    /**
     * Returns the fixture for this OCL Rule File test case.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private OCLRuleFile getFixture()
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
        setFixture(OclRulesFactory.eINSTANCE.createOCLRuleFile());
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

} //OCLRuleFileTest
