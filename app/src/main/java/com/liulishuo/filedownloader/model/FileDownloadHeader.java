package com.liulishuo.filedownloader.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes.dex */
public class FileDownloadHeader implements Parcelable {
    public static final Parcelable.Creator<FileDownloadHeader> CREATOR = new Parcelable.Creator<FileDownloadHeader>() { // from class: com.liulishuo.filedownloader.model.FileDownloadHeader.1
        /* renamed from: a */
        public FileDownloadHeader createFromParcel(Parcel parcel) {
            return new FileDownloadHeader(parcel);
        }

        /* renamed from: a */
        public FileDownloadHeader[] newArray(int i) {
            return new FileDownloadHeader[i];
        }
    };

    /* renamed from: a */
    private HashMap<String, List<String>> f10359a;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    /* renamed from: a */
    public void m19139a(String str, String str2) {
        if (str == null) {
            throw new NullPointerException("name == null");
        } else if (str.isEmpty()) {
            throw new IllegalArgumentException("name is empty");
        } else if (str2 != null) {
            if (this.f10359a == null) {
                this.f10359a = new HashMap<>();
            }
            List<String> list = this.f10359a.get(str);
            if (list == null) {
                list = new ArrayList<>();
                this.f10359a.put(str, list);
            }
            if (!list.contains(str2)) {
                list.add(str2);
            }
        } else {
            throw new NullPointerException("value == null");
        }
    }

    /* renamed from: a */
    public void m19140a(String str) {
        String[] split = str.split(":");
        m19139a(split[0].trim(), split[1].trim());
    }

    /* renamed from: b */
    public void m19138b(String str) {
        HashMap<String, List<String>> hashMap = this.f10359a;
        if (hashMap != null) {
            hashMap.remove(str);
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeMap(this.f10359a);
    }

    /* renamed from: a */
    public HashMap<String, List<String>> m19141a() {
        return this.f10359a;
    }

    public FileDownloadHeader() {
    }

    protected FileDownloadHeader(Parcel parcel) {
        this.f10359a = parcel.readHashMap(String.class.getClassLoader());
    }

    public String toString() {
        return this.f10359a.toString();
    }
}
