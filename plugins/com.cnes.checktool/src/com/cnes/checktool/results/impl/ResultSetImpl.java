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

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import com.cnes.checktool.results.ResultSet;
import com.cnes.checktool.results.ResultsPackage;
import com.cnes.checktool.results.RuleSetResult;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Result Set</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.cnes.checktool.results.impl.ResultSetImpl#getRuleSetResults <em>Rule Set Results</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ResultSetImpl extends EObjectImpl implements ResultSet {
	/**
     * The cached value of the '{@link #getRuleSetResults() <em>Rule Set Results</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getRuleSetResults()
     * @generated
     * @ordered
     */
    protected EList<RuleSetResult> ruleSetResults;

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    protected ResultSetImpl()
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
		return ResultsPackage.Literals.RESULT_SET;
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    public EList<RuleSetResult> getRuleSetResults()
    {
		if (ruleSetResults == null) {
			ruleSetResults = new EObjectContainmentEList<RuleSetResult>(RuleSetResult.class, this, ResultsPackage.RESULT_SET__RULE_SET_RESULTS);
		}
		return ruleSetResults;
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
    {
		switch (featureID) {
			case ResultsPackage.RESULT_SET__RULE_SET_RESULTS:
				return ((InternalEList<?>)getRuleSetResults()).basicRemove(otherEnd, msgs);
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
			case ResultsPackage.RESULT_SET__RULE_SET_RESULTS:
				return getRuleSetResults();
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
			case ResultsPackage.RESULT_SET__RULE_SET_RESULTS:
				getRuleSetResults().clear();
				getRuleSetResults().addAll((Collection<? extends RuleSetResult>)newValue);
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
			case ResultsPackage.RESULT_SET__RULE_SET_RESULTS:
				getRuleSetResults().clear();
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
			case ResultsPackage.RESULT_SET__RULE_SET_RESULTS:
				return ruleSetResults != null && !ruleSetResults.isEmpty();
		}
		return super.eIsSet(featureID);
	}

    /**
     * @generated not
     */
    public boolean resultIsOK()
    {
        for (RuleSetResult rsResult : getRuleSetResults())
        {
            if (!rsResult.resultIsOK())
            {
                return false;
            }
        }
        return true;
    }

} //ResultSetImpl
