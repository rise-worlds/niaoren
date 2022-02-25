package com.lody.virtual.client.stub;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.lody.virtual.client.ipc.VActivityManager;
import com.lody.virtual.helper.utils.ComponentUtils;
import com.lody.virtual.remote.IntentSenderData;
import com.lody.virtual.remote.IntentSenderExtData;

/* loaded from: classes.dex */
public class ShadowPendingReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Intent selector = intent.getSelector();
        if (selector != null) {
            selector.setExtrasClassLoader(IntentSenderExtData.class.getClassLoader());
            Intent intent2 = (Intent) selector.getParcelableExtra("_VA_|_intent_");
            int intExtra = selector.getIntExtra("_VA_|_userId_", -1);
            if (intent2 != null && intExtra != -1) {
                IntentSenderExtData intentSenderExtData = (IntentSenderExtData) intent.getParcelableExtra("_VA_|_ext_");
                if (!(intentSenderExtData == null || intentSenderExtData.sender == null)) {
                    IntentSenderData intentSender = VActivityManager.get().getIntentSender(intentSenderExtData.sender);
                    Intent intent3 = intentSenderExtData.fillIn;
                    if (intent3 != null) {
                        intent2.fillIn(intent3, intentSender.flags);
                    }
                    int i = intentSenderExtData.flagsMask & (-196);
                    intent2.setFlags((intentSenderExtData.flagsValues & i) | ((~i) & intent2.getFlags()));
                }
                context.sendBroadcast(ComponentUtils.redirectBroadcastIntent(intent2, intExtra));
            }
        }
    }
}
