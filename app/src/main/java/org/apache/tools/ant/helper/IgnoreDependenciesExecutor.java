package org.apache.tools.ant.helper;

import java.util.Hashtable;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Executor;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Target;

/* loaded from: classes2.dex */
public class IgnoreDependenciesExecutor implements Executor {
    private static final SingleCheckExecutor SUB_EXECUTOR = new SingleCheckExecutor();

    @Override // org.apache.tools.ant.Executor
    public void executeTargets(Project project, String[] strArr) throws BuildException {
        Target target;
        Hashtable<String, Target> targets = project.getTargets();
        BuildException e = null;
        for (int i = 0; i < strArr.length; i++) {
            try {
                target = targets.get(strArr[i]);
            } catch (BuildException e2) {
                e = e2;
                if (!project.isKeepGoingMode()) {
                    throw e;
                }
            }
            if (target == null) {
                throw new BuildException("Unknown target " + strArr[i]);
                break;
            }
            target.performTasks();
        }
        if (e != null) {
            throw e;
        }
    }

    @Override // org.apache.tools.ant.Executor
    public Executor getSubProjectExecutor() {
        return SUB_EXECUTOR;
    }
}
