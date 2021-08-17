/******************************************************************************************
 * Copyright (c) 2005 AIRBUS FRANCE.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Christophe Le Camus (CS), Marion Feau (CS), Guillaume Jolly (CS), Sébastien Gabel (CS)
 *    Petre Bazavan (AEIC), Vincent Combet (CS) - initial API and implementation
 *********************************************************************************************/
package org.topcased.editors.ocleditor.editor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.TypedRegion;
import org.eclipse.jface.text.rules.IToken;
import org.topcased.editors.ocleditor.MetaModelOCL;
import org.topcased.editors.ocleditor.editor.assistant.OCLContentScanner;

/**
 * Document partitioner for OCL files.
 * 
 * @author <a href="mailto:christophe.le-camus@c-s.fr">Christophe LE CAMUS</a>
 * @author <a href="mailto:sebastien.gabel@c-s.fr">Sebastien GABEL</a>
 */
public class OCLEditorDocumentPartitioner implements IDocumentPartitioner
{
    public static final String PACKAGE_CONTENT = "package_content";

    public static final String CONTEXT_CONTENT = "context_content";

    public static final String RULE_CONTENT = "rule_content";

    public static final String PRE_CONTENT = "pre_content";

    public static final String POST_CONTENT = "post_content";

    public static final String BODY_CONTENT = "body_content";

    public static final String INIT_CONTENT = "init_content";

    public static final String DERIVE_CONTENT = "derive_content";

    public static final String DEFINITION_CONTENT = "definition_content";
    
    public static final String ERROR_ANNOTATION = "error_annotation";
    
    public static final String WARNING_ANNOTATION = "warning_annotation";
    
    public static final String INFO_ANNOTATION = "info_annotation";

    private IDocument document;

    private OCLContentScanner scanner;

    private ITypedRegion[] documentsRegions;

    /**
     * @see org.eclipse.jface.text.IDocumentPartitioner#connect(org.eclipse.jface.text.IDocument)
     */
    public void connect(IDocument doc)
    {
        document = doc;
        scanner = new OCLContentScanner();
        scanner.setDocument(document);
        documentsRegions = computePartitioning(0, document.getLength());
    }

    /**
     * @see org.eclipse.jface.text.IDocumentPartitioner#disconnect()
     */
    public void disconnect()
    {
        document = null;
        scanner = null;
    }

    /**
     * @see org.eclipse.jface.text.IDocumentPartitioner#documentAboutToBeChanged(org.eclipse.jface.text.DocumentEvent)
     */
    public void documentAboutToBeChanged(DocumentEvent event)
    {

    }

    /**
     * @see org.eclipse.jface.text.IDocumentPartitioner#documentChanged(org.eclipse.jface.text.DocumentEvent)
     */
    public boolean documentChanged(DocumentEvent event)
    {
        documentsRegions = computePartitioning(0, document.getLength());
        return true;
    }

    /**
     * @see org.eclipse.jface.text.IDocumentPartitioner#getLegalContentTypes()
     */
    public String[] getLegalContentTypes()
    {
        List<String> legalContent = new ArrayList<String>();
        legalContent.add(RULE_CONTENT);
        legalContent.add(PRE_CONTENT);
        legalContent.add(POST_CONTENT);
        legalContent.add(BODY_CONTENT);
        legalContent.add(INIT_CONTENT);
        legalContent.add(DERIVE_CONTENT);
        legalContent.add(DEFINITION_CONTENT);
        legalContent.add(IDocument.DEFAULT_CONTENT_TYPE);
        return legalContent.toArray(new String[0]);
    }

    /**
     * @see org.eclipse.jface.text.IDocumentPartitioner#getContentType(int)
     */
    public String getContentType(int offset)
    {
        ITypedRegion region = getPartition(offset);
        if (region != null)
        {
            return getPartition(offset).getType();
        }
        else
        {
            return IDocument.DEFAULT_CONTENT_TYPE;
        }
    }

    /**
     * Adds authorized regions.
     * 
     * @param regions
     * @param firstTokenValue
     * @param beginIndex
     * @param endIndex
     * @return
     */
    private List<TypedRegion> addLegalRegion(List<TypedRegion> regions, String firstTokenValue, int beginIndex, int endIndex)
    {
        if (firstTokenValue.equals(MetaModelOCL.INV))
        {
            regions.add(new TypedRegion(beginIndex, endIndex, RULE_CONTENT));
        }
        if (firstTokenValue.equals(MetaModelOCL.PRE))
        {
            regions.add(new TypedRegion(beginIndex, endIndex, PRE_CONTENT));
        }
        if (firstTokenValue.equals(MetaModelOCL.POST))
        {
            regions.add(new TypedRegion(beginIndex, endIndex, POST_CONTENT));
        }
        if (firstTokenValue.equals(MetaModelOCL.BODY))
        {
            regions.add(new TypedRegion(beginIndex, endIndex, BODY_CONTENT));
        }
        if (firstTokenValue.equals(MetaModelOCL.INIT))
        {
            regions.add(new TypedRegion(beginIndex, endIndex, INIT_CONTENT));
        }
        if (firstTokenValue.equals(MetaModelOCL.DERIVE))
        {
            regions.add(new TypedRegion(beginIndex, endIndex, DERIVE_CONTENT));
        }
        if (firstTokenValue.equals(MetaModelOCL.DEFINITION))
        {
            regions.add(new TypedRegion(beginIndex, endIndex, DEFINITION_CONTENT));
        }
        return regions;
    }

