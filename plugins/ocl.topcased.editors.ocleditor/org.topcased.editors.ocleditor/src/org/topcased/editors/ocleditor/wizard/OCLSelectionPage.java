/*******************************************************************************
 * Copyright (c) 2005 AIRBUS FRANCE.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Christophe Le Camus (CS), Guillaume Jolly (CS) - initial API and implementation
 *******************************************************************************/
package org.topcased.editors.ocleditor.wizard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.dialogs.ResourceSelectionDialog;
import org.topcased.editors.ocleditor.OCLEditorPlugin;
import org.topcased.editors.ocleditor.providers.OCLResourceContentProvider;
import org.topcased.editors.ocleditor.providers.OCLResourceLabelProvider;

import com.cnes.checktool.oclrules.oclRules.util.OCLResource;

/**
 * Page allowing to the end-user to make a selection of OCL resources from its workspace.
 * 
 * @author <a href="mailto:sebastien.gabel@c-s.fr">Sebastien GABEL</a>
 */
public class OCLSelectionPage extends WizardPage
{
    private TreeViewer viewer;

    private Map<URI, List<OCLResource>> selectedResources;

    /**
     * Constructor
     */
    public OCLSelectionPage()
    {
        super("OCLSelection"); //$NON-NLS-1$
        setTitle("OCL resources selection");
        setDescription("Select a set of OCL resources to include in your plug-in.");
        selectedResources = new HashMap<URI, List<OCLResource>>();
    }
    
    /**
     * Gets the selected OCL resources.
     * 
     * @return the OCL resources
     */
    public Map<URI, List<OCLResource>> getSelectedResources()
    {
        return selectedResources;
    }

    /**
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    public void createControl(Composite parent)
    {
        final Composite toplevel = new Composite(parent, SWT.NONE);
        GridLayout mainLayout = new GridLayout(2, false);
        mainLayout.verticalSpacing = 10;
        toplevel.setLayout(mainLayout);

        // creation of the table viewer
        createTreeViewer(toplevel);

        // creation of the buttons panel
        createCompositeForButtons(toplevel);

        setControl(toplevel);
    }

    /**
     * Creates the tree viewer.
     * 
     * @param parent The parent composite
     */
    private void createTreeViewer(Composite parent)
    {
        viewer = new TreeViewer(parent, SWT.BORDER | SWT.MULTI);
        viewer.getTree().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        viewer.setLabelProvider(new OCLResourceLabelProvider());
        viewer.setContentProvider(new OCLResourceContentProvider());
        viewer.setInput(selectedResources);
        viewer.addDoubleClickListener(new IDoubleClickListener()
        {
            public void doubleClick(DoubleClickEvent event)
            {
                handleDoubleClick(event);
            }
        });
    }

    /**
     * Creates the composite hosting the two buttons.
     * 
     * @param parent The parent composite
     */
    private void createCompositeForButtons(Composite parent)
    {
        final GridLayout layout = new GridLayout();
        layout.marginHeight = 0;
        layout.marginWidth = 0;

        final Composite container = new Composite(parent, SWT.NONE);
        container.setLayout(layout);
        container.setLayoutData(new GridData(SWT.NONE, SWT.FILL, false, true));

        createButtons(container);
    }

    /**
     * Creates the two buttons : add and remove.
     * 
     * @param parent The parent composite
     */
    private void createButtons(Composite parent)
    {
        final Button addBtn = new Button(parent, SWT.PUSH);
        addBtn.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
        addBtn.setText("Add...");
        addBtn.addSelectionListener(new SelectionAdapter()
        {
            @Override
            public void widgetSelected(SelectionEvent e)
            {
                openResourceSelectionDialog();
            }
        });

        final Button removeBtn = new Button(parent, SWT.PUSH);
        removeBtn.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
        removeBtn.setText("Remove");
        removeBtn.addSelectionListener(new SelectionAdapter()
        {
            @Override
            public void widgetSelected(SelectionEvent e)
            {
                removeSelection();
            }
        });
    }

    /**
     * Opens a ResourceSelectionDialog to browse the current workspace.
     */
    private void openResourceSelectionDialog()
    {
        ResourceSelectionDialog dialog = new ResourceSelectionDialog(getShell(), ResourcesPlugin.getWorkspace().getRoot(), "title");
        dialog.setHelpAvailable(false);
        if (dialog.open() == Window.OK)
        {
            Object[] result = dialog.getResult();
            if (result != null)
            {
                List<OCLResource> selection = new ArrayList<OCLResource>();
                for (Object obj : result)
                {
                    try
                    {
                        if (obj instanceof IFile)
                        {
                            OCLResource resource = new OCLResource((IFile) obj);
                            if ("ocl".equals(resource.getFileExtension()))
                            {
                                selection.add(resource);
                            }
                        }
                    }
                    catch (Exception e)
                    {
                        OCLEditorPlugin.log(e);
                    }
                }
                // updates the map
                updateMap(selection);
            }
        }
    }

    /**
     * Removes the items described by the selection event
     */
    private void removeSelection()
    {
        IStructuredSelection selection = ((IStructuredSelection) viewer.getSelection());
        boolean needRefresh = false;
        for (Object obj : selection.toList())
        {
            if (obj instanceof URI)
            {
                selectedResources.remove(obj);
                needRefresh = true;
            }
            else if (obj instanceof OCLResource)
            {
                URI mmURI = ((OCLResource) obj).getURI();
                List<OCLResource> list = selectedResources.get(mmURI);
                if (list != null)
                {
                    needRefresh = list.remove(obj);
                    if (list.isEmpty())
                    {
                        selectedResources.remove(mmURI);
                    }
                }
            }
        }
        performRefresh(needRefresh);
    }

    /**
     * Updates the Map
     * 
     * @param oclResources A set of selected OCL resources
     */
    private void updateMap(List<OCLResource> oclResources)
    {
        boolean needRefresh = false;
        for (OCLResource oclResource : oclResources)
        {
            URI mmURI = oclResource.getURI();
            List<OCLResource> list = selectedResources.get(mmURI);
            if (list == null)
            {
                // the entry in the map is created
                List<OCLResource> tempList = new ArrayList<OCLResource>();
                needRefresh = tempList.add(oclResource);
                selectedResources.put(mmURI, tempList);
            }
            else
            {
                if (!list.contains(oclResource))
                {
                    needRefresh = list.add(oclResource);
                }
            }
        }
        performRefresh(needRefresh);
    }

    /**
     * Just refreshes the viewer and expand all its nodes.
     * 
     * @param needRefresh boolean indicating if a refresh is needed or not.
     */
    private void performRefresh(boolean needRefresh)
    {
        if (needRefresh)
        {
            viewer.refresh();
            viewer.expandAll();
        }
    }

    /**
     * Handles the double click action
     * 
     * @param event The event to process
     */
    public void handleDoubleClick(DoubleClickEvent event)
    {
        IStructuredSelection selection = (IStructuredSelection) event.getSelection();
        Object selectedObject = selection.getFirstElement();
        viewer.expandToLevel(selectedObject, 1);
    }
}
