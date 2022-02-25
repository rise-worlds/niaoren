package p110z1;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: z1.ahs */
/* loaded from: classes3.dex */
public class FileDownloadExecutors {

    /* renamed from: a */
    private static final int f15839a = 15;

    /* renamed from: a */
    public static ThreadPoolExecutor m13230a(String str) {
        return new ThreadPoolExecutor(0, Integer.MAX_VALUE, 15L, TimeUnit.SECONDS, new SynchronousQueue(), new ThreadFactoryC3476a(str));
    }

    /* renamed from: a */
    public static ThreadPoolExecutor m13232a(int i, String str) {
        return m13231a(i, new LinkedBlockingQueue(), str);
    }

    /* renamed from: a */
    public static ThreadPoolExecutor m13231a(int i, LinkedBlockingQueue<Runnable> linkedBlockingQueue, String str) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(i, i, 15L, TimeUnit.SECONDS, linkedBlockingQueue, new ThreadFactoryC3476a(str));
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        return threadPoolExecutor;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FileDownloadExecutors.java */
    /* renamed from: z1.ahs$a */
    /* loaded from: classes3.dex */
    public static class ThreadFactoryC3476a implements ThreadFactory {

        /* renamed from: a */
        private static final AtomicInteger f15840a = new AtomicInteger(1);

        /* renamed from: b */
        private final String f15841b;

        /* renamed from: d */
        private final AtomicInteger f15843d = new AtomicInteger(1);

        /* renamed from: c */
        private final ThreadGroup f15842c = Thread.currentThread().getThreadGroup();

        ThreadFactoryC3476a(String str) {
            this.f15841b = FileDownloadUtils.m13152l(str);
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            ThreadGroup threadGroup = this.f15842c;
            Thread thread = new Thread(threadGroup, runnable, this.f15841b + this.f15843d.getAndIncrement(), 0L);
            if (thread.isDaemon()) {
                thread.setDaemon(false);
            }
            if (thread.getPriority() != 5) {
                thread.setPriority(5);
            }
            return thread;
        }
    }
}
