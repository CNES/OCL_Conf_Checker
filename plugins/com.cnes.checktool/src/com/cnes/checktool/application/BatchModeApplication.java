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
package com.cnes.checktool.application;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.eclipse.core.internal.resources.Workspace;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

import com.cnes.checktool.Log;
import com.cnes.checktool.utils.BatchUtils;

public class BatchModeApplication implements IApplication
{

    private static final String PROVIDER_WORKSPACE = "provider";

    private static final String RULESETS_PATH = "rulesets";

    private static final String OCLS_PATH = "ocls";

    private static final String INHIBITOR = "inhibitor";

    private static final String XSD_PATH = "xsdpath";

    private static final String XML_MODELS = "xmlmodels";

    private static final String OUTPUT_PATH = "output";

    public Object start(IApplicationContext context) throws Exception
    {
        Display.getDefault();
        PlatformUI.isWorkbenchRunning();
        String[] arguments = (String[]) context.getArguments().get(IApplicationContext.APPLICATION_ARGS);
        ArgOpt[] opts = new ArgOpt[] {
                new ArgOpt(PROVIDER_WORKSPACE, ArgOpt.REQUIRED_ARGUMENT, ArgOpt.REQUIRED_ARGUMENT_VALUE, "pw",
                        "provider-given workspace, containing all rulesets and the XSD constraint. This parameter is mandatory"),
                new ArgOpt(RULESETS_PATH, ArgOpt.OPTIONAL_ARGUMENT, ArgOpt.REQUIRED_ARGUMENT_VALUE, "rsets",
                        "workspace-relative paths, pointing to the ruleset files that need to be checked. This parameter is optional"),
                new ArgOpt(OCLS_PATH, ArgOpt.OPTIONAL_ARGUMENT, ArgOpt.REQUIRED_ARGUMENT_VALUE, "ocls",
                        "workspace-relative paths, pointing to the ocl files that need to be checked. This parameter is optional"),
                new ArgOpt(INHIBITOR, ArgOpt.OPTIONAL_ARGUMENT, ArgOpt.REQUIRED_ARGUMENT_VALUE, "inhib", "absolute path, pointing to the inhibitor file. This parameter is optional"),
                new ArgOpt(XSD_PATH, ArgOpt.OPTIONAL_ARGUMENT, ArgOpt.REQUIRED_ARGUMENT_VALUE, "xsd", "workspace-relative path, pointing to the main XSD constraint. This parameter is optional"),
                new ArgOpt(XML_MODELS, ArgOpt.OPTIONAL_ARGUMENT, ArgOpt.REQUIRED_ARGUMENT_VALUE, "xml", "Direct path to any xml models. This parameter is optional"),
                new ArgOpt(OUTPUT_PATH, ArgOpt.REQUIRED_ARGUMENT, ArgOpt.REQUIRED_ARGUMENT_VALUE, "o", "Path to the final output file. This parameter is mandatory"),

        };
        GetOpt opt = new GetOpt();
        HashMap<String, String> result = opt.getArguments(opts, arguments);

        // Provider
        String providerWorkspace = result.get(PROVIDER_WORKSPACE);
        // rulesets
        String rawRulesets = result.get(RULESETS_PATH);
        List<String> ruleSets = null;
        if (rawRulesets != null)
        {
            ruleSets = Arrays.asList(rawRulesets.split(";"));
        }
        // ocls
        String ocls = result.get(OCLS_PATH);
        List<String> oclList = null;
        if (ocls != null)
        {
            oclList = Arrays.asList(ocls.split(";"));
        }
        List<IFile> oclFiles = new ArrayList<IFile>();

        IWorkspace ws = ResourcesPlugin.getWorkspace();
        IProject project = ws.getRoot().getProject("External Files");
        if (!project.exists())
            project.create(null);
        if (!project.isOpen())
            project.open(null);

        for (String ocl : oclList) {
        	IFile file = ws.getRoot().getFile(Path.fromPortableString(ocl));
        	
            if (!file.exists()) {
            	file = project.getFile(ocl);
            	file.createLink(new Path(providerWorkspace+Path.SEPARATOR+ocl), IResource.NONE, null);
            }
            oclFiles.add(file);
        }
        // inhibitor
        String inhibitor = result.get(INHIBITOR);
        // XSD constraint
        String xsdPath = result.get(XSD_PATH);

        // XML models
        String rawXmlModels = result.get(XML_MODELS);
        List<String> xmlModels = null;
        if (rawXmlModels != null)
        {
            xmlModels = Arrays.asList(rawXmlModels.split(";"));
        }
        // Output path
        String outputPath = result.get(OUTPUT_PATH);

        if (xmlModels == null )
        {
            Log.errorLog("xmlmodels arguments are either empty or cannot be read");
        }
        else if (xsdPath == null && ruleSets == null && ocls == null)
        {
            Log.errorLog("xsdpath, ruleset and ocls arguments are either empty or cannot be read");
        }
        else
        {
            Log.infoLog("Started checking process");
            if (!oclList.isEmpty()) {
                ruleSets = new ArrayList<String>();
                ruleSets.add(BatchUtils.instance.createRuleSetBatch(oclFiles, "temp", "1", providerWorkspace));
            }
            BatchUtils.instance.checkModelBatch(providerWorkspace, ruleSets, xsdPath, inhibitor, xmlModels, outputPath);
        }
        Log.infoLog("Done");
        return null;
    }

    public void stop()
    {
        BatchUtils.instance.destroyLinkProjects();
    }
    

}
