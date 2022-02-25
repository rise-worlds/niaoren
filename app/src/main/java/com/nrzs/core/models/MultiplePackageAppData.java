package com.nrzs.core.models;

import android.graphics.drawable.Drawable;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.remote.InstalledAppInfo;

/* renamed from: com.nrzs.core.models.c */
/* loaded from: classes2.dex */
public class MultiplePackageAppData implements AppData {

    /* renamed from: a */
    public InstalledAppInfo f10558a;

    /* renamed from: b */
    public int f10559b;

    /* renamed from: c */
    public boolean f10560c;

    /* renamed from: d */
    public boolean f10561d;

    /* renamed from: e */
    public Drawable f10562e;

    /* renamed from: f */
    public String f10563f;

    /* renamed from: g */
    public long f10564g;

    /* renamed from: h */
    public GameInfo f10565h;

    /* renamed from: i */
    public boolean f10566i;

    /* renamed from: j */
    public String f10567j;

    /* renamed from: k */
    public boolean f10568k;

    /* renamed from: l */
    public int f10569l;

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

    public MultiplePackageAppData(PackageAppData dVar, int i) {
        Drawable.ConstantState constantState;
        this.f10559b = i;
        this.f10558a = VirtualCore.get().getInstalledAppInfo(dVar.f10570a, 0);
        this.f10560c = !this.f10558a.isLaunched(i);
        if (!(dVar.f10572c == null || (constantState = dVar.f10572c.getConstantState()) == null)) {
            this.f10562e = constantState.newDrawable();
        }
        this.f10563f = dVar.f10571b;
        this.f10564g = dVar.f10576g;
    }

    @Override // com.nrzs.core.models.AppData
    /* renamed from: a */
    public boolean mo18967a() {
        return this.f10561d;
    }

    @Override // com.nrzs.core.models.AppData
    /* renamed from: b */
    public boolean mo18962b() {
        return this.f10560c;
    }

    @Override // com.nrzs.core.models.AppData
    /* renamed from: c */
    public Drawable mo18960c() {
        return this.f10562e;
    }

    @Override // com.nrzs.core.models.AppData
    /* renamed from: d */
    public String mo18959d() {
        return this.f10563f;
    }

    @Override // com.nrzs.core.models.AppData
    /* renamed from: e */
    public String mo18958e() {
        return this.f10558a.packageName;
    }

    @Override // com.nrzs.core.models.AppData
    /* renamed from: j */
    public long mo18953j() {
        return this.f10564g;
    }

    @Override // com.nrzs.core.models.AppData
    /* renamed from: k */
    public GameInfo mo18952k() {
        return this.f10565h;
    }

    @Override // com.nrzs.core.models.AppData
    /* renamed from: l */
    public String mo18951l() {
        return this.f10567j;
    }

    @Override // com.nrzs.core.models.AppData
    /* renamed from: a */
    public void mo18965a(GameInfo gameInfo) {
        this.f10565h = gameInfo;
    }

    @Override // com.nrzs.core.models.AppData
    /* renamed from: m */
    public boolean mo18950m() {
        return this.f10566i;
    }

    @Override // com.nrzs.core.models.AppData
    /* renamed from: a */
    public void mo18964a(String str) {
        this.f10567j = str;
    }

    @Override // com.nrzs.core.models.AppData
    /* renamed from: a */
    public void mo18963a(boolean z) {
        this.f10566i = z;
    }

    @Override // com.nrzs.core.models.AppData
    /* renamed from: n */
    public boolean mo18949n() {
        return this.f10568k;
    }

    @Override // com.nrzs.core.models.AppData
    /* renamed from: b */
    public void mo18961b(boolean z) {
        this.f10568k = z;
    }

    @Override // com.nrzs.core.models.AppData
    /* renamed from: o */
    public int mo18948o() {
        return this.f10569l;
    }
}
