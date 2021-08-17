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

import com.cnes.checktool.xsdrules.xsdRules.XSDRuleFile;
import com.cnes.checktool.xsdrules.xsdRules.XsdRulesFactory;

import junit.framework.TestCase;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>XSD Rule File</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class XSDRuleFileTest extends TestCase
{

    /**
     * The fixture for this XSD Rule File test case.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XSDRuleFile fixture = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static void main(String[] args)
    {
        TestRunner.run(XSDRuleFileTest.class);
    }

    /**
     * Constructs a new XSD Rule File test case with the given name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XSDRuleFileTest(String name)
    {
        super(name);
    }

    /**
     * Sets the fixture for this XSD Rule File test case.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void setFixture(XSDRuleFile fixture)
    {
        this.fixture = fixture;
    }

    /**
     * Returns the fixture for this XSD Rule File test case.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private XSDRuleFile getFixture()
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
        setFixture(XsdRulesFactory.eINSTANCE.createXSDRuleFile());
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

} //XSDRuleFileTest
