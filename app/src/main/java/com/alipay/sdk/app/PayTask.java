package com.alipay.sdk.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import com.alipay.sdk.app.PayResultActivity;
import com.alipay.sdk.widget.C0665a;
import com.stripe.android.model.C2395g;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.mail.EmailConstants;
import org.json.JSONObject;
import p110z1.C3754ao;
import p110z1.C3857aq;
import p110z1.C3876ar;
import p110z1.C3883at;
import p110z1.C3894au;
import p110z1.C4696bq;
import p110z1.C4742bs;
import p110z1.C4745bt;
import p110z1.C4759bu;
import p110z1.C4814by;
import p110z1.C4828bz;
import p110z1.C4921cd;
import p110z1.C4943cf;
import p110z1.C4963cj;
import p110z1.C4985cm;
import p110z1.C5019co;
import p110z1.EnumC4725br;

/* loaded from: classes.dex */
public class PayTask {

    /* renamed from: a */
    static final Object f282a = C4943cf.class;

    /* renamed from: h */
    private static long f283h = 0;

    /* renamed from: i */
    private static final long f284i = 3000;

    /* renamed from: j */
    private static long f285j = -1;

    /* renamed from: b */
    private Activity f286b;

    /* renamed from: c */
    private C0665a f287c;

    /* renamed from: d */
    private String f288d = "wappaygw.alipay.com/service/rest.htm";

    /* renamed from: e */
    private String f289e = "mclient.alipay.com/service/rest.htm";

    /* renamed from: f */
    private String f290f = "mclient.alipay.com/home/exterfaceAssign.htm";

    /* renamed from: g */
    private Map<String, C0647a> f291g = new HashMap();

    public String getVersion() {
        return "15.7.3";
    }

    public PayTask(Activity activity) {
        this.f286b = activity;
        C4759bu.m9273a().m9272a(this.f286b);
        this.f287c = new C0665a(activity, C0665a.f345b);
    }

    public synchronized String pay(String str, boolean z) {
        return m25318a(new C4745bt(this.f286b, str, "pay"), str, z);
    }

    /* renamed from: a */
    private synchronized String m25318a(C4745bt btVar, String str, boolean z) {
        if (m25312b()) {
            C3754ao.m12156a(btVar, C3857aq.f17251b, "RepPay", "");
            return C0663l.m25281d();
        }
        if (z) {
            showLoading();
        }
        if (str.contains("payment_inst=")) {
            String substring = str.substring(str.indexOf("payment_inst=") + 13);
            int indexOf = substring.indexOf(38);
            if (indexOf > 0) {
                substring = substring.substring(0, indexOf);
            }
            C0662k.m25289a(substring.replaceAll("\"", "").toLowerCase(Locale.getDefault()).replaceAll(C2395g.f12108b, ""));
        } else {
            C0662k.m25289a("");
        }
        if (str.contains(C3876ar.f17443t)) {
            C3876ar.f17444u = true;
        }
        if (C3876ar.f17444u) {
            if (str.startsWith(C3876ar.f17445v)) {
                str = str.substring(str.indexOf(C3876ar.f17445v) + 53);
            } else if (str.startsWith(C3876ar.f17446w)) {
                str = str.substring(str.indexOf(C3876ar.f17446w) + 52);
            }
        }
        C4921cd.m5616b(C3876ar.f17447x, "pay prepared: " + str);
        String a = m25321a(str, btVar);
        C4921cd.m5616b(C3876ar.f17447x, "pay raw result: " + a);
        C4963cj.m5209a(btVar, this.f286b.getApplicationContext(), a);
        C3754ao.m12151b(btVar, C3857aq.f17251b, C3857aq.f17222N, "" + SystemClock.elapsedRealtime());
        C3894au.m9967i().m9977a(btVar, this.f286b.getApplicationContext());
        dismissLoading();
        C3754ao.m12152b(this.f286b.getApplicationContext(), btVar, str, btVar.f20085p);
        C4921cd.m5616b(C3876ar.f17447x, "pay returning: " + a);
        return a;
    }

