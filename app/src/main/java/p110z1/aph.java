package p110z1;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/* compiled from: ThreadUtils.java */
/* renamed from: z1.aph */
/* loaded from: classes3.dex */
public final class aph {

    /* renamed from: c */
    private static final byte f17120c = -2;

    /* renamed from: d */
    private static final byte f17121d = -8;

    /* renamed from: e */
    private static final byte f17122e = -4;

    /* renamed from: g */
    private static final byte f17124g = -1;

    /* renamed from: a */
    private static final int f17118a = Runtime.getRuntime().availableProcessors();

    /* renamed from: b */
    private static final Map<AbstractRunnableC3836c, ScheduledExecutorService> f17119b = new ConcurrentHashMap();

    /* renamed from: f */
    private static final Map<Integer, Map<Integer, ExecutorService>> f17123f = new ConcurrentHashMap();

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ThreadUtils.java */
    /* renamed from: z1.aph$a */
    /* loaded from: classes3.dex */
    public static class C3834a {

        /* renamed from: a */
        private static final Handler f17131a;

        private C3834a() {
        }

        static {
            Looper looper;
            try {
                looper = Looper.getMainLooper();
            } catch (Exception unused) {
                looper = null;
            }
            if (looper != null) {
                f17131a = new Handler(looper);
            } else {
                f17131a = null;
            }
        }

        /* renamed from: a */
        static void m11716a(Runnable runnable) {
            Handler handler = f17131a;
            if (handler != null) {
                handler.post(runnable);
            } else {
                runnable.run();
            }
        }
    }

    /* compiled from: ThreadUtils.java */
    /* renamed from: z1.aph$c */
    /* loaded from: classes3.dex */
    public static abstract class AbstractRunnableC3836c<T> implements Runnable {

        /* renamed from: a */
        private static final int f17132a = 2;

        /* renamed from: b */
        private static final int f17133b = 1;

        /* renamed from: c */
        private static final int f17134c = 3;

        /* renamed from: d */
        private static final int f17135d = 0;

        /* renamed from: e */
        private boolean f17136e;

        /* renamed from: f */
        private volatile int f17137f = 0;

        /* renamed from: a */
        public abstract T mo11551a() throws Throwable;

        /* renamed from: a */
        public abstract void mo11549a(T t);

        /* renamed from: a */
        public abstract void mo11548a(Throwable th);

        /* renamed from: b */
        public abstract void mo11547b();

        @Override // java.lang.Runnable
        public void run() {
            try {
                final T a = mo11551a();
                if (this.f17137f != 0) {
                    return;
                }
                if (this.f17136e) {
                    C3834a.m11716a(new Runnable() { // from class: z1.aph.c.1
                        /* JADX WARN: Multi-variable type inference failed */
                        @Override // java.lang.Runnable
                        public void run() {
                            AbstractRunnableC3836c.this.mo11549a((AbstractRunnableC3836c) a);
                        }
                    });
                    return;
                }
                this.f17137f = 1;
                C3834a.m11716a(new Runnable() { // from class: z1.aph.c.2
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // java.lang.Runnable
                    public void run() {
                        AbstractRunnableC3836c.this.mo11549a((AbstractRunnableC3836c) a);
                        aph.m11719h(AbstractRunnableC3836c.this);
                    }
                });
            } catch (Throwable th) {
                if (this.f17137f == 0) {
                    this.f17137f = 3;
                    C3834a.m11716a(new Runnable() { // from class: z1.aph.c.3
                        @Override // java.lang.Runnable
                        public void run() {
                            AbstractRunnableC3836c.this.mo11548a(th);
                            aph.m11719h(AbstractRunnableC3836c.this);
                        }
                    });
                }
            }
        }

        /* renamed from: c */
        public void m11714c() {
            if (this.f17137f == 0) {
                this.f17137f = 2;
                C3834a.m11716a(new Runnable() { // from class: z1.aph.c.4
                    @Override // java.lang.Runnable
                    public void run() {
                        AbstractRunnableC3836c.this.mo11547b();
                        aph.m11719h(AbstractRunnableC3836c.this);
                    }
                });
            }
        }
    }

