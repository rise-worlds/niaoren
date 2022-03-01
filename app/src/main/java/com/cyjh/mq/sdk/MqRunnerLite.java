package com.cyjh.mq.sdk;

import android.app.Application;
import com.cyjh.mobileanjian.ipc.ScriptRunnerLite;
import com.cyjh.mobileanjian.ipc.interfaces.OnRequestCallback;
import com.cyjh.mobileanjian.ipc.interfaces.OnScriptListener;
import com.cyjh.mobileanjian.ipc.interfaces.OnSpecialMqCmdCallback;
import com.cyjh.mq.sdk.entity.Script4Run;
import com.cyjh.mq.sdk.inf.CustomRunner;
import com.cyjh.mq.sdk.inf.IRunner;
import com.cyjh.mq.sdk.inf.IRunnerLite;
import com.cyjh.mq.sdk.inf.OnElfCallback;
import com.google.protobuf.ByteString;

/* renamed from: com.cyjh.mq.sdk.MqRunnerLite */
/* loaded from: classes.dex */
public class MqRunnerLite implements CustomRunner, IRunnerLite {

    /* renamed from: a */
    ScriptRunnerLite scriptRunnerLite;

    /* renamed from: b */
    public OnSpecialMqCmdCallback specialMqCmdCallback;

    /* renamed from: c */
    public OnScriptListener scriptListener;

    /* renamed from: d */
    public OnRequestCallback requestCallback;

    /* renamed from: e */
    public boolean f8888e;

    /* renamed from: f */
    private Script4Run script4Run;

    @Override // com.cyjh.mq.sdk.inf.IRunnerOther
    /* renamed from: a */
    public final void mo20414a(ByteString byteString) {
    }

    @Override // com.cyjh.mq.sdk.inf.IRunnerOther
    /* renamed from: a */
    public final void mo20413a(String str) {
    }

    @Override // com.cyjh.mq.sdk.inf.IRunnerOther
    /* renamed from: b */
    public final void mo20411b(ByteString byteString) {
    }

    @Override // com.cyjh.mq.sdk.inf.IRunnerOther
    /* renamed from: c */
    public final OnElfCallback mo20410c() {
        return null;
    }

    @Override // com.cyjh.mq.sdk.inf.IRunnerOther
    /* renamed from: c */
    public final void mo20409c(ByteString byteString) {
    }

    /* synthetic */ MqRunnerLite(byte b) {
        this();
    }

