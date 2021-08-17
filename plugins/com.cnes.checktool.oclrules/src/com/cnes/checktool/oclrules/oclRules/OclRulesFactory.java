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

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see com.cnes.checktool.oclrules.oclRules.OclRulesPackage
 * @generated
 */
public interface OclRulesFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	OclRulesFactory eINSTANCE = com.cnes.checktool.oclrules.oclRules.impl.OclRulesFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>OCL Rule</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>OCL Rule</em>'.
	 * @generated
	 */
	OCLRule createOCLRule();

	/**
	 * Returns a new object of class '<em>OCL Rule File</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>OCL Rule File</em>'.
	 * @generated
	 */
	OCLRuleFile createOCLRuleFile();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	OclRulesPackage getOclRulesPackage();

	OCLRuleFile createRuleFile(IFile iFile);

} //OclRulesFactory
