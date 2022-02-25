package org.apache.tools.ant.taskdefs.condition;

import org.apache.tools.ant.AntTypeDefinition;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.ComponentHelper;
import org.apache.tools.ant.ProjectComponent;
import org.apache.tools.ant.ProjectHelper;

/* loaded from: classes2.dex */
public class TypeFound extends ProjectComponent implements Condition {
    private String name;
    private String uri;

    public void setName(String str) {
        this.name = str;
    }

    public void setURI(String str) {
        this.uri = str;
    }

    protected boolean doesTypeExist(String str) {
        ComponentHelper componentHelper = ComponentHelper.getComponentHelper(getProject());
        String genComponentName = ProjectHelper.genComponentName(this.uri, str);
        AntTypeDefinition definition = componentHelper.getDefinition(genComponentName);
        boolean z = false;
        if (definition == null) {
            return false;
        }
        if (definition.getExposedClass(getProject()) != null) {
            z = true;
        }
        if (!z) {
            log(componentHelper.diagnoseCreationFailure(genComponentName, "type"), 3);
        }
        return z;
    }

    @Override // org.apache.tools.ant.taskdefs.condition.Condition
    public boolean eval() throws BuildException {
        String str = this.name;
        if (str != null) {
            return doesTypeExist(str);
        }
        throw new BuildException("No type specified");
    }
}
