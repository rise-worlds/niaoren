package com.cyjh.mobileanjian.ipc.log;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.cyjh.ddy.media.p035a.ResultTypeConstant;
import com.cyjh.mobileanjian.ipc.stuff.MqmCode;
import java.io.File;
import org.json.JSONException;
import org.json.JSONObject;
import p110z1.AbstractC4442bk;

/* loaded from: classes.dex */
public class ScriptUnexpectedStopLog extends BaseLog {

    /* renamed from: d */
    private static final String f8279d = "SetErrorLog";

    /* renamed from: e */
    private int f8280e;

    /* renamed from: f */
    private String f8281f;

    public ScriptUnexpectedStopLog(Context context) {
        super(context);
    }

    public ScriptUnexpectedStopLog setMqmCode(int i) {
        this.f8280e = i;
        return this;
    }

    public ScriptUnexpectedStopLog setExtraLog(String str) {
        this.f8281f = str;
        return this;
    }

    @Override // com.cyjh.mobileanjian.ipc.log.BaseLog
    /* renamed from: a */
    protected final void mo20988a() {
        this.f8284c = "http://api4.mobileanjian.com/api" + File.separator + f8279d;
    }

    @Override // com.cyjh.mobileanjian.ipc.log.BaseLog
    /* renamed from: b */
    protected final String mo20987b() {
        JSONObject jSONObject;
        NetworkInfo activeNetworkInfo;
        String str;
        MetaData a = MetaData.m20986a();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject = new JSONObject();
            jSONObject.put("PhoneModel", a.f8288a);
            jSONObject.put("MACMD5", a.f8290c);
            jSONObject.put("AndroidVersion", a.f8289b);
            jSONObject.put("ROM", a.f8291d);
            jSONObject.put("PackageName", a.f8293f);
            jSONObject.put("AppID", a.f8295h);
            jSONObject.put("AppVersion", a.f8297j);
            jSONObject.put(AbstractC4442bk.f19090e, a.f8298k);
            jSONObject.put("IsFree", a.f8296i ? "1" : ResultTypeConstant.f7213z);
            activeNetworkInfo = ((ConnectivityManager) this.f8283b.getSystemService("connectivity")).getActiveNetworkInfo();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            switch (activeNetworkInfo.getType()) {
                case 0:
                    str = "MOBILE";
                    break;
                case 1:
                    str = "WIFI";
                    break;
                default:
                    str = "UNKNOWN";
                    break;
            }
            jSONObject.put("NetworkType", str);
            jSONObject.put("MqmCode", String.valueOf(this.f8280e));
            jSONObject.put("MqmMessage", MqmCode.getMessageFromCode(this.f8280e));
            jSONObject.put("ExtraMessage", this.f8281f);
            jSONObject2.put("Data", jSONObject);
            return jSONObject2.toString();
        }
        str = "No Network";
        jSONObject.put("NetworkType", str);
        jSONObject.put("MqmCode", String.valueOf(this.f8280e));
        jSONObject.put("MqmMessage", MqmCode.getMessageFromCode(this.f8280e));
        jSONObject.put("ExtraMessage", this.f8281f);
        jSONObject2.put("Data", jSONObject);
        return jSONObject2.toString();
    }

    /* renamed from: a */
    private static String m20989a(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            return "No Network";
        }
        switch (activeNetworkInfo.getType()) {
            case 0:
                return "MOBILE";
            case 1:
                return "WIFI";
            default:
                return "UNKNOWN";
        }
    }
}
