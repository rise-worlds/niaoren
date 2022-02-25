package p110z1;

import android.text.TextUtils;
import android.util.Log;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.nrzs.data.DataApp;
import com.nrzs.data.base.BaseResponse;
import com.nrzs.data.p066ft.bean.request.RemarkRequestInfo;
import com.nrzs.data.p066ft.bean.request.SessionRequestInfo;
import com.nrzs.data.p066ft.bean.response.SessionInfoResponse;
import com.nrzs.data.user.bean.request.AutoLogin;
import com.nrzs.data.user.bean.request.LoginOutReg;
import com.nrzs.data.user.bean.respond.LoginResultV1Info;
import com.nrzs.http.ThreadCallback;
import com.nrzs.http.UICallback;

/* renamed from: z1.als */
/* loaded from: classes3.dex */
public class UserKickModel {

    /* renamed from: c */
    private IUserKickCallback f16373c;

    /* renamed from: d */
    private UICallback<BaseResponse<LoginResultV1Info>> f16374d = new UICallback<BaseResponse<LoginResultV1Info>>() { // from class: z1.als.1
        @Override // com.nrzs.http.UICallback
        /* renamed from: a */
        public void mo3021a(Throwable th) {
            ToastUtils.m23030a("登录失败");
        }

        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo3022a(BaseResponse<LoginResultV1Info> baseResponse) {
            if (baseResponse != null && UserKickModel.this.f16373c != null) {
                UserKickModel.this.f16373c.mo12178a(baseResponse);
            }
        }
    };

    /* renamed from: e */
    private UICallback f16375e = new UICallback<SessionInfoResponse>() { // from class: z1.als.2
        @Override // com.nrzs.http.UICallback
        /* renamed from: a */
        public void mo3021a(Throwable th) {
        }

        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo3022a(SessionInfoResponse sessionInfoResponse) {
            if (sessionInfoResponse != null && UserKickModel.this.f16373c != null) {
                UserKickModel.this.f16373c.mo12177a(sessionInfoResponse);
            }
        }
    };

    /* renamed from: a */
    private SessionRepository f16371a = new SessionRepository();

    /* renamed from: b */
    private UserRepository f16372b = new UserRepository();

    public UserKickModel(IUserKickCallback alrVar) {
        this.f16373c = alrVar;
    }

    /* renamed from: a */
    public void m12556a(SessionRequestInfo sessionRequestInfo) {
        try {
            this.f16371a.m12767a(sessionRequestInfo, this.f16375e);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public void m12555a(String str, long j, final String str2, final int i) {
        RemarkRequestInfo remarkRequestInfo = new RemarkRequestInfo();
        remarkRequestInfo.DeviceNote = str2;
        remarkRequestInfo.updateDeviceCode = str;
        remarkRequestInfo.UserID = j;
        this.f16371a.m12768a(remarkRequestInfo, new UICallback<BaseResponse>() { // from class: z1.als.3
            @Override // com.nrzs.http.UICallback
            /* renamed from: a */
            public void mo3021a(Throwable th) {
            }

            /* renamed from: a  reason: avoid collision after fix types in other method */
            public void mo3022a(BaseResponse baseResponse) {
                if (UserKickModel.this.f16373c != null) {
                    UserKickModel.this.f16373c.mo12181a(i, str2);
                }
            }
        });
    }

    /* renamed from: a */
    public void m12557a(long j, String str, final int i, final boolean z) {
        final LoginOutReg loginOutReg = new LoginOutReg();
        loginOutReg.UserId = j;
        loginOutReg.SessionId = str;
        try {
            this.f16372b.m12543a(loginOutReg, new UICallback<BaseResponse>() { // from class: z1.als.4
                @Override // com.nrzs.http.UICallback
                /* renamed from: a */
                public void mo3021a(Throwable th) {
                    Log.i("NetEngin", "test2 onError:" + th.getMessage() + "," + Thread.currentThread().getName());
                }

                /* renamed from: a  reason: avoid collision after fix types in other method */
                public void mo3022a(BaseResponse baseResponse) {
                    if (UserKickModel.this.f16373c == null) {
                        return;
                    }
                    if (z) {
                        UserKickModel.this.f16373c.mo12180a(i, true);
                    } else {
                        UserKickModel.this.f16373c.mo12180a(i, LoginManager.m12620d().m12616h().equals(loginOutReg.SessionId));
                    }
                }
            }, new ThreadCallback<BaseResponse, String>() { // from class: z1.als.5
                /* renamed from: a */
                public BaseResponse onResponse(String str2) {
                    BaseResponse baseResponse = (BaseResponse) apa.m11877a(str2, new TypeToken<BaseResponse<Object>>() { // from class: z1.als.5.1
                    });
                    if (baseResponse != null) {
                        return baseResponse;
                    }
                    return null;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public void m12558a() {
        String b = apf.m11837b(DataApp.m18939d().m18947a(), ShareVal.f16591a, ShareVal.f16597g, "");
        LogUtils.m23734c("token111", "悬浮窗自动登录前：token:" + b);
        Log.e("token", "flog自动登录成功 " + b);
        if (!TextUtils.isEmpty(b)) {
            try {
                AutoLogin autoLogin = new AutoLogin();
                autoLogin.token = b;
                this.f16372b.m12544a(autoLogin, this.f16374d, new ThreadCallback() { // from class: z1.als.6
                    @Override // com.nrzs.http.ThreadCallback
                    public Object onResponse(String str) {
                        LogUtils.m23757a("FtUserKickDialog", (Object) ("autoLogin onResponse:" + str + "," + Thread.currentThread().getName()));
                        BaseResponse baseResponse = (BaseResponse) apa.m11877a(str, new TypeToken<BaseResponse<LoginResultV1Info>>() { // from class: z1.als.6.1
                        });
                        if (baseResponse != null) {
                            return baseResponse;
                        }
                        return null;
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: b */
    public void m12553b() {
        this.f16373c = null;
        this.f16371a = null;
        this.f16372b = null;
    }
}
