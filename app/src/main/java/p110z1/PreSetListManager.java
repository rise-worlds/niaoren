package p110z1;

import com.cyjh.ddy.media.p035a.ResultTypeConstant;
import com.nrzs.data.DataApp;
import com.nrzs.data.other.bean.response.PreSetListResponse;

/* renamed from: z1.aoi */
/* loaded from: classes3.dex */
public class PreSetListManager {

    /* renamed from: a */
    private static PreSetListManager f16956a;

    /* renamed from: b */
    private PreSetListResponse f16957b;

    /* renamed from: a */
    public static PreSetListManager m12116a() {
        if (f16956a == null) {
            synchronized (PreSetListManager.class) {
                if (f16956a == null) {
                    f16956a = new PreSetListManager();
                }
            }
        }
        return f16956a;
    }

    /* renamed from: b */
    public PreSetListResponse m12114b() {
        return this.f16957b;
    }

    /* renamed from: a */
    public void m12115a(PreSetListResponse preSetListResponse) {
        this.f16957b = preSetListResponse;
        PreSetListResponse preSetListResponse2 = this.f16957b;
        if (preSetListResponse2 != null && preSetListResponse2.getRdata() != null) {
            apf.m11841a(DataApp.m18939d().m18947a(), ShareVal.f16591a, ShareVal.f16614x, this.f16957b.getRdata().isCardPayIfOpen());
            apf.m11842a(DataApp.m18939d().m18947a(), ShareVal.f16591a, ShareVal.f16616z, this.f16957b.getRdata().getCardPayLink());
            apf.m11842a(DataApp.m18939d().m18947a(), ShareVal.f16591a, ShareVal.f16581L, this.f16957b.getRdata().getImageOcrTextVersion());
            apf.m11842a(DataApp.m18939d().m18947a(), ShareVal.f16591a, ShareVal.f16582M, this.f16957b.getRdata().getImageOcrTextSize());
            apf.m11842a(DataApp.m18939d().m18947a(), ShareVal.f16591a, ShareVal.f16583N, this.f16957b.getRdata().getFaqurl());
            apf.m11842a(DataApp.m18939d().m18947a(), ShareVal.f16591a, ShareVal.f16584O, this.f16957b.getRdata().getShieldTopicIds());
        }
    }

    /* renamed from: c */
    public String m12113c() {
        PreSetListResponse preSetListResponse = this.f16957b;
        if (preSetListResponse == null || preSetListResponse.getRdata() == null || this.f16957b.getRdata().getBubbleModel() == null) {
            return null;
        }
        return this.f16957b.getRdata().getBubbleModel().getSearchkey();
    }

    /* renamed from: d */
    public String m12112d() {
        PreSetListResponse preSetListResponse = this.f16957b;
        if (preSetListResponse == null || preSetListResponse.getRdata() == null || this.f16957b.getRdata().getVaCourseUrl() == null) {
            return null;
        }
        return this.f16957b.getRdata().getVaCourseUrl();
    }

    /* renamed from: e */
    public String m12111e() {
        PreSetListResponse preSetListResponse = this.f16957b;
        if (preSetListResponse == null || preSetListResponse.getRdata() == null) {
            return null;
        }
        return this.f16957b.getRdata().getFaqurl();
    }

    /* renamed from: f */
    public boolean m12110f() {
        PreSetListResponse preSetListResponse = this.f16957b;
        if (preSetListResponse == null || preSetListResponse.getRdata() == null) {
            return false;
        }
        return this.f16957b.getRdata().isCardPayIfOpen();
    }

    /* renamed from: g */
    public String m12109g() {
        PreSetListResponse preSetListResponse = this.f16957b;
        return (preSetListResponse == null || preSetListResponse.getRdata() == null) ? "" : this.f16957b.getRdata().getCardPayLink();
    }

    /* renamed from: h */
    public String m12108h() {
        PreSetListResponse preSetListResponse = this.f16957b;
        return (preSetListResponse == null || preSetListResponse.getRdata() == null) ? "" : this.f16957b.getRdata().getDDYCourseUrl();
    }

