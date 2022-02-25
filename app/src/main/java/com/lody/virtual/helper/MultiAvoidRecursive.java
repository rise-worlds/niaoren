package com.lody.virtual.helper;

import android.util.SparseBooleanArray;

/* loaded from: classes.dex */
public class MultiAvoidRecursive {
    private SparseBooleanArray mCallings;

    public MultiAvoidRecursive(int i) {
        this.mCallings = new SparseBooleanArray(i);
    }

    public MultiAvoidRecursive() {
        this(7);
    }

    public boolean beginCall(int i) {
        if (this.mCallings.get(i)) {
            return false;
        }
        this.mCallings.put(i, true);
        return true;
    }

    public void finishCall(int i) {
        this.mCallings.put(i, false);
    }
}
