package com.tendcloud.tenddata;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.tendcloud.tenddata.C2945hn;
import java.util.HashMap;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.gn */
/* loaded from: classes2.dex */
public class HandlerC2914gn extends Handler {
    String appId;
    String channelId;
    AbstractC2790d features;
    final /* synthetic */ C2913gm this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HandlerC2914gn(C2913gm gmVar, Looper looper) {
        super(looper);
        this.this$0 = gmVar;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        try {
            if (message.obj != null) {
                HashMap hashMap = (HashMap) message.obj;
                this.appId = String.valueOf(hashMap.get("appId"));
                this.channelId = String.valueOf(hashMap.get("channelId"));
                this.features = (AbstractC2790d) hashMap.get("Features");
            }
            int i = message.what;
            if (i != 3) {
                switch (i) {
                    case 0:
                        this.this$0.m15576b(this.appId, this.channelId, this.features);
                        return;
                    case 1:
                        boolean unused = C2913gm.f14097z = false;
                        this.this$0.m15576b(this.appId, this.channelId, this.features);
                        return;
                    default:
                        return;
                }
            } else {
                C2947ho hoVar = new C2947ho();
                hoVar.f14181b = "app";
                hoVar.f14182c = "initaddition";
                hoVar.f14180a = this.features;
                C2858ev.m15778a().post(hoVar);
                C2945hn hnVar = new C2945hn();
                hnVar.f14178a = this.features;
                hnVar.f14179b = C2945hn.EnumC2946a.IMMEDIATELY;
                C2858ev.m15778a().post(hnVar);
            }
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }
}
