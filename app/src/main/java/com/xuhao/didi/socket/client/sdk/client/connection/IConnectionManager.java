package com.xuhao.didi.socket.client.sdk.client.connection;

import com.xuhao.didi.socket.client.impl.client.PulseManager;
import com.xuhao.didi.socket.client.sdk.client.ConnectionInfo;
import com.xuhao.didi.socket.client.sdk.client.connection.p102a.IConfiguration;
import com.xuhao.didi.socket.client.sdk.client.connection.p102a.IConnectable;
import com.xuhao.didi.socket.client.sdk.client.p100a.ISocketActionListener;
import com.xuhao.didi.socket.p089a.p090a.p092b.p093a.IDisConnectable;
import com.xuhao.didi.socket.p089a.p090a.p092b.p093a.ISender;
import com.xuhao.didi.socket.p089a.p090a.p092b.p094b.IRegister;

/* renamed from: com.xuhao.didi.socket.client.sdk.client.connection.b */
/* loaded from: classes2.dex */
public interface IConnectionManager extends IDisConnectable, ISender<IConnectionManager>, IRegister<ISocketActionListener, IConnectionManager>, IConfiguration, IConnectable {
    /* renamed from: a */
    ConnectionInfo mo14975a();

    /* renamed from: a */
    void mo14974a(ConnectionInfo aVar);

    /* renamed from: a */
    void mo14973a(boolean z);

    /* renamed from: b */
    ConnectionInfo mo14972b();

    /* renamed from: b */
    void mo14971b(ConnectionInfo aVar);

    /* renamed from: f */
    boolean mo14970f();

    /* renamed from: g */
    boolean mo14969g();

    /* renamed from: h */
    PulseManager mo14968h();

    /* renamed from: i */
    AbsReconnectionManager mo14967i();
}
