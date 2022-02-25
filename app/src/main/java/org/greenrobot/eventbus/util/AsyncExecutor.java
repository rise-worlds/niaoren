package org.greenrobot.eventbus.util;

import java.lang.reflect.Constructor;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import p110z1.EventBus;

/* renamed from: org.greenrobot.eventbus.util.a */
/* loaded from: classes2.dex */
public class AsyncExecutor {

    /* renamed from: a */
    private final Executor f14808a;

    /* renamed from: b */
    private final Constructor<?> f14809b;

    /* renamed from: c */
    private final EventBus f14810c;

    /* renamed from: d */
    private final Object f14811d;

    /* compiled from: AsyncExecutor.java */
    /* renamed from: org.greenrobot.eventbus.util.a$b */
    /* loaded from: classes2.dex */
    public interface AbstractC3256b {
        /* renamed from: a */
        void m14795a() throws Exception;
    }

    /* compiled from: AsyncExecutor.java */
    /* renamed from: org.greenrobot.eventbus.util.a$a */
    /* loaded from: classes2.dex */
    public static class C3255a {

        /* renamed from: a */
        private Executor f14814a;

        /* renamed from: b */
        private Class<?> f14815b;

        /* renamed from: c */
        private EventBus f14816c;

        private C3255a() {
        }

        /* renamed from: a */
        public C3255a m14797a(Executor executor) {
            this.f14814a = executor;
            return this;
        }

        /* renamed from: a */
        public C3255a m14799a(Class<?> cls) {
            this.f14815b = cls;
            return this;
        }

        /* renamed from: a */
        public C3255a m14796a(EventBus czfVar) {
            this.f14816c = czfVar;
            return this;
        }

        /* renamed from: a */
        public AsyncExecutor m14800a() {
            return m14798a((Object) null);
        }

        /* renamed from: a */
        public AsyncExecutor m14798a(Object obj) {
            if (this.f14816c == null) {
                this.f14816c = EventBus.m3448a();
            }
            if (this.f14814a == null) {
                this.f14814a = Executors.newCachedThreadPool();
            }
            if (this.f14815b == null) {
                this.f14815b = ThrowableFailureEvent.class;
            }
            return new AsyncExecutor(this.f14814a, this.f14816c, this.f14815b, obj);
        }
    }

    /* renamed from: a */
    public static C3255a m14806a() {
        return new C3255a();
    }

    /* renamed from: b */
    public static AsyncExecutor m14803b() {
        return new C3255a().m14800a();
    }

    private AsyncExecutor(Executor executor, EventBus czfVar, Class<?> cls, Object obj) {
        this.f14808a = executor;
        this.f14810c = czfVar;
        this.f14811d = obj;
        try {
            this.f14809b = cls.getConstructor(Throwable.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Failure event class must have a constructor with one parameter of type Throwable", e);
        }
    }

    /* renamed from: a */
    public void m14805a(final AbstractC3256b bVar) {
        this.f14808a.execute(new Runnable() { // from class: org.greenrobot.eventbus.util.a.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    bVar.m14795a();
                } catch (Exception e) {
                    try {
                        Object newInstance = AsyncExecutor.this.f14809b.newInstance(e);
                        if (newInstance instanceof HasExecutionScope) {
                            ((HasExecutionScope) newInstance).mo14776a(AsyncExecutor.this.f14811d);
                        }
                        AsyncExecutor.this.f14810c.m3427d(newInstance);
                    } catch (Exception e2) {
                        AsyncExecutor.this.f14810c.m3424f().mo3398a(Level.SEVERE, "Original exception:", e);
                        throw new RuntimeException("Could not create failure event", e2);
                    }
                }
            }
        });
    }
}
