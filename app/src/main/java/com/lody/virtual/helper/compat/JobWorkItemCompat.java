package com.lody.virtual.helper.compat;

import android.annotation.TargetApi;
import android.app.job.JobWorkItem;
import android.content.Intent;
import com.lody.virtual.helper.utils.ComponentUtils;

@TargetApi(26)
/* loaded from: classes.dex */
public class JobWorkItemCompat {
    public static JobWorkItem redirect(JobWorkItem jobWorkItem, String str) {
        if (jobWorkItem == null) {
            return null;
        }
        Intent call = p110z1.JobWorkItem.getIntent.call(jobWorkItem, new Object[0]);
        if (call.hasExtra("_VA_|_intent_")) {
            return jobWorkItem;
        }
        JobWorkItem jobWorkItem2 = (JobWorkItem) p110z1.JobWorkItem.ctor.newInstance(ComponentUtils.redirectIntentSender(4, str, call));
        p110z1.JobWorkItem.mWorkId.set(jobWorkItem2, p110z1.JobWorkItem.mWorkId.get(jobWorkItem));
        p110z1.JobWorkItem.mGrants.set(jobWorkItem2, p110z1.JobWorkItem.mGrants.get(jobWorkItem));
        p110z1.JobWorkItem.mDeliveryCount.set(jobWorkItem2, p110z1.JobWorkItem.mDeliveryCount.get(jobWorkItem));
        return jobWorkItem2;
    }
}
