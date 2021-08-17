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
package com.cnes.checktool.oclrules.oclRules;

import org.eclipse.emf.ecore.EClassifier;

import org.eclipse.emf.ecore.EObject;
import com.cnes.checktool.results.Rule;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>OCL Rule</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.cnes.checktool.oclrules.oclRules.OCLRule#getQueryBody <em>Query Body</em>}</li>
 *   <li>{@link com.cnes.checktool.oclrules.oclRules.OCLRule#getContext <em>Context</em>}</li>
 *   <li>{@link com.cnes.checktool.oclrules.oclRules.OCLRule#getConstraintName <em>Constraint Name</em>}</li>
 * </ul>
 *
 * @see com.cnes.checktool.oclrules.oclRules.OclRulesPackage#getOCLRule()
 * @model
 * @generated
 */
public interface OCLRule extends Rule {
	/**
	 * Returns the value of the '<em><b>Query Body</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Query Body</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Query Body</em>' attribute.
	 * @see #setQueryBody(String)
	 * @see com.cnes.checktool.oclrules.oclRules.OclRulesPackage#getOCLRule_QueryBody()
	 * @model
	 * @generated
	 */
	String getQueryBody();

	/**
	 * Sets the value of the '{@link com.cnes.checktool.oclrules.oclRules.OCLRule#getQueryBody <em>Query Body</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Query Body</em>' attribute.
	 * @see #getQueryBody()
	 * @generated
	 */
	void setQueryBody(String value);

	/**
	 * Returns the value of the '<em><b>Context</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Context</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Context</em>' reference.
	 * @see #setContext(EClassifier)
	 * @see com.cnes.checktool.oclrules.oclRules.OclRulesPackage#getOCLRule_Context()
	 * @model
	 * @generated
	 */
	EClassifier getContext();

	/**
	 * Sets the value of the '{@link com.cnes.checktool.oclrules.oclRules.OCLRule#getContext <em>Context</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Context</em>' reference.
	 * @see #getContext()
	 * @generated
	 */
	void setContext(EClassifier value);

	/**
	 * Returns the value of the '<em><b>Constraint Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Constraint Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Constraint Name</em>' attribute.
	 * @see #setConstraintName(String)
	 * @see com.cnes.checktool.oclrules.oclRules.OclRulesPackage#getOCLRule_ConstraintName()
	 * @model
	 * @generated
	 */
	String getConstraintName();

	/**
	 * Sets the value of the '{@link com.cnes.checktool.oclrules.oclRules.OCLRule#getConstraintName <em>Constraint Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Constraint Name</em>' attribute.
	 * @see #getConstraintName()
	 * @generated
	 */
	void setConstraintName(String value);

} // OCLRule
