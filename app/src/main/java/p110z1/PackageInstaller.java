package p110z1;

import android.content.pm.PackageInstaller;
import android.graphics.Bitmap;
import android.net.Uri;
import mirror.RefBoolean;
import mirror.RefClass;
import mirror.RefConstructor;
import mirror.RefFloat;
import mirror.RefInt;
import mirror.RefLong;
import mirror.RefObject;

/* renamed from: z1.cuk */
/* loaded from: classes3.dex */
public class PackageInstaller {

    /* compiled from: PackageInstaller.java */
    /* renamed from: z1.cuk$a */
    /* loaded from: classes3.dex */
    public static class C5140a {
        public static Class<?> TYPE = RefClass.load(C5140a.class, "android.content.pm.PackageInstaller$SessionInfo");
        public static RefBoolean active;
        public static RefObject<Bitmap> appIcon;
        public static RefObject<CharSequence> appLabel;
        public static RefObject<String> appPackageName;
        public static RefConstructor<PackageInstaller.SessionInfo> ctor;
        public static RefObject<String> installerPackageName;
        public static RefInt mode;
        public static RefFloat progress;
        public static RefObject<String> resolvedBaseCodePath;
        public static RefBoolean sealed;
        public static RefInt sessionId;
        public static RefLong sizeBytes;
    }

    /* compiled from: PackageInstaller.java */
    /* renamed from: z1.cuk$b */
    /* loaded from: classes3.dex */
    public static class C5141b {
        public static Class<?> TYPE = RefClass.load(C5141b.class, "android.content.pm.PackageInstaller$SessionParams");
        public static RefObject<String> abiOverride;
        public static RefObject<Bitmap> appIcon;
        public static RefLong appIconLastModified;
        public static RefObject<String> appLabel;
        public static RefObject<String> appPackageName;
        public static RefInt installFlags;
        public static RefInt installLocation;
        public static RefInt mode;
        public static RefObject<Uri> originatingUri;
        public static RefObject<Uri> referrerUri;
        public static RefLong sizeBytes;
    }

    /* compiled from: PackageInstaller.java */
    /* renamed from: z1.cuk$c */
    /* loaded from: classes3.dex */
    public static class C5142c {
        public static Class<?> TYPE = RefClass.load(C5142c.class, "android.content.pm.PackageInstaller$SessionParams");
        public static RefObject<String> abiOverride;
        public static RefObject<Bitmap> appIcon;
        public static RefLong appIconLastModified;
        public static RefObject<String> appLabel;
        public static RefObject<String> appPackageName;
        public static RefObject<String[]> grantedRuntimePermissions;
        public static RefInt installFlags;
        public static RefInt installLocation;
        public static RefInt mode;
        public static RefObject<Uri> originatingUri;
        public static RefObject<Uri> referrerUri;
        public static RefLong sizeBytes;
        public static RefObject<String> volumeUuid;
    }
}
