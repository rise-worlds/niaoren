package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReference;

@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010(\n\u0000\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002¢\u0006\u0002\u0010\u0004J\u000f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\tH\u0096\u0002R(\u0010\u0005\u001a\u001c\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00028\u0000 \u0007*\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00020\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, m8860e = {"Lkotlin/sequences/ConstrainedOnceSequence;", TessBaseAPI.f9204e, "Lkotlin/sequences/Sequence;", "sequence", "(Lkotlin/sequences/Sequence;)V", "sequenceRef", "Ljava/util/concurrent/atomic/AtomicReference;", "kotlin.jvm.PlatformType", "iterator", "", "kotlin-stdlib"})
/* renamed from: z1.cnp */
/* loaded from: classes3.dex */
public final class SequencesJVM<T> implements Sequence<T> {

    /* renamed from: a */
    private final AtomicReference<Sequence<T>> f20882a;

    public SequencesJVM(@NotNull Sequence<? extends T> cobVar) {
        cji.m5162f(cobVar, "sequence");
        this.f20882a = new AtomicReference<>(cobVar);
    }

    @Override // p110z1.Sequence
    @NotNull
    /* renamed from: a */
    public Iterator<T> mo3707a() {
        Sequence<T> andSet = this.f20882a.getAndSet(null);
        if (andSet != null) {
            return andSet.mo3707a();
        }
        throw new IllegalStateException("This sequence can be consumed only once.");
    }
}
