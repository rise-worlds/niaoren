package p110z1;

import com.nrzs.data.appupdate.bean.request.GetVersionRequest;
import com.nrzs.http.Api;
import com.nrzs.http.BaseRepository;
import com.nrzs.http.ThreadCallback;
import com.nrzs.http.UICallback;

/* renamed from: z1.ajm */
/* loaded from: classes3.dex */
public class AppUpdateModel {
    /* renamed from: a */
    public void m12912a(GetVersionRequest getVersionRequest, UICallback oVar, ThreadCallback nVar) throws Exception {
        new BaseRepository().m18568a(Api.m18586a(getVersionRequest.toGetUrl(HttpVal.f16529p))).m18572a(nVar).m18571a(oVar).m18574a(2);
    }
}
