package p110z1;

import com.liulishuo.filedownloader.message.MessageSnapshot;
import com.liulishuo.filedownloader.message.MessageSnapshotTaker;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import p110z1.BaseDownloadTask;

/* renamed from: z1.afk */
/* loaded from: classes3.dex */
public class FileDownloadList {

    /* renamed from: a */
    private final ArrayList<BaseDownloadTask.AbstractC3404b> f15565a;

    /* compiled from: FileDownloadList.java */
    /* renamed from: z1.afk$a */
    /* loaded from: classes3.dex */
    private static final class C3420a {

        /* renamed from: a */
        private static final FileDownloadList f15566a = new FileDownloadList();

        private C3420a() {
        }
    }

    /* renamed from: a */
    public static FileDownloadList m13710a() {
        return C3420a.f15566a;
    }

    private FileDownloadList() {
        this.f15565a = new ArrayList<>();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public boolean m13703b() {
        return this.f15565a.isEmpty();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public int m13700c() {
        return this.f15565a.size();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public int m13709a(int i) {
        int i2;
        synchronized (this.f15565a) {
            Iterator<BaseDownloadTask.AbstractC3404b> it = this.f15565a.iterator();
            i2 = 0;
            while (it.hasNext()) {
                if (it.next().mo13760f(i)) {
                    i2++;
                }
            }
        }
        return i2;
    }

    /* renamed from: b */
    public BaseDownloadTask.AbstractC3404b m13702b(int i) {
        synchronized (this.f15565a) {
            Iterator<BaseDownloadTask.AbstractC3404b> it = this.f15565a.iterator();
            while (it.hasNext()) {
                BaseDownloadTask.AbstractC3404b next = it.next();
                if (next.mo13760f(i)) {
                    return next;
                }
            }
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public List<BaseDownloadTask.AbstractC3404b> m13699c(int i) {
        byte B;
        ArrayList arrayList = new ArrayList();
        synchronized (this.f15565a) {
            Iterator<BaseDownloadTask.AbstractC3404b> it = this.f15565a.iterator();
            while (it.hasNext()) {
                BaseDownloadTask.AbstractC3404b next = it.next();
                if (next.mo13760f(i) && !next.mo13799R() && (B = next.mo13801P().mo13815B()) != 0 && B != 10) {
                    arrayList.add(next);
                }
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: d */
    public List<BaseDownloadTask.AbstractC3404b> m13696d(int i) {
        ArrayList arrayList = new ArrayList();
        synchronized (this.f15565a) {
            Iterator<BaseDownloadTask.AbstractC3404b> it = this.f15565a.iterator();
            while (it.hasNext()) {
                BaseDownloadTask.AbstractC3404b next = it.next();
                if (next.mo13760f(i) && !next.mo13799R()) {
                    arrayList.add(next);
                }
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public boolean m13706a(BaseDownloadTask.AbstractC3404b bVar) {
        return this.f15565a.isEmpty() || !this.f15565a.contains(bVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public List<BaseDownloadTask.AbstractC3404b> m13704a(FileDownloadListener aflVar) {
        ArrayList arrayList = new ArrayList();
        synchronized (this.f15565a) {
            Iterator<BaseDownloadTask.AbstractC3404b> it = this.f15565a.iterator();
            while (it.hasNext()) {
                BaseDownloadTask.AbstractC3404b next = it.next();
                if (next.mo13772b(aflVar)) {
                    arrayList.add(next);
                }
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public List<BaseDownloadTask.AbstractC3404b> m13708a(int i, FileDownloadListener aflVar) {
        ArrayList arrayList = new ArrayList();
        synchronized (this.f15565a) {
            Iterator<BaseDownloadTask.AbstractC3404b> it = this.f15565a.iterator();
            while (it.hasNext()) {
                BaseDownloadTask.AbstractC3404b next = it.next();
                if (next.mo13801P().mo13745t() == aflVar && !next.mo13801P().mo13759g()) {
                    next.mo13758g(i);
                    arrayList.add(next);
                }
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: d */
    public BaseDownloadTask.AbstractC3404b[] m13697d() {
        BaseDownloadTask.AbstractC3404b[] bVarArr;
        synchronized (this.f15565a) {
            bVarArr = (BaseDownloadTask.AbstractC3404b[]) this.f15565a.toArray(new BaseDownloadTask.AbstractC3404b[this.f15565a.size()]);
        }
        return bVarArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m13707a(List<BaseDownloadTask.AbstractC3404b> list) {
        synchronized (this.f15565a) {
            Iterator<BaseDownloadTask.AbstractC3404b> it = this.f15565a.iterator();
            while (it.hasNext()) {
                BaseDownloadTask.AbstractC3404b next = it.next();
                if (!list.contains(next)) {
                    list.add(next);
                }
            }
            this.f15565a.clear();
        }
    }

    /* renamed from: a */
    public boolean m13705a(BaseDownloadTask.AbstractC3404b bVar, MessageSnapshot messageSnapshot) {
        boolean remove;
        byte b = messageSnapshot.mo19170b();
        synchronized (this.f15565a) {
            remove = this.f15565a.remove(bVar);
        }
        if (FileDownloadLog.f15845a && this.f15565a.size() == 0) {
            FileDownloadLog.m13209e(this, "remove %s left %d %d", bVar, Byte.valueOf(b), Integer.valueOf(this.f15565a.size()));
        }
        if (remove) {
            IFileDownloadMessenger d = bVar.mo13800Q().mo13528d();
            switch (b) {
                case -4:
                    d.mo13567g(messageSnapshot);
                    break;
                case -3:
                    d.mo13569e(MessageSnapshotTaker.m19148a(messageSnapshot));
                    break;
                case -2:
                    d.mo13565i(messageSnapshot);
                    break;
                case -1:
                    d.mo13566h(messageSnapshot);
                    break;
            }
        } else {
            FileDownloadLog.m13214a(this, "remove error, not exist: %s %d", bVar, Byte.valueOf(b));
        }
        return remove;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public void m13701b(BaseDownloadTask.AbstractC3404b bVar) {
        if (!bVar.mo13801P().mo13759g()) {
            bVar.mo13797T();
        }
        if (bVar.mo13800Q().mo13528d().mo13579a()) {
            m13698c(bVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public void m13698c(BaseDownloadTask.AbstractC3404b bVar) {
        if (!bVar.mo13796U()) {
            synchronized (this.f15565a) {
                if (this.f15565a.contains(bVar)) {
                    FileDownloadLog.m13210d(this, "already has %s", bVar);
                } else {
                    bVar.mo13795V();
                    this.f15565a.add(bVar);
                    if (FileDownloadLog.f15845a) {
                        FileDownloadLog.m13209e(this, "add list in all %s %d %d", bVar, Byte.valueOf(bVar.mo13801P().mo13815B()), Integer.valueOf(this.f15565a.size()));
                    }
                }
            }
        }
    }
}
