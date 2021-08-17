/***********************************************************************************************************************
 * Copyright (c) 2005-2008 TOPCASED consortium.
 * 
 * Contributors: Guillaume Jolly (CS),
 *               Christophe Le Camus (CS)
 *               Sebastien GABEL (CS) - initial API and implementation
 * 
 * All rights reserved. This program and the accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 **********************************************************************************************************************/
package org.topcased.editors.ocleditor.wizard;

import java.io.File;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ContainerSelectionDialog;
import org.eclipse.ui.dialogs.ResourceSelectionDialog;
import org.topcased.editors.ocleditor.Messages;

/**
 * Wizard page allowing creation of OCL rule files in the workspace.
 * 
 * @author <a href="mailto:sebastien.gabel@c-s.fr">Sebastien GABEL</a>
 */
public class NewRuleFileWizardPage extends WizardPage
{
    private static final String DEFAULT_FILENAME = "ocl_rule"; //$NON-NLS-1$

    private static final String DEFAULT_EXTENSION = "oclfile"; //$NON-NLS-1$

    private Text containerText;

    private Text fileText;

    private Combo modelUriCombo;

    private Text modelText;

    private Button modelUriRadioButton;

    private Button modelFileRadioButton;

    private Button modelBrowseWorkspaceButton;

    private Button modelBrowseSystemButton;

    private ISelection selection;

    private ComboViewer modelUriComboViewer;

    /**
     * Constructor
     * 
     * @param selection The selection
     */
    protected NewRuleFileWizardPage(ISelection selection)
    {
        super("page"); //$NON-NLS-1$
        setTitle(Messages.getString("NewRuleFileWizardPage.3")); //$NON-NLS-1$
        setDescription(Messages.getString("NewRuleFileWizardPage.4")); //$NON-NLS-1$
        this.selection = selection;
    }