    /* compiled from: ThreadUtils.java */
    /* renamed from: z1.aph$b */
    /* loaded from: classes3.dex */
    public static abstract class AbstractC3835b<T> extends AbstractRunnableC3836c<T> {
        @Override // p110z1.aph.AbstractRunnableC3836c
        /* renamed from: b */
        public void mo11547b() {
            Log.e("ThreadUtils", "onCancel: " + Thread.currentThread());
        }

        @Override // p110z1.aph.AbstractRunnableC3836c
        /* renamed from: a */
        public void mo11548a(Throwable th) {
            Log.e("ThreadUtils", "onFail: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ThreadUtils.java */
    /* renamed from: z1.aph$d */
    /* loaded from: classes3.dex */
    public static final class ThreadFactoryC3841d extends AtomicLong implements ThreadFactory {
        private static final AtomicInteger POOL_NUMBER = new AtomicInteger(1);
        private final ThreadGroup group;
        private final String namePrefix;
        private final int priority;

        ThreadFactoryC3841d(String str, int i) {
            SecurityManager securityManager = System.getSecurityManager();
            this.group = securityManager != null ? securityManager.getThreadGroup() : Thread.currentThread().getThreadGroup();
            this.namePrefix = str + "-pool-" + POOL_NUMBER.getAndIncrement() + "-thread-";
            this.priority = i;
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            if (runnable != null) {
                ThreadGroup threadGroup = this.group;
                Thread thread = new Thread(threadGroup, runnable, this.namePrefix + getAndIncrement(), 0L) { // from class: z1.aph.d.1
                    @Override // java.lang.Thread, java.lang.Runnable
                    public void run() {
                        try {
                            super.run();
                        } catch (Throwable th) {
                            Log.e("ThreadUtils", "Request threw uncaught throwable", th);
                        }
                    }
                };
                if (thread.isDaemon()) {
                    thread.setDaemon(false);
                }
                thread.setPriority(this.priority);
                return thread;
            }
            throw new NullPointerException("Argument 'r' of type Runnable (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    /* renamed from: a */
    public static boolean m11782a() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    /* renamed from: a */
    public static ExecutorService m11781a(int i) {
        return m11726f(i);
    }

    /* renamed from: a */
    public static ExecutorService m11780a(int i, int i2) {
        return m11761b(i, i2);
    }

    /* renamed from: b */
    public static ExecutorService m11763b() {
        return m11726f(-1);
    }

    /* renamed from: b */
    public static ExecutorService m11762b(int i) {
        return m11761b(-1, i);
    }

    /* renamed from: c */
    public static ExecutorService m11749c() {
        return m11726f(-2);
    }

    /* renamed from: c */
    public static ExecutorService m11748c(int i) {
        return m11761b(-2, i);
    }

    /* renamed from: d */
    public static ExecutorService m11739d() {
        return m11726f(-2);
    }

    /* renamed from: d */
    public static ExecutorService m11738d(int i) {
        return m11761b(-2, i);
    }

    /* renamed from: e */
    public static ExecutorService m11731e() {
        return m11726f(-8);
    }

    /* renamed from: e */
    public static ExecutorService m11730e(int i) {
        return m11761b(-8, i);
    }

    /* renamed from: a */
    public static <T> void m11779a(int i, AbstractRunnableC3836c<T> cVar) {
        m11758b(m11726f(i), cVar);
    }

    /* renamed from: a */
    public static <T> void m11778a(int i, AbstractRunnableC3836c<T> cVar, int i2) {
        m11758b(m11761b(i, i2), cVar);
    }

    /* renamed from: a */
    public static <T> void m11775a(int i, AbstractRunnableC3836c<T> cVar, long j, TimeUnit timeUnit) {
        m11746c(m11726f(i), cVar, j, timeUnit);
    }

    /* renamed from: a */
    public static <T> void m11774a(int i, AbstractRunnableC3836c<T> cVar, long j, TimeUnit timeUnit, int i2) {
        m11746c(m11761b(i, i2), cVar, j, timeUnit);
    }

    /* renamed from: b */
    public static <T> void m11760b(int i, AbstractRunnableC3836c<T> cVar, long j, TimeUnit timeUnit) {
        m11757b(m11726f(i), cVar, 0L, j, timeUnit);
    }

    /* renamed from: b */
    public static <T> void m11759b(int i, AbstractRunnableC3836c<T> cVar, long j, TimeUnit timeUnit, int i2) {
        m11757b(m11761b(i, i2), cVar, 0L, j, timeUnit);
    }

    /* renamed from: a */
    public static <T> void m11777a(int i, AbstractRunnableC3836c<T> cVar, long j, long j2, TimeUnit timeUnit) {
        m11757b(m11726f(i), cVar, j, j2, timeUnit);
    }

    /* renamed from: a */
    public static <T> void m11776a(int i, AbstractRunnableC3836c<T> cVar, long j, long j2, TimeUnit timeUnit, int i2) {
        m11757b(m11761b(i, i2), cVar, j, j2, timeUnit);
    }

    /* renamed from: a */
    public static <T> void m11769a(AbstractRunnableC3836c<T> cVar) {
        m11758b(m11726f(-1), cVar);
    }

    /* renamed from: a */
    public static <T> void m11768a(AbstractRunnableC3836c<T> cVar, int i) {
        m11758b(m11761b(-1, i), cVar);
    }

    /* renamed from: a */
    public static <T> void m11765a(AbstractRunnableC3836c<T> cVar, long j, TimeUnit timeUnit) {
        m11746c(m11726f(-1), cVar, j, timeUnit);
    }

    /* renamed from: a */
    public static <T> void m11764a(AbstractRunnableC3836c<T> cVar, long j, TimeUnit timeUnit, int i) {
        m11746c(m11761b(-1, i), cVar, j, timeUnit);
    }

    /* renamed from: b */
    public static <T> void m11751b(AbstractRunnableC3836c<T> cVar, long j, TimeUnit timeUnit) {
        m11757b(m11726f(-1), cVar, 0L, j, timeUnit);
    }

    /* renamed from: b */
    public static <T> void m11750b(AbstractRunnableC3836c<T> cVar, long j, TimeUnit timeUnit, int i) {
        m11757b(m11761b(-1, i), cVar, 0L, j, timeUnit);
    }

    /* renamed from: a */
    public static <T> void m11767a(AbstractRunnableC3836c<T> cVar, long j, long j2, TimeUnit timeUnit) {
        m11757b(m11726f(-1), cVar, j, j2, timeUnit);
    }

    /* renamed from: a */
    public static <T> void m11766a(AbstractRunnableC3836c<T> cVar, long j, long j2, TimeUnit timeUnit, int i) {
        m11757b(m11761b(-1, i), cVar, j, j2, timeUnit);
    }

    /* renamed from: b */
    public static <T> void m11755b(AbstractRunnableC3836c<T> cVar) {
        m11758b(m11726f(-2), cVar);
    }

    /* renamed from: b */
    public static <T> void m11754b(AbstractRunnableC3836c<T> cVar, int i) {
        m11758b(m11761b(-2, i), cVar);
    }

    /* renamed from: c */
    public static <T> void m11741c(AbstractRunnableC3836c<T> cVar, long j, TimeUnit timeUnit) {
        m11746c(m11726f(-2), cVar, j, timeUnit);
    }

    /* renamed from: c */
    public static <T> void m11740c(AbstractRunnableC3836c<T> cVar, long j, TimeUnit timeUnit, int i) {
        m11746c(m11761b(-2, i), cVar, j, timeUnit);
    }

    /* renamed from: d */
    public static <T> void m11733d(AbstractRunnableC3836c<T> cVar, long j, TimeUnit timeUnit) {
        m11757b(m11726f(-2), cVar, 0L, j, timeUnit);
    }

    /* renamed from: d */
    public static <T> void m11732d(AbstractRunnableC3836c<T> cVar, long j, TimeUnit timeUnit, int i) {
        m11757b(m11761b(-2, i), cVar, 0L, j, timeUnit);
    }

    /* renamed from: b */
    public static <T> void m11753b(AbstractRunnableC3836c<T> cVar, long j, long j2, TimeUnit timeUnit) {
        m11757b(m11726f(-2), cVar, j, j2, timeUnit);
    }

    /* renamed from: b */
    public static <T> void m11752b(AbstractRunnableC3836c<T> cVar, long j, long j2, TimeUnit timeUnit, int i) {
        m11757b(m11761b(-2, i), cVar, j, j2, timeUnit);
    }

    /* renamed from: c */
    public static <T> void m11745c(AbstractRunnableC3836c<T> cVar) {
        m11758b(m11726f(-4), cVar);
    }

    /* renamed from: c */
    public static <T> void m11744c(AbstractRunnableC3836c<T> cVar, int i) {
        m11758b(m11761b(-4, i), cVar);
    }

    /* renamed from: e */
    public static <T> void m11728e(AbstractRunnableC3836c<T> cVar, long j, TimeUnit timeUnit) {
        m11746c(m11726f(-4), cVar, j, timeUnit);
    }

    /* renamed from: e */
    public static <T> void m11727e(AbstractRunnableC3836c<T> cVar, long j, TimeUnit timeUnit, int i) {
        m11746c(m11761b(-4, i), cVar, j, timeUnit);
    }

    /* renamed from: f */
    public static <T> void m11724f(AbstractRunnableC3836c<T> cVar, long j, TimeUnit timeUnit) {
        m11757b(m11726f(-4), cVar, 0L, j, timeUnit);
    }

    /* renamed from: f */
    public static <T> void m11723f(AbstractRunnableC3836c<T> cVar, long j, TimeUnit timeUnit, int i) {
        m11757b(m11761b(-4, i), cVar, 0L, j, timeUnit);
    }

    /* renamed from: c */
    public static <T> void m11743c(AbstractRunnableC3836c<T> cVar, long j, long j2, TimeUnit timeUnit) {
        m11757b(m11726f(-4), cVar, j, j2, timeUnit);
    }

    /* renamed from: c */
    public static <T> void m11742c(AbstractRunnableC3836c<T> cVar, long j, long j2, TimeUnit timeUnit, int i) {
        m11757b(m11761b(-4, i), cVar, j, j2, timeUnit);
    }

    /* renamed from: d */
    public static <T> void m11737d(AbstractRunnableC3836c<T> cVar) {
        m11758b(m11726f(-8), cVar);
    }

    /* renamed from: d */
    public static <T> void m11736d(AbstractRunnableC3836c<T> cVar, int i) {
        m11758b(m11761b(-8, i), cVar);
    }

    /* renamed from: g */
    public static <T> void m11721g(AbstractRunnableC3836c<T> cVar, long j, TimeUnit timeUnit) {
        m11746c(m11726f(-8), cVar, j, timeUnit);
    }

    /* renamed from: g */
    public static <T> void m11720g(AbstractRunnableC3836c<T> cVar, long j, TimeUnit timeUnit, int i) {
        m11746c(m11761b(-8, i), cVar, j, timeUnit);
    }

    /* renamed from: h */
    public static <T> void m11718h(AbstractRunnableC3836c<T> cVar, long j, TimeUnit timeUnit) {
        m11757b(m11726f(-8), cVar, 0L, j, timeUnit);
    }

    /* renamed from: h */
    public static <T> void m11717h(AbstractRunnableC3836c<T> cVar, long j, TimeUnit timeUnit, int i) {
        m11757b(m11761b(-8, i), cVar, 0L, j, timeUnit);
    }

    /* renamed from: d */
    public static <T> void m11735d(AbstractRunnableC3836c<T> cVar, long j, long j2, TimeUnit timeUnit) {
        m11757b(m11726f(-8), cVar, j, j2, timeUnit);
    }

    /* renamed from: d */
    public static <T> void m11734d(AbstractRunnableC3836c<T> cVar, long j, long j2, TimeUnit timeUnit, int i) {
        m11757b(m11761b(-8, i), cVar, j, j2, timeUnit);
    }

    /* renamed from: a */
    public static <T> void m11772a(ExecutorService executorService, AbstractRunnableC3836c<T> cVar) {
        m11758b(executorService, cVar);
    }

    /* renamed from: a */
    public static <T> void m11770a(ExecutorService executorService, AbstractRunnableC3836c<T> cVar, long j, TimeUnit timeUnit) {
        m11746c(executorService, cVar, j, timeUnit);
    }

    /* renamed from: b */
    public static <T> void m11756b(ExecutorService executorService, AbstractRunnableC3836c<T> cVar, long j, TimeUnit timeUnit) {
        m11757b(executorService, cVar, 0L, j, timeUnit);
    }

    /* renamed from: a */
    public static <T> void m11771a(ExecutorService executorService, AbstractRunnableC3836c<T> cVar, long j, long j2, TimeUnit timeUnit) {
        m11757b(executorService, cVar, j, j2, timeUnit);
    }

    /* renamed from: e */
    public static void m11729e(AbstractRunnableC3836c cVar) {
        cVar.m11714c();
    }

    /* renamed from: b */
    private static <T> void m11758b(ExecutorService executorService, AbstractRunnableC3836c<T> cVar) {
        m11746c(executorService, cVar, 0L, TimeUnit.MILLISECONDS);
    }

    /* renamed from: c */
    private static <T> void m11746c(final ExecutorService executorService, final AbstractRunnableC3836c<T> cVar, long j, TimeUnit timeUnit) {
        if (j <= 0) {
            m11722g(cVar).execute(new Runnable() { // from class: z1.aph.1
                @Override // java.lang.Runnable
                public void run() {
                    executorService.execute(cVar);
                }
            });
        } else {
            m11722g(cVar).schedule(new Runnable() { // from class: z1.aph.2
                @Override // java.lang.Runnable
                public void run() {
                    executorService.execute(cVar);
                }
            }, j, timeUnit);
        }
    }

    /* renamed from: b */
    private static <T> void m11757b(final ExecutorService executorService, final AbstractRunnableC3836c<T> cVar, long j, long j2, TimeUnit timeUnit) {
        ((AbstractRunnableC3836c) cVar).f17136e = true;
        m11722g(cVar).scheduleAtFixedRate(new Runnable() { // from class: z1.aph.3
            @Override // java.lang.Runnable
            public void run() {
                executorService.execute(cVar);
            }
        }, j, j2, timeUnit);
    }

    /* renamed from: g */
    private static ScheduledExecutorService m11722g(AbstractRunnableC3836c cVar) {
        ScheduledExecutorService scheduledExecutorService = f17119b.get(cVar);
        if (scheduledExecutorService != null) {
            return scheduledExecutorService;
        }
        ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(1, new ThreadFactoryC3841d("scheduled", 10));
        f17119b.put(cVar, newScheduledThreadPool);
        return newScheduledThreadPool;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: h */
    public static void m11719h(AbstractRunnableC3836c cVar) {
        ScheduledExecutorService scheduledExecutorService = f17119b.get(cVar);
        if (scheduledExecutorService != null) {
            f17119b.remove(cVar);
            m11773a(scheduledExecutorService);
        }
    }

    /* renamed from: f */
    private static ExecutorService m11726f(int i) {
        return m11761b(i, 5);
    }

    /* renamed from: b */
    private static ExecutorService m11761b(int i, int i2) {
        Map<Integer, ExecutorService> map = f17123f.get(Integer.valueOf(i));
        if (map == null) {
            ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
            ExecutorService c = m11747c(i, i2);
            concurrentHashMap.put(Integer.valueOf(i2), c);
            f17123f.put(Integer.valueOf(i), concurrentHashMap);
            return c;
        }
        ExecutorService executorService = map.get(Integer.valueOf(i2));
        if (executorService != null) {
            return executorService;
        }
        ExecutorService c2 = m11747c(i, i2);
        map.put(Integer.valueOf(i2), c2);
        return c2;
    }

    /* renamed from: c */
    private static ExecutorService m11747c(int i, int i2) {
        if (i == -8) {
            int i3 = f17118a;
            return new ThreadPoolExecutor(i3 + 1, (i3 * 2) + 1, 30L, TimeUnit.SECONDS, new LinkedBlockingQueue(128), new ThreadFactoryC3841d("cpu", i2));
        } else if (i != -4) {
            switch (i) {
                case -2:
                    return Executors.newCachedThreadPool(new ThreadFactoryC3841d("cached", i2));
                case -1:
                    return Executors.newSingleThreadExecutor(new ThreadFactoryC3841d("single", i2));
                default:
                    return Executors.newFixedThreadPool(i, new ThreadFactoryC3841d("fixed(" + i + ")", i2));
            }
        } else {
            int i4 = f17118a;
            return new ThreadPoolExecutor((i4 * 2) + 1, (i4 * 2) + 1, 30L, TimeUnit.SECONDS, new LinkedBlockingQueue(128), new ThreadFactoryC3841d("io", i2));
        }
    }

    /* renamed from: a */
    private static void m11773a(ExecutorService executorService) {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(60L, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
                if (!executorService.awaitTermination(60L, TimeUnit.SECONDS)) {
                    System.err.println("Pool did not terminate");
                }
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }
}
