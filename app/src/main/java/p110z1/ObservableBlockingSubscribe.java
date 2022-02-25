package p110z1;

/* renamed from: z1.biq */
/* loaded from: classes3.dex */
public final class ObservableBlockingSubscribe {
    private ObservableBlockingSubscribe() {
        throw new IllegalStateException("No instances!");
    }

    /* JADX WARN: Removed duplicated region for block: B:5:0x0017  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static <T> void m9660a(p110z1.ObservableSource<? extends T> r4, p110z1.Observer<? super T> r5) {
        /*
            java.util.concurrent.LinkedBlockingQueue r0 = new java.util.concurrent.LinkedBlockingQueue
            r0.<init>()
            z1.awf r1 = new z1.awf
            r1.<init>(r0)
            r5.onSubscribe(r1)
            r4.subscribe(r1)
        L_0x0010:
            boolean r2 = r1.isDisposed()
            if (r2 == 0) goto L_0x0017
            goto L_0x003a
        L_0x0017:
            java.lang.Object r2 = r0.poll()
            if (r2 != 0) goto L_0x002a
            java.lang.Object r2 = r0.take()     // Catch: InterruptedException -> 0x0022
            goto L_0x002a
        L_0x0022:
            r4 = move-exception
            r1.dispose()
            r5.onError(r4)
            return
        L_0x002a:
            boolean r3 = r1.isDisposed()
            if (r3 != 0) goto L_0x003a
            java.lang.Object r3 = p110z1.BlockingObserver.TERMINATED
            if (r4 == r3) goto L_0x003a
            boolean r2 = p110z1.NotificationLite.acceptFull(r2, r5)
            if (r2 == 0) goto L_0x0010
        L_0x003a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.ObservableBlockingSubscribe.m9660a(z1.asq, z1.ass):void");
    }

    /* renamed from: a */
    public static <T> void m9661a(ObservableSource<? extends T> asqVar) {
        BlockingIgnoringReceiver bsqVar = new BlockingIgnoringReceiver();
        LambdaObserver awrVar = new LambdaObserver(Functions.m9914b(), bsqVar, bsqVar, Functions.m9914b());
        asqVar.subscribe(awrVar);
        BlockingHelper.m9443a(bsqVar, awrVar);
        Throwable th = bsqVar.f20062a;
        if (th != null) {
            throw ExceptionHelper.m9432a(th);
        }
    }

    /* renamed from: a */
    public static <T> void m9659a(ObservableSource<? extends T> asqVar, Consumer<? super T> aumVar, Consumer<? super Throwable> aumVar2, Action augVar) {
        ObjectHelper.m9873a(aumVar, "onNext is null");
        ObjectHelper.m9873a(aumVar2, "onError is null");
        ObjectHelper.m9873a(augVar, "onComplete is null");
        m9660a(asqVar, new LambdaObserver(aumVar, aumVar2, augVar, Functions.m9914b()));
    }
}
