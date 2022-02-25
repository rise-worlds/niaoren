package p110z1;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.nrzs.base.router.RouterUtils;
import com.nrzs.data.DataApp;
import com.nrzs.data.ddy.bean.respond.OrderDaileInfo;
import com.nrzs.data.user.bean.UserInfo;
import java.text.SimpleDateFormat;
import java.util.List;
import p110z1.FlaushDdyEvent;
import p110z1.FragmentEvent;
import p110z1.LoginEvent;
import p110z1.UserInfoEvent;

/* renamed from: z1.ald */
/* loaded from: classes3.dex */
public class LoginManager {

    /* renamed from: a */
    private UserInfo f16319a;

    /* renamed from: b */
    private String f16320b;

    /* renamed from: c */
    private long f16321c;

    /* renamed from: d */
    private volatile boolean f16322d;

    private LoginManager() {
        this.f16319a = null;
        this.f16320b = "";
        this.f16322d = false;
    }

    /* renamed from: a */
    public UserInfo m12630a() {
        return this.f16319a;
    }

    /* renamed from: b */
    public long m12623b() {
        return this.f16321c;
    }

    /* renamed from: a */
    public void m12629a(long j) {
        this.f16321c = j;
    }

    /* renamed from: a */
    public void m12628a(UserInfo userInfo) {
        this.f16319a = userInfo;
    }

    /* renamed from: c */
    public String m12621c() {
        return this.f16320b;
    }

    /* renamed from: a */
    public void m12627a(String str) {
        this.f16320b = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: LoginManager.java */
    /* renamed from: z1.ald$a */
    /* loaded from: classes3.dex */
    public static class C3644a {

        /* renamed from: a */
        private static final LoginManager f16323a = new LoginManager();

        private C3644a() {
        }
    }

    /* renamed from: d */
    public static LoginManager m12620d() {
        return C3644a.f16323a;
    }

    /* renamed from: a */
    public void m12624a(boolean z) {
        this.f16322d = z;
    }

    /* renamed from: e */
    public boolean m12619e() {
        return this.f16322d;
    }

    /* renamed from: a */
    public void m12625a(String str, UserInfo userInfo, long j, String str2) {
        apf.m11842a(DataApp.m18939d().m18947a(), ShareVal.f16591a, ShareVal.f16597g, str);
        LogUtils.m23734c("token111", "登录成功：name:" + userInfo.UserName + ",token:" + str);
        Log.e("token", "登录成功 账号---" + userInfo.UserName + "---token__-" + SPUtils.m23341a().m23320b(ShareVal.f16597g, ""));
        this.f16319a = userInfo;
        this.f16321c = j;
        this.f16320b = str2;
        if (str2 != null) {
            Log.e("UploadLocalAppPackage", str2);
        } else {
            Log.e("UploadLocalAppPackage", "空包名");
        }
        apf.m11842a(DataApp.m18939d().m18947a(), ShareVal.f16591a, ShareVal.f16592b, new Gson().m1575b(userInfo));
        EventBus.m3448a().m3427d(new LoginEvent.C3560a(1));
        EventBus.m3448a().m3427d(new FlaushDdyEvent.C3554a());
        if (j > 0 || DataApp.m18939d().f10604g > 0) {
            EventBus.m3448a().m3427d(new FragmentEvent.C3555a(1));
        }
        EventBus.m3448a().m3427d(new UserInfoEvent.C3561a());
        Context a = DataApp.m18939d().m18947a();
        UserInfo userInfo2 = this.f16319a;
        IntentToAssistService.m12805a(a, userInfo2, userInfo2.UserSessionId, this.f16319a.UserID, this.f16319a.ToolSecret);
    }

    /* renamed from: b */
    public void m12622b(String str) {
        apf.m11842a(DataApp.m18939d().m18947a(), ShareVal.f16591a, ShareVal.f16597g, str);
    }

    /* renamed from: a */
    public void m12626a(String str, long j) {
        m12622b(str);
        if (this.f16322d) {
            RouterUtils.toKickOut(j, true);
        }
    }

    /* renamed from: f */
    public String m12618f() {
        return m12606r() ? apa.m11879a(this.f16319a) : "";
    }

    /* renamed from: g */
    public void m12617g() {
        this.f16319a = null;
        Log.e("MyComponentDelegate", "loginout");
        IntentToAssistService.m12812a(DataApp.m18939d().m18947a(), 9);
        apf.m11842a(DataApp.m18939d().m18947a(), ShareVal.f16591a, ShareVal.f16597g, "");
        Log.e("token", "退出登录" + SPUtils.m23341a().m23320b(ShareVal.f16597g, ""));
        apf.m11842a(DataApp.m18939d().m18947a(), ShareVal.f16591a, ShareVal.f16592b, "");
        EventBus.m3448a().m3427d(new LoginEvent.C3560a(3));
        EventBus.m3448a().m3427d(new FragmentEvent.C3555a(2));
    }

    /* renamed from: h */
    public String m12616h() {
        return m12606r() ? this.f16319a.UserSessionId : "";
    }

    /* renamed from: i */
    public String m12615i() {
        return m12606r() ? this.f16319a.UserName : "";
    }

    /* renamed from: j */
    public long m12614j() {
        if (m12606r()) {
            return this.f16319a.UserID;
        }
        return -1L;
    }

    /* renamed from: k */
    public int m12613k() {
        if (m12606r()) {
            return this.f16319a.IsVip;
        }
        return 0;
    }

    /* renamed from: l */
    public String m12612l() {
        return m12606r() ? this.f16319a.ToolSecret : "";
    }

    /* renamed from: m */
    public boolean m12611m() {
        if (!m12606r()) {
            return false;
        }
        String str = this.f16319a.GoldExpireTime;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        long a = TimeUtils.m23105a(TimeUtils.m23094b(str, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")), TimeUtils.m23087c(), (int) TimeConstants.f21692e);
        return a <= 3 && a >= 0;
    }

    /* renamed from: n */
    public boolean m12610n() {
        if (m12606r()) {
            return this.f16319a.IsExpire;
        }
        return false;
    }

    /* renamed from: o */
    public String m12609o() {
        return m12606r() ? this.f16319a.NRVipExpireTime : "";
    }

    /* renamed from: p */
    public List<OrderDaileInfo> m12608p() {
        if (m12606r()) {
            return this.f16319a.DDY_OrderInfos;
        }
        return null;
    }

    /* renamed from: q */
    public float m12607q() {
        if (m12606r()) {
            return this.f16319a.GoldCoinNum;
        }
        return 0.0f;
    }

    /* renamed from: r */
    public boolean m12606r() {
        if (this.f16319a == null) {
            String b = apf.m11837b(DataApp.m18939d().m18947a(), ShareVal.f16591a, ShareVal.f16592b, "");
            if (TextUtils.isEmpty(b)) {
                return false;
            }
            this.f16319a = (UserInfo) apa.m11876b(b, UserInfo.class);
            if (this.f16319a == null) {
                return false;
            }
        }
        return this.f16319a.UserID != -1;
    }

    /* renamed from: s */
    public void m12605s() {
        m12617g();
    }
}
