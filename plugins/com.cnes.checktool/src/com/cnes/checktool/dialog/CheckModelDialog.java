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

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.ui.dialogs.WorkspaceResourceDialog;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.window.Window;
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
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ContainerCheckedTreeViewer;
import org.eclipse.ui.dialogs.ResourceSelectionDialog;

import com.cnes.checktool.providers.FileListContentProvider;
import com.cnes.checktool.providers.FileListLabelProvider;
import com.cnes.checktool.providers.FilterRulesetContentProvider;
import com.cnes.checktool.providers.FilterRulesetLabelProvider;
import com.cnes.checktool.results.Rule;
import com.cnes.checktool.results.RuleFile;
import com.cnes.checktool.results.RuleSet;

public class CheckModelDialog extends Dialog
{
    protected Composite dialogComposite;

    protected Button addButton;

    protected Button removeButton;

    protected ListViewer modelsListViewer;

    protected ContainerCheckedTreeViewer filteredRulesViewer;

    protected List<IFile> modelFiles;

    protected List<RuleSet> ruleSets;

    protected IFile xsdConstraints;

    protected String resultFilePath;

    protected Text xsdPathText;

    protected Text resultsPathText;

    protected Composite xsdGroup;

    protected Composite resultsGroup;

    public CheckModelDialog(Shell parent, String label, List<IFile> files, List<RuleSet> ruleSets)
    {
        super(parent);
        parent.setText(label);
        this.modelFiles = files;
        this.ruleSets = ruleSets;
    }

