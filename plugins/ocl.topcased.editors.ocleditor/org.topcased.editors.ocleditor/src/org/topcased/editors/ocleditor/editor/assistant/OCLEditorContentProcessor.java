/***********************************************************************************************************************
 * Copyright (c) 2008,2009 Communication & Systems.
 * 
 * All rights reserved. This program and the accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Christophe Le Camus (CS), Guillaume Jolly (CS), Sebastien GABEL (CS) - initial API and implementation
 * 
 **********************************************************************************************************************/
package org.topcased.editors.ocleditor.editor.assistant;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.contentassist.CompletionProposal;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.ecore.Constraint;
import org.eclipse.ocl.helper.Choice;
import org.eclipse.ocl.helper.ChoiceKind;
import org.eclipse.ocl.helper.ConstraintKind;
import org.eclipse.ocl.helper.OCLHelper;
import org.eclipse.swt.graphics.Image;
import org.topcased.editors.ocleditor.OCLEditorPlugin;
import org.topcased.editors.ocleditor.editor.OCLEditorDocumentPartitioner;
import org.topcased.editors.ocleditor.utils.MetamodelUtils;
import org.topcased.editors.ocleditor.utils.OCLDocumentUtil;
import org.topcased.editors.ocleditor.utils.OCLEditorUtil;

import com.cnes.checktool.oclrules.oclRules.util.ToolkitOCL;

/**
 * Defines the content processor
 * 
 * @author <a href="mailto:christophe.le-camus@c-s.fr">Christophe LE CAMUS</a>
 * @author <a href="mailto:sebastien.gabel@c-s.fr">Sebastien GABEL</a>
 */
public class OCLEditorContentProcessor implements IContentAssistProcessor
{

    private static final Image PACKAGE_IMAGE = OCLEditorPlugin.getImageDescriptor("/icons/editor/package.gif").createImage();

    private static final Image CLASS_IMAGE = OCLEditorPlugin.getImageDescriptor("/icons/editor/class.gif").createImage();

    private static final Image ATTRIB_IMAGE = OCLEditorPlugin.getImageDescriptor("/icons/editor/attribute.gif").createImage();

    private static final Image OPERAT_IMAGE = OCLEditorPlugin.getImageDescriptor("/icons/editor/operation.gif").createImage();

    private static final Image VAR_IMAGE = OCLEditorPlugin.getImageDescriptor("/icons/editor/var.gif").createImage();

    private static final Image LET_IMAGE = OCLEditorPlugin.getImageDescriptor("/icons/editor/let.gif").createImage();

    private static final Image OCL_IMAGE = OCLEditorPlugin.getImageDescriptor("/icons/editor/ocl.gif").createImage();

    private URI rootMetamodelURI;
    
    private EPackage metamodelPackage;

    private OCLContentScanner oclScanner;

    /**
     * Constructor
     */
    public OCLEditorContentProcessor()
    {
        oclScanner = new OCLContentScanner();
    }

    /**
     * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#computeContextInformation(org.eclipse.jface.text.ITextViewer, int)
     */
    public IContextInformation[] computeContextInformation(ITextViewer viewer, int offset)
    {
        return null;
    }

    /**
     * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#getCompletionProposalAutoActivationCharacters()
     */
    public char[] getCompletionProposalAutoActivationCharacters()
    {
        return new char[] {'.', '>'};
    }

    /**
     * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#getContextInformationAutoActivationCharacters()
     */
    public char[] getContextInformationAutoActivationCharacters()
    {
        return null;
    }

    /**
     * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#getErrorMessage()
     */
    public String getErrorMessage()
    {
        return null;
    }
    
