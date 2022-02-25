package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import java.util.Collection;
import java.util.Iterator;
import org.apache.tools.ant.types.selectors.SizeSelector;

/* compiled from: SequenceBuilder.kt */
@bwy(m8750a = "1.3")
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u001c\n\u0002\b\u0002\n\u0002\u0010(\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b'\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\u00020\u0002B\u0007\b\u0000¢\u0006\u0002\u0010\u0003J\u0019\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00028\u0000H¦@ø\u0001\u0000¢\u0006\u0002\u0010\u0007J\u001f\u0010\b\u001a\u00020\u00052\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\nH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000bJ\u001f\u0010\b\u001a\u00020\u00052\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\rH¦@ø\u0001\u0000¢\u0006\u0002\u0010\u000eJ\u001f\u0010\b\u001a\u00020\u00052\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u0010H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0011\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0012"}, m8860e = {"Lkotlin/sequences/SequenceScope;", TessBaseAPI.f9204e, "", "()V", "yield", "", SizeSelector.SIZE_KEY, "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "yieldAll", "elements", "", "(Ljava/lang/Iterable;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "iterator", "", "(Ljava/util/Iterator;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sequence", "Lkotlin/sequences/Sequence;", "(Lkotlin/sequences/Sequence;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlin-stdlib"})
@ccu
/* renamed from: z1.cod */
/* loaded from: classes3.dex */
public abstract class cod<T> {
    @dbs
    /* renamed from: a */
    public abstract Object mo4457a(T t, @NotNull Continuation<? super Unit> ccpVar);

    @dbs
    /* renamed from: a */
    public abstract Object mo4456a(@NotNull Iterator<? extends T> it, @NotNull Continuation<? super Unit> ccpVar);

    @dbs
    /* renamed from: a */
    public final Object m4458a(@NotNull Iterable<? extends T> iterable, @NotNull Continuation<? super Unit> ccpVar) {
        return (!(iterable instanceof Collection) || !((Collection) iterable).isEmpty()) ? mo4456a((Iterator) iterable.iterator(), ccpVar) : Unit.f20418a;
    }

    @dbs
    /* renamed from: a */
    public final Object m4455a(@NotNull Sequence<? extends T> cobVar, @NotNull Continuation<? super Unit> ccpVar) {
        return mo4456a((Iterator) cobVar.mo3707a(), ccpVar);
    }
}
