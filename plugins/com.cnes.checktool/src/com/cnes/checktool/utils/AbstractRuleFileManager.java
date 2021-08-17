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
package com.cnes.checktool.utils;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.emf.ecore.resource.Resource;

import com.cnes.checktool.results.RuleFile;
import com.cnes.checktool.results.RuleFileResult;

/**
 * @author proland
 */
public abstract class AbstractRuleFileManager implements IExecutableExtension
{

    public abstract RuleFile createRuleFile(IFile iFile);
    
    public abstract RuleFileResult evaluateRuleFile(RuleFile file, List<Resource> processedResources);

}
