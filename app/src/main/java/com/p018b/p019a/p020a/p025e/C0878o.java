package com.p018b.p019a.p020a.p025e;

import com.p018b.p019a.p020a.NamedRunnable;
import java.io.IOException;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Http2Connection.java */
/* renamed from: com.b.a.a.e.o */
/* loaded from: classes.dex */
public final class C0878o extends NamedRunnable {

    /* renamed from: a */
    final /* synthetic */ int f5980a;

    /* renamed from: c */
    final /* synthetic */ List f5981c;

    /* renamed from: d */
    final /* synthetic */ boolean f5982d;

    /* renamed from: e */
    final /* synthetic */ Http2Connection f5983e;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0878o(Http2Connection jVar, String str, Object[] objArr, int i, List list, boolean z) {
        super(str, objArr);
        this.f5983e = jVar;
        this.f5980a = i;
        this.f5981c = list;
        this.f5982d = z;
    }

    @Override // com.p018b.p019a.p020a.NamedRunnable
    /* renamed from: b */
    public final void mo24472b() {
        this.f5983e.f5953i.mo24675b();
        try {
            this.f5983e.f5960p.m24695a(this.f5980a, ErrorCode.CANCEL);
            synchronized (this.f5983e) {
                this.f5983e.f5962r.remove(Integer.valueOf(this.f5980a));
            }
        } catch (IOException unused) {
        }
    }
}