    @Override
    public Control createDialogArea(Composite parent)
    {
        dialogComposite = (Composite) super.createDialogArea(parent);
        FormLayout layout = new FormLayout();
        layout.marginHeight = 5;
        layout.marginWidth = 5;
        dialogComposite.setLayout(layout);
        GridData mainCompositeData = new GridData(SWT.FILL, SWT.FILL, true, true);
        mainCompositeData.heightHint = 600;
        mainCompositeData.widthHint = 400;
        dialogComposite.setLayoutData(mainCompositeData);

        // label + buttons header
        Composite modelsHeader = new Composite(dialogComposite, SWT.NONE);
        FormData headersCompositeData = new FormData();
        headersCompositeData.top = new FormAttachment(0, 5);
        headersCompositeData.right = new FormAttachment(100, -5);
        headersCompositeData.left = new FormAttachment(0, 5);
        modelsHeader.setLayoutData(headersCompositeData);
        modelsHeader.setLayout(new FormLayout());

        Label modelsLabel = new Label(modelsHeader, SWT.NONE);
        FormData labelData = new FormData();
        labelData.left = new FormAttachment(0, 5);
        labelData.bottom = new FormAttachment(100, -5);
        modelsLabel.setLayoutData(labelData);
        modelsLabel.setText("Models to check");

        Composite buttonComposite = new Composite(modelsHeader, SWT.NONE);
        FormData buttonCompositeData = new FormData();
        buttonCompositeData.bottom = new FormAttachment(100, -5);
        buttonCompositeData.right = new FormAttachment(100, -5);
        buttonComposite.setLayoutData(buttonCompositeData);
        buttonComposite.setLayout(new GridLayout(2, false));

        Button addModelFileButton = new Button(buttonComposite, SWT.NONE);
        addModelFileButton.setText("Add Models");
        addModelFileButton.addSelectionListener(new SelectionListener()
        {
            public void widgetSelected(SelectionEvent e)
            {
                // TODO filter all but *xml, uml, c
                IFile[] files = WorkspaceResourceDialog.openFileSelection(getShell(), null, null, true, null, null);
                for (IFile file : files)
                {
                    if (file != null)
                    {
                        boolean found = false;
                        //avoid duplicates
                        for (IFile modelFile : modelFiles)
                        {
                            if (modelFile.equals(file))
                            {
                                found = true;
                                break;
                            }
                        }
                        if (!found)
                        {
                            modelFiles.add(file);
                        }
                    }
                }
                modelsListViewer.setInput(modelFiles);
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
                IStructuredSelection selection = (IStructuredSelection) modelsListViewer.getSelection();
                Iterator< ? > it = selection.iterator();
                while (it.hasNext())
                {
                    Object sel = it.next();
                    if (sel instanceof IFile)
                    {
                        modelFiles.remove(sel);
                        modelsListViewer.setInput(modelFiles);
                    }
                }
            }

            public void widgetDefaultSelected(SelectionEvent e)
            {
                // Do nothing
            }
        });

        // models listviewer
        modelsListViewer = new ListViewer(dialogComposite);
        modelsListViewer.setContentProvider(new FileListContentProvider());
        modelsListViewer.setLabelProvider(new FileListLabelProvider());
        modelsListViewer.setInput(modelFiles);
        FormData modelsListViewerData = new FormData();
        modelsListViewerData.top = new FormAttachment(modelsHeader, 5);
        modelsListViewerData.left = new FormAttachment(0, 5);
        modelsListViewerData.right = new FormAttachment(100, -5);
        modelsListViewer.getControl().setLayoutData(modelsListViewerData);

        // ruleset label and viewer
        Label rulesLabel = new Label(dialogComposite, SWT.NONE);
        rulesLabel.setText("Rulesets");
        FormData rulesData = new FormData();
        rulesData.top = new FormAttachment(modelsListViewer.getControl(), 20);
        rulesData.left = new FormAttachment(0, 5);
        rulesLabel.setLayoutData(rulesData);

        /**
         * Starting from here, we work our way from the bottom and upwards. This is because the rulesets window si the
         * most important and is intended to fill any space left available
         **/

        // result set composite
        resultsGroup = new Composite(dialogComposite, SWT.NONE);
        FormData resultsData = new FormData();
        resultsData.left = new FormAttachment(0, 5);
        resultsData.right = new FormAttachment(100, -5);
        resultsData.bottom = new FormAttachment(100, -5);
        resultsGroup.setLayoutData(resultsData);
        resultsGroup.setLayout(new FormLayout());

        Label resultsPathLabel = new Label(resultsGroup, SWT.NONE);
        resultsPathLabel.setText("Results file");
        FormData resultsPathLabelData = new FormData();
        resultsPathLabelData.top = new FormAttachment(0, 5);
        resultsPathLabelData.left = new FormAttachment(0, 5);
        resultsPathLabel.setLayoutData(resultsPathLabelData);

        resultsPathText = new Text(resultsGroup, SWT.BORDER);
        FormData resultsTextData = new FormData();
        resultsTextData.top = new FormAttachment(resultsPathLabel, 5);
        resultsTextData.left = new FormAttachment(0, 5);
        resultsTextData.right = new FormAttachment(80, -5);
        resultsTextData.bottom = new FormAttachment(100, -5);
        resultsPathText.setLayoutData(resultsTextData);

        Button resultsBrowseButtonText = new Button(resultsGroup, SWT.NONE);
        resultsBrowseButtonText.setText("Browse");
        FormData resultsButtonData = new FormData();
        resultsButtonData.top = new FormAttachment(resultsPathLabel, 5);
        resultsButtonData.left = new FormAttachment(resultsPathText, 5);
        resultsButtonData.right = new FormAttachment(100, -5);
        resultsButtonData.bottom = new FormAttachment(100, -5);
        resultsBrowseButtonText.setLayoutData(resultsButtonData);

        resultsBrowseButtonText.addSelectionListener(new SelectionListener()
        {
            public void widgetSelected(SelectionEvent e)
            {
                FileDialog fDialog = new FileDialog(Display.getDefault().getActiveShell(), SWT.SAVE);
                fDialog.setText("Select result file");
                fDialog.setFilterExtensions(new String[] {"*.result"});
                fDialog.setFilterNames(new String[] { "Result files(*.result)" });
                String filePath = fDialog.open();
                if (filePath != null)
                {
                    if(!filePath.endsWith(".result")) {
                        filePath = filePath.concat(".result");
                    }
                    resultFilePath = filePath;
                    resultsPathText.setText(filePath);
                }
            }

            public void widgetDefaultSelected(SelectionEvent e)
            {
                // Do nothing
            }
        });

        // XSD constraints composite
        xsdGroup = new Composite(dialogComposite, SWT.NONE);
        FormData xsdData = new FormData();
        xsdData.left = new FormAttachment(0, 5);
        xsdData.right = new FormAttachment(100, -5);
        xsdData.bottom = new FormAttachment(resultsGroup, -5);
        xsdGroup.setLayoutData(xsdData);
        xsdGroup.setLayout(new FormLayout());

        Label xsdPathLabel = new Label(xsdGroup, SWT.NONE);
        xsdPathLabel.setText("XSD constraints file");
        FormData xsdPathLabelData = new FormData();
        xsdPathLabelData.top = new FormAttachment(0, 5);
        xsdPathLabelData.left = new FormAttachment(0, 5);
        xsdPathLabel.setLayoutData(xsdPathLabelData);

        xsdPathText = new Text(xsdGroup, SWT.BORDER);
        FormData xsdTextData = new FormData();
        xsdTextData.top = new FormAttachment(xsdPathLabel, 5);
        xsdTextData.left = new FormAttachment(0, 5);
        xsdTextData.right = new FormAttachment(80, -5);
        xsdTextData.bottom = new FormAttachment(100, -5);
        xsdPathText.setLayoutData(xsdTextData);

        Button xsdBrowseButtonText = new Button(xsdGroup, SWT.NONE);
        xsdBrowseButtonText.setText("Browse");
        FormData xsdButtonData = new FormData();
        xsdButtonData.top = new FormAttachment(xsdPathLabel, 5);
        xsdButtonData.left = new FormAttachment(xsdPathText, 5);
        xsdButtonData.right = new FormAttachment(100, -5);
        xsdButtonData.bottom = new FormAttachment(100, -5);
        xsdBrowseButtonText.setLayoutData(xsdButtonData);

        xsdBrowseButtonText.addSelectionListener(new SelectionListener()
        {
            public void widgetSelected(SelectionEvent e)
            {
                ResourceSelectionDialog dialog = new ResourceSelectionDialog(Display.getCurrent().getActiveShell(), ResourcesPlugin.getWorkspace().getRoot(), "Select an xsd constraints file");
                boolean isOK = dialog.open() == Window.OK;
                Object[] result = dialog.getResult();
                if (result.length == 1)
                {
                    Object file = result[0];
                    if (file instanceof IFile)
                    {
                        xsdConstraints = (IFile) file;
                        xsdPathText.setText(xsdConstraints.getFullPath().toString());
                    }
                }
            }

            public void widgetDefaultSelected(SelectionEvent e)
            {
                // Do nothing
            }
        });

        // The rules viewer will take the most space, so we attach its bottom to the top of the composite below
        filteredRulesViewer = new ContainerCheckedTreeViewer(dialogComposite, SWT.BORDER);
        filteredRulesViewer.setContentProvider(new FilterRulesetContentProvider());
        filteredRulesViewer.setLabelProvider(new FilterRulesetLabelProvider());
        filteredRulesViewer.setInput(getRuleSets());
        FormData filteredRulesData = new FormData();
        filteredRulesData.top = new FormAttachment(rulesLabel, 5);
        filteredRulesData.left = new FormAttachment(0, 5);
        filteredRulesData.right = new FormAttachment(100, -5);
        filteredRulesData.bottom = new FormAttachment(xsdGroup, -5);
        filteredRulesViewer.getControl().setLayoutData(filteredRulesData);

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
        // Set checked rules as active
        List< ? > list = Arrays.asList(filteredRulesViewer.getCheckedElements());
        for (RuleSet set : getRuleSets())
        {
            for (RuleFile ruleFile : set.getRuleFiles())
            {
                for (Rule rule : ruleFile.getRules())
                {
                    rule.setIsActive(list.contains(rule));
                }
            }
        }

        super.okPressed();
    }

    public List<RuleSet> getRuleSets()
    {
        return ruleSets;
    }

    public List<IFile> getModelFiles()
    {
        return modelFiles;
    }

    public IFile getContraintsFile()
    {
        return xsdConstraints;
    }
    
    public String getResultFilePath()
    {
        return resultFilePath;
    }

}
