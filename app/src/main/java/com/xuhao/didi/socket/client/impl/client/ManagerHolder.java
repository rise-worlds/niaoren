package com.xuhao.didi.socket.client.impl.client;

import com.xuhao.didi.p082a.p088e.SLog;
import com.xuhao.didi.socket.client.impl.client.p099a.IConnectionSwitchListener;
import com.xuhao.didi.socket.client.sdk.client.ConnectionInfo;
import com.xuhao.didi.socket.client.sdk.client.OkSocketOptions;
import com.xuhao.didi.socket.client.sdk.client.connection.IConnectionManager;
import com.xuhao.didi.socket.p089a.p090a.p092b.p095c.IServerManager;
import com.xuhao.didi.socket.p089a.p090a.p092b.p095c.IServerManagerPrivate;
import com.xuhao.didi.socket.p089a.p090a.p097d.SPIUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public class ManagerHolder {

    /* renamed from: a */
    private volatile Map<ConnectionInfo, IConnectionManager> f14391a;

    /* renamed from: b */
    private volatile Map<Integer, IServerManagerPrivate> f14392b;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class InstanceHolder {

        /* renamed from: a */
        private static final ManagerHolder f14394a = new ManagerHolder();

        private InstanceHolder() {
        }
    }

    /* renamed from: a */
    public static ManagerHolder m15122a() {
        return InstanceHolder.f14394a;
    }

    private ManagerHolder() {
        this.f14391a = new HashMap();
        this.f14392b = new HashMap();
        this.f14391a.clear();
    }

    /* renamed from: a */
    public IServerManager m15121a(int i) {
        IServerManagerPrivate fVar = this.f14392b.get(Integer.valueOf(i));
        if (fVar != null) {
            return fVar;
        }
        IServerManagerPrivate fVar2 = (IServerManagerPrivate) SPIUtils.m15143a(IServerManager.class);
        if (fVar2 != null) {
            synchronized (this.f14392b) {
                this.f14392b.put(Integer.valueOf(i), fVar2);
            }
            fVar2.m15150a(i);
            return fVar2;
        }
        SLog.m15176a("Oksocket.Server() load error. Server plug-in are required! For details link to https://github.com/xuuhaoo/OkSocket");
        throw new IllegalStateException("Oksocket.Server() load error. Server plug-in are required! For details link to https://github.com/xuuhaoo/OkSocket");
    }

    /* renamed from: b */
    public IServerManager m15116b(int i) {
        IServerManagerPrivate fVar = (IServerManagerPrivate) SPIUtils.m15143a(IServerManager.class);
        if (fVar != null) {
            fVar.m15150a(i);
            return fVar;
        }
        SLog.m15176a("Oksocket.Server() load error. Server plug-in are required! For details link to https://github.com/xuuhaoo/OkSocket");
        throw new IllegalStateException("Oksocket.Server() load error. Server plug-in are required! For details link to https://github.com/xuuhaoo/OkSocket");
    }

    /* renamed from: a */
    public IConnectionManager m15119a(ConnectionInfo aVar) {
        IConnectionManager bVar = this.f14391a.get(aVar);
        if (bVar == null) {
            return m15118a(aVar, OkSocketOptions.m15030r());
        }
        return m15118a(aVar, bVar.mo14977e());
    }

    /* renamed from: a */
    public IConnectionManager m15118a(ConnectionInfo aVar, OkSocketOptions okSocketOptions) {
        IConnectionManager bVar = this.f14391a.get(aVar);
        if (bVar == null) {
            return m15113c(aVar, okSocketOptions);
        }
        if (!okSocketOptions.m15035m()) {
            synchronized (this.f14391a) {
                this.f14391a.remove(aVar);
            }
            return m15113c(aVar, okSocketOptions);
        }
        bVar.mo14978a(okSocketOptions);
        return bVar;
    }

    /* renamed from: b */
    public IConnectionManager m15115b(ConnectionInfo aVar) {
        return m15114b(aVar, OkSocketOptions.m15030r());
    }

    /* renamed from: b */
    public IConnectionManager m15114b(ConnectionInfo aVar, OkSocketOptions okSocketOptions) {
        return m15113c(aVar, okSocketOptions);
    }

    /* renamed from: c */
    private IConnectionManager m15113c(ConnectionInfo aVar, OkSocketOptions okSocketOptions) {
        ConnectionManagerImpl connectionManagerImpl = new ConnectionManagerImpl(aVar);
        connectionManagerImpl.mo14978a(okSocketOptions);
        connectionManagerImpl.m15099a(new IConnectionSwitchListener() { // from class: com.xuhao.didi.socket.client.impl.client.ManagerHolder.1
            @Override // com.xuhao.didi.socket.client.impl.client.p099a.IConnectionSwitchListener
            public void onSwitchConnectionInfo(IConnectionManager bVar, ConnectionInfo aVar2, ConnectionInfo aVar3) {
                synchronized (ManagerHolder.this.f14391a) {
                    ManagerHolder.this.f14391a.remove(aVar2);
                    ManagerHolder.this.f14391a.put(aVar3, bVar);
                }
            }
        });
        synchronized (this.f14391a) {
            this.f14391a.put(aVar, connectionManagerImpl);
        }
        return connectionManagerImpl;
    }

    /* renamed from: b */
    protected List<IConnectionManager> m15117b() {
        ArrayList arrayList = new ArrayList();
        HashMap hashMap = new HashMap(this.f14391a);
        Iterator it = hashMap.keySet().iterator();
        while (it.hasNext()) {
            IConnectionManager bVar = (IConnectionManager) hashMap.get((ConnectionInfo) it.next());
            if (!bVar.mo14977e().m15035m()) {
                it.remove();
            } else {
                arrayList.add(bVar);
            }
        }
        return arrayList;
    }
}
