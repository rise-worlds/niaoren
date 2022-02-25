package p110z1;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.common.utils.log.LogUtils;

/* renamed from: z1.adc */
/* loaded from: classes3.dex */
public class HuaweiCompatImpl extends BelowApi23CompatImpl {

    /* renamed from: a */
    private static final String f15283a = "HuaweiCompatImpl";

    @Override // p110z1.FloatingPermissionCompat.AbstractC3335a
    /* renamed from: a */
    public boolean mo14318a() {
        return true;
    }

    @Override // p110z1.FloatingPermissionCompat.AbstractC3335a
    /* renamed from: a */
    public boolean mo14317a(Context context) {
        try {
            Intent intent = new Intent();
            intent.setFlags(268435456);
            intent.setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.addviewmonitor.AddViewMonitorActivity"));
            if (acz.m14333a() == 3.1d) {
                context.startActivity(intent);
            } else {
                intent.setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.notificationmanager.ui.NotificationManagmentActivity"));
                context.startActivity(intent);
            }
            return false;
        } catch (ActivityNotFoundException e) {
            Intent intent2 = new Intent();
            intent2.setFlags(268435456);
            intent2.setComponent(new ComponentName("com.Android.settings", "com.android.settings.permission.TabItem"));
            context.startActivity(intent2);
            LogUtils.m22036e(f15283a, Log.getStackTraceString(e));
            return false;
        } catch (SecurityException e2) {
            Intent intent3 = new Intent();
            intent3.setFlags(268435456);
            intent3.setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.permissionmanager.ui.MainActivity"));
            context.startActivity(intent3);
            LogUtils.m22036e(f15283a, Log.getStackTraceString(e2));
            return false;
        } catch (Exception e3) {
            LogUtils.m22036e(f15283a, Log.getStackTraceString(e3));
            return false;
        }
    }
}
