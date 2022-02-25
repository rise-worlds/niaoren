package p110z1;

import android.annotation.TargetApi;
import android.os.IInterface;
import android.provider.Settings;
import mirror.RefClass;
import mirror.RefObject;
import mirror.RefStaticObject;

/* compiled from: Settings.java */
/* renamed from: z1.cwt */
/* loaded from: classes3.dex */
public class cwt {
    public static Class<?> TYPE = RefClass.load(cwt.class, Settings.class);

    /* compiled from: Settings.java */
    /* renamed from: z1.cwt$a */
    /* loaded from: classes3.dex */
    public static class C5177a {
        public static Class<?> TYPE = RefClass.load(C5177a.class, "android.provider.Settings$ContentProviderHolder");
        public static RefObject<IInterface> mContentProvider;
    }

    /* compiled from: Settings.java */
    @TargetApi(17)
    /* renamed from: z1.cwt$b */
    /* loaded from: classes3.dex */
    public static class C5178b {
        public static Class<?> TYPE = RefClass.load(C5178b.class, Settings.Global.class);
        public static RefStaticObject<Object> sNameValueCache;
    }

    /* compiled from: Settings.java */
    /* renamed from: z1.cwt$c */
    /* loaded from: classes3.dex */
    public static class C5179c {
        public static Class<?> TYPE = RefClass.load(C5179c.class, "android.provider.Settings$NameValueCache");
        public static RefObject<Object> mContentProvider;
    }

    /* compiled from: Settings.java */
    /* renamed from: z1.cwt$d */
    /* loaded from: classes3.dex */
    public static class C5180d {
        public static Class<?> TYPE = RefClass.load(C5180d.class, "android.provider.Settings$NameValueCache");
        public static RefObject<Object> mProviderHolder;
    }

    /* compiled from: Settings.java */
    /* renamed from: z1.cwt$e */
    /* loaded from: classes3.dex */
    public static class C5181e {
        public static Class<?> TYPE = RefClass.load(C5181e.class, Settings.Secure.class);
        public static RefStaticObject<Object> sNameValueCache;
    }

    /* compiled from: Settings.java */
    /* renamed from: z1.cwt$f */
    /* loaded from: classes3.dex */
    public static class C5182f {
        public static Class<?> TYPE = RefClass.load(C5182f.class, Settings.System.class);
        public static RefStaticObject<Object> sNameValueCache;
    }
}
