package p110z1;

import android.util.SparseArray;
import p110z1.BaseNotificationItem;

/* renamed from: z1.ahn */
/* loaded from: classes3.dex */
public class FileDownloadNotificationHelper<T extends BaseNotificationItem> {

    /* renamed from: a */
    private final SparseArray<T> f15832a = new SparseArray<>();

    /* renamed from: a */
    public T m13276a(int i) {
        return this.f15832a.get(i);
    }

    /* renamed from: b */
    public boolean m13272b(int i) {
        return m13276a(i) != null;
    }

    /* renamed from: c */
    public T m13271c(int i) {
        T a = m13276a(i);
        if (a == null) {
            return null;
        }
        this.f15832a.remove(i);
        return a;
    }

    /* renamed from: a */
    public void m13273a(T t) {
        this.f15832a.remove(t.m13288c());
        this.f15832a.put(t.m13288c(), t);
    }

    /* renamed from: a */
    public void m13274a(int i, int i2, int i3) {
        T a = m13276a(i);
        if (a != null) {
            a.m13296a(3);
            a.m13295a(i2, i3);
        }
    }

    /* renamed from: a */
    public void m13275a(int i, int i2) {
        T a = m13276a(i);
        if (a != null) {
            a.m13296a(i2);
            a.m13293a(false);
        }
    }

    /* renamed from: d */
    public void m13270d(int i) {
        T c = m13271c(i);
        if (c != null) {
            c.m13297a();
        }
    }

    /* renamed from: a */
    public void m13277a() {
        SparseArray<T> clone = this.f15832a.clone();
        this.f15832a.clear();
        for (int i = 0; i < clone.size(); i++) {
            clone.get(clone.keyAt(i)).m13297a();
        }
    }
}
