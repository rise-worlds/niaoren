package org.apache.tools.ant.taskdefs;

import org.apache.tools.ant.AntTypeDefinition;
import org.apache.tools.ant.ComponentHelper;
import org.apache.tools.ant.ProjectHelper;
import org.apache.tools.ant.attribute.AttributeNamespace;

/* loaded from: classes2.dex */
public final class AttributeNamespaceDef extends AntlibDefinition {
    @Override // org.apache.tools.ant.Task
    public void execute() {
        String nsToComponentName = ProjectHelper.nsToComponentName(getURI());
        AntTypeDefinition antTypeDefinition = new AntTypeDefinition();
        antTypeDefinition.setName(nsToComponentName);
        antTypeDefinition.setClassName(AttributeNamespace.class.getName());
        antTypeDefinition.setClass(AttributeNamespace.class);
        antTypeDefinition.setRestrict(true);
        antTypeDefinition.setClassLoader(AttributeNamespace.class.getClassLoader());
        ComponentHelper.getComponentHelper(getProject()).addDataTypeDefinition(antTypeDefinition);
    }
}
