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
package com.cnes.checktool.xsdrules.xsdRules.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import com.cnes.checktool.results.Result;
import com.cnes.checktool.results.Rule;
import com.cnes.checktool.results.RuleFile;
import com.cnes.checktool.xsdrules.xsdRules.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see com.cnes.checktool.xsdrules.xsdRules.XsdRulesPackage
 * @generated
 */
public class XsdRulesAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static XsdRulesPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public XsdRulesAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = XsdRulesPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected XsdRulesSwitch modelSwitch =
		new XsdRulesSwitch() {
			public Object caseXSDRuleFile(XSDRuleFile object) {
				return createXSDRuleFileAdapter();
			}
			public Object caseXSDResult(XSDResult object) {
				return createXSDResultAdapter();
			}
			public Object caseXSDRule(XSDRule object) {
				return createXSDRuleAdapter();
			}
			public Object caseRuleFile(RuleFile object) {
				return createRuleFileAdapter();
			}
			public Object caseResult(Result object) {
				return createResultAdapter();
			}
			public Object caseRule(Rule object) {
				return createRuleAdapter();
			}
			public Object defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	public Adapter createAdapter(Notifier target) {
		return (Adapter)modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link com.cnes.checktool.xsdrules.xsdRules.XSDRuleFile <em>XSD Rule File</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.cnes.checktool.xsdrules.xsdRules.XSDRuleFile
	 * @generated
	 */
	public Adapter createXSDRuleFileAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.cnes.checktool.xsdrules.xsdRules.XSDResult <em>XSD Result</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.cnes.checktool.xsdrules.xsdRules.XSDResult
	 * @generated
	 */
	public Adapter createXSDResultAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.cnes.checktool.xsdrules.xsdRules.XSDRule <em>XSD Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.cnes.checktool.xsdrules.xsdRules.XSDRule
	 * @generated
	 */
	public Adapter createXSDRuleAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.cnes.checktool.results.RuleFile <em>Rule File</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.cnes.checktool.results.RuleFile
	 * @generated
	 */
	public Adapter createRuleFileAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.cnes.checktool.results.Result <em>Result</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.cnes.checktool.results.Result
	 * @generated
	 */
	public Adapter createResultAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.cnes.checktool.results.Rule <em>Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.cnes.checktool.results.Rule
	 * @generated
	 */
	public Adapter createRuleAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //XsdRulesAdapterFactory
