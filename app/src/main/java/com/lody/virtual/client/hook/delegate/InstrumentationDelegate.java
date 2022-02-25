package com.lody.virtual.client.hook.delegate;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import android.app.Instrumentation;
import android.app.UiAutomation;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PersistableBundle;
import android.os.UserHandle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import com.lody.virtual.helper.Keep;
import com.lody.virtual.helper.MultiAvoidRecursive;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

@Keep
/* loaded from: classes.dex */
public class InstrumentationDelegate extends Instrumentation {
    private MultiAvoidRecursive avoidRecursive = new MultiAvoidRecursive(20);
    protected Instrumentation base;
    protected Instrumentation root;

    public InstrumentationDelegate(Instrumentation instrumentation) {
        this.base = instrumentation;
        this.root = instrumentation;
    }

    @Override // android.app.Instrumentation
    public void onCreate(Bundle bundle) {
        this.root.onCreate(bundle);
    }

    @Override // android.app.Instrumentation
    public void start() {
        this.root.start();
    }

    @Override // android.app.Instrumentation
    public void onStart() {
        this.root.onStart();
    }

    @Override // android.app.Instrumentation
    public boolean onException(Object obj, Throwable th) {
        return this.root.onException(obj, th);
    }

    @Override // android.app.Instrumentation
    public void sendStatus(int i, Bundle bundle) {
        this.root.sendStatus(i, bundle);
    }

    @Override // android.app.Instrumentation
    public void finish(int i, Bundle bundle) {
        this.root.finish(i, bundle);
    }

    @Override // android.app.Instrumentation
    public void setAutomaticPerformanceSnapshots() {
        this.root.setAutomaticPerformanceSnapshots();
    }

    @Override // android.app.Instrumentation
    public void startPerformanceSnapshot() {
        this.root.startPerformanceSnapshot();
    }

    @Override // android.app.Instrumentation
    public void endPerformanceSnapshot() {
        this.root.endPerformanceSnapshot();
    }

    @Override // android.app.Instrumentation
    public void onDestroy() {
        this.root.onDestroy();
    }

    @Override // android.app.Instrumentation
    public Context getContext() {
        return this.root.getContext();
    }

    @Override // android.app.Instrumentation
    public ComponentName getComponentName() {
        return this.root.getComponentName();
    }

    @Override // android.app.Instrumentation
    public Context getTargetContext() {
        return this.root.getTargetContext();
    }

    @Override // android.app.Instrumentation
    public boolean isProfiling() {
        return this.root.isProfiling();
    }

    @Override // android.app.Instrumentation
    public void startProfiling() {
        this.root.startProfiling();
    }

    @Override // android.app.Instrumentation
    public void stopProfiling() {
        this.root.stopProfiling();
    }

    @Override // android.app.Instrumentation
    public void setInTouchMode(boolean z) {
        this.root.setInTouchMode(z);
    }

    @Override // android.app.Instrumentation
    public void waitForIdle(Runnable runnable) {
        this.root.waitForIdle(runnable);
    }

    @Override // android.app.Instrumentation
    public void waitForIdleSync() {
        this.root.waitForIdleSync();
    }

    @Override // android.app.Instrumentation
    public void runOnMainSync(Runnable runnable) {
        this.root.runOnMainSync(runnable);
    }

    @Override // android.app.Instrumentation
    public Activity startActivitySync(Intent intent) {
        return this.root.startActivitySync(intent);
    }

    @Override // android.app.Instrumentation
    public void addMonitor(Instrumentation.ActivityMonitor activityMonitor) {
        this.root.addMonitor(activityMonitor);
    }

    @Override // android.app.Instrumentation
    public Instrumentation.ActivityMonitor addMonitor(IntentFilter intentFilter, Instrumentation.ActivityResult activityResult, boolean z) {
        return this.root.addMonitor(intentFilter, activityResult, z);
    }

    @Override // android.app.Instrumentation
    public Instrumentation.ActivityMonitor addMonitor(String str, Instrumentation.ActivityResult activityResult, boolean z) {
        return this.root.addMonitor(str, activityResult, z);
    }

    @Override // android.app.Instrumentation
    public boolean checkMonitorHit(Instrumentation.ActivityMonitor activityMonitor, int i) {
        return this.root.checkMonitorHit(activityMonitor, i);
    }

    @Override // android.app.Instrumentation
    public Activity waitForMonitor(Instrumentation.ActivityMonitor activityMonitor) {
        return this.root.waitForMonitor(activityMonitor);
    }

