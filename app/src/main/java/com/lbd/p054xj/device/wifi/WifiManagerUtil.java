package com.lbd.p054xj.device.wifi;

import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import com.common.utils.log.LogUtils;
import com.lbd.p054xj.manager.FullJniUtil;
import java.util.List;

/* renamed from: com.lbd.xj.device.wifi.WifiManagerUtil */
/* loaded from: classes.dex */
public class WifiManagerUtil {
    private static final String TAG = "WifiManagerUtil";
    private HandlerThread mHandlerThreadWIFI;
    private Handler wifiHandler;
    private WifiManager wifiMgr = null;

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.lbd.xj.device.wifi.WifiManagerUtil$SingletonClassInstance */
    /* loaded from: classes.dex */
    public static class SingletonClassInstance {
        public static final WifiManagerUtil manage = new WifiManagerUtil();

        private SingletonClassInstance() {
        }
    }

    public static WifiManagerUtil getInstance() {
        return SingletonClassInstance.manage;
    }

    private void InitializeWifiHandler() {
        this.mHandlerThreadWIFI = new HandlerThread(TAG);
        this.mHandlerThreadWIFI.start();
        this.wifiHandler = new Handler(this.mHandlerThreadWIFI.getLooper(), new Handler.Callback() { // from class: com.lbd.xj.device.wifi.WifiManagerUtil.1
            @Override // android.os.Handler.Callback
            public boolean handleMessage(Message message) {
                WifiManagerUtil.this.GetWIFIInfo();
                return false;
            }
        });
    }

    public void initializeWifiManager(Object obj) {
        if (obj == null) {
            LogUtils.m22037e("获取为 WifiManager == null ");
            return;
        }
        if (obj instanceof WifiManager) {
            this.wifiMgr = (WifiManager) obj;
        }
        InitializeWifiHandler();
    }

    public void closeWifiInfo() {
        HandlerThread handlerThread = this.mHandlerThreadWIFI;
        if (handlerThread != null) {
            handlerThread.quit();
        }
    }

    public void GetWIFIInfo() {
        WifiManager wifiManager = this.wifiMgr;
        if (wifiManager != null) {
            try {
                wifiManager.startScan();
                List<ScanResult> scanResults = this.wifiMgr.getScanResults();
                if (scanResults != null && scanResults.size() > 0) {
                    LogUtils.m22037e("扫描到的周围热点信息数量：" + scanResults.size());
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < scanResults.size() && i < 20; i++) {
                        ScanResult scanResult = scanResults.get(i);
                        if (scanResult != null) {
                            sb.append("ie=\n");
                            sb.append("id=0\n");
                            sb.append(String.format("bssid=%s\n", scanResult.BSSID));
                            sb.append(String.format("freq=%s\n", Integer.valueOf(scanResult.frequency)));
                            sb.append(String.format("level=%s\n", Integer.valueOf(scanResult.level)));
                            sb.append(String.format("tsf=%s\n", Long.valueOf(scanResult.timestamp)));
                            sb.append("flags=[ESS]\n");
                            sb.append(String.format("ssid=%s\n", scanResult.SSID));
                            sb.append("####");
                        }
                    }
                    FullJniUtil.m19759a().MyWIFIChanged(sb.toString());
                    return;
                }
            } catch (Exception e) {
                LogUtils.m22037e("获取WIFI热点信息失败!!!");
                e.printStackTrace();
                return;
            }
        }
        LogUtils.m22037e("VMOS_APP 无法获取 WIFI_SERVICE");
        FullJniUtil.m19759a().MyWIFIChanged("CantGetWifi");
    }

    public void SetWifiStart(int i, int i2) {
        Handler handler = this.wifiHandler;
        if (handler != null) {
            handler.sendEmptyMessage(1);
        }
    }
}
