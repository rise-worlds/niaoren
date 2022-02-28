package com.live.ddy.visual.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.gyf.barlibrary.ImmersionBar;
import com.live.ddy.C1701R;
import com.live.ddy.activity.DdyBaseActivity;
import com.live.ddy.visual.view.DdyMediaLayout;
import p110z1.DdyMediaModel;
import p110z1.EventConstants;
import p110z1.IDdyMediaActivity;

/* loaded from: classes.dex */
public class DdyMediaActivity extends DdyBaseActivity implements IDdyMediaActivity {

    /* renamed from: a */
    private DdyMediaModel f10450a;

    /* renamed from: b */
    private LinearLayout f10451b;

    /* renamed from: c */
    private RelativeLayout f10452c;

    /* renamed from: d */
    private DdyMediaLayout f10453d;

    /* renamed from: e */
    private RelativeLayout f10454e;

    /* renamed from: f */
    private long f10455f;

    /* renamed from: g */
    private String f10456g;

    /* renamed from: h */
    private String f10457h;

    /* renamed from: i */
    private int f10458i = -1;

    /* renamed from: j */
    private Runnable f10459j = new Runnable() { // from class: com.live.ddy.visual.activity.DdyMediaActivity.1
        @Override // java.lang.Runnable
        public void run() {
            DdyMediaActivity.this.f10451b.postDelayed(this, 1000L);
            if (DdyMediaActivity.this.f10458i != -1) {
                if (DdyMediaActivity.this.f10458i > 60) {
                    DdyMediaActivity.this.finish();
                }
                DdyMediaActivity.m19025c(DdyMediaActivity.this);
            }
        }
    };

