package org.apache.tools.ant.taskdefs.optional.jsp;

import com.lody.virtual.client.ipc.ServiceManagerNative;
import java.io.File;
import org.apache.commons.p105io.IOUtils;
import org.apache.tools.ant.util.StringUtils;

/* loaded from: classes2.dex */
public class JspNameMangler implements JspMangler {
    public static final String[] keywords = {"assert", "abstract", "boolean", "break", "byte", "case", "catch", "char", "class", "const", "continue", "default", "do", "double", "else", "extends", "final", "finally", "float", "for", "goto", "if", "implements", "import", "instanceof", "int", "interface", "long", "native", "new", ServiceManagerNative.PACKAGE, "private", "protected", "public", "return", "short", "static", "super", "switch", "synchronized", "this", "throw", "throws", "transient", "try", "void", "volatile", "while"};

    @Override // org.apache.tools.ant.taskdefs.optional.jsp.JspMangler
    public String mapPath(String str) {
        return null;
    }

    @Override // org.apache.tools.ant.taskdefs.optional.jsp.JspMangler
    public String mapJspToJavaName(File file) {
        return mapJspToBaseName(file) + ".java";
    }

    private String mapJspToBaseName(File file) {
        String stripExtension = stripExtension(file);
        int i = 0;
        while (true) {
            String[] strArr = keywords;
            if (i >= strArr.length) {
                break;
            } else if (stripExtension.equals(strArr[i])) {
                stripExtension = stripExtension + "%";
                break;
            } else {
                i++;
            }
        }
        StringBuffer stringBuffer = new StringBuffer(stripExtension.length());
        char charAt = stripExtension.charAt(0);
        if (Character.isJavaIdentifierStart(charAt)) {
            stringBuffer.append(charAt);
        } else {
            stringBuffer.append(mangleChar(charAt));
        }
        for (int i2 = 1; i2 < stripExtension.length(); i2++) {
            char charAt2 = stripExtension.charAt(i2);
            if (Character.isJavaIdentifierPart(charAt2)) {
                stringBuffer.append(charAt2);
            } else {
                stringBuffer.append(mangleChar(charAt2));
            }
        }
        return stringBuffer.toString();
    }

    private String stripExtension(File file) {
        return StringUtils.removeSuffix(file.getName(), ".jsp");
    }

    private static String mangleChar(char c) {
        if (c == File.separatorChar) {
            c = IOUtils.DIR_SEPARATOR_UNIX;
        }
        String hexString = Integer.toHexString(c);
        int length = 5 - hexString.length();
        char[] cArr = new char[6];
        int i = 0;
        cArr[0] = '_';
        for (int i2 = 1; i2 <= length; i2++) {
            cArr[i2] = '0';
        }
        for (int i3 = length + 1; i3 < 6; i3++) {
            i++;
            cArr[i3] = hexString.charAt(i);
        }
        return new String(cArr);
    }
}
