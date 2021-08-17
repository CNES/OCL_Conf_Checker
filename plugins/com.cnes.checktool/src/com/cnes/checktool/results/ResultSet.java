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
 * A representation of the model object '<em><b>Result Set</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.cnes.checktool.results.ResultSet#getRuleSetResults <em>Rule Set Results</em>}</li>
 * </ul>
 *
 * @see com.cnes.checktool.results.ResultsPackage#getResultSet()
 * @model
 * @generated
 */
public interface ResultSet extends EObject {
	/**
	 * Returns the value of the '<em><b>Rule Set Results</b></em>' containment reference list.
	 * The list contents are of type {@link com.cnes.checktool.results.RuleSetResult}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Rule Set Results</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rule Set Results</em>' containment reference list.
	 * @see com.cnes.checktool.results.ResultsPackage#getResultSet_RuleSetResults()
	 * @model containment="true"
	 * @generated
	 */
	EList<RuleSetResult> getRuleSetResults();

} // ResultSet
