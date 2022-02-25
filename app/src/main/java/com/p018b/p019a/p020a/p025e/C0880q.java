package com.p018b.p019a.p020a.p025e;

import com.p018b.p019a.p020a.NamedRunnable;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Http2Connection.java */
/* renamed from: com.b.a.a.e.q */
/* loaded from: classes.dex */
public final class C0880q extends NamedRunnable {

    /* renamed from: a */
    final /* synthetic */ int f5989a;

    /* renamed from: c */
    final /* synthetic */ ErrorCode f5990c;

    /* renamed from: d */
    final /* synthetic */ Http2Connection f5991d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0880q(Http2Connection jVar, String str, Object[] objArr, int i, ErrorCode bVar) {
        super(str, objArr);
        this.f5991d = jVar;
        this.f5989a = i;
        this.f5990c = bVar;
    }

    @Override // com.p018b.p019a.p020a.NamedRunnable
    /* renamed from: b */
    public final void mo24472b() {
        this.f5991d.f5953i.mo24674c();
        synchronized (this.f5991d) {
            this.f5991d.f5962r.remove(Integer.valueOf(this.f5989a));
        }
    }
}
