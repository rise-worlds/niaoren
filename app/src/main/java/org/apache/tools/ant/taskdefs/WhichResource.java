package org.apache.tools.ant.taskdefs;

import java.net.URL;
import org.apache.commons.p105io.FilenameUtils;
import org.apache.commons.p105io.IOUtils;
import org.apache.tools.ant.AntClassLoader;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.taskdefs.Definer;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.types.Reference;

/* loaded from: classes2.dex */
public class WhichResource extends Task {
    private String classname;
    private Path classpath;
    private String property;
    private String resource;

    public void setClasspath(Path path) {
        Path path2 = this.classpath;
        if (path2 == null) {
            this.classpath = path;
        } else {
            path2.append(path);
        }
    }

    public Path createClasspath() {
        if (this.classpath == null) {
            this.classpath = new Path(getProject());
        }
        return this.classpath.createPath();
    }

    public void setClasspathRef(Reference reference) {
        createClasspath().setRefid(reference);
    }

    private void validate() {
        int i = this.classname != null ? 1 : 0;
        if (this.resource != null) {
            i++;
        }
        if (i == 0) {
            throw new BuildException("One of classname or resource must be specified");
        } else if (i > 1) {
            throw new BuildException("Only one of classname or resource can be specified");
        } else if (this.property == null) {
            throw new BuildException(MakeUrl.ERROR_NO_PROPERTY);
        }
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        validate();
        Path path = this.classpath;
        if (path != null) {
            this.classpath = path.concatSystemClasspath(Definer.OnError.POLICY_IGNORE);
            Project project = getProject();
            project.log("using user supplied classpath: " + this.classpath, 4);
        } else {
            this.classpath = new Path(getProject());
            this.classpath = this.classpath.concatSystemClasspath("only");
            Project project2 = getProject();
            project2.log("using system classpath: " + this.classpath, 4);
        }
        AntClassLoader antClassLoader = null;
        try {
            antClassLoader = AntClassLoader.newAntClassLoader(getProject().getCoreLoader(), getProject(), this.classpath, false);
            if (this.classname != null) {
                this.resource = this.classname.replace(FilenameUtils.EXTENSION_SEPARATOR, IOUtils.DIR_SEPARATOR_UNIX) + ".class";
            }
            if (this.resource != null) {
                if (this.resource.startsWith("/")) {
                    this.resource = this.resource.substring(1);
                }
                log("Searching for " + this.resource, 3);
                URL resource = antClassLoader.getResource(this.resource);
                if (resource != null) {
                    getProject().setNewProperty(this.property, resource.toExternalForm());
                }
                if (antClassLoader == null) {
                    return;
                }
                return;
            }
            throw new BuildException("One of class or resource is required");
        } finally {
            if (antClassLoader != null) {
                antClassLoader.cleanup();
            }
        }
    }

    public void setResource(String str) {
        this.resource = str;
    }

    public void setClass(String str) {
        this.classname = str;
    }

    public void setProperty(String str) {
        this.property = str;
    }
}
