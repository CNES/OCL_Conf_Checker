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

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.ocl.Environment;
import org.eclipse.ocl.EnvironmentFactory;
import org.eclipse.ocl.ecore.CallOperationAction;
import org.eclipse.ocl.ecore.Constraint;
import org.eclipse.ocl.ecore.EcoreEnvironment;
import org.eclipse.ocl.ecore.SendSignalAction;

import com.cnes.checktool.utils.MetamodelLoadingUtils;

/**
 * Implementation of the {@link Environment} for parsing OCL expressions on Ecore models. The
 * <code>EcoreEnvironment</code> uses a client-supplied package registry (or the global registry) to look up
 * {@link EPackage}s by qualified name.
 * 
 * @author <a href="mailto:sebastien.gabel@c-s.fr">Sebastien GABEL</a>
 * @author <a href="mailto:christophe.lecamus@c-s.fr">Christophe LE CAMUS</a>
 */
public class ToolkitOCLEnvironment extends EcoreEnvironment
{

    /*
     * Create a specific OCL type to support Sequence(String) as it is needed by numerous string parsing functions
     */
    protected EClassifier OCL_SEQUENCE_STRING = (EClassifier) getOCLFactory().createSequenceType(getOCLStandardLibrary().getString());

    /**
     * Initializes me with a package registry for package look-ups.
     * 
     * @param reg a package registry
     */
    protected ToolkitOCLEnvironment(EPackage.Registry reg)
    {
        super(reg);
        defineCustomOperations();
//        MetamodelLoadingUtils.loadEPackagesFromExtensions(reg);
    }
    

    /**
     * Initializes me with a parent environment, from which I inherit such things as a package registry and a resource.
     * 
     * @param parent my parent environment
     */
    protected ToolkitOCLEnvironment(ToolkitOCLEnvironment parent)
    {
        super(parent);
        OCL_SEQUENCE_STRING = parent.OCL_SEQUENCE_STRING;
    }

    /**
     * Initializes me with a package registry and a resource in which I am persisted (and from which I load myself if it
     * already has content).
     * 
     * @param reg a package registry
     * @param resource a resource, which may or may not already have content
     */

    protected ToolkitOCLEnvironment(EPackage.Registry reg, Resource resource)
    {
        super(reg, resource);
    }

    /**
     * Defines a set of custom operations for the SAM language and register them in the operation helper.
     */
    private void defineCustomOperations()
    {
        defineTrim();
        defineOclType();
        defineRegexMatch();
        defineSplit();
        defineCharAt();
        defineStartsWith();
        defineEndsWith();
        definehexStringToInt();
    }
    
    
    /**
	 * Defines String::definehexStringToInt() : Integer
	 * This method enables to convert hexadecimal integer strings (e.g 0xAB or 0XaB) to decimal integers 
	 */
	private void definehexStringToInt() {
		EOperation op = EcoreFactory.eINSTANCE.createEOperation();
		op.setName("hexStringToInt");
		op.setEType(getOCLStandardLibrary().getInteger());
		EAnnotation annotation = EcoreFactory.eINSTANCE.createEAnnotation();
		annotation.setSource("ToolKitOCLEnvironment");
		op.getEAnnotations().add(annotation);
		addHelperOperation(getOCLStandardLibrary().getString(), op);
	}

    /**
     * Defines String::trim() : String
     */
    private void defineTrim()
    {
        EOperation op = EcoreFactory.eINSTANCE.createEOperation();
        op.setName("trim");
        op.setEType(getOCLStandardLibrary().getString());

        EAnnotation annotation = EcoreFactory.eINSTANCE.createEAnnotation();
        annotation.setSource("ToolKitOCLEnvironment");
        op.getEAnnotations().add(annotation);

        addHelperOperation(getOCLStandardLibrary().getString(), op);        
    }

    /**
     * Defines OclAny::oclType() : String
     */
    private void defineOclType()
    {
        EOperation op = EcoreFactory.eINSTANCE.createEOperation();
        op.setName("oclType");
        op.setEType(getOCLStandardLibrary().getOclType());

        EAnnotation annotation = EcoreFactory.eINSTANCE.createEAnnotation();
        annotation.setSource("ToolKitOCLEnvironment");
        op.getEAnnotations().add(annotation);

        addHelperOperation(getOCLStandardLibrary().getOclAny(), op);
    }

