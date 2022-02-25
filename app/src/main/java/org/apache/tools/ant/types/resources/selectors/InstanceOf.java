package org.apache.tools.ant.types.resources.selectors;

import org.apache.tools.ant.AntTypeDefinition;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.ComponentHelper;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;
import org.apache.tools.ant.types.Resource;

/* loaded from: classes2.dex */
public class InstanceOf implements ResourceSelector {
    private static final String ONE_ONLY = "Exactly one of class|type must be set.";
    private Class<?> clazz;
    private Project project;
    private String type;
    private String uri;

    public void setProject(Project project) {
        this.project = project;
    }

    public void setClass(Class<?> cls) {
        if (this.clazz == null) {
            this.clazz = cls;
            return;
        }
        throw new BuildException("The class attribute has already been set.");
    }

    public void setType(String str) {
        this.type = str;
    }

    public void setURI(String str) {
        this.uri = str;
    }

    public Class<?> getCheckClass() {
        return this.clazz;
    }

    public String getType() {
        return this.type;
    }

    public String getURI() {
        return this.uri;
    }

    @Override // org.apache.tools.ant.types.resources.selectors.ResourceSelector
    public boolean isSelected(Resource resource) {
        boolean z = true;
        boolean z2 = this.clazz == null;
        if (this.type != null) {
            z = false;
        }
        if (z2 != z) {
            Class<?> cls = this.clazz;
            if (this.type != null) {
                Project project = this.project;
                if (project != null) {
                    AntTypeDefinition definition = ComponentHelper.getComponentHelper(project).getDefinition(ProjectHelper.genComponentName(this.uri, this.type));
                    if (definition != null) {
                        try {
                            cls = definition.innerGetTypeClass();
                        } catch (ClassNotFoundException e) {
                            throw new BuildException(e);
                        }
                    } else {
                        throw new BuildException("type " + this.type + " not found.");
                    }
                } else {
                    throw new BuildException("No project set for InstanceOf ResourceSelector; the type attribute is invalid.");
                }
            }
            return cls.isAssignableFrom(resource.getClass());
        }
        throw new BuildException(ONE_ONLY);
    }
}
