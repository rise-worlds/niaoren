package com.cyjh.ddy.media.media.qemu;

import android.content.Intent;
import android.media.AudioTrack;
import android.media.MediaCodec;
import android.media.MediaFormat;
import android.os.Build;
import com.cyjh.ddy.base.utils.CLog;
import com.cyjh.ddy.media.WsApplication;
import java.nio.ByteBuffer;
import org.apache.tools.ant.taskdefs.email.EmailTask;
import p110z1.MyToast;

/* renamed from: com.cyjh.ddy.media.media.qemu.c */
/* loaded from: classes.dex */
public class LiveAudioThread extends LiveMediaCodecThread {

    /* renamed from: b */
    private static final int f7348b = 10;

    /* renamed from: c */
    private static final String f7349c = "audio/mp4a-latm";

    /* renamed from: d */
    private static final int f7350d = 44100;

    /* renamed from: e */
    private AudioTrack f7351e;

    @Override // com.cyjh.ddy.media.media.qemu.LiveMediaCodecThread
    /* renamed from: a */
    protected String mo21545a(boolean z) {
        return f7349c;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.cyjh.ddy.media.media.qemu.LiveMediaCodecThread
    /* renamed from: c */
    public boolean mo21544c() {
        return false;
    }

    public LiveAudioThread() {
        super(10, 18, "LiveAudioThread");
    }

    /* renamed from: a */
    private MediaFormat m21585a(int i, int i2, int i3) {
        MediaFormat mediaFormat = new MediaFormat();
        mediaFormat.setString(EmailTask.MIME, f7349c);
        mediaFormat.setInteger("sample-rate", i2);
        mediaFormat.setInteger("channel-count", i3);
        mediaFormat.setInteger("is-adts", 1);
        int i4 = -1;
        for (int i5 = 0; i5 < 12; i5++) {
            if (new int[]{96000, 88200, 64000, 48000, f7350d, 32000, 24000, 22050, 16000, 12000, 11025, MyToast.f17148g}[i5] == i2) {
                i4 = i5;
            }
        }
        if (i4 == -1) {
            return null;
        }
        ByteBuffer allocate = ByteBuffer.allocate(2);
        allocate.put((byte) ((i << 3) | (i4 >> 1)));
        allocate.position(1);
        allocate.put((byte) (((byte) ((i4 << 7) & 128)) | (i3 << 3)));
        allocate.flip();
        mediaFormat.setByteBuffer("csd-0", allocate);
        return mediaFormat;
    }

    /* renamed from: a */
    private static void m21584a(AudioTrack audioTrack, float f) {
        if (Build.VERSION.SDK_INT >= 21) {
            audioTrack.setVolume(f);
        } else {
            audioTrack.setStereoVolume(f, f);
        }
    }

    @Override // com.cyjh.ddy.media.media.qemu.LiveMediaCodecThread
    /* renamed from: a */
    protected MediaFormat mo21546a(String str) {
        return m21585a(2, f7350d, 2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.cyjh.ddy.media.media.qemu.LiveMediaCodecThread
    /* renamed from: a */
    public void mo21580a(MediaFormat mediaFormat) {
        this.f7351e.setPlaybackRate(mediaFormat.getInteger("sample-rate"));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.cyjh.ddy.media.media.qemu.LiveMediaCodecThread
    /* renamed from: a */
    public boolean mo21581a(MediaCodec.BufferInfo bufferInfo, ByteBuffer byteBuffer) {
        byte[] bArr = new byte[bufferInfo.size];
        byteBuffer.get(bArr);
        byteBuffer.clear();
        this.f7351e.write(bArr, bufferInfo.offset, bufferInfo.offset + bufferInfo.size);
        return true;
    }

    @Override // com.cyjh.ddy.media.media.qemu.LiveMediaCodecThread
    /* renamed from: a */
    protected void mo21583a() {
        int i;
        int minBufferSize = AudioTrack.getMinBufferSize(f7350d, 12, 2) * 4;
        if (minBufferSize <= -1) {
            int minBufferSize2 = AudioTrack.getMinBufferSize(AudioTrack.getNativeOutputSampleRate(3), 12, 2) * 4;
            if (minBufferSize2 > -1) {
                i = minBufferSize2;
            } else {
                return;
            }
        } else {
            i = minBufferSize;
        }
        try {
            this.f7351e = new AudioTrack(3, f7350d, 12, 2, i, 1);
            m21584a(this.f7351e, 1.0f);
            int state = this.f7351e.getState();
            CLog.m21882i("LiveAudioThread", "onStart: state " + state);
            if (state == 0) {
                CLog.m21883e("LiveAudioThread", "onStart: AudioTrack.STATE_UNINITIALIZED");
                Intent intent = new Intent("broadcast_action_live_audio_thread");
                intent.putExtra("broadcast_action_push_action_msg", "AudioTrack.STATE_UNINITIALIZED#onStart");
                if (WsApplication.f7214a != null) {
                    WsApplication.f7214a.sendBroadcast(intent);
                    return;
                }
                return;
            }
            this.f7351e.play();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @Override // com.cyjh.ddy.media.media.qemu.LiveMediaCodecThread
    /* renamed from: b */
    protected void mo21572b() {
        CLog.m21882i(LiveAudioThread.class.getSimpleName(), "LiveAudioThread--onStop");
        int state = this.f7351e.getState();
        CLog.m21883e("LiveAudioThread", "onStop: state " + state);
        if (state == 0) {
            CLog.m21883e("LiveAudioThread", "onStop: AudioTrack.STATE_UNINITIALIZED");
            Intent intent = new Intent("broadcast_action_live_audio_thread");
            intent.putExtra("broadcast_action_push_action_msg", "AudioTrack.STATE_UNINITIALIZED#onStop");
            if (WsApplication.f7214a != null) {
                WsApplication.f7214a.sendBroadcast(intent);
            }
        } else {
            this.f7351e.stop();
            this.f7351e.release();
        }
        this.f7351e = null;
    }
}
