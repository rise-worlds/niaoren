package com.nrzs.data.base;

import android.os.Parcel;
import android.os.Parcelable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import p110z1.apa;

/* loaded from: classes2.dex */
public class BaseCollectInfo implements Parcelable {
    public static final Parcelable.Creator<BaseCollectInfo> CREATOR = new Parcelable.Creator<BaseCollectInfo>() { // from class: com.nrzs.data.base.BaseCollectInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BaseCollectInfo createFromParcel(Parcel parcel) {
            return new BaseCollectInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BaseCollectInfo[] newArray(int i) {
            return new BaseCollectInfo[i];
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
    }

    public String toPrames() throws Exception {
        List<EventCollectCommandParamEntity> attributesInfo = getAttributesInfo();
        return attributesInfo == null ? "" : apa.m11879a(attributesInfo);
    }

    public List<EventCollectCommandParamEntity> getAttributesInfo() throws Exception {
        return getRecursion(getClass(), new ArrayList());
    }

    private List<EventCollectCommandParamEntity> getRecursion(Class cls, List<EventCollectCommandParamEntity> list) throws Exception {
        Field[] declaredFields;
        for (Field field : cls.getDeclaredFields()) {
            field.setAccessible(true);
            if (!Modifier.isStatic(field.getModifiers())) {
                EventCollectCommandParamEntity eventCollectCommandParamEntity = new EventCollectCommandParamEntity();
                eventCollectCommandParamEntity.Key = field.getName().toLowerCase();
                eventCollectCommandParamEntity.Value = String.valueOf(field.get(this));
                list.add(eventCollectCommandParamEntity);
            }
        }
        return (cls.getSuperclass() == null || cls.getSuperclass() == Object.class) ? list : getRecursion(cls.getSuperclass(), list);
    }

    public BaseCollectInfo() {
    }

    protected BaseCollectInfo(Parcel parcel) {
    }
}
