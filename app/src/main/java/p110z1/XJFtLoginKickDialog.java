package p110z1;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.nrzs.data.DataApp;
import com.nrzs.data.base.BaseResponse;
import com.nrzs.data.user.bean.request.AutoLogin;
import com.nrzs.data.user.bean.respond.LoginResultV1Info;
import com.nrzs.http.ThreadCallback;
import com.nrzs.http.UICallback;
import com.nrzs.p067ft.C1990R;
import com.nrzs.p067ft.FloatApp;
import com.nrzs.p067ft.p068ui.base.FtBaseDialog;
import p110z1.XNKJEvent;

/* renamed from: z1.adq */
/* loaded from: classes3.dex */
public class XJFtLoginKickDialog extends FtBaseDialog {

    /* renamed from: a */
    private String f15326a;

    /* renamed from: b */
    private ImageView f15327b;

    /* renamed from: c */
    private TextView f15328c;

    /* renamed from: d */
    private TextView f15329d;

    /* renamed from: e */
    private UserRepository f15330e;

    /* renamed from: f */
    private ThreadCallback<BaseResponse<LoginResultV1Info>, String> f15331f = new ThreadCallback<BaseResponse<LoginResultV1Info>, String>() { // from class: z1.adq.1
        /* renamed from: a */
        public BaseResponse<LoginResultV1Info> onResponse(String str) {
            BaseResponse<LoginResultV1Info> baseResponse = (BaseResponse) apa.m11877a(str, new TypeToken<BaseResponse<LoginResultV1Info>>() { // from class: z1.adq.1.1
            });
            if (baseResponse != null) {
                return baseResponse;
            }
            return null;
        }
    };

    /* renamed from: g */
    private UICallback<BaseResponse<LoginResultV1Info>> f15332g = new UICallback<BaseResponse<LoginResultV1Info>>() { // from class: z1.adq.2
        @Override // com.nrzs.http.UICallback
        /* renamed from: a */
        public void mo3021a(Throwable th) {
            XJFtLoginKickDialog.this.f15329d.setEnabled(true);
            ToastUtils.m23030a("登录失败");
        }

        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo3022a(BaseResponse<LoginResultV1Info> baseResponse) {
            XJFtLoginKickDialog.this.f15329d.setEnabled(true);
            if (baseResponse == null) {
                return;
            }
            if (baseResponse.code == 0) {
                ToastUtils.m23030a(baseResponse.msg);
            } else if (baseResponse.code == 1) {
                LogUtils.m23734c("token111", "悬浮窗自动登录成功：name:" + baseResponse.data.UserInfo.UserName + ",token:" + baseResponse.data.AutoLoginToken);
                apf.m11842a(DataApp.m18939d().m18947a(), ShareVal.f16591a, ShareVal.f16597g, baseResponse.data.AutoLoginToken);
                LogUtils.m23720e("token", "ftlog自动登录成功 账号" + baseResponse.data.UserInfo.UserName + baseResponse.data.AutoLoginToken);
                FtUserManager.m12790g().m12796a(baseResponse.data.UserInfo);
                LoginManager.m12620d().m12628a(baseResponse.data.UserInfo);
                EventBus.m3448a().m3427d(new XNKJEvent.C3574d(baseResponse.data.UserInfo));
                XJFtLoginKickDialog.this.dismiss();
            } else if (baseResponse.code == -1) {
                if (baseResponse.data != null) {
                    LoginManager.m12620d().m12626a(baseResponse.data.AutoLoginToken, baseResponse.data.UserId);
                }
                XJFtLoginKickDialog.this.dismiss();
            }
        }
    };

    public XJFtLoginKickDialog(Context context, String str) {
        super(context, C1990R.style.nrzs_assist_dialog_theme);
        this.f15326a = str;
    }

    @Override // com.nrzs.p067ft.p068ui.base.FtBaseDialog
    /* renamed from: a */
    protected void mo3014a() {
        setCancelable(false);
    }

    @Override // com.nrzs.p067ft.p068ui.base.FtBaseDialog
    /* renamed from: b */
    protected void mo3013b() {
        setContentView(C1990R.layout.nrzs_ft_dialog_login_kick);
        this.f15327b = (ImageView) findViewById(C1990R.C1992id.nrzs_ft_iv_close);
        this.f15328c = (TextView) findViewById(C1990R.C1992id.nrzs_ft_tv_exit_login);
        this.f15329d = (TextView) findViewById(C1990R.C1992id.nrzs_ft_tv_reply_login);
    }

    @Override // com.nrzs.p067ft.p068ui.base.FtBaseDialog
    /* renamed from: c */
    protected void mo3012c() {
        this.f15328c.setOnClickListener(new View.OnClickListener() { // from class: z1.adq.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                XJFtLoginKickDialog.this.dismiss();
                FwManager.getInstance().removeXJAssisInfoView();
                IntentToAssistService.m12813a(FloatApp.m18899b().m18901a());
            }
        });
        this.f15329d.setOnClickListener(new View.OnClickListener() { // from class: z1.adq.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                XJFtLoginKickDialog.this.f15329d.setEnabled(false);
                XJFtLoginKickDialog.this.m14284e();
            }
        });
    }

    @Override // com.nrzs.p067ft.p068ui.base.FtBaseDialog
    /* renamed from: d */
    protected void mo3011d() {
        FloatViewManager.m12346a(FloatApp.m18899b().m18901a()).m12327e();
        this.f15330e = new UserRepository();
    }

    /* renamed from: e */
    public void m14284e() {
        String b = apf.m11837b(FloatApp.m18899b().m18901a(), ShareVal.f16591a, ShareVal.f16597g, "");
        LogUtils.m23734c("token111", "悬浮窗自动登录前：token:" + b);
        LogUtils.m23720e("token", "flog自动登录成功 " + b);
        if (!TextUtils.isEmpty(b)) {
            try {
                AutoLogin autoLogin = new AutoLogin();
                autoLogin.token = b;
                this.f15330e.m12544a(autoLogin, this.f15332g, this.f15331f);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
