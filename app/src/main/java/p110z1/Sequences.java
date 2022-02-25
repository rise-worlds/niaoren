package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import com.stripe.android.RequestOptions;
import java.util.HashSet;
import java.util.Iterator;

@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000,\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010(\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B'\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0007¢\u0006\u0002\u0010\bJ\b\u0010\f\u001a\u00020\rH\u0014R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\t\u001a\u0012\u0012\u0004\u0012\u00028\u00010\nj\b\u0012\u0004\u0012\u00028\u0001`\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, m8860e = {"Lkotlin/sequences/DistinctIterator;", TessBaseAPI.f9204e, "K", "Lkotlin/collections/AbstractIterator;", RequestOptions.f12301a, "", "keySelector", "Lkotlin/Function1;", "(Ljava/util/Iterator;Lkotlin/jvm/functions/Function1;)V", "observed", "Ljava/util/HashSet;", "Lkotlin/collections/HashSet;", "computeNext", "", "kotlin-stdlib"})
/* renamed from: z1.cnq */
/* loaded from: classes3.dex */
final class Sequences<T, K> extends AbstractIterator<T> {

    /* renamed from: a */
    private final HashSet<K> f20883a = new HashSet<>();

    /* renamed from: b */
    private final Iterator<T> f20884b;

    /* renamed from: c */
    private final chd<T, K> f20885c;

    /* JADX WARN: Multi-variable type inference failed */
    public Sequences(@NotNull Iterator<? extends T> it, @NotNull chd<? super T, ? extends K> chdVar) {
        cji.m5162f(it, RequestOptions.f12301a);
        cji.m5162f(chdVar, "keySelector");
        this.f20884b = it;
        this.f20885c = chdVar;
    }

    @Override // p110z1.AbstractIterator
    /* renamed from: a */
    protected void mo4549a() {
        while (this.f20884b.hasNext()) {
            T next = this.f20884b.next();
            if (this.f20883a.add(this.f20885c.invoke(next))) {
                m8325a(next);
                return;
            }
        }
        m8324b();
    }
}
