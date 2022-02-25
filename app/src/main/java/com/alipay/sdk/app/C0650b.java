package com.alipay.sdk.app;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Base64;
import com.lody.virtual.client.ipc.ServiceManagerNative;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;
import p110z1.C3754ao;
import p110z1.C3857aq;
import p110z1.C3894au;
import p110z1.C4745bt;
import p110z1.C4759bu;
import p110z1.C4921cd;
import p110z1.C5019co;

/* renamed from: com.alipay.sdk.app.b */
/* loaded from: classes.dex */
public final class C0650b {

    /* renamed from: a */
    public static final int f298a = 9000;

    /* renamed from: b */
    public static final int f299b = 5000;

    /* renamed from: c */
    public static final int f300c = 4001;

    /* renamed from: d */
    public static final int f301d = 4000;

    /* renamed from: e */
    private static final Map<String, AbstractC0652b> f302e = new ConcurrentHashMap();

    /* renamed from: f */
    private static long f303f = -1;

    /* renamed from: g */
    private static final int f304g = 122;

    /* renamed from: i */
    private final Activity f306i;

    /* renamed from: j */
    private AbstractC0652b f307j;

    /* renamed from: h */
    private volatile boolean f305h = false;

    /* renamed from: k */
    private final Handler f308k = new Handler(Looper.getMainLooper());

    /* renamed from: com.alipay.sdk.app.b$b */
    /* loaded from: classes.dex */
    public interface AbstractC0652b {
        /* renamed from: a */
        void m25294a(int i, String str, Bundle bundle);
    }

    /* renamed from: com.alipay.sdk.app.b$a */
    /* loaded from: classes.dex */
    public enum EnumC0651a {
        Invoice("20000920"),
        AccountAuth("20000067"),
        Deduct("60000157");
        
        private String appId;

        EnumC0651a(String str) {
            this.appId = str;
        }
    }

    public C0650b(Activity activity) {
        this.f306i = activity;
        C4759bu.m9273a().m9272a(activity);
    }

    /* renamed from: a */
    public void m25296a(String str, EnumC0651a aVar, Map<String, String> map, AbstractC0652b bVar, boolean z) {
        Activity activity = this.f306i;
        String valueOf = String.valueOf(map);
        C4745bt btVar = new C4745bt(activity, valueOf, "oa-" + aVar);
        this.f307j = bVar;
        if (m25295a(btVar, str, aVar, map, z)) {
            C3754ao.m12152b(this.f306i, btVar, "", btVar.f20085p);
        }
    }

