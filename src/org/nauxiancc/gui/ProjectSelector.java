package org.nauxiancc.gui;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.*;

import org.nauxiancc.projects.Project;
import org.nauxiancc.projects.ProjectData;

/**
 * The selector is used to display the list of available projects and allow the
 * user to open them. This will display all correctly published Runners.
 *
 * @author Naux
 * @since 1.0
 */

public class ProjectSelector extends JPanel {

    private static final long serialVersionUID = 4869219241938861949L;

    private static final ArrayList<ProjectPanel> PROJECTS = new ArrayList<>();

    /**
     * Constructs a new project selector. Only to be used inside of the
     * {@link GUI#openProject(Project)}.
     *
     * @since 1.0
     */

    public ProjectSelector() {
        super(new BorderLayout());
        final String toolTip = " information. Press 'Open' to start it.";
        final JPanel selector = new JPanel(new SelectorLayout(FlowLayout.LEADING, 5, 5));
        for (final Project project : ProjectData.DATA) {
            final ProjectPanel temp = new ProjectPanel(project);
            PROJECTS.add(temp);
            temp.setToolTipText(project.getName().concat(toolTip));
        }
        Collections.sort(PROJECTS, new Comparator<ProjectPanel>() {

            @Override
            public int compare(ProjectPanel o1, ProjectPanel o2) {
                return o1.compareTo(o2);
            }

        });
        for(final ProjectPanel panel : PROJECTS){
            selector.add(panel);
        }
        add(new JScrollPane(selector), BorderLayout.CENTER);
    }

    /**
     * Gets the list of all added projects to the selector pane.
     *
     * @return The list of available projects.
     * @since 1.0
     */

    public static ArrayList<ProjectPanel> getProjectList() {
        return PROJECTS;
    }
}