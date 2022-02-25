package com.nrzs.http;

import java.util.Map;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import p110z1.Flowable;
import retrofit2.Call;
import retrofit2.http.Part;

/* renamed from: com.nrzs.http.a */
/* loaded from: classes2.dex */
public class Api {
    /* renamed from: a */
    public static Flowable<ResponseBody> m18586a(String str) {
        return ((ApiService) NetEngin.m18553b().m18554a().create(ApiService.class)).m18581a(str);
    }

    /* renamed from: b */
    public static Call<ResponseBody> m18582b(String str) {
        return ((ApiService) NetEngin.m18553b().m18554a().create(ApiService.class)).m18578b(str);
    }

    /* renamed from: a */
    public static Flowable<ResponseBody> m18584a(String str, Map<String, String> map, Map<String, String> map2) {
        return ((ApiService) NetEngin.m18553b().m18554a().create(ApiService.class)).m18580a(str, map, map2);
    }

    /* renamed from: a */
    public static Flowable<ResponseBody> m18583a(String str, @Part("UserId") RequestBody requestBody, @Part("UploadStatue") RequestBody requestBody2, @Part MultipartBody.Part part) {
        return ((ApiService) NetEngin.m18553b().m18554a().create(ApiService.class)).m18579a(str, requestBody, requestBody2, part);
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x006a  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static <T> java.util.List<T> m18585a(java.lang.String r3, java.lang.Class<T> r4) throws java.lang.Exception {
        /*
            r3 = 0
            com.nrzs.http.j r0 = com.nrzs.http.NetEngin.m18553b()     // Catch: all -> 0x004c, IOException -> 0x004f
            retrofit2.Retrofit r0 = r0.m18554a()     // Catch: all -> 0x004c, IOException -> 0x004f
            java.lang.Class<com.nrzs.http.c> r1 = com.nrzs.http.ApiService.class
            java.lang.Object r0 = r0.create(r1)     // Catch: all -> 0x004c, IOException -> 0x004f
            com.nrzs.http.c r0 = (com.nrzs.http.ApiService) r0     // Catch: all -> 0x004c, IOException -> 0x004f
            java.lang.String r1 = "http://news-at.zhihu.com/api/4/version/android/2.3.0"
            retrofit2.Call r0 = r0.m18578b(r1)     // Catch: all -> 0x004c, IOException -> 0x004f
            retrofit2.Response r0 = r0.execute()     // Catch: all -> 0x004c, IOException -> 0x004f
            java.lang.Object r0 = r0.body()     // Catch: all -> 0x004c, IOException -> 0x004f
            okhttp3.ResponseBody r0 = (okhttp3.ResponseBody) r0     // Catch: all -> 0x004c, IOException -> 0x004f
            if (r0 == 0) goto L_0x0046
            okio.BufferedSource r1 = r0.source()     // Catch: all -> 0x0040, IOException -> 0x0043
            okio.BufferedSource r1 = okio.Okio.buffer(r1)     // Catch: all -> 0x0040, IOException -> 0x0043
            java.lang.String r2 = r1.readUtf8()     // Catch: IOException -> 0x003e, all -> 0x0060
            java.util.List r3 = p110z1.apa.m11875c(r2, r4)     // Catch: IOException -> 0x003e, all -> 0x0060
            if (r0 == 0) goto L_0x0038
            r0.close()
        L_0x0038:
            if (r1 == 0) goto L_0x003d
            r1.close()
        L_0x003d:
            return r3
        L_0x003e:
            r4 = move-exception
            goto L_0x0052
        L_0x0040:
            r4 = move-exception
            r1 = r3
            goto L_0x0062
        L_0x0043:
            r4 = move-exception
            r1 = r3
            goto L_0x0052
        L_0x0046:
            if (r0 == 0) goto L_0x005f
            r0.close()
            goto L_0x005f
        L_0x004c:
            r4 = move-exception
            r1 = r3
            goto L_0x0063
        L_0x004f:
            r4 = move-exception
            r0 = r3
            r1 = r0
        L_0x0052:
            r4.printStackTrace()     // Catch: all -> 0x0060
            if (r0 == 0) goto L_0x005a
            r0.close()
        L_0x005a:
            if (r1 == 0) goto L_0x005f
            r1.close()
        L_0x005f:
            return r3
        L_0x0060:
            r3 = move-exception
            r4 = r3
        L_0x0062:
            r3 = r0
        L_0x0063:
            if (r3 == 0) goto L_0x0068
            r3.close()
        L_0x0068:
            if (r1 == 0) goto L_0x006d
            r1.close()
        L_0x006d:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nrzs.http.Api.m18585a(java.lang.String, java.lang.Class):java.util.List");
    }
}
