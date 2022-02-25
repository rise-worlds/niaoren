package p110z1;

import android.support.annotation.RestrictTo;
import java.util.concurrent.Callable;

/* compiled from: Functions.java */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* renamed from: z1.xf */
/* loaded from: classes3.dex */
public final class C5593xf {

    /* renamed from: a */
    public static final Callable<Boolean> f23666a;

    /* renamed from: b */
    public static final Predicate<Object> f23667b;

    /* renamed from: c */
    private static final CallableC5594a f23668c = new CallableC5594a(true);

    static {
        CallableC5594a aVar = f23668c;
        f23666a = aVar;
        f23667b = aVar;
    }

    /* compiled from: Functions.java */
    /* renamed from: z1.xf$a */
    /* loaded from: classes3.dex */
    private static final class CallableC5594a implements Callable<Boolean>, Predicate<Object> {

        /* renamed from: a */
        private final Boolean f23669a;

        CallableC5594a(Boolean bool) {
            this.f23669a = bool;
        }

        /* renamed from: a */
        public Boolean call() {
            return this.f23669a;
        }

        @Override // p110z1.Predicate
        public boolean test(Object obj) {
            return this.f23669a.booleanValue();
        }
    }

    private C5593xf() {
        throw new AssertionError("No instances.");
    }
}
