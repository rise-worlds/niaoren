package com.cyjh.ddy.media.record;

/* loaded from: classes.dex */
public class AudioRecordModel implements IAudioRecordCallBack {

    /* renamed from: a */
    private IAudioMRecord f7408a;

    /* renamed from: b */
    private int f7409b;

    /* renamed from: c */
    private int f7410c;

    /* renamed from: d */
    private int f7411d;

    /* renamed from: e */
    private int f7412e;

    /* renamed from: f */
    private RecordDataCallBack f7413f;

    /* loaded from: classes.dex */
    public interface RecordDataCallBack {
        void onRecordData(byte[] bArr);
    }

    /* renamed from: a */
    public void m21509a(int i, int i2, int i3, int i4) {
        this.f7409b = i;
        this.f7410c = i3;
        this.f7411d = i2;
        this.f7412e = i4;
    }

    /* renamed from: a */
    public void m21510a() {
        this.f7408a = new AudioMRecord();
        this.f7408a.mo21494a(this);
        this.f7408a.mo21495a(this.f7409b, this.f7411d, this.f7410c, this.f7412e);
    }

    /* renamed from: b */
    public void m21507b() {
        IAudioMRecord bVar = this.f7408a;
        if (bVar != null) {
            bVar.mo21496a();
        }
    }

    /* renamed from: c */
    public void m21506c() {
        IAudioMRecord bVar = this.f7408a;
        if (bVar != null) {
            bVar.mo21493b();
        }
    }

    @Override // com.cyjh.ddy.media.record.IAudioRecordCallBack
    /* renamed from: a */
    public void mo21491a(byte[] bArr, int i, int i2, int i3, int i4) {
        RecordDataCallBack recordDataCallBack = this.f7413f;
        if (recordDataCallBack != null) {
            recordDataCallBack.onRecordData(bArr);
        }
    }

    /* renamed from: d */
    public void m21505d() {
        IAudioMRecord bVar = this.f7408a;
        if (bVar != null) {
            bVar.mo21492c();
        }
    }

    /* renamed from: a */
    public void m21508a(RecordDataCallBack recordDataCallBack) {
        this.f7413f = recordDataCallBack;
    }
}
