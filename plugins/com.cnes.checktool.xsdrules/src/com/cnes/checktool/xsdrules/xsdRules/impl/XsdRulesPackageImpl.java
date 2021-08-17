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
package com.cnes.checktool.xsdrules.xsdRules.impl;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import com.cnes.checktool.results.ResultsPackage;
import com.cnes.checktool.xsdrules.xsdRules.XSDResult;
import com.cnes.checktool.xsdrules.xsdRules.XSDRule;
import com.cnes.checktool.xsdrules.xsdRules.XSDRuleFile;
import com.cnes.checktool.xsdrules.xsdRules.XsdRulesFactory;
import com.cnes.checktool.xsdrules.xsdRules.XsdRulesPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class XsdRulesPackageImpl extends EPackageImpl implements XsdRulesPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass xsdRuleFileEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass xsdResultEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass xsdRuleEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType iFileEDataType = null;

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
	 * @see com.cnes.checktool.xsdrules.xsdRules.XsdRulesPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private XsdRulesPackageImpl() {
		super(eNS_URI, XsdRulesFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link XsdRulesPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static XsdRulesPackage init() {
		if (isInited) return (XsdRulesPackage)EPackage.Registry.INSTANCE.getEPackage(XsdRulesPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredXsdRulesPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		XsdRulesPackageImpl theXsdRulesPackage = registeredXsdRulesPackage instanceof XsdRulesPackageImpl ? (XsdRulesPackageImpl)registeredXsdRulesPackage : new XsdRulesPackageImpl();

		isInited = true;

		// Initialize simple dependencies
		ResultsPackage.eINSTANCE.eClass();
		EcorePackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theXsdRulesPackage.createPackageContents();

		// Initialize created meta-data
		theXsdRulesPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theXsdRulesPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(XsdRulesPackage.eNS_URI, theXsdRulesPackage);
		return theXsdRulesPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getXSDRuleFile() {
		return xsdRuleFileEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getXSDResult() {
		return xsdResultEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getXSDResult_ErrorMessage() {
		return (EAttribute)xsdResultEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getXSDRule() {
		return xsdRuleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getIFile() {
		return iFileEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public XsdRulesFactory getXsdRulesFactory() {
		return (XsdRulesFactory)getEFactoryInstance();
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
		xsdRuleFileEClass = createEClass(XSD_RULE_FILE);

		xsdResultEClass = createEClass(XSD_RESULT);
		createEAttribute(xsdResultEClass, XSD_RESULT__ERROR_MESSAGE);

		xsdRuleEClass = createEClass(XSD_RULE);

		// Create data types
		iFileEDataType = createEDataType(IFILE);
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
		xsdRuleFileEClass.getESuperTypes().add(theResultsPackage.getRuleFile());
		xsdResultEClass.getESuperTypes().add(theResultsPackage.getResult());
		xsdRuleEClass.getESuperTypes().add(theResultsPackage.getRule());

		// Initialize classes and features; add operations and parameters
		initEClass(xsdRuleFileEClass, XSDRuleFile.class, "XSDRuleFile", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		addEOperation(xsdRuleFileEClass, theEcorePackage.getEString(), "getFileExtension", 0, 1);

		addEOperation(xsdRuleFileEClass, this.getIFile(), "getFile", 0, 1);

		EOperation op = addEOperation(xsdRuleFileEClass, null, "setFile");
		addEParameter(op, this.getIFile(), "iFile", 0, 1);

		initEClass(xsdResultEClass, XSDResult.class, "XSDResult", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getXSDResult_ErrorMessage(), theEcorePackage.getEString(), "errorMessage", "\"\"", 0, 1, XSDResult.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(xsdRuleEClass, XSDRule.class, "XSDRule", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		// Initialize data types
		initEDataType(iFileEDataType, IFile.class, "IFile", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

} //XsdRulesPackageImpl
