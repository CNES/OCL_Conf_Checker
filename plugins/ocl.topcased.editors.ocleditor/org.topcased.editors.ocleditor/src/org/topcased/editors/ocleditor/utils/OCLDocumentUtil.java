/***********************************************************************************************************************
 * Copyright (c) 2008,2009 Communication & Systems.
 * 
 * All rights reserved. This program and the accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sebastien GABEL (CS) - initial API and implementation
 *               Pierre GAUFILLET (Airbus) - now returns all messages and not only the first one
 * 
 **********************************************************************************************************************/
package org.topcased.editors.ocleditor.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.ocl.OCLInput;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.ecore.Constraint;
import org.eclipse.ocl.expressions.OCLExpression;
import org.topcased.editors.ocleditor.OCLEditorPlugin;

/**
 * Utility class working around OCL Document. Here, the document contains OCL rules.
 * 
 * @author <a href="mailto:sebastien.gabel@c-s.fr">Sebastien GABEL</a>
 * 
 */
public final class OCLDocumentUtil
{

    /**
     * Main URI of the document
     */
    private static URI documentURI = null;

    private static final String TAG_DEF = "\\s*def(\\s+|:)"; //$NON-NLS-1$

    private static final String TAG_INV = "\\s*inv(\\s+|:)"; //$NON-NLS-1$

    private static final String TAG_CONTEXT = "\\s*context(\\s+)"; //$NON-NLS-1$

    private static final String TAG_PACKAGE = "\\s*package(\\s+)"; //$NON-NLS-1$

    private static final String TAG_ENDPACKAGE = "\\s*endpackage(\\s+)"; //$NON-NLS-1$

    private static final Pattern patternDef = Pattern.compile(TAG_DEF);

    private static final Pattern patternInv = Pattern.compile(TAG_INV);

    private static final Pattern patternContext = Pattern.compile(TAG_CONTEXT);

    private static final Pattern patternPackage = Pattern.compile(TAG_PACKAGE);

    private static final Pattern patternEndPackage = Pattern.compile(TAG_ENDPACKAGE);

    /**
     * Constructor
     */
    private OCLDocumentUtil()
    {
        // prevent from instanciation
    }

    /**
     * Returns the main model URI defined in an OCL document
     * 
     * @param document The OCL JFace document
     * @return an URI read from the document
     */
    public static URI getModelURI(IDocument document)
    {
        try
        {
            String firstLine = document.get(0, document.getLineLength(0));
            if (firstLine.startsWith("MainModel :")) //$NON-NLS-1$
            {
                return URI.createURI(firstLine.substring("MainModel :".length()).trim()); //$NON-NLS-1$
            }
        }
        catch (BadLocationException e)
        {
            OCLEditorPlugin.log("Cannot retrieve URI : " + e.getMessage(), IStatus.ERROR, e); //$NON-NLS-1$
        }
        return null;
    }

    /**
     * Gets the content of the OCL Document.
     * 
     * @param topcasedText : input stream containing the full ocl text including metamodel reference
     * @return an OCLInput without metamodel/model reference
     */
    public static OCLInput getOCLDocument(InputStream topcasedText)
    {
        OCLInput document = null;
        BufferedReader br = new BufferedReader(new InputStreamReader(topcasedText));
        String text = ""; //$NON-NLS-1$
        try
        {
            String buffer = br.readLine();

            while (!buffer.startsWith("package") && !buffer.startsWith("context")) //$NON-NLS-1$ //$NON-NLS-2$
            {
                if (buffer.startsWith("MainModel")) //$NON-NLS-1$
                {
                    documentURI = URI.createURI(buffer.substring("MainModel :".length()).trim()); //$NON-NLS-1$
                }
                buffer = br.readLine();
            }
            while (buffer != null)
            {
                text = text.concat(buffer + "\n"); //$NON-NLS-1$
                buffer = br.readLine();
            }
            document = new OCLInput(text);
        }
        catch (IOException e)
        {
            OCLEditorPlugin.log(e);
        }
        finally
        {
            try
            {
                br.close();
            }
            catch (IOException e)
            {
                OCLEditorPlugin.log(e);
            }
        }
        return document;
    }

    /**
     * 
     * @param topcasedText : input stream containing the full ocl text including metamodel reference
     * @return the number of specific lines for Topcased MainModel, SubModel clauses through the first line package,
     *         context
     */
    public static int getNumberOfLinesToAddToDocument(InputStream topcasedText)
    {
        int numberOfLines = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(topcasedText));

