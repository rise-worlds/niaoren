package p110z1;

import android.text.TextUtils;
import com.nrzs.data.DataApp;
import com.nrzs.data.base.BaseResponse;

/* renamed from: z1.alu */
/* loaded from: classes3.dex */
public class DataUtil {
    /* renamed from: a */
    public static <T> T m12537a(String str, Class<T> cls) {
        if (str == null || str.equals("")) {
            return null;
        }
        return (T) apa.m11876b(str, cls);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: b */
    public static <T> BaseResponse<T> m12536b(String str, Class<T> cls) {
        BaseResponse baseResponse;
        T t = null;
        if (TextUtils.isEmpty(str) || (baseResponse = (BaseResponse) apa.m11876b(str, BaseResponse.class)) == null || baseResponse.data == null) {
            return null;
        }
        if (baseResponse.data instanceof String) {
            try {
                String a = DesUtil.m12535a((String) baseResponse.data, DataApp.m18939d().m18947a());
                if (!TextUtils.isEmpty(a) && a.length() > 36) {
                    if (UUIDManager.m12598a().m12595b(str.substring(0, 36))) {
                        t = m12537a(str.substring(36, str.length()), cls);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        BaseResponse<T> baseResponse2 = new BaseResponse<>();
        baseResponse2.msgtype = baseResponse.msgtype;
        baseResponse2.msg = baseResponse.msg;
        baseResponse2.code = baseResponse.code;
        baseResponse2.data = t;
        baseResponse2.f10622r = baseResponse.f10622r;
        baseResponse2.sign = baseResponse.sign;
        return baseResponse2;
    }
}
