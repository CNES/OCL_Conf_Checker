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
package com.cnes.checktool.handlers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;

import com.cnes.checktool.utils.HandlerUtils;

public class CheckModelHandler extends AbstractHandler implements IHandler
{

    /**
     * method to adapt an object
     * 
     * @param o the object to adapt
     * @param aClass the class to adapt to
     * @return null if it can't be adapted, adapted object if there is one
     */
    protected <T> T adapt(Object o, Class<T> aClass)
    {
        T f = null;
        if (aClass.isInstance(o))
        {
            f = (T) o;
        }
        else if (o instanceof IAdaptable)
        {
            IAdaptable adaptable = (IAdaptable) o;
            f = (T) adaptable.getAdapter(aClass);
            if (f == null)
            {
                f = (T) Platform.getAdapterManager().getAdapter(o, aClass);
            }
        }
        return f;
    }

    /**
     * @see org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands.ExecutionEvent)
     */
    public Object execute(ExecutionEvent event) throws ExecutionException
    {
        // Get the content of the selection
        ISelection currentSelection = HandlerUtil.getCurrentSelection(event);
        List<IFile> files = new ArrayList<IFile>();
        List<String> extensions = Arrays.asList(new String[]{"xml","lvcugenconf"});
        if (currentSelection instanceof IStructuredSelection)
        {
            IStructuredSelection structuredSelection = (IStructuredSelection) currentSelection;
            for (Iterator<Object> i = structuredSelection.iterator(); i.hasNext();)
            {
                Object o = i.next();
                IFile f = adapt(o, IFile.class);
                if (f != null && (extensions.contains(f.getFileExtension())))
                {
                    files.add(f);
                }
                else
                {
                    Collection< ? > collec = adapt(o, Collection.class);
                    if (collec != null)
                    {
                        for (Object o2 : collec)
                        {
                            IFile f2 = adapt(o2, IFile.class);
                            if (f2 != null)
                            {
                                files.add(f2);
                            }
                        }
                    }
                }
            }
        }
        HandlerUtils.instance.checkModelGUI(files);
        return null;
    }

}
