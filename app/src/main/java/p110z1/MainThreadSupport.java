package p110z1;

import android.os.Looper;

/* renamed from: z1.czk */
/* loaded from: classes3.dex */
public interface MainThreadSupport {
    /* renamed from: a */
    Poster mo3396a(EventBus czfVar);

    /* renamed from: a */
    boolean mo3397a();

    /* compiled from: MainThreadSupport.java */
    /* renamed from: z1.czk$a */
    /* loaded from: classes3.dex */
    public static class C5219a implements MainThreadSupport {

        /* renamed from: a */
        private final Looper f21164a;

        public C5219a(Looper looper) {
            this.f21164a = looper;
        }

        @Override // p110z1.MainThreadSupport
        /* renamed from: a */
        public boolean mo3397a() {
            return this.f21164a == Looper.myLooper();
        }

        @Override // p110z1.MainThreadSupport
        /* renamed from: a */
        public Poster mo3396a(EventBus czfVar) {
            return new HandlerPoster(czfVar, this.f21164a, 10);
        }
    }
}
