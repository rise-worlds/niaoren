package com.lbd.p054xj.manager.launch;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.net.LocalServerSocket;
import android.net.LocalSocket;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Process;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import com.common.utils.VMCommonConfig;
import com.common.utils.VMProperUtil;
import com.common.utils.log.LogUtils;
import com.lbd.p054xj.app.AppConfig;
import com.lbd.p054xj.app.XJApp;
import com.lbd.p054xj.device.lcd.DisplayParam;
import com.lbd.p054xj.p056ui.activity.XJRenderActivity;
import com.lbd.p054xj.socket.C1545f;
import com.lbd.p054xj.socket.model.AppInfo;
import com.lbd.p054xj.zygote_service.ManagerServiceUtil;
import com.lbd.p054xj.zygote_service.MyService;
import com.tencent.smtt.sdk.TbsConfig;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Iterator;
import p110z1.C4963cj;
import p110z1.DeleteFileUtil;
import p110z1.FileWriteUtils;
import p110z1.GeneralUtil;
import p110z1.PreferencesUtil;
import p110z1.SharepreferenceUtil;
import p110z1.UnZipListener;
import p110z1.UnZipTools;
import p110z1.VersionHelper;
import p110z1.acf;
import p110z1.aee;
import p110z1.aef;
import p110z1.aeo;
import p110z1.aes;

/* renamed from: com.lbd.xj.manager.launch.BoxLaunchManager */
/* loaded from: classes.dex */
public class BoxLaunchManager {
    private static final int DEFAULT_TYPE = 1;
    private static final String ROOM_FILE = "rootfs.zip";
    private static final String TAG = "BoxLaunchManager";
    public static AudioTrack mAudioTrack;
    private static Thread mThreadVirtual;
    public static int mbuffersize;
    private BoxLaunchCallback callBack;
    private DisplayParam displayParam;
    boolean isRecording;
    LocalServerSocket lsssheng_break;
    RecordAudio recordAudio;
    int sampleRateInHz;
    LocalSocket socket;
    public String strDataDir;
    private int unZipProgress;
    public static File rootFile = new File(XJApp.getInstance().getApplicationInfo().dataDir, "osimg");
    public static int none = 0;
    public boolean isHuawei = false;
    private UnZipListener zipListener = new UnZipListener() { // from class: com.lbd.xj.manager.launch.BoxLaunchManager.1
        @Override // p110z1.UnZipListener
        public void unZipFail() {
            LogUtils.m22037e("解压失败");
            if (BoxLaunchManager.this.callBack != null) {
                BoxLaunchManager.this.callBack.upZipFail();
            }
        }

        @Override // p110z1.UnZipListener
        public void unZipProgress(int i) {
            LogUtils.m22037e("解压进度===" + i);
            BoxLaunchManager.this.updateProgress((int) ((((double) i) * 0.65d) + 30.0d));
        }

        @Override // p110z1.UnZipListener
        public void unZipStart() {
            LogUtils.m22039d("unZipStart ");
        }

        @Override // p110z1.UnZipListener
        public void unZipSuccess() {
            LogUtils.m22039d("unZipSuccess ");
            DeleteFileUtil.m14195a(SharepreferenceUtil.m13882b(acf.f15178c, ""));
            SharepreferenceUtil.m13891a(acf.f15178c, "");
            BoxLaunchManager.this.copyTxApp();
            int a = PreferencesUtil.m13937a().m13933a(acf.f15181f, 0);
            String a2 = PreferencesUtil.m13937a().m13930a(acf.f15180e, "");
            if (a <= VersionHelper.m13827c().f15480b || TextUtils.isEmpty(a2)) {
                if (BoxLaunchManager.this.callBack != null) {
                    BoxLaunchManager.this.callBack.unZipSucceed();
                }
                BoxLaunchManager.this.afterUnzip(BoxLaunchManager.rootFile);
            } else {
                UnZipTools a3 = UnZipTools.m13824a();
                a3.m13822a(a2, BoxLaunchManager.rootFile.getPath() + "/r", "rootfs", "ot01", BoxLaunchManager.this.patchListener);
            }
            BoxLaunchManager.this.patchZip();
        }
    };
    private UnZipListener patchListener = new UnZipListener() { // from class: com.lbd.xj.manager.launch.BoxLaunchManager.3
        @Override // p110z1.UnZipListener
        public void unZipStart() {
        }

        @Override // p110z1.UnZipListener
        public void unZipFail() {
            LogUtils.m22037e("解压失败");
            if (BoxLaunchManager.this.callBack != null) {
                BoxLaunchManager.this.callBack.upZipFail();
            }
            PreferencesUtil.m13937a().m13931a(acf.f15181f, (Object) 0);
            BoxLaunchManager.this.initVirtualMachine();
        }

        @Override // p110z1.UnZipListener
        public void unZipProgress(int i) {
            LogUtils.m22037e("解压进度===" + i);
            BoxLaunchManager.this.updateProgress((int) ((((double) i) * 0.65d) + 30.0d));
        }

        @Override // p110z1.UnZipListener
        public void unZipSuccess() {
            if (BoxLaunchManager.this.callBack != null) {
                BoxLaunchManager.this.callBack.unZipSucceed();
            }
            DeleteFileUtil.m14195a(BoxLaunchManager.rootFile + "/patch_done");
            int a = PreferencesUtil.m13937a().m13933a(acf.f15181f, 0);
            LogUtils.m22037e("patchVersion:" + a);
            SharepreferenceUtil.m13894a(acf.f15179d, a);
            BoxLaunchManager.this.afterUnzip(BoxLaunchManager.rootFile);
        }
    };

