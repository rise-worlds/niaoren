package p110z1;

import com.nrzs.data.base.BaseResponse;
import com.nrzs.data.xnkj.bean.request.XJRomStateRequest;
import com.nrzs.http.Api;
import com.nrzs.http.BaseRepository;

/* renamed from: z1.amo */
/* loaded from: classes3.dex */
public class XJRomStateRepository {
    /* renamed from: a */
    public static void m12503a(int i, String str) {
        try {
            BaseRepository eVar = new BaseRepository();
            XJRomStateRequest xJRomStateRequest = new XJRomStateRequest();
            xJRomStateRequest.HookType = i;
            xJRomStateRequest.LogContent = str;
            eVar.m18568a(Api.m18586a(xJRomStateRequest.toGetUrl(HttpVal.f16491G))).m18570a(BaseResponse.class).m18574a(36);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
