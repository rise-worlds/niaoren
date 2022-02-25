package p110z1;

import com.nrzs.data.DataApp;
import com.nrzs.data.user.bean.UserInfo;

/* renamed from: z1.akh */
/* loaded from: classes3.dex */
public class FtUserManager {

    /* renamed from: b */
    private static final Object f16173b = new Object();

    /* renamed from: c */
    private static FtUserManager f16174c;

    /* renamed from: a */
    private UserInfo f16175a;

    /* renamed from: h */
    private UserInfo m12789h() {
        if (this.f16175a == null) {
            this.f16175a = (UserInfo) apa.m11877a(apf.m11837b(DataApp.m18939d().m18947a(), ShareVal.f16591a, ShareVal.f16592b, ""), new TypeToken<UserInfo>() { // from class: z1.akh.1
            });
        }
        return this.f16175a;
    }

    /* renamed from: a */
    public long m12797a() {
        if (m12791f()) {
            return m12789h().UserID;
        }
        return -1L;
    }

    /* renamed from: b */
    public int m12795b() {
        if (m12791f()) {
            return m12789h().IsVip;
        }
        return 0;
    }

    /* renamed from: c */
    public String m12794c() {
        return m12791f() ? this.f16175a.UserSessionId : "";
    }

    /* renamed from: d */
    public String m12793d() {
        return m12791f() ? this.f16175a.ToolSecret : "";
    }

    /* renamed from: e */
    public float m12792e() {
        if (m12791f()) {
            return this.f16175a.GoldCoinNum;
        }
        return 0.0f;
    }

    /* renamed from: a */
    public void m12796a(UserInfo userInfo) {
        this.f16175a = userInfo;
    }

    /* renamed from: f */
    public boolean m12791f() {
        return (m12789h() == null || m12789h().UserID == -1) ? false : true;
    }

    /* renamed from: g */
    public static FtUserManager m12790g() {
        FtUserManager akhVar;
        synchronized (f16173b) {
            if (f16174c == null) {
                f16174c = new FtUserManager();
            }
            akhVar = f16174c;
        }
        return akhVar;
    }
}
