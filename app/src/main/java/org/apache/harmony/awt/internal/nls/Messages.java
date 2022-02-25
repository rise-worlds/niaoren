package org.apache.harmony.awt.internal.nls;

import com.tencent.smtt.sdk.TbsListener;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/* loaded from: classes2.dex */
public class Messages {
    private static ResourceBundle bundle;

    public static String getString(String str) {
        ResourceBundle resourceBundle = bundle;
        if (resourceBundle == null) {
            return str;
        }
        try {
            return resourceBundle.getString(str);
        } catch (MissingResourceException unused) {
            return "Missing message: " + str;
        }
    }

    public static String getString(String str, Object obj) {
        return getString(str, new Object[]{obj});
    }

    public static String getString(String str, int i) {
        return getString(str, new Object[]{Integer.toString(i)});
    }

    public static String getString(String str, char c) {
        return getString(str, new Object[]{String.valueOf(c)});
    }

    public static String getString(String str, Object obj, Object obj2) {
        return getString(str, new Object[]{obj, obj2});
    }

    public static String getString(String str, Object[] objArr) {
        ResourceBundle resourceBundle = bundle;
        if (resourceBundle != null) {
            try {
                str = resourceBundle.getString(str);
            } catch (MissingResourceException unused) {
            }
        }
        return format(str, objArr);
    }

    public static String format(String str, Object[] objArr) {
        StringBuilder sb = new StringBuilder(str.length() + (objArr.length * 20));
        String[] strArr = new String[objArr.length];
        int i = 0;
        for (int i2 = 0; i2 < objArr.length; i2++) {
            if (objArr[i2] == null) {
                strArr[i2] = "<null>";
            } else {
                strArr[i2] = objArr[i2].toString();
            }
        }
        while (true) {
            int indexOf = str.indexOf(TbsListener.ErrorCode.DOWNLOAD_RETRYTIMES302_EXCEED, i);
            if (indexOf < 0) {
                break;
            }
            if (indexOf != 0) {
                int i3 = indexOf - 1;
                if (str.charAt(i3) == '\\') {
                    if (indexOf != 1) {
                        sb.append(str.substring(i, i3));
                    }
                    sb.append('{');
                    i = indexOf + 1;
                }
            }
            if (indexOf > str.length() - 3) {
                sb.append(str.substring(i, str.length()));
                i = str.length();
            } else {
                int i4 = indexOf + 1;
                byte digit = (byte) Character.digit(str.charAt(i4), 10);
                if (digit < 0 || str.charAt(indexOf + 2) != '}') {
                    sb.append(str.substring(i, i4));
                    i = i4;
                } else {
                    sb.append(str.substring(i, indexOf));
                    if (digit >= strArr.length) {
                        sb.append("<missing argument>");
                    } else {
                        sb.append(strArr[digit]);
                    }
                    i = indexOf + 3;
                }
            }
        }
        if (i < str.length()) {
            sb.append(str.substring(i, str.length()));
        }
        return sb.toString();
    }

    public static ResourceBundle setLocale(final Locale locale, final String str) {
        try {
            return (ResourceBundle) AccessController.doPrivileged(new PrivilegedAction<Object>() { // from class: org.apache.harmony.awt.internal.nls.Messages.1
                @Override // java.security.PrivilegedAction
                public Object run() {
                    String str2 = str;
                    Locale locale2 = locale;
                    ClassLoader classLoader = r3;
                    if (classLoader == null) {
                        classLoader = ClassLoader.getSystemClassLoader();
                    }
                    return ResourceBundle.getBundle(str2, locale2, classLoader);
                }
            });
        } catch (MissingResourceException unused) {
            return null;
        }
    }

    static {
        try {
            bundle = setLocale(Locale.getDefault(), "org.apache.harmony.awt.internal.nls.messages");
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
