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
package com.cnes.checktool;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

public class Log
{
    public static void infoLog (String message){
        log(new Status(IStatus.INFO, Activator.PLUGIN_ID, message));
    }
    
    public static void warningLog (String message){
        log(new Status(IStatus.WARNING, Activator.PLUGIN_ID, message));
    }
    
    public static void errorLog (String message){
        log(new Status(IStatus.ERROR, Activator.PLUGIN_ID, message));
    }
    
    private static String getSeverity(int severity)
    {
        switch (severity)
        {
            case IStatus.INFO:
                return "INFO" ;
            case IStatus.ERROR:
                return "ERROR" ;
            case IStatus.WARNING:
                return "WARNING" ;
            default: return "";
        }
    }
    
    
    public static void log (IStatus status){
        Date time = Calendar.getInstance().getTime();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        String formattedTime = format.format(time);
        System.out.println(String.format("%s %s\t%s", formattedTime,getSeverity(status.getSeverity()),status.getMessage()));
        
    }
}
