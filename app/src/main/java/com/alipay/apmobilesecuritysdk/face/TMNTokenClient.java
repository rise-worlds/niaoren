package com.alipay.apmobilesecuritysdk.face;

import android.content.Context;
import com.alipay.apmobilesecuritysdk.otherid.UtdidWrapper;
import com.alipay.apmobilesecuritysdk.p011a.C0621a;
import com.alipay.apmobilesecuritysdk.p016f.C0641b;
import com.lody.virtual.client.stub.ChooseTypeAndAccountActivity;
import java.util.HashMap;
import p110z1.C3877as;
import p110z1.C5097cq;

/* loaded from: classes.dex */
public class TMNTokenClient {

    /* renamed from: a */
    private static TMNTokenClient f253a;

    /* renamed from: b */
    private Context f254b;

    /* loaded from: classes.dex */
    public interface InitResultListener {
        void onResult(String str, int i);
    }

    private TMNTokenClient(Context context) {
        this.f254b = null;
        if (context != null) {
            this.f254b = context;
            return;
        }
        throw new IllegalArgumentException("TMNTokenClient initialization error: context is null.");
    }

    public static TMNTokenClient getInstance(Context context) {
        if (f253a == null) {
            synchronized (TMNTokenClient.class) {
                if (f253a == null) {
                    f253a = new TMNTokenClient(context);
                }
            }
        }
        return f253a;
    }

    public void intiToken(final String str, String str2, String str3, final InitResultListener initResultListener) {
        if (C5097cq.m3699a(str) && initResultListener != null) {
            initResultListener.onResult("", 2);
        }
        if (C5097cq.m3699a(str2) && initResultListener != null) {
            initResultListener.onResult("", 3);
        }
        final HashMap hashMap = new HashMap();
        hashMap.put(C3877as.f17455g, UtdidWrapper.getUtdid(this.f254b));
        hashMap.put("tid", "");
        hashMap.put(ChooseTypeAndAccountActivity.KEY_USER_ID, "");
        hashMap.put("appName", str);
        hashMap.put("appKeyClient", str2);
        hashMap.put("appchannel", "openapi");
        hashMap.put("sessionId", str3);
        hashMap.put("rpcVersion", "8");
        C0641b.m25346a().m25344a(new Runnable() { // from class: com.alipay.apmobilesecuritysdk.face.TMNTokenClient.1
            @Override // java.lang.Runnable
            public void run() {
                int a = new C0621a(TMNTokenClient.this.f254b).m25438a(hashMap);
                InitResultListener initResultListener2 = initResultListener;
                if (initResultListener2 != null) {
                    if (a == 0) {
                        initResultListener.onResult(C0621a.m25439a(TMNTokenClient.this.f254b, str), 0);
                        return;
                    }
                    initResultListener2.onResult("", a);
                }
            }
        });
    }
}
