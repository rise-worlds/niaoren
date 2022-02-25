package com.cyjh.ddy.media.media.qemu;

import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Build;
import android.view.Surface;
import com.blankj.utilcode.util.TimeUtils;
import com.cyjh.ddy.base.utils.CLog;
import com.cyjh.ddy.media.beaninner.VedioPackage;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

/* renamed from: com.cyjh.ddy.media.media.qemu.d */
/* loaded from: classes.dex */
public abstract class LiveMediaCodecThread {

    /* renamed from: a */
    public Runnable f7352a;

    /* renamed from: b */
    private String f7353b;

    /* renamed from: c */
    private MediaCodec.BufferInfo f7354c;

    /* renamed from: d */
    private int f7355d;

    /* renamed from: e */
    private int f7356e;

    /* renamed from: f */
    private MediaCodec f7357f;

    /* renamed from: g */
    private final LinkedBlockingQueue<VedioPackage> f7358g;

    /* renamed from: h */
    private final LinkedBlockingQueue<VedioPackage> f7359h;

    /* renamed from: i */
    private boolean f7360i;

    /* renamed from: j */
    private Thread f7361j;

    /* renamed from: k */
    private Thread f7362k;

    /* renamed from: l */
    private Thread f7363l;

    /* renamed from: m */
    private boolean f7364m;

    /* renamed from: n */
    private long f7365n;

    /* renamed from: o */
    private long f7366o;

    /* renamed from: p */
    private long f7367p;

    /* renamed from: q */
    private long f7368q;

    /* renamed from: r */
    private long f7369r;

    /* renamed from: s */
    private long f7370s;

    /* renamed from: t */
    private long f7371t;

    /* renamed from: u */
    private long f7372u;

    /* renamed from: v */
    private ArrayList<Long> f7373v;

    /* renamed from: w */
    private ArrayList<Long> f7374w;

    /* renamed from: x */
    private Runnable f7375x;

    /* renamed from: y */
    private Runnable f7376y;

    /* renamed from: a */
    protected abstract MediaFormat mo21546a(String str);

    /* renamed from: a */
    protected abstract String mo21545a(boolean z);

    /* renamed from: a */
    protected void mo21583a() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void mo21580a(MediaFormat mediaFormat) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public boolean mo21581a(MediaCodec.BufferInfo bufferInfo, ByteBuffer byteBuffer) {
        return false;
    }

    /* renamed from: b */
    protected void mo21572b() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: c */
    public abstract boolean mo21544c();

    /* renamed from: a */
    public boolean m21578a(VedioPackage bVar) {
        if (bVar == null) {
            CLog.m21883e(this.f7353b, "render avPacket==null");
            return false;
        }
        int dequeueInputBuffer = this.f7357f.dequeueInputBuffer(100L);
        while (dequeueInputBuffer < 0) {
            dequeueInputBuffer = this.f7357f.dequeueInputBuffer(100L);
            if (this.f7360i) {
                return false;
            }
        }
        if (dequeueInputBuffer < 0) {
            return true;
        }
        if (mo21544c()) {
            this.f7373v.add(Long.valueOf(TimeUtils.m23128a()));
        }
        ByteBuffer a = m21582a(dequeueInputBuffer);
        if (a == null) {
            return true;
        }
        a.put(bVar.f7240a, 0, bVar.f7242c);
        this.f7357f.queueInputBuffer(dequeueInputBuffer, 0, bVar.f7242c, bVar.f7241b, bVar.f7244e);
        return true;
    }

    /* renamed from: a */
    private ByteBuffer m21582a(int i) {
        if (this.f7357f == null) {
            return null;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            return this.f7357f.getInputBuffer(i);
        }
        return this.f7357f.getInputBuffers()[i];
    }

    public LiveMediaCodecThread(String str) {
        this(Integer.MAX_VALUE, 1, str);
    }

