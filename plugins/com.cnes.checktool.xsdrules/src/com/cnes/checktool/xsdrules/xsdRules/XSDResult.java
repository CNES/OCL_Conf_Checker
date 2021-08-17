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
package com.cnes.checktool.xsdrules.xsdRules;

import com.cnes.checktool.results.Result;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>XSD Result</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.cnes.checktool.xsdrules.xsdRules.XSDResult#getErrorMessage <em>Error Message</em>}</li>
 * </ul>
 *
 * @see com.cnes.checktool.xsdrules.xsdRules.XsdRulesPackage#getXSDResult()
 * @model
 * @generated
 */
public interface XSDResult extends Result {
	/**
	 * Returns the value of the '<em><b>Error Message</b></em>' attribute.
	 * The default value is <code>"\"\""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Error Message</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Error Message</em>' attribute.
	 * @see #setErrorMessage(String)
	 * @see com.cnes.checktool.xsdrules.xsdRules.XsdRulesPackage#getXSDResult_ErrorMessage()
	 * @model default="\"\""
	 * @generated
	 */
	String getErrorMessage();

	/**
	 * Sets the value of the '{@link com.cnes.checktool.xsdrules.xsdRules.XSDResult#getErrorMessage <em>Error Message</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Error Message</em>' attribute.
	 * @see #getErrorMessage()
	 * @generated
	 */
	void setErrorMessage(String value);

} // XSDResult
