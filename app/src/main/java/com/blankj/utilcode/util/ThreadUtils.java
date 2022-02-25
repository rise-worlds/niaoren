package com.blankj.utilcode.util;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.util.Log;
import java.lang.Thread;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/* renamed from: com.blankj.utilcode.util.az */
/* loaded from: classes.dex */
public final class ThreadUtils {

    /* renamed from: a */
    private static final HashMap<Integer, Map<Integer, ExecutorService>> f6772a = new HashMap<>();

    /* renamed from: b */
    private static final Map<AbstractRunnableC0995b, ScheduledExecutorService> f6773b = new HashMap();

    /* renamed from: c */
    private static final int f6774c = Runtime.getRuntime().availableProcessors();

    /* renamed from: d */
    private static final ScheduledExecutorService f6775d = Executors.newScheduledThreadPool(f6774c, new ThreadFactoryC1000c("scheduled", 10));

    /* renamed from: e */
    private static final byte f6776e = -1;

    /* renamed from: f */
    private static final byte f6777f = -2;

    /* renamed from: g */
    private static final byte f6778g = -4;

    /* renamed from: h */
    private static final byte f6779h = -8;

    /* renamed from: i */
    private static Executor f6780i;

    /* renamed from: a */
    public static boolean m23219a() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    /* renamed from: a */
    public static ExecutorService m23218a(@IntRange(from = 1) int i) {
        return m23155g(i);
    }

    /* renamed from: a */
    public static ExecutorService m23217a(@IntRange(from = 1) int i, @IntRange(from = 1, m25695to = 10) int i2) {
        return m23196b(i, i2);
    }

    /* renamed from: b */
    public static ExecutorService m23198b() {
        return m23155g(-1);
    }

    /* renamed from: b */
    public static ExecutorService m23197b(@IntRange(from = 1, m25695to = 10) int i) {
        return m23196b(-1, i);
    }

    /* renamed from: c */
    public static ExecutorService m23184c() {
        return m23155g(-2);
    }

    /* renamed from: c */
    public static ExecutorService m23183c(@IntRange(from = 1, m25695to = 10) int i) {
        return m23196b(-2, i);
    }

    /* renamed from: d */
    public static ExecutorService m23174d() {
        return m23155g(-4);
    }

    /* renamed from: d */
    public static ExecutorService m23173d(@IntRange(from = 1, m25695to = 10) int i) {
        return m23196b(-4, i);
    }

    /* renamed from: e */
    public static ExecutorService m23166e() {
        return m23155g(-8);
    }

    /* renamed from: e */
    public static ExecutorService m23165e(@IntRange(from = 1, m25695to = 10) int i) {
        return m23196b(-8, i);
    }

    /* renamed from: a */
    public static <T> void m23216a(@IntRange(from = 1) int i, AbstractRunnableC0995b<T> bVar) {
        m23187b(m23155g(i), bVar);
    }

    /* renamed from: a */
    public static <T> void m23215a(@IntRange(from = 1) int i, AbstractRunnableC0995b<T> bVar, @IntRange(from = 1, m25695to = 10) int i2) {
        m23187b(m23196b(i, i2), bVar);
    }

    /* renamed from: a */
    public static <T> void m23212a(@IntRange(from = 1) int i, AbstractRunnableC0995b<T> bVar, long j, TimeUnit timeUnit) {
        m23175c(m23155g(i), bVar, j, timeUnit);
    }

    /* renamed from: a */
    public static <T> void m23211a(@IntRange(from = 1) int i, AbstractRunnableC0995b<T> bVar, long j, TimeUnit timeUnit, @IntRange(from = 1, m25695to = 10) int i2) {
        m23175c(m23196b(i, i2), bVar, j, timeUnit);
    }

    /* renamed from: b */
    public static <T> void m23195b(@IntRange(from = 1) int i, AbstractRunnableC0995b<T> bVar, long j, TimeUnit timeUnit) {
        m23186b(m23155g(i), bVar, 0L, j, timeUnit);
    }

    /* renamed from: b */
    public static <T> void m23194b(@IntRange(from = 1) int i, AbstractRunnableC0995b<T> bVar, long j, TimeUnit timeUnit, @IntRange(from = 1, m25695to = 10) int i2) {
        m23186b(m23196b(i, i2), bVar, 0L, j, timeUnit);
    }

