package com.lody.virtual.server.am;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.env.Constants;
import com.lody.virtual.client.ipc.ServiceManagerNative;
import com.lody.virtual.client.stub.StubManifest;
import com.lody.virtual.helper.collection.SparseArray;
import com.lody.virtual.helper.compat.ObjectsCompat;
import com.lody.virtual.helper.utils.ArrayUtils;
import com.lody.virtual.helper.utils.ClassUtils;
import com.lody.virtual.helper.utils.ComponentUtils;
import com.lody.virtual.remote.AppTaskInfo;
import com.lody.virtual.remote.StubActivityRecord;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import p110z1.ActivityManagerNative;
import p110z1.IActivityManager;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.lody.virtual.server.am.ActivityStack */
/* loaded from: classes.dex */
public class ActivityStack {
    private final VActivityManagerService mService;
    private final SparseArray<TaskRecord> mHistory = new SparseArray<>();
    private final List<ActivityRecord> mLaunchingActivities = new ArrayList();
    private final Map<ActivityRecord, LaunchingActivity> pendingNewIntents = new ConcurrentHashMap();
    private final ActivityManager mAM = (ActivityManager) VirtualCore.get().getContext().getSystemService(ServiceManagerNative.ACTIVITY);

    /* JADX INFO: Access modifiers changed from: package-private */
    public ActivityStack(VActivityManagerService vActivityManagerService) {
        this.mService = vActivityManagerService;
    }

    private static void removeFlags(Intent intent, int i) {
        intent.setFlags((~i) & intent.getFlags());
    }

    private static boolean containFlags(Intent intent, int i) {
        return (intent.getFlags() & i) != 0;
    }

