package com.lody.virtual.client.badger;

import android.content.Intent;
import com.lody.virtual.remote.BadgerInfo;

/* loaded from: classes.dex */
public abstract class BroadcastBadger1 implements IBadger {

    /* loaded from: classes.dex */
    static class AdwHomeBadger extends BroadcastBadger1 {
        @Override // com.lody.virtual.client.badger.BroadcastBadger1, com.lody.virtual.client.badger.IBadger
        public String getAction() {
            return "org.adw.launcher.counter.SEND";
        }

        @Override // com.lody.virtual.client.badger.BroadcastBadger1
        public String getClassNameKey() {
            return "CNAME";
        }

        @Override // com.lody.virtual.client.badger.BroadcastBadger1
        public String getCountKey() {
            return "COUNT";
        }

        @Override // com.lody.virtual.client.badger.BroadcastBadger1
        public String getPackageKey() {
            return "PNAME";
        }
    }

    /* loaded from: classes.dex */
    static class AospHomeBadger extends BroadcastBadger1 {
        @Override // com.lody.virtual.client.badger.BroadcastBadger1, com.lody.virtual.client.badger.IBadger
        public String getAction() {
            return "android.intent.action.BADGE_COUNT_UPDATE";
        }

        @Override // com.lody.virtual.client.badger.BroadcastBadger1
        public String getClassNameKey() {
            return "badge_count_class_name";
        }

        @Override // com.lody.virtual.client.badger.BroadcastBadger1
        public String getCountKey() {
            return "badge_count";
        }

        @Override // com.lody.virtual.client.badger.BroadcastBadger1
        public String getPackageKey() {
            return "badge_count_package_name";
        }
    }

    /* loaded from: classes.dex */
    static class LGHomeBadger extends BroadcastBadger1 {
        @Override // com.lody.virtual.client.badger.BroadcastBadger1, com.lody.virtual.client.badger.IBadger
        public String getAction() {
            return "android.intent.action.BADGE_COUNT_UPDATE";
        }

        @Override // com.lody.virtual.client.badger.BroadcastBadger1
        public String getClassNameKey() {
            return "badge_count_class_name";
        }

        @Override // com.lody.virtual.client.badger.BroadcastBadger1
        public String getCountKey() {
            return "badge_count";
        }

        @Override // com.lody.virtual.client.badger.BroadcastBadger1
        public String getPackageKey() {
            return "badge_count_package_name";
        }
    }

    /* loaded from: classes.dex */
    static class NewHtcHomeBadger2 extends BroadcastBadger1 {
        @Override // com.lody.virtual.client.badger.BroadcastBadger1, com.lody.virtual.client.badger.IBadger
        public String getAction() {
            return "com.htc.launcher.action.UPDATE_SHORTCUT";
        }

        @Override // com.lody.virtual.client.badger.BroadcastBadger1
        public String getClassNameKey() {
            return null;
        }

        @Override // com.lody.virtual.client.badger.BroadcastBadger1
        public String getCountKey() {
            return "count";
        }

        @Override // com.lody.virtual.client.badger.BroadcastBadger1
        public String getPackageKey() {
            return "packagename";
        }
    }

    /* loaded from: classes.dex */
    static class OPPOHomeBader extends BroadcastBadger1 {
        @Override // com.lody.virtual.client.badger.BroadcastBadger1, com.lody.virtual.client.badger.IBadger
        public String getAction() {
            return "com.oppo.unsettledevent";
        }

        @Override // com.lody.virtual.client.badger.BroadcastBadger1
        public String getClassNameKey() {
            return null;
        }

        @Override // com.lody.virtual.client.badger.BroadcastBadger1
        public String getCountKey() {
            return "number";
        }

        @Override // com.lody.virtual.client.badger.BroadcastBadger1
        public String getPackageKey() {
            return "pakeageName";
        }
    }

    @Override // com.lody.virtual.client.badger.IBadger
    public abstract String getAction();

    public abstract String getClassNameKey();

    public abstract String getCountKey();

    public abstract String getPackageKey();

    @Override // com.lody.virtual.client.badger.IBadger
    public BadgerInfo handleBadger(Intent intent) {
        BadgerInfo badgerInfo = new BadgerInfo();
        badgerInfo.packageName = intent.getStringExtra(getPackageKey());
        if (getClassNameKey() != null) {
            badgerInfo.className = intent.getStringExtra(getClassNameKey());
        }
        badgerInfo.badgerCount = intent.getIntExtra(getCountKey(), 0);
        return badgerInfo;
    }
}