    /* renamed from: a */
    public static <T> void m23214a(@IntRange(from = 1) int i, AbstractRunnableC0995b<T> bVar, long j, long j2, TimeUnit timeUnit) {
        m23186b(m23155g(i), bVar, j, j2, timeUnit);
    }

    /* renamed from: a */
    public static <T> void m23213a(@IntRange(from = 1) int i, AbstractRunnableC0995b<T> bVar, long j, long j2, TimeUnit timeUnit, @IntRange(from = 1, m25695to = 10) int i2) {
        m23186b(m23196b(i, i2), bVar, j, j2, timeUnit);
    }

    /* renamed from: a */
    public static <T> void m23210a(final AbstractRunnableC0995b<T> bVar) {
        m23154g(bVar).execute(new Runnable() { // from class: com.blankj.utilcode.util.az.1
            @Override // java.lang.Runnable
            public void run() {
                ThreadUtils.m23155g(-1).execute(AbstractRunnableC0995b.this);
            }
        });
    }

    /* renamed from: a */
    public static <T> void m23209a(AbstractRunnableC0995b<T> bVar, @IntRange(from = 1, m25695to = 10) int i) {
        m23187b(m23196b(-1, i), bVar);
    }

    /* renamed from: a */
    public static <T> void m23206a(AbstractRunnableC0995b<T> bVar, long j, TimeUnit timeUnit) {
        m23175c(m23155g(-1), bVar, j, timeUnit);
    }

    /* renamed from: a */
    public static <T> void m23205a(AbstractRunnableC0995b<T> bVar, long j, TimeUnit timeUnit, @IntRange(from = 1, m25695to = 10) int i) {
        m23175c(m23196b(-1, i), bVar, j, timeUnit);
    }

    /* renamed from: b */
    public static <T> void m23189b(AbstractRunnableC0995b<T> bVar, long j, TimeUnit timeUnit) {
        m23186b(m23155g(-1), bVar, 0L, j, timeUnit);
    }

    /* renamed from: b */
    public static <T> void m23188b(AbstractRunnableC0995b<T> bVar, long j, TimeUnit timeUnit, @IntRange(from = 1, m25695to = 10) int i) {
        m23186b(m23196b(-1, i), bVar, 0L, j, timeUnit);
    }

    /* renamed from: a */
    public static <T> void m23208a(AbstractRunnableC0995b<T> bVar, long j, long j2, TimeUnit timeUnit) {
        m23186b(m23155g(-1), bVar, j, j2, timeUnit);
    }

    /* renamed from: a */
    public static <T> void m23207a(AbstractRunnableC0995b<T> bVar, long j, long j2, TimeUnit timeUnit, @IntRange(from = 1, m25695to = 10) int i) {
        m23186b(m23196b(-1, i), bVar, j, j2, timeUnit);
    }

    /* renamed from: b */
    public static <T> void m23193b(AbstractRunnableC0995b<T> bVar) {
        m23187b(m23155g(-2), bVar);
    }

    /* renamed from: b */
    public static <T> void m23192b(AbstractRunnableC0995b<T> bVar, @IntRange(from = 1, m25695to = 10) int i) {
        m23187b(m23196b(-2, i), bVar);
    }

    /* renamed from: c */
    public static <T> void m23177c(AbstractRunnableC0995b<T> bVar, long j, TimeUnit timeUnit) {
        m23175c(m23155g(-2), bVar, j, timeUnit);
    }

    /* renamed from: c */
    public static <T> void m23176c(AbstractRunnableC0995b<T> bVar, long j, TimeUnit timeUnit, @IntRange(from = 1, m25695to = 10) int i) {
        m23175c(m23196b(-2, i), bVar, j, timeUnit);
    }

    /* renamed from: d */
    public static <T> void m23168d(AbstractRunnableC0995b<T> bVar, long j, TimeUnit timeUnit) {
        m23186b(m23155g(-2), bVar, 0L, j, timeUnit);
    }

    /* renamed from: d */
    public static <T> void m23167d(AbstractRunnableC0995b<T> bVar, long j, TimeUnit timeUnit, @IntRange(from = 1, m25695to = 10) int i) {
        m23186b(m23196b(-2, i), bVar, 0L, j, timeUnit);
    }

    /* renamed from: b */
    public static <T> void m23191b(AbstractRunnableC0995b<T> bVar, long j, long j2, TimeUnit timeUnit) {
        m23186b(m23155g(-2), bVar, j, j2, timeUnit);
    }

