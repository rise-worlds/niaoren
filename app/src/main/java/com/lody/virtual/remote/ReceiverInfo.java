package com.lody.virtual.remote;

import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* loaded from: classes.dex */
public class ReceiverInfo implements Parcelable {
    public static final Parcelable.Creator<ReceiverInfo> CREATOR = new Parcelable.Creator<ReceiverInfo>() { // from class: com.lody.virtual.remote.ReceiverInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ReceiverInfo createFromParcel(Parcel parcel) {
            return new ReceiverInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ReceiverInfo[] newArray(int i) {
            return new ReceiverInfo[i];
        }
    };
    public List<IntentFilter> filters;
    public ActivityInfo info;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ReceiverInfo(ActivityInfo activityInfo, List<IntentFilter> list) {
        this.info = activityInfo;
        this.filters = list;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.info, i);
        parcel.writeTypedList(this.filters);
    }

    protected ReceiverInfo(Parcel parcel) {
        this.info = (ActivityInfo) parcel.readParcelable(ActivityInfo.class.getClassLoader());
        this.filters = parcel.createTypedArrayList(IntentFilter.CREATOR);
    }
}
