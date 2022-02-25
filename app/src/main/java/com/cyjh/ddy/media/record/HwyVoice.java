package com.cyjh.ddy.media.record;

import com.blankj.utilcode.util.EncodeUtils;
import com.cyjh.ddy.media.oksocket.ControlSocket;
import com.cyjh.ddy.media.oksocket.IControlSocketListener;
import com.cyjh.ddy.media.oksocket.SocketHelper;
import com.cyjh.ddy.media.record.AudioRecordModel;
import p110z1.MyToast;

/* renamed from: com.cyjh.ddy.media.record.a */
/* loaded from: classes.dex */
public class HwyVoice {

    /* renamed from: a */
    private AudioRecordModel f7416a;

    /* renamed from: b */
    private ControlSocket f7417b;

    /* renamed from: c */
    private String f7418c;

    /* renamed from: a */
    public void m21501a(boolean z) {
        if (z) {
            m21497c();
        }
    }

    /* renamed from: c */
    private void m21497c() {
        if (this.f7416a == null) {
            this.f7416a = new AudioRecordModel();
            this.f7416a.m21509a(MyToast.f17148g, 16, 1, 320);
            this.f7416a.m21510a();
            this.f7416a.m21508a(new AudioRecordModel.RecordDataCallBack() { // from class: com.cyjh.ddy.media.record.HwyVoice$1
                @Override // com.cyjh.ddy.media.record.AudioRecordModel.RecordDataCallBack
                public void onRecordData(byte[] bArr) {
                    ControlSocket controlSocket;
                    String str;
                    ControlSocket controlSocket2;
                    String b = EncodeUtils.m22387b(bArr);
                    controlSocket = HwyVoice.this.f7417b;
                    if (controlSocket != null) {
                        str = HwyVoice.this.f7418c;
                        String a = SocketHelper.m21520a("voiceData", b, 5, str);
                        controlSocket2 = HwyVoice.this.f7417b;
                        controlSocket2.m21534a(a);
                    }
                }
            });
        }
    }

    /* renamed from: a */
    public void m21504a() {
        AudioRecordModel audioRecordModel = this.f7416a;
        if (audioRecordModel != null) {
            audioRecordModel.m21507b();
        }
    }

    /* renamed from: b */
    public void m21500b() {
        AudioRecordModel audioRecordModel = this.f7416a;
        if (audioRecordModel != null) {
            audioRecordModel.m21506c();
        }
    }

    /* renamed from: b */
    public void m21498b(boolean z) {
        AudioRecordModel audioRecordModel;
        if (z && (audioRecordModel = this.f7416a) != null) {
            audioRecordModel.m21505d();
        }
        ControlSocket controlSocket = this.f7417b;
        if (controlSocket != null && controlSocket.m21532b()) {
            this.f7417b.m21538a();
            this.f7417b = null;
        }
    }

    /* renamed from: a */
    public void m21502a(String str, String str2) {
        this.f7418c = str2;
        this.f7417b = new ControlSocket(str, str2, new IControlSocketListener.IOnMessageListener() { // from class: com.cyjh.ddy.media.record.HwyVoice$2
            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onConnected(ControlSocket controlSocket) {
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onFailure(ControlSocket controlSocket, String str3) {
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onSended(ControlSocket controlSocket) {
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onClosed(String str3) {
                HwyVoice.this.m21498b(false);
            }
        });
    }
}
