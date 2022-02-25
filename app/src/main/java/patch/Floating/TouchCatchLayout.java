package patch.Floating;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import java.util.ArrayList;
import java.util.List;
import p110z1.IFloatingWindow;

/* loaded from: classes2.dex */
public final class TouchCatchLayout extends FrameLayout implements View.OnSystemUiVisibilityChangeListener {

    /* renamed from: a */
    public List f14982a = new ArrayList();

    public TouchCatchLayout(Context context, int i) {
        super(context.getApplicationContext());
        setChildrenDrawingOrderEnabled(true);
    }

    /* renamed from: a */
    public void m14593a(FloatingWindow floatingWindow) {
        this.f14982a.add(floatingWindow);
    }

    /* renamed from: a */
    public void m14594a(FloatingWindow2 floatingWindow2) {
        this.f14982a.add(floatingWindow2);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViewParent parent = getParent();
        if (parent != null && (parent instanceof ViewGroup)) {
            ((ViewGroup) parent).setOnSystemUiVisibilityChangeListener(this);
        }
        for (IFloatingWindow dbzVar : this.f14982a) {
            dbzVar.mo3245c(this);
        }
        this.f14982a.clear();
    }

    @Override // android.view.View.OnSystemUiVisibilityChangeListener
    public void onSystemUiVisibilityChange(int i) {
        ViewParent parent;
        if (((i & 2) == 0 || (i & 4096) == 0) && (parent = getParent()) != null && (parent instanceof ViewGroup)) {
            setSystemUiVisibility(i | 2 | 4096);
        }
    }

    @Override // android.view.View
    public void onWindowFocusChanged(boolean z) {
        ViewParent parent;
        super.onWindowFocusChanged(z);
        if (z && (parent = getParent()) != null && (parent instanceof ViewGroup)) {
            onSystemUiVisibilityChange(((ViewGroup) parent).getVisibility());
        }
    }
}
