package com.angel.nrzs.ddy.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.angel.nrzs.C0692R;
import com.angel.nrzs.p017ui.activity.NRZSWebviewActivity;
import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.Utils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.cyjh.ddysdk.device.base.constants.DdyDeviceErrorConstants;
import com.cyjh.ddysdk.device.command.DdyDeviceCommandContract;
import com.cyjh.ddysdk.device.command.DdyDeviceCommandHelper;
import com.cyjh.ddysdk.order.DdyOrderContract;
import com.cyjh.ddysdk.order.DdyOrderHelper;
import com.cyjh.ddysdk.order.base.bean.DdyOrderInfo;
import com.cyjh.ddysdk.order.base.constants.DdyOrderErrorConstants;
import com.live.ddy.visual.activity.DdyMediaActivity;
import com.nrzs.base.router.provider.ProviderFactory;
import com.nrzs.data.ddy.bean.YsjResultInfo;
import com.nrzs.data.ddy.bean.request.GetDeviteToken;
import com.nrzs.data.ddy.bean.respond.DeviceToken;
import com.nrzs.data.ddy.bean.respond.OrderDaileInfo;
import com.nrzs.http.UICallback;
import java.util.Timer;
import java.util.TimerTask;
import org.apache.tools.ant.taskdefs.WaitFor;
import p110z1.AlterNameDialog;
import p110z1.ChannelDataManager;
import p110z1.DdyManager;
import p110z1.DdyRepository;
import p110z1.EventCollectManager;
import p110z1.EventConstants;
import p110z1.GroupManager;
import p110z1.HttpVal;
import p110z1.LoginManager;
import p110z1.aqg;

/* loaded from: classes.dex */
public class DdyHookview extends RelativeLayout implements View.OnClickListener {

    /* renamed from: A */
    private Timer f5314A;

    /* renamed from: B */
    private TimerTask f5315B;

    /* renamed from: C */
    private C0761a f5316C;

    /* renamed from: a */
    private TextView f5319a;

    /* renamed from: b */
    private TextView f5320b;

    /* renamed from: c */
    private TextView f5321c;

    /* renamed from: d */
    private TextView f5322d;

    /* renamed from: e */
    private TextView f5323e;

    /* renamed from: f */
    private TextView f5324f;

    /* renamed from: g */
    private TextView f5325g;

    /* renamed from: h */
    private TextView f5326h;

    /* renamed from: i */
    private TextView f5327i;

    /* renamed from: j */
    private TextView f5328j;

    /* renamed from: k */
    private TextView f5329k;

    /* renamed from: l */
    private TextView f5330l;

    /* renamed from: m */
    private LinearLayout f5331m;

    /* renamed from: n */
    private ImageView f5332n;

    /* renamed from: o */
    private TextView f5333o;

    /* renamed from: p */
    private ImageView f5334p;

    /* renamed from: q */
    private OrderDaileInfo f5335q;

    /* renamed from: r */
    private DdyOrderInfo f5336r;

    /* renamed from: s */
    private LinearLayout f5337s;

    /* renamed from: u */
    private DdyRepository f5339u;

    /* renamed from: w */
    private Animation f5341w;

    /* renamed from: x */
    private RelativeLayout f5342x;

    /* renamed from: y */
    private ImageView f5343y;

    /* renamed from: t */
    private int f5338t = 0;

    /* renamed from: v */
    private String f5340v = "0E69F586843E466CB88D299A2D336FB333554C6C70D047E49E8AFB07FC56AEBE";

    /* renamed from: z */
    private String f5344z = "DdyHookview";

    /* renamed from: D */
    private boolean f5317D = true;