    /* renamed from: b */
    public static <T> void m23190b(AbstractRunnableC0995b<T> bVar, long j, long j2, TimeUnit timeUnit, @IntRange(from = 1, m25695to = 10) int i) {
        m23186b(m23196b(-2, i), bVar, j, j2, timeUnit);
    }

    /* renamed from: c */
    public static <T> void m23181c(AbstractRunnableC0995b<T> bVar) {
        m23187b(m23155g(-4), bVar);
    }

    /* renamed from: c */
    public static <T> void m23180c(AbstractRunnableC0995b<T> bVar, @IntRange(from = 1, m25695to = 10) int i) {
        m23187b(m23196b(-4, i), bVar);
    }

    /* renamed from: e */
    public static <T> void m23163e(AbstractRunnableC0995b<T> bVar, long j, TimeUnit timeUnit) {
        m23175c(m23155g(-4), bVar, j, timeUnit);
    }

    /* renamed from: e */
    public static <T> void m23162e(AbstractRunnableC0995b<T> bVar, long j, TimeUnit timeUnit, @IntRange(from = 1, m25695to = 10) int i) {
        m23175c(m23196b(-4, i), bVar, j, timeUnit);
    }

    /* renamed from: f */
    public static <T> void m23158f(AbstractRunnableC0995b<T> bVar, long j, TimeUnit timeUnit) {
        m23186b(m23155g(-4), bVar, 0L, j, timeUnit);
    }

    /* renamed from: f */
    public static <T> void m23157f(AbstractRunnableC0995b<T> bVar, long j, TimeUnit timeUnit, @IntRange(from = 1, m25695to = 10) int i) {
        m23186b(m23196b(-4, i), bVar, 0L, j, timeUnit);
    }

    /* renamed from: c */
    public static <T> void m23179c(AbstractRunnableC0995b<T> bVar, long j, long j2, TimeUnit timeUnit) {
        m23186b(m23155g(-4), bVar, j, j2, timeUnit);
    }

    /* renamed from: c */
    public static <T> void m23178c(AbstractRunnableC0995b<T> bVar, long j, long j2, TimeUnit timeUnit, @IntRange(from = 1, m25695to = 10) int i) {
        m23186b(m23196b(-4, i), bVar, j, j2, timeUnit);
    }

    /* renamed from: d */
    public static <T> void m23172d(AbstractRunnableC0995b<T> bVar) {
        m23187b(m23155g(-8), bVar);
    }

    /* renamed from: d */
    public static <T> void m23171d(AbstractRunnableC0995b<T> bVar, @IntRange(from = 1, m25695to = 10) int i) {
        m23187b(m23196b(-8, i), bVar);
    }

    /* renamed from: g */
    public static <T> void m23153g(AbstractRunnableC0995b<T> bVar, long j, TimeUnit timeUnit) {
        m23175c(m23155g(-8), bVar, j, timeUnit);
    }

    /* renamed from: g */
    public static <T> void m23152g(AbstractRunnableC0995b<T> bVar, long j, TimeUnit timeUnit, @IntRange(from = 1, m25695to = 10) int i) {
        m23175c(m23196b(-8, i), bVar, j, timeUnit);
    }

    /* renamed from: h */
    public static <T> void m23150h(AbstractRunnableC0995b<T> bVar, long j, TimeUnit timeUnit) {
        m23186b(m23155g(-8), bVar, 0L, j, timeUnit);
    }

    /* renamed from: h */
    public static <T> void m23149h(AbstractRunnableC0995b<T> bVar, long j, TimeUnit timeUnit, @IntRange(from = 1, m25695to = 10) int i) {
        m23186b(m23196b(-8, i), bVar, 0L, j, timeUnit);
    }

    /* renamed from: d */
    public static <T> void m23170d(AbstractRunnableC0995b<T> bVar, long j, long j2, TimeUnit timeUnit) {
        m23186b(m23155g(-8), bVar, j, j2, timeUnit);
    }

    /* renamed from: d */
    public static <T> void m23169d(AbstractRunnableC0995b<T> bVar, long j, long j2, TimeUnit timeUnit, @IntRange(from = 1, m25695to = 10) int i) {
        m23186b(m23196b(-8, i), bVar, j, j2, timeUnit);
    }

