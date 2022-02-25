package p110z1;

import android.content.Context;
import android.content.Intent;
import com.common.utils.log.LogUtils;

/* renamed from: z1.adf */
/* loaded from: classes3.dex */
public class QihooCompatImpl extends BelowApi23CompatImpl {

    /* renamed from: a */
    private static final String f15286a = "QihooCompatImpl";

    @Override // p110z1.FloatingPermissionCompat.AbstractC3335a
    /* renamed from: a */
    public boolean mo14318a() {
        return true;
    }

    @Override // p110z1.FloatingPermissionCompat.AbstractC3335a
    /* renamed from: a */
    public boolean mo14317a(Context context) {
        Intent intent = new Intent();
        intent.setClassName("com.android.settings", "com.android.settings.Settings$OverlaySettingsActivity");
        intent.setFlags(268435456);
        if (acz.m14332a(context, intent)) {
            context.startActivity(intent);
            return true;
        }
        intent.setClassName("com.qihoo360.mobilesafe", "com.qihoo360.mobilesafe.ui.index.appEnterActivity");
        if (acz.m14332a(context, intent)) {
            context.startActivity(intent);
            return true;
        }
        LogUtils.m22036e(f15286a, "can't open permission page with particular name, please use \"adb shell dumpsys activity\" command and tell me the name of the float window permission page");
        return false;
    }
}
