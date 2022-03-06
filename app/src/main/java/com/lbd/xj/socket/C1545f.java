package com.lbd.xj.socket;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.common.utils.log.LogUtils;
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
/* renamed from: com.lbd.xj.socket.f */
/* loaded from: classes.dex */
public final class C1545f {

    /* renamed from: c */
    private static final byte f9579c = -2;

    /* renamed from: d */
    private static final byte f9580d = -8;

    /* renamed from: e */
    private static final byte f9581e = -4;

    /* renamed from: g */
    private static final byte f9583g = -1;

    /* renamed from: a */
    private static final int f9577a = Runtime.getRuntime().availableProcessors();

    /* renamed from: b */
    private static final Map<AbstractRunnableC1552d, ScheduledExecutorService> f9578b = new ConcurrentHashMap();

    /* renamed from: f */
    private static final Map<Integer, Map<Integer, ExecutorService>> f9582f = new ConcurrentHashMap();

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ThreadUtils.java */
    /* renamed from: com.lbd.xj.socket.f$b */
    /* loaded from: classes.dex */
    public static class C1550b {

        /* renamed from: a */
        private static final Handler f9590a;

        private C1550b() {
        }

        static {
            Looper looper;
            try {
                looper = Looper.getMainLooper();
            } catch (Exception unused) {
                looper = null;
            }
            if (looper != null) {
                f9590a = new Handler(looper);
            } else {
                f9590a = null;
            }
        }

        /* renamed from: a */
        static void m19556a(Runnable runnable) {
            Handler handler = f9590a;
            if (handler != null) {
                handler.post(runnable);
            } else {
                runnable.run();
            }
        }
    }

    /* compiled from: ThreadUtils.java */
    /* renamed from: com.lbd.xj.socket.f$d */
    /* loaded from: classes.dex */
    public static abstract class AbstractRunnableC1552d<T> implements Runnable {
        private static final int CANCELLED = 2;
        private static final int COMPLETING = 1;
        private static final int EXCEPTIONAL = 3;
        private static final int NEW = 0;
        private boolean isSchedule;
        private volatile int state = 0;

        @Nullable
        public abstract T doInBackground() throws Throwable;

        public abstract void onCancel();

        public abstract void onFail(Throwable th);

        public abstract void onSuccess(@Nullable T t);

        @Override // java.lang.Runnable
        public void run() {
            try {
                final T doInBackground = doInBackground();
                if (this.state != 0) {
                    return;
                }
                if (this.isSchedule) {
                    C1550b.m19556a(new Runnable() { // from class: com.lbd.xj.socket.f.d.1
                        /* JADX WARN: Multi-variable type inference failed */
                        @Override // java.lang.Runnable
                        public void run() {
                            AbstractRunnableC1552d.this.onSuccess(doInBackground);
                        }
                    });
                    return;
                }
                this.state = 1;
                C1550b.m19556a(new Runnable() { // from class: com.lbd.xj.socket.f.d.2
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // java.lang.Runnable
                    public void run() {
                        AbstractRunnableC1552d.this.onSuccess(doInBackground);
                        C1545f.m19559h(AbstractRunnableC1552d.this);
                    }
                });
            } catch (Throwable th) {
                if (this.state == 0) {
                    this.state = 3;
                    C1550b.m19556a(new Runnable() { // from class: com.lbd.xj.socket.f.d.3
                        @Override // java.lang.Runnable
                        public void run() {
                            AbstractRunnableC1552d.this.onFail(th);
                            C1545f.m19559h(AbstractRunnableC1552d.this);
                        }
                    });
                }
            }
        }

        public void cancel() {
            if (this.state == 0) {
                this.state = 2;
                C1550b.m19556a(new Runnable() { // from class: com.lbd.xj.socket.f.d.4
                    @Override // java.lang.Runnable
                    public void run() {
                        AbstractRunnableC1552d.this.onCancel();
                        C1545f.m19559h(AbstractRunnableC1552d.this);
                    }
                });
            }
        }
    }

