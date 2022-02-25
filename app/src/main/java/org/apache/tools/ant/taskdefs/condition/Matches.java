package org.apache.tools.ant.taskdefs.condition;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.ProjectComponent;
import org.apache.tools.ant.types.RegularExpression;
import org.apache.tools.ant.util.regexp.RegexpUtil;

/* loaded from: classes2.dex */
public class Matches extends ProjectComponent implements Condition {
    private RegularExpression regularExpression;
    private String string;
    private boolean caseSensitive = true;
    private boolean multiLine = false;
    private boolean singleLine = false;

    public void setString(String str) {
        this.string = str;
    }

    public void setPattern(String str) {
        if (this.regularExpression == null) {
            this.regularExpression = new RegularExpression();
            this.regularExpression.setPattern(str);
            return;
        }
        throw new BuildException("Only one regular expression is allowed.");
    }

    public void addRegexp(RegularExpression regularExpression) {
        if (this.regularExpression == null) {
            this.regularExpression = regularExpression;
            return;
        }
        throw new BuildException("Only one regular expression is allowed.");
    }

    public void setCasesensitive(boolean z) {
        this.caseSensitive = z;
    }

    public void setMultiline(boolean z) {
        this.multiLine = z;
    }

    public void setSingleLine(boolean z) {
        this.singleLine = z;
    }

    @Override // org.apache.tools.ant.taskdefs.condition.Condition
    public boolean eval() throws BuildException {
        if (this.string == null) {
            throw new BuildException("Parameter string is required in matches.");
        } else if (this.regularExpression != null) {
            return this.regularExpression.getRegexp(getProject()).matches(this.string, RegexpUtil.asOptions(this.caseSensitive, this.multiLine, this.singleLine));
        } else {
            throw new BuildException("Missing pattern in matches.");
        }
    }
}
