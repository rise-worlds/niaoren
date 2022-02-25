package p110z1;

import java.util.concurrent.CountDownLatch;

/* renamed from: z1.bsp */
/* loaded from: classes3.dex */
public final class BlockingHelper {
    private BlockingHelper() {
        throw new IllegalStateException("No instances!");
    }

    /* renamed from: a */
    public static void m9443a(CountDownLatch countDownLatch, Disposable atrVar) {
        if (countDownLatch.getCount() != 0) {
            try {
                m9444a();
                countDownLatch.await();
            } catch (InterruptedException e) {
                atrVar.dispose();
                Thread.currentThread().interrupt();
                throw new IllegalStateException("Interrupted while waiting for subscription to complete.", e);
            }
        }
    }

    /* renamed from: a */
    public static void m9444a() {
        if (!RxJavaPlugins.m9180c()) {
            return;
        }
        if ((Thread.currentThread() instanceof NonBlockingThread) || RxJavaPlugins.m9216C()) {
            throw new IllegalStateException("Attempt to block on a Scheduler " + Thread.currentThread().getName() + " that doesn't support blocking operators as they may lead to deadlock");
        }
    }
}
