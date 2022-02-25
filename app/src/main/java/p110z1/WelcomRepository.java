package p110z1;

import android.util.Log;
import com.nrzs.data.base.BaseRequest;
import com.nrzs.data.base.BaseResponse;
import com.nrzs.data.other.bean.request.GetWelcomeV6RequestInfo;
import com.nrzs.data.other.bean.response.GetWelcomeV6ResponseInfo;
import com.nrzs.data.other.bean.response.PreSetListResponse;
import com.nrzs.http.Api;
import com.nrzs.http.BaseRepository;
import com.nrzs.http.ThreadCallback;
import com.nrzs.http.UICallback;
import java.util.HashMap;

/* renamed from: z1.alm */
/* loaded from: classes3.dex */
public class WelcomRepository {

    /* renamed from: a */
    private BaseRepository<GetWelcomeV6ResponseInfo> f16360a;

    /* renamed from: b */
    private ThreadCallback<BaseResponse<PreSetListResponse>, String> f16361b = new ThreadCallback<BaseResponse<PreSetListResponse>, String>() { // from class: z1.alm.1
        /* renamed from: a */
        public BaseResponse<PreSetListResponse> onResponse(String str) {
            BaseResponse<PreSetListResponse> baseResponse = (BaseResponse) apa.m11877a(str, new TypeToken<BaseResponse<PreSetListResponse>>() { // from class: z1.alm.1.1
            });
            if (baseResponse == null || !(baseResponse.data instanceof PreSetListResponse)) {
                return null;
            }
            return baseResponse;
        }
    };

    /* renamed from: a */
    public void m12577a(GetWelcomeV6RequestInfo getWelcomeV6RequestInfo, UICallback oVar, ThreadCallback nVar) {
        try {
            if (this.f16360a == null) {
                this.f16360a = new BaseRepository<>();
            }
            String getUrl = getWelcomeV6RequestInfo.toGetUrl(HttpVal.f16528o);
            Log.e("随机数", getUrl);
            this.f16360a.m18568a(Api.m18586a(getUrl)).m18572a(nVar).m18571a(oVar).m18574a(30);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public void m12578a(BaseRequest baseRequest, UICallback oVar, ThreadCallback nVar) {
        try {
            if (this.f16360a == null) {
                this.f16360a = new BaseRepository<>();
            }
            String str = LocalHttp.f16540a;
            this.f16360a.m18568a(Api.m18584a(str, baseRequest.toPostUrl(str, baseRequest.getMapParams()), new HashMap())).m18572a(nVar).m18571a(oVar).m18574a(29);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public void m12576a(UICallback oVar) {
        try {
            new BaseRepository().m18568a(Api.m18586a(new BaseRequest().toGetUrl(HttpVal.f16537x))).m18572a(this.f16361b).m18571a(oVar).m18574a(31);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
