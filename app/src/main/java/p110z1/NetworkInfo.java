package p110z1;

import android.net.NetworkInfo;
import mirror.MethodParams;
import mirror.RefBoolean;
import mirror.RefClass;
import mirror.RefConstructor;
import mirror.RefInt;
import mirror.RefObject;

/* renamed from: z1.cvt */
/* loaded from: classes3.dex */
public class NetworkInfo {
    public static Class<?> TYPE = RefClass.load(NetworkInfo.class, android.net.NetworkInfo.class);
    @MethodParams({int.class, int.class, String.class, String.class})
    public static RefConstructor<android.net.NetworkInfo> ctor;
    @MethodParams({int.class})
    public static RefConstructor<android.net.NetworkInfo> ctorOld;
    public static RefObject<NetworkInfo.DetailedState> mDetailedState;
    public static RefBoolean mIsAvailable;
    public static RefInt mNetworkType;
    public static RefObject<NetworkInfo.State> mState;
    public static RefObject<String> mTypeName;
}
