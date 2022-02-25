package p110z1;

import android.database.sqlite.SQLiteFullException;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.SystemClock;
import android.support.p003v4.media.session.PlaybackStateCompat;
import com.liulishuo.filedownloader.message.MessageSnapshotFlow;
import com.liulishuo.filedownloader.message.MessageSnapshotTaker;
import com.liulishuo.filedownloader.model.FileDownloadModel;
import com.liulishuo.filedownloader.services.FileDownloadBroadcastHandler;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.LockSupport;

/* renamed from: z1.agv */
/* loaded from: classes3.dex */
public class DownloadStatusCallback implements Handler.Callback {

    /* renamed from: e */
    private static final int f15746e = 1;

    /* renamed from: f */
    private static final int f15747f = 5;

    /* renamed from: g */
    private static final int f15748g = -1;

    /* renamed from: r */
    private static final String f15749r = "require callback %d but the host thread of the flow has already dead, what is occurred because of there are several reason can final this flow on different thread.";

    /* renamed from: a */
    private final FileDownloadModel f15750a;

    /* renamed from: c */
    private final C3464a f15752c;

    /* renamed from: d */
    private final int f15753d;

    /* renamed from: h */
    private final int f15754h;

    /* renamed from: i */
    private final int f15755i;

    /* renamed from: j */
    private long f15756j;

    /* renamed from: k */
    private Handler f15757k;

    /* renamed from: l */
    private HandlerThread f15758l;

    /* renamed from: n */
    private volatile Thread f15760n;

    /* renamed from: m */
    private volatile boolean f15759m = false;

    /* renamed from: o */
    private volatile long f15761o = 0;

    /* renamed from: p */
    private final AtomicLong f15762p = new AtomicLong();

    /* renamed from: q */
    private final AtomicBoolean f15763q = new AtomicBoolean(false);

    /* renamed from: s */
    private final AtomicBoolean f15764s = new AtomicBoolean(false);

    /* renamed from: t */
    private final AtomicBoolean f15765t = new AtomicBoolean(true);

    /* renamed from: b */
    private final FileDownloadDatabase f15751b = CustomComponentHolder.m13415a().m13408c();

    /* JADX INFO: Access modifiers changed from: package-private */
    public DownloadStatusCallback(FileDownloadModel fileDownloadModel, int i, int i2, int i3) {
        this.f15750a = fileDownloadModel;
        this.f15754h = i2 >= 5 ? i2 : 5;
        this.f15755i = i3;
        this.f15752c = new C3464a();
        this.f15753d = i;
    }