    /**
     * Creates the main content of the page.
     * 
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    public void createControl(Composite parent)
    {
        Composite container = new Composite(parent, SWT.NULL);
        container.setLayout(new GridLayout(3, false));

        Label containerLabel = new Label(container, SWT.NONE);
        containerLabel.setText(Messages.getString("NewRuleFileWizardPage.5")); //$NON-NLS-1$
        GridData gd = new GridData();
        gd.widthHint = containerLabel.getText().length() * containerLabel.getFont().getFontData()[0].getHeight() * 2 / 3;
        containerLabel.setLayoutData(gd);

        containerText = new Text(container, SWT.BORDER | SWT.SINGLE);
        containerText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        containerText.addModifyListener(new ModifyListener()
        {
            public void modifyText(ModifyEvent e)
            {
                dialogChanged();
            }
        });

        Button button = new Button(container, SWT.PUSH);
        button.setText(Messages.getString("NewRuleFileWizardPage.6")); //$NON-NLS-1$
        button.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
                handleBrowse();
            }
        });

        Label fileLabel = new Label(container, SWT.NONE);
        fileLabel.setText(Messages.getString("NewRuleFileWizardPage.7")); //$NON-NLS-1$

        fileText = new Text(container, SWT.BORDER | SWT.SINGLE);
        gd = new GridData(SWT.FILL, SWT.NONE, true, false);
        gd.horizontalSpan = 2;
        fileText.setLayoutData(gd);
        fileText.addModifyListener(new ModifyListener()
        {
            public void modifyText(ModifyEvent e)
            {
                dialogChanged();
            }
        });

        modelUriRadioButton = new Button(container, SWT.RADIO);
        modelUriRadioButton.setText(Messages.getString("NewRuleFileWizardPage.8")); //$NON-NLS-1$
        modelUriRadioButton.setSelection(true);
        gd = new GridData();
        gd.widthHint = modelUriRadioButton.getFont().getFontData()[0].getHeight() * modelUriRadioButton.getText().length() * 2 / 3 + 20;
        // ;
        modelUriRadioButton.setLayoutData(gd);
        modelUriRadioButton.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
                modelUriCombo.setEnabled(true);
                modelText.setEnabled(false);
                modelBrowseWorkspaceButton.setEnabled(false);
                modelBrowseSystemButton.setEnabled(false);
                dialogChanged();
            }
        });

        modelUriCombo = new Combo(container, SWT.READ_ONLY | SWT.BORDER);
        gd = new GridData(SWT.FILL, SWT.NONE, true, false);
        gd.horizontalSpan = 2;
        modelUriCombo.setLayoutData(gd);
        modelUriCombo.addModifyListener(new ModifyListener()
        {
            public void modifyText(ModifyEvent e)
            {
                dialogChanged();
            }
        });

        modelUriComboViewer = new ComboViewer(modelUriCombo);
        modelUriComboViewer.setSorter(new ViewerSorter());

        fillComboBox();

        modelFileRadioButton = new Button(container, SWT.RADIO);
        modelFileRadioButton.setText(Messages.getString("NewRuleFileWizardPage.9")); //$NON-NLS-1$
        modelFileRadioButton.setSelection(false);
        modelFileRadioButton.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
                modelUriCombo.setEnabled(false);
                modelText.setEnabled(true);
                modelBrowseWorkspaceButton.setEnabled(true);
                modelBrowseSystemButton.setEnabled(true);
                dialogChanged();
            }
        });

        modelText = new Text(container, SWT.BORDER | SWT.SINGLE);
        gd = new GridData(SWT.FILL, SWT.NONE, true, false);
        gd.horizontalSpan = 2;
        modelText.setLayoutData(gd);
        modelText.setEnabled(false);
        modelText.addModifyListener(new ModifyListener()
        {
            public void modifyText(ModifyEvent e)
            {
                dialogChanged();
            }
        });

        Composite modelButtonsComposite = new Composite(container, SWT.NONE);
        gd = new GridData(SWT.RIGHT, SWT.NONE, true, false);
        gd.horizontalSpan = 3;
        modelButtonsComposite.setLayoutData(gd);
        modelButtonsComposite.setLayout(new GridLayout(2, true));

        modelBrowseWorkspaceButton = new Button(modelButtonsComposite, SWT.PUSH);
        modelBrowseWorkspaceButton.setText(Messages.getString("NewRuleFileWizardPage.10")); //$NON-NLS-1$
        modelBrowseWorkspaceButton.setEnabled(false);
        modelBrowseWorkspaceButton.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
                handleWorkspaceBrowse();
            }
        });

        modelBrowseSystemButton = new Button(modelButtonsComposite, SWT.PUSH);
        modelBrowseSystemButton.setText(Messages.getString("NewRuleFileWizardPage.11")); //$NON-NLS-1$
        modelBrowseSystemButton.setEnabled(false);
        modelBrowseSystemButton.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
                handleSystemBrowse();
            }
        });

        initialize();
        dialogChanged();
        setControl(container);
    }

    /**
     * Fill the text fields with default information
     */
    private void initialize()
    {
        if (selection != null && !selection.isEmpty() && selection instanceof IStructuredSelection)
        {
            IStructuredSelection ssel = (IStructuredSelection) selection;
            if (ssel.size() > 1)
            {
                return;
            }
            Object obj = ssel.getFirstElement();
            if (obj instanceof IResource)
            {
                IContainer container;
                if (obj instanceof IContainer)
                {
                    container = (IContainer) obj;
                }
                else
                {
                    container = ((IResource) obj).getParent();
                }
                containerText.setText(container.getFullPath().toString());
            }
        }
        fileText.setText(DEFAULT_FILENAME + "." + DEFAULT_EXTENSION); //$NON-NLS-1$
    }

    /**
     * Handles a simple browse operation
     */
    private void handleBrowse()
    {
        ContainerSelectionDialog dialog = new ContainerSelectionDialog(getShell(), ResourcesPlugin.getWorkspace().getRoot(), false, Messages.getString("NewRuleFileWizardPage.13")); //$NON-NLS-1$
        if (dialog.open() == ContainerSelectionDialog.OK)
        {
            Object[] result = dialog.getResult();
            if (result.length == 1)
            {
                containerText.setText(((Path) result[0]).toString());
            }
        }
    }

    /**
     * Handle a workspace browse.
     */
    private void handleWorkspaceBrowse()
    {
        ResourceSelectionDialog dialog = new ResourceSelectionDialog(getShell(), ResourcesPlugin.getWorkspace().getRoot(), Messages.getString("NewRuleFileWizardPage.13")); //$NON-NLS-1$
        if (dialog.open() == ResourceSelectionDialog.OK)
        {
            Object[] result = dialog.getResult();
            if (result.length == 1)
            {
                modelText.setText(((IFile) result[0]).getFullPath().toString());
            }
        }
    }

    /**
     * Handles a file system browse.
     */
    private void handleSystemBrowse()
    {
        FileDialog dialog = new FileDialog(getShell());
        String result = dialog.open();
        if (result != null && !result.equals("")) //$NON-NLS-1$
        {
            modelText.setText(new File(result).getPath());
        }
    }

