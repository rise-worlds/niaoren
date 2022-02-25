package p110z1;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;

/* renamed from: z1.afm */
/* loaded from: classes3.dex */
public class FileDownloadMessageStation {

    /* renamed from: a */
    static final int f15567a = 1;

    /* renamed from: b */
    static final int f15568b = 2;

    /* renamed from: c */
    public static final int f15569c = 10;

    /* renamed from: d */
    public static final int f15570d = 5;

    /* renamed from: e */
    static int f15571e = 10;

    /* renamed from: f */
    static int f15572f = 5;

    /* renamed from: g */
    private final Executor f15573g;

    /* renamed from: h */
    private final Handler f15574h;

    /* renamed from: i */
    private final LinkedBlockingQueue<IFileDownloadMessenger> f15575i;

    /* renamed from: j */
    private final Object f15576j;

    /* renamed from: k */
    private final ArrayList<IFileDownloadMessenger> f15577k;

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: FileDownloadMessageStation.java */
    /* renamed from: z1.afm$a */
    /* loaded from: classes3.dex */
    public static final class C3422a {

        /* renamed from: a */
        private static final FileDownloadMessageStation f15580a = new FileDownloadMessageStation();

        private C3422a() {
        }
    }

    /* renamed from: a */
    public static FileDownloadMessageStation m13692a() {
        return C3422a.f15580a;
    }

    private FileDownloadMessageStation() {
        this.f15573g = FileDownloadExecutors.m13232a(5, "BlockCompleted");
        this.f15576j = new Object();
        this.f15577k = new ArrayList<>();
        this.f15574h = new Handler(Looper.getMainLooper(), new C3423b());
        this.f15575i = new LinkedBlockingQueue<>();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m13690a(IFileDownloadMessenger afxVar) {
        m13689a(afxVar, false);
    }

    /* renamed from: a */
    void m13689a(final IFileDownloadMessenger afxVar, boolean z) {
        if (afxVar.mo13574c()) {
            afxVar.mo13576b();
        } else if (afxVar.mo13572d()) {
            this.f15573g.execute(new Runnable() { // from class: z1.afm.1
                @Override // java.lang.Runnable
                public void run() {
                    afxVar.mo13576b();
                }
            });
        } else {
            if (!m13688b() && !this.f15575i.isEmpty()) {
                synchronized (this.f15576j) {
                    if (!this.f15575i.isEmpty()) {
                        Iterator<IFileDownloadMessenger> it = this.f15575i.iterator();
                        while (it.hasNext()) {
                            m13687b(it.next());
                        }
                    }
                    this.f15575i.clear();
                }
            }
            if (!m13688b() || z) {
                m13687b(afxVar);
            } else {
                m13685c(afxVar);
            }
        }
    }

    /* renamed from: b */
    private void m13687b(IFileDownloadMessenger afxVar) {
        Handler handler = this.f15574h;
        handler.sendMessage(handler.obtainMessage(1, afxVar));
    }

    /* renamed from: c */
    private void m13685c(IFileDownloadMessenger afxVar) {
        synchronized (this.f15576j) {
            this.f15575i.offer(afxVar);
        }
        m13686c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public void m13686c() {
        int i;
        synchronized (this.f15576j) {
            if (this.f15577k.isEmpty()) {
                if (!this.f15575i.isEmpty()) {
                    if (!m13688b()) {
                        this.f15575i.drainTo(this.f15577k);
                        i = 0;
                    } else {
                        i = f15571e;
                        int min = Math.min(this.f15575i.size(), f15572f);
                        for (int i2 = 0; i2 < min; i2++) {
                            this.f15577k.add(this.f15575i.remove());
                        }
                    }
                    Handler handler = this.f15574h;
                    handler.sendMessageDelayed(handler.obtainMessage(2, this.f15577k), i);
                }
            }
        }
    }

    /* compiled from: FileDownloadMessageStation.java */
    /* renamed from: z1.afm$b */
    /* loaded from: classes3.dex */
    private static class C3423b implements Handler.Callback {
        private C3423b() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            if (message.what == 1) {
                ((IFileDownloadMessenger) message.obj).mo13576b();
            } else if (message.what == 2) {
                m13683a((ArrayList) message.obj);
                FileDownloadMessageStation.m13692a().m13686c();
            }
            return true;
        }

        /* renamed from: a */
        private void m13683a(ArrayList<IFileDownloadMessenger> arrayList) {
            Iterator<IFileDownloadMessenger> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().mo13576b();
            }
            arrayList.clear();
        }
    }

    /* renamed from: b */
    public static boolean m13688b() {
        return f15571e > 0;
    }
}
