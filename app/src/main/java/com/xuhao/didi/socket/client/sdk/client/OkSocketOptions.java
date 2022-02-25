package com.xuhao.didi.socket.client.sdk.client;

import com.xuhao.didi.p082a.p084b.p085a.IIOCoreOptions;
import com.xuhao.didi.p082a.p087d.IReaderProtocol;
import com.xuhao.didi.socket.client.impl.client.action.ActionDispatcher;
import com.xuhao.didi.socket.client.sdk.client.connection.AbsReconnectionManager;
import com.xuhao.didi.socket.client.sdk.client.connection.DefaultReconnectManager;
import com.xuhao.didi.socket.client.sdk.client.connection.p102a.IConfiguration;
import com.xuhao.didi.socket.p089a.p090a.p096c.DefaultNormalReaderProtocol;
import java.nio.ByteOrder;

/* loaded from: classes2.dex */
public class OkSocketOptions implements IIOCoreOptions {

    /* renamed from: a */
    private static boolean f14440a;

    /* renamed from: b */
    private IOThreadMode f14441b;

    /* renamed from: c */
    private boolean f14442c;

    /* renamed from: d */
    private ByteOrder f14443d;

    /* renamed from: e */
    private ByteOrder f14444e;

    /* renamed from: f */
    private IReaderProtocol f14445f;

    /* renamed from: g */
    private int f14446g;

    /* renamed from: h */
    private int f14447h;

    /* renamed from: i */
    private long f14448i;

    /* renamed from: j */
    private int f14449j;

    /* renamed from: k */
    private int f14450k;

    /* renamed from: l */
    private int f14451l;

    /* renamed from: m */
    private AbsReconnectionManager f14452m;

    /* renamed from: n */
    private OkSocketSSLConfig f14453n;

    /* renamed from: o */
    private OkSocketFactory f14454o;

    /* renamed from: p */
    private boolean f14455p;

    /* renamed from: q */
    private ThreadModeToken f14456q;

    /* loaded from: classes2.dex */
    public enum IOThreadMode {
        SIMPLEX,
        DUPLEX
    }

    /* loaded from: classes2.dex */
    public static abstract class ThreadModeToken {
        public abstract void handleCallbackEvent(ActionDispatcher.ActionRunnable actionRunnable);
    }

    private OkSocketOptions() {
    }

    /* renamed from: a */
    public static void m15053a(boolean z) {
        f14440a = z;
    }

    /* loaded from: classes2.dex */
    public static class Builder {

        /* renamed from: a */
        private OkSocketOptions f14457a;

        public Builder() {
            this(OkSocketOptions.m15030r());
        }

        public Builder(IConfiguration aVar) {
            this(aVar.mo14977e());
        }

        public Builder(OkSocketOptions okSocketOptions) {
            this.f14457a = okSocketOptions;
        }

        public Builder setIOThreadMode(IOThreadMode iOThreadMode) {
            this.f14457a.f14441b = iOThreadMode;
            return this;
        }

        public Builder setMaxReadDataMB(int i) {
            this.f14457a.f14451l = i;
            return this;
        }

        public Builder setSSLConfig(OkSocketSSLConfig okSocketSSLConfig) {
            this.f14457a.f14453n = okSocketSSLConfig;
            return this;
        }

        public Builder setReaderProtocol(IReaderProtocol aVar) {
            this.f14457a.f14445f = aVar;
            return this;
        }

        public Builder setPulseFrequency(long j) {
            this.f14457a.f14448i = j;
            return this;
        }

        public Builder setConnectionHolden(boolean z) {
            this.f14457a.f14442c = z;
            return this;
        }

        public Builder setPulseFeedLoseTimes(int i) {
            this.f14457a.f14449j = i;
            return this;
        }

        public Builder setWriteOrder(ByteOrder byteOrder) {
            setWriteByteOrder(byteOrder);
            return this;
        }

        public Builder setWriteByteOrder(ByteOrder byteOrder) {
            this.f14457a.f14443d = byteOrder;
            return this;
        }

        public Builder setReadByteOrder(ByteOrder byteOrder) {
            this.f14457a.f14444e = byteOrder;
            return this;
        }

        public Builder setWritePackageBytes(int i) {
            this.f14457a.f14446g = i;
            return this;
        }