    /* renamed from: com.lbd.xj.manager.launch.BoxLaunchManager$BoxLaunchCallback */
    /* loaded from: classes.dex */
    public interface BoxLaunchCallback {
        void launchReady();

        void patchUnZip();

        void unZipProgress(int i);

        void unZipSucceed();

        void upZipFail();
    }

    public static native int getbootanim();

    public native int check_gralloc_version();

    public native int deRootfs(String str);

    public native int fixLink(String str);

    public native String getgrallockeys(String str, String str2, String str3, String str4);

    public native int killuid(int i);

    public native int recording_write(byte[] bArr, int i);

    public native boolean startAudioPlayer(String str);

    public native int startOneProcess(int i);

    public native int startst(String str);

    public native int zygote_mgr(String str);

    static {
        System.loadLibrary(AppConfig.f9439f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void patchZip() {
        if (AppConfig.f9435b) {
            final File file = new File("/sdcard/patch.zip");
            if (file.exists()) {
                UnZipTools a = UnZipTools.m13824a();
                a.m13822a("/sdcard/patch.zip", rootFile.getPath() + "/r", "rootfs", "ot01", new UnZipListener() { // from class: com.lbd.xj.manager.launch.BoxLaunchManager.2
                    @Override // p110z1.UnZipListener
                    public void unZipFail() {
                    }

                    @Override // p110z1.UnZipListener
                    public void unZipProgress(int i) {
                    }

                    @Override // p110z1.UnZipListener
                    public void unZipStart() {
                    }

                    @Override // p110z1.UnZipListener
                    public void unZipSuccess() {
                        file.delete();
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void copyTxApp() {
        LogUtils.m22039d("copyTxApp com.tencent.mm");
        AppInfo a = aee.m14183a(TbsConfig.APP_WX);
        if (a != null) {
            aes.m13865a(TbsConfig.APP_WX, a.path, XJApp.getInstance().getApplicationInfo());
        }
        LogUtils.m22039d("copyTxApp com.tencent.mobileqq");
        AppInfo a2 = aee.m14183a(TbsConfig.APP_QQ);
        if (a2 != null) {
            aes.m13865a(TbsConfig.APP_QQ, a2.path, XJApp.getInstance().getApplicationInfo());
        }
    }

    public BoxLaunchManager() {
        init();
    }

    public void setCallBack(BoxLaunchCallback boxLaunchCallback) {
        this.callBack = boxLaunchCallback;
    }

    private void init() {
        initThreadVirtual();
        this.strDataDir = XJApp.getInstance().getApplicationInfo().dataDir;
        String str = Build.BRAND;
        if (str.toLowerCase().equals("huawei") || str.toLowerCase().equals("honor")) {
            this.isHuawei = true;
            ManagerServiceUtil.m19232a();
            LogUtils.m22038d(TAG, "get isHuawei");
        }
    }

    @TargetApi(23)
    public void setDisplayParam(Activity activity, String str) {
        if (this.displayParam == null) {
            this.displayParam = new DisplayParam();
        }
        LogUtils.m22038d(TAG, "getLcd msg:" + str);
        LogUtils.m22038d(TAG, "MY_DEBUG_GETPHONE_W" + aeo.m13910e());
        LogUtils.m22038d(TAG, "MY_DEBUG_GETPHONE_H" + aeo.m13925a());
        int[] iArr = new int[4];
        iArr[0] = aeo.m13910e();
        iArr[1] = aeo.m13925a();
        com.blankj.utilcode.util.LogUtils.m23734c("LBS-XXX", "wh1:" + Arrays.toString(iArr));
        int[] a = aes.m13872a(activity, iArr);
        this.displayParam.setDisplayWidth(a[0]);
        this.displayParam.setDisplayHeight(a[1]);
        com.blankj.utilcode.util.LogUtils.m23734c("LBS-XXX", "wh2:" + Arrays.toString(a));
        int intValue = ((Integer) PreferencesUtil.m13937a().m13927b(acf.f15170I, 1)).intValue();
        com.blankj.utilcode.util.LogUtils.m23734c("LBS-XXX", "lcd:" + intValue);
        if (intValue == 1) {
            a[2] = aeo.m13906g();
            a[3] = aeo.m13904h();
        } else if (intValue == 2) {
            a[2] = 1080;
            a[3] = 1920;
        }
        com.blankj.utilcode.util.LogUtils.m23734c("LBS-XXX", "wh3:" + Arrays.toString(a));
        float[] fArr = {1.0f, 1.0f};
        this.displayParam.setDefaultScale();
        if (intValue != 0) {
            int[] iArr2 = {0, 0};
            aes.m13862a(a, fArr, iArr2);
            this.displayParam.setScaleW(fArr[0]);
            this.displayParam.setScaleH(fArr[1]);
            this.displayParam.setDisplayScaleW(iArr2[0]);
            this.displayParam.setDisplayScaleH(iArr2[1]);
        } else {
            this.displayParam.setDisplayScaleW(a[0]);
            this.displayParam.setDisplayScaleH(a[1]);
        }
        com.blankj.utilcode.util.LogUtils.m23734c("LBS-XXX", "MY_DEBUG_GETPHONE_ mDisplayScaleW=" + this.displayParam.getDisplayScaleW() + " mDisplayScaleH=" + this.displayParam.getDisplayScaleH() + " m_fscalew=" + fArr[0] + " m_fscaleh=" + fArr[1]);
        initVirtualMachine();
        updateProgress(5);
    }

    public void initVirtualMachine() {
        if (aef.m14124e(rootFile + "/r")) {
            LogUtils.m22038d("FileUtils", "r文件夹 创建成功");
        } else {
            LogUtils.m22038d("FileUtils", "r文件夹 创建失败");
        }
        if (aef.m14131d(rootFile)) {
            LogUtils.m22038d("FileUtils", "osimg 创建成功");
        } else {
            LogUtils.m22038d("FileUtils", "osimg 创建失败");
        }
        if (aef.m14124e(rootFile.getAbsolutePath() + "/socket")) {
            LogUtils.m22038d("FileUtils", "socket 创建成功");
        } else {
            LogUtils.m22038d("FileUtils", "socket 创建失败");
        }
        int a = PreferencesUtil.m13937a().m13933a(acf.f15181f, 0);
        String a2 = PreferencesUtil.m13937a().m13930a(acf.f15180e, "");
        LogUtils.m22037e("patch:" + a);
        LogUtils.m22037e("patchPath:" + a2);
        LogUtils.m22037e("rootFile:" + rootFile.getPath() + "/patch_done");
        StringBuilder sb = new StringBuilder();
        sb.append("versionCode:");
        sb.append(VersionHelper.m13827c().f15480b);
        LogUtils.m22037e(sb.toString());
        if (!aef.m14164a(new File(rootFile.getPath() + "/patch_done")) && VersionHelper.m13827c().f15480b <= 100119) {
            LogUtils.m22038d("FileUtils", "patch_done不存在");
            LogUtils.m22038d("FileUtils", "第一次驱动需要解包初始化 才能启动虚拟机");
            intiConfiguration();
        } else if (a <= VersionHelper.m13827c().f15480b || TextUtils.isEmpty(a2)) {
            LogUtils.m22038d("FileUtils", "patch_done 存在");
            if (none == 0) {
                setInitShell(rootFile);
                patchZip();
                startVirtualMachine();
                return;
            }
            BoxLaunchCallback boxLaunchCallback = this.callBack;
            if (boxLaunchCallback != null) {
                boxLaunchCallback.launchReady();
                updateProgress(100);
            }
        } else {
            this.callBack.patchUnZip();
            UnZipTools a3 = UnZipTools.m13824a();
            a3.m13822a(a2, rootFile.getPath() + "/r", "rootfs", "ot01", new UnZipListener() { // from class: com.lbd.xj.manager.launch.BoxLaunchManager.4
                @Override // p110z1.UnZipListener
                public void unZipFail() {
                    LogUtils.m22038d("FileUtils", "解压 patch.zip unZipFail");
                    PreferencesUtil.m13937a().m13931a(acf.f15181f, (Object) 0);
                    BoxLaunchManager.this.initVirtualMachine();
                }

                @Override // p110z1.UnZipListener
                public void unZipProgress(int i) {
                    LogUtils.m22038d("FileUtils", "解压 patch.zip progress:" + i);
                }

                @Override // p110z1.UnZipListener
                public void unZipStart() {
                    LogUtils.m22038d("FileUtils", "解压 patch.zip 开始");
                }

                @Override // p110z1.UnZipListener
                public void unZipSuccess() {
                    LogUtils.m22038d("FileUtils", "解压 patch.zip 成功");
                    int a4 = PreferencesUtil.m13937a().m13933a(acf.f15181f, 0);
                    LogUtils.m22038d("FileUtils", "解压 patch.zip 成功 patch:" + a4);
                    SharepreferenceUtil.m13894a(acf.f15179d, a4);
                    VersionHelper.m13831a(a4);
                    DeleteFileUtil.m14195a(BoxLaunchManager.rootFile + "/patch_done");
                    BoxLaunchManager.this.afterUnzip(BoxLaunchManager.rootFile);
                }
            });
        }
    }

    public void intiConfiguration() {
        LogUtils.m22039d("intiConfiguration");
        C1545f.m19586c(new C1545f.AbstractC1551c<Void>() { // from class: com.lbd.xj.manager.launch.BoxLaunchManager.5
            public void onSuccess(@Nullable Void r1) {
            }

            @Override // com.lbd.p054xj.socket.C1545f.AbstractRunnableC1552d
            @Nullable
            public Void doInBackground() throws Throwable {
                File file = BoxLaunchManager.rootFile;
                String b = SharepreferenceUtil.m13882b(acf.f15178c, "");
                if (AppConfig.f9435b) {
                    b = "/sdcard/rootfs.zip";
                }
                LogUtils.m22039d("path:" + b);
                File file2 = new File(b);
                if (file2.exists()) {
                    aef.m14146b(file2, new File(file.getPath() + "/os.tar.gz"));
                } else if (BoxLaunchManager.this.isUseDefaultRom()) {
                    GeneralUtil.m14005e(file.getPath() + "/os.tar.gz", BoxLaunchManager.ROOM_FILE);
                } else {
                    aef.m14146b(file2, new File(file.getPath() + "/os.tar.gz"));
                }
                if (acf.f15192q.equals(GeneralUtil.m14063a())) {
                    GeneralUtil.m14011d(file.getPath() + "/busybox", "mbx.zip.64");
                } else {
                    GeneralUtil.m14011d(file.getPath() + "/busybox", "mbx.zip");
                }
                BoxLaunchManager.this.updateProgress(25);
                BoxLaunchManager.this.setInitShell(file);
                BoxLaunchManager.this.updateProgress(30);
                BoxLaunchManager.this.zipjavaCompress(BoxLaunchManager.rootFile);
                return null;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isUseDefaultRom() {
        return isAssetFileExist(ROOM_FILE);
    }

    private boolean isAssetFileExist(String str) {
        try {
            for (String str2 : XJApp.getInstance().getAssets().list("")) {
                if (str2.equals(str.trim())) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void afterUnzip(File file) {
        LogUtils.m22037e("afterunzip file:" + file.toString());
        final int b = VersionHelper.m13828b();
        LogUtils.m22037e("systemRomVersion:" + b);
        if (!new File(file.getPath() + "/patch_done").exists() || VersionHelper.m13827c().f15480b < b) {
            new Thread() { // from class: com.lbd.xj.manager.launch.BoxLaunchManager.6
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    try {
                        if (new File(BoxLaunchManager.rootFile.getPath() + "/derootfs01_end").createNewFile()) {
                            Log.e(BoxLaunchManager.TAG, "createNewFile /derootfs01_end");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    int check_gralloc_version = BoxLaunchManager.this.check_gralloc_version();
                    LogUtils.m22039d("grallocversion:" + check_gralloc_version);
                    if (check_gralloc_version == 2) {
                        if (Build.VERSION.RELEASE.startsWith("10")) {
                            if (Build.BRAND.toLowerCase().equals("huawei") || Build.BRAND.toLowerCase().equals("hornor")) {
                                aef.m14123e("/data/data/" + AppConfig.f9441h + "/osimg/r/ot01/init_10", "/data/data/" + AppConfig.f9441h + "/osimg/r/ot01/init");
                                aef.m14123e("/data/data/" + AppConfig.f9441h + "/osimg/r/ot01/sbin/adbd_10", "/data/data/" + AppConfig.f9441h + "/osimg/r/ot01/sbin/adbd");
                                aef.m14123e("/data/data/" + AppConfig.f9441h + "/osimg/r/ot01/system/bin/sh_10", "/data/data/" + AppConfig.f9441h + "/osimg/r/ot01/system/bin/sh");
                                aef.m14123e("/data/data/" + AppConfig.f9441h + "/osimg/r/ot01/system/xbin/daemonsu_10", "/data/data/" + AppConfig.f9441h + "/osimg/r/ot01/system/xbin/daemonsu");
                            }
                            aef.m14123e("/data/data/" + AppConfig.f9441h + "/osimg/r/ot01/system/bin/initlinker32_10", "/data/data/" + AppConfig.f9441h + "/osimg/r/ot01/system/bin/initlinker32");
                            aef.m14123e("/data/data/" + AppConfig.f9441h + "/osimg/r/ot01/system/lib/libui10.so", "/data/data/" + AppConfig.f9441h + "/osimg/r/ot01/system/lib/libui.so");
                            aef.m14123e("/data/data/" + AppConfig.f9441h + "/osimg/r/ot01/system/lib/libhostlibui_10.so", "/data/data/" + AppConfig.f9441h + "/osimg/r/ot01/system/lib/libhostlibui.so");
                            aef.m14123e("/data/data/" + AppConfig.f9441h + "/osimg/r/ot01/system/lib/libEGL_10.so", "/data/data/" + AppConfig.f9441h + "/osimg/r/ot01/system/lib/libEGL.so");
                        }
                        aef.m14123e("/data/data/" + AppConfig.f9441h + "/osimg/r/ot01/system/bin/initlinker32_81", "/data/data/" + AppConfig.f9441h + "/osimg/r/ot01/system/bin/initlinker32");
                    } else if (check_gralloc_version == 1) {
                        aef.m14123e("/data/data/" + AppConfig.f9441h + "/osimg/r/ot01/system/lib/libui51.so", "/data/data/" + AppConfig.f9441h + "/osimg/r/ot01/system/lib/libui.so");
                    }
                    try {
                        String str = "chmod 777 -R " + BoxLaunchManager.rootFile.getPath() + "/r/ot01";
                        LogUtils.m22034i(BoxLaunchManager.TAG, "command = " + str);
                        Runtime.getRuntime().exec(str).waitFor();
                    } catch (IOException e2) {
                        LogUtils.m22034i(BoxLaunchManager.TAG, "chmod fail!!!!");
                        e2.printStackTrace();
                    } catch (InterruptedException e3) {
                        LogUtils.m22034i(BoxLaunchManager.TAG, "chmod 2fail!!!!");
                        e3.printStackTrace();
                    }
                    BoxLaunchManager.this.fixLink(BoxLaunchManager.rootFile.getPath() + "r/ot01/%s");
                    try {
                        String str2 = "chmod -R 777 " + BoxLaunchManager.rootFile.getPath() + "/r/ot01";
                        LogUtils.m22034i(BoxLaunchManager.TAG, "command = " + str2);
                        Runtime.getRuntime().exec(str2).waitFor();
                    } catch (IOException e4) {
                        LogUtils.m22034i(BoxLaunchManager.TAG, "chmod fail!!!!");
                        e4.printStackTrace();
                    } catch (InterruptedException e5) {
                        LogUtils.m22034i(BoxLaunchManager.TAG, "chmod 2fail!!!!");
                        e5.printStackTrace();
                    }
                    BoxLaunchManager.this.deRootfs(BoxLaunchManager.rootFile.getPath() + "/patch.sh");
                    LogUtils.m22038d(BoxLaunchManager.TAG, "deRootfs: patch.sh end");
                    File file2 = new File(BoxLaunchManager.rootFile.getPath() + "/patch_done");
                    while (!file2.exists()) {
                        try {
                            Thread.sleep(1000L);
                        } catch (InterruptedException e6) {
                            e6.printStackTrace();
                        }
                    }
                    int b2 = SharepreferenceUtil.m13883b(acf.f15179d, b);
                    Log.d(BoxLaunchManager.TAG, "romVersion:" + b2);
                    VersionHelper.m13831a(b2);
                    BoxLaunchManager.this.startVirtualMachine();
                }
            }.start();
        } else {
            LogUtils.m22039d("patch_done is exist");
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(22:2|(2:49|3)|11|(1:13)(1:14)|15|47|16|45|19|43|22|(1:24)|25|(1:27)(1:28)|29|(3:31|(2:33|53)(2:34|52)|35)|51|36|(2:38|39)|54|55|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x011c, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x011d, code lost:
        r0.printStackTrace();
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x015f, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0160, code lost:
        r0.printStackTrace();
     */
    /* JADX WARN: Removed duplicated region for block: B:13:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0191  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x01dc A[Catch: IOException -> 0x04e1, TryCatch #1 {IOException -> 0x04e1, blocks: (B:22:0x0163, B:25:0x0193, B:27:0x01dc, B:28:0x023e, B:29:0x0270, B:31:0x0374, B:35:0x03b1, B:36:0x045a, B:38:0x04b8), top: B:43:0x0163 }] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x023e A[Catch: IOException -> 0x04e1, TryCatch #1 {IOException -> 0x04e1, blocks: (B:22:0x0163, B:25:0x0193, B:27:0x01dc, B:28:0x023e, B:29:0x0270, B:31:0x0374, B:35:0x03b1, B:36:0x045a, B:38:0x04b8), top: B:43:0x0163 }] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0374 A[Catch: IOException -> 0x04e1, TryCatch #1 {IOException -> 0x04e1, blocks: (B:22:0x0163, B:25:0x0193, B:27:0x01dc, B:28:0x023e, B:29:0x0270, B:31:0x0374, B:35:0x03b1, B:36:0x045a, B:38:0x04b8), top: B:43:0x0163 }] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x04b8 A[Catch: IOException -> 0x04e1, TRY_LEAVE, TryCatch #1 {IOException -> 0x04e1, blocks: (B:22:0x0163, B:25:0x0193, B:27:0x01dc, B:28:0x023e, B:29:0x0270, B:31:0x0374, B:35:0x03b1, B:36:0x045a, B:38:0x04b8), top: B:43:0x0163 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void setInitShell(java.io.File r17) {
        /*
            Method dump skipped, instructions count: 1254
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lbd.p054xj.manager.launch.BoxLaunchManager.setInitShell(java.io.File):void");
    }

    public void zipjavaCompress(File file) {
        LogUtils.m22039d("zipjavaCompress file:" + file.getAbsolutePath());
        try {
            if (isUseDefaultRom()) {
                GeneralUtil.m14005e(file.getPath() + "/os.tar.gz", ROOM_FILE);
            }
            UnZipTools.m13824a().m13822a(file.getPath() + "/os.tar.gz", file.getPath() + "/r", "rootfs", "ot01", this.zipListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int getDPI() {
        int c = aeo.m13914c();
        int g = aeo.m13906g();
        if (g == 1080) {
            c = 480;
        } else if (g == 720) {
            c = 360;
        }
        try {
            c = ((Integer) PreferencesUtil.m13937a().m13927b("surfaceDPI", Integer.valueOf(c))).intValue();
            LogUtils.m22035i("getDPI:" + c);
            return c;
        } catch (Exception e) {
            e.printStackTrace();
            return c;
        }
    }

    public void startVirtualMachine() {
        LogUtils.m22039d("startVirtualMachine ");
        updateProgress(95);
        none = 1;
        new Thread() { // from class: com.lbd.xj.manager.launch.BoxLaunchManager.7
            @Override // java.lang.Thread, java.lang.Runnable
            @SuppressLint({"SdCardPath"})
            public void run() {
                String str = XJApp.getInstance().getApplicationInfo().dataDir + "/osimg";
                StringBuilder sb = new StringBuilder();
                DeleteFileUtil.m14195a("/data/data/" + AppConfig.f9441h + "/osimg/socket/start");
                DeleteFileUtil.m14195a("/data/data/" + AppConfig.f9441h + "/osimg/socket/zygote");
                DeleteFileUtil.m14195a("/data/data/" + AppConfig.f9441h + "/osimg/socket/opengles");
                DeleteFileUtil.m14195a("/data/data/" + AppConfig.f9441h + "/osimg/socket/rfopengles");
                DeleteFileUtil.m14195a("/data/data/" + AppConfig.f9441h + "/osimg/socket/opengles22501");
                DeleteFileUtil.m14195a("/data/data/" + AppConfig.f9441h + "/osimg/socket/rfopengles22501");
                DeleteFileUtil.m14195a("/data/data/" + AppConfig.f9441h + "/osimg/socket/server");
                DeleteFileUtil.m14195a("/data/data/" + AppConfig.f9441h + "/osimg/socket/rfserver");
                DeleteFileUtil.m14195a("/data/data/" + AppConfig.f9441h + "/osimg/socket/touch22801");
                DeleteFileUtil.m14195a("/data/data/" + AppConfig.f9441h + "/osimg/socket/rftouch22801");
                DeleteFileUtil.m14195a("/data/data/" + AppConfig.f9441h + "/osimg/socket/audio");
                DeleteFileUtil.m14195a("/data/data/" + AppConfig.f9441h + "/osimg/socket/rfaudio");
                DeleteFileUtil.m14195a("/data/data/" + AppConfig.f9441h + "/osimg/r/ot01/dev/socket/bcs");
                DeleteFileUtil.m14195a("/data/data/" + AppConfig.f9441h + "/osimg/r/ot01/dev/socket/bis");
                DeleteFileUtil.m14195a("/data/data/" + AppConfig.f9441h + "/osimg/r/ot01/dev/socket/bhs");
                BoxLaunchManager.this.startMusicThread();
                if (BoxLaunchManager.this.isHuawei) {
                    Intent intent = new Intent(XJApp.getInstance().getApplicationContext(), MyService.class);
                    try {
                        if (Build.VERSION.SDK_INT >= 26) {
                            XJApp.getInstance().getApplicationContext().startForegroundService(intent);
                        } else {
                            XJApp.getInstance().getApplicationContext().startService(intent);
                        }
                        BoxLaunchManager.this.zygote_mgr("mgr");
                    } catch (Exception unused) {
                    }
                }
                int intValue = ((Integer) BoxLaunchManager.this.getParam(acf.f15170I, 1)).intValue();
                if (intValue == 0) {
                    GeneralUtil.m14044a(str, str + "/run_init01.sh " + str, sb);
                } else if (intValue == 1) {
                    GeneralUtil.m14044a(str, str + "/run_init01_js.sh " + str, sb);
                } else if (intValue == 2) {
                    GeneralUtil.m14044a(str, str + "/run_init01_js2.sh " + str, sb);
                } else if (intValue == 3) {
                    GeneralUtil.m14044a(str, str + "/run_init01.sh " + str, sb);
                }
                sb.append(C4963cj.f20745b);
                if (BoxLaunchManager.this.callBack != null) {
                    BoxLaunchManager.this.deleteRomFile();
                    BoxLaunchManager.this.callBack.launchReady();
                    BoxLaunchManager.this.updateProgress(100);
                }
            }
        }.start();
    }

    public void doInitVirtualMachine(String str) {
        LogUtils.m22039d("doInitVirtualMachine ");
        if (str != null) {
            initVirtualMachine();
        } else if (chechRomValid()) {
            initVirtualMachine();
        }
    }

    private boolean chechRomValid() {
        if (!new File(rootFile.getAbsolutePath() + "/patch_done").exists()) {
            return isUseDefaultRom();
        }
        if (VersionHelper.m13827c().f15480b < 100119) {
            return isUseDefaultRom();
        }
        return true;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.lbd.xj.manager.launch.BoxLaunchManager$8] */
    public void startMusicThread() {
        new Thread() { // from class: com.lbd.xj.manager.launch.BoxLaunchManager.8
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                BoxLaunchManager.this.startAudioPlayer("xxxx");
            }
        }.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Object getParam(String str, Object obj) {
        return PreferencesUtil.m13937a().m13927b(str, obj);
    }

    private void initThreadVirtual() {
        LogUtils.m22039d("initThreadVirtual ");
        if (mThreadVirtual == null) {
            mThreadVirtual = new Thread() { // from class: com.lbd.xj.manager.launch.BoxLaunchManager.9
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    try {
                        BoxLaunchManager.this.lsssheng_break = new LocalServerSocket("sheng_break");
                        while (true) {
                            BoxLaunchManager.this.socket = BoxLaunchManager.this.lsssheng_break.accept();
                            InputStream inputStream = BoxLaunchManager.this.socket.getInputStream();
                            OutputStream outputStream = BoxLaunchManager.this.socket.getOutputStream();
                            int read = inputStream.read();
                            BoxLaunchManager.this.socketWrite(outputStream, read);
                            BoxLaunchManager.this.judgeAgreement(read);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            mThreadVirtual.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void socketWrite(OutputStream outputStream, int i) {
        try {
            outputStream.write(i);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void judgeAgreement(int i) {
        switch (i) {
            case 93:
                saveParam(acf.f15170I, 2);
                return;
            case 94:
                saveParam(acf.f15170I, 1);
                return;
            case 95:
                saveParam(acf.f15170I, 0);
                return;
            case 96:
                new Instrumentation().sendKeyDownUpSync(4);
                return;
            case 97:
                saveParam(acf.f15170I, 2);
                GeneralUtil.m14003f();
                GeneralUtil.m14015d();
                killuid(Process.myUid());
                GeneralUtil.m14025c();
                return;
            case 98:
                saveParam(acf.f15170I, 1);
                GeneralUtil.m14003f();
                GeneralUtil.m14015d();
                killuid(Process.myUid());
                GeneralUtil.m14025c();
                return;
            case 99:
                saveParam(acf.f15170I, 0);
                GeneralUtil.m14003f();
                GeneralUtil.m14015d();
                killuid(Process.myUid());
                GeneralUtil.m14025c();
                return;
            default:
                return;
        }
    }

    public Object saveParam(String str, Object obj) {
        PreferencesUtil.m13937a().m13931a(str, obj);
        return str;
    }

    public static boolean isRomExists() {
        if (aef.m14164a(rootFile)) {
            if (aef.m14164a(new File(rootFile.getPath() + "/patch_done"))) {
                return true;
            }
        }
        return false;
    }

    public void gotoFullActivity(Activity activity) {
        LogUtils.m22039d("gotoFullActivity");
        VMCommonConfig.initValue();
        int intValue = ((Integer) PreferencesUtil.m13937a().m13927b(acf.f15170I, 1)).intValue();
        this.displayParam.setIsForceMode(String.valueOf(intValue));
        this.displayParam.setPort("1");
        SharepreferenceUtil.m13893a(acf.f15182g, this.displayParam);
        VMProperUtil.writeDateToLocalFile("on", aef.m14167A(XJApp.getInstance().getPackageCodePath()));
        com.blankj.utilcode.util.LogUtils.m23734c("lbswww", "displayParam:" + this.displayParam.toString());
        com.blankj.utilcode.util.LogUtils.m23734c("lbswww", "surfaceW:" + aeo.m13906g() + "surfaceH:" + aeo.m13904h() + "surfaceDPI:" + getDPI());
        PreferencesUtil.m13937a().m13931a("surfaceW", Integer.valueOf(aeo.m13906g()));
        PreferencesUtil.m13937a().m13931a("surfaceH", Integer.valueOf(aeo.m13904h()));
        PreferencesUtil.m13937a().m13931a("surfaceDPI", Integer.valueOf(getDPI()));
        PreferencesUtil.m13937a().m13931a(acf.f15170I, Integer.valueOf(intValue));
        activity.startActivity(new Intent(activity, XJRenderActivity.class));
        activity.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deleteRomFile() {
        Iterator<String> it = FileWriteUtils.m14073b().iterator();
        while (it.hasNext()) {
            DeleteFileUtil.m14195a("/data/data/" + AppConfig.f9441h + "/osimg/r/ot01" + it.next());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.lbd.xj.manager.launch.BoxLaunchManager$Initsh */
    /* loaded from: classes.dex */
    public static class Initsh {
        String mDpi;
        String mHigh;
        String mShName;
        String mWidth;

        Initsh(String str, String str2, String str3, String str4) {
            this.mWidth = str;
            this.mHigh = str2;
            this.mDpi = str3;
            this.mShName = str4;
        }
    }

    public void updateProgress(int i) {
        this.unZipProgress = i;
        BoxLaunchCallback boxLaunchCallback = this.callBack;
        if (boxLaunchCallback != null) {
            boxLaunchCallback.unZipProgress(i);
            LogUtils.m22039d("progress:" + i);
        }
    }

    public int getProgress() {
        return this.unZipProgress;
    }

    /* renamed from: com.lbd.xj.manager.launch.BoxLaunchManager$RecordAudio */
    /* loaded from: classes.dex */
    public class RecordAudio extends AsyncTask<Void, Integer, Void> {
        public RecordAudio() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Void doInBackground(Void... voidArr) {
            BoxLaunchManager boxLaunchManager = BoxLaunchManager.this;
            boxLaunchManager.isRecording = true;
            try {
                AudioRecord.getMinBufferSize(boxLaunchManager.sampleRateInHz, 2, 2);
                AudioRecord audioRecord = new AudioRecord(1, BoxLaunchManager.this.sampleRateInHz, 2, 2, 1280);
                byte[] bArr = new byte[1280];
                audioRecord.startRecording();
                int i = 0;
                while (BoxLaunchManager.this.isRecording) {
                    BoxLaunchManager.this.recording_write(bArr, audioRecord.read(bArr, 0, 1280));
                    publishProgress(new Integer(i));
                    i++;
                }
                audioRecord.stop();
                audioRecord.release();
                return null;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public void onProgressUpdate(Integer... numArr) {
            super.onProgressUpdate((Object[]) numArr);
        }
    }

    public int recorder_Audio() {
        this.recordAudio = new RecordAudio();
        this.recordAudio.execute(new Void[0]);
        return 0;
    }

    public int recorder_stop() {
        this.isRecording = false;
        RecordAudio recordAudio = this.recordAudio;
        if (recordAudio != null) {
            recordAudio.cancel(true);
        }
        return 0;
    }

    private boolean firstCheckUseDefaultRom() {
        if (!new File(rootFile.getAbsolutePath() + "/patch_done").exists() || VersionHelper.m13827c().f15480b < 100119) {
            return isUseDefaultRom();
        }
        return false;
    }

    private void closeAndroidPDialog() {
        try {
            Class.forName("android.content.pm.PackageParser$Package").getDeclaredConstructor(String.class).setAccessible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Class<?> cls = Class.forName("android.app.ActivityThread");
            Method declaredMethod = cls.getDeclaredMethod("currentActivityThread", new Class[0]);
            declaredMethod.setAccessible(true);
            Object invoke = declaredMethod.invoke(null, new Object[0]);
            Field declaredField = cls.getDeclaredField("mHiddenApiWarningShown");
            declaredField.setAccessible(true);
            declaredField.setBoolean(invoke, true);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static int GetAudioConnect() {
        LogUtils.m22038d(TAG, "GetAudioConnect");
        if (mAudioTrack == null) {
            mbuffersize = AudioTrack.getMinBufferSize(44100, 3, 2);
            LogUtils.m22038d(TAG, "buffersize=" + mbuffersize);
            mAudioTrack = new AudioTrack(3, 44100, 3, 2, mbuffersize, 1);
            mAudioTrack.play();
        }
        return mbuffersize;
    }

    public static int AudioWriteData(byte[] bArr, int i, int i2) {
        int write = mAudioTrack.write(bArr, i, bArr.length);
        if (write < 0) {
            LogUtils.m22038d(TAG, "AudioWriteData write");
        }
        return write;
    }
}
