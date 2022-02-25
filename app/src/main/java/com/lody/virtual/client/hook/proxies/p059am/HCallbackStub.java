package com.lody.virtual.client.hook.proxies.p059am;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import com.lody.virtual.client.VClient;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.interfaces.IInjector;
import com.lody.virtual.client.ipc.VActivityManager;
import com.lody.virtual.helper.AvoidRecursive;
import com.lody.virtual.helper.compat.BuildCompat;
import com.lody.virtual.helper.utils.VLog;
import com.lody.virtual.remote.StubActivityRecord;
import java.util.List;
import p110z1.ActivityManagerNative;
import p110z1.ActivityThread;
import p110z1.ClientTransaction;
import p110z1.ClientTransactionHandler;
import p110z1.IActivityManager;
import p110z1.LaunchActivityItem;
import p110z1.TopResumedActivityChangeItem;

/* renamed from: com.lody.virtual.client.hook.proxies.am.HCallbackStub */
/* loaded from: classes.dex */
public class HCallbackStub implements Handler.Callback, IInjector {
    private static final int EXECUTE_TRANSACTION;
    private static final int LAUNCH_ACTIVITY;
    private static final int SCHEDULE_CRASH = ActivityThread.C5108d.SCHEDULE_CRASH.get();
    private static final String TAG;
    private static final HCallbackStub sCallback;
    private final AvoidRecursive mAvoidRecurisve = new AvoidRecursive();
    private Handler.Callback otherCallback;

    static {
        int i = -1;
        LAUNCH_ACTIVITY = BuildCompat.isPie() ? -1 : ActivityThread.C5108d.LAUNCH_ACTIVITY.get();
        if (BuildCompat.isPie()) {
            i = ActivityThread.C5108d.EXECUTE_TRANSACTION.get();
        }
        EXECUTE_TRANSACTION = i;
        TAG = HCallbackStub.class.getSimpleName();
        sCallback = new HCallbackStub();
    }

    private HCallbackStub() {
    }

    public static HCallbackStub getDefault() {
        return sCallback;
    }

    private static Handler getH() {
        return ActivityThread.f21100mH.get(VirtualCore.mainThread());
    }

    private static Handler.Callback getHCallback() {
        try {
            return p110z1.Handler.mCallback.get(getH());
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        if (!this.mAvoidRecurisve.beginCall()) {
            return false;
        }
        try {
            if (LAUNCH_ACTIVITY == message.what) {
                if (!handleLaunchActivity(message, message.obj)) {
                    return true;
                }
            } else if (!BuildCompat.isPie() || EXECUTE_TRANSACTION != message.what) {
                if (SCHEDULE_CRASH == message.what) {
                    new RemoteException((String) message.obj).printStackTrace();
                    return true;
                }
            } else if (!handleExecuteTransaction(message)) {
                return true;
            }
            if (this.otherCallback != null) {
                return this.otherCallback.handleMessage(message);
            }
            return false;
        } finally {
            this.mAvoidRecurisve.finishCall();
        }
    }

    private boolean handleExecuteTransaction(Message message) {
        List<Object> list;
        Object obj = message.obj;
        Object call = ClientTransactionHandler.getActivityClient.call(VirtualCore.mainThread(), ClientTransaction.mActivityToken.get(obj));
        if (call == null) {
            List<Object> list2 = ClientTransaction.mActivityCallbacks.get(obj);
            if (list2 == null || list2.isEmpty()) {
                return true;
            }
            Object obj2 = list2.get(0);
            if (obj2.getClass() != LaunchActivityItem.TYPE) {
                return true;
            }
            return handleLaunchActivity(message, obj2);
        }
        if (BuildCompat.isQ() && (list = ClientTransaction.mActivityCallbacks.get(obj)) != null && !list.isEmpty()) {
            Object obj3 = list.get(0);
            if (obj3.getClass() == TopResumedActivityChangeItem.TYPE && TopResumedActivityChangeItem.mOnTop.get(obj3) == ActivityThread.C5105a.isTopResumedActivity.get(call)) {
                Log.e("HCallbackStub", "Activity top position already set to onTop=" + TopResumedActivityChangeItem.mOnTop.get(obj3));
                return false;
            }
        }
        return true;
    }

    private boolean handleLaunchActivity(Message message, Object obj) {
        Intent intent;
        IBinder iBinder;
        if (BuildCompat.isPie()) {
            intent = LaunchActivityItem.mIntent.get(obj);
        } else {
            intent = ActivityThread.C5105a.intent.get(obj);
        }
        StubActivityRecord stubActivityRecord = new StubActivityRecord(intent);
        if (stubActivityRecord.intent == null) {
            return true;
        }
        Intent intent2 = stubActivityRecord.intent;
        if (BuildCompat.isPie()) {
            iBinder = ClientTransaction.mActivityToken.get(message.obj);
        } else {
            iBinder = ActivityThread.C5105a.token.get(obj);
        }
        ActivityInfo activityInfo = stubActivityRecord.info;
        if (activityInfo == null) {
            return true;
        }
        if (VClient.get().getClientConfig() == null) {
            if (VirtualCore.get().getInstalledAppInfo(activityInfo.packageName, 0) == null) {
                return true;
            }
            VActivityManager.get().processRestarted(activityInfo.packageName, activityInfo.processName, stubActivityRecord.userId);
            getH().sendMessageAtFrontOfQueue(Message.obtain(message));
            return false;
        } else if (!VClient.get().isAppRunning()) {
            VClient.get().bindApplication(activityInfo.packageName, activityInfo.processName);
            getH().sendMessageAtFrontOfQueue(Message.obtain(message));
            return false;
        } else {
            int intValue = IActivityManager.getTaskForActivity.call(ActivityManagerNative.getDefault.call(new Object[0]), iBinder, false).intValue();
            if (activityInfo.screenOrientation != -1) {
                try {
                    IActivityManager.setRequestedOrientation.call(ActivityManagerNative.getDefault.call(new Object[0]), iBinder, Integer.valueOf(activityInfo.screenOrientation));
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
            VActivityManager.get().onActivityCreate(stubActivityRecord.virtualToken, iBinder, intValue);
            intent2.setExtrasClassLoader(VClient.get().getClassLoader(activityInfo.applicationInfo));
            if (BuildCompat.isPie()) {
                LaunchActivityItem.mIntent.set(obj, intent2);
                LaunchActivityItem.mInfo.set(obj, activityInfo);
            } else {
                ActivityThread.C5105a.intent.set(obj, intent2);
                ActivityThread.C5105a.activityInfo.set(obj, activityInfo);
            }
            return true;
        }
    }

    @Override // com.lody.virtual.client.interfaces.IInjector
    public void inject() {
        this.otherCallback = getHCallback();
        p110z1.Handler.mCallback.set(getH(), this);
    }

    @Override // com.lody.virtual.client.interfaces.IInjector
    public boolean isEnvBad() {
        Handler.Callback hCallback = getHCallback();
        boolean z = hCallback != this;
        if (hCallback != null && z) {
            String str = TAG;
            VLog.m18993d(str, "HCallback has bad, other callback = " + hCallback, new Object[0]);
        }
        return z;
    }
}
