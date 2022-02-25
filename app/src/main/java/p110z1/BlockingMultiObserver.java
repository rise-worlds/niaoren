package p110z1;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* renamed from: z1.awe */
/* loaded from: classes3.dex */
public final class BlockingMultiObserver<T> extends CountDownLatch implements CompletableObserver, MaybeObserver<T>, SingleObserver<T> {

    /* renamed from: a */
    T f17618a;

    /* renamed from: b */
    Throwable f17619b;

    /* renamed from: c */
    Disposable f17620c;

    /* renamed from: d */
    volatile boolean f17621d;

    public BlockingMultiObserver() {
        super(1);
    }

    /* renamed from: a */
    void m9849a() {
        this.f17621d = true;
        Disposable atrVar = this.f17620c;
        if (atrVar != null) {
            atrVar.dispose();
        }
    }

    @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
    public void onSubscribe(Disposable atrVar) {
        this.f17620c = atrVar;
        if (this.f17621d) {
            atrVar.dispose();
        }
    }

    @Override // p110z1.MaybeObserver, p110z1.SingleObserver
    public void onSuccess(T t) {
        this.f17618a = t;
        countDown();
    }

    @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
    public void onError(Throwable th) {
        this.f17619b = th;
        countDown();
    }

    @Override // p110z1.CompletableObserver, p110z1.MaybeObserver
    public void onComplete() {
        countDown();
    }

    /* renamed from: b */
    public T m9846b() {
        if (getCount() != 0) {
            try {
                BlockingHelper.m9444a();
                await();
            } catch (InterruptedException e) {
                m9849a();
                throw ExceptionHelper.m9432a(e);
            }
        }
        Throwable th = this.f17619b;
        if (th == null) {
            return this.f17618a;
        }
        throw ExceptionHelper.m9432a(th);
    }

    /* renamed from: a */
    public T m9847a(T t) {
        if (getCount() != 0) {
            try {
                BlockingHelper.m9444a();
                await();
            } catch (InterruptedException e) {
                m9849a();
                throw ExceptionHelper.m9432a(e);
            }
        }
        Throwable th = this.f17619b;
        if (th == null) {
            T t2 = this.f17618a;
            return t2 != null ? t2 : t;
        }
        throw ExceptionHelper.m9432a(th);
    }

    /* renamed from: c */
    public Throwable m9844c() {
        if (getCount() != 0) {
            try {
                BlockingHelper.m9444a();
                await();
            } catch (InterruptedException e) {
                m9849a();
                return e;
            }
        }
        return this.f17619b;
    }

    /* renamed from: a */
    public Throwable m9848a(long j, TimeUnit timeUnit) {
        if (getCount() != 0) {
            try {
                BlockingHelper.m9444a();
                if (!await(j, timeUnit)) {
                    m9849a();
                    throw ExceptionHelper.m9432a(new TimeoutException(ExceptionHelper.m9433a(j, timeUnit)));
                }
            } catch (InterruptedException e) {
                m9849a();
                throw ExceptionHelper.m9432a(e);
            }
        }
        return this.f17619b;
    }

    /* renamed from: b */
    public boolean m9845b(long j, TimeUnit timeUnit) {
        if (getCount() != 0) {
            try {
                BlockingHelper.m9444a();
                if (!await(j, timeUnit)) {
                    m9849a();
                    return false;
                }
            } catch (InterruptedException e) {
                m9849a();
                throw ExceptionHelper.m9432a(e);
            }
        }
        Throwable th = this.f17619b;
        if (th == null) {
            return true;
        }
        throw ExceptionHelper.m9432a(th);
    }
}
