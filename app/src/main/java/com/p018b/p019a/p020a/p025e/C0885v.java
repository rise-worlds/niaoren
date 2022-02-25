package com.p018b.p019a.p020a.p025e;

import com.p018b.p019a.p020a.NamedRunnable;
import com.p018b.p019a.p020a.p027g.Platform;
import java.io.IOException;

/* compiled from: Http2Connection.java */
/* renamed from: com.b.a.a.e.v */
/* loaded from: classes.dex */
final class C0885v extends NamedRunnable {

    /* renamed from: a */
    final /* synthetic */ Http2Stream f6002a;

    /* renamed from: c */
    final /* synthetic */ C0884u f6003c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0885v(C0884u uVar, String str, Object[] objArr, Http2Stream abVar) {
        super(str, objArr);
        this.f6003c = uVar;
        this.f6002a = abVar;
    }

    @Override // com.p018b.p019a.p020a.NamedRunnable
    /* renamed from: b */
    public final void mo24472b() {
        try {
            this.f6003c.f6001c.f5947c.mo24613a(this.f6002a);
        } catch (IOException e) {
            Platform b = Platform.m24576b();
            b.mo24584a(4, "Http2Connection.Listener failure for " + this.f6003c.f6001c.f5949e, e);
            try {
                this.f6002a.m24716a(ErrorCode.PROTOCOL_ERROR);
            } catch (IOException unused) {
            }
        }
    }
}
