package com.cyjh.ddy.media.oksocket;

import android.os.Handler;
import android.os.Looper;
import com.xuhao.didi.socket.client.impl.client.action.ActionDispatcher;
import com.xuhao.didi.socket.client.sdk.client.OkSocketOptions;

/* renamed from: com.cyjh.ddy.media.oksocket.a */
/* loaded from: classes.dex */
public class LocalToken extends OkSocketOptions.ThreadModeToken {

    /* renamed from: a */
    Handler f7395a = new Handler(Looper.getMainLooper());

    @Override // com.xuhao.didi.socket.client.sdk.client.OkSocketOptions.ThreadModeToken
    public void handleCallbackEvent(ActionDispatcher.ActionRunnable actionRunnable) {
        this.f7395a.post(actionRunnable);
    }
}
