package com.gyf.barlibrary;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.database.ContentObserver;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.FloatRange;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.p003v4.app.DialogFragment;
import android.support.p003v4.app.Fragment;
import android.support.p003v4.content.ContextCompat;
import android.support.p003v4.graphics.ColorUtils;
import android.support.p003v4.view.GravityCompat;
import android.support.p003v4.widget.DrawerLayout;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@TargetApi(19)
/* renamed from: com.gyf.barlibrary.g */
/* loaded from: classes.dex */
public class ImmersionBar {

    /* renamed from: c */
    private static final String f9319c = "navigationbar_is_min";

    /* renamed from: d */
    private static final String f9320d = "EXTRA_FLAG_STATUS_BAR_DARK_MODE";

    /* renamed from: e */
    private static final String f9321e = "EXTRA_FLAG_NAVIGATION_BAR_DARK_MODE";

    /* renamed from: f */
    private static final int f9322f = -4539718;

    /* renamed from: g */
    private static final int f9323g = 0;

    /* renamed from: h */
    private static final int f9324h = 1;

    /* renamed from: i */
    private static final int f9325i = 2;

    /* renamed from: j */
    private static final int f9326j = 3;

    /* renamed from: k */
    private static final int f9327k = 4;

    /* renamed from: A */
    private Map<String, BarParams> f9329A;

    /* renamed from: B */
    private boolean f9330B;

    /* renamed from: C */
    private int f9331C;

    /* renamed from: D */
    private boolean f9332D;

    /* renamed from: E */
    private boolean f9333E;

    /* renamed from: F */
    private int f9334F;

    /* renamed from: G */
    private int f9335G;

    /* renamed from: H */
    private int f9336H;

    /* renamed from: I */
    private int f9337I;

    /* renamed from: m */
    private Activity f9338m;

    /* renamed from: n */
    private Fragment f9339n;

    /* renamed from: o */
    private Dialog f9340o;

    /* renamed from: p */
    private Window f9341p;

    /* renamed from: q */
    private ViewGroup f9342q;

    /* renamed from: r */
    private ViewGroup f9343r;

    /* renamed from: s */
    private BarParams f9344s;

    /* renamed from: t */
    private BarConfig f9345t;

    /* renamed from: u */
    private String f9346u;

    /* renamed from: v */
    private int f9347v;

    /* renamed from: w */
    private int f9348w;

    /* renamed from: x */
    private boolean f9349x;

    /* renamed from: y */
    private ContentObserver f9350y;

    /* renamed from: z */
    private FitsKeyboard f9351z;

    /* renamed from: a */
    private static final int f9317a = C1432R.C1433id.immersion_status_bar_view;

    /* renamed from: b */
    private static final int f9318b = C1432R.C1433id.immersion_navigation_bar_view;

    /* renamed from: l */
    private static Map<String, ImmersionBar> f9328l = new HashMap();

    private ImmersionBar(Activity activity) {
        this.f9347v = 0;
        this.f9348w = 0;
        this.f9349x = false;
        this.f9350y = null;
        this.f9351z = null;
        this.f9329A = new HashMap();
        this.f9330B = false;
        this.f9331C = 0;
        this.f9332D = false;
        this.f9333E = false;
        this.f9334F = 0;
        this.f9335G = 0;
        this.f9336H = 0;
        this.f9337I = 0;
        this.f9338m = activity;
        this.f9341p = this.f9338m.getWindow();
        this.f9346u = this.f9338m.toString();
        this.f9344s = new BarParams();
        this.f9342q = (ViewGroup) this.f9341p.getDecorView();
        this.f9343r = (ViewGroup) this.f9342q.findViewById(16908290);
    }

    private ImmersionBar(Fragment fragment) {
        this(fragment.getActivity(), fragment);
    }

    private ImmersionBar(Activity activity, Fragment fragment) {
        this.f9347v = 0;
        this.f9348w = 0;
        this.f9349x = false;
        this.f9350y = null;
        this.f9351z = null;
        this.f9329A = new HashMap();
        this.f9330B = false;
        this.f9331C = 0;
        this.f9332D = false;
        this.f9333E = false;
        this.f9334F = 0;
        this.f9335G = 0;
        this.f9336H = 0;
        this.f9337I = 0;
        this.f9338m = activity;
        this.f9339n = fragment;
        Activity activity2 = this.f9338m;
        if (activity2 == null) {
            throw new IllegalArgumentException("Activity不能为空!!!");
        } else if (f9328l.get(activity2.toString()) != null) {
            this.f9349x = true;
            this.f9341p = this.f9338m.getWindow();
            this.f9346u = activity.toString() + fragment.toString();
            this.f9344s = new BarParams();
            this.f9342q = (ViewGroup) this.f9341p.getDecorView();
            this.f9343r = (ViewGroup) this.f9342q.findViewById(16908290);
        } else {
            throw new IllegalArgumentException("必须先在宿主Activity初始化");
        }
    }

    private ImmersionBar(DialogFragment dialogFragment) {
        this(dialogFragment, dialogFragment.getDialog());
    }

    private ImmersionBar(DialogFragment dialogFragment, Dialog dialog) {
        this.f9347v = 0;
        this.f9348w = 0;
        this.f9349x = false;
        this.f9350y = null;
        this.f9351z = null;
        this.f9329A = new HashMap();
        this.f9330B = false;
        this.f9331C = 0;
        this.f9332D = false;
        this.f9333E = false;
        this.f9334F = 0;
        this.f9335G = 0;
        this.f9336H = 0;
        this.f9337I = 0;
        this.f9338m = dialogFragment.getActivity();
        this.f9339n = dialogFragment;
        this.f9340o = dialog;
        Activity activity = this.f9338m;
        if (activity == null) {
            throw new IllegalArgumentException("Activity不能为空!!!");
        } else if (this.f9340o == null) {
            throw new IllegalArgumentException("DialogFragment中的dialog不能为空");
        } else if (f9328l.get(activity.toString()) != null) {
            this.f9341p = this.f9340o.getWindow();
            this.f9346u = this.f9338m.toString() + dialogFragment.toString();
            this.f9344s = new BarParams();
            this.f9342q = (ViewGroup) this.f9341p.getDecorView();
            this.f9343r = (ViewGroup) this.f9342q.findViewById(16908290);
        } else {
            throw new IllegalArgumentException("必须先在宿主Activity初始化");
        }
    }

    private ImmersionBar(Activity activity, Dialog dialog) {
        this(activity, dialog, "");
    }

