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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

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
 * @see com.cnes.checktool.oclrules.oclRules.OclRulesFactory
 * @model kind="package"
 * @generated
 */
public interface OclRulesPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "oclRules";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http:///www.topcased.org/checktool/oclRules.ecore";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "oclRules";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	OclRulesPackage eINSTANCE = com.cnes.checktool.oclrules.oclRules.impl.OclRulesPackageImpl.init();

	/**
	 * The meta object id for the '{@link com.cnes.checktool.oclrules.oclRules.impl.OCLRuleImpl <em>OCL Rule</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.cnes.checktool.oclrules.oclRules.impl.OCLRuleImpl
	 * @see com.cnes.checktool.oclrules.oclRules.impl.OclRulesPackageImpl#getOCLRule()
	 * @generated
	 */
	int OCL_RULE = 0;

	/**
	 * The feature id for the '<em><b>Is Active</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_RULE__IS_ACTIVE = ResultsPackage.RULE__IS_ACTIVE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_RULE__NAME = ResultsPackage.RULE__NAME;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_RULE__QUALIFIED_NAME = ResultsPackage.RULE__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_RULE__PARENT = ResultsPackage.RULE__PARENT;

	/**
	 * The feature id for the '<em><b>Query Body</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_RULE__QUERY_BODY = ResultsPackage.RULE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Context</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_RULE__CONTEXT = ResultsPackage.RULE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Constraint Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_RULE__CONSTRAINT_NAME = ResultsPackage.RULE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>OCL Rule</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_RULE_FEATURE_COUNT = ResultsPackage.RULE_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link com.cnes.checktool.oclrules.oclRules.impl.OCLRuleFileImpl <em>OCL Rule File</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.cnes.checktool.oclrules.oclRules.impl.OCLRuleFileImpl
	 * @see com.cnes.checktool.oclrules.oclRules.impl.OclRulesPackageImpl#getOCLRuleFile()
	 * @generated
	 */
	int OCL_RULE_FILE = 1;

	/**
	 * The feature id for the '<em><b>Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_RULE_FILE__RULES = ResultsPackage.RULE_FILE__RULES;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_RULE_FILE__NAME = ResultsPackage.RULE_FILE__NAME;

	/**
	 * The feature id for the '<em><b>File Contents</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_RULE_FILE__FILE_CONTENTS = ResultsPackage.RULE_FILE__FILE_CONTENTS;

	/**
	 * The feature id for the '<em><b>Is Valid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_RULE_FILE__IS_VALID = ResultsPackage.RULE_FILE__IS_VALID;

	/**
	 * The number of structural features of the '<em>OCL Rule File</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCL_RULE_FILE_FEATURE_COUNT = ResultsPackage.RULE_FILE_FEATURE_COUNT + 0;


	/**
	 * The meta object id for the '<em>OCL Resource</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.cnes.checktool.oclrules.oclRules.util.OCLResource
	 * @see com.cnes.checktool.oclrules.oclRules.impl.OclRulesPackageImpl#getOCLResource()
	 * @generated
	 */
	int OCL_RESOURCE = 2;


	/**
	 * Returns the meta object for class '{@link com.cnes.checktool.oclrules.oclRules.OCLRule <em>OCL Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>OCL Rule</em>'.
	 * @see com.cnes.checktool.oclrules.oclRules.OCLRule
	 * @generated
	 */
	EClass getOCLRule();

	/**
	 * Returns the meta object for the attribute '{@link com.cnes.checktool.oclrules.oclRules.OCLRule#getQueryBody <em>Query Body</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Query Body</em>'.
	 * @see com.cnes.checktool.oclrules.oclRules.OCLRule#getQueryBody()
	 * @see #getOCLRule()
	 * @generated
	 */
	EAttribute getOCLRule_QueryBody();

	/**
	 * Returns the meta object for the reference '{@link com.cnes.checktool.oclrules.oclRules.OCLRule#getContext <em>Context</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Context</em>'.
	 * @see com.cnes.checktool.oclrules.oclRules.OCLRule#getContext()
	 * @see #getOCLRule()
	 * @generated
	 */
	EReference getOCLRule_Context();

	/**
	 * Returns the meta object for the attribute '{@link com.cnes.checktool.oclrules.oclRules.OCLRule#getConstraintName <em>Constraint Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Constraint Name</em>'.
	 * @see com.cnes.checktool.oclrules.oclRules.OCLRule#getConstraintName()
	 * @see #getOCLRule()
	 * @generated
	 */
	EAttribute getOCLRule_ConstraintName();

	/**
	 * Returns the meta object for class '{@link com.cnes.checktool.oclrules.oclRules.OCLRuleFile <em>OCL Rule File</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>OCL Rule File</em>'.
	 * @see com.cnes.checktool.oclrules.oclRules.OCLRuleFile
	 * @generated
	 */
	EClass getOCLRuleFile();

	/**
	 * Returns the meta object for data type '{@link com.cnes.checktool.oclrules.oclRules.util.OCLResource <em>OCL Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>OCL Resource</em>'.
	 * @see com.cnes.checktool.oclrules.oclRules.util.OCLResource
	 * @model instanceClass="com.cnes.checktool.oclrules.oclRules.util.OCLResource"
	 * @generated
	 */
	EDataType getOCLResource();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	OclRulesFactory getOclRulesFactory();

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
		 * The meta object literal for the '{@link com.cnes.checktool.oclrules.oclRules.impl.OCLRuleImpl <em>OCL Rule</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.cnes.checktool.oclrules.oclRules.impl.OCLRuleImpl
		 * @see com.cnes.checktool.oclrules.oclRules.impl.OclRulesPackageImpl#getOCLRule()
		 * @generated
		 */
		EClass OCL_RULE = eINSTANCE.getOCLRule();

		/**
		 * The meta object literal for the '<em><b>Query Body</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OCL_RULE__QUERY_BODY = eINSTANCE.getOCLRule_QueryBody();

		/**
		 * The meta object literal for the '<em><b>Context</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OCL_RULE__CONTEXT = eINSTANCE.getOCLRule_Context();

		/**
		 * The meta object literal for the '<em><b>Constraint Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OCL_RULE__CONSTRAINT_NAME = eINSTANCE.getOCLRule_ConstraintName();

		/**
		 * The meta object literal for the '{@link com.cnes.checktool.oclrules.oclRules.impl.OCLRuleFileImpl <em>OCL Rule File</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.cnes.checktool.oclrules.oclRules.impl.OCLRuleFileImpl
		 * @see com.cnes.checktool.oclrules.oclRules.impl.OclRulesPackageImpl#getOCLRuleFile()
		 * @generated
		 */
		EClass OCL_RULE_FILE = eINSTANCE.getOCLRuleFile();

		/**
		 * The meta object literal for the '<em>OCL Resource</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.cnes.checktool.oclrules.oclRules.util.OCLResource
		 * @see com.cnes.checktool.oclrules.oclRules.impl.OclRulesPackageImpl#getOCLResource()
		 * @generated
		 */
		EDataType OCL_RESOURCE = eINSTANCE.getOCLResource();

	}

} //OclRulesPackage
