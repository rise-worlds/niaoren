package com.cyjh.ddy.net.helper;

import com.cyjh.ddy.net.helper.OkHttpClientMethod;
import com.cyjh.ddy.net.inf.IAnalysisJson;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import okhttp3.Call;

/* renamed from: com.cyjh.ddy.net.helper.d */
/* loaded from: classes.dex */
public abstract class OkHttpClientHelper {

    /* renamed from: a */
    private String f7529a = "OkHttpClientHelper";

    /* renamed from: a */
    public abstract void mo21419a(Exception exc);

    /* renamed from: a */
    public abstract void mo21418a(Object obj);

    /* renamed from: b */
    public void m21413b() {
    }

    /* renamed from: c */
    public boolean m21412c() {
        return true;
    }

    /* renamed from: a */
    public void m21417a(String str, String str2, IAnalysisJson aVar, int i) {
        HashMap hashMap = new HashMap();
        hashMap.put("Content-Type", "application/json");
        m21416a(str, str2, aVar, i, hashMap);
    }

    /* renamed from: a */
    public void m21416a(String str, String str2, final IAnalysisJson aVar, int i, Map<String, String> map) {
        try {
            OkHttpClientMethod.m21448a().m21447a(str2, null, map, new OkHttpClientMethod.Callback2() { // from class: com.cyjh.ddy.net.helper.OkHttpClientHelper$1
                @Override // com.cyjh.ddy.net.helper.OkHttpClientMethod.Callback2
                public void onFailure(Call call, IOException iOException) {
                    OkHttpClientHelper.this.mo21419a((Exception) iOException);
                }

                @Override // com.cyjh.ddy.net.helper.OkHttpClientMethod.Callback2
                public void onResponse(Call call, byte[] bArr) {
                    OkHttpClientHelper.this.mo21418a(aVar.getData(new String(bArr)));
                }
            });
        } catch (Exception e) {
            mo21419a(e);
        }
    }

    /* renamed from: a */
    public void m21414a(String str, String str2, Map<String, String> map, final IAnalysisJson aVar, int i) {
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("Content-Type", "application/json");
            OkHttpClientMethod.m21448a().m21446a(str2, null, map, hashMap, new OkHttpClientMethod.Callback2() { // from class: com.cyjh.ddy.net.helper.OkHttpClientHelper$2
                @Override // com.cyjh.ddy.net.helper.OkHttpClientMethod.Callback2
                public void onFailure(Call call, IOException iOException) {
                    OkHttpClientHelper.this.mo21419a((Exception) iOException);
                }

                @Override // com.cyjh.ddy.net.helper.OkHttpClientMethod.Callback2
                public void onResponse(Call call, byte[] bArr) {
                    OkHttpClientHelper.this.mo21418a(aVar.getData(new String(bArr)));
                }
            });
        } catch (Exception e) {
            mo21419a(e);
        }
    }

    /* renamed from: a */
    public void m21415a(String str, String str2, String str3, Map<String, String> map, final IAnalysisJson aVar, int i) {
        map.put("img", str3);
        try {
            OkHttpClientMethod.m21448a().m21446a(str2, null, map, new HashMap(), new OkHttpClientMethod.Callback2() { // from class: com.cyjh.ddy.net.helper.OkHttpClientHelper$3
                @Override // com.cyjh.ddy.net.helper.OkHttpClientMethod.Callback2
                public void onFailure(Call call, IOException iOException) {
                    OkHttpClientHelper.this.mo21419a((Exception) iOException);
                }

                @Override // com.cyjh.ddy.net.helper.OkHttpClientMethod.Callback2
                public void onResponse(Call call, byte[] bArr) {
                    OkHttpClientHelper.this.mo21418a(aVar.getData(new String(bArr)));
                }
            });
        } catch (Exception e) {
            mo21419a(e);
        }
    }
}
