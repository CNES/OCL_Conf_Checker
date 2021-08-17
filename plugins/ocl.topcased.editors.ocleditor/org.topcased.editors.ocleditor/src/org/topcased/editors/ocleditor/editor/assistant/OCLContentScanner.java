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
package org.topcased.editors.ocleditor.editor.assistant;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.NumberRule;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WordRule;
import org.topcased.editors.ocleditor.MetaModelOCL;
import org.topcased.editors.ocleditor.OCLEditorPlugin;

/**
 * Defines the OCL Scanner
 * 
 * @author <a href="mailto:christophe.le-camus@c-s.fr">Chritophe LE CAMUS</a>
 * @author <a href="mailto:sebastien.gabel@c-s.fr">Sebastien GABEL</a>
 */
public class OCLContentScanner
{

    public static final Integer WORD = new Integer(0);

    public static final Integer SEPARATOR = new Integer(1);

    public static final Integer COMMENT = new Integer(2);

    public static final Integer COLON = new Integer(3);

    public static final Integer KEYWORD = new Integer(4);

    public static final Integer LEFT_BRACKET = new Integer(5);

    public static final Integer RIGHT_BRACKET = new Integer(6);

    public static final Integer BASIC_OPERATION = new Integer(7);

    public static final Integer DOT = new Integer(8);

    public static final Integer ARROW = new Integer(9);

    public static final Integer BAR = new Integer(10);

    public static final Integer COMA = new Integer(11);

    public static final Integer DOUBLE_COLON = new Integer(12);

    public static final Integer COLLECTION = new Integer(13);

    public static final Integer BRACES = new Integer(14); // '{', '}'

    public static final Integer NUMBER = new Integer(15);

    public static final Integer STRING = new Integer(16);

    public static final Integer REGIONWORD = new Integer(17);

    public static final Integer OPERATIONS = new Integer(18);

    public static final Integer ERROR_ANNOTATION = new Integer(19);

    public static final Integer WARNING_ANNOTATION = new Integer(20);

    public static final Integer INFO_ANNOTATION = new Integer(21);
    
    public static final Integer MESSAGE_ANNOTATION = new Integer(22);

    public static final Integer TAG = new Integer(23);

    private RuleBasedScanner scanner;

    private IDocument document;

    /**
     * Constructor
     */
    public OCLContentScanner()
    {
        scanner = new RuleBasedScanner();

        WordRule keyWord = new WordRule(new IWordDetector()
        {

            public boolean isWordStart(char c)
            {
                return Character.isLetterOrDigit(c) || c == '_';
            }

            public boolean isWordPart(char c)
            {
                return Character.isLetterOrDigit(c) || c == '_';
            }
        }, new Token(WORD));

        for (String keyword : MetaModelOCL.KEY_WORDS)
        {
            keyWord.addWord(keyword, new Token(KEYWORD));
        }
        keyWord.addWord("Set", new Token(COLLECTION));
        keyWord.addWord("Bag", new Token(COLLECTION));
        keyWord.addWord("OrderedSet", new Token(COLLECTION));
        keyWord.addWord("Sequence", new Token(COLLECTION));

        for (String operationName : MetaModelOCL.getOCLOperations().keySet())
        {
            keyWord.addWord(operationName, new Token(WORD));
            // operationsRule and new Token(OPERATIONS)
        }

        WordRule colon = new WordRule(new IWordDetector()
        {
            public boolean isWordStart(char c)
            {
                return c == ':';
            }

            public boolean isWordPart(char c)
            {
                return false;
            }
        }, new Token(COLON));

        WordRule doubleColon = new WordRule(new IWordDetector()
        {
            public boolean isWordStart(char c)
            {
                return c == ':';
            }

            public boolean isWordPart(char c)
            {
                return c == ':';
            }
        });

        doubleColon.addWord("::", new Token(DOUBLE_COLON));

        // Splits of the separator rule into 2 parts : parenthesis and
        WordRule parenthesis = new WordRule(new IWordDetector()
        {
            public boolean isWordStart(char c)
            {
                return c == '(' || c == ')';
            }

            public boolean isWordPart(char c)
            {
                return false;
            }
        });
        // was commented
        parenthesis.addWord("(", new Token(LEFT_BRACKET));
        parenthesis.addWord(")", new Token(RIGHT_BRACKET));

        WordRule keySeparator = new WordRule(new IWordDetector()
        {

            public boolean isWordStart(char c)
            {
                return c == '.' || c == '-';
            }

            public boolean isWordPart(char c)
            {
                return c == '>';
            }
        });
        // was commented
        keySeparator.addWord(".", new Token(DOT));
        keySeparator.addWord("->", new Token(ARROW));

        WordRule whitespace = new WordRule(new IWordDetector()
        {
            public boolean isWordStart(char c)
            {
                return Character.isWhitespace(c);
            }

            public boolean isWordPart(char c)
            {
                return Character.isWhitespace(c);
            }
        }, Token.WHITESPACE);

        WordRule operations = new WordRule(new IWordDetector()
        {
            public boolean isWordStart(char c)
            {
                return c == '-' || c == '+' || c == '>' || c == '<' || c == '=' || c == '*' || c == '/';
            }

            public boolean isWordPart(char c)
            {
                return c == '>' || c == '=';
            }
        });

        operations.addWord("-", new Token(BASIC_OPERATION));
        operations.addWord("+", new Token(BASIC_OPERATION));
        operations.addWord("*", new Token(BASIC_OPERATION));
        operations.addWord("/", new Token(BASIC_OPERATION));
        operations.addWord("<", new Token(BASIC_OPERATION));
        operations.addWord("<=", new Token(BASIC_OPERATION));
        operations.addWord(">", new Token(BASIC_OPERATION));
        operations.addWord(">=", new Token(BASIC_OPERATION));
        operations.addWord("<>", new Token(BASIC_OPERATION));
        operations.addWord("=", new Token(BASIC_OPERATION));

        WordRule bar = new WordRule(new IWordDetector()
        {
            public boolean isWordStart(char c)
            {
                return c == '|';
            }

            public boolean isWordPart(char c)
            {
                return false;
            }
        }, new Token(BAR));

        WordRule coma = new WordRule(new IWordDetector()
        {
            public boolean isWordStart(char c)
            {
                return c == ',';
            }

            public boolean isWordPart(char c)
            {
                return false;
            }
        }, new Token(COMA));

        List<IRule> rules = new ArrayList<IRule>();
        rules.add(new EndOfLineRule("--@error", new Token(ERROR_ANNOTATION)));
        rules.add(new EndOfLineRule("--@warning", new Token(WARNING_ANNOTATION)));
        rules.add(new EndOfLineRule("--@info", new Token(INFO_ANNOTATION)));
        rules.add(new EndOfLineRule("--@message", new Token(MESSAGE_ANNOTATION)));
        rules.add(new EndOfLineRule("--", new Token(COMMENT)));
        rules.add(new NumberRule(new Token(NUMBER)));
        rules.add(new MultiLineRule("{", "}", new Token(BRACES)));
        rules.add(new MultiLineRule("'", "'", new Token(STRING), '\\'));
        rules.add(keyWord);
        rules.add(keySeparator);
        rules.add(whitespace);
        rules.add(operations);
        rules.add(doubleColon);
        rules.add(colon);
        rules.add(bar);
        rules.add(coma);
        rules.add(parenthesis);
        scanner.setRules(rules.toArray(new IRule[0]));
    }

