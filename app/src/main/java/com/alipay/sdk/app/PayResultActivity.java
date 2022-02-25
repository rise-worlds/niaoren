package com.alipay.sdk.app;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import p110z1.C3754ao;
import p110z1.C3857aq;
import p110z1.C4745bt;
import p110z1.C4921cd;

/* loaded from: classes.dex */
public final class PayResultActivity extends Activity {

    /* renamed from: a */
    public static final String f271a = "{\"isLogin\":\"false\"}";

    /* renamed from: b */
    public static final HashMap<String, Object> f272b = new HashMap<>();

    /* renamed from: c */
    public static final String f273c = "hk.alipay.wallet";

    /* renamed from: d */
    public static final String f274d = "phonecashier.pay.hash";

    /* renamed from: e */
    public static final String f275e = "orderSuffix";

    /* renamed from: f */
    public static final String f276f = "externalPkgName";

    /* renamed from: g */
    public static final String f277g = "phonecashier.pay.result";

    /* renamed from: h */
    public static final String f278h = "phonecashier.pay.resultOrderHash";

    /* renamed from: i */
    private C4745bt f279i = null;

    /* renamed from: com.alipay.sdk.app.PayResultActivity$a */
    /* loaded from: classes.dex */
    public static final class C0646a {

        /* renamed from: a */
        public static volatile String f280a;

        /* renamed from: b */
        public static volatile String f281b;
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        try {
            Intent intent = getIntent();
            if (!TextUtils.isEmpty(intent.getStringExtra(f275e))) {
                C0646a.f280a = intent.getStringExtra(f274d);
                String stringExtra = intent.getStringExtra(f275e);
                String stringExtra2 = intent.getStringExtra(f276f);
                this.f279i = C4745bt.C4746a.m9405a(intent);
                if (this.f279i == null) {
                    finish();
                }
                m25329a(this, C0646a.f280a, stringExtra, stringExtra2);
                m25330a(this, 300);
                return;
            }
            if (this.f279i == null) {
                finish();
            }
            String stringExtra3 = intent.getStringExtra(f277g);
            int intExtra = intent.getIntExtra(f278h, 0);
            if (intExtra != 0 && TextUtils.equals(C0646a.f280a, String.valueOf(intExtra))) {
                if (!TextUtils.isEmpty(stringExtra3)) {
                    m25327a(stringExtra3, C0646a.f280a);
                } else {
                    m25328a(C0646a.f280a);
                }
                C0646a.f280a = "";
                m25330a(this, 300);
                return;
            }
            C4745bt btVar = this.f279i;
            C3754ao.m12156a(btVar, C3857aq.f17251b, C3857aq.f17236aa, "Expected " + C0646a.f280a + ", got " + intExtra);
            m25328a(C0646a.f280a);
            m25330a(this, 300);
        } catch (Throwable unused) {
            finish();
        }
    }

    /* renamed from: a */
    private static void m25329a(Activity activity, String str, String str2, String str3) {
        if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3)) {
            Intent intent = new Intent();
            try {
                intent.setPackage(f273c);
                intent.setData(Uri.parse("alipayhk://platformapi/startApp?appId=20000125&schemePaySession=" + URLEncoder.encode(str, "UTF-8") + "&orderSuffix=" + URLEncoder.encode(str2, "UTF-8") + "&packageName=" + URLEncoder.encode(str3, "UTF-8") + "&externalPkgName=" + URLEncoder.encode(str3, "UTF-8")));
            } catch (UnsupportedEncodingException e) {
                C4921cd.m5618a(e);
            }
            if (activity != null) {
                try {
                    activity.startActivity(intent);
                } catch (Throwable unused) {
                    activity.finish();
                }
            }
        }
    }

    /* renamed from: a */
    private static void m25328a(String str) {
        C0646a.f281b = C0663l.m25282c();
        m25326a(f272b, str);
    }

    /* renamed from: a */
    private static void m25327a(String str, String str2) {
        C0646a.f281b = str;
        m25326a(f272b, str2);
    }

    /* renamed from: a */
    private static void m25330a(Activity activity, int i) {
        new Handler().postDelayed(new RunnableC0659h(activity), i);
    }

    /* renamed from: a */
    private static boolean m25326a(HashMap<String, Object> hashMap, String str) {
        Object obj;
        if (hashMap == null || str == null || (obj = hashMap.get(str)) == null) {
            return false;
        }
        synchronized (obj) {
            obj.notifyAll();
        }
        return true;
    }
}
