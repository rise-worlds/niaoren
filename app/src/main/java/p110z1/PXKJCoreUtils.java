package p110z1;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.p003v4.content.FileProvider;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceView;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.p061os.VEnvironment;
import com.lody.virtual.remote.InstalledAppInfo;
import com.lody.virtual.server.p063pm.PackageSetting;
import com.lody.virtual.server.p063pm.parser.VPackage;
import com.nrzs.core.models.PackageAppData;
import com.nrzs.data.game.bean.VAGameScreenAdapterInfo;
import com.nrzs.p072va.AppInstallResult;
import com.nrzs.p072va.VirtualCoreProxy;
import com.tencent.smtt.sdk.TbsConfig;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* renamed from: z1.aji */
/* loaded from: classes3.dex */
public class PXKJCoreUtils {

    /* renamed from: a */
    public static final String f16059a = Build.MODEL.toLowerCase();

    /* renamed from: b */
    public static final String f16060b = Build.BRAND.toLowerCase();

    /* renamed from: c */
    public static LinkedList<Activity> f16061c;

    /* renamed from: a */
    public static void m12925a(VPackage vPackage, PackageSetting packageSetting) {
        if (vPackage.packageName.equals(TbsConfig.APP_WX)) {
            packageSetting.flag = 0;
            if (new File("/sdcard/" + vPackage.mVersionName + "/").exists()) {
                m12922a("/sdcard/" + vPackage.mVersionName + "/", VEnvironment.getAppLibDirectory(vPackage.packageName).getAbsolutePath());
                return;
            }
            AppInstallResult.makeFailure("当前微信包是64位，未找到对应的32位微信版本为(" + vPackage.mVersionName + ")的库。");
        }
    }

    /* renamed from: a */
    private static int m12922a(String str, String str2) {
        if (!str2.substring(str2.length() - 1).equals("/")) {
            str2 = str2 + "/";
        }
        File file = new File(str);
        if (!file.exists()) {
            return -1;
        }
        File[] listFiles = file.listFiles();
        File file2 = new File(str2);
        if (!file2.exists()) {
            file2.mkdirs();
        }
        for (int i = 0; i < listFiles.length; i++) {
            if (listFiles[i].isDirectory()) {
                m12922a(listFiles[i].getPath() + "/", str2 + listFiles[i].getName() + "/");
            } else {
                m12920b(listFiles[i].getPath(), str2 + listFiles[i].getName());
            }
        }
        return 0;
    }

