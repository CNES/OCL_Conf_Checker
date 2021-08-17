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
package com.cnes.checktool.oclrules.oclRules.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import com.cnes.checktool.oclrules.oclRules.OCLRule;
import com.cnes.checktool.oclrules.oclRules.OCLRuleFile;
import com.cnes.checktool.oclrules.oclRules.OclRulesFactory;
import com.cnes.checktool.oclrules.oclRules.OclRulesPackage;
import com.cnes.checktool.oclrules.oclRules.util.OCLResource;
import com.cnes.checktool.results.ResultsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class OclRulesPackageImpl extends EPackageImpl implements OclRulesPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass oclRuleEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass oclRuleFileEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType oclResourceEDataType = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see com.cnes.checktool.oclrules.oclRules.OclRulesPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private OclRulesPackageImpl() {
		super(eNS_URI, OclRulesFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 *
	 * <p>This method is used to initialize {@link OclRulesPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static OclRulesPackage init() {
		if (isInited) return (OclRulesPackage)EPackage.Registry.INSTANCE.getEPackage(OclRulesPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredOclRulesPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		OclRulesPackageImpl theOclRulesPackage = registeredOclRulesPackage instanceof OclRulesPackageImpl ? (OclRulesPackageImpl)registeredOclRulesPackage : new OclRulesPackageImpl();

		isInited = true;

		// Initialize simple dependencies
		ResultsPackage.eINSTANCE.eClass();
		EcorePackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theOclRulesPackage.createPackageContents();

		// Initialize created meta-data
		theOclRulesPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theOclRulesPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(OclRulesPackage.eNS_URI, theOclRulesPackage);
		return theOclRulesPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOCLRule() {
		return oclRuleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOCLRule_QueryBody() {
		return (EAttribute)oclRuleEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOCLRule_Context() {
		return (EReference)oclRuleEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOCLRule_ConstraintName() {
		return (EAttribute)oclRuleEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOCLRuleFile() {
		return oclRuleFileEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getOCLResource() {
		return oclResourceEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OclRulesFactory getOclRulesFactory() {
		return (OclRulesFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		oclRuleEClass = createEClass(OCL_RULE);
		createEAttribute(oclRuleEClass, OCL_RULE__QUERY_BODY);
		createEReference(oclRuleEClass, OCL_RULE__CONTEXT);
		createEAttribute(oclRuleEClass, OCL_RULE__CONSTRAINT_NAME);

		oclRuleFileEClass = createEClass(OCL_RULE_FILE);

		// Create data types
		oclResourceEDataType = createEDataType(OCL_RESOURCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		ResultsPackage theResultsPackage = (ResultsPackage)EPackage.Registry.INSTANCE.getEPackage(ResultsPackage.eNS_URI);
		EcorePackage theEcorePackage = (EcorePackage)EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);

		// Add supertypes to classes
		oclRuleEClass.getESuperTypes().add(theResultsPackage.getRule());
		oclRuleFileEClass.getESuperTypes().add(theResultsPackage.getRuleFile());

		// Initialize classes and features; add operations and parameters
		initEClass(oclRuleEClass, OCLRule.class, "OCLRule", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getOCLRule_QueryBody(), theEcorePackage.getEString(), "queryBody", null, 0, 1, OCLRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getOCLRule_Context(), theEcorePackage.getEClassifier(), null, "context", null, 0, 1, OCLRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getOCLRule_ConstraintName(), theEcorePackage.getEString(), "constraintName", null, 0, 1, OCLRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(oclRuleFileEClass, OCLRuleFile.class, "OCLRuleFile", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		addEOperation(oclRuleFileEClass, this.getOCLResource(), "getResource", 0, 1);

		EOperation op = addEOperation(oclRuleFileEClass, null, "completeFiles");
		addEParameter(op, theEcorePackage.getEResource(), "resources", 0, -1);

		// Initialize data types
		initEDataType(oclResourceEDataType, OCLResource.class, "OCLResource", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

} //OclRulesPackageImpl
