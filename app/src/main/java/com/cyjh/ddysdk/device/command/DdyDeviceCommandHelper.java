package com.cyjh.ddysdk.device.command;

import android.os.Build;
import android.util.Base64;
import com.blankj.utilcode.util.Utils;
import com.cyjh.ddy.base.p033a.NoProGuard;
import com.cyjh.ddy.base.utils.C1123f;
import com.cyjh.ddy.base.utils.InformationUtils;
import com.cyjh.ddy.base.utils.JsonUtil;
import com.cyjh.ddy.thirdlib.lib_hwobs.ObsCert;
import com.cyjh.ddysdk.device.base.constants.DdyDeviceErrorConstants;
import com.cyjh.ddysdk.device.bean.AppInfo;
import com.cyjh.ddysdk.device.bean.GpsInfo;
import com.cyjh.ddysdk.device.bean.ResponeDeviceInfo;
import com.cyjh.ddysdk.device.bean.ResponseNoticeApps;
import com.cyjh.ddysdk.device.command.DdyDeviceCommandContract;
import com.cyjh.ddysdk.order.base.bean.DdyOrderInfo;
import com.cyjh.ddysdk.order.base.bean.DefineOrderInfo;
import com.stripe.android.view.ShippingInfoWidget;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class DdyDeviceCommandHelper implements NoProGuard, DdyDeviceCommandContract.IApp, DdyDeviceCommandContract.IBusiness {

    /* renamed from: a */
    private static DdyDeviceCommandHelper f7718a;

    /* renamed from: b */
    private Map<Long, DeviceScreencapCmdPresenter> f7719b = new HashMap();

    /* renamed from: c */
    private DeviceAppModule f7720c = new DeviceAppModule();

    private DdyDeviceCommandHelper() {
    }

    public static DdyDeviceCommandHelper getInstance() {
        if (f7718a == null) {
            f7718a = new DdyDeviceCommandHelper();
        }
        return f7718a;
    }

    public void uninit() {
        f7718a = null;
    }

    public void screencap(DdyOrderInfo ddyOrderInfo, long j, long j2, DdyDeviceCommandContract.ScreenCap.IView iView) {
        if (!(ddyOrderInfo instanceof DefineOrderInfo)) {
            iView.updateScreenCapFailure(ddyOrderInfo.OrderId, DdyDeviceErrorConstants.DDE_Exception_Param, "error param");
            return;
        }
        DefineOrderInfo defineOrderInfo = (DefineOrderInfo) ddyOrderInfo;
        try {
            if (this.f7719b.containsKey(Long.valueOf(defineOrderInfo.OrderId))) {
                this.f7719b.get(Long.valueOf(defineOrderInfo.OrderId)).changleView(iView);
            } else {
                this.f7719b.put(Long.valueOf(defineOrderInfo.OrderId), new DeviceScreencapCmdPresenter(iView));
            }
            this.f7719b.get(Long.valueOf(defineOrderInfo.OrderId)).screenCap(defineOrderInfo.OrderId, defineOrderInfo.DeviceTcpHost, j, j2, defineOrderInfo.DeviceToken);
        } catch (Exception e) {
            iView.updateScreenCapFailure(ddyOrderInfo.OrderId, DdyDeviceErrorConstants.DDE_Exception, e.getMessage());
        }
    }

    @Override // com.cyjh.ddysdk.device.command.DdyDeviceCommandContract.IApp
    public void runApp(DdyOrderInfo ddyOrderInfo, String str, String str2, Map<String, String> map, DdyDeviceCommandContract.Callback<Integer> callback) {
        if (map == null || !map.containsKey("ddyStringExtras")) {
            m21277a(ddyOrderInfo, str, str2, null, map, callback);
        } else {
            m21277a(ddyOrderInfo, str, str2, map.get("ddyStringExtras"), null, callback);
        }
    }

    /* renamed from: a */
    private void m21277a(DdyOrderInfo ddyOrderInfo, String str, String str2, String str3, Map<String, String> map, DdyDeviceCommandContract.Callback<Integer> callback) {
        if (ddyOrderInfo instanceof DefineOrderInfo) {
            DefineOrderInfo defineOrderInfo = (DefineOrderInfo) ddyOrderInfo;
            this.f7720c.m21257a(defineOrderInfo.DeviceTcpHost, str, str2, str3, map, defineOrderInfo.DeviceToken, callback);
        } else if (callback != null) {
            callback.failuer(DdyDeviceErrorConstants.DDE_Exception_Param, "error param");
        }
    }

    @Override // com.cyjh.ddysdk.device.command.DdyDeviceCommandContract.IApp
    public void runAppSave(DdyOrderInfo ddyOrderInfo, String str, String str2, Map<String, String> map, String str3, List<String> list, int i, DdyDeviceCommandContract.Callback<Integer> callback) {
        if (map == null || !map.containsKey("ddyStringExtras")) {
            m21276a(ddyOrderInfo, str, str2, "", map, str3, list, i, callback);
        } else {
            m21276a(ddyOrderInfo, str, str2, map.get("ddyStringExtras"), null, str3, list, i, callback);
        }
    }

    /* renamed from: a */
    private void m21276a(DdyOrderInfo ddyOrderInfo, String str, String str2, String str3, Map<String, String> map, String str4, List<String> list, int i, DdyDeviceCommandContract.Callback<Integer> callback) {
        if (ddyOrderInfo instanceof DefineOrderInfo) {
            DefineOrderInfo defineOrderInfo = (DefineOrderInfo) ddyOrderInfo;
            this.f7720c.m21256a(defineOrderInfo.DeviceTcpHost, str, str2, str3, map, defineOrderInfo.DeviceToken, str4, list, i, callback);
        } else if (callback != null) {
            callback.failuer(DdyDeviceErrorConstants.DDE_Exception_Param, "error param");
        }
    }

    @Override // com.cyjh.ddysdk.device.command.DdyDeviceCommandContract.IApp
    public void amStartApp(DdyOrderInfo ddyOrderInfo, String str, String str2, DdyDeviceCommandContract.Callback<String> callback) {
        if (ddyOrderInfo instanceof DefineOrderInfo) {
            DefineOrderInfo defineOrderInfo = (DefineOrderInfo) ddyOrderInfo;
            this.f7720c.m21259a(defineOrderInfo.DeviceTcpHost, str, str2, defineOrderInfo.DeviceToken, callback);
        } else if (callback != null) {
            callback.failuer(DdyDeviceErrorConstants.DDE_Exception_Param, "error param");
        }
    }

    public void amBroadcast(DdyOrderInfo ddyOrderInfo, String str, String str2, String str3, DdyDeviceCommandContract.Callback<String> callback) {
        if (ddyOrderInfo instanceof DefineOrderInfo) {
            DefineOrderInfo defineOrderInfo = (DefineOrderInfo) ddyOrderInfo;
            this.f7720c.m21258a(defineOrderInfo.DeviceTcpHost, str, str2, str3, defineOrderInfo.DeviceToken, callback);
        } else if (callback != null) {
            callback.failuer(DdyDeviceErrorConstants.DDE_Exception_Param, "error param");
        }
    }

    public void amStartService(DdyOrderInfo ddyOrderInfo, String str, String str2, String str3, DdyDeviceCommandContract.Callback<String> callback) {
        if (ddyOrderInfo instanceof DefineOrderInfo) {
            DefineOrderInfo defineOrderInfo = (DefineOrderInfo) ddyOrderInfo;
            this.f7720c.m21248b(defineOrderInfo.DeviceTcpHost, str, str2, str3, defineOrderInfo.DeviceToken, callback);
        } else if (callback != null) {
            callback.failuer(DdyDeviceErrorConstants.DDE_Exception_Param, "error param");
        }
    }

    @Override // com.cyjh.ddysdk.device.command.DdyDeviceCommandContract.IApp
    public void installApp(DdyOrderInfo ddyOrderInfo, String str, String str2, String str3, boolean z, Map<String, String> map, DdyDeviceCommandContract.Callback<Integer> callback) {
        if (map == null || !map.containsKey("ddyStringExtras")) {
            m21275a(ddyOrderInfo, str, str2, str3, z, null, map, callback);
        } else {
            m21275a(ddyOrderInfo, str, str2, str3, z, map.get("ddyStringExtras"), null, callback);
        }
    }

    /* renamed from: a */
    private void m21275a(DdyOrderInfo ddyOrderInfo, String str, String str2, String str3, boolean z, String str4, Map<String, String> map, DdyDeviceCommandContract.Callback<Integer> callback) {
        if (ddyOrderInfo instanceof DefineOrderInfo) {
            DefineOrderInfo defineOrderInfo = (DefineOrderInfo) ddyOrderInfo;
            this.f7720c.m21255a(defineOrderInfo.DeviceTcpHost, str, str2, str3, z, str4, map, defineOrderInfo.DeviceToken, callback);
        } else if (callback != null) {
            callback.failuer(DdyDeviceErrorConstants.DDE_Exception_Param, "error param");
        }
    }

    @Override // com.cyjh.ddysdk.device.command.DdyDeviceCommandContract.IApp
    public void installAppTar(DdyOrderInfo ddyOrderInfo, String str, String str2, String str3, boolean z, Map<String, String> map, DdyDeviceCommandContract.Callback<Integer> callback) {
        if (map == null || !map.containsKey("ddyStringExtras")) {
            m21274b(ddyOrderInfo, str, str2, str3, z, "", map, callback);
        } else {
            m21274b(ddyOrderInfo, str, str2, str3, z, map.get("ddyStringExtras"), null, callback);
        }
    }

    /* renamed from: b */
    private void m21274b(DdyOrderInfo ddyOrderInfo, String str, String str2, String str3, boolean z, String str4, Map<String, String> map, DdyDeviceCommandContract.Callback<Integer> callback) {
        if (ddyOrderInfo instanceof DefineOrderInfo) {
            DefineOrderInfo defineOrderInfo = (DefineOrderInfo) ddyOrderInfo;
            this.f7720c.m21247b(defineOrderInfo.DeviceTcpHost, str, str2, str3, z, str4, map, defineOrderInfo.DeviceToken, callback);
        } else if (callback != null) {
            callback.failuer(DdyDeviceErrorConstants.DDE_Exception_Param, "error param");
        }
    }

    @Override // com.cyjh.ddysdk.device.command.DdyDeviceCommandContract.IApp
    public void uninstallApps(DdyOrderInfo ddyOrderInfo, List<String> list, DdyDeviceCommandContract.Callback<Integer> callback) {
        if (ddyOrderInfo instanceof DefineOrderInfo) {
            DefineOrderInfo defineOrderInfo = (DefineOrderInfo) ddyOrderInfo;
            this.f7720c.m21253a(defineOrderInfo.DeviceTcpHost, list, defineOrderInfo.DeviceToken, callback);
        } else if (callback != null) {
            callback.failuer(DdyDeviceErrorConstants.DDE_Exception_Param, "error param");
        }
    }

    @Override // com.cyjh.ddysdk.device.command.DdyDeviceCommandContract.IApp
    public void stopApps(DdyOrderInfo ddyOrderInfo, List<String> list, DdyDeviceCommandContract.Callback<Integer> callback) {
        if (ddyOrderInfo instanceof DefineOrderInfo) {
            DefineOrderInfo defineOrderInfo = (DefineOrderInfo) ddyOrderInfo;
            this.f7720c.m21246b(defineOrderInfo.DeviceTcpHost, list, defineOrderInfo.DeviceToken, callback);
        } else if (callback != null) {
            callback.failuer(DdyDeviceErrorConstants.DDE_Exception_Param, "error param");
        }
    }

    @Override // com.cyjh.ddysdk.device.command.DdyDeviceCommandContract.IApp
    public void clearApps(DdyOrderInfo ddyOrderInfo, List<String> list, DdyDeviceCommandContract.Callback<Integer> callback) {
        if (ddyOrderInfo instanceof DefineOrderInfo) {
            DefineOrderInfo defineOrderInfo = (DefineOrderInfo) ddyOrderInfo;
            this.f7720c.m21243c(defineOrderInfo.DeviceTcpHost, list, defineOrderInfo.DeviceToken, callback);
        } else if (callback != null) {
            callback.failuer(DdyDeviceErrorConstants.DDE_Exception_Param, "error param");
        }
    }

    @Override // com.cyjh.ddysdk.device.command.DdyDeviceCommandContract.IApp
    public void getAppsInfo(DdyOrderInfo ddyOrderInfo, DdyDeviceCommandContract.Callback<List<AppInfo>> callback) {
        if (ddyOrderInfo instanceof DefineOrderInfo) {
            DefineOrderInfo defineOrderInfo = (DefineOrderInfo) ddyOrderInfo;
            this.f7720c.m21245c(defineOrderInfo.DeviceTcpHost, defineOrderInfo.DeviceToken, callback);
        } else if (callback != null) {
            callback.failuer(DdyDeviceErrorConstants.DDE_Exception_Param, "error param");
        }
    }

    @Override // com.cyjh.ddysdk.device.command.DdyDeviceCommandContract.IApp
    public void getInstallState(DdyOrderInfo ddyOrderInfo, String str, DdyDeviceCommandContract.Callback<String> callback) {
        if (ddyOrderInfo instanceof DefineOrderInfo) {
            DefineOrderInfo defineOrderInfo = (DefineOrderInfo) ddyOrderInfo;
            this.f7720c.m21260a(defineOrderInfo.DeviceTcpHost, str, defineOrderInfo.DeviceToken, callback);
        } else if (callback != null) {
            callback.failuer(DdyDeviceErrorConstants.DDE_Exception_Param, "error param");
        }
    }

    public void setClipBoard(DdyOrderInfo ddyOrderInfo, String str, DdyDeviceCommandContract.Callback<Integer> callback) {
        if (ddyOrderInfo instanceof DefineOrderInfo) {
            DefineOrderInfo defineOrderInfo = (DefineOrderInfo) ddyOrderInfo;
            this.f7720c.m21249b(defineOrderInfo.DeviceTcpHost, str, defineOrderInfo.DeviceToken, callback);
        } else if (callback != null) {
            callback.failuer(DdyDeviceErrorConstants.DDE_Exception_Param, "error param");
        }
    }

    @Override // com.cyjh.ddysdk.device.command.DdyDeviceCommandContract.IBusiness
    public void queryRootState(DdyOrderInfo ddyOrderInfo, DdyDeviceCommandContract.Callback<Boolean> callback) {
        if (ddyOrderInfo instanceof DefineOrderInfo) {
            DefineOrderInfo defineOrderInfo = (DefineOrderInfo) ddyOrderInfo;
            this.f7720c.m21242d(defineOrderInfo.DeviceTcpHost, defineOrderInfo.DeviceToken, callback);
        } else if (callback != null) {
            callback.failuer(DdyDeviceErrorConstants.DDE_Exception_Param, "error param");
        }
    }

    public void inputKeyEvent(DdyOrderInfo ddyOrderInfo, int i, DdyDeviceCommandContract.Callback<Integer> callback) {
        if (ddyOrderInfo instanceof DefineOrderInfo) {
            DefineOrderInfo defineOrderInfo = (DefineOrderInfo) ddyOrderInfo;
            this.f7720c.m21266a(defineOrderInfo.DeviceTcpHost, i, defineOrderInfo.DeviceToken, callback);
        } else if (callback != null) {
            callback.failuer(DdyDeviceErrorConstants.DDE_Exception_Param, "error param");
        }
    }

    public void inputText(DdyOrderInfo ddyOrderInfo, String str, DdyDeviceCommandContract.Callback<Integer> callback) {
        if (ddyOrderInfo instanceof DefineOrderInfo) {
            DefineOrderInfo defineOrderInfo = (DefineOrderInfo) ddyOrderInfo;
            this.f7720c.m21244c(defineOrderInfo.DeviceTcpHost, str, defineOrderInfo.DeviceToken, callback);
        } else if (callback != null) {
            callback.failuer(DdyDeviceErrorConstants.DDE_Exception_Param, "error param");
        }
    }

    public void pushFile(DdyOrderInfo ddyOrderInfo, String str, String str2, ObsCert obsCert, DdyDeviceCommandContract.Callback<Integer> callback) {
        if (ddyOrderInfo instanceof DefineOrderInfo) {
            DefineOrderInfo defineOrderInfo = (DefineOrderInfo) ddyOrderInfo;
            this.f7720c.m21261a(defineOrderInfo.DeviceTcpHost, str, str2, obsCert, defineOrderInfo.DeviceToken, callback);
        } else if (callback != null) {
            callback.failuer(DdyDeviceErrorConstants.DDE_Exception_Param, "error param");
        }
    }

    @Override // com.cyjh.ddysdk.device.command.DdyDeviceCommandContract.IBusiness
    public void photograph(final DdyOrderInfo ddyOrderInfo, String str, final String str2, ObsCert obsCert, final DdyDeviceCommandContract.Callback<String> callback) {
        if ("null".equals(str)) {
            cameraCancel(ddyOrderInfo, callback);
        } else if (ddyOrderInfo instanceof DefineOrderInfo) {
            DefineOrderInfo defineOrderInfo = (DefineOrderInfo) ddyOrderInfo;
            this.f7720c.m21261a(defineOrderInfo.DeviceTcpHost, str, str2, obsCert, defineOrderInfo.DeviceToken, new DdyDeviceCommandContract.Callback<Integer>() { // from class: com.cyjh.ddysdk.device.command.DdyDeviceCommandHelper.1
                public void success(Integer num) {
                    DdyDeviceCommandHelper.this.amBroadcast(ddyOrderInfo, "com.cyjh.ygj.net.http.pullCameraFile", "filepath", str2, new DdyDeviceCommandContract.Callback<String>() { // from class: com.cyjh.ddysdk.device.command.DdyDeviceCommandHelper.1.1
                        public void success(String str3) {
                            callback.success(str3);
                        }

                        @Override // com.cyjh.ddysdk.device.command.DdyDeviceCommandContract.Callback
                        public void failuer(DdyDeviceErrorConstants ddyDeviceErrorConstants, String str3) {
                            callback.failuer(ddyDeviceErrorConstants, str3);
                        }
                    });
                }

                @Override // com.cyjh.ddysdk.device.command.DdyDeviceCommandContract.Callback
                public void failuer(final DdyDeviceErrorConstants ddyDeviceErrorConstants, final String str3) {
                    DdyDeviceCommandHelper.this.amBroadcast(ddyOrderInfo, "com.cyjh.ygj.net.http.pullCameraFile", "filepath", "null", new DdyDeviceCommandContract.Callback<String>() { // from class: com.cyjh.ddysdk.device.command.DdyDeviceCommandHelper.1.2
                        public void success(String str4) {
                            callback.failuer(ddyDeviceErrorConstants, str3);
                        }

                        @Override // com.cyjh.ddysdk.device.command.DdyDeviceCommandContract.Callback
                        public void failuer(DdyDeviceErrorConstants ddyDeviceErrorConstants2, String str4) {
                            callback.failuer(ddyDeviceErrorConstants2, str4);
                        }
                    });
                }
            });
        } else if (callback != null) {
            callback.failuer(DdyDeviceErrorConstants.DDE_Exception_Param, "error param");
        }
    }

    @Override // com.cyjh.ddysdk.device.command.DdyDeviceCommandContract.IBusiness
    public void qrcode(DdyOrderInfo ddyOrderInfo, String str, final DdyDeviceCommandContract.Callback<String> callback) {
        amBroadcast(ddyOrderInfo, "com.cyjh.ddy.scan.ScanQRReceiver", "code", str, new DdyDeviceCommandContract.Callback<String>() { // from class: com.cyjh.ddysdk.device.command.DdyDeviceCommandHelper.2
            public void success(String str2) {
                callback.success(str2);
            }

            @Override // com.cyjh.ddysdk.device.command.DdyDeviceCommandContract.Callback
            public void failuer(DdyDeviceErrorConstants ddyDeviceErrorConstants, String str2) {
                callback.failuer(ddyDeviceErrorConstants, str2);
            }
        });
    }

    @Override // com.cyjh.ddysdk.device.command.DdyDeviceCommandContract.IBusiness
    public void shake(DdyOrderInfo ddyOrderInfo, final DdyDeviceCommandContract.Callback<String> callback) {
        if (ddyOrderInfo instanceof DefineOrderInfo) {
            amStartService(ddyOrderInfo, "com.cyjh.shake.ShakeService", "from", "out", new DdyDeviceCommandContract.Callback<String>() { // from class: com.cyjh.ddysdk.device.command.DdyDeviceCommandHelper.3
                public void success(String str) {
                    callback.success(str);
                }

                @Override // com.cyjh.ddysdk.device.command.DdyDeviceCommandContract.Callback
                public void failuer(DdyDeviceErrorConstants ddyDeviceErrorConstants, String str) {
                    callback.failuer(ddyDeviceErrorConstants, str);
                }
            });
        } else if (callback != null) {
            callback.failuer(DdyDeviceErrorConstants.DDE_Exception_Param, "error param");
        }
    }

    @Override // com.cyjh.ddysdk.device.command.DdyDeviceCommandContract.IBusiness
    public void scan(DdyOrderInfo ddyOrderInfo, final DdyDeviceCommandContract.Callback<String> callback) {
        if (ddyOrderInfo instanceof DefineOrderInfo) {
            amStartService(ddyOrderInfo, "com.cyjh.scan.ScanService", "from", "outbtn", new DdyDeviceCommandContract.Callback<String>() { // from class: com.cyjh.ddysdk.device.command.DdyDeviceCommandHelper.4
                public void success(String str) {
                    callback.success(str);
                }

                @Override // com.cyjh.ddysdk.device.command.DdyDeviceCommandContract.Callback
                public void failuer(DdyDeviceErrorConstants ddyDeviceErrorConstants, String str) {
                    callback.failuer(ddyDeviceErrorConstants, str);
                }
            });
        } else if (callback != null) {
            callback.failuer(DdyDeviceErrorConstants.DDE_Exception_Param, "error param");
        }
    }

    @Override // com.cyjh.ddysdk.device.command.DdyDeviceCommandContract.IBusiness
    public void cameraCancel(DdyOrderInfo ddyOrderInfo, final DdyDeviceCommandContract.Callback<String> callback) {
        if (ddyOrderInfo instanceof DefineOrderInfo) {
            amBroadcast(ddyOrderInfo, "com.cyjh.ygj.net.http.pullCameraFile", "filepath", "null", new DdyDeviceCommandContract.Callback<String>() { // from class: com.cyjh.ddysdk.device.command.DdyDeviceCommandHelper.5
                public void success(String str) {
                    callback.success(str);
                }

                @Override // com.cyjh.ddysdk.device.command.DdyDeviceCommandContract.Callback
                public void failuer(DdyDeviceErrorConstants ddyDeviceErrorConstants, String str) {
                    callback.failuer(ddyDeviceErrorConstants, str);
                }
            });
        } else if (callback != null) {
            callback.failuer(DdyDeviceErrorConstants.DDE_Exception_Param, "error param");
        }
    }

    @Override // com.cyjh.ddysdk.device.command.DdyDeviceCommandContract.IBusiness
    public void screenshot(DdyOrderInfo ddyOrderInfo, final DdyDeviceCommandContract.Callback<String> callback) {
        if (ddyOrderInfo instanceof DefineOrderInfo) {
            amStartService(ddyOrderInfo, "com.cyjh.ddy.screenshot.ScreenshotService", "from", "out", new DdyDeviceCommandContract.Callback<String>() { // from class: com.cyjh.ddysdk.device.command.DdyDeviceCommandHelper.6
                public void success(String str) {
                    callback.success(str);
                }

                @Override // com.cyjh.ddysdk.device.command.DdyDeviceCommandContract.Callback
                public void failuer(DdyDeviceErrorConstants ddyDeviceErrorConstants, String str) {
                    callback.failuer(ddyDeviceErrorConstants, str);
                }
            });
        } else if (callback != null) {
            callback.failuer(DdyDeviceErrorConstants.DDE_Exception_Param, "error param");
        }
    }

    @Override // com.cyjh.ddysdk.device.command.DdyDeviceCommandContract.IBusiness
    public void appsFolders(DdyOrderInfo ddyOrderInfo, String str, final DdyDeviceCommandContract.Callback<String> callback) {
        if (ddyOrderInfo instanceof DefineOrderInfo) {
            amBroadcast(ddyOrderInfo, "com.cyjh.huawei.launcher3.loadPresetShortcut", "preset_data", Base64.encodeToString(str.getBytes(), 2), new DdyDeviceCommandContract.Callback<String>() { // from class: com.cyjh.ddysdk.device.command.DdyDeviceCommandHelper.7
                public void success(String str2) {
                    callback.success(str2);
                }

                @Override // com.cyjh.ddysdk.device.command.DdyDeviceCommandContract.Callback
                public void failuer(DdyDeviceErrorConstants ddyDeviceErrorConstants, String str2) {
                    callback.failuer(ddyDeviceErrorConstants, str2);
                }
            });
        } else if (callback != null) {
            callback.failuer(DdyDeviceErrorConstants.DDE_Exception_Param, "error param");
        }
    }

    public void pullFile(DdyOrderInfo ddyOrderInfo, String str, String str2, ObsCert obsCert, DdyDeviceCommandContract.Callback<Integer> callback) {
        if (ddyOrderInfo instanceof DefineOrderInfo) {
            DefineOrderInfo defineOrderInfo = (DefineOrderInfo) ddyOrderInfo;
            this.f7720c.m21250b(defineOrderInfo.DeviceTcpHost, str, str2, obsCert, defineOrderInfo.DeviceToken, callback);
        } else if (callback != null) {
            callback.failuer(DdyDeviceErrorConstants.DDE_Exception_Param, "error param");
        }
    }

    @Override // com.cyjh.ddysdk.device.command.DdyDeviceCommandContract.IApp
    public void getTopApp(DdyOrderInfo ddyOrderInfo, DdyDeviceCommandContract.Callback<String> callback) {
        if (ddyOrderInfo instanceof DefineOrderInfo) {
            DefineOrderInfo defineOrderInfo = (DefineOrderInfo) ddyOrderInfo;
            this.f7720c.m21240e(defineOrderInfo.DeviceTcpHost, defineOrderInfo.DeviceToken, callback);
        } else if (callback != null) {
            callback.failuer(DdyDeviceErrorConstants.DDE_Exception_Param, "error param");
        }
    }

    @Override // com.cyjh.ddysdk.device.command.DdyDeviceCommandContract.IBusiness
    public void haveSysEnvironment(DdyOrderInfo ddyOrderInfo, DdyDeviceCommandContract.Callback<String> callback) {
        if (ddyOrderInfo instanceof DefineOrderInfo) {
            DefineOrderInfo defineOrderInfo = (DefineOrderInfo) ddyOrderInfo;
            this.f7720c.m21262a(defineOrderInfo.DeviceTcpHost, defineOrderInfo.DeviceToken, callback);
        } else if (callback != null) {
            callback.failuer(DdyDeviceErrorConstants.DDE_Exception_Param, "error param");
        }
    }

    @Override // com.cyjh.ddysdk.device.command.DdyDeviceCommandContract.IBusiness
    public void getNoticeApps(DdyOrderInfo ddyOrderInfo, boolean z, DdyDeviceCommandContract.Callback<List<ResponseNoticeApps>> callback) {
        if (ddyOrderInfo instanceof DefineOrderInfo) {
            DefineOrderInfo defineOrderInfo = (DefineOrderInfo) ddyOrderInfo;
            this.f7720c.m21254a(defineOrderInfo.DeviceTcpHost, defineOrderInfo.DeviceToken, z, callback);
        } else if (callback != null) {
            callback.failuer(DdyDeviceErrorConstants.DDE_Exception_Param, "error param");
        }
    }

    @Override // com.cyjh.ddysdk.device.command.DdyDeviceCommandContract.IBusiness
    public void setNotifyPackages(DdyOrderInfo ddyOrderInfo, List<String> list, DdyDeviceCommandContract.Callback<Integer> callback) {
        if (ddyOrderInfo instanceof DefineOrderInfo) {
            DefineOrderInfo defineOrderInfo = (DefineOrderInfo) ddyOrderInfo;
            this.f7720c.m21241d(defineOrderInfo.DeviceTcpHost, list, defineOrderInfo.DeviceToken, callback);
        } else if (callback != null) {
            callback.failuer(DdyDeviceErrorConstants.DDE_Exception_Param, "error param");
        }
    }

    @Override // com.cyjh.ddysdk.device.command.DdyDeviceCommandContract.IBusiness
    public void addNotifyPackages(DdyOrderInfo ddyOrderInfo, List<String> list, DdyDeviceCommandContract.Callback<Integer> callback) {
        if (ddyOrderInfo instanceof DefineOrderInfo) {
            DefineOrderInfo defineOrderInfo = (DefineOrderInfo) ddyOrderInfo;
            this.f7720c.m21239e(defineOrderInfo.DeviceTcpHost, list, defineOrderInfo.DeviceToken, callback);
        } else if (callback != null) {
            callback.failuer(DdyDeviceErrorConstants.DDE_Exception_Param, "error param");
        }
    }

    @Override // com.cyjh.ddysdk.device.command.DdyDeviceCommandContract.IBusiness
    public void removeNotifyPackages(DdyOrderInfo ddyOrderInfo, List<String> list, DdyDeviceCommandContract.Callback<Integer> callback) {
        if (ddyOrderInfo instanceof DefineOrderInfo) {
            DefineOrderInfo defineOrderInfo = (DefineOrderInfo) ddyOrderInfo;
            this.f7720c.m21238f(defineOrderInfo.DeviceTcpHost, list, defineOrderInfo.DeviceToken, callback);
        } else if (callback != null) {
            callback.failuer(DdyDeviceErrorConstants.DDE_Exception_Param, "error param");
        }
    }

    @Override // com.cyjh.ddysdk.device.command.DdyDeviceCommandContract.IBusiness
    public void setPresetApp(DdyOrderInfo ddyOrderInfo, String str, DdyDeviceCommandContract.Callback<String> callback) {
        if (ddyOrderInfo instanceof DefineOrderInfo) {
            DefineOrderInfo defineOrderInfo = (DefineOrderInfo) ddyOrderInfo;
            this.f7720c.m21263a(str, defineOrderInfo.OrderId, defineOrderInfo.DeviceTcpHost, defineOrderInfo.DeviceToken, callback);
        } else if (callback != null) {
            callback.failuer(DdyDeviceErrorConstants.DDE_Exception_Param, "error param");
        }
    }

    @Override // com.cyjh.ddysdk.device.command.DdyDeviceCommandContract.IBusiness
    public void installSysEnvironment(DdyOrderInfo ddyOrderInfo, DdyDeviceCommandContract.Callback<Integer> callback) {
        if (ddyOrderInfo instanceof DefineOrderInfo) {
            DefineOrderInfo defineOrderInfo = (DefineOrderInfo) ddyOrderInfo;
            this.f7720c.m21251b(defineOrderInfo.DeviceTcpHost, defineOrderInfo.DeviceToken, callback);
        } else if (callback != null) {
            callback.failuer(DdyDeviceErrorConstants.DDE_Exception_Param, "error param");
        }
    }

    @Override // com.cyjh.ddysdk.device.command.DdyDeviceCommandContract.IBusiness
    public void setGPS(DdyOrderInfo ddyOrderInfo, double d, double d2, final DdyDeviceCommandContract.Callback<String> callback) {
        if (ddyOrderInfo instanceof DefineOrderInfo) {
            amStartService(ddyOrderInfo, "com.cyjh.gps.GpsService", "gpsJson", JsonUtil.m21804a(new GpsInfo(ddyOrderInfo.OrderId, d, d2)), new DdyDeviceCommandContract.Callback<String>() { // from class: com.cyjh.ddysdk.device.command.DdyDeviceCommandHelper.8
                public void success(String str) {
                    callback.success(str);
                }

                @Override // com.cyjh.ddysdk.device.command.DdyDeviceCommandContract.Callback
                public void failuer(DdyDeviceErrorConstants ddyDeviceErrorConstants, String str) {
                    callback.failuer(ddyDeviceErrorConstants, str);
                }
            });
        } else if (callback != null) {
            callback.failuer(DdyDeviceErrorConstants.DDE_Exception_Param, "error param");
        }
    }

    public void sendPhoneInfo(DdyOrderInfo ddyOrderInfo, final DdyDeviceCommandContract.Callback<String> callback) {
        ResponeDeviceInfo a = m21278a();
        if (Build.VERSION.SDK_INT >= 29) {
            a.msg = "该功能暂不支持安卓10及以上系统";
        }
        amBroadcast(ddyOrderInfo, "com.cyjh.ddy.newdevice.phoneinfo", ShippingInfoWidget.f12563f, C1123f.m21845a(a).replace("\"", "\\\""), new DdyDeviceCommandContract.Callback<String>() { // from class: com.cyjh.ddysdk.device.command.DdyDeviceCommandHelper.9
            public void success(String str) {
                DdyDeviceCommandContract.Callback callback2 = callback;
                if (callback2 != null) {
                    callback2.success(str);
                }
            }

            @Override // com.cyjh.ddysdk.device.command.DdyDeviceCommandContract.Callback
            public void failuer(DdyDeviceErrorConstants ddyDeviceErrorConstants, String str) {
                DdyDeviceCommandContract.Callback callback2 = callback;
                if (callback2 != null) {
                    callback2.failuer(ddyDeviceErrorConstants, str);
                }
            }
        });
    }

    public void screenCapUrl(DdyOrderInfo ddyOrderInfo, long j, long j2, boolean z, DdyDeviceCommandContract.Callback<String> callback) {
        if (ddyOrderInfo instanceof DefineOrderInfo) {
            DefineOrderInfo defineOrderInfo = (DefineOrderInfo) ddyOrderInfo;
            this.f7720c.m21265a(defineOrderInfo.DeviceTcpHost, j, j2, z, defineOrderInfo.DeviceToken, callback);
        } else if (callback != null) {
            callback.failuer(DdyDeviceErrorConstants.DDE_Exception_Param, "error param");
        }
    }

    /* renamed from: a */
    private ResponeDeviceInfo m21278a() {
        ResponeDeviceInfo responeDeviceInfo = new ResponeDeviceInfo();
        responeDeviceInfo.EquipmentType = InformationUtils.m21819a("ro.product.model");
        responeDeviceInfo.Manufacturer = InformationUtils.m21819a("ro.product.brand");
        responeDeviceInfo.SerialNumber = InformationUtils.m21806l();
        responeDeviceInfo.ICCid = InformationUtils.m21820a(Utils.m24103a().getApplicationContext());
        responeDeviceInfo.MobilePhone = InformationUtils.m21807k();
        responeDeviceInfo.IMSI = InformationUtils.m21809i();
        responeDeviceInfo.IMEI = InformationUtils.m21808j();
        responeDeviceInfo.WIFI = InformationUtils.m21811g();
        responeDeviceInfo.WIFIMac = InformationUtils.m21810h();
        return responeDeviceInfo;
    }
}
