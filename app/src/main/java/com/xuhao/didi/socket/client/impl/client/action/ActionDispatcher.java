package com.xuhao.didi.socket.client.impl.client.action;

import com.xuhao.didi.p082a.p084b.p085a.IOAction;
import com.xuhao.didi.p082a.p084b.p085a.IPulseSendable;
import com.xuhao.didi.p082a.p084b.p085a.ISendable;
import com.xuhao.didi.p082a.p084b.p085a.IStateSender;
import com.xuhao.didi.p082a.p086c.OriginalData;
import com.xuhao.didi.p082a.p088e.SLog;
import com.xuhao.didi.socket.client.sdk.client.ConnectionInfo;
import com.xuhao.didi.socket.client.sdk.client.OkSocketOptions;
import com.xuhao.didi.socket.client.sdk.client.connection.IConnectionManager;
import com.xuhao.didi.socket.client.sdk.client.p100a.IAction;
import com.xuhao.didi.socket.client.sdk.client.p100a.ISocketActionListener;
import com.xuhao.didi.socket.p089a.p090a.p091a.AbsLoopThread;
import com.xuhao.didi.socket.p089a.p090a.p092b.p094b.IRegister;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

/* loaded from: classes2.dex */
public class ActionDispatcher implements IStateSender, IRegister<ISocketActionListener, IConnectionManager> {

    /* renamed from: a */
    private static final DispatchThread f14408a = new DispatchThread();

    /* renamed from: b */
    private static final LinkedBlockingQueue<ActionBean> f14409b = new LinkedBlockingQueue<>();

    /* renamed from: c */
    private volatile List<ISocketActionListener> f14410c = new ArrayList();

    /* renamed from: d */
    private volatile ConnectionInfo f14411d;

    /* renamed from: e */
    private volatile IConnectionManager f14412e;

    static {
        f14408a.start();
    }

    public ActionDispatcher(ConnectionInfo aVar, IConnectionManager bVar) {
        this.f14412e = bVar;
        this.f14411d = aVar;
    }

    /* renamed from: a  reason: avoid collision after fix types in other method */
    public IConnectionManager mo15084b(ISocketActionListener bVar) {
        if (bVar != null) {
            synchronized (this.f14410c) {
                if (!this.f14410c.contains(bVar)) {
                    this.f14410c.add(bVar);
                }
            }
        }
        return this.f14412e;
    }

