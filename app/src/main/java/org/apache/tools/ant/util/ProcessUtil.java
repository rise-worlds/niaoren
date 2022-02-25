package org.apache.tools.ant.util;

import java.lang.management.ManagementFactory;

/* loaded from: classes2.dex */
public class ProcessUtil {
    private ProcessUtil() {
    }

    public static String getProcessId(String str) {
        String name = ManagementFactory.getRuntimeMXBean().getName();
        int indexOf = name.indexOf(64);
        if (indexOf < 1) {
            return str;
        }
        try {
            return Long.toString(Long.parseLong(name.substring(0, indexOf)));
        } catch (NumberFormatException unused) {
            return str;
        }
    }

    public static void main(String[] strArr) {
        System.out.println(getProcessId("<PID>"));
        try {
            Thread.sleep(120000L);
        } catch (Exception unused) {
        }
    }
}
