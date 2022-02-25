package com.kaopu.receiver;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/* loaded from: classes.dex */
public abstract class BroadcastReceiver extends android.content.BroadcastReceiver {
    private Context context;
    private boolean registered;

    @Override // android.content.BroadcastReceiver
    public abstract void onReceive(Context context, Intent intent);

    public void registerReceiver(Context context, IntentFilter intentFilter) {
        if (context != null && !this.registered) {
            context.registerReceiver(this, intentFilter);
            this.context = context;
            this.registered = !this.registered;
        }
    }

    public void unregisterReceiver() {
        if (this.registered) {
            this.context.unregisterReceiver(this);
            this.registered = !this.registered;
        }
    }

    public boolean isRegistered() {
        return this.registered;
    }
}
