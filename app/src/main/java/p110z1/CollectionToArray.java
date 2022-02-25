package p110z1;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

@Metadata(m8864a = 2, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u00002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a#\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00012\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\bH\u0007¢\u0006\u0004\b\t\u0010\n\u001a5\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00012\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\b2\u0010\u0010\u000b\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0018\u00010\u0001H\u0007¢\u0006\u0004\b\t\u0010\f\u001a~\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00012\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\b2\u0014\u0010\u000e\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00010\u000f2\u001a\u0010\u0010\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00010\u00112(\u0010\u0012\u001a$\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001\u0012\u0004\u0012\u00020\u0005\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00010\u0013H\u0082\b¢\u0006\u0002\u0010\u0014\"\u0018\u0010\u0000\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0003\"\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0015"}, m8860e = {"EMPTY", "", "", "[Ljava/lang/Object;", "MAX_SIZE", "", "collectionToArray", "collection", "", "toArray", "(Ljava/util/Collection;)[Ljava/lang/Object;", "a", "(Ljava/util/Collection;[Ljava/lang/Object;)[Ljava/lang/Object;", "toArrayImpl", "empty", "Lkotlin/Function0;", "alloc", "Lkotlin/Function1;", "trim", "Lkotlin/Function2;", "(Ljava/util/Collection;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;)[Ljava/lang/Object;", "kotlin-stdlib"})
@cgo(m5270a = "CollectionToArray")
/* renamed from: z1.ciu */
/* loaded from: classes3.dex */
public final class CollectionToArray {

    /* renamed from: a */
    private static final Object[] f20728a = new Object[0];

    /* renamed from: b */
    private static final int f20729b = 2147483645;

    /* JADX WARN: Type inference failed for: r3v4, types: [java.lang.Object, java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r3v5 */
    /* JADX WARN: Type inference failed for: r3v6, types: [java.lang.Object[], java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v7 */
    /* JADX WARN: Type inference failed for: r3v8 */
    /* JADX WARN: Unknown variable types count: 1 */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static final java.lang.Object[] m5226a(java.util.Collection<?> r2, p110z1.chc<java.lang.Object[]> r3, p110z1.chd<? super java.lang.Integer, java.lang.Object[]> r4, p110z1.cho<? super java.lang.Object[], ? super java.lang.Integer, java.lang.Object[]> r5) {
        /*
            int r0 = r2.size()
            if (r0 != 0) goto L_0x000d
            java.lang.Object r2 = r3.invoke()
            java.lang.Object[] r2 = (java.lang.Object[]) r2
            return r2
        L_0x000d:
            java.util.Iterator r2 = r2.iterator()
            boolean r1 = r2.hasNext()
            if (r1 != 0) goto L_0x001e
            java.lang.Object r2 = r3.invoke()
            java.lang.Object[] r2 = (java.lang.Object[]) r2
            return r2
        L_0x001e:
            java.lang.Integer r3 = java.lang.Integer.valueOf(r0)
            java.lang.Object r3 = r4.invoke(r3)
            java.lang.Object[] r3 = (java.lang.Object[]) r3
            r4 = 0
        L_0x0029:
            int r0 = r4 + 1
            java.lang.Object r1 = r2.next()
            r3[r4] = r1
            int r4 = r3.length
            if (r0 < r4) goto L_0x005e
            boolean r4 = r2.hasNext()
            if (r4 != 0) goto L_0x003b
            return r3
        L_0x003b:
            int r4 = r0 * 3
            int r4 = r4 + 1
            int r4 = r4 >>> 1
            r1 = 2147483645(0x7ffffffd, float:NaN)
            if (r4 > r0) goto L_0x0054
            if (r0 >= r1) goto L_0x004c
            r4 = 2147483645(0x7ffffffd, float:NaN)
            goto L_0x0054
        L_0x004c:
            java.lang.OutOfMemoryError r2 = new java.lang.OutOfMemoryError
            r2.<init>()
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            throw r2
        L_0x0054:
            java.lang.Object[] r3 = java.util.Arrays.copyOf(r3, r4)
            java.lang.String r4 = "Arrays.copyOf(result, newSize)"
            p110z1.cji.m5175b(r3, r4)
            goto L_0x006f
        L_0x005e:
            boolean r4 = r2.hasNext()
            if (r4 != 0) goto L_0x006f
            java.lang.Integer r2 = java.lang.Integer.valueOf(r0)
            java.lang.Object r2 = r5.invoke(r3, r2)
            java.lang.Object[] r2 = (java.lang.Object[]) r2
            return r2
        L_0x006f:
            r4 = r0
            goto L_0x0029
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.CollectionToArray.m5226a(java.util.Collection, z1.chc, z1.chd, z1.cho):java.lang.Object[]");
    }

    @cgo(m5270a = "toArray")
    @NotNull
    /* renamed from: a */
    public static final Object[] m5227a(@NotNull Collection<?> collection) {
        cji.m5162f(collection, "collection");
        int size = collection.size();
        if (size != 0) {
            Iterator<?> it = collection.iterator();
            if (it.hasNext()) {
                Object[] objArr = new Object[size];
                int i = 0;
                while (true) {
                    int i2 = i + 1;
                    objArr[i] = it.next();
                    if (i2 >= objArr.length) {
                        if (!it.hasNext()) {
                            return objArr;
                        }
                        int i3 = ((i2 * 3) + 1) >>> 1;
                        if (i3 <= i2) {
                            if (i2 < f20729b) {
                                i3 = f20729b;
                            } else {
                                throw new OutOfMemoryError();
                            }
                        }
                        objArr = Arrays.copyOf(objArr, i3);
                        cji.m5175b(objArr, "Arrays.copyOf(result, newSize)");
                    } else if (!it.hasNext()) {
                        Object[] copyOf = Arrays.copyOf(objArr, i2);
                        cji.m5175b(copyOf, "Arrays.copyOf(result, size)");
                        return copyOf;
                    }
                    i = i2;
                }
            }
        }
        return f20728a;
    }

    @cgo(m5270a = "toArray")
    @NotNull
    /* renamed from: a */
    public static final Object[] m5225a(@NotNull Collection<?> collection, @dbs Object[] objArr) {
        Object[] objArr2;
        cji.m5162f(collection, "collection");
        if (objArr != null) {
            int size = collection.size();
            int i = 0;
            if (size != 0) {
                Iterator<?> it = collection.iterator();
                if (it.hasNext()) {
                    if (size <= objArr.length) {
                        objArr2 = objArr;
                    } else {
                        Object newInstance = Array.newInstance(objArr.getClass().getComponentType(), size);
                        if (newInstance != null) {
                            objArr2 = (Object[]) newInstance;
                        } else {
                            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
                        }
                    }
                    while (true) {
                        int i2 = i + 1;
                        objArr2[i] = it.next();
                        if (i2 >= objArr2.length) {
                            if (!it.hasNext()) {
                                return objArr2;
                            }
                            int i3 = ((i2 * 3) + 1) >>> 1;
                            if (i3 <= i2) {
                                if (i2 < f20729b) {
                                    i3 = f20729b;
                                } else {
                                    throw new OutOfMemoryError();
                                }
                            }
                            objArr2 = Arrays.copyOf(objArr2, i3);
                            cji.m5175b(objArr2, "Arrays.copyOf(result, newSize)");
                        } else if (!it.hasNext()) {
                            if (objArr2 == objArr) {
                                objArr[i2] = null;
                                return objArr;
                            }
                            Object[] copyOf = Arrays.copyOf(objArr2, i2);
                            cji.m5175b(copyOf, "Arrays.copyOf(result, size)");
                            return copyOf;
                        }
                        i = i2;
                    }
                } else if (objArr.length <= 0) {
                    return objArr;
                } else {
                    objArr[0] = null;
                    return objArr;
                }
            } else if (objArr.length <= 0) {
                return objArr;
            } else {
                objArr[0] = null;
                return objArr;
            }
        } else {
            throw new NullPointerException();
        }
    }
}
