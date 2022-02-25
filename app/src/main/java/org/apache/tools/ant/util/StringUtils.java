package org.apache.tools.ant.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Vector;
import org.apache.commons.p105io.IOUtils;
import org.apache.tools.ant.BuildException;

/* loaded from: classes2.dex */
public final class StringUtils {
    private static final long GIGABYTE = 1073741824;
    private static final long KILOBYTE = 1024;
    public static final String LINE_SEP = System.getProperty("line.separator");
    private static final long MEGABYTE = 1048576;
    private static final long PETABYTE = 1125899906842624L;
    private static final long TERABYTE = 1099511627776L;

    private StringUtils() {
    }

    public static Vector<String> lineSplit(String str) {
        return split(str, 10);
    }

    public static Vector<String> split(String str, int i) {
        Vector<String> vector = new Vector<>();
        int i2 = 0;
        while (true) {
            int indexOf = str.indexOf(i, i2);
            if (indexOf != -1) {
                vector.addElement(str.substring(i2, indexOf));
                i2 = indexOf + 1;
            } else {
                vector.addElement(str.substring(i2));
                return vector;
            }
        }
    }

    public static String replace(String str, String str2, String str3) {
        return str.replace(str2, str3);
    }

    public static String getStackTrace(Throwable th) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter((Writer) stringWriter, true);
        th.printStackTrace(printWriter);
        printWriter.flush();
        printWriter.close();
        return stringWriter.toString();
    }

    public static boolean endsWith(StringBuffer stringBuffer, String str) {
        if (str.length() > stringBuffer.length()) {
            return false;
        }
        int length = stringBuffer.length() - 1;
        for (int length2 = str.length() - 1; length2 >= 0; length2--) {
            if (stringBuffer.charAt(length) != str.charAt(length2)) {
                return false;
            }
            length--;
        }
        return true;
    }

    public static String resolveBackSlash(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        boolean z = false;
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (z) {
                if (charAt == '\\') {
                    stringBuffer.append(IOUtils.DIR_SEPARATOR_WINDOWS);
                } else if (charAt == 'f') {
                    stringBuffer.append('\f');
                } else if (charAt != 'n') {
                    switch (charAt) {
                        case 'r':
                            stringBuffer.append('\r');
                            break;
                        case 's':
                            stringBuffer.append(" \t\n\r\f");
                            break;
                        case 't':
                            stringBuffer.append('\t');
                            break;
                        default:
                            stringBuffer.append(charAt);
                            break;
                    }
                } else {
                    stringBuffer.append('\n');
                }
                z = false;
            } else if (charAt == '\\') {
                z = true;
            } else {
                stringBuffer.append(charAt);
            }
        }
        return stringBuffer.toString();
    }

    public static long parseHumanSizes(String str) throws Exception {
        char charAt = str.charAt(0);
        int i = 1;
        long j = 1;
        if (charAt == '+') {
            str = str.substring(1);
        } else if (charAt == '-') {
            j = -1;
            str = str.substring(1);
        }
        char charAt2 = str.charAt(str.length() - 1);
        if (!Character.isDigit(charAt2)) {
            if (charAt2 == 'G') {
                j *= 1073741824;
            } else if (charAt2 == 'K') {
                j *= 1024;
            } else if (charAt2 == 'M') {
                j *= 1048576;
            } else if (charAt2 == 'P') {
                j *= 1125899906842624L;
            } else if (charAt2 != 'T') {
                i = 0;
            } else {
                j *= 1099511627776L;
            }
            str = str.substring(0, str.length() - i);
        }
        try {
            return j * Long.parseLong(str);
        } catch (NumberFormatException e) {
            throw new BuildException("Failed to parse \"" + str + "\"", e);
        }
    }

    public static String removeSuffix(String str, String str2) {
        return str.endsWith(str2) ? str.substring(0, str.length() - str2.length()) : str;
    }

    public static String removePrefix(String str, String str2) {
        return str.startsWith(str2) ? str.substring(str2.length()) : str;
    }
}
