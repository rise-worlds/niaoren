package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(m8864a = 5, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u00000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001f\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a(\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u0006\u0012\u0002\b\u00030\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004\u001aA\u0010\u0005\u001a\u0002H\u0006\"\u0010\b\u0000\u0010\u0006*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u0007\"\u0004\b\u0001\u0010\u0002*\u0006\u0012\u0002\b\u00030\u00012\u0006\u0010\b\u001a\u0002H\u00062\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004¢\u0006\u0002\u0010\t\u001a&\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\f0\u000b\"\u000e\b\u0000\u0010\f*\b\u0012\u0004\u0012\u0002H\f0\r*\b\u0012\u0004\u0012\u0002H\f0\u0001\u001a8\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\f0\u000b\"\u0004\b\u0000\u0010\f*\b\u0012\u0004\u0012\u0002H\f0\u00012\u001a\u0010\u000e\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\f0\u000fj\n\u0012\u0006\b\u0000\u0012\u0002H\f`\u0010¨\u0006\u0011"}, m8860e = {"filterIsInstance", "Lkotlin/sequences/Sequence;", "R", "klass", "Ljava/lang/Class;", "filterIsInstanceTo", "C", "", "destination", "(Lkotlin/sequences/Sequence;Ljava/util/Collection;Ljava/lang/Class;)Ljava/util/Collection;", "toSortedSet", "Ljava/util/SortedSet;", TessBaseAPI.f9204e, "", "comparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "kotlin-stdlib"}, m8859f = "kotlin/sequences/SequencesKt", m8857h = 1)
/* renamed from: z1.coi */
/* loaded from: classes3.dex */
public class _SequencesJvm extends coh {

    /* compiled from: _SequencesJvm.kt */
    @Metadata(m8864a = 3, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\n¢\u0006\u0002\b\u0005"}, m8860e = {"<anonymous>", "", "R", "it", "", "invoke"})
    /* renamed from: z1.coi$a */
    /* loaded from: classes3.dex */
    static final class C5032a extends Lambda implements chd<Object, Boolean> {
        final /* synthetic */ Class $klass;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C5032a(Class cls) {
            super(1);
            this.$klass = cls;
        }

        /* JADX WARN: Type inference failed for: r2v1, types: [boolean, java.lang.Boolean] */
        @Override // p110z1.chd
        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final Boolean invoke2(@dbs Object obj) {
            return this.$klass.isInstance(obj);
        }
    }

    @NotNull
    /* renamed from: a */
    public static final <R> Sequence<R> m4434a(@NotNull Sequence<?> cobVar, @NotNull Class<R> cls) {
        cji.m5162f(cobVar, "$this$filterIsInstance");
        cji.m5162f(cls, "klass");
        Sequence<R> j = coe.m4324j(cobVar, new C5032a(cls));
        if (j != null) {
            return j;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.sequences.Sequence<R>");
    }

    @NotNull
    /* renamed from: a */
    public static final <C extends Collection<? super R>, R> C m4433a(@NotNull Sequence<?> cobVar, @NotNull C c, @NotNull Class<R> cls) {
        cji.m5162f(cobVar, "$this$filterIsInstanceTo");
        cji.m5162f(c, "destination");
        cji.m5162f(cls, "klass");
        Iterator<?> a = cobVar.mo3707a();
        while (a.hasNext()) {
            Object next = a.next();
            if (cls.isInstance(next)) {
                c.add(next);
            }
        }
        return c;
    }

    @NotNull
    /* renamed from: e */
    public static final <T extends Comparable<? super T>> SortedSet<T> m4431e(@NotNull Sequence<? extends T> cobVar) {
        cji.m5162f(cobVar, "$this$toSortedSet");
        return (SortedSet) coe.m4356c((Sequence) cobVar, new TreeSet());
    }

    @NotNull
    /* renamed from: a */
    public static final <T> SortedSet<T> m4432a(@NotNull Sequence<? extends T> cobVar, @NotNull Comparator<? super T> comparator) {
        cji.m5162f(cobVar, "$this$toSortedSet");
        cji.m5162f(comparator, "comparator");
        return (SortedSet) coe.m4356c((Sequence) cobVar, new TreeSet(comparator));
    }
}
