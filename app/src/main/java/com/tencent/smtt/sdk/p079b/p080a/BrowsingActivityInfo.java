package com.tencent.smtt.sdk.p079b.p080a;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.util.TypedValue;
import com.stripe.android.PaymentResultListener;
import com.tencent.smtt.sdk.TbsConfig;
import java.io.BufferedInputStream;
import java.util.Map;

/* renamed from: com.tencent.smtt.sdk.b.a.b */
/* loaded from: classes2.dex */
public class BrowsingActivityInfo {

    /* renamed from: a */
    private Context f13109a;

    /* renamed from: b */
    private ResolveInfo f13110b;

    /* renamed from: c */
    private Drawable f13111c;

    /* renamed from: d */
    private String f13112d;

    /* renamed from: e */
    private String f13113e;

    /* renamed from: f */
    private int f13114f;

    /* renamed from: a */
    public Drawable m16853a(Map<String, Drawable> map) {
        Drawable a = m16855a(this.f13109a, m16851c());
        if (a != null) {
            return a;
        }
        ResolveInfo resolveInfo = this.f13110b;
        if (resolveInfo != null) {
            return resolveInfo.loadIcon(this.f13109a.getPackageManager());
        }
        return this.f13111c;
    }

    /* renamed from: a */
    public String m16856a() {
        ResolveInfo resolveInfo = this.f13110b;
        if (resolveInfo != null) {
            return resolveInfo.loadLabel(this.f13109a.getPackageManager()).toString();
        }
        return this.f13112d;
    }

    /* renamed from: b */
    public ResolveInfo m16852b() {
        return this.f13110b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BrowsingActivityInfo(Context context, ResolveInfo resolveInfo) {
        this.f13114f = 0;
        this.f13109a = context.getApplicationContext();
        this.f13110b = resolveInfo;
        this.f13111c = null;
        this.f13112d = null;
        this.f13113e = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BrowsingActivityInfo(Context context, Drawable drawable, String str, String str2) {
        this.f13114f = 0;
        this.f13109a = context.getApplicationContext();
        this.f13110b = null;
        this.f13111c = drawable;
        this.f13112d = str;
        this.f13113e = str2;
    }

    /* renamed from: c */
    public String m16851c() {
        ResolveInfo resolveInfo = this.f13110b;
        if (resolveInfo != null) {
            return resolveInfo.activityInfo.packageName;
        }
        String str = this.f13113e;
        return str == null ? "" : str;
    }

    /* renamed from: a */
    public void m16854a(ResolveInfo resolveInfo) {
        this.f13110b = resolveInfo;
    }

    /* renamed from: a */
    public static Drawable m16855a(Context context, String str) {
        if (TbsConfig.APP_QB.equals(str)) {
            try {
                return TBSResources.m16839a("application_icon");
            } catch (Throwable th) {
                Log.e(PaymentResultListener.f11903c, "getApkIcon Error:" + Log.getStackTraceString(th));
                return null;
            }
        } else {
            PackageManager packageManager = context.getApplicationContext().getPackageManager();
            try {
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(str, 128);
                if (applicationInfo == null) {
                    return null;
                }
                Resources resourcesForApplication = packageManager.getResourcesForApplication(applicationInfo);
                TypedValue typedValue = new TypedValue();
                resourcesForApplication.getValue(applicationInfo.icon, typedValue, true);
                try {
                    return Drawable.createFromResourceStream(resourcesForApplication, typedValue, new BufferedInputStream(resourcesForApplication.getAssets().openNonAssetFd(typedValue.assetCookie, typedValue.string.toString()).createInputStream()), null);
                } catch (Exception unused) {
                    return null;
                }
            } catch (Exception e) {
                Log.e("sdk", "e = " + e);
                return null;
            }
        }
    }
}
