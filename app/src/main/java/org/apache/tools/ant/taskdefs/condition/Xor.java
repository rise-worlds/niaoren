package org.apache.tools.ant.taskdefs.condition;

import java.util.Enumeration;
import org.apache.tools.ant.BuildException;

/* loaded from: classes2.dex */
public class Xor extends ConditionBase implements Condition {
    @Override // org.apache.tools.ant.taskdefs.condition.Condition
    public boolean eval() throws BuildException {
        Enumeration conditions = getConditions();
        boolean z = false;
        while (conditions.hasMoreElements()) {
            z ^= ((Condition) conditions.nextElement()).eval();
        }
        return z;
    }
}
