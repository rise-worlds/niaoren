package com.cyjh.mq.sdk;

import android.app.Application;
import com.cyjh.mobileanjian.ipc.CrashRunnerState;
import com.cyjh.mobileanjian.ipc.RootManager;
import com.cyjh.mobileanjian.ipc.RootShell;
import com.cyjh.mobileanjian.ipc.interfaces.OnRequestCallback;
import com.cyjh.mobileanjian.ipc.interfaces.OnSpecialMqCmdCallback;
import com.cyjh.mobileanjian.ipc.share.proto.Ipc;
import com.cyjh.mobileanjian.ipc.share.proto.IpcCommand;
import com.cyjh.mobileanjian.ipc.share.proto.IpcRaw;
import com.cyjh.mobileanjian.ipc.utils.RootUtil;
import com.cyjh.mq.p046a.MyApplication;
import com.cyjh.mq.p048c.IpcConnection;
import com.cyjh.mq.sdk.inf.CustomRunner;
import com.cyjh.mq.sdk.inf.OnElfCallback;
import com.goldcoast.sdk.domain.EntryPoint;

/* renamed from: com.cyjh.mq.sdk.MqRunner */
/* loaded from: classes.dex */
public class MqRunner extends GeneralMqRunner implements CustomRunner {

    /* renamed from: g */
    public OnRequestCallback f8880g;

    /* renamed from: h */
    private boolean f8881h;

    /* renamed from: i */
    private boolean f8882i;

    @Override // com.cyjh.mq.sdk.inf.IRunnerOther
    /* renamed from: c */
    public final OnElfCallback mo20410c() {
        return null;
    }

    @Override // com.cyjh.mobileanjian.ipc.interfaces.EngineStateObserver
    public void onExit() {
    }

    @Override // com.cyjh.mq.sdk.inf.CustomRunner
    public void setOnSpecialMqCmdCallback(OnSpecialMqCmdCallback onSpecialMqCmdCallback) {
    }

    /* synthetic */ MqRunner(byte b) {
        this();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.cyjh.mq.sdk.MqRunner$a */
    /* loaded from: classes.dex */
    public static class C1367a {

        /* renamed from: a */
        private static final MqRunner f8883a = new MqRunner((byte) 0);

        private C1367a() {
        }
    }

    public static synchronized MqRunner getInstance() {
        MqRunner mqRunner;
        synchronized (MqRunner.class) {
            mqRunner = C1367a.f8883a;
        }
        return mqRunner;
    }

    private MqRunner() {
        this.f8881h = false;
        this.f8882i = false;
    }

    public void requestThirdRoot() {
        RootManager.m21044a().m21039b();
    }

    /* renamed from: d */
    private void m20429d() {
        if (!this.f8881h) {
            this.f8881h = true;
            EntryPoint.instance().exec(new String[]{"setenforce 0", MyApplication.m20565a().getAbsolutePath()});
            this.f8894d.sendEmptyMessageDelayed(256, 6000L);
        }
    }

    @Override // com.cyjh.mobileanjian.ipc.interfaces.EngineStateObserver
    public void onCrash(CrashRunnerState bVar) {
        this.f8895e = false;
        if (this.f8892b == null || this.f8892b.f8810f == null || this.f8892b.f8810f.f8793b == 0) {
            m20423a();
        }
    }

    @Override // com.cyjh.mq.sdk.inf.IRunner
    public void start() {
        if (this.f8895e) {
            if (this.f8892b.f8810f.f8794c) {
                notifyRotationStatus();
                m20422a(IpcRaw.m20933a(1));
                return;
            }
            new GeneralMqRunner.AsyncTaskC1371a(this, (byte) 0).execute(this.f8893c);
        } else if (EntryPoint.instance() == null || !EntryPoint.instance().getStatus()) {
            if (!RootUtil.isRoot() || !RootShell.m21029a().f8236d) {
                RootManager.m21044a().m21039b();
            } else {
                m20423a();
            }
        } else if (!this.f8881h) {
            this.f8881h = true;
            EntryPoint.instance().exec(new String[]{"setenforce 0", MyApplication.m20565a().getAbsolutePath()});
            this.f8894d.sendEmptyMessageDelayed(256, 6000L);
        }
    }

    @Override // com.cyjh.mq.sdk.inf.IRunnerOther
    /* renamed from: b */
    public final OnRequestCallback mo20412b() {
        return this.f8880g;
    }

    @Override // com.cyjh.mq.sdk.inf.CustomRunner
    public void recordLog2File(boolean z) {
        this.f8882i = z;
        m20422a(Ipc.IpcMessage.newBuilder().setCmd(68).addArg1(z ? 1 : 0).build());
    }

    @Override // com.cyjh.mq.sdk.inf.CustomRunner
    public void setRequestCallback(OnRequestCallback onRequestCallback) {
        this.f8880g = onRequestCallback;
        if (this.f8892b != null) {
            this.f8892b.f8817m = onRequestCallback;
        }
    }

    @Override // com.cyjh.mq.sdk.inf.CustomRunner
    public void init(Application application, String str) {
        if (this.f8895e) {
            m20422a(Ipc.IpcMessage.newBuilder().setCmd(97).addArg1(1).addArg2(str).build());
        }
    }

    @Override // com.cyjh.mq.sdk.inf.CustomRunner
    public void getScriptPerm(String str) {
        if (this.f8895e) {
            m20422a(Ipc.IpcMessage.newBuilder().setCmd(97).addArg1(5).addArg2(str).build());
            return;
        }
        MqRunnerLite instance = MqRunnerLite.getInstance();
        instance.f8888e = false;
        instance.f8884a.m21006a(str);
    }

    @Override // com.cyjh.mq.sdk.inf.CustomRunner
    public void download(String str) {
        if (this.f8895e) {
            m20422a(Ipc.IpcMessage.newBuilder().setCmd(97).addArg1(2).addArg2(str).build());
            return;
        }
        MqRunnerLite instance = MqRunnerLite.getInstance();
        instance.f8888e = false;
        instance.f8884a.m21006a(str);
    }

    @Override // com.cyjh.mq.sdk.inf.CustomRunner
    public void setSid(long j) {
        if (this.f8895e) {
            m20422a(Ipc.IpcMessage.newBuilder().setCmd(IpcCommand.f8360aG).addArg4(j).build());
        } else {
            MqRunnerLite.getInstance().setSid(j);
        }
    }

    @Override // com.cyjh.mq.sdk.inf.CustomRunner
    public void setDeviceSessionId(String str) {
        if (this.f8895e) {
            m20422a(Ipc.IpcMessage.newBuilder().setCmd(IpcCommand.f8363aJ).addArg2(str).build());
        } else {
            MqRunnerLite.getInstance().setDeviceSessionId(str);
        }
    }

    @Override // com.cyjh.mq.sdk.GeneralMqRunner, com.cyjh.mobileanjian.ipc.interfaces.EngineStateObserver
    public void onConnected(IpcConnection bVar) {
        super.onConnected(bVar);
        recordLog2File(this.f8882i);
        setRequestCallback(this.f8880g);
    }
}
