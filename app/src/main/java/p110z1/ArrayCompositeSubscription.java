package p110z1;

import java.util.concurrent.atomic.AtomicReferenceArray;

/* renamed from: z1.bsb */
/* loaded from: classes3.dex */
public final class ArrayCompositeSubscription extends AtomicReferenceArray<dby> implements Disposable {
    private static final long serialVersionUID = 2746389416410565408L;

    public ArrayCompositeSubscription(int i) {
        super(i);
    }

    public boolean setResource(int i, dby dbyVar) {
        dby dbyVar2;
        do {
            dbyVar2 = get(i);
            if (dbyVar2 == SubscriptionHelper.CANCELLED) {
                if (dbyVar == null) {
                    return false;
                }
                dbyVar.cancel();
                return false;
            }
        } while (!compareAndSet(i, dbyVar2, dbyVar));
        if (dbyVar2 == null) {
            return true;
        }
        dbyVar2.cancel();
        return true;
    }

    public dby replaceResource(int i, dby dbyVar) {
        dby dbyVar2;
        do {
            dbyVar2 = get(i);
            if (dbyVar2 == SubscriptionHelper.CANCELLED) {
                if (dbyVar == null) {
                    return null;
                }
                dbyVar.cancel();
                return null;
            }
        } while (!compareAndSet(i, dbyVar2, dbyVar));
        return dbyVar2;
    }

    @Override // p110z1.Disposable
    public void dispose() {
        dby andSet;
        if (get(0) != SubscriptionHelper.CANCELLED) {
            int length = length();
            for (int i = 0; i < length; i++) {
                if (!(get(i) == SubscriptionHelper.CANCELLED || (andSet = getAndSet(i, SubscriptionHelper.CANCELLED)) == SubscriptionHelper.CANCELLED || andSet == null)) {
                    andSet.cancel();
                }
            }
        }
    }

    @Override // p110z1.Disposable
    public boolean isDisposed() {
        return get(0) == SubscriptionHelper.CANCELLED;
    }
}
