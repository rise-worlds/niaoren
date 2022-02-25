package p110z1;

import android.text.TextUtils;
import android.util.Log;
import com.nrzs.data.DataApp;
import com.nrzs.data.base.BaseRequest;
import com.nrzs.data.base.BaseResponse;
import com.nrzs.data.game.bean.GameScreenInfo;
import com.nrzs.data.game.bean.request.VAGameScreenRequestInfo;
import com.nrzs.data.game.bean.response.GameScreenAdapterReponseInfo;
import com.nrzs.http.Api;
import com.nrzs.http.BaseRepository;
import com.nrzs.http.ThreadCallback;
import com.nrzs.http.UICallback;
import java.util.HashMap;
import java.util.List;

/* renamed from: z1.akx */
/* loaded from: classes3.dex */
public class VAGameScreenInfoRepository {

    /* renamed from: a */
    private BaseRepository<BaseResponse<GameScreenInfo>> f16273a;

    /* renamed from: b */
    private BaseRepository<BaseResponse<List<GameScreenAdapterReponseInfo>>> f16274b;

    /* renamed from: c */
    private ThreadCallback f16275c = new ThreadCallback<BaseResponse<GameScreenInfo>, String>() { // from class: z1.akx.1
        /* renamed from: a */
        public BaseResponse<GameScreenInfo> onResponse(String str) {
            return (BaseResponse) apa.m11877a(str, new TypeToken<BaseResponse<GameScreenInfo>>() { // from class: z1.akx.1.1
            });
        }
    };

    /* renamed from: d */
    private ThreadCallback f16276d = new ThreadCallback<BaseResponse<GameScreenAdapterReponseInfo>, String>() { // from class: z1.akx.2
        /* renamed from: a */
        public BaseResponse<GameScreenAdapterReponseInfo> onResponse(String str) {
            TextUtils.isEmpty(str);
            return (BaseResponse) apa.m11877a(str, new TypeToken<BaseResponse<GameScreenAdapterReponseInfo>>() { // from class: z1.akx.2.1
            });
        }
    };

    /* renamed from: a */
    public void m12694a(UICallback<BaseResponse<GameScreenInfo>> oVar, String str) {
        if (this.f16273a == null) {
            this.f16273a = new BaseRepository<>();
        }
        try {
            VAGameScreenRequestInfo vAGameScreenRequestInfo = new VAGameScreenRequestInfo();
            vAGameScreenRequestInfo.isVa = 1;
            vAGameScreenRequestInfo.gamePackageName = str;
            this.f16273a.m18568a(Api.m18586a(vAGameScreenRequestInfo.toGetUrl(HttpVal.f16485A))).m18572a(this.f16275c).m18571a(oVar).m18574a(28);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public void m12695a() {
        this.f16274b = new BaseRepository<>();
        UICallback<BaseResponse<GameScreenAdapterReponseInfo>> oVar = new UICallback<BaseResponse<GameScreenAdapterReponseInfo>>() { // from class: z1.akx.3
            @Override // com.nrzs.http.UICallback
            /* renamed from: a */
            public void mo3021a(Throwable th) {
                Log.e("loadScreenData", "onError");
            }

            /* renamed from: a  reason: avoid collision after fix types in other method */
            public void mo3022a(BaseResponse<GameScreenAdapterReponseInfo> baseResponse) {
                if (baseResponse.data != null && baseResponse.data.rdata != null) {
                    apf.m11842a(DataApp.m18939d().m18947a(), ShareVal.f16591a, ShareVal.f16613w, apa.m11879a(baseResponse.data.rdata));
                }
            }
        };
        BaseRequest baseRequest = new BaseRequest();
        String str = HttpVal.f16487C;
        try {
            this.f16274b.m18568a(Api.m18584a(str, baseRequest.toPostUrl(str, baseRequest.getMapParams()), new HashMap())).m18572a(this.f16276d).m18571a(oVar).m18574a(29);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
