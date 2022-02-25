package com.p018b.p019a;

import com.p018b.p029b.BufferedSink;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: RequestBody.java */
/* renamed from: com.b.a.ar */
/* loaded from: classes.dex */
public final class C0904ar extends RequestBody {

    /* renamed from: b */
    final /* synthetic */ int f6168b;

    /* renamed from: c */
    final /* synthetic */ byte[] f6169c;

    /* renamed from: a */
    final /* synthetic */ MediaType f6167a = null;

    /* renamed from: d */
    final /* synthetic */ int f6170d = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0904ar(int i, byte[] bArr) {
        this.f6168b = i;
        this.f6169c = bArr;
    }

    @Override // com.p018b.p019a.RequestBody
    /* renamed from: a */
    public final MediaType mo24366a() {
        return this.f6167a;
    }

    @Override // com.p018b.p019a.RequestBody
    /* renamed from: b */
    public final long mo24363b() {
        return this.f6168b;
    }

    @Override // com.p018b.p019a.RequestBody
    /* renamed from: a */
    public final void mo24365a(BufferedSink gVar) {
        gVar.mo24296b(this.f6169c, this.f6170d, this.f6168b);
    }
}
