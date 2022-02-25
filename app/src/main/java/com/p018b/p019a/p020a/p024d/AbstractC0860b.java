package com.p018b.p019a.p020a.p024d;

import com.p018b.p029b.ForwardingTimeout;
import com.p018b.p029b.Source;
import com.p018b.p029b.Timeout;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Http1Codec.java */
/* renamed from: com.b.a.a.d.b */
/* loaded from: classes.dex */
public abstract class AbstractC0860b implements Source {

    /* renamed from: a */
    protected final ForwardingTimeout f5823a;

    /* renamed from: b */
    protected boolean f5824b;

    /* renamed from: c */
    final /* synthetic */ Http1Codec f5825c;

    private AbstractC0860b(Http1Codec aVar) {
        this.f5825c = aVar;
        this.f5823a = new ForwardingTimeout(this.f5825c.f5820c.mo24250a());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ AbstractC0860b(Http1Codec aVar, byte b) {
        this(aVar);
    }

    @Override // com.p018b.p029b.Source
    /* renamed from: a */
    public final Timeout mo24250a() {
        return this.f5823a;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public final void m24719a(boolean z) {
        if (this.f5825c.f5822e != 6) {
            if (this.f5825c.f5822e == 5) {
                Http1Codec.m24721a(this.f5823a);
                Http1Codec aVar = this.f5825c;
                aVar.f5822e = 6;
                if (aVar.f5819b != null) {
                    this.f5825c.f5819b.m24778a(!z, this.f5825c);
                    return;
                }
                return;
            }
            throw new IllegalStateException("state: " + this.f5825c.f5822e);
        }
    }
}