    /**
     * Sets the document
     * 
     * @param document the JFace Document
     */
    public void setDocument(IDocument document)
    {
        this.document = document;
    }

    /**
     * Sets the range
     * 
     * @param offset
     * @param length
     */
    public void setRange(int offset, int length)
    {
        scanner.setRange(document, offset, length);
    }

    /**
     * Returns the next token in the document that is not a whitespace.<br>
     * Returns a token where isEOF is true if the end of the document has been reached.
     * 
     * @return the next token
     */
    public IToken nextToken()
    {
        IToken token = scanner.nextToken();
        while (token.isWhitespace())
        {
            token = scanner.nextToken();
        }
        return token;
    }

    /**
     * Gets the token value.
     * 
     * @return the value contained in the token
     */
    public String getTokenValue()
    {
        try
        {
            return document.get(scanner.getTokenOffset(), scanner.getTokenLength());
        }
        catch (BadLocationException e)
        {
            return null;
        }
    }

    /**
     * This function uses the scanner to look for the element in the given range of the document The scanner is
     * positionned right after the element.
     * 
     * @param element the string to be looked for
     * @param offset the search starting offset
     * @param length the search length
     * @return the token corresponding to the element or null if not found.
     */
    public IToken lookFor(String element, int offset, int length)
    {
        setRange(offset, length);
        IToken token = nextToken();
        while (!token.isEOF())
        {
            try
            {
                if (document.get(scanner.getTokenOffset(), scanner.getTokenLength()).equals(element))
                {
                    return token;
                }
            }
            catch (BadLocationException e)
            {
                OCLEditorPlugin.log("OCLContentScanner : " + e, IStatus.ERROR);
            }
            token = nextToken();

        }
        return null;
    }

    public int getTokenOffset()
    {
        return scanner.getTokenOffset();
    }

    /**
     * Returns if the end of the range of the document has been reached.<br>
     * If false is returned, then the next token will return false to the method isEOF.
     * 
     * @return true if the next token returns true with the method isEOF
     */
    public boolean endOfScanner()
    {
        IToken token = nextToken();
        boolean end = token.isEOF();
        if (!end)
        {
            int length = getTokenValue().length();
            for (int i = 0; i < length; i++)
            {
                scanner.unread();
            }
        }
        return end;
    }
}
