package com.tencent.smtt.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Looper;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.tencent.smtt.sdk.WebView;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.tencent.smtt.utils.d */
/* loaded from: classes2.dex */
public class DebugTbsPlugin {

    /* renamed from: b */
    private static DexClassLoader f13285b;

    /* renamed from: c */
    private static Looper f13286c;

    /* renamed from: d */
    private static DebugTbsPlugin f13287d;

    /* renamed from: a */
    public String f13288a;

    /* compiled from: DebugTbsPlugin.java */
    /* renamed from: com.tencent.smtt.utils.d$a */
    /* loaded from: classes2.dex */
    public interface AbstractC2641a {
        /* renamed from: a */
        void mo16484a();

        /* renamed from: a */
        void mo16483a(int i);

        /* renamed from: a */
        void mo16482a(Throwable th);
    }

    private DebugTbsPlugin(Context context) {
        this.f13288a = "";
        this.f13288a = context.getDir("debugtbs", 0).getAbsolutePath() + File.separator + "plugin";
    }

    /* renamed from: a */
    public static DebugTbsPlugin m16488a(Context context) {
        if (f13287d == null) {
            f13287d = new DebugTbsPlugin(context);
        }
        return f13287d;
    }

    /* renamed from: a */
    public void m16487a(final String str, final WebView webView, final Context context) {
        final RelativeLayout relativeLayout = new RelativeLayout(context);
        final TextView textView = new TextView(context);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(13);
        textView.setText("加载中，请稍后...");
        relativeLayout.addView(textView, layoutParams);
        webView.addView(relativeLayout, new FrameLayout.LayoutParams(-1, -1));
        String str2 = this.f13288a + File.separator + "DebugPlugin.tbs";
        FileUtil.m16444b(new File(str2));
        m16485a(str2, new AbstractC2641a() { // from class: com.tencent.smtt.utils.d.1
            @Override // com.tencent.smtt.utils.DebugTbsPlugin.AbstractC2641a
            /* renamed from: a */
            public void mo16484a() {
                webView.post(new Runnable() { // from class: com.tencent.smtt.utils.d.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        Toast.makeText(context, "下载成功", 0).show();
                        relativeLayout.setVisibility(4);
                        DebugTbsPlugin.this.m16486a(str, webView, context, DebugTbsPlugin.f13286c);
                    }
                });
            }

            @Override // com.tencent.smtt.utils.DebugTbsPlugin.AbstractC2641a
            /* renamed from: a */
            public void mo16483a(final int i) {
                webView.post(new Runnable() { // from class: com.tencent.smtt.utils.d.1.2
                    @Override // java.lang.Runnable
                    public void run() {
                        TextView textView2 = textView;
                        textView2.setText("已下载" + i + "%");
                    }
                });
            }

            @Override // com.tencent.smtt.utils.DebugTbsPlugin.AbstractC2641a
            /* renamed from: a */
            public void mo16482a(Throwable th) {
                webView.post(new Runnable() { // from class: com.tencent.smtt.utils.d.1.3
                    @Override // java.lang.Runnable
                    public void run() {
                        Toast.makeText(context, "下载失败，请检查网络", 0).show();
                    }
                });
            }
        });
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.tencent.smtt.utils.d$2] */
    @SuppressLint({"NewApi"})
    /* renamed from: a */
    public static void m16485a(final String str, final AbstractC2641a aVar) {
        new Thread() { // from class: com.tencent.smtt.utils.d.2
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:27:0x0077 -> B:28:0x007a). Please submit an issue!!! */
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                Exception e;
                int contentLength;
                InputStream inputStream;
                InputStream inputStream2 = null;
                r0 = null;
                FileOutputStream fileOutputStream = null;
                inputStream2 = null;
                try {
                    try {
                        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL("http://soft.tbs.imtt.qq.com/17421/tbs_res_imtt_tbs_DebugPlugin_DebugPlugin.tbs").openConnection();
                        contentLength = httpURLConnection.getContentLength();
                        httpURLConnection.setConnectTimeout(5000);
                        httpURLConnection.connect();
                        inputStream = httpURLConnection.getInputStream();
                    } catch (Exception e2) {
                        while (true) {
                            e2.printStackTrace();
                            return;
                        }
                    }
                } catch (Exception e3) {
                    e = e3;
                    fileOutputStream = null;
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream = null;
                }
                try {
                    fileOutputStream = FileUtil.m16437d(new File(str));
                    byte[] bArr = new byte[8192];
                    int i = 0;
                    while (true) {
                        int read = inputStream.read(bArr);
                        if (read <= 0) {
                            break;
                        }
                        i += read;
                        fileOutputStream.write(bArr, 0, read);
                        aVar.mo16483a((i * 100) / contentLength);
                    }
                    aVar.mo16484a();
                    try {
                        inputStream.close();
                    } catch (Exception e4) {
                        e4.printStackTrace();
                    }
                    fileOutputStream.close();
                } catch (Exception e5) {
                    e = e5;
                    inputStream2 = inputStream;
                    try {
                        e.printStackTrace();
                        aVar.mo16482a(e);
                        try {
                            inputStream2.close();
                        } catch (Exception e6) {
                            e6.printStackTrace();
                        }
                        fileOutputStream.close();
                    } catch (Throwable th2) {
                        th = th2;
                        try {
                            inputStream2.close();
                        } catch (Exception e7) {
                            e7.printStackTrace();
                        }
                        try {
                            fileOutputStream.close();
                        } catch (Exception e8) {
                            e8.printStackTrace();
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    inputStream2 = inputStream;
                    inputStream2.close();
                    fileOutputStream.close();
                    throw th;
                }
            }
        }.start();
    }

    @SuppressLint({"NewApi"})
    /* renamed from: a */
    public void m16486a(String str, WebView webView, Context context, Looper looper) {
        TbsLog.m16531i("debugtbs", "showPluginView -- url: " + str + "; webview: " + webView + "; context: " + context);
        String str2 = this.f13288a + File.separator + "DebugPlugin.apk";
        File file = new File(this.f13288a + File.separator + "DebugPlugin.tbs");
        File file2 = new File(str2);
        f13286c = looper;
        if (file.exists()) {
            file2.delete();
            file.renameTo(file2);
        }
        if (!file2.exists()) {
            TbsLog.m16531i("debugtbs", "showPluginView - going to download plugin...");
            m16487a(str, webView, context);
            return;
        }
        try {
            String a = AppUtil.m16508a(context, true, new File(str2));
            if (!"308203773082025fa003020102020448bb959d300d06092a864886f70d01010b0500306b310b300906035504061302636e31123010060355040813094775616e67646f6e673111300f060355040713085368656e7a68656e3110300e060355040a130754656e63656e74310c300a060355040b13034d4947311530130603550403130c4d696e676875204875616e673020170d3136303532313039353730335a180f32303731303232323039353730335a306b310b300906035504061302636e31123010060355040813094775616e67646f6e673111300f060355040713085368656e7a68656e3110300e060355040a130754656e63656e74310c300a060355040b13034d4947311530130603550403130c4d696e676875204875616e6730820122300d06092a864886f70d01010105000382010f003082010a02820101008c58deabefe95f699c6322f9a75620873b490d26520c7267eb8382a91da625a5876b2bd617116eb40b371c4f88c988c1ba73052caaa9964873c94b7755c3429fca47a6677229fb2e72908d3b17df82f1ebe70447b94c1e5b0a763dad8948312180322657325306f01e423e0409ef3a59e5c0e0b9c765a2322699a2dec2d4dbe58ec15f41752516192169d9596169f5bf08eaf8aab9893240ad679e82fc92b97d2ae98b28021dc5a752f0a69437ea603c541e6753cea52dbc8e8043fe21fd5da46066c92e0714905dfad3116f35aca52b13871c57481459aa4ca255a6482ba972bd17af90d0d2c21a57ef65376bbd4ce7078e6047060640669f3867fdc69fbb750203010001a321301f301d0603551d0e0416041450fb9b6362e829797b1b29ca55e6d5e082e93ff3300d06092a864886f70d01010b050003820101004952ffbfba7c00ee9b84f44b05ec62bc2400dc769fb2e83f80395e3fbb54e44d56e16527413d144f42bf8f21fa443bc42a7a732de9d5124df906c6d728e75ca94eefc918080876bd3ce6cb5f7f2d9cc8d8e708033afc1295c7f347fb2d2098be2e4a79220e9552171d5b5f8f59cff4c6478cc41dce24cbe942305757488d37659d3265838ee54ebe44fccbd1bec93d809f950034f5ef292f20179554d22f5856c03b4d44997fcb9b3579e16a49218fce0e2e6bfe1fd4aa0ab39f548344c244c171c203baff1a730883aaf4506b6865a45c3c9aba40c6326d4152b6ce09cc058864bec1d6422e83dad9496b83fb252b4bfb30d3a6badf996099793e11f9af618d".equals(a)) {
                TbsLog.m16533e("debugtbs", "verifyPlugin apk: " + str2 + " signature failed - sig: " + a);
                Toast.makeText(context, "插件校验失败，请重试", 0).show();
                file.delete();
                file2.delete();
                return;
            }
            String str3 = this.f13288a + File.separator + "opt";
            File file3 = new File(str3);
            if (!file3.exists()) {
                file3.mkdirs();
            }
            if (f13285b == null) {
                f13285b = new DexClassLoader(str2, str3, null, context.getClassLoader());
            }
            HashMap hashMap = new HashMap();
            hashMap.put("url", str);
            hashMap.put("tbs_version", "" + WebView.getTbsSDKVersion(context));
            hashMap.put("tbs_core_version", "" + WebView.getTbsCoreVersion(context));
            if (f13286c != null) {
                hashMap.put("looper", looper);
            }
            Object newInstance = f13285b.loadClass("com.tencent.tbs.debug.plugin.DebugView").getConstructor(Context.class, Map.class).newInstance(context, hashMap);
            if (newInstance instanceof FrameLayout) {
                FrameLayout frameLayout = (FrameLayout) newInstance;
                webView.addView(frameLayout, new FrameLayout.LayoutParams(-1, -1));
                TbsLog.m16531i("debugtbs", "show " + frameLayout + " successful in " + webView);
                return;
            }
            TbsLog.m16533e("debugtbs", "get debugview failure: " + newInstance);
        } catch (Exception e) {
            FileUtil.m16444b(file2);
            e.printStackTrace();
        }
    }
}
