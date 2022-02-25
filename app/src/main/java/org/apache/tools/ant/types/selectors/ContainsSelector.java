package org.apache.tools.ant.types.selectors;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.Parameter;
import org.apache.tools.ant.types.Resource;
import org.apache.tools.ant.types.resources.FileResource;
import org.apache.tools.ant.types.resources.selectors.ResourceSelector;
import org.apache.tools.ant.util.FileUtils;
import p110z1.C4963cj;
import p110z1.Typography;

/* loaded from: classes2.dex */
public class ContainsSelector extends BaseExtendSelector implements ResourceSelector {
    public static final String CASE_KEY = "casesensitive";
    public static final String CONTAINS_KEY = "text";
    public static final String EXPRESSION_KEY = "expression";
    public static final String WHITESPACE_KEY = "ignorewhitespace";
    private String contains = null;
    private boolean casesensitive = true;
    private boolean ignorewhitespace = false;
    private String encoding = null;

    @Override // org.apache.tools.ant.types.DataType
    public String toString() {
        StringBuilder sb = new StringBuilder("{containsselector text: ");
        sb.append(Typography.f21049a);
        sb.append(this.contains);
        sb.append(Typography.f21049a);
        sb.append(" casesensitive: ");
        sb.append(this.casesensitive ? "true" : "false");
        sb.append(" ignorewhitespace: ");
        sb.append(this.ignorewhitespace ? "true" : "false");
        sb.append(C4963cj.f20747d);
        return sb.toString();
    }

    public void setText(String str) {
        this.contains = str;
    }

    public void setEncoding(String str) {
        this.encoding = str;
    }

    public void setCasesensitive(boolean z) {
        this.casesensitive = z;
    }

    public void setIgnorewhitespace(boolean z) {
        this.ignorewhitespace = z;
    }

    @Override // org.apache.tools.ant.types.selectors.BaseExtendSelector, org.apache.tools.ant.types.Parameterizable
    public void setParameters(Parameter[] parameterArr) {
        super.setParameters(parameterArr);
        if (parameterArr != null) {
            for (int i = 0; i < parameterArr.length; i++) {
                String name = parameterArr[i].getName();
                if (CONTAINS_KEY.equalsIgnoreCase(name)) {
                    setText(parameterArr[i].getValue());
                } else if ("casesensitive".equalsIgnoreCase(name)) {
                    setCasesensitive(Project.toBoolean(parameterArr[i].getValue()));
                } else if (WHITESPACE_KEY.equalsIgnoreCase(name)) {
                    setIgnorewhitespace(Project.toBoolean(parameterArr[i].getValue()));
                } else {
                    setError("Invalid parameter " + name);
                }
            }
        }
    }

    @Override // org.apache.tools.ant.types.selectors.BaseSelector
    public void verifySettings() {
        if (this.contains == null) {
            setError("The text attribute is required");
        }
    }

    @Override // org.apache.tools.ant.types.selectors.BaseExtendSelector, org.apache.tools.ant.types.selectors.BaseSelector, org.apache.tools.ant.types.selectors.FileSelector
    public boolean isSelected(File file, String str, File file2) {
        return isSelected(new FileResource(file2));
    }

    @Override // org.apache.tools.ant.types.resources.selectors.ResourceSelector
    public boolean isSelected(Resource resource) {
        BufferedReader bufferedReader;
        validate();
        if (resource.isDirectory() || this.contains.length() == 0) {
            return true;
        }
        String str = this.contains;
        if (!this.casesensitive) {
            str = str.toLowerCase();
        }
        if (this.ignorewhitespace) {
            str = SelectorUtils.removeWhitespace(str);
        }
        try {
            if (this.encoding != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(resource.getInputStream(), this.encoding));
            } else {
                bufferedReader = new BufferedReader(new InputStreamReader(resource.getInputStream()));
            }
            try {
                try {
                    String readLine = bufferedReader.readLine();
                    while (readLine != null) {
                        if (!this.casesensitive) {
                            readLine = readLine.toLowerCase();
                        }
                        if (this.ignorewhitespace) {
                            readLine = SelectorUtils.removeWhitespace(readLine);
                        }
                        if (readLine.indexOf(str) > -1) {
                            return true;
                        }
                        readLine = bufferedReader.readLine();
                    }
                    return false;
                } catch (IOException unused) {
                    throw new BuildException("Could not read " + resource.toLongString());
                }
            } finally {
                FileUtils.close(bufferedReader);
            }
        } catch (Exception e) {
            throw new BuildException("Could not get InputStream from " + resource.toLongString(), e);
        }
    }
}
