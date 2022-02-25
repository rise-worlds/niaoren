package org.apache.tools.ant.util;

/* loaded from: classes2.dex */
public class UnicodeUtil {
    private UnicodeUtil() {
    }

    public static StringBuffer EscapeUnicode(char c) {
        StringBuffer stringBuffer = new StringBuffer("u0000");
        String hexString = Integer.toHexString(c);
        for (int i = 0; i < hexString.length(); i++) {
            stringBuffer.setCharAt((stringBuffer.length() - hexString.length()) + i, hexString.charAt(i));
        }
        return stringBuffer;
    }
}
