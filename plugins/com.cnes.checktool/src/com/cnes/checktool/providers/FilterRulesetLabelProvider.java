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
package com.cnes.checktool.providers;

import org.eclipse.jface.viewers.LabelProvider;

import com.cnes.checktool.results.Rule;
import com.cnes.checktool.results.RuleFile;
import com.cnes.checktool.results.RuleSet;

public class FilterRulesetLabelProvider extends LabelProvider
{
    public FilterRulesetLabelProvider()
    {
    }

    @Override
    public String getText(Object element)
    {
        if (element instanceof Rule)
        {
            return ((Rule) element).getName();
        }
        else if (element instanceof RuleSet)
        {
            RuleSet set = (RuleSet) element;
            return set.getName() + " v" + set.getVersion().toString();
        }
        else if (element instanceof RuleFile)
        {
            return ((RuleFile) element).getName();
        }
        return super.getText(element);
    }
}
