package com.cyjh.mobileanjian.ipc.p042a;

import android.os.Handler;
import android.os.Message;
import com.cyjh.mobileanjian.ipc.interfaces.OnRecordScriptCallback;

/* renamed from: com.cyjh.mobileanjian.ipc.a.b */
/* loaded from: classes.dex */
public final class OnRecordScriptHandler implements OnRecordScriptCallback {

    /* renamed from: a */
    public String f8206a = "";

    /* renamed from: b */
    private Handler f8207b;

    @Override // com.cyjh.mobileanjian.ipc.interfaces.OnRecordScriptCallback
    public final void onOpenRecord() {
    }

    public OnRecordScriptHandler(Handler handler) {
        this.f8207b = null;
        this.f8207b = handler;
    }

    @Override // com.cyjh.mobileanjian.ipc.interfaces.OnRecordScriptCallback
    public final void onStartRecord(int i) {
        Message message = new Message();
        message.arg1 = i;
        message.what = 1;
        this.f8207b.sendMessage(message);
    }

    @Override // com.cyjh.mobileanjian.ipc.interfaces.OnRecordScriptCallback
    public final void onFinishRecord(String str) {
        Message message = new Message();
        message.obj = str;
        message.what = 3;
        this.f8207b.sendMessage(message);
    }
}
