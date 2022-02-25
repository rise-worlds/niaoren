package com.lbd.p054xj.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.common.utils.StringUtil;
import p110z1.EventBus;
import p110z1.KeyCodeEvent;

/* renamed from: com.lbd.xj.receiver.KeyCodeReceiver */
/* loaded from: classes.dex */
public class KeyCodeReceiver extends BroadcastReceiver {

    /* renamed from: a */
    public static final String f9484a = "KEYCODE_BACK";

    /* renamed from: b */
    public static final String f9485b = "KEYCODE_HOME";

    /* renamed from: c */
    public static final String f9486c = "KEYCODE_WIN_HOME";

    /* renamed from: d */
    public static final String f9487d = "XJKEYACTION";

    /* renamed from: e */
    public static final String f9488e = "LOCATION_KEY";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (!StringUtil.isEmpty(action) && "XJKEYACTION".equals(action)) {
            String stringExtra = intent.getStringExtra(f9488e);
            if (f9484a.equals(stringExtra)) {
                EventBus.m3448a().m3427d(new KeyCodeEvent(f9484a));
            } else if (f9485b.equals(stringExtra)) {
                EventBus.m3448a().m3427d(new KeyCodeEvent(f9485b));
            } else if (f9486c.equals(stringExtra)) {
                EventBus.m3448a().m3427d(new KeyCodeEvent(f9485b));
            }
        }
    }
}