    /**
     * Defines String::regexMatch(String) : Sequence(String)
     */
    private void defineRegexMatch()
    {
        EOperation op = EcoreFactory.eINSTANCE.createEOperation();
        op.setName("regexMatch");
        op.setEType(OCL_SEQUENCE_STRING);
        
        EParameter parm = EcoreFactory.eINSTANCE.createEParameter();
        parm.setName("pattern");
        parm.setEType(getOCLStandardLibrary().getString());
        op.getEParameters().add(parm);

        // annotate it so that we will recognize it in the evaluation environment
        EAnnotation annotation = EcoreFactory.eINSTANCE.createEAnnotation();
        annotation.setSource("ToolKitOCLEnvironment");
        op.getEAnnotations().add(annotation);

        // define it as an additional operation on OCL String
        addHelperOperation(getOCLStandardLibrary().getString(), op);
    }

    /**
     * Defines String::Split() : Sequence(String)
     */
    private void defineSplit()
    {
        EOperation op = EcoreFactory.eINSTANCE.createEOperation();
        op.setName("split");
        op.setEType(OCL_SEQUENCE_STRING);

        EAnnotation annotation = EcoreFactory.eINSTANCE.createEAnnotation();
        annotation.setSource("ToolKitOCLEnvironment");
        op.getEAnnotations().add(annotation);

        addHelperOperation(getOCLStandardLibrary().getString(), op);
    }

    /**
     * Defines String::charAt() : String
     */
    private void defineCharAt()
    {
        EOperation op = EcoreFactory.eINSTANCE.createEOperation();
        op.setName("charAt");
        op.setEType(getOCLStandardLibrary().getString());
        
        EParameter parm = EcoreFactory.eINSTANCE.createEParameter();
        parm.setName("position");
        parm.setEType(getOCLStandardLibrary().getInteger());
        op.getEParameters().add(parm);

        EAnnotation annotation = EcoreFactory.eINSTANCE.createEAnnotation();
        annotation.setSource("ToolKitOCLEnvironment");
        op.getEAnnotations().add(annotation);

        addHelperOperation(getOCLStandardLibrary().getString(), op);
    }

    /**
     * Defines String::startsWith() : String
     */
    private void defineStartsWith()
    {
        EOperation op = EcoreFactory.eINSTANCE.createEOperation();
        op.setName("startsWith");
        op.setEType(getOCLStandardLibrary().getBoolean());
        
        EParameter parm = EcoreFactory.eINSTANCE.createEParameter();
        parm.setName("subString");
        parm.setEType(getOCLStandardLibrary().getString());
        op.getEParameters().add(parm);

        EAnnotation annotation = EcoreFactory.eINSTANCE.createEAnnotation();
        annotation.setSource("ToolKitOCLEnvironment");
        op.getEAnnotations().add(annotation);

        addHelperOperation(getOCLStandardLibrary().getString(), op);
    }

    /**
     * Defines String::endsWith() : String
     */
    private void defineEndsWith()
    {
        EOperation op = EcoreFactory.eINSTANCE.createEOperation();
        op.setName("endsWith");
        op.setEType(getOCLStandardLibrary().getBoolean());
        
        EParameter parm = EcoreFactory.eINSTANCE.createEParameter();
        parm.setName("subString");
        parm.setEType(getOCLStandardLibrary().getString());
        op.getEParameters().add(parm);

        EAnnotation annotation = EcoreFactory.eINSTANCE.createEAnnotation();
        annotation.setSource("ToolKitOCLEnvironment");
        op.getEAnnotations().add(annotation);

        addHelperOperation(getOCLStandardLibrary().getString(), op);
    }


    /**
     * @see org.eclipse.ocl.ecore.EcoreEnvironment#setFactory(org.eclipse.ocl.EnvironmentFactory)
     */
    @Override
    protected void setFactory(
            EnvironmentFactory<EPackage, EClassifier, EOperation, EStructuralFeature, EEnumLiteral, EParameter, EObject, CallOperationAction, SendSignalAction, Constraint, EClass, EObject> factory)
    {
        super.setFactory(factory);
    }
}
