package com.liulishuo.filedownloader.model;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;
import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import p110z1.FileDownloadUtils;

/* loaded from: classes.dex */
public class FileDownloadModel implements Parcelable {
    public static final Parcelable.Creator<FileDownloadModel> CREATOR = new Parcelable.Creator<FileDownloadModel>() { // from class: com.liulishuo.filedownloader.model.FileDownloadModel.1
        /* renamed from: a */
        public FileDownloadModel createFromParcel(Parcel parcel) {
            return new FileDownloadModel(parcel);
        }

        /* renamed from: a */
        public FileDownloadModel[] newArray(int i) {
            return new FileDownloadModel[i];
        }
    };

    /* renamed from: a */
    public static final int f10360a = -1;

    /* renamed from: b */
    public static final int f10361b = 100;

    /* renamed from: c */
    public static final String f10362c = "_id";

    /* renamed from: d */
    public static final String f10363d = "url";

    /* renamed from: e */
    public static final String f10364e = "path";

    /* renamed from: f */
    public static final String f10365f = "pathAsDirectory";

    /* renamed from: g */
    public static final String f10366g = "filename";

    /* renamed from: h */
    public static final String f10367h = "status";

    /* renamed from: i */
    public static final String f10368i = "sofar";

    /* renamed from: j */
    public static final String f10369j = "total";

    /* renamed from: k */
    public static final String f10370k = "errMsg";

    /* renamed from: l */
    public static final String f10371l = "etag";

    /* renamed from: m */
    public static final String f10372m = "connectionCount";

    /* renamed from: n */
    private int f10373n;

    /* renamed from: o */
    private String f10374o;

    /* renamed from: p */
    private String f10375p;

    /* renamed from: q */
    private boolean f10376q;

    /* renamed from: r */
    private String f10377r;

    /* renamed from: s */
    private final AtomicInteger f10378s;

    /* renamed from: t */
    private final AtomicLong f10379t;

    /* renamed from: u */
    private long f10380u;

    /* renamed from: v */
    private String f10381v;

    /* renamed from: w */
    private String f10382w;

    /* renamed from: x */
    private int f10383x;

    /* renamed from: y */
    private boolean f10384y;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    /* renamed from: a */
    public void m19133a(int i) {
        this.f10373n = i;
    }

    /* renamed from: a */
    public void m19131a(String str) {
        this.f10374o = str;
    }

    /* renamed from: a */
    public void m19130a(String str, boolean z) {
        this.f10375p = str;
        this.f10376q = z;
    }

    /* renamed from: a */
    public void m19134a(byte b) {
        this.f10378s.set(b);
    }

    /* renamed from: a */
    public void m19132a(long j) {
        this.f10379t.set(j);
    }

    /* renamed from: b */
    public void m19127b(long j) {
        this.f10379t.addAndGet(j);
    }

    /* renamed from: c */
    public void m19124c(long j) {
        this.f10384y = j > 2147483647L;
        this.f10380u = j;
    }

    /* renamed from: a */
    public int m19135a() {
        return this.f10373n;
    }

    /* renamed from: b */
    public String m19129b() {
        return this.f10374o;
    }

    /* renamed from: c */
    public String m19125c() {
        return this.f10375p;
    }

    /* renamed from: d */
    public String m19122d() {
        return FileDownloadUtils.m13183a(m19125c(), m19113l(), m19112m());
    }

    /* renamed from: e */
    public String m19120e() {
        if (m19122d() == null) {
            return null;
        }
        return FileDownloadUtils.m13160e(m19122d());
    }

    /* renamed from: f */
    public byte m19119f() {
        return (byte) this.f10378s.get();
    }

    /* renamed from: g */
    public long m19118g() {
        return this.f10379t.get();
    }

    /* renamed from: h */
    public long m19117h() {
        return this.f10380u;
    }

    /* renamed from: i */
    public boolean m19116i() {
        return this.f10380u == -1;
    }

    /* renamed from: j */
    public String m19115j() {
        return this.f10382w;
    }

