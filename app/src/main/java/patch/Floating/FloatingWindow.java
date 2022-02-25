package patch.Floating;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.IBinder;
import android.util.AttributeSet;
import android.view.KeyEvent;
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
public class FloatingWindow implements IFloatingWindow {

    /* renamed from: a */
    public static final int f14860a = 0;

    /* renamed from: b */
    public static final int f14861b = 1;

    /* renamed from: c */
    public static final int f14862c = 2;

    /* renamed from: A */
    private final int[] f14863A;

    /* renamed from: B */
    private final int[] f14864B;

    /* renamed from: C */
    private final Rect f14865C;

    /* renamed from: D */
    private Context f14866D;

    /* renamed from: E */
    private WindowManager f14867E;

    /* renamed from: F */
    private boolean f14868F;

    /* renamed from: G */
    private C3273b f14869G;

    /* renamed from: H */
    private View f14870H;

    /* renamed from: I */
    private View f14871I;

    /* renamed from: J */
    private boolean f14872J;

    /* renamed from: K */
    private int f14873K;

    /* renamed from: L */
    private int f14874L;

    /* renamed from: M */
    private boolean f14875M;

    /* renamed from: N */
    private boolean f14876N;

    /* renamed from: O */
    private boolean f14877O;

    /* renamed from: P */
    private int f14878P;

    /* renamed from: Q */
    private boolean f14879Q;

    /* renamed from: R */
    private boolean f14880R;

    /* renamed from: S */
    private boolean f14881S;

    /* renamed from: T */
    private boolean f14882T;

    /* renamed from: U */
    private boolean f14883U;

    /* renamed from: V */
    private boolean f14884V;

    /* renamed from: W */
    private boolean f14885W;

    /* renamed from: X */
    private boolean f14886X;

    /* renamed from: Y */
    private boolean f14887Y;

    /* renamed from: Z */
    private int f14888Z;

    /* renamed from: d */
    private int f14889d;

    /* renamed from: e */
    private Point f14890e;

    /* renamed from: f */
    private View.OnTouchListener f14891f;

    /* renamed from: g */
    private int f14892g;

    /* renamed from: h */
    private int f14893h;

    /* renamed from: i */
    private int f14894i;

    /* renamed from: j */
    private int f14895j;

    /* renamed from: k */
    private int f14896k;

    /* renamed from: l */
    private int f14897l;

    /* renamed from: m */
    private int f14898m;

    /* renamed from: n */
    private int f14899n;

    /* renamed from: o */
    private float f14900o;

    /* renamed from: p */
    private Drawable f14901p;

    /* renamed from: q */
    private int f14902q;

    /* renamed from: r */
    private PopupWindow.OnDismissListener f14903r;

    /* renamed from: s */
    private boolean f14904s;

    /* renamed from: t */
    private int f14905t;

    /* renamed from: u */
    private WeakReference f14906u;

    /* renamed from: v */
    private final ViewTreeObserver.OnScrollChangedListener f14907v;

    /* renamed from: w */
    private int f14908w;

    /* renamed from: x */
    private int f14909x;

    /* renamed from: y */
    private int f14910y;

