package p110z1;

import android.util.Log;
import com.nrzs.data.DataApp;
import com.nrzs.data.base.BaseResponse;
import com.nrzs.data.p066ft.bean.AssistInfo;
import com.nrzs.data.p066ft.bean.request.AssistRequestInfo;
import com.nrzs.data.p066ft.bean.response.AssistResponseInfo;
import com.nrzs.http.Api;
import com.nrzs.http.BaseRepository;
import com.nrzs.http.ThreadCallback;
import com.nrzs.http.UICallback;
import java.util.List;

/* renamed from: z1.aki */
/* loaded from: classes3.dex */
public class AssistRepository {

    /* renamed from: a */
    private BaseRepository<List<AssistInfo>> f16177a;

    /* renamed from: b */
    private ThreadCallback f16178b = new ThreadCallback<List<AssistInfo>, String>() { // from class: z1.aki.1
        /* renamed from: a */
        public List<AssistInfo> onResponse(String str) {
            BaseResponse baseResponse = (BaseResponse) apa.m11877a(str, new TypeToken<BaseResponse<AssistResponseInfo>>() { // from class: z1.aki.1.1
            });
            if (baseResponse == null || baseResponse.data == 0) {
                return null;
            }
            return ((AssistResponseInfo) baseResponse.data).rdata;
        }
    };

    /* renamed from: a */
    public void m12787a(AssistRequestInfo assistRequestInfo, UICallback<List<AssistInfo>> oVar) {
        String b = apf.m11837b(DataApp.m18939d().m18947a(), ShareVal.f16591a, ShareVal.f16586Q, "45.158.183.252");
        try {
            if (this.f16177a == null) {
                this.f16177a = new BaseRepository<>();
            }
            String getUrl = assistRequestInfo.toGetUrl(b);
            Log.e("IP地址", b);
            this.f16177a.m18568a(Api.m18586a(getUrl)).m18572a(this.f16178b).m18571a(oVar).m18574a(3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public void m12788a() {
        if (this.f16177a != null) {
            this.f16177a = null;
        }
    }
}
