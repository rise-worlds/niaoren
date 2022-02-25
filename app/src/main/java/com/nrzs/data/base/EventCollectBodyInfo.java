package com.nrzs.data.base;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class EventCollectBodyInfo implements Parcelable {
    public static final Parcelable.Creator<EventCollectBodyInfo> CREATOR = new Parcelable.Creator<EventCollectBodyInfo>() { // from class: com.nrzs.data.base.EventCollectBodyInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public EventCollectBodyInfo createFromParcel(Parcel parcel) {
            return new EventCollectBodyInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public EventCollectBodyInfo[] newArray(int i) {
            return new EventCollectBodyInfo[i];
        }
    };
    public List<EventCollectCommandListInfo> CommandList;
    public String EventCode;
    public String PositionCode;
    public String SourceKey;
    public String SourceName;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.SourceName);
        parcel.writeString(this.SourceKey);
        parcel.writeString(this.PositionCode);
        parcel.writeString(this.EventCode);
        parcel.writeList(this.CommandList);
    }

    public EventCollectBodyInfo() {
    }

    protected EventCollectBodyInfo(Parcel parcel) {
        this.SourceName = parcel.readString();
        this.SourceKey = parcel.readString();
        this.PositionCode = parcel.readString();
        this.EventCode = parcel.readString();
        this.CommandList = new ArrayList();
        parcel.readList(this.CommandList, EventCollectCommandListInfo.class.getClassLoader());
    }

    public String toString() {
        return "EventCollectBodyInfo{SourceName='" + this.SourceName + "', SourceKey='" + this.SourceKey + "', PositionCode='" + this.PositionCode + "', EventCode='" + this.EventCode + "', CommandList=" + this.CommandList + '}';
    }
}
