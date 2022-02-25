package p110z1;

import com.blankj.utilcode.util.LogUtils;
import com.nrzs.data.xnkj.bean.XJBannerInfo;
import com.nrzs.data.xnkj.bean.request.BannerRequestInfo;
import com.nrzs.data.xnkj.bean.response.XJBaseAppReponse;
import com.nrzs.http.Api;
import com.nrzs.http.BaseRepository;
import com.nrzs.http.ThreadCallback;
import com.nrzs.http.UICallback;
import java.util.HashMap;
import java.util.List;

/* renamed from: z1.amk */
/* loaded from: classes3.dex */
public class BannerRepository {

    /* renamed from: a */
    private BaseRepository<XJBaseAppReponse<List<XJBannerInfo>>> f16617a;

    /* renamed from: b */
    private ThreadCallback f16618b = new ThreadCallback<XJBaseAppReponse<List<XJBannerInfo>>, String>() { // from class: z1.amk.1
        /* JADX WARN: Type inference failed for: r0v4, types: [T, java.lang.Object] */
        /* renamed from: a */
        public XJBaseAppReponse<List<XJBannerInfo>> onResponse(String str) {
            try {
                XJBaseAppReponse xJBaseAppReponse = (XJBaseAppReponse) apa.m11877a(str, new TypeToken<XJBaseAppReponse<String>>() { // from class: z1.amk.1.1
                });
                if (xJBaseAppReponse.Code != 1) {
                    return null;
                }
                String b = DesManager.m12646b().m12645b((String) xJBaseAppReponse.Data);
                LogUtils.m23734c("ADS", "json:" + b);
                XJBaseAppReponse<List<XJBannerInfo>> xJBaseAppReponse2 = new XJBaseAppReponse<>();
                xJBaseAppReponse2.Data = apa.m11877a(b, new TypeToken<List<XJBannerInfo>>() { // from class: z1.amk.1.2
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
    public void m12511a(UICallback<XJBaseAppReponse<List<XJBannerInfo>>> oVar) {
        if (this.f16617a == null) {
            this.f16617a = new BaseRepository<>();
        }
        try {
            BannerRequestInfo bannerRequestInfo = new BannerRequestInfo();
            HashMap hashMap = new HashMap();
            hashMap.put("data", bannerRequestInfo.getJson());
            this.f16617a.m18568a(Api.m18584a("http://va.niaoren001.com/app/Ads", hashMap, new HashMap())).m18572a(this.f16618b).m18571a(oVar).m18574a(33);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
