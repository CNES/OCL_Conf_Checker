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
package com.cnes.checktool.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import com.cnes.checktool.Log;
import com.cnes.checktool.results.ResultsFactory;
import com.cnes.checktool.results.ResultsPackage;
import com.cnes.checktool.results.Rule;
import com.cnes.checktool.results.RuleFile;
import com.cnes.checktool.results.RuleSet;


public class BatchUtils
{

    private static final String LINK_PROJECT = "LinkProject";


    public static BatchUtils instance = new BatchUtils();

    public void checkModelBatch(String rulesetWorkspacePath, List<String> ruleSetPaths, String xsdPath, String inhibitor, List<String> xmlModels, String resultsPath)
    {
        Log.infoLog("Preparing temporary working space");
        IProject linkProject = ResourcesPlugin.getWorkspace().getRoot().getProject(LINK_PROJECT);
        initProjects(linkProject);

        Log.infoLog("Importing rulesets");
        List<RuleSet> rulesets = findAndInitRulesets(new File(rulesetWorkspacePath.trim()), ruleSetPaths, xsdPath, inhibitor, linkProject);
        if (rulesets == null || rulesets.isEmpty())
        {
            return;
        }

        Log.infoLog("Importing model files");
        List<IFile> modelFiles = getModelIFiles(xmlModels, linkProject);
        if (modelFiles == null || modelFiles.isEmpty())
        {
            return;
        }

        Log.infoLog("Checking files");
        try
        {
            HandlerUtils.instance.doCheck(modelFiles, rulesets, resultsPath);
        }
        catch (CoreException e)
        {
            Log.errorLog(e.getMessage());
        }
    }
    
	/**
	 * 
	 * Create a ruleSetFile without UI dialog
	 * @param oclFiles list of OCL files as input
	 * @param name ruleset file name
	 * @param version ruleset file version
	 * @param folderName fullPath of targetFolder for ruleSet file
	 * @return the generated full path name
	 */
	public String createRuleSetBatch(List<IFile> oclFiles, String name, String version, String folderName) {

		RuleSet createdRuleSet = ResultsFactory.eINSTANCE.createRuleSet(name, version, oclFiles);

		String fileName = File.separator + name + "_v" + version + ".ruleset";
		String fullFilePath = folderName + fileName;

		try {
			URI fileURI = URI.createFileURI(new File(fullFilePath).getAbsolutePath());
			ResourceSet resSet = new ResourceSetImpl();
			Resource resource = resSet.createResource(fileURI);
			resource.getContents().add(createdRuleSet);
			resource.save(Collections.emptyMap());
			return fileName;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

    protected void initProjects(IProject linkProject)
    {
        try
        {
            linkProject.delete(true, new NullProgressMonitor());

            if (!linkProject.exists())
            {
                linkProject.create(new NullProgressMonitor());
            }
            if (!linkProject.isOpen())
            {
                linkProject.open(new NullProgressMonitor());
            }
        }
        catch (CoreException e)
        {
            e.printStackTrace();
        }
    }

    protected List<IFile> getModelIFiles(List<String> xmlModels, IProject linkProject)
    {
        List<IFile> modelIFiles = new ArrayList<IFile>();

        getXMLModels(xmlModels, linkProject, modelIFiles);

        return modelIFiles;
    }

    protected void getXMLModels(List<String> xmlModels, IProject linkProject, List<IFile> modelIFiles)
    {
        if (xmlModels != null)
        {
            for (String xmlModel : xmlModels)
            {
                File xmlFile = new File(xmlModel.trim());
                IFile iFile = linkProject.getFile(new Path(xmlFile.getName()));
                try
                {
                    iFile.createLink(new Path(xmlFile.getAbsolutePath()), 0, new NullProgressMonitor());
                    modelIFiles.add(iFile);
                }
                catch (CoreException e)
                {
                    Log.errorLog("Could not import XML models because of error " + e.getMessage());

                }
            }
        }
    }



    protected List<RuleSet> findAndInitRulesets(File rootFile, List<String> ruleSets, String xsdPath, String inhibitor, IProject linkProject)
    {
        List<RuleSet> result = new ArrayList<RuleSet>();

        List<File> ruleSetFiles = findRulesetFiles(rootFile, ruleSets, xsdPath, linkProject, result);

        List<Inhibition> inhibitions = getInhibitions(inhibitor);

        ResultsPackage.eINSTANCE.eClass();
        Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
        Map<String, Object> m = reg.getExtensionToFactoryMap();
        m.put("ruleset", new XMIResourceFactoryImpl());
        ResourceSet set = new ResourceSetImpl();
        set.getLoadOptions().put(XMLResource.OPTION_DEFER_ATTACHMENT, true);
        set.getLoadOptions().put(XMLResource.OPTION_DEFER_IDREF_RESOLUTION, true);
        // if findRulesetFiles fails at any point it will return an empty list : this is safe
        for (File ruleSetFile : ruleSetFiles)
        {
            URI fileURI = URI.createFileURI(ruleSetFile.getAbsolutePath().toString());
            Resource resource = set.getResource(fileURI, true);
            for (EObject eobj : resource.getContents())
            {
                if (eobj instanceof RuleSet)
                {
                    RuleSet ruleSet = (RuleSet) eobj;
                    for (RuleFile ruleFile : ruleSet.getRuleFiles())
                    {
                        ruleFile.initRulesfromRuleset();
                        for (Rule rule : ruleFile.getRules())
                        {
                            rule.setIsActive(!isInhibited(ruleSet, ruleFile, rule, inhibitions));
                        }
                    }
                    result.add(ruleSet);
                }
                break;
            }
        }

        return result;
    }

    protected List<Inhibition> getInhibitions(String inhibitorPath)
    {
    	List<Inhibition> inhibitions = new ArrayList<Inhibition>();
    	if(inhibitorPath != null) {
    		File inhibitorFile = new File(inhibitorPath.trim());
    		if (inhibitorFile != null && inhibitorFile.exists())
    		{
    			try
    			{
    				BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(inhibitorFile)));
    				String readLine;
    				while ((readLine = in.readLine()) != null)
    				{
    					readLine = readLine.trim();
    					Inhibition inhibition = Inhibition.parse(readLine);
    					if (inhibition != null)
    					{
    						inhibitions.add(inhibition);
    					}
    				}
    				in.close();
    			}
    			catch (IOException e)
    			{
    				Log.errorLog("Could not read inhibitor file " + inhibitorFile.getName() + " because of error " + e.getMessage());
    			}
    		}
    	}
    	return inhibitions;
    }

