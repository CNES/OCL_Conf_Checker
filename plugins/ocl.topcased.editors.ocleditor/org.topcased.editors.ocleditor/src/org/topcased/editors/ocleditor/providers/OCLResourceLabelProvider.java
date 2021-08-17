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

import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.topcased.editors.ocleditor.OCLEditorPlugin;
import com.cnes.checktool.oclrules.oclRules.util.OCLResource;

/**
 * Label Provider for displaying OCL resources.<br />
 * 
 * @author <a href="mailto:sebastien.gabel@c-s.fr">Sebastien GABEL</a>
 */
public class OCLResourceLabelProvider extends LabelProvider
{

    /**
     * @see org.eclipse.jface.viewers.ILabelProvider#getText(java.lang.Object)
     */
    public String getText(Object object)
    {
        if (object instanceof OCLResource)
        {
            OCLResource resource = (OCLResource) object;
            return resource.makeRelative().toString();
        }
        return object.toString();
    }

    /**
     * @see org.eclipse.jface.viewers.LabelProvider#getImage(java.lang.Object)
     */
    @Override
    public Image getImage(Object element)
    {
        if (element instanceof URI)
        {
            return OCLEditorPlugin.getImageDescriptor("icons/package.gif").createImage();
        }
        if (element instanceof OCLResource)
        {
            ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
            return sharedImages.getImageDescriptor(ISharedImages.IMG_OBJ_FILE).createImage();
        }
        return null;
    }
}
