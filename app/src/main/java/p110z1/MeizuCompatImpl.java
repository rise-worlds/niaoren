package p110z1;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

/* renamed from: z1.add */
/* loaded from: classes3.dex */
public class MeizuCompatImpl extends BelowApi23CompatImpl {
    @Override // p110z1.FloatingPermissionCompat.AbstractC3335a
    /* renamed from: a */
    public boolean mo14318a() {
        return true;
    }

    @Override // p110z1.FloatingPermissionCompat.AbstractC3335a
    /* renamed from: a */
    public boolean mo14317a(Context context) {
        try {
            Intent intent = new Intent("com.meizu.safe.security.SHOW_APPSEC");
            intent.setClassName("com.meizu.safe", "com.meizu.safe.security.AppSecActivity");
            intent.putExtra("packageName", context.getPackageName());
            intent.setFlags(268435456);
            context.startActivity(intent);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            try {
                Intent intent2 = new Intent(Settings.class.getDeclaredField("ACTION_MANAGE_OVERLAY_PERMISSION").get(null).toString());
                intent2.setFlags(268435456);
                intent2.setData(Uri.parse("package:" + context.getPackageName()));
                context.startActivity(intent2);
                return true;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
