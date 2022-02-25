package com.lody.virtual.client.hook.proxies.wifi;

import android.net.DhcpInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.WorkSource;
import android.text.TextUtils;
import com.alipay.sdk.app.C0650b;
import com.lody.virtual.client.core.SettingConfig;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.hook.base.BinderInvocationProxy;
import com.lody.virtual.client.hook.base.MethodProxy;
import com.lody.virtual.client.hook.base.ReplaceCallingPkgMethodProxy;
import com.lody.virtual.client.hook.base.ResultStaticMethodProxy;
import com.lody.virtual.client.hook.base.StaticMethodProxy;
import com.lody.virtual.client.hook.utils.MethodParameterUtils;
import com.lody.virtual.helper.compat.BuildCompat;
import com.lody.virtual.helper.utils.ArrayUtils;
import com.lody.virtual.helper.utils.Reflect;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Pattern;
import p110z1.IWifiManager;
import p110z1.WifiSsid;

/* loaded from: classes.dex */
public class WifiManagerStub extends BinderInvocationProxy {

    /* loaded from: classes.dex */
    public static class IPInfo {
        InetAddress addr;
        NetworkInterface intf;

        /* renamed from: ip */
        String f10494ip;
        int ip_hex;
        int netmask_hex;
    }

    private static int netmask_to_hex(int i) {
        int i2 = 0;
        int i3 = 0;
        int i4 = 1;
        while (i2 < i) {
            i3 |= i4;
            i2++;
            i4 <<= 1;
        }
        return i3;
    }

