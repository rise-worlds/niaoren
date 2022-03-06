package p110z1;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;
import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.EncodeUtils;
import com.blankj.utilcode.util.Utils;
import com.common.utils.log.LogUtils;
import com.lbd.xj.socket.model.AppInfo;
import com.lbd.xj.socket.model.FileBean;
import com.lbd.xj.socket.model.FileInfo;
import com.lbd.xj.socket.model.FileType;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.ref.SoftReference;
import java.net.URLEncoder;
import java.text.Collator;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import org.apache.commons.mail.EmailConstants;

/* compiled from: FileUtil.java */
/* renamed from: z1.aee */
/* loaded from: classes3.dex */
public class aee {

    /* renamed from: a */
    public static Comparator<File> f15431a = new Comparator<File>() { // from class: z1.aee.1
        /* renamed from: a */
        public int compare(File file, File file2) {
            if (file.isDirectory() && file2.isFile()) {
                return -1;
            }
            if (!file.isFile() || !file2.isDirectory()) {
                return file.getName().compareTo(file2.getName());
            }
            return 1;
        }
    };

    /* renamed from: b */
    private static final Collator f15432b = Collator.getInstance(Locale.CHINA);

    /* renamed from: a */
    public static Drawable m14182a(String str, Context context) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageArchiveInfo = packageManager.getPackageArchiveInfo(str, 1);
        Drawable drawable = null;
        if (packageArchiveInfo == null) {
            return null;
        }
        ApplicationInfo applicationInfo = packageArchiveInfo.applicationInfo;
        applicationInfo.sourceDir = str;
        applicationInfo.publicSourceDir = str;
        packageManager.getApplicationLabel(applicationInfo).toString();
        try {
            drawable = packageManager.getApplicationIcon(applicationInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Drawable drawable2 = (Drawable) new SoftReference(drawable).get();
        String.format("PackageName:%s, Vesion: %s, AppName: %s", applicationInfo.packageName, packageArchiveInfo.versionName, packageManager.getApplicationLabel(applicationInfo).toString());
        return drawable2;
    }

    /* renamed from: a */
    public static ArrayList<FileBean> m14190a(Context context) {
        ArrayList<FileBean> arrayList = new ArrayList<>();
        ContentResolver contentResolver = context.getContentResolver();
        Cursor query = contentResolver.query(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, new String[]{"image_id", "_data"}, null, null, null);
        if (query.moveToFirst()) {
            do {
                FileBean d = m14173d(new File(query.getString(1)));
                d.setImageId(query.getInt(0));
                arrayList.add(d);
            } while (query.moveToNext());
            query.close();
        }
        for (int i = 0; i < arrayList.size(); i++) {
            Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            Cursor query2 = contentResolver.query(uri, new String[]{"_data"}, "_id=" + arrayList.get(i).getImageId() + "", null, null);
            LogUtils.m22036e("setVmosPath", "cursor.getCount();  " + query2.getCount() + "    cursor.getColumnCount()��" + query2.getColumnCount());
            if (query2.moveToFirst()) {
                do {
                    arrayList.get(i).setVmosPath(query2.getString(0));
                } while (query2.moveToNext());
                query2.close();
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    public static int m14184a(File file) {
        if (!file.isDirectory()) {
            return 0;
        }
        int i = 0;
        for (File file2 : file.listFiles()) {
            if (!file2.isHidden()) {
                i++;
            }
        }
        return i;
    }

    /* renamed from: b */
    public static int m14179b(File file) {
        if (file.isDirectory()) {
            return file.listFiles().length;
        }
        return 0;
    }

    /* renamed from: c */
    public static FileInfo m14176c(File file) {
        FileInfo fileInfo = new FileInfo();
        fileInfo.setFileName(file.getName());
        fileInfo.setFilePath(file.getPath());
        fileInfo.setFileSize(file.length());
        fileInfo.setDirectory(file.isDirectory());
        int lastIndexOf = file.getName().lastIndexOf(Consts.f23430h);
        if (lastIndexOf > 0) {
            fileInfo.setSuffix(file.getName().substring(lastIndexOf + 1));
        }
        return fileInfo;
    }

    /* renamed from: d */
    public static FileBean m14173d(File file) {
        FileBean fileBean = new FileBean();
        fileBean.setName(file.getName());
        fileBean.setPath(file.getPath());
        fileBean.setSize(file.length());
        fileBean.setFileType(FileType.image);
        return fileBean;
    }

    /* renamed from: e */
    public static FileType m14171e(File file) {
        if (file.isDirectory()) {
            return FileType.directory;
        }
        String lowerCase = file.getName().toLowerCase();
        if (lowerCase.contains(".mp3")) {
            return FileType.music;
        }
        if (lowerCase.contains(".mp4") || lowerCase.contains(".avi") || lowerCase.contains(".3gp") || lowerCase.contains(".mov") || lowerCase.contains(".rmvb") || lowerCase.contains(".mkv") || lowerCase.contains(".flv") || lowerCase.contains(".rm")) {
            return FileType.video;
        }
        if (lowerCase.contains(".txt") || lowerCase.contains(".log") || lowerCase.contains(".xml")) {
            return FileType.txt;
        }
        if (lowerCase.contains(".zip") || lowerCase.contains(".rar")) {
            return FileType.zip;
        }
        if (lowerCase.contains(".png") || lowerCase.contains(".gif") || lowerCase.contains(".jpeg") || lowerCase.contains(".jpg")) {
            return FileType.image;
        }
        if (lowerCase.contains(".apk")) {
            return FileType.apk;
        }
        return FileType.other;
    }

    /* renamed from: a */
    public static void m14189a(Context context, File file) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

    /* renamed from: b */
    public static void m14180b(Context context, File file) {
        Uri fromFile = Uri.fromFile(file);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setFlags(67108864);
        intent.setDataAndType(fromFile, "application/*");
        context.startActivity(intent);
    }

    /* renamed from: c */
    public static void m14177c(Context context, File file) {
        Uri fromFile = Uri.fromFile(file);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setDataAndType(fromFile, "image/*");
        intent.setFlags(67108864);
        context.startActivity(intent);
    }

    /* renamed from: d */
    public static void m14174d(Context context, File file) {
        Uri fromFile = Uri.fromFile(file);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setFlags(67108864);
        intent.setDataAndType(fromFile, "audio/*");
        context.startActivity(intent);
    }

    /* renamed from: e */
    public static void m14172e(Context context, File file) {
        Uri fromFile = Uri.fromFile(file);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setDataAndType(fromFile, "text/*");
        intent.setFlags(67108864);
        context.startActivity(intent);
    }

    /* renamed from: f */
    public static void m14170f(Context context, File file) {
        Uri fromFile = Uri.fromFile(file);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setFlags(67108864);
        intent.setDataAndType(fromFile, "video/*");
        context.startActivity(intent);
    }

    /* renamed from: b */
    public static ArrayList<FileBean> m14181b(Context context) {
        ArrayList<FileBean> arrayList = new ArrayList<>();
        Cursor query = context.getContentResolver().query(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, new String[]{"_id", "image_id", "_data"}, null, null, null);
        if (!query.moveToFirst()) {
            return arrayList;
        }
        do {
            int i = query.getInt(query.getColumnIndex("_id"));
            int i2 = query.getInt(query.getColumnIndex("image_id"));
            String string = query.getString(query.getColumnIndex("_data"));
            LogUtils.m22036e("queryAbbreviationImages", i + " image_id:" + i2 + " path:" + string + "---");
            arrayList.add(m14173d(new File(string)));
        } while (query.moveToNext());
        return arrayList;
    }

    /* renamed from: c */
    public static ArrayList<FileBean> m14178c(Context context) throws UnsupportedEncodingException {
        PackageInfo packageArchiveInfo;
        ArrayList<FileBean> arrayList = new ArrayList<>();
        Cursor cursor = null;
        try {
            cursor = context.getContentResolver().query(Uri.parse("content://media/external/file"), new String[]{"_id", "_data", "_size"}, "_data like ?", new String[]{"%.apk"}, null);
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    cursor.getString(cursor.getColumnIndex("_id"));
                    String string = cursor.getString(cursor.getColumnIndex("_data"));
                    String string2 = cursor.getString(cursor.getColumnIndex("_size"));
                    FileBean fileBean = new FileBean();
                    try {
                        fileBean.setDrawable(URLEncoder.encode(EncodeUtils.m22387b(ConvertUtils.m22465a(m14182a(string, context), Bitmap.CompressFormat.PNG)), EmailConstants.UTF_8));
                        fileBean.setPath(string);
                        fileBean.setSize(Long.parseLong(string2));
                        packageArchiveInfo = context.getPackageManager().getPackageArchiveInfo(string, 1);
                    } catch (Exception e) {
                        fileBean.setSize(0L);
                        e.printStackTrace();
                    }
                    if (packageArchiveInfo != null) {
                        fileBean.setPackageName(packageArchiveInfo.applicationInfo.packageName);
                        fileBean.setVersionCode(packageArchiveInfo.versionCode);
                        fileBean.setVersionName(packageArchiveInfo.versionName);
                        fileBean.setName(string.substring(string.lastIndexOf("/") + 1));
                        arrayList.add(fileBean);
                    }
                } while (cursor.moveToNext());
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (cursor != null) {
            cursor.close();
        }
        return arrayList;
    }

    /* renamed from: a */
    public static ArrayList<FileBean> m14188a(Context context, String str) throws UnsupportedEncodingException {
        ArrayList<FileBean> arrayList = new ArrayList<>();
        Cursor query = context.getContentResolver().query(Uri.parse("content://media/external/file"), new String[]{"_id", "_data", "_size"}, "_data like ?", new String[]{"%.apk"}, null);
        if (query != null) {
            try {
                if (query.moveToFirst()) {
                    do {
                        query.getString(query.getColumnIndex("_id"));
                        String string = query.getString(query.getColumnIndex("_data"));
                        String string2 = query.getString(query.getColumnIndex("_size"));
                        FileBean fileBean = new FileBean();
                        try {
                            fileBean.setPath(string);
                            fileBean.setSize(Long.parseLong(string2));
                        } catch (Exception e) {
                            fileBean.setSize(0L);
                            e.printStackTrace();
                        }
                        fileBean.setName(string.substring(string.lastIndexOf("/") + 1));
                        arrayList.add(fileBean);
                    } while (query.moveToNext());
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            query.close();
        }
        return arrayList;
    }

    /* renamed from: d */
    public static ArrayList<FileBean> m14175d(Context context) {
        ArrayList<FileBean> arrayList = new ArrayList<>();
        ContentResolver contentResolver = context.getContentResolver();
        Cursor query = contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{"_data", "_display_name", "date_added", "_id"}, "mime_type=? or mime_type=? or mime_type=?", new String[]{"image/jpeg", "image/png", "image/gif"}, "date_added DESC");
        if (query != null) {
            if (query.moveToFirst()) {
                do {
                    String string = query.getString(query.getColumnIndexOrThrow("_data"));
                    if (!TextUtils.isEmpty(string) && new File(string).exists()) {
                        FileBean d = m14173d(new File(string));
                        d.setImageId(query.getInt(query.getColumnIndexOrThrow("_id")));
                        arrayList.add(d);
                    }
                } while (query.moveToNext());
                query.close();
            } else {
                query.close();
            }
        }
        for (int i = 0; i < arrayList.size(); i++) {
            Uri uri = MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI;
            Cursor query2 = contentResolver.query(uri, new String[]{"_data"}, "image_id=" + arrayList.get(i).getImageId() + "", null, null);
            if (query2.moveToFirst()) {
                do {
                    arrayList.get(i).setVmosPath(query2.getString(0));
                } while (query2.moveToNext());
                query2.close();
            }
        }
        return arrayList;
    }

    /* renamed from: g */
    public static void m14169g(Context context, File file) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.putExtra("android.intent.extra.STREAM", Uri.fromFile(file));
        intent.setType("*/*");
        context.startActivity(Intent.createChooser(intent, "����"));
    }

    /* renamed from: a */
    public static String m14191a(long j) {
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        double d = (j * 1.0d) / 1024.0d;
        double d2 = d / 1024.0d;
        double d3 = d2 / 1024.0d;
        if (d3 >= 1.0d) {
            return decimalFormat.format(d3) + " GB";
        } else if (d2 >= 1.0d) {
            return decimalFormat.format(d2) + " MB";
        } else if (d >= 1.0d) {
            return decimalFormat.format(d) + " KB";
        } else {
            return j + " B";
        }
    }

    /* renamed from: a */
    public static List<AppInfo> m14187a(Context context, List<PackageInfo> list, boolean z, boolean z2) {
        PackageManager packageManager = context.getPackageManager();
        ArrayList arrayList = new ArrayList(list.size());
        LogUtils.m22038d("sunya", "applist = " + list);
        for (PackageInfo packageInfo : list) {
            if (!context.getPackageName().equals(packageInfo.packageName) && (!z || !m14186a(packageInfo))) {
                if ((packageInfo.applicationInfo.flags & 4) == 0) {
                    LogUtils.m22038d("sunya", "skipapplist " + packageInfo.packageName + " by FLAG_HAS_CODE");
                } else {
                    ApplicationInfo applicationInfo = packageInfo.applicationInfo;
                    String str = applicationInfo.publicSourceDir != null ? applicationInfo.publicSourceDir : applicationInfo.sourceDir;
                    if (str == null) {
                        LogUtils.m22038d("sunya", "skipapplist " + packageInfo.packageName + " by path=null");
                    } else {
                        AppInfo appInfo = new AppInfo();
                        appInfo.packageName = packageInfo.packageName;
                        appInfo.path = str;
                        appInfo.appName = applicationInfo.loadLabel(packageManager).toString();
                        appInfo.versionName = packageInfo.versionName;
                        appInfo.versionCode = packageInfo.versionCode;
                        try {
                            appInfo.appIcon = URLEncoder.encode(EncodeUtils.m22387b(ConvertUtils.m22465a(packageManager.getApplicationIcon(applicationInfo), Bitmap.CompressFormat.PNG)), EmailConstants.UTF_8);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        arrayList.add(appInfo);
                    }
                }
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    public static AppInfo m14183a(String str) {
        try {
            PackageManager packageManager = Utils.m24103a().getPackageManager();
            if (packageManager == null) {
                return null;
            }
            return m14185a(packageManager, packageManager.getPackageInfo(str, 0));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    private static AppInfo m14185a(PackageManager packageManager, PackageInfo packageInfo) {
        if (packageInfo == null) {
            return null;
        }
        ApplicationInfo applicationInfo = packageInfo.applicationInfo;
        AppInfo appInfo = new AppInfo();
        appInfo.packageName = packageInfo.packageName;
        appInfo.path = applicationInfo.sourceDir;
        appInfo.appName = applicationInfo.loadLabel(packageManager).toString();
        appInfo.versionName = packageInfo.versionName;
        appInfo.versionCode = packageInfo.versionCode;
        try {
            appInfo.appIcon = URLEncoder.encode(EncodeUtils.m22387b(ConvertUtils.m22465a(packageManager.getApplicationIcon(applicationInfo), Bitmap.CompressFormat.PNG)), EmailConstants.UTF_8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return appInfo;
    }

    /* renamed from: a */
    private static boolean m14186a(PackageInfo packageInfo) {
        int i = packageInfo.applicationInfo.uid;
        return i < 10000 || i > 19999 || (packageInfo.applicationInfo.flags & 1) != 0;
    }
}
