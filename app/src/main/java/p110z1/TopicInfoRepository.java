package p110z1;

import com.nrzs.data.base.BaseResponse;
import com.nrzs.data.game.bean.request.ScriptDetailRequestInfo;
import com.nrzs.data.game.bean.request.TopicListRequestInfo;
import com.nrzs.data.game.bean.response.ScriptInfoResponse;
import com.nrzs.data.game.bean.response.TopicDetailResponseInfo;
import com.nrzs.data.other.bean.request.GetTopicInfo;
import com.nrzs.http.Api;
import com.nrzs.http.BaseRepository;
import com.nrzs.http.ThreadCallback;
import com.nrzs.http.UICallback;

/* renamed from: z1.akv */
/* loaded from: classes3.dex */
public class TopicInfoRepository {

    /* renamed from: a */
    private ThreadCallback f16262a = new ThreadCallback<TopicDetailResponseInfo, String>() { // from class: z1.akv.1
        /* renamed from: a */
        public TopicDetailResponseInfo onResponse(String str) {
            BaseResponse baseResponse = (BaseResponse) apa.m11877a(str, new TypeToken<BaseResponse<TopicDetailResponseInfo>>() { // from class: z1.akv.1.1
            });
            int i = baseResponse.code;
            if (!"失败".equals(baseResponse.msg) && baseResponse != null && (baseResponse.data instanceof TopicDetailResponseInfo)) {
                return (TopicDetailResponseInfo) baseResponse.data;
            }
            return null;
        }
    };

    /* renamed from: a */
    public void m12706a(TopicListRequestInfo topicListRequestInfo, UICallback oVar) throws Exception {
        new BaseRepository().m18568a(Api.m18586a(topicListRequestInfo.toGetUrl(HttpVal.f16533t))).m18572a(this.f16262a).m18571a(oVar).m18574a(15);
    }

    /* renamed from: a */
    public void m12705a(GetTopicInfo getTopicInfo, UICallback oVar, ThreadCallback nVar) throws Exception {
        new BaseRepository().m18568a(Api.m18586a(getTopicInfo.toGetUrl(HttpVal.f16512aa))).m18572a(nVar).m18571a(oVar).m18574a(15);
    }

    /* renamed from: a */
    public void m12704a(String str, UICallback oVar) throws Exception {
        ScriptDetailRequestInfo scriptDetailRequestInfo = new ScriptDetailRequestInfo();
        scriptDetailRequestInfo.setOnlyId(str);
        new BaseRepository().m18568a(Api.m18586a(scriptDetailRequestInfo.toGetUrl(HttpVal.f16534u))).m18572a(new ThreadCallback() { // from class: z1.akv.2
            @Override // com.nrzs.http.ThreadCallback
            public Object onResponse(String str2) {
                BaseResponse baseResponse = (BaseResponse) apa.m11877a(str2, new TypeToken<BaseResponse<ScriptInfoResponse>>() { // from class: z1.akv.2.1
                });
                int i = baseResponse.code;
                if (baseResponse == null || !(baseResponse.data instanceof ScriptInfoResponse)) {
                    return null;
                }
                return (ScriptInfoResponse) baseResponse.data;
            }
        }).m18571a(oVar).m18574a(16);
    }
}
