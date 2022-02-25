package com.lody.virtual.server.content;

import android.accounts.Account;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SyncAdapterType;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.util.Xml;
import com.lody.virtual.helper.utils.ComponentUtils;
import com.lody.virtual.server.accounts.RegisteredServicesParser;
import com.lody.virtual.server.p063pm.VPackageManagerService;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import p110z1.R_Hide;
import p110z1.SyncAdapterTypeN;

/* loaded from: classes.dex */
public class SyncAdaptersCache {
    private Context mContext;
    private final Map<String, SyncAdapterInfo> mSyncAdapterInfos = new HashMap();

    public SyncAdaptersCache(Context context) {
        this.mContext = context;
    }

    /* loaded from: classes.dex */
    public static class SyncAdapterInfo {
        public ComponentName componentName;
        public ServiceInfo serviceInfo;
        public SyncAdapterType type;

        SyncAdapterInfo(SyncAdapterType syncAdapterType, ServiceInfo serviceInfo) {
            this.type = syncAdapterType;
            this.serviceInfo = serviceInfo;
            this.componentName = ComponentUtils.toComponentName(serviceInfo);
        }
    }

    public void refreshServiceCache(String str) {
        Intent intent = new Intent("android.content.SyncAdapter");
        if (str != null) {
            intent.setPackage(str);
        }
        generateServicesMap(VPackageManagerService.get().queryIntentServices(intent, null, 128, 0), this.mSyncAdapterInfos, new RegisteredServicesParser());
    }

    public SyncAdapterInfo getServiceInfo(Account account, String str) {
        SyncAdapterInfo syncAdapterInfo;
        synchronized (this.mSyncAdapterInfos) {
            Map<String, SyncAdapterInfo> map = this.mSyncAdapterInfos;
            syncAdapterInfo = map.get(account.type + "/" + str);
        }
        return syncAdapterInfo;
    }

    public Collection<SyncAdapterInfo> getAllServices() {
        return this.mSyncAdapterInfos.values();
    }

    private void generateServicesMap(List<ResolveInfo> list, Map<String, SyncAdapterInfo> map, RegisteredServicesParser registeredServicesParser) {
        SyncAdapterType parseSyncAdapterType;
        for (ResolveInfo resolveInfo : list) {
            XmlResourceParser parser = registeredServicesParser.getParser(this.mContext, resolveInfo.serviceInfo, "android.content.SyncAdapter");
            if (parser != null) {
                try {
                    AttributeSet asAttributeSet = Xml.asAttributeSet(parser);
                    while (true) {
                        int next = parser.next();
                        if (next == 1 || next == 2) {
                            break;
                        }
                    }
                    if ("sync-adapter".equals(parser.getName()) && (parseSyncAdapterType = parseSyncAdapterType(registeredServicesParser.getResources(this.mContext, resolveInfo.serviceInfo.applicationInfo), asAttributeSet)) != null) {
                        map.put(parseSyncAdapterType.accountType + "/" + parseSyncAdapterType.authority, new SyncAdapterInfo(parseSyncAdapterType, resolveInfo.serviceInfo));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private SyncAdapterType parseSyncAdapterType(Resources resources, AttributeSet attributeSet) {
        TypedArray obtainAttributes = resources.obtainAttributes(attributeSet, R_Hide.C5192d.SyncAdapter.get());
        try {
            String string = obtainAttributes.getString(R_Hide.C5192d.SyncAdapter_contentAuthority.get());
            String string2 = obtainAttributes.getString(R_Hide.C5192d.SyncAdapter_accountType.get());
            if (!(string == null || string2 == null)) {
                boolean z = obtainAttributes.getBoolean(R_Hide.C5192d.SyncAdapter_userVisible.get(), true);
                boolean z2 = obtainAttributes.getBoolean(R_Hide.C5192d.SyncAdapter_supportsUploading.get(), true);
                boolean z3 = obtainAttributes.getBoolean(R_Hide.C5192d.SyncAdapter_isAlwaysSyncable.get(), true);
                boolean z4 = obtainAttributes.getBoolean(R_Hide.C5192d.SyncAdapter_allowParallelSyncs.get(), true);
                String string3 = obtainAttributes.getString(R_Hide.C5192d.SyncAdapter_settingsActivity.get());
                if (SyncAdapterTypeN.ctor != null) {
                    SyncAdapterType newInstance = SyncAdapterTypeN.ctor.newInstance(string, string2, Boolean.valueOf(z), Boolean.valueOf(z2), Boolean.valueOf(z3), Boolean.valueOf(z4), string3, null);
                    obtainAttributes.recycle();
                    return newInstance;
                }
                SyncAdapterType newInstance2 = p110z1.SyncAdapterType.ctor.newInstance(string, string2, Boolean.valueOf(z), Boolean.valueOf(z2), Boolean.valueOf(z3), Boolean.valueOf(z4), string3);
                obtainAttributes.recycle();
                return newInstance2;
            }
            obtainAttributes.recycle();
            return null;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }
}
