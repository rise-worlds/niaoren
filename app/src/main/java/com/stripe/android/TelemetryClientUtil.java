package com.stripe.android;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/* renamed from: com.stripe.android.x */
/* loaded from: classes2.dex */
class TelemetryClientUtil {
    TelemetryClientUtil() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static Map<String, Object> m17198a(@NonNull Context context) {
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        hashMap.put("v2", 1);
        hashMap.put("tag", C2368a.f11783f);
        hashMap.put("src", "android-sdk");
        hashMap2.put("c", m17197a(Locale.getDefault().toString()));
        hashMap2.put("d", m17197a(m17196b()));
        hashMap2.put("f", m17197a(m17194c(context)));
        hashMap2.put("g", m17197a(m17199a()));
        hashMap.put("a", hashMap2);
        hashMap3.put("d", m17193d(context));
        String e = m17192e(context);
        hashMap3.put("k", e);
        hashMap3.put("o", Build.VERSION.RELEASE);
        hashMap3.put("p", Integer.valueOf(Build.VERSION.SDK_INT));
        hashMap3.put("q", Build.MANUFACTURER);
        hashMap3.put("r", Build.BRAND);
        hashMap3.put("s", Build.MODEL);
        hashMap3.put("t", Build.TAGS);
        if (context.getPackageName() != null) {
            try {
                hashMap3.put("l", context.getPackageManager().getPackageInfo(e, 0).versionName);
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
        hashMap.put("b", hashMap3);
        return hashMap;
    }

    @NonNull
    /* renamed from: a */
    private static Map<String, Object> m17197a(Object obj) {
        HashMap hashMap = new HashMap();
        hashMap.put("v", obj);
        return hashMap;
    }

    @NonNull
    /* renamed from: a */
    private static String m17199a() {
        int convert = (int) TimeUnit.MINUTES.convert(TimeZone.getDefault().getRawOffset(), TimeUnit.MILLISECONDS);
        if (convert % 60 == 0) {
            return String.valueOf(convert / 60);
        }
        return new BigDecimal(convert).setScale(2, 6).divide(new BigDecimal(60), new MathContext(2)).setScale(2, 6).toString();
    }

    @NonNull
    /* renamed from: c */
    private static String m17194c(@NonNull Context context) {
        if (context.getResources() == null) {
            return "";
        }
        return String.format(Locale.ENGLISH, "%dw_%dh_%ddpi", Integer.valueOf(context.getResources().getDisplayMetrics().widthPixels), Integer.valueOf(context.getResources().getDisplayMetrics().heightPixels), Integer.valueOf(context.getResources().getDisplayMetrics().densityDpi));
    }

    @NonNull
    /* renamed from: b */
    private static String m17196b() {
        return "Android" + ExpandableTextView.f6958c + Build.VERSION.RELEASE + ExpandableTextView.f6958c + Build.VERSION.CODENAME + ExpandableTextView.f6958c + Build.VERSION.SDK_INT;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    /* renamed from: b */
    public static String m17195b(@NonNull Context context) {
        String d;
        String string = Settings.Secure.getString(context.getContentResolver(), "android_id");
        return (!StripeTextUtils.m17202b(string) && (d = StripeTextUtils.m17200d(string)) != null) ? d : "";
    }

    @NonNull
    /* renamed from: d */
    private static String m17193d(@NonNull Context context) {
        String b = m17195b(context);
        String e = m17192e(context);
        String d = StripeTextUtils.m17200d(e + b);
        return d == null ? "" : d;
    }

    @NonNull
    /* renamed from: e */
    private static String m17192e(@NonNull Context context) {
        return (context.getApplicationContext() == null || context.getApplicationContext().getPackageName() == null) ? "" : context.getApplicationContext().getPackageName();
    }
}
