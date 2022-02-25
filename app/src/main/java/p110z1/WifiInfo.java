package p110z1;

import android.net.wifi.SupplicantState;
import java.net.InetAddress;
import mirror.RefClass;
import mirror.RefConstructor;
import mirror.RefInt;
import mirror.RefObject;

/* renamed from: z1.cvv */
/* loaded from: classes3.dex */
public class WifiInfo {
    public static Class<?> TYPE = RefClass.load(WifiInfo.class, android.net.wifi.WifiInfo.class);
    public static RefConstructor<android.net.wifi.WifiInfo> ctor;
    public static RefObject<String> mBSSID;
    public static RefInt mFrequency;
    public static RefObject<InetAddress> mIpAddress;
    public static RefInt mLinkSpeed;
    public static RefObject<String> mMacAddress;
    public static RefInt mNetworkId;
    public static RefInt mRssi;
    public static RefObject<String> mSSID;
    public static RefObject<SupplicantState> mSupplicantState;
    public static RefObject<Object> mWifiSsid;
}
