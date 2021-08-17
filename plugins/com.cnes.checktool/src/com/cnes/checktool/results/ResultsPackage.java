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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

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
 * @see com.cnes.checktool.results.ResultsFactory
 * @model kind="package"
 * @generated
 */
public interface ResultsPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "results";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http:///www.topcased.org/checktool/results.ecore";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "results";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ResultsPackage eINSTANCE = com.cnes.checktool.results.impl.ResultsPackageImpl.init();

	/**
	 * The meta object id for the '{@link com.cnes.checktool.results.impl.RuleSetImpl <em>Rule Set</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.cnes.checktool.results.impl.RuleSetImpl
	 * @see com.cnes.checktool.results.impl.ResultsPackageImpl#getRuleSet()
	 * @generated
	 */
	int RULE_SET = 0;

	/**
	 * The feature id for the '<em><b>Checksum</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_SET__CHECKSUM = 0;

	/**
	 * The feature id for the '<em><b>Rule Files</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_SET__RULE_FILES = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_SET__NAME = 2;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_SET__VERSION = 3;

	/**
	 * The number of structural features of the '<em>Rule Set</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_SET_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link com.cnes.checktool.results.impl.RuleImpl <em>Rule</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.cnes.checktool.results.impl.RuleImpl
	 * @see com.cnes.checktool.results.impl.ResultsPackageImpl#getRule()
	 * @generated
	 */
	int RULE = 1;

	/**
	 * The feature id for the '<em><b>Is Active</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE__IS_ACTIVE = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE__NAME = 1;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE__QUALIFIED_NAME = 2;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE__PARENT = 3;

	/**
	 * The number of structural features of the '<em>Rule</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link com.cnes.checktool.results.impl.CMetamodelImpl <em>CMetamodel</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.cnes.checktool.results.impl.CMetamodelImpl
	 * @see com.cnes.checktool.results.impl.ResultsPackageImpl#getCMetamodel()
	 * @generated
	 */
	int CMETAMODEL = 2;

	/**
	 * The number of structural features of the '<em>CMetamodel</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CMETAMODEL_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.cnes.checktool.results.impl.ConfigurationImpl <em>Configuration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.cnes.checktool.results.impl.ConfigurationImpl
	 * @see com.cnes.checktool.results.impl.ResultsPackageImpl#getConfiguration()
	 * @generated
	 */
	int CONFIGURATION = 3;

	/**
	 * The number of structural features of the '<em>Configuration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.cnes.checktool.results.impl.ResultSetImpl <em>Result Set</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.cnes.checktool.results.impl.ResultSetImpl
	 * @see com.cnes.checktool.results.impl.ResultsPackageImpl#getResultSet()
	 * @generated
	 */
	int RESULT_SET = 4;

	/**
	 * The feature id for the '<em><b>Rule Set Results</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESULT_SET__RULE_SET_RESULTS = 0;

	/**
	 * The number of structural features of the '<em>Result Set</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESULT_SET_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link com.cnes.checktool.results.impl.RuleSetResultImpl <em>Rule Set Result</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.cnes.checktool.results.impl.RuleSetResultImpl
	 * @see com.cnes.checktool.results.impl.ResultsPackageImpl#getRuleSetResult()
	 * @generated
	 */
	int RULE_SET_RESULT = 5;

	/**
	 * The feature id for the '<em><b>Rule Set</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_SET_RESULT__RULE_SET = 0;

	/**
	 * The feature id for the '<em><b>Rule File Results</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_SET_RESULT__RULE_FILE_RESULTS = 1;

	/**
	 * The number of structural features of the '<em>Rule Set Result</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_SET_RESULT_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link com.cnes.checktool.results.impl.ResultImpl <em>Result</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.cnes.checktool.results.impl.ResultImpl
	 * @see com.cnes.checktool.results.impl.ResultsPackageImpl#getResult()
	 * @generated
	 */
	int RESULT = 6;

	/**
	 * The feature id for the '<em><b>Rule</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESULT__RULE = 0;

	/**
	 * The feature id for the '<em><b>Result</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESULT__RESULT = 1;

	/**
	 * The feature id for the '<em><b>Failed Items</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESULT__FAILED_ITEMS = 2;

	/**
	 * The feature id for the '<em><b>Rule Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESULT__RULE_NAME = 3;

	/**
	 * The feature id for the '<em><b>Resource Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESULT__RESOURCE_NAME = 4;

	/**
	 * The number of structural features of the '<em>Result</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESULT_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link com.cnes.checktool.results.impl.RuleFileImpl <em>Rule File</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.cnes.checktool.results.impl.RuleFileImpl
	 * @see com.cnes.checktool.results.impl.ResultsPackageImpl#getRuleFile()
	 * @generated
	 */
	int RULE_FILE = 7;

	/**
	 * The feature id for the '<em><b>Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_FILE__RULES = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_FILE__NAME = 1;

	/**
	 * The feature id for the '<em><b>File Contents</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_FILE__FILE_CONTENTS = 2;

	/**
	 * The feature id for the '<em><b>Is Valid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_FILE__IS_VALID = 3;

	/**
	 * The number of structural features of the '<em>Rule File</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_FILE_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link com.cnes.checktool.results.impl.RuleFileResultImpl <em>Rule File Result</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.cnes.checktool.results.impl.RuleFileResultImpl
	 * @see com.cnes.checktool.results.impl.ResultsPackageImpl#getRuleFileResult()
	 * @generated
	 */
	int RULE_FILE_RESULT = 8;

	/**
	 * The feature id for the '<em><b>Rule Results</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_FILE_RESULT__RULE_RESULTS = 0;

	/**
	 * The feature id for the '<em><b>Rule File</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_FILE_RESULT__RULE_FILE = 1;

	/**
	 * The feature id for the '<em><b>Rule File Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_FILE_RESULT__RULE_FILE_NAME = 2;

	/**
	 * The number of structural features of the '<em>Rule File Result</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_FILE_RESULT_FEATURE_COUNT = 3;


	/**
	 * Returns the meta object for class '{@link com.cnes.checktool.results.RuleSet <em>Rule Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Rule Set</em>'.
	 * @see com.cnes.checktool.results.RuleSet
	 * @generated
	 */
	EClass getRuleSet();

	/**
	 * Returns the meta object for the attribute '{@link com.cnes.checktool.results.RuleSet#getChecksum <em>Checksum</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Checksum</em>'.
	 * @see com.cnes.checktool.results.RuleSet#getChecksum()
	 * @see #getRuleSet()
	 * @generated
	 */
	EAttribute getRuleSet_Checksum();

	/**
	 * Returns the meta object for the containment reference list '{@link com.cnes.checktool.results.RuleSet#getRuleFiles <em>Rule Files</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Rule Files</em>'.
	 * @see com.cnes.checktool.results.RuleSet#getRuleFiles()
	 * @see #getRuleSet()
	 * @generated
	 */
	EReference getRuleSet_RuleFiles();

	/**
	 * Returns the meta object for the attribute '{@link com.cnes.checktool.results.RuleSet#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.cnes.checktool.results.RuleSet#getName()
	 * @see #getRuleSet()
	 * @generated
	 */
	EAttribute getRuleSet_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.cnes.checktool.results.RuleSet#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see com.cnes.checktool.results.RuleSet#getVersion()
	 * @see #getRuleSet()
	 * @generated
	 */
	EAttribute getRuleSet_Version();

	/**
	 * Returns the meta object for class '{@link com.cnes.checktool.results.Rule <em>Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Rule</em>'.
	 * @see com.cnes.checktool.results.Rule
	 * @generated
	 */
	EClass getRule();

	/**
	 * Returns the meta object for the attribute '{@link com.cnes.checktool.results.Rule#isIsActive <em>Is Active</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Active</em>'.
	 * @see com.cnes.checktool.results.Rule#isIsActive()
	 * @see #getRule()
	 * @generated
	 */
	EAttribute getRule_IsActive();

	/**
	 * Returns the meta object for the attribute '{@link com.cnes.checktool.results.Rule#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.cnes.checktool.results.Rule#getName()
	 * @see #getRule()
	 * @generated
	 */
	EAttribute getRule_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.cnes.checktool.results.Rule#getQualifiedName <em>Qualified Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Qualified Name</em>'.
	 * @see com.cnes.checktool.results.Rule#getQualifiedName()
	 * @see #getRule()
	 * @generated
	 */
	EAttribute getRule_QualifiedName();

	/**
	 * Returns the meta object for the container reference '{@link com.cnes.checktool.results.Rule#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Parent</em>'.
	 * @see com.cnes.checktool.results.Rule#getParent()
	 * @see #getRule()
	 * @generated
	 */
	EReference getRule_Parent();

	/**
	 * Returns the meta object for class '{@link com.cnes.checktool.results.CMetamodel <em>CMetamodel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CMetamodel</em>'.
	 * @see com.cnes.checktool.results.CMetamodel
	 * @generated
	 */
	EClass getCMetamodel();

	/**
	 * Returns the meta object for class '{@link com.cnes.checktool.results.Configuration <em>Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Configuration</em>'.
	 * @see com.cnes.checktool.results.Configuration
	 * @generated
	 */
	EClass getConfiguration();

	/**
	 * Returns the meta object for class '{@link com.cnes.checktool.results.ResultSet <em>Result Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Result Set</em>'.
	 * @see com.cnes.checktool.results.ResultSet
	 * @generated
	 */
	EClass getResultSet();

	/**
	 * Returns the meta object for the containment reference list '{@link com.cnes.checktool.results.ResultSet#getRuleSetResults <em>Rule Set Results</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Rule Set Results</em>'.
	 * @see com.cnes.checktool.results.ResultSet#getRuleSetResults()
	 * @see #getResultSet()
	 * @generated
	 */
	EReference getResultSet_RuleSetResults();

	/**
	 * Returns the meta object for class '{@link com.cnes.checktool.results.RuleSetResult <em>Rule Set Result</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Rule Set Result</em>'.
	 * @see com.cnes.checktool.results.RuleSetResult
	 * @generated
	 */
	EClass getRuleSetResult();

	/**
	 * Returns the meta object for the containment reference '{@link com.cnes.checktool.results.RuleSetResult#getRuleSet <em>Rule Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Rule Set</em>'.
	 * @see com.cnes.checktool.results.RuleSetResult#getRuleSet()
	 * @see #getRuleSetResult()
	 * @generated
	 */
	EReference getRuleSetResult_RuleSet();

	/**
	 * Returns the meta object for the containment reference list '{@link com.cnes.checktool.results.RuleSetResult#getRuleFileResults <em>Rule File Results</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Rule File Results</em>'.
	 * @see com.cnes.checktool.results.RuleSetResult#getRuleFileResults()
	 * @see #getRuleSetResult()
	 * @generated
	 */
	EReference getRuleSetResult_RuleFileResults();

	/**
	 * Returns the meta object for class '{@link com.cnes.checktool.results.Result <em>Result</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Result</em>'.
	 * @see com.cnes.checktool.results.Result
	 * @generated
	 */
	EClass getResult();

	/**
	 * Returns the meta object for the reference '{@link com.cnes.checktool.results.Result#getRule <em>Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Rule</em>'.
	 * @see com.cnes.checktool.results.Result#getRule()
	 * @see #getResult()
	 * @generated
	 */
	EReference getResult_Rule();

	/**
	 * Returns the meta object for the attribute '{@link com.cnes.checktool.results.Result#isResult <em>Result</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Result</em>'.
	 * @see com.cnes.checktool.results.Result#isResult()
	 * @see #getResult()
	 * @generated
	 */
	EAttribute getResult_Result();

	/**
	 * Returns the meta object for the attribute list '{@link com.cnes.checktool.results.Result#getFailedItems <em>Failed Items</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Failed Items</em>'.
	 * @see com.cnes.checktool.results.Result#getFailedItems()
	 * @see #getResult()
	 * @generated
	 */
	EAttribute getResult_FailedItems();

	/**
	 * Returns the meta object for the attribute '{@link com.cnes.checktool.results.Result#getRuleName <em>Rule Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Rule Name</em>'.
	 * @see com.cnes.checktool.results.Result#getRuleName()
	 * @see #getResult()
	 * @generated
	 */
	EAttribute getResult_RuleName();

	/**
	 * Returns the meta object for the attribute '{@link com.cnes.checktool.results.Result#getResourceName <em>Resource Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Resource Name</em>'.
	 * @see com.cnes.checktool.results.Result#getResourceName()
	 * @see #getResult()
	 * @generated
	 */
	EAttribute getResult_ResourceName();

	/**
	 * Returns the meta object for class '{@link com.cnes.checktool.results.RuleFile <em>Rule File</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Rule File</em>'.
	 * @see com.cnes.checktool.results.RuleFile
	 * @generated
	 */
	EClass getRuleFile();

	/**
	 * Returns the meta object for the containment reference list '{@link com.cnes.checktool.results.RuleFile#getRules <em>Rules</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Rules</em>'.
	 * @see com.cnes.checktool.results.RuleFile#getRules()
	 * @see #getRuleFile()
	 * @generated
	 */
	EReference getRuleFile_Rules();

	/**
	 * Returns the meta object for the attribute '{@link com.cnes.checktool.results.RuleFile#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.cnes.checktool.results.RuleFile#getName()
	 * @see #getRuleFile()
	 * @generated
	 */
	EAttribute getRuleFile_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.cnes.checktool.results.RuleFile#getFileContents <em>File Contents</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>File Contents</em>'.
	 * @see com.cnes.checktool.results.RuleFile#getFileContents()
	 * @see #getRuleFile()
	 * @generated
	 */
	EAttribute getRuleFile_FileContents();

	/**
	 * Returns the meta object for the attribute '{@link com.cnes.checktool.results.RuleFile#isIsValid <em>Is Valid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Valid</em>'.
	 * @see com.cnes.checktool.results.RuleFile#isIsValid()
	 * @see #getRuleFile()
	 * @generated
	 */
	EAttribute getRuleFile_IsValid();

	/**
	 * Returns the meta object for class '{@link com.cnes.checktool.results.RuleFileResult <em>Rule File Result</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Rule File Result</em>'.
	 * @see com.cnes.checktool.results.RuleFileResult
	 * @generated
	 */
	EClass getRuleFileResult();

	/**
	 * Returns the meta object for the containment reference list '{@link com.cnes.checktool.results.RuleFileResult#getRuleResults <em>Rule Results</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Rule Results</em>'.
	 * @see com.cnes.checktool.results.RuleFileResult#getRuleResults()
	 * @see #getRuleFileResult()
	 * @generated
	 */
	EReference getRuleFileResult_RuleResults();

	/**
	 * Returns the meta object for the reference '{@link com.cnes.checktool.results.RuleFileResult#getRuleFile <em>Rule File</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Rule File</em>'.
	 * @see com.cnes.checktool.results.RuleFileResult#getRuleFile()
	 * @see #getRuleFileResult()
	 * @generated
	 */
	EReference getRuleFileResult_RuleFile();

	/**
	 * Returns the meta object for the attribute '{@link com.cnes.checktool.results.RuleFileResult#getRuleFileName <em>Rule File Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Rule File Name</em>'.
	 * @see com.cnes.checktool.results.RuleFileResult#getRuleFileName()
	 * @see #getRuleFileResult()
	 * @generated
	 */
	EAttribute getRuleFileResult_RuleFileName();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ResultsFactory getResultsFactory();

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
		 * The meta object literal for the '{@link com.cnes.checktool.results.impl.RuleSetImpl <em>Rule Set</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.cnes.checktool.results.impl.RuleSetImpl
		 * @see com.cnes.checktool.results.impl.ResultsPackageImpl#getRuleSet()
		 * @generated
		 */
		EClass RULE_SET = eINSTANCE.getRuleSet();

		/**
		 * The meta object literal for the '<em><b>Checksum</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RULE_SET__CHECKSUM = eINSTANCE.getRuleSet_Checksum();

		/**
		 * The meta object literal for the '<em><b>Rule Files</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RULE_SET__RULE_FILES = eINSTANCE.getRuleSet_RuleFiles();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RULE_SET__NAME = eINSTANCE.getRuleSet_Name();

		/**
		 * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RULE_SET__VERSION = eINSTANCE.getRuleSet_Version();

		/**
		 * The meta object literal for the '{@link com.cnes.checktool.results.impl.RuleImpl <em>Rule</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.cnes.checktool.results.impl.RuleImpl
		 * @see com.cnes.checktool.results.impl.ResultsPackageImpl#getRule()
		 * @generated
		 */
		EClass RULE = eINSTANCE.getRule();

		/**
		 * The meta object literal for the '<em><b>Is Active</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RULE__IS_ACTIVE = eINSTANCE.getRule_IsActive();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RULE__NAME = eINSTANCE.getRule_Name();

		/**
		 * The meta object literal for the '<em><b>Qualified Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RULE__QUALIFIED_NAME = eINSTANCE.getRule_QualifiedName();

		/**
		 * The meta object literal for the '<em><b>Parent</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RULE__PARENT = eINSTANCE.getRule_Parent();

		/**
		 * The meta object literal for the '{@link com.cnes.checktool.results.impl.CMetamodelImpl <em>CMetamodel</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.cnes.checktool.results.impl.CMetamodelImpl
		 * @see com.cnes.checktool.results.impl.ResultsPackageImpl#getCMetamodel()
		 * @generated
		 */
		EClass CMETAMODEL = eINSTANCE.getCMetamodel();

		/**
		 * The meta object literal for the '{@link com.cnes.checktool.results.impl.ConfigurationImpl <em>Configuration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.cnes.checktool.results.impl.ConfigurationImpl
		 * @see com.cnes.checktool.results.impl.ResultsPackageImpl#getConfiguration()
		 * @generated
		 */
		EClass CONFIGURATION = eINSTANCE.getConfiguration();

		/**
		 * The meta object literal for the '{@link com.cnes.checktool.results.impl.ResultSetImpl <em>Result Set</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.cnes.checktool.results.impl.ResultSetImpl
		 * @see com.cnes.checktool.results.impl.ResultsPackageImpl#getResultSet()
		 * @generated
		 */
		EClass RESULT_SET = eINSTANCE.getResultSet();

		/**
		 * The meta object literal for the '<em><b>Rule Set Results</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESULT_SET__RULE_SET_RESULTS = eINSTANCE.getResultSet_RuleSetResults();

		/**
		 * The meta object literal for the '{@link com.cnes.checktool.results.impl.RuleSetResultImpl <em>Rule Set Result</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.cnes.checktool.results.impl.RuleSetResultImpl
		 * @see com.cnes.checktool.results.impl.ResultsPackageImpl#getRuleSetResult()
		 * @generated
		 */
		EClass RULE_SET_RESULT = eINSTANCE.getRuleSetResult();

		/**
		 * The meta object literal for the '<em><b>Rule Set</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RULE_SET_RESULT__RULE_SET = eINSTANCE.getRuleSetResult_RuleSet();

		/**
		 * The meta object literal for the '<em><b>Rule File Results</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RULE_SET_RESULT__RULE_FILE_RESULTS = eINSTANCE.getRuleSetResult_RuleFileResults();

		/**
		 * The meta object literal for the '{@link com.cnes.checktool.results.impl.ResultImpl <em>Result</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.cnes.checktool.results.impl.ResultImpl
		 * @see com.cnes.checktool.results.impl.ResultsPackageImpl#getResult()
		 * @generated
		 */
		EClass RESULT = eINSTANCE.getResult();

		/**
		 * The meta object literal for the '<em><b>Rule</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESULT__RULE = eINSTANCE.getResult_Rule();

		/**
		 * The meta object literal for the '<em><b>Result</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESULT__RESULT = eINSTANCE.getResult_Result();

		/**
		 * The meta object literal for the '<em><b>Failed Items</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESULT__FAILED_ITEMS = eINSTANCE.getResult_FailedItems();

		/**
		 * The meta object literal for the '<em><b>Rule Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESULT__RULE_NAME = eINSTANCE.getResult_RuleName();

		/**
		 * The meta object literal for the '<em><b>Resource Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESULT__RESOURCE_NAME = eINSTANCE.getResult_ResourceName();

		/**
		 * The meta object literal for the '{@link com.cnes.checktool.results.impl.RuleFileImpl <em>Rule File</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.cnes.checktool.results.impl.RuleFileImpl
		 * @see com.cnes.checktool.results.impl.ResultsPackageImpl#getRuleFile()
		 * @generated
		 */
		EClass RULE_FILE = eINSTANCE.getRuleFile();

		/**
		 * The meta object literal for the '<em><b>Rules</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RULE_FILE__RULES = eINSTANCE.getRuleFile_Rules();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RULE_FILE__NAME = eINSTANCE.getRuleFile_Name();

		/**
		 * The meta object literal for the '<em><b>File Contents</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RULE_FILE__FILE_CONTENTS = eINSTANCE.getRuleFile_FileContents();

		/**
		 * The meta object literal for the '<em><b>Is Valid</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RULE_FILE__IS_VALID = eINSTANCE.getRuleFile_IsValid();

		/**
		 * The meta object literal for the '{@link com.cnes.checktool.results.impl.RuleFileResultImpl <em>Rule File Result</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.cnes.checktool.results.impl.RuleFileResultImpl
		 * @see com.cnes.checktool.results.impl.ResultsPackageImpl#getRuleFileResult()
		 * @generated
		 */
		EClass RULE_FILE_RESULT = eINSTANCE.getRuleFileResult();

		/**
		 * The meta object literal for the '<em><b>Rule Results</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RULE_FILE_RESULT__RULE_RESULTS = eINSTANCE.getRuleFileResult_RuleResults();

		/**
		 * The meta object literal for the '<em><b>Rule File</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RULE_FILE_RESULT__RULE_FILE = eINSTANCE.getRuleFileResult_RuleFile();

		/**
		 * The meta object literal for the '<em><b>Rule File Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RULE_FILE_RESULT__RULE_FILE_NAME = eINSTANCE.getRuleFileResult_RuleFileName();

	}

} //ResultsPackage
