package p110z1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.brb */
/* loaded from: classes3.dex */
public final class SchedulerPoolFactory {

    /* renamed from: a */
    static final String f19942a = "rx2.purge-enabled";

    /* renamed from: b */
    public static final boolean f19943b;

    /* renamed from: c */
    static final String f19944c = "rx2.purge-period-seconds";

    /* renamed from: d */
    public static final int f19945d;

    /* renamed from: e */
    static final AtomicReference<ScheduledExecutorService> f19946e = new AtomicReference<>();

    /* renamed from: f */
    static final Map<ScheduledThreadPoolExecutor, Object> f19947f = new ConcurrentHashMap();

    private SchedulerPoolFactory() {
        throw new IllegalStateException("No instances!");
    }

    static {
        Properties properties = System.getProperties();
        C4727a aVar = new C4727a();
        aVar.m9487a(properties);
        f19943b = aVar.f19948a;
        f19945d = aVar.f19949b;
        m9492a();
    }

    /* renamed from: a */
    public static void m9492a() {
        m9490a(f19943b);
    }

    /* renamed from: a */
    static void m9490a(boolean z) {
        if (z) {
            while (true) {
                ScheduledExecutorService scheduledExecutorService = f19946e.get();
                if (scheduledExecutorService == null) {
                    ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(1, new RxThreadFactory("RxSchedulerPurge"));
                    if (f19946e.compareAndSet(scheduledExecutorService, newScheduledThreadPool)) {
                        RunnableC4728b bVar = new RunnableC4728b();
                        int i = f19945d;
                        newScheduledThreadPool.scheduleAtFixedRate(bVar, i, i, TimeUnit.SECONDS);
                        return;
                    }
                    newScheduledThreadPool.shutdownNow();
                } else {
                    return;
                }
            }
        }
    }

    /* renamed from: b */
    public static void m9488b() {
        ScheduledExecutorService andSet = f19946e.getAndSet(null);
        if (andSet != null) {
            andSet.shutdownNow();
        }
        f19947f.clear();
    }

    /* compiled from: SchedulerPoolFactory.java */
    /* renamed from: z1.brb$a */
    /* loaded from: classes3.dex */
    static final class C4727a {

        /* renamed from: a */
        boolean f19948a;

        /* renamed from: b */
        int f19949b;

        C4727a() {
        }

        /* renamed from: a */
        void m9487a(Properties properties) {
            if (properties.containsKey(SchedulerPoolFactory.f19942a)) {
                this.f19948a = Boolean.parseBoolean(properties.getProperty(SchedulerPoolFactory.f19942a));
            } else {
                this.f19948a = true;
            }
            if (!this.f19948a || !properties.containsKey(SchedulerPoolFactory.f19944c)) {
                this.f19949b = 1;
                return;
            }
            try {
                this.f19949b = Integer.parseInt(properties.getProperty(SchedulerPoolFactory.f19944c));
            } catch (NumberFormatException unused) {
                this.f19949b = 1;
            }
        }
    }

    /* renamed from: a */
    public static ScheduledExecutorService m9491a(ThreadFactory threadFactory) {
        ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(1, threadFactory);
        m9489a(f19943b, newScheduledThreadPool);
        return newScheduledThreadPool;
    }

    /* renamed from: a */
    static void m9489a(boolean z, ScheduledExecutorService scheduledExecutorService) {
        if (z && (scheduledExecutorService instanceof ScheduledThreadPoolExecutor)) {
            f19947f.put((ScheduledThreadPoolExecutor) scheduledExecutorService, scheduledExecutorService);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: SchedulerPoolFactory.java */
    /* renamed from: z1.brb$b */
    /* loaded from: classes3.dex */
    public static final class RunnableC4728b implements Runnable {
        RunnableC4728b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Iterator it = new ArrayList(SchedulerPoolFactory.f19947f.keySet()).iterator();
            while (it.hasNext()) {
                ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = (ScheduledThreadPoolExecutor) it.next();
                if (scheduledThreadPoolExecutor.isShutdown()) {
                    SchedulerPoolFactory.f19947f.remove(scheduledThreadPoolExecutor);
                } else {
                    scheduledThreadPoolExecutor.purge();
                }
            }
        }
    }
}
