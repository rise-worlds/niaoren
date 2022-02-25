package com.p018b.p019a.p020a.p025e;

import com.p018b.p019a.p020a.NamedRunnable;
import java.io.IOException;

/* compiled from: Http2Connection.java */
/* renamed from: com.b.a.a.e.x */
/* loaded from: classes.dex */
final class C0887x extends NamedRunnable {

    /* renamed from: a */
    final /* synthetic */ Settings f6005a;

    /* renamed from: c */
    final /* synthetic */ C0884u f6006c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0887x(C0884u uVar, String str, Object[] objArr, Settings alVar) {
        super(str, objArr);
        this.f6006c = uVar;
        this.f6005a = alVar;
    }

    @Override // com.p018b.p019a.p020a.NamedRunnable
    /* renamed from: b */
    public final void mo24472b() {
        try {
            this.f6006c.f6001c.f5960p.m24693a(this.f6005a);
        } catch (IOException unused) {
        }
    }
}
