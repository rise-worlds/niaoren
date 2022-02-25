package com.p018b.p019a.p020a.p021a;

/* compiled from: DiskLruCache.java */
/* renamed from: com.b.a.a.a.g */
/* loaded from: classes.dex */
public final class C0855g {

    /* renamed from: a */
    final C0856h f5724a;

    /* renamed from: b */
    final /* synthetic */ DiskLruCache f5725b;

    /* renamed from: c */
    private boolean f5726c;

    /* renamed from: a */
    public final void m24811a() {
        synchronized (this.f5725b) {
            if (!this.f5726c) {
                if (this.f5724a.f5732f == this) {
                    this.f5725b.m24816a(this);
                }
                this.f5726c = true;
            } else {
                throw new IllegalStateException();
            }
        }
    }
}
