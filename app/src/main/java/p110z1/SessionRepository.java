package p110z1;

import com.nrzs.data.base.BaseResponse;
import com.nrzs.data.p066ft.bean.SessionInfo;
import com.nrzs.data.p066ft.bean.request.RemarkRequestInfo;
import com.nrzs.data.p066ft.bean.request.SessionRequestInfo;
import com.nrzs.data.p066ft.bean.response.SessionInfoResponse;
import com.nrzs.http.Api;
import com.nrzs.http.BaseRepository;
import com.nrzs.http.ThreadCallback;
import com.nrzs.http.UICallback;
import java.util.List;

/* renamed from: z1.akm */
/* loaded from: classes3.dex */
public class SessionRepository {

    /* renamed from: a */
    private BaseRepository<SessionInfoResponse> f16198a;

    /* renamed from: b */
    private ThreadCallback f16199b = new ThreadCallback<SessionInfoResponse, String>() { // from class: z1.akm.1
        /* renamed from: a */
        public SessionInfoResponse onResponse(String str) {
            BaseResponse baseResponse = (BaseResponse) apa.m11877a(str, new TypeToken<BaseResponse<SessionInfoResponse>>() { // from class: z1.akm.1.1
            });
            if (baseResponse == null || baseResponse.data == 0) {
                return null;
            }
            return (SessionInfoResponse) baseResponse.data;
        }
    };

    /* renamed from: a */
    public void m12767a(SessionRequestInfo sessionRequestInfo, UICallback<List<SessionInfo>> oVar) {
        try {
            if (this.f16198a == null) {
                this.f16198a = new BaseRepository<>();
            }
            this.f16198a.m18568a(Api.m18586a(sessionRequestInfo.toGetUrl(HttpVal.f16493I))).m18572a(this.f16199b).m18571a(oVar).m18574a(3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public void m12768a(RemarkRequestInfo remarkRequestInfo, UICallback<BaseResponse> oVar) {
        try {
            if (this.f16198a == null) {
                this.f16198a = new BaseRepository<>();
            }
            this.f16198a.m18568a(Api.m18586a(remarkRequestInfo.toGetUrl(HttpVal.f16494J))).m18572a(new ThreadCallback() { // from class: z1.akm.2
                @Override // com.nrzs.http.ThreadCallback
                public Object onResponse(String str) {
                    BaseResponse baseResponse = (BaseResponse) apa.m11877a(str, new TypeToken<BaseResponse>() { // from class: z1.akm.2.1
                    });
                    if (baseResponse != null) {
                        return baseResponse;
                    }
                    return null;
                }
            }).m18571a(oVar).m18574a(3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public void m12769a() {
        if (this.f16198a != null) {
            this.f16198a = null;
        }
    }
}
