package p110z1;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import p110z1.BaseDownloadTask;

/* renamed from: z1.ahw */
/* loaded from: classes3.dex */
public class FileDownloadSerialQueue {

    /* renamed from: a */
    public static final int f15866a = 0;

    /* renamed from: j */
    private static final int f15867j = 1;

    /* renamed from: b */
    volatile BaseDownloadTask f15868b;

    /* renamed from: e */
    private final Object f15871e = new Object();

    /* renamed from: f */
    private final BlockingQueue<BaseDownloadTask> f15872f = new LinkedBlockingQueue();

    /* renamed from: g */
    private final List<BaseDownloadTask> f15873g = new ArrayList();

    /* renamed from: d */
    volatile boolean f15870d = false;

    /* renamed from: h */
    private final HandlerThread f15874h = new HandlerThread(FileDownloadUtils.m13152l("SerialDownloadManager"));

    /* renamed from: i */
    private final Handler f15875i = new Handler(this.f15874h.getLooper(), new C3486b());

    /* renamed from: c */
    final C3485a f15869c = new C3485a(new WeakReference(this));

    public FileDownloadSerialQueue() {
        this.f15874h.start();
        m13197f();
    }

    /* renamed from: a */
    public void m13204a(BaseDownloadTask afaVar) {
        synchronized (this.f15869c) {
            if (this.f15870d) {
                this.f15873g.add(afaVar);
                return;
            }
            try {
                this.f15872f.put(afaVar);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    public void m13205a() {
        synchronized (this.f15869c) {
            if (this.f15870d) {
                FileDownloadLog.m13210d(this, "require pause this queue(remain %d), but it has already been paused", Integer.valueOf(this.f15872f.size()));
                return;
            }
            this.f15870d = true;
            this.f15872f.drainTo(this.f15873g);
            if (this.f15868b != null) {
                this.f15868b.mo13767c(this.f15869c);
                this.f15868b.mo13756i();
            }
        }
    }

    /* renamed from: b */
    public void m13202b() {
        synchronized (this.f15869c) {
            if (!this.f15870d) {
                FileDownloadLog.m13210d(this, "require resume this queue(remain %d), but it is still running", Integer.valueOf(this.f15872f.size()));
                return;
            }
            this.f15870d = false;
            this.f15872f.addAll(this.f15873g);
            this.f15873g.clear();
            if (this.f15868b == null) {
                m13197f();
            } else {
                this.f15868b.mo13773b(this.f15869c);
                this.f15868b.mo13757h();
            }
        }
    }

    /* renamed from: c */
    public int m13200c() {
        if (this.f15868b != null) {
            return this.f15868b.mo13754k();
        }
        return 0;
    }

    /* renamed from: d */
    public int m13199d() {
        return this.f15872f.size() + this.f15873g.size();
    }

    /* renamed from: e */
    public List<BaseDownloadTask> m13198e() {
        ArrayList arrayList;
        synchronized (this.f15869c) {
            if (this.f15868b != null) {
                m13205a();
            }
            arrayList = new ArrayList(this.f15873g);
            this.f15873g.clear();
            this.f15875i.removeMessages(1);
            this.f15874h.interrupt();
            this.f15874h.quit();
        }
        return arrayList;
    }

    /* compiled from: FileDownloadSerialQueue.java */
    /* renamed from: z1.ahw$b */
    /* loaded from: classes3.dex */
    private class C3486b implements Handler.Callback {
        private C3486b() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            if (message.what == 1) {
                try {
                    if (FileDownloadSerialQueue.this.f15870d) {
                        return false;
                    }
                    FileDownloadSerialQueue.this.f15868b = (BaseDownloadTask) FileDownloadSerialQueue.this.f15872f.take();
                    FileDownloadSerialQueue.this.f15868b.mo13773b(FileDownloadSerialQueue.this.f15869c).mo13757h();
                } catch (InterruptedException unused) {
                    return false;
                }
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: FileDownloadSerialQueue.java */
    /* renamed from: z1.ahw$a */
    /* loaded from: classes3.dex */
    public static class C3485a implements BaseDownloadTask.AbstractC3403a {

        /* renamed from: a */
        private final WeakReference<FileDownloadSerialQueue> f15876a;

        C3485a(WeakReference<FileDownloadSerialQueue> weakReference) {
            this.f15876a = weakReference;
        }

        @Override // p110z1.BaseDownloadTask.AbstractC3403a
        /* renamed from: a */
        public synchronized void mo13196a(BaseDownloadTask afaVar) {
            afaVar.mo13767c(this);
            if (this.f15876a != null) {
                FileDownloadSerialQueue ahwVar = this.f15876a.get();
                if (ahwVar != null) {
                    ahwVar.f15868b = null;
                    if (!ahwVar.f15870d) {
                        ahwVar.m13197f();
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: f */
    public void m13197f() {
        this.f15875i.sendEmptyMessage(1);
    }
}
