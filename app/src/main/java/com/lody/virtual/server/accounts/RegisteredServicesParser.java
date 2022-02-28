package com.lody.virtual.server.accounts;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.ServiceInfo;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import com.lody.virtual.server.pm.PackageCacheManager;
import com.lody.virtual.server.pm.PackageSetting;

/* loaded from: classes.dex */
public class RegisteredServicesParser {
    public XmlResourceParser getParser(Context context, ServiceInfo serviceInfo, String str) {
        int i;
        Bundle bundle = serviceInfo.metaData;
        if (bundle == null || (i = bundle.getInt(str)) == 0) {
            return null;
        }
        try {
            return getResources(context, serviceInfo.applicationInfo).getXml(i);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Resources getResources(Context context, ApplicationInfo applicationInfo) {
        PackageSetting setting = PackageCacheManager.getSetting(applicationInfo.packageName);
        if (setting == null) {
            return null;
        }
        AssetManager newInstance = p110z1.AssetManager.ctor.newInstance();
        p110z1.AssetManager.addAssetPath.call(newInstance, setting.getApkPath(false));
        Resources resources = context.getResources();
        return new Resources(newInstance, resources.getDisplayMetrics(), resources.getConfiguration());
    }
}
