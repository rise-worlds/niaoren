package com.liulishuo.filedownloader.model;

import android.os.Parcel;
import android.os.Parcelable;
import p110z1.FileDownloadUtils;

/* loaded from: classes.dex */
public class FileDownloadTaskAtom implements Parcelable {
    public static final Parcelable.Creator<FileDownloadTaskAtom> CREATOR = new Parcelable.Creator<FileDownloadTaskAtom>() { // from class: com.liulishuo.filedownloader.model.FileDownloadTaskAtom.1
        /* renamed from: a */
        public FileDownloadTaskAtom createFromParcel(Parcel parcel) {
            return new FileDownloadTaskAtom(parcel);
        }

        /* renamed from: a */
        public FileDownloadTaskAtom[] newArray(int i) {
            return new FileDownloadTaskAtom[i];
        }
    };

    /* renamed from: a */
    private String f10385a;

    /* renamed from: b */
    private String f10386b;

    /* renamed from: c */
    private long f10387c;

    /* renamed from: d */
    private int f10388d;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public FileDownloadTaskAtom(String str, String str2, long j) {
        m19100a(str);
        m19098b(str2);
        m19101a(j);
    }

    /* renamed from: a */
    public int m19102a() {
        int i = this.f10388d;
        if (i != 0) {
            return i;
        }
        int b = FileDownloadUtils.m13173b(m19099b(), m19097c());
        this.f10388d = b;
        return b;
    }

    /* renamed from: b */
    public String m19099b() {
        return this.f10385a;
    }

    /* renamed from: a */
    public void m19100a(String str) {
        this.f10385a = str;
    }

    /* renamed from: c */
    public String m19097c() {
        return this.f10386b;
    }

    /* renamed from: b */
    public void m19098b(String str) {
        this.f10386b = str;
    }

    /* renamed from: d */
    public long m19096d() {
        return this.f10387c;
    }

    /* renamed from: a */
    public void m19101a(long j) {
        this.f10387c = j;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f10385a);
        parcel.writeString(this.f10386b);
        parcel.writeLong(this.f10387c);
    }

    protected FileDownloadTaskAtom(Parcel parcel) {
        this.f10385a = parcel.readString();
        this.f10386b = parcel.readString();
        this.f10387c = parcel.readLong();
    }
}
