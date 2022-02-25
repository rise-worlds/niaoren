package p110z1;

import android.util.Log;
import com.nrzs.data.base.BaseResponse;
import com.nrzs.data.other.bean.request.GetRewardRequestinfo;
import com.nrzs.data.other.bean.response.GiveRewardresponseinfo;
import com.nrzs.data.other.bean.response.RewarResponseinfo;
import com.nrzs.http.Api;
import com.nrzs.http.BaseRepository;
import com.nrzs.http.ThreadCallback;
import com.nrzs.http.UICallback;

/* renamed from: z1.all */
/* loaded from: classes3.dex */
public class RewardRepository {

    /* renamed from: a */
    private BaseRepository<BaseResponse<RewarResponseinfo>> f16358a;

    /* renamed from: b */
    private BaseRepository<BaseResponse<GiveRewardresponseinfo>> f16359b;

    /* renamed from: a */
    public void m12580a(GetRewardRequestinfo getRewardRequestinfo, UICallback oVar, ThreadCallback nVar) throws Exception {
        if (this.f16358a == null) {
            this.f16358a = new BaseRepository<>();
        }
        String getUrl = getRewardRequestinfo.toGetUrl(HttpVal.f16488D);
        Log.e("url", getUrl);
        this.f16358a.m18568a(Api.m18586a(getUrl)).m18572a(nVar).m18571a(oVar).m18574a(13);
    }

    /* renamed from: b */
    public void m12579b(GetRewardRequestinfo getRewardRequestinfo, UICallback oVar, ThreadCallback nVar) throws Exception {
        if (this.f16359b == null) {
            this.f16359b = new BaseRepository<>();
        }
        String getUrl = getRewardRequestinfo.toGetUrl(HttpVal.f16489E);
        Log.e("url", getUrl);
        this.f16359b.m18568a(Api.m18586a(getUrl)).m18572a(nVar).m18571a(oVar).m18574a(14);
    }
}
