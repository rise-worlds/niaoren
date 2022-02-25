package com.tencent.smtt.utils;

import android.content.ComponentName;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.content.res.XmlResourceParser;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.MimeTypeMap;
import com.lody.virtual.client.ipc.ServiceManagerNative;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.TbsConfig;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.p105io.IOUtils;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes2.dex */
public class FileProvider extends ContentProvider {

    /* renamed from: a */
    private static final String[] f13255a = {"_display_name", "_size"};

    /* renamed from: b */
    private static final File f13256b = new File("/");

    /* renamed from: c */
    private static HashMap<String, AbstractC2633a> f13257c = new HashMap<>();

    /* renamed from: d */
    private AbstractC2633a f13258d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.tencent.smtt.utils.FileProvider$a */
    /* loaded from: classes2.dex */
    public interface AbstractC2633a {
        /* renamed from: a */
        Uri mo16537a(File file);

        /* renamed from: a */
        File mo16538a(Uri uri);
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        return true;
    }

    @Override // android.content.ContentProvider
    public void attachInfo(Context context, ProviderInfo providerInfo) {
        super.attachInfo(context, providerInfo);
        if (providerInfo.exported) {
            throw new SecurityException("Provider must not be exported");
        } else if (providerInfo.grantUriPermissions) {
            this.f13258d = m16540b(context, providerInfo.authority);
        } else {
            throw new SecurityException("Provider must grant uri permissions");
        }
    }

    /* renamed from: a */
    public static Uri m16545a(Context context, String str, File file) {
        return m16540b(context, str).mo16537a(file);
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        File a = this.f13258d.mo16538a(uri);
        if (strArr == null) {
            strArr = f13255a;
        }
        String[] strArr3 = new String[strArr.length];
        Object[] objArr = new Object[strArr.length];
        int i = 0;
        for (String str3 : strArr) {
            if ("_display_name".equals(str3)) {
                strArr3[i] = "_display_name";
                i++;
                objArr[i] = a.getName();
            } else if ("_size".equals(str3)) {
                strArr3[i] = "_size";
                i++;
                objArr[i] = Long.valueOf(a.length());
            }
        }
        String[] a2 = m16541a(strArr3, i);
        Object[] a3 = m16542a(objArr, i);
        MatrixCursor matrixCursor = new MatrixCursor(a2, 1);
        matrixCursor.addRow(a3);
        return matrixCursor;
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        File a = this.f13258d.mo16538a(uri);
        int lastIndexOf = a.getName().lastIndexOf(46);
        if (lastIndexOf < 0) {
            return "application/octet-stream";
        }
        String mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(a.getName().substring(lastIndexOf + 1));
        return mimeTypeFromExtension != null ? mimeTypeFromExtension : "application/octet-stream";
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        throw new UnsupportedOperationException("No external inserts");
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        throw new UnsupportedOperationException("No external updates");
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        return this.f13258d.mo16538a(uri).delete() ? 1 : 0;
    }

    @Override // android.content.ContentProvider
    public ParcelFileDescriptor openFile(Uri uri, String str) throws FileNotFoundException {
        return ParcelFileDescriptor.open(this.f13258d.mo16538a(uri), m16543a(str));
    }

    /* renamed from: b */
    private static AbstractC2633a m16540b(Context context, String str) {
        AbstractC2633a aVar;
        synchronized (f13257c) {
            aVar = f13257c.get(str);
            if (aVar == null) {
                try {
                    aVar = m16539c(context, str);
                    f13257c.put(str, aVar);
                } catch (IOException e) {
                    throw new IllegalArgumentException("Failed to parse android.support.FILE_PROVIDER_PATHS meta-data", e);
                } catch (XmlPullParserException e2) {
                    throw new IllegalArgumentException("Failed to parse android.support.FILE_PROVIDER_PATHS meta-data", e2);
                }
            }
        }
        return aVar;
    }

