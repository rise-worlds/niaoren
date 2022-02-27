package com.redwas.redwars;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.cyjh.ddy.media.p035a.ResultTypeConstant;
import com.nrzs.data.base.BaseResponse;
import com.nrzs.data.redbag.bean.MoneyInfo;
import com.nrzs.http.ThreadCallback;
import com.nrzs.http.UICallback;
import com.redwas.bean.RedConfigResutlInfo;
import com.redwas.bean.RedPacket;
import com.redwas.bean.RedRequestResultInfo;
import com.redwas.bean.RequestInfo;
import com.redwas.service.LuckMoneyService;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import p110z1.AccessbilityUtil;
import p110z1.Config;
import p110z1.NRZSCommonManager;
import p110z1.RedManager;
import p110z1.RedRepository;
import p110z1.RedUtil;
import p110z1.TypeToken;
import p110z1.UpDateVersionUtil;
import p110z1.apa;

/* loaded from: classes2.dex */
public class RedbgaIndextActivity extends AppCompatActivity {

    /* renamed from: a */
    private TextView f11665a;

    /* renamed from: b */
    private ImageView f11666b;

    /* renamed from: c */
    private TextView f11667c;

    /* renamed from: d */
    private TextView f11668d;

    /* renamed from: e */
    private C2358a f11669e;

    /* renamed from: f */
    private TextView f11670f;

    /* renamed from: g */
    private TextView f11671g;

    /* renamed from: h */
    private TextView f11672h;

    /* renamed from: i */
    private LinearLayout f11673i;

    /* renamed from: j */
    private TextView f11674j;

    /* renamed from: k */
    private TextView f11675k;

    /* renamed from: l */
    private TextView f11676l;

    /* renamed from: m */
    private TextView f11677m;

    /* renamed from: n */
    private MoneyInfo f11678n;

    /* renamed from: o */
    private RedRepository f11679o;

    /* renamed from: p */
    private ImageView f11680p;

    /* renamed from: q */
    private AnimationDrawable f11681q;

