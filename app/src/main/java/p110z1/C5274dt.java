package p110z1;

import android.content.Context;

/* renamed from: z1.dt */
/* loaded from: classes3.dex */
public class C5274dt implements AbstractC5273ds {

    /* renamed from: a */
    private static AbstractC5273ds f21342a;

    /* renamed from: b */
    private static AbstractC5264dj f21343b;

    /* renamed from: a */
    public static AbstractC5273ds m3203a(Context context, String str) {
        if (context == null) {
            return null;
        }
        if (f21342a == null) {
            f21343b = C5268dn.m3210a(context, str);
            f21342a = new C5274dt();
        }
        return f21342a;
    }

    @Override // p110z1.AbstractC5273ds
    /* renamed from: a */
    public C5271dq mo3201a(C5272dr drVar) {
        return C5270dp.m3208a(f21343b.mo3215a(C5270dp.m3207a(drVar)));
    }

    @Override // p110z1.AbstractC5273ds
    /* renamed from: a */
    public boolean mo3202a(String str) {
        return f21343b.mo3213a(str);
    }
}
