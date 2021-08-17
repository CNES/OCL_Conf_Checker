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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

import com.cnes.checktool.results.ResultsPackage;
import com.cnes.checktool.results.Rule;
import com.cnes.checktool.results.RuleFile;
import com.cnes.checktool.results.RuleSet;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Rule</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.cnes.checktool.results.impl.RuleImpl#isIsActive <em>Is Active</em>}</li>
 *   <li>{@link com.cnes.checktool.results.impl.RuleImpl#getName <em>Name</em>}</li>
 *   <li>{@link com.cnes.checktool.results.impl.RuleImpl#getQualifiedName <em>Qualified Name</em>}</li>
 *   <li>{@link com.cnes.checktool.results.impl.RuleImpl#getParent <em>Parent</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class RuleImpl extends EObjectImpl implements Rule {
	/**
	 * The default value of the '{@link #isIsActive() <em>Is Active</em>}' attribute.
	 * <!-- begin-user-doc --> <!--
     * end-user-doc -->
	 * @see #isIsActive()
	 * @generated
	 * @ordered
	 */
    protected static final boolean IS_ACTIVE_EDEFAULT = false;

    /**
	 * The cached value of the '{@link #isIsActive() <em>Is Active</em>}' attribute.
	 * <!-- begin-user-doc --> <!--
     * end-user-doc -->
	 * @see #isIsActive()
	 * @generated
	 * @ordered
	 */
    protected boolean isActive = IS_ACTIVE_EDEFAULT;

    /**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see #getName()
     * @generated
     * @ordered
     */
    protected static final String NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see #getName()
     * @generated
     * @ordered
     */
    protected String name = NAME_EDEFAULT;

    /**
	 * The default value of the '{@link #getQualifiedName() <em>Qualified Name</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getQualifiedName()
	 * @generated
	 * @ordered
	 */
    protected static final String QUALIFIED_NAME_EDEFAULT = null;

    /**
	 * The cached value of the '{@link #getQualifiedName() <em>Qualified Name</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getQualifiedName()
	 * @generated
	 * @ordered
	 */
    protected String qualifiedName = QUALIFIED_NAME_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    protected RuleImpl()
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
		return ResultsPackage.Literals.RULE;
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    public boolean isIsActive()
    {
		return isActive;
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    public void setIsActive(boolean newIsActive)
    {
		boolean oldIsActive = isActive;
		isActive = newIsActive;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ResultsPackage.RULE__IS_ACTIVE, oldIsActive, isActive));
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    public String getName()
    {
		return name;
	}

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    public void setName(String newName)
    {
        String oldName = name;
        name = newName;
        RuleFile ruleFile = (RuleFile) eContainer();
        if (ruleFile != null)
        {
            RuleSet set = (RuleSet) ruleFile.eContainer();
            if (set != null)
            {
                qualifiedName = set.getName() + "[" + set.getVersion() + "]-" + ruleFile.getName() + "-" + getName();
            }
        }
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ResultsPackage.RULE__NAME, oldName, name));
    }

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    public String getQualifiedName()
    {
		return qualifiedName;
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    public void setQualifiedName(String newQualifiedName)
    {
		String oldQualifiedName = qualifiedName;
		qualifiedName = newQualifiedName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ResultsPackage.RULE__QUALIFIED_NAME, oldQualifiedName, qualifiedName));
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    public RuleFile getParent()
    {
		if (eContainerFeatureID() != ResultsPackage.RULE__PARENT) return null;
		return (RuleFile)eInternalContainer();
	}

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    public NotificationChain basicSetParent(RuleFile newParent, NotificationChain msgs)
    {
        msgs = eBasicSetContainer((InternalEObject) newParent, ResultsPackage.RULE__PARENT, msgs);
        RuleFile ruleFile = (RuleFile) eContainer();
        if (ruleFile != null)
        {
            RuleSet set = (RuleSet) ruleFile.eContainer();
            if (set != null)
            {
                qualifiedName = set.getName() + "[" + set.getVersion() + "]-" + ruleFile.getName() + "-" + getName();
            }
        }
        return msgs;
    }

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    public void setParent(RuleFile newParent)
    {
		if (newParent != eInternalContainer() || (eContainerFeatureID() != ResultsPackage.RULE__PARENT && newParent != null)) {
			if (EcoreUtil.isAncestor(this, newParent))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newParent != null)
				msgs = ((InternalEObject)newParent).eInverseAdd(this, ResultsPackage.RULE_FILE__RULES, RuleFile.class, msgs);
			msgs = basicSetParent(newParent, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ResultsPackage.RULE__PARENT, newParent, newParent));
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
    {
		switch (featureID) {
			case ResultsPackage.RULE__PARENT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetParent((RuleFile)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
    {
		switch (featureID) {
			case ResultsPackage.RULE__PARENT:
				return basicSetParent(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs)
    {
		switch (eContainerFeatureID()) {
			case ResultsPackage.RULE__PARENT:
				return eInternalContainer().eInverseRemove(this, ResultsPackage.RULE_FILE__RULES, RuleFile.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType)
    {
		switch (featureID) {
			case ResultsPackage.RULE__IS_ACTIVE:
				return isIsActive();
			case ResultsPackage.RULE__NAME:
				return getName();
			case ResultsPackage.RULE__QUALIFIED_NAME:
				return getQualifiedName();
			case ResultsPackage.RULE__PARENT:
				return getParent();
		}
		return super.eGet(featureID, resolve, coreType);
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public void eSet(int featureID, Object newValue)
    {
		switch (featureID) {
			case ResultsPackage.RULE__IS_ACTIVE:
				setIsActive((Boolean)newValue);
				return;
			case ResultsPackage.RULE__NAME:
				setName((String)newValue);
				return;
			case ResultsPackage.RULE__QUALIFIED_NAME:
				setQualifiedName((String)newValue);
				return;
			case ResultsPackage.RULE__PARENT:
				setParent((RuleFile)newValue);
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
			case ResultsPackage.RULE__IS_ACTIVE:
				setIsActive(IS_ACTIVE_EDEFAULT);
				return;
			case ResultsPackage.RULE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ResultsPackage.RULE__QUALIFIED_NAME:
				setQualifiedName(QUALIFIED_NAME_EDEFAULT);
				return;
			case ResultsPackage.RULE__PARENT:
				setParent((RuleFile)null);
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
			case ResultsPackage.RULE__IS_ACTIVE:
				return isActive != IS_ACTIVE_EDEFAULT;
			case ResultsPackage.RULE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case ResultsPackage.RULE__QUALIFIED_NAME:
				return QUALIFIED_NAME_EDEFAULT == null ? qualifiedName != null : !QUALIFIED_NAME_EDEFAULT.equals(qualifiedName);
			case ResultsPackage.RULE__PARENT:
				return getParent() != null;
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
		result.append(" (isActive: ");
		result.append(isActive);
		result.append(", name: ");
		result.append(name);
		result.append(", qualifiedName: ");
		result.append(qualifiedName);
		result.append(')');
		return result.toString();
	}

} //RuleImpl
