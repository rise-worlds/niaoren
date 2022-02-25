package org.apache.tools.ant.util;

import org.apache.commons.p105io.IOUtils;
import org.apache.tools.ant.BuildException;
import org.slf4j.Marker;

/* loaded from: classes2.dex */
public class GlobPatternMapper implements FileNameMapper {
    protected int postfixLength;
    protected int prefixLength;
    protected String fromPrefix = null;
    protected String fromPostfix = null;
    protected String toPrefix = null;
    protected String toPostfix = null;
    private boolean fromContainsStar = false;
    private boolean toContainsStar = false;
    private boolean handleDirSep = false;
    private boolean caseSensitive = true;

    public void setHandleDirSep(boolean z) {
        this.handleDirSep = z;
    }

    public boolean getHandleDirSep() {
        return this.handleDirSep;
    }

    public void setCaseSensitive(boolean z) {
        this.caseSensitive = z;
    }

    @Override // org.apache.tools.ant.util.FileNameMapper
    public void setFrom(String str) {
        if (str != null) {
            int lastIndexOf = str.lastIndexOf(Marker.ANY_MARKER);
            if (lastIndexOf == -1) {
                this.fromPrefix = str;
                this.fromPostfix = "";
            } else {
                this.fromPrefix = str.substring(0, lastIndexOf);
                this.fromPostfix = str.substring(lastIndexOf + 1);
                this.fromContainsStar = true;
            }
            this.prefixLength = this.fromPrefix.length();
            this.postfixLength = this.fromPostfix.length();
            return;
        }
        throw new BuildException("this mapper requires a 'from' attribute");
    }

    @Override // org.apache.tools.ant.util.FileNameMapper
    public void setTo(String str) {
        if (str != null) {
            int lastIndexOf = str.lastIndexOf(Marker.ANY_MARKER);
            if (lastIndexOf == -1) {
                this.toPrefix = str;
                this.toPostfix = "";
                return;
            }
            this.toPrefix = str.substring(0, lastIndexOf);
            this.toPostfix = str.substring(lastIndexOf + 1);
            this.toContainsStar = true;
            return;
        }
        throw new BuildException("this mapper requires a 'to' attribute");
    }

    @Override // org.apache.tools.ant.util.FileNameMapper
    public String[] mapFileName(String str) {
        String str2;
        String modifyName = modifyName(str);
        if (this.fromPrefix == null || str.length() < this.prefixLength + this.postfixLength) {
            return null;
        }
        if (!this.fromContainsStar && !modifyName.equals(modifyName(this.fromPrefix))) {
            return null;
        }
        if (this.fromContainsStar && (!modifyName.startsWith(modifyName(this.fromPrefix)) || !modifyName.endsWith(modifyName(this.fromPostfix)))) {
            return null;
        }
        String[] strArr = new String[1];
        StringBuilder sb = new StringBuilder();
        sb.append(this.toPrefix);
        if (this.toContainsStar) {
            str2 = extractVariablePart(str) + this.toPostfix;
        } else {
            str2 = "";
        }
        sb.append(str2);
        strArr[0] = sb.toString();
        return strArr;
    }

    protected String extractVariablePart(String str) {
        return str.substring(this.prefixLength, str.length() - this.postfixLength);
    }

    private String modifyName(String str) {
        if (!this.caseSensitive) {
            str = str.toLowerCase();
        }
        return (!this.handleDirSep || str.indexOf(92) == -1) ? str : str.replace(IOUtils.DIR_SEPARATOR_WINDOWS, IOUtils.DIR_SEPARATOR_UNIX);
    }
}
