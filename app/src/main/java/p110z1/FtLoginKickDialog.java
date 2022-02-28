package p110z1;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.cyjh.mobileanjian.ipc.utils.Util;
import com.nrzs.data.DataApp;
import com.nrzs.data.base.BaseResponse;
import com.nrzs.data.p066ft.bean.EnginInteraRequestInfo;
import com.nrzs.data.user.bean.request.AutoLogin;
import com.nrzs.data.user.bean.respond.LoginResultV1Info;
import com.nrzs.http.ThreadCallback;
import com.nrzs.http.UICallback;
import com.nrzs.ft.C1990R;
import com.nrzs.ft.FloatApp;
import com.nrzs.ft.ui.base.FtBaseDialog;
import java.io.File;
import org.apache.tools.ant.taskdefs.optional.vss.MSVSSConstants;
import p110z1.FtEvent;

/* renamed from: z1.anx */
/* loaded from: classes3.dex */
public class FtLoginKickDialog extends FtBaseDialog {

    /* renamed from: a */
    public static final int f16890a = 1;

    /* renamed from: b */
    public static final int f16891b = 2;

    /* renamed from: c */
    public static final int f16892c = 3;

    /* renamed from: d */
    private int f16893d;

    /* renamed from: e */
    private ImageView f16894e;

    /* renamed from: f */
    private TextView f16895f;

    /* renamed from: g */
    private TextView f16896g;

    /* renamed from: h */
    private TextView f16897h;

    /* renamed from: i */
    private UserRepository f16898i;

    /* renamed from: j */
    private ThreadCallback<BaseResponse<LoginResultV1Info>, String> f16899j = new ThreadCallback<BaseResponse<LoginResultV1Info>, String>() { // from class: z1.anx.1
        /* renamed from: a */
        public BaseResponse<LoginResultV1Info> onResponse(String str) {
            LogUtils.m23757a("FtLoginKickDialog", (Object) ("autoLogin onResponse:" + str + "," + Thread.currentThread().getName()));
            BaseResponse<LoginResultV1Info> baseResponse = (BaseResponse) apa.m11877a(str, new TypeToken<BaseResponse<LoginResultV1Info>>() { // from class: z1.anx.1.1
            });
            if (baseResponse != null) {
                return baseResponse;
            }
            return null;
        }
    };

    /* renamed from: k */
    private UICallback<BaseResponse<LoginResultV1Info>> f16900k = new UICallback<BaseResponse<LoginResultV1Info>>() { // from class: z1.anx.2
        @Override // com.nrzs.http.UICallback
        /* renamed from: a */
        public void mo3021a(Throwable th) {
            FtLoginKickDialog.this.f16896g.setEnabled(true);
            ToastUtils.m23030a("登录失败");
        }

        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo3022a(BaseResponse<LoginResultV1Info> baseResponse) {
            FtLoginKickDialog.this.f16896g.setEnabled(true);
            if (baseResponse == null) {
                return;
            }
            if (baseResponse.code == 0) {
                ToastUtils.m23030a(baseResponse.msg);
                FtLoginKickDialog.this.dismiss();
                IntentToAssistService.m12813a(FloatApp.m18899b().m18901a());
            } else if (baseResponse.code == 1) {
                LogUtils.m23734c("token111", "悬浮窗自动登录成功：name:" + baseResponse.data.UserInfo.UserName + ",token:" + baseResponse.data.AutoLoginToken);
                apf.m11842a(DataApp.m18939d().m18947a(), ShareVal.f16591a, ShareVal.f16597g, baseResponse.data.AutoLoginToken);
                Log.e("token", "ftlog自动登录成功 账号" + baseResponse.data.UserInfo.UserName + baseResponse.data.AutoLoginToken);
                FtUserManager.m12790g().m12796a(baseResponse.data.UserInfo);
                LoginManager.m12620d().m12628a(baseResponse.data.UserInfo);
                EnginInteraRequestInfo enginInteraRequestInfo = new EnginInteraRequestInfo();
                enginInteraRequestInfo.AppSign = Util.getAppSinature(FloatApp.m18899b().m18901a());
                enginInteraRequestInfo.Command = 1;
                enginInteraRequestInfo.SessionId = FtUserManager.m12790g().m12794c();
                enginInteraRequestInfo.UserId = FtUserManager.m12790g().m12797a();
                enginInteraRequestInfo.DesKey = FtUserManager.m12790g().m12793d();
                enginInteraRequestInfo.ScriptCacheRPath = ShareVal.f16591a + File.separatorChar + MSVSSConstants.SS_EXE;
                enginInteraRequestInfo.dycIp = LocalHttp.f16542c;
                FloatAssistManager.m12397i().m12420a(enginInteraRequestInfo, "钥匙激活成功");
                EventBus.m3448a().m3427d(new FtEvent.C3567e(baseResponse.data.UserInfo));
                FloatViewManager.m12346a(FtLoginKickDialog.this.getContext().getApplicationContext()).m12317l();
            } else if (baseResponse.code == -1) {
                FloatViewManager.m12346a(FloatApp.m18899b().m18901a()).m12329d(FtLoginKickDialog.this.getContext());
                FloatViewManager.m12346a(FtLoginKickDialog.this.getContext().getApplicationContext()).m12317l();
            }
        }
    };

