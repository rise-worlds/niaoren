package p110z1;

import java.util.concurrent.Callable;

/* renamed from: z1.avu */
/* loaded from: classes3.dex */
public interface ScalarCallable<T> extends Callable<T> {
    @Override // java.util.concurrent.Callable
    T call();
}
