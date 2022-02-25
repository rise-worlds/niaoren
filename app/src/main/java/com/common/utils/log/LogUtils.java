package com.common.utils.log;

import p110z1.Gson;

/* loaded from: classes.dex */
public class LogUtils {
    private static boolean mIsDebug = false;
    private static final LogUtilPrinter printer = new LogUtilPrinter();

    private LogUtils() {
    }

    public static Settings initParam(boolean z) {
        mIsDebug = z;
        return printer.getSettings();
    }

    /* renamed from: d */
    public static void m22038d(String str, Object obj) {
        if (mIsDebug) {
            printer.mo22044d(obj, str);
        }
    }

    /* renamed from: e */
    public static void m22036e(String str, Object obj) {
        if (mIsDebug) {
            printer.mo22043e(obj, str);
        }
    }

    /* renamed from: w */
    public static void m22030w(String str, Object obj) {
        if (mIsDebug) {
            printer.mo22040w(obj, str);
        }
    }

    /* renamed from: i */
    public static void m22034i(String str, Object obj) {
        if (mIsDebug) {
            printer.mo22042i(obj, str);
        }
    }

    /* renamed from: v */
    public static void m22032v(String str, Object obj) {
        if (mIsDebug) {
            printer.mo22041v(obj, str);
        }
    }

    public static void wtf(String str, Object obj) {
        if (mIsDebug) {
            printer.wtf(obj, str);
        }
    }

    public static void json(String str) {
        if (mIsDebug) {
            printer.json(str, "");
        }
    }

    public static void xml(String str) {
        if (mIsDebug) {
            printer.xml(str, "");
        }
    }

    public static void json(String str, String str2) {
        if (mIsDebug) {
            printer.json(str2, str);
        }
    }

    public static void xml(String str, String str2) {
        if (mIsDebug) {
            printer.xml(str2, str);
        }
    }

    public static void object(Object obj) {
        object("", obj);
    }

    public static void object(String str, Object obj) {
        if (obj != null) {
            String b = new Gson().m1575b(obj);
            String simpleName = obj.getClass().getSimpleName();
            json(str, simpleName + ":" + b);
        }
    }

    public static void printD(String str, Object obj) {
        if (mIsDebug) {
            printer.printD(obj, str);
        }
    }

    public static void printE(String str, Object obj) {
        if (mIsDebug) {
            printer.printE(obj, str);
        }
    }

    /* renamed from: d */
    public static void m22039d(Object obj) {
        if (mIsDebug) {
            printer.mo22044d(obj, "");
        }
    }

    /* renamed from: e */
    public static void m22037e(Object obj) {
        if (mIsDebug) {
            printer.mo22043e(obj, "");
        }
    }

    /* renamed from: w */
    public static void m22031w(Object obj) {
        if (mIsDebug) {
            printer.mo22040w(obj, "");
        }
    }

    /* renamed from: i */
    public static void m22035i(Object obj) {
        if (mIsDebug) {
            printer.mo22042i(obj, "");
        }
    }

    /* renamed from: v */
    public static void m22033v(Object obj) {
        if (mIsDebug) {
            printer.mo22041v(obj, "");
        }
    }

    public static void wtf(Object obj) {
        if (mIsDebug) {
            printer.wtf(obj, "");
        }
    }

    public static void printD(Object obj) {
        if (mIsDebug) {
            printer.printD(obj, "");
        }
    }

    public static void printE(Object obj) {
        if (mIsDebug) {
            printer.printE(obj, "");
        }
    }
}