    /* renamed from: z */
    private boolean f14911z;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: patch.Floating.FloatingWindow$a */
    /* loaded from: classes2.dex */
    public class C3272a extends FrameLayout {
        public C3272a(Context context) {
            super(context);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: patch.Floating.FloatingWindow$b */
    /* loaded from: classes2.dex */
    public class C3273b extends FrameLayout {
        public C3273b(Context context) {
            super(context);
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            KeyEvent.DispatcherState keyDispatcherState;
            if (keyEvent.getKeyCode() != 4) {
                return super.dispatchKeyEvent(keyEvent);
            }
            if (getKeyDispatcherState() == null) {
                return super.dispatchKeyEvent(keyEvent);
            }
            if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                KeyEvent.DispatcherState keyDispatcherState2 = getKeyDispatcherState();
                if (keyDispatcherState2 != null) {
                    keyDispatcherState2.startTracking(keyEvent, this);
                }
                return true;
            } else if (keyEvent.getAction() != 1 || (keyDispatcherState = getKeyDispatcherState()) == null || !keyDispatcherState.isTracking(keyEvent) || keyEvent.isCanceled()) {
                return super.dispatchKeyEvent(keyEvent);
            } else {
                if (FloatingWindow.this.f14887Y) {
                    FloatingWindow.this.m14746a();
                }
                return true;
            }
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent motionEvent) {
            if (FloatingWindow.this.f14891f == null || !FloatingWindow.this.f14891f.onTouch(this, motionEvent)) {
                return super.dispatchTouchEvent(motionEvent);
            }
            return true;
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent motionEvent) {
            int x = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            boolean z = true;
            if (motionEvent.getAction() == 0) {
                if (x >= 0 && x < getWidth() && y >= 0 && y < getHeight()) {
                    if (motionEvent.getAction() != 4) {
                        z = super.onTouchEvent(motionEvent);
                    } else if (!FloatingWindow.this.f14887Y) {
                        return true;
                    } else {
                        FloatingWindow.this.m14746a();
                        return true;
                    }
                }
                if (!FloatingWindow.this.f14887Y) {
                    return z;
                }
                FloatingWindow.this.m14746a();
                return z;
            } else if (motionEvent.getAction() != 4) {
                return super.onTouchEvent(motionEvent);
            } else {
                if (!FloatingWindow.this.f14887Y) {
                    return true;
                }
                FloatingWindow.this.m14746a();
                return true;
            }
        }
    }

    public FloatingWindow(Context context) {
        this(context, (AttributeSet) null);
    }

    public FloatingWindow() {
        this((View) null, 0, 0);
    }

    public FloatingWindow(View view, int i, int i2) {
        this(view, i, i2, false);
    }

    public FloatingWindow(int i, int i2) {
        this((View) null, i, i2);
    }

    public FloatingWindow(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FloatingWindow(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public FloatingWindow(Context context, AttributeSet attributeSet, int i, int i2) {
        this.f14863A = new int[2];
        this.f14864B = new int[2];
        this.f14865C = new Rect();
        this.f14873K = 2;
        this.f14874L = 1;
        this.f14875M = true;
        this.f14876N = true;
        this.f14877O = false;
        this.f14878P = -1;
        this.f14881S = true;
        this.f14882T = false;
        this.f14884V = true;
        this.f14885W = false;
        this.f14887Y = false;
        this.f14888Z = -1;
        this.f14889d = 8388659;
        this.f14890e = new Point(0, 0);
        this.f14893h = -2;
        this.f14896k = -2;
        this.f14902q = 1000;
        this.f14904s = false;
        this.f14905t = -1;
        this.f14907v = new ViewTreeObserver.OnScrollChangedListener() { // from class: patch.Floating.FloatingWindow.1
            @Override // android.view.ViewTreeObserver.OnScrollChangedListener
            public void onScrollChanged() {
                if ((FloatingWindow.this.f14906u != null ? FloatingWindow.this.f14906u.get() : null) != null && FloatingWindow.this.f14869G != null) {
                    WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams) FloatingWindow.this.f14869G.getLayoutParams();
                    FloatingWindow.this.m14741a(layoutParams.x, layoutParams.y, -1, -1, true);
                }
            }
        };
        this.f14866D = context;
        this.f14867E = (WindowManager) context.getSystemService("window");
    }

    public FloatingWindow(View view) {
        this(view, 0, 0);
    }

    public FloatingWindow(View view, int i, int i2, boolean z) {
        this.f14863A = new int[2];
        this.f14864B = new int[2];
        this.f14865C = new Rect();
        this.f14873K = 2;
        this.f14874L = 1;
        this.f14875M = true;
        this.f14876N = true;
        this.f14877O = false;
        this.f14878P = -1;
        this.f14881S = true;
        this.f14882T = false;
        this.f14884V = true;
        this.f14885W = false;
        this.f14887Y = false;
        this.f14888Z = -1;
        this.f14889d = 8388659;
        this.f14890e = new Point(0, 0);
        this.f14893h = -2;
        this.f14896k = -2;
        this.f14902q = 1000;
        this.f14904s = false;
        this.f14905t = -1;
        this.f14907v = new ViewTreeObserver.OnScrollChangedListener() { // from class: patch.Floating.FloatingWindow.2
            @Override // android.view.ViewTreeObserver.OnScrollChangedListener
            public void onScrollChanged() {
                if ((FloatingWindow.this.f14906u != null ? FloatingWindow.this.f14906u.get() : null) != null && FloatingWindow.this.f14869G != null) {
                    WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams) FloatingWindow.this.f14869G.getLayoutParams();
                    FloatingWindow.this.m14741a(layoutParams.x, layoutParams.y, -1, -1, true);
                }
            }
        };
        if (view != null) {
            this.f14866D = view.getContext();
            this.f14867E = (WindowManager) this.f14866D.getSystemService("window");
        }
        m14720b(view);
        m14699f(i);
        m14715c(i2);
        m14706d(z);
    }

