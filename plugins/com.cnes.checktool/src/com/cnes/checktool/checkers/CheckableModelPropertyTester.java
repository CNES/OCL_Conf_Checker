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
package com.cnes.checktool.checkers;

import java.util.Collection;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.content.IContentType;

/**
 * Property tester responsible for enabling/disabling the Check command according to the user selection.<br>
 * Actually, the check command must be available only when the selected resource is an EMF model registered as valid
 * content-type.
 * 
 * Creation 5th January 2009<br>
 * 
 * @author <a href="mailto:sebastien.gabel@c-s.fr">Sebastien GABEL</a>
 * @since Topcased 2.4.0
 * 
 */
public class CheckableModelPropertyTester extends PropertyTester
{
    /**
     * @see org.eclipse.core.expressions.IPropertyTester#test(java.lang.Object, java.lang.String, java.lang.Object[],
     *      java.lang.Object)
     */
    public boolean test(Object receiver, String property, Object[] args, Object expectedValue)
    {
        if (receiver instanceof IFile)
        {
            IFile toTest = (IFile) receiver;
            IContentType[] contentTypeArray = Platform.getContentTypeManager().findContentTypesFor(toTest.getFullPath().lastSegment());
            for (IContentType contentType : contentTypeArray)
            {
                if (expectedValue.equals(contentType.getId()))
                {
                    return true;
                }
            }
        } else if (receiver instanceof Collection<?>) {
            Collection<?> toTest = (Collection<?>) receiver;
            boolean result = true ;
            for (Object o : toTest)
            {
                result &= test(o,property,args,expectedValue);
            }
            return result;
        }
        return false;
    }
}
