package com.goldcoast.sdk.p051b;

import android.content.Context;
import com.goldcoast.sdk.p052c.C1392e;
import com.goldcoast.sdk.p052c.LogUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/* renamed from: com.goldcoast.sdk.b.a */
/* loaded from: classes.dex */
public final class NetWork {
    /* renamed from: a */
    public static String m20347a(String str, Context context, String str2) {
        String str3 = context.getFilesDir().getPath() + File.separator + "ota" + File.separator + "elf" + File.separator + str2;
        LogUtil.m20321a();
        LogUtil.m20319a(str);
        LogUtil.m20321a();
        LogUtil.m20319a("download filename=" + str2);
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setInstanceFollowRedirects(true);
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setConnectTimeout(60000);
        httpURLConnection.setReadTimeout(60000);
        httpURLConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/36.0.1985.143 Safari/537.36");
        System.setProperty("http.keepAlive", "false");
        httpURLConnection.connect();
        if (httpURLConnection.getResponseCode() == 200) {
            FileOutputStream fileOutputStream = new FileOutputStream(str3);
            InputStream inputStream = httpURLConnection.getInputStream();
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr);
                if (read <= 0) {
                    break;
                }
                fileOutputStream.write(bArr, 0, read);
            }
            fileOutputStream.close();
            C1392e.m20325a(str3, 493);
            httpURLConnection.disconnect();
        }
        return str3;
    }
}
