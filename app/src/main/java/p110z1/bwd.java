package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import org.apache.tools.ant.taskdefs.condition.ParserSupports;
import org.apache.tools.ant.types.selectors.SizeSelector;

/* compiled from: Lazy.kt */
@Metadata(m8864a = 5, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001f\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u0002H\u0002¢\u0006\u0002\u0010\u0004\u001a4\u0010\u0005\u001a\u0002H\u0002\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\tH\u0087\n¢\u0006\u0002\u0010\n¨\u0006\u000b"}, m8860e = {"lazyOf", "Lkotlin/Lazy;", TessBaseAPI.f9204e, SizeSelector.SIZE_KEY, "(Ljava/lang/Object;)Lkotlin/Lazy;", "getValue", "thisRef", "", ParserSupports.PROPERTY, "Lkotlin/reflect/KProperty;", "(Lkotlin/Lazy;Ljava/lang/Object;Lkotlin/reflect/KProperty;)Ljava/lang/Object;", "kotlin-stdlib"}, m8859f = "kotlin/LazyKt", m8857h = 1)
/* renamed from: z1.bwd */
/* loaded from: classes3.dex */
class bwd extends LazyJVM {
    @NotNull
    /* renamed from: a */
    public static final <T> bvz<T> m8866a(T t) {
        return new Lazy(t);
    }

    @cey
    /* renamed from: a */
    private static final <T> T m8865a(@NotNull bvz<? extends T> bvzVar, Object obj, cnf<?> cnfVar) {
        cji.m5162f(bvzVar, "$this$getValue");
        return (T) bvzVar.getValue();
    }
}