    @Override // android.app.Instrumentation
    public Activity waitForMonitorWithTimeout(Instrumentation.ActivityMonitor activityMonitor, long j) {
        return this.root.waitForMonitorWithTimeout(activityMonitor, j);
    }

    @Override // android.app.Instrumentation
    public void removeMonitor(Instrumentation.ActivityMonitor activityMonitor) {
        this.root.removeMonitor(activityMonitor);
    }

    @Override // android.app.Instrumentation
    public boolean invokeMenuActionSync(Activity activity, int i, int i2) {
        return this.root.invokeMenuActionSync(activity, i, i2);
    }

    @Override // android.app.Instrumentation
    public boolean invokeContextMenuAction(Activity activity, int i, int i2) {
        return this.root.invokeContextMenuAction(activity, i, i2);
    }

    @Override // android.app.Instrumentation
    public void sendStringSync(String str) {
        this.root.sendStringSync(str);
    }

    @Override // android.app.Instrumentation
    public void sendKeySync(KeyEvent keyEvent) {
        this.root.sendKeySync(keyEvent);
    }

    @Override // android.app.Instrumentation
    public void sendKeyDownUpSync(int i) {
        this.root.sendKeyDownUpSync(i);
    }

    @Override // android.app.Instrumentation
    public void sendCharacterSync(int i) {
        this.root.sendCharacterSync(i);
    }

    @Override // android.app.Instrumentation
    public void sendPointerSync(MotionEvent motionEvent) {
        this.root.sendPointerSync(motionEvent);
    }

    @Override // android.app.Instrumentation
    public void sendTrackballEventSync(MotionEvent motionEvent) {
        this.root.sendTrackballEventSync(motionEvent);
    }

