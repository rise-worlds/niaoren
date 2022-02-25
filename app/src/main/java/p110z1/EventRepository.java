package p110z1;

import com.nrzs.data.DataApp;
import com.nrzs.data.base.BaseResponse;
import com.nrzs.data.other.bean.request.EventCollectRequestInfo;
import com.nrzs.http.Api;
import com.nrzs.http.BaseRepository;
import com.nrzs.http.ThreadCallback;
import com.nrzs.http.UICallback;
import java.io.File;
import java.util.HashMap;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/* renamed from: z1.ali */
/* loaded from: classes3.dex */
public class EventRepository {
    /* renamed from: a */
    public void m12583a(String str) {
        try {
            String b = apf.m11837b(DataApp.m18939d().m18947a(), ShareVal.f16591a, ShareVal.f16590U, "45.158.183.252");
            BaseRepository eVar = new BaseRepository();
            EventCollectRequestInfo eventCollectRequestInfo = new EventCollectRequestInfo();
            eventCollectRequestInfo.setData(str);
            eventCollectRequestInfo.setAppCode(EventConstants.f16423a);
            eventCollectRequestInfo.setAppKey(ShareVal.f16591a);
            eventCollectRequestInfo.setUserId(LoginManager.m12620d().m12614j());
            String getUrl = eventCollectRequestInfo.toGetUrl(b);
            HashMap hashMap = new HashMap();
            hashMap.put("Content-Serial", apb.m11874a(getUrl));
            eVar.m18568a(Api.m18584a(b, eventCollectRequestInfo.getMapParams(), hashMap)).m18570a(BaseResponse.class).m18574a(6);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public void m12584a(UICallback oVar, ThreadCallback nVar, String str, long j, int i) {
        try {
            new BaseRepository().m18572a(nVar).m18571a(oVar).m18568a(Api.m18583a(HttpVal.f16509Y, RequestBody.create(MediaType.parse("text/plain"), String.valueOf(j)), RequestBody.create(MediaType.parse("text/plain"), String.valueOf(i)), MultipartBody.Part.createFormData("file", "LocalAppFileMD5", RequestBody.create(MediaType.parse("file"), new File(str))))).m18570a(BaseResponse.class).m18574a(6);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
