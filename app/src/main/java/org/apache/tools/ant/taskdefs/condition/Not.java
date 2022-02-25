package org.apache.tools.ant.taskdefs.condition;

import org.apache.tools.ant.BuildException;

/* loaded from: classes2.dex */
public class Not extends ConditionBase implements Condition {
    @Override // org.apache.tools.ant.taskdefs.condition.Condition
    public boolean eval() throws BuildException {
        if (countConditions() > 1) {
            throw new BuildException("You must not nest more than one condition into <not>");
        } else if (countConditions() >= 1) {
            return !((Condition) getConditions().nextElement()).eval();
        } else {
            throw new BuildException("You must nest a condition into <not>");
        }
    }
}
