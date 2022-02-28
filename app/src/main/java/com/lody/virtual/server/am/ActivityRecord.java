package com.lody.virtual.server.am;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Binder;
import android.os.IBinder;

/* renamed from: com.lody.virtual.server.am.ActivityRecord */
/* loaded from: classes.dex */
class ActivityRecord extends Binder {
    public ComponentName component;
    public ActivityInfo info;
    public Intent intent;
    public boolean marked;
    public ProcessRecord process;
    public IBinder resultTo;
    public TaskRecord task;
    public IBinder token;
    public int userId;

    public ActivityRecord(Intent intent, ActivityInfo activityInfo, IBinder iBinder) {
        this.intent = intent;
        this.info = activityInfo;
        if (activityInfo.targetActivity != null) {
            this.component = new ComponentName(activityInfo.packageName, activityInfo.targetActivity);
        } else {
            this.component = new ComponentName(activityInfo.packageName, activityInfo.name);
        }
        this.resultTo = iBinder;
    }

    public void init(TaskRecord taskRecord, ProcessRecord processRecord, IBinder iBinder) {
        this.task = taskRecord;
        this.process = processRecord;
        this.token = iBinder;
    }

    public boolean isLaunching() {
        return this.process == null;
    }
}
