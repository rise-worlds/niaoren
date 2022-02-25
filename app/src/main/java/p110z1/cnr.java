package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import com.stripe.android.RequestOptions;
import java.util.Iterator;

/* compiled from: Sequences.kt */
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010(\n\u0000\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B'\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0006¢\u0006\u0002\u0010\u0007J\u000f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\tH\u0096\u0002R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, m8860e = {"Lkotlin/sequences/DistinctSequence;", TessBaseAPI.f9204e, "K", "Lkotlin/sequences/Sequence;", RequestOptions.f12301a, "keySelector", "Lkotlin/Function1;", "(Lkotlin/sequences/Sequence;Lkotlin/jvm/functions/Function1;)V", "iterator", "", "kotlin-stdlib"})
/* renamed from: z1.cnr */
/* loaded from: classes3.dex */
public final class cnr<T, K> implements Sequence<T> {

    /* renamed from: a */
    private final Sequence<T> f20886a;

    /* renamed from: b */
    private final chd<T, K> f20887b;

    /* JADX WARN: Multi-variable type inference failed */
    public cnr(@NotNull Sequence<? extends T> cobVar, @NotNull chd<? super T, ? extends K> chdVar) {
        cji.m5162f(cobVar, RequestOptions.f12301a);
        cji.m5162f(chdVar, "keySelector");
        this.f20886a = cobVar;
        this.f20887b = chdVar;
    }

    @Override // p110z1.Sequence
    @NotNull
    /* renamed from: a */
    public Iterator<T> mo3707a() {
        return new Sequences(this.f20886a.mo3707a(), this.f20887b);
    }
}
