package p110z1;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import com.blankj.utilcode.util.NetworkUtils;
import com.cyjh.mobileanjian.ipc.utils.Util;
import com.lody.virtual.client.stub.ChooseTypeAndAccountActivity;
import com.nrzs.base.router.RouterUtils;
import com.nrzs.data.game.bean.TopicInfo;
import com.nrzs.data.other.bean.AdResultInfoItem;
import com.nrzs.data.p066ft.bean.AssistInfo;
import com.nrzs.data.p066ft.bean.EnginInteraRequestInfo;
import com.nrzs.data.user.bean.UserInfo;
import java.io.File;
import java.util.List;
import org.apache.http.HttpHost;
import org.apache.http.HttpVersion;
import org.apache.tools.ant.taskdefs.optional.vss.MSVSSConstants;

/* renamed from: z1.ake */
/* loaded from: classes3.dex */
public class IntentToAssistService {

    /* renamed from: a */
    public static final int f16138a = 1;

    /* renamed from: b */
    public static final int f16139b = 2;

    /* renamed from: c */
    public static final String f16140c = "com.nrzs.ft.service.AssistService";

    /* renamed from: d */
    public static final String f16141d = "class_key";

    /* renamed from: e */
    public static final String f16142e = "script_setvice_key";

    /* renamed from: f */
    public static final String f16143f = "script_setvice_engin_user_init";

    /* renamed from: g */
    public static final String f16144g = "script_setvice_where_key";

    /* renamed from: h */
    public static final String f16145h = "ENGIN_UPDATE_DIS";

    /* renamed from: i */
    public static final int f16146i = 1;

    /* renamed from: j */
    public static final int f16147j = 2;

    /* renamed from: k */
    public static final int f16148k = 3;

    /* renamed from: l */
    public static final int f16149l = 4;

    /* renamed from: m */
    public static final int f16150m = 5;

    /* renamed from: n */
    public static final int f16151n = 6;

    /* renamed from: o */
    public static final int f16152o = 7;

    /* renamed from: p */
    public static final int f16153p = 8;

    /* renamed from: q */
    public static final int f16154q = 9;

    /* renamed from: r */
    public static final int f16155r = 10;

    /* renamed from: s */
    public static final int f16156s = 11;

    /* renamed from: a */
    public static void m12803a(Context context, Class cls, TopicInfo topicInfo, AssistInfo assistInfo, int i, int i2) {
        String str;
        if (i2 != 7) {
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.setFlags(268435456);
            intent.addCategory("android.intent.category.HOME");
            context.startActivity(intent);
        }
        Intent intent2 = new Intent();
        String packageName = context.getPackageName();
        Log.d("MyComponentDelegate", "go toScriptServiceForKey pkg:" + packageName);
        if (packageName.endsWith(".addon.arm64")) {
            str = context.getPackageName().substring(0, packageName.indexOf(".addon.arm64"));
        } else {
            str = context.getPackageName();
        }
        intent2.setClassName(str, f16140c);
        intent2.putExtra(f16142e, i2);
        intent2.putExtra("topicInfo", topicInfo);
        intent2.putExtra("assistInfo", assistInfo);
        intent2.putExtra(f16144g, i);
        intent2.putExtra(f16142e, i2);
        intent2.putExtra(f16141d, cls);
        if (Build.VERSION.SDK_INT >= 26) {
            Log.d("MyComponentDelegate", "start 1 go toScriptServiceForKey pkg:" + str);
            context.startForegroundService(intent2);
            return;
        }
        Log.d("MyComponentDelegate", "start 2 go toScriptServiceForKey pkg:" + str);
        context.startService(intent2);
    }

