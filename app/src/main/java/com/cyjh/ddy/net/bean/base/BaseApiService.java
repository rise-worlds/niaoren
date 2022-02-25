package com.cyjh.ddy.net.bean.base;

import java.util.Map;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import p110z1.Flowable;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Url;

/* loaded from: classes.dex */
public interface BaseApiService {
    @GET
    Flowable<ResponseBody> executeGet(@Url String str);

    @FormUrlEncoded
    @POST
    Flowable<ResponseBody> executePost(@Url String str, @FieldMap Map<String, String> map, @HeaderMap Map<String, String> map2);

    @POST
    Flowable<ResponseBody> uploadResourcePost(@Url String str, @Body RequestBody requestBody);
}
