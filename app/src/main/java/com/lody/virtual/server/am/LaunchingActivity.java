package com.lody.virtual.server.am;

import android.content.ComponentName;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* renamed from: com.lody.virtual.server.am.LaunchingActivity */
/* loaded from: classes.dex */
public class LaunchingActivity {
    public ComponentName componentName;
    public List<PendingNewIntent> pendingNewIntents = new CopyOnWriteArrayList();

    public LaunchingActivity(ComponentName componentName) {
        this.componentName = componentName;
    }

    public boolean Match(ComponentName componentName) {
        ComponentName componentName2;
        if (componentName == null || (componentName2 = this.componentName) == null) {
            return false;
        }
        return componentName2.equals(componentName);
    }
}
