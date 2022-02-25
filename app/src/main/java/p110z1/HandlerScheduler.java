package p110z1;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import java.util.concurrent.TimeUnit;
import p110z1.Scheduler;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: z1.atg */
/* loaded from: classes3.dex */
public final class HandlerScheduler extends Scheduler {

    /* renamed from: b */
    private final Handler f17497b;

    /* renamed from: c */
    private final boolean f17498c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public HandlerScheduler(Handler handler, boolean z) {
        this.f17497b = handler;
        this.f17498c = z;
    }

    @Override // p110z1.Scheduler
    @SuppressLint({"NewApi"})
    /* renamed from: a */
    public Disposable mo9480a(Runnable runnable, long j, TimeUnit timeUnit) {
        if (runnable == null) {
            throw new NullPointerException("run == null");
        } else if (timeUnit != null) {
            RunnableC3888b bVar = new RunnableC3888b(this.f17497b, RxJavaPlugins.m9213a(runnable));
            Message obtain = Message.obtain(this.f17497b, bVar);
            if (this.f17498c) {
                obtain.setAsynchronous(true);
            }
            this.f17497b.sendMessageDelayed(obtain, timeUnit.toMillis(j));
            return bVar;
        } else {
            throw new NullPointerException("unit == null");
        }
    }

    @Override // p110z1.Scheduler
    /* renamed from: b */
    public Scheduler.AbstractC3881c mo9034b() {
        return new C3887a(this.f17497b, this.f17498c);
    }

    /* compiled from: HandlerScheduler.java */
    /* renamed from: z1.atg$a */
    /* loaded from: classes3.dex */
    private static final class C3887a extends Scheduler.AbstractC3881c {

        /* renamed from: a */
        private final Handler f17499a;

        /* renamed from: b */
        private final boolean f17500b;

        /* renamed from: c */
        private volatile boolean f17501c;

        C3887a(Handler handler, boolean z) {
            this.f17499a = handler;
            this.f17500b = z;
        }

        @Override // p110z1.Scheduler.AbstractC3881c
        @SuppressLint({"NewApi"})
        /* renamed from: a */
        public Disposable mo9030a(Runnable runnable, long j, TimeUnit timeUnit) {
            if (runnable == null) {
                throw new NullPointerException("run == null");
            } else if (timeUnit == null) {
                throw new NullPointerException("unit == null");
            } else if (this.f17501c) {
                return Disposables.m9989b();
            } else {
                RunnableC3888b bVar = new RunnableC3888b(this.f17499a, RxJavaPlugins.m9213a(runnable));
                Message obtain = Message.obtain(this.f17499a, bVar);
                obtain.obj = this;
                if (this.f17500b) {
                    obtain.setAsynchronous(true);
                }
                this.f17499a.sendMessageDelayed(obtain, timeUnit.toMillis(j));
                if (!this.f17501c) {
                    return bVar;
                }
                this.f17499a.removeCallbacks(bVar);
                return Disposables.m9989b();
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f17501c = true;
            this.f17499a.removeCallbacksAndMessages(this);
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f17501c;
        }
    }

    /* compiled from: HandlerScheduler.java */
    /* renamed from: z1.atg$b */
    /* loaded from: classes3.dex */
    private static final class RunnableC3888b implements Runnable, Disposable {

        /* renamed from: a */
        private final Handler f17502a;

        /* renamed from: b */
        private final Runnable f17503b;

        /* renamed from: c */
        private volatile boolean f17504c;

        RunnableC3888b(Handler handler, Runnable runnable) {
            this.f17502a = handler;
            this.f17503b = runnable;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.f17503b.run();
            } catch (Throwable th) {
                RxJavaPlugins.m9212a(th);
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f17502a.removeCallbacks(this);
            this.f17504c = true;
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f17504c;
        }
    }
}
