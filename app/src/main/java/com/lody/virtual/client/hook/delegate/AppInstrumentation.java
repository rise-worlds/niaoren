package com.lody.virtual.client.hook.delegate;

import android.app.Activity;
import android.app.Application;
import android.app.Instrumentation;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import com.lody.virtual.client.VClient;
import com.lody.virtual.client.core.InvocationStubManager;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.fixer.ActivityFixer;
import com.lody.virtual.client.fixer.ContextFixer;
import com.lody.virtual.client.hook.proxies.p059am.HCallbackStub;
import com.lody.virtual.client.interfaces.IInjector;
import com.lody.virtual.helper.compat.ActivityManagerCompat;
import com.lody.virtual.helper.utils.VLog;
import p110z1.ActivityThread;

/* loaded from: classes.dex */
public final class AppInstrumentation extends InstrumentationDelegate implements IInjector {
    private static final String TAG = "AppInstrumentation";
    private static AppInstrumentation gDefault;

    private AppInstrumentation(Instrumentation instrumentation) {
        super(instrumentation);
    }

    public static AppInstrumentation getDefault() {
        if (gDefault == null) {
            synchronized (AppInstrumentation.class) {
                if (gDefault == null) {
                    gDefault = create();
                }
            }
        }
        return gDefault;
    }

    private static AppInstrumentation create() {
        Instrumentation instrumentation = ActivityThread.mInstrumentation.get(VirtualCore.mainThread());
        if (instrumentation instanceof AppInstrumentation) {
            return (AppInstrumentation) instrumentation;
        }
        return new AppInstrumentation(instrumentation);
    }

    @Override // com.lody.virtual.client.interfaces.IInjector
    public void inject() {
        Instrumentation instrumentation = ActivityThread.mInstrumentation.get(VirtualCore.mainThread());
        if (this.base == null) {
            this.base = instrumentation;
        }
        if (instrumentation != this.base) {
            VLog.m18986w(TAG, "some plugin framework", new Object[0]);
            this.root = this.base;
            this.base = instrumentation;
        }
        InstrumentationResolver.resolveInstrumentation(VClient.get().getCurrentPackage());
        ActivityThread.mInstrumentation.set(VirtualCore.mainThread(), this);
    }

    @Override // com.lody.virtual.client.interfaces.IInjector
    public boolean isEnvBad() {
        return !(ActivityThread.mInstrumentation.get(VirtualCore.mainThread()) instanceof AppInstrumentation);
    }

    private void checkActivityCallback() {
        InvocationStubManager.getInstance().checkEnv(HCallbackStub.class);
        InvocationStubManager.getInstance().checkEnv(AppInstrumentation.class);
    }

    @Override // com.lody.virtual.client.hook.delegate.InstrumentationDelegate, android.app.Instrumentation
    public void callActivityOnCreate(Activity activity, Bundle bundle) {
        checkActivityCallback();
        ContextFixer.fixContext(activity);
        ActivityFixer.fixActivity(activity);
        ActivityInfo activityInfo = p110z1.Activity.mActivityInfo.get(activity);
        if (activityInfo != null) {
            if (activityInfo.theme != 0) {
                activity.setTheme(activityInfo.theme);
            }
            if (!(activity.getRequestedOrientation() != -1 || activityInfo.screenOrientation == -1 || activity.getRequestedOrientation() == activityInfo.screenOrientation)) {
                ActivityManagerCompat.setActivityOrientation(activity, activityInfo.screenOrientation);
                Configuration configuration = activity.getResources().getConfiguration();
                boolean z = false;
                if (isOrientationLandscape(activityInfo.screenOrientation)) {
                    if (configuration.orientation != 2) {
                        z = true;
                    }
                    configuration.orientation = 2;
                } else {
                    if (configuration.orientation != 1) {
                        z = true;
                    }
                    configuration.orientation = 1;
                }
                if (z) {
                    try {
                        Thread.sleep(800L);
                    } catch (Exception unused) {
                    }
                }
            }
        }
        VirtualCore.get().getAppCallback().beforeActivityCreate(activity);
        super.callActivityOnCreate(activity, bundle);
        VirtualCore.get().getAppCallback().afterActivityCreate(activity);
    }

    private boolean isOrientationLandscape(int i) {
        return Build.VERSION.SDK_INT >= 18 ? i == 0 || i == 6 || i == 8 || i == 11 : i == 0 || i == 6 || i == 8;
    }

    @Override // com.lody.virtual.client.hook.delegate.InstrumentationDelegate, android.app.Instrumentation
    public void callActivityOnDestroy(Activity activity) {
        super.callActivityOnDestroy(activity);
    }

    @Override // com.lody.virtual.client.hook.delegate.InstrumentationDelegate, android.app.Instrumentation
    public void callActivityOnPause(Activity activity) {
        super.callActivityOnPause(activity);
    }

    @Override // com.lody.virtual.client.hook.delegate.InstrumentationDelegate, android.app.Instrumentation
    public void callApplicationOnCreate(Application application) {
        checkActivityCallback();
        super.callApplicationOnCreate(application);
    }

    @Override // com.lody.virtual.client.hook.delegate.InstrumentationDelegate, android.app.Instrumentation
    public Activity newActivity(ClassLoader classLoader, String str, Intent intent) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        try {
            return super.newActivity(classLoader, str, intent);
        } catch (ClassNotFoundException unused) {
            return this.root.newActivity(classLoader, str, intent);
        }
    }

    @Override // com.lody.virtual.client.hook.delegate.InstrumentationDelegate, android.app.Instrumentation
    public void callActivityOnResume(Activity activity) {
        super.callActivityOnResume(activity);
        VirtualCore.get().getAppCallback().afterActivityResume(activity);
    }
}