    /* renamed from: c */
    private static AbstractC2633a m16539c(Context context, String str) throws IOException, XmlPullParserException {
        C2634b bVar = new C2634b(str);
        ProviderInfo resolveContentProvider = context.getPackageManager().resolveContentProvider(str, 128);
        if (resolveContentProvider != null) {
            XmlResourceParser loadXmlMetaData = resolveContentProvider.loadXmlMetaData(context.getPackageManager(), "android.support.FILE_PROVIDER_PATHS");
            if (loadXmlMetaData != null) {
                while (true) {
                    int next = loadXmlMetaData.next();
                    if (next == 1) {
                        return bVar;
                    }
                    if (next == 2) {
                        String name = loadXmlMetaData.getName();
                        File file = null;
                        String attributeValue = loadXmlMetaData.getAttributeValue(null, "name");
                        String attributeValue2 = loadXmlMetaData.getAttributeValue(null, "path");
                        if ("root-path".equals(name)) {
                            file = m16544a(f13256b, attributeValue2);
                        } else if ("files-path".equals(name)) {
                            file = m16544a(context.getFilesDir(), attributeValue2);
                        } else if ("cache-path".equals(name)) {
                            file = m16544a(context.getCacheDir(), attributeValue2);
                        } else if ("external-path".equals(name)) {
                            file = m16544a(Environment.getExternalStorageDirectory(), attributeValue2);
                        }
                        if (file != null) {
                            bVar.m16536a(attributeValue, file);
                        }
                    }
                }
            } else {
                throw new IllegalArgumentException("Missing android.support.FILE_PROVIDER_PATHS meta-data");
            }
        } else {
            throw new RuntimeException("Must declare com.tencent.smtt.utils.FileProvider in AndroidManifest above Android 7.0,please view document in x5.tencent.com");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.tencent.smtt.utils.FileProvider$b */
    /* loaded from: classes2.dex */
    public static class C2634b implements AbstractC2633a {

        /* renamed from: a */
        private final String f13259a;

        /* renamed from: b */
        private final HashMap<String, File> f13260b = new HashMap<>();

        public C2634b(String str) {
            this.f13259a = str;
        }

        /* renamed from: a */
        public void m16536a(String str, File file) {
            if (!TextUtils.isEmpty(str)) {
                try {
                    this.f13260b.put(str, file.getCanonicalFile());
                } catch (IOException e) {
                    throw new IllegalArgumentException("Failed to resolve canonical path for " + file, e);
                }
            } else {
                throw new IllegalArgumentException("Name must not be empty");
            }
        }

        @Override // com.tencent.smtt.utils.FileProvider.AbstractC2633a
        /* renamed from: a */
        public Uri mo16537a(File file) {
            String str;
            try {
                String canonicalPath = file.getCanonicalPath();
                Map.Entry<String, File> entry = null;
                for (Map.Entry<String, File> entry2 : this.f13260b.entrySet()) {
                    String path = entry2.getValue().getPath();
                    if (canonicalPath.startsWith(path) && (entry == null || path.length() > entry.getValue().getPath().length())) {
                        entry = entry2;
                    }
                }
                if (entry != null) {
                    String path2 = entry.getValue().getPath();
                    if (path2.endsWith("/")) {
                        str = canonicalPath.substring(path2.length());
                    } else {
                        str = canonicalPath.substring(path2.length() + 1);
                    }
                    return new Uri.Builder().scheme(ServiceManagerNative.CONTENT).authority(this.f13259a).encodedPath(Uri.encode(entry.getKey()) + IOUtils.DIR_SEPARATOR_UNIX + Uri.encode(str, "/")).build();
                }
                throw new IllegalArgumentException("Failed to find configured root that contains " + canonicalPath);
            } catch (IOException unused) {
                throw new IllegalArgumentException("Failed to resolve canonical path for " + file);
            }
        }

        @Override // com.tencent.smtt.utils.FileProvider.AbstractC2633a
        /* renamed from: a */
        public File mo16538a(Uri uri) {
            String encodedPath = uri.getEncodedPath();
            int indexOf = encodedPath.indexOf(47, 1);
            String decode = Uri.decode(encodedPath.substring(1, indexOf));
            String decode2 = Uri.decode(encodedPath.substring(indexOf + 1));
            File file = this.f13260b.get(decode);
            if (file != null) {
                File file2 = new File(file, decode2);
                try {
                    File canonicalFile = file2.getCanonicalFile();
                    if (canonicalFile.getPath().startsWith(file.getPath())) {
                        return canonicalFile;
                    }
                    throw new SecurityException("Resolved path jumped beyond configured root");
                } catch (IOException unused) {
                    throw new IllegalArgumentException("Failed to resolve canonical path for " + file2);
                }
            } else {
                throw new IllegalArgumentException("Unable to find configured root for " + uri);
            }
        }
    }

    /* renamed from: a */
    private static int m16543a(String str) {
        if ("r".equals(str)) {
            return 268435456;
        }
        if ("w".equals(str) || "wt".equals(str)) {
            return 738197504;
        }
        if ("wa".equals(str)) {
            return 704643072;
        }
        if ("rw".equals(str)) {
            return 939524096;
        }
        if ("rwt".equals(str)) {
            return 1006632960;
        }
        throw new IllegalArgumentException("Invalid mode: " + str);
    }

    /* renamed from: a */
    private static File m16544a(File file, String... strArr) {
        for (String str : strArr) {
            if (str != null) {
                file = new File(file, str);
            }
        }
        return file;
    }

    /* renamed from: a */
    private static String[] m16541a(String[] strArr, int i) {
        String[] strArr2 = new String[i];
        System.arraycopy(strArr, 0, strArr2, 0, i);
        return strArr2;
    }

    /* renamed from: a */
    private static Object[] m16542a(Object[] objArr, int i) {
        Object[] objArr2 = new Object[i];
        System.arraycopy(objArr, 0, objArr2, 0, i);
        return objArr2;
    }

    /* renamed from: a */
    static Uri m16547a(Context context, File file) {
        Method declaredMethod;
        String str = "";
        if (Build.VERSION.SDK_INT < 24) {
            return null;
        }
        try {
            str = context.getPackageManager().getProviderInfo(new ComponentName(context.getPackageName(), "android.support.v4.content.FileProvider"), 0).authority;
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            Class<?> cls = Class.forName("android.support.v4.content.FileProvider");
            if (cls == null || (declaredMethod = cls.getDeclaredMethod("getUriForFile", Context.class, String.class, File.class)) == null) {
                return null;
            }
            Object invoke = declaredMethod.invoke(null, context, str, file);
            if (invoke instanceof Uri) {
                return (Uri) invoke;
            }
            return null;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static Uri m16546a(Context context, String str) {
        Uri uri = null;
        if (context == null || context.getApplicationContext() == null || !TbsConfig.APP_QQ.equals(context.getApplicationContext().getApplicationInfo().packageName)) {
            if (context != null && context.getApplicationInfo().targetSdkVersion >= 24 && Build.VERSION.SDK_INT >= 24 && (uri = m16547a(context, new File(str))) == null && QbSdk.checkContentProviderPrivilage(context)) {
                uri = m16545a(context, context.getApplicationInfo().packageName + ".provider", new File(str));
            }
            if (uri != null) {
                return uri;
            }
            try {
                return Uri.fromFile(new File(str));
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("FileProvider", "create uri failed,please check again");
                return uri;
            }
        } else {
            try {
                return (Uri) ReflectionUtils.m16407a(Class.forName("com.tencent.mobileqq.utils.kapalaiadapter.FileProvider7Helper"), "getUriForFile", (Class<?>[]) new Class[]{Context.class, File.class}, context, new File(str));
            } catch (Exception e2) {
                e2.printStackTrace();
                return null;
            }
        }
    }
}
