package com.cyjh.ddy.thirdlib.lib_hwobs;

/* loaded from: classes.dex */
public class ObsCert {

    /* renamed from: ak */
    public String f7557ak;
    public String bucketName;
    public String endPoint;
    public String securityToken;

    /* renamed from: sk */
    public String f7558sk;

    public ObsCert() {
    }

    public ObsCert(String str, String str2, String str3, String str4) {
        this(str, str2, str3, str4, null);
    }

    public ObsCert(String str, String str2, String str3, String str4, String str5) {
        this.endPoint = str;
        this.bucketName = str2;
        this.f7557ak = str3;
        this.f7558sk = str4;
        this.securityToken = str5;
    }
}
