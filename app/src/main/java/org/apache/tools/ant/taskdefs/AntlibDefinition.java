package org.apache.tools.ant.taskdefs;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.ProjectHelper;
import org.apache.tools.ant.Task;

/* loaded from: classes2.dex */
public class AntlibDefinition extends Task {
    private ClassLoader antlibClassLoader;
    private String uri = "";

    public void setURI(String str) throws BuildException {
        if (str.equals(ProjectHelper.ANT_CORE_URI)) {
            str = "";
        }
        if (!str.startsWith("ant:")) {
            this.uri = str;
            return;
        }
        throw new BuildException("Attempt to use a reserved URI " + str);
    }

    public String getURI() {
        return this.uri;
    }

    public void setAntlibClassLoader(ClassLoader classLoader) {
        this.antlibClassLoader = classLoader;
    }

    public ClassLoader getAntlibClassLoader() {
        return this.antlibClassLoader;
    }
}
