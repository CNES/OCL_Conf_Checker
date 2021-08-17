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
package com.cnes.checktool.dialog;

import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.ui.dialogs.WorkspaceResourceDialog;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.cnes.checktool.providers.FileListContentProvider;
import com.cnes.checktool.providers.FileListLabelProvider;

public class CreateRulesetDialog extends Dialog
{

    protected Label nameLabel;

    protected Text nameText;

    protected Label versionLabel;

    protected Text versionText;

    protected final List<IFile> ruleFiles;

    protected Composite dialogComposite;

    protected ListViewer ruleViewer;

    protected String name;

    protected String version;

    protected List<IFile> files;

    public CreateRulesetDialog(Shell parent, String label, List<IFile> oclFiles)
    {
        super(parent);
        parent.setText(label);
        this.ruleFiles = oclFiles;
    }

    @Override
    protected Control createDialogArea(Composite parent)
    {
        dialogComposite = (Composite) super.createDialogArea(parent);
        FormLayout layout = new FormLayout();
        layout.marginHeight = 5;
        layout.marginWidth = 5;
        dialogComposite.setLayout(layout);
        GridData mainCompositeData = new GridData(SWT.FILL, SWT.FILL, true, true);
        mainCompositeData.heightHint = 300;
        mainCompositeData.widthHint = 400;
        dialogComposite.setLayoutData(mainCompositeData);

        // idGroup ; name and version labels
        Group idGroup = new Group(dialogComposite, SWT.NONE);
        idGroup.setText("Ruleset ID");
        FormData idGroupData = new FormData();
        idGroupData.top = new FormAttachment(0, 5);
        idGroupData.left = new FormAttachment(0, 5);
        idGroupData.right = new FormAttachment(100, -5);
        idGroup.setLayoutData(idGroupData);
        idGroup.setLayout(new GridLayout(2, true));
        nameLabel = new Label(idGroup, SWT.NONE);
        nameLabel.setText("Ruleset Name");
        nameLabel.setLayoutData(new GridData());
        nameText = new Text(idGroup, SWT.BORDER);
        nameText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

        versionLabel = new Label(idGroup, SWT.NONE);
        versionLabel.setText("Ruleset Version"); // TODO add controls to check for existing versions?
        versionLabel.setLayoutData(new GridData());
        versionText = new Text(idGroup, SWT.BORDER);
        versionText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

        // buttons
        Composite buttonComposite = new Composite(dialogComposite, SWT.NONE);
        FormData buttonCompositeData = new FormData();
        buttonCompositeData.top = new FormAttachment(idGroup, 5);
        buttonCompositeData.right = new FormAttachment(100, -5);
        buttonComposite.setLayoutData(buttonCompositeData);
        buttonComposite.setLayout(new GridLayout(2, false));

        Button addRuleFileButton = new Button(buttonComposite, SWT.NONE);
        addRuleFileButton.setText("Add Rules");
        addRuleFileButton.addSelectionListener(new SelectionListener()
        {
            public void widgetSelected(SelectionEvent e)
            {
                // TODO filter all but *xsd, ocl, c, h
                IFile[] files = WorkspaceResourceDialog.openFileSelection(getShell(), null, null, true, null, null);
                for (IFile file : files)
                {
                    if (file != null)
                    {
                        // Check existing rules to avoid duplicates
                        boolean found = false;
                        for (IFile ruleFile : ruleFiles)
                        {
                            if (ruleFile.equals(file))
                            {
                                found = true;
                                break;
                            }
                        }
                        if (!found)
                        {
                            ruleFiles.add(file);
                        }
                    }
                }
                ruleViewer.setInput(ruleFiles);
            }

            public void widgetDefaultSelected(SelectionEvent e)
            {
                // Do nothing
            }
        });

        Button removeRuleFileButton = new Button(buttonComposite, SWT.NONE);
        removeRuleFileButton.setText("Remove");
        removeRuleFileButton.addSelectionListener(new SelectionListener()
        {
            public void widgetSelected(SelectionEvent e)
            {
                IStructuredSelection selection = (IStructuredSelection) ruleViewer.getSelection();
                Iterator< ? > it = selection.iterator();
                while (it.hasNext())
                {
                    Object sel = it.next();
                    if (sel instanceof IFile)
                    {
                        ruleFiles.remove(sel);
                        ruleViewer.setInput(ruleFiles);
                    }
                }
            }

            public void widgetDefaultSelected(SelectionEvent e)
            {
                // Do nothing
            }
        });

        // listviewer
        ruleViewer = new ListViewer(dialogComposite);
        ruleViewer.setContentProvider(new FileListContentProvider());
        ruleViewer.setLabelProvider(new FileListLabelProvider());
        ruleViewer.setInput(ruleFiles);
        FormData ruleViewerData = new FormData();
        ruleViewerData.top = new FormAttachment(buttonComposite, 5);
        ruleViewerData.left = new FormAttachment(0, 5);
        ruleViewerData.right = new FormAttachment(100, -5);
        ruleViewerData.bottom = new FormAttachment(100, -5);
        ruleViewer.getControl().setLayoutData(ruleViewerData);

        return dialogComposite;
    }

    @Override
    protected boolean isResizable()
    {
        return true;
    }

    @Override
    protected void okPressed()
    {
        name = nameText.getText();
        version = versionText.getText();
        files = (List<IFile>) ruleViewer.getInput();
        super.okPressed();
    }

    public String getName()
    {
        return name;
    }

    public String getVersion()
    {
        return version;
    }

    public List<IFile> getFiles()
    {
        return files;
    }

}
