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
package results.tests;

import com.cnes.checktool.results.Result;
import com.cnes.checktool.results.ResultsFactory;

import junit.framework.TestCase;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Result</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are tested:
 * <ul>
 *   <li>{@link results.Result#getRuleName() <em>Rule Name</em>}</li>
 * </ul>
 * </p>
 * @generated
 */
public class ResultTest extends TestCase
{

    /**
     * The fixture for this Result test case.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected Result fixture = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static void main(String[] args)
    {
        TestRunner.run(ResultTest.class);
    }

    /**
     * Constructs a new Result test case with the given name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ResultTest(String name)
    {
        super(name);
    }

    /**
     * Sets the fixture for this Result test case.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void setFixture(Result fixture)
    {
        this.fixture = fixture;
    }

    /**
     * Returns the fixture for this Result test case.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected Result getFixture()
    {
        return fixture;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see junit.framework.TestCase#setUp()
     * @generated
     */
    @Override
    protected void setUp() throws Exception
    {
        setFixture(ResultsFactory.eINSTANCE.createResult());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see junit.framework.TestCase#tearDown()
     * @generated
     */
    @Override
    protected void tearDown() throws Exception
    {
        setFixture(null);
    }

    /**
     * Tests the '{@link results.Result#getRuleName() <em>Rule Name</em>}' feature getter.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see results.Result#getRuleName()
     * @generated
     */
    public void testGetRuleName()
    {
        // TODO: implement this feature getter test method
        // Ensure that you remove @generated or mark it @generated NOT
        fail();
    }

    /**
     * Tests the '{@link results.Result#setRuleName(java.lang.String) <em>Rule Name</em>}' feature setter.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see results.Result#setRuleName(java.lang.String)
     * @generated
     */
    public void testSetRuleName()
    {
        // TODO: implement this feature setter test method
        // Ensure that you remove @generated or mark it @generated NOT
        fail();
    }

} //ResultTest