    /* renamed from: h */
    private int m14692h(int i) {
        int i2 = i & (-8815129);
        if (this.f14904s) {
            i2 |= 32768;
        }
        if (!this.f14872J) {
            i2 |= 8;
            if (this.f14873K == 1) {
                i2 |= 131072;
            }
        } else if (this.f14873K == 2) {
            i2 |= 131072;
        }
        if (!this.f14875M) {
            i2 |= 16;
        }
        if (this.f14876N) {
            i2 |= 262144;
        }
        if (!this.f14877O) {
            i2 |= 512;
        }
        if (m14676s()) {
            i2 |= 8388608;
        }
        if (this.f14879Q) {
            i2 |= 256;
        }
        if (this.f14882T) {
            i2 |= 65536;
        }
        if (this.f14883U) {
            i2 |= 32;
        }
        if (this.f14884V) {
            i2 |= MemoryConstants.f21646d;
        }
        return i2 | 16777216;
    }

    /* renamed from: a */
    private WindowManager.LayoutParams m14739a(IBinder iBinder) {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.gravity = 8388659;
        layoutParams.flags = m14692h(layoutParams.flags);
        layoutParams.type = this.f14902q;
        layoutParams.token = iBinder;
        layoutParams.softInputMode = this.f14874L;
        layoutParams.windowAnimations = m14671x();
        Drawable drawable = this.f14901p;
        layoutParams.format = drawable != null ? drawable.getOpacity() : -3;
        int i = this.f14895j;
        if (i < 0) {
            this.f14897l = i;
            layoutParams.height = i;
        } else {
            int i2 = this.f14896k;
            this.f14897l = i2;
            layoutParams.height = i2;
        }
        int i3 = this.f14892g;
        if (i3 < 0) {
            this.f14894i = i3;
            layoutParams.width = i3;
        } else {
            int i4 = this.f14893h;
            this.f14894i = i4;
            layoutParams.width = i4;
        }
        layoutParams.setTitle("PopupWindow:" + Integer.toHexString(hashCode()));
        return layoutParams;
    }

    /* renamed from: d */
    private C3272a m14708d(View view) {
        ViewGroup.LayoutParams layoutParams = this.f14871I.getLayoutParams();
        int i = -2;
        if (layoutParams == null || layoutParams.height != -2) {
            i = -1;
        }
        C3272a aVar = new C3272a(this.f14866D);
        aVar.addView(view, new FrameLayout.LayoutParams(-1, i));
        return aVar;
    }

