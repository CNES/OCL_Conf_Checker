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
package com.cnes.checktool.oclrules.oclRules.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EPackageRegistryImpl;
import org.eclipse.emf.ecore.resource.ResourceSet;

/**
 * A specific registry which to attach to a {@link ResourceSet} this registry uses by default epackages loaded in the
 * {@link ResourceSet} This class could be useless if one day an import notation is created in ocl files
 * 
 * @author Tristan Faure
 * 
 */
public class ToolkitRegistry extends EPackageRegistryImpl
{

    private static final long serialVersionUID = -6858839738274275988L;

    protected Map<String, EPackage> copy = new HashMap<String, EPackage>();

    public ToolkitRegistry()
    {
        super(EPackage.Registry.INSTANCE);
    }

    /**
     * @see org.eclipse.emf.ecore.impl.EPackageRegistryImpl#getEPackage(java.lang.String) This method is called when the
     *      resource set loads an epackage. Be careful do not use this registry for standard operation, because this
     *      method can be called in other way
     * @param nsURI
     * @return
     */
    @Override
    public EPackage getEPackage(String nsURI)
    {
        EPackage pack = super.getEPackage(nsURI);
        if (pack != null)
        {
            put(nsURI, pack);
            copy.put(nsURI, pack);
            put(pack.getNsPrefix(), pack);
            copy.put(pack.getNsPrefix(), pack);
        }
        return pack;
    }

    /**
     * @see java.util.HashMap#values()
     * 
     * @return
     */
    @Override
    public Collection<Object> values()
    {
        Collection<Object> objects = new HashSet<Object>(super.values());
        Collection<Object> delegate = delegateRegistry.values();
        for (Object o : delegate)
        {
            if (o instanceof EPackage)
            {
                EPackage pack = (EPackage) o;
                if (!copy.containsKey(pack.getNsPrefix()) && !copy.containsKey(pack.getNsURI()))
                {
                    objects.add(o);
                }
            }
        }
        return objects;
    }

}
