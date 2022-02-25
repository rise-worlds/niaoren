package p110z1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

/* renamed from: z1.ckl */
/* loaded from: classes3.dex */
public class SpreadBuilder {

    /* renamed from: a */
    private final ArrayList<Object> f20780a;

    public SpreadBuilder(int i) {
        this.f20780a = new ArrayList<>(i);
    }

    /* renamed from: a */
    public void m5081a(Object obj) {
        if (obj != null) {
            if (obj instanceof Object[]) {
                Object[] objArr = (Object[]) obj;
                if (objArr.length > 0) {
                    ArrayList<Object> arrayList = this.f20780a;
                    arrayList.ensureCapacity(arrayList.size() + objArr.length);
                    Collections.addAll(this.f20780a, objArr);
                }
            } else if (obj instanceof Collection) {
                this.f20780a.addAll((Collection) obj);
            } else if (obj instanceof Iterable) {
                for (Object obj2 : (Iterable) obj) {
                    this.f20780a.add(obj2);
                }
            } else if (obj instanceof Iterator) {
                Iterator it = (Iterator) obj;
                while (it.hasNext()) {
                    this.f20780a.add(it.next());
                }
            } else {
                throw new UnsupportedOperationException("Don't know how to spread " + obj.getClass());
            }
        }
    }

    /* renamed from: a */
    public int m5082a() {
        return this.f20780a.size();
    }

    /* renamed from: b */
    public void m5079b(Object obj) {
        this.f20780a.add(obj);
    }

    /* renamed from: a */
    public Object[] m5080a(Object[] objArr) {
        return this.f20780a.toArray(objArr);
    }
}
