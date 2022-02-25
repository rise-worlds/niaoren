package com.lidroid.xutils.util;

import android.text.TextUtils;
import android.util.Log;
import p110z1.Consts;

/* loaded from: classes.dex */
public class LogUtils {
    public static boolean allowD = true;
    public static boolean allowE = true;
    public static boolean allowI = true;
    public static boolean allowV = true;
    public static boolean allowW = true;
    public static boolean allowWtf = true;
    public static CustomLogger customLogger = null;
    public static String customTagPrefix = "";

    /* loaded from: classes.dex */
    public interface CustomLogger {
        /* renamed from: d */
        void m19211d(String str, String str2);

        /* renamed from: d */
        void m19210d(String str, String str2, Throwable th);

        /* renamed from: e */
        void m19209e(String str, String str2);

        /* renamed from: e */
        void m19208e(String str, String str2, Throwable th);

        /* renamed from: i */
        void m19207i(String str, String str2);

        /* renamed from: i */
        void m19206i(String str, String str2, Throwable th);

        /* renamed from: v */
        void m19205v(String str, String str2);

        /* renamed from: v */
        void m19204v(String str, String str2, Throwable th);

        /* renamed from: w */
        void m19203w(String str, String str2);

        /* renamed from: w */
        void m19202w(String str, String str2, Throwable th);

        /* renamed from: w */
        void m19201w(String str, Throwable th);

        void wtf(String str, String str2);

        void wtf(String str, String str2, Throwable th);

        void wtf(String str, Throwable th);
    }

    private LogUtils() {
    }

    private static String generateTag(StackTraceElement stackTraceElement) {
        String className = stackTraceElement.getClassName();
        String format = String.format("%s.%s(L:%d)", className.substring(className.lastIndexOf(Consts.f23430h) + 1), stackTraceElement.getMethodName(), Integer.valueOf(stackTraceElement.getLineNumber()));
        if (TextUtils.isEmpty(customTagPrefix)) {
            return format;
        }
        return String.valueOf(customTagPrefix) + ":" + format;
    }

    /* renamed from: d */
    public static void m19222d(String str) {
        if (allowD) {
            String generateTag = generateTag(OtherUtils.getCallerStackTraceElement());
            CustomLogger customLogger2 = customLogger;
            if (customLogger2 != null) {
                customLogger2.m19211d(generateTag, str);
            } else {
                Log.d(generateTag, str);
            }
        }
    }

    /* renamed from: d */
    public static void m19221d(String str, Throwable th) {
        if (allowD) {
            String generateTag = generateTag(OtherUtils.getCallerStackTraceElement());
            CustomLogger customLogger2 = customLogger;
            if (customLogger2 != null) {
                customLogger2.m19210d(generateTag, str, th);
            } else {
                Log.d(generateTag, str, th);
            }
        }
    }

    /* renamed from: e */
    public static void m19220e(String str) {
        if (allowE) {
            String generateTag = generateTag(OtherUtils.getCallerStackTraceElement());
            CustomLogger customLogger2 = customLogger;
            if (customLogger2 != null) {
                customLogger2.m19209e(generateTag, str);
            } else {
                Log.e(generateTag, str);
            }
        }
    }

    /* renamed from: e */
    public static void m19219e(String str, Throwable th) {
        if (allowE) {
            String generateTag = generateTag(OtherUtils.getCallerStackTraceElement());
            CustomLogger customLogger2 = customLogger;
            if (customLogger2 != null) {
                customLogger2.m19208e(generateTag, str, th);
            } else {
                Log.e(generateTag, str, th);
            }
        }
    }

    /* renamed from: i */
    public static void m19218i(String str) {
        if (allowI) {
            String generateTag = generateTag(OtherUtils.getCallerStackTraceElement());
            CustomLogger customLogger2 = customLogger;
            if (customLogger2 != null) {
                customLogger2.m19207i(generateTag, str);
            } else {
                Log.i(generateTag, str);
            }
        }
    }

    /* renamed from: i */
    public static void m19217i(String str, Throwable th) {
        if (allowI) {
            String generateTag = generateTag(OtherUtils.getCallerStackTraceElement());
            CustomLogger customLogger2 = customLogger;
            if (customLogger2 != null) {
                customLogger2.m19206i(generateTag, str, th);
            } else {
                Log.i(generateTag, str, th);
            }
        }
    }

    /* renamed from: v */
    public static void m19216v(String str) {
        if (allowV) {
            String generateTag = generateTag(OtherUtils.getCallerStackTraceElement());
            CustomLogger customLogger2 = customLogger;
            if (customLogger2 != null) {
                customLogger2.m19205v(generateTag, str);
            } else {
                Log.v(generateTag, str);
            }
        }
    }

    /* renamed from: v */
    public static void m19215v(String str, Throwable th) {
        if (allowV) {
            String generateTag = generateTag(OtherUtils.getCallerStackTraceElement());
            CustomLogger customLogger2 = customLogger;
            if (customLogger2 != null) {
                customLogger2.m19204v(generateTag, str, th);
            } else {
                Log.v(generateTag, str, th);
            }
        }
    }

    /* renamed from: w */
    public static void m19214w(String str) {
        if (allowW) {
            String generateTag = generateTag(OtherUtils.getCallerStackTraceElement());
            CustomLogger customLogger2 = customLogger;
            if (customLogger2 != null) {
                customLogger2.m19203w(generateTag, str);
            } else {
                Log.w(generateTag, str);
            }
        }
    }

    /* renamed from: w */
    public static void m19213w(String str, Throwable th) {
        if (allowW) {
            String generateTag = generateTag(OtherUtils.getCallerStackTraceElement());
            CustomLogger customLogger2 = customLogger;
            if (customLogger2 != null) {
                customLogger2.m19202w(generateTag, str, th);
            } else {
                Log.w(generateTag, str, th);
            }
        }
    }

    /* renamed from: w */
    public static void m19212w(Throwable th) {
        if (allowW) {
            String generateTag = generateTag(OtherUtils.getCallerStackTraceElement());
            CustomLogger customLogger2 = customLogger;
            if (customLogger2 != null) {
                customLogger2.m19201w(generateTag, th);
            } else {
                Log.w(generateTag, th);
            }
        }
    }

    public static void wtf(String str) {
        if (allowWtf) {
            String generateTag = generateTag(OtherUtils.getCallerStackTraceElement());
            CustomLogger customLogger2 = customLogger;
            if (customLogger2 != null) {
                customLogger2.wtf(generateTag, str);
            } else {
                Log.wtf(generateTag, str);
            }
        }
    }

    public static void wtf(String str, Throwable th) {
        if (allowWtf) {
            String generateTag = generateTag(OtherUtils.getCallerStackTraceElement());
            CustomLogger customLogger2 = customLogger;
            if (customLogger2 != null) {
                customLogger2.wtf(generateTag, str, th);
            } else {
                Log.wtf(generateTag, str, th);
            }
        }
    }

    public static void wtf(Throwable th) {
        if (allowWtf) {
            String generateTag = generateTag(OtherUtils.getCallerStackTraceElement());
            CustomLogger customLogger2 = customLogger;
            if (customLogger2 != null) {
                customLogger2.wtf(generateTag, th);
            } else {
                Log.wtf(generateTag, th);
            }
        }
    }
}
