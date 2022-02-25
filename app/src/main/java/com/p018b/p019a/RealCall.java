package com.p018b.p019a;

import com.p018b.p019a.p020a.p021a.CacheInterceptor;
import com.p018b.p019a.p020a.p022b.ConnectInterceptor;
import com.p018b.p019a.p020a.p023c.BridgeInterceptor;
import com.p018b.p019a.p020a.p023c.CallServerInterceptor;
import com.p018b.p019a.p020a.p023c.RealInterceptorChain;
import com.p018b.p019a.p020a.p023c.RetryAndFollowUpInterceptor;
import com.p018b.p019a.p020a.p027g.Platform;
import java.io.IOException;
import java.util.ArrayList;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.b.a.am */
/* loaded from: classes.dex */
public final class RealCall implements Call {

    /* renamed from: a */
    final OkHttpClient f6149a;

    /* renamed from: b */
    final RetryAndFollowUpInterceptor f6150b;

    /* renamed from: c */
    final Request f6151c;

    /* renamed from: d */
    final boolean f6152d;

    /* renamed from: e */
    private boolean f6153e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RealCall(OkHttpClient aiVar, Request aoVar, boolean z) {
        this.f6149a = aiVar;
        this.f6151c = aoVar;
        this.f6152d = z;
        this.f6150b = new RetryAndFollowUpInterceptor(aiVar, z);
    }

    @Override // com.p018b.p019a.Call
    /* renamed from: a */
    public final Response mo24407a() {
        synchronized (this) {
            if (!this.f6153e) {
                this.f6153e = true;
            } else {
                throw new IllegalStateException("Already Executed");
            }
        }
        m24474d();
        try {
            this.f6149a.f6093c.m24375a(this);
            Response c = m24475c();
            if (c != null) {
                return c;
            }
            throw new IOException("Canceled");
        } finally {
            this.f6149a.f6093c.m24371b(this);
        }
    }

    /* renamed from: d */
    private void m24474d() {
        this.f6150b.m24725a(Platform.m24576b().mo24583a("response.body().close()"));
    }

    @Override // com.p018b.p019a.Call
    /* renamed from: a */
    public final void mo24406a(Callback hVar) {
        synchronized (this) {
            if (!this.f6153e) {
                this.f6153e = true;
            } else {
                throw new IllegalStateException("Already Executed");
            }
        }
        m24474d();
        this.f6149a.f6093c.m24374a(new C0902an(this, hVar));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public final String m24476b() {
        C0897ad d = this.f6151c.f6156a.m24532d("/...");
        d.f6070b = HttpUrl.m24542a("", " \"':;<=>@[]^`{}|/\\?#", false, false, false, true);
        d.f6071c = HttpUrl.m24542a("", " \"':;<=>@[]^`{}|/\\?#", false, false, false, true);
        return d.m24519b().toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public final Response m24475c() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.f6149a.f6097g);
        arrayList.add(this.f6150b);
        arrayList.add(new BridgeInterceptor(this.f6149a.f6100j));
        OkHttpClient aiVar = this.f6149a;
        arrayList.add(new CacheInterceptor(aiVar.f6101k != null ? aiVar.f6101k.f6210a : aiVar.f6102l));
        arrayList.add(new ConnectInterceptor(this.f6149a));
        if (!this.f6152d) {
            arrayList.addAll(this.f6149a.f6098h);
        }
        arrayList.add(new CallServerInterceptor(this.f6152d));
        return new RealInterceptorChain(arrayList, null, null, null, 0, this.f6151c).mo24511a(this.f6151c);
    }

    public final /* synthetic */ Object clone() {
        return new RealCall(this.f6149a, this.f6151c, this.f6152d);
    }
}
