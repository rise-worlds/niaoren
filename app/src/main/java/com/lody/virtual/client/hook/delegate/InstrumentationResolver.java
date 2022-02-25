package com.lody.virtual.client.hook.delegate;

import android.app.Instrumentation;
import com.lody.virtual.helper.utils.Reflect;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class InstrumentationResolver {
    private static Map<String, IResolver> resolvers = new HashMap();
    private static IResolver defaultResolver = new IResolver() { // from class: com.lody.virtual.client.hook.delegate.InstrumentationResolver.1
        @Override // com.lody.virtual.client.hook.delegate.InstrumentationResolver.IResolver
        public boolean resolve(Instrumentation instrumentation, Instrumentation instrumentation2) {
            Field checkInstrumentation = InstrumentationResolver.checkInstrumentation(instrumentation2);
            if (checkInstrumentation == null) {
                return false;
            }
            try {
                checkInstrumentation.setAccessible(true);
                checkInstrumentation.set(instrumentation2, instrumentation);
                return true;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return false;
            }
        }
    };

    @FunctionalInterface
    /* loaded from: classes.dex */
    public interface IResolver {
        boolean resolve(Instrumentation instrumentation, Instrumentation instrumentation2);
    }

    static {
        resolvers.put("com.duowan.mobile", new IResolver() { // from class: com.lody.virtual.client.hook.delegate.InstrumentationResolver.2
            @Override // com.lody.virtual.client.hook.delegate.InstrumentationResolver.IResolver
            public boolean resolve(Instrumentation instrumentation, Instrumentation instrumentation2) {
                Class<?> type;
                if (instrumentation == null || instrumentation2 == null || instrumentation2 == instrumentation) {
                    return false;
                }
                Class<?> cls = instrumentation2.getClass();
                if (cls.getName().equals("com.yy.android.small.launcher.ApkPluginLauncher$InstrumentationWrapper") && (type = Reflect.m18996on("com.yy.android.small.launcher.ApkPluginLauncher", cls.getClassLoader()).type()) != null) {
                    try {
                        Reflect.m18999on(type).set("sHostInstrumentation", instrumentation);
                        return true;
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
                return false;
            }
        });
    }

    public static boolean resolveInstrumentation(String str) {
        Instrumentation instrumentation = AppInstrumentation.getDefault().root;
        Instrumentation instrumentation2 = AppInstrumentation.getDefault().base;
        IResolver iResolver = resolvers.get(str);
        if (iResolver == null || !iResolver.resolve(instrumentation, instrumentation2)) {
            return defaultResolver.resolve(instrumentation, instrumentation2);
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Field checkInstrumentation(Instrumentation instrumentation) {
        if (instrumentation instanceof AppInstrumentation) {
            return null;
        }
        Class<?> cls = instrumentation.getClass();
        if (Instrumentation.class.equals(cls)) {
            return null;
        }
        do {
            Field[] declaredFields = cls.getDeclaredFields();
            if (declaredFields != null) {
                for (Field field : declaredFields) {
                    if (Instrumentation.class.isAssignableFrom(field.getType())) {
                        field.setAccessible(true);
                        try {
                            if (field.get(instrumentation) instanceof AppInstrumentation) {
                                return field;
                            }
                        } catch (IllegalAccessException unused) {
                            return null;
                        }
                    }
                }
            }
            cls = cls.getSuperclass();
        } while (!Instrumentation.class.equals(cls));
        return null;
    }
}