    /* renamed from: E */
    private UICallback<DeviceToken> f5318E = new UICallback<DeviceToken>() { // from class: com.angel.nrzs.ddy.view.DdyHookview.1
        @Override // com.nrzs.http.UICallback
        /* renamed from: a */
        public void mo3021a(Throwable th) {
            aqg.m11586a(DdyHookview.this.getContext(), "获取deviceToken失败");
        }

        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo3022a(DeviceToken deviceToken) {
            if (deviceToken != null) {
                DdyHookview.this.f5340v = deviceToken.AccessToken;
                DdyHookview.this.m25073e();
                return;
            }
            aqg.m11586a(DdyHookview.this.getContext(), "获取deviceToken失败");
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: k */
    public void m25061k() {
        this.f5332n.startAnimation(this.f5341w);
    }

    public DdyHookview(Context context) {
        super(context);
    }

    public DdyHookview(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public DdyHookview(Context context, OrderDaileInfo orderDaileInfo) {
        super(context);
        m25084a();
        this.f5335q = orderDaileInfo;
        Log.e("DdyHookview_cj", "DDYviews");
        m25057o();
        m25058n();
        m25060l();
        m25059m();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.angel.nrzs.ddy.view.DdyHookview$a */
    /* loaded from: classes.dex */
    public class C0761a extends BroadcastReceiver {
        private C0761a() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            long longExtra = intent.getLongExtra("orderid", 0L);
            if (DdyHookview.this.f5336r != null && DdyHookview.this.f5336r.OrderId == longExtra) {
                DdyHookview.this.m25059m();
            }
        }
    }

    /* renamed from: a */
    public void m25084a() {
        IntentFilter intentFilter = new IntentFilter("DDY_FINISH");
        if (this.f5316C == null) {
            this.f5316C = new C0761a();
        }
        getContext().registerReceiver(this.f5316C, intentFilter);
    }

    /* renamed from: b */
    public void m25079b() {
        if (this.f5316C != null) {
            getContext().unregisterReceiver(this.f5316C);
        }
    }

    /* renamed from: l */
    private void m25060l() {
        this.f5319a.setText(this.f5335q.DeviceName);
        TextView textView = this.f5320b;
        textView.setText("编号 ：" + this.f5335q.DDYOrderId);
        TextView textView2 = this.f5321c;
        textView2.setText("剩余 ：" + this.f5335q.RemainTime);
        this.f5326h.setText(this.f5335q.TypeName);
        if (this.f5335q.TypeCode == 11 || this.f5335q.TypeCode == 4) {
            this.f5334p.setImageDrawable(getResources().getDrawable(C0692R.C0693drawable.f2175ka));
        } else if (this.f5335q.TypeCode == 12) {
            this.f5334p.setImageDrawable(getResources().getDrawable(C0692R.C0693drawable.f2178kd));
        } else if (this.f5335q.TypeCode == 7) {
            this.f5334p.setImageDrawable(getResources().getDrawable(C0692R.C0693drawable.f2177kc));
        } else if (this.f5335q.TypeCode == 3 || this.f5335q.TypeCode == 9) {
            this.f5334p.setImageDrawable(getResources().getDrawable(C0692R.C0693drawable.f2176kb));
        } else {
            this.f5334p.setImageDrawable(getResources().getDrawable(C0692R.C0693drawable.f2177kc));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: m */
    public void m25059m() {
        Log.e("requestOrderDetail", "initdata");
        if (DdyManager.m3122c().m3128a()) {
            DdyOrderHelper.getInstance().requestOrderDetail(Long.parseLong(this.f5335q.DDYOrderId), DdyManager.m3122c().m3123b(), "", "", new DdyOrderContract.TCallback<DdyOrderInfo>() { // from class: com.angel.nrzs.ddy.view.DdyHookview.2
                /* renamed from: a */
                public void onSuccess(DdyOrderInfo ddyOrderInfo) {
                    DdyHookview.this.f5336r = ddyOrderInfo;
                    if (DdyHookview.this.f5317D && DdyHookview.this.f5336r != null) {
                        DdyHookview.this.m25069g();
                        DdyHookview.this.f5317D = false;
                    }
                    Log.e("requestOrderDetail", DdyHookview.this.f5336r.OrderStatus + "---onSuccess");
                    Log.e("requestOrderDetail", "onSuccess" + DdyHookview.this.f5336r.OrderStatus);
                    if (DdyHookview.this.f5336r.OrderStatus == 5 || DdyHookview.this.f5336r.OrderStatus == 7) {
                        DdyHookview.this.m25077c();
                        if (DdyHookview.this.f5336r.OrderStatus == 5) {
                            DdyHookview.this.f5333o.setText("重启中, 请稍后");
                        } else if (DdyHookview.this.f5336r.OrderStatus == 7) {
                            DdyHookview.this.f5333o.setText("恢复出厂设置, 请稍后");
                        }
                        DdyHookview.this.f5331m.setVisibility(0);
                        DdyHookview.this.m25061k();
                        return;
                    }
                    DdyHookview.this.m25075d();
                    DdyHookview.this.m25067h();
                    DdyHookview.this.f5331m.setVisibility(8);
                }

                @Override // com.cyjh.ddysdk.order.DdyOrderContract.TCallback
                public void onFail(DdyOrderErrorConstants ddyOrderErrorConstants, String str) {
                    Log.e("requestOrderDetail", str);
                }
            });
        }
    }

    /* renamed from: n */
    private void m25058n() {
        this.f5325g.setOnClickListener(this);
        this.f5324f.setOnClickListener(this);
        this.f5323e.setOnClickListener(this);
        this.f5343y.setOnClickListener(this);
        this.f5322d.setOnClickListener(this);
        this.f5319a.setOnClickListener(this);
        this.f5328j.setOnClickListener(this);
        this.f5327i.setOnClickListener(this);
        this.f5329k.setOnClickListener(this);
        this.f5330l.setOnClickListener(this);
    }

    /* renamed from: o */
    private void m25057o() {
        LayoutInflater.from(getContext()).inflate(C0692R.layout.nrzs_view_ddy, this);
        this.f5319a = (TextView) findViewById(C0692R.C0694id.ysj_name);
        this.f5331m = (LinearLayout) findViewById(C0692R.C0694id.device_state_lay);
        this.f5332n = (ImageView) findViewById(C0692R.C0694id.f2472b0);
        this.f5333o = (TextView) findViewById(C0692R.C0694id.loading_tv);
        this.f5320b = (TextView) findViewById(C0692R.C0694id.ysj_num);
        this.f5321c = (TextView) findViewById(C0692R.C0694id.ysj_time);
        this.f5322d = (TextView) findViewById(C0692R.C0694id.ysj_flash);
        this.f5334p = (ImageView) findViewById(C0692R.C0694id.f2471az);
        this.f5326h = (TextView) findViewById(C0692R.C0694id.TypeName);
        this.f5323e = (TextView) findViewById(C0692R.C0694id.ysj_start);
        this.f5324f = (TextView) findViewById(C0692R.C0694id.ysj_menu);
        this.f5325g = (TextView) findViewById(C0692R.C0694id.ysj_buy);
        this.f5328j = (TextView) findViewById(C0692R.C0694id.BuyTv);
        this.f5329k = (TextView) findViewById(C0692R.C0694id.reStart);
        this.f5330l = (TextView) findViewById(C0692R.C0694id.factory);
        this.f5337s = (LinearLayout) findViewById(C0692R.C0694id.menu_lay);
        this.f5343y = (ImageView) findViewById(C0692R.C0694id.device_img);
        this.f5327i = (TextView) findViewById(C0692R.C0694id.alter_name_btn);
        this.f5342x = (RelativeLayout) findViewById(C0692R.C0694id.button_lay);
        this.f5331m.setVisibility(8);
        this.f5341w = AnimationUtils.loadAnimation(getContext(), C0692R.C0693drawable.f1965ao);
        this.f5332n.setAnimation(this.f5341w);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.e("ddyhook", "onDetachedFromWindow");
        m25079b();
    }

    /* renamed from: c */
    public void m25077c() {
        Log.e("requestOrderDetail", "timerstar");
        m25075d();
        this.f5314A = new Timer();
        this.f5315B = new TimerTask() { // from class: com.angel.nrzs.ddy.view.DdyHookview.3
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                DdyHookview.this.m25059m();
            }
        };
        this.f5314A.scheduleAtFixedRate(this.f5315B, WaitFor.ONE_MINUTE, WaitFor.ONE_MINUTE);
    }

    /* renamed from: d */
    public void m25075d() {
        Timer timer = this.f5314A;
        if (timer != null) {
            timer.cancel();
            this.f5314A = null;
        }
        TimerTask timerTask = this.f5315B;
        if (timerTask != null) {
            timerTask.cancel();
            this.f5315B = null;
        }
    }

    /* renamed from: e */
    public void m25073e() {
        DdyOrderInfo ddyOrderInfo = this.f5336r;
        if (ddyOrderInfo == null) {
            aqg.m11586a(getContext(), "设备连接中，请稍后再试");
            m25059m();
        } else if (ddyOrderInfo.OrderStatus == 11) {
            aqg.m11586a(getContext(), "设备正在优化中请稍后再试");
            m25059m();
        } else if (this.f5336r.OrderStatus == 2) {
            EventCollectManager.m12642a().m12640a(getContext(), this.f5335q.TypeName, "进入云手机", EventConstants.f16429af);
            DdyMediaActivity.m19028a(getContext(), this.f5336r.OrderId, DdyManager.m3122c().m3123b(), this.f5340v);
        } else {
            aqg.m11586a(getContext(), "设备连接中，请稍后再试");
            m25059m();
        }
    }

    /* renamed from: f */
    public void m25071f() {
        if (this.f5339u == null) {
            this.f5339u = new DdyRepository();
        }
        GetDeviteToken getDeviteToken = new GetDeviteToken();
        getDeviteToken.UserId = LoginManager.m12620d().m12614j();
        getDeviteToken.OrderId = this.f5335q.f10629Id;
        this.f5339u.m12875a(getDeviteToken, this.f5318E);
    }

    /* renamed from: g */
    public void m25069g() {
        DdyDeviceCommandHelper.getInstance().setPresetApp(this.f5336r, DdyManager.m3122c().m3123b(), new DdyDeviceCommandContract.Callback<String>() { // from class: com.angel.nrzs.ddy.view.DdyHookview.4
            /* renamed from: a */
            public void success(String str) {
            }

            @Override // com.cyjh.ddysdk.device.command.DdyDeviceCommandContract.Callback
            public void failuer(DdyDeviceErrorConstants ddyDeviceErrorConstants, String str) {
            }
        });
    }

    /* renamed from: h */
    public void m25067h() {
        DdyDeviceCommandHelper.getInstance().screencap(this.f5336r, ScreenUtils.m23306a(), ConvertUtils.m22471a(184.0f), new DdyDeviceCommandContract.ScreenCap.IView() { // from class: com.angel.nrzs.ddy.view.DdyHookview.5
            @Override // com.cyjh.ddysdk.device.command.DdyDeviceCommandContract.ScreenCap.IView
            public void updateScreenCap(long j, byte[] bArr) {
                Glide.with(Utils.m24103a()).load(bArr).apply((BaseRequestOptions<?>) RequestOptions.bitmapTransform(new RoundedCorners(20))).into(DdyHookview.this.f5343y);
            }

            @Override // com.cyjh.ddysdk.device.command.DdyDeviceCommandContract.ScreenCap.IView
            public void updateScreenCapFailure(long j, DdyDeviceErrorConstants ddyDeviceErrorConstants, String str) {
                String str2 = DdyHookview.this.f5344z;
                String concat = "".concat("orderid= " + j);
                String concat2 = concat.concat(" errcode= " + ddyDeviceErrorConstants.name());
                Log.e(str2, concat2.concat(" msg: " + str));
            }
        });
    }

    /* renamed from: i */
    public void m25065i() {
        DdyOrderHelper.getInstance().requestOrderReset(this.f5336r.OrderId, DdyManager.m3122c().m3123b(), this.f5340v, "", new DdyOrderContract.Callback() { // from class: com.angel.nrzs.ddy.view.DdyHookview.6
            @Override // com.cyjh.ddysdk.order.DdyOrderContract.Callback
            public void onSuccess(Object obj) {
                aqg.m11586a(DdyHookview.this.getContext(), "重置成功，自己去取订单，取状态");
                DdyHookview.this.m25059m();
                DdyHookview.this.m25077c();
            }

            @Override // com.cyjh.ddysdk.order.DdyOrderContract.Callback
            public void onFail(DdyOrderErrorConstants ddyOrderErrorConstants, String str) {
                String concat = "".concat(" code=" + ddyOrderErrorConstants.value());
                String concat2 = concat.concat(" msg=" + str);
                Log.i(DdyHookview.this.f5344z, "onFail(): ".concat(concat2));
                Context context = DdyHookview.this.getContext();
                aqg.m11586a(context, "重启失败" + concat2);
                DdyHookview.this.f5331m.setVisibility(8);
            }
        });
    }

    /* renamed from: j */
    public void m25063j() {
        DdyOrderHelper.getInstance().requestOrderReboot(this.f5336r.OrderId, DdyManager.m3122c().m3123b(), this.f5340v, "", new DdyOrderContract.Callback() { // from class: com.angel.nrzs.ddy.view.DdyHookview.7
            @Override // com.cyjh.ddysdk.order.DdyOrderContract.Callback
            public void onSuccess(Object obj) {
                DdyHookview.this.m25059m();
                DdyHookview.this.m25077c();
            }

            @Override // com.cyjh.ddysdk.order.DdyOrderContract.Callback
            public void onFail(DdyOrderErrorConstants ddyOrderErrorConstants, String str) {
                String concat = "".concat(" code=" + ddyOrderErrorConstants.value());
                String concat2 = concat.concat(" msg=" + str);
                Log.i(DdyHookview.this.f5344z, "onFail(): ".concat(concat2));
                Context context = DdyHookview.this.getContext();
                aqg.m11586a(context, "重启失败" + concat2);
                DdyHookview.this.f5331m.setVisibility(8);
            }
        });
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == this.f5324f.getId()) {
            if (this.f5338t == 0) {
                this.f5338t = 1;
                this.f5337s.setVisibility(0);
                EventCollectManager.m12642a().m12640a(getContext(), this.f5335q.TypeName, "菜单显示", EventConstants.f16431ah);
                return;
            }
            this.f5338t = 0;
            this.f5337s.setVisibility(8);
        } else if (id == this.f5323e.getId() || this.f5343y.getId() == id) {
            DdyOrderInfo ddyOrderInfo = this.f5336r;
            if (ddyOrderInfo == null) {
                aqg.m11586a(getContext(), "设备连接中，请稍后再试");
                m25059m();
            } else if (ddyOrderInfo.OrderStatus == 5) {
                aqg.m11586a(getContext(), "正在重启中，请稍后");
            } else if (this.f5336r.OrderStatus == 7) {
                aqg.m11586a(getContext(), "正在恢复出厂设置，请稍后");
            } else {
                m25071f();
            }
        } else if (id == this.f5319a.getId() || id == this.f5327i.getId()) {
            this.f5338t = 0;
            this.f5337s.setVisibility(8);
            new AlterNameDialog(getContext(), new AlterNameDialog.AbstractC5281a() { // from class: com.angel.nrzs.ddy.view.DdyHookview.8
                @Override // p110z1.AlterNameDialog.AbstractC5281a
                /* renamed from: a */
                public void mo3187a(String str) {
                    DdyHookview.this.f5319a.setText(str);
                    GroupManager.m12887a().m12886a(DdyHookview.this.f5335q.f10629Id, str);
                }
            }, this.f5335q.f10629Id).show();
        } else if (this.f5329k.getId() == id) {
            EventCollectManager.m12642a().m12640a(getContext(), this.f5335q.TypeName, "重启", EventConstants.f16432ai);
            DdyOrderInfo ddyOrderInfo2 = this.f5336r;
            if (ddyOrderInfo2 == null) {
                aqg.m11586a(getContext(), "设备连接中，请稍后再试");
                m25059m();
            } else if (ddyOrderInfo2.OrderStatus == 5) {
                aqg.m11586a(getContext(), "正在重启中，请稍后");
            } else if (this.f5336r.OrderStatus == 7) {
                aqg.m11586a(getContext(), "正在恢复出厂设置，请稍后");
            } else {
                this.f5338t = 0;
                this.f5337s.setVisibility(8);
                this.f5331m.setVisibility(0);
                this.f5333o.setText("重启中, 请稍后");
                m25063j();
                m25061k();
            }
        } else if (this.f5330l.getId() == id) {
            EventCollectManager.m12642a().m12640a(getContext(), this.f5335q.TypeName, "恢复出厂设置", EventConstants.f16433aj);
            DdyOrderInfo ddyOrderInfo3 = this.f5336r;
            if (ddyOrderInfo3 == null) {
                aqg.m11586a(getContext(), "设备连接中，请稍后再试");
                m25059m();
            } else if (ddyOrderInfo3.OrderStatus == 5) {
                aqg.m11586a(getContext(), "正在重启中，请稍后");
            } else if (this.f5336r.OrderStatus == 7) {
                aqg.m11586a(getContext(), "正在恢复出厂设置，请稍后");
            } else {
                this.f5338t = 0;
                this.f5337s.setVisibility(8);
                this.f5331m.setVisibility(0);
                this.f5333o.setText("恢复出厂设置, 请稍后");
                m25065i();
                m25061k();
            }
        } else if (this.f5325g.getId() == id || this.f5328j.getId() == id) {
            this.f5338t = 0;
            this.f5337s.setVisibility(8);
            EventCollectManager.m12642a().m12640a(getContext(), this.f5335q.TypeName, "设备页续费", EventConstants.f16428ae);
            if (ChannelDataManager.m12655a().m12650d()) {
                ProviderFactory.createCarkey().showdialog(getContext());
                return;
            }
            YsjResultInfo ysjResultInfo = new YsjResultInfo();
            ysjResultInfo.Title = "续费云手机";
            ysjResultInfo.ExecArgs = HttpVal.f16516c;
            NRZSWebviewActivity.m25005a(getContext(), 0, 1, 5, ysjResultInfo, this.f5335q.f10629Id);
        } else if (id == this.f5322d.getId()) {
            EventCollectManager.m12642a().m12640a(getContext(), this.f5335q.TypeName, "刷新画面", EventConstants.f16430ag);
            DdyOrderInfo ddyOrderInfo4 = this.f5336r;
            if (ddyOrderInfo4 == null) {
                aqg.m11586a(getContext(), "设备连接中，请稍后再试");
                m25059m();
            } else if (ddyOrderInfo4.OrderStatus == 5) {
                aqg.m11586a(getContext(), "正在重启中，请稍后");
            } else if (this.f5336r.OrderStatus == 7) {
                aqg.m11586a(getContext(), "正在恢复出厂设置，请稍后");
            } else if (this.f5336r.OrderStatus == 2) {
                m25067h();
            } else {
                aqg.m11586a(getContext(), "设备连接中，请稍后再试");
                m25059m();
            }
        }
    }
}
