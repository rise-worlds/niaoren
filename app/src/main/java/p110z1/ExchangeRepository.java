package p110z1;

import com.nrzs.data.other.bean.request.GetExchangeRequestInfo;
import com.nrzs.data.other.bean.response.ExchageResponseinfo;
import com.nrzs.http.Api;
import com.nrzs.http.BaseRepository;
import com.nrzs.http.ThreadCallback;
import com.nrzs.http.UICallback;

/* renamed from: z1.alj */
/* loaded from: classes3.dex */
public class ExchangeRepository {

    /* renamed from: a */
    private BaseRepository<ExchageResponseinfo> f16356a;

    /* renamed from: a */
    public void m12582a(GetExchangeRequestInfo getExchangeRequestInfo, UICallback oVar, ThreadCallback nVar) {
        try {
            if (this.f16356a == null) {
                this.f16356a = new BaseRepository<>();
            }
            this.f16356a.m18568a(Api.m18586a(getExchangeRequestInfo.toGetUrl(HttpVal.f16490F))).m18572a(nVar).m18571a(oVar).m18574a(7);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
