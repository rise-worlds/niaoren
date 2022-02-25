package com.lody.virtual.client.stub;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.ipc.VActivityManager;
import com.lody.virtual.remote.IntentSenderData;
import com.lody.virtual.remote.IntentSenderExtData;

/* loaded from: classes.dex */
public class ShadowPendingActivity extends Activity {
    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        Intent intent;
        super.onCreate(bundle);
        finish();
        Intent intent2 = getIntent();
        Intent selector = intent2.getSelector();
        if (selector == null) {
            Log.i("efdf2", "selector = null");
            return;
        }
        selector.setExtrasClassLoader(IntentSenderExtData.class.getClassLoader());
        Intent intent3 = (Intent) selector.getParcelableExtra("_VA_|_intent_");
        int intExtra = selector.getIntExtra("_VA_|_userId_", -1);
        if (intent3 == null || intExtra == -1) {
            Log.i("efdf2", "targetIntent = null");
            return;
        }
        IntentSenderExtData intentSenderExtData = (IntentSenderExtData) intent2.getParcelableExtra("_VA_|_ext_");
        if (intentSenderExtData == null || intentSenderExtData.sender == null) {
            Log.i("efdf2", "ext = null && ext.sender == null");
            VActivityManager.get().startActivity(intent3, intExtra);
            return;
        }
        IntentSenderData intentSender = VActivityManager.get().getIntentSender(intentSenderExtData.sender);
        Log.i("efdf2", "finalIntent ---- 1");
        if (intent3 == null || intentSender.intent == null || intent3.getExtras() == null || intentSender.intent == null) {
            intent = intent3;
        } else {
            Log.i("efdf2", "finalIntent ---- 2");
            intent = intentSender.intent;
        }
        Log.i("efdf2", "finalIntent ---- 3");
        Intent intent4 = intentSenderExtData.fillIn;
        if (intent4 != null) {
            intent.fillIn(intent4, intentSender.flags);
        }
        Log.i("efdf2", "finalIntent ---- 4");
        int i = intentSenderExtData.flagsMask & (-196);
        int i2 = intentSenderExtData.flagsValues & i;
        Log.i("efdf2", "finalIntent ---- 5");
        intent.setFlags(((~i) & intent.getFlags()) | i2);
        Log.i("efdf2", "finalIntent ---- 6");
        ActivityInfo resolveActivityInfo = VirtualCore.get().resolveActivityInfo(intent2, intentSender.userId);
        Log.i("efdf2", "finalIntent ---- 7");
        int startActivity = VActivityManager.get().startActivity(intent, resolveActivityInfo, intentSenderExtData.resultTo, intentSenderExtData.options, intentSenderExtData.resultWho, intentSenderExtData.requestCode, intentSender.userId);
        Log.i("efdf2", "finalIntent ---- 8");
        if (startActivity == 0 || intentSenderExtData.resultTo == null || intentSenderExtData.requestCode <= 0) {
            Log.i("efdf2", "res != 0 && ext.resultTo != null && ext.requestCode > 0");
        } else {
            VActivityManager.get().sendCancelActivityResult(intentSenderExtData.resultTo, intentSenderExtData.resultWho, intentSenderExtData.requestCode);
        }
    }
}
