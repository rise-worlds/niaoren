package com.p018b.p019a.p020a.p025e;

import com.p018b.p019a.p020a.NamedRunnable;
import com.p018b.p029b.Buffer;
import java.io.IOException;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Http2Connection.java */
/* renamed from: com.b.a.a.e.p */
/* loaded from: classes.dex */
public final class C0879p extends NamedRunnable {

    /* renamed from: a */
    final /* synthetic */ int f5984a;

    /* renamed from: c */
    final /* synthetic */ Buffer f5985c;

    /* renamed from: d */
    final /* synthetic */ int f5986d;

    /* renamed from: e */
    final /* synthetic */ boolean f5987e;

    /* renamed from: f */
    final /* synthetic */ Http2Connection f5988f;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0879p(Http2Connection jVar, String str, Object[] objArr, int i, Buffer fVar, int i2, boolean z) {
        super(str, objArr);
        this.f5988f = jVar;
        this.f5984a = i;
        this.f5985c = fVar;
        this.f5986d = i2;
        this.f5987e = z;
    }

    @Override // com.p018b.p019a.p020a.NamedRunnable
    /* renamed from: b */
    public final void mo24472b() {
        try {
            this.f5988f.f5953i.mo24676a(this.f5985c, this.f5986d);
            this.f5988f.f5960p.m24695a(this.f5984a, ErrorCode.CANCEL);
            synchronized (this.f5988f) {
                this.f5988f.f5962r.remove(Integer.valueOf(this.f5984a));
            }
        } catch (IOException unused) {
        }
    }
}
