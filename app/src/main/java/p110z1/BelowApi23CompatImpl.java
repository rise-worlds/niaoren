package p110z1;

import android.content.Context;
import android.os.Build;
import p110z1.FloatingPermissionCompat;

/* renamed from: z1.adb */
/* loaded from: classes3.dex */
public abstract class BelowApi23CompatImpl implements FloatingPermissionCompat.AbstractC3335a {
    @Override // p110z1.FloatingPermissionCompat.AbstractC3335a
    /* renamed from: b */
    public boolean mo14323b(Context context) {
        if (Build.VERSION.SDK_INT >= 19) {
            return FloatingPermissionCompat.m14336a(context, 24);
        }
        return true;
    }
}
