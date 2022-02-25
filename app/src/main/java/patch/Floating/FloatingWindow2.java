package patch.Floating;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.IBinder;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import java.lang.ref.WeakReference;
import p110z1.IFloatingWindow;
import p110z1.MemoryConstants;

/* loaded from: classes2.dex */
public class FloatingWindow2 implements IFloatingWindow {

    /* renamed from: a */
    public static final int f14921a = 0;

    /* renamed from: b */
    public static final int f14922b = 1;

    /* renamed from: c */
    public static final int f14923c = 2;

    /* renamed from: A */
    private final int[] f14924A;

    /* renamed from: B */
    private final int[] f14925B;

    /* renamed from: C */
    private final Rect f14926C;

    /* renamed from: D */
    private Context f14927D;

    /* renamed from: E */
    private WindowManager f14928E;

    /* renamed from: F */
    private boolean f14929F;

    /* renamed from: G */
    private C3278b f14930G;

    /* renamed from: H */
    private View f14931H;

    /* renamed from: I */
    private View f14932I;

    /* renamed from: J */
    private boolean f14933J;

    /* renamed from: K */
    private int f14934K;

    /* renamed from: L */
    private int f14935L;

    /* renamed from: M */
    private boolean f14936M;

    /* renamed from: N */
    private boolean f14937N;

    /* renamed from: O */
    private boolean f14938O;

    /* renamed from: P */
    private int f14939P;

    /* renamed from: Q */
    private boolean f14940Q;

    /* renamed from: R */
    private boolean f14941R;

    /* renamed from: S */
    private boolean f14942S;

    /* renamed from: T */
    private boolean f14943T;

    /* renamed from: U */
    private boolean f14944U;

    /* renamed from: V */
    private boolean f14945V;

    /* renamed from: W */
    private boolean f14946W;

    /* renamed from: X */
    private boolean f14947X;

    /* renamed from: Y */
    private boolean f14948Y;

    /* renamed from: Z */
    private int f14949Z;

    /* renamed from: d */
    private int f14950d;

    /* renamed from: e */
    private Point f14951e;

    /* renamed from: f */
    private View.OnTouchListener f14952f;

    /* renamed from: g */
    private int f14953g;

    /* renamed from: h */
    private int f14954h;

    /* renamed from: i */
    private int f14955i;

    /* renamed from: j */
    private int f14956j;

    /* renamed from: k */
    private int f14957k;

    /* renamed from: l */
    private int f14958l;

    /* renamed from: m */
    private int f14959m;

    /* renamed from: n */
    private int f14960n;

    /* renamed from: o */
    private float f14961o;

    /* renamed from: p */
    private Drawable f14962p;

    /* renamed from: q */
    private int f14963q;

    /* renamed from: r */
    private PopupWindow.OnDismissListener f14964r;

    /* renamed from: s */
    private boolean f14965s;

    /* renamed from: t */
    private int f14966t;

    /* renamed from: u */
    private WeakReference f14967u;

    /* renamed from: v */
    private final ViewTreeObserver.OnScrollChangedListener f14968v;

    /* renamed from: w */
    private int f14969w;

    /* renamed from: x */
    private int f14970x;

    /* renamed from: y */
    private int f14971y;

