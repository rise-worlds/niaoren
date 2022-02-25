package com.p018b.p019a;

import com.p018b.p019a.p020a.Internal;
import com.p018b.p019a.p020a.Util;
import com.p018b.p019a.p020a.p022b.RealConnection;
import com.p018b.p019a.p020a.p022b.RouteDatabase;
import com.p018b.p019a.p020a.p022b.StreamAllocation;
import java.net.Socket;
import javax.net.ssl.SSLSocket;

/* compiled from: OkHttpClient.java */
/* renamed from: com.b.a.aj */
/* loaded from: classes.dex */
final class C0900aj extends Internal {
    @Override // com.p018b.p019a.p020a.Internal
    /* renamed from: a */
    public final void mo24485a(C0896ab abVar, String str, String str2) {
        abVar.m24551b(str, str2);
    }

    @Override // com.p018b.p019a.p020a.Internal
    /* renamed from: a */
    public final boolean mo24482a(ConnectionPool nVar, RealConnection cVar) {
        return nVar.m24395b(cVar);
    }

    @Override // com.p018b.p019a.p020a.Internal
    /* renamed from: a */
    public final RealConnection mo24481a(ConnectionPool nVar, Address aVar, StreamAllocation gVar) {
        return nVar.m24396a(aVar, gVar);
    }

    @Override // com.p018b.p019a.p020a.Internal
    /* renamed from: b */
    public final Socket mo24478b(ConnectionPool nVar, Address aVar, StreamAllocation gVar) {
        return nVar.m24394b(aVar, gVar);
    }

    @Override // com.p018b.p019a.p020a.Internal
    /* renamed from: b */
    public final void mo24479b(ConnectionPool nVar, RealConnection cVar) {
        nVar.m24397a(cVar);
    }

    @Override // com.p018b.p019a.p020a.Internal
    /* renamed from: a */
    public final RouteDatabase mo24483a(ConnectionPool nVar) {
        return nVar.f6357a;
    }

    @Override // com.p018b.p019a.p020a.Internal
    /* renamed from: a */
    public final int mo24484a(C0905at atVar) {
        return atVar.f6186c;
    }

    @Override // com.p018b.p019a.p020a.Internal
    /* renamed from: a */
    public final void mo24486a(C0896ab abVar, String str) {
        int indexOf = str.indexOf(":", 1);
        if (indexOf != -1) {
            abVar.m24551b(str.substring(0, indexOf), str.substring(indexOf + 1));
        } else if (str.startsWith(":")) {
            abVar.m24551b("", str.substring(1));
        } else {
            abVar.m24551b("", str);
        }
    }

    @Override // com.p018b.p019a.p020a.Internal
    /* renamed from: a */
    public final void mo24480a(ConnectionSpec pVar, SSLSocket sSLSocket, boolean z) {
        String[] strArr;
        String[] strArr2;
        if (pVar.f6370f != null) {
            strArr = (String[]) Util.m24762a(String.class, pVar.f6370f, sSLSocket.getEnabledCipherSuites());
        } else {
            strArr = sSLSocket.getEnabledCipherSuites();
        }
        if (pVar.f6371g != null) {
            strArr2 = (String[]) Util.m24762a(String.class, pVar.f6371g, sSLSocket.getEnabledProtocols());
        } else {
            strArr2 = sSLSocket.getEnabledProtocols();
        }
        if (z && Util.m24751a(sSLSocket.getSupportedCipherSuites(), "TLS_FALLBACK_SCSV") != -1) {
            strArr = Util.m24750a(strArr, "TLS_FALLBACK_SCSV");
        }
        ConnectionSpec b = new C0912q(pVar).m24387a(strArr).m24385b(strArr2).m24386b();
        if (b.f6371g != null) {
            sSLSocket.setEnabledProtocols(b.f6371g);
        }
        if (b.f6370f != null) {
            sSLSocket.setEnabledCipherSuites(b.f6370f);
        }
    }
}