    /* renamed from: w */
    private void m14672w() {
        Object obj;
        WeakReference weakReference = this.f14906u;
        if (weakReference != null && (obj = weakReference.get()) != null) {
            this.f14869G.setLayoutDirection(((View) obj).getLayoutDirection());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public void m14714c(int i, int i2) {
        m14742a(i, i2, -1, -1);
    }

    /* renamed from: c */
    private void m14713c(View view, int i, int i2, int i3) {
        m14670y();
        this.f14906u = new WeakReference(view);
        ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
        if (viewTreeObserver != null) {
            viewTreeObserver.addOnScrollChangedListener(this.f14907v);
        }
        this.f14908w = i;
        this.f14909x = i2;
        this.f14910y = i3;
    }

    /* renamed from: a */
    private void m14731a(View view, ViewGroup viewGroup, View view2) {
        if (view.getParent() != null) {
            this.f14867E.removeViewImmediate(view);
        }
        if (viewGroup != null) {
            viewGroup.removeView(view2);
        }
        this.f14869G = null;
        this.f14870H = null;
    }

    /* renamed from: a */
    private void m14730a(View view, WindowManager.LayoutParams layoutParams) {
        Context context = this.f14866D;
        if (context != null) {
            layoutParams.packageName = context.getPackageName();
        }
        C3273b bVar = this.f14869G;
        bVar.setFitsSystemWindows(this.f14882T);
        bVar.setSystemUiVisibility(view.getSystemUiVisibility());
        m14672w();
        this.f14867E.addView(bVar, layoutParams);
    }

    /* renamed from: a */
    private void m14729a(View view, boolean z, int i, int i2, boolean z2, int i3, int i4) {
        boolean z3;
        int i5;
        if (m14677r() && this.f14871I != null) {
            WeakReference weakReference = this.f14906u;
            boolean z4 = false;
            if (z) {
                if (this.f14908w == i) {
                    int i6 = this.f14909x;
                }
                z3 = true;
            } else {
                z3 = false;
            }
            if (weakReference == null || weakReference.get() != view || z3) {
                m14713c(view, i, i2, this.f14910y);
            } else if (z3) {
                this.f14908w = i;
                this.f14909x = i2;
            }
            if (z2) {
                if (i3 == -1) {
                    i5 = this.f14898m;
                } else {
                    this.f14898m = i3;
                    i5 = i3;
                }
                if (i4 == -1) {
                    int i7 = this.f14899n;
                    WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams) this.f14869G.getLayoutParams();
                    m14741a(layoutParams.x, layoutParams.y, i5, i7, (layoutParams.x == layoutParams.x && layoutParams.y == layoutParams.y) ? false : true);
                }
                this.f14899n = i4;
            } else {
                i5 = i3;
            }
            WindowManager.LayoutParams layoutParams2 = (WindowManager.LayoutParams) this.f14869G.getLayoutParams();
            int i8 = layoutParams2.x;
            int i9 = layoutParams2.y;
            int i10 = layoutParams2.x;
            int i11 = layoutParams2.y;
            if (!(i8 == layoutParams2.x && i9 == layoutParams2.y)) {
                z4 = true;
            }
            m14741a(i10, i11, i5, i4, z4);
        }
    }

    /* renamed from: a */
    private void m14728a(WindowManager.LayoutParams layoutParams) {
        View view = this.f14871I;
        if (view == null || this.f14866D == null || this.f14867E == null) {
            throw new IllegalStateException("You must specify a valid content view by calling setContentView() before attempting to show the popup.");
        }
        if (this.f14901p != null) {
            this.f14870H = m14708d(view);
            this.f14870H.setBackground(this.f14901p);
        } else {
            this.f14870H = view;
            if (view.getBackground() == null) {
                this.f14871I.setBackgroundColor(-16777216);
            }
        }
        this.f14869G = m14703e(this.f14870H);
        this.f14870H.setElevation(this.f14900o);
        this.f14898m = layoutParams.width;
        this.f14899n = layoutParams.height;
    }

    /* renamed from: x */
    private int m14671x() {
        int i = this.f14905t;
        if (i == -1) {
            return 0;
        }
        return i;
    }

    /* renamed from: e */
    private C3273b m14703e(View view) {
        ViewGroup.LayoutParams layoutParams = this.f14871I.getLayoutParams();
        int i = -2;
        if (layoutParams == null || layoutParams.height != -2) {
            i = -1;
        }
        C3273b bVar = new C3273b(this.f14866D);
        view.getParent();
        bVar.addView(view, -1, i);
        bVar.setClipChildren(false);
        bVar.setClipToPadding(false);
        return bVar;
    }

    /* renamed from: y */
    private void m14670y() {
        WeakReference weakReference = this.f14906u;
        Object obj = weakReference == null ? null : weakReference.get();
        if (obj != null) {
            ((View) obj).getViewTreeObserver().removeOnScrollChangedListener(this.f14907v);
        }
        this.f14906u = null;
    }

    /* renamed from: a */
    public void m14746a() {
        if (m14677r()) {
            C3273b bVar = this.f14869G;
            ViewParent parent = this.f14871I.getParent();
            if (!(parent instanceof ViewGroup)) {
                parent = null;
            }
            this.f14868F = false;
            m14731a(bVar, (ViewGroup) parent, this.f14871I);
            m14670y();
            PopupWindow.OnDismissListener onDismissListener = this.f14903r;
            if (onDismissListener != null) {
                onDismissListener.onDismiss();
            }
        }
    }

    /* renamed from: b */
    public int m14723b() {
        return this.f14905t;
    }

    /* renamed from: c */
    public Drawable m14716c() {
        return this.f14901p;
    }

    /* renamed from: d */
    public View m14710d() {
        return this.f14871I;
    }

    /* renamed from: e */
    public float m14705e() {
        return this.f14900o;
    }

    /* renamed from: f */
    public int m14700f() {
        return this.f14896k;
    }

    /* renamed from: g */
    public int m14696g() {
        return this.f14873K;
    }

    /* renamed from: a */
    public int m14737a(View view) {
        return m14736a(view, 0);
    }

    /* renamed from: a */
    public int m14736a(View view, int i) {
        return m14732a(view, i, false);
    }

    /* renamed from: a */
    public int m14732a(View view, int i, boolean z) {
        Rect rect = new Rect();
        view.getWindowVisibleDisplayFrame(rect);
        int[] iArr = this.f14863A;
        view.getLocationOnScreen(iArr);
        int i2 = rect.bottom;
        if (z) {
            i2 = view.getContext().getResources().getDisplayMetrics().heightPixels;
        }
        int max = Math.max((i2 - (iArr[1] + view.getHeight())) - i, (iArr[1] - rect.top) + i);
        Drawable drawable = this.f14901p;
        if (drawable == null) {
            return max;
        }
        drawable.getPadding(this.f14865C);
        return max - (this.f14865C.top + this.f14865C.bottom);
    }

    /* renamed from: h */
    public boolean m14693h() {
        return this.f14911z;
    }

    /* renamed from: i */
    public Point m14690i() {
        return this.f14890e;
    }

    /* renamed from: j */
    public int m14688j() {
        return this.f14874L;
    }

    /* renamed from: k */
    public int m14686k() {
        return this.f14893h;
    }

    /* renamed from: l */
    public int m14684l() {
        return this.f14902q;
    }

    /* renamed from: m */
    public boolean m14682m() {
        return this.f14884V;
    }

    /* renamed from: n */
    public boolean m14681n() {
        return this.f14877O;
    }

    /* renamed from: o */
    public boolean m14680o() {
        return this.f14872J;
    }

    /* renamed from: p */
    public boolean m14679p() {
        return this.f14879Q;
    }

    /* renamed from: q */
    public boolean m14678q() {
        return this.f14876N;
    }

    /* renamed from: r */
    public boolean m14677r() {
        return this.f14868F;
    }

    /* renamed from: s */
    public boolean m14676s() {
        Context context;
        if (this.f14878P >= 0 || (context = this.f14866D) == null) {
            if (this.f14878P != 1) {
                return false;
            }
        } else if (context.getApplicationInfo().targetSdkVersion < 11) {
            return false;
        }
        return true;
    }

    /* renamed from: t */
    public boolean m14675t() {
        return this.f14875M;
    }

    /* renamed from: a */
    public void m14744a(int i) {
        this.f14905t = i;
    }

    /* renamed from: a */
    public void m14724a(boolean z) {
        this.f14884V = z;
        this.f14885W = true;
    }

    /* renamed from: a */
    public void m14740a(Drawable drawable) {
        this.f14901p = drawable;
    }

    /* renamed from: b */
    public void m14717b(boolean z) {
        this.f14880R = z;
        m14711c(!z);
    }

    /* renamed from: c */
    public void m14711c(boolean z) {
        this.f14877O = z;
    }

    /* renamed from: b */
    public void m14720b(View view) {
        View view2;
        if (!m14677r()) {
            this.f14871I = view;
            if (this.f14866D == null && (view2 = this.f14871I) != null) {
                this.f14866D = view2.getContext();
            }
            if (this.f14867E == null && this.f14871I != null) {
                this.f14867E = (WindowManager) this.f14866D.getSystemService("window");
            }
            Context context = this.f14866D;
            if (context != null && !this.f14885W) {
                m14724a(context.getApplicationInfo().targetSdkVersion >= 22);
            }
        }
    }

    /* renamed from: a */
    public void m14745a(float f) {
        this.f14900o = f;
    }

    /* renamed from: d */
    public void m14706d(boolean z) {
        this.f14872J = z;
    }

    /* renamed from: b */
    public void m14722b(int i) {
        this.f14889d = i;
    }

    /* renamed from: c */
    public void m14715c(int i) {
        this.f14896k = i;
    }

    /* renamed from: u */
    public void m14674u() {
        this.f14904s = true;
    }

    /* renamed from: d */
    public void m14709d(int i) {
        this.f14873K = i;
    }

    /* renamed from: e */
    public void m14701e(boolean z) {
        this.f14879Q = z;
    }

    /* renamed from: f */
    public void m14697f(boolean z) {
        this.f14882T = z;
    }

    /* renamed from: g */
    public void m14694g(boolean z) {
        this.f14886X = z;
    }

    /* renamed from: a */
    public void m14727a(PopupWindow.OnDismissListener onDismissListener) {
        this.f14903r = onDismissListener;
    }

    /* renamed from: h */
    public void m14691h(boolean z) {
        this.f14876N = z;
    }

    /* renamed from: i */
    public void m14689i(boolean z) {
        this.f14911z = z;
    }

    /* renamed from: e */
    public void m14704e(int i) {
        this.f14874L = i;
    }

    /* renamed from: j */
    public void m14687j(boolean z) {
        this.f14878P = z ? 1 : 0;
    }

    /* renamed from: a */
    public void m14738a(View.OnTouchListener onTouchListener) {
        this.f14891f = onTouchListener;
    }

    /* renamed from: k */
    public void m14685k(boolean z) {
        this.f14883U = !z;
    }

    /* renamed from: l */
    public void m14683l(boolean z) {
        this.f14875M = z;
    }

    /* renamed from: f */
    public void m14699f(int i) {
        this.f14893h = i;
    }

    @Deprecated
    /* renamed from: a */
    public void m14743a(int i, int i2) {
        this.f14892g = i;
        this.f14895j = i2;
    }

    /* renamed from: g */
    public void m14695g(int i) {
        this.f14902q = i;
    }

    @Override // p110z1.IFloatingWindow
    /* renamed from: c */
    public void mo3245c(View view) {
        m14734a(view, this.f14889d, this.f14890e.x, this.f14890e.y);
    }

    /* renamed from: a */
    public void m14734a(View view, int i, int i2, int i3) {
        this.f14889d = i;
        Point point = this.f14890e;
        point.x = i2;
        point.y = i3;
        if (!m14677r() && this.f14871I != null) {
            m14670y();
            this.f14868F = true;
            WindowManager.LayoutParams a = m14739a(view.getWindowToken());
            m14728a(a);
            if (i != 0) {
                a.gravity = i;
            }
            a.x = i2;
            a.y = i3;
            if (this.f14886X && this.f14891f == null) {
                this.f14891f = new View.OnTouchListener() { // from class: patch.Floating.FloatingWindow.3

                    /* renamed from: b */
                    private boolean f14915b;

                    /* renamed from: c */
                    private float f14916c;

                    /* renamed from: d */
                    private float f14917d;

                    /* renamed from: e */
                    private Point f14918e = new Point(0, -1);

                    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
                    @Override // android.view.View.OnTouchListener
                    public boolean onTouch(View view2, MotionEvent motionEvent) {
                        if (!FloatingWindow.this.f14886X) {
                            return false;
                        }
                        switch (motionEvent.getAction()) {
                            case 0:
                                this.f14915b = false;
                                this.f14916c = motionEvent.getRawX();
                                this.f14917d = motionEvent.getRawY();
                                this.f14918e.set(FloatingWindow.this.f14890e.x, FloatingWindow.this.f14890e.y);
                                break;
                            case 1:
                                break;
                            case 2:
                                int rawX = (int) (motionEvent.getRawX() - this.f14916c);
                                int rawY = (int) (motionEvent.getRawY() - this.f14917d);
                                if (!this.f14915b && (Math.abs(rawX) > 10 || Math.abs(rawY) > 10)) {
                                    this.f14915b = true;
                                }
                                if (!this.f14915b) {
                                    return false;
                                }
                                FloatingWindow.this.f14890e.x = rawX + this.f14918e.x;
                                FloatingWindow.this.f14890e.y = this.f14918e.y + rawY;
                                FloatingWindow floatingWindow = FloatingWindow.this;
                                floatingWindow.m14714c(floatingWindow.f14890e.x, FloatingWindow.this.f14890e.y);
                                return false;
                            default:
                                return false;
                        }
                        if (this.f14915b) {
                            return true;
                        }
                        view2.performClick();
                        return false;
                    }
                };
            }
            m14730a(view, a);
        }
    }

    /* renamed from: a */
    public void m14742a(int i, int i2, int i3, int i4) {
        m14741a(i, i2, i3, i4, false);
    }

    /* renamed from: a */
    public void m14741a(int i, int i2, int i3, int i4, boolean z) {
        if (i3 >= 0) {
            this.f14894i = i3;
            m14699f(i3);
        }
        if (i4 >= 0) {
            this.f14897l = i4;
            m14715c(i4);
        }
        if (m14677r() && this.f14871I != null) {
            ViewGroup.LayoutParams layoutParams = this.f14869G.getLayoutParams();
            int i5 = this.f14892g;
            if (i5 >= 0) {
                i5 = this.f14894i;
            }
            if (i3 != -1) {
                WindowManager.LayoutParams layoutParams2 = (WindowManager.LayoutParams) layoutParams;
                if (layoutParams2.width != i5) {
                    this.f14894i = i5;
                    layoutParams2.width = i5;
                    z = true;
                }
            }
            int i6 = this.f14895j;
            if (i6 >= 0) {
                i6 = this.f14897l;
            }
            if (i4 != -1) {
                WindowManager.LayoutParams layoutParams3 = (WindowManager.LayoutParams) layoutParams;
                if (layoutParams3.height != i6) {
                    this.f14897l = i6;
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
            int x = m14671x();
            if (x != layoutParams4.windowAnimations) {
                layoutParams4.windowAnimations = x;
                z = true;
            }
            int h = m14692h(layoutParams4.flags);
            if (h != layoutParams4.flags) {
                layoutParams4.flags = h;
                z = true;
            }
            if (z) {
                m14672w();
                this.f14867E.updateViewLayout(this.f14869G, layoutParams);
            }
        }
    }

    /* renamed from: v */
    public void m14673v() {
        if (m14677r() && this.f14871I != null) {
            ViewGroup.LayoutParams layoutParams = this.f14869G.getLayoutParams();
            boolean z = false;
            int x = m14671x();
            WindowManager.LayoutParams layoutParams2 = (WindowManager.LayoutParams) layoutParams;
            if (x != layoutParams2.windowAnimations) {
                layoutParams2.windowAnimations = x;
                z = true;
            }
            int h = m14692h(layoutParams2.flags);
            if (h != layoutParams2.flags) {
                layoutParams2.flags = h;
                z = true;
            }
            if (z) {
                m14672w();
                this.f14867E.updateViewLayout(this.f14869G, layoutParams);
            }
        }
    }

    /* renamed from: b */
    public void m14721b(int i, int i2) {
        WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams) this.f14869G.getLayoutParams();
        m14741a(layoutParams.x, layoutParams.y, i, i2, false);
    }

    /* renamed from: a */
    public void m14735a(View view, int i, int i2) {
        m14729a(view, false, 0, 0, true, i, i2);
    }

    /* renamed from: a */
    public void m14733a(View view, int i, int i2, int i3, int i4) {
        m14729a(view, true, i, i2, true, i3, i4);
    }

    /* renamed from: b */
    public void m14719b(View view, int i, int i2, int i3) {
        Point point = this.f14890e;
        point.x = i2;
        point.y = i3;
        if (i != this.f14889d) {
            this.f14889d = i;
            m14746a();
            m14734a(view, i, i2, i3);
            return;
        }
        m14742a(i2, i3, m14686k(), m14700f());
    }
}
