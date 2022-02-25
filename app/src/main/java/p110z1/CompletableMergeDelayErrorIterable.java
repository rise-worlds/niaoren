package p110z1;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;
import p110z1.CompletableMergeDelayErrorArray;

/* renamed from: z1.ayb */
/* loaded from: classes3.dex */
public final class CompletableMergeDelayErrorIterable extends Completable {

    /* renamed from: a */
    final Iterable<? extends CompletableSource> f17758a;

    public CompletableMergeDelayErrorIterable(Iterable<? extends CompletableSource> iterable) {
        this.f17758a = iterable;
    }

    @Override // p110z1.Completable
    /* renamed from: b */
    public void mo9001b(CompletableObserver arpVar) {
        CompositeDisposable atqVar = new CompositeDisposable();
        arpVar.onSubscribe(atqVar);
        try {
            Iterator it = (Iterator) ObjectHelper.m9873a(this.f17758a.iterator(), "The source iterator returned is null");
            AtomicInteger atomicInteger = new AtomicInteger(1);
            AtomicThrowable bsnVar = new AtomicThrowable();
            while (!atqVar.isDisposed()) {
                try {
                    if (it.hasNext()) {
                        if (!atqVar.isDisposed()) {
                            try {
                                CompletableSource arsVar = (CompletableSource) ObjectHelper.m9873a(it.next(), "The iterator returned a null CompletableSource");
                                if (!atqVar.isDisposed()) {
                                    atomicInteger.getAndIncrement();
                                    arsVar.mo11309a(new CompletableMergeDelayErrorArray.C3962a(arpVar, atqVar, bsnVar, atomicInteger));
                                } else {
                                    return;
                                }
                            } catch (Throwable th) {
                                Exceptions.m9983b(th);
                                bsnVar.addThrowable(th);
                            }
                        } else {
                            return;
                        }
                    }
                } catch (Throwable th2) {
                    Exceptions.m9983b(th2);
                    bsnVar.addThrowable(th2);
                }
                if (atomicInteger.decrementAndGet() == 0) {
                    Throwable terminate = bsnVar.terminate();
                    if (terminate == null) {
                        arpVar.onComplete();
                        return;
                    } else {
                        arpVar.onError(terminate);
                        return;
                    }
                } else {
                    return;
                }
            }
        } catch (Throwable th3) {
            Exceptions.m9983b(th3);
            arpVar.onError(th3);
        }
    }
}
