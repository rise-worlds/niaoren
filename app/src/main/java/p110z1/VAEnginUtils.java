package p110z1;

import android.app.Activity;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/* renamed from: z1.ajj */
/* loaded from: classes3.dex */
public class VAEnginUtils {

    /* renamed from: a */
    static Activity f16062a;

    /* renamed from: b */
    static Handler f16063b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: VAEnginUtils.java */
    /* renamed from: z1.ajj$a */
    /* loaded from: classes3.dex */
    public static class RunnableC3526a implements Runnable {

        /* renamed from: a */
        public static int f16064a = -1;

        /* renamed from: b */
        public static int f16065b = -1;

        /* renamed from: c */
        public static String f16066c;

        /* renamed from: d */
        View f16067d;

        public RunnableC3526a(View view) {
            this.f16067d = view;
        }

        @Override // java.lang.Runnable
        public void run() {
            int i = f16064a;
            if (i == 0) {
                this.f16067d.dispatchKeyEvent(new KeyEvent(0, f16065b));
                this.f16067d.dispatchKeyEvent(new KeyEvent(1, f16065b));
            } else if (i == 1) {
                View view = this.f16067d;
                if (view instanceof TextView) {
                    ((TextView) view).setText(f16066c);
                    View view2 = this.f16067d;
                    if (view2 instanceof EditText) {
                        ((EditText) view2).setSelection(f16066c.length());
                    }
                }
            }
        }
    }

    /* renamed from: a */
    public static void m12917a(Activity activity) {
        f16062a = activity;
    }

    /* renamed from: a */
    public static void m12916a(View view) {
        Handler handler;
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            viewGroup.setOnTouchListener(new View.OnTouchListener() { // from class: z1.ajj.1
                @Override // android.view.View.OnTouchListener
                public boolean onTouch(View view2, MotionEvent motionEvent) {
                    return view2.onTouchEvent(motionEvent);
                }
            });
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                m12916a(viewGroup.getChildAt(i));
            }
        } else if (view.isFocused() && (handler = f16063b) != null) {
            handler.post(new RunnableC3526a(view));
        }
    }

    /* renamed from: b */
    public static ViewGroup m12914b(View view) {
        ViewGroup viewGroup = (ViewGroup) view;
        if (viewGroup.getChildCount() <= 0) {
            return viewGroup;
        }
        View childAt = viewGroup.getChildAt(0);
        return childAt instanceof ViewGroup ? m12914b(childAt) : viewGroup;
    }

    /* renamed from: a */
    public static void m12918a(int i) {
        if (f16062a != null) {
            RunnableC3526a.f16064a = 0;
            RunnableC3526a.f16065b = i;
            try {
                Field declaredField = Activity.class.getDeclaredField("mHandler");
                declaredField.setAccessible(true);
                f16063b = (Handler) declaredField.get(f16062a);
                m12916a(f16062a.getWindow().getDecorView().findViewById(16908290));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e2) {
                e2.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    public static void m12915a(String str) {
        if (f16062a != null) {
            RunnableC3526a.f16064a = 1;
            RunnableC3526a.f16066c = str;
            try {
                Field declaredField = Activity.class.getDeclaredField("mHandler");
                declaredField.setAccessible(true);
                f16063b = (Handler) declaredField.get(f16062a);
                m12916a(f16062a.getWindow().getDecorView().findViewById(16908290));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e2) {
                e2.printStackTrace();
            }
        }
    }

    /* renamed from: b */
    public static Class<?> m12913b(String str) {
        Class<?> cls;
        ClassNotFoundException e;
        NoSuchFieldException e2;
        IllegalAccessException e3;
        NoSuchMethodException e4;
        InvocationTargetException e5;
        try {
            Class<?> cls2 = Class.forName("android.app.ApplicationLoaders");
            Field declaredField = cls2.getDeclaredField("gApplicationLoaders");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(null);
            Field declaredField2 = cls2.getDeclaredField("mLoaders");
            declaredField2.setAccessible(true);
            Object obj2 = declaredField2.get(obj);
            Object invoke = obj2.getClass().getDeclaredMethod("values", new Class[0]).invoke(obj2, new Object[0]);
            cls = null;
            for (Object obj3 : (Object[]) invoke.getClass().getDeclaredMethod("toArray", new Class[0]).invoke(invoke, new Object[0])) {
                try {
                    try {
                        try {
                            cls = (Class) ClassLoader.class.getDeclaredMethod("loadClass", String.class).invoke(obj3, str);
                        } catch (InvocationTargetException unused) {
                        }
                        if (cls != null) {
                            break;
                        }
                    } catch (InvocationTargetException e6) {
                        e5 = e6;
                        e5.printStackTrace();
                        return cls;
                    }
                } catch (ClassNotFoundException e7) {
                    e = e7;
                    e.printStackTrace();
                    return cls;
                } catch (IllegalAccessException e8) {
                    e3 = e8;
                    e3.printStackTrace();
                    return cls;
                } catch (NoSuchFieldException e9) {
                    e2 = e9;
                    e2.printStackTrace();
                    return cls;
                } catch (NoSuchMethodException e10) {
                    e4 = e10;
                    e4.printStackTrace();
                    return cls;
                }
            }
        } catch (ClassNotFoundException e11) {
            e = e11;
            cls = null;
        } catch (IllegalAccessException e12) {
            e3 = e12;
            cls = null;
        } catch (NoSuchFieldException e13) {
            e2 = e13;
            cls = null;
        } catch (NoSuchMethodException e14) {
            e4 = e14;
            cls = null;
        } catch (InvocationTargetException e15) {
            e5 = e15;
            cls = null;
        }
        return cls;
    }
}
