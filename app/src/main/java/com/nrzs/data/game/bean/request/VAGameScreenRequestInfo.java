package com.nrzs.data.game.bean.request;

import android.os.Build;
import com.nrzs.data.base.BaseRequest;
import p110z1.DevUtil;

/* loaded from: classes2.dex */
public class VAGameScreenRequestInfo extends BaseRequest {
    public String gamePackageName;
    public String deviceModel = DevUtil.m12533b();
    public String deviceVendor = Build.MANUFACTURER;
    public String sysVersion = Build.BRAND;
}