    /**
     * Splits an OCL Document in regions in giving a typed value for each of them.<br>
     * The two majors type are :
     * <ul>
     * <li>RULE_CONTENT for an ocl rule.</li>
     * <li>IDocument.DEFAULT_CONTENT_TYPE for all the others information.</li>
     * </ul>
     * 
     * @param offset The begin offset of the OCL document, often set to 0.
     * @param length The total length of the OCL document
     */
    public ITypedRegion[] computePartitioning(int offset, int length)
    {
        List<TypedRegion> regions = new ArrayList<TypedRegion>();
        scanner.setRange(offset, length);

        // read the first token
        IToken firstToken = scanner.nextToken();
        String firstTokenValue = scanner.getTokenValue();
        int firstTokenOffset = scanner.getTokenOffset();

        // read the next token (the second which must exist)
        IToken secondToken = scanner.nextToken();
        String secondTokenValue = scanner.getTokenValue();
        int secondTokenOffset = scanner.getTokenOffset();

        while (!scanner.endOfScanner())
        {
            if (secondToken.getData() != null && secondToken.getData().equals(OCLContentScanner.KEYWORD))
            {
                if (MetaModelOCL.isDefiningRule(firstTokenValue) && (MetaModelOCL.endOfDefiningRule(secondTokenValue)))
                {
                    regions = addLegalRegion(regions, firstTokenValue, firstTokenOffset, secondTokenOffset - firstTokenOffset);
                    firstToken = secondToken;
                    firstTokenValue = secondTokenValue;
                    firstTokenOffset = secondTokenOffset;
                }
                else
                {
                    if (!MetaModelOCL.isDefiningRule(firstTokenValue) && (MetaModelOCL.endOfDefiningRule(secondTokenValue)))
                    {
                        regions.add(new TypedRegion(firstTokenOffset, secondTokenOffset - firstTokenOffset, IDocument.DEFAULT_CONTENT_TYPE));
                        firstToken = secondToken;
                        firstTokenValue = secondTokenValue;
                        firstTokenOffset = secondTokenOffset;
                    }
                }
            }
            secondToken = scanner.nextToken();
            secondTokenValue = scanner.getTokenValue();
            secondTokenOffset = scanner.getTokenOffset();
        }

        if (!secondToken.equals(firstToken))
        {
            if (MetaModelOCL.isDefiningRule(firstTokenValue))
            {
                if (secondToken.getData() != null && MetaModelOCL.endOfDefiningRule(secondTokenValue))
                {
                    regions = addLegalRegion(regions, firstTokenValue, firstTokenOffset, secondTokenOffset - firstTokenOffset);
                }
                else
                {
                    regions = addLegalRegion(regions, firstTokenValue, firstTokenOffset, secondTokenOffset - firstTokenOffset + secondTokenValue.length());
                }
            }
            else
            {
                if (!MetaModelOCL.endOfDefiningRule(secondTokenValue))
                {
                    regions.add(new TypedRegion(firstTokenOffset, secondTokenOffset + secondTokenValue.length() - firstTokenOffset, IDocument.DEFAULT_CONTENT_TYPE));
                }
                else
                {
                    regions.add(new TypedRegion(firstTokenOffset, secondTokenOffset - firstTokenOffset, IDocument.DEFAULT_CONTENT_TYPE));
                }
            }
        }
        if (secondToken.getData() != null && MetaModelOCL.endOfDefiningRule(secondTokenValue))
        {
            regions.add(new TypedRegion(secondTokenOffset, secondTokenValue.length(), IDocument.DEFAULT_CONTENT_TYPE));
        }

        return (ITypedRegion[]) regions.toArray(new ITypedRegion[0]);
    }

    /**
     * Enables to get from the array the region stored at the given position.
     * 
     * @param offset The starting position corresponding to the begin of the region.
     * @return Returns a TypedRegion object representing the region.
     */
    public ITypedRegion getPartition(int offset)
    {
        boolean found = false;

        ITypedRegion result = null;
        for (int i = 0; i < documentsRegions.length && !found; i++)
        {
            ITypedRegion currentRegion = documentsRegions[i];
            if (currentRegion.getOffset() <= offset && offset <= currentRegion.getOffset() + currentRegion.getLength())
            {
                result = currentRegion;
                found = true;
            }
        }
        if (!found)
        {
            result = new TypedRegion(offset, 0, IDocument.DEFAULT_CONTENT_TYPE);
        }
        return result;
    }
}