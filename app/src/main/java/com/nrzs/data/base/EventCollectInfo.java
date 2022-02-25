package com.nrzs.data.base;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* loaded from: classes2.dex */
public class EventCollectInfo implements Parcelable {
    public static final Parcelable.Creator<EventCollectInfo> CREATOR = new Parcelable.Creator<EventCollectInfo>() { // from class: com.nrzs.data.base.EventCollectInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public EventCollectInfo createFromParcel(Parcel parcel) {
            return new EventCollectInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public EventCollectInfo[] newArray(int i) {
            return new EventCollectInfo[i];
        }
    };
    public List<EventCollectBodyInfo> MsgBody;
    public EventCollectHeadInfo MsgHead;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.MsgHead, i);
        parcel.writeTypedList(this.MsgBody);
    }

    public EventCollectInfo() {
    }

    protected EventCollectInfo(Parcel parcel) {
        this.MsgHead = (EventCollectHeadInfo) parcel.readParcelable(EventCollectHeadInfo.class.getClassLoader());
        this.MsgBody = parcel.createTypedArrayList(EventCollectBodyInfo.CREATOR);
    }
}