    @Override // android.app.Instrumentation
    public Application newApplication(ClassLoader classLoader, String str, Context context) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        try {
            if (this.avoidRecursive.beginCall(0)) {
                return this.base.newApplication(classLoader, str, context);
            }
            return this.root.newApplication(classLoader, str, context);
        } finally {
            this.avoidRecursive.finishCall(0);
        }
    }

    @Override // android.app.Instrumentation
    public void callApplicationOnCreate(Application application) {
        try {
            if (this.avoidRecursive.beginCall(1)) {
                this.base.callApplicationOnCreate(application);
            } else {
                this.root.callApplicationOnCreate(application);
            }
        } finally {
            this.avoidRecursive.finishCall(1);
        }
    }

    @Override // android.app.Instrumentation
    public Activity newActivity(Class<?> cls, Context context, IBinder iBinder, Application application, Intent intent, ActivityInfo activityInfo, CharSequence charSequence, Activity activity, String str, Object obj) throws InstantiationException, IllegalAccessException {
        try {
            if (this.avoidRecursive.beginCall(2)) {
                return this.base.newActivity(cls, context, iBinder, application, intent, activityInfo, charSequence, activity, str, obj);
            }
            return this.root.newActivity(cls, context, iBinder, application, intent, activityInfo, charSequence, activity, str, obj);
        } finally {
            this.avoidRecursive.finishCall(2);
        }
    }

    @Override // android.app.Instrumentation
    public Activity newActivity(ClassLoader classLoader, String str, Intent intent) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        try {
            if (this.avoidRecursive.beginCall(3)) {
                return this.base.newActivity(classLoader, str, intent);
            }
            return this.root.newActivity(classLoader, str, intent);
        } finally {
            this.avoidRecursive.finishCall(3);
        }
    }

    @Override // android.app.Instrumentation
    public void callActivityOnCreate(Activity activity, Bundle bundle) {
        try {
            if (this.avoidRecursive.beginCall(4)) {
                this.base.callActivityOnCreate(activity, bundle);
            } else {
                this.root.callActivityOnCreate(activity, bundle);
            }
        } finally {
            this.avoidRecursive.finishCall(4);
        }
    }

    @Override // android.app.Instrumentation
    @TargetApi(21)
    public void callActivityOnCreate(Activity activity, Bundle bundle, PersistableBundle persistableBundle) {
        try {
            if (this.avoidRecursive.beginCall(5)) {
                this.base.callActivityOnCreate(activity, bundle, persistableBundle);
            } else {
                this.root.callActivityOnCreate(activity, bundle, persistableBundle);
            }
        } finally {
            this.avoidRecursive.finishCall(5);
        }
    }

    @Override // android.app.Instrumentation
    public void callActivityOnDestroy(Activity activity) {
        try {
            if (this.avoidRecursive.beginCall(6)) {
                this.base.callActivityOnDestroy(activity);
            } else {
                this.root.callActivityOnDestroy(activity);
            }
        } finally {
            this.avoidRecursive.finishCall(6);
        }
    }

    @Override // android.app.Instrumentation
    public void callActivityOnRestoreInstanceState(Activity activity, Bundle bundle) {
        try {
            if (this.avoidRecursive.beginCall(7)) {
                this.base.callActivityOnRestoreInstanceState(activity, bundle);
            } else {
                this.root.callActivityOnRestoreInstanceState(activity, bundle);
            }
        } finally {
            this.avoidRecursive.finishCall(7);
        }
    }

    @Override // android.app.Instrumentation
    @TargetApi(21)
    public void callActivityOnRestoreInstanceState(Activity activity, Bundle bundle, PersistableBundle persistableBundle) {
        try {
            if (this.avoidRecursive.beginCall(8)) {
                this.base.callActivityOnRestoreInstanceState(activity, bundle, persistableBundle);
            } else {
                this.root.callActivityOnRestoreInstanceState(activity, bundle, persistableBundle);
            }
        } finally {
            this.avoidRecursive.finishCall(8);
        }
    }

    @Override // android.app.Instrumentation
    public void callActivityOnPostCreate(Activity activity, Bundle bundle) {
        try {
            if (this.avoidRecursive.beginCall(9)) {
                this.base.callActivityOnPostCreate(activity, bundle);
            } else {
                this.root.callActivityOnPostCreate(activity, bundle);
            }
        } finally {
            this.avoidRecursive.finishCall(9);
        }
    }

    @Override // android.app.Instrumentation
    @TargetApi(21)
    public void callActivityOnPostCreate(Activity activity, Bundle bundle, PersistableBundle persistableBundle) {
        try {
            if (this.avoidRecursive.beginCall(10)) {
                this.base.callActivityOnPostCreate(activity, bundle, persistableBundle);
            } else {
                this.root.callActivityOnPostCreate(activity, bundle, persistableBundle);
            }
        } finally {
            this.avoidRecursive.finishCall(10);
        }
    }

    @Override // android.app.Instrumentation
    public void callActivityOnNewIntent(Activity activity, Intent intent) {
        try {
            if (this.avoidRecursive.beginCall(11)) {
                this.base.callActivityOnNewIntent(activity, intent);
            } else {
                this.root.callActivityOnNewIntent(activity, intent);
            }
        } finally {
            this.avoidRecursive.finishCall(11);
        }
    }

    @Override // android.app.Instrumentation
    public void callActivityOnStart(Activity activity) {
        try {
            if (this.avoidRecursive.beginCall(12)) {
                this.base.callActivityOnStart(activity);
            } else {
                this.root.callActivityOnStart(activity);
            }
        } finally {
            this.avoidRecursive.finishCall(12);
        }
    }

    @Override // android.app.Instrumentation
    public void callActivityOnRestart(Activity activity) {
        try {
            if (this.avoidRecursive.beginCall(13)) {
                this.base.callActivityOnRestart(activity);
            } else {
                this.root.callActivityOnRestart(activity);
            }
        } finally {
            this.avoidRecursive.finishCall(13);
        }
    }

    @Override // android.app.Instrumentation
    public void callActivityOnResume(Activity activity) {
        try {
            if (this.avoidRecursive.beginCall(14)) {
                this.base.callActivityOnResume(activity);
            } else {
                this.root.callActivityOnResume(activity);
            }
        } finally {
            this.avoidRecursive.finishCall(14);
        }
    }

    @Override // android.app.Instrumentation
    public void callActivityOnStop(Activity activity) {
        try {
            if (this.avoidRecursive.beginCall(15)) {
                this.base.callActivityOnStop(activity);
            } else {
                this.root.callActivityOnStop(activity);
            }
        } finally {
            this.avoidRecursive.finishCall(15);
        }
    }

    @Override // android.app.Instrumentation
    public void callActivityOnSaveInstanceState(Activity activity, Bundle bundle) {
        try {
            if (this.avoidRecursive.beginCall(16)) {
                this.base.callActivityOnSaveInstanceState(activity, bundle);
            } else {
                this.root.callActivityOnSaveInstanceState(activity, bundle);
            }
        } finally {
            this.avoidRecursive.finishCall(16);
        }
    }

    @Override // android.app.Instrumentation
    @TargetApi(21)
    public void callActivityOnSaveInstanceState(Activity activity, Bundle bundle, PersistableBundle persistableBundle) {
        try {
            if (this.avoidRecursive.beginCall(17)) {
                this.base.callActivityOnSaveInstanceState(activity, bundle, persistableBundle);
            } else {
                this.root.callActivityOnSaveInstanceState(activity, bundle, persistableBundle);
            }
        } finally {
            this.avoidRecursive.finishCall(17);
        }
    }

    @Override // android.app.Instrumentation
    public void callActivityOnPause(Activity activity) {
        try {
            if (this.avoidRecursive.beginCall(18)) {
                this.base.callActivityOnPause(activity);
            } else {
                this.root.callActivityOnPause(activity);
            }
        } finally {
            this.avoidRecursive.finishCall(18);
        }
    }

    @Override // android.app.Instrumentation
    public void callActivityOnUserLeaving(Activity activity) {
        try {
            if (this.avoidRecursive.beginCall(19)) {
                this.base.callActivityOnUserLeaving(activity);
            } else {
                this.root.callActivityOnUserLeaving(activity);
            }
        } finally {
            this.avoidRecursive.finishCall(19);
        }
    }

    @Override // android.app.Instrumentation
    public Bundle getAllocCounts() {
        return this.root.getAllocCounts();
    }

    @Override // android.app.Instrumentation
    public Bundle getBinderCounts() {
        return this.root.getBinderCounts();
    }

    @Override // android.app.Instrumentation
    @TargetApi(18)
    public UiAutomation getUiAutomation() {
        return this.root.getUiAutomation();
    }

    public Instrumentation.ActivityResult execStartActivity(Context context, IBinder iBinder, IBinder iBinder2, Activity activity, Intent intent, int i, Bundle bundle) throws Throwable {
        try {
            return this.avoidRecursive.beginCall(20) ? (Instrumentation.ActivityResult) findDeclaredMethod(this.base, "execStartActivity", Context.class, IBinder.class, IBinder.class, Activity.class, Intent.class, Integer.TYPE, Bundle.class).invoke(this.base, context, iBinder, iBinder2, activity, intent, Integer.valueOf(i), bundle) : (Instrumentation.ActivityResult) findDeclaredMethod(this.root, "execStartActivity", Context.class, IBinder.class, IBinder.class, Activity.class, Intent.class, Integer.TYPE, Bundle.class).invoke(this.base, context, iBinder, iBinder2, activity, intent, Integer.valueOf(i), bundle);
        } catch (InvocationTargetException e) {
            if (e.getCause() == null) {
                return null;
            }
            throw e.getCause();
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        } finally {
            this.avoidRecursive.finishCall(20);
        }
    }

    public Instrumentation.ActivityResult execStartActivity(Context context, IBinder iBinder, IBinder iBinder2, String str, Intent intent, int i, Bundle bundle) throws Throwable {
        try {
            return this.avoidRecursive.beginCall(21) ? (Instrumentation.ActivityResult) findDeclaredMethod(this.base, "execStartActivity", Context.class, IBinder.class, IBinder.class, String.class, Intent.class, Integer.TYPE, Bundle.class).invoke(this.base, context, iBinder, iBinder2, str, intent, Integer.valueOf(i), bundle) : (Instrumentation.ActivityResult) findDeclaredMethod(this.root, "execStartActivity", Context.class, IBinder.class, IBinder.class, String.class, Intent.class, Integer.TYPE, Bundle.class).invoke(this.base, context, iBinder, iBinder2, str, intent, Integer.valueOf(i), bundle);
        } catch (InvocationTargetException e) {
            if (e.getCause() == null) {
                return null;
            }
            throw e.getCause();
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        } finally {
            this.avoidRecursive.finishCall(21);
        }
    }

    public Instrumentation.ActivityResult execStartActivity(Context context, IBinder iBinder, IBinder iBinder2, Fragment fragment, Intent intent, int i) throws Throwable {
        try {
            return this.avoidRecursive.beginCall(22) ? (Instrumentation.ActivityResult) findDeclaredMethod(this.base, "execStartActivity", Context.class, IBinder.class, IBinder.class, Fragment.class, Intent.class, Integer.TYPE).invoke(this.base, context, iBinder, iBinder2, fragment, intent, Integer.valueOf(i)) : (Instrumentation.ActivityResult) findDeclaredMethod(this.root, "execStartActivity", Context.class, IBinder.class, IBinder.class, Fragment.class, Intent.class, Integer.TYPE).invoke(this.base, context, iBinder, iBinder2, fragment, intent, Integer.valueOf(i));
        } catch (InvocationTargetException e) {
            if (e.getCause() == null) {
                return null;
            }
            throw e.getCause();
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        } finally {
            this.avoidRecursive.finishCall(22);
        }
    }

    public Instrumentation.ActivityResult execStartActivity(Context context, IBinder iBinder, IBinder iBinder2, Activity activity, Intent intent, int i) throws Throwable {
        try {
            return this.avoidRecursive.beginCall(23) ? (Instrumentation.ActivityResult) findDeclaredMethod(this.base, "execStartActivity", Context.class, IBinder.class, IBinder.class, Activity.class, Intent.class, Integer.TYPE).invoke(this.base, context, iBinder, iBinder2, activity, intent, Integer.valueOf(i)) : (Instrumentation.ActivityResult) findDeclaredMethod(this.root, "execStartActivity", Context.class, IBinder.class, IBinder.class, Activity.class, Intent.class, Integer.TYPE).invoke(this.base, context, iBinder, iBinder2, activity, intent, Integer.valueOf(i));
        } catch (InvocationTargetException e) {
            if (e.getCause() == null) {
                return null;
            }
            throw e.getCause();
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        } finally {
            this.avoidRecursive.finishCall(23);
        }
    }

    public Instrumentation.ActivityResult execStartActivity(Context context, IBinder iBinder, IBinder iBinder2, Fragment fragment, Intent intent, int i, Bundle bundle) throws Throwable {
        try {
            return this.avoidRecursive.beginCall(24) ? (Instrumentation.ActivityResult) findDeclaredMethod(this.base, "execStartActivity", Context.class, IBinder.class, IBinder.class, Fragment.class, Intent.class, Integer.TYPE, Bundle.class).invoke(this.base, context, iBinder, iBinder2, fragment, intent, Integer.valueOf(i), bundle) : (Instrumentation.ActivityResult) findDeclaredMethod(this.root, "execStartActivity", Context.class, IBinder.class, IBinder.class, Fragment.class, Intent.class, Integer.TYPE, Bundle.class).invoke(this.base, context, iBinder, iBinder2, fragment, intent, Integer.valueOf(i), bundle);
        } catch (InvocationTargetException e) {
            if (e.getCause() == null) {
                return null;
            }
            throw e.getCause();
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        } finally {
            this.avoidRecursive.finishCall(24);
        }
    }

    @TargetApi(17)
    public Instrumentation.ActivityResult execStartActivity(Context context, IBinder iBinder, IBinder iBinder2, Activity activity, Intent intent, int i, Bundle bundle, UserHandle userHandle) throws Throwable {
        try {
            return this.avoidRecursive.beginCall(25) ? (Instrumentation.ActivityResult) findDeclaredMethod(this.base, "execStartActivity", Context.class, IBinder.class, IBinder.class, Activity.class, Intent.class, Integer.TYPE, Bundle.class, UserHandle.class).invoke(this.base, context, iBinder, iBinder2, activity, intent, Integer.valueOf(i), bundle, userHandle) : (Instrumentation.ActivityResult) findDeclaredMethod(this.root, "execStartActivity", Context.class, IBinder.class, IBinder.class, Activity.class, Intent.class, Integer.TYPE, Bundle.class, UserHandle.class).invoke(this.base, context, iBinder, iBinder2, activity, intent, Integer.valueOf(i), bundle, userHandle);
        } catch (InvocationTargetException e) {
            if (e.getCause() == null) {
                return null;
            }
            throw e.getCause();
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        } finally {
            this.avoidRecursive.finishCall(25);
        }
    }

    private static Method findDeclaredMethod(Object obj, String str, Class<?>... clsArr) throws NoSuchMethodException {
        for (Class<?> cls = obj.getClass(); cls != null; cls = cls.getSuperclass()) {
            try {
                Method declaredMethod = cls.getDeclaredMethod(str, clsArr);
                if (!declaredMethod.isAccessible()) {
                    declaredMethod.setAccessible(true);
                }
                return declaredMethod;
            } catch (NoSuchMethodException unused) {
            }
        }
        throw new NoSuchMethodException("Method " + str + " with parameters " + Arrays.asList(clsArr) + " not found in " + obj.getClass());
    }
}
