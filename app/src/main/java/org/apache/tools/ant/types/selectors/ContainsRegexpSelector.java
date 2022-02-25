package org.apache.tools.ant.types.selectors;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.Parameter;
import org.apache.tools.ant.types.RegularExpression;
import org.apache.tools.ant.types.Resource;
import org.apache.tools.ant.types.resources.FileResource;
import org.apache.tools.ant.types.resources.selectors.ResourceSelector;
import org.apache.tools.ant.util.regexp.Regexp;
import org.apache.tools.ant.util.regexp.RegexpUtil;
import p110z1.C4963cj;

/* loaded from: classes2.dex */
public class ContainsRegexpSelector extends BaseExtendSelector implements ResourceSelector {
    private static final String CS_KEY = "casesensitive";
    public static final String EXPRESSION_KEY = "expression";
    private static final String ML_KEY = "multiline";
    private static final String SL_KEY = "singleline";
    private String userProvidedExpression = null;
    private RegularExpression myRegExp = null;
    private Regexp myExpression = null;
    private boolean caseSensitive = true;
    private boolean multiLine = false;
    private boolean singleLine = false;

    @Override // org.apache.tools.ant.types.DataType
    public String toString() {
        return "{containsregexpselector expression: " + this.userProvidedExpression + C4963cj.f20747d;
    }

    public void setExpression(String str) {
        this.userProvidedExpression = str;
    }

    public void setCaseSensitive(boolean z) {
        this.caseSensitive = z;
    }

    public void setMultiLine(boolean z) {
        this.multiLine = z;
    }

    public void setSingleLine(boolean z) {
        this.singleLine = z;
    }

    @Override // org.apache.tools.ant.types.selectors.BaseExtendSelector, org.apache.tools.ant.types.Parameterizable
    public void setParameters(Parameter[] parameterArr) {
        super.setParameters(parameterArr);
        if (parameterArr != null) {
            for (int i = 0; i < parameterArr.length; i++) {
                String name = parameterArr[i].getName();
                if ("expression".equalsIgnoreCase(name)) {
                    setExpression(parameterArr[i].getValue());
                } else if ("casesensitive".equalsIgnoreCase(name)) {
                    setCaseSensitive(Project.toBoolean(parameterArr[i].getValue()));
                } else if (ML_KEY.equalsIgnoreCase(name)) {
                    setMultiLine(Project.toBoolean(parameterArr[i].getValue()));
                } else if (SL_KEY.equalsIgnoreCase(name)) {
                    setSingleLine(Project.toBoolean(parameterArr[i].getValue()));
                } else {
                    setError("Invalid parameter " + name);
                }
            }
        }
    }

    @Override // org.apache.tools.ant.types.selectors.BaseSelector
    public void verifySettings() {
        if (this.userProvidedExpression == null) {
            setError("The expression attribute is required");
        }
    }

    @Override // org.apache.tools.ant.types.selectors.BaseExtendSelector, org.apache.tools.ant.types.selectors.BaseSelector, org.apache.tools.ant.types.selectors.FileSelector
    public boolean isSelected(File file, String str, File file2) {
        return isSelected(new FileResource(file2));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.apache.tools.ant.types.resources.selectors.ResourceSelector
    public boolean isSelected(Resource resource) {
        validate();
        if (resource.isDirectory()) {
            return true;
        }
        RegularExpression regularExpression = this.myRegExp;
        BufferedReader bufferedReader = regularExpression;
        if (regularExpression == null) {
            this.myRegExp = new RegularExpression();
            this.myRegExp.setPattern(this.userProvidedExpression);
            Regexp regexp = this.myRegExp.getRegexp(getProject());
            this.myExpression = regexp;
            bufferedReader = regexp;
        }
        try {
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(resource.getInputStream()));
                try {
                    for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
                        if (this.myExpression.matches(readLine, RegexpUtil.asOptions(this.caseSensitive, this.multiLine, this.singleLine))) {
                            try {
                                bufferedReader.close();
                                return true;
                            } catch (Exception unused) {
                                throw new BuildException("Could not close " + resource.toLongString());
                            }
                        }
                    }
                    try {
                        bufferedReader.close();
                        return false;
                    } catch (Exception unused2) {
                        throw new BuildException("Could not close " + resource.toLongString());
                    }
                } catch (IOException unused3) {
                    throw new BuildException("Could not read " + resource.toLongString());
                }
            } catch (Exception e) {
                throw new BuildException("Could not get InputStream from " + resource.toLongString(), e);
            }
        } catch (Throwable th) {
            try {
                bufferedReader.close();
                throw th;
            } catch (Exception unused4) {
                throw new BuildException("Could not close " + resource.toLongString());
            }
        }
    }
}
