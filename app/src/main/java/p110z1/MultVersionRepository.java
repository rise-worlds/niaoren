package p110z1;

import com.nrzs.data.base.BaseResponse;
import com.nrzs.data.p066ft.bean.MultVersion;
import com.nrzs.data.p066ft.bean.request.WxsoRequestInfo;
import com.nrzs.http.Api;
import com.nrzs.http.BaseRepository;
import com.nrzs.http.ThreadCallback;
import com.nrzs.http.UICallback;
import java.util.List;

/* renamed from: z1.akl */
/* loaded from: classes3.dex */
public class MultVersionRepository {

    /* renamed from: a */
    private BaseRepository<MultVersion> f16191a;

    /* renamed from: b */
    private ThreadCallback f16192b = new ThreadCallback<MultVersion, String>() { // from class: z1.akl.1
        /* renamed from: a */
        public MultVersion onResponse(String str) {
            BaseResponse baseResponse = (BaseResponse) apa.m11877a(str, new TypeToken<BaseResponse<MultVersion>>() { // from class: z1.akl.1.1
            });
            if (baseResponse == null || baseResponse.data == 0) {
                return null;
            }
            return (MultVersion) baseResponse.data;
        }
    };

    /* renamed from: c */
    private ThreadCallback f16193c = new ThreadCallback<List<MultVersion>, String>() { // from class: z1.akl.2
        /* renamed from: a */
        public List<MultVersion> onResponse(String str) {
            BaseResponse baseResponse = (BaseResponse) apa.m11877a(str, new TypeToken<BaseResponse<List<MultVersion>>>() { // from class: z1.akl.2.1
            });
            if (baseResponse == null || baseResponse.data == 0) {
                return null;
            }
            return (List) baseResponse.data;
        }
    };

    /* renamed from: a */
    public void m12772a(String str, UICallback<MultVersion> oVar) {
        try {
            if (this.f16191a == null) {
                this.f16191a = new BaseRepository<>();
            }
            WxsoRequestInfo wxsoRequestInfo = new WxsoRequestInfo();
            wxsoRequestInfo.Version = str;
            this.f16191a.m18568a(Api.m18586a(wxsoRequestInfo.toGetUrl(HttpVal.f16495K))).m18572a(this.f16192b).m18571a(oVar).m18574a(3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public void m12773a(UICallback<List<MultVersion>> oVar) {
        try {
            if (this.f16191a == null) {
                this.f16191a = new BaseRepository<>();
            }
            WxsoRequestInfo wxsoRequestInfo = new WxsoRequestInfo();
            wxsoRequestInfo.BitCfg = NRZSLocalConfig.m12513c() ? 0 : 1;
            this.f16191a.m18568a(Api.m18586a(wxsoRequestInfo.toGetUrl(HttpVal.f16496L))).m18572a(this.f16193c).m18571a(oVar).m18574a(3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public void m12774a() {
        if (this.f16191a != null) {
            this.f16191a = null;
        }
    }
}
