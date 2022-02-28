package com.lody.virtual.server.pm.legacy;

import android.os.Parcel;
import android.util.SparseArray;
import com.lody.virtual.server.pm.PackageUserState;

/* renamed from: com.lody.virtual.server.pm.legacy.PackageSettingV1 */
/* loaded from: classes.dex */
public class PackageSettingV1 {
    public int appId;
    public int flag;
    public boolean notCopyApk;
    public String packageName;
    public SparseArray<PackageUserState> userState;

    public void readFromParcel(Parcel parcel, int i) {
        this.packageName = parcel.readString();
        parcel.readString();
        parcel.readString();
        this.notCopyApk = parcel.readByte() != 0;
        this.appId = parcel.readInt();
        this.userState = parcel.readSparseArray(PackageUserState.class.getClassLoader());
        parcel.readByte();
        if (i > 3) {
            this.flag = parcel.readInt();
        }
    }
}