    public LiveMediaCodecThread(int i, int i2, String str) {
        this.f7364m = false;
        this.f7365n = 0L;
        this.f7366o = 0L;
        this.f7367p = 0L;
        this.f7368q = 0L;
        this.f7369r = 0L;
        this.f7370s = 0L;
        this.f7371t = 0L;
        this.f7372u = 0L;
        this.f7375x = new Runnable() { // from class: com.cyjh.ddy.media.media.qemu.LiveMediaCodecThread$1
            /* JADX WARN: Incorrect condition in loop: B:4:0x0011 */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void run() {
                /*
                    r4 = this;
                    com.cyjh.ddy.media.media.qemu.d r0 = com.cyjh.ddy.media.media.qemu.LiveMediaCodecThread.this
                    java.lang.String r0 = com.cyjh.ddy.media.media.qemu.LiveMediaCodecThread.m21577a(r0)
                    java.lang.String r1 = "LiveMediaCodecThread inputTempRun start"
                    com.cyjh.ddy.base.utils.CLog.m21882i(r0, r1)
                L_0x000b:
                    com.cyjh.ddy.media.media.qemu.d r0 = com.cyjh.ddy.media.media.qemu.LiveMediaCodecThread.this
                    boolean r0 = com.cyjh.ddy.media.media.qemu.LiveMediaCodecThread.m21569b(r0)
                    if (r0 != 0) goto L_0x0048
                L_0x0013:
                    com.cyjh.ddy.media.media.qemu.d r0 = com.cyjh.ddy.media.media.qemu.LiveMediaCodecThread.this     // Catch: Exception -> 0x0046
                    java.util.concurrent.LinkedBlockingQueue r0 = com.cyjh.ddy.media.media.qemu.LiveMediaCodecThread.m21564d(r0)     // Catch: Exception -> 0x0046
                    com.cyjh.ddy.media.media.qemu.d r1 = com.cyjh.ddy.media.media.qemu.LiveMediaCodecThread.this     // Catch: Exception -> 0x0046
                    int r1 = com.cyjh.ddy.media.media.qemu.LiveMediaCodecThread.m21567c(r1)     // Catch: Exception -> 0x0046
                    long r1 = (long) r1     // Catch: Exception -> 0x0046
                    java.util.concurrent.TimeUnit r3 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch: Exception -> 0x0046
                    java.lang.Object r0 = r0.poll(r1, r3)     // Catch: Exception -> 0x0046
                    com.cyjh.ddy.media.beaninner.b r0 = (com.cyjh.ddy.media.beaninner.VedioPackage) r0     // Catch: Exception -> 0x0046
                    if (r0 != 0) goto L_0x0032
                    com.cyjh.ddy.media.media.qemu.d r1 = com.cyjh.ddy.media.media.qemu.LiveMediaCodecThread.this     // Catch: Exception -> 0x0046
                    boolean r1 = com.cyjh.ddy.media.media.qemu.LiveMediaCodecThread.m21569b(r1)     // Catch: Exception -> 0x0046
                    if (r1 == 0) goto L_0x0013
                L_0x0032:
                    com.cyjh.ddy.media.media.qemu.d r1 = com.cyjh.ddy.media.media.qemu.LiveMediaCodecThread.this     // Catch: Exception -> 0x0046
                    java.util.concurrent.LinkedBlockingQueue r1 = com.cyjh.ddy.media.media.qemu.LiveMediaCodecThread.m21561e(r1)     // Catch: Exception -> 0x0046
                    r1.put(r0)     // Catch: Exception -> 0x0046
                    com.cyjh.ddy.media.media.qemu.d r0 = com.cyjh.ddy.media.media.qemu.LiveMediaCodecThread.this     // Catch: Exception -> 0x0046
                    int r0 = com.cyjh.ddy.media.media.qemu.LiveMediaCodecThread.m21567c(r0)     // Catch: Exception -> 0x0046
                    long r0 = (long) r0     // Catch: Exception -> 0x0046
                    java.lang.Thread.sleep(r0)     // Catch: Exception -> 0x0046
                    goto L_0x000b
                L_0x0046:
                    goto L_0x000b
                L_0x0048:
                    com.cyjh.ddy.media.media.qemu.d r0 = com.cyjh.ddy.media.media.qemu.LiveMediaCodecThread.this
                    java.lang.String r0 = com.cyjh.ddy.media.media.qemu.LiveMediaCodecThread.m21577a(r0)
                    java.lang.String r1 = "LiveMediaCodecThread inputTempRun end"
                    com.cyjh.ddy.base.utils.CLog.m21882i(r0, r1)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.cyjh.ddy.media.media.qemu.LiveMediaCodecThread$1.run():void");
            }
        };
        this.f7376y = new Runnable() { // from class: com.cyjh.ddy.media.media.qemu.LiveMediaCodecThread$2
            /* JADX WARN: Incorrect condition in loop: B:4:0x0024 */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void run() {
                /*
                    Method dump skipped, instructions count: 306
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: com.cyjh.ddy.media.media.qemu.LiveMediaCodecThread$2.run():void");
            }
        };
        this.f7352a = new Runnable() { // from class: com.cyjh.ddy.media.media.qemu.LiveMediaCodecThread$3
            @Override // java.lang.Runnable
            public void run() {
                String str2;
                boolean z;
                String str3;
                MediaCodec mediaCodec;
                MediaCodec mediaCodec2;
                MediaCodec mediaCodec3;
                MediaCodec.BufferInfo bufferInfo;
                boolean z2;
                MediaCodec mediaCodec4;
                MediaCodec.BufferInfo bufferInfo2;
                long j;
                long j2;
                long j3;
                long j4;
                String str4;
                LinkedBlockingQueue linkedBlockingQueue;
                LinkedBlockingQueue linkedBlockingQueue2;
                ArrayList arrayList;
                ArrayList arrayList2;
                ArrayList arrayList3;
                MediaCodec mediaCodec5;
                str2 = LiveMediaCodecThread.this.f7353b;
                CLog.m21882i(str2, "LiveMediaCodecThread outputRun start");
                LiveMediaCodecThread.this.f7371t = 0L;
                LiveMediaCodecThread.this.f7372u = 0L;
                while (true) {
                    z = LiveMediaCodecThread.this.f7360i;
                    if (z) {
                        break;
                    }
                    mediaCodec = LiveMediaCodecThread.this.f7357f;
                    if (mediaCodec == null) {
                        break;
                    }
                    mediaCodec2 = LiveMediaCodecThread.this.f7357f;
                    synchronized (mediaCodec2) {
                        try {
                            long a = TimeUtils.m23128a();
                            mediaCodec3 = LiveMediaCodecThread.this.f7357f;
                            bufferInfo = LiveMediaCodecThread.this.f7354c;
                            int dequeueOutputBuffer = mediaCodec3.dequeueOutputBuffer(bufferInfo, 100L);
                            if (dequeueOutputBuffer == -2) {
                                LiveMediaCodecThread dVar = LiveMediaCodecThread.this;
                                mediaCodec5 = LiveMediaCodecThread.this.f7357f;
                                dVar.mo21580a(mediaCodec5.getOutputFormat());
                            }
                            if (dequeueOutputBuffer >= 0) {
                                z2 = LiveMediaCodecThread.this.f7360i;
                                if (z2) {
                                    break;
                                }
                                mediaCodec4 = LiveMediaCodecThread.this.f7357f;
                                LiveMediaCodecThread dVar2 = LiveMediaCodecThread.this;
                                bufferInfo2 = LiveMediaCodecThread.this.f7354c;
                                mediaCodec4.releaseOutputBuffer(dequeueOutputBuffer, !dVar2.mo21581a(bufferInfo2, m21641a(dequeueOutputBuffer)));
                                long a2 = TimeUtils.m23128a();
                                if (LiveMediaCodecThread.this.mo21544c()) {
                                    arrayList = LiveMediaCodecThread.this.f7374w;
                                    int size = arrayList.size();
                                    arrayList2 = LiveMediaCodecThread.this.f7373v;
                                    if (size < arrayList2.size()) {
                                        arrayList3 = LiveMediaCodecThread.this.f7374w;
                                        arrayList3.add(Long.valueOf(a2));
                                    }
                                }
                                long j5 = a2 - a;
                                LiveMediaCodecThread dVar3 = LiveMediaCodecThread.this;
                                j = LiveMediaCodecThread.this.f7371t;
                                dVar3.f7371t = j + j5;
                                LiveMediaCodecThread dVar4 = LiveMediaCodecThread.this;
                                j2 = LiveMediaCodecThread.this.f7372u;
                                dVar4.f7372u = j2 + 1;
                                j3 = LiveMediaCodecThread.this.f7371t;
                                j4 = LiveMediaCodecThread.this.f7372u;
                                long j6 = j3 / j4;
                                if (j6 > 1 && j5 > 3 * j6) {
                                    str4 = LiveMediaCodecThread.this.f7353b;
                                    linkedBlockingQueue = LiveMediaCodecThread.this.f7359h;
                                    linkedBlockingQueue2 = LiveMediaCodecThread.this.f7358g;
                                    CLog.m21883e(str4, String.format("解码 输出avg=%d,cur=%d, leftsize=%d,%d", Long.valueOf(j6), Long.valueOf(j5), Integer.valueOf(linkedBlockingQueue.size()), Integer.valueOf(linkedBlockingQueue2.size())));
                                }
                            }
                        } catch (Exception e) {
                            LiveMediaCodecThread.this.m21573a(e);
                        } catch (Throwable th) {
                            th.printStackTrace();
                        }
                    }
                }
                str3 = LiveMediaCodecThread.this.f7353b;
                CLog.m21882i(str3, "LiveMediaCodecThread outputRun end");
            }

            /* renamed from: a */
            private ByteBuffer m21641a(int i3) {
                MediaCodec mediaCodec;
                MediaCodec mediaCodec2;
                MediaCodec mediaCodec3;
                mediaCodec = LiveMediaCodecThread.this.f7357f;
                if (mediaCodec == null) {
                    return null;
                }
                if (Build.VERSION.SDK_INT >= 21) {
                    mediaCodec3 = LiveMediaCodecThread.this.f7357f;
                    return mediaCodec3.getOutputBuffer(i3);
                }
                mediaCodec2 = LiveMediaCodecThread.this.f7357f;
                return mediaCodec2.getOutputBuffers()[i3];
            }
        };
        this.f7353b = str;
        this.f7358g = new LinkedBlockingQueue<>();
        this.f7359h = new LinkedBlockingQueue<>();
        this.f7354c = new MediaCodec.BufferInfo();
        this.f7355d = i;
        this.f7356e = i2;
        this.f7360i = true;
        this.f7373v = new ArrayList<>();
        this.f7374w = new ArrayList<>();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m21573a(Exception exc) {
        String str = this.f7353b;
        CLog.m21883e(str, "catchRenderException " + exc.getMessage());
    }

    /* renamed from: d */
    public boolean m21565d() {
        return !this.f7360i;
    }

    /* renamed from: e */
    public void m21562e() {
        m21579a((Surface) null, false);
    }

    /* renamed from: a */
    public void m21579a(Surface surface, boolean z) {
        m21571b(surface, z);
        if (this.f7357f != null) {
            this.f7360i = false;
            mo21583a();
            m21552j();
            this.f7362k = new Thread(this.f7376y);
            this.f7361j = new Thread(this.f7375x);
            this.f7363l = new Thread(this.f7352a);
            this.f7362k.start();
            this.f7361j.start();
            this.f7363l.start();
        }
    }

    /* renamed from: b */
    private void m21571b(Surface surface, boolean z) {
        MediaFormat a;
        this.f7365n = TimeUtils.m23128a();
        this.f7367p = 0L;
        this.f7368q = 0L;
        while (true) {
            try {
                this.f7357f = MediaCodec.createDecoderByType(mo21545a(z));
                synchronized (this.f7357f) {
                    if (!(this.f7357f == null || (a = mo21546a(mo21545a(z))) == null)) {
                        this.f7357f.configure(a, surface, (MediaCrypto) null, 0);
                        this.f7357f.start();
                    }
                }
                return;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e2) {
                e2.printStackTrace();
                return;
            }
        }
    }

    /* renamed from: i */
    private void m21554i() {
        try {
            if (this.f7357f != null) {
                synchronized (this.f7357f) {
                    this.f7357f.stop();
                    this.f7357f.release();
                }
            }
            CLog.m21882i(this.f7353b, String.format("解码 接收avg=%d,count=%d,  输入avg=%d,count=%d,  输出avg=%d,count=%d", Integer.valueOf(this.f7368q == 0 ? 0 : (int) (this.f7367p / this.f7368q)), Long.valueOf(this.f7368q), Integer.valueOf(this.f7370s == 0 ? 0 : (int) (this.f7369r / this.f7370s)), Long.valueOf(this.f7370s), Integer.valueOf(this.f7372u == 0 ? 0 : (int) (this.f7371t / this.f7372u)), Long.valueOf(this.f7372u)));
        } catch (IllegalStateException unused) {
        } finally {
            this.f7357f = null;
        }
    }

    /* renamed from: f */
    public void m21560f() {
        try {
            if (!this.f7360i) {
                this.f7360i = true;
                m21554i();
                this.f7358g.clear();
                this.f7359h.clear();
                m21552j();
                mo21572b();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: b */
    public void m21570b(VedioPackage bVar) {
        if (this.f7360i || !this.f7364m) {
            String str = this.f7353b;
            StringBuilder sb = new StringBuilder();
            sb.append("putPacket ...,  but  mStopped=");
            sb.append(this.f7360i ? "true" : "false");
            sb.append(",isRunning=");
            sb.append(this.f7364m ? "true" : "false");
            CLog.m21883e(str, sb.toString());
        }
        try {
            this.f7366o = TimeUtils.m23128a() - this.f7365n;
            this.f7367p += this.f7366o;
            this.f7365n = TimeUtils.m23128a();
            this.f7368q++;
            this.f7359h.put(bVar);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /* renamed from: g */
    public int m21558g() {
        return this.f7358g.size() + this.f7359h.size();
    }

    /* renamed from: h */
    public long m21556h() {
        int min = Math.min(this.f7373v.size(), this.f7374w.size());
        long j = 0;
        if (min > 0) {
            long j2 = 0;
            for (int i = 0; i < min; i++) {
                j2 += this.f7374w.get(i).longValue() - this.f7373v.get(i).longValue();
            }
            if (j2 > 0) {
                j = j2 / min;
            }
        }
        m21552j();
        return j;
    }

    /* renamed from: j */
    private void m21552j() {
        this.f7374w.clear();
        this.f7373v.clear();
    }
}
