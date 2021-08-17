/******************************************************************************************
 * Copyright (c) 2005 AIRBUS FRANCE.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Christophe Le Camus (CS), Marion Feau (CS), Guillaume Jolly (CS), S�bastien Gabel (CS)
 *    Petre Bazavan (AEIC), Vincent Combet (CS) - initial API and implementation
 *********************************************************************************************/
package org.topcased.editors.ocleditor.editor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WordRule;
import org.eclipse.jface.text.source.Annotation;
import org.eclipse.jface.text.source.IAnnotationHover;
import org.eclipse.jface.text.source.IAnnotationModel;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.topcased.editors.ocleditor.MetaModelOCL;
import org.topcased.editors.ocleditor.OCLEditorPlugin;
import org.topcased.editors.ocleditor.editor.assistant.OCLEditorContentProcessor;

public class OCLEditorSourceViewerConfiguration extends SourceViewerConfiguration
{

    private OCLEditorContentProcessor contentProcessor;

    private RuleBasedScanner scanner = null;

    public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer)
    {
        PresentationReconciler reconciler = new PresentationReconciler();

        List<IRule> rules = new ArrayList<IRule>();
        // words in general
        WordRule wr = new WordRule(new privateWordDetector(), new Token(new TextAttribute(new Color(null, 0, 0, 0))));
        rules.add(wr);
        // special comments with annotations
        rules.add(new EndOfLineRule("--@error", new Token(new TextAttribute(new Color(null, 255, 0, 0)))));
        rules.add(new EndOfLineRule("--@warning", new Token(new TextAttribute(new Color(null, 255, 165,0)))));
        rules.add(new EndOfLineRule("--@info", new Token(new TextAttribute(new Color(null, 0, 0, 255)))));
        rules.add(new EndOfLineRule("--@message", new Token(new TextAttribute(new Color(null, 0, 0, 255)))));

        // comments
        rules.add(new EndOfLineRule("--", new Token(new TextAttribute(new Color(null, 50, 150, 50)))));
        // strings
        rules.add(new MultiLineRule("'", "'", new Token(new TextAttribute(new Color(null, 50, 100, 250))), '\\', true));
        // parentesis for operations
        WordRule parenthesis = new WordRule(new privateParenthesisDetector(), new Token(new TextAttribute(new Color(null, 0, 250, 0))));
        rules.add(parenthesis);
        // separator detector to be colorized in Red
        WordRule separator = new WordRule(new privateSeparatorDetector(), new Token(new TextAttribute(new Color(null, 250, 0, 0))));
        rules.add(separator);

        Token wordToken = new Token(new TextAttribute(new Color(null, 150, 50, 100), null, SWT.BOLD));
        for (String keywords : MetaModelOCL.KEY_WORDS)
        {
            wr.addWord(keywords, wordToken);
        }

        // Adds the OCL operations to be colorized in green
        Token operToken = new Token(new TextAttribute(new Color(null, 0, 190, 0), null, SWT.ITALIC + SWT.BOLD));
        for (String operationName : MetaModelOCL.getOCLOperations().keySet())
        {
            wr.addWord(operationName, operToken);
        }

        // Adds MainModel and SubModel keywords to be colorized in dark blue
        Token modelToken = new Token(new TextAttribute(new Color(null, 0, 0, 190), null, SWT.BOLD));
        wr.addWord("MainModel", modelToken);
        wr.addWord("SubModel", modelToken);

        // Adds parenthesis characters for the operations identification that are not part of the operation name
        Token parenthesisToken = new Token(new TextAttribute(new Color(null, 0, 250, 0), null, SWT.BOLD));
        parenthesis.addWord("(", parenthesisToken);
        parenthesis.addWord(")", parenthesisToken);

        // Adds separator characters for navigation recognition
        Token separatorToken = new Token(new TextAttribute(new Color(null, 250, 0, 0), null, SWT.BOLD));
        separator.addWord(".", separatorToken);
        separator.addWord("->", separatorToken);

        scanner = new RuleBasedScanner();
        scanner.setRules(rules.toArray(new IRule[0]));

        DefaultDamagerRepairer ddr = new DefaultDamagerRepairer(scanner);
        reconciler.setDamager(ddr, IDocument.DEFAULT_CONTENT_TYPE);
        reconciler.setRepairer(ddr, IDocument.DEFAULT_CONTENT_TYPE);
        reconciler.setDamager(ddr, OCLEditorDocumentPartitioner.RULE_CONTENT);
        reconciler.setRepairer(ddr, OCLEditorDocumentPartitioner.RULE_CONTENT);
        reconciler.setDamager(ddr, OCLEditorDocumentPartitioner.PRE_CONTENT);
        reconciler.setRepairer(ddr, OCLEditorDocumentPartitioner.PRE_CONTENT);
        reconciler.setDamager(ddr, OCLEditorDocumentPartitioner.POST_CONTENT);
        reconciler.setRepairer(ddr, OCLEditorDocumentPartitioner.POST_CONTENT);
        reconciler.setDamager(ddr, OCLEditorDocumentPartitioner.BODY_CONTENT);
        reconciler.setRepairer(ddr, OCLEditorDocumentPartitioner.BODY_CONTENT);
        reconciler.setDamager(ddr, OCLEditorDocumentPartitioner.INIT_CONTENT);
        reconciler.setRepairer(ddr, OCLEditorDocumentPartitioner.INIT_CONTENT);
        reconciler.setDamager(ddr, OCLEditorDocumentPartitioner.DERIVE_CONTENT);
        reconciler.setRepairer(ddr, OCLEditorDocumentPartitioner.DERIVE_CONTENT);
        reconciler.setDamager(ddr, OCLEditorDocumentPartitioner.DEFINITION_CONTENT);
        reconciler.setRepairer(ddr, OCLEditorDocumentPartitioner.DEFINITION_CONTENT);
//        reconciler.setDamager(ddr, OCLEditorDocumentPartitioner.ERROR_ANNOTATION);
//        reconciler.setRepairer(ddr, OCLEditorDocumentPartitioner.ERROR_ANNOTATION);
//        reconciler.setDamager(ddr, OCLEditorDocumentPartitioner.WARNING_ANNOTATION);
//        reconciler.setRepairer(ddr, OCLEditorDocumentPartitioner.WARNING_ANNOTATION);
//        reconciler.setDamager(ddr, OCLEditorDocumentPartitioner.INFO_ANNOTATION);
//        reconciler.setRepairer(ddr, OCLEditorDocumentPartitioner.INFO_ANNOTATION);
        return reconciler;
    }

    public IContentAssistant getContentAssistant(ISourceViewer sourceViewer)
    {
        if (contentProcessor == null)
        {
            contentProcessor = new OCLEditorContentProcessor();
        }
        ContentAssistant assistant = new ContentAssistant();
        assistant.enableAutoActivation(true);
        assistant.setContentAssistProcessor(contentProcessor, IDocument.DEFAULT_CONTENT_TYPE);
        assistant.setContentAssistProcessor(contentProcessor, OCLEditorDocumentPartitioner.DEFINITION_CONTENT);
        assistant.setContentAssistProcessor(contentProcessor, OCLEditorDocumentPartitioner.PRE_CONTENT);
        assistant.setContentAssistProcessor(contentProcessor, OCLEditorDocumentPartitioner.POST_CONTENT);
        assistant.setContentAssistProcessor(contentProcessor, OCLEditorDocumentPartitioner.BODY_CONTENT);
        assistant.setContentAssistProcessor(contentProcessor, OCLEditorDocumentPartitioner.INIT_CONTENT);
        assistant.setContentAssistProcessor(contentProcessor, OCLEditorDocumentPartitioner.DERIVE_CONTENT);
        assistant.setContentAssistProcessor(contentProcessor, OCLEditorDocumentPartitioner.RULE_CONTENT);
        assistant.setAutoActivationDelay(500);
        assistant.setInformationControlCreator(getInformationControlCreator(sourceViewer));
        assistant.install(sourceViewer);
        return assistant;
    }

    public void setContentProcessorOclTypesPackage(EPackage ePackage)
    {
        if (contentProcessor == null)
        {
            contentProcessor = new OCLEditorContentProcessor();
        }
    }

    public OCLEditorContentProcessor getContentProcessor()
    {
        return contentProcessor;
    }

    private class privateWordDetector implements IWordDetector
    {

        public boolean isWordStart(char c)
        {
            return ((Character.isLetterOrDigit(c) || c == '_'));
        }

        public boolean isWordPart(char c)
        {
            return Character.isLetterOrDigit(c) || c == '_';
        }
    }

    /**
     * Detection of right and left parenthsesis
     * 
     * @author clecamus
     * 
     */
    private class privateParenthesisDetector implements IWordDetector
    {

        public boolean isWordStart(char c)
        {
            return (c == '(' || c == ')');
        }

        public boolean isWordPart(char c)
        {
            return false;
        }
    }

    /**
     * Detection of Separator . or ->
     * 
     * @author clecamus
     * 
     */
    private class privateSeparatorDetector implements IWordDetector
    {

        public boolean isWordStart(char c)
        {
            return (c == '.' || c == '-');
        }

        public boolean isWordPart(char c)
        {
            return (c == '>');
        }
    }

    /**
     * @see org.eclipse.jface.text.source.SourceViewerConfiguration#getAnnotationHover(org.eclipse.jface.text.source.
     *      ISourceViewer)
     */
    public IAnnotationHover getAnnotationHover(ISourceViewer sourceViewer)
    {
        return new IAnnotationHover()
        {
            public String getHoverInfo(ISourceViewer sourceViewer, int lineNumber)
            {
                IAnnotationModel model = sourceViewer.getAnnotationModel();
                if (model != null)
                {
                    for (Iterator< ? > ite = model.getAnnotationIterator(); ite.hasNext();)
                    {
                        Annotation annotation = (Annotation) ite.next();
                        int offset = model.getPosition(annotation).getOffset();
                        try
                        {
                            if (sourceViewer.getDocument().getLineOfOffset(offset) == lineNumber)
                            {
                                return annotation.getText();
                            }
                        }
                        catch (Exception e)
                        {
                            OCLEditorPlugin.log(e.getMessage(), IStatus.ERROR, e);
                        }
                    }
                }
                return null;
            }
        };
    }
}
