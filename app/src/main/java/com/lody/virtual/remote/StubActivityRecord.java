package com.lody.virtual.remote;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.IBinder;
import com.lody.virtual.helper.compat.BundleCompat;

/* loaded from: classes.dex */
public class StubActivityRecord {
    public ActivityInfo info;
    public Intent intent;
    public int userId;
    public IBinder virtualToken;

    public StubActivityRecord(Intent intent, ActivityInfo activityInfo, int i, IBinder iBinder) {
        this.intent = intent;
        this.info = activityInfo;
        this.userId = i;
        this.virtualToken = iBinder;
    }

    public StubActivityRecord(Intent intent) {
        this.intent = (Intent) intent.getParcelableExtra("_VA_|_intent_");
        this.info = (ActivityInfo) intent.getParcelableExtra("_VA_|_info_");
        this.userId = intent.getIntExtra("_VA_|_user_id_", 0);
        this.virtualToken = BundleCompat.getBinder(intent, "_VA_|_token_");
    }

    public void saveToIntent(Intent intent) {
        intent.putExtra("_VA_|_intent_", this.intent);
        intent.putExtra("_VA_|_info_", this.info);
        intent.putExtra("_VA_|_user_id_", this.userId);
        BundleCompat.putBinder(intent, "_VA_|_token_", this.virtualToken);
    }
}
