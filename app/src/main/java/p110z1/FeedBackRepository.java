package p110z1;

import android.os.Build;
import com.nrzs.data.xnkj.bean.request.FeedBackRequestInfo;
import com.nrzs.data.xnkj.bean.response.XJBaseAppReponse;
import com.nrzs.http.Api;
import com.nrzs.http.BaseRepository;
import com.nrzs.http.ThreadCallback;
import com.nrzs.http.UICallback;
import java.util.HashMap;

/* renamed from: z1.aml */
/* loaded from: classes3.dex */
public class FeedBackRepository {

    /* renamed from: a */
    private BaseRepository<XJBaseAppReponse<String>> f16622a;

    /* renamed from: b */
    private ThreadCallback f16623b = new ThreadCallback<XJBaseAppReponse<String>, String>() { // from class: z1.aml.1
        /* renamed from: a */
        public XJBaseAppReponse<String> onResponse(String str) {
            return (XJBaseAppReponse) apa.m11877a(str, new TypeToken<XJBaseAppReponse<String>>() { // from class: z1.aml.1.1
            });
        }
    };

    /* renamed from: a */
    public void m12509a(FeedBackRequestInfo feedBackRequestInfo, UICallback<XJBaseAppReponse<String>> oVar) {
        if (this.f16622a == null) {
            this.f16622a = new BaseRepository<>();
        }
        if (feedBackRequestInfo != null) {
            try {
                feedBackRequestInfo.mobileModel = Build.MODEL;
                feedBackRequestInfo.mobileVendor = Build.BRAND;
                feedBackRequestInfo.sysVersion = Build.VERSION.RELEASE;
                HashMap hashMap = new HashMap();
                hashMap.put("data", feedBackRequestInfo.getJson());
                this.f16622a.m18568a(Api.m18584a("http://va.niaoren001.com/api/FeedBack", hashMap, new HashMap())).m18572a(this.f16623b).m18571a(oVar).m18574a(32);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
