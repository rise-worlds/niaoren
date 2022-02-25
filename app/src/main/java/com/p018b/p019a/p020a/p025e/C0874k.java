package com.p018b.p019a.p020a.p025e;

import com.p018b.p019a.p020a.NamedRunnable;
import java.io.IOException;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Http2Connection.java */
/* renamed from: com.b.a.a.e.k */
/* loaded from: classes.dex */
public final class C0874k extends NamedRunnable {

    /* renamed from: a */
    final /* synthetic */ int f5966a;

    /* renamed from: c */
    final /* synthetic */ ErrorCode f5967c;

    /* renamed from: d */
    final /* synthetic */ Http2Connection f5968d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0874k(Http2Connection jVar, String str, Object[] objArr, int i, ErrorCode bVar) {
        super(str, objArr);
        this.f5968d = jVar;
        this.f5966a = i;
        this.f5967c = bVar;
    }

    @Override // com.p018b.p019a.p020a.NamedRunnable
    /* renamed from: b */
    public final void mo24472b() {
        try {
            this.f5968d.m24623b(this.f5966a, this.f5967c);
        } catch (IOException unused) {
        }
    }
}