    /* renamed from: a */
    public static <T> void m23202a(ExecutorService executorService, AbstractRunnableC0995b<T> bVar) {
        m23187b(executorService, bVar);
    }

    /* renamed from: a */
    public static <T> void m23200a(ExecutorService executorService, AbstractRunnableC0995b<T> bVar, long j, TimeUnit timeUnit) {
        m23175c(executorService, bVar, j, timeUnit);
    }

    /* renamed from: b */
    public static <T> void m23185b(ExecutorService executorService, AbstractRunnableC0995b<T> bVar, long j, TimeUnit timeUnit) {
        m23186b(executorService, bVar, 0L, j, timeUnit);
    }

    /* renamed from: a */
    public static <T> void m23201a(ExecutorService executorService, AbstractRunnableC0995b<T> bVar, long j, long j2, TimeUnit timeUnit) {
        m23186b(executorService, bVar, j, j2, timeUnit);
    }

    /* renamed from: e */
    public static void m23164e(AbstractRunnableC0995b bVar) {
        if (bVar != null) {
            bVar.m23145c();
        }
    }

    /* renamed from: a */
    public static void m23199a(AbstractRunnableC0995b... bVarArr) {
        if (!(bVarArr == null || bVarArr.length == 0)) {
            for (AbstractRunnableC0995b bVar : bVarArr) {
                if (bVar != null) {
                    bVar.m23145c();
                }
            }
        }
    }

    /* renamed from: a */
    public static void m23204a(List<AbstractRunnableC0995b> list) {
        if (!(list == null || list.size() == 0)) {
            for (AbstractRunnableC0995b bVar : list) {
                if (bVar != null) {
                    bVar.m23145c();
                }
            }
        }
    }

    /* renamed from: a */
    public static void m23203a(Executor executor) {
        f6780i = executor;
    }

    /* renamed from: b */
    private static <T> void m23187b(ExecutorService executorService, AbstractRunnableC0995b<T> bVar) {
        m23175c(executorService, bVar, 0L, TimeUnit.MILLISECONDS);
    }

    /* renamed from: c */
    private static <T> void m23175c(final ExecutorService executorService, final AbstractRunnableC0995b<T> bVar, long j, TimeUnit timeUnit) {
        if (j <= 0) {
            m23154g(bVar).execute(new Runnable() { // from class: com.blankj.utilcode.util.az.2
                @Override // java.lang.Runnable
                public void run() {
                    executorService.execute(bVar);
                }
            });
        } else {
            m23154g(bVar).schedule(new Runnable() { // from class: com.blankj.utilcode.util.az.3
                @Override // java.lang.Runnable
                public void run() {
                    executorService.execute(bVar);
                }
            }, j, timeUnit);
        }
    }

    /* renamed from: b */
    private static <T> void m23186b(final ExecutorService executorService, final AbstractRunnableC0995b<T> bVar, long j, long j2, TimeUnit timeUnit) {
        bVar.m23146b(true);
        m23154g(bVar).scheduleAtFixedRate(new Runnable() { // from class: com.blankj.utilcode.util.az.4
            @Override // java.lang.Runnable
            public void run() {
                executorService.execute(bVar);
            }
        }, j, j2, timeUnit);
    }

