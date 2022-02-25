package p110z1;

import com.liulishuo.filedownloader.model.ConnectionModel;
import com.liulishuo.filedownloader.model.FileDownloadHeader;
import p110z1.ConnectTask;

/* renamed from: z1.agu */
/* loaded from: classes3.dex */
public class DownloadRunnable implements Runnable {

    /* renamed from: a */
    final int f15733a;

    /* renamed from: b */
    private final ConnectTask f15734b;

    /* renamed from: c */
    private final ProcessCallback f15735c;

    /* renamed from: d */
    private final String f15736d;

    /* renamed from: e */
    private final boolean f15737e;

    /* renamed from: f */
    private FetchDataTask f15738f;

    /* renamed from: g */
    private volatile boolean f15739g;

    /* renamed from: h */
    private final int f15740h;

    private DownloadRunnable(int i, int i2, ConnectTask agqVar, ProcessCallback agxVar, boolean z, String str) {
        this.f15740h = i;
        this.f15733a = i2;
        this.f15739g = false;
        this.f15735c = agxVar;
        this.f15736d = str;
        this.f15734b = agqVar;
        this.f15737e = z;
    }

    /* renamed from: a */
    public void m13372a() {
        this.f15739g = true;
        FetchDataTask agwVar = this.f15738f;
        if (agwVar != null) {
            agwVar.m13328a();
        }
    }

    /* renamed from: b */
    public void m13371b() {
        m13372a();
    }

    /* JADX WARN: Removed duplicated region for block: B:60:0x0144  */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void run() {
        /*
            Method dump skipped, instructions count: 328
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.DownloadRunnable.run():void");
    }

    /* renamed from: c */
    private long m13370c() {
        FileDownloadDatabase c = CustomComponentHolder.m13415a().m13408c();
        if (this.f15733a < 0) {
            return c.mo13453b(this.f15740h).m19118g();
        }
        for (ConnectionModel aVar : c.mo13448c(this.f15740h)) {
            if (aVar.m19089b() == this.f15733a) {
                return aVar.m19084d();
            }
        }
        return 0L;
    }

    /* compiled from: DownloadRunnable.java */
    /* renamed from: z1.agu$a */
    /* loaded from: classes3.dex */
    public static class C3463a {

        /* renamed from: a */
        private final ConnectTask.C3453a f15741a = new ConnectTask.C3453a();

        /* renamed from: b */
        private ProcessCallback f15742b;

        /* renamed from: c */
        private String f15743c;

        /* renamed from: d */
        private Boolean f15744d;

        /* renamed from: e */
        private Integer f15745e;

        /* renamed from: a */
        public C3463a m13362a(ProcessCallback agxVar) {
            this.f15742b = agxVar;
            return this;
        }

        /* renamed from: a */
        public C3463a m13368a(int i) {
            this.f15741a.m13426a(i);
            return this;
        }

        /* renamed from: a */
        public C3463a m13365a(String str) {
            this.f15741a.m13424a(str);
            return this;
        }

        /* renamed from: b */
        public C3463a m13360b(String str) {
            this.f15741a.m13422b(str);
            return this;
        }

        /* renamed from: a */
        public C3463a m13367a(FileDownloadHeader fileDownloadHeader) {
            this.f15741a.m13425a(fileDownloadHeader);
            return this;
        }

        /* renamed from: a */
        public C3463a m13363a(ConnectionProfile agrVar) {
            this.f15741a.m13423a(agrVar);
            return this;
        }

        /* renamed from: c */
        public C3463a m13359c(String str) {
            this.f15743c = str;
            return this;
        }

        /* renamed from: a */
        public C3463a m13361a(boolean z) {
            this.f15744d = Boolean.valueOf(z);
            return this;
        }

        /* renamed from: a */
        public C3463a m13366a(Integer num) {
            this.f15745e = num;
            return this;
        }

        /* renamed from: a */
        public DownloadRunnable m13369a() {
            if (this.f15742b == null || this.f15743c == null || this.f15744d == null || this.f15745e == null) {
                throw new IllegalArgumentException(FileDownloadUtils.m13182a("%s %s %B", this.f15742b, this.f15743c, this.f15744d));
            }
            ConnectTask a = this.f15741a.m13427a();
            return new DownloadRunnable(a.f15669a, this.f15745e.intValue(), a, this.f15742b, this.f15744d.booleanValue(), this.f15743c);
        }

        /* renamed from: a */
        DownloadRunnable m13364a(ConnectTask agqVar) {
            return new DownloadRunnable(agqVar.f15669a, 0, agqVar, this.f15742b, false, "");
        }
    }
}
