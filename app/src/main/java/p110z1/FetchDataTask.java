package p110z1;

import android.os.SystemClock;
import java.io.IOException;

/* renamed from: z1.agw */
/* loaded from: classes3.dex */
public class FetchDataTask {

    /* renamed from: a */
    static final int f15769a = 4096;

    /* renamed from: b */
    long f15770b;

    /* renamed from: c */
    private final ProcessCallback f15771c;

    /* renamed from: d */
    private final int f15772d;

    /* renamed from: e */
    private final int f15773e;

    /* renamed from: f */
    private final DownloadRunnable f15774f;

    /* renamed from: g */
    private final FileDownloadConnection f15775g;

    /* renamed from: h */
    private final boolean f15776h;

    /* renamed from: i */
    private final long f15777i;

    /* renamed from: j */
    private final long f15778j;

    /* renamed from: k */
    private final long f15779k;

    /* renamed from: l */
    private final String f15780l;

    /* renamed from: m */
    private FileDownloadOutputStream f15781m;

    /* renamed from: n */
    private volatile boolean f15782n;

    /* renamed from: o */
    private final FileDownloadDatabase f15783o;

    /* renamed from: p */
    private volatile long f15784p;

    /* renamed from: q */
    private volatile long f15785q;

    /* renamed from: a */
    public void m13328a() {
        this.f15782n = true;
    }

    private FetchDataTask(FileDownloadConnection agiVar, ConnectionProfile agrVar, DownloadRunnable aguVar, int i, int i2, boolean z, ProcessCallback agxVar, String str) {
        this.f15784p = 0L;
        this.f15785q = 0L;
        this.f15771c = agxVar;
        this.f15780l = str;
        this.f15775g = agiVar;
        this.f15776h = z;
        this.f15774f = aguVar;
        this.f15773e = i2;
        this.f15772d = i;
        this.f15783o = CustomComponentHolder.m13415a().m13408c();
        this.f15777i = agrVar.f15682b;
        this.f15778j = agrVar.f15684d;
        this.f15770b = agrVar.f15683c;
        this.f15779k = agrVar.f15685e;
    }

    /* JADX WARN: Code restructure failed: missing block: B:109:0x01f9, code lost:
        throw new p110z1.FileDownloadNetworkPolicyException();
     */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void m13327b() throws java.io.IOException, java.lang.IllegalAccessException, java.lang.IllegalArgumentException, p110z1.FileDownloadGiveUpRetryException {
        /*
            Method dump skipped, instructions count: 589
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.FetchDataTask.m13327b():void");
    }

    /* renamed from: c */
    private void m13326c() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (FileDownloadUtils.m13188a(this.f15770b - this.f15784p, elapsedRealtime - this.f15785q)) {
            m13325d();
            this.f15784p = this.f15770b;
            this.f15785q = elapsedRealtime;
        }
    }

    /* renamed from: d */
    private void m13325d() {
        boolean z;
        long uptimeMillis = SystemClock.uptimeMillis();
        try {
            this.f15781m.mo13251a();
            z = true;
        } catch (IOException e) {
            if (FileDownloadLog.f15845a) {
                FileDownloadLog.m13211c(this, "Because of the system cannot guarantee that all the buffers have been synchronized with physical media, or write to filefailed, we just not flushAndSync process to database too %s", e);
            }
            z = false;
        }
        if (z) {
            if (this.f15773e >= 0) {
                this.f15783o.mo13466a(this.f15772d, this.f15773e, this.f15770b);
            } else {
                this.f15771c.mo13311c();
            }
            if (FileDownloadLog.f15845a) {
                FileDownloadLog.m13211c(this, "require flushAndSync id[%d] index[%d] offset[%d], consume[%d]", Integer.valueOf(this.f15772d), Integer.valueOf(this.f15773e), Long.valueOf(this.f15770b), Long.valueOf(SystemClock.uptimeMillis() - uptimeMillis));
            }
        }
    }

    /* compiled from: FetchDataTask.java */
    /* renamed from: z1.agw$a */
    /* loaded from: classes3.dex */
    public static class C3466a {

        /* renamed from: a */
        DownloadRunnable f15786a;

        /* renamed from: b */
        FileDownloadConnection f15787b;

        /* renamed from: c */
        ConnectionProfile f15788c;

        /* renamed from: d */
        ProcessCallback f15789d;

        /* renamed from: e */
        String f15790e;

        /* renamed from: f */
        Boolean f15791f;

        /* renamed from: g */
        Integer f15792g;

        /* renamed from: h */
        Integer f15793h;

        /* renamed from: a */
        public C3466a m13321a(FileDownloadConnection agiVar) {
            this.f15787b = agiVar;
            return this;
        }

        /* renamed from: a */
        public C3466a m13320a(ConnectionProfile agrVar) {
            this.f15788c = agrVar;
            return this;
        }

        /* renamed from: a */
        public C3466a m13318a(ProcessCallback agxVar) {
            this.f15789d = agxVar;
            return this;
        }

        /* renamed from: a */
        public C3466a m13322a(String str) {
            this.f15790e = str;
            return this;
        }

        /* renamed from: a */
        public C3466a m13317a(boolean z) {
            this.f15791f = Boolean.valueOf(z);
            return this;
        }

        /* renamed from: a */
        public C3466a m13319a(DownloadRunnable aguVar) {
            this.f15786a = aguVar;
            return this;
        }

        /* renamed from: a */
        public C3466a m13323a(int i) {
            this.f15792g = Integer.valueOf(i);
            return this;
        }

        /* renamed from: b */
        public C3466a m13316b(int i) {
            this.f15793h = Integer.valueOf(i);
            return this;
        }

        /* renamed from: a */
        public FetchDataTask m13324a() throws IllegalArgumentException {
            FileDownloadConnection agiVar;
            ConnectionProfile agrVar;
            Integer num;
            if (this.f15791f != null && (agiVar = this.f15787b) != null && (agrVar = this.f15788c) != null && this.f15789d != null && this.f15790e != null && (num = this.f15793h) != null && this.f15792g != null) {
                return new FetchDataTask(agiVar, agrVar, this.f15786a, num.intValue(), this.f15792g.intValue(), this.f15791f.booleanValue(), this.f15789d, this.f15790e);
            }
            throw new IllegalArgumentException();
        }
    }
}
