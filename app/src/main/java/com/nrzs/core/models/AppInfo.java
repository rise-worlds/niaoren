package com.nrzs.core.models;

import android.graphics.drawable.Drawable;

/* renamed from: com.nrzs.core.models.b */
/* loaded from: classes2.dex */
public class AppInfo {

    /* renamed from: a */
    public String f10550a;

    /* renamed from: b */
    public String f10551b;

    /* renamed from: c */
    public boolean f10552c;

    /* renamed from: d */
    public Drawable f10553d;

    /* renamed from: e */
    public CharSequence f10554e;

    /* renamed from: f */
    public int f10555f;

    /* renamed from: g */
    public GameInfo f10556g;

    /* renamed from: h */
    public boolean f10557h;

    /* renamed from: a */
    public void m18968a(GameInfo gameInfo) {
        this.f10556g = gameInfo;
    }

    public boolean equals(Object obj) {
        return ((AppInfo) obj).f10550a.equals(this.f10550a) || super.equals(obj);
    }

    public String toString() {
        return "AppInfo{packageName='" + this.f10550a + "', path='" + this.f10551b + "', fastOpen=" + this.f10552c + ", icon=" + this.f10553d + ", name=" + ((Object) this.f10554e) + ", cloneCount=" + this.f10555f + ", gameInfo=" + this.f10556g + ", isLocalInstall=" + this.f10557h + '}';
    }
}
