package com.lody.virtual.client.stub;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.lody.virtual.client.ipc.VActivityManager;
import com.lody.virtual.remote.IntentSenderData;
import com.lody.virtual.remote.IntentSenderExtData;

/* loaded from: classes.dex */
public class ShadowPendingService extends Service {
    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        Intent selector = intent.getSelector();
        if (selector == null) {
            return 2;
        }
        selector.setExtrasClassLoader(IntentSenderExtData.class.getClassLoader());
        Intent intent2 = (Intent) selector.getParcelableExtra("_VA_|_intent_");
        int intExtra = selector.getIntExtra("_VA_|_userId_", -1);
        if (intent2 == null || intExtra == -1) {
            return 2;
        }
        IntentSenderExtData intentSenderExtData = (IntentSenderExtData) intent.getParcelableExtra("_VA_|_ext_");
        if (!(intentSenderExtData == null || intentSenderExtData.sender == null)) {
            IntentSenderData intentSender = VActivityManager.get().getIntentSender(intentSenderExtData.sender);
            Intent intent3 = intentSenderExtData.fillIn;
            if (intent3 != null) {
                intent2.fillIn(intent3, intentSender.flags);
            }
            int i3 = intentSenderExtData.flagsMask & (-196);
            intent2.setFlags((intentSenderExtData.flagsValues & i3) | ((~i3) & intent2.getFlags()));
        }
        VActivityManager.get().startService(intent2, null, intExtra);
        stopSelf();
        return 2;
    }
}
