package org.apache.tools.ant.taskdefs.optional.script;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DynamicConfigurator;
import org.apache.tools.ant.MagicNames;
import org.apache.tools.ant.Task;
import p110z1.SimpleComparison;

/* loaded from: classes2.dex */
public class ScriptDefBase extends Task implements DynamicConfigurator {
    private String text;
    private Map nestedElementMap = new HashMap();
    private Map attributes = new HashMap();

    @Override // org.apache.tools.ant.Task
    public void execute() {
        getScript().executeScript(this.attributes, this.nestedElementMap, this);
    }

    private ScriptDef getScript() {
        String taskType = getTaskType();
        Map map = (Map) getProject().getReference(MagicNames.SCRIPT_REPOSITORY);
        if (map != null) {
            ScriptDef scriptDef = (ScriptDef) map.get(getTaskType());
            if (scriptDef != null) {
                return scriptDef;
            }
            throw new BuildException("Script definition not found for " + taskType);
        }
        throw new BuildException("Script repository not found for " + taskType);
    }

    @Override // org.apache.tools.ant.DynamicElement
    public Object createDynamicElement(String str) {
        List list = (List) this.nestedElementMap.get(str);
        if (list == null) {
            list = new ArrayList();
            this.nestedElementMap.put(str, list);
        }
        Object createNestedElement = getScript().createNestedElement(str);
        list.add(createNestedElement);
        return createNestedElement;
    }

    @Override // org.apache.tools.ant.DynamicAttribute
    public void setDynamicAttribute(String str, String str2) {
        if (getScript().isAttributeSupported(str)) {
            this.attributes.put(str, str2);
            return;
        }
        throw new BuildException(SimpleComparison.f23612f + getTaskType() + "> does not support the \"" + str + "\" attribute");
    }

    public void addText(String str) {
        this.text = getProject().replaceProperties(str);
    }

    public String getText() {
        return this.text;
    }

    public void fail(String str) {
        throw new BuildException(str);
    }
}
