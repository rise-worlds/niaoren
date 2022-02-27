package com.noober.background;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.noober.background.view.Const;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class BackgroundFactory implements LayoutInflater.Factory2 {
    private LayoutInflater.Factory mViewCreateFactory;
    private LayoutInflater.Factory2 mViewCreateFactory2;
    private static final Class<?>[] sConstructorSignature = {Context.class, AttributeSet.class};
    private static final Object[] mConstructorArgs = new Object[2];
    private static final Map<String, Constructor<? extends View>> sConstructorMap = new ArrayMap();
    private static final HashMap<String, HashMap<String, Method>> methodMap = new HashMap<>();

    private static boolean hasStatus(int i, int i2) {
        return (i & i2) == i2;
    }

    @Override // android.view.LayoutInflater.Factory
    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        View view = null;
        if (str.startsWith("com.noober.background.view")) {
            return null;
        }
        LayoutInflater.Factory2 factory2 = this.mViewCreateFactory2;
        if (factory2 != null) {
            View onCreateView = factory2.onCreateView(str, context, attributeSet);
            view = onCreateView == null ? this.mViewCreateFactory2.onCreateView(null, str, context, attributeSet) : onCreateView;
        } else {
            LayoutInflater.Factory factory = this.mViewCreateFactory;
            if (factory != null) {
                view = factory.onCreateView(str, context, attributeSet);
            }
        }
        return setViewBackground(str, context, attributeSet, view);
    }

    private String switchBLViewToOriginal(String str) {
        return str.equals(Const.BLButton) ? "Button" : str.equals(Const.BLCheckBox) ? "CheckBox" : str.equals(Const.BLEditText) ? "EditText" : str.equals(Const.BLFrameLayout) ? "FrameLayout" : str.equals(Const.BLGridLayout) ? "GridLayout" : str.equals(Const.BLGridView) ? "GridView" : str.equals(Const.BLImageButton) ? "ImageButton" : str.equals(Const.BLImageView) ? "ImageView" : str.equals(Const.BLLinearLayout) ? "LinearLayout" : str.equals(Const.BLListView) ? "ListView" : str.equals(Const.BLRadioButton) ? "RadioButton" : str.equals(Const.BLRadioGroup) ? "RadioGroup" : str.equals(Const.BLRelativeLayout) ? "RelativeLayout" : str.equals(Const.BLScrollView) ? "ScrollView" : str.equals(Const.BLTextView) ? "TextView" : str.equals(Const.BLView) ? "View" : str.equals(Const.BLConstraintLayout) ? "android.support.constraint.ConstraintLayout" : str;
    }

    @Nullable
    public static View setViewBackground(Context context, AttributeSet attributeSet, View view) {
        return setViewBackground(null, context, attributeSet, view);
    }

    /* JADX WARN: Removed duplicated region for block: B:82:0x0199  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x01ac  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x01de A[Catch: Exception -> 0x021a, all -> 0x021c, TryCatch #1 {Exception -> 0x021a, blocks: (B:29:0x00b0, B:31:0x00b6, B:34:0x00bd, B:35:0x00c4, B:36:0x00c5, B:38:0x00cb, B:41:0x00d2, B:42:0x00d9, B:43:0x00da, B:45:0x00e2, B:47:0x00e6, B:48:0x00f4, B:50:0x00fa, B:51:0x0105, B:53:0x010b, B:54:0x011a, B:56:0x0120, B:57:0x0128, B:59:0x012e, B:60:0x013b, B:62:0x0141, B:64:0x0150, B:66:0x0154, B:68:0x0158, B:70:0x015e, B:71:0x0169, B:73:0x016d, B:75:0x0173, B:76:0x017d, B:78:0x0185, B:80:0x018d, B:84:0x019c, B:86:0x01ae, B:87:0x01d6, B:89:0x01de, B:91:0x01ea), top: B:105:0x00b0 }] */
    @android.support.annotation.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static android.view.View setViewBackground(java.lang.String r18, android.content.Context r19, android.util.AttributeSet r20, android.view.View r21) {
        /*
            Method dump skipped, instructions count: 604
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.noober.background.BackgroundFactory.setViewBackground(java.lang.String, android.content.Context, android.util.AttributeSet, android.view.View):android.view.View");
    }

    private static Method getMethod(Class cls, String str) {
        Method method;
        HashMap<String, Method> hashMap = methodMap.get(cls.getCanonicalName());
        if (hashMap != null) {
            method = methodMap.get(cls.getCanonicalName()).get(str);
        } else {
            hashMap = new HashMap<>();
            methodMap.put(cls.getCanonicalName(), hashMap);
            method = null;
        }
        if (method == null && (method = findMethod(cls, str)) != null) {
            hashMap.put(str, method);
        }
        return method;
    }

    private static Method findMethod(Class cls, String str) {
        try {
            return cls.getMethod(str, new Class[0]);
        } catch (NoSuchMethodException unused) {
            return findDeclaredMethod(cls, str);
        }
    }

    private static Method findDeclaredMethod(Class cls, String str) {
        Method method = null;
        try {
            method = cls.getDeclaredMethod(str, new Class[0]);
            method.setAccessible(true);
            return method;
        } catch (NoSuchMethodException unused) {
            return cls.getSuperclass() != null ? findDeclaredMethod(cls.getSuperclass(), str) : method;
        }
    }

    private static void setDrawable(Drawable drawable, View view, TypedArray typedArray, TypedArray typedArray2) {
        if (!(view instanceof TextView)) {
            setBackground(drawable, view, typedArray2);
        } else if (!typedArray.hasValue(C1944R.styleable.bl_other_bl_position)) {
            setBackground(drawable, view, typedArray2);
        } else if (typedArray.getInt(C1944R.styleable.bl_other_bl_position, 0) == 1) {
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            ((TextView) view).setCompoundDrawables(drawable, null, null, null);
        } else if (typedArray.getInt(C1944R.styleable.bl_other_bl_position, 0) == 2) {
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            ((TextView) view).setCompoundDrawables(null, drawable, null, null);
        } else if (typedArray.getInt(C1944R.styleable.bl_other_bl_position, 0) == 4) {
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            ((TextView) view).setCompoundDrawables(null, null, drawable, null);
        } else if (typedArray.getInt(C1944R.styleable.bl_other_bl_position, 0) == 8) {
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            ((TextView) view).setCompoundDrawables(null, null, null, drawable);
        }
    }

    private static void setBackground(Drawable drawable, View view, TypedArray typedArray) {
        if (typedArray.hasValue(C1944R.styleable.background_bl_stroke_width) && typedArray.hasValue(C1944R.styleable.background_bl_stroke_position)) {
            float dimension = typedArray.getDimension(C1944R.styleable.background_bl_stroke_width, 0.0f);
            int i = typedArray.getInt(C1944R.styleable.background_bl_stroke_position, 0);
            float f = hasStatus(i, 2) ? dimension : -dimension;
            float f2 = hasStatus(i, 4) ? dimension : -dimension;
            float f3 = hasStatus(i, 8) ? dimension : -dimension;
            if (!hasStatus(i, 16)) {
                dimension = -dimension;
            }
            LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{drawable});
            layerDrawable.setLayerInset(0, (int) f, (int) f2, (int) f3, (int) dimension);
            drawable = layerDrawable;
        }
        if (Build.VERSION.SDK_INT >= 16) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }

    public void setInterceptFactory(LayoutInflater.Factory factory) {
        this.mViewCreateFactory = factory;
    }

    public void setInterceptFactory2(LayoutInflater.Factory2 factory2) {
        this.mViewCreateFactory2 = factory2;
    }

    private static View createViewFromTag(Context context, String str, AttributeSet attributeSet) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (str.equals("view")) {
            str = attributeSet.getAttributeValue(null, "class");
        }
        try {
            mConstructorArgs[0] = context;
            mConstructorArgs[1] = attributeSet;
            if (-1 != str.indexOf(46)) {
                return createView(context, str, null);
            }
            View createView = "View".equals(str) ? createView(context, str, "android.view.") : null;
            if (createView == null) {
                createView = createView(context, str, "android.widget.");
            }
            if (createView == null) {
                createView = createView(context, str, "android.webkit.");
            }
            return createView;
        } catch (Exception unused) {
            Log.w("BackgroundLibrary", "cannot create 【" + str + "】 : ");
            return null;
        } finally {
            Object[] objArr = mConstructorArgs;
            objArr[0] = null;
            objArr[1] = null;
        }
    }

    private static View createView(Context context, String str, String str2) throws InflateException {
        String str3;
        Constructor<? extends View> constructor = sConstructorMap.get(str);
        if (constructor == null) {
            try {
                ClassLoader classLoader = context.getClassLoader();
                if (str2 != null) {
                    str3 = str2 + str;
                } else {
                    str3 = str;
                }
                constructor = classLoader.loadClass(str3).asSubclass(View.class).getConstructor(sConstructorSignature);
                sConstructorMap.put(str, constructor);
            } catch (Exception unused) {
                Log.w("BackgroundLibrary", "cannot create 【" + str + "】 : ");
                return null;
            }
        }
        constructor.setAccessible(true);
        return (View) constructor.newInstance(mConstructorArgs);
    }

    @Override // android.view.LayoutInflater.Factory2
    public View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        return onCreateView(str, context, attributeSet);
    }
}
