package p110z1;

import java.net.ProtocolException;

/* renamed from: z1.agr */
/* loaded from: classes3.dex */
public class ConnectionProfile {

    /* renamed from: a */
    static final int f15681a = -1;

    /* renamed from: b */
    final long f15682b;

    /* renamed from: c */
    final long f15683c;

    /* renamed from: d */
    final long f15684d;

    /* renamed from: e */
    final long f15685e;

    /* renamed from: f */
    private final boolean f15686f;

    /* renamed from: g */
    private final boolean f15687g;

    private ConnectionProfile() {
        this.f15682b = 0L;
        this.f15683c = 0L;
        this.f15684d = 0L;
        this.f15685e = 0L;
        this.f15686f = false;
        this.f15687g = true;
    }

    private ConnectionProfile(long j, long j2, long j3, long j4) {
        this(j, j2, j3, j4, false);
    }

    private ConnectionProfile(long j, long j2, long j3, long j4, boolean z) {
        if (!(j == 0 && j3 == 0) && z) {
            throw new IllegalArgumentException();
        }
        this.f15682b = j;
        this.f15683c = j2;
        this.f15684d = j3;
        this.f15685e = j4;
        this.f15686f = z;
        this.f15687g = false;
    }

    /* renamed from: a */
    public void m13421a(FileDownloadConnection agiVar) throws ProtocolException {
        if (!this.f15686f) {
            if (this.f15687g && FileDownloadProperties.m13208a().f15864h) {
                agiVar.mo13494b("HEAD");
            }
            agiVar.mo13496a("Range", this.f15684d == -1 ? FileDownloadUtils.m13182a("bytes=%d-", Long.valueOf(this.f15683c)) : FileDownloadUtils.m13182a("bytes=%d-%d", Long.valueOf(this.f15683c), Long.valueOf(this.f15684d)));
        }
    }

    public String toString() {
        return FileDownloadUtils.m13182a("range[%d, %d) current offset[%d]", Long.valueOf(this.f15682b), Long.valueOf(this.f15684d), Long.valueOf(this.f15683c));
    }

    /* compiled from: ConnectionProfile.java */
    /* renamed from: z1.agr$a */
    /* loaded from: classes3.dex */
    public static class C3456a {
        /* renamed from: a */
        public static ConnectionProfile m13420a() {
            return new ConnectionProfile();
        }

        /* renamed from: b */
        public static ConnectionProfile m13416b() {
            return new ConnectionProfile(0L, 0L, 0L, 0L, true);
        }

        /* renamed from: a */
        public static ConnectionProfile m13419a(long j) {
            return new ConnectionProfile(0L, 0L, -1L, j);
        }

        /* renamed from: a */
        public static ConnectionProfile m13418a(long j, long j2, long j3) {
            return new ConnectionProfile(j, j2, -1L, j3);
        }

        /* renamed from: a */
        public static ConnectionProfile m13417a(long j, long j2, long j3, long j4) {
            return new ConnectionProfile(j, j2, j3, j4);
        }
    }
}
