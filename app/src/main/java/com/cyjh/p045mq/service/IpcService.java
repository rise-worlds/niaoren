package com.cyjh.p045mq.service;

import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.HandlerThread;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import com.alipay.sdk.widget.C0675j;
import com.cyjh.event.Injector;
import com.cyjh.mobileanjian.ipc.RootShell;
import com.cyjh.mobileanjian.ipc.rpc.AndroidHelper;
import com.cyjh.mobileanjian.ipc.utils.FileUtils;
import com.cyjh.mobileanjian.ipc.utils.RomUtils;
import com.cyjh.mobileanjian.ipc.utils.UniqIdUtil;
import com.cyjh.mobileanjian.screencap.ScreenShoterV3;
import com.cyjh.mqsdk.C1375R;
import com.cyjh.mqsdk.C1378a;
import com.cyjh.p045mq.p046a.MyApplication;
import com.cyjh.p045mq.p048c.IpcServer;
import com.cyjh.p045mq.sdk.MqRunner;
import com.cyjh.p045mq.sdk.MqRunnerLite;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.UnknownHostException;

/* renamed from: com.cyjh.mq.service.IpcService */
/* loaded from: classes.dex */
public class IpcService extends Service {

    /* renamed from: f */
    private static final String f8901f = "mqm_engine";

    /* renamed from: g */
    private static final String f8902g = "ipc_server_port";

    /* renamed from: h */
    private static final String f8903h = "/system/bin/app_process32";

    /* renamed from: i */
    private static final String f8904i = "elfinject";

    /* renamed from: j */
    private static final String f8905j = "model";

    /* renamed from: k */
    private static final String f8906k = "code_cache";

    /* renamed from: l */
    private static final String f8907l = "secondary-dexes";

    /* renamed from: m */
    private static final String f8908m = "mycache";

    /* renamed from: n */
    private static final String f8909n = "dalvik-cache";

    /* renamed from: b */
    private int f8911b;

    /* renamed from: e */
    private IpcServer f8914e;

    /* renamed from: a */
    private String f8910a = null;

    /* renamed from: c */
    private String f8912c = "";

    /* renamed from: d */
    private String f8913d = "";

