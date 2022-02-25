package com.lody.virtual.server.p062am;

import android.content.ComponentName;
import android.content.Intent;
import com.lody.virtual.remote.AppTaskInfo;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.lody.virtual.server.am.TaskRecord */
/* loaded from: classes.dex */
public class TaskRecord {
    public final List<ActivityRecord> activities = new ArrayList();
    public String affinity;
    public int taskId;
    public Intent taskRoot;
    public int userId;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TaskRecord(int i, int i2, String str, Intent intent) {
        this.taskId = i;
        this.userId = i2;
        this.affinity = str;
        this.taskRoot = intent;
    }

    ActivityRecord getRootActivityRecord() {
        synchronized (this.activities) {
            for (int i = 0; i < this.activities.size(); i++) {
                ActivityRecord activityRecord = this.activities.get(i);
                if (!activityRecord.marked) {
                    return activityRecord;
                }
            }
            return null;
        }
    }

    public ActivityRecord getTopActivityRecord() {
        return getTopActivityRecord(false);
    }

    public ActivityRecord getTopActivityRecord(boolean z) {
        synchronized (this.activities) {
            if (this.activities.isEmpty()) {
                return null;
            }
            for (int size = this.activities.size() - 1; size >= 0; size--) {
                ActivityRecord activityRecord = this.activities.get(size);
                if (!z && activityRecord.marked) {
                }
                return activityRecord;
            }
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AppTaskInfo getAppTaskInfo() {
        int size = this.activities.size();
        if (size <= 0) {
            return null;
        }
        ComponentName componentName = this.activities.get(size - 1).component;
        int i = this.taskId;
        Intent intent = this.taskRoot;
        return new AppTaskInfo(i, intent, intent.getComponent(), componentName);
    }

    public boolean isFinishing() {
        boolean z = true;
        for (ActivityRecord activityRecord : this.activities) {
            if (!activityRecord.marked) {
                z = false;
            }
        }
        return z;
    }

    public void finish() {
        synchronized (this.activities) {
            for (ActivityRecord activityRecord : this.activities) {
                activityRecord.marked = true;
            }
        }
    }
}
