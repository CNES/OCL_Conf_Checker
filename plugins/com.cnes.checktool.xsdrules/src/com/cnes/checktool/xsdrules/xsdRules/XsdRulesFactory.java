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

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see com.cnes.checktool.xsdrules.xsdRules.XsdRulesPackage
 * @generated
 */
public interface XsdRulesFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	XsdRulesFactory eINSTANCE = com.cnes.checktool.xsdrules.xsdRules.impl.XsdRulesFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>XSD Rule File</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>XSD Rule File</em>'.
	 * @generated
	 */
	XSDRuleFile createXSDRuleFile();

	/**
	 * Returns a new object of class '<em>XSD Result</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>XSD Result</em>'.
	 * @generated
	 */
	XSDResult createXSDResult();

	/**
	 * Returns a new object of class '<em>XSD Rule</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>XSD Rule</em>'.
	 * @generated
	 */
	XSDRule createXSDRule();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	XsdRulesPackage getXsdRulesPackage();

} //XsdRulesFactory
