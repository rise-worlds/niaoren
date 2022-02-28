package p110z1;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.blankj.utilcode.util.Utils;
import com.cyjh.ddy.media.media.ActionCode;
import com.nrzs.data.base.BaseResponse;
import com.nrzs.data.other.bean.AdResultInfoItem;
import com.nrzs.data.other.bean.request.GetExchangeRequestInfo;
import com.nrzs.data.other.bean.response.ExchageResponseinfo;
import com.nrzs.data.user.bean.UserInfo;
import com.nrzs.http.ThreadCallback;
import com.nrzs.http.UICallback;
import com.nrzs.ft.C1990R;
import p110z1.FtEvent;

/* renamed from: z1.ant */
/* loaded from: classes3.dex */
public class CdKeyDialog extends Dialog implements View.OnClickListener {

    /* renamed from: a */
    Context f16841a;

    /* renamed from: c */
    private LinearLayout f16843c;

    /* renamed from: d */
    private LinearLayout f16844d;

    /* renamed from: e */
    private EditText f16845e;

    /* renamed from: f */
    private TextView f16846f;

    /* renamed from: g */
    private TextView f16847g;

    /* renamed from: h */
    private TextView f16848h;

    /* renamed from: i */
    private TextView f16849i;

    /* renamed from: j */
    private TextView f16850j;

    /* renamed from: k */
    private TextView f16851k;

    /* renamed from: l */
    private ImageView f16852l;

    /* renamed from: m */
    private ImageView f16853m;

    /* renamed from: n */
    private ThreadCallback<BaseResponse<ExchageResponseinfo>, String> f16854n = new ThreadCallback<BaseResponse<ExchageResponseinfo>, String>() { // from class: z1.ant.1
        /* renamed from: a */
        public BaseResponse<ExchageResponseinfo> onResponse(String str) {
            BaseResponse<ExchageResponseinfo> baseResponse = (BaseResponse) apa.m11877a(str, new TypeToken<BaseResponse<ExchageResponseinfo>>() { // from class: z1.ant.1.1
            });
            if (baseResponse != null) {
                return baseResponse;
            }
            return null;
        }
    };

    /* renamed from: o */
    private UICallback<BaseResponse<ExchageResponseinfo>> f16855o = new UICallback<BaseResponse<ExchageResponseinfo>>() { // from class: z1.ant.2
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
                CdKeyDialog.this.f16846f.setVisibility(8);
                CdKeyDialog.this.f16844d.setVisibility(8);
                CdKeyDialog.this.f16843c.setVisibility(0);
                ExchageResponseinfo exchageResponseinfo = baseResponse.data;
                CdKeyDialog.this.f16849i.setText(exchageResponseinfo.VIPExpireTime);
                CdKeyDialog.this.f16850j.setText(exchageResponseinfo.SGB);
                UserInfoManager.m12594a().m12588c(new DoneCallback<UserInfo>() { // from class: z1.ant.2.1
                    /* renamed from: a */
                    public void onDone(UserInfo userInfo) {
                        if (userInfo != null) {
                            EventBus.m3448a().m3427d(new FtEvent.C3566d(userInfo));
                        }
                    }
                });
                return;
            }
            CdKeyDialog.this.f16846f.setVisibility(0);
            CdKeyDialog.this.f16846f.setText(baseResponse.msg);
        }
    };

    /* renamed from: b */
    private ExchangeRepository f16842b = new ExchangeRepository();

    public CdKeyDialog(@NonNull Context context) {
        super(context);
        this.f16841a = context;
        m12210a();
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().requestFeature(1);
        setContentView(C1990R.layout.nrzs_fl_cdkey_vip_dialog);
        setCancelable(false);
        m12208b();
        m12204d();
        m12202e();
    }

    /* renamed from: a */
    public void m12210a() {
        if (getWindow() == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 24) {
            getWindow().setType(2005);
        } else if (Build.VERSION.SDK_INT >= 26) {
            getWindow().setType(2038);
        } else {
            getWindow().setType(ActionCode.CtrlConnectRefuse_2002);
        }
    }

    /* renamed from: c */
    private void m12206c() {
        if (this.f16842b == null) {
            this.f16842b = new ExchangeRepository();
        }
        GetExchangeRequestInfo getExchangeRequestInfo = new GetExchangeRequestInfo();
        getExchangeRequestInfo.cardnumber = this.f16845e.getText().toString().trim();
        getExchangeRequestInfo.userid = LoginManager.m12620d().m12614j();
        this.f16842b.m12582a(getExchangeRequestInfo, this.f16855o, this.f16854n);
    }

    /* renamed from: d */
    private void m12204d() {
        this.f16843c = (LinearLayout) findViewById(C1990R.C1992id.rl_by_succeed);
        this.f16844d = (LinearLayout) findViewById(C1990R.C1992id.ll_buy_vip);
        this.f16845e = (EditText) findViewById(C1990R.C1992id.et_vip_code);
        this.f16846f = (TextView) findViewById(C1990R.C1992id.tv_vip_error);
        this.f16847g = (TextView) findViewById(C1990R.C1992id.tv_vip_convert);
        this.f16848h = (TextView) findViewById(C1990R.C1992id.tv_vip_bycode);
        this.f16852l = (ImageView) findViewById(C1990R.C1992id.iv_vip_close);
        this.f16853m = (ImageView) findViewById(C1990R.C1992id.iv_vip_close_1);
        this.f16851k = (TextView) findViewById(C1990R.C1992id.okbtn);
        this.f16849i = (TextView) findViewById(C1990R.C1992id.tv_vip_day);
        this.f16850j = (TextView) findViewById(C1990R.C1992id.tv_vip_count);
    }

    /* renamed from: e */
    private void m12202e() {
        this.f16852l.setOnClickListener(this);
        this.f16853m.setOnClickListener(this);
        this.f16851k.setOnClickListener(this);
        this.f16847g.setOnClickListener(this);
        this.f16848h.setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        String str;
        int id = view.getId();
        if (id == this.f16852l.getId()) {
            dismiss();
        } else if (id == this.f16853m.getId()) {
            dismiss();
        } else if (id == this.f16851k.getId()) {
            dismiss();
        } else if (id == this.f16847g.getId()) {
            m12206c();
        } else if (id == this.f16848h.getId()) {
            if (ChannelDataManager.m12655a().m12650d()) {
                str = ChannelDataManager.m12655a().m12651c();
            } else {
                str = apf.m11837b(Utils.m24103a(), ShareVal.f16591a, ShareVal.f16616z, "");
            }
            AdResultInfoItem adResultInfoItem = new AdResultInfoItem();
            adResultInfoItem.Title = "购买卡密";
            adResultInfoItem.ExecArgs = str;
            IntentToAssistService.m12807a(view.getContext(), adResultInfoItem, 0);
            dismiss();
        }
    }

    /* renamed from: b */
    public void m12208b() {
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        getWindow().setGravity(17);
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        getWindow().getDecorView().setBackgroundColor(getContext().getResources().getColor(C1990R.color.transparent));
        getWindow().setAttributes(attributes);
    }
}
