package p110z1;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import com.common.utils.log.LogUtils;
import p110z1.FloatingPermissionCompat;

/* renamed from: z1.ada */
/* loaded from: classes3.dex */
public class Api23CompatImpl implements FloatingPermissionCompat.AbstractC3335a {

    /* renamed from: a */
    private static final String f15282a = "Api23CompatImpl";

    @Override // p110z1.FloatingPermissionCompat.AbstractC3335a
    /* renamed from: a */
    public boolean mo14318a() {
        return true;
    }

    @Override // p110z1.FloatingPermissionCompat.AbstractC3335a
    /* renamed from: b */
    public boolean mo14323b(Context context) {
        if (Build.VERSION.SDK_INT < 23) {
            return true;
        }
        try {
            return ((Boolean) Settings.class.getDeclaredMethod("canDrawOverlays", Context.class).invoke(null, context)).booleanValue();
        } catch (Exception e) {
            LogUtils.m22036e(f15282a, Log.getStackTraceString(e));
            return true;
        }
    }

    @Override // p110z1.FloatingPermissionCompat.AbstractC3335a
    /* renamed from: a */
    public boolean mo14317a(Context context) {
        try {
            Intent intent = new Intent(Settings.class.getDeclaredField("ACTION_MANAGE_OVERLAY_PERMISSION").get(null).toString());
            intent.setFlags(268435456);
            intent.setData(Uri.parse("package:" + context.getPackageName()));
            context.startActivity(intent);
            return true;
        } catch (Exception e) {
            LogUtils.m22036e(f15282a, Log.getStackTraceString(e));
            return false;
        }
    }
}
