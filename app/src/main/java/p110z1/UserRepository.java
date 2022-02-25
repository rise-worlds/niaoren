package p110z1;

import android.util.Log;
import com.nrzs.data.DataApp;
import com.nrzs.data.base.BaseResponse;
import com.nrzs.data.user.bean.UserInfo;
import com.nrzs.data.user.bean.request.AccoutnReginfo;
import com.nrzs.data.user.bean.request.AlterPwinfm;
import com.nrzs.data.user.bean.request.AlterPwinfmZh;
import com.nrzs.data.user.bean.request.AutoLogin;
import com.nrzs.data.user.bean.request.LoginOutReg;
import com.nrzs.data.user.bean.request.LoginRInfo;
import com.nrzs.data.user.bean.request.RegSmsinfo;
import com.nrzs.data.user.bean.request.Reginfo;
import com.nrzs.data.user.bean.request.UserRInfo;
import com.nrzs.data.user.bean.respond.LoginResultV1Info;
import com.nrzs.http.Api;
import com.nrzs.http.BaseRepository;
import com.nrzs.http.ThreadCallback;
import com.nrzs.http.UICallback;

/* renamed from: z1.alt */
/* loaded from: classes3.dex */
public class UserRepository {

    /* renamed from: a */
    private BaseRepository<BaseResponse<LoginResultV1Info>> f16389a;

    /* renamed from: b */
    private BaseRepository<BaseResponse<LoginResultV1Info>> f16390b;

    /* renamed from: c */
    private BaseRepository<BaseResponse<UserInfo>> f16391c;

    /* renamed from: d */
    private BaseRepository<BaseResponse<Object>> f16392d;

    /* renamed from: e */
    private BaseRepository<BaseResponse<LoginResultV1Info>> f16393e;

    /* renamed from: f */
    private BaseRepository<BaseResponse<Object>> f16394f;

    /* renamed from: g */
    private BaseRepository<Object> f16395g;

    /* renamed from: h */
    private BaseRepository<BaseResponse<LoginResultV1Info>> f16396h;

    /* renamed from: a */
    public void m12542a(LoginRInfo loginRInfo, UICallback oVar, ThreadCallback nVar) throws Exception {
        if (this.f16389a == null) {
            this.f16389a = new BaseRepository<>();
        }
        String getUrl = loginRInfo.toGetUrl(HttpVal.f16517d);
        Log.e("url", getUrl);
        this.f16389a.m18568a(Api.m18586a(getUrl)).m18572a(nVar).m18571a(oVar).m18574a(18);
    }

    /* renamed from: a */
    public void m12539a(UserRInfo userRInfo, UICallback oVar, ThreadCallback nVar) throws Exception {
        if (this.f16391c == null) {
            this.f16391c = new BaseRepository<>();
        }
        String getUrl = userRInfo.toGetUrl(apf.m11837b(DataApp.m18939d().m18947a(), ShareVal.f16591a, ShareVal.f16589T, "45.158.183.252"));
        Log.e("url", getUrl);
        this.f16391c.m18568a(Api.m18586a(getUrl)).m18572a(nVar).m18571a(oVar).m18574a(19);
    }

    /* renamed from: a */
    public void m12540a(Reginfo reginfo, UICallback oVar, ThreadCallback nVar) throws Exception {
        if (this.f16390b == null) {
            this.f16390b = new BaseRepository<>();
        }
        this.f16390b.m18568a(Api.m18586a(reginfo.toGetUrl(HttpVal.f16519f))).m18572a(nVar).m18571a(oVar).m18574a(20);
    }

    /* renamed from: a */
    public void m12547a(AccoutnReginfo accoutnReginfo, UICallback oVar, ThreadCallback nVar) throws Exception {
        if (this.f16393e == null) {
            this.f16393e = new BaseRepository<>();
        }
        this.f16393e.m18568a(Api.m18586a(accoutnReginfo.toGetUrl(HttpVal.f16522i))).m18572a(nVar).m18571a(oVar).m18574a(21);
    }

    /* renamed from: a */
    public void m12541a(RegSmsinfo regSmsinfo, UICallback oVar, ThreadCallback nVar) throws Exception {
        if (this.f16392d == null) {
            this.f16392d = new BaseRepository<>();
        }
        this.f16392d.m18568a(Api.m18586a(regSmsinfo.toGetUrl(HttpVal.f16520g))).m18572a(nVar).m18571a(oVar).m18574a(22);
    }

    /* renamed from: b */
    public void m12538b(RegSmsinfo regSmsinfo, UICallback oVar, ThreadCallback nVar) throws Exception {
        if (this.f16392d == null) {
            this.f16392d = new BaseRepository<>();
        }
        this.f16392d.m18568a(Api.m18586a(regSmsinfo.toGetUrl(HttpVal.f16521h))).m18572a(nVar).m18571a(oVar).m18574a(23);
    }

    /* renamed from: a */
    public void m12546a(AlterPwinfm alterPwinfm, UICallback oVar, ThreadCallback nVar) throws Exception {
        if (this.f16394f == null) {
            this.f16394f = new BaseRepository<>();
        }
        this.f16392d.m18568a(Api.m18586a(alterPwinfm.toGetUrl(HttpVal.f16523j))).m18572a(nVar).m18571a(oVar).m18574a(24);
    }

    /* renamed from: a */
    public void m12545a(AlterPwinfmZh alterPwinfmZh, UICallback oVar, ThreadCallback nVar) throws Exception {
        if (this.f16394f == null) {
            this.f16394f = new BaseRepository<>();
        }
        this.f16394f.m18568a(Api.m18586a(alterPwinfmZh.toGetUrl(HttpVal.f16524k))).m18572a(nVar).m18571a(oVar).m18574a(25);
    }

    /* renamed from: a */
    public void m12543a(LoginOutReg loginOutReg, UICallback oVar, ThreadCallback nVar) throws Exception {
        if (this.f16395g == null) {
            this.f16395g = new BaseRepository<>();
        }
        this.f16395g.m18568a(Api.m18586a(loginOutReg.toGetUrl(HttpVal.f16525l))).m18572a(nVar).m18571a(oVar).m18574a(26);
    }

    /* renamed from: a */
    public void m12544a(AutoLogin autoLogin, UICallback oVar, ThreadCallback nVar) throws Exception {
        if (this.f16396h == null) {
            this.f16396h = new BaseRepository<>();
        }
        this.f16396h.m18568a(Api.m18586a(autoLogin.toGetUrl(HttpVal.f16526m))).m18572a(nVar).m18571a(oVar).m18574a(27);
    }
}
