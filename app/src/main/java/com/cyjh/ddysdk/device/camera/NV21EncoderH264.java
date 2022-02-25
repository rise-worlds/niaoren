package com.cyjh.ddysdk.device.camera;

import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.view.Surface;
import com.cyjh.ddy.base.utils.CLog;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.ArrayBlockingQueue;

/* loaded from: classes.dex */
public class NV21EncoderH264 {

    /* renamed from: a */
    public static final String f7704a = "sdk-cameraIng";

    /* renamed from: b */
    private int f7705b;

    /* renamed from: c */
    private int f7706c;

    /* renamed from: e */
    private MediaCodec f7708e;

    /* renamed from: f */
    private EncoderListener f7709f;

    /* renamed from: k */
    private long f7714k;

    /* renamed from: d */
    private int f7707d = 20;

    /* renamed from: g */
    private int f7710g = 5;

    /* renamed from: h */
    private boolean f7711h = false;

    /* renamed from: i */
    private int f7712i = this.f7707d / 2;

    /* renamed from: j */
    private ArrayBlockingQueue<byte[]> f7713j = new ArrayBlockingQueue<>(this.f7712i);

    /* renamed from: l */
    private boolean f7715l = true;

    /* renamed from: m */
    private int f7716m = 0;

    /* loaded from: classes.dex */
    public interface EncoderListener {
        void h264(byte[] bArr);
    }

    public NV21EncoderH264(int i, int i2) {
        this.f7705b = i;
        this.f7706c = i2;
        m21280d();
    }

    /* renamed from: a */
    public void m21292a() {
        m21284b();
        this.f7713j.clear();
    }

    /* renamed from: a */
    public void m21291a(int i) {
        this.f7707d = i;
    }

    /* renamed from: d */
    private void m21280d() {
        MediaFormat createVideoFormat = MediaFormat.createVideoFormat("video/avc", this.f7705b, this.f7706c);
        createVideoFormat.setInteger("color-format", 21);
        createVideoFormat.setInteger("bitrate", this.f7705b * this.f7706c * this.f7710g);
        createVideoFormat.setInteger("frame-rate", this.f7707d);
        createVideoFormat.setInteger("i-frame-interval", 1);
        try {
            this.f7708e = MediaCodec.createEncoderByType("video/avc");
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.f7708e.configure(createVideoFormat, (Surface) null, (MediaCrypto) null, 1);
        this.f7708e.start();
        m21282c();
    }

    /* renamed from: b */
    public void m21284b() {
        this.f7711h = false;
        try {
            this.f7708e.stop();
            this.f7708e.release();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: c */
    public void m21282c() {
        new Thread(new Runnable() { // from class: com.cyjh.ddysdk.device.camera.NV21EncoderH264.1
            @Override // java.lang.Runnable
            public void run() {
                NV21EncoderH264.this.f7711h = true;
                long j = -1;
                byte[] bArr = null;
                long j2 = -1;
                long j3 = 0;
                while (NV21EncoderH264.this.f7711h) {
                    CLog.m21882i(NV21EncoderH264.f7704a, "h264start");
                    if (NV21EncoderH264.this.f7713j.size() > 0) {
                        bArr = (byte[]) NV21EncoderH264.this.f7713j.poll();
                    }
                    if (bArr != null) {
                        try {
                            ByteBuffer[] inputBuffers = NV21EncoderH264.this.f7708e.getInputBuffers();
                            ByteBuffer[] outputBuffers = NV21EncoderH264.this.f7708e.getOutputBuffers();
                            int dequeueInputBuffer = NV21EncoderH264.this.f7708e.dequeueInputBuffer(j);
                            if (dequeueInputBuffer >= 0) {
                                long a = NV21EncoderH264.this.m21290a(j3);
                                ByteBuffer byteBuffer = inputBuffers[dequeueInputBuffer];
                                byteBuffer.clear();
                                byteBuffer.put(bArr);
                                NV21EncoderH264.this.f7708e.queueInputBuffer(dequeueInputBuffer, 0, bArr.length, a, 0);
                                j3++;
                            }
                            MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
                            int dequeueOutputBuffer = NV21EncoderH264.this.f7708e.dequeueOutputBuffer(bufferInfo, 12000L);
                            while (dequeueOutputBuffer >= 0) {
                                if (j2 < 0) {
                                    j2 = (System.currentTimeMillis() * 1000) - bufferInfo.presentationTimeUs;
                                }
                                long j4 = bufferInfo.presentationTimeUs + j2;
                                long currentTimeMillis = System.currentTimeMillis();
                                Long.signum(currentTimeMillis);
                                long j5 = (j4 - (currentTimeMillis * 1000)) / 1000;
                                if (j5 > 0) {
                                    Thread.sleep(j5);
                                }
                                ByteBuffer byteBuffer2 = outputBuffers[dequeueOutputBuffer];
                                byte[] bArr2 = new byte[bufferInfo.size];
                                byteBuffer2.get(bArr2);
                                if (NV21EncoderH264.this.f7709f != null) {
                                    NV21EncoderH264.this.f7709f.h264(bArr2);
                                }
                                NV21EncoderH264.this.f7708e.releaseOutputBuffer(dequeueOutputBuffer, false);
                                dequeueOutputBuffer = NV21EncoderH264.this.f7708e.dequeueOutputBuffer(bufferInfo, 12000L);
                            }
                            CLog.m21882i(NV21EncoderH264.f7704a, "h264end");
                        } catch (Throwable th) {
                            th.printStackTrace();
                        }
                        bArr = bArr;
                        j = -1;
                    } else {
                        CLog.m21882i(NV21EncoderH264.f7704a, "sleep");
                        try {
                            Thread.sleep(100L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        bArr = bArr;
                        j = -1;
                    }
                }
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public long m21290a(long j) {
        return ((j * 1000000) / this.f7707d) + 132;
    }

    /* renamed from: a */
    public void m21285a(byte[] bArr) {
        CLog.m21882i(f7704a, "setH264");
        if (this.f7715l) {
            this.f7714k = System.currentTimeMillis();
            this.f7715l = false;
        }
        if (this.f7716m < this.f7707d) {
            if (this.f7713j.size() >= this.f7712i) {
                this.f7713j.poll();
                CLog.m21882i(f7704a, "inQueue");
            }
            this.f7713j.add(bArr);
            this.f7716m++;
        } else if (System.currentTimeMillis() - this.f7714k > 1000 / this.f7707d) {
            this.f7714k = System.currentTimeMillis();
            this.f7716m = 0;
        }
    }

    /* renamed from: a */
    public void m21289a(EncoderListener encoderListener) {
        this.f7709f = encoderListener;
    }
}