    private MqRunnerLite() {
        this.f8888e = true;
        this.scriptRunnerLite = new ScriptRunnerLite();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.cyjh.mq.sdk.MqRunnerLite$a */
    /* loaded from: classes.dex */
    public static class C1369a {

        /* renamed from: a */
        private static final MqRunnerLite f8890a = new MqRunnerLite((byte) 0);

        private C1369a() {
        }
    }

    public static synchronized MqRunnerLite getInstance() {
        MqRunnerLite mqRunnerLite;
        synchronized (MqRunnerLite.class) {
            mqRunnerLite = C1369a.f8890a;
        }
        return mqRunnerLite;
    }

    @Override // com.cyjh.mq.sdk.inf.IRunner
    public boolean isScriptStarted() {
        ScriptRunnerLite eVar = this.scriptRunnerLite;
        if (eVar == null) {
            return false;
        }
        return eVar.m21013a();
    }

    @Override // com.cyjh.mq.sdk.inf.IRunner
    public IRunner setScript(Script4Run script4Run) {
        this.script4Run = script4Run;
        ScriptRunnerLite eVar = this.scriptRunnerLite;
        if (!(eVar == null || script4Run == null)) {
            eVar.f8249a = script4Run;
        }
        return this;
    }

    @Override // com.cyjh.mq.sdk.inf.IRunner
    public IRunner setOnScriptListener(OnScriptListener onScriptListener) {
        this.scriptListener = onScriptListener;
        ScriptRunnerLite eVar = this.scriptRunnerLite;
        if (!(eVar == null || onScriptListener == null)) {
            eVar.f8250b = onScriptListener;
        }
        return this;
    }

    @Override // com.cyjh.mq.sdk.inf.CustomRunner
    public void setOnSpecialMqCmdCallback(OnSpecialMqCmdCallback onSpecialMqCmdCallback) {
        this.specialMqCmdCallback = onSpecialMqCmdCallback;
    }

    @Override // com.cyjh.mq.sdk.inf.CustomRunner
    public void setRequestCallback(OnRequestCallback onRequestCallback) {
        this.requestCallback = onRequestCallback;
    }

    @Override // com.cyjh.mq.sdk.inf.CustomRunner
    public void recordLog2File(boolean z) {
        this.scriptRunnerLite.m20993g().SetWriteLog2File(z);
    }

    @Override // com.cyjh.mq.sdk.inf.IRunnerLite
    /* renamed from: a */
    public final OnScriptListener getScriptListener() {
        return this.scriptListener;
    }

    @Override // com.cyjh.mq.sdk.inf.IRunnerLite
    /* renamed from: d */
    public final OnSpecialMqCmdCallback getSpecialMqCmdCallback() {
        return this.specialMqCmdCallback;
    }

    @Override // com.cyjh.mq.sdk.inf.IRunnerOther
    /* renamed from: b */
    public final OnRequestCallback getRequestCallback() {
        return this.requestCallback;
    }

    @Override // com.cyjh.mq.sdk.inf.IRunner
    public void start() {
        ScriptRunnerLite eVar = this.scriptRunnerLite;
        if (eVar == null) {
            return;
        }
        if (!eVar.f8251c) {
            try {
                if (eVar.f8249a.isFengwoScript) {
                    eVar.f8256h.put(new ScriptRunnerLite.C1268a(eVar, 0, ""));
                    return;
                }
                new ScriptRunnerLite.C1269b(eVar, (byte) 0).start();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else if (eVar.f8250b != null) {
            eVar.f8250b.onScriptIsRunning();
        }
    }

    @Override // com.cyjh.mq.sdk.inf.IRunner
    public void resume() {
        ScriptRunnerLite eVar = this.scriptRunnerLite;
        if (eVar != null) {
            eVar.m20993g().Resume();
        }
    }

    @Override // com.cyjh.mq.sdk.inf.IRunner
    public void pause() {
        ScriptRunnerLite eVar = this.scriptRunnerLite;
        if (eVar != null) {
            eVar.m20993g().Pause();
        }
    }

    @Override // com.cyjh.mq.sdk.inf.IRunner
    public void stop() {
        ScriptRunnerLite eVar = this.scriptRunnerLite;
        if (eVar != null) {
            eVar.mo20996e();
        }
    }

    @Override // com.cyjh.mq.sdk.inf.IRunner
    public void notifyRotationStatus() {
        this.scriptRunnerLite.m20994f();
    }

    public void setImageCrop(boolean z, int i, int i2, int i3, int i4) {
        this.scriptRunnerLite.m20992h().SetImageCrop(z, i, i2, i3, i4);
    }

    @Override // com.cyjh.mq.sdk.inf.CustomRunner
    public void init(Application application, String str) {
        ScriptRunnerLite eVar = this.scriptRunnerLite;
        try {
            if (!eVar.f8253e) {
                eVar.f8255g = new ScriptRunnerLite.C1270c("ScriptRunnerLite_Request");
                eVar.f8255g.start();
            }
            eVar.f8256h.put(new ScriptRunnerLite.C1268a(application, 1, str));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override // com.cyjh.mq.sdk.inf.CustomRunner
    public void getScriptPerm(String str) {
        this.f8888e = true;
        this.scriptRunnerLite.m21006a(str);
    }

    @Override // com.cyjh.mq.sdk.inf.CustomRunner
    public void download(String str) {
        this.f8888e = true;
        this.scriptRunnerLite.m21006a(str);
    }

    @Override // com.cyjh.mq.sdk.inf.CustomRunner
    public void setSid(long j) {
        this.f8888e = true;
        ScriptRunnerLite eVar = this.scriptRunnerLite;
        try {
            eVar.m20993g().SetSid(Long.valueOf(new ScriptRunnerLite.C1268a(eVar, 17, String.valueOf(j)).f8269i).longValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.cyjh.mq.sdk.inf.CustomRunner
    public void setDeviceSessionId(String str) {
        this.f8888e = true;
        ScriptRunnerLite eVar = this.scriptRunnerLite;
        try {
            eVar.m20993g().SetDeviceSessionId(new ScriptRunnerLite.C1268a(eVar, 18, str).f8269i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.cyjh.mq.sdk.inf.IRunnerLite
    /* renamed from: e */
    public final boolean mo20415e() {
        return this.f8888e;
    }

    /* renamed from: b */
    private void m20427b(String str) {
        this.f8888e = false;
        this.scriptRunnerLite.m21006a(str);
    }

    /* renamed from: c */
    private void m20426c(String str) {
        this.f8888e = false;
        this.scriptRunnerLite.m21006a(str);
    }

    /* renamed from: f */
    private void m20425f() {
        ScriptRunnerLite eVar = this.scriptRunnerLite;
        eVar.f8254f = false;
        eVar.mo20996e();
        try {
            eVar.f8256h.put(new ScriptRunnerLite.C1268a(eVar, 16, ""));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
