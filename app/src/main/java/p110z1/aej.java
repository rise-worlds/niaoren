package p110z1;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import com.lbd.p054xj.p056ui.activity.XJWebViewActivity;
import org.apache.http.HttpHost;
import org.apache.http.HttpVersion;

/* compiled from: IntentUtils.java */
/* renamed from: z1.aej */
/* loaded from: classes3.dex */
public class aej {
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* renamed from: a */
    public static Intent m13958a() {
        char c;
        ComponentName componentName;
        String lowerCase = Build.BRAND.toLowerCase();
        switch (lowerCase.hashCode()) {
            case -1320380160:
                if (lowerCase.equals("oneplus")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -1206476313:
                if (lowerCase.equals("huawei")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -759499589:
                if (lowerCase.equals("xiaomi")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 50733:
                if (lowerCase.equals("360")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 3418016:
                if (lowerCase.equals("oppo")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 3620012:
                if (lowerCase.equals("vivo")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 103777484:
                if (lowerCase.equals("meizu")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 1864941562:
                if (lowerCase.equals("samsung")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                componentName = new ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.startupmgr.ui.StartupNormalAppListActivity");
                break;
            case 1:
                componentName = new ComponentName("com.coloros.oppoguardelf", "com.coloros.powermanager.fuelgaue.PowerUsageModelActivity");
                break;
            case 2:
                componentName = new ComponentName("com.samsung.android.sm", "com.samsung.android.sm.app.dashboard.SmartManagerDashBoardActivity");
                break;
            case 3:
                componentName = new ComponentName("com.oneplus.security", "com.oneplus.security.chainlaunch.view.ChainLaunchAppListActivity");
                break;
            case 4:
                componentName = new ComponentName("com.miui.securitycenter", "com.miui.permcenter.autostart.AutoStartManagementActivity");
                break;
            case 5:
                componentName = new ComponentName("com.yulong.android.coolsafe", "com.yulong.android.coolsafe.ui.activity.autorun.AutoRunListActivity");
                break;
            case 6:
                componentName = new ComponentName("com.iqoo.secure", "com.iqoo.secure.ui.phoneoptimize.AddWhiteListActivity");
                break;
            case 7:
                componentName = new ComponentName("com.meizu.safe", "com.meizu.safe.permission.SmartBGActivity");
                break;
            default:
                componentName = null;
                break;
        }
        Intent intent = new Intent();
        intent.addFlags(268435456);
        if (componentName != null) {
            intent.setComponent(componentName);
        } else {
            intent.setAction("android.settings.SETTINGS");
        }
        return intent;
    }

    /* renamed from: a */
    public static void m13956a(Context context, String str) {
        Intent intent = new Intent(context, XJWebViewActivity.class);
        intent.addFlags(268435456);
        intent.putExtra("url", str);
        context.startActivity(intent);
    }

    /* renamed from: b */
    public static void m13955b(Context context, String str) {
        if (str != null) {
            try {
                if (str.startsWith(HttpHost.DEFAULT_SCHEME_NAME) || str.startsWith(HttpVersion.HTTP)) {
                    Uri parse = Uri.parse(str);
                    Intent intent = new Intent();
                    intent.setFlags(337641472);
                    intent.setAction("android.intent.action.VIEW");
                    intent.setData(parse);
                    context.startActivity(intent);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    public static void m13957a(Context context, Intent intent) {
        if (Build.VERSION.SDK_INT >= 26) {
            context.startForegroundService(intent);
        } else {
            context.startService(intent);
        }
    }
}