    /* renamed from: a */
    private boolean m25295a(C4745bt btVar, String str, EnumC0651a aVar, Map<String, String> map, boolean z) {
        if (this.f305h) {
            this.f308k.post(new RunnableC0653c(this, f301d, "该 OpenAuthTask 已在执行", null, null));
            return true;
        }
        this.f305h = true;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (elapsedRealtime - f303f <= 3000) {
            this.f308k.post(new RunnableC0653c(this, 5000, "3s 内重复支付", null, null));
            return true;
        }
        f303f = elapsedRealtime;
        String a = C5019co.m4503a(32);
        HashMap hashMap = new HashMap(map);
        hashMap.put("mqpPkgName", this.f306i.getPackageName());
        hashMap.put("mqpScene", "sdk");
        List<C3894au.C3895a> h = C3894au.m9967i().m9968h();
        if (!C3894au.m9967i().f17539s || h == null) {
            h = C0662k.f328a;
        }
        C5019co.C5020a a2 = C5019co.m4493a(btVar, this.f306i, h);
        if (a2 != null && !a2.m4468a(btVar) && !a2.m4469a() && a2.f20928a != null && a2.f20928a.versionCode >= 122) {
            try {
                HashMap<String, String> a3 = C4745bt.m9413a(btVar);
                a3.put("ts_scheme", String.valueOf(SystemClock.elapsedRealtime()));
                hashMap.put("mqpLoc", new JSONObject(a3).toString());
                String a4 = m25299a(aVar, hashMap);
                f302e.put(a, this.f307j);
                String str2 = null;
                try {
                    str2 = m25300a(elapsedRealtime, a, aVar, a4);
                } catch (JSONException e) {
                    C3754ao.m12155a(btVar, C3857aq.f17251b, C3857aq.f17242ag, e);
                }
                if (TextUtils.isEmpty(str2)) {
                    this.f308k.post(new RunnableC0653c(this, f301d, "参数错误", null, null));
                    return true;
                }
                Intent intent = new Intent("android.intent.action.VIEW", new Uri.Builder().scheme("alipays").authority("platformapi").path("startapp").appendQueryParameter("appId", "20001129").appendQueryParameter("payload", str2).build());
                intent.addFlags(268435456);
                intent.setPackage(a2.f20928a.packageName);
                try {
                    C3754ao.m12151b(btVar, C3857aq.f17251b, C3857aq.f17224P, "" + elapsedRealtime);
                    C4745bt.C4746a.m9402a(btVar, a);
                    this.f306i.startActivity(intent);
                } catch (Throwable th) {
                    C3754ao.m12155a(btVar, C3857aq.f17251b, "StartWalletEx", th);
                }
                return false;
            }
        } else if (z) {
            hashMap.put("mqpScheme", String.valueOf(str));
            hashMap.put("mqpNotifyName", a);
            hashMap.put("mqpScene", "landing");
            String a5 = m25299a(aVar, hashMap);
            Intent intent2 = new Intent(this.f306i, H5OpenAuthActivity.class);
            intent2.putExtra("url", String.format("https://render.alipay.com/p/s/i?scheme=%s", Uri.encode(a5)));
            C4745bt.C4746a.m9403a(btVar, intent2);
            this.f306i.startActivity(intent2);
            return false;
        } else {
            this.f308k.post(new RunnableC0653c(this, f300c, "支付宝未安装或签名错误", null, null));
            return true;
        }
    }

    /* renamed from: a */
    private String m25299a(EnumC0651a aVar, Map<String, String> map) {
        if (aVar != null) {
            Uri.Builder appendQueryParameter = new Uri.Builder().scheme("alipays").authority("platformapi").path("startapp").appendQueryParameter("appId", aVar.appId);
            if (C0658g.f321a[aVar.ordinal()] == 1) {
                appendQueryParameter.appendQueryParameter("appClearTop", "false").appendQueryParameter("startMultApp", "YES");
            }
            for (Map.Entry<String, String> entry : map.entrySet()) {
                appendQueryParameter.appendQueryParameter(entry.getKey(), entry.getValue());
            }
            return appendQueryParameter.build().toString();
        }
        throw new RuntimeException("missing bizType");
    }

    /* renamed from: a */
    private String m25300a(long j, String str, EnumC0651a aVar, String str2) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("startTime", String.valueOf(j));
        jSONObject.put("session", str);
        jSONObject.put(ServiceManagerNative.PACKAGE, this.f306i.getPackageName());
        if (aVar != null) {
            jSONObject.put("appId", aVar.appId);
        }
        jSONObject.put("sdkVersion", "h.a.3.7.3");
        jSONObject.put("mqpURL", str2);
        return Base64.encodeToString(jSONObject.toString().getBytes(Charset.forName("UTF-8")), 2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m25297a(String str, int i, String str2, Bundle bundle) {
        AbstractC0652b remove = f302e.remove(str);
        if (remove != null) {
            try {
                remove.m25294a(i, str2, bundle);
            } catch (Throwable th) {
                C4921cd.m5618a(th);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.alipay.sdk.app.b$c */
    /* loaded from: classes.dex */
    public final class RunnableC0653c implements Runnable {

        /* renamed from: a */
        final int f309a;

        /* renamed from: b */
        final String f310b;

        /* renamed from: c */
        final Bundle f311c;

        /* synthetic */ RunnableC0653c(C0650b bVar, int i, String str, Bundle bundle, C0658g gVar) {
            this(i, str, bundle);
        }

        private RunnableC0653c(int i, String str, Bundle bundle) {
            this.f309a = i;
            this.f310b = str;
            this.f311c = bundle;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (C0650b.this.f307j != null) {
                C0650b.this.f307j.m25294a(this.f309a, this.f310b, this.f311c);
            }
        }
    }
}
