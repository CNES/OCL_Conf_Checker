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
package com.cnes.checktool.results;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Rule Set Result</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.cnes.checktool.results.RuleSetResult#getRuleSet <em>Rule Set</em>}</li>
 *   <li>{@link com.cnes.checktool.results.RuleSetResult#getRuleFileResults <em>Rule File Results</em>}</li>
 * </ul>
 *
 * @see com.cnes.checktool.results.ResultsPackage#getRuleSetResult()
 * @model
 * @generated
 */
public interface RuleSetResult extends EObject {
	/**
	 * Returns the value of the '<em><b>Rule Set</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Rule Set</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rule Set</em>' containment reference.
	 * @see #setRuleSet(RuleSet)
	 * @see com.cnes.checktool.results.ResultsPackage#getRuleSetResult_RuleSet()
	 * @model containment="true" required="true"
	 * @generated
	 */
	RuleSet getRuleSet();

	/**
	 * Sets the value of the '{@link com.cnes.checktool.results.RuleSetResult#getRuleSet <em>Rule Set</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Rule Set</em>' containment reference.
	 * @see #getRuleSet()
	 * @generated
	 */
	void setRuleSet(RuleSet value);

	/**
	 * Returns the value of the '<em><b>Rule File Results</b></em>' containment reference list.
	 * The list contents are of type {@link com.cnes.checktool.results.RuleFileResult}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Rule File Results</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rule File Results</em>' containment reference list.
	 * @see com.cnes.checktool.results.ResultsPackage#getRuleSetResult_RuleFileResults()
	 * @model containment="true"
	 * @generated
	 */
	EList<RuleFileResult> getRuleFileResults();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	boolean resultIsOK();

} // RuleSetResult
