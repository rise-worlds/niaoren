package p110z1;

import com.nrzs.data.DataApp;
import com.nrzs.data.base.BaseRequest;
import com.nrzs.data.base.BaseResponse;
import com.nrzs.data.game.bean.request.SearchRequestInfo;
import com.nrzs.data.game.bean.response.SearchKeyResponse;
import com.nrzs.data.game.bean.response.SearchResponse;
import com.nrzs.http.Api;
import com.nrzs.http.BaseRepository;
import com.nrzs.http.ThreadCallback;
import com.nrzs.http.UICallback;

/* renamed from: z1.aku */
/* loaded from: classes3.dex */
public class GameSearchRepository {

    /* renamed from: a */
    private ThreadCallback f16256a = new ThreadCallback<SearchKeyResponse, String>() { // from class: z1.aku.1
        /* renamed from: a */
        public SearchKeyResponse onResponse(String str) {
            BaseResponse baseResponse = (BaseResponse) apa.m11877a(str, new TypeToken<BaseResponse<SearchKeyResponse>>() { // from class: z1.aku.1.1
            });
            int i = baseResponse.code;
            if (baseResponse == null || !(baseResponse.data instanceof SearchKeyResponse)) {
                return null;
            }
            return (SearchKeyResponse) baseResponse.data;
        }
    };

    /* renamed from: b */
    private ThreadCallback f16257b = new ThreadCallback<SearchResponse, String>() { // from class: z1.aku.2
        /* renamed from: a */
        public SearchResponse onResponse(String str) {
            BaseResponse baseResponse = (BaseResponse) apa.m11877a(str, new TypeToken<BaseResponse<SearchResponse>>() { // from class: z1.aku.2.1
            });
            int i = baseResponse.code;
            if (baseResponse == null || !(baseResponse.data instanceof SearchResponse)) {
                return null;
            }
            return (SearchResponse) baseResponse.data;
        }
    };

    /* renamed from: a */
    public void m12709a(UICallback oVar) throws Exception {
        new BaseRepository().m18568a(Api.m18586a(new BaseRequest().toGetUrl(HttpVal.f16535v))).m18572a(this.f16256a).m18571a(oVar).m18574a(9);
    }

    /* renamed from: a */
    public void m12710a(int i, int i2, String str, UICallback oVar) throws Exception {
        SearchRequestInfo searchRequestInfo = new SearchRequestInfo();
        searchRequestInfo.setCurrentPage(i);
        searchRequestInfo.setPageSize(i2);
        searchRequestInfo.setSearchKey(str);
        searchRequestInfo.setAuthorId(DataApp.m18939d().f10605h);
        if (LoginManager.m12620d().m12630a() == null) {
            searchRequestInfo.setUserID(-1L);
        } else {
            searchRequestInfo.setUserID(LoginManager.m12620d().m12630a().UserID);
        }
        new BaseRepository().m18568a(Api.m18586a(searchRequestInfo.toGetUrl(HttpVal.f16536w))).m18572a(this.f16257b).m18571a(oVar).m18574a(10);
    }
}
