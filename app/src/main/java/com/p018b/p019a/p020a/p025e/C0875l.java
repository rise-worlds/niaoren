package com.p018b.p019a.p020a.p025e;

import com.p018b.p019a.p020a.NamedRunnable;
import java.io.IOException;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Http2Connection.java */
/* renamed from: com.b.a.a.e.l */
/* loaded from: classes.dex */
public final class C0875l extends NamedRunnable {

    /* renamed from: a */
    final /* synthetic */ int f5969a;

    /* renamed from: c */
    final /* synthetic */ long f5970c;

    /* renamed from: d */
    final /* synthetic */ Http2Connection f5971d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0875l(Http2Connection jVar, String str, Object[] objArr, int i, long j) {
        super(str, objArr);
        this.f5971d = jVar;
        this.f5969a = i;
        this.f5970c = j;
    }

    @Override // com.p018b.p019a.p020a.NamedRunnable
    /* renamed from: b */
    public final void mo24472b() {
        try {
            this.f5971d.f5960p.m24696a(this.f5969a, this.f5970c);
        } catch (IOException unused) {
        }
    }
}
