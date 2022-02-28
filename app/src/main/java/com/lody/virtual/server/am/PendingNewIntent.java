package com.lody.virtual.server.am;

import android.content.Intent;

/* renamed from: com.lody.virtual.server.am.PendingNewIntent */
/* loaded from: classes.dex */
public class PendingNewIntent {
    public Intent intent;
    public ActivityRecord sourceRecord;
    public int userId;

    public PendingNewIntent(int i, ActivityRecord activityRecord, Intent intent) {
        this.userId = i;
        this.sourceRecord = activityRecord;
        this.intent = intent;
    }
}