    private ImmersionBar(Activity activity, Dialog dialog, String str) {
        this.f9347v = 0;
        this.f9348w = 0;
        this.f9349x = false;
        this.f9350y = null;
        this.f9351z = null;
        this.f9329A = new HashMap();
        this.f9330B = false;
        this.f9331C = 0;
        this.f9332D = false;
        this.f9333E = false;
        this.f9334F = 0;
        this.f9335G = 0;
        this.f9336H = 0;
        this.f9337I = 0;
        this.f9338m = activity;
        this.f9340o = dialog;
        Activity activity2 = this.f9338m;
        if (activity2 == null) {
            throw new IllegalArgumentException("Activity不能为空!!!");
        } else if (this.f9340o == null) {
            throw new IllegalArgumentException("dialog不能为空");
        } else if (f9328l.get(activity2.toString()) != null) {
            this.f9341p = this.f9340o.getWindow();
            this.f9346u = activity.toString() + dialog.toString() + str;
            this.f9344s = new BarParams();
            this.f9342q = (ViewGroup) this.f9341p.getDecorView();
            this.f9343r = (ViewGroup) this.f9342q.findViewById(16908290);
        } else {
            throw new IllegalArgumentException("必须先在宿主Activity初始化");
        }
    }

    /* renamed from: a */
    public static ImmersionBar m20080a(@NonNull Activity activity) {
        ImmersionBar gVar = f9328l.get(activity.toString());
        if (gVar != null) {
            return gVar;
        }
        ImmersionBar gVar2 = new ImmersionBar(activity);
        f9328l.put(activity.toString(), gVar2);
        return gVar2;
    }

    /* renamed from: a */
    public static ImmersionBar m20073a(@NonNull Fragment fragment) {
        if (fragment.getActivity() != null) {
            Map<String, ImmersionBar> map = f9328l;
            ImmersionBar gVar = map.get(fragment.getActivity().toString() + fragment.toString());
            if (gVar != null) {
                return gVar;
            }
            ImmersionBar gVar2 = new ImmersionBar(fragment);
            Map<String, ImmersionBar> map2 = f9328l;
            map2.put(fragment.getActivity().toString() + fragment.toString(), gVar2);
            return gVar2;
        }
        throw new IllegalArgumentException("Activity不能为空!!!");
    }

    /* renamed from: a */
    public static ImmersionBar m20077a(@NonNull Activity activity, @NonNull Fragment fragment) {
        Map<String, ImmersionBar> map = f9328l;
        ImmersionBar gVar = map.get(activity.toString() + fragment.toString());
        if (gVar != null) {
            return gVar;
        }
        ImmersionBar gVar2 = new ImmersionBar(activity, fragment);
        Map<String, ImmersionBar> map2 = f9328l;
        map2.put(activity.toString() + fragment.toString(), gVar2);
        return gVar2;
    }

    /* renamed from: a */
    public static ImmersionBar m20075a(@NonNull DialogFragment dialogFragment) {
        if (dialogFragment.getActivity() != null) {
            Map<String, ImmersionBar> map = f9328l;
            ImmersionBar gVar = map.get(dialogFragment.getActivity().toString() + dialogFragment.toString());
            if (gVar != null) {
                return gVar;
            }
            ImmersionBar gVar2 = new ImmersionBar(dialogFragment);
            Map<String, ImmersionBar> map2 = f9328l;
            map2.put(dialogFragment.getActivity().toString() + dialogFragment.toString(), gVar2);
            return gVar2;
        }
        throw new IllegalArgumentException("Activity不能为空!!!");
    }

    @Deprecated
    /* renamed from: a */
    public static ImmersionBar m20074a(@NonNull DialogFragment dialogFragment, @NonNull Dialog dialog) {
        if (dialogFragment.getActivity() != null) {
            Map<String, ImmersionBar> map = f9328l;
            ImmersionBar gVar = map.get(dialogFragment.getActivity().toString() + dialogFragment.toString());
            if (gVar != null) {
                return gVar;
            }
            ImmersionBar gVar2 = new ImmersionBar(dialogFragment, dialog);
            Map<String, ImmersionBar> map2 = f9328l;
            map2.put(dialogFragment.getActivity().toString() + dialogFragment.toString(), gVar2);
            return gVar2;
        }
        throw new IllegalArgumentException("Activity不能为空!!!");
    }

    /* renamed from: a */
    public static ImmersionBar m20079a(@NonNull Activity activity, @NonNull Dialog dialog) {
        Map<String, ImmersionBar> map = f9328l;
        ImmersionBar gVar = map.get(activity.toString() + dialog.toString());
        if (gVar != null) {
            return gVar;
        }
        ImmersionBar gVar2 = new ImmersionBar(activity, dialog);
        Map<String, ImmersionBar> map2 = f9328l;
        map2.put(activity.toString() + dialog.toString(), gVar2);
        return gVar2;
    }

    @Deprecated
    /* renamed from: a */
    public static ImmersionBar m20078a(@NonNull Activity activity, @NonNull Dialog dialog, @NonNull String str) {
        Map<String, ImmersionBar> map = f9328l;
        ImmersionBar gVar = map.get(activity.toString() + dialog.toString() + str);
        if (gVar != null) {
            return gVar;
        }
        ImmersionBar gVar2 = new ImmersionBar(activity, dialog, str);
        Map<String, ImmersionBar> map2 = f9328l;
        map2.put(activity.toString() + dialog.toString() + str, gVar2);
        return gVar2;
    }

    /* renamed from: a */
    public ImmersionBar m20089a() {
        this.f9344s.f9267a = 0;
        return this;
    }

    /* renamed from: b */
    public ImmersionBar m20051b() {
        BarParams cVar = this.f9344s;
        cVar.f9268b = 0;
        cVar.f9272f = true;
        return this;
    }

    /* renamed from: c */
    public ImmersionBar m20031c() {
        BarParams cVar = this.f9344s;
        cVar.f9267a = 0;
        cVar.f9268b = 0;
        cVar.f9272f = true;
        return this;
    }

    /* renamed from: a */
    public ImmersionBar m20087a(@ColorRes int i) {
        return m20049b(ContextCompat.getColor(this.f9338m, i));
    }

    /* renamed from: a */
    public ImmersionBar m20086a(@ColorRes int i, @FloatRange(from = 0.0d, m25696to = 1.0d) float f) {
        return m20048b(ContextCompat.getColor(this.f9338m, i), f);
    }

    /* renamed from: a */
    public ImmersionBar m20085a(@ColorRes int i, @ColorRes int i2, @FloatRange(from = 0.0d, m25696to = 1.0d) float f) {
        return m20047b(ContextCompat.getColor(this.f9338m, i), ContextCompat.getColor(this.f9338m, i2), f);
    }

    /* renamed from: a */
    public ImmersionBar m20058a(String str) {
        return m20049b(Color.parseColor(str));
    }

    /* renamed from: a */
    public ImmersionBar m20057a(String str, @FloatRange(from = 0.0d, m25696to = 1.0d) float f) {
        return m20048b(Color.parseColor(str), f);
    }

    /* renamed from: a */
    public ImmersionBar m20056a(String str, String str2, @FloatRange(from = 0.0d, m25696to = 1.0d) float f) {
        return m20047b(Color.parseColor(str), Color.parseColor(str2), f);
    }

