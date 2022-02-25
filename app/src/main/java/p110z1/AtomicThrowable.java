package p110z1;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bsn */
/* loaded from: classes3.dex */
public final class AtomicThrowable extends AtomicReference<Throwable> {
    private static final long serialVersionUID = 3949248817947090603L;

    public boolean addThrowable(Throwable th) {
        return ExceptionHelper.m9430a(this, th);
    }

    public Throwable terminate() {
        return ExceptionHelper.m9431a(this);
    }

    public boolean isTerminated() {
        return get() == ExceptionHelper.f20064a;
    }
}
