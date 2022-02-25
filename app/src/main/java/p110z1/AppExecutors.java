package p110z1;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* renamed from: z1.aqw */
/* loaded from: classes3.dex */
public class AppExecutors {

    /* renamed from: a */
    private final ExecutorService f17415a;

    /* renamed from: b */
    private final ExecutorService f17416b;

    /* renamed from: c */
    private final Executor f17417c;

    /* renamed from: d */
    private final ScheduledExecutorService f17418d;

    private AppExecutors() {
        this.f17415a = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), new MyThreadFactory("DiskIoThreadExecutor"));
        this.f17416b = new ThreadPoolExecutor(3, 3, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), new MyThreadFactory("NetworkIOExecutor"));
        this.f17417c = new ExecutorC3875b();
        this.f17418d = new ScheduledThreadPoolExecutor(1, new MyThreadFactory("ScheduledExecutorService"));
    }

    /* renamed from: a */
    public ExecutorService m11426a() {
        return this.f17415a;
    }

    /* renamed from: b */
    public ExecutorService m11425b() {
        return this.f17416b;
    }

    /* renamed from: c */
    public Executor m11424c() {
        return this.f17417c;
    }

    /* renamed from: d */
    public ScheduledExecutorService m11423d() {
        return this.f17418d;
    }

    /* compiled from: AppExecutors.java */
    /* renamed from: z1.aqw$b */
    /* loaded from: classes3.dex */
    private static class ExecutorC3875b implements Executor {

        /* renamed from: a */
        private Handler f17420a;

        private ExecutorC3875b() {
            this.f17420a = new Handler(Looper.getMainLooper());
        }

        @Override // java.util.concurrent.Executor
        public void execute(@NonNull Runnable runnable) {
            this.f17420a.post(runnable);
        }
    }

    /* renamed from: e */
    public static AppExecutors m11422e() {
        return C3874a.f17419a;
    }

    /* compiled from: AppExecutors.java */
    /* renamed from: z1.aqw$a */
    /* loaded from: classes3.dex */
    static class C3874a {

        /* renamed from: a */
        static AppExecutors f17419a = new AppExecutors();

        C3874a() {
        }
    }
}
