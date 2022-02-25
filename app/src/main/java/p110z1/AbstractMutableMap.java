package p110z1;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import org.apache.tools.ant.types.selectors.SizeSelector;

@bwy(m8750a = "1.1")
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0006\b'\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u00032\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0004B\u0007\b\u0004¢\u0006\u0002\u0010\u0005J\u001f\u0010\u0006\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u0007\u001a\u00028\u00002\u0006\u0010\b\u001a\u00028\u0001H&¢\u0006\u0002\u0010\t¨\u0006\n"}, m8860e = {"Lkotlin/collections/AbstractMutableMap;", "K", "V", "", "Ljava/util/AbstractMap;", "()V", "put", "key", SizeSelector.SIZE_KEY, "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "kotlin-stdlib"})
/* renamed from: z1.byx */
/* loaded from: classes3.dex */
public abstract class AbstractMutableMap<K, V> extends AbstractMap<K, V> implements Map<K, V>, ckw {
    /* renamed from: a */
    public abstract Set m8301a();

    @Override // java.util.AbstractMap, java.util.Map
    @dbs
    public abstract V put(K k, V v);

    protected AbstractMutableMap() {
    }

    /* renamed from: b */
    public Set m8300b() {
        return super.keySet();
    }

    /* renamed from: c */
    public int m8299c() {
        return super.size();
    }

    /* renamed from: d */
    public Collection m8298d() {
        return super.values();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set<Map.Entry<K, V>> entrySet() {
        return m8301a();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set<K> keySet() {
        return m8300b();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int size() {
        return m8299c();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Collection<V> values() {
        return m8298d();
    }
}
