package com.cyjh.ddy.media.record;

import android.annotation.TargetApi;
import android.media.AudioRecord;
import android.os.Process;

/* loaded from: classes.dex */
public class AudioMRecord implements IAudioMRecord {

    /* renamed from: a */
    private static final int f7398a = 2;

    /* renamed from: b */
    private int f7399b;

    /* renamed from: c */
    private int f7400c;

    /* renamed from: d */
    private int f7401d;

    /* renamed from: e */
    private int f7402e;

    /* renamed from: f */
    private AudioRecord f7403f = null;

    /* renamed from: g */
    private AudioRecordThread f7404g = null;

    /* renamed from: h */
    private IAudioRecordCallBack f7405h;

    /* loaded from: classes.dex */
    private class AudioRecordThread extends Thread {

        /* renamed from: b */
        private volatile boolean f7407b = true;

        public AudioRecordThread(String str) {
            super(str);
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            Process.setThreadPriority(-19);
            if (AudioMRecord.this.f7403f.getRecordingState() == 3) {
                while (this.f7407b) {
                    try {
                        byte[] bArr = new byte[AudioMRecord.this.f7402e];
                        int a = m21511a(bArr, 0, AudioMRecord.this.f7402e);
                        if (AudioMRecord.this.f7405h != null && a == AudioMRecord.this.f7402e && this.f7407b) {
                            AudioMRecord.this.f7405h.mo21491a(bArr, AudioMRecord.this.f7402e, AudioMRecord.this.f7399b, AudioMRecord.this.f7401d, AudioMRecord.this.f7400c);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        public void stopThread() {
            this.f7407b = false;
        }

        /* renamed from: a */
        private int m21511a(byte[] bArr, int i, int i2) {
            int i3 = i;
            int i4 = i2;
            while (i4 > 0) {
                if (!this.f7407b) {
                    return 0;
                }
                if (AudioMRecord.this.f7403f == null) {
                    return i2 - i4;
                }
                int read = AudioMRecord.this.f7403f.read(bArr, i3, i4);
                i4 -= read;
                i3 += read;
            }
            return i2;
        }
    }

    @Override // com.cyjh.ddy.media.record.IAudioMRecord
    /* renamed from: a */
    public void mo21494a(IAudioRecordCallBack cVar) {
        this.f7405h = cVar;
    }

    @Override // com.cyjh.ddy.media.record.IAudioMRecord
    @TargetApi(16)
    /* renamed from: a */
    public void mo21495a(int i, int i2, int i3, int i4) {
        if (this.f7403f == null) {
            this.f7399b = i;
            this.f7401d = i2;
            this.f7400c = i3;
            this.f7402e = i4;
            int i5 = i3 == 2 ? 12 : 16;
            int i6 = i2 == 8 ? 3 : 2;
            int minBufferSize = AudioRecord.getMinBufferSize(i, i5, i6);
            if (minBufferSize != -1 && minBufferSize != -2) {
                int max = Math.max(minBufferSize * 2, this.f7402e);
                try {
                    if (this.f7403f == null) {
                        this.f7403f = new AudioRecord(1, i, i5, i6, max);
                    }
                    AudioRecord audioRecord = this.f7403f;
                    if (audioRecord != null && audioRecord.getState() == 1) {
                    }
                } catch (IllegalArgumentException unused) {
                }
            }
        }
    }

    @Override // com.cyjh.ddy.media.record.IAudioMRecord
    /* renamed from: a */
    public boolean mo21496a() {
        AudioRecord audioRecord = this.f7403f;
        if (audioRecord == null) {
            return false;
        }
        try {
            audioRecord.startRecording();
            if (this.f7403f.getRecordingState() != 3) {
                return false;
            }
            this.f7404g = new AudioRecordThread("AudioRecordJavaThread");
            this.f7404g.start();
            return true;
        } catch (IllegalStateException unused) {
            return false;
        }
    }

    @Override // com.cyjh.ddy.media.record.IAudioMRecord
    /* renamed from: b */
    public boolean mo21493b() {
        AudioRecordThread audioRecordThread = this.f7404g;
        if (audioRecordThread != null) {
            audioRecordThread.stopThread();
            this.f7404g = null;
        }
        AudioRecord audioRecord = this.f7403f;
        if (audioRecord == null) {
            return false;
        }
        if (audioRecord.getState() == 0) {
            return true;
        }
        this.f7403f.stop();
        return true;
    }

    @Override // com.cyjh.ddy.media.record.IAudioMRecord
    /* renamed from: c */
    public boolean mo21492c() {
        AudioRecordThread audioRecordThread = this.f7404g;
        if (audioRecordThread != null) {
            audioRecordThread.stopThread();
            this.f7404g = null;
        }
        AudioRecord audioRecord = this.f7403f;
        if (audioRecord == null) {
            return false;
        }
        if (audioRecord.getState() != 0) {
            this.f7403f.stop();
            this.f7403f.release();
        }
        this.f7403f = null;
        return true;
    }
}
