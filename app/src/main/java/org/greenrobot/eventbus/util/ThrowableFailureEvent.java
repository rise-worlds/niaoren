package org.greenrobot.eventbus.util;

/* renamed from: org.greenrobot.eventbus.util.f */
/* loaded from: classes2.dex */
public class ThrowableFailureEvent implements HasExecutionScope {

    /* renamed from: a */
    protected final Throwable f14828a;

    /* renamed from: b */
    protected final boolean f14829b;

    /* renamed from: c */
    private Object f14830c;

    public ThrowableFailureEvent(Throwable th) {
        this.f14828a = th;
        this.f14829b = false;
    }

    public ThrowableFailureEvent(Throwable th, boolean z) {
        this.f14828a = th;
        this.f14829b = z;
    }

    /* renamed from: b */
    public Throwable m14775b() {
        return this.f14828a;
    }

    /* renamed from: c */
    public boolean m14774c() {
        return this.f14829b;
    }

    @Override // org.greenrobot.eventbus.util.HasExecutionScope
    /* renamed from: a */
    public Object mo14777a() {
        return this.f14830c;
    }

    @Override // org.greenrobot.eventbus.util.HasExecutionScope
    /* renamed from: a */
    public void mo14776a(Object obj) {
        this.f14830c = obj;
    }
}