    /* compiled from: ThreadUtils.java */
    /* renamed from: com.lbd.xj.socket.f$c */
    /* loaded from: classes.dex */
    public static abstract class AbstractC1551c<T> extends AbstractRunnableC1552d<T> {
        @Override // com.lbd.xj.socket.C1545f.AbstractRunnableC1552d
        public void onCancel() {
            LogUtils.m22036e("ThreadUtils", "onCancel: " + Thread.currentThread());
        }

        @Override // com.lbd.xj.socket.C1545f.AbstractRunnableC1552d
        public void onFail(Throwable th) {
            LogUtils.m22036e("ThreadUtils", "onFail: " + th);
        }
    }

    /* compiled from: ThreadUtils.java */
    /* renamed from: com.lbd.xj.socket.f$a */
    /* loaded from: classes.dex */
    public static abstract class AbstractC1549a extends AbstractRunnableC1552d {
        @Override // com.lbd.xj.socket.C1545f.AbstractRunnableC1552d
        public void onSuccess(@Nullable Object obj) {
        }

        @Override // com.lbd.xj.socket.C1545f.AbstractRunnableC1552d
        public void onCancel() {
            LogUtils.m22036e("ThreadUtils", "onCancel: " + Thread.currentThread());
        }

        @Override // com.lbd.xj.socket.C1545f.AbstractRunnableC1552d
        public void onFail(Throwable th) {
            LogUtils.m22036e("ThreadUtils", "onFail: " + th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ThreadUtils.java */
    /* renamed from: com.lbd.xj.socket.f$e */
    /* loaded from: classes.dex */
    public static final class ThreadFactoryC1557e extends AtomicLong implements ThreadFactory {
        private static final AtomicInteger POOL_NUMBER = new AtomicInteger(1);
        private final ThreadGroup group;
        private final String namePrefix;
        private final int priority;

        ThreadFactoryC1557e(String str, int i) {
            SecurityManager securityManager = System.getSecurityManager();
            this.group = securityManager != null ? securityManager.getThreadGroup() : Thread.currentThread().getThreadGroup();
            this.namePrefix = str + "-pool-" + POOL_NUMBER.getAndIncrement() + "-thread-";
            this.priority = i;
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(@NonNull Runnable runnable) {
            if (runnable != null) {
                ThreadGroup threadGroup = this.group;
                Thread thread = new Thread(threadGroup, runnable, this.namePrefix + getAndIncrement(), 0L) { // from class: com.lbd.xj.socket.f.e.1
                    @Override // java.lang.Thread, java.lang.Runnable
                    public void run() {
                        try {
                            super.run();
                        } catch (Throwable th) {
                            LogUtils.m22036e("ThreadUtils", "Request threw uncaught throwable" + th);
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
    public static boolean m19622a() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    /* renamed from: a */
    public static ExecutorService m19621a(@IntRange(from = 1) int i) {
        return m19566f(i);
    }

    /* renamed from: a */
    public static ExecutorService m19620a(@IntRange(from = 1) int i, @IntRange(from = 1, m25695to = 10) int i2) {
        return m19601b(i, i2);
    }

    /* renamed from: b */
    public static ExecutorService m19603b() {
        return m19566f(-1);
    }

    /* renamed from: b */
    public static ExecutorService m19602b(@IntRange(from = 1, m25695to = 10) int i) {
        return m19601b(-1, i);
    }

    /* renamed from: c */
    public static ExecutorService m19589c() {
        return m19566f(-2);
    }

    /* renamed from: c */
    public static ExecutorService m19588c(@IntRange(from = 1, m25695to = 10) int i) {
        return m19601b(-2, i);
    }

    /* renamed from: d */
    public static ExecutorService m19579d() {
        return m19566f(-2);
    }

    /* renamed from: d */
    public static ExecutorService m19578d(@IntRange(from = 1, m25695to = 10) int i) {
        return m19601b(-2, i);
    }

    /* renamed from: e */
    public static ExecutorService m19571e() {
        return m19566f(-8);
    }

    /* renamed from: e */
    public static ExecutorService m19570e(@IntRange(from = 1, m25695to = 10) int i) {
        return m19601b(-8, i);
    }

    /* renamed from: a */
    public static <T> void m19619a(@IntRange(from = 1) int i, AbstractRunnableC1552d<T> dVar) {
        m19592b(m19566f(i), dVar);
    }

    /* renamed from: a */
    public static <T> void m19618a(@IntRange(from = 1) int i, AbstractRunnableC1552d<T> dVar, @IntRange(from = 1, m25695to = 10) int i2) {
        m19592b(m19601b(i, i2), dVar);
    }

    /* renamed from: a */
    public static <T> void m19615a(@IntRange(from = 1) int i, AbstractRunnableC1552d<T> dVar, long j, TimeUnit timeUnit) {
        m19580c(m19566f(i), dVar, j, timeUnit);
    }

    /* renamed from: a */
    public static <T> void m19614a(@IntRange(from = 1) int i, AbstractRunnableC1552d<T> dVar, long j, TimeUnit timeUnit, @IntRange(from = 1, m25695to = 10) int i2) {
        m19580c(m19601b(i, i2), dVar, j, timeUnit);
    }

    /* renamed from: b */
    public static <T> void m19600b(@IntRange(from = 1) int i, AbstractRunnableC1552d<T> dVar, long j, TimeUnit timeUnit) {
        m19591b(m19566f(i), dVar, 0L, j, timeUnit);
    }

    /* renamed from: b */
    public static <T> void m19599b(@IntRange(from = 1) int i, AbstractRunnableC1552d<T> dVar, long j, TimeUnit timeUnit, @IntRange(from = 1, m25695to = 10) int i2) {
        m19591b(m19601b(i, i2), dVar, 0L, j, timeUnit);
    }

    /* renamed from: a */
    public static <T> void m19617a(@IntRange(from = 1) int i, AbstractRunnableC1552d<T> dVar, long j, long j2, TimeUnit timeUnit) {
        m19591b(m19566f(i), dVar, j, j2, timeUnit);
    }

    /* renamed from: a */
    public static <T> void m19616a(@IntRange(from = 1) int i, AbstractRunnableC1552d<T> dVar, long j, long j2, TimeUnit timeUnit, @IntRange(from = 1, m25695to = 10) int i2) {
        m19591b(m19601b(i, i2), dVar, j, j2, timeUnit);
    }

    /* renamed from: a */
    public static <T> void m19613a(AbstractRunnableC1552d<T> dVar) {
        m19592b(m19566f(-1), dVar);
    }

    /* renamed from: a */
    public static <T> void m19612a(AbstractRunnableC1552d<T> dVar, @IntRange(from = 1, m25695to = 10) int i) {
        m19592b(m19601b(-1, i), dVar);
    }

    /* renamed from: a */
    public static <T> void m19609a(AbstractRunnableC1552d<T> dVar, long j, TimeUnit timeUnit) {
        m19580c(m19566f(-1), dVar, j, timeUnit);
    }

    /* renamed from: a */
    public static <T> void m19608a(AbstractRunnableC1552d<T> dVar, long j, TimeUnit timeUnit, @IntRange(from = 1, m25695to = 10) int i) {
        m19580c(m19601b(-1, i), dVar, j, timeUnit);
    }

    /* renamed from: b */
    public static <T> void m19594b(AbstractRunnableC1552d<T> dVar, long j, TimeUnit timeUnit) {
        m19591b(m19566f(-1), dVar, 0L, j, timeUnit);
    }

    /* renamed from: b */
    public static <T> void m19593b(AbstractRunnableC1552d<T> dVar, long j, TimeUnit timeUnit, @IntRange(from = 1, m25695to = 10) int i) {
        m19591b(m19601b(-1, i), dVar, 0L, j, timeUnit);
    }

    /* renamed from: a */
    public static <T> void m19611a(AbstractRunnableC1552d<T> dVar, long j, long j2, TimeUnit timeUnit) {
        m19591b(m19566f(-1), dVar, j, j2, timeUnit);
    }

    /* renamed from: a */
    public static <T> void m19610a(AbstractRunnableC1552d<T> dVar, long j, long j2, TimeUnit timeUnit, @IntRange(from = 1, m25695to = 10) int i) {
        m19591b(m19601b(-1, i), dVar, j, j2, timeUnit);
    }

    /* renamed from: b */
    public static <T> void m19598b(AbstractRunnableC1552d<T> dVar) {
        m19592b(m19566f(-2), dVar);
    }

    /* renamed from: b */
    public static <T> void m19597b(AbstractRunnableC1552d<T> dVar, @IntRange(from = 1, m25695to = 10) int i) {
        m19592b(m19601b(-2, i), dVar);
    }

    /* renamed from: c */
    public static <T> void m19582c(AbstractRunnableC1552d<T> dVar, long j, TimeUnit timeUnit) {
        m19580c(m19566f(-2), dVar, j, timeUnit);
    }

    /* renamed from: c */
    public static <T> void m19581c(AbstractRunnableC1552d<T> dVar, long j, TimeUnit timeUnit, @IntRange(from = 1, m25695to = 10) int i) {
        m19580c(m19601b(-2, i), dVar, j, timeUnit);
    }

    /* renamed from: d */
    public static <T> void m19573d(AbstractRunnableC1552d<T> dVar, long j, TimeUnit timeUnit) {
        m19591b(m19566f(-2), dVar, 0L, j, timeUnit);
    }

    /* renamed from: d */
    public static <T> void m19572d(AbstractRunnableC1552d<T> dVar, long j, TimeUnit timeUnit, @IntRange(from = 1, m25695to = 10) int i) {
        m19591b(m19601b(-2, i), dVar, 0L, j, timeUnit);
    }

    /* renamed from: b */
    public static <T> void m19596b(AbstractRunnableC1552d<T> dVar, long j, long j2, TimeUnit timeUnit) {
        m19591b(m19566f(-2), dVar, j, j2, timeUnit);
    }

    /* renamed from: b */
    public static <T> void m19595b(AbstractRunnableC1552d<T> dVar, long j, long j2, TimeUnit timeUnit, @IntRange(from = 1, m25695to = 10) int i) {
        m19591b(m19601b(-2, i), dVar, j, j2, timeUnit);
    }

    /* renamed from: c */
    public static <T> void m19586c(AbstractRunnableC1552d<T> dVar) {
        m19592b(m19566f(-4), dVar);
    }

    /* renamed from: c */
    public static <T> void m19585c(AbstractRunnableC1552d<T> dVar, @IntRange(from = 1, m25695to = 10) int i) {
        m19592b(m19601b(-4, i), dVar);
    }

    /* renamed from: e */
    public static <T> void m19568e(AbstractRunnableC1552d<T> dVar, long j, TimeUnit timeUnit) {
        m19580c(m19566f(-4), dVar, j, timeUnit);
    }

    /* renamed from: e */
    public static <T> void m19567e(AbstractRunnableC1552d<T> dVar, long j, TimeUnit timeUnit, @IntRange(from = 1, m25695to = 10) int i) {
        m19580c(m19601b(-4, i), dVar, j, timeUnit);
    }

    /* renamed from: f */
    public static <T> void m19564f(AbstractRunnableC1552d<T> dVar, long j, TimeUnit timeUnit) {
        m19591b(m19566f(-4), dVar, 0L, j, timeUnit);
    }

    /* renamed from: f */
    public static <T> void m19563f(AbstractRunnableC1552d<T> dVar, long j, TimeUnit timeUnit, @IntRange(from = 1, m25695to = 10) int i) {
        m19591b(m19601b(-4, i), dVar, 0L, j, timeUnit);
    }

    /* renamed from: c */
    public static <T> void m19584c(AbstractRunnableC1552d<T> dVar, long j, long j2, TimeUnit timeUnit) {
        m19591b(m19566f(-4), dVar, j, j2, timeUnit);
    }

    /* renamed from: c */
    public static <T> void m19583c(AbstractRunnableC1552d<T> dVar, long j, long j2, TimeUnit timeUnit, @IntRange(from = 1, m25695to = 10) int i) {
        m19591b(m19601b(-4, i), dVar, j, j2, timeUnit);
    }

    /* renamed from: d */
    public static <T> void m19577d(AbstractRunnableC1552d<T> dVar) {
        m19592b(m19566f(-8), dVar);
    }

    /* renamed from: d */
    public static <T> void m19576d(AbstractRunnableC1552d<T> dVar, @IntRange(from = 1, m25695to = 10) int i) {
        m19592b(m19601b(-8, i), dVar);
    }

    /* renamed from: g */
    public static <T> void m19561g(AbstractRunnableC1552d<T> dVar, long j, TimeUnit timeUnit) {
        m19580c(m19566f(-8), dVar, j, timeUnit);
    }

    /* renamed from: g */
    public static <T> void m19560g(AbstractRunnableC1552d<T> dVar, long j, TimeUnit timeUnit, @IntRange(from = 1, m25695to = 10) int i) {
        m19580c(m19601b(-8, i), dVar, j, timeUnit);
    }

    /* renamed from: h */
    public static <T> void m19558h(AbstractRunnableC1552d<T> dVar, long j, TimeUnit timeUnit) {
        m19591b(m19566f(-8), dVar, 0L, j, timeUnit);
    }

    /* renamed from: h */
    public static <T> void m19557h(AbstractRunnableC1552d<T> dVar, long j, TimeUnit timeUnit, @IntRange(from = 1, m25695to = 10) int i) {
        m19591b(m19601b(-8, i), dVar, 0L, j, timeUnit);
    }

    /* renamed from: d */
    public static <T> void m19575d(AbstractRunnableC1552d<T> dVar, long j, long j2, TimeUnit timeUnit) {
        m19591b(m19566f(-8), dVar, j, j2, timeUnit);
    }

    /* renamed from: d */
    public static <T> void m19574d(AbstractRunnableC1552d<T> dVar, long j, long j2, TimeUnit timeUnit, @IntRange(from = 1, m25695to = 10) int i) {
        m19591b(m19601b(-8, i), dVar, j, j2, timeUnit);
    }

    /* renamed from: a */
    public static <T> void m19606a(ExecutorService executorService, AbstractRunnableC1552d<T> dVar) {
        m19592b(executorService, dVar);
    }

    /* renamed from: a */
    public static <T> void m19604a(ExecutorService executorService, AbstractRunnableC1552d<T> dVar, long j, TimeUnit timeUnit) {
        m19580c(executorService, dVar, j, timeUnit);
    }

    /* renamed from: b */
    public static <T> void m19590b(ExecutorService executorService, AbstractRunnableC1552d<T> dVar, long j, TimeUnit timeUnit) {
        m19591b(executorService, dVar, 0L, j, timeUnit);
    }

    /* renamed from: a */
    public static <T> void m19605a(ExecutorService executorService, AbstractRunnableC1552d<T> dVar, long j, long j2, TimeUnit timeUnit) {
        m19591b(executorService, dVar, j, j2, timeUnit);
    }

    /* renamed from: e */
    public static void m19569e(AbstractRunnableC1552d dVar) {
        dVar.cancel();
    }

    /* renamed from: b */
    private static <T> void m19592b(ExecutorService executorService, AbstractRunnableC1552d<T> dVar) {
        m19580c(executorService, dVar, 0L, TimeUnit.MILLISECONDS);
    }

    /* renamed from: c */
    private static <T> void m19580c(final ExecutorService executorService, final AbstractRunnableC1552d<T> dVar, long j, TimeUnit timeUnit) {
        if (j <= 0) {
            m19562g(dVar).execute(new Runnable() { // from class: com.lbd.xj.socket.f.1
                @Override // java.lang.Runnable
                public void run() {
                    executorService.execute(dVar);
                }
            });
        } else {
            m19562g(dVar).schedule(new Runnable() { // from class: com.lbd.xj.socket.f.2
                @Override // java.lang.Runnable
                public void run() {
                    executorService.execute(dVar);
                }
            }, j, timeUnit);
        }
    }

    /* renamed from: b */
    private static <T> void m19591b(final ExecutorService executorService, final AbstractRunnableC1552d<T> dVar, long j, long j2, TimeUnit timeUnit) {
        ((AbstractRunnableC1552d) dVar).isSchedule = true;
        m19562g(dVar).scheduleAtFixedRate(new Runnable() { // from class: com.lbd.xj.socket.f.3
            @Override // java.lang.Runnable
            public void run() {
                executorService.execute(dVar);
            }
        }, j, j2, timeUnit);
    }

    /* renamed from: g */
    private static ScheduledExecutorService m19562g(AbstractRunnableC1552d dVar) {
        ScheduledExecutorService scheduledExecutorService = f9578b.get(dVar);
        if (scheduledExecutorService != null) {
            return scheduledExecutorService;
        }
        ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(1, new ThreadFactoryC1557e("scheduled", 10));
        f9578b.put(dVar, newScheduledThreadPool);
        return newScheduledThreadPool;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: h */
    public static void m19559h(AbstractRunnableC1552d dVar) {
        ScheduledExecutorService scheduledExecutorService = f9578b.get(dVar);
        if (scheduledExecutorService != null) {
            f9578b.remove(dVar);
            m19607a(scheduledExecutorService);
        }
    }

    /* renamed from: f */
    private static ExecutorService m19566f(int i) {
        return m19601b(i, 5);
    }

    /* renamed from: b */
    private static ExecutorService m19601b(int i, int i2) {
        Map<Integer, ExecutorService> map = f9582f.get(Integer.valueOf(i));
        if (map == null) {
            ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
            ExecutorService c = m19587c(i, i2);
            concurrentHashMap.put(Integer.valueOf(i2), c);
            f9582f.put(Integer.valueOf(i), concurrentHashMap);
            return c;
        }
        ExecutorService executorService = map.get(Integer.valueOf(i2));
        if (executorService != null) {
            return executorService;
        }
        ExecutorService c2 = m19587c(i, i2);
        map.put(Integer.valueOf(i2), c2);
        return c2;
    }

    /* renamed from: c */
    private static ExecutorService m19587c(int i, int i2) {
        if (i == -8) {
            int i3 = f9577a;
            return new ThreadPoolExecutor(i3 + 1, (i3 * 2) + 1, 30L, TimeUnit.SECONDS, new LinkedBlockingQueue(128), new ThreadFactoryC1557e("cpu", i2));
        } else if (i != -4) {
            switch (i) {
                case -2:
                    return Executors.newCachedThreadPool(new ThreadFactoryC1557e("cached", i2));
                case -1:
                    return Executors.newSingleThreadExecutor(new ThreadFactoryC1557e("single", i2));
                default:
                    return Executors.newFixedThreadPool(i, new ThreadFactoryC1557e("fixed(" + i + ")", i2));
            }
        } else {
            int i4 = f9577a;
            return new ThreadPoolExecutor((i4 * 2) + 1, (i4 * 2) + 1, 30L, TimeUnit.SECONDS, new LinkedBlockingQueue(128), new ThreadFactoryC1557e("io", i2));
        }
    }

    /* renamed from: a */
    private static void m19607a(ExecutorService executorService) {
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
