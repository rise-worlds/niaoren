package com.p018b.p019a.p020a.p025e;

import com.p018b.p019a.p020a.NamedRunnable;
import java.io.IOException;

/* compiled from: Http2Connection.java */
/* renamed from: com.b.a.a.e.m */
/* loaded from: classes.dex */
final class C0876m extends NamedRunnable {

    /* renamed from: c */
    final /* synthetic */ int f5973c;

    /* renamed from: d */
    final /* synthetic */ int f5974d;

    /* renamed from: f */
    final /* synthetic */ Http2Connection f5976f;

    /* renamed from: a */
    final /* synthetic */ boolean f5972a = true;

    /* renamed from: e */
    final /* synthetic */ Ping f5975e = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0876m(Http2Connection jVar, String str, Object[] objArr, int i, int i2) {
        super(str, objArr);
        this.f5976f = jVar;
        this.f5973c = i;
        this.f5974d = i2;
    }

    @Override // com.p018b.p019a.p020a.NamedRunnable
    /* renamed from: b */
    public final void mo24472b() {
        try {
            Http2Connection jVar = this.f5976f;
            boolean z = this.f5972a;
            int i = this.f5973c;
            int i2 = this.f5974d;
            Ping aiVar = this.f5975e;
            synchronized (jVar.f5960p) {
                if (aiVar != null) {
                    aiVar.m24680a();
                }
                jVar.f5960p.m24692a(z, i, i2);
            }
        } catch (IOException unused) {
        }
    }
}
