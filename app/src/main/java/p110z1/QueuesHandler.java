package p110z1;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.SparseArray;
import java.lang.ref.WeakReference;
import java.util.List;
import p110z1.BaseDownloadTask;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: z1.agf */
/* loaded from: classes3.dex */
public class QueuesHandler implements IQueuesHandler {

    /* renamed from: a */
    static final int f15617a = 1;

    /* renamed from: b */
    static final int f15618b = 2;

    /* renamed from: c */
    static final int f15619c = 3;

    /* renamed from: d */
    private final SparseArray<Handler> f15620d = new SparseArray<>();

    @Override // p110z1.IQueuesHandler
    /* renamed from: a */
    public boolean mo13511a(FileDownloadListener aflVar) {
        int hashCode = aflVar.hashCode();
        List<BaseDownloadTask.AbstractC3404b> a = FileDownloadList.m13710a().m13708a(hashCode, aflVar);
        if (m13514a(hashCode, a, aflVar, false)) {
            return false;
        }
        for (BaseDownloadTask.AbstractC3404b bVar : a) {
            bVar.mo13793X();
        }
        return true;
    }

    @Override // p110z1.IQueuesHandler
    /* renamed from: b */
    public boolean mo13507b(FileDownloadListener aflVar) {
        C3440b bVar = new C3440b();
        int hashCode = bVar.hashCode();
        List<BaseDownloadTask.AbstractC3404b> a = FileDownloadList.m13710a().m13708a(hashCode, aflVar);
        if (m13514a(hashCode, a, aflVar, true)) {
            return false;
        }
        HandlerThread handlerThread = new HandlerThread(FileDownloadUtils.m13182a("filedownloader serial thread %s-%d", aflVar, Integer.valueOf(hashCode)));
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper(), bVar);
        bVar.m13503a(handler);
        bVar.m13502a(a);
        bVar.m13504a(0);
        synchronized (this.f15620d) {
            this.f15620d.put(hashCode, handler);
        }
        return true;
    }

    @Override // p110z1.IQueuesHandler
    /* renamed from: a */
    public void mo13516a() {
        for (int i = 0; i < this.f15620d.size(); i++) {
            m13513a(this.f15620d.get(this.f15620d.keyAt(i)));
        }
    }

    @Override // p110z1.IQueuesHandler
    /* renamed from: a */
    public void mo13512a(List<Integer> list) {
        for (Integer num : list) {
            m13508b(this.f15620d.get(num.intValue()));
        }
    }

    @Override // p110z1.IQueuesHandler
    /* renamed from: b */
    public int mo13509b() {
        return this.f15620d.size();
    }

    @Override // p110z1.IQueuesHandler
    /* renamed from: a */
    public boolean mo13515a(int i) {
        return this.f15620d.get(i) != null;
    }

    /* renamed from: a */
    private boolean m13514a(int i, List<BaseDownloadTask.AbstractC3404b> list, FileDownloadListener aflVar, boolean z) {
        if (FileDownloadMonitor.m13676c()) {
            FileDownloadMonitor.m13677b().m13675a(list.size(), true, aflVar);
        }
        if (FileDownloadLog.f15845a) {
            FileDownloadLog.m13209e(FileDownloader.class, "start list attachKey[%d] size[%d] listener[%s] isSerial[%B]", Integer.valueOf(i), Integer.valueOf(list.size()), aflVar, Boolean.valueOf(z));
        }
        if (list != null && !list.isEmpty()) {
            return false;
        }
        FileDownloadLog.m13210d(FileDownloader.class, "Tasks with the listener can't start, because can't find any task with the provided listener, maybe tasks instance has been started in the past, so they are all are inUsing, if in this case, you can use [BaseDownloadTask#reuse] to reuse theme first then start again: [%s, %B]", aflVar, Boolean.valueOf(z));
        return true;
    }

    /* compiled from: QueuesHandler.java */
    /* renamed from: z1.agf$b */
    /* loaded from: classes3.dex */
    private class C3440b implements Handler.Callback {

        /* renamed from: b */
        private Handler f15624b;

        /* renamed from: c */
        private List<BaseDownloadTask.AbstractC3404b> f15625c;

        /* renamed from: d */
        private int f15626d = 0;

        /* renamed from: e */
        private C3439a f15627e = new C3439a(new WeakReference(this));

        C3440b() {
        }

        /* renamed from: a */
        public void m13503a(Handler handler) {
            this.f15624b = handler;
        }

        /* renamed from: a */
        public void m13502a(List<BaseDownloadTask.AbstractC3404b> list) {
            this.f15625c = list;
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            if (message.what == 1) {
                if (message.arg1 >= this.f15625c.size()) {
                    synchronized (QueuesHandler.this.f15620d) {
                        QueuesHandler.this.f15620d.remove(this.f15625c.get(0).mo13798S());
                    }
                    Handler handler = this.f15624b;
                    FileDownloadListener aflVar = null;
                    if (!(handler == null || handler.getLooper() == null)) {
                        this.f15624b.getLooper().quit();
                        this.f15624b = null;
                        this.f15625c = null;
                        this.f15627e = null;
                    }
                    if (FileDownloadLog.f15845a) {
                        Object[] objArr = new Object[2];
                        List<BaseDownloadTask.AbstractC3404b> list = this.f15625c;
                        if (!(list == null || list.get(0) == null)) {
                            aflVar = this.f15625c.get(0).mo13801P().mo13745t();
                        }
                        objArr[0] = aflVar;
                        objArr[1] = Integer.valueOf(message.arg1);
                        FileDownloadLog.m13211c(C3440b.class, "final serial %s %d", objArr);
                    }
                    return true;
                }
                this.f15626d = message.arg1;
                BaseDownloadTask.AbstractC3404b bVar = this.f15625c.get(this.f15626d);
                synchronized (bVar.mo13791Z()) {
                    if (bVar.mo13801P().mo13815B() == 0 && !FileDownloadList.m13710a().m13706a(bVar)) {
                        bVar.mo13801P().mo13773b(this.f15627e.m13506a(this.f15626d + 1));
                        bVar.mo13793X();
                    }
                    if (FileDownloadLog.f15845a) {
                        FileDownloadLog.m13211c(C3440b.class, "direct go next by not contains %s %d", bVar, Integer.valueOf(message.arg1));
                    }
                    m13504a(message.arg1 + 1);
                    return true;
                }
            } else if (message.what == 2) {
                m13505a();
            } else if (message.what == 3) {
                m13500b();
            }
            return true;
        }

        /* renamed from: a */
        public void m13505a() {
            this.f15625c.get(this.f15626d).mo13801P().mo13767c(this.f15627e);
            this.f15624b.removeCallbacksAndMessages(null);
        }

        /* renamed from: b */
        public void m13500b() {
            m13504a(this.f15626d);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public void m13504a(int i) {
            Handler handler = this.f15624b;
            if (handler == null || this.f15625c == null) {
                FileDownloadLog.m13210d(this, "need go next %d, but params is not ready %s %s", Integer.valueOf(i), this.f15624b, this.f15625c);
                return;
            }
            Message obtainMessage = handler.obtainMessage();
            obtainMessage.what = 1;
            obtainMessage.arg1 = i;
            if (FileDownloadLog.f15845a) {
                Object[] objArr = new Object[2];
                List<BaseDownloadTask.AbstractC3404b> list = this.f15625c;
                FileDownloadListener aflVar = null;
                if (!(list == null || list.get(0) == null)) {
                    aflVar = this.f15625c.get(0).mo13801P().mo13745t();
                }
                objArr[0] = aflVar;
                objArr[1] = Integer.valueOf(obtainMessage.arg1);
                FileDownloadLog.m13211c(C3440b.class, "start next %s %s", objArr);
            }
            this.f15624b.sendMessage(obtainMessage);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: QueuesHandler.java */
    /* renamed from: z1.agf$a */
    /* loaded from: classes3.dex */
    public static class C3439a implements BaseDownloadTask.AbstractC3403a {

        /* renamed from: a */
        private final WeakReference<C3440b> f15621a;

        /* renamed from: b */
        private int f15622b;

        private C3439a(WeakReference<C3440b> weakReference) {
            this.f15621a = weakReference;
        }

        /* renamed from: a */
        public BaseDownloadTask.AbstractC3403a m13506a(int i) {
            this.f15622b = i;
            return this;
        }

        @Override // p110z1.BaseDownloadTask.AbstractC3403a
        /* renamed from: a */
        public void mo13196a(BaseDownloadTask afaVar) {
            WeakReference<C3440b> weakReference = this.f15621a;
            if (weakReference != null && weakReference.get() != null) {
                this.f15621a.get().m13504a(this.f15622b);
            }
        }
    }

    /* renamed from: a */
    private void m13513a(Handler handler) {
        handler.sendEmptyMessage(2);
    }

    /* renamed from: b */
    private void m13508b(Handler handler) {
        handler.sendEmptyMessage(3);
    }
}
