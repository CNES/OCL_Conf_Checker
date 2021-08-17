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

public class ArgOpt
{
    public static final int NO_ARGUMENT_VALUE = 0;

    public static final int REQUIRED_ARGUMENT_VALUE = 1;

    public static final int OPTIONAL_ARGUMENT_VALUE = 2;

    public static final int REQUIRED_ARGUMENT = 0;

    public static final int OPTIONAL_ARGUMENT = 1;
    
    public static final int ALONE_ARGUMENT = 2;

    protected String longName;

    protected int argType;

    protected int argValueType;

    protected String shortName;

    protected String description;

    public ArgOpt(String longName, int argType, int argValueType, String shortName, String description)
    {
        this.longName = longName;
        this.argType = argType;
        this.argValueType = argValueType;
        this.shortName = shortName;
        this.description = description;
    }

    public String getLongName()
    {
        return this.longName;
    }

    public int getArg_type()
    {
        return this.argType;
    }

    public int getArgValue_type()
    {
        return this.argValueType;
    }

    public String getShortName()
    {
        return this.shortName;
    }

    public String getDescription()
    {
        return this.description;
    }
    

}
