package org.apache.tools.ant.util;

import java.io.File;
import org.apache.tools.ant.ProjectComponent;
import org.apache.tools.ant.taskdefs.email.EmailTask;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.types.Reference;
import org.apache.tools.ant.types.ResourceCollection;
import org.apache.tools.ant.types.resources.Union;
import org.apache.tools.ant.util.ClasspathUtils;

/* loaded from: classes2.dex */
public class ScriptRunnerHelper {
    private String language;
    private ProjectComponent projectComponent;
    private File srcFile;
    private String text;
    private ClasspathUtils.Delegate cpDelegate = null;
    private String manager = EmailTask.AUTO;
    private boolean setBeans = true;
    private ClassLoader scriptLoader = null;
    private Union resources = new Union();

    public void setProjectComponent(ProjectComponent projectComponent) {
        this.projectComponent = projectComponent;
    }

    public ScriptRunnerBase getScriptRunner() {
        ScriptRunnerBase runner = getRunner();
        File file = this.srcFile;
        if (file != null) {
            runner.setSrc(file);
        }
        String str = this.text;
        if (str != null) {
            runner.addText(str);
        }
        Union union = this.resources;
        if (union != null) {
            runner.loadResources(union);
        }
        if (this.setBeans) {
            runner.bindToComponent(this.projectComponent);
        } else {
            runner.bindToComponentMinimum(this.projectComponent);
        }
        return runner;
    }

    public Path createClasspath() {
        return getClassPathDelegate().createClasspath();
    }

    public void setClasspath(Path path) {
        getClassPathDelegate().setClasspath(path);
    }

    public void setClasspathRef(Reference reference) {
        getClassPathDelegate().setClasspathref(reference);
    }

    public void setSrc(File file) {
        this.srcFile = file;
    }

    public void addText(String str) {
        this.text = str;
    }

    public void setManager(String str) {
        this.manager = str;
    }

    public void setLanguage(String str) {
        this.language = str;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setSetBeans(boolean z) {
        this.setBeans = z;
    }

    public void setClassLoader(ClassLoader classLoader) {
        this.scriptLoader = classLoader;
    }

    private synchronized ClassLoader generateClassLoader() {
        if (this.scriptLoader != null) {
            return this.scriptLoader;
        } else if (this.cpDelegate == null) {
            this.scriptLoader = getClass().getClassLoader();
            return this.scriptLoader;
        } else {
            this.scriptLoader = this.cpDelegate.getClassLoader();
            return this.scriptLoader;
        }
    }

    private ClasspathUtils.Delegate getClassPathDelegate() {
        if (this.cpDelegate == null) {
            ProjectComponent projectComponent = this.projectComponent;
            if (projectComponent != null) {
                this.cpDelegate = ClasspathUtils.getDelegate(projectComponent);
            } else {
                throw new IllegalStateException("Can't access classpath without a project component");
            }
        }
        return this.cpDelegate;
    }

    private ScriptRunnerBase getRunner() {
        return new ScriptRunnerCreator(this.projectComponent.getProject()).createRunner(this.manager, this.language, generateClassLoader());
    }

    public void add(ResourceCollection resourceCollection) {
        this.resources.add(resourceCollection);
    }
}
