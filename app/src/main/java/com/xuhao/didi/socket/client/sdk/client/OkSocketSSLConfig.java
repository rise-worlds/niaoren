package com.xuhao.didi.socket.client.sdk.client;

import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

/* loaded from: classes2.dex */
public class OkSocketSSLConfig {

    /* renamed from: a */
    private String f14459a;

    /* renamed from: b */
    private TrustManager[] f14460b;

    /* renamed from: c */
    private KeyManager[] f14461c;

    /* renamed from: d */
    private SSLSocketFactory f14462d;

    private OkSocketSSLConfig() {
    }

    /* loaded from: classes2.dex */
    public static class Builder {

        /* renamed from: a */
        private OkSocketSSLConfig f14463a = new OkSocketSSLConfig();

        public Builder setProtocol(String str) {
            this.f14463a.f14459a = str;
            return this;
        }

        public Builder setTrustManagers(TrustManager[] trustManagerArr) {
            this.f14463a.f14460b = trustManagerArr;
            return this;
        }

        public Builder setKeyManagers(KeyManager[] keyManagerArr) {
            this.f14463a.f14461c = keyManagerArr;
            return this;
        }

        public Builder setCustomSSLFactory(SSLSocketFactory sSLSocketFactory) {
            this.f14463a.f14462d = sSLSocketFactory;
            return this;
        }

        public OkSocketSSLConfig build() {
            return this.f14463a;
        }
    }

    /* renamed from: a */
    public KeyManager[] m15029a() {
        return this.f14461c;
    }

    /* renamed from: b */
    public String m15024b() {
        return this.f14459a;
    }

    /* renamed from: c */
    public TrustManager[] m15023c() {
        return this.f14460b;
    }

    /* renamed from: d */
    public SSLSocketFactory m15022d() {
        return this.f14462d;
    }
}
