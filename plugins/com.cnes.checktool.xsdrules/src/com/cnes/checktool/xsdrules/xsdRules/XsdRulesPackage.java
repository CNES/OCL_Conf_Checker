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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;

import com.cnes.checktool.results.ResultsPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see com.cnes.checktool.xsdrules.xsdRules.XsdRulesFactory
 * @model kind="package"
 * @generated
 */
public interface XsdRulesPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "xsdRules";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http:///www.topcased.org/checktool/xsdRules.ecore";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "xsdRules";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	XsdRulesPackage eINSTANCE = com.cnes.checktool.xsdrules.xsdRules.impl.XsdRulesPackageImpl.init();

	/**
	 * The meta object id for the '{@link com.cnes.checktool.xsdrules.xsdRules.impl.XSDRuleFileImpl <em>XSD Rule File</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.cnes.checktool.xsdrules.xsdRules.impl.XSDRuleFileImpl
	 * @see com.cnes.checktool.xsdrules.xsdRules.impl.XsdRulesPackageImpl#getXSDRuleFile()
	 * @generated
	 */
	int XSD_RULE_FILE = 0;

	/**
	 * The feature id for the '<em><b>Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XSD_RULE_FILE__RULES = ResultsPackage.RULE_FILE__RULES;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XSD_RULE_FILE__NAME = ResultsPackage.RULE_FILE__NAME;

	/**
	 * The feature id for the '<em><b>File Contents</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XSD_RULE_FILE__FILE_CONTENTS = ResultsPackage.RULE_FILE__FILE_CONTENTS;

	/**
	 * The feature id for the '<em><b>Is Valid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XSD_RULE_FILE__IS_VALID = ResultsPackage.RULE_FILE__IS_VALID;

	/**
	 * The number of structural features of the '<em>XSD Rule File</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XSD_RULE_FILE_FEATURE_COUNT = ResultsPackage.RULE_FILE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.cnes.checktool.xsdrules.xsdRules.impl.XSDResultImpl <em>XSD Result</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.cnes.checktool.xsdrules.xsdRules.impl.XSDResultImpl
	 * @see com.cnes.checktool.xsdrules.xsdRules.impl.XsdRulesPackageImpl#getXSDResult()
	 * @generated
	 */
	int XSD_RESULT = 1;

	/**
	 * The feature id for the '<em><b>Rule</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XSD_RESULT__RULE = ResultsPackage.RESULT__RULE;

	/**
	 * The feature id for the '<em><b>Result</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XSD_RESULT__RESULT = ResultsPackage.RESULT__RESULT;

	/**
	 * The feature id for the '<em><b>Failed Items</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XSD_RESULT__FAILED_ITEMS = ResultsPackage.RESULT__FAILED_ITEMS;

	/**
	 * The feature id for the '<em><b>Rule Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XSD_RESULT__RULE_NAME = ResultsPackage.RESULT__RULE_NAME;

	/**
	 * The feature id for the '<em><b>Resource Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XSD_RESULT__RESOURCE_NAME = ResultsPackage.RESULT__RESOURCE_NAME;

	/**
	 * The feature id for the '<em><b>Error Message</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XSD_RESULT__ERROR_MESSAGE = ResultsPackage.RESULT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>XSD Result</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XSD_RESULT_FEATURE_COUNT = ResultsPackage.RESULT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link com.cnes.checktool.xsdrules.xsdRules.impl.XSDRuleImpl <em>XSD Rule</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.cnes.checktool.xsdrules.xsdRules.impl.XSDRuleImpl
	 * @see com.cnes.checktool.xsdrules.xsdRules.impl.XsdRulesPackageImpl#getXSDRule()
	 * @generated
	 */
	int XSD_RULE = 2;

	/**
	 * The feature id for the '<em><b>Is Active</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XSD_RULE__IS_ACTIVE = ResultsPackage.RULE__IS_ACTIVE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XSD_RULE__NAME = ResultsPackage.RULE__NAME;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XSD_RULE__QUALIFIED_NAME = ResultsPackage.RULE__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XSD_RULE__PARENT = ResultsPackage.RULE__PARENT;

	/**
	 * The number of structural features of the '<em>XSD Rule</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XSD_RULE_FEATURE_COUNT = ResultsPackage.RULE_FEATURE_COUNT + 0;


	/**
	 * The meta object id for the '<em>IFile</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.core.resources.IFile
	 * @see com.cnes.checktool.xsdrules.xsdRules.impl.XsdRulesPackageImpl#getIFile()
	 * @generated
	 */
	int IFILE = 3;


	/**
	 * Returns the meta object for class '{@link com.cnes.checktool.xsdrules.xsdRules.XSDRuleFile <em>XSD Rule File</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>XSD Rule File</em>'.
	 * @see com.cnes.checktool.xsdrules.xsdRules.XSDRuleFile
	 * @generated
	 */
	EClass getXSDRuleFile();

	/**
	 * Returns the meta object for class '{@link com.cnes.checktool.xsdrules.xsdRules.XSDResult <em>XSD Result</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>XSD Result</em>'.
	 * @see com.cnes.checktool.xsdrules.xsdRules.XSDResult
	 * @generated
	 */
	EClass getXSDResult();

	/**
	 * Returns the meta object for the attribute '{@link com.cnes.checktool.xsdrules.xsdRules.XSDResult#getErrorMessage <em>Error Message</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Error Message</em>'.
	 * @see com.cnes.checktool.xsdrules.xsdRules.XSDResult#getErrorMessage()
	 * @see #getXSDResult()
	 * @generated
	 */
	EAttribute getXSDResult_ErrorMessage();

	/**
	 * Returns the meta object for class '{@link com.cnes.checktool.xsdrules.xsdRules.XSDRule <em>XSD Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>XSD Rule</em>'.
	 * @see com.cnes.checktool.xsdrules.xsdRules.XSDRule
	 * @generated
	 */
	EClass getXSDRule();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.core.resources.IFile <em>IFile</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>IFile</em>'.
	 * @see org.eclipse.core.resources.IFile
	 * @model instanceClass="org.eclipse.core.resources.IFile"
	 * @generated
	 */
	EDataType getIFile();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	XsdRulesFactory getXsdRulesFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link com.cnes.checktool.xsdrules.xsdRules.impl.XSDRuleFileImpl <em>XSD Rule File</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.cnes.checktool.xsdrules.xsdRules.impl.XSDRuleFileImpl
		 * @see com.cnes.checktool.xsdrules.xsdRules.impl.XsdRulesPackageImpl#getXSDRuleFile()
		 * @generated
		 */
		EClass XSD_RULE_FILE = eINSTANCE.getXSDRuleFile();

		/**
		 * The meta object literal for the '{@link com.cnes.checktool.xsdrules.xsdRules.impl.XSDResultImpl <em>XSD Result</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.cnes.checktool.xsdrules.xsdRules.impl.XSDResultImpl
		 * @see com.cnes.checktool.xsdrules.xsdRules.impl.XsdRulesPackageImpl#getXSDResult()
		 * @generated
		 */
		EClass XSD_RESULT = eINSTANCE.getXSDResult();

		/**
		 * The meta object literal for the '<em><b>Error Message</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute XSD_RESULT__ERROR_MESSAGE = eINSTANCE.getXSDResult_ErrorMessage();

		/**
		 * The meta object literal for the '{@link com.cnes.checktool.xsdrules.xsdRules.impl.XSDRuleImpl <em>XSD Rule</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.cnes.checktool.xsdrules.xsdRules.impl.XSDRuleImpl
		 * @see com.cnes.checktool.xsdrules.xsdRules.impl.XsdRulesPackageImpl#getXSDRule()
		 * @generated
		 */
		EClass XSD_RULE = eINSTANCE.getXSDRule();

		/**
		 * The meta object literal for the '<em>IFile</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.core.resources.IFile
		 * @see com.cnes.checktool.xsdrules.xsdRules.impl.XsdRulesPackageImpl#getIFile()
		 * @generated
		 */
		EDataType IFILE = eINSTANCE.getIFile();

	}

} //XsdRulesPackage
