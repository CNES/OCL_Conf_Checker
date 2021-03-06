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
import java.util.Collections;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EContentAdapter;

/**
 * This cache creates a map associating EClasses to all the corresponding
 * This implementation takes less space but it is less performant for get and put methods
 * instances
 * 
 * @author Tristan Faure
 */
public class ModelSetQueryAdapterSizeMatters extends EContentAdapter {

	/**
	 * The cache of elements
	 */
	private Map<EClassifier, Collection<EObject>> cache = Collections.synchronizedMap(new IdentityHashMap<EClassifier, Collection<EObject>>());

	private Map<EClassifier, Collection<EClassifier>> subTypes = Collections.synchronizedMap(new IdentityHashMap<EClassifier, Collection<EClassifier>>());

	public ModelSetQueryAdapterSizeMatters() {
		super();
	}
	
	public int size() {
		return cache.size();
	}
	
	public void clear (){
		cache.clear();
		subTypes.clear();
	}

	protected void addAdapter(Notifier notifier) {
		super.addAdapter(notifier);
		if(notifier instanceof EObject) {
			EObject eobject = (EObject)notifier;
			addObjectInCache(eobject);
		}
	}

	@Override
	protected void removeAdapter(Notifier notifier) {
		super.removeAdapter(notifier);
		if(notifier instanceof EObject) {
			EObject eobject = (EObject)notifier;
			removeObjectFromCache(eobject);
		}
	}

	private void addObjectInCache(EObject newObj) {
		putObjectInCache(newObj);
	}

	private void putObjectInCache(EObject newObj) {
		putObjectInMap(newObj.eClass(), newObj);
		addSubTypes(newObj.eClass());
	}

	private void addSubTypes(EClass eClassifier) {
		for(EClass superType : eClassifier.getESuperTypes()) {
			addSubType(superType, eClassifier);
			addSubTypes(superType);
		}
	}

	protected void addSubType(EClass superType, EClassifier eClassifier) {
		Collection<EClassifier> result = subTypes.get(superType);
		if(result == null) {
			result = new HashSet<EClassifier>();
			subTypes.put(superType, result);
		}
		result.add(eClassifier);
	}

	private void putObjectInMap(EClassifier eClassifier, EObject obj) {
		Collection<EObject> result = cache.get(eClassifier);
		if(result == null) {
			result = new HashSet<EObject>();
			cache.put(eClassifier, result);
		}
		result.add(obj);
	}

	private void removeObjectFromCache(EObject newObj) {
		EClass eClass = newObj.eClass();
		removeObjectFromCache(eClass, newObj);
	}

	private void removeObjectFromCache(EClassifier eClassifier, EObject newObj) {
		Collection<EObject> listOfClassifiers = cache.get(eClassifier);
		if(listOfClassifiers != null) {
			listOfClassifiers.remove(newObj);
			if(listOfClassifiers.isEmpty()) {
				cache.remove(eClassifier);
			}
		}
	}

	public Collection<EObject> getReachableObjectsOfType(EObject object, EClassifier type) {
		Set<EObject> buffer = new HashSet<EObject>();
		Set<EClassifier> alreadyComputed = new HashSet<EClassifier>();
		Stack<EClassifier> types = new Stack<EClassifier>();
		types.push(type);
		while(!types.isEmpty()) {
			EClassifier top = types.pop();
			alreadyComputed.add(top);
			// add instances to the buffer
			Collection<EObject> c = cache.get(top);
			if(c != null) {
				buffer.addAll(c);
			}
			//  compute sub types
			Collection<EClassifier> c2 = subTypes.get(top);
			if(c2 != null && !alreadyComputed.contains(c2)) {
				types.addAll(c2);
			}
		}
		return buffer;
	}

	public void dispose() {
		cache.clear();
		subTypes.clear();
		cache = null;
		subTypes = null;
	}

	/**
	 * This method provides a way for user to force first entries in the cache.
	 * The list of element must be a HashSet to optimize the performances
	 * 
	 * @param type
	 * @param list
	 */
	public void addEntriesInCache(EClassifier type, HashSet<EObject> list) {
		for(EObject e : list) {
			addObjectInCache(e);
		}
	}

	public boolean isAlreadyComputed(EClassifier type) {
		return cache.containsKey(type);
	}

}