    /* renamed from: b */
    public void m19126b(String str) {
        this.f10382w = str;
    }

    /* renamed from: k */
    public String m19114k() {
        return this.f10381v;
    }

    /* renamed from: c */
    public void m19123c(String str) {
        this.f10381v = str;
    }

    /* renamed from: d */
    public void m19121d(String str) {
        this.f10377r = str;
    }

    /* renamed from: l */
    public boolean m19113l() {
        return this.f10376q;
    }

    /* renamed from: m */
    public String m19112m() {
        return this.f10377r;
    }

    /* renamed from: b */
    public void m19128b(int i) {
        this.f10383x = i;
    }

    /* renamed from: n */
    public int m19111n() {
        return this.f10383x;
    }

    /* renamed from: o */
    public void m19110o() {
        this.f10383x = 1;
    }

    /* renamed from: p */
    public ContentValues m19109p() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("_id", Integer.valueOf(m19135a()));
        contentValues.put("url", m19129b());
        contentValues.put("path", m19125c());
        contentValues.put("status", Byte.valueOf(m19119f()));
        contentValues.put(f10368i, Long.valueOf(m19118g()));
        contentValues.put(f10369j, Long.valueOf(m19117h()));
        contentValues.put(f10370k, m19114k());
        contentValues.put("etag", m19115j());
        contentValues.put(f10372m, Integer.valueOf(m19111n()));
        contentValues.put(f10365f, Boolean.valueOf(m19113l()));
        if (m19113l() && m19112m() != null) {
            contentValues.put("filename", m19112m());
        }
        return contentValues;
    }

    /* renamed from: q */
    public boolean m19108q() {
        return this.f10384y;
    }

    /* renamed from: r */
    public void m19107r() {
        m19106s();
        m19105t();
    }

    /* renamed from: s */
    public void m19106s() {
        String e = m19120e();
        if (e != null) {
            File file = new File(e);
            if (file.exists()) {
                file.delete();
            }
        }
    }

    /* renamed from: t */
    public void m19105t() {
        String d = m19122d();
        if (d != null) {
            File file = new File(d);
            if (file.exists()) {
                file.delete();
            }
        }
    }

    public String toString() {
        return FileDownloadUtils.m13182a("id[%d], url[%s], path[%s], status[%d], sofar[%s], total[%d], etag[%s], %s", Integer.valueOf(this.f10373n), this.f10374o, this.f10375p, Integer.valueOf(this.f10378s.get()), this.f10379t, Long.valueOf(this.f10380u), this.f10382w, super.toString());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f10373n);
        parcel.writeString(this.f10374o);
        parcel.writeString(this.f10375p);
        parcel.writeByte(this.f10376q ? (byte) 1 : (byte) 0);
        parcel.writeString(this.f10377r);
        parcel.writeByte((byte) this.f10378s.get());
        parcel.writeLong(this.f10379t.get());
        parcel.writeLong(this.f10380u);
        parcel.writeString(this.f10381v);
        parcel.writeString(this.f10382w);
        parcel.writeInt(this.f10383x);
        parcel.writeByte(this.f10384y ? (byte) 1 : (byte) 0);
    }

    public FileDownloadModel() {
        this.f10379t = new AtomicLong();
        this.f10378s = new AtomicInteger();
    }

    protected FileDownloadModel(Parcel parcel) {
        this.f10373n = parcel.readInt();
        this.f10374o = parcel.readString();
        this.f10375p = parcel.readString();
        boolean z = true;
        this.f10376q = parcel.readByte() != 0;
        this.f10377r = parcel.readString();
        this.f10378s = new AtomicInteger(parcel.readByte());
        this.f10379t = new AtomicLong(parcel.readLong());
        this.f10380u = parcel.readLong();
        this.f10381v = parcel.readString();
        this.f10382w = parcel.readString();
        this.f10383x = parcel.readInt();
        this.f10384y = parcel.readByte() == 0 ? false : z;
    }
}
