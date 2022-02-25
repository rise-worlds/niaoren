package com.cyjh.mobileanjian.ipc.rpc;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.telephony.SmsManager;
import com.tencent.smtt.sdk.WebView;

/* loaded from: classes.dex */
public class Telephony {

    /* renamed from: a */
    private static Context f8304a;

    public static void init(Context context) {
        f8304a = context;
    }

    public static void dialNumber(String str, String str2) {
        Intent intent;
        if (str2.equals("1")) {
            intent = new Intent("android.intent.action.CALL", Uri.parse(WebView.SCHEME_TEL + str));
        } else {
            intent = new Intent("android.intent.action.DIAL", Uri.parse(WebView.SCHEME_TEL + str));
        }
        intent.addFlags(268435456);
        f8304a.startActivity(intent);
    }

    public static void sendSMS(String str, String str2) {
        SmsManager smsManager = SmsManager.getDefault();
        if (smsManager != null) {
            smsManager.sendTextMessage(str, null, str2, null, null);
        }
    }
}
