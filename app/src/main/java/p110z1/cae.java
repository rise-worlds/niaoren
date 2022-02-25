package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/* compiled from: Grouping.kt */
@Metadata(m8864a = 5, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000@\n\u0000\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\u001a\u009b\u0001\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0002\"\u0004\b\u0002\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00020\u00052b\u0010\u0006\u001a^\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0015\u0012\u0013\u0018\u0001H\u0003¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u0011H\u0004¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\r¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u0002H\u00030\u0007H\u0087\b\u001a´\u0001\u0010\u000f\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0002\"\u0004\b\u0002\u0010\u0003\"\u0016\b\u0003\u0010\u0010*\u0010\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0011*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00020\u00052\u0006\u0010\u0012\u001a\u0002H\u00102b\u0010\u0006\u001a^\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0015\u0012\u0013\u0018\u0001H\u0003¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u0011H\u0004¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\r¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u0002H\u00030\u0007H\u0087\b¢\u0006\u0002\u0010\u0013\u001aI\u0010\u0014\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0002\"\u0016\b\u0002\u0010\u0010*\u0010\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00150\u0011*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00020\u00052\u0006\u0010\u0012\u001a\u0002H\u0010H\u0007¢\u0006\u0002\u0010\u0016\u001a¼\u0001\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0002\"\u0004\b\u0002\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00020\u000526\u0010\u0018\u001a2\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u0011H\u0004¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0004\u0012\u0002H\u00030\u00192K\u0010\u0006\u001aG\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u0011H\u0003¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u0011H\u0004¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0004\u0012\u0002H\u00030\u001aH\u0087\b\u001a|\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0002\"\u0004\b\u0002\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00020\u00052\u0006\u0010\u001b\u001a\u0002H\u000326\u0010\u0006\u001a2\u0012\u0013\u0012\u0011H\u0003¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u0011H\u0004¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0004\u0012\u0002H\u00030\u0019H\u0087\b¢\u0006\u0002\u0010\u001c\u001aÕ\u0001\u0010\u001d\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0002\"\u0004\b\u0002\u0010\u0003\"\u0016\b\u0003\u0010\u0010*\u0010\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0011*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00020\u00052\u0006\u0010\u0012\u001a\u0002H\u001026\u0010\u0018\u001a2\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u0011H\u0004¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0004\u0012\u0002H\u00030\u00192K\u0010\u0006\u001aG\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u0011H\u0003¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u0011H\u0004¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0004\u0012\u0002H\u00030\u001aH\u0087\b¢\u0006\u0002\u0010\u001e\u001a\u0090\u0001\u0010\u001d\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0002\"\u0004\b\u0002\u0010\u0003\"\u0016\b\u0003\u0010\u0010*\u0010\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0011*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00020\u00052\u0006\u0010\u0012\u001a\u0002H\u00102\u0006\u0010\u001b\u001a\u0002H\u000326\u0010\u0006\u001a2\u0012\u0013\u0012\u0011H\u0003¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u0011H\u0004¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0004\u0012\u0002H\u00030\u0019H\u0087\b¢\u0006\u0002\u0010\u001f\u001a\u0088\u0001\u0010 \u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H!0\u0001\"\u0004\b\u0000\u0010!\"\b\b\u0001\u0010\u0004*\u0002H!\"\u0004\b\u0002\u0010\u0002*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00020\u00052K\u0010\u0006\u001aG\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u0011H!¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u0011H\u0004¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0004\u0012\u0002H!0\u001aH\u0087\b\u001a¡\u0001\u0010\"\u001a\u0002H\u0010\"\u0004\b\u0000\u0010!\"\b\b\u0001\u0010\u0004*\u0002H!\"\u0004\b\u0002\u0010\u0002\"\u0016\b\u0003\u0010\u0010*\u0010\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H!0\u0011*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00020\u00052\u0006\u0010\u0012\u001a\u0002H\u00102K\u0010\u0006\u001aG\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u0011H!¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u0011H\u0004¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0004\u0012\u0002H!0\u001aH\u0087\b¢\u0006\u0002\u0010#¨\u0006$"}, m8860e = {"aggregate", "", "K", "R", TessBaseAPI.f9204e, "Lkotlin/collections/Grouping;", "operation", "Lkotlin/Function4;", "Lkotlin/ParameterName;", "name", "key", "accumulator", "element", "", "first", "aggregateTo", "M", "", "destination", "(Lkotlin/collections/Grouping;Ljava/util/Map;Lkotlin/jvm/functions/Function4;)Ljava/util/Map;", "eachCountTo", "", "(Lkotlin/collections/Grouping;Ljava/util/Map;)Ljava/util/Map;", "fold", "initialValueSelector", "Lkotlin/Function2;", "Lkotlin/Function3;", "initialValue", "(Lkotlin/collections/Grouping;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/util/Map;", "foldTo", "(Lkotlin/collections/Grouping;Ljava/util/Map;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;)Ljava/util/Map;", "(Lkotlin/collections/Grouping;Ljava/util/Map;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/util/Map;", "reduce", "S", "reduceTo", "(Lkotlin/collections/Grouping;Ljava/util/Map;Lkotlin/jvm/functions/Function3;)Ljava/util/Map;", "kotlin-stdlib"}, m8859f = "kotlin/collections/GroupingKt", m8857h = 1)
/* renamed from: z1.cae */
/* loaded from: classes3.dex */
class cae extends GroupingJVM {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.lang.Object] */
    /* JADX WARN: Unknown variable types count: 1 */
    @p110z1.bwy(m8750a = "1.1")
    @p110z1.NotNull
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final <T, K, R> java.util.Map<K, R> m6499a(@p110z1.NotNull p110z1.Grouping<T, ? extends K> r6, @p110z1.NotNull p110z1.cht<? super K, ? super R, ? super T, ? super java.lang.Boolean, ? extends R> r7) {
        /*
            java.lang.String r0 = "$this$aggregate"
            p110z1.cji.m5162f(r6, r0)
            java.lang.String r0 = "operation"
            p110z1.cji.m5162f(r7, r0)
            java.util.LinkedHashMap r0 = new java.util.LinkedHashMap
            r0.<init>()
            java.util.Map r0 = (java.util.Map) r0
            java.util.Iterator r1 = r6.mo3706a()
        L_0x0015:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x003e
            java.lang.Object r2 = r1.next()
            java.lang.Object r3 = r6.mo3704a(r2)
            java.lang.Object r4 = r0.get(r3)
            if (r4 != 0) goto L_0x0031
            boolean r5 = r0.containsKey(r3)
            if (r5 != 0) goto L_0x0031
            r5 = 1
            goto L_0x0032
        L_0x0031:
            r5 = 0
        L_0x0032:
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
            java.lang.Object r2 = r7.invoke(r3, r4, r2, r5)
            r0.put(r3, r2)
            goto L_0x0015
        L_0x003e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.cae.m6499a(z1.cab, z1.cht):java.util.Map");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.Object] */
    /* JADX WARN: Unknown variable types count: 1 */
    @p110z1.bwy(m8750a = "1.1")
    @p110z1.NotNull
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final <T, K, R, M extends java.util.Map<? super K, R>> M m6502a(@p110z1.NotNull p110z1.Grouping<T, ? extends K> r5, @p110z1.NotNull M r6, @p110z1.NotNull p110z1.cht<? super K, ? super R, ? super T, ? super java.lang.Boolean, ? extends R> r7) {
        /*
            java.lang.String r0 = "$this$aggregateTo"
            p110z1.cji.m5162f(r5, r0)
            java.lang.String r0 = "destination"
            p110z1.cji.m5162f(r6, r0)
            java.lang.String r0 = "operation"
            p110z1.cji.m5162f(r7, r0)
            java.util.Iterator r0 = r5.mo3706a()
        L_0x0013:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x003c
            java.lang.Object r1 = r0.next()
            java.lang.Object r2 = r5.mo3704a(r1)
            java.lang.Object r3 = r6.get(r2)
            if (r3 != 0) goto L_0x002f
            boolean r4 = r6.containsKey(r2)
            if (r4 != 0) goto L_0x002f
            r4 = 1
            goto L_0x0030
        L_0x002f:
            r4 = 0
        L_0x0030:
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r4)
            java.lang.Object r1 = r7.invoke(r2, r3, r1, r4)
            r6.put(r2, r1)
            goto L_0x0013
        L_0x003c:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.cae.m6502a(z1.cab, java.util.Map, z1.cht):java.util.Map");
    }

    /* JADX WARN: Multi-variable type inference failed */
    @bwy(m8750a = "1.1")
    @NotNull
    /* renamed from: a */
    public static final <T, K, M extends Map<? super K, Integer>> M m6506a(@NotNull Grouping<T, ? extends K> cabVar, @NotNull M m) {
        cji.m5162f(cabVar, "$this$eachCountTo");
        cji.m5162f(m, "destination");
        Iterator<T> a = cabVar.mo3706a();
        while (a.hasNext()) {
            Object a2 = cabVar.mo3704a(a.next());
            Object obj = m.get(a2);
            if (obj == null && !m.containsKey(a2)) {
                obj = 0;
            }
            m.put(a2, Integer.valueOf(((Number) obj).intValue() + 1));
        }
        return m;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.lang.Object] */
    /* JADX WARN: Unknown variable types count: 1 */
    @p110z1.bwy(m8750a = "1.1")
    @p110z1.NotNull
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final <T, K, R> java.util.Map<K, R> m6501a(@p110z1.NotNull p110z1.Grouping<T, ? extends K> r6, @p110z1.NotNull p110z1.cho<? super K, ? super T, ? extends R> r7, @p110z1.NotNull p110z1.chs<? super K, ? super R, ? super T, ? extends R> r8) {
        /*
            java.lang.String r0 = "$this$fold"
            p110z1.cji.m5162f(r6, r0)
            java.lang.String r0 = "initialValueSelector"
            p110z1.cji.m5162f(r7, r0)
            java.lang.String r0 = "operation"
            p110z1.cji.m5162f(r8, r0)
            java.util.LinkedHashMap r0 = new java.util.LinkedHashMap
            r0.<init>()
            java.util.Map r0 = (java.util.Map) r0
            java.util.Iterator r1 = r6.mo3706a()
        L_0x001a:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0045
            java.lang.Object r2 = r1.next()
            java.lang.Object r3 = r6.mo3704a(r2)
            java.lang.Object r4 = r0.get(r3)
            if (r4 != 0) goto L_0x0036
            boolean r5 = r0.containsKey(r3)
            if (r5 != 0) goto L_0x0036
            r5 = 1
            goto L_0x0037
        L_0x0036:
            r5 = 0
        L_0x0037:
            if (r5 == 0) goto L_0x003d
            java.lang.Object r4 = r7.invoke(r3, r2)
        L_0x003d:
            java.lang.Object r2 = r8.invoke(r3, r4, r2)
            r0.put(r3, r2)
            goto L_0x001a
        L_0x0045:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.cae.m6501a(z1.cab, z1.cho, z1.chs):java.util.Map");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.Object] */
    /* JADX WARN: Unknown variable types count: 1 */
    @p110z1.bwy(m8750a = "1.1")
    @p110z1.NotNull
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final <T, K, R, M extends java.util.Map<? super K, R>> M m6504a(@p110z1.NotNull p110z1.Grouping<T, ? extends K> r5, @p110z1.NotNull M r6, @p110z1.NotNull p110z1.cho<? super K, ? super T, ? extends R> r7, @p110z1.NotNull p110z1.chs<? super K, ? super R, ? super T, ? extends R> r8) {
        /*
            java.lang.String r0 = "$this$foldTo"
            p110z1.cji.m5162f(r5, r0)
            java.lang.String r0 = "destination"
            p110z1.cji.m5162f(r6, r0)
            java.lang.String r0 = "initialValueSelector"
            p110z1.cji.m5162f(r7, r0)
            java.lang.String r0 = "operation"
            p110z1.cji.m5162f(r8, r0)
            java.util.Iterator r0 = r5.mo3706a()
        L_0x0018:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0043
            java.lang.Object r1 = r0.next()
            java.lang.Object r2 = r5.mo3704a(r1)
            java.lang.Object r3 = r6.get(r2)
            if (r3 != 0) goto L_0x0034
            boolean r4 = r6.containsKey(r2)
            if (r4 != 0) goto L_0x0034
            r4 = 1
            goto L_0x0035
        L_0x0034:
            r4 = 0
        L_0x0035:
            if (r4 == 0) goto L_0x003b
            java.lang.Object r3 = r7.invoke(r2, r1)
        L_0x003b:
            java.lang.Object r1 = r8.invoke(r2, r3, r1)
            r6.put(r2, r1)
            goto L_0x0018
        L_0x0043:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.cae.m6504a(z1.cab, java.util.Map, z1.cho, z1.chs):java.util.Map");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.lang.Object] */
    /* JADX WARN: Unknown variable types count: 1 */
    @p110z1.bwy(m8750a = "1.1")
    @p110z1.NotNull
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final <T, K, R> java.util.Map<K, R> m6507a(@p110z1.NotNull p110z1.Grouping<T, ? extends K> r6, R r7, @p110z1.NotNull p110z1.cho<? super R, ? super T, ? extends R> r8) {
        /*
            java.lang.String r0 = "$this$fold"
            p110z1.cji.m5162f(r6, r0)
            java.lang.String r0 = "operation"
            p110z1.cji.m5162f(r8, r0)
            java.util.LinkedHashMap r0 = new java.util.LinkedHashMap
            r0.<init>()
            java.util.Map r0 = (java.util.Map) r0
            java.util.Iterator r1 = r6.mo3706a()
        L_0x0015:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x003d
            java.lang.Object r2 = r1.next()
            java.lang.Object r3 = r6.mo3704a(r2)
            java.lang.Object r4 = r0.get(r3)
            if (r4 != 0) goto L_0x0031
            boolean r5 = r0.containsKey(r3)
            if (r5 != 0) goto L_0x0031
            r5 = 1
            goto L_0x0032
        L_0x0031:
            r5 = 0
        L_0x0032:
            if (r5 == 0) goto L_0x0035
            r4 = r7
        L_0x0035:
            java.lang.Object r2 = r8.invoke(r4, r2)
            r0.put(r3, r2)
            goto L_0x0015
        L_0x003d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.cae.m6507a(z1.cab, java.lang.Object, z1.cho):java.util.Map");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.Object] */
    /* JADX WARN: Unknown variable types count: 1 */
    @p110z1.bwy(m8750a = "1.1")
    @p110z1.NotNull
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final <T, K, R, M extends java.util.Map<? super K, R>> M m6505a(@p110z1.NotNull p110z1.Grouping<T, ? extends K> r5, @p110z1.NotNull M r6, R r7, @p110z1.NotNull p110z1.cho<? super R, ? super T, ? extends R> r8) {
        /*
            java.lang.String r0 = "$this$foldTo"
            p110z1.cji.m5162f(r5, r0)
            java.lang.String r0 = "destination"
            p110z1.cji.m5162f(r6, r0)
            java.lang.String r0 = "operation"
            p110z1.cji.m5162f(r8, r0)
            java.util.Iterator r0 = r5.mo3706a()
        L_0x0013:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x003b
            java.lang.Object r1 = r0.next()
            java.lang.Object r2 = r5.mo3704a(r1)
            java.lang.Object r3 = r6.get(r2)
            if (r3 != 0) goto L_0x002f
            boolean r4 = r6.containsKey(r2)
            if (r4 != 0) goto L_0x002f
            r4 = 1
            goto L_0x0030
        L_0x002f:
            r4 = 0
        L_0x0030:
            if (r4 == 0) goto L_0x0033
            r3 = r7
        L_0x0033:
            java.lang.Object r1 = r8.invoke(r3, r1)
            r6.put(r2, r1)
            goto L_0x0013
        L_0x003b:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.cae.m6505a(z1.cab, java.util.Map, java.lang.Object, z1.cho):java.util.Map");
    }

    /* JADX WARN: Multi-variable type inference failed */
    @bwy(m8750a = "1.1")
    @NotNull
    /* renamed from: a */
    public static final <S, T extends S, K> Map<K, S> m6500a(@NotNull Grouping<T, ? extends K> cabVar, @NotNull chs<? super K, ? super S, ? super T, ? extends S> chsVar) {
        cji.m5162f(cabVar, "$this$reduce");
        cji.m5162f(chsVar, "operation");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Iterator a = cabVar.mo3706a();
        while (a.hasNext()) {
            Object obj = (Object) a.next();
            Object obj2 = (Object) cabVar.mo3704a(obj);
            Object obj3 = (Object) linkedHashMap.get(obj2);
            if (!(obj3 == 0 && !linkedHashMap.containsKey(obj2))) {
                obj = (Object) chsVar.invoke(obj2, obj3, obj);
            }
            linkedHashMap.put(obj2, obj);
        }
        return linkedHashMap;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @bwy(m8750a = "1.1")
    @NotNull
    /* renamed from: a */
    public static final <S, T extends S, K, M extends Map<? super K, S>> M m6503a(@NotNull Grouping<T, ? extends K> cabVar, @NotNull M m, @NotNull chs<? super K, ? super S, ? super T, ? extends S> chsVar) {
        cji.m5162f(cabVar, "$this$reduceTo");
        cji.m5162f(m, "destination");
        cji.m5162f(chsVar, "operation");
        Iterator a = cabVar.mo3706a();
        while (a.hasNext()) {
            Object obj = (Object) a.next();
            Object obj2 = (Object) cabVar.mo3704a(obj);
            Object obj3 = (Object) m.get(obj2);
            if (!(obj3 == 0 && !m.containsKey(obj2))) {
                obj = (Object) chsVar.invoke(obj2, obj3, obj);
            }
            m.put(obj2, obj);
        }
        return m;
    }
}
