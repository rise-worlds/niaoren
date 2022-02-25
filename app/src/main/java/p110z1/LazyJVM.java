package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import org.apache.tools.ant.taskdefs.optional.clearcase.ClearCase;

@Metadata(m8864a = 5, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a \u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004\u001a*\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004\u001a(\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004¨\u0006\t"}, m8860e = {"lazy", "Lkotlin/Lazy;", TessBaseAPI.f9204e, "initializer", "Lkotlin/Function0;", ClearCase.COMMAND_LOCK, "", "mode", "Lkotlin/LazyThreadSafetyMode;", "kotlin-stdlib"}, m8859f = "kotlin/LazyKt", m8857h = 1)
/* renamed from: z1.bwc */
/* loaded from: classes3.dex */
class LazyJVM {
    @NotNull
    /* renamed from: a */
    public static final <T> bvz<T> m8867a(@NotNull chc<? extends T> chcVar) {
        cji.m5162f(chcVar, "initializer");
        return new bxf(chcVar, null, 2, null);
    }

    @NotNull
    /* renamed from: a */
    public static final <T> bvz<T> m8868a(@NotNull bwe bweVar, @NotNull chc<? extends T> chcVar) {
        cji.m5162f(bweVar, "mode");
        cji.m5162f(chcVar, "initializer");
        switch (bwb.f20361a[bweVar.ordinal()]) {
            case 1:
                return new bxf(chcVar, null, 2, null);
            case 2:
                return new bwx(chcVar);
            case 3:
                return new bye(chcVar);
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    @NotNull
    /* renamed from: a */
    public static final <T> bvz<T> m8869a(@dbs Object obj, @NotNull chc<? extends T> chcVar) {
        cji.m5162f(chcVar, "initializer");
        return new bxf(chcVar, obj);
    }
}
