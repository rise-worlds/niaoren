package p110z1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/* renamed from: z1.bsm */
/* loaded from: classes3.dex */
public enum ArrayListSupplier implements Callable<List<Object>>, Function<Object, List<Object>> {
    INSTANCE;

    public static <T> Callable<List<T>> asCallable() {
        return INSTANCE;
    }

    public static <T, O> Function<O, List<T>> asFunction() {
        return INSTANCE;
    }

    @Override // java.util.concurrent.Callable
    public List<Object> call() throws Exception {
        return new ArrayList();
    }

    @Override // p110z1.Function
    public List<Object> apply(Object obj) throws Exception {
        return new ArrayList();
    }
}
