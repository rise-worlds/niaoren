package p110z1;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.blankj.utilcode.util.Utils;
import com.nrzs.base.router.RouterUtils;
import com.nrzs.data.base.BaseResponse;
import com.nrzs.data.user.bean.request.AutoLogin;
import com.nrzs.data.user.bean.respond.LoginResultV1Info;
import com.nrzs.http.ThreadCallback;
import com.nrzs.http.UICallback;

/* renamed from: z1.apz */
/* loaded from: classes3.dex */
public class Autologinmanager {

    /* renamed from: a */
    private static Autologinmanager f17201a;

    /* renamed from: b */
    private Context f17202b;

    /* renamed from: c */
    private UserRepository f17203c;

    /* renamed from: d */
    private ThreadCallback<BaseResponse<LoginResultV1Info>, String> f17204d = new ThreadCallback<BaseResponse<LoginResultV1Info>, String>() { // from class: z1.apz.1
        /* renamed from: a */
        public BaseResponse<LoginResultV1Info> onResponse(String str) {
            BaseResponse<LoginResultV1Info> baseResponse = (BaseResponse) apa.m11877a(str, new TypeToken<BaseResponse<LoginResultV1Info>>() { // from class: z1.apz.1.1
            });
            if (baseResponse != null) {
                return baseResponse;
            }
            return null;
        }
    };

    /* renamed from: e */
    private UICallback<BaseResponse<LoginResultV1Info>> f17205e = new UICallback<BaseResponse<LoginResultV1Info>>() { // from class: z1.apz.2
        @Override // com.nrzs.http.UICallback
        /* renamed from: a */
        public void mo3021a(Throwable th) {
            aqg.m11586a(Autologinmanager.this.f17202b, "自动登录失败");
        }

        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo3022a(BaseResponse<LoginResultV1Info> baseResponse) {
            if (baseResponse == null) {
                return;
            }
            if (baseResponse.code == 0) {
                if ("登录已过期".equals(baseResponse.msg)) {
                    LoginManager.m12620d().m12617g();
                    aqg.m11586a(Autologinmanager.this.f17202b, "登录信息失效，请重新登录");
                    RouterUtils.toLogin(1, 0);
                    return;
                }
                LoginManager.m12620d().m12617g();
                RouterUtils.toLogin(1, 0);
                aqg.m11586a(Autologinmanager.this.f17202b, baseResponse.msg);
            } else if (baseResponse.code != -1) {
                LoginManager.m12620d().m12625a(baseResponse.data.AutoLoginToken, baseResponse.data.UserInfo, baseResponse.data.AscriptionAuthorId, baseResponse.data.UploadLocalAppPackage);
            } else if (baseResponse.data != null) {
                LoginManager.m12620d().m12626a(baseResponse.data.AutoLoginToken, baseResponse.data.UserId);
            }
        }
    };

    private Autologinmanager(Context context) {
        this.f17202b = context;
    }

    /* renamed from: a */
    public static Autologinmanager m11654a(Context context) {
        if (f17201a == null) {
            synchronized (Autologinmanager.class) {
                if (f17201a == null) {
                    f17201a = new Autologinmanager(context);
                }
            }
        }
        return f17201a;
    }

    /* renamed from: a */
    public void m11655a() {
        String b = apf.m11837b(Utils.m24103a(), ShareVal.f16591a, ShareVal.f16597g, "");
        Log.e("token", "自动登录成功 " + b);
        if (!TextUtils.isEmpty(b)) {
            try {
                this.f17203c = new UserRepository();
                AutoLogin autoLogin = new AutoLogin();
                autoLogin.token = b;
                this.f17203c.m12544a(autoLogin, this.f17205e, this.f17204d);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
