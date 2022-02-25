package com.nrzs.core.receive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import p110z1.BroadVal;
import p110z1.VAEnginUtils;

/* loaded from: classes2.dex */
public class PXKJReceiveReceive extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        int intExtra = intent.getIntExtra(BroadVal.f16467a, -1);
        if (intExtra == 2) {
            VAEnginUtils.m12918a(intent.getIntExtra(BroadVal.f16470d, -1));
        } else if (intExtra == 1) {
            VAEnginUtils.m12915a(intent.getStringExtra(BroadVal.f16471e));
        }
    }
}
