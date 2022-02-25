package p110z1;

import java.util.concurrent.CountDownLatch;

/* renamed from: z1.bsq */
/* loaded from: classes3.dex */
public final class BlockingIgnoringReceiver extends CountDownLatch implements Action, Consumer<Throwable> {

    /* renamed from: a */
    public Throwable f20062a;

    public BlockingIgnoringReceiver() {
        super(1);
    }

    /* renamed from: a */
    public void accept(Throwable th) {
        this.f20062a = th;
        countDown();
    }

    @Override // p110z1.Action
    /* renamed from: a */
    public void mo9442a() {
        countDown();
    }
}
