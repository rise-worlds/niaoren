package com.nrzs.data.user.bean.request;

import com.nrzs.data.base.BaseRequest;
import p110z1.Config;

/* loaded from: classes2.dex */
public class LoginOutReg extends BaseRequest {
    public String DeviceCode = Config.m12527a();
    public String SessionId;
    public long UserId;
}
