package com.tendcloud.tenddata;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.SystemClock;
import com.tendcloud.tenddata.C2832ea;
import com.tendcloud.tenddata.C2945hn;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Calendar;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.zip.CRC32;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.id */
/* loaded from: classes2.dex */
public class C2966id {

    /* renamed from: a */
    private static String f14227a = "utf-8";

    /* renamed from: c */
    private static final int f14229c = 5;

    /* renamed from: d */
    private static final int f14230d = 6;

    /* renamed from: e */
    private static final int f14231e = 30000;

    /* renamed from: h */
    private static final boolean f14232h = true;

    /* renamed from: i */
    private static final int f14233i = 65536;

    /* renamed from: k */
    private static HandlerThread f14235k;

    /* renamed from: f */
    private long f14236f = 0;

    /* renamed from: g */
    private boolean f14237g = true;

    /* renamed from: l */
    private Handler f14238l;

    /* renamed from: b */
    private static final CRC32 f14228b = new CRC32();

    /* renamed from: j */
    private static volatile C2966id f14234j = null;

    static {
        try {
            C2858ev.m15778a().register(m15457a());
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d */
    public void m15447d() {
        int i;
        if (!this.f14238l.hasMessages(5)) {
            try {
                SecureRandom b = C2855es.m15794b();
                int[] a = C2664ab.m16361a();
                if (C2836ec.m15862j(C2664ab.f13513g)) {
                    i = !this.f14237g ? (a[1] * 1) + b.nextInt(30000) : a[1];
                } else {
                    i = !this.f14237g ? (a[0] * 1) + (b.nextInt(60000) - 30000) : a[0];
                }
                int i2 = C2664ab.f13496M;
                if (i <= 1800000) {
                    i2 = i;
                }
                Iterator it = AbstractC2790d.getFeaturesList().iterator();
                while (it.hasNext()) {
                    this.f14238l.sendMessageDelayed(Message.obtain(this.f14238l, 5, (AbstractC2790d) it.next()), i2);
                }
            } catch (Throwable th) {
                C2933hb.postSDKError(th);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e */
    public static boolean m15446e() {
        try {
            Calendar instance = Calendar.getInstance();
            long j = (instance.get(6) * 1000) + instance.get(3);
            instance.setTimeInMillis(C2812dr.m16014e());
            long j2 = (instance.get(6) * 1000) + instance.get(3);
            long b = C2843eh.m15841b(C2664ab.f13513g, C2664ab.f13527u, C2664ab.f13530x, 0L);
            if (System.currentTimeMillis() - C2812dr.m16014e() < 5000 || Math.abs((j2 / 1000) - (j / 1000)) == 1) {
                return true;
            }
            return Math.abs((j % 100) - (b % 100)) >= 1;
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: f */
    public static boolean m15445f() {
        try {
            Calendar instance = Calendar.getInstance();
            long j = (instance.get(6) * 1000) + instance.get(3);
            instance.setTimeInMillis(C2812dr.m16014e());
            long j2 = (instance.get(6) * 1000) + instance.get(3);
            long b = C2843eh.m15841b(C2664ab.f13513g, C2664ab.f13527u, C2664ab.f13531y, 0L);
            if (System.currentTimeMillis() - C2812dr.m16014e() < 5000 || Math.abs((j2 / 1000) - (j / 1000)) == 1) {
                return true;
            }
            return Math.abs((j % 100) - (b % 100)) >= 1;
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
            return false;
        }
    }

    /* renamed from: a */
    private void m15453a(String str, AbstractC2790d dVar, boolean z) {
        String str2 = "[" + dVar.name() + "] " + str;
        switch (dVar.index()) {
            case 0:
            case 1:
            case 3:
            case 7:
            case 8:
                if (z) {
                    C2811dq.iForDeveloper(str2);
                    return;
                } else {
                    C2811dq.dForInternal(str2);
                    return;
                }
            case 2:
            case 5:
            case 6:
            default:
                C2811dq.iForInternal(str2);
                return;
            case 4:
                if (z) {
                    C2811dq.iForDeveloper("[Push] " + str);
                    return;
                }
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m15456a(AbstractC2790d dVar) {
        boolean z;
        Throwable th;
        TreeSet a;
        if (C2836ec.m15869c(C2664ab.f13513g) && dVar.needToSendData()) {
            try {
                z = C2832ea.m15899a(C2832ea.EnumC2834b.getFeatureLockFileName(dVar.index()));
            } catch (Throwable th2) {
                th = th2;
                z = false;
            }
            if (z) {
                try {
                    a = C2969ig.m15443a().m15442a(dVar);
                } catch (Throwable th3) {
                    th = th3;
                    try {
                        C2933hb.postSDKError(th);
                        if (!z) {
                            return;
                        }
                        C2832ea.releaseFileLock(C2832ea.EnumC2834b.getFeatureLockFileName(dVar.index()));
                        return;
                    } finally {
                        if (z) {
                            C2832ea.releaseFileLock(C2832ea.EnumC2834b.getFeatureLockFileName(dVar.index()));
                        }
                    }
                }
                if (a != null && a.size() > 0) {
                    m15453a("New data found, Submitting...", dVar, true);
                    byte[] a2 = m15451a(a, C2855es.m15782e(m15452a(a)));
                    f14228b.reset();
                    f14228b.update(a2);
                    StringBuilder sb = new StringBuilder(dVar.getUrl());
                    if (dVar.name().equals("TRACKING")) {
                        sb.append("/" + Long.toHexString(f14228b.getValue()));
                        sb.append("/1");
                    } else {
                        sb.append("/" + Long.toHexString(f14228b.getValue()));
                    }
                    if (C2813ds.m15995a(C2664ab.f13513g, dVar, sb.toString(), a2, "").getStatusCode() == 200) {
                        this.f14236f = SystemClock.elapsedRealtime();
                        this.f14237g = true;
                        C2969ig.m15443a().sendMessageSuccess(dVar);
                        m15453a("Data submitted successfully!", dVar, true);
                        C2664ab.f13495L.set(0);
                    } else {
                        C2664ab.f13495L.incrementAndGet();
                        C2969ig.m15443a().sendMessageFaild(dVar);
                        this.f14237g = false;
                        m15453a("Failed to submit data!", dVar, true);
                    }
                    if (z) {
                        C2832ea.releaseFileLock(C2832ea.EnumC2834b.getFeatureLockFileName(dVar.index()));
                        return;
                    }
                    return;
                }
                m15453a("No new data found!", dVar, false);
                if (z) {
                    C2832ea.releaseFileLock(C2832ea.EnumC2834b.getFeatureLockFileName(dVar.index()));
                }
            }
        }
    }

    /* renamed from: a */
    private static String m15452a(TreeSet treeSet) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Iterator it = treeSet.iterator();
        while (it.hasNext()) {
            C2970ih ihVar = (C2970ih) it.next();
            if (ihVar.m15433c() != null && ihVar.m15433c().length > 0) {
                sb.append(new String(ihVar.m15433c()));
                sb.append(",");
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");
        return sb.toString();
    }

    /* renamed from: a */
    private static byte[] m15451a(TreeSet treeSet, byte[] bArr) {
        try {
            if (bArr.length < 65536) {
                return bArr;
            }
            int size = treeSet.size();
            for (int i = 0; i < size / 2; i++) {
                treeSet.pollLast();
            }
            return m15451a(treeSet, C2855es.m15782e(m15452a(treeSet)));
        } catch (Throwable unused) {
            return bArr;
        }
    }

    /* renamed from: a */
    public static byte[] m15450a(byte[] bArr) {
        BufferedInputStream bufferedInputStream;
        byte[] bArr2 = new byte[2048];
        try {
            bufferedInputStream = new BufferedInputStream(new InflaterInputStream(new ByteArrayInputStream(bArr), new Inflater(false)));
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                while (true) {
                    int read = bufferedInputStream.read(bArr2);
                    if (read != -1) {
                        byteArrayOutputStream.write(bArr2, 0, read);
                    } else {
                        byteArrayOutputStream.close();
                        bufferedInputStream.close();
                        return byteArrayOutputStream.toByteArray();
                    }
                }
            } catch (Exception unused) {
                if (bufferedInputStream != null) {
                    try {
                        bufferedInputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }
        } catch (Exception unused2) {
            bufferedInputStream = null;
        }
    }

    /* renamed from: g */
    private static void m15444g() {
        if (C2836ec.m15869c(C2664ab.f13513g)) {
            try {
                C2852ep.f13904a.execute(new RunnableC2967ie());
            } catch (Throwable unused) {
            }
        }
    }

    public final void onTDEBEventForwardRequest(C2945hn hnVar) {
        if (hnVar != null && C2664ab.f13513g != null) {
            if (hnVar.f14179b.equals(C2945hn.EnumC2946a.IMMEDIATELY)) {
                if (this.f14238l.hasMessages(5, hnVar.f14178a)) {
                    this.f14238l.removeMessages(5);
                }
                Message.obtain(this.f14238l, 5, hnVar.f14178a).sendToTarget();
            } else if (hnVar.f14179b.equals(C2945hn.EnumC2946a.HIGH)) {
                if (this.f14238l.hasMessages(5)) {
                    this.f14238l.removeMessages(5);
                }
                long abs = Math.abs((SystemClock.elapsedRealtime() - this.f14236f) - C2664ab.f13498O);
                if (abs > C2664ab.f13498O) {
                    abs = 30000;
                }
                this.f14238l.sendMessageDelayed(Message.obtain(this.f14238l, 5, hnVar.f14178a), abs);
            }
        }
    }

    /* renamed from: a */
    public static C2966id m15457a() {
        if (f14234j == null) {
            synchronized (C2966id.class) {
                if (f14234j == null) {
                    f14234j = new C2966id();
                }
            }
        }
        return f14234j;
    }

    private C2966id() {
        this.f14238l = null;
        f14235k = new HandlerThread("ModuleDataForward");
        f14235k.start();
        this.f14238l = new HandlerC2968if(this, f14235k.getLooper());
        m15444g();
        m15447d();
    }
}
