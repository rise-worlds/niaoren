package com.cyjh.mobileanjian.ipc.uip;

import android.util.Log;
import com.cyjh.mobileanjian.ipc.share.proto.UiMessage;
import com.cyjh.mqm.MQUipStub;

/* loaded from: classes.dex */
public class UisScriptRunner {

    /* renamed from: a */
    private static UisScriptRunner f8630a = null;

    /* renamed from: b */
    private static boolean f8631b = false;

    /* renamed from: c */
    private MQUipStub f8632c = new MQUipStub();

    private UisScriptRunner() {
    }

    public static UisScriptRunner getInstance() {
        if (f8630a == null) {
            f8630a = new UisScriptRunner();
        }
        return f8630a;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.cyjh.mobileanjian.ipc.uip.UisScriptRunner$1] */
    public void startLoop(final String str) {
        if (f8631b) {
            stopLoop();
            while (f8631b) {
                m20707a();
            }
        }
        new Thread() { // from class: com.cyjh.mobileanjian.ipc.uip.UisScriptRunner.1
            @Override // java.lang.Thread, java.lang.Runnable
            public final void run() {
                super.run();
                Log.i("LBSAAA", "开始运行UIS脚本");
                boolean unused = UisScriptRunner.f8631b = true;
                UisScriptRunner.this.f8632c.StartLoop(str, 0L);
                Log.i("LBSAAA", "已停止UIS脚本");
                boolean unused2 = UisScriptRunner.f8631b = false;
            }
        }.start();
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.cyjh.mobileanjian.ipc.uip.UisScriptRunner$2] */
    public void startLoop(final byte[] bArr) {
        if (f8631b) {
            stopLoop();
            while (f8631b) {
                m20707a();
            }
        }
        new Thread() { // from class: com.cyjh.mobileanjian.ipc.uip.UisScriptRunner.2
            @Override // java.lang.Thread, java.lang.Runnable
            public final void run() {
                super.run();
                boolean unused = UisScriptRunner.f8631b = true;
                UisScriptRunner.this.f8632c.StartLoop(bArr, 0L);
                boolean unused2 = UisScriptRunner.f8631b = false;
            }
        }.start();
    }

    public void stopLoop() {
        Log.i("LBSAAA", " stopLoop 1");
        if (f8631b) {
            Log.i("LBSAAA", " stopLoop 2");
            this.f8632c.StopLoop();
            UipEventStub.hasEvent(UiMessage.UipToCommand.newBuilder().setCommand(UiMessage.UipToCommand.Command_Type.EVENT).setIsSuccess(true).setEvent(UiMessage.ControlEvent.newBuilder().setControlId("stop_id").setType(UiMessage.ControlEvent.Event_Type.ON_CLOSE_EXIT).build()).build().toByteString());
        }
        Log.i("LBSAAA", " stopLoop 3");
    }

    /* renamed from: a */
    private static void m20707a() {
        try {
            Thread.sleep(50L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