    /* renamed from: r */
    private Handler f11682r = new Handler() { // from class: com.redwas.redwars.RedbgaIndextActivity.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what == 1) {
                RedbgaIndextActivity.this.f11670f.setText(RedbgaIndextActivity.this.f11678n.f10656g);
                RedbgaIndextActivity.this.f11671g.setText(RedbgaIndextActivity.this.f11678n.f10657h);
                RedbgaIndextActivity redbgaIndextActivity = RedbgaIndextActivity.this;
                redbgaIndextActivity.m18175a(redbgaIndextActivity.f11678n);
                RedbgaIndextActivity.this.m18178a();
            }
        }
    };

    /* renamed from: s */
    private UICallback f11683s = new UICallback<BaseResponse<RedRequestResultInfo>>() { // from class: com.redwas.redwars.RedbgaIndextActivity.3
        @Override // com.nrzs.http.UICallback
        /* renamed from: a */
        public void mo3021a(Throwable th) {
            NRZSCommonManager.m11698b().m11700a();
        }

        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo3022a(BaseResponse<RedRequestResultInfo> baseResponse) {
            List<RedConfigResutlInfo> weChatConfigs;
            if (baseResponse == null) {
                Log.e("红包", "返回是null" + baseResponse.msg);
            } else if (baseResponse.code == 1 && (weChatConfigs = baseResponse.data.getWeChatConfigs()) != null) {
                String b = UpDateVersionUtil.m11429b(RedbgaIndextActivity.this);
                for (int i = 0; i < weChatConfigs.size(); i++) {
                    if (b.equals(weChatConfigs.get(i).getWeChatVersion())) {
                        RedUtil.m11452a().m11451a(weChatConfigs.get(i).getConfigInfo());
                        Log.e("红包", "设置id" + baseResponse.msg);
                        return;
                    }
                }
            }
        }
    };

    /* renamed from: t */
    private ThreadCallback f11684t = new ThreadCallback<BaseResponse<RedRequestResultInfo>, String>() { // from class: com.redwas.redwars.RedbgaIndextActivity.4
        /* renamed from: a */
        public BaseResponse<RedRequestResultInfo> onResponse(String str) {
            BaseResponse<RedRequestResultInfo> baseResponse = (BaseResponse) apa.m11877a(str, new TypeToken<BaseResponse<RedRequestResultInfo>>() { // from class: com.redwas.redwars.RedbgaIndextActivity.4.1
            });
            if (baseResponse != null) {
                return baseResponse;
            }
            return null;
        }
    };

    /* renamed from: u */
    private UICallback f11685u = new UICallback<BaseResponse<Object>>() { // from class: com.redwas.redwars.RedbgaIndextActivity.5
        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo3022a(BaseResponse<Object> baseResponse) {
        }

        @Override // com.nrzs.http.UICallback
        /* renamed from: a */
        public void mo3021a(Throwable th) {
        }
    };

    /* renamed from: v */
    private ThreadCallback f11686v = new ThreadCallback<BaseResponse<Object>, String>() { // from class: com.redwas.redwars.RedbgaIndextActivity.6
        /* renamed from: a */
        public BaseResponse<Object> onResponse(String str) {
            BaseResponse<Object> baseResponse = (BaseResponse) apa.m11877a(str, new TypeToken<BaseResponse<Object>>() { // from class: com.redwas.redwars.RedbgaIndextActivity.6.1
            });
            if (baseResponse != null) {
                return baseResponse;
            }
            return null;
        }
    };

    /* renamed from: a */
    public static void m18176a(Context context) {
        context.startActivity(new Intent(context, RedbgaIndextActivity.class));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.p006v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C2342R.layout.activity_red_indext);
        IntentFilter intentFilter = new IntentFilter("redbroadcase_action");
        if (this.f11669e == null) {
            this.f11669e = new C2358a();
        }
        registerReceiver(this.f11669e, intentFilter);
        m18160g();
        m18162f();
        m18166d();
        m18164e();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m18171b() {
        if (this.f11681q.isRunning()) {
            this.f11681q.stop();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public void m18168c() {
        this.f11681q.start();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (!AccessbilityUtil.m11528a(this, LuckMoneyService.class)) {
            return;
        }
        if (LuckMoneyService.f11717c) {
            m18177a(0);
            m18171b();
            return;
        }
        m18177a(1);
        m18168c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d */
    public void m18166d() {
        List<MoneyInfo> d = RedManager.m11534c().m11533d();
        if (d == null || d.size() <= 0) {
            this.f11673i.setVisibility(8);
            this.f11670f.setText(ResultTypeConstant.f7213z);
            this.f11671g.setText(ResultTypeConstant.f7213z);
            return;
        }
        String str = d.get(d.size() - 1).f10656g;
        String str2 = d.get(d.size() - 1).f10657h;
        this.f11670f.setText(str);
        this.f11671g.setText(str2);
        if (str == null || str2 == null) {
            RedManager.m11534c().m11541a(ResultTypeConstant.f7213z);
            RedManager.m11534c().m11535b(ResultTypeConstant.f7213z);
        } else {
            RedManager.m11534c().m11541a(str);
            RedManager.m11534c().m11535b(str2);
        }
        m18175a(d.get(d.size() - 1));
    }

    /* renamed from: a */
    public void m18178a() {
        try {
            if (this.f11679o == null) {
                this.f11679o = new RedRepository();
            }
            RedPacket redPacket = new RedPacket();
            redPacket.Money = this.f11678n.f10655f;
            redPacket.PacketType = 2;
            redPacket.DeviceCode = Config.m12527a();
            this.f11679o.m11545a(redPacket, this.f11685u, this.f11686v);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m18175a(MoneyInfo moneyInfo) {
        this.f11673i.setVisibility(0);
        this.f11674j.setText(moneyInfo.f10652c);
        this.f11675k.setText(moneyInfo.f10653d);
        this.f11676l.setText(moneyInfo.f10654e);
        this.f11677m.setText(moneyInfo.f10655f);
    }

    /* renamed from: e */
    private void m18164e() {
        this.f11672h.setOnClickListener(new View.OnClickListener() { // from class: com.redwas.redwars.RedbgaIndextActivity.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                HistoryDialog bVar = new HistoryDialog(RedbgaIndextActivity.this, RedManager.m11534c().m11533d());
                bVar.show();
                WindowManager windowManager = RedbgaIndextActivity.this.getWindowManager();
                bVar.getWindow().setGravity(80);
                Display defaultDisplay = windowManager.getDefaultDisplay();
                WindowManager.LayoutParams attributes = bVar.getWindow().getAttributes();
                attributes.width = defaultDisplay.getWidth();
                bVar.getWindow().setAttributes(attributes);
            }
        });
        this.f11665a.setOnClickListener(new View.OnClickListener() { // from class: com.redwas.redwars.RedbgaIndextActivity.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CoursuActivity.m18179a(view.getContext());
            }
        });
        this.f11666b.setOnClickListener(new View.OnClickListener() { // from class: com.redwas.redwars.RedbgaIndextActivity.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RedbgaIndextActivity.this.finish();
            }
        });
        this.f11667c.setOnClickListener(new View.OnClickListener() { // from class: com.redwas.redwars.RedbgaIndextActivity.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!AccessbilityUtil.m11528a(view.getContext(), LuckMoneyService.class)) {
                    RedbgaIndextActivity.this.startActivity(new Intent("android.settings.ACCESSIBILITY_SETTINGS"));
                } else if (RedbgaIndextActivity.this.f11667c.getText().toString().trim().equals("开始抢红包")) {
                    Log.e("红包", "设置开启");
                    RedbgaIndextActivity.this.m18168c();
                    LuckMoneyService.f11717c = false;
                    RedbgaIndextActivity.this.m18177a(1);
                } else {
                    Log.e("红包", "设置关闭");
                    LuckMoneyService.f11717c = true;
                    RedbgaIndextActivity.this.m18171b();
                    RedbgaIndextActivity.this.m18177a(0);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m18177a(int i) {
        if (i == 1) {
            this.f11668d.setVisibility(0);
            this.f11667c.setText("关闭抢红包");
            return;
        }
        this.f11667c.setText("开始抢红包");
        this.f11668d.setVisibility(8);
    }

    /* renamed from: f */
    private void m18162f() {
        try {
            this.f11679o = new RedRepository();
            String b = UpDateVersionUtil.m11429b(this);
            RequestInfo requestInfo = new RequestInfo();
            int i = 18;
            if (Build.VERSION.SDK_INT < 18) {
                i = 17;
            }
            requestInfo.setAndroidVersion(i);
            requestInfo.setWeChatVersion(b);
            this.f11679o.m11544a(requestInfo, this.f11683s, this.f11684t);
            RedManager.m11534c().m11540a(new RedManager.AbstractC3867a() { // from class: com.redwas.redwars.RedbgaIndextActivity.2
                @Override // p110z1.RedManager.AbstractC3867a
                /* renamed from: b */
                public void mo11529b() {
                }

                @Override // p110z1.RedManager.AbstractC3867a
                /* renamed from: a */
                public void mo11530a() {
                    RedbgaIndextActivity.this.m18166d();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: g */
    private void m18160g() {
        this.f11665a = (TextView) findViewById(C2342R.C2344id.course_btn_redbag);
        this.f11666b = (ImageView) findViewById(C2342R.C2344id.redbag_back);
        this.f11667c = (TextView) findViewById(C2342R.C2344id.start_btn);
        this.f11668d = (TextView) findViewById(C2342R.C2344id.tip_qhb_tv);
        this.f11670f = (TextView) findViewById(C2342R.C2344id.count_red);
        this.f11671g = (TextView) findViewById(C2342R.C2344id.count_money);
        this.f11673i = (LinearLayout) findViewById(C2342R.C2344id.record_lay);
        this.f11674j = (TextView) findViewById(C2342R.C2344id.record_day);
        this.f11675k = (TextView) findViewById(C2342R.C2344id.record_time);
        this.f11676l = (TextView) findViewById(C2342R.C2344id.record_user_name);
        this.f11677m = (TextView) findViewById(C2342R.C2344id.record_money);
        this.f11672h = (TextView) findViewById(C2342R.C2344id.more_histroy);
        this.f11680p = (ImageView) findViewById(C2342R.C2344id.bird_ani_searching);
        this.f11680p.setImageResource(C2342R.C2343drawable.bird_ani_searching);
        this.f11681q = (AnimationDrawable) this.f11680p.getDrawable();
    }

    /* renamed from: com.redwas.redwars.RedbgaIndextActivity$a */
    /* loaded from: classes2.dex */
    private class C2358a extends BroadcastReceiver {
        private C2358a() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String stringExtra = intent.getStringExtra("times");
            String stringExtra2 = intent.getStringExtra("moenys");
            String stringExtra3 = intent.getStringExtra("users");
            Log.e("红包", "在main中time" + stringExtra + "moenys" + stringExtra2 + "users" + stringExtra3);
            String substring = stringExtra2.substring(0, stringExtra2.length() - 1);
            String format = new DecimalFormat("0.00").format(new Double(RedManager.m11534c().m11537b()).doubleValue() + new Double(substring).doubleValue());
            RedManager.m11534c().m11535b(format);
            int intValue = Integer.valueOf(RedManager.m11534c().m11543a()).intValue() + 1;
            Log.e("红包", "在main中time" + stringExtra + "zje" + format + "count" + intValue);
            RedManager c = RedManager.m11534c();
            StringBuilder sb = new StringBuilder();
            sb.append(intValue);
            sb.append("");
            c.m11541a(sb.toString());
            MoneyInfo moneyInfo = new MoneyInfo();
            moneyInfo.f10652c = RedbgaIndextActivity.this.m18158h();
            moneyInfo.f10655f = substring;
            moneyInfo.f10653d = stringExtra;
            moneyInfo.f10654e = stringExtra3;
            moneyInfo.f10657h = format;
            moneyInfo.f10656g = intValue + "";
            RedbgaIndextActivity.this.f11678n = moneyInfo;
            RedManager.m11534c().m11542a(moneyInfo);
            RedbgaIndextActivity.this.f11682r.sendEmptyMessage(1);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.p006v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(this.f11669e);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: h */
    public String m18158h() {
        return new SimpleDateFormat("yyyy年MM月dd日", Locale.getDefault()).format(Long.valueOf(System.currentTimeMillis()));
    }
}