        public Builder setReadPackageBytes(int i) {
            this.f14457a.f14447h = i;
            return this;
        }

        public Builder setConnectTimeoutSecond(int i) {
            this.f14457a.f14450k = i;
            return this;
        }

        public Builder setReconnectionManager(AbsReconnectionManager aVar) {
            this.f14457a.f14452m = aVar;
            return this;
        }

        public Builder setSocketFactory(OkSocketFactory bVar) {
            this.f14457a.f14454o = bVar;
            return this;
        }

        public Builder setCallbackThreadModeToken(ThreadModeToken threadModeToken) {
            this.f14457a.f14456q = threadModeToken;
            return this;
        }

        public Builder setCallbackInIndependentThread(boolean z) {
            this.f14457a.f14455p = z;
            return this;
        }

        public OkSocketOptions build() {
            return this.f14457a;
        }
    }

    /* renamed from: h */
    public IOThreadMode m15040h() {
        return this.f14441b;
    }

    /* renamed from: i */
    public long m15039i() {
        return this.f14448i;
    }

    /* renamed from: j */
    public OkSocketSSLConfig m15038j() {
        return this.f14453n;
    }

    /* renamed from: k */
    public OkSocketFactory m15037k() {
        return this.f14454o;
    }

    /* renamed from: l */
    public int m15036l() {
        return this.f14450k;
    }

    /* renamed from: m */
    public boolean m15035m() {
        return this.f14442c;
    }

    /* renamed from: n */
    public int m15034n() {
        return this.f14449j;
    }

    /* renamed from: o */
    public AbsReconnectionManager m15033o() {
        return this.f14452m;
    }

    @Override // com.xuhao.didi.p082a.p084b.p085a.IIOCoreOptions
    /* renamed from: g */
    public boolean mo15041g() {
        return f14440a;
    }

    @Override // com.xuhao.didi.p082a.p084b.p085a.IIOCoreOptions
    /* renamed from: f */
    public int mo15042f() {
        return this.f14446g;
    }

    @Override // com.xuhao.didi.p082a.p084b.p085a.IIOCoreOptions
    /* renamed from: e */
    public int mo15044e() {
        return this.f14447h;
    }

    @Override // com.xuhao.didi.p082a.p084b.p085a.IIOCoreOptions
    /* renamed from: d */
    public ByteOrder mo15046d() {
        return this.f14443d;
    }

    @Override // com.xuhao.didi.p082a.p084b.p085a.IIOCoreOptions
    /* renamed from: c */
    public IReaderProtocol mo15048c() {
        return this.f14445f;
    }

    @Override // com.xuhao.didi.p082a.p084b.p085a.IIOCoreOptions
    /* renamed from: b */
    public int mo15052b() {
        return this.f14451l;
    }

    @Override // com.xuhao.didi.p082a.p084b.p085a.IIOCoreOptions
    /* renamed from: a */
    public ByteOrder mo15064a() {
        return this.f14444e;
    }

    /* renamed from: p */
    public ThreadModeToken m15032p() {
        return this.f14456q;
    }

    /* renamed from: q */
    public boolean m15031q() {
        return this.f14455p;
    }

    /* renamed from: r */
    public static OkSocketOptions m15030r() {
        OkSocketOptions okSocketOptions = new OkSocketOptions();
        okSocketOptions.f14448i = 5000L;
        okSocketOptions.f14441b = IOThreadMode.DUPLEX;
        okSocketOptions.f14445f = new DefaultNormalReaderProtocol();
        okSocketOptions.f14451l = 5;
        okSocketOptions.f14450k = 3;
        okSocketOptions.f14446g = 100;
        okSocketOptions.f14447h = 50;
        okSocketOptions.f14444e = ByteOrder.LITTLE_ENDIAN;
        okSocketOptions.f14443d = ByteOrder.LITTLE_ENDIAN;
        okSocketOptions.f14442c = true;
        okSocketOptions.f14449j = 5;
        okSocketOptions.f14452m = new DefaultReconnectManager();
        okSocketOptions.f14453n = null;
        okSocketOptions.f14454o = null;
        okSocketOptions.f14455p = true;
        okSocketOptions.f14456q = null;
        return okSocketOptions;
    }
}
