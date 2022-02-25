package p110z1;

import java.util.Map;
import org.apache.tools.ant.taskdefs.condition.ParserSupports;
import org.apache.tools.ant.types.selectors.SizeSelector;

@Metadata(m8864a = 2, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000.\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\u001aK\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0002\"\b\b\u0001\u0010\u0001*\u0002H\u0002*\u0015\u0012\u0006\b\u0000\u0012\u00020\u0004\u0012\t\u0012\u0007H\u0002¢\u0006\u0002\b\u00050\u00032\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\tH\u0087\n¢\u0006\u0002\u0010\n\u001a@\u0010\u0000\u001a\u0002H\u0002\"\u0004\b\u0000\u0010\u0002*\u0012\u0012\u0006\b\u0000\u0012\u00020\u0004\u0012\u0006\b\u0000\u0012\u0002H\u00020\u000b2\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\tH\u0087\b¢\u0006\u0004\b\f\u0010\n\u001aO\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0002\"\b\b\u0001\u0010\u0001*\u0002H\u0002*\u0017\u0012\u0006\b\u0000\u0012\u00020\u0004\u0012\u000b\b\u0001\u0012\u0007H\u0002¢\u0006\u0002\b\u00050\u000b2\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\tH\u0087\n¢\u0006\u0004\b\r\u0010\n\u001aF\u0010\u000e\u001a\u00020\u000f\"\u0004\b\u0000\u0010\u0002*\u0012\u0012\u0006\b\u0000\u0012\u00020\u0004\u0012\u0006\b\u0000\u0012\u0002H\u00020\u000b2\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\t2\u0006\u0010\u0010\u001a\u0002H\u0002H\u0087\n¢\u0006\u0002\u0010\u0011¨\u0006\u0012"}, m8860e = {"getValue", "V1", "V", "", "", "Lkotlin/internal/Exact;", "thisRef", "", ParserSupports.PROPERTY, "Lkotlin/reflect/KProperty;", "(Ljava/util/Map;Ljava/lang/Object;Lkotlin/reflect/KProperty;)Ljava/lang/Object;", "", "getVarContravariant", "getVar", "setValue", "", SizeSelector.SIZE_KEY, "(Ljava/util/Map;Ljava/lang/Object;Lkotlin/reflect/KProperty;Ljava/lang/Object;)V", "kotlin-stdlib"})
@cgo(m5270a = "MapAccessorsKt")
/* renamed from: z1.cak */
/* loaded from: classes3.dex */
public final class MapAccessors {
    @cey
    /* renamed from: a */
    private static final <V, V1 extends V> V1 m6489a(@NotNull Map<? super String, ? extends V> map, Object obj, cnf<?> cnfVar) {
        cji.m5162f(map, "$this$getValue");
        return (V1) can.m6481a((Map<String, ? extends Object>) map, cnfVar.getName());
    }

    @cey
    @cgo(m5270a = "getVar")
    /* renamed from: b */
    private static final <V, V1 extends V> V1 m6487b(@NotNull Map<? super String, ? extends V> map, Object obj, cnf<?> cnfVar) {
        cji.m5162f(map, "$this$getValue");
        return (V1) can.m6481a((Map<String, ? extends Object>) map, cnfVar.getName());
    }

    @Annotations(m8902a = "Use getValue() with two type parameters instead", m8900c = bvk.ERROR)
    @cey
    @cez
    @cgo(m5270a = "getVarContravariant")
    /* renamed from: c */
    private static final <V> V m6486c(@NotNull Map<? super String, ? super V> map, Object obj, cnf<?> cnfVar) {
        return (V) can.m6481a((Map<String, ? extends Object>) map, cnfVar.getName());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @cey
    /* renamed from: a */
    private static final <V> void m6488a(@NotNull Map<? super String, ? super V> map, Object obj, cnf<?> cnfVar, V v) {
        cji.m5162f(map, "$this$setValue");
        map.put(cnfVar.getName(), v);
    }
}
