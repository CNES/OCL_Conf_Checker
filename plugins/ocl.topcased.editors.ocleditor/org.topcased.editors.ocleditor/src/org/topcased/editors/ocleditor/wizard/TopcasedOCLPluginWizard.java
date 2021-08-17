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
package org.topcased.editors.ocleditor.wizard;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.core.plugin.IPluginReference;
import org.eclipse.pde.ui.IFieldData;
import org.eclipse.pde.ui.IPluginContentWizard;
import org.topcased.editors.ocleditor.OCLEditorPlugin;
import org.topcased.editors.ocleditor.export.TopcasedOCLPluginGenerator;

/**
 * Represents the part of the wizard that is specific to the OCL plug-in creation.<br>
 * 
 * @author <a href="mailto:sebastien.gabel@c-s.fr">Sebastien GABEL</a>
 */
public class TopcasedOCLPluginWizard extends Wizard implements IPluginContentWizard
{
    /**
     * The selection page => last page of the wizard
     */
    private OCLSelectionPage newPage;

    public TopcasedOCLPluginWizard()
    {
        super();
        setDefaultPageImageDescriptor(OCLEditorPlugin.getImageDescriptor("icons/editor/ocl.gif"));
        setWindowTitle("New Topcased plug-in project with OCL files");
    }

    /**
     * Adds the future 'model' directory in the build.properties file.
     * 
     * @see org.eclipse.pde.ui.IPluginContentWizard#getNewFiles()
     */
    public String[] getNewFiles()
    {
        String[] newFiles = new String[1];
        newFiles[0] = "model/";
        return newFiles;
    }

    /**
     * @see org.eclipse.pde.ui.IPluginContentWizard#init(org.eclipse.pde.ui.IFieldData)
     */
    public void init(IFieldData data)
    {
        // Nothing to do
    }

    /**
     * @see org.eclipse.pde.ui.IPluginContentWizard#performFinish(org.eclipse.core.resources.IProject,
     *      org.eclipse.pde.core.plugin.IPluginModelBase, org.eclipse.core.runtime.IProgressMonitor)
     */
    public boolean performFinish(IProject project, IPluginModelBase model, IProgressMonitor monitor)
    {
        TopcasedOCLPluginGenerator.createOCLPluginContent(project, model, newPage.getSelectedResources());
        return true;
    }

    /**
     * @see org.eclipse.jface.wizard.Wizard#addPages()
     */
    public void addPages()
    {
        super.addPages();
        newPage = new OCLSelectionPage();
        addPage(newPage);
    }

    /**
     * @see org.eclipse.jface.wizard.Wizard#performFinish()
     */
    public boolean performFinish()
    {
        return true;
    }

    /**
     * @see org.eclipse.pde.ui.IPluginContentWizard#getDependencies(java.lang.String)
     */
    public IPluginReference[] getDependencies(String schemaVersion)
    {
        // So they must be returned here to be added to the list of the plug-in dependencies
        return new IPluginReference[0];
    }
}