    /**
     * Gets rule text
     * 
     * @param oclDocument The OCL document to analyse
     * @param region
     * @return
     */
    private String getRuleText(Document oclDocument, ITypedRegion region)
    {
        String ruleText = "";
        try
        {
            ruleText = oclDocument.get(region.getOffset(), region.getLength());
        }
        catch (BadLocationException e)
        {
            OCLEditorPlugin.log("BadLocationException :" + e.getMessage(), IStatus.ERROR, e);
        }
        return ruleText.trim();
    }
    
    /**
     * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#computeCompletionProposals(org.eclipse.jface.text
     *      .ITextViewer, int)
     */
    public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer, int offset)
    {
        // Defines the document to be analyzed
        Document oclDocument = new Document(viewer.getDocument().get());
        oclScanner.setDocument(oclDocument);

        // Defines the root URI and the main metamodel
        rootMetamodelURI = OCLDocumentUtil.getModelURI(oclDocument);
        metamodelPackage = MetamodelUtils.findMetamodelPackage(rootMetamodelURI);

        // create the Environment and the Helper
        OCLHelper<EClassifier, EOperation, EStructuralFeature, Constraint> oclHelper = ToolkitOCL.getOCL(metamodelPackage).createOCLHelper();

        // analyzed the document to get the different regions
        OCLEditorDocumentPartitioner partitionner = new OCLEditorDocumentPartitioner();
        partitionner.connect(viewer.getDocument());
        
        List<Object> myList = new ArrayList<Object>(0);
        List<Object> allContextsList = new ArrayList<Object>(0);
        List<Object> allOCLTypes = new ArrayList<Object>(0);
        
        ITypedRegion[] regions = partitionner.computePartitioning(0, offset);
        ITypedRegion packageRegion = null;
        if (regions.length > 0)
        {
            packageRegion = regions[0];
        }
        String startString = "";

        for (ITypedRegion typedRegion : regions)
        {
            if (typedRegion.getType().equals(IDocument.DEFAULT_CONTENT_TYPE))
            {
                String ruleText = getRuleText(oclDocument, typedRegion);

                if (typedRegion.getType().equals(OCLEditorDocumentPartitioner.PACKAGE_CONTENT))
                {
                    myList.clear();
                    startString = computeStartString(ruleText);
                    myList = filterList(myList, startString);
                }

                if (!ruleText.matches("\\s*"))
                {
                    if (ruleText.startsWith("context"))
                    {
                        EClassifier contextClassifier = OCLEditorUtil.computeContextClassifier(oclDocument, metamodelPackage, typedRegion, packageRegion);
                        oclHelper.setContext(contextClassifier);
                        myList.clear();
                        
                        // during the context completion we do not recognize any :: so propose root subpackages and
                        // included classifiers.
                        if (ruleText.indexOf("::") == -1 || ruleText.endsWith("::"))
                        {
                            myList.add(metamodelPackage);
                            myList.addAll(metamodelPackage.getESubpackages());
                            myList.addAll(metamodelPackage.getEClassifiers());
                        }
                        else
                        {
                            if (contextClassifier != null)
                            {
                                ETypedElement typedContext = OCLEditorUtil.computeTypedContext(oclDocument, metamodelPackage, typedRegion, packageRegion);
                                if (typedContext == null)
                                {
                                    myList.addAll(((EClass) contextClassifier).getEAllStructuralFeatures());
                                    myList.addAll(((EClass) contextClassifier).getEAllOperations());
                                }
                                else
                                {
                                    if (typedContext instanceof EOperation)
                                    {
                                        EOperation operation = (EOperation) typedContext;
                                        oclHelper.setOperationContext(contextClassifier, operation);
                                    }

                                    if (typedContext instanceof EStructuralFeature)
                                    {
                                        EStructuralFeature feature = (EStructuralFeature) typedContext;
                                        oclHelper.setAttributeContext(contextClassifier, feature);
                                    }
                                }
                            }
                            else
                            {
                                myList.addAll(metamodelPackage.getESubpackages());
                                myList.addAll(metamodelPackage.getEClassifiers());
                            }
                        }
                        startString = computeStartOfContextString(ruleText);
                        allOCLTypes.clear();

                        // CLC :
                        EList<EClassifier> oclDataTypes = null;
                        EObject objectPack = ((EObject) oclHelper.getEnvironment().getOCLStandardLibrary().getOclAny()).eContainer();
                        if (objectPack instanceof EPackage)
                        {
                            oclDataTypes = ((EPackage) objectPack).getEClassifiers();

                            for (Iterator<EClassifier> it = oclDataTypes.iterator(); it.hasNext();)
                            {
                                EClassifier aClassifier = (EClassifier) it.next();
                                if (!aClassifier.getName().contains("_Class") && !aClassifier.getName().startsWith("T"))
                                {
                                    allOCLTypes.add(aClassifier);
                                }
                            }
                        }
                        //List<Object> filteredList = ;
                        //myList.clear();
                        myList = filterList(myList, startString); //.addAll(filteredList);
                    }
                    else if (ruleText.startsWith("package"))
                    {
                        startString = computeStartOfPackageString(ruleText);
                        packageRegion = typedRegion;
                        myList.clear();
                        List<Object> completion = handleCompletionForPackages(oclHelper, ruleText);
                        myList.addAll(filterList(completion, startString));
                    }
                    else if (ruleText.startsWith("endpackage"))
                    {
                        packageRegion = typedRegion;
                        metamodelPackage = MetamodelUtils.findMetamodelPackage(rootMetamodelURI);
                        oclHelper.setInstanceContext(metamodelPackage);
                        myList.clear();
                    }
                }
            }
            else
            {
                if (typedRegion.getType().equals(OCLEditorDocumentPartitioner.PACKAGE_CONTENT))
                {
                    String ruleText = getRuleText(oclDocument, typedRegion);
                    myList.clear();
                    startString = computeStartString(ruleText);
                    myList = filterList(myList, startString);
                }
                else if (typedRegion.getType().equals(OCLEditorDocumentPartitioner.RULE_CONTENT))
                {
                    String ruleText = getRuleText(oclDocument, typedRegion);
                    myList.clear();
                    myList.addAll(oclHelper.getSyntaxHelp(ConstraintKind.INVARIANT, ruleText));
                    startString = computeStartString(ruleText);
                    myList = filterList(myList, startString);
                    if (myList.isEmpty())
                    {
                        myList.addAll(oclHelper.getSyntaxHelp(ConstraintKind.INVARIANT, ruleText.concat("self")));
                    }
                }
                else if (typedRegion.getType().equals(OCLEditorDocumentPartitioner.PRE_CONTENT))
                {
                    myList = handleCompletionProposal(oclDocument, typedRegion, oclHelper, ConstraintKind.PRECONDITION);
                }
                else if (typedRegion.getType().equals(OCLEditorDocumentPartitioner.POST_CONTENT))
                {
                    myList = handleCompletionProposal(oclDocument, typedRegion, oclHelper, ConstraintKind.POSTCONDITION);
                }
                else if (typedRegion.getType().equals(OCLEditorDocumentPartitioner.BODY_CONTENT))
                {
                    myList = handleCompletionProposal(oclDocument, typedRegion, oclHelper, ConstraintKind.BODYCONDITION);
                }
                else if (typedRegion.getType().equals(OCLEditorDocumentPartitioner.INIT_CONTENT))
                {
                    myList = handleCompletionProposal(oclDocument, typedRegion, oclHelper, ConstraintKind.INVARIANT);
                }
                else if (typedRegion.getType().equals(OCLEditorDocumentPartitioner.DERIVE_CONTENT))
                {
                    myList = handleCompletionProposal(oclDocument, typedRegion, oclHelper, ConstraintKind.INVARIANT);
                }
                else if (typedRegion.getType().equals(OCLEditorDocumentPartitioner.DEFINITION_CONTENT))
                {
                    String ruleText = getRuleText(oclDocument, typedRegion);
                    String[] defNameExpression = ruleText.split("def:", 0);
                    int ruleStartPosition = defNameExpression[1].indexOf("=");
                    ETypedElement attrOrOpe = null;
                    int parameterStart = defNameExpression[1].indexOf("(", 0);
                    int parameterEnd = defNameExpression[1].indexOf(")", 0);

                    if ((parameterStart > -1 && parameterEnd > -1) && (parameterStart < ruleStartPosition && parameterEnd < ruleStartPosition))
                    {

                        if (defNameExpression[1].trim().endsWith("="))
                        {

                            String expression = new String(defNameExpression[1]);
                            int equalPosition = expression.indexOf("=");
                            // extract the definition string without expression type or expression literal
                            expression = expression.substring(0, equalPosition + 1);
                            // concat null to simulate a void type of expression else the definition throws an exception
                            expression = expression.concat("null");
                            try
                            {
                                attrOrOpe = oclHelper.defineOperation(expression);
                                oclHelper.setOperationContext(oclHelper.getContextClassifier(), (EOperation) attrOrOpe);
                                myList.clear();
                                myList.addAll(oclHelper.getSyntaxHelp(ConstraintKind.DEFINITION, defNameExpression[1]));
                                startString = computeStartString(ruleText);
                                myList = filterList(myList, startString);

                            }
                            catch (ParserException e)
                            {
                                OCLEditorPlugin.log(e);
                            }
                        }
                        else
                        {
                            if (defNameExpression[1].contains("="))
                            {

                                String expression = new String(defNameExpression[1]);
                                if (expression.endsWith("."))
                                {
                                    expression = expression.substring(0, expression.length() - 1);
                                }
                                if (expression.endsWith("->"))
                                {
                                    expression = expression.substring(0, expression.length() - 2);
                                }

                                try
                                {
                                    attrOrOpe = oclHelper.defineOperation(expression);
                                    oclHelper.setOperationContext(oclHelper.getContextClassifier(), (EOperation) attrOrOpe);
                                    myList.clear();
                                    myList.addAll(oclHelper.getSyntaxHelp(ConstraintKind.DEFINITION, ruleText));
                                    startString = computeStartString(ruleText);
                                    myList = filterList(myList, startString);

                                }
                                catch (ParserException e)
                                {
                                    // secure the definition of partial def
                                    expression = new String(defNameExpression[1]);
                                    int equalPosition = expression.indexOf("=");
                                    // extract the definition string without expression type or expression literal
                                    expression = expression.substring(0, equalPosition + 1);
                                    // concat null to simulate a void type of expression else the definition throws an
                                    // exception
                                    expression = expression.concat("null");
                                    try
                                    {
                                        attrOrOpe = oclHelper.defineOperation(expression);
                                        oclHelper.setOperationContext(oclHelper.getContextClassifier(), (EOperation) attrOrOpe);
                                        myList.clear();
                                        myList.addAll(oclHelper.getSyntaxHelp(ConstraintKind.DEFINITION, defNameExpression[1]));
                                        startString = computeStartString(ruleText);
                                        myList = filterList(myList, startString);
                                    }
                                    catch (ParserException e1)
                                    {
                                        OCLEditorPlugin.log(e1);
                                    }
                                }
                            }
                        }
                    }
                    else
                    {
                        if ((parameterStart > 0 && parameterEnd == -1) || (parameterStart > 0 && parameterEnd > 0 && ruleStartPosition == -1))
                        {
                            // DO NOTING : the Def definition is not complete.
                            // Only to avoid to define an attribute
                            myList.clear();

                            if (defNameExpression[1].trim().endsWith(":"))
                            {
                                myList.addAll(allContextsList);
                                myList.addAll(allOCLTypes);
                                startString = computeStartString(ruleText);
                            }
                            else
                            {
                                myList.addAll(oclHelper.getSyntaxHelp(ConstraintKind.DEFINITION, defNameExpression[1]));
                                myList.addAll(allContextsList);
                                myList.addAll(allOCLTypes);
                                startString = computeStartString(ruleText);
                                myList = filterList(myList, startString);
                            }
                        }
                        else
                        {
                            if (parameterStart == -1 && parameterEnd == -1)
                            {
                                try
                                {
                                    attrOrOpe = oclHelper.defineAttribute(defNameExpression[1].trim());
                                    oclHelper.setAttributeContext(oclHelper.getContextClassifier(), (EAttribute) attrOrOpe);
                                    myList.clear();
                                    myList.addAll(oclHelper.getSyntaxHelp(ConstraintKind.DEFINITION, defNameExpression[1]));
                                    startString = computeStartString(ruleText);
                                    myList = filterList(myList, startString);
                                }
                                catch (ParserException e)
                                {
                                    OCLEditorPlugin.log(e);
                                }
                            }
                        }
                    }
                }
            }
        }

        return fillProposals(oclHelper, myList, offset, startString.length());
    }
    
    private IPath getPackagePath(String text)
    {
        String nsPrefix = text.substring("Package".length()).trim();
        nsPrefix = nsPrefix.replaceAll("::?", "/");
        return new Path(nsPrefix);
    }
    
    private List<Object> handleCompletionForPackages(OCLHelper<?, ?, ?, ?> oclHelper, String text)
    {
        IPath nsPrefixPath = getPackagePath(text);
        EPackage tempPck = null;
        
        if (!nsPrefixPath.isEmpty())
        {
            URI modelURI = rootMetamodelURI;
            if (modelURI.isFile())
            {
                modelURI = modelURI.appendFragment(nsPrefixPath.lastSegment());
                metamodelPackage = MetamodelUtils.findMetamodelPackage(modelURI);
            }
            else
            {
                String[] segArray = nsPrefixPath.segments();
                for (int i = segArray.length - 1 ; i >= 0 ; i--)
                {
                    tempPck = MetamodelUtils.getEPackage(segArray[i]);
                    if (tempPck != null)
                    {  
                        metamodelPackage = tempPck;
                        break;
                    }
                }
            }
            oclHelper.setInstanceContext(metamodelPackage);
        }
       
        List<Object> collection = new ArrayList<Object>();
        if (tempPck == null)
        {
            collection.add(metamodelPackage);
        }
        else
        {
            collection.addAll(metamodelPackage.getESubpackages());
        }
        return collection;
    }

    private List<Object> handleCompletionProposal(Document doc, ITypedRegion region, OCLHelper<?,?,?,?> helper, ConstraintKind constraint)
    {
        List<Object> toReturn = new ArrayList<Object>();
        String ruleText = getRuleText(doc, region);
        toReturn.addAll(helper.getSyntaxHelp(constraint, ruleText));
        String startString = computeStartString(ruleText);
        return filterList(toReturn, startString);  
    }
    
    /**
     * @param ruleText
     * @return
     */
    private String computeStartString(String ruleText)
    {
        String returnString = "";
        int posRuleSeparator = ruleText.lastIndexOf("inv:") + 4; // CLC modification
        int posEqualSeparator = ruleText.lastIndexOf("=") + 1;
        int posPoint = ruleText.lastIndexOf(".") + 1;
        int posArrow = ruleText.lastIndexOf("->") + 2;
        int posColon = ruleText.lastIndexOf(":") + 1;
        int posDoubleColon = ruleText.lastIndexOf("::") + 2;
        int maxSeparator = Math.max(posPoint, posEqualSeparator);
        maxSeparator = Math.max(maxSeparator, posArrow);
        maxSeparator = Math.max(maxSeparator, posRuleSeparator);
        maxSeparator = Math.max(maxSeparator, posColon);
        maxSeparator = Math.max(maxSeparator, posDoubleColon);
        returnString = returnString.concat(ruleText.substring(maxSeparator));
        return returnString.trim();
    }

    /**
     * @param ruleText
     * @return
     */
    private String computeStartOfContextString(String ruleText)
    {
        String returnString = "";
        int posDoubleColon = ruleText.lastIndexOf("::") + 2;
        if (posDoubleColon - 2 <= -1)
        {
            int posContext = ruleText.lastIndexOf("context ") + "context ".length();
            returnString = returnString.concat(ruleText.substring(posContext));
        }
        else
        {
            returnString = returnString.concat(ruleText.substring(posDoubleColon));
        }
        return returnString.trim();
    }
    
    /**
     * @param ruleText
     * @return
     */
    private String computeStartOfPackageString(String ruleText)
    {
        IPath packagePath = getPackagePath(ruleText);
        if (ruleText.endsWith("::") || packagePath.isEmpty())
        {
            return "";
        }
        else
        {
            return packagePath.lastSegment();
        }
    }

    /**
     * @param myList
     * @param startString
     * @return
     */
    private List<Object> filterList(List<Object> myList, String startString)
    {
        if (startString.endsWith(".") || startString.endsWith("->") || startString.endsWith("::"))
        {
            return myList;
        }
        if (startString.length() == 0)
        {
            return myList;
        }
        else
        {
            if ((startString.indexOf("(") != -1) && (!startString.contains("=")))
            {
                startString = startString.substring(0, startString.indexOf("("));
            }
        }

        List<Object> filteredList = new ArrayList<Object>();
        for (Object anObject : myList)
        {
            if (anObject instanceof Choice)
            {
                Choice completionItem = (Choice) anObject;
                String completionName = completionItem.getName();
                if (completionName.startsWith(startString) || startString.trim().endsWith("="))
                {
                    filteredList.add(anObject);
                }
            }
            else if (anObject instanceof ENamedElement)
            {
                ENamedElement completionItem = (ENamedElement) anObject;
                String completionName = completionItem.getName();
                if (completionName.startsWith(startString) || startString.trim().endsWith("="))
                {
                    filteredList.add(anObject);
                }
            }
        }
        return filteredList;
    }

    /**
     * @param oclHelper
     * @param item
     * @return
     */
    private String computeStructuralFeatureDescription(OCLHelper<EClassifier, EOperation, EStructuralFeature, Constraint> oclHelper, Choice item)
    {
        EClass contextClass = (EClass) oclHelper.getContextClassifier();
        EList<EStructuralFeature> allFeatures = contextClass.getEAllStructuralFeatures();
        boolean found = false;
        int i = 0;
        EStructuralFeature myFeature = null;
        while (i < allFeatures.size() && !found)
        {
            if (((EStructuralFeature) allFeatures.get(i)).getName().equals(item.getName()))
            {
                myFeature = (EStructuralFeature) allFeatures.get(i);
                found = !found;
            }
            i++;
        }

        String typeString = "";
        if (myFeature != null)
        {
            typeString = myFeature.getEType().getName();
            if (myFeature.isMany())
            {
                if (myFeature.isOrdered())
                {
                    if (myFeature.isUnique())
                    {
                        typeString = "OrderedSet(".concat(typeString).concat(")");
                    }
                    else
                    {
                        typeString = "Sequence(".concat(typeString).concat(")");
                    }
                }
                else
                {
                    if (myFeature.isUnique())
                    {
                        typeString = "Set(".concat(typeString).concat(")");
                    }
                    else
                    {
                        typeString = "Bag(".concat(typeString).concat(")");
                    }
                }
            }
            else
            {
                typeString = myFeature.getEType().getName();
            }
        }
        else
        {
            typeString = item.getDescription().trim();
        }
        return typeString;
    }

    /**
     * @param item
     * @return
     */
    private String splitOperationDescription(Choice item)
    {
        String name = "";
        int posRihgtPar = item.getDescription().indexOf(")");
        name = name.concat(item.getDescription().substring(0, posRihgtPar + 1));
        return name;
    }

    /**
     * @param oclHelper
     * @param myList
     * @param offset
     * @param startString
     * @return
     */
    CompletionProposal[] fillProposals(OCLHelper<EClassifier, EOperation, EStructuralFeature, Constraint> oclHelper, List<Object> myList, int offset, int replacementLength)
    {
        // useful in all cases.
        int replacementOffset = offset - replacementLength;

        // 1) case where in this scope, there is no completion proposal.
        if (myList.isEmpty())
        {
            String text = "No Completion Proposal";
            CompletionProposal defaultProposal = new CompletionProposal("", replacementOffset, 0, 0, null, text, null, null);
            return new CompletionProposal[] {defaultProposal};
        }

        // Compute the proposals
        List<String> limitedList = new ArrayList<String>();
        List<Image> listImage = new ArrayList<Image>();
        List<String> listDescription = new ArrayList<String>();

        for (int i = 0; i < myList.size(); i++)
        {
            if (myList.get(i) instanceof Choice)
            {
                Choice completionItem = (Choice) myList.get(i);
                ChoiceKind kind = completionItem.getKind();
                switch (kind)
                {
                    case PROPERTY:
                        if (completionItem.getElement() instanceof EAttribute)
                        {
                            EAttribute attr = (EAttribute) completionItem.getElement();

                            if (attr.getEType() instanceof EEnum)
                            {
                                // maintain the old mapping of enumeration-type attributes
                                // ENUMERATION_LITERAL
                                limitedList.add(completionItem.getName());
                                listDescription.add(completionItem.getName().concat(new String(" # ".concat(completionItem.getDescription()))));
                                listImage.add(LET_IMAGE);
                                break;
                            }
                        }
                        // // STRUCTURAL_FEATURE
                    case TYPE:
                    case STATE:
                    case ASSOCIATION_CLASS:
                    case PACKAGE:
                    case ENUMERATION_LITERAL:
                        // STRUCTURAL_FEATURE
                        limitedList.add(completionItem.getName());
                        String typeString = computeStructuralFeatureDescription(oclHelper, completionItem);
                        listDescription.add(completionItem.getName().concat(new String(" : ".concat(typeString))));
                        listImage.add(ATTRIB_IMAGE);
                        break;
                    case SIGNAL:
                    case OPERATION:
                        // BEHAVIORAL_FEATURE
                        limitedList.add(splitOperationDescription(completionItem));
                        listDescription.add(new String(completionItem.getDescription()));
                        listImage.add(OPERAT_IMAGE);
                        break;
                    case VARIABLE:
                        // VARIABLE
                        String description = new String();
                        if (completionItem.getDescription() != null)
                        {
                            description = completionItem.getDescription();
                        }
                        limitedList.add(completionItem.getName());
                        listDescription.add(completionItem.getName().concat(new String(" : ".concat(description))));
                        listImage.add(VAR_IMAGE);
                        break;
                    default:
                        // UNCATEGORIZED
                        limitedList.add(completionItem.getName());
                        listDescription.add(completionItem.getName().concat(new String(" : UNCATEGORIZED")));
                        listImage.add(CLASS_IMAGE);
                        break;
                }
            }
            else
            {
                boolean alreadyAdded = false;
                if (myList.get(i) instanceof EPackage)
                {
                    EPackage aPackage = (EPackage) myList.get(i);
                    limitedList.add(aPackage.getName());
                    listDescription.add(aPackage.getName().concat(new String(" : EPackage")));
                    listImage.add(PACKAGE_IMAGE);
                    alreadyAdded = true;
                }
                if (myList.get(i) instanceof EClass && !(myList.get(i) instanceof ENamedElement))
                {
                    EClass aClass = (EClass) myList.get(i);
                    limitedList.add(aClass.getName());
                    listDescription.add(aClass.getName().concat(new String(" : EClass")));
                    listImage.add(CLASS_IMAGE);
                    alreadyAdded = true;
                }
                if (myList.get(i) instanceof EAttribute)
                {
                    EAttribute anAttribute = (EAttribute) myList.get(i);
                    limitedList.add(anAttribute.getName());
                    listDescription.add(anAttribute.getName().concat(new String(" : ").concat(anAttribute.getEType().getName())));
                    listImage.add(ATTRIB_IMAGE);
                    alreadyAdded = true;
                }
                if (myList.get(i) instanceof EReference)
                {
                    EReference aReference = (EReference) myList.get(i);
                    limitedList.add(aReference.getName());
                    listDescription.add(aReference.getName().concat(new String(" : ").concat(aReference.getEType().getName())));
                    listImage.add(ATTRIB_IMAGE);
                    alreadyAdded = true;
                }
                if (myList.get(i) instanceof EOperation)
                {
                    EOperation anOperation = (EOperation) myList.get(i);
                    limitedList.add(buildOperationNameWithReturnType(anOperation));
                    listDescription.add(buildOperationNameWithReturnType(anOperation));
                    listImage.add(OPERAT_IMAGE);
                    alreadyAdded = true;
                }
                if (myList.get(i) instanceof EParameter)
                {
                    EParameter aParameter = (EParameter) myList.get(i);
                    limitedList.add(aParameter.getName());
                    listDescription.add(aParameter.getName().concat(new String(" : ").concat(aParameter.getEType().getName())));
                    listImage.add(ATTRIB_IMAGE);
                    alreadyAdded = true;
                }
                if ((myList.get(i) instanceof ENamedElement) && (!alreadyAdded))
                {
                    ENamedElement aNamedElement = (ENamedElement) myList.get(i);
                    limitedList.add(aNamedElement.getName());
                    listDescription.add(aNamedElement.getName().concat(new String(" : ").concat(aNamedElement.eClass().getName())));
                    if (aNamedElement.eContainer() instanceof ENamedElement)
                    {
                        if (((ENamedElement) aNamedElement.eContainer()).getName().equals("oclstdlib"))
                        {
                            listImage.add(OCL_IMAGE);
                        }
                        else
                        {
                            listImage.add(CLASS_IMAGE);
                        }
                    }
                }
            }
        }

        CompletionProposal[] proposals = new CompletionProposal[limitedList.size()];
        // TODO : compute the length of the word which has to be replaced
        // case1 : a word between two points case of completion on an existing word
        // case2 : if we ask from a point
        for (int i = 0; i < limitedList.size(); i++)
        {
            String text = limitedList.get(i);
            proposals[i] = new CompletionProposal(text, replacementOffset, replacementLength, text.length(), listImage.get(i), listDescription.get(i), null, "");
        }
        return proposals;
    }



    /**
     * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#getContextInformationValidator()
     */
    public IContextInformationValidator getContextInformationValidator()
    {
        return null;
    }

    /**
     * @param operation
     * @return
     */
    private String buildOperationNameWithReturnType(EOperation operation)
    {
        if (operation.getName() == "")
        {
            return "unNamedOperation()";
        }
        String result = new String(operation.getName().concat("("));
        for (Iterator<EParameter> it = operation.getEParameters().iterator(); it.hasNext();)
        {
            EParameter param = it.next();
            result = result.concat(param.getName()).concat(": ".concat(param.getEType().getName())).concat(", ");
        }
        if (operation.getEParameters().size() != 0)
        {
            result = result.substring(0, result.length() - 2);
        }
        result = result.concat(") : ");
        if (operation.getEType() != null)
        {
            result = result.concat(operation.getEType().getName());
        }
        else
        {
            result = result.concat("OclIsUndefined");
        }
        return result;
    }
    

}
