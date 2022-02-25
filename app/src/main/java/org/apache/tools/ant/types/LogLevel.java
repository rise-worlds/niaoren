package org.apache.tools.ant.types;

import com.stripe.android.PaymentResultListener;

/* loaded from: classes2.dex */
public class LogLevel extends EnumeratedAttribute {
    public static final LogLevel ERR = new LogLevel(PaymentResultListener.f11903c);
    public static final LogLevel WARN = new LogLevel("warn");
    public static final LogLevel INFO = new LogLevel("info");
    public static final LogLevel VERBOSE = new LogLevel("verbose");
    public static final LogLevel DEBUG = new LogLevel("debug");
    private static int[] levels = {0, 1, 1, 2, 3, 4};

    public LogLevel() {
    }

    private LogLevel(String str) {
        this();
        setValue(str);
    }

    @Override // org.apache.tools.ant.types.EnumeratedAttribute
    public String[] getValues() {
        return new String[]{PaymentResultListener.f11903c, "warn", "warning", "info", "verbose", "debug"};
    }

    public int getLevel() {
        return levels[getIndex()];
    }
}
