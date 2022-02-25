package com.nrzs.core.models;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import com.lody.virtual.remote.InstalledAppInfo;

/* renamed from: com.nrzs.core.models.d */
/* loaded from: classes2.dex */
public class PackageAppData implements AppData {

    /* renamed from: a */
    public String f10570a;

    /* renamed from: b */
    public String f10571b;

    /* renamed from: c */
    public Drawable f10572c;

    /* renamed from: d */
    public boolean f10573d;

    /* renamed from: e */
    public boolean f10574e;

    /* renamed from: f */
    public boolean f10575f;

    /* renamed from: g */
    public long f10576g;

    /* renamed from: h */
    public GameInfo f10577h;

    /* renamed from: i */
    public boolean f10578i;

    /* renamed from: j */
    public String f10579j;

    /* renamed from: k */
    public boolean f10580k;

    /* renamed from: l */
    public int f10581l;

    @Override // com.nrzs.core.models.AppData
    /* renamed from: f */
    public boolean mo18957f() {
        return true;
    }

    @Override // com.nrzs.core.models.AppData
    /* renamed from: g */
    public boolean mo18956g() {
        return true;
    }

    @Override // com.nrzs.core.models.AppData
    /* renamed from: h */
    public boolean mo18955h() {
        return true;
    }

    @Override // com.nrzs.core.models.AppData
    /* renamed from: i */
    public boolean mo18954i() {
        return true;
    }

    public PackageAppData(Context context, InstalledAppInfo installedAppInfo) {
        this.f10570a = installedAppInfo.packageName;
        this.f10574e = !installedAppInfo.isLaunched(0);
        m18966a(context, installedAppInfo.getApplicationInfo(installedAppInfo.getInstalledUsers()[0]));
    }

    /* renamed from: a */
    private void m18966a(Context context, ApplicationInfo applicationInfo) {
        if (applicationInfo != null) {
            PackageManager packageManager = context.getPackageManager();
            try {
                CharSequence loadLabel = applicationInfo.loadLabel(packageManager);
                if (loadLabel != null) {
                    this.f10571b = loadLabel.toString();
                }
                this.f10572c = applicationInfo.loadIcon(packageManager);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    @Override // com.nrzs.core.models.AppData
    /* renamed from: a */
    public boolean mo18967a() {
        return this.f10575f;
    }

    @Override // com.nrzs.core.models.AppData
    /* renamed from: b */
    public boolean mo18962b() {
        return this.f10574e;
    }

    @Override // com.nrzs.core.models.AppData
    /* renamed from: c */
    public Drawable mo18960c() {
        return this.f10572c;
    }

    @Override // com.nrzs.core.models.AppData
    /* renamed from: d */
    public String mo18959d() {
        return this.f10571b;
    }

    @Override // com.nrzs.core.models.AppData
    /* renamed from: e */
    public String mo18958e() {
        return this.f10570a;
    }

    @Override // com.nrzs.core.models.AppData
    /* renamed from: j */
    public long mo18953j() {
        return this.f10576g;
    }

    @Override // com.nrzs.core.models.AppData
    /* renamed from: k */
    public GameInfo mo18952k() {
        return this.f10577h;
    }

    @Override // com.nrzs.core.models.AppData
    /* renamed from: a */
    public void mo18965a(GameInfo gameInfo) {
        this.f10577h = gameInfo;
    }

    @Override // com.nrzs.core.models.AppData
    /* renamed from: m */
    public boolean mo18950m() {
        return this.f10578i;
    }

    @Override // com.nrzs.core.models.AppData
    /* renamed from: a */
    public void mo18964a(String str) {
        this.f10579j = str;
    }

    @Override // com.nrzs.core.models.AppData
    /* renamed from: a */
    public void mo18963a(boolean z) {
        this.f10578i = z;
    }

    @Override // com.nrzs.core.models.AppData
    /* renamed from: l */
    public String mo18951l() {
        return this.f10579j;
    }

    @Override // com.nrzs.core.models.AppData
    /* renamed from: n */
    public boolean mo18949n() {
        return this.f10580k;
    }

    @Override // com.nrzs.core.models.AppData
    /* renamed from: b */
    public void mo18961b(boolean z) {
        this.f10580k = z;
    }

    @Override // com.nrzs.core.models.AppData
    /* renamed from: o */
    public int mo18948o() {
        return this.f10581l;
    }
}
