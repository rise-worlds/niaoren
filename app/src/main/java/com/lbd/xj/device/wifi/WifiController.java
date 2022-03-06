package com.lbd.xj.device.wifi;

import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import com.common.utils.log.LogUtils;
import com.lbd.xj.app.XJApp;
import java.util.List;

/* renamed from: com.lbd.xj.device.wifi.WifiController */
/* loaded from: classes.dex */
public class WifiController {
    private WIFIChangedListener listener;
    private WifiManager wifiMgr;
    private int nCount = 0;
    private int IsStartSendMywifiData = 0;

    /* renamed from: com.lbd.xj.device.wifi.WifiController$WIFIChangedListener */
    /* loaded from: classes.dex */
    public interface WIFIChangedListener {
        void wifiChanged(String str);
    }

    public WifiController(WIFIChangedListener wIFIChangedListener) {
        this.listener = wIFIChangedListener;
        initWifi();
    }

    /* JADX WARN: Type inference failed for: r0v5, types: [com.lbd.xj.device.wifi.WifiController$1] */
    private void initWifi() {
        this.wifiMgr = (WifiManager) XJApp.getInstance().getApplicationContext().getSystemService("wifi");
        if (this.wifiMgr == null) {
            LogUtils.m22038d("SHENG", "yuelog mywifi rfbox getSystemService == null ");
        }
        new Thread() { // from class: com.lbd.xj.device.wifi.WifiController.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                LogUtils.m22038d("SHENG", "yuelog wifi0505thread XUNIDASHI new Thread() ");
                while (true) {
                    try {
                        Thread.sleep(10L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (WifiController.this.IsStartSendMywifiData == 1) {
                        LogUtils.m22038d("SHENG", "yuelog wifi0505thread XUNIDASHI Thread IsStartSendMywifiData == 1");
                        LogUtils.m22038d("SHENG", "yuelog   wifi0505thread  XUNIDASHI GetWIFIInfo");
                        WifiController.this.getWIFIInfo();
                        WifiController.this.IsStartSendMywifiData = 0;
                    }
                }
            }
        }.start();
    }

    public void getWIFIInfo() {
        WifiManager wifiManager = this.wifiMgr;
        if (wifiManager != null) {
            wifiManager.startScan();
            List<ScanResult> scanResults = this.wifiMgr.getScanResults();
            String str = new String();
            if (scanResults == null) {
                LogUtils.m22036e("SHENG", "getwifi0505 无法扫描到的周围热点信息");
                this.listener.wifiChanged("CantGetWifi");
            } else if (scanResults.size() == 0) {
                this.listener.wifiChanged("CantGetWifi");
            } else {
                int i = 20;
                if (scanResults.size() < 20) {
                    i = scanResults.size();
                }
                String str2 = str;
                for (int i2 = 0; i2 < i; i2++) {
                    str2 = str2 + String.format("ie=\nid=0\nbssid=%s\nfreq=%d\nlevel=%d\ntsf=%d\nflags=[ESS]\nssid=%s\n####", scanResults.get(i2).BSSID, Integer.valueOf(scanResults.get(i2).frequency), Integer.valueOf(scanResults.get(i2).level), Long.valueOf(scanResults.get(i2).timestamp), scanResults.get(i2).SSID);
                }
                this.nCount++;
                this.listener.wifiChanged(str2);
            }
        } else {
            LogUtils.m22036e("SHENG", "getwifi0505 无法获取 WIFI_SERVICE");
            this.listener.wifiChanged("CantGetWifi");
        }
    }

    public void setWifiStart(int i) {
        LogUtils.m22034i("SHENG", "yuelog xunidashi SetMyWifiStart  IsStartSendMywifiData = 1 ");
        this.IsStartSendMywifiData = i;
    }

    public int getIsStartSendMywifiData() {
        return this.IsStartSendMywifiData;
    }
}
