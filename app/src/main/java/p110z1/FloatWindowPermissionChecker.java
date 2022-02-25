package p110z1;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.widget.Toast;
import java.lang.reflect.Method;

/* renamed from: z1.anm */
/* loaded from: classes3.dex */
public class FloatWindowPermissionChecker {

    /* renamed from: a */
    private static final String f16785a = "FloatWindowPermissionChecker";

    /* renamed from: b */
    private static final String f16786b = "无法跳转至权限设置页面，请手动设置或向我们反馈";

    /* renamed from: a */
    public static boolean m12294a(Context context) {
        if (Build.VERSION.SDK_INT >= 23) {
            return Settings.canDrawOverlays(context);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            return m12289d(context);
        }
        return true;
    }

    @RequiresApi(api = 19)
    /* renamed from: d */
    private static boolean m12289d(Context context) {
        Method method;
        try {
            Object systemService = context.getSystemService("appops");
            if (systemService == null || (method = systemService.getClass().getMethod("checkOp", Integer.TYPE, Integer.TYPE, String.class)) == null) {
                return false;
            }
            if (((Integer) method.invoke(systemService, 24, Integer.valueOf(Binder.getCallingUid()), context.getPackageName())).intValue() != 0) {
                if (ann.m12260o()) {
                    return false;
                }
            }
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    /* renamed from: b */
    public static void m12292b(Context context) {
        if (Build.VERSION.SDK_INT < 21) {
            String c = ann.m12272c();
            char c2 = 65535;
            switch (c.hashCode()) {
                case -2053026509:
                    if (c.equals(ann.f16795i)) {
                        c2 = '\b';
                        break;
                    }
                    break;
                case 89163:
                    if (c.equals(ann.f16797k)) {
                        c2 = 7;
                        break;
                    }
                    break;
                case 2132284:
                    if (c.equals(ann.f16788b)) {
                        c2 = 1;
                        break;
                    }
                    break;
                case 2333115:
                    if (c.equals(ann.f16794h)) {
                        c2 = '\t';
                        break;
                    }
                    break;
                case 2366768:
                    if (c.equals(ann.f16787a)) {
                        c2 = 0;
                        break;
                    }
                    break;
                case 2432928:
                    if (c.equals(ann.f16790d)) {
                        c2 = 3;
                        break;
                    }
                    break;
                case 2485634:
                    if (c.equals(ann.f16793g)) {
                        c2 = 4;
                        break;
                    }
                    break;
                case 2634924:
                    if (c.equals(ann.f16789c)) {
                        c2 = 2;
                        break;
                    }
                    break;
                case 1343164416:
                    if (c.equals(ann.f16792f)) {
                        c2 = 5;
                        break;
                    }
                    break;
                case 1670208650:
                    if (c.equals(ann.f16798l)) {
                        c2 = 6;
                        break;
                    }
                    break;
            }
            switch (c2) {
                case 0:
                    m12279n(context);
                    return;
                case 1:
                    m12277p(context);
                    return;
                case 2:
                    m12282k(context);
                    return;
                case 3:
                    m12281l(context);
                    return;
                case 4:
                    m12280m(context);
                    return;
                case 5:
                    m12276q(context);
                    return;
                case 6:
                    m12286g(context);
                    return;
                case 7:
                    m12284i(context);
                    return;
                case '\b':
                    m12285h(context);
                    return;
                case '\t':
                    m12283j(context);
                    return;
                default:
                    Toast.makeText(context, f16786b, 1).show();
                    return;
            }
        } else if (ann.m12267h()) {
            try {
                try {
                    m12278o(context);
                } catch (Exception unused) {
                    m12288e(context);
                }
            } catch (Exception unused2) {
                m12290c(context);
            }
        } else if (ann.m12271d()) {
            m12279n(context);
        } else {
            m12287f(context);
        }
    }

    /* renamed from: c */
    public static void m12290c(Context context) throws Exception {
        Intent intent = new Intent(Settings.class.getDeclaredField("ACTION_MANAGE_OVERLAY_PERMISSION").get(null).toString());
        intent.setFlags(268435456);
        intent.setData(Uri.parse("package:" + context.getPackageName()));
        context.startActivity(intent);
    }

    /* renamed from: a */
    private static boolean m12293a(Intent intent, Context context) {
        if (!m12291b(intent, context)) {
            return false;
        }
        intent.setFlags(268435456);
        context.startActivity(intent);
        return true;
    }

    /* renamed from: b */
    private static boolean m12291b(Intent intent, Context context) {
        return intent != null && context.getPackageManager().queryIntentActivities(intent, 65536).size() > 0;
    }

    /* renamed from: e */
    private static void m12288e(Context context) {
        Toast.makeText(context.getApplicationContext(), f16786b, 1).show();
    }

    /* renamed from: f */
    private static void m12287f(Context context) {
        try {
            Intent intent = new Intent(Settings.class.getDeclaredField("ACTION_MANAGE_OVERLAY_PERMISSION").get(null).toString());
            intent.setData(Uri.parse("package:" + context.getPackageName()));
            m12293a(intent, context);
        } catch (Exception unused) {
            m12288e(context);
        }
    }

    /* renamed from: g */
    private static void m12286g(Context context) {
        Intent intent = new Intent();
        intent.setClassName("com.yulong.android.seccenter", "com.yulong.android.seccenter.dataprotection.ui.AppListActivity");
        if (!m12293a(intent, context)) {
            m12288e(context);
        }
    }

    /* renamed from: h */
    private static void m12285h(Context context) {
        Intent intent = new Intent();
        intent.setClassName("com.lenovo.safecenter", "com.lenovo.safecenter.MainTab.LeSafeMainActivity");
        if (!m12293a(intent, context)) {
            m12288e(context);
        }
    }

    /* renamed from: i */
    private static void m12284i(Context context) {
        Intent intent = new Intent();
        intent.setAction("com.zte.heartyservice.intent.action.startActivity.PERMISSION_SCANNER");
        if (!m12293a(intent, context)) {
            m12288e(context);
        }
    }

    /* renamed from: j */
    private static void m12283j(Context context) {
        Intent intent = new Intent();
        intent.setClassName("com.letv.android.letvsafe", "com.letv.android.letvsafe.AppActivity");
        if (!m12293a(intent, context)) {
            m12288e(context);
        }
    }

    /* renamed from: k */
    private static void m12282k(Context context) {
        Intent intent = new Intent();
        intent.setClassName("com.iqoo.secure", "com.iqoo.secure.ui.phoneoptimize.FloatWindowManager");
        if (!m12293a(intent, context)) {
            m12288e(context);
        }
    }

    /* renamed from: l */
    private static void m12281l(Context context) {
        Intent intent = new Intent();
        intent.putExtra("packageName", context.getPackageName());
        intent.setAction("com.oppo.safe");
        intent.setClassName("com.oppo.safe", "com.oppo.safe.permission.PermissionAppListActivity");
        if (!m12293a(intent, context)) {
            intent.setAction("com.color.safecenter");
            intent.setClassName("com.color.safecenter", "com.color.safecenter.permission.floatwindow.FloatWindowListActivity");
            if (!m12293a(intent, context)) {
                intent.setAction("com.coloros.safecenter");
                intent.setClassName("com.coloros.safecenter", "com.coloros.safecenter.sysfloatwindow.FloatWindowListActivity");
                if (!m12293a(intent, context)) {
                    m12288e(context);
                }
            }
        }
    }

    /* renamed from: m */
    private static void m12280m(Context context) {
        Intent intent = new Intent();
        intent.setClassName("com.android.settings", "com.android.settings.Settings$OverlaySettingsActivity");
        if (!m12293a(intent, context)) {
            intent.setClassName("com.qihoo360.mobilesafe", "com.qihoo360.mobilesafe.ui.index.AppEnterActivity");
            if (!m12293a(intent, context)) {
                m12288e(context);
            }
        }
    }

    /* renamed from: n */
    private static void m12279n(Context context) {
        Intent intent = new Intent();
        intent.setAction("miui.intent.action.APP_PERM_EDITOR");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.putExtra("extra_pkgname", context.getPackageName());
        if (!m12293a(intent, context)) {
            m12288e(context);
        }
    }

    /* renamed from: o */
    private static void m12278o(Context context) {
        Intent intent = new Intent("com.meizu.safe.security.SHOW_APPSEC");
        intent.setClassName("com.meizu.safe", "com.meizu.safe.security.AppSecActivity");
        intent.putExtra("packageName", context.getPackageName());
        if (!m12293a(intent, context)) {
            m12288e(context);
        }
    }

    /* renamed from: p */
    private static void m12277p(Context context) {
        try {
            Intent intent = new Intent();
            intent.setFlags(268435456);
            intent.setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.addviewmonitor.AddViewMonitorActivity"));
            if (!m12293a(intent, context)) {
                intent.setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.notificationmanager.ui.NotificationManagmentActivity"));
                context.startActivity(intent);
            }
        } catch (ActivityNotFoundException unused) {
            Intent intent2 = new Intent();
            intent2.setFlags(268435456);
            intent2.setComponent(new ComponentName("com.Android.settings", "com.android.settings.permission.TabItem"));
            context.startActivity(intent2);
        } catch (SecurityException unused2) {
            Intent intent3 = new Intent();
            intent3.setFlags(268435456);
            intent3.setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.permissionmanager.ui.FtMainActivity"));
            context.startActivity(intent3);
        } catch (Exception unused3) {
            m12288e(context);
        }
    }

    /* renamed from: q */
    private static void m12276q(Context context) {
        Intent intent = new Intent("com.smartisanos.security.action.SWITCHED_PERMISSIONS_NEW");
        intent.setClassName("com.smartisanos.security", "com.smartisanos.security.SwitchedPermissions");
        intent.putExtra("index", 17);
        if (!m12293a(intent, context)) {
            Intent intent2 = new Intent("com.smartisanos.security.action.SWITCHED_PERMISSIONS");
            intent2.setClassName("com.smartisanos.security", "com.smartisanos.security.SwitchedPermissions");
            intent2.putExtra("permission", new String[]{"android.permission.SYSTEM_ALERT_WINDOW"});
            if (!m12293a(intent2, context)) {
                m12288e(context);
            }
        }
    }
}
