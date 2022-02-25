package com.youth.banner;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import java.lang.ref.WeakReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* renamed from: com.youth.banner.e */
/* loaded from: classes2.dex */
public class WeakHandler {
    @VisibleForTesting

    /* renamed from: a */
    final C3051a f14586a;

    /* renamed from: b */
    private final Handler.Callback f14587b;

    /* renamed from: c */
    private final HandlerC3052b f14588c;

    /* renamed from: d */
    private Lock f14589d;

    public WeakHandler() {
        this.f14589d = new ReentrantLock();
        this.f14586a = new C3051a(this.f14589d, null);
        this.f14587b = null;
        this.f14588c = new HandlerC3052b();
    }

    public WeakHandler(@Nullable Handler.Callback callback) {
        this.f14589d = new ReentrantLock();
        this.f14586a = new C3051a(this.f14589d, null);
        this.f14587b = callback;
        this.f14588c = new HandlerC3052b(new WeakReference(callback));
    }

    public WeakHandler(@NonNull Looper looper) {
        this.f14589d = new ReentrantLock();
        this.f14586a = new C3051a(this.f14589d, null);
        this.f14587b = null;
        this.f14588c = new HandlerC3052b(looper);
    }

    public WeakHandler(@NonNull Looper looper, @NonNull Handler.Callback callback) {
        this.f14589d = new ReentrantLock();
        this.f14586a = new C3051a(this.f14589d, null);
        this.f14587b = callback;
        this.f14588c = new HandlerC3052b(looper, new WeakReference(callback));
    }

    /* renamed from: a */
    public final boolean m14918a(@NonNull Runnable runnable) {
        return this.f14588c.post(m14905d(runnable));
    }

    /* renamed from: a */
    public final boolean m14917a(@NonNull Runnable runnable, long j) {
        return this.f14588c.postAtTime(m14905d(runnable), j);
    }

    /* renamed from: a */
    public final boolean m14915a(Runnable runnable, Object obj, long j) {
        return this.f14588c.postAtTime(m14905d(runnable), obj, j);
    }

    /* renamed from: b */
    public final boolean m14908b(Runnable runnable, long j) {
        return this.f14588c.postDelayed(m14905d(runnable), j);
    }

    /* renamed from: b */
    public final boolean m14909b(Runnable runnable) {
        return this.f14588c.postAtFrontOfQueue(m14905d(runnable));
    }

    /* renamed from: c */
    public final void m14906c(Runnable runnable) {
        RunnableC3053c a = this.f14586a.m14902a(runnable);
        if (a != null) {
            this.f14588c.removeCallbacks(a);
        }
    }

    /* renamed from: a */
    public final void m14916a(Runnable runnable, Object obj) {
        RunnableC3053c a = this.f14586a.m14902a(runnable);
        if (a != null) {
            this.f14588c.removeCallbacks(a, obj);
        }
    }

    /* renamed from: a */
    public final boolean m14921a(Message message) {
        return this.f14588c.sendMessage(message);
    }

    /* renamed from: a */
    public final boolean m14924a(int i) {
        return this.f14588c.sendEmptyMessage(i);
    }

    /* renamed from: a */
    public final boolean m14923a(int i, long j) {
        return this.f14588c.sendEmptyMessageDelayed(i, j);
    }

    /* renamed from: b */
    public final boolean m14913b(int i, long j) {
        return this.f14588c.sendEmptyMessageAtTime(i, j);
    }

    /* renamed from: a */
    public final boolean m14920a(Message message, long j) {
        return this.f14588c.sendMessageDelayed(message, j);
    }

    /* renamed from: b */
    public boolean m14910b(Message message, long j) {
        return this.f14588c.sendMessageAtTime(message, j);
    }

    /* renamed from: b */
    public final boolean m14911b(Message message) {
        return this.f14588c.sendMessageAtFrontOfQueue(message);
    }

    /* renamed from: b */
    public final void m14914b(int i) {
        this.f14588c.removeMessages(i);
    }

    /* renamed from: a */
    public final void m14922a(int i, Object obj) {
        this.f14588c.removeMessages(i, obj);
    }

    /* renamed from: a */
    public final void m14919a(Object obj) {
        this.f14588c.removeCallbacksAndMessages(obj);
    }

    /* renamed from: c */
    public final boolean m14907c(int i) {
        return this.f14588c.hasMessages(i);
    }

    /* renamed from: b */
    public final boolean m14912b(int i, Object obj) {
        return this.f14588c.hasMessages(i, obj);
    }

