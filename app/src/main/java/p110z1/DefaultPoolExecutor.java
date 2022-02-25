package p110z1;

import com.alibaba.android.arouter.facade.template.ILogger;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* renamed from: z1.r */
/* loaded from: classes3.dex */
public class DefaultPoolExecutor extends ThreadPoolExecutor {

    /* renamed from: a */
    private static final int f23051a = Runtime.getRuntime().availableProcessors();

    /* renamed from: b */
    private static final int f23052b = f23051a + 1;

    /* renamed from: c */
    private static final int f23053c = f23052b;

    /* renamed from: d */
    private static final long f23054d = 30;

    /* renamed from: e */
    private static DefaultPoolExecutor f23055e;

    /* renamed from: a */
    public static DefaultPoolExecutor m1225a() {
        if (f23055e == null) {
            synchronized (DefaultPoolExecutor.class) {
                if (f23055e == null) {
                    f23055e = new DefaultPoolExecutor(f23052b, f23053c, f23054d, TimeUnit.SECONDS, new ArrayBlockingQueue(64), new DefaultThreadFactory());
                }
            }
        }
        return f23055e;
    }

    private DefaultPoolExecutor(int i, int i2, long j, TimeUnit timeUnit, BlockingQueue<Runnable> blockingQueue, ThreadFactory threadFactory) {
        super(i, i2, j, timeUnit, blockingQueue, threadFactory, new RejectedExecutionHandler() { // from class: z1.r.1
            @Override // java.util.concurrent.RejectedExecutionHandler
            public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
                ARouter.f22668c.error("ARouter::", "Task rejected, too many task!");
            }
        });
    }

    @Override // java.util.concurrent.ThreadPoolExecutor
    protected void afterExecute(Runnable runnable, Throwable e) {
        super.afterExecute(runnable, e);
        if (e == null && (runnable instanceof Future)) {
            try {
                ((Future) runnable).get();
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
            } catch (CancellationException e2) {
                e = e2;
            } catch (ExecutionException e3) {
                e = e3.getCause();
            }
        }
        if (e != null) {
            ILogger iLogger = ARouter.f22668c;
            iLogger.warning("ARouter::", "Running task appeared exception! Thread [" + Thread.currentThread().getName() + "], because [" + e.getMessage() + "]\n" + C5599y.m73a(e.getStackTrace()));
        }
    }
}
