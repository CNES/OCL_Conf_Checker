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

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import com.cnes.checktool.Log;
import com.cnes.checktool.oclrules.oclRules.*;
import com.cnes.checktool.oclrules.oclRules.OCLRule;
import com.cnes.checktool.oclrules.oclRules.OCLRuleFile;
import com.cnes.checktool.oclrules.oclRules.OclRulesFactory;
import com.cnes.checktool.oclrules.oclRules.OclRulesPackage;
import com.cnes.checktool.oclrules.oclRules.util.OCLResource;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class OclRulesFactoryImpl extends EFactoryImpl implements OclRulesFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static OclRulesFactory init() {
		try {
			OclRulesFactory theOclRulesFactory = (OclRulesFactory)EPackage.Registry.INSTANCE.getEFactory(OclRulesPackage.eNS_URI);
			if (theOclRulesFactory != null) {
				return theOclRulesFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new OclRulesFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OclRulesFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case OclRulesPackage.OCL_RULE: return createOCLRule();
			case OclRulesPackage.OCL_RULE_FILE: return createOCLRuleFile();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case OclRulesPackage.OCL_RESOURCE:
				return createOCLResourceFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case OclRulesPackage.OCL_RESOURCE:
				return convertOCLResourceToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OCLRule createOCLRule() {
		OCLRuleImpl oclRule = new OCLRuleImpl();
		return oclRule;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OCLRuleFile createOCLRuleFile() {
		OCLRuleFileImpl oclRuleFile = new OCLRuleFileImpl();
		return oclRuleFile;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OCLResource createOCLResourceFromString(EDataType eDataType, String initialValue) {
		return (OCLResource)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertOCLResourceToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OclRulesPackage getOclRulesPackage() {
		return (OclRulesPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	public static OclRulesPackage getPackage() {
		return OclRulesPackage.eINSTANCE;
	}
	
	/**
	 * @param iFile
	 * @return
	 */
	public OCLRuleFile createRuleFile(IFile iFile)
    {
        try
        {
            OCLRuleFile rFile = createOCLRuleFile();
            rFile.setName(iFile.getName());
            InputStream in = iFile.getContents();
            InputStreamReader is = new InputStreamReader(in);
            StringBuilder sb = new StringBuilder();
            BufferedReader br = new BufferedReader(is);
            String read = br.readLine();

            while (read != null)
            {
                sb.append(read);
                sb.append("\n");
                read = br.readLine();
            }
            rFile.setFileContents(sb.toString());
            return rFile;
        }
        catch (Exception e)
        {
            Log.errorLog("Could not parse OCL file " + iFile.getName());
            e.printStackTrace();
        }
        return null;
    }

} //OclRulesFactoryImpl