    /* renamed from: b */
    private static int m12920b(String str, String str2) {
        try {
            FileInputStream fileInputStream = new FileInputStream(str);
            FileOutputStream fileOutputStream = new FileOutputStream(str2);
            byte[] bArr = new byte[1024];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read > 0) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    fileInputStream.close();
                    fileOutputStream.close();
                    return 0;
                }
            }
        } catch (Exception unused) {
            return -1;
        }
    }

    /* renamed from: a */
    public static int m12927a(Context context, String str) {
        Uri uri;
        if (VirtualCoreProxy.isEngineInstalled()) {
            return 0;
        }
        m12926a(context, "1.a", str, "1.apk");
        String str2 = str + "1.apk";
        Intent intent = new Intent("android.intent.action.VIEW");
        try {
            if (Build.VERSION.SDK_INT >= 24) {
                uri = FileProvider.getUriForFile(context, "com.angel.nrzs.fileprovider", new File(str2));
                intent.setAction("android.intent.action.INSTALL_PACKAGE");
                intent.addFlags(1);
                intent.addFlags(64);
            } else {
                uri = Uri.fromFile(new File(str2));
                intent.setAction("android.intent.action.VIEW");
                intent.setFlags(268435456);
            }
            intent.setDataAndType(uri, "application/vnd.android.package-archive");
            context.startActivity(intent);
            return 1;
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            return -1;
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
            return -1;
        } catch (Exception e3) {
            e3.printStackTrace();
            return -1;
        }
    }

    /* renamed from: a */
    public static boolean m12926a(Context context, String str, String str2, String str3) {
        DataInputStream dataInputStream;
        Throwable th;
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2 = null;
        try {
            dataInputStream = new DataInputStream(context.getAssets().open(str));
            try {
                m12924a(str2);
                File file = new File(str2, str3);
                if (file.exists()) {
                    file.delete();
                }
                fileOutputStream = new FileOutputStream(file);
            } catch (Exception unused) {
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception unused2) {
            dataInputStream = null;
        } catch (Throwable th3) {
            th = th3;
            dataInputStream = null;
        }
        try {
            byte[] bArr = new byte[1024];
            while (true) {
                int read = dataInputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                fileOutputStream.write(bArr, 0, read);
            }
            fileOutputStream.flush();
            try {
                fileOutputStream.close();
                dataInputStream.close();
            } catch (Exception unused3) {
            }
            return true;
        } catch (Exception unused4) {
            fileOutputStream2 = fileOutputStream;
            if (fileOutputStream2 != null) {
                try {
                    fileOutputStream2.close();
                } catch (Exception unused5) {
                    return false;
                }
            }
            if (dataInputStream != null) {
                dataInputStream.close();
            }
            return false;
        } catch (Throwable th4) {
            th = th4;
            fileOutputStream2 = fileOutputStream;
            if (fileOutputStream2 != null) {
                try {
                    fileOutputStream2.close();
                } catch (Exception unused6) {
                    throw th;
                }
            }
            if (dataInputStream != null) {
                dataInputStream.close();
            }
            throw th;
        }
    }

    /* renamed from: a */
    public static void m12924a(String str) {
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    /* renamed from: a */
    public static void m12923a(String str, int i) {
        PackageAppData a = PackageAppDataStorage.m12941a().m12937c(str);
        if (a != null) {
            a.f10574e = false;
            FloatLoadingAppDialog.m12062a(GameApp.m13000d().m13006b(), i, a.f10570a, a);
        }
    }

    /* renamed from: a */
    public static void m12930a() {
        List<InstalledAppInfo> installedApps = VirtualCore.get().getInstalledApps(0);
        if (installedApps != null && installedApps.size() > 0) {
            Iterator<InstalledAppInfo> it = installedApps.iterator();
            while (it.hasNext()) {
                Log.i("LBS_GETPACKAGEINFO", "installedAppInfo:" + it.next().toString());
            }
        }
    }

    /* renamed from: a */
    public static void m12929a(Activity activity) {
        List<VAGameScreenAdapterInfo> list = (List) apa.m11877a(apf.m11837b(GameApp.m13000d().m13006b(), ShareVal.f16591a, ShareVal.f16613w, ""), new TypeToken<List<VAGameScreenAdapterInfo>>() { // from class: z1.aji.1
        });
        if (!(list == null || list.isEmpty())) {
            for (VAGameScreenAdapterInfo vAGameScreenAdapterInfo : list) {
                if (activity.getComponentName().getClassName().equals(vAGameScreenAdapterInfo.name) && f16060b.equals(vAGameScreenAdapterInfo.brand) && f16059a.equals(vAGameScreenAdapterInfo.model)) {
                    try {
                        SurfaceView surfaceView = (SurfaceView) ((ViewGroup) ((FrameLayout) activity.getWindow().getDecorView().findViewById(16908290)).getChildAt(0)).getChildAt(0);
                        ViewGroup.LayoutParams layoutParams = surfaceView.getLayoutParams();
                        layoutParams.height = apf.m11838b(GameApp.m13000d().m13006b(), ShareVal.f16591a, ShareVal.f16612v, 0);
                        layoutParams.width = apf.m11838b(GameApp.m13000d().m13006b(), ShareVal.f16591a, ShareVal.f16611u, 0);
                        surfaceView.setLayoutParams(layoutParams);
                        return;
                    } catch (Exception unused) {
                        return;
                    }
                }
            }
        }
    }

    /* renamed from: a */
    public static void m12928a(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        if (Build.VERSION.SDK_INT >= 17) {
            windowManager.getDefaultDisplay().getRealMetrics(displayMetrics);
            apf.m11843a(context, ShareVal.f16608r, ShareVal.f16611u, displayMetrics.widthPixels > displayMetrics.heightPixels ? displayMetrics.widthPixels : displayMetrics.heightPixels);
            apf.m11843a(context, ShareVal.f16608r, ShareVal.f16612v, displayMetrics.widthPixels <= displayMetrics.heightPixels ? displayMetrics.widthPixels : displayMetrics.heightPixels);
        }
    }

    /* renamed from: b */
    public static void m12921b(Activity activity) {
        if (f16061c == null) {
            f16061c = new LinkedList<>();
        }
        f16061c.add(activity);
    }

    /* renamed from: c */
    public static void m12919c(Activity activity) {
        LinkedList<Activity> linkedList = f16061c;
        if (linkedList != null) {
            linkedList.remove(activity);
            if (f16061c.size() == 0) {
                Log.d("sunya", "exit when no Activity");
                System.exit(0);
            }
        }
    }
}