    /* renamed from: b */
    public ImmersionBar m20049b(@ColorInt int i) {
        this.f9344s.f9267a = i;
        return this;
    }

    /* renamed from: b */
    public ImmersionBar m20048b(@ColorInt int i, @FloatRange(from = 0.0d, m25696to = 1.0d) float f) {
        BarParams cVar = this.f9344s;
        cVar.f9267a = i;
        cVar.f9270d = f;
        return this;
    }

    /* renamed from: b */
    public ImmersionBar m20047b(@ColorInt int i, @ColorInt int i2, @FloatRange(from = 0.0d, m25696to = 1.0d) float f) {
        BarParams cVar = this.f9344s;
        cVar.f9267a = i;
        cVar.f9282p = i2;
        cVar.f9270d = f;
        return this;
    }

    /* renamed from: c */
    public ImmersionBar m20029c(@ColorRes int i) {
        return m20013d(ContextCompat.getColor(this.f9338m, i));
    }

    /* renamed from: c */
    public ImmersionBar m20028c(@ColorRes int i, @FloatRange(from = 0.0d, m25696to = 1.0d) float f) {
        return m20012d(ContextCompat.getColor(this.f9338m, i), f);
    }

    /* renamed from: c */
    public ImmersionBar m20027c(@ColorRes int i, @ColorRes int i2, @FloatRange(from = 0.0d, m25696to = 1.0d) float f) {
        return m20011d(ContextCompat.getColor(this.f9338m, i), ContextCompat.getColor(this.f9338m, i2), f);
    }

    /* renamed from: b */
    public ImmersionBar m20038b(String str) {
        return m20013d(Color.parseColor(str));
    }

    /* renamed from: b */
    public ImmersionBar m20037b(String str, @FloatRange(from = 0.0d, m25696to = 1.0d) float f) {
        return m20012d(Color.parseColor(str), f);
    }

    /* renamed from: b */
    public ImmersionBar m20036b(String str, String str2, @FloatRange(from = 0.0d, m25696to = 1.0d) float f) {
        return m20011d(Color.parseColor(str), Color.parseColor(str2), f);
    }

    /* renamed from: d */
    public ImmersionBar m20013d(@ColorInt int i) {
        this.f9344s.f9268b = i;
        return this;
    }

    /* renamed from: d */
    public ImmersionBar m20012d(@ColorInt int i, @FloatRange(from = 0.0d, m25696to = 1.0d) float f) {
        BarParams cVar = this.f9344s;
        cVar.f9268b = i;
        cVar.f9271e = f;
        return this;
    }

    /* renamed from: d */
    public ImmersionBar m20011d(@ColorInt int i, @ColorInt int i2, @FloatRange(from = 0.0d, m25696to = 1.0d) float f) {
        BarParams cVar = this.f9344s;
        cVar.f9268b = i;
        cVar.f9283q = i2;
        cVar.f9271e = f;
        return this;
    }

    /* renamed from: e */
    public ImmersionBar m20003e(@ColorRes int i) {
        return m19993f(ContextCompat.getColor(this.f9338m, i));
    }

    /* renamed from: e */
    public ImmersionBar m20002e(@ColorRes int i, @FloatRange(from = 0.0d, m25696to = 1.0d) float f) {
        return m19992f(ContextCompat.getColor(this.f9338m, i), i);
    }

    /* renamed from: e */
    public ImmersionBar m20001e(@ColorRes int i, @ColorRes int i2, @FloatRange(from = 0.0d, m25696to = 1.0d) float f) {
        return m19991f(ContextCompat.getColor(this.f9338m, i), ContextCompat.getColor(this.f9338m, i2), f);
    }

    /* renamed from: c */
    public ImmersionBar m20021c(String str) {
        return m19993f(Color.parseColor(str));
    }

    /* renamed from: c */
    public ImmersionBar m20020c(String str, @FloatRange(from = 0.0d, m25696to = 1.0d) float f) {
        return m19992f(Color.parseColor(str), f);
    }

    /* renamed from: c */
    public ImmersionBar m20019c(String str, String str2, @FloatRange(from = 0.0d, m25696to = 1.0d) float f) {
        return m19991f(Color.parseColor(str), Color.parseColor(str2), f);
    }

    /* renamed from: f */
    public ImmersionBar m19993f(@ColorInt int i) {
        BarParams cVar = this.f9344s;
        cVar.f9267a = i;
        cVar.f9268b = i;
        return this;
    }

    /* renamed from: f */
    public ImmersionBar m19992f(@ColorInt int i, @FloatRange(from = 0.0d, m25696to = 1.0d) float f) {
        BarParams cVar = this.f9344s;
        cVar.f9267a = i;
        cVar.f9268b = i;
        cVar.f9270d = f;
        cVar.f9271e = f;
        return this;
    }

    /* renamed from: f */
    public ImmersionBar m19991f(@ColorInt int i, @ColorInt int i2, @FloatRange(from = 0.0d, m25696to = 1.0d) float f) {
        BarParams cVar = this.f9344s;
        cVar.f9267a = i;
        cVar.f9268b = i;
        cVar.f9282p = i2;
        cVar.f9283q = i2;
        cVar.f9270d = f;
        cVar.f9271e = f;
        return this;
    }

    /* renamed from: g */
    public ImmersionBar m19984g(@ColorRes int i) {
        return m19977h(ContextCompat.getColor(this.f9338m, i));
    }

    /* renamed from: d */
    public ImmersionBar m20007d(String str) {
        return m19977h(Color.parseColor(str));
    }

    /* renamed from: h */
    public ImmersionBar m19977h(@ColorInt int i) {
        this.f9344s.f9282p = i;
        return this;
    }

    /* renamed from: i */
    public ImmersionBar m19972i(@ColorRes int i) {
        return m19967j(ContextCompat.getColor(this.f9338m, i));
    }

    /* renamed from: e */
    public ImmersionBar m19997e(String str) {
        return m19967j(Color.parseColor(str));
    }

    /* renamed from: j */
    public ImmersionBar m19967j(@ColorInt int i) {
        this.f9344s.f9283q = i;
        return this;
    }

    /* renamed from: k */
    public ImmersionBar m19963k(@ColorRes int i) {
        return m19960l(ContextCompat.getColor(this.f9338m, i));
    }

    /* renamed from: f */
    public ImmersionBar m19987f(String str) {
        return m19960l(Color.parseColor(str));
    }

    /* renamed from: l */
    public ImmersionBar m19960l(@ColorInt int i) {
        BarParams cVar = this.f9344s;
        cVar.f9282p = i;
        cVar.f9283q = i;
        return this;
    }

    /* renamed from: a */
    public ImmersionBar m20072a(View view) {
        return m20042b(view, this.f9344s.f9282p);
    }

