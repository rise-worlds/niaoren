package com.cyjh.ddysdk.ddyobs.bean.response;

/* loaded from: classes.dex */
public class CreatCertResponse {
    public String BucketName;
    public String EndPoint;
    public TokenBean Token;

    /* loaded from: classes.dex */
    public static class TokenBean {
        public String Access;
        public long ExpireSecond;
        public String Expires;
        public String Expires_at;
        public String Secret;
        public String Securitytoken;
    }
}
