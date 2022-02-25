package com.alipay.sdk.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import com.alipay.sdk.widget.C0665a;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import p110z1.C3754ao;
import p110z1.C3857aq;
import p110z1.C3883at;
import p110z1.C3894au;
import p110z1.C4490bl;
import p110z1.C4742bs;
import p110z1.C4745bt;
import p110z1.C4759bu;
import p110z1.C4921cd;
import p110z1.C4943cf;
import p110z1.C4985cm;
import p110z1.C5019co;
import p110z1.EnumC4725br;

/* loaded from: classes.dex */
public class AuthTask {

    /* renamed from: a */
    static final Object f260a = C4943cf.class;

    /* renamed from: b */
    private Activity f261b;

    /* renamed from: c */
    private C0665a f262c;

    public AuthTask(Activity activity) {
        this.f261b = activity;
        C4759bu.m9273a().m9272a(this.f261b);
        this.f262c = new C0665a(activity, C0665a.f346c);
    }

    /* renamed from: a */
    private C4943cf.AbstractC4944a m25339a() {
        return new C0654c(this);
    }

    /* renamed from: b */
    private void m25335b() {
        C0665a aVar = this.f262c;
        if (aVar != null) {
            aVar.m25269b();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public void m25333c() {
        C0665a aVar = this.f262c;
        if (aVar != null) {
            aVar.m25267c();
        }
    }

    public synchronized Map<String, String> authV2(String str, boolean z) {
        C4745bt btVar;
        btVar = new C4745bt(this.f261b, str, "authV2");
        return C4985cm.m4836a(btVar, innerAuth(btVar, str, z));
    }

    public synchronized String auth(String str, boolean z) {
        return innerAuth(new C4745bt(this.f261b, str, C3857aq.f17253d), str, z);
    }

    public synchronized String innerAuth(C4745bt btVar, String str, boolean z) {
        String c;
        Activity activity;
        String str2;
        if (z) {
            m25335b();
        }
        C4759bu.m9273a().m9272a(this.f261b);
        c = C0663l.m25282c();
        C0662k.m25289a("");
        try {
            c = m25338a(this.f261b, str, btVar);
            C3754ao.m12151b(btVar, C3857aq.f17251b, C3857aq.f17222N, "" + SystemClock.elapsedRealtime());
            C3894au.m9967i().m9977a(btVar, this.f261b);
            m25333c();
            activity = this.f261b;
            str2 = btVar.f20085p;
        } catch (Exception e) {
            C4921cd.m5618a(e);
            C3754ao.m12151b(btVar, C3857aq.f17251b, C3857aq.f17222N, "" + SystemClock.elapsedRealtime());
            C3894au.m9967i().m9977a(btVar, this.f261b);
            m25333c();
            activity = this.f261b;
            str2 = btVar.f20085p;
        }
        C3754ao.m12152b(activity, btVar, str, str2);
        return c;
    }

    /* renamed from: a */
    private String m25338a(Activity activity, String str, C4745bt btVar) {
        String a = btVar.m9417a(str);
        List<C3894au.C3895a> h = C3894au.m9967i().m9968h();
        if (!C3894au.m9967i().f17539s || h == null) {
            h = C0662k.f328a;
        }
        if (C5019co.m4483b(btVar, this.f261b, h)) {
            String a2 = new C4943cf(activity, btVar, m25339a()).m5487a(a);
            if (!TextUtils.equals(a2, "failed") && !TextUtils.equals(a2, C4943cf.f20634b)) {
                return TextUtils.isEmpty(a2) ? C0663l.m25282c() : a2;
            }
            C3754ao.m12157a(btVar, C3857aq.f17251b, C3857aq.f17232X);
            return m25334b(activity, a, btVar);
        }
        C3754ao.m12157a(btVar, C3857aq.f17251b, C3857aq.f17233Y);
        return m25334b(activity, a, btVar);
    }

    /* renamed from: b */
    private String m25334b(Activity activity, String str, C4745bt btVar) {
        EnumC0664m mVar;
        m25335b();
        try {
            try {
                List<C4742bs> a = C4742bs.m9461a(new C4490bl().mo9548a(btVar, activity, str).m9689c().optJSONObject(C3883at.f17479c).optJSONObject(C3883at.f17480d));
                m25333c();
                for (int i = 0; i < a.size(); i++) {
                    if (a.get(i).m9459b() == EnumC4725br.WapPay) {
                        String a2 = m25336a(btVar, a.get(i));
                        m25333c();
                        return a2;
                    }
                }
            } catch (IOException e) {
                mVar = EnumC0664m.m25275b(EnumC0664m.NETWORK_ERROR.m25279a());
                C3754ao.m12153a(btVar, C3857aq.f17235a, e);
                m25333c();
            } catch (Throwable th) {
                C3754ao.m12155a(btVar, C3857aq.f17251b, C3857aq.f17271v, th);
            }
            m25333c();
            mVar = null;
            if (mVar == null) {
                mVar = EnumC0664m.m25275b(EnumC0664m.FAILED.m25279a());
            }
            return C0663l.m25286a(mVar.m25279a(), mVar.m25276b(), "");
        } catch (Throwable th2) {
            m25333c();
            throw th2;
        }
    }

    /* renamed from: a */
    private String m25336a(C4745bt btVar, C4742bs bsVar) {
        String[] c = bsVar.m9457c();
        Bundle bundle = new Bundle();
        bundle.putString("url", c[0]);
        Intent intent = new Intent(this.f261b, H5AuthActivity.class);
        intent.putExtras(bundle);
        C4745bt.C4746a.m9403a(btVar, intent);
        this.f261b.startActivity(intent);
        synchronized (f260a) {
            try {
                f260a.wait();
            } catch (InterruptedException unused) {
                return C0663l.m25282c();
            }
        }
        String a = C0663l.m25287a();
        return TextUtils.isEmpty(a) ? C0663l.m25282c() : a;
    }
}
