package p110z1;

/* renamed from: z1.ber */
/* loaded from: classes3.dex */
abstract class AbstractMaybeWithUpstream<T, R> extends Maybe<R> implements HasUpstreamMaybeSource<T> {

    /* renamed from: a */
    protected final MaybeSource<T> f18529a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AbstractMaybeWithUpstream(MaybeSource<T> asiVar) {
        this.f18529a = asiVar;
    }

    @Override // p110z1.HasUpstreamMaybeSource
    /* renamed from: s_ */
    public final MaybeSource<T> mo9687s_() {
        return this.f18529a;
    }
}
