package org.apache.tools.ant.types.optional;

import java.io.File;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectComponent;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.types.Reference;
import org.apache.tools.ant.util.ScriptRunnerBase;
import org.apache.tools.ant.util.ScriptRunnerHelper;

/* loaded from: classes2.dex */
public abstract class AbstractScriptComponent extends ProjectComponent {
    private ScriptRunnerHelper helper = new ScriptRunnerHelper();
    private ScriptRunnerBase runner = null;

    @Override // org.apache.tools.ant.ProjectComponent
    public void setProject(Project project) {
        super.setProject(project);
        this.helper.setProjectComponent(this);
    }

    public ScriptRunnerBase getRunner() {
        initScriptRunner();
        return this.runner;
    }

    public void setSrc(File file) {
        this.helper.setSrc(file);
    }

    public void addText(String str) {
        this.helper.addText(str);
    }

    public void setManager(String str) {
        this.helper.setManager(str);
    }

    public void setLanguage(String str) {
        this.helper.setLanguage(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void initScriptRunner() {
        if (this.runner == null) {
            this.helper.setProjectComponent(this);
            this.runner = this.helper.getScriptRunner();
        }
    }

    public void setClasspath(Path path) {
        this.helper.setClasspath(path);
    }

    public Path createClasspath() {
        return this.helper.createClasspath();
    }

    public void setClasspathRef(Reference reference) {
        this.helper.setClasspathRef(reference);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void executeScript(String str) {
        getRunner().executeScript(str);
    }

    public void setSetBeans(boolean z) {
        this.helper.setSetBeans(z);
    }
}
