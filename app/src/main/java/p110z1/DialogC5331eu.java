package p110z1;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.angel.nrzs.C0692R;
import com.angel.nrzs.ui.activity.NRZSWebviewActivity;
import com.nrzs.data.base.BaseResponse;
import com.nrzs.data.other.bean.AdResultInfoItem;
import com.nrzs.data.other.bean.request.GetExchangeRequestInfo;
import com.nrzs.data.other.bean.response.ExchageResponseinfo;
import com.nrzs.http.ThreadCallback;
import com.nrzs.http.UICallback;

/* compiled from: CdKeyDialog.java */
/* renamed from: z1.eu */
/* loaded from: classes3.dex */
public class DialogC5331eu extends Dialog implements View.OnClickListener {

    /* renamed from: g */
    private static final long f21538g = 2000;

    /* renamed from: a */
    Context f21540a;

    /* renamed from: c */
    private LinearLayout f21542c;

    /* renamed from: d */
    private LinearLayout f21543d;

    /* renamed from: e */
    private LinearLayout f21544e;

    /* renamed from: h */
    private TextView f21546h;

    /* renamed from: i */
    private TextView f21547i;

    /* renamed from: j */
    private TextView f21548j;

    /* renamed from: k */
    private LinearLayout f21549k;

    /* renamed from: l */
    private EditText f21550l;

    /* renamed from: m */
    private String f21551m;

    /* renamed from: n */
    private TextView f21552n;

    /* renamed from: o */
    private TextView f21553o;

    /* renamed from: p */
    private TextView f21554p;

    /* renamed from: q */
    private TextView f21555q;

    /* renamed from: r */
    private TextView f21556r;

    /* renamed from: s */
    private TextView f21557s;

    /* renamed from: t */
    private TextView f21558t;

    /* renamed from: u */
    private TextView f21559u;

    /* renamed from: v */
    private TextView f21560v;

    /* renamed from: w */
    private ImageView f21561w;

    /* renamed from: x */
    private ImageView f21562x;

    /* renamed from: y */
    private ImageView f21563y;

    /* renamed from: f */
    private long f21545f = 0;

    /* renamed from: z */
    private ThreadCallback<BaseResponse<ExchageResponseinfo>, String> f21564z = new ThreadCallback<BaseResponse<ExchageResponseinfo>, String>() { // from class: z1.eu.1
        /* renamed from: a */
        public BaseResponse<ExchageResponseinfo> onResponse(String str) {
            BaseResponse<ExchageResponseinfo> baseResponse = (BaseResponse) apa.m11877a(str, new TypeToken<BaseResponse<ExchageResponseinfo>>() { // from class: z1.eu.1.1
            });
            if (baseResponse != null) {
                return baseResponse;
            }
            return null;
        }
    };

    /* renamed from: A */
    private UICallback<BaseResponse<ExchageResponseinfo>> f21539A = new UICallback<BaseResponse<ExchageResponseinfo>>() { // from class: z1.eu.2
        @Override // com.nrzs.http.UICallback
        /* renamed from: a */
        public void mo3021a(Throwable th) {
        }

        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo3022a(BaseResponse<ExchageResponseinfo> baseResponse) {
            if (baseResponse == null) {
                return;
            }
            if (baseResponse.code == 1) {
                if (baseResponse.data.BatchType == 1) {
                    DialogC5331eu.this.f21552n.setVisibility(8);
                    DialogC5331eu.this.f21549k.setVisibility(8);
                    DialogC5331eu.this.f21542c.setVisibility(0);
                    ExchageResponseinfo exchageResponseinfo = baseResponse.data;
                    DialogC5331eu.this.f21555q.setText(exchageResponseinfo.VIPExpireTime);
                    DialogC5331eu.this.f21556r.setText(exchageResponseinfo.SGB);
                } else if (baseResponse.data.BatchType == 2) {
                    DialogC5331eu.this.f21552n.setVisibility(8);
                    DialogC5331eu.this.f21549k.setVisibility(8);
                    DialogC5331eu.this.f21543d.setVisibility(0);
                    ExchageResponseinfo exchageResponseinfo2 = baseResponse.data;
                    TextView textView = DialogC5331eu.this.f21558t;
                    textView.setText(exchageResponseinfo2.GoldNum + "个");
                    TextView textView2 = DialogC5331eu.this.f21559u;
                    textView2.setText(exchageResponseinfo2.GoldCoinNum + "个");
                } else if (baseResponse.data.BatchType == 3 || baseResponse.data.BatchType == 4) {
                    DialogC5331eu.this.f21544e.setVisibility(0);
                    DialogC5331eu.this.f21552n.setVisibility(8);
                    DialogC5331eu.this.f21549k.setVisibility(8);
                    ExchageResponseinfo exchageResponseinfo3 = baseResponse.data;
                    DialogC5331eu.this.f21546h.setText(exchageResponseinfo3.TypeName);
                    TextView textView3 = DialogC5331eu.this.f21547i;
                    textView3.setText(exchageResponseinfo3.VIPDay + "天");
                    EventBus.m3448a().m3427d(new BuysuccessEvent.C3552a());
                }
                EventBus.m3448a().m3427d(new UserInfoEvent.C3561a());
            } else if (baseResponse.code == 2) {
                new ExchangeDialog(DialogC5331eu.this.getContext(), baseResponse.data.TypeCode, DialogC5331eu.this.f21551m).show();
                DialogC5331eu.this.dismiss();
            } else {
                DialogC5331eu.this.f21552n.setVisibility(0);
                DialogC5331eu.this.f21552n.setText(baseResponse.msg);
            }
        }
    };

