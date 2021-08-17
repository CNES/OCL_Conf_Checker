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

import org.eclipse.core.resources.IFile;

import org.eclipse.emf.ecore.EObject;
import com.cnes.checktool.results.RuleFile;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>XSD Rule File</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see com.cnes.checktool.xsdrules.xsdRules.XsdRulesPackage#getXSDRuleFile()
 * @model
 * @generated
 */
public interface XSDRuleFile extends RuleFile {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	String getFileExtension();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" dataType="com.cnes.checktool.xsdrules.xsdRules.IFile"
	 * @generated
	 */
	IFile getFile();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model iFileDataType="com.cnes.checktool.xsdrules.xsdRules.IFile"
	 * @generated
	 */
	void setFile(IFile iFile);
} // XSDRuleFile
