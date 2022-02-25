package p110z1;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.Executor;

/* renamed from: z1.agy */
/* loaded from: classes3.dex */
public class DownloadEventPoolImpl implements IDownloadEventPool {

    /* renamed from: a */
    private final Executor f15794a = FileDownloadExecutors.m13232a(10, "EventPool");

    /* renamed from: b */
    private final HashMap<String, LinkedList<IDownloadListener>> f15795b = new HashMap<>();

    @Override // p110z1.IDownloadEventPool
    /* renamed from: a */
    public boolean mo13304a(String str, IDownloadListener ahdVar) {
        boolean add;
        if (FileDownloadLog.f15845a) {
            FileDownloadLog.m13209e(this, "setListener %s", str);
        }
        if (ahdVar != null) {
            LinkedList<IDownloadListener> linkedList = this.f15795b.get(str);
            if (linkedList == null) {
                synchronized (str.intern()) {
                    linkedList = this.f15795b.get(str);
                    if (linkedList == null) {
                        HashMap<String, LinkedList<IDownloadListener>> hashMap = this.f15795b;
                        LinkedList<IDownloadListener> linkedList2 = new LinkedList<>();
                        hashMap.put(str, linkedList2);
                        linkedList = linkedList2;
                    }
                }
            }
            synchronized (str.intern()) {
                add = linkedList.add(ahdVar);
            }
            return add;
        }
        throw new IllegalArgumentException("listener must not be null!");
    }

    @Override // p110z1.IDownloadEventPool
    /* renamed from: b */
    public boolean mo13302b(String str, IDownloadListener ahdVar) {
        boolean remove;
        if (FileDownloadLog.f15845a) {
            FileDownloadLog.m13209e(this, "removeListener %s", str);
        }
        LinkedList<IDownloadListener> linkedList = this.f15795b.get(str);
        if (linkedList == null) {
            synchronized (str.intern()) {
                linkedList = this.f15795b.get(str);
            }
        }
        if (linkedList == null || ahdVar == null) {
            return false;
        }
        synchronized (str.intern()) {
            remove = linkedList.remove(ahdVar);
            if (linkedList.size() <= 0) {
                this.f15795b.remove(str);
            }
        }
        return remove;
    }

    @Override // p110z1.IDownloadEventPool
    /* renamed from: a */
    public boolean mo13303a(IDownloadEvent ahbVar) {
        if (FileDownloadLog.f15845a) {
            FileDownloadLog.m13209e(this, "publish %s", ahbVar.m13305b());
        }
        if (ahbVar != null) {
            String b = ahbVar.m13305b();
            LinkedList<IDownloadListener> linkedList = this.f15795b.get(b);
            if (linkedList == null) {
                synchronized (b.intern()) {
                    linkedList = this.f15795b.get(b);
                    if (linkedList == null) {
                        if (FileDownloadLog.f15845a) {
                            FileDownloadLog.m13211c(this, "No listener for this event %s", b);
                        }
                        return false;
                    }
                }
            }
            m13309a(linkedList, ahbVar);
            return true;
        }
        throw new IllegalArgumentException("event must not be null!");
    }

    @Override // p110z1.IDownloadEventPool
    /* renamed from: b */
    public void mo13301b(final IDownloadEvent ahbVar) {
        if (FileDownloadLog.f15845a) {
            FileDownloadLog.m13209e(this, "asyncPublishInNewThread %s", ahbVar.m13305b());
        }
        if (ahbVar != null) {
            this.f15794a.execute(new Runnable() { // from class: z1.agy.1
                @Override // java.lang.Runnable
                public void run() {
                    DownloadEventPoolImpl.this.mo13303a(ahbVar);
                }
            });
            return;
        }
        throw new IllegalArgumentException("event must not be null!");
    }

    /* renamed from: a */
    private void m13309a(LinkedList<IDownloadListener> linkedList, IDownloadEvent ahbVar) {
        Object[] array;
        for (Object obj : linkedList.toArray()) {
            if (obj != null && ((IDownloadListener) obj).mo13300a(ahbVar)) {
                break;
            }
        }
        if (ahbVar.f15802b != null) {
            ahbVar.f15802b.run();
        }
    }
}
