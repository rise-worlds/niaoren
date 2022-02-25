package p110z1;

import android.os.Looper;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: z1.atc */
/* loaded from: classes3.dex */
public abstract class MainThreadDisposable implements Disposable {

    /* renamed from: a */
    private final AtomicBoolean f17491a = new AtomicBoolean();

    /* renamed from: a */
    protected abstract void mo33a();

    /* renamed from: b */
    public static void m10016b() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new IllegalStateException("Expected to be called on the main thread but was " + Thread.currentThread().getName());
        }
    }

    @Override // p110z1.Disposable
    public final boolean isDisposed() {
        return this.f17491a.get();
    }

    @Override // p110z1.Disposable
    public final void dispose() {
        if (!this.f17491a.compareAndSet(false, true)) {
            return;
        }
        if (Looper.myLooper() == Looper.getMainLooper()) {
            mo33a();
        } else {
            AndroidSchedulers.m10005a().mo9481a(new Runnable() { // from class: z1.atc.1
                @Override // java.lang.Runnable
                public void run() {
                    MainThreadDisposable.this.mo33a();
                }
            });
        }
    }
}
