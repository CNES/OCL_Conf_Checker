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

import com.cnes.checktool.results.Result;
import com.cnes.checktool.results.ResultsPackage;
import com.cnes.checktool.results.RuleFile;
import com.cnes.checktool.results.RuleFileResult;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Rule File Result</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.cnes.checktool.results.impl.RuleFileResultImpl#getRuleResults <em>Rule Results</em>}</li>
 *   <li>{@link com.cnes.checktool.results.impl.RuleFileResultImpl#getRuleFile <em>Rule File</em>}</li>
 *   <li>{@link com.cnes.checktool.results.impl.RuleFileResultImpl#getRuleFileName <em>Rule File Name</em>}</li>
 * </ul>
 *
 * @generated
 */
public class RuleFileResultImpl extends EObjectImpl implements RuleFileResult {

	/**
	 * The cached value of the '{@link #getRuleResults() <em>Rule Results</em>}' containment reference list.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @see #getRuleResults()
	 * @generated
	 * @ordered
	 */
	protected EList<Result> ruleResults;

	/**
	 * The cached value of the '{@link #getRuleFile() <em>Rule File</em>}' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getRuleFile()
	 * @generated
	 * @ordered
	 */
	protected RuleFile ruleFile;

	/**
	 * The default value of the '{@link #getRuleFileName() <em>Rule File Name</em>}' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see #getRuleFileName()
	 * @generated
	 * @ordered
	 */
	protected static final String RULE_FILE_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRuleFileName() <em>Rule File Name</em>}' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see #getRuleFileName()
	 * @generated
	 * @ordered
	 */
	protected String ruleFileName = RULE_FILE_NAME_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected RuleFileResultImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ResultsPackage.Literals.RULE_FILE_RESULT;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Result> getRuleResults() {
		if (ruleResults == null) {
			ruleResults = new EObjectContainmentEList<Result>(Result.class, this, ResultsPackage.RULE_FILE_RESULT__RULE_RESULTS);
		}
		return ruleResults;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public RuleFile getRuleFile() {
		if (ruleFile != null && ruleFile.eIsProxy()) {
			InternalEObject oldRuleFile = (InternalEObject)ruleFile;
			ruleFile = (RuleFile)eResolveProxy(oldRuleFile);
			if (ruleFile != oldRuleFile) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ResultsPackage.RULE_FILE_RESULT__RULE_FILE, oldRuleFile, ruleFile));
			}
		}
		return ruleFile;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public RuleFile basicGetRuleFile() {
		return ruleFile;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated not
	 */
	public void setRuleFile(RuleFile newRuleFile) {
		RuleFile oldRuleFile = ruleFile;
		ruleFile = newRuleFile;
		if (newRuleFile != null) {
			setRuleFileName(newRuleFile.getName());
		}
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					ResultsPackage.RULE_FILE_RESULT__RULE_FILE, oldRuleFile,
					ruleFile));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated not
	 */
	public String getRuleFileName() {
		if (ruleFile != null) {
			ruleFileName = ruleFile.getName();
		}
		return ruleFileName;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setRuleFileName(String newRuleFileName) {
		String oldRuleFileName = ruleFileName;
		ruleFileName = newRuleFileName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ResultsPackage.RULE_FILE_RESULT__RULE_FILE_NAME, oldRuleFileName, ruleFileName));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ResultsPackage.RULE_FILE_RESULT__RULE_RESULTS:
				return ((InternalEList<?>)getRuleResults()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ResultsPackage.RULE_FILE_RESULT__RULE_RESULTS:
				return getRuleResults();
			case ResultsPackage.RULE_FILE_RESULT__RULE_FILE:
				if (resolve) return getRuleFile();
				return basicGetRuleFile();
			case ResultsPackage.RULE_FILE_RESULT__RULE_FILE_NAME:
				return getRuleFileName();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ResultsPackage.RULE_FILE_RESULT__RULE_RESULTS:
				getRuleResults().clear();
				getRuleResults().addAll((Collection<? extends Result>)newValue);
				return;
			case ResultsPackage.RULE_FILE_RESULT__RULE_FILE:
				setRuleFile((RuleFile)newValue);
				return;
			case ResultsPackage.RULE_FILE_RESULT__RULE_FILE_NAME:
				setRuleFileName((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ResultsPackage.RULE_FILE_RESULT__RULE_RESULTS:
				getRuleResults().clear();
				return;
			case ResultsPackage.RULE_FILE_RESULT__RULE_FILE:
				setRuleFile((RuleFile)null);
				return;
			case ResultsPackage.RULE_FILE_RESULT__RULE_FILE_NAME:
				setRuleFileName(RULE_FILE_NAME_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ResultsPackage.RULE_FILE_RESULT__RULE_RESULTS:
				return ruleResults != null && !ruleResults.isEmpty();
			case ResultsPackage.RULE_FILE_RESULT__RULE_FILE:
				return ruleFile != null;
			case ResultsPackage.RULE_FILE_RESULT__RULE_FILE_NAME:
				return RULE_FILE_NAME_EDEFAULT == null ? ruleFileName != null : !RULE_FILE_NAME_EDEFAULT.equals(ruleFileName);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (ruleFileName: ");
		result.append(ruleFileName);
		result.append(')');
		return result.toString();
	}

	/**
	 * generated not
	 */
	public boolean resultIsOK() {
		for (Result result : getRuleResults()) {
			if (!result.isResult()) {
				return false;
			}
		}
		return true;
	}

} // RuleFileResultImpl
