package com.lody.virtual.helper.compat;

import android.os.Parcelable;
import java.lang.reflect.Method;
import java.util.List;
import p110z1.ParceledListSlice;
import p110z1.ParceledListSliceJBMR2;

/* loaded from: classes.dex */
public class ParceledListSliceCompat {
    public static boolean isReturnParceledListSlice(Method method) {
        return method != null && method.getReturnType() == ParceledListSlice.TYPE;
    }

    public static Object create(List list) {
        if (ParceledListSliceJBMR2.ctor != null) {
            return ParceledListSliceJBMR2.ctor.newInstance(list);
        }
        Parcelable newInstance = ParceledListSlice.ctor.newInstance();
        for (Object obj : list) {
            ParceledListSlice.append.call(newInstance, obj);
        }
        ParceledListSlice.setLastSlice.call(newInstance, true);
        return newInstance;
    }
}
