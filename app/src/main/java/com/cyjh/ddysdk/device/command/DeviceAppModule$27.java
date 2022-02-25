package com.cyjh.ddysdk.device.command;

import android.text.TextUtils;
import com.cyjh.ddy.base.utils.CLog;
import com.cyjh.ddy.net.bean.base.BaseResultWrapper;
import com.cyjh.ddy.net.inf.IUIDataListener;
import com.cyjh.ddysdk.device.base.constants.DdyConstants;
import com.cyjh.ddysdk.device.base.constants.DdyDeviceErrorConstants;
import com.cyjh.ddysdk.device.bean.PresetAppInfo;
import com.cyjh.ddysdk.device.command.DdyDeviceCommandContract;
import java.util.ArrayList;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class DeviceAppModule$27 implements IUIDataListener {

    /* renamed from: a */
    final /* synthetic */ DdyDeviceCommandContract.Callback f7826a;

    /* renamed from: b */
    final /* synthetic */ String f7827b;

    /* renamed from: c */
    final /* synthetic */ String f7828c;

    /* renamed from: d */
    final /* synthetic */ ArrayList f7829d;

    /* renamed from: e */
    final /* synthetic */ ArrayList f7830e;

    /* renamed from: f */
    final /* synthetic */ DeviceAppModule f7831f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DeviceAppModule$27(DeviceAppModule aVar, DdyDeviceCommandContract.Callback callback, String str, String str2, ArrayList arrayList, ArrayList arrayList2) {
        this.f7831f = aVar;
        this.f7826a = callback;
        this.f7827b = str;
        this.f7828c = str2;
        this.f7829d = arrayList;
        this.f7830e = arrayList2;
    }

    @Override // com.cyjh.ddy.net.inf.IUIDataListener
    public void uiDataSuccess(Object obj) {
        BaseResultWrapper baseResultWrapper = (BaseResultWrapper) obj;
        if (baseResultWrapper == null || baseResultWrapper.code != 1 || baseResultWrapper.data == 0) {
            DdyDeviceCommandContract.Callback callback = this.f7826a;
            if (callback != null) {
                callback.failuer(DdyDeviceErrorConstants.DDE_Preset_Exception, "getPresetApp fail");
                return;
            }
            return;
        }
        new Thread(new RunnableC12091(baseResultWrapper)).start();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.cyjh.ddysdk.device.command.DeviceAppModule$27$1 */
    /* loaded from: classes.dex */
    public class RunnableC12091 implements Runnable {

        /* renamed from: a */
        final /* synthetic */ BaseResultWrapper f7832a;

        RunnableC12091(BaseResultWrapper baseResultWrapper) {
            this.f7832a = baseResultWrapper;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (!(((PresetAppInfo) this.f7832a.data).UnInstallApps == null || ((PresetAppInfo) this.f7832a.data).UnInstallApps.size() == 0)) {
                final ArrayList arrayList = new ArrayList();
                for (PresetAppInfo.UnInstallAppsBean unInstallAppsBean : ((PresetAppInfo) this.f7832a.data).UnInstallApps) {
                    arrayList.add(unInstallAppsBean.AppPackageName);
                    CLog.m21882i("sdk-device", "UnInstallApps " + unInstallAppsBean.AppPackageName);
                }
                DeviceAppModule$27.this.f7831f.m21253a(DeviceAppModule$27.this.f7827b, arrayList, DeviceAppModule$27.this.f7828c, new DdyDeviceCommandContract.Callback<Integer>() { // from class: com.cyjh.ddysdk.device.command.DeviceAppModule.27.1.1
                    public void success(Integer num) {
                        CLog.m21882i("sdk-device", "UnInstallApps " + arrayList + "success");
                        DeviceAppModule$27.this.f7829d.add(1);
                        DeviceAppModule$27.this.f7831f.m21252a(DeviceAppModule$27.this.f7829d, ((PresetAppInfo) RunnableC12091.this.f7832a.data).UnInstallApps.size(), DeviceAppModule$27.this.f7830e, ((PresetAppInfo) RunnableC12091.this.f7832a.data).InstallApps.size(), DeviceAppModule$27.this.f7826a);
                    }

                    @Override // com.cyjh.ddysdk.device.command.DdyDeviceCommandContract.Callback
                    public void failuer(DdyDeviceErrorConstants ddyDeviceErrorConstants, String str) {
                        CLog.m21882i("sdk-device", "UnInstallApps " + arrayList + "fail" + str);
                        DeviceAppModule$27.this.f7829d.add(0);
                        DeviceAppModule$27.this.f7831f.m21252a(DeviceAppModule$27.this.f7829d, ((PresetAppInfo) RunnableC12091.this.f7832a.data).UnInstallApps.size(), DeviceAppModule$27.this.f7830e, ((PresetAppInfo) RunnableC12091.this.f7832a.data).InstallApps.size(), DeviceAppModule$27.this.f7826a);
                    }
                });
                try {
                    Thread.sleep(1000L);
                } catch (Exception unused) {
                }
            }
            if (!(((PresetAppInfo) this.f7832a.data).InstallApps == null || ((PresetAppInfo) this.f7832a.data).InstallApps.size() == 0)) {
                for (final PresetAppInfo.InstallAppsBean installAppsBean : ((PresetAppInfo) this.f7832a.data).InstallApps) {
                    if (!TextUtils.isEmpty(installAppsBean.AppPackageName) && !TextUtils.isEmpty(installAppsBean.AppUrl)) {
                        DeviceAppModule$27.this.f7831f.m21260a(DeviceAppModule$27.this.f7827b, installAppsBean.AppPackageName, DeviceAppModule$27.this.f7828c, new DdyDeviceCommandContract.Callback<String>() { // from class: com.cyjh.ddysdk.device.command.DeviceAppModule.27.1.2
                            public void success(String str) {
                                CLog.m21882i("sdk-device", "InstallApps " + installAppsBean.AppPackageName);
                                if (TextUtils.equals(str, DdyConstants.APP_INSTALL_UNINSTALL)) {
                                    DeviceAppModule$27.this.f7831f.m21255a(DeviceAppModule$27.this.f7827b, installAppsBean.AppUrl, installAppsBean.AppPackageName, "", false, null, null, DeviceAppModule$27.this.f7828c, new DdyDeviceCommandContract.Callback<Integer>() { // from class: com.cyjh.ddysdk.device.command.DeviceAppModule.27.1.2.1
                                        public void success(Integer num) {
                                            CLog.m21882i("sdk-device", "InstallApps " + installAppsBean.AppPackageName + "success");
                                            DeviceAppModule$27.this.f7830e.add(1);
                                            DeviceAppModule$27.this.f7831f.m21252a(DeviceAppModule$27.this.f7829d, ((PresetAppInfo) RunnableC12091.this.f7832a.data).UnInstallApps.size(), DeviceAppModule$27.this.f7830e, ((PresetAppInfo) RunnableC12091.this.f7832a.data).InstallApps.size(), DeviceAppModule$27.this.f7826a);
                                        }

                                        @Override // com.cyjh.ddysdk.device.command.DdyDeviceCommandContract.Callback
                                        public void failuer(DdyDeviceErrorConstants ddyDeviceErrorConstants, String str2) {
                                            CLog.m21882i("sdk-device", "InstallApps " + installAppsBean.AppPackageName + "fail" + str2);
                                            DeviceAppModule$27.this.f7830e.add(0);
                                            DeviceAppModule$27.this.f7831f.m21252a(DeviceAppModule$27.this.f7829d, ((PresetAppInfo) RunnableC12091.this.f7832a.data).UnInstallApps.size(), DeviceAppModule$27.this.f7830e, ((PresetAppInfo) RunnableC12091.this.f7832a.data).InstallApps.size(), DeviceAppModule$27.this.f7826a);
                                        }
                                    });
                                    return;
                                }
                                CLog.m21882i("sdk-device", "InstallApps " + installAppsBean.AppPackageName + "have");
                                DeviceAppModule$27.this.f7830e.add(1);
                                DeviceAppModule$27.this.f7831f.m21252a(DeviceAppModule$27.this.f7829d, ((PresetAppInfo) RunnableC12091.this.f7832a.data).UnInstallApps.size(), DeviceAppModule$27.this.f7830e, ((PresetAppInfo) RunnableC12091.this.f7832a.data).InstallApps.size(), DeviceAppModule$27.this.f7826a);
                            }

                            @Override // com.cyjh.ddysdk.device.command.DdyDeviceCommandContract.Callback
                            public void failuer(DdyDeviceErrorConstants ddyDeviceErrorConstants, String str) {
                                CLog.m21882i("sdk-device", String.format("getInstallState error: %s; msg: %s", ddyDeviceErrorConstants, str));
                                DeviceAppModule$27.this.f7830e.add(0);
                                DeviceAppModule$27.this.f7831f.m21252a(DeviceAppModule$27.this.f7829d, ((PresetAppInfo) RunnableC12091.this.f7832a.data).UnInstallApps.size(), DeviceAppModule$27.this.f7830e, ((PresetAppInfo) RunnableC12091.this.f7832a.data).InstallApps.size(), DeviceAppModule$27.this.f7826a);
                            }
                        });
                        try {
                            Thread.sleep(2000L);
                        } catch (Exception unused2) {
                        }
                    }
                }
            }
        }
    }

    @Override // com.cyjh.ddy.net.inf.IUIDataListener
    public void uiDataError(Exception exc) {
        CLog.m21882i("sdk-device", "getPresetApp error: " + exc.toString());
        DdyDeviceCommandContract.Callback callback = this.f7826a;
        if (callback != null) {
            callback.failuer(DdyDeviceErrorConstants.DDE_Preset_Exception, "getPresetApp fail");
        }
    }
}