    /* renamed from: i */
    public String m12107i() {
        PreSetListResponse preSetListResponse = this.f16957b;
        return (preSetListResponse == null || preSetListResponse.getRdata() == null) ? "" : this.f16957b.getRdata().getDDYNavShieldingChannels();
    }

    /* renamed from: j */
    public String m12106j() {
        PreSetListResponse preSetListResponse = this.f16957b;
        return (preSetListResponse == null || preSetListResponse.getRdata() == null) ? "" : this.f16957b.getRdata().getShieldTopicIds();
    }

    /* renamed from: k */
    public String m12105k() {
        PreSetListResponse preSetListResponse = this.f16957b;
        return (preSetListResponse == null || preSetListResponse.getRdata() == null) ? "" : this.f16957b.getRdata().getBackgroundHangNavShieldingChannels();
    }

    /* renamed from: l */
    public String m12104l() {
        PreSetListResponse preSetListResponse = this.f16957b;
        return (preSetListResponse == null || preSetListResponse.getRdata() == null) ? "" : this.f16957b.getRdata().getWechatSettingCourseURL();
    }

    /* renamed from: m */
    public String m12103m() {
        PreSetListResponse preSetListResponse = this.f16957b;
        return (preSetListResponse == null || preSetListResponse.getRdata() == null || this.f16957b.getRdata().getRedPackEnter_IsOpe() == null) ? ResultTypeConstant.f7213z : this.f16957b.getRdata().getRedPackEnter_IsOpe();
    }

    /* renamed from: n */
    public String m12102n() {
        PreSetListResponse preSetListResponse = this.f16957b;
        return (preSetListResponse == null || preSetListResponse.getRdata() == null) ? "" : this.f16957b.getRdata().getRedPackEnter_Title() == null ? "2021抢红包神器" : this.f16957b.getRdata().getRedPackEnter_Title();
    }

    /* renamed from: o */
    public String m12101o() {
        PreSetListResponse preSetListResponse = this.f16957b;
        return (preSetListResponse == null || preSetListResponse.getRdata() == null || this.f16957b.getRdata().getImageOcrTextUrl() == null) ? "" : this.f16957b.getRdata().getImageOcrTextUrl();
    }

    /* renamed from: p */
    public String m12100p() {
        PreSetListResponse preSetListResponse = this.f16957b;
        return (preSetListResponse == null || preSetListResponse.getRdata() == null || this.f16957b.getRdata().getImageOcrTextVersion() == null) ? "" : this.f16957b.getRdata().getImageOcrTextVersion();
    }

    /* renamed from: q */
    public String m12099q() {
        PreSetListResponse preSetListResponse = this.f16957b;
        return (preSetListResponse == null || preSetListResponse.getRdata() == null || this.f16957b.getRdata().getImageOcrTextSize() == null) ? "" : this.f16957b.getRdata().getImageOcrTextSize();
    }

    /* renamed from: r */
    public String m12098r() {
        PreSetListResponse preSetListResponse = this.f16957b;
        return (preSetListResponse == null || preSetListResponse.getRdata() == null) ? "" : this.f16957b.getRdata().getRedPackEnter_SecTitle() == null ? "支持微信后台抢红包" : this.f16957b.getRdata().getRedPackEnter_SecTitle();
    }

    /* renamed from: s */
    public String m12097s() {
        PreSetListResponse preSetListResponse = this.f16957b;
        return (preSetListResponse == null || preSetListResponse.getRdata() == null || this.f16957b.getRdata().getRedPackEnter_Logo() == null) ? "" : this.f16957b.getRdata().getRedPackEnter_Logo();
    }

    /* renamed from: t */
    public String m12096t() {
        PreSetListResponse preSetListResponse = this.f16957b;
        return (preSetListResponse == null || preSetListResponse.getRdata() == null) ? "" : this.f16957b.getRdata().get_64BitNRDownUrl();
    }

    /* renamed from: u */
    public String m12095u() {
        PreSetListResponse preSetListResponse = this.f16957b;
        return (preSetListResponse == null || preSetListResponse.getRdata() == null) ? "" : this.f16957b.getRdata().get_32BitNRDownUrl();
    }
}
