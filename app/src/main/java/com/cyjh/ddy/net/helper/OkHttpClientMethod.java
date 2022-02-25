package com.cyjh.ddy.net.helper;

import android.os.Handler;
import android.os.Looper;
import com.blankj.utilcode.util.Utils;
import com.cyjh.ddy.net.utils.OkHttpDns;
import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import p110z1.C4745bt;
import p110z1.SimpleComparison;

/* loaded from: classes.dex */
public class OkHttpClientMethod {

    /* renamed from: a */
    private static OkHttpClientMethod f7506a;

    /* renamed from: b */
    private OkHttpClient f7507b;

    /* loaded from: classes.dex */
    public interface Callback2 {
        void onFailure(Call call, IOException iOException);

        void onResponse(Call call, byte[] bArr) throws IOException;
    }

    private OkHttpClientMethod() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(30L, TimeUnit.SECONDS);
        builder.connectTimeout(30L, TimeUnit.SECONDS);
        builder.writeTimeout(30L, TimeUnit.SECONDS);
        builder.sslSocketFactory(m21444b());
        builder.hostnameVerifier(new HostnameVerifier() { // from class: com.cyjh.ddy.net.helper.OkHttpClientMethod.1
            @Override // javax.net.ssl.HostnameVerifier
            public boolean verify(String str, SSLSession sSLSession) {
                return true;
            }
        });
        builder.dns(OkHttpDns.getInstance(Utils.m24103a()));
        this.f7507b = builder.build();
    }

    /* renamed from: b */
    private static SSLSocketFactory m21444b() {
        try {
            SSLContext instance = SSLContext.getInstance("TLS");
            instance.init(null, new TrustManager[]{new TrustAllCerts()}, new SecureRandom());
            return instance.getSocketFactory();
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class TrustAllCerts implements X509TrustManager {
        @Override // javax.net.ssl.X509TrustManager
        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
        }

        private TrustAllCerts() {
        }

        @Override // javax.net.ssl.X509TrustManager
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    /* renamed from: a */
    public static OkHttpClientMethod m21448a() {
        if (f7506a == null) {
            synchronized (OkHttpClientMethod.class) {
                if (f7506a == null) {
                    f7506a = new OkHttpClientMethod();
                }
            }
        }
        return f7506a;
    }

    /* renamed from: a */
    private Headers m21445a(Map<String, String> map) {
        Headers.Builder builder = new Headers.Builder();
        if (map != null) {
            for (String str : map.keySet()) {
                builder.add(str, map.get(str));
            }
        }
        return builder.build();
    }

    /* renamed from: b */
    private String m21443b(Map<String, String> map) {
        StringBuffer stringBuffer = new StringBuffer();
        if (map == null) {
            return stringBuffer.toString();
        }
        int i = 0;
        for (String str : map.keySet()) {
            if (i == 0) {
                stringBuffer.append("?");
            } else {
                stringBuffer.append(C4745bt.f20071b);
            }
            stringBuffer.append(str);
            stringBuffer.append(SimpleComparison.f23609c);
            stringBuffer.append(map.get(str));
            i++;
        }
        return stringBuffer.toString();
    }

    /* renamed from: c */
    private RequestBody m21442c(Map<String, String> map) {
        if (map.containsKey("img")) {
            MultipartBody.Builder builder = new MultipartBody.Builder();
            builder.setType(MediaType.parse("multipart/form-data; charset=utf-8"));
            if (map != null) {
                for (String str : map.keySet()) {
                    if (!str.equals("img")) {
                        builder.addFormDataPart(str, map.get(str));
                    }
                }
                String str2 = map.get("img");
                builder.addFormDataPart("img", str2.substring(str2.lastIndexOf(47) + 1), RequestBody.create(MediaType.parse("image/*"), new File(str2)));
            }
            return builder.build();
        }
        FormBody.Builder builder2 = new FormBody.Builder();
        if (map != null) {
            for (String str3 : map.keySet()) {
                builder2.add(str3, map.get(str3));
            }
        }
        return builder2.build();
    }

    /* loaded from: classes.dex */
    public class CallbackToMainThread implements Callback {

        /* renamed from: d */
        private Callback2 f7512d;

        /* renamed from: e */
        private Call f7513e = null;

        /* renamed from: f */
        private IOException f7514f = null;

        /* renamed from: g */
        private byte[] f7515g = null;

        /* renamed from: a */
        Runnable f7509a = new Runnable() { // from class: com.cyjh.ddy.net.helper.OkHttpClientMethod.CallbackToMainThread.1
            @Override // java.lang.Runnable
            public void run() {
                CallbackToMainThread.this.f7512d.onFailure(CallbackToMainThread.this.f7513e, CallbackToMainThread.this.f7514f);
            }
        };

        /* renamed from: b */
        Runnable f7510b = new Runnable() { // from class: com.cyjh.ddy.net.helper.OkHttpClientMethod.CallbackToMainThread.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    CallbackToMainThread.this.f7512d.onResponse(CallbackToMainThread.this.f7513e, CallbackToMainThread.this.f7515g);
                } catch (IOException e) {
                    CallbackToMainThread.this.f7512d.onFailure(CallbackToMainThread.this.f7513e, e);
                }
            }
        };

        public CallbackToMainThread(Callback2 callback2) {
            this.f7512d = null;
            this.f7512d = callback2;
        }

        @Override // okhttp3.Callback
        public void onFailure(Call call, IOException iOException) {
            this.f7513e = call;
            this.f7514f = iOException;
            new Handler(Looper.getMainLooper()).post(this.f7509a);
        }

        @Override // okhttp3.Callback
        public void onResponse(Call call, Response response) {
            this.f7513e = call;
            try {
                this.f7515g = response.body().bytes();
                new Handler(Looper.getMainLooper()).post(this.f7510b);
            } catch (IOException e) {
                this.f7514f = e;
                new Handler(Looper.getMainLooper()).post(this.f7509a);
            }
        }
    }

    /* renamed from: a */
    public void m21447a(String str, Map<String, String> map, Map<String, String> map2, Callback2 callback2) {
        try {
            Request.Builder builder = new Request.Builder();
            this.f7507b.newCall(builder.url(str + m21443b(map)).headers(m21445a(map2)).get().build()).enqueue(new CallbackToMainThread(callback2));
        } catch (Throwable th) {
            th.printStackTrace();
            callback2.onFailure(null, new IOException(th.getMessage()));
        }
    }

    /* renamed from: a */
    public void m21446a(String str, Map<String, String> map, Map<String, String> map2, Map<String, String> map3, Callback2 callback2) {
        try {
            Request.Builder builder = new Request.Builder();
            this.f7507b.newCall(builder.url(str + m21443b(map)).headers(m21445a(map3)).post(m21442c(map2)).build()).enqueue(new CallbackToMainThread(callback2));
        } catch (Throwable th) {
            th.printStackTrace();
            callback2.onFailure(null, new IOException(th.getMessage()));
        }
    }
}
