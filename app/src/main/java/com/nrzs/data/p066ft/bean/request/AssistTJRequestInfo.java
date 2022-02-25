package com.nrzs.data.p066ft.bean.request;

import android.os.Build;
import com.nrzs.data.base.BaseRequest;
import p110z1.DevUtil;

/* renamed from: com.nrzs.data.ft.bean.request.AssistTJRequestInfo */
/* loaded from: classes2.dex */
public class AssistTJRequestInfo extends BaseRequest {
    public String Key;
    public String OnlyID;
    public long SID;
    public long ScriptAuthorID;
    public long ScriptID;
    public String ScriptName;
    public String ScriptVersion;
    public int StartOrStop;
    public long TopicID;
    public long UserID;
    public String DeviceModel = DevUtil.m12534a();
    public String DeviceVendor = Build.BRAND;
    public String AndroidVerdion = Build.VERSION.SDK_INT + "";
}
