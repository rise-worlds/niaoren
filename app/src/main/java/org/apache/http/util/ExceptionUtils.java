package org.apache.http.util;

import java.lang.reflect.Method;

@Deprecated
/* loaded from: assets/org.apache.http.legacy.boot */
public final class ExceptionUtils {
    private static final Method INIT_CAUSE_METHOD = getInitCauseMethod();

    private static Method getInitCauseMethod() {
        try {
            Class[] paramsClasses = {Throwable.class};
            return Throwable.class.getMethod("initCause", paramsClasses);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    public static void initCause(Throwable throwable, Throwable cause) {
        if (INIT_CAUSE_METHOD != null) {
            try {
                INIT_CAUSE_METHOD.invoke(throwable, cause);
            } catch (Exception e) {
            }
        }
    }

    private ExceptionUtils() {
    }
}
