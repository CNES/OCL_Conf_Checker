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
package org.topcased.editors.ocleditor;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The main plugin class to be used in the desktop.
 */
public class OCLEditorPlugin extends AbstractUIPlugin
{

    // The shared instance.
    private static OCLEditorPlugin plugin;

    /**
     * The constructor.
     */
    public OCLEditorPlugin()
    {
        plugin = this;
    }

    /**
     * This method is called upon plug-in activation
     */
    public void start(BundleContext context) throws Exception
    {
        super.start(context);
    }

    /**
     * This method is called when the plug-in is stopped
     */
    public void stop(BundleContext context) throws Exception
    {
        super.stop(context);
        plugin = null;
    }

    /**
     * Returns the shared instance.
     */
    public static OCLEditorPlugin getDefault()
    {
        return plugin;
    }

    /**
     * Returns an image descriptor for the image file at the given plug-in relative path.
     * 
     * @param path the path
     * @return the image descriptor
     */
    public static ImageDescriptor getImageDescriptor(String path)
    {
        return AbstractUIPlugin.imageDescriptorFromPlugin(getId(), path);
    }

    /**
     * Gets the Id of the Plugin
     * 
     * @return the Plugin Identifer
     */
    private static String getId()
    {
        return getDefault().getBundle().getSymbolicName();
    }

    /**
     * Logs a message with given level into the Eclipse log file
     * 
     * @param message the message to log
     * @param severity the message priority
     */
    public static void log(Exception e)
    {
        log(e.getMessage(), IStatus.ERROR, e);
    }

    /**
     * Logs a message with given level into the Eclipse log file
     * 
     * @param message the message to log
     * @param severity the message priority
     */
    public static void log(String message, int severity)
    {
        log(message, severity, null);
    }

    /**
     * Logs a message with given level into the Eclipse log file
     * 
     * @param message the message to log
     * @param severity the message priority
     * @param e exception to log
     */
    public static void log(String message, int severity, Exception e)
    {
        IStatus status = new Status(severity, getId(), severity, message, e);
        getDefault().getLog().log(status);
    }
}