    /**
     * Fills in the list according to values register in EMF registry.
     */
    private void fillComboBox()
    {
        for (String uri : EPackage.Registry.INSTANCE.keySet())
        {
            modelUriComboViewer.add(uri);
        }
        modelUriComboViewer.getCombo().select(0);
    }

    /**
     * Handles error/warning messages when items from the dialog change
     */
    private void dialogChanged()
    {
        if (checkLocation() && checkFileName() && checkMetamodel())
        {
            updateStatus(null);
        }
    }

    /**
     * Checks collected information about the location.
     * 
     * @return <code>true</code> if the location is valid, <code>false</code> otherwise.
     */
    private boolean checkLocation()
    {
        IResource container = ResourcesPlugin.getWorkspace().getRoot().findMember(getLocation());
        if (getLocation().segmentCount() == 0)
        {
            updateStatus(Messages.getString("NewRuleFileWizardPage.16")); //$NON-NLS-1$
            return false;
        }
        if (!container.exists())
        {
            updateStatus(Messages.getString("NewRuleFileWizardPage.17")); //$NON-NLS-1$
            return false;
        }
        if (container.getType() == IResource.FILE)
        {
            updateStatus(Messages.getString("NewRuleFileWizardPage.18")); //$NON-NLS-1$
            return false;
        }
        if (!container.isAccessible())
        {
            updateStatus(Messages.getString("NewRuleFileWizardPage.19")); //$NON-NLS-1$
            return false;
        }
        return true;
    }

    /**
     * Checks collected information about the filename.
     * 
     * @return <code>true</code> if the filename is valid, <code>false</code> otherwise.
     */
    private boolean checkFileName()
    {
        IPath fileName = getFileName();
        if (fileName.removeFileExtension().isEmpty())
        {
            updateStatus(Messages.getString("NewRuleFileWizardPage.20")); //$NON-NLS-1$
            return false;
        }
        if (!DEFAULT_EXTENSION.equalsIgnoreCase(fileName.getFileExtension()))
        {
            updateStatus(Messages.getString("NewRuleFileWizardPage.21")); //$NON-NLS-1$
            return false;
        }
        if (resourceExist())
        {
            updateStatus(Messages.getString("NewRuleFileWizardPage.22")); //$NON-NLS-1$
            return false;
        }
        return true;
    }

    /**
     * Checks that the OCL resource the user is creating does not already exist.
     * 
     * @return <code>true</code> if the future resource already exists, <code>false</code> otherwise.
     */
    private boolean resourceExist()
    {
        IWorkspaceRoot wsRoot = ResourcesPlugin.getWorkspace().getRoot();
        IResource oclResource = wsRoot.getFile(getLocation().append(getFileName()));
        return oclResource.exists();
    }

    /**
     * Checks collected information about the metamodel.
     * 
     * @return <code>true</code> if the metamodel is known, <code>false</code> otherwise.
     */
    private boolean checkMetamodel()
    {
        if (getMetamodelUri().length() == 0)
        {
            updateStatus(Messages.getString("NewRuleFileWizardPage.23")); //$NON-NLS-1$
            return false;
        }
        return true;
    }

    /**
     * Updates the status of the page.
     * 
     * @param message a message to display in the message area.
     */
    private void updateStatus(String message)
    {
        setMessage(message, WizardPage.ERROR);
        setPageComplete(message == null);
    }

    /**
     * Gets the location based on the workspace
     * 
     * @return The name of the container
     */
    public IPath getLocation()
    {
        return new Path(containerText.getText());
    }

    /**
     * Gets the filename
     * 
     * @return the filename
     */
    public IPath getFileName()
    {
        return new Path(fileText.getText());
    }

    /**
     * Gets the metamodel from the EMF registry.
     * 
     * @return a string representing the path to the metamodel.
     */
    public String getMetamodelUri()
    {
        if (modelUriRadioButton.getSelection())
        {
            return modelUriCombo.getText();
        }
        else
        {
            return modelText.getText();
        }
    }

    /**
     * Gets the metamodel from a workspace resource.
     * 
     * @return a string representing the path to the metamodel.
     */
    public String getMetaModelFromFile()
    {
        if (modelUriRadioButton.getSelection())
        {
            return null;
        }
        else
        {
            return modelText.getText();
        }
    }
}
