package com.lody.virtual.client.hook.providers;

import android.os.Bundle;
import com.lody.virtual.client.VClient;
import com.lody.virtual.client.hook.base.MethodBox;
import com.lody.virtual.client.ipc.ServiceManagerNative;
import com.lody.virtual.client.ipc.VActivityManager;
import com.lody.virtual.os.VUserHandle;
import com.lody.virtual.remote.BadgerInfo;
import java.lang.reflect.InvocationTargetException;

/* loaded from: classes.dex */
public class BadgeProviderHook extends ExternalProviderHook {
    public BadgeProviderHook(Object obj) {
        super(obj);
    }

    @Override // com.lody.virtual.client.hook.providers.ProviderHook
    public Bundle call(MethodBox methodBox, String str, String str2, Bundle bundle) throws InvocationTargetException {
        if ("change_badge".equals(str)) {
            BadgerInfo badgerInfo = new BadgerInfo();
            badgerInfo.userId = VUserHandle.myUserId();
            badgerInfo.packageName = bundle.getString(ServiceManagerNative.PACKAGE);
            badgerInfo.className = bundle.getString("class");
            badgerInfo.badgerCount = bundle.getInt("badgenumber");
            VActivityManager.get().notifyBadgerChange(badgerInfo);
            Bundle bundle2 = new Bundle();
            bundle2.putBoolean("success", true);
            return bundle2;
        }
        if ("setAppBadgeCount".equals(str)) {
            BadgerInfo badgerInfo2 = new BadgerInfo();
            badgerInfo2.userId = VUserHandle.myUserId();
            badgerInfo2.packageName = VClient.get().getCurrentPackage();
            badgerInfo2.badgerCount = bundle.getInt("app_badge_count");
            VActivityManager.get().notifyBadgerChange(badgerInfo2);
            new Bundle().putBoolean("success", true);
        }
        return super.call(methodBox, str, str2, bundle);
    }
}
