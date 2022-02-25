package patch.Floating;

import android.content.Context;
import android.view.View;
import com.lody.virtual.client.core.VirtualCore;

/* renamed from: patch.Floating.a */
/* loaded from: classes2.dex */
public class FloatingViewManager {

    /* renamed from: f */
    private static FloatingViewManager f14983f;

    /* renamed from: a */
    boolean f14984a = false;

    /* renamed from: b */
    private Context f14985b;

    /* renamed from: c */
    private IFloatingViewFactory f14986c;

    /* renamed from: d */
    private FloatingWindow f14987d;

    /* renamed from: e */
    private FloatingWindow2 f14988e;

    /* renamed from: f */
    private TouchCatchLayout m14585f() {
        return GameEngine.m14580b().m14578c();
    }

    /* renamed from: a */
    public Context m14592a() {
        this.f14985b = VirtualCore.get().getContext();
        return this.f14985b;
    }

    /* renamed from: b */
    public void m14589b() {
        FloatingWindow floatingWindow = this.f14987d;
        if (floatingWindow != null) {
            floatingWindow.m14746a();
        }
        FloatingWindow2 floatingWindow2 = this.f14988e;
        if (floatingWindow2 != null) {
            floatingWindow2.m14669a();
        }
    }

    /* renamed from: a */
    public static void m14591a(IFloatingViewFactory cVar) {
        synchronized (FloatingViewManager.class) {
            if (f14983f == null) {
                f14983f = new FloatingViewManager(cVar);
            }
        }
    }

    /* renamed from: c */
    public void m14588c() {
        m14589b();
        IFloatingViewFactory cVar = this.f14986c;
        if (cVar != null) {
            try {
                if (this.f14987d == null) {
                    View a = cVar.m14567a(m14592a());
                    this.f14987d = new FloatingWindow(m14592a());
                    this.f14987d.m14720b(a);
                    this.f14987d.m14694g(true);
                    this.f14987d.m14722b(17);
                }
                if (this.f14987d.m14677r()) {
                    this.f14987d.m14746a();
                }
                TouchCatchLayout f = m14585f();
                if (f != null) {
                    if (f.isAttachedToWindow()) {
                        this.f14987d.mo3245c(f);
                    } else {
                        f.m14593a(this.f14987d);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: d */
    public void m14587d() {
        m14589b();
        IFloatingViewFactory cVar = this.f14986c;
        if (cVar != null) {
            try {
                if (this.f14988e == null) {
                    View b = cVar.m14566b(m14592a());
                    this.f14988e = new FloatingWindow2(m14592a());
                    this.f14988e.m14643b(b);
                    this.f14988e.m14619g(true);
                    this.f14988e.m14645b(17);
                }
                if (this.f14988e.m14602r()) {
                    this.f14988e.m14669a();
                }
                TouchCatchLayout f = m14585f();
                if (f != null) {
                    if (f.isAttachedToWindow()) {
                        this.f14988e.mo3245c(f);
                    } else {
                        f.m14594a(this.f14988e);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    public void m14590a(boolean z) {
        this.f14984a = z;
    }

    /* renamed from: e */
    public static FloatingViewManager m14586e() {
        return f14983f;
    }

    private FloatingViewManager(IFloatingViewFactory cVar) {
        this.f14986c = cVar;
    }
}
