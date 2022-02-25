package org.apache.tools.ant.types.selectors.modifiedselector;

import java.util.Comparator;

/* loaded from: classes2.dex */
public class EqualComparator implements Comparator<Object> {
    public String toString() {
        return "EqualComparator";
    }

    @Override // java.util.Comparator
    public int compare(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null ? 1 : 0;
        }
        return !obj.equals(obj2);
    }
}
