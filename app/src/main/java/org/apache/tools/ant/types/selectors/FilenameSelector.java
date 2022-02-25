package org.apache.tools.ant.types.selectors;

import java.io.File;
import org.apache.commons.p105io.IOUtils;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.Parameter;
import org.apache.tools.ant.types.RegularExpression;
import org.apache.tools.ant.util.regexp.Regexp;
import org.apache.tools.ant.util.regexp.RegexpUtil;
import p110z1.C4963cj;

/* loaded from: classes2.dex */
public class FilenameSelector extends BaseExtendSelector {
    public static final String CASE_KEY = "casesensitive";
    public static final String NAME_KEY = "name";
    public static final String NEGATE_KEY = "negate";
    public static final String REGEX_KEY = "regex";
    private Regexp expression;
    private RegularExpression reg;
    private String pattern = null;
    private String regex = null;
    private boolean casesensitive = true;
    private boolean negated = false;

    @Override // org.apache.tools.ant.types.DataType
    public String toString() {
        StringBuilder sb = new StringBuilder("{filenameselector name: ");
        String str = this.pattern;
        if (str != null) {
            sb.append(str);
        }
        String str2 = this.regex;
        if (str2 != null) {
            sb.append(str2);
            sb.append(" [as regular expression]");
        }
        sb.append(" negate: ");
        sb.append(this.negated);
        sb.append(" casesensitive: ");
        sb.append(this.casesensitive);
        sb.append(C4963cj.f20747d);
        return sb.toString();
    }

    public void setName(String str) {
        String replace = str.replace(IOUtils.DIR_SEPARATOR_UNIX, File.separatorChar).replace(IOUtils.DIR_SEPARATOR_WINDOWS, File.separatorChar);
        if (replace.endsWith(File.separator)) {
            replace = replace + SelectorUtils.DEEP_TREE_MATCH;
        }
        this.pattern = replace;
    }

    public void setRegex(String str) {
        this.regex = str;
        this.reg = null;
    }

    public void setCasesensitive(boolean z) {
        this.casesensitive = z;
    }

    public void setNegate(boolean z) {
        this.negated = z;
    }

    @Override // org.apache.tools.ant.types.selectors.BaseExtendSelector, org.apache.tools.ant.types.Parameterizable
    public void setParameters(Parameter[] parameterArr) {
        super.setParameters(parameterArr);
        if (parameterArr != null) {
            for (int i = 0; i < parameterArr.length; i++) {
                String name = parameterArr[i].getName();
                if ("name".equalsIgnoreCase(name)) {
                    setName(parameterArr[i].getValue());
                } else if ("casesensitive".equalsIgnoreCase(name)) {
                    setCasesensitive(Project.toBoolean(parameterArr[i].getValue()));
                } else if (NEGATE_KEY.equalsIgnoreCase(name)) {
                    setNegate(Project.toBoolean(parameterArr[i].getValue()));
                } else if (REGEX_KEY.equalsIgnoreCase(name)) {
                    setRegex(parameterArr[i].getValue());
                } else {
                    setError("Invalid parameter " + name);
                }
            }
        }
    }

    @Override // org.apache.tools.ant.types.selectors.BaseSelector
    public void verifySettings() {
        if (this.pattern == null && this.regex == null) {
            setError("The name or regex attribute is required");
        } else if (this.pattern != null && this.regex != null) {
            setError("Only one of name and regex attribute is allowed");
        }
    }

    @Override // org.apache.tools.ant.types.selectors.BaseExtendSelector, org.apache.tools.ant.types.selectors.BaseSelector, org.apache.tools.ant.types.selectors.FileSelector
    public boolean isSelected(File file, String str, File file2) {
        validate();
        String str2 = this.pattern;
        if (str2 != null) {
            return SelectorUtils.matchPath(str2, str, this.casesensitive) == (this.negated ^ true);
        }
        if (this.reg == null) {
            this.reg = new RegularExpression();
            this.reg.setPattern(this.regex);
            this.expression = this.reg.getRegexp(getProject());
        }
        return this.expression.matches(str, RegexpUtil.asOptions(this.casesensitive)) == (this.negated ^ true);
    }
}
