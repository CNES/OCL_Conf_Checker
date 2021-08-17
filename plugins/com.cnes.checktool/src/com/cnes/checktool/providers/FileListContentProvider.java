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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.core.resources.IFile;

public class FileListContentProvider implements IStructuredContentProvider
{

    public void dispose()
    {
        // Do nothing

    }

    public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
    {
        // Do nothing
    }

    public Object[] getElements(Object inputElement)
    {
        List<IFile> result = new ArrayList<IFile>();
        if(inputElement instanceof List<?>) {
            for(Object obj : (List<?>) inputElement) {
                if(obj instanceof IFile) {
                    result.add((IFile) obj);
                }
            }
        }
        return result.toArray();
    }

}
