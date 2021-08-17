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

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.impl.EObjectImpl;
import com.cnes.checktool.oclrules.oclRules.OCLRule;
import com.cnes.checktool.oclrules.oclRules.OclRulesPackage;
import com.cnes.checktool.oclrules.oclRules.util.OCLResource;
import com.cnes.checktool.results.impl.RuleImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>OCL Rule</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.cnes.checktool.oclrules.oclRules.impl.OCLRuleImpl#getQueryBody <em>Query Body</em>}</li>
 *   <li>{@link com.cnes.checktool.oclrules.oclRules.impl.OCLRuleImpl#getContext <em>Context</em>}</li>
 *   <li>{@link com.cnes.checktool.oclrules.oclRules.impl.OCLRuleImpl#getConstraintName <em>Constraint Name</em>}</li>
 * </ul>
 *
 * @generated
 */
public class OCLRuleImpl extends RuleImpl implements OCLRule {
	/**
	 * The default value of the '{@link #getQueryBody() <em>Query Body</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQueryBody()
	 * @generated
	 * @ordered
	 */
	protected static final String QUERY_BODY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getQueryBody() <em>Query Body</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQueryBody()
	 * @generated
	 * @ordered
	 */
	protected String queryBody = QUERY_BODY_EDEFAULT;

	/**
	 * The cached value of the '{@link #getContext() <em>Context</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContext()
	 * @generated
	 * @ordered
	 */
	protected EClassifier context;

	/**
	 * The default value of the '{@link #getConstraintName() <em>Constraint Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConstraintName()
	 * @generated
	 * @ordered
	 */
	protected static final String CONSTRAINT_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getConstraintName() <em>Constraint Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConstraintName()
	 * @generated
	 * @ordered
	 */
	protected String constraintName = CONSTRAINT_NAME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OCLRuleImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return OclRulesPackage.Literals.OCL_RULE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getQueryBody() {
		return queryBody;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setQueryBody(String newQueryBody) {
		String oldQueryBody = queryBody;
		queryBody = newQueryBody;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OclRulesPackage.OCL_RULE__QUERY_BODY, oldQueryBody, queryBody));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClassifier getContext() {
		if (context != null && context.eIsProxy()) {
			InternalEObject oldContext = (InternalEObject)context;
			context = (EClassifier)eResolveProxy(oldContext);
			if (context != oldContext) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, OclRulesPackage.OCL_RULE__CONTEXT, oldContext, context));
			}
		}
		return context;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClassifier basicGetContext() {
		return context;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setContext(EClassifier newContext) {
		EClassifier oldContext = context;
		context = newContext;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OclRulesPackage.OCL_RULE__CONTEXT, oldContext, context));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getConstraintName() {
		return constraintName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConstraintName(String newConstraintName) {
		String oldConstraintName = constraintName;
		constraintName = newConstraintName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OclRulesPackage.OCL_RULE__CONSTRAINT_NAME, oldConstraintName, constraintName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case OclRulesPackage.OCL_RULE__QUERY_BODY:
				return getQueryBody();
			case OclRulesPackage.OCL_RULE__CONTEXT:
				if (resolve) return getContext();
				return basicGetContext();
			case OclRulesPackage.OCL_RULE__CONSTRAINT_NAME:
				return getConstraintName();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case OclRulesPackage.OCL_RULE__QUERY_BODY:
				setQueryBody((String)newValue);
				return;
			case OclRulesPackage.OCL_RULE__CONTEXT:
				setContext((EClassifier)newValue);
				return;
			case OclRulesPackage.OCL_RULE__CONSTRAINT_NAME:
				setConstraintName((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(int featureID) {
		switch (featureID) {
			case OclRulesPackage.OCL_RULE__QUERY_BODY:
				setQueryBody(QUERY_BODY_EDEFAULT);
				return;
			case OclRulesPackage.OCL_RULE__CONTEXT:
				setContext((EClassifier)null);
				return;
			case OclRulesPackage.OCL_RULE__CONSTRAINT_NAME:
				setConstraintName(CONSTRAINT_NAME_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case OclRulesPackage.OCL_RULE__QUERY_BODY:
				return QUERY_BODY_EDEFAULT == null ? queryBody != null : !QUERY_BODY_EDEFAULT.equals(queryBody);
			case OclRulesPackage.OCL_RULE__CONTEXT:
				return context != null;
			case OclRulesPackage.OCL_RULE__CONSTRAINT_NAME:
				return CONSTRAINT_NAME_EDEFAULT == null ? constraintName != null : !CONSTRAINT_NAME_EDEFAULT.equals(constraintName);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (queryBody: ");
		result.append(queryBody);
		result.append(", constraintName: ");
		result.append(constraintName);
		result.append(')');
		return result.toString();
	}

} //OCLRuleImpl
