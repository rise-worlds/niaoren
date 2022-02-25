package p110z1;

import com.liulishuo.filedownloader.message.MessageSnapshotFlow;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import p110z1.ITaskHunter;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: z1.afu */
/* loaded from: classes3.dex */
public class FileDownloadTaskLauncher {

    /* renamed from: a */
    private final C3429b f15603a = new C3429b();

    FileDownloadTaskLauncher() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: FileDownloadTaskLauncher.java */
    /* renamed from: z1.afu$a */
    /* loaded from: classes3.dex */
    public static class C3428a {

        /* renamed from: a */
        private static final FileDownloadTaskLauncher f15604a = new FileDownloadTaskLauncher();

        private C3428a() {
        }

        static {
            MessageSnapshotFlow.m19157a().m19155a(new MessageSnapshotGate());
        }
    }

    /* renamed from: a */
    public static FileDownloadTaskLauncher m13640a() {
        return C3428a.f15604a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public synchronized void m13638a(ITaskHunter.AbstractC3437b bVar) {
        this.f15603a.m13632a(bVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public synchronized void m13637b() {
        this.f15603a.m13634a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public synchronized void m13636b(ITaskHunter.AbstractC3437b bVar) {
        this.f15603a.m13630b(bVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public synchronized void m13639a(FileDownloadListener aflVar) {
        this.f15603a.m13633a(aflVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: FileDownloadTaskLauncher.java */
    /* renamed from: z1.afu$b */
    /* loaded from: classes3.dex */
    public static class C3429b {

        /* renamed from: a */
        private ThreadPoolExecutor f15605a;

        /* renamed from: b */
        private LinkedBlockingQueue<Runnable> f15606b;

        C3429b() {
            m13631b();
        }

        /* renamed from: a */
        public void m13632a(ITaskHunter.AbstractC3437b bVar) {
            this.f15605a.execute(new RunnableC3430c(bVar));
        }

        /* renamed from: b */
        public void m13630b(ITaskHunter.AbstractC3437b bVar) {
            this.f15606b.remove(bVar);
        }

        /* renamed from: a */
        public void m13633a(FileDownloadListener aflVar) {
            if (aflVar == null) {
                FileDownloadLog.m13210d(this, "want to expire by listener, but the listener provided is null", new Object[0]);
                return;
            }
            ArrayList<Runnable> arrayList = new ArrayList();
            Iterator<Runnable> it = this.f15606b.iterator();
            while (it.hasNext()) {
                Runnable next = it.next();
                RunnableC3430c cVar = (RunnableC3430c) next;
                if (cVar.m13628a(aflVar)) {
                    cVar.m13629a();
                    arrayList.add(next);
                }
            }
            if (!arrayList.isEmpty()) {
                if (FileDownloadLog.f15845a) {
                    FileDownloadLog.m13211c(this, "expire %d tasks with listener[%s]", Integer.valueOf(arrayList.size()), aflVar);
                }
                for (Runnable runnable : arrayList) {
                    this.f15605a.remove(runnable);
                }
            }
        }

        /* renamed from: a */
        public void m13634a() {
            if (FileDownloadLog.f15845a) {
                FileDownloadLog.m13211c(this, "expire %d tasks", Integer.valueOf(this.f15606b.size()));
            }
            this.f15605a.shutdownNow();
            m13631b();
        }

        /* renamed from: b */
        private void m13631b() {
            this.f15606b = new LinkedBlockingQueue<>();
            this.f15605a = FileDownloadExecutors.m13231a(3, this.f15606b, "LauncherTask");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: FileDownloadTaskLauncher.java */
    /* renamed from: z1.afu$c */
    /* loaded from: classes3.dex */
    public static class RunnableC3430c implements Runnable {

        /* renamed from: a */
        private final ITaskHunter.AbstractC3437b f15607a;

        /* renamed from: b */
        private boolean f15608b = false;

        RunnableC3430c(ITaskHunter.AbstractC3437b bVar) {
            this.f15607a = bVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (!this.f15608b) {
                this.f15607a.mo13525r();
            }
        }

        /* renamed from: a */
        public boolean m13628a(FileDownloadListener aflVar) {
            ITaskHunter.AbstractC3437b bVar = this.f15607a;
            return bVar != null && bVar.mo13526a(aflVar);
        }

        public boolean equals(Object obj) {
            return super.equals(obj) || obj == this.f15607a;
        }

        /* renamed from: a */
        public void m13629a() {
            this.f15608b = true;
        }
    }
}
