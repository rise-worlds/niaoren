package org.greenrobot.eventbus.util;

import android.content.res.Resources;
import android.util.Log;
import p110z1.EventBus;

/* renamed from: org.greenrobot.eventbus.util.b */
/* loaded from: classes2.dex */
public class ErrorDialogConfig {

    /* renamed from: a */
    final Resources f14817a;

    /* renamed from: b */
    final int f14818b;

    /* renamed from: c */
    final int f14819c;

    /* renamed from: e */
    EventBus f14821e;

    /* renamed from: g */
    String f14823g;

    /* renamed from: h */
    int f14824h;

    /* renamed from: i */
    Class<?> f14825i;

    /* renamed from: f */
    boolean f14822f = true;

    /* renamed from: d */
    final ExceptionToResourceMapping f14820d = new ExceptionToResourceMapping();

    public ErrorDialogConfig(Resources resources, int i, int i2) {
        this.f14817a = resources;
        this.f14818b = i;
        this.f14819c = i2;
    }

    /* renamed from: a */
    public ErrorDialogConfig m14791a(Class<? extends Throwable> cls, int i) {
        this.f14820d.m14780a(cls, i);
        return this;
    }

    /* renamed from: a */
    public int m14789a(Throwable th) {
        Integer a = this.f14820d.m14779a(th);
        if (a != null) {
            return a.intValue();
        }
        String str = EventBus.f21113a;
        Log.d(str, "No specific message ressource ID found for " + th);
        return this.f14819c;
    }

    /* renamed from: a */
    public void m14793a(int i) {
        this.f14824h = i;
    }

    /* renamed from: a */
    public void m14792a(Class<?> cls) {
        this.f14825i = cls;
    }

    /* renamed from: a */
    public void m14794a() {
        this.f14822f = false;
    }

    /* renamed from: a */
    public void m14790a(String str) {
        this.f14823g = str;
    }

    /* renamed from: a */
    public void m14788a(EventBus czfVar) {
        this.f14821e = czfVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public EventBus m14787b() {
        EventBus czfVar = this.f14821e;
        return czfVar != null ? czfVar : EventBus.m3448a();
    }
}
