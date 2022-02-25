package p110z1;

import com.nrzs.data.other.bean.AdResultInfoItem;
import com.nrzs.data.other.bean.request.GetWelcomeV6RequestInfo;
import com.nrzs.http.Api;
import com.nrzs.http.BaseRepository;
import com.nrzs.http.ThreadCallback;
import com.nrzs.http.UICallback;
import java.util.List;

/* renamed from: z1.alh */
/* loaded from: classes3.dex */
public class ADRepository {

    /* renamed from: a */
    private BaseRepository<List<AdResultInfoItem>> f16355a;

    /* renamed from: a */
    public void m12585a(GetWelcomeV6RequestInfo getWelcomeV6RequestInfo, UICallback oVar, ThreadCallback nVar) throws Exception {
        if (this.f16355a == null) {
            this.f16355a = new BaseRepository<>();
        }
        this.f16355a.m18568a(Api.m18586a(getWelcomeV6RequestInfo.toGetUrl(HttpVal.f16532s))).m18572a(nVar).m18571a(oVar).m18574a(1);
    }
}
