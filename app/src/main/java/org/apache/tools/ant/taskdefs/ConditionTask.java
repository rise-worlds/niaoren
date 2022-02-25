package org.apache.tools.ant.taskdefs;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.PropertyHelper;
import org.apache.tools.ant.taskdefs.condition.Condition;
import org.apache.tools.ant.taskdefs.condition.ConditionBase;
import p110z1.SimpleComparison;

/* loaded from: classes2.dex */
public class ConditionTask extends ConditionBase {
    private String property = null;
    private Object value = "true";
    private Object alternative = null;

    public ConditionTask() {
        super("condition");
    }

    public void setProperty(String str) {
        this.property = str;
    }

    public void setValue(Object obj) {
        this.value = obj;
    }

    public void setValue(String str) {
        setValue((Object) str);
    }

    public void setElse(Object obj) {
        this.alternative = obj;
    }

    public void setElse(String str) {
        setElse((Object) str);
    }

    public void execute() throws BuildException {
        if (countConditions() > 1) {
            throw new BuildException("You must not nest more than one condition into <" + getTaskName() + SimpleComparison.f23610d);
        } else if (countConditions() < 1) {
            throw new BuildException("You must nest a condition into <" + getTaskName() + SimpleComparison.f23610d);
        } else if (this.property == null) {
            throw new BuildException("The property attribute is required.");
        } else if (((Condition) getConditions().nextElement()).eval()) {
            log("Condition true; setting " + this.property + " to " + this.value, 4);
            PropertyHelper.getPropertyHelper(getProject()).setNewProperty(this.property, this.value);
        } else if (this.alternative != null) {
            log("Condition false; setting " + this.property + " to " + this.alternative, 4);
            PropertyHelper.getPropertyHelper(getProject()).setNewProperty(this.property, this.alternative);
        } else {
            log("Condition false; not setting " + this.property, 4);
        }
    }
}
