package com.blankj.utilcode.util;

import android.support.annotation.NonNull;
import com.blankj.utilcode.util.Utils;
import java.util.List;

/* renamed from: com.blankj.utilcode.util.av */
/* loaded from: classes.dex */
public final class ShellUtils {

    /* renamed from: a */
    private static final String f6744a = System.getProperty("line.separator");

    private ShellUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /* renamed from: a */
    public static Utils.AbstractRunnableC0956e<C0985a> m23275a(String str, boolean z, Utils.AbstractC0953b<C0985a> bVar) {
        return m23265a(new String[]{str}, z, true, bVar);
    }

    /* renamed from: a */
    public static Utils.AbstractRunnableC0956e<C0985a> m23271a(List<String> list, boolean z, Utils.AbstractC0953b<C0985a> bVar) {
        return m23265a(list == null ? null : (String[]) list.toArray(new String[0]), z, true, bVar);
    }

    /* renamed from: a */
    public static Utils.AbstractRunnableC0956e<C0985a> m23267a(String[] strArr, boolean z, Utils.AbstractC0953b<C0985a> bVar) {
        return m23265a(strArr, z, true, bVar);
    }

    /* renamed from: a */
    public static Utils.AbstractRunnableC0956e<C0985a> m23273a(String str, boolean z, boolean z2, Utils.AbstractC0953b<C0985a> bVar) {
        return m23265a(new String[]{str}, z, z2, bVar);
    }

    /* renamed from: a */
    public static Utils.AbstractRunnableC0956e<C0985a> m23269a(List<String> list, boolean z, boolean z2, Utils.AbstractC0953b<C0985a> bVar) {
        return m23265a(list == null ? null : (String[]) list.toArray(new String[0]), z, z2, bVar);
    }

    /* renamed from: a */
    public static Utils.AbstractRunnableC0956e<C0985a> m23265a(final String[] strArr, final boolean z, final boolean z2, @NonNull Utils.AbstractC0953b<C0985a> bVar) {
        if (bVar != null) {
            return Utils.m24100a((Utils.AbstractRunnableC0956e) new Utils.AbstractRunnableC0956e<C0985a>(bVar) { // from class: com.blankj.utilcode.util.av.1
                /* renamed from: a */
                public C0985a mo23263b() {
                    return ShellUtils.m23266a(strArr, z, z2);
                }
            });
        }
        throw new NullPointerException("Argument 'callback' of type Utils.Callback<CommandResult> (#3 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* renamed from: a */
    public static C0985a m23276a(String str, boolean z) {
        return m23266a(new String[]{str}, z, true);
    }

    /* renamed from: a */
    public static C0985a m23272a(List<String> list, boolean z) {
        return m23266a(list == null ? null : (String[]) list.toArray(new String[0]), z, true);
    }

    /* renamed from: a */
    public static C0985a m23268a(String[] strArr, boolean z) {
        return m23266a(strArr, z, true);
    }

    /* renamed from: a */
    public static C0985a m23274a(String str, boolean z, boolean z2) {
        return m23266a(new String[]{str}, z, z2);
    }

    /* renamed from: a */
    public static C0985a m23270a(List<String> list, boolean z, boolean z2) {
        return m23266a(list == null ? null : (String[]) list.toArray(new String[0]), z, z2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:100:0x0154  */
    /* JADX WARN: Removed duplicated region for block: B:101:0x0157  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0182  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x016e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:131:0x012e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:137:0x0164 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:139:0x0124 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:144:0x0178 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:146:0x0138 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0142  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x014b  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x014e  */
    /* JADX WARN: Type inference failed for: r4v1, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.blankj.utilcode.util.ShellUtils.C0985a m23266a(java.lang.String[] r9, boolean r10, boolean r11) {
        /*
            Method dump skipped, instructions count: 400
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.blankj.utilcode.util.ShellUtils.m23266a(java.lang.String[], boolean, boolean):com.blankj.utilcode.util.av$a");
    }

    /* compiled from: ShellUtils.java */
    /* renamed from: com.blankj.utilcode.util.av$a */
    /* loaded from: classes.dex */
    public static class C0985a {

        /* renamed from: a */
        public int f6748a;

        /* renamed from: b */
        public String f6749b;

        /* renamed from: c */
        public String f6750c;

        public C0985a(int i, String str, String str2) {
            this.f6748a = i;
            this.f6749b = str;
            this.f6750c = str2;
        }

        public String toString() {
            return "result: " + this.f6748a + "\nsuccessMsg: " + this.f6749b + "\nerrorMsg: " + this.f6750c;
        }
    }
}
