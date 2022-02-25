package p110z1;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

/* renamed from: z1.bsx */
/* loaded from: classes3.dex */
public enum HashMapSupplier implements Callable<Map<Object, Object>> {
    INSTANCE;

    public static <K, V> Callable<Map<K, V>> asCallable() {
        return INSTANCE;
    }

    @Override // java.util.concurrent.Callable
    public Map<Object, Object> call() throws Exception {
        return new HashMap();
    }
}