    /* renamed from: g */
    private static synchronized ScheduledExecutorService m23154g(AbstractRunnableC0995b bVar) {
        ScheduledExecutorService scheduledExecutorService;
        synchronized (ThreadUtils.class) {
            scheduledExecutorService = f6773b.get(bVar);
            if (scheduledExecutorService == null) {
                scheduledExecutorService = Executors.newScheduledThreadPool(1, new ThreadFactoryC1000c("scheduled", 10));
                f6773b.put(bVar, scheduledExecutorService);
            }
        }
        return scheduledExecutorService;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: h */
    public static synchronized void m23151h(AbstractRunnableC0995b bVar) {
        synchronized (ThreadUtils.class) {
            ScheduledExecutorService scheduledExecutorService = f6773b.get(bVar);
            if (scheduledExecutorService != null) {
                f6773b.remove(bVar);
                scheduledExecutorService.shutdownNow();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: g */
    public static ExecutorService m23155g(int i) {
        return m23196b(i, 5);
    }

    /* renamed from: b */
    private static synchronized ExecutorService m23196b(int i, int i2) {
        ExecutorService executorService;
        synchronized (ThreadUtils.class) {
            Map<Integer, ExecutorService> map = f6772a.get(Integer.valueOf(i));
            if (map == null) {
                HashMap hashMap = new HashMap();
                executorService = m23182c(i, i2);
                hashMap.put(Integer.valueOf(i2), executorService);
                f6772a.put(Integer.valueOf(i), hashMap);
            } else {
                executorService = map.get(Integer.valueOf(i2));
                if (executorService == null) {
                    executorService = m23182c(i, i2);
                    map.put(Integer.valueOf(i2), executorService);
                }
            }
        }
        return executorService;
    }

    /* renamed from: c */
    private static ExecutorService m23182c(int i, int i2) {
        if (i == -8) {
            int i3 = f6774c;
            return new ThreadPoolExecutor(i3 + 1, (i3 * 2) + 1, 30L, TimeUnit.SECONDS, new LinkedBlockingQueue(128), new ThreadFactoryC1000c("cpu", i2));
        } else if (i != -4) {
            switch (i) {
                case -2:
                    return new ThreadPoolExecutor(1, Math.max(f6774c * 8, 64), 60L, TimeUnit.SECONDS, new SynchronousQueue(), new ThreadFactoryC1000c("cached", i2, false), new ThreadPoolExecutor.CallerRunsPolicy());
                case -1:
                    return new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), new ThreadFactoryC1000c("single", i2, true));
                default:
                    TimeUnit timeUnit = TimeUnit.MILLISECONDS;
                    LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue(128);
                    return new ThreadPoolExecutor(i, i, 0L, timeUnit, linkedBlockingQueue, new ThreadFactoryC1000c("fixed(" + i + ")", i2), new ThreadPoolExecutor.DiscardOldestPolicy());
            }
        } else {
            int i4 = f6774c;
            return new ThreadPoolExecutor((i4 * 2) + 1, (i4 * 2) + 1, 30L, TimeUnit.SECONDS, new LinkedBlockingQueue(128), new ThreadFactoryC1000c("io", i2));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: g */
    public static Executor m23156g() {
        if (f6780i == null) {
            f6780i = new Executor() { // from class: com.blankj.utilcode.util.az.5

                /* renamed from: a */
                private final Handler f6788a = new Handler(Looper.getMainLooper());

                @Override // java.util.concurrent.Executor
                public void execute(@NonNull Runnable runnable) {
                    if (runnable != null) {
                        this.f6788a.post(runnable);
                        return;
                    }
                    throw new NullPointerException("Argument 'command' of type Runnable (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
                }
            };
        }
        return f6780i;
    }

    /* compiled from: ThreadUtils.java */
    /* renamed from: com.blankj.utilcode.util.az$a */
    /* loaded from: classes.dex */
    public static abstract class AbstractC0994a<T> extends AbstractRunnableC0995b<T> {
        @Override // com.blankj.utilcode.util.ThreadUtils.AbstractRunnableC0995b
        /* renamed from: a */
        public void mo14306a() {
            Log.e("ThreadUtils", "onCancel: " + Thread.currentThread());
        }

        @Override // com.blankj.utilcode.util.ThreadUtils.AbstractRunnableC0995b
        /* renamed from: a */
        public void mo14302a(Throwable th) {
            Log.e("ThreadUtils", "onFail: ", th);
        }
    }

    /* compiled from: ThreadUtils.java */
    /* renamed from: com.blankj.utilcode.util.az$b */
    /* loaded from: classes.dex */
    public static abstract class AbstractRunnableC0995b<T> implements Runnable {

        /* renamed from: a */
        private static final int f6789a = 0;

        /* renamed from: b */
        private static final int f6790b = 1;

        /* renamed from: c */
        private static final int f6791c = 2;

        /* renamed from: d */
        private static final int f6792d = 3;

        /* renamed from: e */
        private static final Object f6793e = "";

        /* renamed from: f */
        private volatile int f6794f = 0;

        /* renamed from: g */
        private volatile boolean f6795g;

        /* renamed from: h */
        private volatile Thread f6796h;

        /* renamed from: a */
        public abstract void mo14306a();

        /* renamed from: a */
        public abstract void mo14303a(T t);

        /* renamed from: a */
        public abstract void mo14302a(Throwable th);

        /* renamed from: b */
        public abstract T mo14301b() throws Throwable;

        @Override // java.lang.Runnable
        public void run() {
            if (this.f6794f == 0) {
                synchronized (f6793e) {
                    this.f6796h = Thread.currentThread();
                }
                try {
                    final T b = mo14301b();
                    if (this.f6794f == 0) {
                        if (this.f6795g) {
                            ThreadUtils.m23156g().execute(new Runnable() { // from class: com.blankj.utilcode.util.az.b.1
                                /* JADX WARN: Multi-variable type inference failed */
                                @Override // java.lang.Runnable
                                public void run() {
                                    AbstractRunnableC0995b.this.mo14303a((AbstractRunnableC0995b) b);
                                }
                            });
                        } else {
                            this.f6794f = 1;
                            ThreadUtils.m23156g().execute(new Runnable() { // from class: com.blankj.utilcode.util.az.b.2
                                /* JADX WARN: Multi-variable type inference failed */
                                @Override // java.lang.Runnable
                                public void run() {
                                    AbstractRunnableC0995b.this.mo14303a((AbstractRunnableC0995b) b);
                                    ThreadUtils.m23151h(AbstractRunnableC0995b.this);
                                }
                            });
                        }
                    }
                } catch (InterruptedException unused) {
                    System.out.println("InterruptedException");
                } catch (Throwable th) {
                    if (this.f6794f == 0) {
                        this.f6794f = 3;
                        ThreadUtils.m23156g().execute(new Runnable() { // from class: com.blankj.utilcode.util.az.b.3
                            @Override // java.lang.Runnable
                            public void run() {
                                AbstractRunnableC0995b.this.mo14302a(th);
                                ThreadUtils.m23151h(AbstractRunnableC0995b.this);
                            }
                        });
                    }
                }
            }
        }

        /* renamed from: c */
        public void m23145c() {
            m23147a(true);
        }

        /* renamed from: a */
        public void m23147a(boolean z) {
            if (this.f6794f == 0) {
                if (z) {
                    synchronized (f6793e) {
                        if (this.f6796h != null) {
                            this.f6796h.interrupt();
                        }
                    }
                }
                this.f6794f = 2;
                ThreadUtils.m23156g().execute(new Runnable() { // from class: com.blankj.utilcode.util.az.b.4
                    @Override // java.lang.Runnable
                    public void run() {
                        AbstractRunnableC0995b.this.mo14306a();
                        ThreadUtils.m23151h(AbstractRunnableC0995b.this);
                    }
                });
            }
        }

        /* renamed from: d */
        public boolean m23144d() {
            return this.f6794f == 2;
        }

        /* renamed from: e */
        public boolean m23143e() {
            return this.f6794f != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: b */
        public void m23146b(boolean z) {
            this.f6795g = z;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ThreadUtils.java */
    /* renamed from: com.blankj.utilcode.util.az$c */
    /* loaded from: classes.dex */
    public static final class ThreadFactoryC1000c extends AtomicLong implements ThreadFactory {
        private static final AtomicInteger POOL_NUMBER = new AtomicInteger(1);
        private static final long serialVersionUID = -9209200509960368598L;
        private final boolean isDaemon;
        private final String namePrefix;
        private final int priority;

        ThreadFactoryC1000c(String str, int i) {
            this(str, i, false);
        }

        ThreadFactoryC1000c(String str, int i, boolean z) {
            this.namePrefix = str + "-pool-" + POOL_NUMBER.getAndIncrement() + "-thread-";
            this.priority = i;
            this.isDaemon = z;
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(@NonNull Runnable runnable) {
            if (runnable != null) {
                Thread thread = new Thread(runnable, this.namePrefix + getAndIncrement()) { // from class: com.blankj.utilcode.util.az.c.1
                    @Override // java.lang.Thread, java.lang.Runnable
                    public void run() {
                        try {
                            super.run();
                        } catch (Throwable th) {
                            Log.e("ThreadUtils", "Request threw uncaught throwable", th);
                        }
                    }
                };
                thread.setDaemon(this.isDaemon);
                thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() { // from class: com.blankj.utilcode.util.az.c.2
                    @Override // java.lang.Thread.UncaughtExceptionHandler
                    public void uncaughtException(Thread thread2, Throwable th) {
                        System.out.println(th);
                    }
                });
                thread.setPriority(this.priority);
                return thread;
            }
            throw new NullPointerException("Argument 'r' of type Runnable (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }
}
