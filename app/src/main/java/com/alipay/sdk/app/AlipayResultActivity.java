package com.alipay.sdk.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Base64;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;
import p110z1.C3754ao;
import p110z1.C3857aq;
import p110z1.C4745bt;
import p110z1.C4985cm;

/* loaded from: classes.dex */
public class AlipayResultActivity extends Activity {

    /* renamed from: a */
    public static final ConcurrentHashMap<String, AbstractC0645a> f259a = new ConcurrentHashMap<>();

    /* renamed from: com.alipay.sdk.app.AlipayResultActivity$a */
    /* loaded from: classes.dex */
    public interface AbstractC0645a {
        /* renamed from: a */
        void mo5265a(int i, String str, String str2);
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        Throwable th;
        super.onCreate(bundle);
        try {
            Intent intent = getIntent();
            String stringExtra = intent.getStringExtra("session");
            Bundle bundleExtra = intent.getBundleExtra(C4985cm.f20833c);
            String stringExtra2 = intent.getStringExtra("scene");
            C4745bt a = C4745bt.C4746a.m9404a(stringExtra);
            if (a == null) {
                finish();
                return;
            }
            C3754ao.m12151b(a, C3857aq.f17251b, "BSPSession", stringExtra + "|" + SystemClock.elapsedRealtime());
            if (TextUtils.equals("mqpSchemePay", stringExtra2)) {
                m25340a(stringExtra, bundleExtra);
                return;
            }
            if ((TextUtils.isEmpty(stringExtra) || bundleExtra == null) && intent.getData() != null) {
                try {
                    JSONObject jSONObject = new JSONObject(new String(Base64.decode(intent.getData().getQuery(), 2), "UTF-8"));
                    JSONObject jSONObject2 = jSONObject.getJSONObject(C4985cm.f20833c);
                    stringExtra = jSONObject.getString("session");
                    C3754ao.m12151b(a, C3857aq.f17251b, "BSPUriSession", stringExtra);
                    Bundle bundle2 = new Bundle();
                    try {
                        Iterator<String> keys = jSONObject2.keys();
                        while (keys.hasNext()) {
                            String next = keys.next();
                            bundle2.putString(next, jSONObject2.getString(next));
                        }
                        bundleExtra = bundle2;
                    } catch (Throwable th2) {
                        th = th2;
                        bundleExtra = bundle2;
                        C3754ao.m12155a(a, C3857aq.f17251b, "BSPResEx", th);
                        C3754ao.m12155a(a, C3857aq.f17251b, C3857aq.f17244ai, th);
                        if (!TextUtils.isEmpty(stringExtra)) {
                        }
                        C3754ao.m12152b(this, a, "", a.f20085p);
                        finish();
                        return;
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
            }
            if (!TextUtils.isEmpty(stringExtra) || bundleExtra == null) {
                C3754ao.m12152b(this, a, "", a.f20085p);
                finish();
                return;
            }
            C3754ao.m12151b(a, C3857aq.f17251b, C3857aq.f17222N, "" + SystemClock.elapsedRealtime());
            C0650b.m25297a(stringExtra, (int) C0650b.f298a, "OK", bundleExtra);
            C3754ao.m12152b(this, a, "", a.f20085p);
            finish();
        } catch (Throwable unused) {
            finish();
        }
    }

    /* renamed from: a */
    private void m25340a(String str, Bundle bundle) {
        AbstractC0645a remove = f259a.remove(str);
        if (remove != null) {
            try {
                remove.mo5265a(bundle.getInt("endCode"), bundle.getString(C4985cm.f20832b), bundle.getString(C4985cm.f20833c));
            } finally {
                finish();
            }
        }
    }
}
