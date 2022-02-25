package com.lody.virtual.remote;

import android.annotation.TargetApi;
import android.app.job.JobWorkItem;
import android.os.Parcel;
import android.os.Parcelable;

@TargetApi(26)
/* loaded from: classes.dex */
public class VJobWorkItem implements Parcelable {
    public static final Parcelable.Creator<VJobWorkItem> CREATOR = new Parcelable.Creator<VJobWorkItem>() { // from class: com.lody.virtual.remote.VJobWorkItem.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VJobWorkItem createFromParcel(Parcel parcel) {
            return new VJobWorkItem(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VJobWorkItem[] newArray(int i) {
            return new VJobWorkItem[i];
        }
    };
    private JobWorkItem item;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public JobWorkItem get() {
        return this.item;
    }

    public void set(JobWorkItem jobWorkItem) {
        this.item = jobWorkItem;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.item, i);
    }

    public VJobWorkItem() {
    }

    public VJobWorkItem(JobWorkItem jobWorkItem) {
        this.item = jobWorkItem;
    }

    protected VJobWorkItem(Parcel parcel) {
        this.item = (JobWorkItem) parcel.readParcelable(JobWorkItem.class.getClassLoader());
    }
}
