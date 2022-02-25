package org.apache.tools.ant.taskdefs;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.ExitStatusException;
import org.apache.tools.ant.PropertyHelper;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.taskdefs.condition.Condition;
import org.apache.tools.ant.taskdefs.condition.ConditionBase;

/* loaded from: classes2.dex */
public class Exit extends Task {
    private Object ifCondition;
    private String message;
    private NestedCondition nestedCondition;
    private Integer status;
    private Object unlessCondition;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class NestedCondition extends ConditionBase implements Condition {
        private NestedCondition() {
        }

        @Override // org.apache.tools.ant.taskdefs.condition.Condition
        public boolean eval() {
            if (countConditions() == 1) {
                return ((Condition) getConditions().nextElement()).eval();
            }
            throw new BuildException("A single nested condition is required.");
        }
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public void setIf(Object obj) {
        this.ifCondition = obj;
    }

    public void setIf(String str) {
        setIf((Object) str);
    }

    public void setUnless(Object obj) {
        this.unlessCondition = obj;
    }

    public void setUnless(String str) {
        setUnless((Object) str);
    }

    public void setStatus(int i) {
        this.status = new Integer(i);
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        String str;
        if (nestedConditionPresent() ? testNestedCondition() : testIfCondition() && testUnlessCondition()) {
            String str2 = null;
            String str3 = this.message;
            if (str3 == null || str3.trim().length() <= 0) {
                Object obj = this.ifCondition;
                if (obj != null && !"".equals(obj) && testIfCondition()) {
                    str2 = "if=" + this.ifCondition;
                }
                Object obj2 = this.unlessCondition;
                if (obj2 != null && !"".equals(obj2) && testUnlessCondition()) {
                    if (str2 == null) {
                        str = "";
                    } else {
                        str = str2 + " and ";
                    }
                    str2 = str + "unless=" + this.unlessCondition;
                }
                if (nestedConditionPresent()) {
                    str2 = "condition satisfied";
                } else if (str2 == null) {
                    str2 = "No message";
                }
            } else {
                str2 = this.message.trim();
            }
            log("failing due to " + str2, 4);
            Integer num = this.status;
            if (num != null) {
                throw new ExitStatusException(str2, num.intValue());
            }
        }
    }

    public void addText(String str) {
        if (this.message == null) {
            this.message = "";
        }
        this.message += getProject().replaceProperties(str);
    }

    public ConditionBase createCondition() {
        if (this.nestedCondition == null) {
            this.nestedCondition = new NestedCondition();
            return this.nestedCondition;
        }
        throw new BuildException("Only one nested condition is allowed.");
    }

    private boolean testIfCondition() {
        return PropertyHelper.getPropertyHelper(getProject()).testIfCondition(this.ifCondition);
    }

    private boolean testUnlessCondition() {
        return PropertyHelper.getPropertyHelper(getProject()).testUnlessCondition(this.unlessCondition);
    }

    private boolean testNestedCondition() {
        boolean nestedConditionPresent = nestedConditionPresent();
        if ((!nestedConditionPresent || this.ifCondition == null) && this.unlessCondition == null) {
            return nestedConditionPresent && this.nestedCondition.eval();
        }
        throw new BuildException("Nested conditions not permitted in conjunction with if/unless attributes");
    }

    private boolean nestedConditionPresent() {
        return this.nestedCondition != null;
    }
}
