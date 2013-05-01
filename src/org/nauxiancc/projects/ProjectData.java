package org.nauxiancc.projects;

import java.io.File;
import java.util.LinkedList;

import org.nauxiancc.configuration.Global.Paths;

/**
 * Loads locally found runners for the updater and for the loader. This will
 * retrieve all runners found in the respective categories.
 *
 * @author Naux
 * @since 1.0
 */

public class ProjectData {

    public static LinkedList<Project> DATA;

    private ProjectData() {
    }

    /**
     * This does a sweep of the source folder to get all 5 categories and
     * respective Runners inside of them. This is should only be called to check
     * for currently loaded Runners, then to add all the updated versions of
     * Runners, if any. The key for the HashMap is the category, with the value
     * being a list of runners.
     *
     * @since 1.0
     */

    public static void loadCurrent() {
        DATA = new LinkedList<>();
        final File root = new File(Paths.SOURCE);
        if (!root.exists() || root.listFiles() == null) {
            return;
        }
        for (final File file : root.listFiles()) {
            final String name = file.getName();
            final int idx = name.indexOf("Runner.class");
            if (idx == -1) {
                continue;
            }
            try {
                final Project p = new Project(name.substring(0, idx), file);
                DATA.add(p);
            } catch (final Exception e) {
                e.printStackTrace();
            }
        }
    }

}
