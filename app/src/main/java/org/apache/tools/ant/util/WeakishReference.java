package org.apache.tools.ant.util;

import java.lang.ref.WeakReference;

/* loaded from: classes2.dex */
public class WeakishReference {
    private WeakReference weakref;

    WeakishReference(Object obj) {
        this.weakref = new WeakReference(obj);
    }

    public Object get() {
        return this.weakref.get();
    }

    public static WeakishReference createReference(Object obj) {
        return new WeakishReference(obj);
    }

    /* loaded from: classes2.dex */
    public static class HardReference extends WeakishReference {
        public HardReference(Object obj) {
            super(obj);
        }
    }
}
