package com.p018b.p019a.p020a;

import com.p018b.p019a.Address;
import com.p018b.p019a.C0896ab;
import com.p018b.p019a.C0905at;
import com.p018b.p019a.ConnectionPool;
import com.p018b.p019a.ConnectionSpec;
import com.p018b.p019a.p020a.p022b.RealConnection;
import com.p018b.p019a.p020a.p022b.RouteDatabase;
import com.p018b.p019a.p020a.p022b.StreamAllocation;
import java.net.Socket;
import javax.net.ssl.SSLSocket;

/* renamed from: com.b.a.a.a */
/* loaded from: classes.dex */
public abstract class Internal {

    /* renamed from: a */
    public static Internal f5689a;

    /* renamed from: a */
    public abstract int mo24484a(C0905at atVar);

    /* renamed from: a */
    public abstract RealConnection mo24481a(ConnectionPool nVar, Address aVar, StreamAllocation gVar);

    /* renamed from: a */
    public abstract RouteDatabase mo24483a(ConnectionPool nVar);

    /* renamed from: a */
    public abstract void mo24486a(C0896ab abVar, String str);

    /* renamed from: a */
    public abstract void mo24485a(C0896ab abVar, String str, String str2);

    /* renamed from: a */
    public abstract void mo24480a(ConnectionSpec pVar, SSLSocket sSLSocket, boolean z);

    /* renamed from: a */
    public abstract boolean mo24482a(ConnectionPool nVar, RealConnection cVar);

    /* renamed from: b */
    public abstract Socket mo24478b(ConnectionPool nVar, Address aVar, StreamAllocation gVar);

    /* renamed from: b */
    public abstract void mo24479b(ConnectionPool nVar, RealConnection cVar);
}
