package com.lody.virtual.server.am;

import com.lody.virtual.helper.collection.ArrayMap;
import com.lody.virtual.helper.collection.SparseArray;

/* renamed from: com.lody.virtual.server.am.ProcessMap */
/* loaded from: classes.dex */
class ProcessMap<E> {
    private final ArrayMap<String, SparseArray<E>> mMap = new ArrayMap<>();

    public E get(String str, int i) {
        SparseArray<E> sparseArray = this.mMap.get(str);
        if (sparseArray == null) {
            return null;
        }
        return sparseArray.get(i);
    }

    public E put(String str, int i, E e) {
        SparseArray<E> sparseArray = this.mMap.get(str);
        if (sparseArray == null) {
            sparseArray = new SparseArray<>(2);
            this.mMap.put(str, sparseArray);
        }
        sparseArray.put(i, e);
        return e;
    }

    public E remove(String str, int i) {
        SparseArray<E> sparseArray = this.mMap.get(str);
        if (sparseArray == null) {
            return null;
        }
        E removeReturnOld = sparseArray.removeReturnOld(i);
        if (sparseArray.size() == 0) {
            this.mMap.remove(str);
        }
        return removeReturnOld;
    }

    public ArrayMap<String, SparseArray<E>> getMap() {
        return this.mMap;
    }
}
