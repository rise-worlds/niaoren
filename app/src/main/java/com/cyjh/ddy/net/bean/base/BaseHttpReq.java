package com.cyjh.ddy.net.bean.base;

import android.text.TextUtils;
import com.cyjh.ddy.p032a.C1118a;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import p110z1.AbstractC4442bk;

/* loaded from: classes.dex */
public class BaseHttpReq {
    public String Data;

    public String toWebParamsFroData(String str, BaseRequestInfo baseRequestInfo) throws Exception {
        int randomInt = getRandomInt();
        this.Data = baseRequestInfo.toJson(str);
        this.Data = URLEncoder.encode(this.Data, "UTF-8");
        String sign = getSign(str, randomInt);
        int i = baseRequestInfo.signType;
        return "Data=" + this.Data + "&r=" + randomInt + "&sign=" + sign + "&signType=" + i + "&AppId=" + baseRequestInfo.AppId;
    }

    public String toWebParams(BaseRequestInfo baseRequestInfo) throws Exception {
        int randomInt = getRandomInt();
        String json = baseRequestInfo.getJson();
        this.Data = baseRequestInfo.toJson(json);
        this.Data = URLEncoder.encode(this.Data, "UTF-8");
        String sign = getSign(json, randomInt);
        int i = baseRequestInfo.signType;
        return "Data=" + this.Data + "&r=" + randomInt + "&sign=" + sign + "&signType=" + i + "&AppId=" + baseRequestInfo.AppId;
    }

    public Map<String, String> toMapPrames(BaseRequestInfo baseRequestInfo) throws Exception {
        int randomInt = getRandomInt();
        String json = baseRequestInfo.getJson();
        this.Data = baseRequestInfo.toJson(json);
        String sign = getSign(json, randomInt);
        HashMap hashMap = new HashMap();
        hashMap.put("Data", this.Data);
        hashMap.put("Sign", sign);
        hashMap.put("R", randomInt + "");
        hashMap.put("signType", "" + baseRequestInfo.signType);
        hashMap.put(AbstractC4442bk.f19091f, baseRequestInfo.AppId);
        return hashMap;
    }

    public Map<String, String> toMapPrames(BaseRequest baseRequest) throws Exception {
        int randomInt = getRandomInt();
        String json = baseRequest.getJson();
        this.Data = baseRequest.toJson(json);
        String sign = getSign(json, randomInt);
        HashMap hashMap = new HashMap();
        hashMap.put("Data", this.Data);
        hashMap.put("Sign", sign);
        hashMap.put("R", randomInt + "");
        hashMap.put("signType", "" + baseRequest.signType);
        hashMap.put(AbstractC4442bk.f19091f, baseRequest.AppId);
        return hashMap;
    }

    public Map<String, String> toMapPramesByNoEnc(BaseRequestInfo baseRequestInfo) throws Exception {
        this.Data = baseRequestInfo.getJson();
        HashMap hashMap = new HashMap();
        hashMap.put("Data", this.Data);
        hashMap.put("signType", "" + baseRequestInfo.signType);
        hashMap.put(AbstractC4442bk.f19091f, baseRequestInfo.AppId);
        return hashMap;
    }

    private int getRandomInt() {
        return new Random().nextInt(8);
    }

    private String getSign(String str, int i) throws Exception {
        String a = C1118a.m21980a(str, i);
        return TextUtils.isEmpty(a) ? "" : a;
    }
}
