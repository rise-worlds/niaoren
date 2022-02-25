package com.xuhao.didi.socket.client.sdk;

import com.xuhao.didi.socket.client.impl.client.ManagerHolder;
import com.xuhao.didi.socket.client.sdk.client.ConnectionInfo;
import com.xuhao.didi.socket.client.sdk.client.OkSocketOptions;
import com.xuhao.didi.socket.client.sdk.client.connection.IConnectionManager;
import com.xuhao.didi.socket.p089a.p090a.p092b.p094b.IRegister;
import com.xuhao.didi.socket.p089a.p090a.p092b.p095c.IServerActionListener;
import com.xuhao.didi.socket.p089a.p090a.p092b.p095c.IServerManager;

/* renamed from: com.xuhao.didi.socket.client.sdk.a */
/* loaded from: classes2.dex */
public class OkSocket {

    /* renamed from: a */
    private static ManagerHolder f14439a = ManagerHolder.m15122a();

    /* renamed from: a */
    public static IRegister<IServerActionListener, IServerManager> m15069a(int i) {
        return (IRegister) f14439a.m15116b(i);
    }

    /* renamed from: a */
    public static IConnectionManager m15068a(ConnectionInfo aVar) {
        return f14439a.m15115b(aVar);
    }

    /* renamed from: a */
    public static IConnectionManager m15066a(String str, int i) {
        return f14439a.m15115b(new ConnectionInfo(str, i));
    }

    /* renamed from: a */
    public static IConnectionManager m15067a(ConnectionInfo aVar, OkSocketOptions okSocketOptions) {
        return f14439a.m15114b(aVar, okSocketOptions);
    }

    /* renamed from: a */
    public static IConnectionManager m15065a(String str, int i, OkSocketOptions okSocketOptions) {
        return f14439a.m15114b(new ConnectionInfo(str, i), okSocketOptions);
    }
}