    /* renamed from: b */
    private ExchangeRepository f21541b = new ExchangeRepository();

    public DialogC5331eu(@NonNull Context context) {
        super(context);
        this.f21540a = context;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().requestFeature(1);
        setContentView(C0692R.layout.nrzs_cdkey_vip_dialog);
        setCancelable(false);
        m3040a();
        m3036c();
        m3034d();
    }

    /* renamed from: b */
    private void m3038b() {
        if (this.f21541b == null) {
            this.f21541b = new ExchangeRepository();
        }
        GetExchangeRequestInfo getExchangeRequestInfo = new GetExchangeRequestInfo();
        getExchangeRequestInfo.cardnumber = this.f21550l.getText().toString().trim();
        getExchangeRequestInfo.userid = LoginManager.m12620d().m12614j();
        this.f21541b.m12582a(getExchangeRequestInfo, this.f21539A, this.f21564z);
        this.f21551m = this.f21550l.getText().toString().trim();
    }

    /* renamed from: c */
    private void m3036c() {
        this.f21542c = (LinearLayout) findViewById(C0692R.C0694id.f2984t3);
        this.f21543d = (LinearLayout) findViewById(C0692R.C0694id.jb_success);
        this.f21544e = (LinearLayout) findViewById(C0692R.C0694id.ssuccess_lay);
        this.f21549k = (LinearLayout) findViewById(C0692R.C0694id.f2732j6);
        this.f21550l = (EditText) findViewById(C0692R.C0694id.f2607f_);
        this.f21552n = (TextView) findViewById(C0692R.C0694id.f3194zl);
        this.f21553o = (TextView) findViewById(C0692R.C0694id.f3191zh);
        this.f21554p = (TextView) findViewById(C0692R.C0694id.f3190zg);
        this.f21561w = (ImageView) findViewById(C0692R.C0694id.f2697i4);
        this.f21562x = (ImageView) findViewById(C0692R.C0694id.f2698i5);
        this.f21557s = (TextView) findViewById(C0692R.C0694id.f2916qz);
        this.f21555q = (TextView) findViewById(C0692R.C0694id.f3193zk);
        this.f21556r = (TextView) findViewById(C0692R.C0694id.f3192zi);
        this.f21558t = (TextView) findViewById(C0692R.C0694id.tv_vip_count_glod);
        this.f21559u = (TextView) findViewById(C0692R.C0694id.yuer_tv);
        this.f21563y = (ImageView) findViewById(C0692R.C0694id.jb_close);
        this.f21560v = (TextView) findViewById(C0692R.C0694id.jb_ok);
        this.f21548j = (TextView) findViewById(C0692R.C0694id.success_ok);
        this.f21546h = (TextView) findViewById(C0692R.C0694id.ssuccess_typename);
        this.f21547i = (TextView) findViewById(C0692R.C0694id.success_time);
    }

    /* renamed from: d */
    private void m3034d() {
        this.f21561w.setOnClickListener(this);
        this.f21562x.setOnClickListener(this);
        this.f21557s.setOnClickListener(this);
        this.f21553o.setOnClickListener(this);
        this.f21554p.setOnClickListener(this);
        this.f21563y.setOnClickListener(this);
        this.f21560v.setOnClickListener(this);
        this.f21548j.setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        String str;
        int id = view.getId();
        if (id == this.f21561w.getId() || id == this.f21563y.getId() || id == this.f21560v.getId() || this.f21548j.getId() == id) {
            dismiss();
        } else if (id == this.f21562x.getId()) {
            dismiss();
        } else if (id == this.f21553o.getId()) {
            if (this.f21545f == 0) {
                this.f21545f = System.currentTimeMillis();
                m3038b();
                return;
            }
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - this.f21545f > 2000) {
                this.f21545f = currentTimeMillis;
                m3038b();
                return;
            }
            this.f21545f = currentTimeMillis;
            aqg.m11586a(getContext(), "操作太快，等2秒后再试");
        } else if (id == this.f21557s.getId()) {
            dismiss();
        } else if (id == this.f21554p.getId()) {
            if (ChannelDataManager.m12655a().m12650d()) {
                str = ChannelDataManager.m12655a().m12651c();
            } else {
                str = PreSetListManager.m12116a().m12109g();
            }
            AdResultInfoItem adResultInfoItem = new AdResultInfoItem();
            adResultInfoItem.Title = "购买卡密";
            adResultInfoItem.ExecArgs = str;
            NRZSWebviewActivity.m25004a(this.f21540a, 0, 1, adResultInfoItem);
            dismiss();
        }
    }

    /* renamed from: a */
    public void m3040a() {
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        getWindow().setGravity(17);
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        getWindow().getDecorView().setBackgroundColor(getContext().getResources().getColor(C0692R.color.f1440f2));
        getWindow().setAttributes(attributes);
    }
}
