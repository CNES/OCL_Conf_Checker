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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EPackage;

/**
 * Utility class for metamodel and EPackages loading
 *
 */
public class MetamodelLoadingUtils {

	public static final String METAMODEL_EPACKAGES_EXTENSION ="com.cnes.checktool.metamodel.epackage";
	
	/**
	 * Get EPackages to load from list of EPackages references in extension point
	 * @return list of EPackages
	 */
	public static List<EPackage> getEPackagesToLoad() {
		List<EPackage> epackagesToLoad = new LinkedList<EPackage>();
		
		IConfigurationElement[] elements = Platform.getExtensionRegistry().getConfigurationElementsFor(METAMODEL_EPACKAGES_EXTENSION);
		for (IConfigurationElement element : elements) {
//			try {
				String s = element.getAttribute("EPackageURI");
				EPackage epackage = EPackage.Registry.INSTANCE.getEPackage(s);
				if (epackage != null) {
					epackagesToLoad.add(epackage);
				}
//			} catch( CoreException e){
//				// TODO log the exception 
//			}
		}
		
		return epackagesToLoad;
	}
	
	
	public static void loadEPackagesFromExtensions(EPackage.Registry registry) {
		for (EPackage ePackage : getEPackagesToLoad()) {
			loadEPackage(registry, ePackage);
		}
	}
	
	
	/**
	 * Get EPackages to load from list of EPackages references in extension point
	 * @return list of EPackages
	 */
	public static void loadEPackage(EPackage.Registry registry, EPackage ePackage) {

        List<EPackage> packages = new ArrayList<EPackage>();
        packages.addAll(ePackage.getESubpackages());
        packages.add(0, ePackage);
        for (Iterator<EPackage> it = packages.iterator(); it.hasNext();)
        {
            EPackage p = it.next();
            String nsURI = p.getNsURI();
            if (nsURI == null)
            {
                nsURI = p.getName();
                p.setNsURI(nsURI);
            } 
            if (registry != null)
            {
            	registry.put(nsURI, p);
            }
        }
	}
	
	
	
	  /**
     * Register the ePackages if it has not been registered before. This is necessary to take into account local meta
     * models that have not been prepared as new plugin.
     * 
     * @param ePackage
     */
    public static void registerMetaModel(EPackage ePackage)
    {
    	loadEPackage(EPackage.Registry.INSTANCE, ePackage);
    }
}