    /* renamed from: a */
    public ImmersionBar m20071a(View view, @ColorRes int i) {
        return m20042b(view, ContextCompat.getColor(this.f9338m, i));
    }

    /* renamed from: a */
    public ImmersionBar m20070a(View view, @ColorRes int i, @ColorRes int i2) {
        return m20041b(view, ContextCompat.getColor(this.f9338m, i), ContextCompat.getColor(this.f9338m, i2));
    }

    /* renamed from: a */
    public ImmersionBar m20069a(View view, String str) {
        return m20042b(view, Color.parseColor(str));
    }

    /* renamed from: a */
    public ImmersionBar m20068a(View view, String str, String str2) {
        return m20041b(view, Color.parseColor(str), Color.parseColor(str2));
    }

    /* renamed from: b */
    public ImmersionBar m20042b(View view, @ColorInt int i) {
        if (view != null) {
            HashMap hashMap = new HashMap();
            hashMap.put(Integer.valueOf(this.f9344s.f9267a), Integer.valueOf(i));
            this.f9344s.f9284r.put(view, hashMap);
            return this;
        }
        throw new IllegalArgumentException("View参数不能为空");
    }

    /* renamed from: b */
    public ImmersionBar m20041b(View view, @ColorInt int i, @ColorInt int i2) {
        if (view != null) {
            HashMap hashMap = new HashMap();
            hashMap.put(Integer.valueOf(i), Integer.valueOf(i2));
            this.f9344s.f9284r.put(view, hashMap);
            return this;
        }
        throw new IllegalArgumentException("View参数不能为空");
    }

    /* renamed from: a */
    public ImmersionBar m20088a(@FloatRange(from = 0.0d, m25696to = 1.0d) float f) {
        this.f9344s.f9285s = f;
        return this;
    }

    /* renamed from: b */
    public ImmersionBar m20043b(View view) {
        if (view != null) {
            if (this.f9344s.f9284r.get(view).size() != 0) {
                this.f9344s.f9284r.remove(view);
            }
            return this;
        }
        throw new IllegalArgumentException("View参数不能为空");
    }

    /* renamed from: d */
    public ImmersionBar m20015d() {
        if (this.f9344s.f9284r.size() != 0) {
            this.f9344s.f9284r.clear();
        }
        return this;
    }

    /* renamed from: a */
    public ImmersionBar m20055a(boolean z) {
        this.f9344s.f9272f = z;
        return this;
    }

    /* renamed from: b */
    public ImmersionBar m20050b(@FloatRange(from = 0.0d, m25696to = 1.0d) float f) {
        this.f9344s.f9270d = f;
        return this;
    }

    /* renamed from: c */
    public ImmersionBar m20030c(@FloatRange(from = 0.0d, m25696to = 1.0d) float f) {
        this.f9344s.f9271e = f;
        return this;
    }

    /* renamed from: d */
    public ImmersionBar m20014d(@FloatRange(from = 0.0d, m25696to = 1.0d) float f) {
        BarParams cVar = this.f9344s;
        cVar.f9270d = f;
        cVar.f9271e = f;
        return this;
    }

    /* renamed from: b */
    public ImmersionBar m20035b(boolean z) {
        return m20054a(z, 0.0f);
    }

    /* renamed from: a */
    public ImmersionBar m20054a(boolean z, @FloatRange(from = 0.0d, m25696to = 1.0d) float f) {
        BarParams cVar = this.f9344s;
        cVar.f9277k = z;
        cVar.f9279m = f;
        cVar.f9278l = z;
        cVar.f9280n = f;
        return this;
    }

    /* renamed from: b */
    public ImmersionBar m20034b(boolean z, @FloatRange(from = 0.0d, m25696to = 1.0d) float f) {
        BarParams cVar = this.f9344s;
        cVar.f9277k = z;
        cVar.f9279m = f;
        return this;
    }

    /* renamed from: c */
    public ImmersionBar m20017c(boolean z, @FloatRange(from = 0.0d, m25696to = 1.0d) float f) {
        BarParams cVar = this.f9344s;
        cVar.f9278l = z;
        cVar.f9280n = f;
        return this;
    }

    /* renamed from: c */
    public ImmersionBar m20018c(boolean z) {
        return m20005d(z, 0.0f);
    }

    /* renamed from: d */
    public ImmersionBar m20005d(boolean z, @FloatRange(from = 0.0d, m25696to = 1.0d) float f) {
        this.f9344s.f9275i = z;
        if (!z || m19958m()) {
            BarParams cVar = this.f9344s;
            cVar.f9292z = 0;
            cVar.f9270d = 0.0f;
        } else {
            this.f9344s.f9270d = f;
        }
        return this;
    }

    /* renamed from: d */
    public ImmersionBar m20006d(boolean z) {
        return m19995e(z, 0.0f);
    }

    /* renamed from: e */
    public ImmersionBar m19995e(boolean z, @FloatRange(from = 0.0d, m25696to = 1.0d) float f) {
        this.f9344s.f9276j = z;
        if (!z || m19956n()) {
            this.f9344s.f9271e = 0.0f;
        } else {
            this.f9344s.f9271e = f;
        }
        return this;
    }

    /* renamed from: m */
    public ImmersionBar m19957m(@ColorRes int i) {
        this.f9344s.f9292z = ContextCompat.getColor(this.f9338m, i);
        return this;
    }

    /* renamed from: g */
    public ImmersionBar m19980g(String str) {
        this.f9344s.f9292z = Color.parseColor(str);
        return this;
    }

    /* renamed from: n */
    public ImmersionBar m19955n(@ColorInt int i) {
        this.f9344s.f9292z = i;
        return this;
    }

    /* renamed from: a */
    public ImmersionBar m20064a(BarHide bVar) {
        this.f9344s.f9274h = bVar;
        if (Build.VERSION.SDK_INT == 19 || OSUtils.m19901h()) {
            if (this.f9344s.f9274h == BarHide.FLAG_HIDE_NAVIGATION_BAR || this.f9344s.f9274h == BarHide.FLAG_HIDE_BAR) {
                this.f9344s.f9273g = true;
            } else {
                this.f9344s.f9273g = false;
            }
        }
        return this;
    }

    /* renamed from: e */
    public ImmersionBar m19996e(boolean z) {
        BarParams cVar = this.f9344s;
        cVar.f9289w = z;
        if (!cVar.f9289w) {
            this.f9331C = 0;
        } else if (this.f9331C == 0) {
            this.f9331C = 4;
        }
        return this;
    }

    /* renamed from: a */
    public ImmersionBar m20053a(boolean z, @ColorRes int i) {
        return m20033b(z, ContextCompat.getColor(this.f9338m, i));
    }

