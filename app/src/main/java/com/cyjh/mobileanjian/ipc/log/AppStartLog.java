package com.cyjh.mobileanjian.ipc.log;

import android.content.Context;
import com.cyjh.ddy.media.p035a.ResultTypeConstant;
import com.cyjh.ddysdk.device.base.constants.DdyConstants;
import java.io.File;
import org.json.JSONException;
import org.json.JSONObject;
import p110z1.AbstractC4442bk;

/* loaded from: classes.dex */
public class AppStartLog extends BaseLog {

    /* renamed from: d */
    private static final String f8273d = "SetOperationLog";

    /* renamed from: e */
    private static final int f8274e = 3;

    public AppStartLog(Context context) {
        super(context);
    }

    @Override // com.cyjh.mobileanjian.ipc.log.BaseLog
    /* renamed from: a */
    protected final void mo20988a() {
        this.f8284c = "http://api4.mobileanjian.com/api" + File.separator + f8273d;
    }

    @Override // com.cyjh.mobileanjian.ipc.log.BaseLog
    /* renamed from: b */
    protected final String mo20987b() {
        MetaData a = MetaData.m20986a();
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("PhoneModel", a.f8288a);
            jSONObject2.put("MACMD5", a.f8290c);
            jSONObject2.put("AndroidVersion", a.f8289b);
            jSONObject2.put("ROM", a.f8291d);
            jSONObject2.put("PackageName", a.f8293f);
            jSONObject2.put("IsNew", a.f8299l ? "1" : ResultTypeConstant.f7213z);
            jSONObject2.put("AppID", a.f8295h);
            jSONObject2.put("AppVersion", a.f8297j);
            jSONObject2.put(AbstractC4442bk.f19090e, a.f8298k);
            jSONObject2.put("IsFree", a.f8296i ? "1" : ResultTypeConstant.f7213z);
            jSONObject2.put("UsedTime", ResultTypeConstant.f7213z);
            jSONObject.put("LogType", DdyConstants.APP_INSTALL_ERROR);
            jSONObject.put("Data", jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }
}
