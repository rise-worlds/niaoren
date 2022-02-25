package com.lidroid.xutils;

import android.app.Activity;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceGroup;
import android.view.View;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.EventListenerManager;
import com.lidroid.xutils.view.ResLoader;
import com.lidroid.xutils.view.ViewFinder;
import com.lidroid.xutils.view.ViewInjectInfo;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.PreferenceInject;
import com.lidroid.xutils.view.annotation.ResInject;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.EventBase;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.apache.tools.ant.types.selectors.SizeSelector;

/* loaded from: classes.dex */
public class ViewUtils {
    private ViewUtils() {
    }

    public static void inject(View view) {
        injectObject(view, new ViewFinder(view));
    }

    public static void inject(Activity activity) {
        injectObject(activity, new ViewFinder(activity));
    }

    public static void inject(PreferenceActivity preferenceActivity) {
        injectObject(preferenceActivity, new ViewFinder(preferenceActivity));
    }

    public static void inject(Object obj, View view) {
        injectObject(obj, new ViewFinder(view));
    }

    public static void inject(Object obj, Activity activity) {
        injectObject(obj, new ViewFinder(activity));
    }

    public static void inject(Object obj, PreferenceGroup preferenceGroup) {
        injectObject(obj, new ViewFinder(preferenceGroup));
    }

    public static void inject(Object obj, PreferenceActivity preferenceActivity) {
        injectObject(obj, new ViewFinder(preferenceActivity));
    }

    private static void injectObject(Object obj, ViewFinder viewFinder) {
        Throwable th;
        Method method;
        Class<?> cls = obj.getClass();
        ContentView contentView = (ContentView) cls.getAnnotation(ContentView.class);
        boolean z = true;
        int i = 0;
        if (contentView != null) {
            try {
                cls.getMethod("setContentView", Integer.TYPE).invoke(obj, Integer.valueOf(contentView.value()));
            } catch (Throwable th2) {
                LogUtils.m19219e(th2.getMessage(), th2);
            }
        }
        Field[] declaredFields = cls.getDeclaredFields();
        if (declaredFields != null && declaredFields.length > 0) {
            for (Field field : declaredFields) {
                ViewInject viewInject = (ViewInject) field.getAnnotation(ViewInject.class);
                if (viewInject != null) {
                    try {
                        View findViewById = viewFinder.findViewById(viewInject.value(), viewInject.parentId());
                        if (findViewById != null) {
                            field.setAccessible(true);
                            field.set(obj, findViewById);
                        }
                    } catch (Throwable th3) {
                        LogUtils.m19219e(th3.getMessage(), th3);
                    }
                } else {
                    ResInject resInject = (ResInject) field.getAnnotation(ResInject.class);
                    if (resInject != null) {
                        try {
                            Object loadRes = ResLoader.loadRes(resInject.type(), viewFinder.getContext(), resInject.m19200id());
                            if (loadRes != null) {
                                field.setAccessible(true);
                                field.set(obj, loadRes);
                            }
                        } catch (Throwable th4) {
                            LogUtils.m19219e(th4.getMessage(), th4);
                        }
                    } else {
                        PreferenceInject preferenceInject = (PreferenceInject) field.getAnnotation(PreferenceInject.class);
                        if (preferenceInject != null) {
                            try {
                                Preference findPreference = viewFinder.findPreference(preferenceInject.value());
                                if (findPreference != null) {
                                    field.setAccessible(true);
                                    field.set(obj, findPreference);
                                }
                            } catch (Throwable th5) {
                                LogUtils.m19219e(th5.getMessage(), th5);
                            }
                        }
                    }
                }
            }
        }
        Method[] declaredMethods = cls.getDeclaredMethods();
        if (declaredMethods != null && declaredMethods.length > 0) {
            int length = declaredMethods.length;
            int i2 = 0;
            while (i2 < length) {
                Method method2 = declaredMethods[i2];
                Annotation[] declaredAnnotations = method2.getDeclaredAnnotations();
                if (declaredAnnotations != null && declaredAnnotations.length > 0) {
                    int length2 = declaredAnnotations.length;
                    int i3 = 0;
                    while (i3 < length2) {
                        Annotation annotation = declaredAnnotations[i3];
                        Class<? extends Annotation> annotationType = annotation.annotationType();
                        if (annotationType.getAnnotation(EventBase.class) != null) {
                            method2.setAccessible(z);
                            try {
                                Method declaredMethod = annotationType.getDeclaredMethod(SizeSelector.SIZE_KEY, new Class[i]);
                                Object obj2 = null;
                                try {
                                    method = annotationType.getDeclaredMethod("parentId", new Class[i]);
                                } catch (Throwable unused) {
                                    method = null;
                                }
                                Object invoke = declaredMethod.invoke(annotation, new Object[i]);
                                if (method != null) {
                                    obj2 = method.invoke(annotation, new Object[i]);
                                }
                                int length3 = obj2 == null ? 0 : Array.getLength(obj2);
                                int length4 = Array.getLength(invoke);
                                int i4 = 0;
                                while (i4 < length4) {
                                    ViewInjectInfo viewInjectInfo = new ViewInjectInfo();
                                    declaredMethods = declaredMethods;
                                    try {
                                        viewInjectInfo.value = Array.get(invoke, i4);
                                        viewInjectInfo.parentId = length3 > i4 ? ((Integer) Array.get(obj2, i4)).intValue() : 0;
                                        EventListenerManager.addEventMethod(viewFinder, viewInjectInfo, annotation, obj, method2);
                                        i4++;
                                        declaredMethods = declaredMethods;
                                    } catch (Throwable th6) {
                                        th = th6;
                                        LogUtils.m19219e(th.getMessage(), th);
                                        i3++;
                                        z = true;
                                        i = 0;
                                    }
                                }
                                declaredMethods = declaredMethods;
                            } catch (Throwable th7) {
                                th = th7;
                                declaredMethods = declaredMethods;
                            }
                        } else {
                            declaredMethods = declaredMethods;
                        }
                        i3++;
                        z = true;
                        i = 0;
                    }
                }
                i2++;
                z = true;
                i = 0;
            }
        }
    }
}
