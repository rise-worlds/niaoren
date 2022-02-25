package p110z1;

import android.support.annotation.NonNull;
import android.view.View;

/* renamed from: z1.xq */
/* loaded from: classes3.dex */
final class AutoValue_ViewLayoutChangeEvent extends ViewLayoutChangeEvent {

    /* renamed from: a */
    private final View f23678a;

    /* renamed from: b */
    private final int f23679b;

    /* renamed from: c */
    private final int f23680c;

    /* renamed from: d */
    private final int f23681d;

    /* renamed from: e */
    private final int f23682e;

    /* renamed from: f */
    private final int f23683f;

    /* renamed from: g */
    private final int f23684g;

    /* renamed from: h */
    private final int f23685h;

    /* renamed from: i */
    private final int f23686i;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_ViewLayoutChangeEvent(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        if (view != null) {
            this.f23678a = view;
            this.f23679b = i;
            this.f23680c = i2;
            this.f23681d = i3;
            this.f23682e = i4;
            this.f23683f = i5;
            this.f23684g = i6;
            this.f23685h = i7;
            this.f23686i = i8;
            return;
        }
        throw new NullPointerException("Null view");
    }

    @Override // p110z1.ViewLayoutChangeEvent
    @NonNull
    /* renamed from: a */
    public View mo63a() {
        return this.f23678a;
    }

    @Override // p110z1.ViewLayoutChangeEvent
    /* renamed from: b */
    public int mo61b() {
        return this.f23679b;
    }

    @Override // p110z1.ViewLayoutChangeEvent
    /* renamed from: c */
    public int mo60c() {
        return this.f23680c;
    }

    @Override // p110z1.ViewLayoutChangeEvent
    /* renamed from: d */
    public int mo59d() {
        return this.f23681d;
    }

    @Override // p110z1.ViewLayoutChangeEvent
    /* renamed from: e */
    public int mo58e() {
        return this.f23682e;
    }

    @Override // p110z1.ViewLayoutChangeEvent
    /* renamed from: f */
    public int mo57f() {
        return this.f23683f;
    }

    @Override // p110z1.ViewLayoutChangeEvent
    /* renamed from: g */
    public int mo56g() {
        return this.f23684g;
    }

    @Override // p110z1.ViewLayoutChangeEvent
    /* renamed from: h */
    public int mo55h() {
        return this.f23685h;
    }

    @Override // p110z1.ViewLayoutChangeEvent
    /* renamed from: i */
    public int mo54i() {
        return this.f23686i;
    }

    public String toString() {
        return "ViewLayoutChangeEvent{view=" + this.f23678a + ", left=" + this.f23679b + ", top=" + this.f23680c + ", right=" + this.f23681d + ", bottom=" + this.f23682e + ", oldLeft=" + this.f23683f + ", oldTop=" + this.f23684g + ", oldRight=" + this.f23685h + ", oldBottom=" + this.f23686i + C4963cj.f20747d;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ViewLayoutChangeEvent)) {
            return false;
        }
        ViewLayoutChangeEvent yoVar = (ViewLayoutChangeEvent) obj;
        return this.f23678a.equals(yoVar.mo63a()) && this.f23679b == yoVar.mo61b() && this.f23680c == yoVar.mo60c() && this.f23681d == yoVar.mo59d() && this.f23682e == yoVar.mo58e() && this.f23683f == yoVar.mo57f() && this.f23684g == yoVar.mo56g() && this.f23685h == yoVar.mo55h() && this.f23686i == yoVar.mo54i();
    }

    public int hashCode() {
        return ((((((((((((((((this.f23678a.hashCode() ^ 1000003) * 1000003) ^ this.f23679b) * 1000003) ^ this.f23680c) * 1000003) ^ this.f23681d) * 1000003) ^ this.f23682e) * 1000003) ^ this.f23683f) * 1000003) ^ this.f23684g) * 1000003) ^ this.f23685h) * 1000003) ^ this.f23686i;
    }
}
