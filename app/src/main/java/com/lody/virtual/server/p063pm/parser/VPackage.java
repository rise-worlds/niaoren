package com.lody.virtual.server.p063pm.parser;

import android.content.ComponentName;
import android.content.IntentFilter;
import android.content.p001pm.PackageParser;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.ConfigurationInfo;
import android.content.pm.FeatureInfo;
import android.content.pm.InstrumentationInfo;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;
import android.content.pm.ProviderInfo;
import android.content.pm.ServiceInfo;
import android.content.pm.Signature;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: com.lody.virtual.server.pm.parser.VPackage */
/* loaded from: classes.dex */
public class VPackage implements Parcelable {
    public static final Parcelable.Creator<VPackage> CREATOR = new Parcelable.Creator<VPackage>() { // from class: com.lody.virtual.server.pm.parser.VPackage.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VPackage createFromParcel(Parcel parcel) {
            return new VPackage(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VPackage[] newArray(int i) {
            return new VPackage[i];
        }
    };
    public ArrayList<ActivityComponent> activities;
    public ApplicationInfo applicationInfo;
    public ArrayList<ConfigurationInfo> configPreferences;
    public ArrayList<InstrumentationComponent> instrumentation;
    public Bundle mAppMetaData;
    public Object mExtras;
    public int mPreferredOrder;
    public String mSharedUserId;
    public int mSharedUserLabel;
    public Signature[] mSignatures;
    public int mVersionCode;
    public String mVersionName;
    public String packageName;
    public ArrayList<PermissionGroupComponent> permissionGroups;
    public ArrayList<PermissionComponent> permissions;
    public ArrayList<String> protectedBroadcasts;
    public ArrayList<ProviderComponent> providers;
    public ArrayList<ActivityComponent> receivers;
    public ArrayList<FeatureInfo> reqFeatures;
    public ArrayList<String> requestedPermissions;
    public ArrayList<ServiceComponent> services;
    public ArrayList<String> usesLibraries;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public VPackage() {
        this.configPreferences = null;
        this.reqFeatures = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public VPackage(Parcel parcel) {
        this.configPreferences = null;
        this.reqFeatures = null;
        int readInt = parcel.readInt();
        this.activities = new ArrayList<>(readInt);
        while (true) {
            readInt--;
            if (readInt <= 0) {
                break;
            }
            this.activities.add(new ActivityComponent(parcel));
        }
        int readInt2 = parcel.readInt();
        this.receivers = new ArrayList<>(readInt2);
        while (true) {
            readInt2--;
            if (readInt2 <= 0) {
                break;
            }
            this.receivers.add(new ActivityComponent(parcel));
        }
        int readInt3 = parcel.readInt();
        this.providers = new ArrayList<>(readInt3);
        while (true) {
            readInt3--;
            if (readInt3 <= 0) {
                break;
            }
            this.providers.add(new ProviderComponent(parcel));
        }
        int readInt4 = parcel.readInt();
        this.services = new ArrayList<>(readInt4);
        while (true) {
            readInt4--;
            if (readInt4 <= 0) {
                break;
            }
            this.services.add(new ServiceComponent(parcel));
        }
        int readInt5 = parcel.readInt();
        this.instrumentation = new ArrayList<>(readInt5);
        while (true) {
            readInt5--;
            if (readInt5 <= 0) {
                break;
            }
            this.instrumentation.add(new InstrumentationComponent(parcel));
        }
        int readInt6 = parcel.readInt();
        this.permissions = new ArrayList<>(readInt6);
        while (true) {
            readInt6--;
            if (readInt6 <= 0) {
                break;
            }
            this.permissions.add(new PermissionComponent(parcel));
        }
        int readInt7 = parcel.readInt();
        this.permissionGroups = new ArrayList<>(readInt7);
        while (true) {
            readInt7--;
            if (readInt7 > 0) {
                this.permissionGroups.add(new PermissionGroupComponent(parcel));
            } else {
                this.requestedPermissions = parcel.createStringArrayList();
                this.protectedBroadcasts = parcel.createStringArrayList();
                this.applicationInfo = (ApplicationInfo) parcel.readParcelable(ApplicationInfo.class.getClassLoader());
                this.mAppMetaData = parcel.readBundle(Bundle.class.getClassLoader());
                this.packageName = parcel.readString();
                this.mPreferredOrder = parcel.readInt();
                this.mVersionName = parcel.readString();
                this.mSharedUserId = parcel.readString();
                this.usesLibraries = parcel.createStringArrayList();
                this.mVersionCode = parcel.readInt();
                this.mSharedUserLabel = parcel.readInt();
                this.configPreferences = parcel.createTypedArrayList(ConfigurationInfo.CREATOR);
                this.reqFeatures = parcel.createTypedArrayList(FeatureInfo.CREATOR);
                return;
            }
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.activities.size());
        Iterator<ActivityComponent> it = this.activities.iterator();
        while (true) {
            int i2 = 0;
            if (!it.hasNext()) {
                break;
            }
            ActivityComponent next = it.next();
            parcel.writeParcelable(next.info, 0);
            parcel.writeString(next.className);
            parcel.writeBundle(next.metaData);
            if (next.intents != null) {
                i2 = next.intents.size();
            }
            parcel.writeInt(i2);
            if (next.intents != null) {
                Iterator it2 = next.intents.iterator();
                while (it2.hasNext()) {
                    ((ActivityIntentInfo) it2.next()).writeToParcel(parcel, i);
                }
            }
        }
        parcel.writeInt(this.receivers.size());
        Iterator<ActivityComponent> it3 = this.receivers.iterator();
        while (it3.hasNext()) {
            ActivityComponent next2 = it3.next();
            parcel.writeParcelable(next2.info, 0);
            parcel.writeString(next2.className);
            parcel.writeBundle(next2.metaData);
            parcel.writeInt(next2.intents != null ? next2.intents.size() : 0);
            if (next2.intents != null) {
                Iterator it4 = next2.intents.iterator();
                while (it4.hasNext()) {
                    ((ActivityIntentInfo) it4.next()).writeToParcel(parcel, i);
                }
            }
        }
        parcel.writeInt(this.providers.size());
        Iterator<ProviderComponent> it5 = this.providers.iterator();
        while (it5.hasNext()) {
            ProviderComponent next3 = it5.next();
            parcel.writeParcelable(next3.info, 0);
            parcel.writeString(next3.className);
            parcel.writeBundle(next3.metaData);
            parcel.writeInt(next3.intents != null ? next3.intents.size() : 0);
            if (next3.intents != null) {
                Iterator it6 = next3.intents.iterator();
                while (it6.hasNext()) {
                    ((ProviderIntentInfo) it6.next()).writeToParcel(parcel, i);
                }
            }
        }
        parcel.writeInt(this.services.size());
        Iterator<ServiceComponent> it7 = this.services.iterator();
        while (it7.hasNext()) {
            ServiceComponent next4 = it7.next();
            parcel.writeParcelable(next4.info, 0);
            parcel.writeString(next4.className);
            parcel.writeBundle(next4.metaData);
            parcel.writeInt(next4.intents != null ? next4.intents.size() : 0);
            if (next4.intents != null) {
                Iterator it8 = next4.intents.iterator();
                while (it8.hasNext()) {
                    ((ServiceIntentInfo) it8.next()).writeToParcel(parcel, i);
                }
            }
        }
        parcel.writeInt(this.instrumentation.size());
        Iterator<InstrumentationComponent> it9 = this.instrumentation.iterator();
        while (it9.hasNext()) {
            InstrumentationComponent next5 = it9.next();
            parcel.writeParcelable(next5.info, 0);
            parcel.writeString(next5.className);
            parcel.writeBundle(next5.metaData);
            parcel.writeInt(next5.intents != null ? next5.intents.size() : 0);
            if (next5.intents != null) {
                Iterator it10 = next5.intents.iterator();
                while (it10.hasNext()) {
                    ((IntentInfo) it10.next()).writeToParcel(parcel, i);
                }
            }
        }
        parcel.writeInt(this.permissions.size());
        Iterator<PermissionComponent> it11 = this.permissions.iterator();
        while (it11.hasNext()) {
            PermissionComponent next6 = it11.next();
            parcel.writeParcelable(next6.info, 0);
            parcel.writeString(next6.className);
            parcel.writeBundle(next6.metaData);
            parcel.writeInt(next6.intents != null ? next6.intents.size() : 0);
            if (next6.intents != null) {
                Iterator it12 = next6.intents.iterator();
                while (it12.hasNext()) {
                    ((IntentInfo) it12.next()).writeToParcel(parcel, i);
                }
            }
        }
        parcel.writeInt(this.permissionGroups.size());
        Iterator<PermissionGroupComponent> it13 = this.permissionGroups.iterator();
        while (it13.hasNext()) {
            PermissionGroupComponent next7 = it13.next();
            parcel.writeParcelable(next7.info, 0);
            parcel.writeString(next7.className);
            parcel.writeBundle(next7.metaData);
            parcel.writeInt(next7.intents != null ? next7.intents.size() : 0);
            if (next7.intents != null) {
                Iterator it14 = next7.intents.iterator();
                while (it14.hasNext()) {
                    ((IntentInfo) it14.next()).writeToParcel(parcel, i);
                }
            }
        }
        parcel.writeStringList(this.requestedPermissions);
        parcel.writeStringList(this.protectedBroadcasts);
        parcel.writeParcelable(this.applicationInfo, i);
        parcel.writeBundle(this.mAppMetaData);
        parcel.writeString(this.packageName);
        parcel.writeInt(this.mPreferredOrder);
        parcel.writeString(this.mVersionName);
        parcel.writeString(this.mSharedUserId);
        parcel.writeStringList(this.usesLibraries);
        parcel.writeInt(this.mVersionCode);
        parcel.writeInt(this.mSharedUserLabel);
        parcel.writeTypedList(this.configPreferences);
        parcel.writeTypedList(this.reqFeatures);
    }

    /* renamed from: com.lody.virtual.server.pm.parser.VPackage$ActivityIntentInfo */
    /* loaded from: classes.dex */
    public static class ActivityIntentInfo extends IntentInfo {
        public ActivityComponent activity;

        public ActivityIntentInfo(PackageParser.IntentInfo intentInfo) {
            super(intentInfo);
        }

        protected ActivityIntentInfo(Parcel parcel) {
            super(parcel);
        }
    }

    /* renamed from: com.lody.virtual.server.pm.parser.VPackage$ServiceIntentInfo */
    /* loaded from: classes.dex */
    public static class ServiceIntentInfo extends IntentInfo {
        public ServiceComponent service;

        public ServiceIntentInfo(PackageParser.IntentInfo intentInfo) {
            super(intentInfo);
        }

        protected ServiceIntentInfo(Parcel parcel) {
            super(parcel);
        }
    }

    /* renamed from: com.lody.virtual.server.pm.parser.VPackage$ProviderIntentInfo */
    /* loaded from: classes.dex */
    public static class ProviderIntentInfo extends IntentInfo {
        public ProviderComponent provider;

        public ProviderIntentInfo(PackageParser.IntentInfo intentInfo) {
            super(intentInfo);
        }

        protected ProviderIntentInfo(Parcel parcel) {
            super(parcel);
        }
    }

    /* renamed from: com.lody.virtual.server.pm.parser.VPackage$IntentInfo */
    /* loaded from: classes.dex */
    public static class IntentInfo implements Parcelable {
        public static final Parcelable.Creator<IntentInfo> CREATOR = new Parcelable.Creator<IntentInfo>() { // from class: com.lody.virtual.server.pm.parser.VPackage.IntentInfo.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public IntentInfo createFromParcel(Parcel parcel) {
                return new IntentInfo(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public IntentInfo[] newArray(int i) {
                return new IntentInfo[i];
            }
        };
        public int banner;
        public IntentFilter filter;
        public boolean hasDefault;
        public int icon;
        public int labelRes;
        public int logo;
        public String nonLocalizedLabel;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public IntentInfo(PackageParser.IntentInfo intentInfo) {
            this.filter = intentInfo;
            this.hasDefault = intentInfo.hasDefault;
            this.labelRes = intentInfo.labelRes;
            if (intentInfo.nonLocalizedLabel != null) {
                this.nonLocalizedLabel = intentInfo.nonLocalizedLabel.toString();
            }
            this.icon = intentInfo.icon;
            this.logo = intentInfo.logo;
            if (Build.VERSION.SDK_INT > 19) {
                this.banner = intentInfo.banner;
            }
        }

        protected IntentInfo(Parcel parcel) {
            this.filter = (IntentFilter) parcel.readParcelable(VPackage.class.getClassLoader());
            this.hasDefault = parcel.readByte() != 0;
            this.labelRes = parcel.readInt();
            this.nonLocalizedLabel = parcel.readString();
            this.icon = parcel.readInt();
            this.logo = parcel.readInt();
            this.banner = parcel.readInt();
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.filter, i);
            parcel.writeByte(this.hasDefault ? (byte) 1 : (byte) 0);
            parcel.writeInt(this.labelRes);
            parcel.writeString(this.nonLocalizedLabel);
            parcel.writeInt(this.icon);
            parcel.writeInt(this.logo);
            parcel.writeInt(this.banner);
        }
    }

    /* renamed from: com.lody.virtual.server.pm.parser.VPackage$Component */
    /* loaded from: classes.dex */
    public static class Component<II extends IntentInfo> {
        public String className;
        private ComponentName componentName;
        public ArrayList<II> intents;
        public Bundle metaData;
        public VPackage owner;

        protected Component() {
        }

        public Component(PackageParser.Component component) {
            this.className = component.className;
            this.metaData = component.metaData;
        }

        public ComponentName getComponentName() {
            ComponentName componentName = this.componentName;
            if (componentName != null) {
                return componentName;
            }
            if (this.className != null) {
                this.componentName = new ComponentName(this.owner.packageName, this.className);
            }
            return this.componentName;
        }
    }

    /* renamed from: com.lody.virtual.server.pm.parser.VPackage$ActivityComponent */
    /* loaded from: classes.dex */
    public static class ActivityComponent extends Component<ActivityIntentInfo> {
        public ActivityInfo info;

        public ActivityComponent(PackageParser.Activity activity) {
            super(activity);
            if (activity.intents != null) {
                this.intents = new ArrayList<>(activity.intents.size());
                Iterator it = activity.intents.iterator();
                while (it.hasNext()) {
                    this.intents.add(new ActivityIntentInfo((PackageParser.IntentInfo) it.next()));
                }
            }
            this.info = activity.info;
        }

        protected ActivityComponent(Parcel parcel) {
            this.info = (ActivityInfo) parcel.readParcelable(ActivityInfo.class.getClassLoader());
            this.className = parcel.readString();
            this.metaData = parcel.readBundle(Bundle.class.getClassLoader());
            int readInt = parcel.readInt();
            this.intents = new ArrayList<>(readInt);
            while (true) {
                readInt--;
                if (readInt > 0) {
                    this.intents.add(new ActivityIntentInfo(parcel));
                } else {
                    return;
                }
            }
        }
    }

    /* renamed from: com.lody.virtual.server.pm.parser.VPackage$ServiceComponent */
    /* loaded from: classes.dex */
    public static class ServiceComponent extends Component<ServiceIntentInfo> {
        public ServiceInfo info;

        public ServiceComponent(PackageParser.Service service) {
            super(service);
            if (service.intents != null) {
                this.intents = new ArrayList<>(service.intents.size());
                Iterator it = service.intents.iterator();
                while (it.hasNext()) {
                    this.intents.add(new ServiceIntentInfo((PackageParser.IntentInfo) it.next()));
                }
            }
            this.info = service.info;
        }

        protected ServiceComponent(Parcel parcel) {
            this.info = (ServiceInfo) parcel.readParcelable(ActivityInfo.class.getClassLoader());
            this.className = parcel.readString();
            this.metaData = parcel.readBundle(Bundle.class.getClassLoader());
            int readInt = parcel.readInt();
            this.intents = new ArrayList<>(readInt);
            while (true) {
                readInt--;
                if (readInt > 0) {
                    this.intents.add(new ServiceIntentInfo(parcel));
                } else {
                    return;
                }
            }
        }
    }

    /* renamed from: com.lody.virtual.server.pm.parser.VPackage$ProviderComponent */
    /* loaded from: classes.dex */
    public static class ProviderComponent extends Component<ProviderIntentInfo> {
        public ProviderInfo info;

        public ProviderComponent(PackageParser.Provider provider) {
            super(provider);
            if (provider.intents != null) {
                this.intents = new ArrayList<>(provider.intents.size());
                Iterator it = provider.intents.iterator();
                while (it.hasNext()) {
                    this.intents.add(new ProviderIntentInfo((PackageParser.IntentInfo) it.next()));
                }
            }
            this.info = provider.info;
        }

        protected ProviderComponent(Parcel parcel) {
            this.info = (ProviderInfo) parcel.readParcelable(ActivityInfo.class.getClassLoader());
            this.className = parcel.readString();
            this.metaData = parcel.readBundle(Bundle.class.getClassLoader());
            int readInt = parcel.readInt();
            this.intents = new ArrayList<>(readInt);
            while (true) {
                readInt--;
                if (readInt > 0) {
                    this.intents.add(new ProviderIntentInfo(parcel));
                } else {
                    return;
                }
            }
        }
    }

    /* renamed from: com.lody.virtual.server.pm.parser.VPackage$InstrumentationComponent */
    /* loaded from: classes.dex */
    public static class InstrumentationComponent extends Component<IntentInfo> {
        public InstrumentationInfo info;

        public InstrumentationComponent(PackageParser.Instrumentation instrumentation) {
            super(instrumentation);
            this.info = instrumentation.info;
        }

        protected InstrumentationComponent(Parcel parcel) {
            this.info = (InstrumentationInfo) parcel.readParcelable(ActivityInfo.class.getClassLoader());
            this.className = parcel.readString();
            this.metaData = parcel.readBundle(Bundle.class.getClassLoader());
            int readInt = parcel.readInt();
            this.intents = new ArrayList<>(readInt);
            while (true) {
                readInt--;
                if (readInt > 0) {
                    this.intents.add(new IntentInfo(parcel));
                } else {
                    return;
                }
            }
        }
    }

    /* renamed from: com.lody.virtual.server.pm.parser.VPackage$PermissionComponent */
    /* loaded from: classes.dex */
    public static class PermissionComponent extends Component<IntentInfo> {
        public PermissionInfo info;

        public PermissionComponent(PackageParser.Permission permission) {
            super(permission);
            this.info = permission.info;
        }

        protected PermissionComponent(Parcel parcel) {
            this.info = (PermissionInfo) parcel.readParcelable(ActivityInfo.class.getClassLoader());
            this.className = parcel.readString();
            this.metaData = parcel.readBundle(Bundle.class.getClassLoader());
            int readInt = parcel.readInt();
            this.intents = new ArrayList<>(readInt);
            while (true) {
                readInt--;
                if (readInt > 0) {
                    this.intents.add(new IntentInfo(parcel));
                } else {
                    return;
                }
            }
        }
    }

    /* renamed from: com.lody.virtual.server.pm.parser.VPackage$PermissionGroupComponent */
    /* loaded from: classes.dex */
    public static class PermissionGroupComponent extends Component<IntentInfo> {
        public PermissionGroupInfo info;

        public PermissionGroupComponent(PackageParser.PermissionGroup permissionGroup) {
            super(permissionGroup);
            this.info = permissionGroup.info;
        }

        protected PermissionGroupComponent(Parcel parcel) {
            this.info = (PermissionGroupInfo) parcel.readParcelable(ActivityInfo.class.getClassLoader());
            this.className = parcel.readString();
            this.metaData = parcel.readBundle(Bundle.class.getClassLoader());
            int readInt = parcel.readInt();
            this.intents = new ArrayList<>(readInt);
            while (true) {
                readInt--;
                if (readInt > 0) {
                    this.intents.add(new IntentInfo(parcel));
                } else {
                    return;
                }
            }
        }
    }
}