    @SuppressLint({"MissingPermission"})
    /* renamed from: a */
    public static void m12805a(Context context, UserInfo userInfo, String str, long j, String str2) {
        String str3;
        Intent intent = new Intent(f16140c);
        String packageName = context.getPackageName();
        if (packageName.endsWith(".addon.arm64")) {
            str3 = context.getPackageName().substring(0, packageName.indexOf(".addon.arm64"));
        } else {
            str3 = context.getPackageName();
        }
        intent.setClassName(str3, f16140c);
        EnginInteraRequestInfo enginInteraRequestInfo = new EnginInteraRequestInfo();
        enginInteraRequestInfo.AppSign = Util.getAppSinature(context);
        enginInteraRequestInfo.Command = 1;
        enginInteraRequestInfo.SessionId = str;
        enginInteraRequestInfo.dycIp = LocalHttp.f16542c;
        enginInteraRequestInfo.UserId = j;
        enginInteraRequestInfo.DesKey = str2;
        enginInteraRequestInfo.IsVa = aly.m12531a(1);
        if (NetworkUtils.m23636i()) {
            enginInteraRequestInfo.NetworkType = 1;
        } else if (NetworkUtils.m23637h()) {
            enginInteraRequestInfo.NetworkType = 2;
        }
        enginInteraRequestInfo.ScriptCacheRPath = ShareVal.f16591a + File.separatorChar + MSVSSConstants.SS_EXE;
        intent.putExtra(f16143f, enginInteraRequestInfo);
        intent.putExtra(f16142e, 1);
        intent.putExtra(UserInfo.class.getName(), userInfo);
        if (Build.VERSION.SDK_INT >= 26) {
            context.startForegroundService(intent);
        } else {
            context.startService(intent);
        }
    }

    /* renamed from: a */
    public static void m12812a(Context context, int i) {
        String str;
        Intent intent = new Intent();
        String packageName = context.getPackageName();
        Log.d("MyComponentDelegate", "go toScriptServiceForKey pkg:" + packageName);
        if (packageName.endsWith(".addon.arm64")) {
            str = context.getPackageName().substring(0, packageName.indexOf(".addon.arm64"));
        } else {
            str = context.getPackageName();
        }
        intent.setClassName(str, f16140c);
        intent.putExtra(f16142e, i);
        if (Build.VERSION.SDK_INT >= 26) {
            Log.d("MyComponentDelegate", "start 1 go toScriptServiceForKey pkg:" + str);
            context.startForegroundService(intent);
            return;
        }
        Log.d("MyComponentDelegate", "start 2 go toScriptServiceForKey pkg:" + str);
        context.startService(intent);
    }

    /* renamed from: a */
    public static void m12806a(Context context, UserInfo userInfo) {
        String str;
        Intent intent = new Intent();
        String packageName = context.getPackageName();
        Log.d("MyComponentDelegate", "go toScriptServiceForKey pkg:" + packageName);
        if (packageName.endsWith(".addon.arm64")) {
            str = context.getPackageName().substring(0, packageName.indexOf(".addon.arm64"));
        } else {
            str = context.getPackageName();
        }
        intent.setClassName(str, f16140c);
        intent.putExtra(f16142e, 11);
        intent.putExtra(UserInfo.class.getName(), userInfo);
        if (Build.VERSION.SDK_INT >= 26) {
            Log.d("MyComponentDelegate", "start 1 go toScriptServiceForKey pkg:" + str);
            context.startForegroundService(intent);
            return;
        }
        Log.d("MyComponentDelegate", "start 2 go toScriptServiceForKey pkg:" + str);
        context.startService(intent);
    }

    /* renamed from: a */
    public static void m12801a(Context context, String str, int i) {
        String str2;
        Intent intent = new Intent(f16140c);
        String packageName = context.getPackageName();
        if (packageName.endsWith(".addon.arm64")) {
            str2 = context.getPackageName().substring(0, packageName.indexOf(".addon.arm64"));
        } else {
            str2 = context.getPackageName();
        }
        intent.setClassName(str2, f16140c);
        intent.putExtra("localGamePackage", str);
        intent.putExtra(ChooseTypeAndAccountActivity.KEY_USER_ID, i);
        intent.putExtra(f16142e, 8);
        if (Build.VERSION.SDK_INT >= 26) {
            context.startForegroundService(intent);
        } else {
            context.startService(intent);
        }
    }