        try
        {
            String buffer = br.readLine();
            while (!buffer.startsWith("package") && !buffer.startsWith("context")) //$NON-NLS-1$ //$NON-NLS-2$
            {
                numberOfLines++;
                buffer = br.readLine();
            }
        }
        catch (IOException e)
        {
            OCLEditorPlugin.log(e);
        }
        finally
        {
            try
            {
                br.close();
            }
            catch (IOException e)
            {
                OCLEditorPlugin.log(e);
            }
        }
        return numberOfLines;
    }

    /**
     * Gets the URI mentioned in the header of the OCL resource
     * 
     * @return the value of the NsURI specified after the keyword MainModel
     */
    public static URI getDocumentURI()
    {
        return documentURI;
    }

    private static int indexOfNextRule(List<Constraint> constraints, int currentIndex)
    {
        int index = constraints.size() - 1;
        int j = currentIndex + 1;
        boolean end = false;
        while (j < constraints.size() && !end)
        {
            if (constraints.get(j).getStereotype().equals("invariant")) //$NON-NLS-1$
            {
                index = j;
                end = true;
            }
            j++;
        }

        return index;
    }

    private static int startOfRule(OCLInput document, List<Constraint> constraints, int currentIndex)
    {

        int position = 0;
        int currentPosition = 0;
        String name = null;

        String stringDocument = new String();
        try
        {
            stringDocument = document.getContentAsString();
        }
        catch (ParserException e)
        {
            OCLEditorPlugin.log(e);
        }

        // go further in the document to find the right rule
        for (int i = 0; i < currentIndex + 1; i++)
        {
            if (constraints.get(i).getStereotype().equals("invariant")) { //$NON-NLS-1$
                position = stringDocument.indexOf("inv ", position) - "inv".length(); //$NON-NLS-1$ //$NON-NLS-2$
                name = constraints.get(i).getName();
                if (name != null)
                {
                    currentPosition = stringDocument.indexOf(constraints.get(i).getName(), position);
                    if (currentPosition > position)
                    {
                        position = currentPosition;
                    }
                }
                else
                {
                    currentPosition = stringDocument.indexOf("inv", position); //$NON-NLS-1$
                    if (currentPosition > position)
                    {
                        position = currentPosition;
                    }
                }
            }
        }
        return position;
    }

    public static String getEndOfExpression(OCLInput document, OCLExpression<EClassifier> oclExp, Constraint aCst, List<Constraint> constraints)
    {
        int currentIndex = constraints.indexOf(aCst);
        int maxPositionExtraction = 0;
        String message = ""; //$NON-NLS-1$
        try
        {
            String stringDocument = document.getContentAsString();
            maxPositionExtraction = stringDocument.length();
            int startOfCurrentRule = 0;
            startOfCurrentRule = startOfRule(document, constraints, currentIndex);
            int startOfNextRule = 0;
            int nextRuleIndex = indexOfNextRule(constraints, currentIndex);
            if (constraints.size() - 1 > currentIndex)
            {
                // Here we get the starting point of the following rule to extract the whole content of the current one.
                startOfNextRule = startOfRule(document, constraints, nextRuleIndex);
            }
            else
            {
                startOfNextRule = maxPositionExtraction;
            }
            String extract = stringDocument.substring(startOfCurrentRule, startOfNextRule);
            if (extract.contains("--@")) //$NON-NLS-1$
            { //$NON-NLS-1$
                message = extract.substring(extract.indexOf(":") + 1, extract.indexOf("\n", extract.lastIndexOf("--@"))); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            }
            else
            {
                int min_pos = extract.length();
                Matcher matcherPackage = patternPackage.matcher(extract);
                Matcher matcherContext = patternContext.matcher(extract);
                Matcher matcherInv = patternInv.matcher(extract);
                Matcher matcherDef = patternDef.matcher(extract);
                Matcher matcherEndPackage = patternEndPackage.matcher(extract);
                while (matcherPackage.find())
                {
                    min_pos = Math.min(min_pos, extract.indexOf(matcherPackage.group()));
                }
                while (matcherContext.find())
                {
                    min_pos = Math.min(min_pos, extract.indexOf(matcherContext.group()));
                }
                while (matcherInv.find())
                {
                    min_pos = Math.min(min_pos, extract.indexOf(matcherInv.group()));
                }
                while (matcherDef.find())
                {
                    min_pos = Math.min(min_pos, extract.indexOf(matcherDef.group()));
                }
                while (matcherEndPackage.find())
                {
                    min_pos = Math.min(min_pos, extract.indexOf(matcherEndPackage.group()));
                }

                message = extract.substring(extract.indexOf(":") + 1, min_pos); //$NON-NLS-1$
            }
        }
        catch (ParserException e)
        {
            OCLEditorPlugin.log(e);
        }

        return message;
    }
}
