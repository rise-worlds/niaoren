package com.nrzs.data.xnkj.bean.request;

import com.nrzs.data.DataApp;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import org.json.JSONObject;
import p110z1.DesManager;

/* loaded from: classes2.dex */
public class XJBaseRequestValue {
    public String appId = DataApp.m18939d().f10598a;
    public String appVersionNum = DataApp.m18939d().f10599b;
    public long appVersionCode = DataApp.m18939d().f10600c;
    public String channelName = DataApp.m18939d().f10601d;
    public String appSigner = DataApp.m18939d().f10602e;
    public String appPackageName = DataApp.m18939d().f10603f;
    public String imei = DataApp.m18939d().m18941c();

    public String getJson() throws Exception {
        return DesManager.m12646b().m12647a(getJson(getClass(), new JSONObject()));
    }

    private String getJson(Class cls, JSONObject jSONObject) throws Exception {
        Field[] fields;
        for (Field field : cls.getFields()) {
            if (!Modifier.isStatic(field.getModifiers())) {
                jSONObject.put(field.getName(), field.get(this));
            }
        }
        if (cls.getSuperclass() == null || cls.getSuperclass() == Object.class) {
            return jSONObject.toString();
        }
        return getJson(cls.getSuperclass(), jSONObject);
    }
}
