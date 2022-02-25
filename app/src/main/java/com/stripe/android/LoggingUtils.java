package com.stripe.android;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* renamed from: com.stripe.android.h */
/* loaded from: classes2.dex */
class LoggingUtils {

    /* renamed from: a */
    static final String f11870a = "unknown";

    /* renamed from: b */
    static final String f11871b = "no_context";

    /* renamed from: c */
    static final String f11872c = "token_creation";

    /* renamed from: d */
    static final String f11873d = "source_creation";

    /* renamed from: e */
    static final String f11874e = "add_source";

    /* renamed from: f */
    static final String f11875f = "default_source";

    /* renamed from: g */
    static final String f11876g = "delete_source";

    /* renamed from: h */
    static final String f11877h = "set_shipping_info";

    /* renamed from: i */
    static final String f11878i = "product_usage";

    /* renamed from: j */
    static final String f11879j = "analytics_ua";

    /* renamed from: k */
    static final String f11880k = "app_name";

    /* renamed from: l */
    static final String f11881l = "app_version";

    /* renamed from: m */
    static final String f11882m = "bindings_version";

    /* renamed from: n */
    static final String f11883n = "device_type";

    /* renamed from: o */
    static final String f11884o = "event";

    /* renamed from: p */
    static final String f11885p = "os_name";

    /* renamed from: q */
    static final String f11886q = "os_release";

    /* renamed from: r */
    static final String f11887r = "os_version";

    /* renamed from: s */
    static final String f11888s = "publishable_key";

    /* renamed from: t */
    static final String f11889t = "source_type";

    /* renamed from: u */
    static final String f11890u = "token_type";

    /* renamed from: v */
    static final Set<String> f11891v = new HashSet();

    /* renamed from: w */
    private static final String f11892w = "analytics";

    /* renamed from: x */
    private static final String f11893x = "stripe_android";

    /* renamed from: y */
    private static final String f11894y = "1.0";

    @NonNull
    /* renamed from: b */
    static String m18001b() {
        return "analytics.stripe_android-1.0";
    }

    LoggingUtils() {
    }

    static {
        f11891v.add(f11879j);
        f11891v.add(f11880k);
        f11891v.add(f11881l);
        f11891v.add(f11882m);
        f11891v.add(f11883n);
        f11891v.add("event");
        f11891v.add(f11887r);
        f11891v.add(f11885p);
        f11891v.add(f11886q);
        f11891v.add(f11878i);
        f11891v.add(f11888s);
        f11891v.add(f11889t);
        f11891v.add(f11890u);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    /* renamed from: a */
    public static Map<String, Object> m18005a(@NonNull Context context, @NonNull List<String> list, @NonNull String str, @Nullable String str2) {
        return m18004a(context, list, null, str2, str, f11872c);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    /* renamed from: b */
    public static Map<String, Object> m18000b(@NonNull Context context, @Nullable List<String> list, @NonNull String str, @NonNull String str2) {
        return m18004a(context, list, str2, null, str, f11873d);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    /* renamed from: c */
    public static Map<String, Object> m17999c(@NonNull Context context, @Nullable List<String> list, @NonNull String str, @NonNull String str2) {
        return m18004a(context, list, str2, null, str, f11874e);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    /* renamed from: a */
    public static Map<String, Object> m18006a(@NonNull Context context, @Nullable List<String> list, @NonNull String str) {
        return m18004a(context, list, null, null, str, f11876g);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    /* renamed from: a */
    public static Map<String, Object> m18004a(@NonNull Context context, @Nullable List<String> list, @Nullable String str, @Nullable String str2, @NonNull String str3, @NonNull String str4) {
        HashMap hashMap = new HashMap();
        hashMap.put(f11879j, m18001b());
        hashMap.put("event", m18003a(str4));
        hashMap.put(f11888s, str3);
        hashMap.put(f11885p, Build.VERSION.CODENAME);
        hashMap.put(f11886q, Build.VERSION.RELEASE);
        hashMap.put(f11887r, Integer.valueOf(Build.VERSION.SDK_INT));
        hashMap.put(f11883n, m18007a());
        hashMap.put(f11882m, C2368a.f11783f);
        m18002a(hashMap, context);
        if (list != null) {
            hashMap.put(f11878i, list);
        }
        if (str != null) {
            hashMap.put(f11889t, str);
        }
        if (str2 != null) {
            hashMap.put(f11890u, str2);
        } else if (str == null) {
            hashMap.put(f11890u, "unknown");
        }
        return hashMap;
    }

    /* renamed from: a */
    static void m18002a(@NonNull Map<String, Object> map, @NonNull Context context) {
        Context applicationContext = context.getApplicationContext();
        if (applicationContext == null || applicationContext.getPackageManager() == null) {
            map.put(f11880k, f11871b);
            map.put(f11881l, f11871b);
            return;
        }
        try {
            PackageInfo packageInfo = applicationContext.getPackageManager().getPackageInfo(applicationContext.getPackageName(), 0);
            String str = null;
            if (packageInfo.applicationInfo != null) {
                CharSequence loadLabel = packageInfo.applicationInfo.loadLabel(applicationContext.getPackageManager());
                if (loadLabel != null) {
                    str = loadLabel.toString();
                }
                map.put(f11880k, str);
            }
            if (StripeTextUtils.m17202b(str)) {
                map.put(f11880k, packageInfo.packageName);
            }
            map.put(f11881l, Integer.valueOf(packageInfo.versionCode));
        } catch (PackageManager.NameNotFoundException unused) {
            map.put(f11880k, "unknown");
            map.put(f11881l, "unknown");
        }
    }

    @NonNull
    /* renamed from: a */
    static String m18007a() {
        return Build.MANUFACTURER + '_' + Build.BRAND + '_' + Build.MODEL;
    }

    @NonNull
    /* renamed from: a */
    static String m18003a(@NonNull String str) {
        return "stripe_android." + str;
    }
}
