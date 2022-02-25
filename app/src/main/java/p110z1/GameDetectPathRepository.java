package p110z1;

import com.nrzs.data.base.BaseResponse;
import com.nrzs.data.game.bean.request.VAGamePathRequest;
import com.nrzs.http.Api;
import com.nrzs.http.BaseRepository;
import com.nrzs.http.ThreadCallback;
import com.nrzs.http.UICallback;
import java.util.List;

/* renamed from: z1.akt */
/* loaded from: classes3.dex */
public class GameDetectPathRepository {

    /* renamed from: a */
    private BaseRepository<BaseResponse<List<String>>> f16252a;

    /* renamed from: b */
    private ThreadCallback f16253b = new ThreadCallback<BaseResponse<List<String>>, String>() { // from class: z1.akt.1
        /* renamed from: a */
        public BaseResponse<List<String>> onResponse(String str) {
            return (BaseResponse) apa.m11877a(str, new TypeToken<BaseResponse<List<String>>>() { // from class: z1.akt.1.1
            });
        }
    };

    /* renamed from: a */
    public void m12712a(UICallback<BaseResponse<List<String>>> oVar, String str, String str2) {
        if (this.f16252a == null) {
            this.f16252a = new BaseRepository<>();
        }
        try {
            VAGamePathRequest vAGamePathRequest = new VAGamePathRequest();
            vAGamePathRequest.gamePackageName = str;
            vAGamePathRequest.gameVersionName = str2;
            this.f16252a.m18568a(Api.m18586a(vAGamePathRequest.toGetUrl(HttpVal.f16539z))).m18572a(this.f16253b).m18571a(oVar).m18574a(8);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
