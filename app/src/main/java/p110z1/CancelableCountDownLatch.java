package p110z1;

import java.util.concurrent.CountDownLatch;

/* renamed from: z1.q */
/* loaded from: classes3.dex */
public class CancelableCountDownLatch extends CountDownLatch {
    public CancelableCountDownLatch(int i) {
        super(i);
    }

    /* renamed from: a */
    public void m1379a() {
        while (getCount() > 0) {
            countDown();
        }
    }
}
