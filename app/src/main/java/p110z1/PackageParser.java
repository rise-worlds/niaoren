package p110z1;

import android.content.ComponentName;
import android.content.IntentFilter;
import android.content.p001pm.PackageParser;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;
import android.content.pm.ProviderInfo;
import android.content.pm.ServiceInfo;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.DisplayMetrics;
import java.io.File;
import java.util.List;
import mirror.MethodParams;
import mirror.MethodReflectParams;
import mirror.RefClass;
import mirror.RefConstructor;
import mirror.RefMethod;
import mirror.RefObject;
import mirror.RefStaticMethod;

/* renamed from: z1.cul */
/* loaded from: classes3.dex */
public class PackageParser {
    public static Class<?> TYPE = RefClass.load(PackageParser.class, "android.content.pm.PackageParser");
    @MethodReflectParams({"android.content.pm.PackageParser$Package", "int"})
    public static RefMethod<Void> collectCertificates;
    @MethodParams({String.class})
    public static RefConstructor<android.content.p001pm.PackageParser> ctor;
    @MethodReflectParams({"android.content.pm.PackageParser$Activity", "int"})
    public static RefStaticMethod<ActivityInfo> generateActivityInfo;
    @MethodReflectParams({"android.content.pm.PackageParser$Package", "int"})
    public static RefStaticMethod<ApplicationInfo> generateApplicationInfo;
    @MethodReflectParams({"android.content.pm.PackageParser$Package", "[I", "int", "long", "long"})
    public static RefStaticMethod<PackageInfo> generatePackageInfo;
    @MethodReflectParams({"android.content.pm.PackageParser$Provider", "int"})
    public static RefStaticMethod<ProviderInfo> generateProviderInfo;
    @MethodReflectParams({"android.content.pm.PackageParser$Service", "int"})
    public static RefStaticMethod<ServiceInfo> generateServiceInfo;
    @MethodParams({File.class, String.class, DisplayMetrics.class, int.class})
    public static RefMethod<PackageParser.Package> parsePackage;

    /* compiled from: PackageParser.java */
    /* renamed from: z1.cul$a */
    /* loaded from: classes3.dex */
    public static class C5143a {
        public static Class<?> TYPE = RefClass.load(C5143a.class, "android.content.pm.PackageParser$Activity");
        public static RefObject<ActivityInfo> info;
    }

    /* compiled from: PackageParser.java */
    /* renamed from: z1.cul$b */
    /* loaded from: classes3.dex */
    public static class C5144b {
        public static Class<?> TYPE = RefClass.load(C5144b.class, "android.content.pm.PackageParser$Component");
        public static RefObject<String> className;
        public static RefObject<ComponentName> componentName;
        public static RefObject<List<IntentFilter>> intents;
    }

    /* compiled from: PackageParser.java */
    /* renamed from: z1.cul$c */
    /* loaded from: classes3.dex */
    public static class C5145c {
        public static Class<?> TYPE = RefClass.load(C5145c.class, "android.content.pm.PackageParser$Package");
        public static RefObject<List> activities;
        public static RefObject<Bundle> mAppMetaData;
        public static RefObject<String> mSharedUserId;
        public static RefObject<Signature[]> mSignatures;
        public static RefObject<Object> mSigningDetails;
        public static RefObject<Integer> mVersionCode;
        public static RefObject<String> packageName;
        public static RefObject<List> permissionGroups;
        public static RefObject<List> permissions;
        public static RefObject<List<String>> protectedBroadcasts;
        public static RefObject<List> providers;
        public static RefObject<List> receivers;
        public static RefObject<List<String>> requestedPermissions;
        public static RefObject<List> services;
    }

    /* compiled from: PackageParser.java */
    /* renamed from: z1.cul$d */
    /* loaded from: classes3.dex */
    public static class C5146d {
        public static Class<?> TYPE = RefClass.load(C5146d.class, "android.content.pm.PackageParser$Permission");
        public static RefObject<PermissionInfo> info;
    }

    /* compiled from: PackageParser.java */
    /* renamed from: z1.cul$e */
    /* loaded from: classes3.dex */
    public static class C5147e {
        public static Class<?> TYPE = RefClass.load(C5147e.class, "android.content.pm.PackageParser$PermissionGroup");
        public static RefObject<PermissionGroupInfo> info;
    }

    /* compiled from: PackageParser.java */
    /* renamed from: z1.cul$f */
    /* loaded from: classes3.dex */
    public static class C5148f {
        public static Class<?> TYPE = RefClass.load(C5148f.class, "android.content.pm.PackageParser$Provider");
        public static RefObject<ProviderInfo> info;
    }

    /* compiled from: PackageParser.java */
    /* renamed from: z1.cul$g */
    /* loaded from: classes3.dex */
    public static class C5149g {
        public static Class<?> TYPE = RefClass.load(C5148f.class, "android.content.pm.PackageParser$Service");
        public static RefObject<ServiceInfo> info;
    }

    /* compiled from: PackageParser.java */
    /* renamed from: z1.cul$h */
    /* loaded from: classes3.dex */
    public static class C5150h {
        public static Class<?> TYPE = RefClass.load(C5150h.class, "android.content.pm.PackageParser$SigningDetails");
        public static RefMethod<Boolean> hasPastSigningCertificates;
        public static RefMethod<Boolean> hasSignatures;
        public static RefObject<Signature[]> pastSigningCertificates;
        public static RefObject<Signature[]> signatures;
    }
}
