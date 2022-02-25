package com.liulishuo.filedownloader.model;

import android.content.ContentValues;
import java.util.List;
import p110z1.FileDownloadUtils;

/* renamed from: com.liulishuo.filedownloader.model.a */
/* loaded from: classes.dex */
public class ConnectionModel {

    /* renamed from: a */
    public static final String f10389a = "id";

    /* renamed from: b */
    public static final String f10390b = "connectionIndex";

    /* renamed from: c */
    public static final String f10391c = "startOffset";

    /* renamed from: d */
    public static final String f10392d = "currentOffset";

    /* renamed from: e */
    public static final String f10393e = "endOffset";

    /* renamed from: f */
    private int f10394f;

    /* renamed from: g */
    private int f10395g;

    /* renamed from: h */
    private long f10396h;

    /* renamed from: i */
    private long f10397i;

    /* renamed from: j */
    private long f10398j;

    /* renamed from: a */
    public int m19093a() {
        return this.f10394f;
    }

    /* renamed from: a */
    public void m19092a(int i) {
        this.f10394f = i;
    }

    /* renamed from: b */
    public int m19089b() {
        return this.f10395g;
    }

    /* renamed from: b */
    public void m19088b(int i) {
        this.f10395g = i;
    }

    /* renamed from: c */
    public long m19086c() {
        return this.f10396h;
    }

    /* renamed from: a */
    public void m19091a(long j) {
        this.f10396h = j;
    }

    /* renamed from: d */
    public long m19084d() {
        return this.f10397i;
    }

    /* renamed from: b */
    public void m19087b(long j) {
        this.f10397i = j;
    }

    /* renamed from: e */
    public long m19083e() {
        return this.f10398j;
    }

    /* renamed from: c */
    public void m19085c(long j) {
        this.f10398j = j;
    }

    /* renamed from: f */
    public ContentValues m19082f() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(f10389a, Integer.valueOf(this.f10394f));
        contentValues.put(f10390b, Integer.valueOf(this.f10395g));
        contentValues.put(f10391c, Long.valueOf(this.f10396h));
        contentValues.put(f10392d, Long.valueOf(this.f10397i));
        contentValues.put(f10393e, Long.valueOf(this.f10398j));
        return contentValues;
    }

    /* renamed from: a */
    public static long m19090a(List<ConnectionModel> list) {
        long j = 0;
        for (ConnectionModel aVar : list) {
            j += aVar.m19084d() - aVar.m19086c();
        }
        return j;
    }

    public String toString() {
        return FileDownloadUtils.m13182a("id[%d] index[%d] range[%d, %d) current offset(%d)", Integer.valueOf(this.f10394f), Integer.valueOf(this.f10395g), Long.valueOf(this.f10396h), Long.valueOf(this.f10398j), Long.valueOf(this.f10397i));
    }
}
