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

import org.eclipse.emf.common.util.EList;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.List;


import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.ocl.ecore.Constraint;

import com.cnes.checktool.oclrules.ChecktoolOCLCheckerEngine;
import com.cnes.checktool.oclrules.oclRules.OCLRule;
import com.cnes.checktool.oclrules.oclRules.OCLRuleFile;
import com.cnes.checktool.oclrules.oclRules.OclRulesFactory;
import com.cnes.checktool.oclrules.oclRules.OclRulesPackage;
import com.cnes.checktool.oclrules.oclRules.util.OCLResource;
import com.cnes.checktool.results.Rule;
import com.cnes.checktool.results.impl.RuleFileImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>OCL Rule File</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class OCLRuleFileImpl extends RuleFileImpl implements OCLRuleFile {
	/**
     * @generated not
     */
    protected OCLResource resource;

    /**
     * @generated not
     */
    protected boolean isInit;

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    protected OCLRuleFileImpl()
    {
		super();
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass()
    {
		return OclRulesPackage.Literals.OCL_RULE_FILE;
	}

    /**
     * Gives empty child OCLRules without parsing of OCL files. This is useful because we need to determine the contents
     * of this OCL file at a time when the corresponding metamodel may have not been created yet.
     * 
     * @generated not
     */
    public void initRulesfromRuleset()
    {
        try
        {
            BufferedReader in = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(fileContents.getBytes("UTF-8"))));
            String read;
            while ((read = in.readLine()) != null)
            {
                String name = null;
                read = read.trim();
                if (read.startsWith("inv"))
                {
                    read = read.substring(4);
                    int colonIndex = read.indexOf(":");
                    name = read.substring(0, colonIndex).trim();
                }
                if (name != null)
                {
                    OCLRule createdRule = OclRulesFactory.eINSTANCE.createOCLRule();
                    getRules().add(createdRule);
                    createdRule.setName(name);
                }
            }
            in.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            // TODO;
        }
        return;
    }

    public void initRulesFromFile(IFile file)
    {
        throw new UnsupportedOperationException();
    }

    /**
     * @generated not
     */
    public OCLResource getResource()
    {
        return resource;
    }

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated not
	 */
	public void completeFiles(EList resources) {
		if (getRules().isEmpty())
        {
            initRulesfromRuleset();
        }

        if (isInit)
        {
            return;
        }
        this.isInit = true;

        try
        {
            InputStream in = new BufferedInputStream(new ByteArrayInputStream(fileContents.getBytes("UTF-8")));
            File tmpFile = File.createTempFile("tmpOCLRuleFile", ".ocl");
            tmpFile.deleteOnExit();
            OutputStream out = new BufferedOutputStream(new FileOutputStream(tmpFile));
            byte buf[] = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0)
                out.write(buf, 0, len);
            out.close();
            in.close();

            resource = new OCLResource(tmpFile.getAbsolutePath().toString());
            List<Constraint> constraints = ChecktoolOCLCheckerEngine.getInstance().getRulesFromFile(resources, getResource(), this.getName());
            if (constraints.isEmpty())
            {
                setIsValid(false);
                return;
            }
            for (Constraint constraint : constraints)
            {
                for (Rule rule : getRules())
                {
                    if (rule instanceof OCLRule && rule.getName().equals(constraint.getName()))
                    {
                        OCLRule oclRule = (OCLRule) rule;
                        oclRule.setQueryBody(constraint.getSpecification().getBodyExpression().toString());
                        oclRule.setConstraintName(constraint.getName());
                    }
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
	}

	/**
     * @return not
     */
    public String getFileExtension()
    {
        return "ocl";
    }

} //OCLRuleFileImpl
