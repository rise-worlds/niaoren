package com.tencent.smtt.sdk.p078a;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import com.cyjh.ddy.media.p035a.ResultTypeConstant;
import com.tencent.smtt.sdk.TbsConfig;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.utils.FileProvider;
import com.tendcloud.tenddata.C2663aa;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/* renamed from: com.tencent.smtt.sdk.a.c */
/* loaded from: classes2.dex */
public class MttLoader {

    /* compiled from: MttLoader.java */
    /* renamed from: com.tencent.smtt.sdk.a.c$a */
    /* loaded from: classes2.dex */
    public static class C2609a {

        /* renamed from: a */
        public int f13086a = -1;

        /* renamed from: b */
        public int f13087b = -1;

        /* renamed from: c */
        public String f13088c = "";

        /* renamed from: d */
        public String f13089d = ResultTypeConstant.f7213z;

        /* renamed from: e */
        public String f13090e = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: MttLoader.java */
    /* renamed from: com.tencent.smtt.sdk.a.c$b */
    /* loaded from: classes2.dex */
    public static class C2610b {

        /* renamed from: a */
        public String f13091a;

        /* renamed from: b */
        public String f13092b;

        private C2610b() {
            this.f13091a = "";
            this.f13092b = "";
        }
    }

    /* renamed from: a */
    public static boolean m16879a(Context context, String str, int i, String str2, HashMap<String, String> hashMap, Bundle bundle) {
        Set<String> keySet;
        try {
            Intent intent = new Intent("com.tencent.QQBrowser.action.sdk.document");
            if (!(hashMap == null || (keySet = hashMap.keySet()) == null)) {
                for (String str3 : keySet) {
                    String str4 = hashMap.get(str3);
                    if (!TextUtils.isEmpty(str4)) {
                        intent.putExtra(str3, str4);
                    }
                }
            }
            new File(str);
            intent.putExtra("key_reader_sdk_id", 3);
            intent.putExtra("key_reader_sdk_type", i);
            if (i == 0) {
                intent.putExtra("key_reader_sdk_path", str);
            } else if (i == 1) {
                intent.putExtra("key_reader_sdk_url", str);
            }
            intent.putExtra("key_reader_sdk_format", str2);
            if (context != null && context.getApplicationInfo().targetSdkVersion >= 24 && Build.VERSION.SDK_INT >= 24) {
                intent.addFlags(1);
            }
            Uri a = m16880a(context, str);
            if (a == null) {
                return false;
            }
            intent.setDataAndType(a, "mtt/" + str2);
            intent.putExtra("loginType", m16872d(context.getApplicationContext()));
            if (bundle != null) {
                intent.putExtra("key_reader_sdk_extrals", bundle);
            }
            context.startActivity(intent);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: a */
    private static Uri m16880a(Context context, String str) {
        return FileProvider.m16546a(context, str);
    }

    /* renamed from: a */
    public static boolean m16878a(Context context, String str, HashMap<String, String> hashMap) {
        boolean z;
        Set<String> keySet;
        Uri parse = Uri.parse(str);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setFlags(268435456);
        intent.setDataAndType(parse, "video/*");
        if (!(hashMap == null || (keySet = hashMap.keySet()) == null)) {
            for (String str2 : keySet) {
                String str3 = hashMap.get(str2);
                if (!TextUtils.isEmpty(str3)) {
                    intent.putExtra(str2, str3);
                }
            }
        }
        try {
            intent.putExtra("loginType", m16872d(context));
            intent.setComponent(new ComponentName(TbsConfig.APP_QB, "com.tencent.mtt.browser.video.H5VideoThrdcallActivity"));
            context.startActivity(intent);
            z = true;
        } catch (Throwable unused) {
            z = false;
        }
        if (!z) {
            try {
                intent.setComponent(null);
                context.startActivity(intent);
            } catch (Throwable th) {
                th.printStackTrace();
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(12:2|23|3|(3:7|8|(8:10|25|13|(1:15)|16|(1:18)(1:19)|20|21))|11|25|13|(0)|16|(0)(0)|20|21) */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0028  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x002f  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int m16876a(android.content.Context r4, java.lang.String r5, java.util.HashMap<java.lang.String, java.lang.String> r6, java.lang.String r7, com.tencent.smtt.sdk.WebView r8) {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r1 = 0
            android.content.pm.PackageManager r2 = r4.getPackageManager()     // Catch: Throwable -> 0x001f
            if (r2 == 0) goto L_0x001d
            java.lang.String r3 = "com.tencent.mtt"
            android.content.pm.PackageInfo r2 = r2.getPackageInfo(r3, r1)     // Catch: Throwable -> 0x001f
            if (r2 == 0) goto L_0x001d
            int r2 = r2.versionCode     // Catch: Throwable -> 0x001f
            r3 = 601000(0x92ba8, float:8.4218E-40)
            if (r2 <= r3) goto L_0x001d
            r2 = 1
            goto L_0x0020
        L_0x001d:
            r2 = 0
            goto L_0x0020
        L_0x001f:
            r2 = 0
        L_0x0020:
            java.lang.String r3 = "UTF-8"
            java.lang.String r1 = java.net.URLEncoder.encode(r5, r3)     // Catch: Exception -> 0x002a
            if (r2 == 0) goto L_0x0029
            r5 = r1
        L_0x0029:
            r1 = r2
        L_0x002a:
            if (r1 == 0) goto L_0x002f
            java.lang.String r1 = ",encoded=1"
            goto L_0x0031
        L_0x002f:
            java.lang.String r1 = ""
        L_0x0031:
            java.lang.String r2 = "mttbrowser://url="
            r0.append(r2)
            r0.append(r5)
            java.lang.String r5 = ",product="
            r0.append(r5)
            java.lang.String r5 = "TBS"
            r0.append(r5)
            java.lang.String r5 = ",packagename="
            r0.append(r5)
            java.lang.String r5 = r4.getPackageName()
            r0.append(r5)
            java.lang.String r5 = ",from="
            r0.append(r5)
            r0.append(r7)
            java.lang.String r5 = ",version="
            r0.append(r5)
            java.lang.String r5 = "4.3.0.1148"
            r0.append(r5)
            r0.append(r1)
            java.lang.String r5 = r0.toString()
            int r4 = m16877a(r4, r5, r6, r8)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.p078a.MttLoader.m16876a(android.content.Context, java.lang.String, java.util.HashMap, java.lang.String, com.tencent.smtt.sdk.WebView):int");
    }

    /* renamed from: a */
    public static int m16877a(Context context, String str, HashMap<String, String> hashMap, WebView webView) {
        Set<String> keySet;
        if (context == null) {
            return 3;
        }
        if (!m16875a(str)) {
            str = "http://" + str;
        }
        try {
            Uri parse = Uri.parse(str);
            if (parse == null) {
                return 2;
            }
            C2609a a = m16883a(context);
            if (a.f13086a == -1) {
                return 4;
            }
            if (a.f13086a == 2 && a.f13087b < 33) {
                return 5;
            }
            Intent intent = new Intent("android.intent.action.VIEW");
            if (a.f13086a == 2) {
                if (a.f13087b >= 33 && a.f13087b <= 39) {
                    intent.setClassName(TbsConfig.APP_QB, "com.tencent.mtt.MainActivity");
                } else if (a.f13087b >= 40 && a.f13087b <= 45) {
                    intent.setClassName(TbsConfig.APP_QB, "com.tencent.mtt.SplashActivity");
                } else if (a.f13087b >= 46) {
                    intent = new Intent("com.tencent.QQBrowser.action.VIEW");
                    C2610b a2 = m16881a(context, parse);
                    if (a2 != null && !TextUtils.isEmpty(a2.f13091a)) {
                        intent.setClassName(a2.f13092b, a2.f13091a);
                    }
                }
            } else if (a.f13086a == 1) {
                if (a.f13087b == 1) {
                    intent.setClassName("com.tencent.qbx5", "com.tencent.qbx5.MainActivity");
                } else if (a.f13087b == 2) {
                    intent.setClassName("com.tencent.qbx5", "com.tencent.qbx5.SplashActivity");
                }
            } else if (a.f13086a != 0) {
                intent = new Intent("com.tencent.QQBrowser.action.VIEW");
                C2610b a3 = m16881a(context, parse);
                if (a3 != null && !TextUtils.isEmpty(a3.f13091a)) {
                    intent.setClassName(a3.f13092b, a3.f13091a);
                }
            } else if (a.f13087b >= 4 && a.f13087b <= 6) {
                intent.setClassName("com.tencent.qbx", "com.tencent.qbx.SplashActivity");
            } else if (a.f13087b > 6) {
                intent = new Intent("com.tencent.QQBrowser.action.VIEW");
                C2610b a4 = m16881a(context, parse);
                if (a4 != null && !TextUtils.isEmpty(a4.f13091a)) {
                    intent.setClassName(a4.f13092b, a4.f13091a);
                }
            }
            intent.setData(parse);
            if (!(hashMap == null || (keySet = hashMap.keySet()) == null)) {
                for (String str2 : keySet) {
                    String str3 = hashMap.get(str2);
                    if (!TextUtils.isEmpty(str3)) {
                        intent.putExtra(str2, str3);
                    }
                }
            }
            try {
                intent.putExtra("loginType", m16872d(context));
                intent.addFlags(268435456);
                if (webView != null) {
                    intent.putExtra("AnchorPoint", new Point(webView.getScrollX(), webView.getScrollY()));
                    intent.putExtra("ContentSize", new Point(webView.getContentWidth(), webView.getContentHeight()));
                }
                context.startActivity(intent);
                return 0;
            } catch (ActivityNotFoundException unused) {
                return 4;
            }
        } catch (Exception unused2) {
            return 2;
        }
    }

    /* renamed from: d */
    private static int m16872d(Context context) {
        String str = context.getApplicationInfo().processName;
        if (str.equals(TbsConfig.APP_QQ)) {
            return 13;
        }
        if (str.equals(TbsConfig.APP_QZONE)) {
            return 14;
        }
        if (str.equals("com.tencent.WBlog")) {
            return 15;
        }
        return str.equals(TbsConfig.APP_WX) ? 24 : 26;
    }

    /* renamed from: a */
    private static C2610b m16881a(Context context, Uri uri) {
        Intent intent = new Intent("com.tencent.QQBrowser.action.VIEW");
        intent.setData(uri);
        List<ResolveInfo> queryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 0);
        if (queryIntentActivities.size() <= 0) {
            return null;
        }
        C2610b bVar = new C2610b();
        for (ResolveInfo resolveInfo : queryIntentActivities) {
            String str = resolveInfo.activityInfo.packageName;
            if (str.contains(TbsConfig.APP_QB)) {
                bVar.f13091a = resolveInfo.activityInfo.name;
                bVar.f13092b = resolveInfo.activityInfo.packageName;
                return bVar;
            } else if (str.contains("com.tencent.qbx")) {
                bVar.f13091a = resolveInfo.activityInfo.name;
                bVar.f13092b = resolveInfo.activityInfo.packageName;
            }
        }
        return bVar;
    }

    /* renamed from: a */
    public static C2609a m16883a(Context context) {
        boolean z = context.getApplicationContext().getSharedPreferences("x5_proxy_setting", 0).getBoolean("qb_install_status", false);
        C2609a aVar = new C2609a();
        if (z) {
            return aVar;
        }
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = null;
            try {
                packageInfo = packageManager.getPackageInfo(TbsConfig.APP_QB, 0);
                aVar.f13086a = 2;
                aVar.f13090e = TbsConfig.APP_QB;
                aVar.f13088c = "ADRQB_";
                if (packageInfo != null && packageInfo.versionCode > 420000) {
                    aVar.f13087b = packageInfo.versionCode;
                    aVar.f13088c += packageInfo.versionName.replaceAll("\\.", "");
                    aVar.f13089d = packageInfo.versionName.replaceAll("\\.", "");
                    return aVar;
                }
            } catch (PackageManager.NameNotFoundException unused) {
            }
            try {
                try {
                    try {
                        try {
                            try {
                                packageInfo = packageManager.getPackageInfo("com.tencent.qbx", 0);
                                aVar.f13086a = 0;
                                aVar.f13090e = "com.tencent.qbx";
                                aVar.f13088c = "ADRQBX_";
                            } catch (PackageManager.NameNotFoundException unused2) {
                                packageInfo = packageManager.getPackageInfo("com.tencent.mtt.x86", 0);
                                aVar.f13090e = "com.tencent.mtt.x86";
                                aVar.f13086a = 2;
                                aVar.f13088c = "ADRQB_";
                            }
                        } catch (PackageManager.NameNotFoundException unused3) {
                            packageInfo = packageManager.getPackageInfo(TbsConfig.APP_QB, 0);
                            aVar.f13090e = TbsConfig.APP_QB;
                            aVar.f13086a = 2;
                            aVar.f13088c = "ADRQB_";
                        }
                    } catch (PackageManager.NameNotFoundException unused4) {
                        packageInfo = packageManager.getPackageInfo("com.tencent.qbx5", 0);
                        aVar.f13086a = 1;
                        aVar.f13090e = "com.tencent.qbx5";
                        aVar.f13088c = "ADRQBX5_";
                    }
                } catch (Exception unused5) {
                    C2610b a = m16881a(context, Uri.parse("http://mdc.html5.qq.com/mh?channel_id=50079&u="));
                    if (a != null && !TextUtils.isEmpty(a.f13092b)) {
                        PackageInfo packageInfo2 = packageManager.getPackageInfo(a.f13092b, 0);
                        try {
                            aVar.f13090e = a.f13092b;
                            aVar.f13086a = 2;
                            aVar.f13088c = "ADRQB_";
                            packageInfo = packageInfo2;
                        } catch (Exception unused6) {
                            packageInfo = packageInfo2;
                        }
                    }
                }
            } catch (Exception unused7) {
            }
            if (packageInfo != null) {
                aVar.f13087b = packageInfo.versionCode;
                aVar.f13088c += packageInfo.versionName.replaceAll("\\.", "");
                aVar.f13089d = packageInfo.versionName.replaceAll("\\.", "");
            }
        } catch (Exception unused8) {
        }
        return aVar;
    }

    /* renamed from: a */
    private static boolean m16875a(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        String trim = str.trim();
        int indexOf = trim.toLowerCase().indexOf(C2663aa.f13457a);
        int indexOf2 = trim.toLowerCase().indexOf(46);
        if (indexOf <= 0 || indexOf2 <= 0 || indexOf <= indexOf2) {
            return trim.toLowerCase().contains(C2663aa.f13457a);
        }
        return false;
    }

    /* renamed from: b */
    public static boolean m16874b(Context context) {
        return m16883a(context).f13086a != -1;
    }

    /* renamed from: c */
    public static boolean m16873c(Context context) {
        C2609a a = m16883a(context);
        boolean z = false;
        try {
            if (Long.valueOf(a.f13089d).longValue() >= 6001500) {
                z = true;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if (a.f13087b >= 601500) {
            return true;
        }
        return z;
    }

    /* renamed from: a */
    public static boolean m16882a(Context context, long j, long j2) {
        C2609a a = m16883a(context);
        boolean z = false;
        try {
            if (Long.valueOf(a.f13089d).longValue() >= j) {
                z = true;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if (a.f13087b >= j2) {
            return true;
        }
        return z;
    }
}