    /* renamed from: a */
    public final Looper m14925a() {
        return this.f14588c.getLooper();
    }

    /* renamed from: d */
    private RunnableC3053c m14905d(@NonNull Runnable runnable) {
        if (runnable != null) {
            C3051a aVar = new C3051a(this.f14589d, runnable);
            this.f14586a.m14903a(aVar);
            return aVar.f14593d;
        }
        throw new NullPointerException("Runnable can't be null");
    }

    /* compiled from: WeakHandler.java */
    /* renamed from: com.youth.banner.e$b */
    /* loaded from: classes2.dex */
    private static class HandlerC3052b extends Handler {

        /* renamed from: a */
        private final WeakReference<Handler.Callback> f14595a;

        HandlerC3052b() {
            this.f14595a = null;
        }

        HandlerC3052b(WeakReference<Handler.Callback> weakReference) {
            this.f14595a = weakReference;
        }

        HandlerC3052b(Looper looper) {
            super(looper);
            this.f14595a = null;
        }

        HandlerC3052b(Looper looper, WeakReference<Handler.Callback> weakReference) {
            super(looper);
            this.f14595a = weakReference;
        }

        @Override // android.os.Handler
        public void handleMessage(@NonNull Message message) {
            Handler.Callback callback;
            WeakReference<Handler.Callback> weakReference = this.f14595a;
            if (weakReference != null && (callback = weakReference.get()) != null) {
                callback.handleMessage(message);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: WeakHandler.java */
    /* renamed from: com.youth.banner.e$c */
    /* loaded from: classes2.dex */
    public static class RunnableC3053c implements Runnable {

        /* renamed from: a */
        private final WeakReference<Runnable> f14596a;

        /* renamed from: b */
        private final WeakReference<C3051a> f14597b;

        RunnableC3053c(WeakReference<Runnable> weakReference, WeakReference<C3051a> weakReference2) {
            this.f14596a = weakReference;
            this.f14597b = weakReference2;
        }

        @Override // java.lang.Runnable
        public void run() {
            Runnable runnable = this.f14596a.get();
            C3051a aVar = this.f14597b.get();
            if (aVar != null) {
                aVar.m14904a();
            }
            if (runnable != null) {
                runnable.run();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: WeakHandler.java */
    /* renamed from: com.youth.banner.e$a */
    /* loaded from: classes2.dex */
    public static class C3051a {
        @Nullable

        /* renamed from: a */
        C3051a f14590a;
        @Nullable

        /* renamed from: b */
        C3051a f14591b;
        @NonNull

        /* renamed from: c */
        final Runnable f14592c;
        @NonNull

        /* renamed from: d */
        final RunnableC3053c f14593d;
        @NonNull

        /* renamed from: e */
        Lock f14594e;

        public C3051a(@NonNull Lock lock, @NonNull Runnable runnable) {
            this.f14592c = runnable;
            this.f14594e = lock;
            this.f14593d = new RunnableC3053c(new WeakReference(runnable), new WeakReference(this));
        }

        /* JADX WARN: Finally extract failed */
        /* renamed from: a */
        public RunnableC3053c m14904a() {
            this.f14594e.lock();
            try {
                if (this.f14591b != null) {
                    this.f14591b.f14590a = this.f14590a;
                }
                if (this.f14590a != null) {
                    this.f14590a.f14591b = this.f14591b;
                }
                this.f14591b = null;
                this.f14590a = null;
                this.f14594e.unlock();
                return this.f14593d;
            } catch (Throwable th) {
                this.f14594e.unlock();
                throw th;
            }
        }

        /* renamed from: a */
        public void m14903a(@NonNull C3051a aVar) {
            this.f14594e.lock();
            try {
                if (this.f14590a != null) {
                    this.f14590a.f14591b = aVar;
                }
                aVar.f14590a = this.f14590a;
                this.f14590a = aVar;
                aVar.f14591b = this;
            } finally {
                this.f14594e.unlock();
            }
        }

        @Nullable
        /* renamed from: a */
        public RunnableC3053c m14902a(Runnable runnable) {
            this.f14594e.lock();
            try {
                for (C3051a aVar = this.f14590a; aVar != null; aVar = aVar.f14590a) {
                    if (aVar.f14592c == runnable) {
                        return aVar.m14904a();
                    }
                }
                this.f14594e.unlock();
                return null;
            } finally {
                this.f14594e.unlock();
            }
        }
    }
}
