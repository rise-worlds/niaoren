package com.lody.virtual.client.badger;

import android.content.ComponentName;
import android.content.Intent;
import com.lody.virtual.remote.BadgerInfo;

/* loaded from: classes.dex */
public abstract class BroadcastBadger2 implements IBadger {

    /* loaded from: classes.dex */
    static class NewHtcHomeBadger1 extends BroadcastBadger2 {
        @Override // com.lody.virtual.client.badger.BroadcastBadger2, com.lody.virtual.client.badger.IBadger
        public String getAction() {
            return "com.htc.launcher.action.SET_NOTIFICATION";
        }

        @Override // com.lody.virtual.client.badger.BroadcastBadger2
        public String getComponentKey() {
            return "com.htc.launcher.extra.COMPONENT";
        }

        @Override // com.lody.virtual.client.badger.BroadcastBadger2
        public String getCountKey() {
            return "com.htc.launcher.extra.COUNT";
        }
    }

    @Override // com.lody.virtual.client.badger.IBadger
    public abstract String getAction();

    public abstract String getComponentKey();

    public abstract String getCountKey();

    @Override // com.lody.virtual.client.badger.IBadger
    public BadgerInfo handleBadger(Intent intent) {
        BadgerInfo badgerInfo = new BadgerInfo();
        ComponentName unflattenFromString = ComponentName.unflattenFromString(intent.getStringExtra(getComponentKey()));
        if (unflattenFromString == null) {
            return null;
        }
        badgerInfo.packageName = unflattenFromString.getPackageName();
        badgerInfo.className = unflattenFromString.getClassName();
        badgerInfo.badgerCount = intent.getIntExtra(getCountKey(), 0);
        return badgerInfo;
    }
}
