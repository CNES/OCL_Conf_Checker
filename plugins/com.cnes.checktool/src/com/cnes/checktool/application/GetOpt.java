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

import java.io.PrintStream;
import java.util.HashMap;
import java.util.regex.Pattern;

public class GetOpt
{

    public static StringBuffer error = new StringBuffer();

    public HashMap<String, String> getArguments(ArgOpt[] listOpts, String[] args) throws Exception
    {

        String argFlat = getFlatArguments(args);

        HashMap<String, String> result = new HashMap<String, String>();
        GetExpressionByRegex getExpressionByRegex = new GetExpressionByRegex();

        String[] strings = argFlat.split("(\\s\\-)");
        for (ArgOpt opt : listOpts)
        {
            Pattern shortM = Pattern.compile(opt.shortName + ".*");
            Pattern slongM = Pattern.compile("\\-" + opt.longName + ".*");
            boolean found = false;
            for (String s : strings)
            {
                if (s.length() > 0 && (shortM.matcher(s).matches() || slongM.matcher(s).matches()))
                {
                    found = true;

                    // patern for expression : -f dir=xxx
                    String regex1 = ".*" + opt.shortName + " .*=(.*)";
                    // patern for expression : --foo=xxx
                    String regex2 = ".*-" + opt.longName + "=(.*) -.*|.*-" + opt.longName + "=(.*)";
                    // patern for expression : -f
                    String regex3 = ".*(" + opt.shortName + ").*";
                    // patern for expression : --foo
                    String regex4 = ".*-(" + opt.longName + ").*";

                    if (opt.getArgValue_type() == ArgOpt.NO_ARGUMENT_VALUE)
                    {
                        getExpressionByRegex.addPattern(opt.shortName, regex3);
                        getExpressionByRegex.addPattern(opt.longName, regex4);
                    }
                    else if (opt.getArgValue_type() == ArgOpt.REQUIRED_ARGUMENT_VALUE)
                    {
                        getExpressionByRegex.addPattern(opt.shortName, regex1);
                        getExpressionByRegex.addPattern(opt.longName, regex2);
                    }

                    String argShort = getExpressionByRegex.getAttributeValue(opt.shortName, s);
                    String argLong = getExpressionByRegex.getAttributeValue(opt.longName, s);

                    if (opt.getArgValue_type() == ArgOpt.REQUIRED_ARGUMENT_VALUE || opt.getArgValue_type() == ArgOpt.REQUIRED_ARGUMENT)
                    {
                        if ((argShort == null || argShort.length() == 0) && (argLong == null || argLong.length() == 0))
                        {
                            error.append("Value of the  argument <" + opt.longName + "> is required.\n");
                        }
                    }

                    if ((argShort != null && argShort.length() > 0) && (argLong != null && argLong.length() > 0))
                    {
                        error.append("The argument <" + opt.longName + "> is not redundant. Use " + opt.shortName + " or " + opt.longName + ".\n");
                    }

                    if (argShort == null || argShort.length() == 0)
                    {
                        result.put(opt.longName, argLong);
                        break;
                    }
                    else
                    {
                        result.put(opt.longName, argShort);
                        break;
                    }

                }
            }
            if (!found && opt.getArg_type() == ArgOpt.REQUIRED_ARGUMENT)
            {
                error.append("The argument <" + opt.longName + ">  is required.\n");
            }

        }

        return result;
    }

    public String getFlatArguments(String[] args)
    {
        String argFlat = "";
        for (int i = 0; i < args.length; i++)
        {
            argFlat = argFlat.concat(" " + args[i]);
        }
        return argFlat;
    }

    public void printHelp(ArgOpt[] listOpts, PrintStream out)
    {
        out.println("Commands available");
        for (ArgOpt opt : listOpts)
        {
            out.println(String.format("\t--%s (-%s)\t\t: %s", opt.getLongName(), opt.getShortName(), opt.getDescription()));
        }
    }

    public void printHelp(ArgOpt[] listOpts)
    {
        printHelp(listOpts, System.out);
    }

}
