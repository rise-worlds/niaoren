package p110z1;

import android.support.annotation.NonNull;
import com.alibaba.android.arouter.facade.template.ILogger;
import java.lang.Thread;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: z1.s */
/* loaded from: classes3.dex */
public class DefaultThreadFactory implements ThreadFactory {

    /* renamed from: a */
    private static final AtomicInteger f23226a = new AtomicInteger(1);

    /* renamed from: b */
    private final AtomicInteger f23227b = new AtomicInteger(1);

    /* renamed from: c */
    private final ThreadGroup f23228c;

    /* renamed from: d */
    private final String f23229d;

    public DefaultThreadFactory() {
        ThreadGroup threadGroup;
        SecurityManager securityManager = System.getSecurityManager();
        if (securityManager != null) {
            threadGroup = securityManager.getThreadGroup();
        } else {
            threadGroup = Thread.currentThread().getThreadGroup();
        }
        this.f23228c = threadGroup;
        this.f23229d = "ARouter task pool No." + f23226a.getAndIncrement() + ", thread No.";
    }

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(@NonNull Runnable runnable) {
        String str = this.f23229d + this.f23227b.getAndIncrement();
        ARouter.f22668c.info("ARouter::", "Thread production, name is [" + str + "]");
        Thread thread = new Thread(this.f23228c, runnable, str, 0L);
        if (thread.isDaemon()) {
            thread.setDaemon(false);
        }
        if (thread.getPriority() != 5) {
            thread.setPriority(5);
        }
        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() { // from class: z1.s.1
            @Override // java.lang.Thread.UncaughtExceptionHandler
            public void uncaughtException(Thread thread2, Throwable th) {
                ILogger iLogger = ARouter.f22668c;
                iLogger.info("ARouter::", "Running task appeared exception! Thread [" + thread2.getName() + "], because [" + th.getMessage() + "]");
            }
        });
        return thread;
    }
}