    public synchronized Map<String, String> payV2(String str, boolean z) {
        C4745bt btVar;
        btVar = new C4745bt(this.f286b, str, "payV2");
        return C4985cm.m4836a(btVar, m25318a(btVar, str, z));
    }

    public synchronized String fetchTradeToken() {
        return C4963cj.m5210a(new C4745bt(this.f286b, "", "fetchTradeToken"), this.f286b.getApplicationContext());
    }

    public synchronized boolean payInterceptorWithUrl(String str, boolean z, H5PayCallback h5PayCallback) {
        String fetchOrderInfoFromH5PayUrl;
        fetchOrderInfoFromH5PayUrl = fetchOrderInfoFromH5PayUrl(str);
        if (!TextUtils.isEmpty(fetchOrderInfoFromH5PayUrl)) {
            C4921cd.m5616b(C3876ar.f17447x, "intercepted: " + fetchOrderInfoFromH5PayUrl);
            new Thread(new RunnableC0660i(this, fetchOrderInfoFromH5PayUrl, z, h5PayCallback)).start();
        }
        return !TextUtils.isEmpty(fetchOrderInfoFromH5PayUrl);
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x00e0, code lost:
        if (r9.startsWith("http://" + r16.f289e) != false) goto L_0x00e2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0181, code lost:
        if (r9.startsWith("http://" + r16.f290f) != false) goto L_0x0183;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x003f, code lost:
        if (r9.startsWith("http://" + r16.f288d) != false) goto L_0x0041;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized java.lang.String fetchOrderInfoFromH5PayUrl(java.lang.String r17) {
        /*
            Method dump skipped, instructions count: 1233
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.app.PayTask.fetchOrderInfoFromH5PayUrl(java.lang.String):java.lang.String");
    }

    /* renamed from: a */
    private static final String m25313a(String... strArr) {
        if (strArr == null) {
            return "";
        }
        for (String str : strArr) {
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
        }
        return "";
    }

    public static synchronized boolean fetchSdkConfig(Context context) {
        synchronized (PayTask.class) {
            try {
                C4759bu.m9273a().m9272a(context);
                long elapsedRealtime = SystemClock.elapsedRealtime() / 1000;
                if (elapsedRealtime - f283h < C3894au.m9967i().m9970f()) {
                    return false;
                }
                f283h = elapsedRealtime;
                C3894au.m9967i().m9977a(C4745bt.m9418a(), context.getApplicationContext());
                return true;
            } catch (Exception e) {
                C4921cd.m5618a(e);
                return false;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.alipay.sdk.app.PayTask$a */
    /* loaded from: classes.dex */
    public class C0647a {

        /* renamed from: b */
        private String f293b;

        /* renamed from: c */
        private String f294c;

        /* renamed from: d */
        private String f295d;

        /* renamed from: e */
        private String f296e;

        private C0647a() {
            this.f293b = "";
            this.f294c = "";
            this.f295d = "";
            this.f296e = "";
        }

        /* synthetic */ C0647a(PayTask payTask, RunnableC0660i iVar) {
            this();
        }

        /* renamed from: a */
        public String m25311a() {
            return this.f293b;
        }

        /* renamed from: a */
        public void m25310a(String str) {
            this.f293b = str;
        }

        /* renamed from: b */
        public String m25309b() {
            return this.f295d;
        }

        /* renamed from: b */
        public void m25308b(String str) {
            this.f295d = str;
        }

        /* renamed from: c */
        public String m25307c() {
            return this.f294c;
        }

        /* renamed from: c */
        public void m25306c(String str) {
            this.f294c = str;
        }

        /* renamed from: d */
        public String m25305d() {
            return this.f296e;
        }

        /* renamed from: d */
        public void m25304d(String str) {
            this.f296e = str;
        }
    }

    /* renamed from: a */
    private boolean m25314a(boolean z, boolean z2, String str, StringBuilder sb, Map<String, String> map, String... strArr) {
        String str2 = "";
        int length = strArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            String str3 = strArr[i];
            if (!TextUtils.isEmpty(map.get(str3))) {
                str2 = map.get(str3);
                break;
            }
            i++;
        }
        if (TextUtils.isEmpty(str2)) {
            return !z2;
        }
        if (z) {
            sb.append(C4745bt.f20071b);
            sb.append(str);
            sb.append("=\"");
            sb.append(str2);
            sb.append("\"");
            return true;
        }
        sb.append(str);
        sb.append("=\"");
        sb.append(str2);
        sb.append("\"");
        return true;
    }

    public synchronized C4828bz h5Pay(C4745bt btVar, String str, boolean z) {
        C4828bz bzVar;
        bzVar = new C4828bz();
        String[] split = m25318a(btVar, str, z).split(C4963cj.f20745b);
        HashMap hashMap = new HashMap();
        for (String str2 : split) {
            int indexOf = str2.indexOf("={");
            if (indexOf >= 0) {
                String substring = str2.substring(0, indexOf);
                hashMap.put(substring, m25323a(str2, substring));
            }
        }
        if (hashMap.containsKey(C4985cm.f20831a)) {
            bzVar.m8291b(hashMap.get(C4985cm.f20831a));
        }
        bzVar.m8293a(m25322a(str, hashMap));
        if (TextUtils.isEmpty(bzVar.m8294a())) {
            C3754ao.m12156a(btVar, C3857aq.f17251b, C3857aq.f17238ac, "");
        }
        return bzVar;
    }

    /* renamed from: a */
    private String m25322a(String str, Map<String, String> map) throws UnsupportedEncodingException {
        boolean equals = "9000".equals(map.get(C4985cm.f20831a));
        String str2 = map.get(C4985cm.f20833c);
        C0647a remove = this.f291g.remove(str);
        String[] strArr = new String[2];
        strArr[0] = remove != null ? remove.m25309b() : "";
        strArr[1] = remove != null ? remove.m25305d() : "";
        m25313a(strArr);
        if (map.containsKey("callBackUrl")) {
            return map.get("callBackUrl");
        }
        if (str2.length() > 15) {
            String a = m25313a(C5019co.m4497a("&callBackUrl=\"", "\"", str2), C5019co.m4497a("&call_back_url=\"", "\"", str2), C5019co.m4497a(C3876ar.f17441r, "\"", str2), URLDecoder.decode(C5019co.m4497a(C3876ar.f17442s, C4745bt.f20071b, str2), EmailConstants.UTF_8), URLDecoder.decode(C5019co.m4497a("&callBackUrl=", C4745bt.f20071b, str2), EmailConstants.UTF_8), C5019co.m4497a("call_back_url=\"", "\"", str2));
            if (!TextUtils.isEmpty(a)) {
                return a;
            }
        }
        if (remove != null) {
            String a2 = equals ? remove.m25311a() : remove.m25307c();
            if (!TextUtils.isEmpty(a2)) {
                return a2;
            }
        }
        return remove != null ? C3894au.m9967i().m9971e() : "";
    }

    /* renamed from: a */
    private String m25323a(String str, String str2) {
        String str3 = str2 + "={";
        return str.substring(str.indexOf(str3) + str3.length(), str.lastIndexOf(C4963cj.f20747d));
    }

    /* renamed from: a */
    private C4943cf.AbstractC4944a m25325a() {
        return new C0661j(this);
    }

    public void showLoading() {
        C0665a aVar = this.f287c;
        if (aVar != null) {
            aVar.m25269b();
        }
    }

    public void dismissLoading() {
        C0665a aVar = this.f287c;
        if (aVar != null) {
            aVar.m25267c();
            this.f287c = null;
        }
    }

    /* renamed from: a */
    private String m25321a(String str, C4745bt btVar) {
        String a = btVar.m9417a(str);
        if (a.contains("paymethod=\"expressGateway\"")) {
            return m25320a(btVar, a);
        }
        List<C3894au.C3895a> h = C3894au.m9967i().m9968h();
        if (!C3894au.m9967i().f17539s || h == null) {
            h = C0662k.f328a;
        }
        if (C5019co.m4483b(btVar, this.f286b, h)) {
            C4943cf cfVar = new C4943cf(this.f286b, btVar, m25325a());
            C4921cd.m5616b(C3876ar.f17447x, "pay inner started: " + a);
            String a2 = cfVar.m5487a(a);
            C4921cd.m5616b(C3876ar.f17447x, "pay inner raw result: " + a2);
            cfVar.m5488a();
            if (TextUtils.equals(a2, "failed") || TextUtils.equals(a2, C4943cf.f20634b)) {
                C3754ao.m12157a(btVar, C3857aq.f17251b, C3857aq.f17232X);
                return m25320a(btVar, a);
            } else if (TextUtils.isEmpty(a2)) {
                return C0663l.m25282c();
            } else {
                if (!a2.contains(PayResultActivity.f271a)) {
                    return a2;
                }
                C3754ao.m12157a(btVar, C3857aq.f17251b, C3857aq.f17234Z);
                return m25319a(btVar, a, h, a2, this.f286b);
            }
        } else {
            C3754ao.m12157a(btVar, C3857aq.f17251b, C3857aq.f17233Y);
            return m25320a(btVar, a);
        }
    }

    /* renamed from: a */
    private static String m25319a(C4745bt btVar, String str, List<C3894au.C3895a> list, String str2, Activity activity) {
        C5019co.C5020a a = C5019co.m4493a(btVar, activity, list);
        if (a == null || a.m4468a(btVar) || a.m4469a() || !TextUtils.equals(a.f20928a.packageName, PayResultActivity.f273c)) {
            return str2;
        }
        C4921cd.m5620a(C3876ar.f17447x, "PayTask not_login");
        String valueOf = String.valueOf(str.hashCode());
        PayResultActivity.f272b.put(valueOf, new Object());
        Intent intent = new Intent(activity, PayResultActivity.class);
        intent.putExtra(PayResultActivity.f275e, str);
        intent.putExtra(PayResultActivity.f276f, activity.getPackageName());
        intent.putExtra(PayResultActivity.f274d, valueOf);
        C4745bt.C4746a.m9403a(btVar, intent);
        activity.startActivity(intent);
        synchronized (PayResultActivity.f272b.get(valueOf)) {
            try {
                C4921cd.m5620a(C3876ar.f17447x, "PayTask wait");
                PayResultActivity.f272b.get(valueOf).wait();
            } catch (InterruptedException unused) {
                C4921cd.m5620a(C3876ar.f17447x, "PayTask interrupted");
                return C0663l.m25282c();
            }
        }
        String str3 = PayResultActivity.C0646a.f281b;
        C4921cd.m5620a(C3876ar.f17447x, "PayTask ret: " + str3);
        return str3;
    }

    /* renamed from: a */
    private String m25320a(C4745bt btVar, String str) {
        showLoading();
        EnumC0664m mVar = null;
        try {
            try {
                JSONObject c = new C4696bq().mo9548a(btVar, this.f286b.getApplicationContext(), str).m9689c();
                String optString = c.optString("end_code", null);
                List<C4742bs> a = C4742bs.m9461a(c.optJSONObject(C3883at.f17479c).optJSONObject(C3883at.f17480d));
                for (int i = 0; i < a.size(); i++) {
                    if (a.get(i).m9459b() == EnumC4725br.Update) {
                        C4742bs.m9460a(a.get(i));
                    }
                }
                m25317a(btVar, c);
                dismissLoading();
                C3754ao.m12158a(this.f286b, btVar, str, btVar.f20085p);
                for (int i2 = 0; i2 < a.size(); i2++) {
                    C4742bs bsVar = a.get(i2);
                    if (bsVar.m9459b() == EnumC4725br.WapPay) {
                        String a2 = m25316a(btVar, bsVar);
                        dismissLoading();
                        C3754ao.m12158a(this.f286b, btVar, str, btVar.f20085p);
                        return a2;
                    } else if (bsVar.m9459b() == EnumC4725br.OpenWeb) {
                        String a3 = m25315a(btVar, bsVar, optString);
                        dismissLoading();
                        C3754ao.m12158a(this.f286b, btVar, str, btVar.f20085p);
                        return a3;
                    }
                }
            } catch (IOException e) {
                mVar = EnumC0664m.m25275b(EnumC0664m.NETWORK_ERROR.m25279a());
                C3754ao.m12153a(btVar, C3857aq.f17235a, e);
                dismissLoading();
                C3754ao.m12158a(this.f286b, btVar, str, btVar.f20085p);
            } catch (Throwable th) {
                C4921cd.m5618a(th);
                C3754ao.m12155a(btVar, C3857aq.f17251b, C3857aq.f17270u, th);
            }
            dismissLoading();
            C3754ao.m12158a(this.f286b, btVar, str, btVar.f20085p);
            if (mVar == null) {
                mVar = EnumC0664m.m25275b(EnumC0664m.FAILED.m25279a());
            }
            return C0663l.m25286a(mVar.m25279a(), mVar.m25276b(), "");
        } catch (Throwable th2) {
            dismissLoading();
            C3754ao.m12158a(this.f286b, btVar, str, btVar.f20085p);
            throw th2;
        }
    }

    /* renamed from: a */
    private void m25317a(C4745bt btVar, JSONObject jSONObject) {
        try {
            String optString = jSONObject.optString("tid");
            String optString2 = jSONObject.optString(C4814by.f20406e);
            if (!TextUtils.isEmpty(optString) && !TextUtils.isEmpty(optString2)) {
                C4814by.m8377a(C4759bu.m9273a().m9271b()).m8376a(optString, optString2);
            }
        } catch (Throwable th) {
            C3754ao.m12155a(btVar, C3857aq.f17251b, C3857aq.f17216H, th);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0091, code lost:
        r0 = r6.m9457c();
        r11 = com.alipay.sdk.app.C0663l.m25286a(java.lang.Integer.valueOf(r0[1]).intValue(), r0[0], p110z1.C5019co.m4482b(r10, r0[2]));
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String m25315a(p110z1.C4745bt r10, p110z1.C4742bs r11, java.lang.String r12) {
        /*
            Method dump skipped, instructions count: 279
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.app.PayTask.m25315a(z1.bt, z1.bs, java.lang.String):java.lang.String");
    }

    /* renamed from: a */
    private String m25316a(C4745bt btVar, C4742bs bsVar) {
        String[] c = bsVar.m9457c();
        Intent intent = new Intent(this.f286b, H5PayActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("url", c[0]);
        if (c.length == 2) {
            bundle.putString("cookie", c[1]);
        }
        intent.putExtras(bundle);
        C4745bt.C4746a.m9403a(btVar, intent);
        this.f286b.startActivity(intent);
        synchronized (f282a) {
            try {
                f282a.wait();
            } catch (InterruptedException e) {
                C4921cd.m5618a(e);
                return C0663l.m25282c();
            }
        }
        String a = C0663l.m25287a();
        return TextUtils.isEmpty(a) ? C0663l.m25282c() : a;
    }

    /* renamed from: b */
    private static boolean m25312b() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (elapsedRealtime - f285j < f284i) {
            return true;
        }
        f285j = elapsedRealtime;
        return false;
    }
}
