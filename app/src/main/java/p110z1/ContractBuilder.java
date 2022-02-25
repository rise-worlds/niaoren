package p110z1;

import org.apache.tools.ant.types.selectors.SizeSelector;

@bwy(m8750a = "1.3")
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J&\u0010\u0002\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00040\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bH'J\b\u0010\t\u001a\u00020\nH'J\u0012\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001H'J\b\u0010\f\u001a\u00020\rH'¨\u0006\u000e"}, m8860e = {"Lkotlin/contracts/ContractBuilder;", "", "callsInPlace", "Lkotlin/contracts/CallsInPlace;", "R", "lambda", "Lkotlin/Function;", "kind", "Lkotlin/contracts/InvocationKind;", "returns", "Lkotlin/contracts/Returns;", SizeSelector.SIZE_KEY, "returnsNotNull", "Lkotlin/contracts/ReturnsNotNull;", "kotlin-stdlib"})
@cci
@ceu
/* renamed from: z1.ccf */
/* loaded from: classes3.dex */
public interface ContractBuilder {
    @ceu
    @NotNull
    /* renamed from: a */
    <R> Effect m5657a(@NotNull bvt<? extends R> bvtVar, @NotNull ccj ccjVar);

    @ceu
    @NotNull
    /* renamed from: a */
    cck m5659a();

    @ceu
    @NotNull
    /* renamed from: a */
    cck m5658a(@dbs Object obj);

    @ceu
    @NotNull
    /* renamed from: b */
    ccl m5656b();

    /* compiled from: ContractBuilder.kt */
    @Metadata(m8864a = 3, m8863b = {1, 1, 15}, m8862c = {1, 0, 3})
    /* renamed from: z1.ccf$a */
    /* loaded from: classes3.dex */
    public static final class C4903a {
        /* renamed from: a */
        public static /* synthetic */ Effect m5655a(ContractBuilder ccfVar, bvt bvtVar, ccj ccjVar, int i, Object obj) {
            if (obj == null) {
                if ((i & 2) != 0) {
                    ccjVar = ccj.UNKNOWN;
                }
                return ccfVar.m5657a(bvtVar, ccjVar);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: callsInPlace");
        }
    }
}
