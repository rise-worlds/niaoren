package com.nrzs.data.base;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* loaded from: classes2.dex */
public class EventCollectCommandListInfo implements Parcelable {
    public static final Parcelable.Creator<EventCollectCommandListInfo> CREATOR = new Parcelable.Creator<EventCollectCommandListInfo>() { // from class: com.nrzs.data.base.EventCollectCommandListInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public EventCollectCommandListInfo createFromParcel(Parcel parcel) {
            return new EventCollectCommandListInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public EventCollectCommandListInfo[] newArray(int i) {
            return new EventCollectCommandListInfo[i];
        }
    };
    public List<EventCollectCommandParamEntity> CommandParam;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.CommandParam);
    }

    public EventCollectCommandListInfo() {
    }

    protected EventCollectCommandListInfo(Parcel parcel) {
        this.CommandParam = parcel.createTypedArrayList(EventCollectCommandParamEntity.CREATOR);
    }
}