    @Override // com.lody.virtual.client.hook.base.BinderInvocationProxy, com.lody.virtual.client.hook.base.MethodInvocationProxy, com.lody.virtual.client.interfaces.IInjector
    public void inject() throws Throwable {
        super.inject();
        WifiManager wifiManager = (WifiManager) VirtualCore.get().getContext().getSystemService("wifi");
        if (p110z1.WifiManager.mService != null) {
            try {
                p110z1.WifiManager.mService.set(wifiManager, getInvocationStub().getProxyInterface());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (p110z1.WifiManager.sService != null) {
            try {
                p110z1.WifiManager.sService.set(getInvocationStub().getProxyInterface());
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /* loaded from: classes.dex */
    private class RemoveWorkSourceMethodProxy extends StaticMethodProxy {
        RemoveWorkSourceMethodProxy(String str) {
            super(str);
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            int indexOfFirst = ArrayUtils.indexOfFirst(objArr, WorkSource.class);
            if (indexOfFirst >= 0) {
                objArr[indexOfFirst] = null;
            }
            return super.call(obj, method, objArr);
        }
    }

    public WifiManagerStub() {
        super(IWifiManager.C5169a.asInterface, "wifi");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.lody.virtual.client.hook.base.MethodInvocationProxy
    public void onBindMethods() {
        super.onBindMethods();
        addMethodProxy(new MethodProxy() { // from class: com.lody.virtual.client.hook.proxies.wifi.WifiManagerStub.1
            @Override // com.lody.virtual.client.hook.base.MethodProxy
            public String getMethodName() {
                return "isWifiEnabled";
            }

            @Override // com.lody.virtual.client.hook.base.MethodProxy
            public Object call(Object obj, Method method, Object... objArr) throws Throwable {
                if (getConfig().getFakeWifiStatus() != null) {
                    return true;
                }
                return super.call(obj, method, objArr);
            }
        });
        addMethodProxy(new MethodProxy() { // from class: com.lody.virtual.client.hook.proxies.wifi.WifiManagerStub.2
            @Override // com.lody.virtual.client.hook.base.MethodProxy
            public String getMethodName() {
                return "getWifiEnabledState";
            }

            @Override // com.lody.virtual.client.hook.base.MethodProxy
            public Object call(Object obj, Method method, Object... objArr) throws Throwable {
                if (getConfig().getFakeWifiStatus() != null) {
                    return 3;
                }
                return super.call(obj, method, objArr);
            }
        });
        addMethodProxy(new MethodProxy() { // from class: com.lody.virtual.client.hook.proxies.wifi.WifiManagerStub.3
            @Override // com.lody.virtual.client.hook.base.MethodProxy
            public String getMethodName() {
                return "createDhcpInfo";
            }

            @Override // com.lody.virtual.client.hook.base.MethodProxy
            public Object call(Object obj, Method method, Object... objArr) throws Throwable {
                IPInfo iPInfo;
                if (getConfig().getFakeWifiStatus() == null || (iPInfo = WifiManagerStub.getIPInfo()) == null) {
                    return super.call(obj, method, objArr);
                }
                return WifiManagerStub.this.createDhcpInfo(iPInfo);
            }
        });
        addMethodProxy(new GetConnectionInfo());
        addMethodProxy(new GetScanResults());
        addMethodProxy(new ReplaceCallingPkgMethodProxy("getBatchedScanResults"));
        addMethodProxy(new RemoveWorkSourceMethodProxy("acquireWifiLock"));
        addMethodProxy(new RemoveWorkSourceMethodProxy("updateWifiLockWorkSource"));
        if (Build.VERSION.SDK_INT > 21) {
            addMethodProxy(new RemoveWorkSourceMethodProxy("startLocationRestrictedScan"));
        }
        if (Build.VERSION.SDK_INT >= 19) {
            addMethodProxy(new RemoveWorkSourceMethodProxy("requestBatchedScan"));
        }
        addMethodProxy(new ReplaceCallingPkgMethodProxy("setWifiEnabled"));
        addMethodProxy(new StaticMethodProxy("getWifiApConfiguration") { // from class: com.lody.virtual.client.hook.proxies.wifi.WifiManagerStub.4
            @Override // com.lody.virtual.client.hook.base.MethodProxy
            public Object call(Object obj, Method method, Object... objArr) throws Throwable {
                List<WifiConfiguration> configuredNetworks = ((WifiManager) WifiManagerStub.this.getContext().getApplicationContext().getSystemService("wifi")).getConfiguredNetworks();
                if (!configuredNetworks.isEmpty()) {
                    return configuredNetworks.get(0);
                }
                WifiConfiguration wifiConfiguration = new WifiConfiguration();
                wifiConfiguration.SSID = "AndroidAP_" + new Random().nextInt(C0650b.f298a) + 1000;
                wifiConfiguration.allowedKeyManagement.set(4);
                String uuid = UUID.randomUUID().toString();
                wifiConfiguration.preSharedKey = uuid.substring(0, 8) + uuid.substring(9, 13);
                return wifiConfiguration;
            }
        });
        addMethodProxy(new ResultStaticMethodProxy("setWifiApConfiguration", 0));
        addMethodProxy(new ReplaceCallingPkgMethodProxy("startLocalOnlyHotspot"));
        if (BuildCompat.isOreo()) {
            addMethodProxy(new RemoveWorkSourceMethodProxy("startScan") { // from class: com.lody.virtual.client.hook.proxies.wifi.WifiManagerStub.5
                @Override // com.lody.virtual.client.hook.proxies.wifi.WifiManagerStub.RemoveWorkSourceMethodProxy, com.lody.virtual.client.hook.base.MethodProxy
                public Object call(Object obj, Method method, Object... objArr) throws Throwable {
                    MethodParameterUtils.replaceFirstAppPkg(objArr);
                    return super.call(obj, method, objArr);
                }
            });
        } else if (Build.VERSION.SDK_INT >= 19) {
            addMethodProxy(new RemoveWorkSourceMethodProxy("startScan"));
        }
    }

    /* loaded from: classes.dex */
    private final class GetConnectionInfo extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getConnectionInfo";
        }

        private GetConnectionInfo() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            MethodParameterUtils.replaceFirstAppPkg(objArr);
            SettingConfig.FakeWifiStatus fakeWifiStatus = getConfig().getFakeWifiStatus();
            if (fakeWifiStatus != null) {
                return WifiManagerStub.createWifiInfo(fakeWifiStatus);
            }
            WifiInfo wifiInfo = (WifiInfo) method.invoke(obj, objArr);
            if (wifiInfo != null) {
                if (isFakeLocationEnable()) {
                    p110z1.WifiInfo.mBSSID.set(wifiInfo, "00:00:00:00:00:00");
                    p110z1.WifiInfo.mMacAddress.set(wifiInfo, "00:00:00:00:00:00");
                } else if (getDeviceConfig().enable) {
                    String str = getDeviceConfig().wifiMac;
                    if (!TextUtils.isEmpty(str)) {
                        p110z1.WifiInfo.mMacAddress.set(wifiInfo, str);
                    }
                }
            }
            return wifiInfo;
        }
    }

    /* loaded from: classes.dex */
    private final class GetScanResults extends ReplaceCallingPkgMethodProxy {
        public GetScanResults() {
            super("getScanResults");
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            if (isFakeLocationEnable()) {
                return new ArrayList();
            }
            return super.call(obj, method, objArr);
        }
    }

    private static ScanResult cloneScanResult(Parcelable parcelable) {
        Parcel obtain = Parcel.obtain();
        parcelable.writeToParcel(obtain, 0);
        obtain.setDataPosition(0);
        ScanResult scanResult = (ScanResult) Reflect.m18998on(parcelable).field("CREATOR").call("createFromParcel", obtain).get();
        obtain.recycle();
        return scanResult;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static IPInfo getIPInfo() {
        try {
            for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                for (InetAddress inetAddress : Collections.list(networkInterface.getInetAddresses())) {
                    if (!inetAddress.isLoopbackAddress()) {
                        String upperCase = inetAddress.getHostAddress().toUpperCase();
                        if (isIPv4Address(upperCase)) {
                            IPInfo iPInfo = new IPInfo();
                            iPInfo.addr = inetAddress;
                            iPInfo.intf = networkInterface;
                            iPInfo.f10494ip = upperCase;
                            iPInfo.ip_hex = InetAddress_to_hex(inetAddress);
                            iPInfo.netmask_hex = netmask_to_hex(networkInterface.getInterfaceAddresses().get(0).getNetworkPrefixLength());
                            return iPInfo;
                        }
                    }
                }
            }
            return null;
        } catch (SocketException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static boolean isIPv4Address(String str) {
        return Pattern.compile("^(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}$").matcher(str).matches();
    }

    private static int InetAddress_to_hex(InetAddress inetAddress) {
        byte[] address = inetAddress.getAddress();
        int i = 0;
        for (int i2 = 0; i2 < 4; i2++) {
            i |= (address[i2] & 255) << (i2 * 8);
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public DhcpInfo createDhcpInfo(IPInfo iPInfo) {
        DhcpInfo dhcpInfo = new DhcpInfo();
        dhcpInfo.ipAddress = iPInfo.ip_hex;
        dhcpInfo.netmask = iPInfo.netmask_hex;
        dhcpInfo.dns1 = 67372036;
        dhcpInfo.dns2 = 134744072;
        return dhcpInfo;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static WifiInfo createWifiInfo(SettingConfig.FakeWifiStatus fakeWifiStatus) {
        WifiInfo newInstance = p110z1.WifiInfo.ctor.newInstance();
        IPInfo iPInfo = getIPInfo();
        InetAddress inetAddress = iPInfo != null ? iPInfo.addr : null;
        p110z1.WifiInfo.mNetworkId.set(newInstance, 1);
        p110z1.WifiInfo.mSupplicantState.set(newInstance, SupplicantState.COMPLETED);
        p110z1.WifiInfo.mBSSID.set(newInstance, fakeWifiStatus.getBSSID());
        p110z1.WifiInfo.mMacAddress.set(newInstance, fakeWifiStatus.getMAC());
        p110z1.WifiInfo.mIpAddress.set(newInstance, inetAddress);
        p110z1.WifiInfo.mLinkSpeed.set(newInstance, 65);
        if (Build.VERSION.SDK_INT >= 21) {
            p110z1.WifiInfo.mFrequency.set(newInstance, 5000);
        }
        p110z1.WifiInfo.mRssi.set(newInstance, 200);
        if (p110z1.WifiInfo.mWifiSsid != null) {
            p110z1.WifiInfo.mWifiSsid.set(newInstance, WifiSsid.createFromAsciiEncoded.call(fakeWifiStatus.getSSID()));
        } else {
            p110z1.WifiInfo.mSSID.set(newInstance, fakeWifiStatus.getSSID());
        }
        return newInstance;
    }
}
