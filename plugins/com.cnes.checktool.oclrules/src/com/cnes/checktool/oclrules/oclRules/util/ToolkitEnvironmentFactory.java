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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.ocl.Environment;
import org.eclipse.ocl.EnvironmentFactory;
import org.eclipse.ocl.EvaluationEnvironment;
import org.eclipse.ocl.ecore.CallOperationAction;
import org.eclipse.ocl.ecore.Constraint;
import org.eclipse.ocl.ecore.EcoreEnvironmentFactory;
import org.eclipse.ocl.ecore.SendSignalAction;


/**
 * Implementation of the {@link EnvironmentFactory} for parsing OCL expressions on Ecore models.
 * 
 * @author Christian W. Damus (cdamus)
 */

public class ToolkitEnvironmentFactory extends EcoreEnvironmentFactory {

	public static ToolkitEnvironmentFactory INSTANCE = new ToolkitEnvironmentFactory();

	private EPackage.Registry registry;

	/**
	 * Initializes me. Environments that I create will use the global package registry to look up packages.
	 */
	public ToolkitEnvironmentFactory() {
		this(EPackage.Registry.INSTANCE);
	}

	/**
	 * Initializes me with an <code>EPackage.Registry</code> that the environments I create will use to look up
	 * packages.
	 * 
	 * @param reg
	 *        my package registry (must not be <code>null</code>)
	 */
	public ToolkitEnvironmentFactory(EPackage.Registry reg) {
		super();
		this.registry = reg;
	}

	@Override
	public Environment<EPackage, EClassifier, EOperation, EStructuralFeature, EEnumLiteral, EParameter, EObject, CallOperationAction, SendSignalAction, Constraint, EClass, EObject> createEnvironment(Environment<EPackage, EClassifier, EOperation, EStructuralFeature, EEnumLiteral, EParameter, EObject, CallOperationAction, SendSignalAction, Constraint, EClass, EObject> parent) {
		if(!(parent instanceof ToolkitOCLEnvironment)) {
			throw new IllegalArgumentException("Parent environment must be ecore environment: " + parent);
		}

		ToolkitOCLEnvironment result = new ToolkitOCLEnvironment((ToolkitOCLEnvironment)parent);
		result.setFactory(this);
		return result;
	}

	@Override
	public Environment<EPackage, EClassifier, EOperation, EStructuralFeature, EEnumLiteral, EParameter, EObject, CallOperationAction, SendSignalAction, Constraint, EClass, EObject> createEnvironment() {
		ToolkitOCLEnvironment result = new ToolkitOCLEnvironment(registry);
		result.setFactory(this);
		return result;
	}

	@Override
	public EvaluationEnvironment<EClassifier, EOperation, EStructuralFeature, EClass, EObject> createEvaluationEnvironment() {
		return new ToolkitEvaluationEnvironment();
	}

	@Override
	public EvaluationEnvironment<EClassifier, EOperation, EStructuralFeature, EClass, EObject> createEvaluationEnvironment(EvaluationEnvironment<EClassifier, EOperation, EStructuralFeature, EClass, EObject> parent) {
		return new ToolkitEvaluationEnvironment(parent);
	}

	@Override
	public Environment<EPackage, EClassifier, EOperation, EStructuralFeature, EEnumLiteral, EParameter, EObject, CallOperationAction, SendSignalAction, Constraint, EClass, EObject> loadEnvironment(Resource resource) {
		ToolkitOCLEnvironment result = new ToolkitOCLEnvironment(registry, resource);
		result.setFactory(this);
		return result;
	}

	public void setRegistry(Registry packageRegistry) {
		registry = packageRegistry;
	}

}