    @Override // p110z1.IDdyMediaActivity
    /* renamed from: c */
    public Context mo13134c() {
        return this;
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: d */
    public void mo18300d() {
    }

    @Override // p110z1.IDdyMediaActivity
    /* renamed from: h */
    public void mo13130h() {
    }

    @Override // p110z1.IDdyMediaActivity
    /* renamed from: i */
    public void mo13129i() {
    }

    /* renamed from: c */
    static /* synthetic */ int m19025c(DdyMediaActivity ddyMediaActivity) {
        int i = ddyMediaActivity.f10458i;
        ddyMediaActivity.f10458i = i + 1;
        return i;
    }

    /* renamed from: a */
    public static void m19028a(Context context, long j, String str, String str2) {
        Intent intent = new Intent(context, DdyMediaActivity.class);
        intent.putExtra("Orderid", j);
        intent.putExtra("ucid", str);
        intent.putExtra("token", str2);
        context.startActivity(intent);
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: a */
    protected int mo18307a() {
        return C1701R.layout.ddy_activity_media_layout;
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: b */
    public void mo18303b() {
        getWindow().setFlags(1024, 1024);
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: a */
    public void mo18305a(Bundle bundle) {
        ImmersionBar.m20080a(this).m19994f();
        this.f10451b = (LinearLayout) findViewById(C1701R.C1703id.nrzs_ddy_id_tv_loading);
        this.f10454e = (RelativeLayout) findViewById(C1701R.C1703id.nrzs_ddy_rl_parent_media);
        this.f10452c = (RelativeLayout) findViewById(C1701R.C1703id.nrzs_ddy_id_rl_visual_opera);
        this.f10453d = (DdyMediaLayout) findViewById(C1701R.C1703id.nrzs_ddy_id_ll_media);
        this.f10455f = getIntent().getLongExtra("Orderid", 0L);
        this.f10456g = getIntent().getStringExtra("ucid");
        this.f10457h = getIntent().getStringExtra("token");
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: b */
    public void mo18302b(Bundle bundle) {
        this.f10458i = 0;
        this.f10451b.postDelayed(this.f10459j, 1000L);
        if (this.f10450a == null) {
            this.f10450a = new DdyMediaModel();
        }
        this.f10454e.post(new Runnable() { // from class: com.live.ddy.visual.activity.DdyMediaActivity.2
            @Override // java.lang.Runnable
            public void run() {
                DdyMediaModel aigVar = DdyMediaActivity.this.f10450a;
                DdyMediaActivity ddyMediaActivity = DdyMediaActivity.this;
                aigVar.m13082a(ddyMediaActivity, ddyMediaActivity.f10452c);
                DdyMediaModel aigVar2 = DdyMediaActivity.this.f10450a;
                DdyMediaActivity ddyMediaActivity2 = DdyMediaActivity.this;
                aigVar2.m13083a(ddyMediaActivity2, ddyMediaActivity2.f10454e, 8);
                DdyMediaModel aigVar3 = DdyMediaActivity.this.f10450a;
                DdyMediaActivity ddyMediaActivity3 = DdyMediaActivity.this;
                aigVar3.m13077a(ddyMediaActivity3, ddyMediaActivity3.f10455f, DdyMediaActivity.this.f10456g, DdyMediaActivity.this.f10457h, "");
                DdyMediaActivity.this.f10450a.m13079a((LinearLayout) DdyMediaActivity.this.f10453d);
                DdyMediaActivity.this.f10450a.m13078a(EventConstants.f16434b);
            }
        });
    }

    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        DdyMediaModel aigVar = this.f10450a;
        if (aigVar != null) {
            aigVar.m13080a(this.f10454e);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        finish();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.f10458i = -1;
        m19017k();
        this.f10451b.removeCallbacks(this.f10459j);
        DdyMediaModel aigVar = this.f10450a;
        if (aigVar != null) {
            aigVar.m13067d();
            this.f10450a = null;
        }
    }

    /* renamed from: k */
    private void m19017k() {
        Intent intent = new Intent("DDY_FINISH");
        intent.putExtra("orderid", this.f10455f);
        sendBroadcast(intent);
    }

    @Override // p110z1.IDdyMediaActivity
    /* renamed from: e */
    public void mo13133e() {
        this.f10458i = -1;
        runOnUiThread(new Runnable() { // from class: com.live.ddy.visual.activity.DdyMediaActivity.3
            @Override // java.lang.Runnable
            public void run() {
                DdyMediaActivity.this.f10451b.setVisibility(8);
                DdyMediaModel aigVar = DdyMediaActivity.this.f10450a;
                DdyMediaActivity ddyMediaActivity = DdyMediaActivity.this;
                aigVar.m13082a(ddyMediaActivity, ddyMediaActivity.f10452c);
                DdyMediaModel aigVar2 = DdyMediaActivity.this.f10450a;
                DdyMediaActivity ddyMediaActivity2 = DdyMediaActivity.this;
                aigVar2.m13083a(ddyMediaActivity2, ddyMediaActivity2.f10454e, 0);
            }
        });
    }

    @Override // p110z1.IDdyMediaActivity
    /* renamed from: a */
    public void mo13137a(int i) {
        this.f10450a.m13083a(this, this.f10454e, i);
    }

    @Override // p110z1.IDdyMediaActivity
    /* renamed from: f */
    public void mo13132f() {
        DdyMediaModel aigVar = this.f10450a;
        if (aigVar != null) {
            aigVar.m13074b();
        }
    }

    @Override // p110z1.IDdyMediaActivity
    /* renamed from: g */
    public void mo13131g() {
        finish();
    }

    @Override // p110z1.IDdyMediaActivity
    /* renamed from: a */
    public void mo13136a(long j, long j2) {
        this.f10458i = 0;
        if (this.f10450a != null) {
            this.f10451b.setVisibility(0);
            this.f10450a.m13073b(j, j2);
        }
    }

    @Override // p110z1.IDdyMediaActivity
    /* renamed from: a */
    public void mo13135a(String str) {
        if (this.f10450a != null) {
            this.f10451b.setVisibility(0);
            this.f10450a.m13072b(str);
        }
    }

    @Override // p110z1.IDdyMediaActivity
    /* renamed from: j */
    public void mo13128j() {
        if (this.f10450a != null) {
            this.f10458i = -1;
        }
    }
}
