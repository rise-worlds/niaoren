package com.tencent.smtt.utils;

import android.content.Context;
import android.os.Environment;
import android.os.Looper;
import android.os.Process;
import android.widget.TextView;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Locale;

/* loaded from: classes2.dex */
public class TbsLogClient {

    /* renamed from: a */
    static TbsLogClient f13265a = null;

    /* renamed from: c */
    static File f13266c = null;

    /* renamed from: d */
    static String f13267d = null;

    /* renamed from: e */
    static byte[] f13268e = null;

    /* renamed from: i */
    private static boolean f13269i = true;

    /* renamed from: b */
    TextView f13270b;

    /* renamed from: f */
    private SimpleDateFormat f13271f;

    /* renamed from: g */
    private Context f13272g;

    /* renamed from: h */
    private StringBuffer f13273h = new StringBuffer();

    /* renamed from: d */
    public void m16524d(String str, String str2) {
    }

    /* renamed from: e */
    public void m16523e(String str, String str2) {
    }

    /* renamed from: i */
    public void m16522i(String str, String str2) {
    }

    /* renamed from: v */
    public void m16521v(String str, String str2) {
    }

    /* renamed from: w */
    public void m16520w(String str, String str2) {
    }

    public TbsLogClient(Context context) {
        this.f13271f = null;
        this.f13272g = null;
        try {
            this.f13272g = context.getApplicationContext();
            this.f13271f = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss.SSS", Locale.US);
        } catch (Exception unused) {
            this.f13271f = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss.SSS");
        }
    }

    /* renamed from: a */
    private void m16525a() {
        try {
            if (f13266c == null) {
                if (Environment.getExternalStorageState().equals("mounted")) {
                    String a = FileUtil.m16465a(this.f13272g, 6);
                    if (a == null) {
                        f13266c = null;
                    } else {
                        f13266c = new File(a, "tbslog.txt");
                        f13267d = LogFileUtils.createKey();
                        f13268e = LogFileUtils.createHeaderText(f13266c.getName(), f13267d);
                    }
                } else {
                    f13266c = null;
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (SecurityException e2) {
            e2.printStackTrace();
        }
    }

    public void writeLog(String str) {
        try {
            String format = this.f13271f.format(Long.valueOf(System.currentTimeMillis()));
            StringBuffer stringBuffer = this.f13273h;
            stringBuffer.append(format);
            stringBuffer.append(" pid=");
            stringBuffer.append(Process.myPid());
            stringBuffer.append(" tid=");
            stringBuffer.append(Process.myTid());
            stringBuffer.append(str);
            stringBuffer.append("\n");
            if (Thread.currentThread() != Looper.getMainLooper().getThread() || f13269i) {
                writeLogToDisk();
            }
            if (this.f13273h.length() > 524288) {
                this.f13273h.delete(0, this.f13273h.length());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeLogToDisk() {
        try {
            m16525a();
            if (f13266c != null) {
                LogFileUtils.writeDataToStorage(f13266c, f13267d, f13268e, this.f13273h.toString(), true);
                this.f13273h.delete(0, this.f13273h.length());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: com.tencent.smtt.utils.TbsLogClient$a */
    /* loaded from: classes2.dex */
    private class RunnableC2635a implements Runnable {

        /* renamed from: a */
        String f13274a;

        RunnableC2635a(String str) {
            this.f13274a = null;
            this.f13274a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (TbsLogClient.this.f13270b != null) {
                TextView textView = TbsLogClient.this.f13270b;
                textView.append(this.f13274a + "\n");
            }
        }
    }

    public void showLog(String str) {
        TextView textView = this.f13270b;
        if (textView != null) {
            textView.post(new RunnableC2635a(str));
        }
    }

    public void setLogView(TextView textView) {
        this.f13270b = textView;
    }

    public static void setWriteLogJIT(boolean z) {
        f13269i = z;
    }
}
