package com.xuhao.didi.socket.p089a.p090a.p092b.p095c;

import com.xuhao.didi.p082a.p087d.IReaderProtocol;
import com.xuhao.didi.socket.p089a.p090a.p092b.p093a.IDisConnectable;
import com.xuhao.didi.socket.p089a.p090a.p092b.p093a.ISender;
import java.io.Serializable;

/* renamed from: com.xuhao.didi.socket.a.a.b.c.a */
/* loaded from: classes2.dex */
public interface IClient extends IDisConnectable, ISender<IClient>, Serializable {
    /* renamed from: a */
    String m15172a();

    /* renamed from: a */
    void m15171a(IReaderProtocol aVar);

    /* renamed from: a */
    void m15170a(IClientIOCallback bVar);

    /* renamed from: b */
    String m15169b();

    /* renamed from: b */
    void m15168b(IClientIOCallback bVar);

    /* renamed from: c */
    String m15167c();

    /* renamed from: e */
    void m15166e();
}
