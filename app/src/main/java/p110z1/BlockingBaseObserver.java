package p110z1;

import java.util.concurrent.CountDownLatch;

/* renamed from: z1.awb */
/* loaded from: classes3.dex */
public abstract class BlockingBaseObserver<T> extends CountDownLatch implements Observer<T>, Disposable {

    /* renamed from: a */
    T f17614a;

    /* renamed from: b */
    Throwable f17615b;

    /* renamed from: c */
    Disposable f17616c;

    /* renamed from: d */
    volatile boolean f17617d;

    public BlockingBaseObserver() {
        super(1);
    }

    @Override // p110z1.Observer
    public final void onSubscribe(Disposable atrVar) {
        this.f17616c = atrVar;
        if (this.f17617d) {
            atrVar.dispose();
        }
    }

    @Override // p110z1.Observer
    public final void onComplete() {
        countDown();
    }

    @Override // p110z1.Disposable
    public final void dispose() {
        this.f17617d = true;
        Disposable atrVar = this.f17616c;
        if (atrVar != null) {
            atrVar.dispose();
        }
    }

    @Override // p110z1.Disposable
    public final boolean isDisposed() {
        return this.f17617d;
    }

    /* renamed from: a */
    public final T m9850a() {
        if (getCount() != 0) {
            try {
                BlockingHelper.m9444a();
                await();
            } catch (InterruptedException e) {
                dispose();
                throw ExceptionHelper.m9432a(e);
            }
        }
        Throwable th = this.f17615b;
        if (th == null) {
            return this.f17614a;
        }
        throw ExceptionHelper.m9432a(th);
    }
}
