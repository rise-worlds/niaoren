package p110z1;

import java.lang.reflect.Type;
import retrofit2.Call;
import retrofit2.CallAdapter;

/* renamed from: z1.xb */
/* loaded from: classes3.dex */
final class RxJava2CallAdapter implements CallAdapter<Object> {

    /* renamed from: a */
    private final Type f23656a;

    /* renamed from: b */
    private final Scheduler f23657b;

    /* renamed from: c */
    private final boolean f23658c;

    /* renamed from: d */
    private final boolean f23659d;

    /* renamed from: e */
    private final boolean f23660e;

    /* renamed from: f */
    private final boolean f23661f;

    /* renamed from: g */
    private final boolean f23662g;

    /* renamed from: h */
    private final boolean f23663h;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RxJava2CallAdapter(Type type, Scheduler astVar, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6) {
        this.f23656a = type;
        this.f23657b = astVar;
        this.f23658c = z;
        this.f23659d = z2;
        this.f23660e = z3;
        this.f23661f = z4;
        this.f23662g = z5;
        this.f23663h = z6;
    }

    @Override // retrofit2.CallAdapter
    public Type responseType() {
        return this.f23656a;
    }

    @Override // retrofit2.CallAdapter
    public <R> Object adapt(Call<R> call) {
        Observable aslVar;
        CallObservable wxVar = new CallObservable(call);
        if (this.f23658c) {
            aslVar = new ResultObservable(wxVar);
        } else {
            aslVar = this.f23659d ? new BodyObservable(wxVar) : wxVar;
        }
        Scheduler astVar = this.f23657b;
        if (astVar != null) {
            aslVar = aslVar.m10350c(astVar);
        }
        if (this.f23660e) {
            return aslVar.m10531a(BackpressureStrategy.LATEST);
        }
        if (this.f23661f) {
            return aslVar.m10618J();
        }
        if (this.f23662g) {
            return aslVar.m10620I();
        }
        return this.f23663h ? aslVar.m10173w() : aslVar;
    }
}