    private void deliverNewIntentLocked(int i, ActivityRecord activityRecord, ActivityRecord activityRecord2, Intent intent) {
        if (activityRecord2 != null) {
            String callingPackage = getCallingPackage(i, activityRecord);
            if (callingPackage == null) {
                callingPackage = "android";
            }
            try {
                activityRecord2.process.client.scheduleNewIntent(callingPackage, activityRecord2.token, intent);
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (NullPointerException e2) {
                e2.printStackTrace();
            }
        }
    }

    private TaskRecord findTaskByAffinityLocked(int i, String str) {
        for (int i2 = 0; i2 < this.mHistory.size(); i2++) {
            TaskRecord valueAt = this.mHistory.valueAt(i2);
            if (i == valueAt.userId && str.equals(valueAt.affinity)) {
                return valueAt;
            }
        }
        return null;
    }

    private TaskRecord findTaskByIntentLocked(int i, Intent intent) {
        for (int i2 = 0; i2 < this.mHistory.size(); i2++) {
            TaskRecord valueAt = this.mHistory.valueAt(i2);
            if (i == valueAt.userId && valueAt.taskRoot != null && ObjectsCompat.equals(intent.getComponent(), valueAt.taskRoot.getComponent())) {
                return valueAt;
            }
        }
        return null;
    }

    private ActivityRecord findActivityByToken(int i, IBinder iBinder) {
        ActivityRecord activityRecord = null;
        if (iBinder != null) {
            for (int i2 = 0; i2 < this.mHistory.size(); i2++) {
                TaskRecord valueAt = this.mHistory.valueAt(i2);
                if (valueAt.userId == i) {
                    synchronized (valueAt.activities) {
                        for (ActivityRecord activityRecord2 : valueAt.activities) {
                            if (activityRecord2.token == iBinder) {
                                activityRecord = activityRecord2;
                            }
                        }
                    }
                }
            }
        }
        return activityRecord;
    }

    private void optimizeTasksLocked() {
        List<ActivityManager.RecentTaskInfo> recentTasksEx = VirtualCore.get().getRecentTasksEx(Integer.MAX_VALUE, 3);
        int size = this.mHistory.size();
        while (true) {
            int i = size - 1;
            if (size > 0) {
                TaskRecord valueAt = this.mHistory.valueAt(i);
                ListIterator<ActivityManager.RecentTaskInfo> listIterator = recentTasksEx.listIterator();
                boolean z = false;
                while (true) {
                    if (listIterator.hasNext()) {
                        if (listIterator.next().id == valueAt.taskId) {
                            z = true;
                            listIterator.remove();
                            break;
                        }
                    } else {
                        break;
                    }
                }
                if (!z) {
                    this.mHistory.removeAt(i);
                }
                size = i;
            } else {
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int startActivitiesLocked(int i, Intent[] intentArr, ActivityInfo[] activityInfoArr, String[] strArr, IBinder iBinder, Bundle bundle, int i2) {
        for (int i3 = 0; i3 < intentArr.length; i3++) {
            startActivityLocked(i, intentArr[i3], activityInfoArr[i3], iBinder, bundle, null, 0, i2);
        }
        return 0;
    }

    private boolean isAllowUseSourceTask(ActivityRecord activityRecord, ActivityInfo activityInfo) {
        return (activityRecord == null || activityRecord.info.launchMode == 3) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public int startActivityLocked(int i, Intent intent, ActivityInfo activityInfo, IBinder iBinder, Bundle bundle, String str, int i2, int i3) {
        TaskRecord taskRecord;
        ActivityRecord activityRecord;
        boolean z;
        boolean z2;
        ActivityRecord activityRecord2;
        IBinder iBinder2;
        int i4;
        ActivityRecord activityRecord3;
        boolean z3;
        synchronized (this.mHistory) {
            optimizeTasksLocked();
        }
        IBinder iBinder3 = iBinder;
        ActivityRecord findActivityByToken = findActivityByToken(i, iBinder3);
        if (findActivityByToken == null) {
            iBinder3 = null;
        }
        String taskAffinity = ComponentUtils.getTaskAffinity(activityInfo);
        boolean containFlags = containFlags(intent, 268435456);
        boolean containFlags2 = containFlags(intent, 67108864);
        boolean containFlags3 = containFlags(intent, 32768);
        boolean z4 = containFlags && containFlags(intent, 134217728);
        boolean containFlags4 = containFlags(intent, 131072);
        boolean containFlags5 = containFlags(intent, 536870912);
        int i5 = 8388608;
        if ((activityInfo.flags & 32) == 0 && !containFlags(intent, 8388608)) {
            i5 = 0;
        }
        boolean z5 = containFlags2 || containFlags5 || containFlags3;
        if (!containFlags) {
            containFlags3 = false;
        }
        TaskRecord taskRecord2 = findActivityByToken != null ? findActivityByToken.task : null;
        if (!z4) {
            switch (activityInfo.launchMode) {
                case 0:
                case 1:
                case 2:
                    if (!containFlags && taskRecord2 != null) {
                        if (isAllowUseSourceTask(findActivityByToken, activityInfo)) {
                            taskRecord = taskRecord2;
                            break;
                        }
                    } else {
                        taskRecord = findTaskByAffinityLocked(i, taskAffinity);
                        break;
                    }
                    break;
                case 3:
                    taskRecord = findTaskByAffinityLocked(i, taskAffinity);
                    break;
            }
            if (taskRecord != null || taskRecord.isFinishing()) {
                return startActivityInNewTaskLocked(i5, i, intent, activityInfo, bundle, i3);
            }
            this.mAM.moveTaskToFront(taskRecord.taskId, 0);
            if (!z5 && ComponentUtils.intentFilterEquals(taskRecord.taskRoot, intent) && taskRecord.taskRoot.getFlags() == intent.getFlags()) {
                return 0;
            }
            ComponentName componentName = ComponentUtils.toComponentName(activityInfo);
            if (activityInfo.launchMode == 3) {
                synchronized (taskRecord.activities) {
                    Iterator<ActivityRecord> it = taskRecord.activities.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            activityRecord = it.next();
                            if (!activityRecord.component.equals(componentName)) {
                                it = it;
                            }
                        } else {
                            activityRecord = null;
                        }
                    }
                }
            } else {
                activityRecord = null;
            }
            if (activityInfo.launchMode == 2 || containFlags2) {
                synchronized (taskRecord.activities) {
                    int size = taskRecord.activities.size();
                    while (true) {
                        int i6 = size - 1;
                        if (size > 0) {
                            activityRecord3 = taskRecord.activities.get(i6);
                            i4 = i6;
                            if (activityRecord3.marked || !activityRecord3.component.equals(componentName)) {
                                activityRecord = activityRecord;
                                size = i4;
                            } else {
                                z3 = true;
                            }
                        } else {
                            i4 = i6;
                            activityRecord3 = activityRecord;
                            z3 = false;
                        }
                    }
                    if (z3) {
                        z = z3;
                        while (true) {
                            int i7 = i4 + 1;
                            if (i4 < taskRecord.activities.size() - 1) {
                                taskRecord.activities.get(i7).marked = true;
                                i4 = i7;
                                i5 = i5;
                                iBinder3 = iBinder3;
                            } else if (containFlags2 && activityInfo.launchMode == 0) {
                                activityRecord3.marked = true;
                                activityRecord = null;
                            }
                        }
                    } else {
                        iBinder3 = iBinder3;
                        z = z3;
                        i5 = i5;
                    }
                    activityRecord = activityRecord3;
                }
                z2 = true;
            } else {
                iBinder3 = iBinder3;
                i5 = i5;
                z2 = false;
                z = false;
            }
            if (activityInfo.launchMode == 1 || containFlags5) {
                ActivityRecord topActivityRecord = taskRecord.getTopActivityRecord();
                if (topActivityRecord == null || topActivityRecord.marked || !topActivityRecord.component.equals(componentName)) {
                    synchronized (this.mLaunchingActivities) {
                        Iterator<ActivityRecord> it2 = this.mLaunchingActivities.iterator();
                        while (true) {
                            if (it2.hasNext()) {
                                ActivityRecord next = it2.next();
                                if (!next.marked && next.component.equals(componentName)) {
                                    activityRecord2 = next;
                                }
                            } else {
                                activityRecord2 = null;
                            }
                        }
                    }
                    z2 = true;
                } else {
                    activityRecord = topActivityRecord;
                    activityRecord2 = null;
                    z2 = true;
                }
            } else {
                activityRecord2 = null;
            }
            if (containFlags4) {
                ActivityRecord topActivityRecord2 = taskRecord.getTopActivityRecord();
                if (topActivityRecord2.component.equals(componentName)) {
                    activityRecord = topActivityRecord2;
                } else {
                    synchronized (taskRecord.activities) {
                        int size2 = taskRecord.activities.size();
                        while (true) {
                            int i8 = size2 - 1;
                            if (size2 > 0) {
                                ActivityRecord activityRecord4 = taskRecord.activities.get(i8);
                                if (!activityRecord4.component.equals(componentName)) {
                                    size2 = i8;
                                } else if (z2) {
                                    activityRecord = activityRecord4;
                                } else {
                                    activityRecord4.marked = true;
                                    z = true;
                                }
                            }
                        }
                    }
                }
            }
            if (containFlags3) {
                synchronized (taskRecord.activities) {
                    for (ActivityRecord activityRecord5 : taskRecord.activities) {
                        activityRecord5.marked = true;
                    }
                }
                z = true;
            }
            if (z) {
                finishMarkedActivity();
            }
            if (activityRecord != null) {
                deliverNewIntentLocked(i, findActivityByToken, activityRecord, intent);
                if (!activityRecord.marked) {
                    return 0;
                }
                iBinder2 = iBinder3;
            } else if (activityRecord2 != null) {
                synchronized (this.mLaunchingActivities) {
                    LaunchingActivity launchingActivity = this.pendingNewIntents.get(findActivityByToken);
                    if (launchingActivity == null) {
                        launchingActivity = new LaunchingActivity(activityRecord2.component);
                    }
                    if (findActivityByToken == null) {
                        findActivityByToken = activityRecord2;
                    }
                    launchingActivity.pendingNewIntents.add(new PendingNewIntent(i, findActivityByToken, intent));
                }
                return 0;
            } else {
                iBinder2 = iBinder3;
            }
            ActivityRecord newActivityRecord = newActivityRecord(intent, activityInfo, iBinder2);
            Intent startActivityProcess = startActivityProcess(i, newActivityRecord, intent, activityInfo, i3);
            if (startActivityProcess != null) {
                startActivityProcess.addFlags(i5);
                if (taskRecord2 != taskRecord) {
                    findActivityByToken = taskRecord.getTopActivityRecord(true);
                }
                startActivityFromSourceTask(findActivityByToken.process, findActivityByToken.token, startActivityProcess, str, i2, bundle);
                return 0;
            }
            synchronized (this.mLaunchingActivities) {
                if (this.mLaunchingActivities.remove(newActivityRecord)) {
                    this.pendingNewIntents.remove(newActivityRecord);
                }
            }
            return -1;
        }
        taskRecord = null;
        if (taskRecord != null) {
        }
        return startActivityInNewTaskLocked(i5, i, intent, activityInfo, bundle, i3);
    }

    private ActivityRecord newActivityRecord(Intent intent, ActivityInfo activityInfo, IBinder iBinder) {
        ActivityRecord activityRecord = new ActivityRecord(intent, activityInfo, iBinder);
        synchronized (this.mLaunchingActivities) {
            this.mLaunchingActivities.add(activityRecord);
        }
        return activityRecord;
    }

    private int startActivityInNewTaskLocked(int i, int i2, Intent intent, ActivityInfo activityInfo, Bundle bundle, int i3) {
        boolean z;
        ActivityRecord newActivityRecord = newActivityRecord(intent, activityInfo, null);
        Intent startActivityProcess = startActivityProcess(i2, newActivityRecord, intent, activityInfo, i3);
        if (startActivityProcess != null) {
            startActivityProcess.addFlags(i);
            startActivityProcess.addFlags(268435456);
            startActivityProcess.addFlags(134217728);
            startActivityProcess.addFlags(2097152);
            if (Build.VERSION.SDK_INT < 21) {
                startActivityProcess.addFlags(524288);
            } else {
                startActivityProcess.addFlags(524288);
            }
            try {
                z = intent.getBooleanExtra("_VA_|no_animation", false);
            } catch (Throwable unused) {
                z = false;
            }
            if (z) {
                startActivityProcess.addFlags(65536);
            }
            if (bundle == null || Build.VERSION.SDK_INT < 16) {
                VirtualCore.get().getContext().startActivity(startActivityProcess);
            } else {
                VirtualCore.get().getContext().startActivity(startActivityProcess, bundle);
            }
            return 0;
        }
        this.mLaunchingActivities.remove(newActivityRecord);
        return -1;
    }

    private void finishMarkedActivity() {
        synchronized (this.mHistory) {
            int size = this.mHistory.size();
            while (true) {
                int i = size - 1;
                if (size > 0) {
                    TaskRecord valueAt = this.mHistory.valueAt(i);
                    synchronized (valueAt.activities) {
                        for (ActivityRecord activityRecord : valueAt.activities) {
                            if (activityRecord.marked) {
                                try {
                                    activityRecord.process.client.finishActivity(activityRecord.token);
                                } catch (RemoteException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                    size = i;
                }
            }
        }
    }

    public boolean finishActivityAffinity(int i, IBinder iBinder) {
        synchronized (this.mHistory) {
            ActivityRecord findActivityByToken = findActivityByToken(i, iBinder);
            if (findActivityByToken == null) {
                return false;
            }
            String taskAffinity = ComponentUtils.getTaskAffinity(findActivityByToken.info);
            synchronized (findActivityByToken.task.activities) {
                for (int indexOf = findActivityByToken.task.activities.indexOf(findActivityByToken); indexOf >= 0; indexOf--) {
                    ActivityRecord activityRecord = findActivityByToken.task.activities.get(indexOf);
                    if (!ComponentUtils.getTaskAffinity(activityRecord.info).equals(taskAffinity)) {
                        break;
                    }
                    activityRecord.marked = true;
                }
            }
            finishMarkedActivity();
            return false;
        }
    }

    private void startActivityFromSourceTask(ProcessRecord processRecord, IBinder iBinder, Intent intent, String str, int i, Bundle bundle) {
        realStartActivityLocked(processRecord.appThread, iBinder, intent, str, i, bundle);
    }

    private void realStartActivityLocked(IInterface iInterface, IBinder iBinder, Intent intent, String str, int i, Bundle bundle) {
        Class<?>[] paramList = IActivityManager.startActivity.paramList();
        Object[] objArr = new Object[paramList.length];
        objArr[0] = iInterface;
        int protoIndexOf = ArrayUtils.protoIndexOf(paramList, Intent.class);
        int protoIndexOf2 = ArrayUtils.protoIndexOf(paramList, IBinder.class, 2);
        int protoIndexOf3 = ArrayUtils.protoIndexOf(paramList, Bundle.class);
        int i2 = protoIndexOf + 1;
        objArr[protoIndexOf] = intent;
        objArr[protoIndexOf2] = iBinder;
        objArr[protoIndexOf2 + 1] = str;
        objArr[protoIndexOf2 + 2] = Integer.valueOf(i);
        if (protoIndexOf3 != -1) {
            objArr[protoIndexOf3] = bundle;
        }
        objArr[i2] = intent.getType();
        if (Build.VERSION.SDK_INT >= 18) {
            objArr[protoIndexOf - 1] = VirtualCore.get().getHostPkg();
        }
        ClassUtils.fixArgs(paramList, objArr);
        try {
            IActivityManager.startActivity.call(ActivityManagerNative.getDefault.call(new Object[0]), objArr);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0051 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x005d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String fetchStubActivity(int r8, android.content.pm.ActivityInfo r9) {
        /*
            r7 = this;
            r0 = 0
            mirror.l<int[]> r1 = p110z1.R_Hide.C5192d.Window     // Catch: Throwable -> 0x0048
            java.lang.Object r1 = r1.get()     // Catch: Throwable -> 0x0048
            int[] r1 = (int[]) r1     // Catch: Throwable -> 0x0048
            mirror.j r2 = p110z1.R_Hide.C5192d.Window_windowIsTranslucent     // Catch: Throwable -> 0x0048
            int r2 = r2.get()     // Catch: Throwable -> 0x0048
            mirror.j r3 = p110z1.R_Hide.C5192d.Window_windowIsFloating     // Catch: Throwable -> 0x0048
            int r3 = r3.get()     // Catch: Throwable -> 0x0048
            mirror.j r4 = p110z1.R_Hide.C5192d.Window_windowShowWallpaper     // Catch: Throwable -> 0x0048
            int r4 = r4.get()     // Catch: Throwable -> 0x0048
            com.lody.virtual.server.am.AttributeCache r5 = com.lody.virtual.server.am.AttributeCache.instance()     // Catch: Throwable -> 0x0048
            java.lang.String r6 = r9.packageName     // Catch: Throwable -> 0x0048
            int r9 = r9.theme     // Catch: Throwable -> 0x0048
            com.lody.virtual.server.am.AttributeCache$Entry r9 = r5.get(r6, r9, r1)     // Catch: Throwable -> 0x0048
            if (r9 == 0) goto L_0x0044
            android.content.res.TypedArray r1 = r9.array     // Catch: Throwable -> 0x0048
            if (r1 == 0) goto L_0x0044
            android.content.res.TypedArray r1 = r9.array     // Catch: Throwable -> 0x0048
            boolean r1 = r1.getBoolean(r4, r0)     // Catch: Throwable -> 0x0048
            android.content.res.TypedArray r4 = r9.array     // Catch: Throwable -> 0x0042
            boolean r2 = r4.getBoolean(r2, r0)     // Catch: Throwable -> 0x0042
            android.content.res.TypedArray r9 = r9.array     // Catch: Throwable -> 0x0040
            boolean r9 = r9.getBoolean(r3, r0)     // Catch: Throwable -> 0x0040
            goto L_0x004f
        L_0x0040:
            r9 = move-exception
            goto L_0x004b
        L_0x0042:
            r9 = move-exception
            goto L_0x004a
        L_0x0044:
            r9 = 0
            r1 = 0
            r2 = 0
            goto L_0x004f
        L_0x0048:
            r9 = move-exception
            r1 = 0
        L_0x004a:
            r2 = 0
        L_0x004b:
            r9.printStackTrace()
            r9 = 0
        L_0x004f:
            if (r9 != 0) goto L_0x0055
            if (r2 != 0) goto L_0x0055
            if (r1 == 0) goto L_0x0056
        L_0x0055:
            r0 = 1
        L_0x0056:
            if (r0 == 0) goto L_0x005d
            java.lang.String r8 = com.lody.virtual.client.stub.StubManifest.getStubDialogName(r8)
            return r8
        L_0x005d:
            java.lang.String r8 = com.lody.virtual.client.stub.StubManifest.getStubActivityName(r8)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lody.virtual.server.am.ActivityStack.fetchStubActivity(int, android.content.pm.ActivityInfo):java.lang.String");
    }

    private Intent startActivityProcess(int i, ActivityRecord activityRecord, Intent intent, ActivityInfo activityInfo, int i2) {
        ProcessRecord startProcessIfNeedLocked = this.mService.startProcessIfNeedLocked(activityInfo.processName, i, activityInfo.packageName, -1, i2);
        if (startProcessIfNeedLocked == null) {
            return null;
        }
        return getStartStubActivityIntentInner(intent, startProcessIfNeedLocked.is64bit, startProcessIfNeedLocked.vpid, i, activityRecord, activityInfo);
    }

    private Intent getStartStubActivityIntentInner(Intent intent, boolean z, int i, int i2, ActivityRecord activityRecord, ActivityInfo activityInfo) {
        Intent intent2 = new Intent(intent);
        Intent intent3 = new Intent();
        intent3.setClassName(StubManifest.getStubPackageName(z), fetchStubActivity(i, activityInfo));
        ComponentName component = intent2.getComponent();
        if (component == null) {
            component = ComponentUtils.toComponentName(activityInfo);
        }
        intent3.setType(component.flattenToString());
        new StubActivityRecord(intent2, activityInfo, i2, activityRecord).saveToIntent(intent3);
        return intent3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onActivityCreated(ProcessRecord processRecord, IBinder iBinder, int i, ActivityRecord activityRecord) {
        LaunchingActivity remove;
        synchronized (this.mLaunchingActivities) {
            remove = this.mLaunchingActivities.remove(activityRecord) ? this.pendingNewIntents.remove(activityRecord) : null;
        }
        synchronized (this.mHistory) {
            optimizeTasksLocked();
            TaskRecord taskRecord = this.mHistory.get(i);
            if (taskRecord == null) {
                taskRecord = new TaskRecord(i, processRecord.userId, ComponentUtils.getTaskAffinity(activityRecord.info), activityRecord.intent);
                this.mHistory.put(i, taskRecord);
                Intent intent = new Intent(Constants.ACTION_NEW_TASK_CREATED);
                intent.putExtra(Constants.EXTRA_USER_HANDLE, activityRecord.userId);
                intent.putExtra(Constants.EXTRA_PACKAGE_NAME, activityRecord.info.packageName);
                VirtualCore.get().getContext().sendBroadcast(intent);
            }
            activityRecord.init(taskRecord, processRecord, iBinder);
            synchronized (taskRecord.activities) {
                taskRecord.activities.add(activityRecord);
            }
        }
        if (remove != null && remove.Match(activityRecord.component)) {
            for (PendingNewIntent pendingNewIntent : remove.pendingNewIntents) {
                deliverNewIntentLocked(pendingNewIntent.userId, pendingNewIntent.sourceRecord, activityRecord, pendingNewIntent.intent);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onActivityResumed(int i, IBinder iBinder) {
        synchronized (this.mHistory) {
            optimizeTasksLocked();
            ActivityRecord findActivityByToken = findActivityByToken(i, iBinder);
            if (findActivityByToken != null) {
                synchronized (findActivityByToken.task.activities) {
                    findActivityByToken.task.activities.remove(findActivityByToken);
                    findActivityByToken.task.activities.add(findActivityByToken);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onActivityFinish(int i, IBinder iBinder) {
        synchronized (this.mHistory) {
            ActivityRecord findActivityByToken = findActivityByToken(i, iBinder);
            if (findActivityByToken != null) {
                findActivityByToken.marked = true;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ActivityRecord onActivityDestroyed(int i, IBinder iBinder) {
        ActivityRecord findActivityByToken;
        synchronized (this.mHistory) {
            optimizeTasksLocked();
            findActivityByToken = findActivityByToken(i, iBinder);
            if (findActivityByToken != null) {
                findActivityByToken.marked = true;
                synchronized (findActivityByToken.task.activities) {
                    findActivityByToken.task.activities.remove(findActivityByToken);
                }
            }
        }
        return findActivityByToken;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void processDied(ProcessRecord processRecord) {
        synchronized (this.mHistory) {
            optimizeTasksLocked();
            int size = this.mHistory.size();
            while (true) {
                int i = size - 1;
                if (size > 0) {
                    TaskRecord valueAt = this.mHistory.valueAt(i);
                    synchronized (valueAt.activities) {
                        Iterator<ActivityRecord> it = valueAt.activities.iterator();
                        while (it.hasNext()) {
                            if (it.next().process.pid == processRecord.pid) {
                                it.remove();
                                if (valueAt.activities.isEmpty()) {
                                    this.mHistory.remove(valueAt.taskId);
                                }
                            }
                        }
                    }
                    size = i;
                }
            }
        }
    }

    public void finishAllActivities(ProcessRecord processRecord) {
        synchronized (this.mHistory) {
            int size = this.mHistory.size();
            while (true) {
                int i = size - 1;
                if (size > 0) {
                    TaskRecord valueAt = this.mHistory.valueAt(i);
                    synchronized (valueAt.activities) {
                        for (ActivityRecord activityRecord : valueAt.activities) {
                            if (activityRecord.process.pid == processRecord.pid) {
                                activityRecord.marked = true;
                            }
                        }
                    }
                    size = i;
                }
            }
        }
        finishMarkedActivity();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getPackageForToken(int i, IBinder iBinder) {
        synchronized (this.mHistory) {
            ActivityRecord findActivityByToken = findActivityByToken(i, iBinder);
            if (findActivityByToken == null) {
                return null;
            }
            return findActivityByToken.info.packageName;
        }
    }

    private ActivityRecord getCallingRecordLocked(int i, IBinder iBinder) {
        ActivityRecord findActivityByToken = findActivityByToken(i, iBinder);
        if (findActivityByToken == null) {
            return null;
        }
        return findActivityByToken(i, findActivityByToken.resultTo);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ComponentName getCallingActivity(int i, IBinder iBinder) {
        ActivityRecord callingRecordLocked = getCallingRecordLocked(i, iBinder);
        if (callingRecordLocked != null) {
            return callingRecordLocked.intent.getComponent();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getCallingPackage(int i, IBinder iBinder) {
        ActivityRecord callingRecordLocked = getCallingRecordLocked(i, iBinder);
        if (callingRecordLocked != null) {
            return callingRecordLocked.info.packageName;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AppTaskInfo getTaskInfo(int i) {
        synchronized (this.mHistory) {
            TaskRecord taskRecord = this.mHistory.get(i);
            if (taskRecord == null) {
                return null;
            }
            return taskRecord.getAppTaskInfo();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ComponentName getActivityClassForToken(int i, IBinder iBinder) {
        synchronized (this.mHistory) {
            ActivityRecord findActivityByToken = findActivityByToken(i, iBinder);
            if (findActivityByToken == null) {
                return null;
            }
            return findActivityByToken.component;
        }
    }
}
