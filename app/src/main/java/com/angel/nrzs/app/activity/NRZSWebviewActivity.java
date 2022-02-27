package com.angel.nrzs.app.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.angel.nrzs.C0692R;
import com.angel.nrzs.bean.UrlBaseRequest;
import com.angel.nrzs.app.base.AppBaseActivity;
import com.angel.nrzs.utls.x5webview.NRZSX5WebView;
import com.gyf.barlibrary.ImmersionBar;
import com.nrzs.base.router.RouterConstants;
import com.nrzs.base.router.RouterUtils;
import com.nrzs.data.other.bean.AdResultInfoItem;
import com.tencent.smtt.sdk.CookieSyncManager;
import p110z1.C4745bt;
import p110z1.DialogC5331eu;
import p110z1.EventBus;
import p110z1.GlobalConstants;
import p110z1.LoginManager;
import p110z1.PayEvent;
import p110z1.Subscribe;
import p110z1.ThreadMode;

@Route(path = RouterConstants.WEB)
/* renamed from: com.angel.nrzs.ui.activity.NRZSWebviewActivity */
/* loaded from: classes.dex */
public class NRZSWebviewActivity extends AppBaseActivity {

    /* renamed from: a */
    public static final int f5425a = 99;

    /* renamed from: b */
    private LinearLayout f5426b;

    /* renamed from: c */
    private RelativeLayout f5427c;

    /* renamed from: d */
    private TextView f5428d;

    /* renamed from: e */
    private ImageView f5429e;

    /* renamed from: f */
    private ImageView f5430f;

    /* renamed from: g */
    private TextView f5431g;

    /* renamed from: h */
    private TextView f5432h;

    /* renamed from: i */
    private TextView f5433i;

    /* renamed from: k */
    private NRZSX5WebView f5435k;

    /* renamed from: l */
    private AdResultInfoItem f5436l;

    /* renamed from: m */
    private int f5437m;

    /* renamed from: n */
    private int f5438n;

    /* renamed from: p */
    private long f5440p;

    /* renamed from: j */
    private String f5434j = "";

    /* renamed from: o */
    private int f5439o = 1;

