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
package org.topcased.editors.ocleditor.editor.builders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;

/**
 * Defines the OCL project nature.
 * 
 * @author <a href="mailto:sebastien.gabel@c-s.fr">Sebastien GABEL</a>
 */
public class OCLProjectNature implements IProjectNature
{

    private IProject project;

    /**
     * @see org.eclipse.core.resources.IProjectNature#configure()
     */
    public void configure() throws CoreException
    {
        IProjectDescription description = project.getDescription();
        List<ICommand> oldBuilders = Arrays.asList(description.getBuildSpec());
        List<ICommand> newBuilders = new ArrayList<ICommand>(oldBuilders);

        boolean commandFound = false;
        for (ICommand oldCommand : oldBuilders)
        {
            if (oldCommand.getBuilderName().equals(OCLEditorConstant.EDITOR_BUILDER))
            {
                commandFound = true;
            }
        }

        if (!commandFound)
        {
            ICommand command = description.newCommand();
            command.setBuilderName(OCLEditorConstant.EDITOR_BUILDER);
            command.setBuilding(IncrementalProjectBuilder.AUTO_BUILD, true);
            command.setBuilding(IncrementalProjectBuilder.INCREMENTAL_BUILD, true);
            command.setBuilding(IncrementalProjectBuilder.FULL_BUILD, true);
            command.setBuilding(IncrementalProjectBuilder.CLEAN_BUILD, true);
            newBuilders.add(command);
            description.setBuildSpec((ICommand[]) newBuilders.toArray(new ICommand[oldBuilders.size()]));
            project.setDescription(description, null);
        }
    }

    /**
     * @see org.eclipse.core.resources.IProjectNature#deconfigure()
     */
    public void deconfigure() throws CoreException
    {
        IProjectDescription description = project.getDescription();
        List<ICommand> oldBuilders = Arrays.asList(description.getBuildSpec());

        boolean commandFound = false;
        for (ICommand oldCommand : oldBuilders)
        {
            if (oldCommand.getBuilderName().equals(OCLEditorConstant.EDITOR_BUILDER))
            {
                oldBuilders.remove(oldCommand);
                commandFound = true;
            }
        }

        if (commandFound)
        {
            description.setBuildSpec((ICommand[]) oldBuilders.toArray(new ICommand[oldBuilders.size()]));
            project.setDescription(description, null);
        }
    }

    /**
     * @see org.eclipse.core.resources.IProjectNature#getProject()
     */
    public IProject getProject()
    {
        return project;
    }

    /**
     * @see org.eclipse.core.resources.IProjectNature#setProject(org.eclipse.core.resources.IProject)
     */
    public void setProject(IProject project_)
    {
        project = project_;
    }
}