    public FtLoginKickDialog(Context context, int i) {
        super(context, C1990R.style.nrzs_assist_dialog_theme);
        this.f16893d = i;
    }

    @Override // com.nrzs.ft.ui.base.FtBaseDialog
    /* renamed from: a */
    protected void mo3014a() {
        setCancelable(false);
    }

    @Override // com.nrzs.ft.ui.base.FtBaseDialog
    /* renamed from: b */
    protected void mo3013b() {
        setContentView(C1990R.layout.nrzs_ft_dialog_login_kick);
        this.f16894e = (ImageView) findViewById(C1990R.C1992id.nrzs_ft_iv_close);
        this.f16895f = (TextView) findViewById(C1990R.C1992id.nrzs_ft_tv_exit_login);
        this.f16896g = (TextView) findViewById(C1990R.C1992id.nrzs_ft_tv_reply_login);
        this.f16897h = (TextView) findViewById(C1990R.C1992id.tv_ft_login_code);
        m12187a(this.f16893d);
    }

    /* renamed from: a */
    public void m12187a(int i) {
        this.f16893d = i;
        TextView textView = this.f16897h;
        if (textView != null) {
            textView.setText("ErrorCode:" + i);
        }
    }

    @Override // com.nrzs.ft.ui.base.FtBaseDialog
    /* renamed from: c */
    protected void mo3012c() {
        this.f16895f.setOnClickListener(new View.OnClickListener() { // from class: z1.anx.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FtLoginKickDialog.this.dismiss();
                IntentToAssistService.m12813a(FloatApp.m18899b().m18901a());
            }
        });
        this.f16896g.setOnClickListener(new View.OnClickListener() { // from class: z1.anx.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FtLoginKickDialog.this.f16896g.setEnabled(false);
                FtLoginKickDialog.this.m12185e();
            }
        });
    }

    @Override // com.nrzs.ft.ui.base.FtBaseDialog
    /* renamed from: d */
    protected void mo3011d() {
        FloatViewManager.m12346a(FloatApp.m18899b().m18901a()).m12327e();
        this.f16898i = new UserRepository();
    }

    /* renamed from: e */
    public void m12185e() {
        String b = apf.m11837b(FloatApp.m18899b().m18901a(), ShareVal.f16591a, ShareVal.f16597g, "");
        LogUtils.m23734c("token111", "悬浮窗自动登录前：token:" + b);
        Log.e("token", "flog自动登录成功 " + b);
        if (!TextUtils.isEmpty(b)) {
            try {
                AutoLogin autoLogin = new AutoLogin();
                autoLogin.token = b;
                this.f16898i.m12544a(autoLogin, this.f16900k, this.f16899j);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