    @Override // android.app.Service
    @Nullable
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        return 2;
    }

    @Override // android.app.Service
    public void onCreate() {
        String absolutePath;
        super.onCreate();
        AndroidHelper.init(this);
        Injector.init(this);
        new File(getApplicationContext().getFilesDir().getAbsolutePath()).setExecutable(true, false);
        this.f8910a = getApplicationContext().getPackageName() + MyApplication.f8784c;
        this.f8911b = m20395g();
        String str = getFilesDir().getAbsolutePath() + File.separator + MyApplication.f8786e;
        String makeAbsolutePath = FileUtils.makeAbsolutePath(getApplicationInfo().dataDir, "lib", MyApplication.f8787f);
        String str2 = "app_process";
        if (new File(f8903h).exists()) {
            str2 = f8903h;
        }
        File file = new File(FileUtils.makeAbsolutePath(getApplicationInfo().dataDir, f8908m, f8909n));
        file.mkdirs();
        file.setReadable(true, false);
        file.setWritable(true, false);
        file.setExecutable(true, false);
        file.getParentFile().setReadable(true, false);
        file.getParentFile().setWritable(true, false);
        file.getParentFile().setExecutable(true, false);
        this.f8912c = "export CLASSPATH=" + str + "\n" + String.format("exec %s %s %s %s %s %s &\n", str2, absolutePath, MyApplication.f8785d, this.f8910a, makeAbsolutePath, Integer.valueOf(this.f8911b));
        if (RomUtils.isOppoR9S()) {
            this.f8912c = "export CLASSPATH=" + str + "\n" + String.format("exec %s -Xnodex2oat %s %s %s %s %s &\n", str2, absolutePath, MyApplication.f8785d, this.f8910a, makeAbsolutePath, Integer.valueOf(this.f8911b));
        }
        this.f8913d = "export ANDROID_DATA=" + file.getParent() + "\n" + this.f8912c + "\necho Okay";
        new HandlerThreadC13721("ipcserver_thread").start();
        new C13732("startRootRequest").start();
        Log.i("VERSION", "build info: build on 2021-11-29 15:50:52");
    }

    /* renamed from: a */
    private void m20408a() {
        String absolutePath;
        new File(getApplicationContext().getFilesDir().getAbsolutePath()).setExecutable(true, false);
        this.f8910a = getApplicationContext().getPackageName() + MyApplication.f8784c;
        this.f8911b = m20395g();
        String str = getFilesDir().getAbsolutePath() + File.separator + MyApplication.f8786e;
        String makeAbsolutePath = FileUtils.makeAbsolutePath(getApplicationInfo().dataDir, "lib", MyApplication.f8787f);
        String str2 = "app_process";
        if (new File(f8903h).exists()) {
            str2 = f8903h;
        }
        File file = new File(FileUtils.makeAbsolutePath(getApplicationInfo().dataDir, f8908m, f8909n));
        file.mkdirs();
        file.setReadable(true, false);
        file.setWritable(true, false);
        file.setExecutable(true, false);
        file.getParentFile().setReadable(true, false);
        file.getParentFile().setWritable(true, false);
        file.getParentFile().setExecutable(true, false);
        this.f8912c = "export CLASSPATH=" + str + "\n" + String.format("exec %s %s %s %s %s %s &\n", str2, absolutePath, MyApplication.f8785d, this.f8910a, makeAbsolutePath, Integer.valueOf(this.f8911b));
        if (RomUtils.isOppoR9S()) {
            this.f8912c = "export CLASSPATH=" + str + "\n" + String.format("exec %s -Xnodex2oat %s %s %s %s %s &\n", str2, absolutePath, MyApplication.f8785d, this.f8910a, makeAbsolutePath, Integer.valueOf(this.f8911b));
        }
        this.f8913d = "export ANDROID_DATA=" + file.getParent() + "\n" + this.f8912c + "\necho Okay";
    }

    @Override // android.app.Service
    public void onDestroy() {
        this.f8914e.onAppQuit();
        super.onDestroy();
        RootShell a = RootShell.m21029a();
        if (a.f8236d) {
            a.m21022a(C0675j.f377o);
            if (a.f8235c.isAlive()) {
                a.f8235c.interrupt();
            }
            try {
                a.f8237e.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        RootShell.f8232a = null;
    }

    @Override // android.app.Service, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        MqRunner.getInstance().notifyRotationStatus();
        MqRunnerLite.getInstance().notifyRotationStatus();
        ScreenShoterV3.getInstance().updateScreenSize();
    }

    /* renamed from: b */
    private void m20405b() {
        String[] list;
        File dir = getDir("model", 0);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        try {
            for (String str : getResources().getAssets().list("model")) {
                File file = new File(dir, str);
                FileUtils.copyAssetsFile(this, new File("model", str).getPath(), file.getAbsolutePath());
                if (file.exists()) {
                    file.setReadable(true, false);
                }
            }
            File file2 = new File(getFilesDir(), MyApplication.f8786e);
            FileUtils.copyAssetsFile(this, MyApplication.f8786e, file2.getAbsolutePath());
            file2.setReadable(true, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* renamed from: c */
    private void m20403c() {
        String str = getFilesDir().getAbsolutePath() + File.separator + f8904i;
        if (UniqIdUtil.m20610b(this)) {
            FileUtils.copyRawFile(this, C1375R.raw.elfinject_x86, str);
        } else {
            FileUtils.copyRawFile(this, C1375R.raw.elfinject_arm, str);
        }
    }

    /* renamed from: d */
    private void m20401d() {
        File externalFilesDir;
        File filesDir = getFilesDir();
        File file = new File(filesDir, MyApplication.f8782a);
        FileUtils.writeStringToFile(file, this.f8913d);
        file.setReadable(true, false);
        file.setExecutable(true, false);
        new StringBuilder("execute command mRootScriptContent: ").append(this.f8912c);
        FileUtils.writeStringToFile(new File(filesDir, MyApplication.f8783b), this.f8912c);
        MyApplication.m20565a().setExecutable(true, false);
        if (Build.VERSION.SDK_INT >= 23 && (externalFilesDir = getExternalFilesDir(null)) != null && externalFilesDir.exists()) {
            FileUtils.writeStringToFile(new File(externalFilesDir, MyApplication.f8782a), this.f8913d);
        }
    }

    /* renamed from: com.cyjh.mq.service.IpcService$1 */
    /* loaded from: classes.dex */
    final class HandlerThreadC13721 extends HandlerThread {
        HandlerThreadC13721(String str) {
            super(str);
        }

        @Override // android.os.HandlerThread
        protected final void onLooperPrepared() {
            super.onLooperPrepared();
            IpcService ipcService = IpcService.this;
            ipcService.f8914e = new IpcServer(ipcService);
            IpcServer cVar = IpcService.this.f8914e;
            cVar.f8831a.add(MqRunner.getInstance());
            IpcService.this.f8914e.m20494a(IpcService.this.f8910a, IpcService.this.f8911b);
        }
    }

    /* renamed from: e */
    private void m20399e() {
        new HandlerThreadC13721("ipcserver_thread").start();
    }

    /* renamed from: com.cyjh.mq.service.IpcService$2 */
    /* loaded from: classes.dex */
    final class C13732 extends Thread {
        C13732(String str) {
            super(str);
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public final void run() {
            super.run();
            IpcService.m20400d(IpcService.this);
            IpcService.m20398e(IpcService.this);
            IpcService.m20396f(IpcService.this);
            RootShell.m21029a().m21024a(MqRunner.getInstance());
        }
    }

    /* renamed from: f */
    private void m20397f() {
        new C13732("startRootRequest").start();
    }

    /* renamed from: g */
    private static int m20395g() {
        int i = C1378a.f8928h;
        for (int i2 = 0; i2 < 9; i2++) {
            try {
                new ServerSocket(i).close();
                break;
            } catch (UnknownHostException e) {
                i++;
                e.printStackTrace();
            } catch (IOException e2) {
                i++;
                e2.printStackTrace();
            }
        }
        return i;
    }

    /* renamed from: d */
    static /* synthetic */ void m20400d(IpcService ipcService) {
        String[] list;
        File dir = ipcService.getDir("model", 0);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        try {
            for (String str : ipcService.getResources().getAssets().list("model")) {
                File file = new File(dir, str);
                FileUtils.copyAssetsFile(ipcService, new File("model", str).getPath(), file.getAbsolutePath());
                if (file.exists()) {
                    file.setReadable(true, false);
                }
            }
            File file2 = new File(ipcService.getFilesDir(), MyApplication.f8786e);
            FileUtils.copyAssetsFile(ipcService, MyApplication.f8786e, file2.getAbsolutePath());
            file2.setReadable(true, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* renamed from: e */
    static /* synthetic */ void m20398e(IpcService ipcService) {
        String str = ipcService.getFilesDir().getAbsolutePath() + File.separator + f8904i;
        if (UniqIdUtil.m20610b(ipcService)) {
            FileUtils.copyRawFile(ipcService, C1375R.raw.elfinject_x86, str);
        } else {
            FileUtils.copyRawFile(ipcService, C1375R.raw.elfinject_arm, str);
        }
    }

    /* renamed from: f */
    static /* synthetic */ void m20396f(IpcService ipcService) {
        File externalFilesDir;
        File filesDir = ipcService.getFilesDir();
        File file = new File(filesDir, MyApplication.f8782a);
        FileUtils.writeStringToFile(file, ipcService.f8913d);
        file.setReadable(true, false);
        file.setExecutable(true, false);
        new StringBuilder("execute command mRootScriptContent: ").append(ipcService.f8912c);
        FileUtils.writeStringToFile(new File(filesDir, MyApplication.f8783b), ipcService.f8912c);
        MyApplication.m20565a().setExecutable(true, false);
        if (Build.VERSION.SDK_INT >= 23 && (externalFilesDir = ipcService.getExternalFilesDir(null)) != null && externalFilesDir.exists()) {
            FileUtils.writeStringToFile(new File(externalFilesDir, MyApplication.f8782a), ipcService.f8913d);
        }
    }
}
