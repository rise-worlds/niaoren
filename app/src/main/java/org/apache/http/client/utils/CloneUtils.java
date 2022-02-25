package org.apache.http.client.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Deprecated
/* loaded from: assets/org.apache.http.legacy.boot */
public class CloneUtils {
    public static Object clone(Object obj) throws CloneNotSupportedException {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Cloneable) {
            Class<?> clazz = obj.getClass();
            try {
                Method m = clazz.getMethod("clone", null);
                try {
                    return m.invoke(obj, null);
                } catch (IllegalAccessException ex) {
                    throw new IllegalAccessError(ex.getMessage());
                } catch (InvocationTargetException ex2) {
                    Throwable cause = ex2.getCause();
                    if (cause instanceof CloneNotSupportedException) {
                        throw ((CloneNotSupportedException) cause);
                    }
                    throw new Error("Unexpected exception", cause);
                }
            } catch (NoSuchMethodException ex3) {
                throw new NoSuchMethodError(ex3.getMessage());
            }
        } else {
            throw new CloneNotSupportedException();
        }
    }

    private CloneUtils() {
    }
}
