package com.cyjh.ddy.net.bean.base;

import android.text.TextUtils;
import com.cyjh.ddy.p032a.C1118a;
import p110z1.C4963cj;
import p110z1.SerializedName;

/* loaded from: classes.dex */
public class BaseDataResult {
    @SerializedName(m1427a = "Code", m1426b = {"code"})
    public Integer Code;
    @SerializedName(m1427a = "Data", m1426b = {"data"})
    public String Data;
    @SerializedName(m1427a = "Msg", m1426b = {"msg"})
    public String Msg;

    public void setData() {
        if (TextUtils.isEmpty(this.Data)) {
            this.Data = "null";
            return;
        }
        try {
            this.Data = C1118a.m21975b(this.Data);
        } catch (Exception unused) {
            this.Data = "null";
        }
    }

    public String getJson() {
        return "{\"Msg\":\"" + this.Msg + "\",\"Code\":" + this.Code + ",\"Data\":" + this.Data + C4963cj.f20747d;
    }
}
