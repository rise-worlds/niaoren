package org.apache.tools.ant;

/* loaded from: classes2.dex */
public interface Executor {
    void executeTargets(Project project, String[] strArr) throws BuildException;

    Executor getSubProjectExecutor();
}
