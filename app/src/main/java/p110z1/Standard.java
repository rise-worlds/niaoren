package p110z1;

@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00060\u0001j\u0002`\u0002B\u000f\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, m8860e = {"Lkotlin/NotImplementedError;", "Ljava/lang/Error;", "Lkotlin/Error;", "message", "", "(Ljava/lang/String;)V", "kotlin-stdlib"})
/* renamed from: z1.bwh */
/* loaded from: classes3.dex */
public final class Standard extends Error {
    public Standard() {
        this(null, 1, null);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Standard(@NotNull String str) {
        super(str);
        cji.m5162f(str, "message");
    }

    public /* synthetic */ Standard(String str, int i, DefaultConstructorMarker civVar) {
        this((i & 1) != 0 ? "An operation is not implemented." : str);
    }
}
