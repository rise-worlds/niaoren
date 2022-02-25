package com.cyjh.mobileanjian.ipc.p042a;

import com.cyjh.mobileanjian.ipc.CrashRunnerState;
import com.cyjh.mobileanjian.ipc.RootManager;
import com.cyjh.mobileanjian.ipc.interfaces.EngineStateObserver;
import com.cyjh.mobileanjian.ipc.interfaces.OnRecordScriptCallback;
import com.cyjh.mobileanjian.ipc.share.proto.Ipc;
import com.cyjh.mobileanjian.ipc.share.proto.IpcRaw;
import com.cyjh.mobileanjian.ipc.utils.RootUtil;
import com.cyjh.mq.p048c.IpcConnection;

/* renamed from: com.cyjh.mobileanjian.ipc.a.d */
/* loaded from: classes.dex */
public final class ScriptRecorder implements EngineStateObserver {

    /* renamed from: c */
    private static ScriptRecorder f8211c;

    /* renamed from: a */
    IpcConnection f8212a = null;

    /* renamed from: b */
    OnRecordScriptCallback f8213b = null;

    /* renamed from: d */
    private boolean f8214d = false;

    /* renamed from: e */
    private boolean f8215e = false;

    @Override // com.cyjh.mobileanjian.ipc.interfaces.EngineStateObserver
    public final void onCrash(CrashRunnerState bVar) {
    }

    @Override // com.cyjh.mobileanjian.ipc.interfaces.EngineStateObserver
    public final void onExit() {
    }

    private ScriptRecorder() {
    }

    /* renamed from: a */
    public static synchronized ScriptRecorder m21052a() {
        ScriptRecorder dVar;
        synchronized (ScriptRecorder.class) {
            if (f8211c == null) {
                f8211c = new ScriptRecorder();
            }
            dVar = f8211c;
        }
        return dVar;
    }

    /* renamed from: a */
    private void m21051a(OnRecordScriptCallback onRecordScriptCallback) {
        this.f8213b = onRecordScriptCallback;
        IpcConnection bVar = this.f8212a;
        if (bVar != null) {
            bVar.f8812h = this.f8213b;
        }
    }

    /* renamed from: b */
    public final void m21049b() {
        if (this.f8212a == null) {
            this.f8214d = true;
            if (RootUtil.isRoot()) {
                RootManager.m21044a().f8225g = 2;
                RootManager.m21044a().m21039b();
                return;
            }
            return;
        }
        this.f8212a.m20531a(Ipc.IpcMessage.newBuilder().setCmd(18).build());
    }

    /* renamed from: a */
    public final void m21050a(boolean z) {
        this.f8215e = z;
        if (this.f8212a != null) {
            this.f8212a.m20531a(Ipc.IpcMessage.newBuilder().setCmd(26).addArg1(this.f8215e ? 1 : 0).build());
        }
    }

    /* renamed from: c */
    private void m21048c() {
        if (this.f8212a == null) {
            RootManager.m21044a().f8225g = 2;
            RootManager.m21044a().m21039b();
            return;
        }
        this.f8212a.m20531a(Ipc.IpcMessage.newBuilder().setCmd(20).build());
    }

    /* renamed from: d */
    private void m21047d() {
        IpcConnection bVar = this.f8212a;
        if (bVar != null) {
            bVar.m20531a(IpcRaw.m20933a(22));
        }
    }

    @Override // com.cyjh.mobileanjian.ipc.interfaces.EngineStateObserver
    public final void onConnected(IpcConnection bVar) {
        this.f8212a = bVar;
        this.f8212a.f8812h = this.f8213b;
        m21050a(this.f8215e);
        if (this.f8214d) {
            this.f8214d = false;
            m21049b();
        }
    }
}
