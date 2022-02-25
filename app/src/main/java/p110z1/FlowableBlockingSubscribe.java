package p110z1;

import java.util.concurrent.LinkedBlockingQueue;

/* renamed from: z1.azb */
/* loaded from: classes3.dex */
public final class FlowableBlockingSubscribe {
    private FlowableBlockingSubscribe() {
        throw new IllegalStateException("No instances!");
    }

    /* renamed from: a */
    public static <T> void m9814a(Publisher<? extends T> dbwVar, Subscriber<? super T> dbxVar) {
        Object poll;
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();
        BlockingSubscriber brkVar = new BlockingSubscriber(linkedBlockingQueue);
        dbwVar.subscribe(brkVar);
        do {
            try {
                if (!brkVar.isCancelled()) {
                    poll = linkedBlockingQueue.poll();
                    if (poll == null) {
                        if (!brkVar.isCancelled()) {
                            BlockingHelper.m9444a();
                            poll = linkedBlockingQueue.take();
                        } else {
                            return;
                        }
                    }
                    if (brkVar.isCancelled() || poll == BlockingSubscriber.TERMINATED) {
                        return;
                    }
                } else {
                    return;
                }
            } catch (InterruptedException e) {
                brkVar.cancel();
                dbxVar.onError(e);
                return;
            }
        } while (!NotificationLite.acceptFull(poll, dbxVar));
    }

    /* renamed from: a */
    public static <T> void m9817a(Publisher<? extends T> dbwVar) {
        BlockingIgnoringReceiver bsqVar = new BlockingIgnoringReceiver();
        LambdaSubscriber brrVar = new LambdaSubscriber(Functions.m9914b(), bsqVar, bsqVar, Functions.f17566l);
        dbwVar.subscribe(brrVar);
        BlockingHelper.m9443a(bsqVar, brrVar);
        Throwable th = bsqVar.f20062a;
        if (th != null) {
            throw ExceptionHelper.m9432a(th);
        }
    }

    /* renamed from: a */
    public static <T> void m9816a(Publisher<? extends T> dbwVar, Consumer<? super T> aumVar, Consumer<? super Throwable> aumVar2, Action augVar) {
        ObjectHelper.m9873a(aumVar, "onNext is null");
        ObjectHelper.m9873a(aumVar2, "onError is null");
        ObjectHelper.m9873a(augVar, "onComplete is null");
        m9814a(dbwVar, new LambdaSubscriber(aumVar, aumVar2, augVar, Functions.f17566l));
    }

    /* renamed from: a */
    public static <T> void m9815a(Publisher<? extends T> dbwVar, Consumer<? super T> aumVar, Consumer<? super Throwable> aumVar2, Action augVar, int i) {
        ObjectHelper.m9873a(aumVar, "onNext is null");
        ObjectHelper.m9873a(aumVar2, "onError is null");
        ObjectHelper.m9873a(augVar, "onComplete is null");
        ObjectHelper.m9878a(i, "number > 0 required");
        m9814a(dbwVar, new BoundedSubscriber(aumVar, aumVar2, augVar, Functions.m9913b(i), i));
    }
}