    /* renamed from: a */
    public ImmersionBar m20052a(boolean z, @ColorRes int i, @ColorRes int i2, @FloatRange(from = 0.0d, m25696to = 1.0d) float f) {
        return m20032b(z, ContextCompat.getColor(this.f9338m, i), ContextCompat.getColor(this.f9338m, i2), f);
    }

    /* renamed from: b */
    public ImmersionBar m20033b(boolean z, @ColorInt int i) {
        return m20032b(z, i, -16777216, 0.0f);
    }

    /* renamed from: b */
    public ImmersionBar m20032b(boolean z, @ColorInt int i, @ColorInt int i2, @FloatRange(from = 0.0d, m25696to = 1.0d) float f) {
        BarParams cVar = this.f9344s;
        cVar.f9289w = z;
        cVar.f9286t = i;
        cVar.f9287u = i2;
        cVar.f9288v = f;
        if (!cVar.f9289w) {
            this.f9331C = 0;
        } else if (this.f9331C == 0) {
            this.f9331C = 4;
        }
        this.f9343r.setBackgroundColor(ColorUtils.blendARGB(this.f9344s.f9286t, this.f9344s.f9287u, this.f9344s.f9288v));
        return this;
    }

    /* renamed from: c */
    public ImmersionBar m20023c(View view) {
        if (view == null) {
            return this;
        }
        this.f9344s.f9291y = view;
        if (this.f9331C == 0) {
            this.f9331C = 3;
        }
        return this;
    }

    /* renamed from: o */
    public ImmersionBar m19953o(@IdRes int i) {
        return m20023c(this.f9338m.findViewById(i));
    }

    /* renamed from: a */
    public ImmersionBar m20083a(@IdRes int i, View view) {
        return m20023c(view.findViewById(i));
    }

    /* renamed from: f */
    public ImmersionBar m19986f(boolean z) {
        this.f9344s.f9259A = z;
        return this;
    }

    /* renamed from: d */
    public ImmersionBar m20009d(View view) {
        return view == null ? this : m20067a(view, true);
    }

    /* renamed from: a */
    public ImmersionBar m20067a(View view, boolean z) {
        if (view == null) {
            return this;
        }
        if (this.f9331C == 0) {
            this.f9331C = 1;
        }
        BarParams cVar = this.f9344s;
        cVar.f9290x = view;
        cVar.f9281o = z;
        return this;
    }

    /* renamed from: p */
    public ImmersionBar m19951p(@IdRes int i) {
        return m20081a(i, true);
    }

    /* renamed from: a */
    public ImmersionBar m20081a(@IdRes int i, boolean z) {
        Fragment fragment = this.f9339n;
        if (fragment == null || fragment.getView() == null) {
            return m20067a(this.f9338m.findViewById(i), z);
        }
        return m20067a(this.f9339n.getView().findViewById(i), z);
    }

    /* renamed from: b */
    public ImmersionBar m20046b(@IdRes int i, View view) {
        return m20067a(view.findViewById(i), true);
    }

    /* renamed from: a */
    public ImmersionBar m20082a(@IdRes int i, View view, boolean z) {
        return m20067a(view.findViewById(i), z);
    }

    /* renamed from: q */
    public ImmersionBar m19949q(@IdRes int i) {
        Fragment fragment = this.f9339n;
        if (fragment == null || fragment.getView() == null) {
            return m19999e(this.f9338m.findViewById(i));
        }
        return m19999e(this.f9339n.getView().findViewById(i));
    }

    /* renamed from: c */
    public ImmersionBar m20026c(@IdRes int i, View view) {
        return m19999e(view.findViewById(i));
    }

    /* renamed from: e */
    public ImmersionBar m19999e(View view) {
        if (view == null) {
            return this;
        }
        if (this.f9331C == 0) {
            this.f9331C = 2;
        }
        this.f9344s.f9290x = view;
        return this;
    }

    /* renamed from: g */
    public ImmersionBar m19979g(boolean z) {
        this.f9344s.f9281o = z;
        return this;
    }

    /* renamed from: e */
    public ImmersionBar m20004e() {
        this.f9344s = new BarParams();
        this.f9331C = 0;
        return this;
    }

    /* renamed from: h */
    public ImmersionBar m19975h(String str) {
        if (!m19966j(str)) {
            this.f9329A.put(str, this.f9344s.clone());
            return this;
        }
        throw new IllegalArgumentException("tag不能为空");
    }

    /* renamed from: i */
    public ImmersionBar m19970i(String str) {
        if (!m19966j(str)) {
            BarParams cVar = this.f9329A.get(str);
            if (cVar != null) {
                this.f9344s = cVar.clone();
            }
            return this;
        }
        throw new IllegalArgumentException("tag不能为空");
    }

    /* renamed from: h */
    public ImmersionBar m19974h(boolean z) {
        return m20016c(z, this.f9344s.f9261C);
    }

    /* renamed from: c */
    public ImmersionBar m20016c(boolean z, int i) {
        BarParams cVar = this.f9344s;
        cVar.f9260B = z;
        cVar.f9261C = i;
        return this;
    }

    /* renamed from: r */
    public ImmersionBar m19947r(int i) {
        this.f9344s.f9261C = i;
        return this;
    }

    /* renamed from: a */
    public ImmersionBar m20059a(OnKeyboardListener lVar) {
        if (this.f9344s.f9266H == null) {
            this.f9344s.f9266H = lVar;
        }
        return this;
    }

    /* renamed from: i */
    public ImmersionBar m19969i(boolean z) {
        this.f9344s.f9262D = z;
        return this;
    }

    /* renamed from: j */
    public ImmersionBar m19965j(boolean z) {
        this.f9344s.f9263E = z;
        return this;
    }

    /* renamed from: k */
    public ImmersionBar m19962k(boolean z) {
        this.f9344s.f9264F = z;
        return this;
    }

    @Deprecated
    /* renamed from: l */
    public ImmersionBar m19959l(boolean z) {
        this.f9344s.f9265G = z;
        return this;
    }

    /* renamed from: f */
    public void m19994f() {
        m19954o();
        m19952p();
        m19935z();
        m20090C();
        m20092A();
    }

