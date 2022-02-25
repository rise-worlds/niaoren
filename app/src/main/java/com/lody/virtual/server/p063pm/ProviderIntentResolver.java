package com.lody.virtual.server.p063pm;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import com.lody.virtual.helper.compat.ObjectsCompat;
import com.lody.virtual.helper.utils.VLog;
import com.lody.virtual.server.p063pm.parser.PackageParserEx;
import com.lody.virtual.server.p063pm.parser.VPackage;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.lody.virtual.server.pm.ProviderIntentResolver */
/* loaded from: classes.dex */
public final class ProviderIntentResolver extends IntentResolver<VPackage.ProviderIntentInfo, ResolveInfo> {
    private int mFlags;
    private final HashMap<ComponentName, VPackage.ProviderComponent> mProviders = new HashMap<>();

    /* JADX INFO: Access modifiers changed from: protected */
    public void dumpFilter(PrintWriter printWriter, String str, VPackage.ProviderIntentInfo providerIntentInfo) {
    }

    @Override // com.lody.virtual.server.p063pm.IntentResolver
    protected void dumpFilterLabel(PrintWriter printWriter, String str, Object obj, int i) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isFilterStopped(VPackage.ProviderIntentInfo providerIntentInfo) {
        return false;
    }

    @Override // com.lody.virtual.server.p063pm.IntentResolver
    public List<ResolveInfo> queryIntent(Intent intent, String str, boolean z, int i) {
        this.mFlags = z ? 65536 : 0;
        return super.queryIntent(intent, str, z, i);
    }

    public List<ResolveInfo> queryIntent(Intent intent, String str, int i, int i2) {
        this.mFlags = i;
        return super.queryIntent(intent, str, (i & 65536) != 0, i2);
    }

    public List<ResolveInfo> queryIntentForPackage(Intent intent, String str, int i, ArrayList<VPackage.ProviderComponent> arrayList, int i2) {
        if (arrayList == null) {
            return null;
        }
        this.mFlags = i;
        boolean z = (i & 65536) != 0;
        int size = arrayList.size();
        ArrayList arrayList2 = new ArrayList(size);
        for (int i3 = 0; i3 < size; i3++) {
            ArrayList<II> arrayList3 = arrayList.get(i3).intents;
            if (arrayList3 != 0 && arrayList3.size() > 0) {
                VPackage.ProviderIntentInfo[] providerIntentInfoArr = new VPackage.ProviderIntentInfo[arrayList3.size()];
                arrayList3.toArray(providerIntentInfoArr);
                arrayList2.add(providerIntentInfoArr);
            }
        }
        return super.queryIntentFromList(intent, str, z, arrayList2, i2);
    }

    public final void addProvider(VPackage.ProviderComponent providerComponent) {
        if (this.mProviders.containsKey(providerComponent.getComponentName())) {
            VLog.m18986w("PackageManager", "Provider " + providerComponent.getComponentName() + " already defined; ignoring", new Object[0]);
            return;
        }
        this.mProviders.put(providerComponent.getComponentName(), providerComponent);
        int size = providerComponent.intents.size();
        for (int i = 0; i < size; i++) {
            addFilter((VPackage.ProviderIntentInfo) providerComponent.intents.get(i));
        }
    }

    public final void removeProvider(VPackage.ProviderComponent providerComponent) {
        this.mProviders.remove(providerComponent.getComponentName());
        int size = providerComponent.intents.size();
        for (int i = 0; i < size; i++) {
            removeFilter((VPackage.ProviderIntentInfo) providerComponent.intents.get(i));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @TargetApi(19)
    public boolean allowFilterResult(VPackage.ProviderIntentInfo providerIntentInfo, List<ResolveInfo> list) {
        ProviderInfo providerInfo = providerIntentInfo.provider.info;
        for (int size = list.size() - 1; size >= 0; size--) {
            ProviderInfo providerInfo2 = list.get(size).providerInfo;
            if (ObjectsCompat.equals(providerInfo2.name, providerInfo.name) && ObjectsCompat.equals(providerInfo2.packageName, providerInfo.packageName)) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.lody.virtual.server.p063pm.IntentResolver
    public VPackage.ProviderIntentInfo[] newArray(int i) {
        return new VPackage.ProviderIntentInfo[i];
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isPackageForFilter(String str, VPackage.ProviderIntentInfo providerIntentInfo) {
        return str.equals(providerIntentInfo.provider.owner.packageName);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @TargetApi(19)
    public ResolveInfo newResult(VPackage.ProviderIntentInfo providerIntentInfo, int i, int i2) {
        ProviderInfo generateProviderInfo;
        VPackage.ProviderComponent providerComponent = providerIntentInfo.provider;
        if (!PackageParserEx.isEnabledLPr(providerComponent.info, this.mFlags, i2) || (generateProviderInfo = PackageParserEx.generateProviderInfo(providerComponent, this.mFlags, ((PackageSetting) providerComponent.owner.mExtras).readUserState(i2), i2)) == null) {
            return null;
        }
        ResolveInfo resolveInfo = new ResolveInfo();
        resolveInfo.providerInfo = generateProviderInfo;
        if ((this.mFlags & 64) != 0) {
            resolveInfo.filter = providerIntentInfo.filter;
        }
        resolveInfo.priority = providerIntentInfo.filter.getPriority();
        resolveInfo.preferredOrder = providerComponent.owner.mPreferredOrder;
        resolveInfo.match = i;
        resolveInfo.isDefault = providerIntentInfo.hasDefault;
        resolveInfo.labelRes = providerIntentInfo.labelRes;
        resolveInfo.nonLocalizedLabel = providerIntentInfo.nonLocalizedLabel;
        resolveInfo.icon = providerIntentInfo.icon;
        return resolveInfo;
    }

    @Override // com.lody.virtual.server.p063pm.IntentResolver
    protected void sortResults(List<ResolveInfo> list) {
        Collections.sort(list, VPackageManagerService.sResolvePrioritySorter);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Object filterToLabel(VPackage.ProviderIntentInfo providerIntentInfo) {
        return providerIntentInfo.provider;
    }
}
