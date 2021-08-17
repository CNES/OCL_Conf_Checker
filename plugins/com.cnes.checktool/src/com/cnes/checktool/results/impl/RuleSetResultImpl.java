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
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import com.cnes.checktool.results.ResultsPackage;
import com.cnes.checktool.results.RuleFileResult;
import com.cnes.checktool.results.RuleSet;
import com.cnes.checktool.results.RuleSetResult;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Rule Set Result</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.cnes.checktool.results.impl.RuleSetResultImpl#getRuleSet <em>Rule Set</em>}</li>
 *   <li>{@link com.cnes.checktool.results.impl.RuleSetResultImpl#getRuleFileResults <em>Rule File Results</em>}</li>
 * </ul>
 *
 * @generated
 */
public class RuleSetResultImpl extends EObjectImpl implements RuleSetResult {
	 /**
	 * The cached value of the '{@link #getRuleSet() <em>Rule Set</em>}' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getRuleSet()
	 * @generated
	 * @ordered
	 */
    protected RuleSet ruleSet;

    /**
	 * The cached value of the '{@link #getRuleFileResults() <em>Rule File Results</em>}' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getRuleFileResults()
	 * @generated
	 * @ordered
	 */
    protected EList<RuleFileResult> ruleFileResults;

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    protected RuleSetResultImpl()
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
		return ResultsPackage.Literals.RULE_SET_RESULT;
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    public RuleSet getRuleSet()
    {
		return ruleSet;
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetRuleSet(RuleSet newRuleSet, NotificationChain msgs)
    {
		RuleSet oldRuleSet = ruleSet;
		ruleSet = newRuleSet;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ResultsPackage.RULE_SET_RESULT__RULE_SET, oldRuleSet, newRuleSet);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    public void setRuleSet(RuleSet newRuleSet)
    {
		if (newRuleSet != ruleSet) {
			NotificationChain msgs = null;
			if (ruleSet != null)
				msgs = ((InternalEObject)ruleSet).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ResultsPackage.RULE_SET_RESULT__RULE_SET, null, msgs);
			if (newRuleSet != null)
				msgs = ((InternalEObject)newRuleSet).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ResultsPackage.RULE_SET_RESULT__RULE_SET, null, msgs);
			msgs = basicSetRuleSet(newRuleSet, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ResultsPackage.RULE_SET_RESULT__RULE_SET, newRuleSet, newRuleSet));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList<RuleFileResult> getRuleFileResults()
    {
		if (ruleFileResults == null) {
			ruleFileResults = new EObjectContainmentEList<RuleFileResult>(RuleFileResult.class, this, ResultsPackage.RULE_SET_RESULT__RULE_FILE_RESULTS);
		}
		return ruleFileResults;
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
    {
		switch (featureID) {
			case ResultsPackage.RULE_SET_RESULT__RULE_SET:
				return basicSetRuleSet(null, msgs);
			case ResultsPackage.RULE_SET_RESULT__RULE_FILE_RESULTS:
				return ((InternalEList<?>)getRuleFileResults()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType)
    {
		switch (featureID) {
			case ResultsPackage.RULE_SET_RESULT__RULE_SET:
				return getRuleSet();
			case ResultsPackage.RULE_SET_RESULT__RULE_FILE_RESULTS:
				return getRuleFileResults();
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
			case ResultsPackage.RULE_SET_RESULT__RULE_SET:
				setRuleSet((RuleSet)newValue);
				return;
			case ResultsPackage.RULE_SET_RESULT__RULE_FILE_RESULTS:
				getRuleFileResults().clear();
				getRuleFileResults().addAll((Collection<? extends RuleFileResult>)newValue);
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
			case ResultsPackage.RULE_SET_RESULT__RULE_SET:
				setRuleSet((RuleSet)null);
				return;
			case ResultsPackage.RULE_SET_RESULT__RULE_FILE_RESULTS:
				getRuleFileResults().clear();
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
			case ResultsPackage.RULE_SET_RESULT__RULE_SET:
				return ruleSet != null;
			case ResultsPackage.RULE_SET_RESULT__RULE_FILE_RESULTS:
				return ruleFileResults != null && !ruleFileResults.isEmpty();
		}
		return super.eIsSet(featureID);
	}

    public boolean resultIsOK()
    {
        for (RuleFileResult result : getRuleFileResults())
        {
            if (!result.resultIsOK())
            {
                return false;
            }
        }
        return true;
    }

} //RuleSetResultImpl
