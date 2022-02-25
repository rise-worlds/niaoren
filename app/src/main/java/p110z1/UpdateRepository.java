package p110z1;

import com.nrzs.data.xnkj.bean.AppUpdateInfo;
import com.nrzs.data.xnkj.bean.request.UpdateRequestInfo;
import com.nrzs.data.xnkj.bean.response.XJBaseAppReponse;
import com.nrzs.http.Api;
import com.nrzs.http.BaseRepository;
import com.nrzs.http.ThreadCallback;
import com.nrzs.http.UICallback;
import java.util.HashMap;

/* renamed from: z1.amn */
/* loaded from: classes3.dex */
public class UpdateRepository {

    /* renamed from: a */
    private BaseRepository<XJBaseAppReponse<AppUpdateInfo>> f16631a;

    /* renamed from: b */
    private ThreadCallback f16632b = new ThreadCallback<XJBaseAppReponse<AppUpdateInfo>, String>() { // from class: z1.amn.1
        /* JADX WARN: Type inference failed for: r4v8, types: [T, java.lang.Object] */
        /* renamed from: a */
        public XJBaseAppReponse<AppUpdateInfo> onResponse(String str) {
            try {
                XJBaseAppReponse xJBaseAppReponse = (XJBaseAppReponse) apa.m11877a(str, new TypeToken<XJBaseAppReponse<String>>() { // from class: z1.amn.1.1
                });
                XJBaseAppReponse<AppUpdateInfo> xJBaseAppReponse2 = new XJBaseAppReponse<>();
                xJBaseAppReponse2.Code = xJBaseAppReponse.Code;
                xJBaseAppReponse2.Msg = xJBaseAppReponse.Msg;
                if (xJBaseAppReponse.Code == 1) {
                    xJBaseAppReponse2.Data = apa.m11877a(DesManager.m12646b().m12645b((String) xJBaseAppReponse.Data), new TypeToken<AppUpdateInfo>() { // from class: z1.amn.1.2
                    });
                }
                return xJBaseAppReponse2;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    };

    /* renamed from: a */
    public void m12505a(int i, UICallback<XJBaseAppReponse<AppUpdateInfo>> oVar) {
        if (this.f16631a == null) {
            this.f16631a = new BaseRepository<>();
        }
        try {
            UpdateRequestInfo updateRequestInfo = new UpdateRequestInfo();
            if (i == 0) {
                i = 1;
            }
            updateRequestInfo.appVersionCode = i;
            HashMap hashMap = new HashMap();
            hashMap.put("data", updateRequestInfo.getJson());
            this.f16631a.m18568a(Api.m18584a("http://va.niaoren001.com/app/AppUpdate", hashMap, new HashMap())).m18572a(this.f16632b).m18571a(oVar).m18574a(35);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
