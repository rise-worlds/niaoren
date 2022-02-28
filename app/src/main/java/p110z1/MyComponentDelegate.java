package p110z1;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.Window;
import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import com.cyjh.ddy.media.p035a.ResultTypeConstant;
import com.lody.virtual.helper.utils.Reflect;
import com.lody.virtual.helper.utils.VLog;
import com.nrzs.core.receive.PXKJReceiveReceive;
import com.nrzs.va.AppCallback;
import java.io.File;
import patch.MyFixer;

/* renamed from: z1.aiz */
/* loaded from: classes3.dex */
public class MyComponentDelegate extends AppCallback {

    /* renamed from: a */
    public File f16037a = null;

    @Override // com.lody.virtual.client.core.AppCallback
    public void beforeStartApplication(String str, String str2, Context context) {
        Log.d("MyComponentDelegate", "beforeStartApplication");
    }

    @Override // com.lody.virtual.client.core.AppCallback
    public void beforeApplicationCreate(String str, String str2, Application application) {
        Log.d("MyComponentDelegate", "beforeApplicationCreate");
        if (application != null) {
            PXKJReceiveReceive pXKJReceiveReceive = new PXKJReceiveReceive();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("_VA_" + application.getPackageName());
            intentFilter.addAction("_VA_zhuyanyonbanben");
            application.registerReceiver(pXKJReceiveReceive, intentFilter);
        }
    }

    /* renamed from: a */
    private void m12967a(Application application, String str, String str2, String str3) {
        try {
            SharedPreferences.Editor edit = application.getSharedPreferences(str2, 0).edit();
            edit.putBoolean(str3, true);
            edit.apply();
            VLog.m18993d("sunya", "autoagree " + str + ExpandableTextView.f6958c + str2, new Object[0]);
        } catch (Exception unused) {
            VLog.m18993d("sunya", "not found " + str, new Object[0]);
        }
    }

