package p110z1;

import android.util.Log;
import com.nrzs.data.other.bean.PurchassedAssistinfo;
import com.nrzs.data.other.bean.request.PurchassedAssistRequestInfo;
import com.nrzs.http.Api;
import com.nrzs.http.BaseRepository;
import com.nrzs.http.ThreadCallback;
import com.nrzs.http.UICallback;
import java.util.List;

/* renamed from: z1.alk */
/* loaded from: classes3.dex */
public class PurchassedAssistRepository {

    /* renamed from: a */
    private BaseRepository<List<PurchassedAssistinfo>> f16357a;

    /* renamed from: a */
    public void m12581a(PurchassedAssistRequestInfo purchassedAssistRequestInfo, UICallback oVar, ThreadCallback nVar) throws Exception {
        if (this.f16357a == null) {
            this.f16357a = new BaseRepository<>();
        }
        String getUrl = purchassedAssistRequestInfo.toGetUrl(HttpVal.f16531r);
        Log.e("url", getUrl);
        this.f16357a.m18568a(Api.m18586a(getUrl)).m18572a(nVar).m18571a(oVar).m18574a(12);
    }
}
