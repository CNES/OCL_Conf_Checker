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

import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.cnes.checktool.results.Rule;
import com.cnes.checktool.results.RuleFile;
import com.cnes.checktool.results.RuleSet;

public class FilterRulesetContentProvider implements ITreeContentProvider
{

    private List<RuleSet> ruleSets;

    public void dispose()
    {
        //Do nothing
    }

    public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
    {
    }

    public Object[] getElements(Object inputElement)
    {
        if (inputElement instanceof List<?>)
        {
            ruleSets = ((List<RuleSet>) inputElement);
            return ruleSets.toArray();
        }
        return null;
    }

    public Object[] getChildren(Object parentElement)
    {
        if (parentElement instanceof RuleSet)
        {
            return ((RuleSet) parentElement).getRuleFiles().toArray();
        }
        else if (parentElement instanceof RuleFile)
        {
            return ((RuleFile) parentElement).getRules().toArray();
        }
        return null;
    }

    public Object getParent(Object element)
    {
        if(element instanceof RuleFile) {
            for(RuleSet set : ruleSets) {
                if(set.getRuleFiles().contains(element)) {
                    return set;
                }
            }
        }
        else if(element instanceof Rule) {
            for(RuleSet set : ruleSets) {
                for(RuleFile file : set.getRuleFiles()) {
                    if(file.getRules().contains(element)) {
                        return file;
                    }
                }
            }
        }
        return null;
    }

    public boolean hasChildren(Object element)
    {
        final Object[] children = getChildren(element);
        return !(children == null || children.length == 0);
    }

}
