package com.alipay.sdk.app;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import com.alipay.sdk.widget.AbstractC0672g;
import com.alipay.sdk.widget.C0673h;
import com.alipay.sdk.widget.C0675j;
import org.apache.http.cookie.ClientCookie;
import p110z1.AbstractC4442bk;
import p110z1.C3754ao;
import p110z1.C3857aq;
import p110z1.C3894au;
import p110z1.C4745bt;
import p110z1.C4921cd;
import p110z1.C5019co;

/* loaded from: classes.dex */
public class H5PayActivity extends Activity {

    /* renamed from: a */
    private AbstractC0672g f264a;

    /* renamed from: b */
    private String f265b;

    /* renamed from: c */
    private String f266c;

    /* renamed from: d */
    private String f267d;

    /* renamed from: e */
    private String f268e;

    /* renamed from: f */
    private boolean f269f;

    /* renamed from: g */
    private String f270g;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        m25331b();
        super.onCreate(bundle);
        try {
            C4745bt a = C4745bt.C4746a.m9405a(getIntent());
            if (a == null) {
                finish();
                return;
            }
            if (!C3894au.m9967i().m9975b()) {
                setRequestedOrientation(1);
            } else {
                setRequestedOrientation(3);
            }
            try {
                Bundle extras = getIntent().getExtras();
                this.f265b = extras.getString("url", null);
                if (!C5019co.m4476d(this.f265b)) {
                    finish();
                    return;
                }
                this.f267d = extras.getString("cookie", null);
                this.f266c = extras.getString(AbstractC4442bk.f19102q, null);
                this.f268e = extras.getString(C0675j.f373k, null);
                this.f270g = extras.getString(ClientCookie.VERSION_ATTR, "v1");
                this.f269f = extras.getBoolean("backisexit", false);
                try {
                    if ("v2".equals(this.f270g)) {
                        C0675j jVar = new C0675j(this, a);
                        setContentView(jVar);
                        jVar.m25250a(this.f268e, this.f266c, this.f269f);
                        jVar.mo25252a(this.f265b);
                        this.f264a = jVar;
                        return;
                    }
                    this.f264a = new C0673h(this, a);
                    setContentView(this.f264a);
                    this.f264a.m25257a(this.f265b, this.f267d);
                    this.f264a.mo25252a(this.f265b);
                } catch (Throwable th) {
                    C3754ao.m12155a(a, C3857aq.f17251b, "GetInstalledAppEx", th);
                    finish();
                }
            } catch (Exception unused) {
                finish();
            }
        } catch (Exception unused2) {
            finish();
        }
    }

    /* renamed from: b */
    private void m25331b() {
        try {
            super.requestWindowFeature(1);
        } catch (Throwable th) {
            C4921cd.m5618a(th);
        }
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        AbstractC0672g gVar = this.f264a;
        if (gVar instanceof C0673h) {
            gVar.mo25248b();
            return;
        }
        if (!gVar.mo25248b()) {
            super.onBackPressed();
        }
        C0663l.m25285a(C0663l.m25282c());
        finish();
    }

    @Override // android.app.Activity
    public void finish() {
        mo25332a();
        super.finish();
    }

    /* renamed from: a */
    public void mo25332a() {
        Object obj = PayTask.f282a;
        synchronized (obj) {
            try {
                obj.notify();
            } catch (Exception unused) {
            }
        }
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        AbstractC0672g gVar = this.f264a;
        if (gVar != null) {
            gVar.mo25255a();
        }
    }

    @Override // android.app.Activity
    public void setRequestedOrientation(int i) {
        try {
            super.setRequestedOrientation(i);
        } catch (Throwable th) {
            try {
                C3754ao.m12155a(C4745bt.C4746a.m9405a(getIntent()), C3857aq.f17251b, C3857aq.f17270u, th);
            } catch (Throwable unused) {
            }
        }
    }
}