    protected boolean isInhibited(RuleSet ruleSet, RuleFile ruleFile, Rule rule, List<Inhibition> inhibitions)
    {
        boolean result = false;
        for (Inhibition inhibition : inhibitions)
        {
            boolean ruleSetMatch = ruleSet.getName().equals(inhibition.getRuleSetName());
            boolean ruleFileMatch = ruleFile.getName().equals(inhibition.getRuleFileName());
            boolean ruleMatch = rule.getName().equals(inhibition.getRuleName());
            if (ruleSetMatch && ruleFileMatch && ruleMatch)
            {
                result = true;
                break;
            }
        }
        return result;
    }

    protected List<File> findRulesetFiles(File rootFile, List<String> ruleSets, String xsdPath, IProject linkProject, List<RuleSet> result)
    {
        List<File> ruleSetFiles = new ArrayList<File>();
        // Then create links, and add all specified rulesets
        if (ruleSets != null && !ruleSets.isEmpty())
        {
            for (String relativePath : ruleSets)
            {
                File ruleSetFile = new File(rootFile.getAbsolutePath() + relativePath.trim());
                if (!ruleSetFile.exists())
                {
                    Log.errorLog("Ruleset file " + relativePath + " could not be found. Please check the given path and try again");
                    return Collections.emptyList();
                }
                else
                {
                    ruleSetFiles.add(ruleSetFile);
                }
            }
        }

        // find all .xsd files (even those which are not the main xsd files, as they might be needed)
        List<File> xsds = new ArrayList<File>();
        recursiveFindFileByExtension(rootFile, xsds, "xsd");
        // Add the main xsdPath as a ruleset
        if (xsdPath != null)
        {
            File mainXSD = new File(rootFile.getAbsolutePath() + xsdPath.trim());
            if (!mainXSD.exists())
            {
                Log.errorLog("XSD file " + xsdPath + " could not be found. Please check the path and try again");
                return Collections.emptyList();
            }
            for (File file : xsds)
            {
                IFile f = linkProject.getFile(new Path(file.getName()));
                try
                {
                    f.createLink(new Path(file.getAbsolutePath()), 0, new NullProgressMonitor());
                    if (file.equals(mainXSD))
                    {
                        RuleSet constraintsRuleset = ResultsFactory.eINSTANCE.createRuleSet(file.getName(), "1.0", Collections.singletonList(f));
                        result.add(constraintsRuleset);
                    }
                }
                catch (CoreException e)
                {
                    Log.errorLog("XSD file " + file.getName() + " was found, but could not be retrieved because of error : " + e.getMessage());
                }
            }
        }
        return ruleSetFiles;
    }

    protected void recursiveFindFileByExtension(File parentFile, List<File> foundFiles, String extension)
    {
        File[] children = parentFile.listFiles();
        if (children != null)
        {
            for (File child : children)
            {
                if (child.isFile())
                {
                    String[] split = child.getName().split("\\.");
                    if (split.length > 1 && split[split.length - 1].equalsIgnoreCase(extension))
                    {
                        foundFiles.add(child);
                    }
                }
                recursiveFindFileByExtension(child, foundFiles, extension);
            }
        }
    }

    public void destroyLinkProjects()
    {
        IProject baselinkProject = ResourcesPlugin.getWorkspace().getRoot().getProject(LINK_PROJECT);
        if (baselinkProject.exists())
        {
            try
            {
                baselinkProject.delete(true, new NullProgressMonitor());
            }
            catch (CoreException e)
            {
                e.printStackTrace();
            }
        }
    }

    protected static class Inhibition
    {
        private String ruleSetName;

        private String ruleFileName;

        private String ruleName;

        public Inhibition(String ruleSetName, String ruleFileName, String ruleName)
        {
            super();
            this.ruleSetName = ruleSetName;
            this.ruleFileName = ruleFileName;
            this.ruleName = ruleName;
        }

        public static Inhibition parse(String line)
        {
            String[] split = line.split(":");
            if (split.length == 3)
            {
                return new Inhibition(split[0], split[1], split[2]);
            }
            else
            {
                return null;
            }
        }

        public String getRuleSetName()
        {
            return ruleSetName;
        }

        public String getRuleFileName()
        {
            return ruleFileName;
        }

        public String getRuleName()
        {
            return ruleName;
        }
    }

}
