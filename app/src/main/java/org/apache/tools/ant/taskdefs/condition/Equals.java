package org.apache.tools.ant.taskdefs.condition;

import org.apache.tools.ant.BuildException;

/* loaded from: classes2.dex */
public class Equals implements Condition {
    private static final int REQUIRED = 3;
    private Object arg1;
    private Object arg2;
    private int args;
    private boolean trim = false;
    private boolean caseSensitive = true;
    private boolean forcestring = false;

    public void setArg1(Object obj) {
        if (obj instanceof String) {
            setArg1((String) obj);
        } else {
            setArg1Internal(obj);
        }
    }

    public void setArg1(String str) {
        setArg1Internal(str);
    }

    private void setArg1Internal(Object obj) {
        this.arg1 = obj;
        this.args |= 1;
    }

    public void setArg2(Object obj) {
        if (obj instanceof String) {
            setArg2((String) obj);
        } else {
            setArg2Internal(obj);
        }
    }

    public void setArg2(String str) {
        setArg2Internal(str);
    }

    private void setArg2Internal(Object obj) {
        this.arg2 = obj;
        this.args |= 2;
    }

    public void setTrim(boolean z) {
        this.trim = z;
    }

    public void setCasesensitive(boolean z) {
        this.caseSensitive = z;
    }

    public void setForcestring(boolean z) {
        this.forcestring = z;
    }

    @Override // org.apache.tools.ant.taskdefs.condition.Condition
    public boolean eval() throws BuildException {
        if ((this.args & 3) == 3) {
            Object obj = this.arg1;
            Object obj2 = this.arg2;
            if (obj == obj2) {
                return true;
            }
            if (obj != null && obj.equals(obj2)) {
                return true;
            }
            if (this.forcestring) {
                Object obj3 = this.arg1;
                this.arg1 = (obj3 == null || (obj3 instanceof String)) ? this.arg1 : obj3.toString();
                Object obj4 = this.arg2;
                this.arg2 = (obj4 == null || (obj4 instanceof String)) ? this.arg2 : obj4.toString();
            }
            Object obj5 = this.arg1;
            if ((obj5 instanceof String) && this.trim) {
                this.arg1 = ((String) obj5).trim();
            }
            Object obj6 = this.arg2;
            if ((obj6 instanceof String) && this.trim) {
                this.arg2 = ((String) obj6).trim();
            }
            Object obj7 = this.arg1;
            if (!(obj7 instanceof String)) {
                return false;
            }
            Object obj8 = this.arg2;
            if (!(obj8 instanceof String)) {
                return false;
            }
            String str = (String) obj7;
            String str2 = (String) obj8;
            return this.caseSensitive ? str.equals(str2) : str.equalsIgnoreCase(str2);
        }
        throw new BuildException("both arg1 and arg2 are required in equals");
    }
}