    /* renamed from: b  reason: avoid collision after fix types in other method */
    public IConnectionManager mo15089a(ISocketActionListener bVar) {
        if (bVar != null) {
            synchronized (this.f14410c) {
                this.f14410c.remove(bVar);
            }
        }
        return this.f14412e;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* renamed from: a */
    public void m15086a(String str, Serializable serializable, ISocketActionListener bVar) {
        char c;
        switch (str.hashCode()) {
            case -1455248519:
                if (str.equals(IOAction.f14363a)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -1321574355:
                if (str.equals(IAction.f14468e)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case -1245920523:
                if (str.equals(IAction.f14473j)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -1201839197:
                if (str.equals(IAction.f14474k)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -1121297674:
                if (str.equals(IAction.f14470g)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case -749410229:
                if (str.equals(IAction.f14472i)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -542453077:
                if (str.equals(IAction.f14469f)) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 190576450:
                if (str.equals(IAction.f14471h)) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 1756120480:
                if (str.equals(IOAction.f14365c)) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case 2146005698:
                if (str.equals(IOAction.f14364b)) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                try {
                    bVar.onSocketConnectionSuccess(this.f14411d, str);
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            case 1:
                try {
                    bVar.onSocketConnectionFailed(this.f14411d, str, (Exception) serializable);
                    return;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return;
                }
            case 2:
                try {
                    bVar.onSocketDisconnection(this.f14411d, str, (Exception) serializable);
                    return;
                } catch (Exception e3) {
                    e3.printStackTrace();
                    return;
                }
            case 3:
                try {
                    bVar.onSocketReadResponse(this.f14411d, str, (OriginalData) serializable);
                    return;
                } catch (Exception e4) {
                    e4.printStackTrace();
                    return;
                }
            case 4:
            case 5:
                try {
                    bVar.onSocketIOThreadStart(str);
                    return;
                } catch (Exception e5) {
                    e5.printStackTrace();
                    return;
                }
            case 6:
                try {
                    bVar.onSocketWriteResponse(this.f14411d, str, (ISendable) serializable);
                    return;
                } catch (Exception e6) {
                    e6.printStackTrace();
                    return;
                }
            case 7:
            case '\b':
                try {
                    bVar.onSocketIOThreadShutdown(str, (Exception) serializable);
                    return;
                } catch (Exception e7) {
                    e7.printStackTrace();
                    return;
                }
            case '\t':
                try {
                    bVar.onPulseSend(this.f14411d, (IPulseSendable) serializable);
                    return;
                } catch (Exception e8) {
                    e8.printStackTrace();
                    return;
                }
            default:
                return;
        }
    }

    @Override // com.xuhao.didi.p082a.p084b.p085a.IStateSender
    /* renamed from: a */
    public void mo15087a(String str, Serializable serializable) {
        OkSocketOptions e = this.f14412e.mo14977e();
        if (e != null) {
            OkSocketOptions.ThreadModeToken p = e.m15032p();
            if (p != null) {
                try {
                    p.handleCallbackEvent(new ActionRunnable(new ActionBean(str, serializable, this)));
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            } else if (e.m15031q()) {
                f14409b.offer(new ActionBean(str, serializable, this));
            } else if (!e.m15031q()) {
                synchronized (this.f14410c) {
                    for (ISocketActionListener bVar : new ArrayList(this.f14410c)) {
                        m15086a(str, serializable, bVar);
                    }
                }
            } else {
                SLog.m15176a("ActionDispatcher error action:" + str + " is not dispatch");
            }
        }
    }

    @Override // com.xuhao.didi.p082a.p084b.p085a.IStateSender
    /* renamed from: a */
    public void mo15088a(String str) {
        mo15087a(str, null);
    }

    /* renamed from: a */
    public void m15090a(ConnectionInfo aVar) {
        this.f14411d = aVar;
    }

    /* loaded from: classes2.dex */
    private static class DispatchThread extends AbsLoopThread {
        @Override // com.xuhao.didi.socket.p089a.p090a.p091a.AbsLoopThread
        /* renamed from: a */
        protected void mo14998a(Exception exc) {
        }

        public DispatchThread() {
            super("client_action_dispatch_thread");
        }

        @Override // com.xuhao.didi.socket.p089a.p090a.p091a.AbsLoopThread
        /* renamed from: a */
        protected void mo14999a() throws Exception {
            ActionBean actionBean = (ActionBean) ActionDispatcher.f14409b.take();
            if (!(actionBean == null || actionBean.f14415c == null)) {
                ActionDispatcher actionDispatcher = actionBean.f14415c;
                synchronized (actionDispatcher.f14410c) {
                    for (ISocketActionListener bVar : new ArrayList(actionDispatcher.f14410c)) {
                        actionDispatcher.m15086a(actionBean.f14413a, actionBean.f14414b, bVar);
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes2.dex */
    public static class ActionBean {

        /* renamed from: a */
        String f14413a;

        /* renamed from: b */
        Serializable f14414b;

        /* renamed from: c */
        ActionDispatcher f14415c;

        public ActionBean(String str, Serializable serializable, ActionDispatcher actionDispatcher) {
            this.f14413a = "";
            this.f14413a = str;
            this.f14414b = serializable;
            this.f14415c = actionDispatcher;
        }
    }

    /* loaded from: classes2.dex */
    public static class ActionRunnable implements Runnable {

        /* renamed from: a */
        private ActionBean f14416a;

        ActionRunnable(ActionBean actionBean) {
            this.f14416a = actionBean;
        }

        @Override // java.lang.Runnable
        public void run() {
            ActionBean actionBean = this.f14416a;
            if (!(actionBean == null || actionBean.f14415c == null)) {
                ActionDispatcher actionDispatcher = this.f14416a.f14415c;
                synchronized (actionDispatcher.f14410c) {
                    for (ISocketActionListener bVar : new ArrayList(actionDispatcher.f14410c)) {
                        actionDispatcher.m15086a(this.f14416a.f14413a, this.f14416a.f14414b, bVar);
                    }
                }
            }
        }
    }
}
