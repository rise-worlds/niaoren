package p110z1;

import android.app.Notification;
import android.os.Looper;
import java.io.File;

/* renamed from: z1.afi */
/* loaded from: classes3.dex */
public class FileDownloadLine {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FileDownloadLine.java */
    /* renamed from: z1.afi$b */
    /* loaded from: classes3.dex */
    public interface AbstractC3417b {
        /* renamed from: a */
        void mo13713a();

        /* renamed from: b */
        Object mo13712b();
    }

    /* renamed from: a */
    public void m13718a(final int i, final Notification notification) {
        if (FileDownloader.m13627a().m13591j()) {
            FileDownloader.m13627a().m13625a(i, notification);
        } else {
            m13716a(new AbstractC3417b() { // from class: z1.afi.1
                @Override // p110z1.FileDownloadLine.AbstractC3417b
                /* renamed from: b */
                public Object mo13712b() {
                    return null;
                }

                @Override // p110z1.FileDownloadLine.AbstractC3417b
                /* renamed from: a */
                public void mo13713a() {
                    FileDownloader.m13627a().m13625a(i, notification);
                }
            });
        }
    }

    /* renamed from: a */
    public long m13719a(final int i) {
        if (FileDownloader.m13627a().m13591j()) {
            return FileDownloader.m13627a().m13600d(i);
        }
        AbstractC3417b bVar = new AbstractC3417b() { // from class: z1.afi.2

            /* renamed from: c */
            private long f15552c;

            @Override // p110z1.FileDownloadLine.AbstractC3417b
            /* renamed from: a */
            public void mo13713a() {
                this.f15552c = FileDownloader.m13627a().m13600d(i);
            }

            @Override // p110z1.FileDownloadLine.AbstractC3417b
            /* renamed from: b */
            public Object mo13712b() {
                return Long.valueOf(this.f15552c);
            }
        };
        m13716a(bVar);
        return ((Long) bVar.mo13712b()).longValue();
    }

    /* renamed from: b */
    public long m13715b(final int i) {
        if (FileDownloader.m13627a().m13591j()) {
            return FileDownloader.m13627a().m13598e(i);
        }
        AbstractC3417b bVar = new AbstractC3417b() { // from class: z1.afi.3

            /* renamed from: c */
            private long f15555c;

            @Override // p110z1.FileDownloadLine.AbstractC3417b
            /* renamed from: a */
            public void mo13713a() {
                this.f15555c = FileDownloader.m13627a().m13598e(i);
            }

            @Override // p110z1.FileDownloadLine.AbstractC3417b
            /* renamed from: b */
            public Object mo13712b() {
                return Long.valueOf(this.f15555c);
            }
        };
        m13716a(bVar);
        return ((Long) bVar.mo13712b()).longValue();
    }

    /* renamed from: a */
    public byte m13717a(final int i, final String str) {
        if (FileDownloader.m13627a().m13591j()) {
            return FileDownloader.m13627a().m13606b(i, str);
        }
        if (str != null && new File(str).exists()) {
            return (byte) -3;
        }
        AbstractC3417b bVar = new AbstractC3417b() { // from class: z1.afi.4

            /* renamed from: d */
            private byte f15559d;

            @Override // p110z1.FileDownloadLine.AbstractC3417b
            /* renamed from: a */
            public void mo13713a() {
                this.f15559d = FileDownloader.m13627a().m13606b(i, str);
            }

            @Override // p110z1.FileDownloadLine.AbstractC3417b
            /* renamed from: b */
            public Object mo13712b() {
                return Byte.valueOf(this.f15559d);
            }
        };
        m13716a(bVar);
        return ((Byte) bVar.mo13712b()).byteValue();
    }

    /* renamed from: a */
    private void m13716a(AbstractC3417b bVar) {
        RunnableC3416a aVar = new RunnableC3416a(bVar);
        synchronized (aVar) {
            FileDownloader.m13627a().m13619a(aVar);
            if (!aVar.m13714a()) {
                if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
                    try {
                        aVar.wait(200000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    throw new IllegalThreadStateException("Sorry, FileDownloader can not block the main thread, because the system is also  callbacks ServiceConnection#onServiceConnected method in the main thread.");
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FileDownloadLine.java */
    /* renamed from: z1.afi$a */
    /* loaded from: classes3.dex */
    public static class RunnableC3416a implements Runnable {

        /* renamed from: a */
        private boolean f15560a = false;

        /* renamed from: b */
        private final AbstractC3417b f15561b;

        RunnableC3416a(AbstractC3417b bVar) {
            this.f15561b = bVar;
        }

        /* renamed from: a */
        public boolean m13714a() {
            return this.f15560a;
        }

        @Override // java.lang.Runnable
        public void run() {
            synchronized (this) {
                this.f15561b.mo13713a();
                this.f15560a = true;
                notifyAll();
            }
        }
    }
}
