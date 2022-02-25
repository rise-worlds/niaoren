package p110z1;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* renamed from: z1.bth */
/* loaded from: classes3.dex */
public final class SorterFunction<T> implements Function<List<T>, List<T>> {

    /* renamed from: a */
    final Comparator<? super T> f20105a;

    public SorterFunction(Comparator<? super T> comparator) {
        this.f20105a = comparator;
    }

    /* renamed from: a */
    public List<T> apply(List<T> list) throws Exception {
        Collections.sort(list, this.f20105a);
        return list;
    }
}
