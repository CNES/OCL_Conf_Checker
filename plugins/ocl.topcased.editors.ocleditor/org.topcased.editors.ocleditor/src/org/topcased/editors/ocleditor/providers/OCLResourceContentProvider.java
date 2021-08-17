/***********************************************************************************************************************
 * Copyright (c) 2008 TOPCASED consortium.
 * 
 * All rights reserved. This program and the accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sebastien GABEL (CS) - initial API and implementation
 * 
 **********************************************************************************************************************/
package org.topcased.editors.ocleditor.providers;

import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;

/**
 * Content provider for displaying selected OCL resources by metamodel URI.<br />
 * 
 * @author <a href="mailto:sebastien.gabel@c-s.fr">Sebastien GABEL</a>
 */
public class OCLResourceContentProvider extends ArrayContentProvider implements ITreeContentProvider
{
    private Map<URI, List<IFile>> input = null;
    
    /**
     * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
     */
    @SuppressWarnings("unchecked")
    public Object[] getElements(Object inputElement)
    {
        if (inputElement instanceof Map)
        {
            input = (Map<URI, List<IFile>>) inputElement;
            return input.keySet().toArray();
        }
        return super.getElements(inputElement);
    }

    public Object[] getChildren(Object parentElement)
    {
        if (parentElement instanceof URI)
        {
            return input.get(parentElement).toArray();
        }
        return null;
    }

    public Object getParent(Object element)
    {
        return null;
    }

    public boolean hasChildren(Object element)
    {
        // in our map, the URI represents the key
        if (element instanceof URI)
        {
            List<IFile> tempList = input.get(element);
            return tempList != null && !tempList.isEmpty();
        }
        return false;
    }
}