    /* renamed from: q */
    private Handler f5441q = new Handler(Looper.getMainLooper()) { // from class: com.angel.nrzs.ui.activity.NRZSWebviewActivity.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    NRZSWebviewActivity.this.f5428d.setText(NRZSWebviewActivity.this.f5434j);
                    return;
                case 2:
                    NRZSWebviewActivity.this.finish();
                    return;
                default:
                    return;
            }
        }
    };

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: a */
    protected int mo18307a() {
        return C0692R.layout.nrzs_activity_webview;
    }

    /* renamed from: a */
    public static void m25004a(Context context, int i, int i2, AdResultInfoItem adResultInfoItem) {
        Intent intent = new Intent(context, NRZSWebviewActivity.class);
        intent.addFlags(268435456);
        intent.putExtra(GlobalConstants.f16482e, i);
        intent.putExtra(GlobalConstants.f16483f, i2);
        intent.putExtra(GlobalConstants.f16481d, adResultInfoItem);
        context.startActivity(intent);
    }

    /* renamed from: a */
    public static void m25006a(Context context, int i, int i2, int i3, AdResultInfoItem adResultInfoItem) {
        Intent intent = new Intent(context, NRZSWebviewActivity.class);
        intent.addFlags(268435456);
        intent.putExtra(GlobalConstants.f16482e, i);
        intent.putExtra(GlobalConstants.f16483f, i2);
        intent.putExtra(GlobalConstants.f16484g, i3);
        intent.putExtra(GlobalConstants.f16481d, adResultInfoItem);
        context.startActivity(intent);
    }

    /* renamed from: a */
    public static void m25005a(Context context, int i, int i2, int i3, AdResultInfoItem adResultInfoItem, long j) {
        Intent intent = new Intent(context, NRZSWebviewActivity.class);
        intent.addFlags(268435456);
        intent.putExtra(GlobalConstants.f16482e, i);
        intent.putExtra(GlobalConstants.f16483f, i2);
        intent.putExtra(GlobalConstants.f16484g, i3);
        intent.putExtra(GlobalConstants.f16481d, adResultInfoItem);
        intent.putExtra("ORDERID", j);
        context.startActivity(intent);
    }

    @Override // com.angel.nrzs.app.base.AppBaseActivity, com.nrzs.libcommon.BaseActivity, android.support.p006v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        EventBus.m3448a().m3446a(this);
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: b */
    public void mo18303b() {
        ImmersionBar.m20080a(this).m20087a(C0692R.color.f1303bb).m19974h(true).m19994f();
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: a */
    public void mo18305a(Bundle bundle) {
        this.f5426b = (LinearLayout) findViewById(C0692R.C0694id.f2738jd);
        this.f5427c = (RelativeLayout) findViewById(C0692R.C0694id.rl_title_all);
        this.f5428d = (TextView) findViewById(C0692R.C0694id.f3089we);
        this.f5431g = (TextView) findViewById(C0692R.C0694id.textexchange);
        this.f5432h = (TextView) findViewById(C0692R.C0694id.texquestio);
        this.f5429e = (ImageView) findViewById(C0692R.C0694id.f2520ca);
        this.f5430f = (ImageView) findViewById(C0692R.C0694id.finish_img);
        this.f5435k = new NRZSX5WebView(this);
        this.f5426b.addView(this.f5435k, -1, -1);
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: b */
    public void mo18302b(Bundle bundle) {
        getIntent();
        this.f5436l = (AdResultInfoItem) getIntent().getParcelableExtra(GlobalConstants.f16481d);
        this.f5438n = getIntent().getIntExtra(GlobalConstants.f16483f, 1);
        this.f5437m = getIntent().getIntExtra(GlobalConstants.f16482e, 3);
        this.f5439o = getIntent().getIntExtra(GlobalConstants.f16484g, 1);
        this.f5440p = getIntent().getLongExtra("ORDERID", 0L);
        AdResultInfoItem adResultInfoItem = this.f5436l;
        if (adResultInfoItem != null && adResultInfoItem.ExecArgs.contains("RedirectToPay")) {
            m24996c();
        }
        AdResultInfoItem adResultInfoItem2 = this.f5436l;
        if (adResultInfoItem2 != null && adResultInfoItem2.Title.equals("FAQ")) {
            this.f5432h.setVisibility(0);
        }
        m24993f();
    }

    /* renamed from: c */
    public void m24996c() {
        this.f5431g.setVisibility(0);
    }

    @Override // com.angel.nrzs.app.base.AppBaseActivity, android.support.p006v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        NRZSX5WebView nRZSX5WebView = this.f5435k;
        if (nRZSX5WebView != null) {
            nRZSX5WebView.destroy();
        }
        super.onDestroy();
        if (EventBus.m3448a().m3434b(this)) {
            EventBus.m3448a().m3430c(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        m24999a(true);
        this.f5436l = (AdResultInfoItem) intent.getParcelableExtra(GlobalConstants.f16481d);
        if (this.f5436l.ExecArgs.contains("RedirectToPay")) {
            m24996c();
        }
        m24993f();
    }

    /* renamed from: f */
    private void m24993f() {
        String str;
        AdResultInfoItem adResultInfoItem = this.f5436l;
        if (adResultInfoItem != null) {
            TextView textView = this.f5428d;
            if (textView != null) {
                textView.setText(adResultInfoItem.Title);
            }
            str = this.f5436l.ExecArgs;
            if (str != null) {
                UrlBaseRequest urlBaseRequest = new UrlBaseRequest();
                if (this.f5437m != 99) {
                    String str2 = "";
                    try {
                        urlBaseRequest.setUserId(LoginManager.m12620d().m12614j());
                        urlBaseRequest.setUserName(LoginManager.m12620d().m12630a().UserName);
                        urlBaseRequest.setPayBusType(this.f5439o);
                        str2 = urlBaseRequest.toPrames();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (this.f5439o == 5) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(str);
                        sb.append(str.contains("?") ? "" : "?");
                        sb.append(C4745bt.f20071b);
                        sb.append(str2);
                        sb.append("&orderId=");
                        sb.append(this.f5440p);
                        str = sb.toString();
                    } else {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append(str);
                        sb2.append(str.contains("?") ? "" : "?");
                        sb2.append(C4745bt.f20071b);
                        sb2.append(str2);
                        str = sb2.toString();
                    }
                }
            } else {
                str = "";
            }
        } else {
            str = "";
        }
        this.f5435k.m24845a(str, "", null);
        CookieSyncManager.createInstance(this);
        CookieSyncManager.getInstance().sync();
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: d */
    public void mo18300d() {
        this.f5432h.setOnClickListener(new View.OnClickListener() { // from class: com.angel.nrzs.ui.activity.NRZSWebviewActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RouterUtils.toQuesttion();
            }
        });
        this.f5429e.setOnClickListener(new View.OnClickListener() { // from class: com.angel.nrzs.ui.activity.NRZSWebviewActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (NRZSWebviewActivity.this.f5435k.canGoBack()) {
                    NRZSWebviewActivity.this.f5435k.goBack();
                } else {
                    NRZSWebviewActivity.this.m24994e();
                }
            }
        });
        this.f5430f.setOnClickListener(new View.OnClickListener() { // from class: com.angel.nrzs.ui.activity.NRZSWebviewActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NRZSWebviewActivity.this.finish();
            }
        });
        this.f5431g.setOnClickListener(new View.OnClickListener() { // from class: com.angel.nrzs.ui.activity.NRZSWebviewActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                new DialogC5331eu(view.getContext()).show();
            }
        });
    }

    /* renamed from: a */
    public void m24999a(boolean z) {
        RelativeLayout relativeLayout = this.f5427c;
        if (relativeLayout != null) {
            relativeLayout.setVisibility(z ? 0 : 8);
        }
    }

    /* renamed from: a */
    public void m25001a(String str, String str2) {
        NRZSX5WebView nRZSX5WebView = this.f5435k;
        if (nRZSX5WebView != null) {
            nRZSX5WebView.loadUrl("javascript:showImgCallBack('" + str + "','" + str2 + "')");
        }
    }

    /* renamed from: a */
    public void m25002a(String str) {
        if (this.f5428d != null) {
            this.f5434j = str;
            this.f5441q.sendEmptyMessage(1);
        }
    }

    @Subscribe(m3389a = ThreadMode.MAIN)
    /* renamed from: a */
    public void m25000a(PayEvent.C3680a aVar) {
        Log.e("paysueesee", "收到刷新的回调");
        NRZSX5WebView nRZSX5WebView = this.f5435k;
        if (nRZSX5WebView != null) {
            nRZSX5WebView.loadUrl(aVar.f16685a);
        }
    }

    /* renamed from: b */
    public void m24997b(String str) {
        NRZSX5WebView nRZSX5WebView = this.f5435k;
        if (nRZSX5WebView != null) {
            nRZSX5WebView.loadUrl("javascript:MiGuPaySuccess(" + str + ")");
        }
    }

    @Override // android.support.p006v7.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            if (this.f5435k.canGoBack()) {
                this.f5435k.goBack();
                return true;
            }
            m24994e();
        }
        return super.onKeyDown(i, keyEvent);
    }

    /* renamed from: e */
    public void m24994e() {
        try {
            if (this.f5438n == 0) {
                MainActivity.m25033a(this);
            }
            finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
