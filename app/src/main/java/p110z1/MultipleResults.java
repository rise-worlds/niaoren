package p110z1;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* renamed from: z1.dbk */
/* loaded from: classes3.dex */
public class MultipleResults implements Iterable<OneResult> {

    /* renamed from: a */
    private final List<OneResult> f21281a;

    public MultipleResults(int i) {
        this.f21281a = new CopyOnWriteArrayList(new OneResult[i]);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void m3260a(int i, OneResult dbnVar) {
        this.f21281a.set(i, dbnVar);
    }

    /* renamed from: a */
    public OneResult m3261a(int i) {
        return this.f21281a.get(i);
    }

    @Override // java.lang.Iterable
    public Iterator<OneResult> iterator() {
        return this.f21281a.iterator();
    }

    /* renamed from: a */
    public int m3262a() {
        return this.f21281a.size();
    }

    public String toString() {
        return "MultipleResults [results=" + this.f21281a + "]";
    }
}
