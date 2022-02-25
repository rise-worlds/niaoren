package p110z1;

import android.support.annotation.NonNull;
import android.view.View;

/* renamed from: z1.xr */
/* loaded from: classes3.dex */
final class AutoValue_ViewScrollChangeEvent extends ViewScrollChangeEvent {

    /* renamed from: a */
    private final View f23687a;

    /* renamed from: b */
    private final int f23688b;

    /* renamed from: c */
    private final int f23689c;

    /* renamed from: d */
    private final int f23690d;

    /* renamed from: e */
    private final int f23691e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_ViewScrollChangeEvent(View view, int i, int i2, int i3, int i4) {
        if (view != null) {
            this.f23687a = view;
            this.f23688b = i;
            this.f23689c = i2;
            this.f23690d = i3;
            this.f23691e = i4;
            return;
        }
        throw new NullPointerException("Null view");
    }

    @Override // p110z1.ViewScrollChangeEvent
    @NonNull
    /* renamed from: a */
    public View mo53a() {
        return this.f23687a;
    }

    @Override // p110z1.ViewScrollChangeEvent
    /* renamed from: b */
    public int mo51b() {
        return this.f23688b;
    }

    @Override // p110z1.ViewScrollChangeEvent
    /* renamed from: c */
    public int mo50c() {
        return this.f23689c;
    }

    @Override // p110z1.ViewScrollChangeEvent
    /* renamed from: d */
    public int mo49d() {
        return this.f23690d;
    }

    @Override // p110z1.ViewScrollChangeEvent
    /* renamed from: e */
    public int mo48e() {
        return this.f23691e;
    }

    public String toString() {
        return "ViewScrollChangeEvent{view=" + this.f23687a + ", scrollX=" + this.f23688b + ", scrollY=" + this.f23689c + ", oldScrollX=" + this.f23690d + ", oldScrollY=" + this.f23691e + C4963cj.f20747d;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ViewScrollChangeEvent)) {
            return false;
        }
        ViewScrollChangeEvent ysVar = (ViewScrollChangeEvent) obj;
        return this.f23687a.equals(ysVar.mo53a()) && this.f23688b == ysVar.mo51b() && this.f23689c == ysVar.mo50c() && this.f23690d == ysVar.mo49d() && this.f23691e == ysVar.mo48e();
    }

    public int hashCode() {
        return ((((((((this.f23687a.hashCode() ^ 1000003) * 1000003) ^ this.f23688b) * 1000003) ^ this.f23689c) * 1000003) ^ this.f23690d) * 1000003) ^ this.f23691e;
    }
}
