package p110z1;

import org.apache.tools.ant.types.selectors.SizeSelector;

@Metadata(m8864a = 5, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\u001a\u0011\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0087\b\u001a\u001f\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0087\b¨\u0006\u0007"}, m8860e = {"assert", "", SizeSelector.SIZE_KEY, "", "lazyMessage", "Lkotlin/Function0;", "", "kotlin-stdlib"}, m8859f = "kotlin/PreconditionsKt", m8857h = 1)
/* renamed from: z1.bwr */
/* loaded from: classes3.dex */
class AssertionsJVM {
    @cey
    /* renamed from: a */
    private static final void m8779a(boolean z) {
        if (byj.f20419a && !z) {
            throw new AssertionError("Assertion failed");
        }
    }

    @cey
    /* renamed from: a */
    private static final void m8778a(boolean z, chc<? extends Object> chcVar) {
        if (byj.f20419a && !z) {
            throw new AssertionError(chcVar.invoke());
        }
    }
}
