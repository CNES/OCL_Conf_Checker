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

import com.cnes.checktool.xsdrules.xsdRules.XSDRule;
import com.cnes.checktool.xsdrules.xsdRules.XsdRulesFactory;

import junit.framework.TestCase;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>XSD Rule</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class XSDRuleTest extends TestCase
{

    /**
     * The fixture for this XSD Rule test case.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XSDRule fixture = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static void main(String[] args)
    {
        TestRunner.run(XSDRuleTest.class);
    }

    /**
     * Constructs a new XSD Rule test case with the given name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XSDRuleTest(String name)
    {
        super(name);
    }

    /**
     * Sets the fixture for this XSD Rule test case.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void setFixture(XSDRule fixture)
    {
        this.fixture = fixture;
    }

    /**
     * Returns the fixture for this XSD Rule test case.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private XSDRule getFixture()
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
        setFixture(XsdRulesFactory.eINSTANCE.createXSDRule());
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

} //XSDRuleTest
