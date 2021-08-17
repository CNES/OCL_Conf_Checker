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
package com.cnes.checktool.oclrules.oclRules.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;

import com.cnes.checktool.Log;

/**
 * An OCL resource can come either from the workspace or from a plug-in (as a local copy). So, the OCL resource has a
 * source, a content and a URI related to a given metamodel.
 * 
 */
public class OCLResource
{
    private IPath relativePath;

    private IPath absolutePath;

    private boolean isFromPlugin = false;

    private boolean isFromWorkspace = false;

    private boolean isExternal = false;

    private URI uri;

    private IFile file;
    
    private static final Pattern patternImport = Pattern.compile("^\\s*import .*:?\\s*'(.*)'");

    private Map<String, String> metadataMap;

    /**
     * Constructor for restoring an OCL resource from a result model.
     * 
     * @param relativePath relative path
     * @param absolutePath absolute path
     * @param origin The OCL rule file origin
     */
    public OCLResource(String relativePath, String absolutePath, int origin)
    {
        this.relativePath = new Path(relativePath);
        this.absolutePath = new Path(absolutePath);
        switch (origin)
        {
            case 0:
                isFromPlugin = true;
                break;
            case 1:
                isFromWorkspace = true;
                break;
            case 2:
                isExternal = true;
                break;
            default:
                break;
        }
        initMetadata();
    }

    /**
     * An OCL resource from the file system.
     * 
     * @param path the resource as an {@link String}
     */
    public OCLResource(String path)
    {
        relativePath = new Path(path);
        absolutePath = relativePath;
        isExternal = true;
        initMetadata();
    }

    /**
     * An OCL resource from the workspace.
     * 
     * @param file the resource as an {@link IFile}
     */
    public OCLResource(IFile file)
    {
        this.file = file;
        relativePath = file.getFullPath();
        absolutePath = file.getLocation();
        isFromWorkspace = true;
        initMetadata();
    }


    /**
     * Reads the file metadata and adds it to the OCL Resource
     */
    public void initMetadata()
    {
        this.metadataMap = new HashMap<String, String>();
        StringBuilder fullMetadataBuilder = new StringBuilder();
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(getContent()));
            String currentLine;
            boolean started = false;
            boolean end = false;
            while (!end && (currentLine = br.readLine()) != null)
            {

                if (!started && currentLine.contains("@metadata"))
                {
                    started = true;
                    // remove any -- that might remain at the beginning of a line
                    currentLine = currentLine.trim();
                    Pattern pLine = Pattern.compile("^-*(.*)");
                    Matcher mLine = pLine.matcher(currentLine);
                    if (mLine.find())
                    {
                        currentLine = mLine.group(1);
                    }
                    fullMetadataBuilder.append(currentLine);
                }
                else if (started)
                {
                    // remove any -- that might remain at the beginning of a line
                    currentLine = currentLine.trim();
                    Pattern pLine = Pattern.compile("^-*(.*)");
                    Matcher mLine = pLine.matcher(currentLine);
                    if (mLine.find())
                    {
                        currentLine = mLine.group(1);
                    }
                    fullMetadataBuilder.append(currentLine);
                }
                if (currentLine.contains("}"))
                {
                    end = true;
                }
            }
            br.close();
        }
        catch (IOException ioException)
        {
        	Log.errorLog("Could not read file metadata for file " + getAbsolutePath().toString() + ": " + ioException);
        }

        String fullMetadata = fullMetadataBuilder.toString();
        String fullClauses = null;

        // retrieve the text located between brackets
        Pattern p = Pattern.compile("\\{(.*?)\\}");
        Matcher m = p.matcher(fullMetadata);
        if (m.find())
        {
            fullClauses = m.group(1);
        }

        if (fullClauses != null)
        {
            // split into clauses; either , or ; are acceptable
            String[] clauses = fullClauses.split(",|;");
            for (String clause : clauses)
            {
                // then split along the = and save to map if everything went OK
                String[] split = clause.split("=");
                if (split.length == 2)
                {
                    String name = split[0].trim();
                    String value = split[1].trim();
                    // allow for empty values
                    if (name != null && value != null && !name.equals(""))
                    {
                        metadataMap.put(name, value);
                    }
                }
            }
        }

    }

    /**
     * @return an unmodifiable copy of the resource's metadata map
     */
    public Map<String, String> getMetadataMap()
    {
        return Collections.unmodifiableMap(metadataMap);
    }

    /**
     * Gets content of the resource.
     * 
     * @return the content of the OCL resource
     */
    public InputStream getContent() throws IOException
    {
        return new FileInputStream(getAbsolutePath().toString());
    }

    /**
     * Returns the file extension portion of this path, or null if there is none.
     * 
     * @return the file extension portion as a String
     */
    public String getFileExtension()
    {
        return relativePath.getFileExtension();
    }

    /**
     * Gets the URI contained in the header of the OCL file.
     * 
     * @return the URI related to a metamodel, null if not found.
     */
    public URI getURI()
    {
        if (uri == null)
        {
            uri = readURI();
        }
        return uri;
    }

    /**
     * Reads the URI contained in the header of the OCL file.
     * 
     * @return the URI related to a metamodel, null if not found.
     * @throws IOExecption if the resource can not be accessed.
     */
    private URI readURI()
    {
        try
        {
            BufferedReader buffer = new BufferedReader(new FileReader(absolutePath.toString()));
            String line = null;
            while ((line = buffer.readLine()) != null)
            {
            	
            	Matcher matcherImport = patternImport.matcher(line);
                if (matcherImport.find()) 
                {
                    String uri = matcherImport.group(1);
                    buffer.close();
                    return URI.createURI(uri);
                }
            }
            buffer.close();
        }
        catch (IOException ioException)
        {
        	Log.errorLog(ioException.toString());
        }
        return null;
    }

    /**
     * Gets the relative path to this resource.
     * 
     * @return The absolute path
     */
    public IPath getRelativePath()
    {
        return relativePath;
    }

    /**
     * Indicates if the resource comes from a plug-in
     * 
     * @return <code>true</code> if the file comes from a plug-in, <code>false</code> otherwise.
     */
    public boolean isFromPlugin()
    {
        return isFromPlugin;
    }

    /**
     * Indicates if the resource comes from a local workspace
     * 
     * @return <code>true</code> if the file comes from the workspace, <code>false</code> otherwise.
     */
    public boolean isFromWorkspace()
    {
        return isFromWorkspace;
    }

    /**
     * Indicates if the resource comes from outside the workspace, in others words from the file system.
     * 
     * @return <code>true</code> if the file comes from the file system, <code>false</code> otherwise.
     */
    public boolean isExternal()
    {
        return isExternal;
    }

    /**
     * Gets the absolute path to this resource.
     * 
     * @return The absolute path
     */
    public IPath getAbsolutePath()
    {
        return absolutePath;
    }

    /**
     * Returns a relative path with the segments and device id of this path. Absolute paths start with a path separator
     * and relative paths do not. If this path is relative, it is simply returned.
     * 
     * @return the relative path
     */
    public IPath makeRelative()
    {
        return relativePath.makeRelative();
    }

    /**
     * Gets the OCL resource as an IFile (resource workspace).
     * 
     * @return the workspace resource as an IFile, or <code>null</code> if bad usage.
     */
    public IFile getFile()
    {
        if (isFromWorkspace)
        {
            return file;
        }
        return null;
    }
}