    /* renamed from: a */
    public static void m12811a(Context context, int i, int i2) {
        String str;
        Intent intent = new Intent();
        String packageName = context.getPackageName();
        if (packageName.endsWith(".addon.arm64")) {
            str = context.getPackageName().substring(0, packageName.indexOf(".addon.arm64"));
        } else {
            str = context.getPackageName();
        }
        intent.setClassName(str, f16140c);
        intent.putExtra(f16142e, i);
        intent.putExtra(f16142e, i2);
        if (Build.VERSION.SDK_INT >= 26) {
            context.startForegroundService(intent);
        } else {
            context.startService(intent);
        }
    }

    /* renamed from: a */
    private static Intent m12808a(Context context, Intent intent) {
        List<ResolveInfo> queryIntentServices = context.getPackageManager().queryIntentServices(intent, 0);
        ResolveInfo resolveInfo = null;
        if (queryIntentServices == null || queryIntentServices.size() == 0) {
            return null;
        }
        if (queryIntentServices.size() > 1) {
            for (ResolveInfo resolveInfo2 : queryIntentServices) {
                if (resolveInfo2.serviceInfo.packageName.equals(context.getPackageName())) {
                    resolveInfo = resolveInfo2;
                }
            }
        } else {
            resolveInfo = queryIntentServices.get(0);
        }
        ComponentName componentName = new ComponentName(resolveInfo.serviceInfo.packageName, resolveInfo.serviceInfo.name);
        Intent intent2 = new Intent(intent);
        intent2.setComponent(componentName);
        return intent2;
    }

    /* renamed from: a */
    public static void m12813a(Context context) {
        RouterUtils.toLogin(1, 0);
    }

    /* renamed from: a */
    public static void m12804a(Context context, Class cls) {
        try {
            Intent intent = new Intent(context, cls);
            intent.setPackage("com.angel.nrzs");
            intent.setFlags(337641472);
            context.startActivity(intent);
        } catch (Exception unused) {
            Intent intent2 = new Intent("com.angel.nrzs.ui.activity.MainActivity");
            intent2.setPackage("com.angel.nrzs");
            intent2.setFlags(337641472);
            context.startActivity(intent2);
        }
    }

    /* renamed from: a */
    public static void m12810a(Context context, long j) {
        AdResultInfoItem adResultInfoItem = new AdResultInfoItem();
        adResultInfoItem.Title = "购买VIP";
        adResultInfoItem.ExecArgs = HttpVal.f16516c;
        RouterUtils.toMainWebWithWhere(0, 0, adResultInfoItem);
    }

    /* renamed from: a */
    public static void m12809a(Context context, long j, int i) {
        AdResultInfoItem adResultInfoItem = new AdResultInfoItem();
        if (i == 2) {
            adResultInfoItem.Title = "支持鸟人送金币";
        } else {
            adResultInfoItem.Title = "购买续费";
        }
        adResultInfoItem.ExecArgs = HttpVal.f16516c;
        RouterUtils.toMainWebWithWhere(0, i, adResultInfoItem);
    }

    /* renamed from: b */
    public static void m12800b(Context context, long j, int i) {
        AdResultInfoItem adResultInfoItem = new AdResultInfoItem();
        if (i == 2) {
            adResultInfoItem.Title = "支持鸟人送金币";
        } else {
            adResultInfoItem.Title = "购买续费";
        }
        adResultInfoItem.ExecArgs = HttpVal.f16516c;
        RouterUtils.toMainWeb(0, 1, i, adResultInfoItem);
    }

    /* renamed from: a */
    public static void m12807a(Context context, AdResultInfoItem adResultInfoItem, int i) {
        RouterUtils.toMainWeb(i, 1, 0, adResultInfoItem);
    }

    /* renamed from: a */
    public static void m12802a(Context context, String str) {
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
}
