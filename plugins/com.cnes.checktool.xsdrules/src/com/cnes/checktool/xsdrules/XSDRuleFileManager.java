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
package com.cnes.checktool.xsdrules;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;

import com.cnes.checktool.results.ResultsFactory;
import com.cnes.checktool.results.RuleFile;
import com.cnes.checktool.results.RuleFileResult;
import com.cnes.checktool.xsdrules.xsdRules.XSDResult;
import com.cnes.checktool.xsdrules.xsdRules.XSDRule;
import com.cnes.checktool.xsdrules.xsdRules.XSDRuleFile;
import com.cnes.checktool.xsdrules.xsdRules.XsdRulesFactory;

public class XSDRuleFileManager extends com.cnes.checktool.utils.AbstractRuleFileManager
{

    @Override
    public RuleFile createRuleFile(IFile iFile)
    {
        try
        {
            final XSDRuleFile rFile = XsdRulesFactory.eINSTANCE.createXSDRuleFile();
            rFile.setName(iFile.getName());
            ((XSDRuleFile) rFile).setFile(iFile);
            // There is only one rule per xsd file
            final XSDRule xsdRule = XsdRulesFactory.eINSTANCE.createXSDRule();
            xsdRule.setName(iFile.getName());
            xsdRule.setIsActive(true);
            rFile.getRules().add(xsdRule);
            return rFile;
        }
        catch (Exception e)
        {
            // TODO
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public RuleFileResult evaluateRuleFile(RuleFile file, List<Resource> processedResources)
    {
        RuleFileResult rfResult = null;
        if (file instanceof XSDRuleFile)
        {
            XSDRuleFile xsdRFile = (XSDRuleFile) file;
            if (xsdRFile.getRules() != null && !xsdRFile.getRules().isEmpty())
            {
                rfResult = ResultsFactory.eINSTANCE.createRuleFileResult();
                rfResult.setRuleFile(xsdRFile);
                for (Resource resource : processedResources)
                {
                    if ("xml".equalsIgnoreCase(resource.getURI().fileExtension()))
                    {
                        XSDResult xsdResult = XsdRulesFactory.eINSTANCE.createXSDResult();
                        // by default is true. Is set to false if something happens
                        xsdResult.setResult(true);
                        // xsd rule files may only ever have one rule
                        xsdResult.setRule(xsdRFile.getRules().get(0));
                        xsdResult.setResourceName(resource.getURI().lastSegment());
                        rfResult.getRuleResults().add(xsdResult);
                        saxValidate(xsdResult, resource, xsdRFile.getFile().getLocation());
                    }
                }
            }
        }
        return rfResult;
    }

    public void setInitializationData(IConfigurationElement config, String propertyName, Object data) throws CoreException
    {
        // Do nothing
    }

    protected void saxValidate(final XSDResult xsdResult, final Resource resource, final IPath xsdPath)
    {
        try
        {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setValidating(false);
            factory.setNamespaceAware(true);

            SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
            SAXParser parser = null;
            try
            {
                factory.setSchema(schemaFactory.newSchema(new Source[] {new StreamSource(xsdPath.toString())}));
                parser = factory.newSAXParser();
            }
            catch (SAXException se)
            {
                xsdResult.setResult(false);
                xsdResult.setErrorMessage(xsdResult.getErrorMessage() + String.format("Parsing of file %s failed: %s \n", xsdPath.lastSegment(), se.getMessage()));
                return;
            }

            XMLReader reader = parser.getXMLReader();
            reader.setErrorHandler(new ErrorHandler()
            {
                public void warning(SAXParseException e) throws SAXException
                {
                    xsdResult.setResult(false);
                    xsdResult.setErrorMessage(xsdResult.getErrorMessage() + String.format("Parsing of resource %s failed: %s \n", resource.getURI(), e.getMessage()));
                    return;
                }

                public void error(SAXParseException e) throws SAXException
                {
                    xsdResult.setResult(false);
                    xsdResult.setErrorMessage(xsdResult.getErrorMessage() + String.format("Parsing of resource %s failed: %s \n", resource.getURI(), e.getMessage()));
                    return;
                }

                public void fatalError(SAXParseException e) throws SAXException
                {
                    xsdResult.setResult(false);
                    xsdResult.setErrorMessage(xsdResult.getErrorMessage() + String.format("Parsing of resource %s failed: %s \n", resource.getURI(), e.getMessage()));
                    return;
                }
            });
            // Save the resource into a buffer byte array
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            Map<String, Boolean> options = new HashMap<String, Boolean>();
            options.put(XMLResource.OPTION_SCHEMA_LOCATION, Boolean.TRUE);
            resource.save(out, options);

            // then feed it back into the parser
            reader.parse(new InputSource(new ByteArrayInputStream(out.toByteArray())));
            return;
        }
        catch (ParserConfigurationException pce)
        {
            return;
        }
        catch (IOException io)
        {
            return;
        }
        catch (SAXException se)
        {
            return;
        }
    }
}
