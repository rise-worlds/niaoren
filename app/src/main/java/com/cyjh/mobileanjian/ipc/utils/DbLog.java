package com.cyjh.mobileanjian.ipc.utils;

import android.content.Context;
import android.os.SystemClock;
import com.cyjh.ddy.media.p035a.ResultTypeConstant;
import com.cyjh.ddysdk.device.base.constants.DdyConstants;
import com.cyjh.mobileanjian.ipc.stuff.AppAttr;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.client.HttpRequest;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.cyjh.mobileanjian.ipc.utils.e */
/* loaded from: classes.dex */
public final class DbLog {

    /* renamed from: a */
    private static final String f8680a = "http://logapi4.mobileanjian.com/api/SetLog";

    /* renamed from: b */
    private static final int f8681b = 4;

    /* renamed from: c */
    private static final String f8682c = "RunScriptDurationApi";

    /* renamed from: d */
    private static String f8683d;

    /* renamed from: a */
    private static String m20653a(Context context, long... jArr) {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("AppID", AppAttr.m20920c());
            jSONObject2.put("MachineCode", UniqIdUtil.m20613a(context));
            jSONObject2.put("AppVersion", AppAttr.m20923b());
            jSONObject2.put("IsFree", AppAttr.m20927a() ? "1" : ResultTypeConstant.f7213z);
            jSONObject2.put("UsedTime", String.valueOf((SystemClock.uptimeMillis() - jArr[0]) / 1000));
            jSONObject.put("LogType", DdyConstants.APP_INSTALL_DOWNLOADING);
            jSONObject.put("Data", jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }

    /* renamed from: a */
    private static void m20654a(Context context, long j) {
        f8683d = f8680a;
        new StringBuilder("log uri: ").append(f8683d);
        HttpUtils httpUtils = new HttpUtils();
        RequestParams requestParams = new RequestParams();
        requestParams.addQueryStringParameter("Data", m20653a(context, j));
        httpUtils.send(HttpRequest.HttpMethod.GET, f8683d, requestParams, null);
    }
}
