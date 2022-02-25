package p110z1;

import com.nrzs.data.base.BaseResponse;
import com.nrzs.http.Api;
import com.nrzs.http.BaseRepository;
import com.nrzs.http.ThreadCallback;
import com.nrzs.http.UICallback;
import com.redwas.bean.RedPacket;
import com.redwas.bean.RedRequestResultInfo;
import com.redwas.bean.RequestInfo;

/* renamed from: z1.aqo */
/* loaded from: classes3.dex */
public class RedRepository {

    /* renamed from: a */
    private BaseRepository<BaseResponse<RedRequestResultInfo>> f17374a;

    /* renamed from: b */
    private BaseRepository<BaseResponse<Object>> f17375b;

    /* renamed from: a */
    public void m11544a(RequestInfo requestInfo, UICallback oVar, ThreadCallback nVar) throws Exception {
        if (this.f17374a == null) {
            this.f17374a = new BaseRepository<>();
        }
        this.f17374a.m18568a(Api.m18586a(requestInfo.toGetUrl(HttpVal.f16501Q))).m18572a(nVar).m18571a(oVar).m18574a(27);
    }

    /* renamed from: a */
    public void m11545a(RedPacket redPacket, UICallback oVar, ThreadCallback nVar) throws Exception {
        if (this.f17375b == null) {
            this.f17375b = new BaseRepository<>();
        }
        this.f17375b.m18568a(Api.m18586a(redPacket.toGetUrl(HttpVal.f16502R))).m18572a(nVar).m18571a(oVar).m18574a(27);
    }
}