    @Override // com.lody.virtual.client.core.AppCallback
    public void afterApplicationCreate(String str, String str2, Application application) {
        Log.d("MyComponentDelegate", "afterApplicationCreate");
        if (application != null) {
            m12967a(application, "com.pwrd.userterm.b.a", "sp_fatigue_user_term_cache_file", "sp_fatigue_user_term_agree_key");
            m12967a(application, "com.tencent.gcloud.msdk.core.policy.MSDKPolicyTDMHelper", "MSDK_POLICY_PREFERENCES", "MSDK_POLICY_ALLOWED");
            m12967a(application, "com.pwrd.tzyxmznew.UserTermManager", "sp_fatigue_user_term_cache_file", "sp_fatigue_user_term_agree_key");
            m12967a(application, "com.pwrd.zsyj.support.UserTermManager", "sp_fatigue_user_term_cache_file", "sp_fatigue_user_term_agree_key");
            m12967a(application, "com.haojiesdk.wrapper.util.HJPreferenceHelper", "HJSDK", "agree_agreement");
            ((Application) Reflect.m18998on(application).call("getApplicationContext").get()).registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() { // from class: z1.aiz.1
                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityCreated(Activity activity, Bundle bundle) {
                    PXKJCoreUtils.m12921b(activity);
                    Log.d("MyComponentDelegate", "onActivityCreated:" + activity.getClass().getName());
                    if (MyComponentDelegate.this.isNeedCallActivity) {
                        MyComponentDelegate.this.beforeActivityCreate(activity);
                    }
                    if (activity.getPackageName().contains("myhero") || activity.getPackageName().contains("wdyz")) {
                        activity.setRequestedOrientation(0);
                    }
                    if (activity.getPackageName().equals("com.tencent.tmgp.djsy")) {
                        MyFixer.m14535f(activity);
                    }
                    if (!(activity == null || activity.getComponentName() == null)) {
                        String className = activity.getComponentName().getClassName();
                        if (className != null && className.equals("com.pwrd.zsyj.support.LaunchActivity")) {
                            Reflect.m18998on(Reflect.m18996on("com.pwrd.zsyj.support.UserTermManager", activity.getClassLoader()).call("getInstance").get()).set("mAgree", true);
                        }
                        if (className != null && className.equals("shell.LaunchActivity")) {
                            Reflect.m18998on(Reflect.m18996on("shell.UserTermManager", activity.getClassLoader()).call("getInstance").get()).set("mAgree", true);
                        }
                        if (className != null && className.equals("com.pwrd.tzyxmznew.MainActivity")) {
                            Reflect.m18998on(Reflect.m18996on("com.pwrd.tzyxmznew.UserTermManager", activity.getClassLoader()).call("getInstance").get()).set("mAgree", true);
                        }
                    }
                    if (MyComponentDelegate.this.isNeedCallActivity) {
                        MyComponentDelegate.this.afterActivityCreate(activity);
                    }
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityStarted(Activity activity) {
                    Log.d("MyComponentDelegate", "onActivityStarted:" + activity.getClass().getName());
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityResumed(Activity activity) {
                    Log.d("MyComponentDelegate", "onActivityResumed:" + activity.getClass().getName());
                    if (!TencentSupport.f16013a.contains(activity.getPackageName())) {
                        Log.d("MyComponentDelegate", "go toScriptServiceForKey");
                        Log.e("MyComponentDelegate", "onActivityResumed");
                        IntentToAssistService.m12812a(GameApp.getInstance().m13006b(), 3);
                    }
                    VAEnginUtils.m12917a(activity);
                    PXKJCoreUtils.m12929a(activity);
                    if (MyComponentDelegate.this.isNeedCallActivity) {
                        MyComponentDelegate.this.afterActivityResume(activity);
                    }
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityPaused(Activity activity) {
                    Log.d("MyComponentDelegate", "onActivityPaused:" + activity.getClass().getName());
                    Log.e("MyComponentDelegate", "MyComponentDelegate_onActivityPaused");
                    IntentToAssistService.m12812a(GameApp.getInstance().m13006b(), 6);
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityStopped(Activity activity) {
                    Log.d("MyComponentDelegate", "onActivityStopped:" + activity.getClass().getName());
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
                    Log.d("MyComponentDelegate", "onActivitySaveInstanceState:" + activity.getClass().getName());
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityDestroyed(Activity activity) {
                    Log.d("MyComponentDelegate", "onActivityDestroyed:" + activity.getClass().getName());
                    PXKJCoreUtils.m12919c(activity);
                }
            });
        }
    }

    @Override // com.nrzs.va.AppCallback, com.lody.virtual.client.core.AppCallback
    public void beforeActivityCreate(Activity activity) {
        Log.d("MyComponentDelegate", "beforeActivityCreate");
        if (activity.getPackageName().equals("com.hytc.sg")) {
            activity.requestWindowFeature(1);
        }
        if (!(activity.getComponentName() == null || activity.getComponentName().getClassName() == null || !activity.getComponentName().getClassName().contains("ShadowDialogActivity"))) {
            activity.requestWindowFeature(1);
        }
        try {
            Intent intent = activity.getIntent();
            if (intent != null && intent.getExtras() != null) {
                if ((intent.getComponent() == null || !intent.getComponent().getPackageName().contains("com.huawei.hwid")) && intent.hasExtra("_VA_|_intent_")) {
                    activity.setIntent((Intent) intent.getParcelableExtra("_VA_|_intent_"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.nrzs.va.AppCallback, com.lody.virtual.client.core.AppCallback
    public void afterActivityResume(final Activity activity) {
        m12965a(activity.getWindow());
        Log.d("updateEnginDis", "afterActivityResume=1");
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: z1.aiz.2
            @Override // java.lang.Runnable
            public void run() {
                Log.d("updateEnginDis", "afterActivityResume=2");
                int[] a = MyFixer.m14549a(activity.getWindow());
                int i = a[0];
                Log.d("updateEnginDis", "a1=" + i);
                if (i > 0) {
                    apf.m11843a(GameApp.getInstance().m13006b(), ShareVal.f16608r, ShareVal.f16609s, i);
                    IntentToAssistService.m12811a(GameApp.getInstance().m13006b(), i, 10);
                }
                int i2 = a[1];
                Log.d("updateEnginDis", "a2=" + i2);
                if (i2 > 0) {
                    apf.m11843a(GameApp.getInstance().m13006b(), ShareVal.f16608r, ShareVal.f16610t, i2);
                    IntentToAssistService.m12811a(GameApp.getInstance().m13006b(), i2, 10);
                }
            }
        }, 1000L);
    }

    /* renamed from: a */
    public static void m12965a(Window window) {
        View decorView = window.getDecorView();
        VLog.m18993d("sunya-viewUtil", "hideNavigation v=" + decorView, new Object[0]);
        decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() | 2 | 512 | 4 | 1024 | 2048);
    }

    @Override // com.nrzs.va.AppCallback, com.lody.virtual.client.core.AppCallback
    public void afterActivityCreate(Activity activity) {
        m12968a(activity);
        if (this.f16037a != null) {
            Reflect.m18998on((Context) Reflect.m18998on(activity.getApplicationContext()).get("mBase")).set("mFilesDir", this.f16037a);
        }
    }

    /* renamed from: a */
    public static boolean m12969a() {
        return Build.MANUFACTURER.equals("HUAWEI");
    }

    /* renamed from: a */
    public static boolean m12966a(Context context) {
        try {
            Resources resources = context.getResources();
            int identifier = resources.getIdentifier("config_showNavigationBar", "bool", "android");
            boolean z = identifier > 0 ? resources.getBoolean(identifier) : false;
            try {
                Class<?> cls = Class.forName("android.os.SystemProperties");
                String str = (String) cls.getMethod("get", String.class).invoke(cls, "qemu.hw.mainkeys");
                if ("1".equals(str)) {
                    return false;
                }
                if (ResultTypeConstant.f7213z.equals(str)) {
                    return true;
                }
                return z;
            } catch (Exception unused) {
                return z;
            }
        } catch (Exception unused2) {
            return false;
        }
    }

    @TargetApi(19)
    /* renamed from: a */
    private void m12968a(Activity activity) {
        if (m12969a() && m12966a((Context) activity)) {
            final View decorView = activity.getWindow().getDecorView();
            decorView.setSystemUiVisibility(5894);
            decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() { // from class: z1.aiz.3
                @Override // android.view.View.OnSystemUiVisibilityChangeListener
                public void onSystemUiVisibilityChange(int i) {
                    if ((i & 4) == 0) {
                        decorView.setSystemUiVisibility(5894);
                    }
                }
            });
        }
    }
}
