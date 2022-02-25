package org.apache.tools.ant.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Vector;

/* loaded from: classes2.dex */
public class CollectionUtils {
    @Deprecated
    public static final List EMPTY_LIST = Collections.EMPTY_LIST;

    public static boolean equals(Vector<?> vector, Vector<?> vector2) {
        if (vector == vector2) {
            return true;
        }
        if (vector == null || vector2 == null) {
            return false;
        }
        return vector.equals(vector2);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x001f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean equals(java.util.Dictionary<?, ?> r5, java.util.Dictionary<?, ?> r6) {
        /*
            r0 = 1
            if (r5 != r6) goto L_0x0004
            return r0
        L_0x0004:
            r1 = 0
            if (r5 == 0) goto L_0x0035
            if (r6 != 0) goto L_0x000a
            goto L_0x0035
        L_0x000a:
            int r2 = r5.size()
            int r3 = r6.size()
            if (r2 == r3) goto L_0x0015
            return r1
        L_0x0015:
            java.util.Enumeration r2 = r5.keys()
        L_0x0019:
            boolean r3 = r2.hasMoreElements()
            if (r3 == 0) goto L_0x0034
            java.lang.Object r3 = r2.nextElement()
            java.lang.Object r4 = r5.get(r3)
            java.lang.Object r3 = r6.get(r3)
            if (r3 == 0) goto L_0x0033
            boolean r3 = r4.equals(r3)
            if (r3 != 0) goto L_0x0019
        L_0x0033:
            return r1
        L_0x0034:
            return r0
        L_0x0035:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.util.CollectionUtils.equals(java.util.Dictionary, java.util.Dictionary):boolean");
    }

    public static String flattenToString(Collection<?> collection) {
        StringBuilder sb = new StringBuilder();
        for (Object obj : collection) {
            if (sb.length() != 0) {
                sb.append(",");
            }
            sb.append(obj);
        }
        return sb.toString();
    }

    public static <K, V> void putAll(Dictionary<? super K, ? super V> dictionary, Dictionary<? extends K, ? extends V> dictionary2) {
        Enumeration<? extends K> keys = dictionary2.keys();
        while (keys.hasMoreElements()) {
            Object obj = (Object) keys.nextElement();
            dictionary.put(obj, (Object) dictionary2.get(obj));
        }
    }

    /* loaded from: classes2.dex */
    public static final class EmptyEnumeration<E> implements Enumeration<E> {
        @Override // java.util.Enumeration
        public boolean hasMoreElements() {
            return false;
        }

        @Override // java.util.Enumeration
        public E nextElement() throws NoSuchElementException {
            throw new NoSuchElementException();
        }
    }

    public static <E> Enumeration<E> append(Enumeration<E> enumeration, Enumeration<E> enumeration2) {
        return new CompoundEnumeration(enumeration, enumeration2);
    }

    public static <E> Enumeration<E> asEnumeration(final Iterator<E> it) {
        return new Enumeration<E>() { // from class: org.apache.tools.ant.util.CollectionUtils.1
            @Override // java.util.Enumeration
            public boolean hasMoreElements() {
                return it.hasNext();
            }

            /* JADX WARN: Type inference failed for: r0v1, types: [E, java.lang.Object] */
            @Override // java.util.Enumeration
            public E nextElement() {
                return it.next();
            }
        };
    }

    public static <E> Iterator<E> asIterator(final Enumeration<E> enumeration) {
        return new Iterator<E>() { // from class: org.apache.tools.ant.util.CollectionUtils.2
            @Override // java.util.Iterator
            public boolean hasNext() {
                return enumeration.hasMoreElements();
            }

            /* JADX WARN: Type inference failed for: r0v1, types: [E, java.lang.Object] */
            @Override // java.util.Iterator
            public E next() {
                return enumeration.nextElement();
            }

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public static <T> Collection<T> asCollection(Iterator<? extends T> it) {
        ArrayList arrayList = new ArrayList();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        return arrayList;
    }

    /* loaded from: classes2.dex */
    private static final class CompoundEnumeration<E> implements Enumeration<E> {

        /* renamed from: e1 */
        private final Enumeration<E> f14774e1;

        /* renamed from: e2 */
        private final Enumeration<E> f14775e2;

        public CompoundEnumeration(Enumeration<E> enumeration, Enumeration<E> enumeration2) {
            this.f14774e1 = enumeration;
            this.f14775e2 = enumeration2;
        }

        @Override // java.util.Enumeration
        public boolean hasMoreElements() {
            return this.f14774e1.hasMoreElements() || this.f14775e2.hasMoreElements();
        }

        @Override // java.util.Enumeration
        public E nextElement() throws NoSuchElementException {
            if (this.f14774e1.hasMoreElements()) {
                return this.f14774e1.nextElement();
            }
            return this.f14775e2.nextElement();
        }
    }

    public static int frequency(Collection<?> collection, Object obj) {
        int i = 0;
        if (collection != null) {
            for (Object obj2 : collection) {
                if (obj == null) {
                    if (obj2 == null) {
                        i++;
                    }
                } else if (obj.equals(obj2)) {
                    i++;
                }
            }
        }
        return i;
    }
}
