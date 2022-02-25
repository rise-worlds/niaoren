package org.apache.tools.ant.taskdefs;

import java.util.ArrayList;
import java.util.List;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.ProjectHelper;
import org.apache.tools.ant.ProjectHelperRepository;
import org.apache.tools.ant.Task;

/* loaded from: classes2.dex */
public class ProjectHelperTask extends Task {
    private List projectHelpers = new ArrayList();

    public synchronized void addConfigured(ProjectHelper projectHelper) {
        this.projectHelpers.add(projectHelper);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        ProjectHelperRepository instance = ProjectHelperRepository.getInstance();
        for (ProjectHelper projectHelper : this.projectHelpers) {
            instance.registerProjectHelper((Class<? extends ProjectHelper>) projectHelper.getClass());
        }
    }
}