    /* renamed from: g */
    public void m19985g() {
        m20091B();
        Iterator<Map.Entry<String, ImmersionBar>> it = f9328l.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, ImmersionBar> next = it.next();
            if (next.getKey().contains(this.f9346u) || next.getKey().equals(this.f9346u)) {
                it.remove();
            }
        }
    }

    /* renamed from: o */
    private void m19954o() {
        ImmersionBar gVar;
        m19942u();
        if (Build.VERSION.SDK_INT >= 19) {
            if (OSUtils.m19901h() && this.f9344s.f9263E) {
                BarParams cVar = this.f9344s;
                cVar.f9263E = cVar.f9264F;
            }
            this.f9345t = new BarConfig(this.f9338m);
            if (this.f9349x && (gVar = f9328l.get(this.f9338m.toString())) != null) {
                gVar.f9344s = this.f9344s;
            }
        }
    }

    /* renamed from: p */
    private void m19952p() {
        if (Build.VERSION.SDK_INT >= 19) {
            int i = 256;
            if (Build.VERSION.SDK_INT < 21 || OSUtils.m19901h()) {
                m19948r();
            } else {
                m19950q();
                i = m19939v(m19941u(m19945s(256)));
            }
            int t = m19943t(i);
            m19940v();
            this.f9342q.setSystemUiVisibility(t);
        }
        if (OSUtils.m19907b()) {
            m20065a(this.f9341p, f9320d, this.f9344s.f9275i);
            if (this.f9344s.f9262D) {
                m20065a(this.f9341p, f9321e, this.f9344s.f9276j);
            }
        }
        if (!OSUtils.m19899j()) {
            return;
        }
        if (this.f9344s.f9292z != 0) {
            FlymeOSStatusBarFontUtils.m20100a(this.f9338m, this.f9344s.f9292z);
        } else {
            FlymeOSStatusBarFontUtils.m20099a(this.f9338m, this.f9344s.f9275i);
        }
    }

    /* renamed from: q */
    private void m19950q() {
        if (Build.VERSION.SDK_INT >= 28 && !this.f9333E) {
            WindowManager.LayoutParams attributes = this.f9341p.getAttributes();
            attributes.layoutInDisplayCutoutMode = 1;
            this.f9341p.setAttributes(attributes);
            this.f9333E = true;
        }
    }

    @RequiresApi(api = 21)
    /* renamed from: s */
    private int m19945s(int i) {
        if (!this.f9332D) {
            this.f9344s.f9269c = this.f9341p.getNavigationBarColor();
            this.f9332D = true;
        }
        int i2 = i | 1024;
        if (this.f9344s.f9272f && this.f9344s.f9262D) {
            i2 |= 512;
        }
        this.f9341p.clearFlags(67108864);
        if (this.f9345t.m20110d()) {
            this.f9341p.clearFlags(134217728);
        }
        this.f9341p.addFlags(Integer.MIN_VALUE);
        if (this.f9344s.f9281o) {
            this.f9341p.setStatusBarColor(ColorUtils.blendARGB(this.f9344s.f9267a, this.f9344s.f9282p, this.f9344s.f9270d));
        } else {
            this.f9341p.setStatusBarColor(ColorUtils.blendARGB(this.f9344s.f9267a, 0, this.f9344s.f9270d));
        }
        if (this.f9344s.f9262D) {
            this.f9341p.setNavigationBarColor(ColorUtils.blendARGB(this.f9344s.f9268b, this.f9344s.f9283q, this.f9344s.f9271e));
        } else {
            this.f9341p.setNavigationBarColor(this.f9344s.f9269c);
        }
        return i2;
    }

    /* renamed from: r */
    private void m19948r() {
        this.f9341p.addFlags(67108864);
        m19946s();
        if (this.f9345t.m20110d() || OSUtils.m19901h()) {
            if (!this.f9344s.f9262D || !this.f9344s.f9263E) {
                this.f9341p.clearFlags(134217728);
            } else {
                this.f9341p.addFlags(134217728);
            }
            if (this.f9347v == 0) {
                this.f9347v = this.f9345t.m20109e();
            }
            if (this.f9348w == 0) {
                this.f9348w = this.f9345t.m20108f();
            }
            m19944t();
        }
    }

    /* renamed from: s */
    private void m19946s() {
        View findViewById = this.f9342q.findViewById(f9317a);
        if (findViewById == null) {
            findViewById = new View(this.f9338m);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, this.f9345t.m20115b());
            layoutParams.gravity = 48;
            findViewById.setLayoutParams(layoutParams);
            findViewById.setVisibility(0);
            findViewById.setId(f9317a);
            this.f9342q.addView(findViewById);
        }
        if (this.f9344s.f9281o) {
            findViewById.setBackgroundColor(ColorUtils.blendARGB(this.f9344s.f9267a, this.f9344s.f9282p, this.f9344s.f9270d));
        } else {
            findViewById.setBackgroundColor(ColorUtils.blendARGB(this.f9344s.f9267a, 0, this.f9344s.f9270d));
        }
    }

    /* renamed from: t */
    private void m19944t() {
        FrameLayout.LayoutParams layoutParams;
        View findViewById = this.f9342q.findViewById(f9318b);
        if (findViewById == null) {
            findViewById = new View(this.f9338m);
            findViewById.setId(f9318b);
            this.f9342q.addView(findViewById);
        }
        if (this.f9345t.m20119a()) {
            layoutParams = new FrameLayout.LayoutParams(-1, this.f9345t.m20109e());
            layoutParams.gravity = 80;
        } else {
            layoutParams = new FrameLayout.LayoutParams(this.f9345t.m20108f(), -1);
            layoutParams.gravity = GravityCompat.END;
        }
        findViewById.setLayoutParams(layoutParams);
        findViewById.setBackgroundColor(ColorUtils.blendARGB(this.f9344s.f9268b, this.f9344s.f9283q, this.f9344s.f9271e));
        if (!this.f9344s.f9262D || !this.f9344s.f9263E || this.f9344s.f9273g) {
            findViewById.setVisibility(8);
        } else {
            findViewById.setVisibility(0);
        }
    }

    /* renamed from: u */
    private void m19942u() {
        boolean z = true;
        if (this.f9344s.f9277k) {
            m20005d(this.f9344s.f9267a != 0 && this.f9344s.f9267a > f9322f, this.f9344s.f9279m);
        }
        if (this.f9344s.f9278l) {
            if (this.f9344s.f9268b == 0 || this.f9344s.f9268b <= f9322f) {
                z = false;
            }
            m19995e(z, this.f9344s.f9280n);
        }
    }

    /* renamed from: t */
    private int m19943t(int i) {
        if (Build.VERSION.SDK_INT >= 16) {
            switch (this.f9344s.f9274h) {
                case FLAG_HIDE_BAR:
                    i |= 518;
                    break;
                case FLAG_HIDE_STATUS_BAR:
                    i |= 1028;
                    break;
                case FLAG_HIDE_NAVIGATION_BAR:
                    i |= 514;
                    break;
                case FLAG_SHOW_BAR:
                    i |= 0;
                    break;
            }
        }
        return i | 4096;
    }

    /* renamed from: v */
    private void m19940v() {
        if (Build.VERSION.SDK_INT < 21 || OSUtils.m19901h()) {
            m19937x();
            if (!this.f9349x && OSUtils.m19901h()) {
                m19936y();
                return;
            }
            return;
        }
        m19938w();
    }

    /* renamed from: w */
    private void m19938w() {
        if (!m19989f(this.f9342q.findViewById(16908290))) {
            int b = (!this.f9344s.f9289w || this.f9331C != 4) ? 0 : this.f9345t.m20115b();
            if (this.f9344s.f9259A) {
                b = this.f9345t.m20115b() + this.f9345t.m20112c();
            }
            m20084a(0, b, 0, 0);
        } else if (this.f9344s.f9259A) {
            m20084a(0, this.f9345t.m20112c(), 0, 0);
        }
    }

    /* renamed from: x */
    private void m19937x() {
        int i;
        int i2;
        if (!m19989f(this.f9342q.findViewById(16908290))) {
            int b = (!this.f9344s.f9289w || this.f9331C != 4) ? 0 : this.f9345t.m20115b();
            if (this.f9344s.f9259A) {
                b = this.f9345t.m20115b() + this.f9345t.m20112c();
            }
            if (!this.f9345t.m20110d() || !this.f9344s.f9262D || !this.f9344s.f9263E) {
                i2 = 0;
                i = 0;
            } else {
                if (this.f9344s.f9272f) {
                    i2 = 0;
                    i = 0;
                } else if (this.f9345t.m20119a()) {
                    i = this.f9345t.m20109e();
                    i2 = 0;
                } else {
                    i2 = this.f9345t.m20108f();
                    i = 0;
                }
                if (this.f9344s.f9273g) {
                    if (this.f9345t.m20119a()) {
                        i = 0;
                    } else {
                        i2 = 0;
                    }
                } else if (!this.f9345t.m20119a()) {
                    i2 = this.f9345t.m20108f();
                }
            }
            m20084a(0, b, i2, i);
        } else if (this.f9344s.f9259A) {
            m20084a(0, this.f9345t.m20112c(), 0, 0);
        }
    }

    /* renamed from: y */
    private void m19936y() {
        final View findViewById = this.f9342q.findViewById(f9318b);
        if (!this.f9344s.f9262D || !this.f9344s.f9263E) {
            findViewById.setVisibility(8);
        } else if (findViewById != null && this.f9350y == null) {
            this.f9350y = new ContentObserver(new Handler()) { // from class: com.gyf.barlibrary.g.1
                @Override // android.database.ContentObserver
                public void onChange(boolean z) {
                    ImmersionBar gVar = ImmersionBar.this;
                    gVar.f9345t = new BarConfig(gVar.f9338m);
                    int paddingBottom = ImmersionBar.this.f9343r.getPaddingBottom();
                    int paddingRight = ImmersionBar.this.f9343r.getPaddingRight();
                    if (!(ImmersionBar.this.f9338m == null || ImmersionBar.this.f9338m.getContentResolver() == null)) {
                        if (Settings.System.getInt(ImmersionBar.this.f9338m.getContentResolver(), ImmersionBar.f9319c, 0) == 1) {
                            findViewById.setVisibility(8);
                            paddingBottom = 0;
                            paddingRight = 0;
                        } else {
                            findViewById.setVisibility(0);
                            if (ImmersionBar.m19989f(ImmersionBar.this.f9342q.findViewById(16908290))) {
                                paddingBottom = 0;
                                paddingRight = 0;
                            } else {
                                if (ImmersionBar.this.f9347v == 0) {
                                    ImmersionBar gVar2 = ImmersionBar.this;
                                    gVar2.f9347v = gVar2.f9345t.m20109e();
                                }
                                if (ImmersionBar.this.f9348w == 0) {
                                    ImmersionBar gVar3 = ImmersionBar.this;
                                    gVar3.f9348w = gVar3.f9345t.m20108f();
                                }
                                if (!ImmersionBar.this.f9344s.f9273g) {
                                    FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) findViewById.getLayoutParams();
                                    if (ImmersionBar.this.f9345t.m20119a()) {
                                        layoutParams.gravity = 80;
                                        layoutParams.height = ImmersionBar.this.f9347v;
                                        paddingBottom = !ImmersionBar.this.f9344s.f9272f ? ImmersionBar.this.f9347v : 0;
                                        paddingRight = 0;
                                    } else {
                                        layoutParams.gravity = GravityCompat.END;
                                        layoutParams.width = ImmersionBar.this.f9348w;
                                        paddingRight = !ImmersionBar.this.f9344s.f9272f ? ImmersionBar.this.f9348w : 0;
                                        paddingBottom = 0;
                                    }
                                    findViewById.setLayoutParams(layoutParams);
                                }
                            }
                        }
                    }
                    ImmersionBar gVar4 = ImmersionBar.this;
                    gVar4.m20084a(0, gVar4.f9343r.getPaddingTop(), paddingRight, paddingBottom);
                }
            };
            Activity activity = this.f9338m;
            if (activity != null && activity.getContentResolver() != null && this.f9350y != null) {
                this.f9338m.getContentResolver().registerContentObserver(Settings.System.getUriFor(f9319c), true, this.f9350y);
            }
        }
    }

    /* renamed from: u */
    private int m19941u(int i) {
        return (Build.VERSION.SDK_INT < 23 || !this.f9344s.f9275i) ? i : i | 8192;
    }

    /* renamed from: v */
    private int m19939v(int i) {
        return (Build.VERSION.SDK_INT < 26 || !this.f9344s.f9276j) ? i : i | 16;
    }

    @SuppressLint({"PrivateApi"})
    /* renamed from: a */
    private void m20065a(Window window, String str, boolean z) {
        if (window != null) {
            Class<?> cls = window.getClass();
            try {
                Class<?> cls2 = Class.forName("android.view.MiuiWindowManager$LayoutParams");
                int i = cls2.getField(str).getInt(cls2);
                Method method = cls.getMethod("setExtraFlags", Integer.TYPE, Integer.TYPE);
                if (z) {
                    method.invoke(window, Integer.valueOf(i), Integer.valueOf(i));
                } else {
                    method.invoke(window, 0, Integer.valueOf(i));
                }
            } catch (Exception unused) {
            }
        }
    }

    /* renamed from: z */
    private void m19935z() {
        if (Build.VERSION.SDK_INT >= 19 && !this.f9330B) {
            switch (this.f9331C) {
                case 1:
                    m20076a(this.f9338m, this.f9344s.f9290x);
                    this.f9330B = true;
                    return;
                case 2:
                    m20044b(this.f9338m, this.f9344s.f9290x);
                    this.f9330B = true;
                    return;
                case 3:
                    m20024c(this.f9338m, this.f9344s.f9291y);
                    this.f9330B = true;
                    return;
                default:
                    return;
            }
        }
    }

    /* renamed from: A */
    private void m20092A() {
        if (this.f9344s.f9284r.size() != 0) {
            for (Map.Entry<View, Map<Integer, Integer>> entry : this.f9344s.f9284r.entrySet()) {
                View key = entry.getKey();
                Integer valueOf = Integer.valueOf(this.f9344s.f9267a);
                Integer valueOf2 = Integer.valueOf(this.f9344s.f9282p);
                for (Map.Entry<Integer, Integer> entry2 : entry.getValue().entrySet()) {
                    valueOf = entry2.getKey();
                    valueOf2 = entry2.getValue();
                }
                if (key != null) {
                    if (Math.abs(this.f9344s.f9285s - 0.0f) == 0.0f) {
                        key.setBackgroundColor(ColorUtils.blendARGB(valueOf.intValue(), valueOf2.intValue(), this.f9344s.f9270d));
                    } else {
                        key.setBackgroundColor(ColorUtils.blendARGB(valueOf.intValue(), valueOf2.intValue(), this.f9344s.f9285s));
                    }
                }
            }
        }
    }

    /* renamed from: B */
    private void m20091B() {
        Activity activity = this.f9338m;
        if (activity != null) {
            if (this.f9350y != null) {
                activity.getContentResolver().unregisterContentObserver(this.f9350y);
                this.f9350y = null;
            }
            FitsKeyboard eVar = this.f9351z;
            if (eVar != null) {
                eVar.m20103b();
                this.f9351z = null;
            }
        }
    }

    /* renamed from: C */
    private void m20090C() {
        if (Build.VERSION.SDK_INT < 19) {
            return;
        }
        if (this.f9349x) {
            ImmersionBar gVar = f9328l.get(this.f9338m.toString());
            if (gVar == null) {
                return;
            }
            if (gVar.f9344s.f9260B) {
                if (gVar.f9351z == null) {
                    gVar.f9351z = new FitsKeyboard(gVar, gVar.f9338m, gVar.f9341p);
                }
                gVar.f9351z.m20104a(gVar.f9344s.f9261C);
                return;
            }
            FitsKeyboard eVar = gVar.f9351z;
            if (eVar != null) {
                eVar.m20105a();
            }
        } else if (this.f9344s.f9260B) {
            if (this.f9351z == null) {
                this.f9351z = new FitsKeyboard(this, this.f9338m, this.f9341p);
            }
            this.f9351z.m20104a(this.f9344s.f9261C);
        } else {
            FitsKeyboard eVar2 = this.f9351z;
            if (eVar2 != null) {
                eVar2.m20105a();
            }
        }
    }

    /* renamed from: h */
    public BarParams m19978h() {
        return this.f9344s;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m20084a(int i, int i2, int i3, int i4) {
        ViewGroup viewGroup = this.f9343r;
        if (viewGroup != null) {
            viewGroup.setPadding(i, i2, i3, i4);
        }
        this.f9334F = i;
        this.f9335G = i2;
        this.f9336H = i3;
        this.f9337I = i4;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: i */
    public int m19973i() {
        return this.f9334F;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: j */
    public int m19968j() {
        return this.f9335G;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: k */
    public int m19964k() {
        return this.f9336H;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: l */
    public int m19961l() {
        return this.f9337I;
    }

    /* renamed from: m */
    public static boolean m19958m() {
        return OSUtils.m19907b() || OSUtils.m19899j() || Build.VERSION.SDK_INT >= 23;
    }

    /* renamed from: n */
    public static boolean m19956n() {
        return OSUtils.m19907b() || Build.VERSION.SDK_INT >= 26;
    }

    /* renamed from: a */
    public static void m20076a(final Activity activity, final View view) {
        if (activity != null && view != null && Build.VERSION.SDK_INT >= 19) {
            final ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams.height == -2 || layoutParams.height == -1) {
                view.post(new Runnable() { // from class: com.gyf.barlibrary.g.2
                    @Override // java.lang.Runnable
                    public void run() {
                        layoutParams.height = view.getHeight() + ImmersionBar.m19983g(activity);
                        View view2 = view;
                        view2.setPadding(view2.getPaddingLeft(), view.getPaddingTop() + ImmersionBar.m19983g(activity), view.getPaddingRight(), view.getPaddingBottom());
                    }
                });
                return;
            }
            layoutParams.height += m19983g(activity);
            view.setPadding(view.getPaddingLeft(), view.getPaddingTop() + m19983g(activity), view.getPaddingRight(), view.getPaddingBottom());
        }
    }

    /* renamed from: b */
    public static void m20044b(Activity activity, View view) {
        if (activity != null && view != null && Build.VERSION.SDK_INT >= 19) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            marginLayoutParams.setMargins(marginLayoutParams.leftMargin, marginLayoutParams.topMargin + m19983g(activity), marginLayoutParams.rightMargin, marginLayoutParams.bottomMargin);
        }
    }

    /* renamed from: c */
    public static void m20024c(Activity activity, View view) {
        if (activity != null && view != null && Build.VERSION.SDK_INT >= 19) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            layoutParams.height = m19983g(activity);
            view.setLayoutParams(layoutParams);
        }
    }

    /* renamed from: b */
    public static void m20045b(Activity activity) {
        if (activity != null) {
            ViewGroup viewGroup = (ViewGroup) activity.findViewById(16908290);
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                if ((childAt instanceof ViewGroup) && !(childAt instanceof DrawerLayout)) {
                    childAt.setFitsSystemWindows(true);
                    ((ViewGroup) childAt).setClipToPadding(true);
                }
            }
        }
    }

    /* renamed from: f */
    public static boolean m19989f(View view) {
        if (view.getFitsSystemWindows()) {
            return true;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                if (((childAt instanceof DrawerLayout) && m19989f(childAt)) || childAt.getFitsSystemWindows()) {
                    return true;
                }
            }
        }
        return false;
    }

    @TargetApi(14)
    /* renamed from: c */
    public static boolean m20025c(@NonNull Activity activity) {
        return new BarConfig(activity).m20110d();
    }

    @TargetApi(14)
    /* renamed from: d */
    public static int m20010d(@NonNull Activity activity) {
        return new BarConfig(activity).m20109e();
    }

    @TargetApi(14)
    /* renamed from: e */
    public static int m20000e(@NonNull Activity activity) {
        return new BarConfig(activity).m20108f();
    }

    @TargetApi(14)
    /* renamed from: f */
    public static boolean m19990f(@NonNull Activity activity) {
        return new BarConfig(activity).m20119a();
    }

    @TargetApi(14)
    /* renamed from: g */
    public static int m19983g(@NonNull Activity activity) {
        return new BarConfig(activity).m20115b();
    }

    @TargetApi(14)
    /* renamed from: h */
    public static int m19976h(@NonNull Activity activity) {
        return new BarConfig(activity).m20112c();
    }

    /* renamed from: i */
    public static boolean m19971i(@NonNull Activity activity) {
        return new BarConfig(activity).m20107g();
    }

    /* renamed from: g */
    public static boolean m19982g(@NonNull View view) {
        return NotchUtils.m19917a(view);
    }

    /* renamed from: a */
    public static void m20066a(@NonNull Window window) {
        window.setFlags(1024, 1024);
    }

    /* renamed from: j */
    private static boolean m19966j(String str) {
        return str == null || str.trim().length() == 0;
    }
}
