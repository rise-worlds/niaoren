package com.alipay.apmobilesecuritysdk.face;

import android.content.Context;
import com.alipay.apmobilesecuritysdk.otherid.UmidSdkWrapper;
import com.alipay.apmobilesecuritysdk.otherid.UtdidWrapper;
import com.alipay.apmobilesecuritysdk.p011a.C0621a;
import com.alipay.apmobilesecuritysdk.p012b.C0622a;
import com.alipay.apmobilesecuritysdk.p015e.C0631a;
import com.alipay.apmobilesecuritysdk.p015e.C0634d;
import com.alipay.apmobilesecuritysdk.p015e.C0637g;
import com.alipay.apmobilesecuritysdk.p015e.C0638h;
import com.alipay.apmobilesecuritysdk.p015e.C0639i;
import com.alipay.apmobilesecuritysdk.p016f.C0641b;
import com.lody.virtual.client.stub.ChooseTypeAndAccountActivity;
import java.util.HashMap;
import java.util.Map;
import p110z1.C3877as;
import p110z1.C5097cq;

/* loaded from: classes.dex */
public class APSecuritySdk {

    /* renamed from: a */
    private static APSecuritySdk f246a;

    /* renamed from: c */
    private static Object f247c = new Object();

    /* renamed from: b */
    private Context f248b;

    /* loaded from: classes.dex */
    public interface InitResultListener {
        void onResult(TokenResult tokenResult);
    }

    /* loaded from: classes.dex */
    public class TokenResult {
        public String apdid;
        public String apdidToken;
        public String clientKey;
        public String umidToken;

        public TokenResult() {
        }
    }

    private APSecuritySdk(Context context) {
        this.f248b = context;
    }

    public static APSecuritySdk getInstance(Context context) {
        if (f246a == null) {
            synchronized (f247c) {
                if (f246a == null) {
                    f246a = new APSecuritySdk(context);
                }
            }
        }
        return f246a;
    }

    public static String getUtdid(Context context) {
        return UtdidWrapper.getUtdid(context);
    }

    public String getApdidToken() {
        String a = C0621a.m25439a(this.f248b, "");
        if (C5097cq.m3699a(a)) {
            initToken(0, new HashMap(), null);
        }
        return a;
    }

    public String getSdkName() {
        return "APPSecuritySDK-ALIPAYSDK";
    }

    public String getSdkVersion() {
        return "3.4.0.201910161639";
    }

    public synchronized TokenResult getTokenResult() {
        TokenResult tokenResult;
        tokenResult = new TokenResult();
        try {
            tokenResult.apdidToken = C0621a.m25439a(this.f248b, "");
            tokenResult.clientKey = C0638h.m25372f(this.f248b);
            tokenResult.apdid = C0621a.m25440a(this.f248b);
            tokenResult.umidToken = UmidSdkWrapper.getSecurityToken(this.f248b);
            if (C5097cq.m3699a(tokenResult.apdid) || C5097cq.m3699a(tokenResult.apdidToken) || C5097cq.m3699a(tokenResult.clientKey)) {
                initToken(0, new HashMap(), null);
            }
        } catch (Throwable unused) {
        }
        return tokenResult;
    }

    public void initToken(int i, Map<String, String> map, final InitResultListener initResultListener) {
        C0622a.m25434a().m25433a(i);
        String b = C0638h.m25380b(this.f248b);
        String c = C0622a.m25434a().m25431c();
        if (C5097cq.m3695b(b) && !C5097cq.m3698a(b, c)) {
            C0631a.m25413a(this.f248b);
            C0634d.m25406a(this.f248b);
            C0637g.m25388a(this.f248b);
            C0639i.m25351h();
        }
        if (!C5097cq.m3698a(b, c)) {
            C0638h.m25377c(this.f248b, c);
        }
        String a = C5097cq.m3696a(map, C3877as.f17455g, "");
        String a2 = C5097cq.m3696a(map, "tid", "");
        String a3 = C5097cq.m3696a(map, ChooseTypeAndAccountActivity.KEY_USER_ID, "");
        if (C5097cq.m3699a(a)) {
            a = UtdidWrapper.getUtdid(this.f248b);
        }
        final HashMap hashMap = new HashMap();
        hashMap.put(C3877as.f17455g, a);
        hashMap.put("tid", a2);
        hashMap.put(ChooseTypeAndAccountActivity.KEY_USER_ID, a3);
        hashMap.put("appName", "");
        hashMap.put("appKeyClient", "");
        hashMap.put("appchannel", "");
        hashMap.put("rpcVersion", "8");
        C0641b.m25346a().m25344a(new Runnable() { // from class: com.alipay.apmobilesecuritysdk.face.APSecuritySdk.1
            @Override // java.lang.Runnable
            public void run() {
                new C0621a(APSecuritySdk.this.f248b).m25438a(hashMap);
                InitResultListener initResultListener2 = initResultListener;
                if (initResultListener2 != null) {
                    initResultListener2.onResult(APSecuritySdk.this.getTokenResult());
                }
            }
        });
    }
}