    /* renamed from: a */
    public boolean m13358a() {
        HandlerThread handlerThread = this.f15758l;
        return handlerThread != null && handlerThread.isAlive();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public void m13349b() {
        Handler handler = this.f15757k;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.f15758l.quit();
            this.f15760n = Thread.currentThread();
            while (this.f15759m) {
                LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(100L));
            }
            this.f15760n = null;
        }
    }

    /* renamed from: c */
    public void m13345c() {
        this.f15750a.m19134a((byte) 1);
        this.f15751b.mo13444f(this.f15750a.m19135a());
        m13357a((byte) 1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: d */
    public void m13343d() {
        this.f15750a.m19134a((byte) 6);
        m13357a((byte) 6);
        this.f15751b.mo13468a(this.f15750a.m19135a());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m13350a(boolean z, long j, String str, String str2) throws IllegalArgumentException {
        String j2 = this.f15750a.m19115j();
        if (j2 == null || j2.equals(str)) {
            this.f15752c.m13331a(z);
            this.f15750a.m19134a((byte) 2);
            this.f15750a.m19124c(j);
            this.f15750a.m19126b(str);
            this.f15750a.m19121d(str2);
            this.f15751b.mo13464a(this.f15750a.m19135a(), j, str, str2);
            m13357a((byte) 2);
            this.f15756j = m13355a(j, this.f15755i);
            this.f15764s.compareAndSet(false, true);
            return;
        }
        throw new IllegalArgumentException(FileDownloadUtils.m13182a("callback onConnected must with precondition succeed, but the etag is changes(%s != %s)", str, j2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: e */
    public void m13342e() {
        this.f15758l = new HandlerThread("source-status-callback");
        this.f15758l.start();
        this.f15757k = new Handler(this.f15758l.getLooper(), this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m13356a(long j) {
        this.f15762p.addAndGet(j);
        this.f15750a.m19127b(j);
        m13348b(SystemClock.elapsedRealtime());
        if (this.f15757k == null) {
            m13338i();
        } else if (this.f15763q.get()) {
            m13353a(this.f15757k.obtainMessage(3));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m13351a(Exception exc, int i) {
        this.f15762p.set(0L);
        Handler handler = this.f15757k;
        if (handler == null) {
            m13346b(exc, i);
        } else {
            m13353a(handler.obtainMessage(5, i, 0, exc));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: f */
    public void m13341f() {
        m13335l();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m13352a(Exception exc) {
        m13344c(exc);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: g */
    public void m13340g() throws IOException {
        if (!m13336k()) {
            m13337j();
        }
    }

    /* renamed from: a */
    private synchronized void m13353a(Message message) {
        if (!this.f15758l.isAlive()) {
            if (FileDownloadLog.f15845a) {
                FileDownloadLog.m13211c(this, f15749r, Integer.valueOf(message.what));
            }
            return;
        }
        try {
            this.f15757k.sendMessage(message);
        } catch (IllegalStateException e) {
            if (this.f15758l.isAlive()) {
                throw e;
            } else if (FileDownloadLog.f15845a) {
                FileDownloadLog.m13211c(this, f15749r, Integer.valueOf(message.what));
            }
        }
    }

    /* renamed from: a */
    private static long m13355a(long j, long j2) {
        if (j2 <= 0) {
            return -1L;
        }
        if (j == -1) {
            return 1L;
        }
        long j3 = j / j2;
        if (j3 <= 0) {
            return 1L;
        }
        return j3;
    }

    /* renamed from: b */
    private Exception m13347b(Exception exc) {
        String e = this.f15750a.m19120e();
        if ((!this.f15750a.m19116i() && !FileDownloadProperties.m13208a().f15862f) || !(exc instanceof IOException) || !new File(e).exists()) {
            return exc;
        }
        long h = FileDownloadUtils.m13156h(e);
        if (h > PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM) {
            return exc;
        }
        long j = 0;
        File file = new File(e);
        if (!file.exists()) {
            FileDownloadLog.m13213a(this, exc, "Exception with: free space isn't enough, and the target file not exist.", new Object[0]);
        } else {
            j = file.length();
        }
        if (Build.VERSION.SDK_INT >= 9) {
            return new FileDownloadOutOfSpaceException(h, PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM, j, exc);
        }
        return new FileDownloadOutOfSpaceException(h, PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM, j);
    }

    /* renamed from: a */
    private void m13354a(SQLiteFullException sQLiteFullException) {
        int a = this.f15750a.m19135a();
        if (FileDownloadLog.f15845a) {
            FileDownloadLog.m13211c(this, "the data of the task[%d] is dirty, because the SQLite full exception[%s], so remove it from the database directly.", Integer.valueOf(a), sQLiteFullException.toString());
        }
        this.f15750a.m19123c(sQLiteFullException.toString());
        this.f15750a.m19134a((byte) -1);
        this.f15751b.mo13445e(a);
        this.f15751b.mo13446d(a);
    }

    /* renamed from: h */
    private void m13339h() throws IOException {
        boolean z;
        Throwable th;
        String e = this.f15750a.m19120e();
        String d = this.f15750a.m19122d();
        File file = new File(e);
        try {
            File file2 = new File(d);
            if (file2.exists()) {
                long length = file2.length();
                if (file2.delete()) {
                    FileDownloadLog.m13210d(this, "The target file([%s], [%d]) will be replaced with the new downloaded file[%d]", d, Long.valueOf(length), Long.valueOf(file.length()));
                } else {
                    throw new IOException(FileDownloadUtils.m13182a("Can't delete the old file([%s], [%d]), so can't replace it with the new downloaded one.", d, Long.valueOf(length)));
                }
            }
            z = !file.renameTo(file2);
            if (z) {
                try {
                    throw new IOException(FileDownloadUtils.m13182a("Can't rename the  temp downloaded file(%s) to the target file(%s)", e, d));
                } catch (Throwable th2) {
                    th = th2;
                    if (z && file.exists() && !file.delete()) {
                        FileDownloadLog.m13210d(this, "delete the temp file(%s) failed, on completed downloading.", e);
                    }
                    throw th;
                }
            } else if (z && file.exists() && !file.delete()) {
                FileDownloadLog.m13210d(this, "delete the temp file(%s) failed, on completed downloading.", e);
            }
        } catch (Throwable th3) {
            th = th3;
            z = true;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0020 A[DONT_GENERATE] */
    @Override // android.os.Handler.Callback
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean handleMessage(android.os.Message r5) {
        /*
            r4 = this;
            r0 = 1
            r4.f15759m = r0
            int r1 = r5.what
            r2 = 3
            r3 = 0
            if (r1 == r2) goto L_0x0017
            r2 = 5
            if (r1 == r2) goto L_0x000d
            goto L_0x001a
        L_0x000d:
            java.lang.Object r1 = r5.obj     // Catch: all -> 0x0026
            java.lang.Exception r1 = (java.lang.Exception) r1     // Catch: all -> 0x0026
            int r5 = r5.arg1     // Catch: all -> 0x0026
            r4.m13346b(r1, r5)     // Catch: all -> 0x0026
            goto L_0x001a
        L_0x0017:
            r4.m13338i()     // Catch: all -> 0x0026
        L_0x001a:
            r4.f15759m = r3
            java.lang.Thread r5 = r4.f15760n
            if (r5 == 0) goto L_0x0025
            java.lang.Thread r5 = r4.f15760n
            java.util.concurrent.locks.LockSupport.unpark(r5)
        L_0x0025:
            return r0
        L_0x0026:
            r5 = move-exception
            r4.f15759m = r3
            java.lang.Thread r0 = r4.f15760n
            if (r0 == 0) goto L_0x0032
            java.lang.Thread r0 = r4.f15760n
            java.util.concurrent.locks.LockSupport.unpark(r0)
        L_0x0032:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.DownloadStatusCallback.handleMessage(android.os.Message):boolean");
    }

    /* renamed from: i */
    private void m13338i() {
        if (this.f15750a.m19118g() == this.f15750a.m19117h()) {
            this.f15751b.mo13465a(this.f15750a.m19135a(), this.f15750a.m19118g());
            return;
        }
        if (this.f15764s.compareAndSet(true, false)) {
            if (FileDownloadLog.f15845a) {
                FileDownloadLog.m13212b(this, "handleProgress update model's status with progress", new Object[0]);
            }
            this.f15750a.m19134a((byte) 3);
        }
        if (this.f15763q.compareAndSet(true, false)) {
            if (FileDownloadLog.f15845a) {
                FileDownloadLog.m13212b(this, "handleProgress notify user progress status", new Object[0]);
            }
            m13357a((byte) 3);
        }
    }

    /* renamed from: j */
    private void m13337j() throws IOException {
        m13339h();
        this.f15750a.m19134a((byte) -3);
        this.f15751b.mo13452b(this.f15750a.m19135a(), this.f15750a.m19117h());
        this.f15751b.mo13446d(this.f15750a.m19135a());
        m13357a((byte) -3);
        if (FileDownloadProperties.m13208a().f15863g) {
            FileDownloadBroadcastHandler.m19049a(this.f15750a);
        }
    }

    /* renamed from: k */
    private boolean m13336k() {
        if (this.f15750a.m19116i()) {
            FileDownloadModel fileDownloadModel = this.f15750a;
            fileDownloadModel.m19124c(fileDownloadModel.m19118g());
        } else if (this.f15750a.m19118g() != this.f15750a.m19117h()) {
            m13352a(new FileDownloadGiveUpRetryException(FileDownloadUtils.m13182a("sofar[%d] not equal total[%d]", Long.valueOf(this.f15750a.m19118g()), Long.valueOf(this.f15750a.m19117h()))));
            return true;
        }
        return false;
    }

    /* renamed from: b */
    private void m13346b(Exception exc, int i) {
        Exception b = m13347b(exc);
        this.f15752c.m13332a(b);
        this.f15752c.m13333a(this.f15753d - i);
        this.f15750a.m19134a((byte) 5);
        this.f15750a.m19123c(b.toString());
        this.f15751b.mo13461a(this.f15750a.m19135a(), b);
        m13357a((byte) 5);
    }

    /* renamed from: l */
    private void m13335l() {
        this.f15750a.m19134a((byte) -2);
        this.f15751b.mo13447c(this.f15750a.m19135a(), this.f15750a.m19118g());
        m13357a((byte) -2);
    }

    /* renamed from: c */
    private void m13344c(Exception exc) {
        Exception e = m13347b(exc);
        if (e instanceof SQLiteFullException) {
            m13354a((SQLiteFullException) e);
        } else {
            try {
                this.f15750a.m19134a((byte) -1);
                this.f15750a.m19123c(exc.toString());
                this.f15751b.mo13460a(this.f15750a.m19135a(), e, this.f15750a.m19118g());
            } catch (SQLiteFullException e2) {
                e = e2;
                m13354a((SQLiteFullException) e);
            }
        }
        this.f15752c.m13332a(e);
        m13357a((byte) -1);
    }

    /* renamed from: b */
    private void m13348b(long j) {
        boolean z;
        if (this.f15765t.compareAndSet(true, false)) {
            z = true;
        } else {
            z = this.f15756j != -1 && this.f15762p.get() >= this.f15756j && j - this.f15761o >= ((long) this.f15754h);
        }
        if (z && this.f15763q.compareAndSet(false, true)) {
            if (FileDownloadLog.f15845a) {
                FileDownloadLog.m13212b(this, "inspectNeedCallbackToUser need callback to user", new Object[0]);
            }
            this.f15761o = j;
            this.f15762p.set(0L);
        }
    }

    /* renamed from: a */
    private void m13357a(byte b) {
        if (b != -2) {
            MessageSnapshotFlow.m19157a().m19156a(MessageSnapshotTaker.m19152a(b, this.f15750a, this.f15752c));
        } else if (FileDownloadLog.f15845a) {
            FileDownloadLog.m13211c(this, "High concurrent cause, Already paused and we don't need to call-back to Task in here, %d", Integer.valueOf(this.f15750a.m19135a()));
        }
    }

    /* compiled from: DownloadStatusCallback.java */
    /* renamed from: z1.agv$a */
    /* loaded from: classes3.dex */
    public static class C3464a {

        /* renamed from: a */
        private boolean f15766a;

        /* renamed from: b */
        private Exception f15767b;

        /* renamed from: c */
        private int f15768c;

        /* renamed from: a */
        void m13331a(boolean z) {
            this.f15766a = z;
        }

        /* renamed from: a */
        public boolean m13334a() {
            return this.f15766a;
        }

        /* renamed from: a */
        void m13332a(Exception exc) {
            this.f15767b = exc;
        }

        /* renamed from: a */
        void m13333a(int i) {
            this.f15768c = i;
        }

        /* renamed from: b */
        public Exception m13330b() {
            return this.f15767b;
        }

        /* renamed from: c */
        public int m13329c() {
            return this.f15768c;
        }
    }
}
