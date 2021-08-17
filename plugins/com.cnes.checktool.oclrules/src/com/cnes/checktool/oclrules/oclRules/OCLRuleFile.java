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

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;
import com.cnes.checktool.oclrules.oclRules.util.OCLResource;
import com.cnes.checktool.results.RuleFile;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>OCL Rule File</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see com.cnes.checktool.oclrules.oclRules.OclRulesPackage#getOCLRuleFile()
 * @model
 * @generated
 */
public interface OCLRuleFile extends RuleFile {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" dataType="com.cnes.checktool.oclrules.oclRules.OCLResource"
	 * @generated
	 */
	OCLResource getResource();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model resourcesMany="true"
	 * @generated
	 */
	void completeFiles(EList resources);
} // OCLRuleFile
