package p110z1;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.common.utils.log.LogUtils;
import com.lody.virtual.client.ipc.ServiceManagerNative;

/* renamed from: z1.ade */
/* loaded from: classes3.dex */
public class MiuiCompatImpl extends BelowApi23CompatImpl {

    /* renamed from: a */
    private static final String f15284a = "MiuiCompatImpl";

    /* renamed from: b */
    private int f15285b;

    public MiuiCompatImpl() {
        this.f15285b = -1;
        this.f15285b = acz.m14330b();
    }

    @Override // p110z1.FloatingPermissionCompat.AbstractC3335a
    /* renamed from: a */
    public boolean mo14318a() {
        int i = this.f15285b;
        return i >= 5 && i <= 8;
    }

    @Override // p110z1.FloatingPermissionCompat.AbstractC3335a
    /* renamed from: a */
    public boolean mo14317a(Context context) {
        int i = this.f15285b;
        if (i == 5) {
            return m14319f(context);
        }
        if (i == 6) {
            return m14320e(context);
        }
        if (i == 7) {
            return m14321d(context);
        }
        if (i == 8) {
            return m14322c(context);
        }
        LogUtils.m22036e(f15284a, "this is a special MIUI rom version, its version code " + this.f15285b);
        return false;
    }

    /* renamed from: c */
    private boolean m14322c(Context context) {
        Intent intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
        intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.PermissionsEditorActivity");
        intent.putExtra("extra_pkgname", context.getPackageName());
        intent.setFlags(268435456);
        if (acz.m14332a(context, intent)) {
            context.startActivity(intent);
            return true;
        }
        Intent intent2 = new Intent("miui.intent.action.APP_PERM_EDITOR");
        intent2.setPackage("com.miui.securitycenter");
        intent2.putExtra("extra_pkgname", context.getPackageName());
        intent2.setFlags(268435456);
        if (!acz.m14332a(context, intent2)) {
            return m14319f(context);
        }
        context.startActivity(intent2);
        return true;
    }

    /* renamed from: d */
    private boolean m14321d(Context context) {
        Intent intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
        intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
        intent.putExtra("extra_pkgname", context.getPackageName());
        intent.setFlags(268435456);
        if (acz.m14332a(context, intent)) {
            context.startActivity(intent);
            return true;
        }
        LogUtils.m22036e(f15284a, "applyV7 Intent is not available!");
        return false;
    }

    /* renamed from: e */
    private boolean m14320e(Context context) {
        Intent intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
        intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
        intent.putExtra("extra_pkgname", context.getPackageName());
        intent.setFlags(268435456);
        if (acz.m14332a(context, intent)) {
            context.startActivity(intent);
            return true;
        }
        LogUtils.m22036e(f15284a, "applyV6 Intent is not available!");
        return false;
    }

    /* renamed from: f */
    private boolean m14319f(Context context) {
        String packageName = context.getPackageName();
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.fromParts(ServiceManagerNative.PACKAGE, packageName, null));
        intent.setFlags(268435456);
        if (acz.m14332a(context, intent)) {
            context.startActivity(intent);
            return true;
        }
        LogUtils.m22036e(f15284a, "applyV5 intent is not available!");
        return false;
    }
}
