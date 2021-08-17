/******************************************************************************************
 * Copyright (c) 2008,2009 Communication & Systems.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Christophe Le Camus (CS), Marion Feau (CS), Guillaume Jolly (CS), S�bastien Gabel (CS), Jerome Morassin(CS)
 *    Petre Bazavan (AEIC), Vincent Combet (CS) - initial API and implementation
 *    
 *********************************************************************************************/
package org.topcased.editors.ocleditor.editor;

import java.util.ResourceBundle;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.texteditor.ContentAssistAction;
import org.eclipse.ui.texteditor.DefaultMarkerAnnotationAccess;
import org.eclipse.ui.texteditor.ITextEditorActionDefinitionIds;
import org.eclipse.ui.texteditor.SourceViewerDecorationSupport;
import org.topcased.editors.ocleditor.OCLEditorPlugin;
import org.topcased.editors.ocleditor.utils.OCLEditorUtil;

public class OCLEditor extends TextEditor
{

    private static final String CONTEXT_MENU_ID = "org.topcased.editors.ocleditor.contextmenu.id";

    /**
     * Constructor
     */
    public OCLEditor()
    {
        super();
        setSourceViewerConfiguration(new OCLEditorSourceViewerConfiguration());
        setEditorContextMenuId(CONTEXT_MENU_ID);
        configureSourceViewerDecorationSupport(new SourceViewerDecorationSupport(getSourceViewer(), getOverviewRuler(), new DefaultMarkerAnnotationAccess(), getSharedColors()));
    }

    /**
     * @see org.eclipse.ui.editors.text.TextEditor#createActions()
     */
    protected void createActions()
    {
        super.createActions();
        ContentAssistAction contentAssist = new ContentAssistAction(ResourceBundle.getBundle("org.topcased.editors.ocleditor.OCLEditorMessages"), "ContentAssistProposal", this);
        contentAssist.setActionDefinitionId(ITextEditorActionDefinitionIds.CONTENT_ASSIST_PROPOSALS);
        setAction("ContentAssistProposal", contentAssist);
        getSourceViewer().getTextWidget().addVerifyListener(new VerifyListener()
        {
            public void verifyText(VerifyEvent e)
            {
                try
                {
                    if (getSourceViewer().getDocument().getLineOfOffset(e.start) == 0)
                    {
                        e.doit = false;
                    }
                    if (getSourceViewer().getDocument().getLineOfOffset(e.start) == 1)
                    {
                        e.doit = false;
                    }

                }
                catch (BadLocationException ex)
                {
                    OCLEditorPlugin.log(ex);
                }
            }
        });
    }

    /**
     * @see org.eclipse.ui.texteditor.AbstractTextEditor#init(org.eclipse.ui.IEditorSite, org.eclipse.ui.IEditorInput)
     */
    public void init(IEditorSite site, IEditorInput input) throws PartInitException
    {
        super.init(site, input);
        setEditorContextMenuId(CONTEXT_MENU_ID);
        OCLEditorDocumentPartitioner partitionner = new OCLEditorDocumentPartitioner();
        IDocument document = getDocumentProvider().getDocument(input);
        document.setDocumentPartitioner(partitionner);
        partitionner.connect(document);
    }

    /**
     * Gets the context classifier
     * 
     * @param partition
     * @return
     */
    public EClassifier getContextClassifierForPartition(ITypedRegion partition)
    {
        OCLEditorSourceViewerConfiguration svc = (OCLEditorSourceViewerConfiguration) getSourceViewerConfiguration();
        if (svc.getContentProcessor() != null)
        {
            return OCLEditorUtil.computeContextClassifierForPartition(getSourceViewer().getDocument(), partition);
        }
        return null;
    }
}
