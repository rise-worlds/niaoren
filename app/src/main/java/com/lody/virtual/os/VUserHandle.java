package com.lody.virtual.os;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;
import com.lody.virtual.client.VClient;
import com.lody.virtual.client.core.VirtualCore;
import java.io.PrintWriter;
import p110z1.C4963cj;

/* renamed from: com.lody.virtual.os.VUserHandle */
/* loaded from: classes.dex */
public final class VUserHandle implements Parcelable {
    public static final int FIRST_ISOLATED_UID = 99000;
    public static final int FIRST_SHARED_APPLICATION_GID = 50000;
    public static final int LAST_ISOLATED_UID = 99999;
    public static final int LAST_SHARED_APPLICATION_GID = 59999;
    public static final boolean MU_ENABLED = true;
    public static final int PER_USER_RANGE = 100000;
    public static final int USER_ALL = -1;
    public static final int USER_CURRENT = -2;
    public static final int USER_CURRENT_OR_SELF = -3;
    public static final int USER_NULL = -10000;
    public static final int USER_OWNER = 0;
    final int mHandle;
    public static final VUserHandle ALL = new VUserHandle(-1);
    public static final VUserHandle CURRENT = new VUserHandle(-2);
    public static final VUserHandle CURRENT_OR_SELF = new VUserHandle(-3);
    public static final VUserHandle OWNER = new VUserHandle(0);
    public static final Parcelable.Creator<VUserHandle> CREATOR = new Parcelable.Creator<VUserHandle>() { // from class: com.lody.virtual.os.VUserHandle.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VUserHandle createFromParcel(Parcel parcel) {
            return new VUserHandle(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VUserHandle[] newArray(int i) {
            return new VUserHandle[i];
        }
    };
    private static final SparseArray<VUserHandle> userHandles = new SparseArray<>();

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public VUserHandle(int i) {
        this.mHandle = i;
    }

    public VUserHandle(Parcel parcel) {
        this.mHandle = parcel.readInt();
    }

    public static boolean isSameUser(int i, int i2) {
        return getUserId(i) == getUserId(i2);
    }

    public static boolean accept(int i) {
        return i == -1 || i == myUserId();
    }

    public static final boolean isSameApp(int i, int i2) {
        return getAppId(i) == getAppId(i2);
    }

    public static final boolean isIsolated(int i) {
        int appId;
        return i > 0 && (appId = getAppId(i)) >= 99000 && appId <= 99999;
    }

    public static boolean isApp(int i) {
        int appId;
        return i > 0 && (appId = getAppId(i)) >= 10000 && appId <= 19999;
    }

    public static int getUserId(int i) {
        if (i < 0) {
            return 0;
        }
        return i / 100000;
    }

    public static int getCallingUserId() {
        return getUserId(VClient.get().getCallingVUid());
    }

    public static VUserHandle getCallingUserHandle() {
        int userId = getUserId(VBinder.getCallingUid());
        VUserHandle vUserHandle = userHandles.get(userId);
        if (vUserHandle != null) {
            return vUserHandle;
        }
        VUserHandle vUserHandle2 = new VUserHandle(userId);
        userHandles.put(userId, vUserHandle2);
        return vUserHandle2;
    }

    public static int getUid(int i, int i2) {
        return (i * 100000) + (i2 % 100000);
    }

    public static int getAppId(int i) {
        return i % 100000;
    }

    public static int myAppId() {
        return getAppId(VClient.get().getVUid());
    }

    public static int getAppIdFromSharedAppGid(int i) {
        int appId = getAppId(i);
        if (appId >= 50000 && appId <= 59999) {
            return (appId + 10000) - FIRST_SHARED_APPLICATION_GID;
        }
        throw new IllegalArgumentException(Integer.toString(i) + " is not a shared app gid");
    }

    public static void formatUid(StringBuilder sb, int i) {
        if (i < 10000) {
            sb.append(i);
            return;
        }
        sb.append('u');
        sb.append(getUserId(i));
        int appId = getAppId(i);
        if (appId >= 99000 && appId <= 99999) {
            sb.append('i');
            sb.append(appId - FIRST_ISOLATED_UID);
        } else if (appId >= 10000) {
            sb.append('a');
            sb.append(appId - 10000);
        } else {
            sb.append('s');
            sb.append(appId);
        }
    }

    public static String formatUid(int i) {
        StringBuilder sb = new StringBuilder();
        formatUid(sb, i);
        return sb.toString();
    }

    public static void formatUid(PrintWriter printWriter, int i) {
        if (i < 10000) {
            printWriter.print(i);
            return;
        }
        printWriter.print('u');
        printWriter.print(getUserId(i));
        int appId = getAppId(i);
        if (appId >= 99000 && appId <= 99999) {
            printWriter.print('i');
            printWriter.print(appId - FIRST_ISOLATED_UID);
        } else if (appId >= 10000) {
            printWriter.print('a');
            printWriter.print(appId - 10000);
        } else {
            printWriter.print('s');
            printWriter.print(appId);
        }
    }

    public static int myUserId() {
        return getUserId(VClient.get().getVUid());
    }

    public static int realUserId() {
        if (Build.VERSION.SDK_INT < 17) {
            return 0;
        }
        return getUserId(VirtualCore.get().myUid());
    }

    public static void writeToParcel(VUserHandle vUserHandle, Parcel parcel) {
        if (vUserHandle != null) {
            vUserHandle.writeToParcel(parcel, 0);
        } else {
            parcel.writeInt(USER_NULL);
        }
    }

    public static VUserHandle readFromParcel(Parcel parcel) {
        int readInt = parcel.readInt();
        if (readInt != -10000) {
            return new VUserHandle(readInt);
        }
        return null;
    }

    public static VUserHandle myUserHandle() {
        return new VUserHandle(myUserId());
    }

    public final boolean isOwner() {
        return equals(OWNER);
    }

    public int getIdentifier() {
        return this.mHandle;
    }

    public String toString() {
        return "VUserHandle{" + this.mHandle + C4963cj.f20747d;
    }

    public boolean equals(Object obj) {
        if (obj != null) {
            try {
                return this.mHandle == ((VUserHandle) obj).mHandle;
            } catch (ClassCastException unused) {
            }
        }
        return false;
    }

    public int hashCode() {
        return this.mHandle;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mHandle);
    }
}
