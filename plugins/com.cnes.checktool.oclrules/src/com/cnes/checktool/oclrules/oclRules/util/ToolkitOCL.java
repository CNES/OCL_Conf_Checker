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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.GenericXMLResourceFactoryImpl;
import org.eclipse.ocl.Environment;
import org.eclipse.ocl.EnvironmentFactory;
import org.eclipse.ocl.OCL;
import org.eclipse.ocl.ecore.CallOperationAction;
import org.eclipse.ocl.ecore.Constraint;
import org.eclipse.ocl.ecore.SendSignalAction;
import org.eclipse.ocl.options.ParsingOptions;

import com.cnes.checktool.Log;
import com.cnes.checktool.utils.MetamodelLoadingUtils;

/**
 * Class allowing to get the OCL object build around the defined ToolKitOCLEnvironment.
 * 
 * @author <a href="mailto:sebastien.gabel@c-s.fr">Sebastien GABEL</a>
 * @since Topcased 3.2.0
 * @see {@link org.ToolkitOCLEnvironment.ocl.TopcasedOCLEnvironment}
 */
public final class ToolkitOCL
{

    private static final String PACKAGE_ELEMENT = "package";

    private static final String NSURI_ATTRIBUTE = "nsURI";

    private static final String CLASS_ATTRIBUTE = "class";

    static
    {
        // resolve all the epackage to make possible the usage of nsPrefix
        Collection<String> keys = new ArrayList<String>(EPackage.Registry.INSTANCE.keySet());
        for (String s : keys)
        {
            // if a package descriptor exists it will be automatically resolved in getEPackage method
        	try
        	{
        		EPackage.Registry.INSTANCE.getEPackage(s);
        	}
        	catch (Throwable e)
        	{
        		// an exception does not have to stop the init process
        		e.printStackTrace();
        	}
        }
    }

    public static OCL<EPackage, EClassifier, EOperation, EStructuralFeature, ? , ? , ? , ? , ? , Constraint, EClass, EObject> getOCL(EPackage pkg, ResourceSet set)
    {
        EnvironmentFactory<EPackage, EClassifier, EOperation, EStructuralFeature, EEnumLiteral, EParameter, EObject, CallOperationAction, SendSignalAction, Constraint, EClass, EObject> factory = ToolkitOCL.getEnvironmentFactory(pkg.getNsURI());
        if (set != null && factory instanceof ToolkitEnvironmentFactory)
        {
            set.getPackageRegistry();
//             if(data != null) {
//             System.out.println(data);
//             }
            ToolkitEnvironmentFactory topFactory = (ToolkitEnvironmentFactory) factory;
            EPackage.Registry packageRegistry = set.getPackageRegistry();
            MetamodelLoadingUtils.loadEPackagesFromExtensions(packageRegistry);
            topFactory.setRegistry(packageRegistry);
        }
        else if (set != null)
        {
            Log.infoLog("A factory with default package management has been loaded : " + factory.getClass().toString());
        }
        List<String> listPackages = new BasicEList<String>();
        listPackages.add(pkg.getName());
        for (EPackage p : MetamodelLoadingUtils.getEPackagesToLoad()) {
        	listPackages.add(p.getName());	
        }
        
        Environment<EPackage, EClassifier, EOperation, EStructuralFeature, EEnumLiteral, EParameter, EObject, CallOperationAction, SendSignalAction, Constraint, EClass, EObject> createEnvironment = factory.createEnvironment();
        Environment<EPackage, EClassifier, EOperation, EStructuralFeature, EEnumLiteral, EParameter, EObject, CallOperationAction, SendSignalAction, Constraint, EClass, EObject> createPackageContext = factory.createPackageContext(
                createEnvironment, listPackages);
        OCL<EPackage, EClassifier, EOperation, EStructuralFeature, ? , ? , ? , ? , ? , Constraint, EClass, EObject> ocl = null;
        if (createPackageContext == null)
        {
            ocl = OCL.newInstance(factory);
        }
        else
        {
            ocl = OCL.newInstance(createPackageContext);
        }

        ParsingOptions.setOption(ocl.getEnvironment(), ParsingOptions.implicitRootClass(ocl.getEnvironment()), EcorePackage.Literals.EOBJECT);

        return ocl;
    }

    /**
     * Gets the OCL object for the {@link Epackage} given in parameter.<br>
     * 
     * @param modelPackage The model package
     * @return The corresponding OCL helper
     */
    public static OCL<EPackage, EClassifier, EOperation, EStructuralFeature, ? , ? , ? , ? , ? , Constraint, EClass, EObject> getOCL(EPackage pkg)
    {
        return getOCL(pkg, null);
    }
    
    public static ResourceSet createConfiguredResourceSet()
    {
    	ResourceSet set = new ResourceSetImpl();
    	set.getLoadOptions().put(XMLResource.OPTION_DEFER_ATTACHMENT, true);
    	set.getLoadOptions().put(XMLResource.OPTION_DEFER_IDREF_RESOLUTION, true);
    	set.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xml", new GenericXMLResourceFactoryImpl());
    	set.setPackageRegistry(new ToolkitRegistry());
    	return set;
    }

    /**
     * Searches all plug-ins which use OCL Environments extension point.
     * 
     * @FIXME : I tried to use {@link Environment.Registry} without success.
     */
    @SuppressWarnings("unchecked")
    private static EnvironmentFactory<EPackage, EClassifier, EOperation, EStructuralFeature, EEnumLiteral, EParameter, EObject, CallOperationAction, SendSignalAction, Constraint, EClass, EObject> getEnvironmentFactory(
            String uri2Retrieve)
    {
        IConfigurationElement[] elements = Platform.getExtensionRegistry().getConfigurationElementsFor("com.cnes.checktool.ocl.rules.environments"); //$NON-NLS-1$
        for (IConfigurationElement element : elements)
        {
            if (element.getChildren(PACKAGE_ELEMENT) != null) //$NON-NLS-1$
            {
                IConfigurationElement[] packageElements = element.getChildren(PACKAGE_ELEMENT); //$NON-NLS-1$
                for (IConfigurationElement packageElt : packageElements)
                {
                    if (packageElt.getAttribute(NSURI_ATTRIBUTE).equals(uri2Retrieve)) //$NON-NLS-1$
                    {
                        try
                        {
                            return (EnvironmentFactory<EPackage, EClassifier, EOperation, EStructuralFeature, EEnumLiteral, EParameter, EObject, CallOperationAction, SendSignalAction, Constraint, EClass, EObject>) element.createExecutableExtension(CLASS_ATTRIBUTE);
                        }
                        catch (CoreException e)
                        {
                            Log.errorLog("Cannot instantiate the Environment Factory for : " + uri2Retrieve);
                        }
                    }
                }
            }
        }
        return new ToolkitEnvironmentFactory(EPackage.Registry.INSTANCE);
    }

}
