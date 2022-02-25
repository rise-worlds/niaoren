package com.nrzs.http;

import java.util.Map;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import p110z1.Flowable;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Url;

/* renamed from: com.nrzs.http.c */
/* loaded from: classes2.dex */
public interface ApiService {
    @GET
    /* renamed from: a */
    Flowable<ResponseBody> m18581a(@Url String str);

    @FormUrlEncoded
    @POST
    /* renamed from: a */
    Flowable<ResponseBody> m18580a(@Url String str, @FieldMap Map<String, String> map, @HeaderMap Map<String, String> map2);

    @POST
    @Multipart
    /* renamed from: a */
    Flowable<ResponseBody> m18579a(@Url String str, @Part("UserId") RequestBody requestBody, @Part("UploadStatue") RequestBody requestBody2, @Part MultipartBody.Part part);

    @GET
    /* renamed from: b */
    Call<ResponseBody> m18578b(@Url String str);

    @FormUrlEncoded
    @POST
    /* renamed from: b */
    Call<ResponseBody> m18577b(@Url String str, @FieldMap Map<String, String> map, @HeaderMap Map<String, String> map2);
}
