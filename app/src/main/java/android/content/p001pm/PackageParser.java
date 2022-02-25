package android.content.p001pm;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.IntentFilter;
import android.os.Bundle;
import java.security.cert.CertificateException;
import java.util.ArrayList;

/* renamed from: android.content.pm.PackageParser */
/* loaded from: classes.dex */
public class PackageParser {
    public static final int PARSE_COLLECT_CERTIFICATES = 32;
    public static final int PARSE_ENFORCE_CODE = 64;
    public static final int PARSE_IS_SYSTEM = 1;
    public static final int PARSE_IS_SYSTEM_DIR = 16;

    /* renamed from: android.content.pm.PackageParser$Activity */
    /* loaded from: classes.dex */
    public static final class Activity extends Component<ActivityIntentInfo> {
        public ActivityInfo info;
    }

    @TargetApi(29)
    /* renamed from: android.content.pm.PackageParser$Callback */
    /* loaded from: classes.dex */
    public interface Callback {
    }

    /* renamed from: android.content.pm.PackageParser$Component */
    /* loaded from: classes.dex */
    public static class Component<II extends IntentInfo> {
        public String className;
        public ArrayList<II> intents;
        public Bundle metaData;
        public Package owner;

        public ComponentName getComponentName() {
            return null;
        }
    }

    /* renamed from: android.content.pm.PackageParser$IntentInfo */
    /* loaded from: classes.dex */
    public static class IntentInfo extends IntentFilter {
        public int banner;
        public boolean hasDefault;
        public int icon;
        public int labelRes;
        public int logo;
        public CharSequence nonLocalizedLabel;
    }

    @TargetApi(28)
    /* renamed from: android.content.pm.PackageParser$SigningDetails */
    /* loaded from: classes.dex */
    public static final class SigningDetails {
        public static final SigningDetails UNKNOWN = null;
        public Signature[] signatures;
    }

    @TargetApi(29)
    /* renamed from: android.content.pm.PackageParser$CallbackImpl */
    /* loaded from: classes.dex */
    public static final class CallbackImpl implements Callback {
        public CallbackImpl(PackageManager packageManager) {
            throw new RuntimeException("Stub!");
        }
    }

    @TargetApi(29)
    public void setCallback(Callback callback) {
        throw new RuntimeException("Stub!");
    }

    @TargetApi(28)
    /* renamed from: android.content.pm.PackageParser$Builder */
    /* loaded from: classes.dex */
    public static class Builder {
        private Signature[] mSignatures;

        public Builder setSignatures(Signature[] signatureArr) {
            this.mSignatures = signatureArr;
            return this;
        }

        public SigningDetails build() throws CertificateException {
            return new SigningDetails();
        }
    }

    /* renamed from: android.content.pm.PackageParser$Package */
    /* loaded from: classes.dex */
    public class Package {
        public ApplicationInfo applicationInfo;
        public Bundle mAppMetaData;
        public Object mExtras;
        public int mPreferredOrder;
        public String mSharedUserId;
        public int mSharedUserLabel;
        public Signature[] mSignatures;
        public SigningDetails mSigningDetails;
        public int mVersionCode;
        public String mVersionName;
        public String packageName;
        public ArrayList<String> usesLibraries;
        public final ArrayList<Activity> activities = new ArrayList<>(0);
        public final ArrayList<Activity> receivers = new ArrayList<>(0);
        public final ArrayList<Provider> providers = new ArrayList<>(0);
        public final ArrayList<Service> services = new ArrayList<>(0);
        public final ArrayList<Instrumentation> instrumentation = new ArrayList<>(0);
        public final ArrayList<Permission> permissions = new ArrayList<>(0);
        public final ArrayList<PermissionGroup> permissionGroups = new ArrayList<>(0);
        public final ArrayList<String> requestedPermissions = new ArrayList<>();
        public ArrayList<ConfigurationInfo> configPreferences = null;
        public ArrayList<FeatureInfo> reqFeatures = null;

        public Package() {
        }
    }

    /* renamed from: android.content.pm.PackageParser$Service */
    /* loaded from: classes.dex */
    public final class Service extends Component<ServiceIntentInfo> {
        public ServiceInfo info;

        public Service() {
        }
    }

    /* renamed from: android.content.pm.PackageParser$Provider */
    /* loaded from: classes.dex */
    public final class Provider extends Component<ProviderIntentInfo> {
        public ProviderInfo info;

        public Provider() {
        }
    }

    /* renamed from: android.content.pm.PackageParser$Instrumentation */
    /* loaded from: classes.dex */
    public final class Instrumentation extends Component<IntentInfo> {
        public InstrumentationInfo info;

        public Instrumentation() {
        }
    }

    /* renamed from: android.content.pm.PackageParser$Permission */
    /* loaded from: classes.dex */
    public final class Permission extends Component<IntentInfo> {
        public PermissionInfo info;

        public Permission() {
        }
    }

    /* renamed from: android.content.pm.PackageParser$PermissionGroup */
    /* loaded from: classes.dex */
    public final class PermissionGroup extends Component<IntentInfo> {
        public PermissionGroupInfo info;

        public PermissionGroup() {
        }
    }

    /* renamed from: android.content.pm.PackageParser$ActivityIntentInfo */
    /* loaded from: classes.dex */
    public class ActivityIntentInfo extends IntentInfo {
        public Activity activity;

        public ActivityIntentInfo() {
        }
    }

    /* renamed from: android.content.pm.PackageParser$ServiceIntentInfo */
    /* loaded from: classes.dex */
    public class ServiceIntentInfo extends IntentInfo {
        public Service service;

        public ServiceIntentInfo() {
        }
    }

    /* renamed from: android.content.pm.PackageParser$ProviderIntentInfo */
    /* loaded from: classes.dex */
    public class ProviderIntentInfo extends IntentInfo {
        public Provider provider;

        public ProviderIntentInfo() {
        }
    }
}
