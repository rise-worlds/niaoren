package com.redwas.bean;

import com.nrzs.data.base.BaseRequest;
import p110z1.LoginManager;

/* loaded from: classes2.dex */
public class RedPacket extends BaseRequest {
    public String DeviceCode;
    public String Money;
    public int PacketType;
    public long UserId = LoginManager.m12620d().m12614j();
    public String UserName = LoginManager.m12620d().m12615i();
}
