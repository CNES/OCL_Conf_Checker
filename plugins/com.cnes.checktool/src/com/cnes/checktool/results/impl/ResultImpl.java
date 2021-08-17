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
package com.cnes.checktool.results.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

import com.cnes.checktool.results.Result;
import com.cnes.checktool.results.ResultsPackage;
import com.cnes.checktool.results.Rule;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Result</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.cnes.checktool.results.impl.ResultImpl#getRule <em>Rule</em>}</li>
 *   <li>{@link com.cnes.checktool.results.impl.ResultImpl#isResult <em>Result</em>}</li>
 *   <li>{@link com.cnes.checktool.results.impl.ResultImpl#getFailedItems <em>Failed Items</em>}</li>
 *   <li>{@link com.cnes.checktool.results.impl.ResultImpl#getRuleName <em>Rule Name</em>}</li>
 *   <li>{@link com.cnes.checktool.results.impl.ResultImpl#getResourceName <em>Resource Name</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ResultImpl extends EObjectImpl implements Result {
	/**
     * The cached value of the '{@link #getRule() <em>Rule</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see #getRule()
     * @generated
     * @ordered
     */
    protected Rule rule;

    /**
	 * The default value of the '{@link #isResult() <em>Result</em>}' attribute.
	 * <!-- begin-user-doc --> <!--
     * end-user-doc -->
	 * @see #isResult()
	 * @generated
	 * @ordered
	 */
    protected static final boolean RESULT_EDEFAULT = false;

    /**
	 * The cached value of the '{@link #isResult() <em>Result</em>}' attribute.
	 * <!-- begin-user-doc --> <!--
     * end-user-doc -->
	 * @see #isResult()
	 * @generated
	 * @ordered
	 */
    protected boolean result = RESULT_EDEFAULT;

    /**
	 * The cached value of the '{@link #getFailedItems() <em>Failed Items</em>}' attribute list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getFailedItems()
	 * @generated
	 * @ordered
	 */
    protected EList<String> failedItems;

    /**
	 * The default value of the '{@link #getRuleName() <em>Rule Name</em>}' attribute.
	 * <!-- begin-user-doc --> <!--
     * end-user-doc -->
	 * @see #getRuleName()
	 * @generated
	 * @ordered
	 */
    protected static final String RULE_NAME_EDEFAULT = null;

    /**
	 * The cached value of the '{@link #getRuleName() <em>Rule Name</em>}' attribute.
	 * <!-- begin-user-doc --> <!--
     * end-user-doc -->
	 * @see #getRuleName()
	 * @generated
	 * @ordered
	 */
    protected String ruleName = RULE_NAME_EDEFAULT;

    /**
	 * The default value of the '{@link #getResourceName() <em>Resource Name</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getResourceName()
	 * @generated
	 * @ordered
	 */
    protected static final String RESOURCE_NAME_EDEFAULT = null;

    /**
	 * The cached value of the '{@link #getResourceName() <em>Resource Name</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getResourceName()
	 * @generated
	 * @ordered
	 */
    protected String resourceName = RESOURCE_NAME_EDEFAULT;

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    protected ResultImpl()
    {
		super();
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    protected EClass eStaticClass()
    {
		return ResultsPackage.Literals.RESULT;
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    public Rule getRule()
    {
		if (rule != null && rule.eIsProxy()) {
			InternalEObject oldRule = (InternalEObject)rule;
			rule = (Rule)eResolveProxy(oldRule);
			if (rule != oldRule) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ResultsPackage.RESULT__RULE, oldRule, rule));
			}
		}
		return rule;
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    public Rule basicGetRule()
    {
		return rule;
	}

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated not
     */
    public void setRule(Rule newRule)
    {
        Rule oldRule = rule;
        rule = newRule;
        if (newRule != null)
        {
            setRuleName(newRule.getName());
        }
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ResultsPackage.RESULT__RULE, oldRule, rule));
    }

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    public EList<String> getFailedItems()
    {
		if (failedItems == null) {
			failedItems = new EDataTypeUniqueEList<String>(String.class, this, ResultsPackage.RESULT__FAILED_ITEMS);
		}
		return failedItems;
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    public String getRuleName()
    {
		return ruleName;
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    public void setRuleName(String newRuleName)
    {
		String oldRuleName = ruleName;
		ruleName = newRuleName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ResultsPackage.RESULT__RULE_NAME, oldRuleName, ruleName));
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    public String getResourceName()
    {
		return resourceName;
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    public void setResourceName(String newResourceName)
    {
		String oldResourceName = resourceName;
		resourceName = newResourceName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ResultsPackage.RESULT__RESOURCE_NAME, oldResourceName, resourceName));
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    public boolean isResult()
    {
		return result;
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    public void setResult(boolean newResult)
    {
		boolean oldResult = result;
		result = newResult;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ResultsPackage.RESULT__RESULT, oldResult, result));
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType)
    {
		switch (featureID) {
			case ResultsPackage.RESULT__RULE:
				if (resolve) return getRule();
				return basicGetRule();
			case ResultsPackage.RESULT__RESULT:
				return isResult();
			case ResultsPackage.RESULT__FAILED_ITEMS:
				return getFailedItems();
			case ResultsPackage.RESULT__RULE_NAME:
				return getRuleName();
			case ResultsPackage.RESULT__RESOURCE_NAME:
				return getResourceName();
		}
		return super.eGet(featureID, resolve, coreType);
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue)
    {
		switch (featureID) {
			case ResultsPackage.RESULT__RULE:
				setRule((Rule)newValue);
				return;
			case ResultsPackage.RESULT__RESULT:
				setResult((Boolean)newValue);
				return;
			case ResultsPackage.RESULT__FAILED_ITEMS:
				getFailedItems().clear();
				getFailedItems().addAll((Collection<? extends String>)newValue);
				return;
			case ResultsPackage.RESULT__RULE_NAME:
				setRuleName((String)newValue);
				return;
			case ResultsPackage.RESULT__RESOURCE_NAME:
				setResourceName((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public void eUnset(int featureID)
    {
		switch (featureID) {
			case ResultsPackage.RESULT__RULE:
				setRule((Rule)null);
				return;
			case ResultsPackage.RESULT__RESULT:
				setResult(RESULT_EDEFAULT);
				return;
			case ResultsPackage.RESULT__FAILED_ITEMS:
				getFailedItems().clear();
				return;
			case ResultsPackage.RESULT__RULE_NAME:
				setRuleName(RULE_NAME_EDEFAULT);
				return;
			case ResultsPackage.RESULT__RESOURCE_NAME:
				setResourceName(RESOURCE_NAME_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public boolean eIsSet(int featureID)
    {
		switch (featureID) {
			case ResultsPackage.RESULT__RULE:
				return rule != null;
			case ResultsPackage.RESULT__RESULT:
				return result != RESULT_EDEFAULT;
			case ResultsPackage.RESULT__FAILED_ITEMS:
				return failedItems != null && !failedItems.isEmpty();
			case ResultsPackage.RESULT__RULE_NAME:
				return RULE_NAME_EDEFAULT == null ? ruleName != null : !RULE_NAME_EDEFAULT.equals(ruleName);
			case ResultsPackage.RESULT__RESOURCE_NAME:
				return RESOURCE_NAME_EDEFAULT == null ? resourceName != null : !RESOURCE_NAME_EDEFAULT.equals(resourceName);
		}
		return super.eIsSet(featureID);
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public String toString()
    {
		if (eIsProxy()) return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (result: ");
		result.append(result);
		result.append(", failedItems: ");
		result.append(failedItems);
		result.append(", ruleName: ");
		result.append(ruleName);
		result.append(", resourceName: ");
		result.append(resourceName);
		result.append(')');
		return result.toString();
	}

} //ResultImpl
