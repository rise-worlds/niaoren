package com.tendcloud.tenddata;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import com.cyjh.ddy.media.p035a.ResultTypeConstant;
import com.cyjh.mq.p049d.C1363e;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.ej */
/* loaded from: classes2.dex */
public final class C2845ej {

    /* renamed from: a */
    private static boolean f13882a = false;

    /* renamed from: b */
    private static boolean f13883b = false;

    /* renamed from: c */
    private static final String[] f13884c = {"com.noshufou.android.su", "com.noshufou.android.su.elite", "eu.chainfire.supersu", "com.koushikdutta.superuser", "com.thirdparty.superuser", "com.yellowes.su", "com.topjohnwu.magisk"};

    /* renamed from: d */
    private static final String[] f13885d = {"com.koushikdutta.rommanager", "com.koushikdutta.rommanager.license", "com.dimonvideo.luckypatcher", "com.chelpus.lackypatch", "com.ramdroid.appquarantine", "com.ramdroid.appquarantinepro", "com.android.vending.billing.InAppBillingService.COIN", "com.chelpus.luckypatcher"};

    /* renamed from: e */
    private static final String[] f13886e = {"/data/local/", "/data/local/bin/", "/data/local/xbin/", "/sbin/", "/su/bin/", "/system/bin/", "/system/bin/.ext/", "/system/bin/failsafe/", "/system/sd/xbin/", "/system/usr/we-need-root/", "/system/xbin/", "/cache", "/data", "/dev"};

    /* renamed from: f */
    private static final String[] f13887f = {"/system", "/system/bin", "/system/sbin", "/system/xbin", "/vendor/bin", "/sbin", "/etc"};

    /* renamed from: a */
    public static boolean m15833a(Context context) {
        boolean z;
        if (!f13882a) {
            if (!m15829b(context) && !m15827c(context) && !m15832a(C1363e.f8870a) && !m15832a("busybox") && !m15834a() && !m15830b() && !m15825e() && !m15826d() && !m15828c()) {
                z = false;
                f13883b = z;
                f13882a = true;
            }
            z = true;
            f13883b = z;
            f13882a = true;
        }
        return f13883b;
    }

    /* renamed from: b */
    private static boolean m15829b(Context context) {
        return m15831a(new ArrayList(Arrays.asList(f13884c)), context);
    }

    /* renamed from: a */
    private static boolean m15831a(List list, Context context) {
        PackageManager packageManager = context.getPackageManager();
        Iterator it = list.iterator();
        boolean z = false;
        while (it.hasNext()) {
            try {
                packageManager.getPackageInfo((String) it.next(), 0);
                z = true;
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
        return z;
    }

    /* renamed from: c */
    private static boolean m15827c(Context context) {
        return m15831a(new ArrayList(Arrays.asList(f13885d)), context);
    }

    /* renamed from: a */
    private static boolean m15832a(String str) {
        try {
            boolean z = false;
            for (String str2 : f13886e) {
                try {
                    try {
                        if (new File(str2, str).exists()) {
                            z = true;
                        }
                    } catch (Throwable unused) {
                    }
                } catch (Throwable unused2) {
                    return z;
                }
            }
            return z;
        } catch (Throwable unused3) {
            return false;
        }
    }

    /* renamed from: a */
    private static boolean m15834a() {
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("ro.debuggable", "1");
            hashMap.put("ro.secure", ResultTypeConstant.f7213z);
            String[] f = m15824f();
            if (f == null) {
                return false;
            }
            boolean z = false;
            for (String str : f) {
                try {
                    try {
                        for (String str2 : hashMap.keySet()) {
                            try {
                                if (str.contains(str2)) {
                                    if (str.contains("[" + ((String) hashMap.get(str2)) + "]")) {
                                        z = true;
                                    }
                                }
                            } catch (Throwable unused) {
                            }
                        }
                    } catch (Throwable unused2) {
                    }
                } catch (Throwable unused3) {
                    return z;
                }
            }
            return z;
        } catch (Throwable unused4) {
            return false;
        }
    }

    /* renamed from: b */
    private static boolean m15830b() {
        try {
            String[] g = m15823g();
            if (g == null) {
                return false;
            }
            boolean z = false;
            for (String str : g) {
                try {
                    try {
                        String[] split = str.split(ExpandableTextView.f6958c);
                        if (split.length >= 4) {
                            String str2 = split[1];
                            String str3 = split[3];
                            boolean z2 = z;
                            for (String str4 : f13887f) {
                                try {
                                    try {
                                        if (str2.equalsIgnoreCase(str4)) {
                                            String[] split2 = str3.split(",");
                                            int length = split2.length;
                                            int i = 0;
                                            while (true) {
                                                if (i < length) {
                                                    if (split2[i].equalsIgnoreCase("rw")) {
                                                        z2 = true;
                                                        break;
                                                    }
                                                    i++;
                                                }
                                            }
                                        }
                                    } catch (Throwable unused) {
                                    }
                                } catch (Throwable unused2) {
                                    z = z2;
                                }
                            }
                            z = z2;
                        }
                    } catch (Throwable unused3) {
                    }
                } catch (Throwable unused4) {
                    return z;
                }
            }
            return z;
        } catch (Throwable unused5) {
            return false;
        }
    }

    /* renamed from: c */
    private static boolean m15828c() {
        return m15832a("magisk");
    }

    /* renamed from: d */
    private static boolean m15826d() {
        boolean z = false;
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(new String[]{"which", C1363e.f8870a});
            if (new BufferedReader(new InputStreamReader(process.getInputStream())).readLine() != null) {
                z = true;
            }
            if (process != null) {
                process.destroy();
            }
            return z;
        } catch (Throwable unused) {
            if (process != null) {
                process.destroy();
            }
            return false;
        }
    }

    /* renamed from: e */
    private static boolean m15825e() {
        String str = Build.TAGS;
        return str != null && str.contains("test-keys");
    }

    /* renamed from: f */
    private static String[] m15824f() {
        try {
            InputStream inputStream = Runtime.getRuntime().exec("getprop").getInputStream();
            if (inputStream == null) {
                return null;
            }
            return new Scanner(inputStream).useDelimiter("\\A").next().split("\n");
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: g */
    private static String[] m15823g() {
        try {
            InputStream inputStream = Runtime.getRuntime().exec("mount").getInputStream();
            if (inputStream == null) {
                return null;
            }
            return new Scanner(inputStream).useDelimiter("\\A").next().split("\n");
        } catch (Throwable unused) {
            return null;
        }
    }
}
