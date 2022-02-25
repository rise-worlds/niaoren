package p110z1;

import com.nrzs.data.xnkj.bean.FeedTagInfo;
import com.nrzs.data.xnkj.bean.request.PreSetRequestInfo;
import com.nrzs.data.xnkj.bean.response.XJBaseAppReponse;
import com.nrzs.http.Api;
import com.nrzs.http.BaseRepository;
import com.nrzs.http.ThreadCallback;
import com.nrzs.http.UICallback;
import java.util.HashMap;

/* renamed from: z1.amm */
/* loaded from: classes3.dex */
public class PreSetRepository {

    /* renamed from: a */
    private BaseRepository<XJBaseAppReponse<FeedTagInfo>> f16626a;

    /* renamed from: b */
    private ThreadCallback f16627b = new ThreadCallback<XJBaseAppReponse<FeedTagInfo>, String>() { // from class: z1.amm.1
        /* JADX WARN: Type inference failed for: r0v4, types: [T, java.lang.Object] */
        /* renamed from: a */
        public XJBaseAppReponse<FeedTagInfo> onResponse(String str) {
            try {
                XJBaseAppReponse xJBaseAppReponse = (XJBaseAppReponse) apa.m11877a(str, new TypeToken<XJBaseAppReponse<String>>() { // from class: z1.amm.1.1
                });
                if (xJBaseAppReponse.Code != 1) {
                    return null;
                }
                String b = DesManager.m12646b().m12645b((String) xJBaseAppReponse.Data);
                XJBaseAppReponse<FeedTagInfo> xJBaseAppReponse2 = new XJBaseAppReponse<>();
                xJBaseAppReponse2.Data = apa.m11877a(b, new TypeToken<FeedTagInfo>() { // from class: z1.amm.1.2
                });
                xJBaseAppReponse2.Code = xJBaseAppReponse.Code;
                xJBaseAppReponse2.Msg = xJBaseAppReponse.Msg;
                return xJBaseAppReponse2;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    };

    /* renamed from: a */
    public void m12507a(UICallback<XJBaseAppReponse<FeedTagInfo>> oVar) {
        if (this.f16626a == null) {
            this.f16626a = new BaseRepository<>();
        }
        try {
            PreSetRequestInfo preSetRequestInfo = new PreSetRequestInfo();
            HashMap hashMap = new HashMap();
            hashMap.put("data", preSetRequestInfo.getJson());
            this.f16626a.m18568a(Api.m18584a("http://va.niaoren001.com/api/PreSet", hashMap, new HashMap())).m18572a(this.f16627b).m18571a(oVar).m18574a(34);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