    /* renamed from: z */
    private boolean f14972z;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: patch.Floating.FloatingWindow2$a */
    /* loaded from: classes2.dex */
    public class C3277a extends FrameLayout {
        public C3277a(Context context) {
            super(context);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: patch.Floating.FloatingWindow2$b */
    /* loaded from: classes2.dex */
    public class C3278b extends FrameLayout {
        public C3278b(Context context) {
            super(context);
        }
    }

    public FloatingWindow2(Context context) {
        this(context, (AttributeSet) null);
    }

    public FloatingWindow2() {
        this((View) null, 0, 0);
    }

    public FloatingWindow2(View view, int i, int i2) {
        this(view, i, i2, false);
    }

    public FloatingWindow2(int i, int i2) {
        this((View) null, i, i2);
    }

    public FloatingWindow2(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FloatingWindow2(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public FloatingWindow2(Context context, AttributeSet attributeSet, int i, int i2) {
        this.f14924A = new int[2];
        this.f14925B = new int[2];
        this.f14926C = new Rect();
        this.f14934K = 2;
        this.f14935L = 1;
        this.f14936M = true;
        this.f14937N = true;
        this.f14938O = false;
        this.f14939P = -1;
        this.f14942S = true;
        this.f14943T = false;
        this.f14945V = true;
        this.f14946W = false;
        this.f14948Y = false;
        this.f14949Z = -1;
        this.f14950d = 8388659;
        this.f14951e = new Point(0, 0);
        this.f14954h = -2;
        this.f14957k = -2;
        this.f14963q = 1000;
        this.f14965s = false;
        this.f14966t = -1;
        this.f14968v = new ViewTreeObserver.OnScrollChangedListener() { // from class: patch.Floating.FloatingWindow2.1
            @Override // android.view.ViewTreeObserver.OnScrollChangedListener
            public void onScrollChanged() {
                if ((FloatingWindow2.this.f14967u != null ? FloatingWindow2.this.f14967u.get() : null) != null && FloatingWindow2.this.f14930G != null) {
                    WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams) FloatingWindow2.this.f14930G.getLayoutParams();
                    FloatingWindow2.this.m14664a(layoutParams.x, layoutParams.y, -1, -1, true);
                }
            }
        };
        this.f14927D = context;
        this.f14928E = (WindowManager) context.getSystemService("window");
    }

    public FloatingWindow2(View view) {
        this(view, 0, 0);
    }

    public FloatingWindow2(View view, int i, int i2, boolean z) {
        this.f14924A = new int[2];
        this.f14925B = new int[2];
        this.f14926C = new Rect();
        this.f14934K = 2;
        this.f14935L = 1;
        this.f14936M = true;
        this.f14937N = true;
        this.f14938O = false;
        this.f14939P = -1;
        this.f14942S = true;
        this.f14943T = false;
        this.f14945V = true;
        this.f14946W = false;
        this.f14948Y = false;
        this.f14949Z = -1;
        this.f14950d = 8388659;
        this.f14951e = new Point(0, 0);
        this.f14954h = -2;
        this.f14957k = -2;
        this.f14963q = 1000;
        this.f14965s = false;
        this.f14966t = -1;
        this.f14968v = new ViewTreeObserver.OnScrollChangedListener() { // from class: patch.Floating.FloatingWindow2.2
            @Override // android.view.ViewTreeObserver.OnScrollChangedListener
            public void onScrollChanged() {
                if ((FloatingWindow2.this.f14967u != null ? FloatingWindow2.this.f14967u.get() : null) != null && FloatingWindow2.this.f14930G != null) {
                    WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams) FloatingWindow2.this.f14930G.getLayoutParams();
                    FloatingWindow2.this.m14664a(layoutParams.x, layoutParams.y, -1, -1, true);
                }
            }
        };
        if (view != null) {
            this.f14927D = view.getContext();
            this.f14928E = (WindowManager) this.f14927D.getSystemService("window");
        }
        m14643b(view);
        m14623f(i);
        m14638c(i2);
        m14629d(z);
    }

    /* renamed from: h */
    private int m14617h(int i) {
        int i2 = i & (-8815129);
        if (this.f14965s) {
            i2 |= 32768;
        }
        if (!this.f14933J) {
            i2 |= 8;
            if (this.f14934K == 1) {
                i2 |= 131072;
            }
        } else if (this.f14934K == 2) {
            i2 |= 131072;
        }
        if (!this.f14936M) {
            i2 |= 16;
        }
        if (this.f14937N) {
            i2 |= 262144;
        }
        if (!this.f14938O) {
            i2 |= 512;
        }
        if (m14601s()) {
            i2 |= 8388608;
        }
        if (this.f14940Q) {
            i2 |= 256;
        }
        if (this.f14943T) {
            i2 |= 65536;
        }
        if (this.f14944U) {
            i2 |= 32;
        }
        if (this.f14945V) {
            i2 |= MemoryConstants.f21646d;
        }
        return i2 | 16777216;
    }

    /* renamed from: a */
    private WindowManager.LayoutParams m14662a(IBinder iBinder) {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.gravity = 8388659;
        layoutParams.flags = m14617h(layoutParams.flags);
        layoutParams.type = this.f14963q;
        layoutParams.token = iBinder;
        layoutParams.softInputMode = this.f14935L;
        layoutParams.windowAnimations = m14596x();
        Drawable drawable = this.f14962p;
        layoutParams.format = drawable != null ? drawable.getOpacity() : -3;
        int i = this.f14956j;
        if (i < 0) {
            this.f14958l = i;
            layoutParams.height = i;
        } else {
            int i2 = this.f14957k;
            this.f14958l = i2;
            layoutParams.height = i2;
        }
        int i3 = this.f14953g;
        if (i3 < 0) {
            this.f14955i = i3;
            layoutParams.width = i3;
        } else {
            int i4 = this.f14954h;
            this.f14955i = i4;
            layoutParams.width = i4;
        }
        layoutParams.setTitle("PopupWindow:" + Integer.toHexString(hashCode()));
        return layoutParams;
    }

    /* renamed from: d */
    private C3277a m14631d(View view) {
        ViewGroup.LayoutParams layoutParams = this.f14932I.getLayoutParams();
        int i = -2;
        if (layoutParams == null || layoutParams.height != -2) {
            i = -1;
        }
        C3277a aVar = new C3277a(this.f14927D);
        aVar.addView(view, new FrameLayout.LayoutParams(-1, i));
        return aVar;
    }

    /* renamed from: w */
    private void m14597w() {
        Object obj;
        WeakReference weakReference = this.f14967u;
        if (weakReference != null && (obj = weakReference.get()) != null) {
            this.f14930G.setLayoutDirection(((View) obj).getLayoutDirection());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public void m14637c(int i, int i2) {
        m14665a(i, i2, -1, -1);
    }

    /* renamed from: c */
    private void m14636c(View view, int i, int i2, int i3) {
        m14595y();
        this.f14967u = new WeakReference(view);
        ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
        if (viewTreeObserver != null) {
            viewTreeObserver.addOnScrollChangedListener(this.f14968v);
        }
        this.f14969w = i;
        this.f14970x = i2;
        this.f14971y = i3;
    }

    /* renamed from: a */
    private void m14654a(View view, ViewGroup viewGroup, View view2) {
        if (view.getParent() != null) {
            this.f14928E.removeViewImmediate(view);
        }
        if (viewGroup != null) {
            viewGroup.removeView(view2);
        }
        this.f14930G = null;
        this.f14931H = null;
    }

    /* renamed from: a */
    private void m14653a(View view, WindowManager.LayoutParams layoutParams) {
        Context context = this.f14927D;
        if (context != null) {
            layoutParams.packageName = context.getPackageName();
        }
        C3278b bVar = this.f14930G;
        bVar.setFitsSystemWindows(this.f14943T);
        bVar.setSystemUiVisibility(view.getSystemUiVisibility());
        m14597w();
        this.f14928E.addView(bVar, layoutParams);
    }

    /* renamed from: a */
    private void m14652a(View view, boolean z, int i, int i2, boolean z2, int i3, int i4) {
        boolean z3;
        int i5;
        if (m14602r() && this.f14932I != null) {
            WeakReference weakReference = this.f14967u;
            boolean z4 = false;
            if (z) {
                if (this.f14969w == i) {
                    int i6 = this.f14970x;
                }
                z3 = true;
            } else {
                z3 = false;
            }
            if (weakReference == null || weakReference.get() != view || z3) {
                m14636c(view, i, i2, this.f14971y);
            } else if (z3) {
                this.f14969w = i;
                this.f14970x = i2;
            }
            if (z2) {
                if (i3 == -1) {
                    i5 = this.f14959m;
                } else {
                    this.f14959m = i3;
                    i5 = i3;
                }
                if (i4 == -1) {
                    int i7 = this.f14960n;
                    WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams) this.f14930G.getLayoutParams();
                    m14664a(layoutParams.x, layoutParams.y, i5, i7, (layoutParams.x == layoutParams.x && layoutParams.y == layoutParams.y) ? false : true);
                }
                this.f14960n = i4;
            } else {
                i5 = i3;
            }
            WindowManager.LayoutParams layoutParams2 = (WindowManager.LayoutParams) this.f14930G.getLayoutParams();
            int i8 = layoutParams2.x;
            int i9 = layoutParams2.y;
            int i10 = layoutParams2.x;
            int i11 = layoutParams2.y;
            if (!(i8 == layoutParams2.x && i9 == layoutParams2.y)) {
                z4 = true;
            }
            m14664a(i10, i11, i5, i4, z4);
        }
    }

    /* renamed from: a */
    private void m14651a(WindowManager.LayoutParams layoutParams) {
        View view = this.f14932I;
        if (view == null || this.f14927D == null || this.f14928E == null) {
            throw new IllegalStateException("You must specify a valid content view by calling setContentView() before attempting to show the popup.");
        }
        if (this.f14962p != null) {
            this.f14931H = m14631d(view);
            this.f14931H.setBackground(this.f14962p);
        } else {
            this.f14931H = view;
            if (view.getBackground() == null) {
                this.f14932I.setBackgroundColor(-16777216);
            }
        }
        this.f14930G = m14626e(this.f14931H);
        this.f14931H.setElevation(this.f14961o);
        this.f14959m = layoutParams.width;
        this.f14960n = layoutParams.height;
    }

    /* renamed from: x */
    private int m14596x() {
        int i = this.f14966t;
        if (i == -1) {
            return 0;
        }
        return i;
    }

    /* renamed from: e */
    private C3278b m14626e(View view) {
        ViewGroup.LayoutParams layoutParams = this.f14932I.getLayoutParams();
        int i = -2;
        if (layoutParams == null || layoutParams.height != -2) {
            i = -1;
        }
        C3278b bVar = new C3278b(this.f14927D);
        view.getParent();
        bVar.addView(view, -1, i);
        bVar.setClipChildren(false);
        bVar.setClipToPadding(false);
        return bVar;
    }

    /* renamed from: y */
    private void m14595y() {
        WeakReference weakReference = this.f14967u;
        Object obj = weakReference == null ? null : weakReference.get();
        if (obj != null) {
            ((View) obj).getViewTreeObserver().removeOnScrollChangedListener(this.f14968v);
        }
        this.f14967u = null;
    }

    /* renamed from: a */
    public void m14669a() {
        if (m14602r()) {
            C3278b bVar = this.f14930G;
            ViewParent parent = this.f14932I.getParent();
            if (!(parent instanceof ViewGroup)) {
                parent = null;
            }
            this.f14929F = false;
            m14654a(bVar, (ViewGroup) parent, this.f14932I);
            m14595y();
            PopupWindow.OnDismissListener onDismissListener = this.f14964r;
            if (onDismissListener != null) {
                onDismissListener.onDismiss();
            }
        }
    }

    /* renamed from: b */
    public int m14646b() {
        return this.f14966t;
    }

    /* renamed from: c */
    public Drawable m14639c() {
        return this.f14962p;
    }

    /* renamed from: d */
    public View m14633d() {
        return this.f14932I;
    }

    /* renamed from: e */
    public float m14628e() {
        return this.f14961o;
    }

    /* renamed from: f */
    public int m14624f() {
        return this.f14957k;
    }

    /* renamed from: g */
    public int m14621g() {
        return this.f14934K;
    }

    /* renamed from: a */
    public int m14660a(View view) {
        return m14659a(view, 0);
    }

    /* renamed from: a */
    public int m14659a(View view, int i) {
        return m14655a(view, i, false);
    }

    /* renamed from: a */
    public int m14655a(View view, int i, boolean z) {
        Rect rect = new Rect();
        view.getWindowVisibleDisplayFrame(rect);
        int[] iArr = this.f14924A;
        view.getLocationOnScreen(iArr);
        int i2 = rect.bottom;
        if (z) {
            i2 = view.getContext().getResources().getDisplayMetrics().heightPixels;
        }
        int max = Math.max((i2 - (iArr[1] + view.getHeight())) - i, (iArr[1] - rect.top) + i);
        Drawable drawable = this.f14962p;
        if (drawable == null) {
            return max;
        }
        drawable.getPadding(this.f14926C);
        return max - (this.f14926C.top + this.f14926C.bottom);
    }

    /* renamed from: h */
    public boolean m14618h() {
        return this.f14972z;
    }

    /* renamed from: i */
    public Point m14615i() {
        return this.f14951e;
    }

    /* renamed from: j */
    public int m14613j() {
        return this.f14935L;
    }

    /* renamed from: k */
    public int m14611k() {
        return this.f14954h;
    }

    /* renamed from: l */
    public int m14609l() {
        return this.f14963q;
    }

    /* renamed from: m */
    public boolean m14607m() {
        return this.f14945V;
    }

    /* renamed from: n */
    public boolean m14606n() {
        return this.f14938O;
    }

    /* renamed from: o */
    public boolean m14605o() {
        return this.f14933J;
    }

    /* renamed from: p */
    public boolean m14604p() {
        return this.f14940Q;
    }

    /* renamed from: q */
    public boolean m14603q() {
        return this.f14937N;
    }

    /* renamed from: r */
    public boolean m14602r() {
        return this.f14929F;
    }

    /* renamed from: s */
    public boolean m14601s() {
        Context context;
        if (this.f14939P >= 0 || (context = this.f14927D) == null) {
            if (this.f14939P != 1) {
                return false;
            }
        } else if (context.getApplicationInfo().targetSdkVersion < 11) {
            return false;
        }
        return true;
    }

    /* renamed from: t */
    public boolean m14600t() {
        return this.f14936M;
    }

    /* renamed from: a */
    public void m14667a(int i) {
        this.f14966t = i;
    }

    /* renamed from: a */
    public void m14647a(boolean z) {
        this.f14945V = z;
        this.f14946W = true;
    }

    /* renamed from: a */
    public void m14663a(Drawable drawable) {
        this.f14962p = drawable;
    }

    /* renamed from: b */
    public void m14640b(boolean z) {
        this.f14941R = z;
        m14634c(!z);
    }

    /* renamed from: c */
    public void m14634c(boolean z) {
        this.f14938O = z;
    }

    /* renamed from: b */
    public void m14643b(View view) {
        View view2;
        if (!m14602r()) {
            this.f14932I = view;
            if (this.f14927D == null && (view2 = this.f14932I) != null) {
                this.f14927D = view2.getContext();
            }
            if (this.f14928E == null && this.f14932I != null) {
                this.f14928E = (WindowManager) this.f14927D.getSystemService("window");
            }
            Context context = this.f14927D;
            if (context != null && !this.f14946W) {
                m14647a(context.getApplicationInfo().targetSdkVersion >= 22);
            }
        }
    }

    /* renamed from: a */
    public void m14668a(float f) {
        this.f14961o = f;
    }

    /* renamed from: d */
    public void m14629d(boolean z) {
        this.f14933J = z;
    }

    /* renamed from: b */
    public void m14645b(int i) {
        this.f14950d = i;
    }

    /* renamed from: c */
    public void m14638c(int i) {
        this.f14957k = i;
    }

    /* renamed from: u */
    public void m14599u() {
        this.f14965s = true;
    }

    /* renamed from: d */
    public void m14632d(int i) {
        this.f14934K = i;
    }

    /* renamed from: e */
    public void m14625e(boolean z) {
        this.f14940Q = z;
    }

    /* renamed from: f */
    public void m14622f(boolean z) {
        this.f14943T = z;
    }

    /* renamed from: g */
    public void m14619g(boolean z) {
        this.f14947X = z;
    }

    /* renamed from: a */
    public void m14650a(PopupWindow.OnDismissListener onDismissListener) {
        this.f14964r = onDismissListener;
    }

    /* renamed from: h */
    public void m14616h(boolean z) {
        this.f14937N = z;
    }

    /* renamed from: i */
    public void m14614i(boolean z) {
        this.f14972z = z;
    }

    /* renamed from: e */
    public void m14627e(int i) {
        this.f14935L = i;
    }

    /* renamed from: j */
    public void m14612j(boolean z) {
        this.f14939P = z ? 1 : 0;
    }

    /* renamed from: a */
    public void m14661a(View.OnTouchListener onTouchListener) {
        this.f14952f = onTouchListener;
    }

    /* renamed from: k */
    public void m14610k(boolean z) {
        this.f14944U = !z;
    }

    /* renamed from: l */
    public void m14608l(boolean z) {
        this.f14936M = z;
    }

    /* renamed from: f */
    public void m14623f(int i) {
        this.f14954h = i;
    }

    @Deprecated
    /* renamed from: a */
    public void m14666a(int i, int i2) {
        this.f14953g = i;
        this.f14956j = i2;
    }

    /* renamed from: g */
    public void m14620g(int i) {
        this.f14963q = i;
    }

    @Override // p110z1.IFloatingWindow
    /* renamed from: c */
    public void mo3245c(View view) {
        m14657a(view, this.f14950d, this.f14951e.x, this.f14951e.y);
    }

    /* renamed from: a */
    public void m14657a(View view, int i, int i2, int i3) {
        this.f14950d = i;
        Point point = this.f14951e;
        point.x = i2;
        point.y = i3;
        if (!m14602r() && this.f14932I != null) {
            m14595y();
            this.f14929F = true;
            WindowManager.LayoutParams a = m14662a(view.getWindowToken());
            m14651a(a);
            if (i != 0) {
                a.gravity = i;
            }
            a.x = i2;
            a.y = i3;
            if (this.f14947X && this.f14952f == null) {
                this.f14952f = new View.OnTouchListener() { // from class: patch.Floating.FloatingWindow2.3

                    /* renamed from: b */
                    private boolean f14976b;

                    /* renamed from: c */
                    private float f14977c;

                    /* renamed from: d */
                    private float f14978d;

                    /* renamed from: e */
                    private Point f14979e = new Point(0, -1);

                    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
                    @Override // android.view.View.OnTouchListener
                    public boolean onTouch(View view2, MotionEvent motionEvent) {
                        if (!FloatingWindow2.this.f14947X) {
                            return false;
                        }
                        switch (motionEvent.getAction()) {
                            case 0:
                                this.f14976b = false;
                                this.f14977c = motionEvent.getRawX();
                                this.f14978d = motionEvent.getRawY();
                                this.f14979e.set(FloatingWindow2.this.f14951e.x, FloatingWindow2.this.f14951e.y);
                                break;
                            case 1:
                                break;
                            case 2:
                                int rawX = (int) (motionEvent.getRawX() - this.f14977c);
                                int rawY = (int) (motionEvent.getRawY() - this.f14978d);
                                if (!this.f14976b && (Math.abs(rawX) > 10 || Math.abs(rawY) > 10)) {
                                    this.f14976b = true;
                                }
                                if (!this.f14976b) {
                                    return false;
                                }
                                FloatingWindow2.this.f14951e.x = rawX + this.f14979e.x;
                                FloatingWindow2.this.f14951e.y = this.f14979e.y + rawY;
                                FloatingWindow2 floatingWindow2 = FloatingWindow2.this;
                                floatingWindow2.m14637c(floatingWindow2.f14951e.x, FloatingWindow2.this.f14951e.y);
                                return false;
                            default:
                                return false;
                        }
                        if (this.f14976b) {
                            return true;
                        }
                        view2.performClick();
                        return false;
                    }
                };
            }
            m14653a(view, a);
        }
    }

    /* renamed from: a */
    public void m14665a(int i, int i2, int i3, int i4) {
        m14664a(i, i2, i3, i4, false);
    }

    /* renamed from: a */
    public void m14664a(int i, int i2, int i3, int i4, boolean z) {
        if (i3 >= 0) {
            this.f14955i = i3;
            m14623f(i3);
        }
        if (i4 >= 0) {
            this.f14958l = i4;
            m14638c(i4);
        }
        if (m14602r() && this.f14932I != null) {
            ViewGroup.LayoutParams layoutParams = this.f14930G.getLayoutParams();
            int i5 = this.f14953g;
            if (i5 >= 0) {
                i5 = this.f14955i;
            }
            if (i3 != -1) {
                WindowManager.LayoutParams layoutParams2 = (WindowManager.LayoutParams) layoutParams;
                if (layoutParams2.width != i5) {
                    this.f14955i = i5;
                    layoutParams2.width = i5;
                    z = true;
                }
            }
            int i6 = this.f14956j;
            if (i6 >= 0) {
                i6 = this.f14958l;
            }
            if (i4 != -1) {
                WindowManager.LayoutParams layoutParams3 = (WindowManager.LayoutParams) layoutParams;
                if (layoutParams3.height != i6) {
                    this.f14958l = i6;
                    layoutParams3.height = i6;
                    z = true;
                }
            }
            WindowManager.LayoutParams layoutParams4 = (WindowManager.LayoutParams) layoutParams;
            if (layoutParams4.x != i) {
                layoutParams4.x = i;
                z = true;
            }
            if (layoutParams4.y != i2) {
                layoutParams4.y = i2;
                z = true;
            }
            int x = m14596x();
            if (x != layoutParams4.windowAnimations) {
                layoutParams4.windowAnimations = x;
                z = true;
            }
            int h = m14617h(layoutParams4.flags);
            if (h != layoutParams4.flags) {
                layoutParams4.flags = h;
                z = true;
            }
            if (z) {
                m14597w();
                this.f14928E.updateViewLayout(this.f14930G, layoutParams);
            }
        }
    }

    /* renamed from: v */
    public void m14598v() {
        if (m14602r() && this.f14932I != null) {
            ViewGroup.LayoutParams layoutParams = this.f14930G.getLayoutParams();
            boolean z = false;
            int x = m14596x();
            WindowManager.LayoutParams layoutParams2 = (WindowManager.LayoutParams) layoutParams;
            if (x != layoutParams2.windowAnimations) {
                layoutParams2.windowAnimations = x;
                z = true;
            }
            int h = m14617h(layoutParams2.flags);
            if (h != layoutParams2.flags) {
                layoutParams2.flags = h;
                z = true;
            }
            if (z) {
                m14597w();
                this.f14928E.updateViewLayout(this.f14930G, layoutParams);
            }
        }
    }

    /* renamed from: b */
    public void m14644b(int i, int i2) {
        WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams) this.f14930G.getLayoutParams();
        m14664a(layoutParams.x, layoutParams.y, i, i2, false);
    }

    /* renamed from: a */
    public void m14658a(View view, int i, int i2) {
        m14652a(view, false, 0, 0, true, i, i2);
    }

    /* renamed from: a */
    public void m14656a(View view, int i, int i2, int i3, int i4) {
        m14652a(view, true, i, i2, true, i3, i4);
    }

    /* renamed from: b */
    public void m14642b(View view, int i, int i2, int i3) {
        Point point = this.f14951e;
        point.x = i2;
        point.y = i3;
        if (i != this.f14950d) {
            this.f14950d = i;
            m14669a();
            m14657a(view, i, i2, i3);
            return;
        }
        m14665a(i2, i3, m14611k(), m14624f());
    }
}
