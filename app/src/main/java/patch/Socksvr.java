package patch;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Process;
import android.util.Log;
import com.lody.virtual.client.ipc.ServiceManagerNative;
import com.lody.virtual.helper.utils.VLog;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import p110z1.aoy;
import p110z1.apa;

/* loaded from: classes2.dex */
public class Socksvr extends Activity {

    /* renamed from: a */
    public static Context f14995a = null;

    /* renamed from: b */
    private static HandlerThread f14996b = null;

    /* renamed from: c */
    private static Handler f14997c = null;

    /* renamed from: d */
    private static String f14998d = "127.0.0.1";

    /* renamed from: e */
    private static int f14999e;

    /* renamed from: f */
    private static Map<String, Socket> f15000f = new LinkedHashMap();

    /* renamed from: g */
    private static Socket f15001g = null;

    /* renamed from: h */
    private static Context f15002h;

    Socksvr() {
        f15002h = this;
    }

    /* renamed from: a */
    private static String m14563a(Context context, int i) {
        Iterator<ActivityManager.RunningAppProcessInfo> it = ((ActivityManager) context.getSystemService(ServiceManagerNative.ACTIVITY)).getRunningAppProcesses().iterator();
        while (it.hasNext()) {
            ActivityManager.RunningAppProcessInfo next = it.next();
            try {
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (next.pid == i) {
                return next.processName;
            }
            continue;
        }
        return null;
    }

    /* renamed from: a */
    public static void m14561a(String str) {
        VLog.m18993d("sunya", "onlysocket=" + f15001g, new Object[0]);
        try {
            f15001g.getOutputStream().write(str.getBytes());
        } catch (Exception e) {
            VLog.m18993d("sunya", "socksvr responseString e=" + e.getMessage(), new Object[0]);
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public static void m14562a(Context context, boolean z) {
        if (!m14563a(context, Process.myPid()).contains(":")) {
            if (context.getPackageName().equals("com.tencent.tmgp.jx3m")) {
                f14999e = 8181;
            }
            if (context.getPackageName().equals("com.tencent.raziel")) {
                f14999e = 8181;
            }
            if (context.getPackageName().equals("com.tencent.tmgp.dnftest")) {
                f14999e = 38181;
            }
            if (context.getPackageName().equals("com.lilithgames.rok.offical.cn")) {
                f14999e = 38182;
                Log.d("LBS_SOCKSVR", "5 --- mDstPort = " + f14999e + ",package: " + context.getPackageName());
            }
            int i = 64;
            if (f14999e == 0) {
                f14999e = (Process.myPid() % 10000) + 30000;
                SockPortInfo eVar = new SockPortInfo();
                eVar.f15021b = f14999e;
                if (z) {
                    eVar.f15020a = 64;
                } else {
                    eVar.f15020a = 32;
                }
                aoy.m11884a("/sdcard/nrzs/port/" + context.getPackageName() + ".txt", apa.m11879a(eVar), false);
                Log.d("LBS_SOCKSVR", "5 --- mDstPort = " + f14999e + ",package: " + context.getPackageName());
            }
            Log.d("LBS_SOCKSVR", "mDstPort = " + f14999e);
            f14995a = context;
            f14996b = new HandlerThread("MainActivity", 10);
            f14996b.start();
            f14997c = new Handler(f14996b.getLooper());
            Handler handler = f14997c;
            if (!z) {
                i = 32;
            }
            handler.post(new RunnableC3280a(i));
        }
    }

    /* renamed from: patch.Socksvr$a */
    /* loaded from: classes2.dex */
    private static class RunnableC3280a implements Runnable {

        /* renamed from: a */
        private int f15003a;

        public RunnableC3280a(int i) {
            this.f15003a = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            Log.d("LBS_SOCKSVR", "开始注入");
            try {
                while (true) {
                    final Socket accept = new ServerSocket(Socksvr.f14999e).accept();
                    new Thread(new Runnable() { // from class: patch.Socksvr.a.1
                        @Override // java.lang.Runnable
                        public void run() {
                            Looper.prepare();
                            try {
                                Socket unused = Socksvr.f15001g = accept;
                                InputStream inputStream = accept.getInputStream();
                                byte[] bArr = new byte[1024];
                                while (true) {
                                    int read = inputStream.read(bArr);
                                    if (read != -1) {
                                        String str = new String(bArr, 0, read);
                                        Log.d("LBS_SOCKSVR", "注入通讯 socksvr recv(" + str + ")");
                                        if (!str.startsWith("#")) {
                                            if (str.equals("\r\n") || str.equals("\n") || str.equals("close")) {
                                                break;
                                            } else if (str.contains("loadso")) {
                                                Log.d("LBS_SOCKSVR", "开始注入1");
                                                String str2 = Socksvr.f14995a.getFilesDir().getAbsolutePath() + "/b";
                                                Log.d("LBS_SOCKSVR", "开始注入2");
                                                String str3 = RunnableC3280a.this.f15003a == 32 ? "/sdcard/a" : "/sdcard/b";
                                                Socksvr.m14560a(str3, str2);
                                                Log.d("LBS_SOCKSVR", "开始注入3");
                                                System.load(str2);
                                                Log.d("LBS_SOCKSVR", "开始注入4");
                                                new File(str2).delete();
                                                Log.d("LBS_SOCKSVR", "开始注入5");
                                                File file = new File(str3);
                                                Log.d("LBS_SOCKSVR", "开始注入6");
                                                if (file.exists()) {
                                                    Log.d("LBS_SOCKSVR", "开始注入7");
                                                    file.delete();
                                                }
                                                Log.d("LBS_SOCKSVR", "注入成功，加载结束");
                                                Socksvr.m14561a("success");
                                            }
                                        } else {
                                            Socksvr.f15000f.put(str, accept);
                                            Log.d("LBS_SOCKSVR", "server-认证用户");
                                        }
                                    } else {
                                        return;
                                    }
                                }
                                Log.d("LBS_SOCKSVR", "关闭socket close socket!!");
                                accept.close();
                            } catch (Exception e) {
                                Socksvr.m14561a("fail");
                                Log.d("LBS_SOCKSVR", "注入失败 error1b " + e.getMessage());
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }
            } catch (Exception e) {
                Log.d("LBS_SOCKSVR", "注入失败  mDstPort = " + Socksvr.f14999e);
                e.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    public static void m14560a(String str, String str2) {
        try {
            if (new File(str).exists()) {
                FileInputStream fileInputStream = new FileInputStream(str);
                FileOutputStream fileOutputStream = new FileOutputStream(str2);
                byte[] bArr = new byte[1444];
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read != -1) {
                        fileOutputStream.write(bArr, 0, read);
                    } else {
                        fileInputStream.close();
                        return;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
