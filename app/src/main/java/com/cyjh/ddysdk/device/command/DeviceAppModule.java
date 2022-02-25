package com.cyjh.ddysdk.device.command;

import android.text.TextUtils;
import com.blankj.utilcode.util.EncryptUtils;
import com.blankj.utilcode.util.FileUtils;
import com.cyjh.ddy.base.utils.C1123f;
import com.cyjh.ddy.base.utils.CLog;
import com.cyjh.ddy.media.bean.CommandResponseInfo;
import com.cyjh.ddy.media.oksocket.ControlSocket;
import com.cyjh.ddy.media.oksocket.IControlSocketListener;
import com.cyjh.ddy.media.oksocket.SocketHelper;
import com.cyjh.ddy.net.bean.base.BaseHttpReq;
import com.cyjh.ddy.net.bean.base.BaseResultWrapper;
import com.cyjh.ddy.net.helper.ActivityHttpHelper;
import com.cyjh.ddy.net.inf.IUIDataListener;
import com.cyjh.ddy.thirdlib.lib_hwobs.HWYunManager;
import com.cyjh.ddy.thirdlib.lib_hwobs.ObsCert;
import com.cyjh.ddysdk.device.base.constants.DdyConstants;
import com.cyjh.ddysdk.device.base.constants.DdyDeviceErrorConstants;
import com.cyjh.ddysdk.device.bean.AmBroadcastInfo;
import com.cyjh.ddysdk.device.bean.AmStartAppInfo;
import com.cyjh.ddysdk.device.bean.AppInfo;
import com.cyjh.ddysdk.device.bean.InstallAppInfo;
import com.cyjh.ddysdk.device.bean.PresetAppInfo;
import com.cyjh.ddysdk.device.bean.PresetAppRequest;
import com.cyjh.ddysdk.device.bean.RemoteFileInfo;
import com.cyjh.ddysdk.device.bean.RequestNoticeApps;
import com.cyjh.ddysdk.device.bean.ResponseNoticeApps;
import com.cyjh.ddysdk.device.bean.RunAppInfo;
import com.cyjh.ddysdk.device.bean.ScreenCapRequest;
import com.cyjh.ddysdk.device.command.DdyDeviceCommandContract;
import com.cyjh.ddysdk.order.base.constants.OrderConstants;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import p110z1.TypeToken;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.cyjh.ddysdk.device.command.a */
/* loaded from: classes.dex */
public class DeviceAppModule {

    /* renamed from: a */
    private ActivityHttpHelper f7897a;

    /* renamed from: a */
    public void m21257a(String str, final String str2, final String str3, final String str4, final Map<String, String> map, String str5, final DdyDeviceCommandContract.Callback<Integer> callback) {
        new ControlSocket(str, str5, new IControlSocketListener.OnCommandResponseInfoMessageListener() { // from class: com.cyjh.ddysdk.device.command.DeviceAppModule$1
            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onConnected(ControlSocket controlSocket) {
                CLog.m21882i("sdk-device", "requestRunApp onConnected");
                controlSocket.m21533a("runApp", C1123f.m21845a(new RunAppInfo(str2, str3, str4, map)));
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onSended(ControlSocket controlSocket) {
                CLog.m21882i("sdk-device", "requestRunApp onSended");
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.OnCommandResponseInfoMessageListener
            public void onMessage(CommandResponseInfo commandResponseInfo, ControlSocket controlSocket) {
                CLog.m21882i("sdk-device", "requestRunApp onMessage " + commandResponseInfo.command);
                if (commandResponseInfo.command.equals("runApp")) {
                    DdyDeviceCommandContract.Callback callback2 = callback;
                    if (callback2 != null) {
                        callback2.success(1);
                    }
                    controlSocket.m21538a();
                }
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onFailure(ControlSocket controlSocket, String str6) {
                CLog.m21883e("sdk-device", "requestRunApp onFailure");
                DdyDeviceCommandContract.Callback callback2 = callback;
                if (callback2 != null) {
                    callback2.failuer(DdyDeviceErrorConstants.DDE_App_Net_Failure, str6);
                }
                controlSocket.m21538a();
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onClosed(String str6) {
                CLog.m21882i("sdk-device", "requestRunApp onClosed");
            }
        });
    }

    /* renamed from: a */
    public void m21256a(String str, final String str2, final String str3, final String str4, final Map<String, String> map, String str5, final String str6, final List<String> list, final int i, final DdyDeviceCommandContract.Callback<Integer> callback) {
        new ControlSocket(str, str5, new IControlSocketListener.OnCommandResponseInfoMessageListener() { // from class: com.cyjh.ddysdk.device.command.DeviceAppModule$2
            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onConnected(ControlSocket controlSocket) {
                CLog.m21882i("sdk-device", "requestRunAppSave onConnected");
                RunAppInfo runAppInfo = new RunAppInfo(str2, str3, str4, map);
                runAppInfo.savePaths = list;
                runAppInfo.saveMode = i;
                String str7 = str6;
                runAppInfo.gameAccount = str7;
                if (str7 != null) {
                    runAppInfo.frontPath = "YunGameSave/" + str2 + "/" + str6;
                }
                controlSocket.m21533a("runAppSave", C1123f.m21845a(runAppInfo));
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onSended(ControlSocket controlSocket) {
                CLog.m21882i("sdk-device", "requestRunAppSave onSended");
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.OnCommandResponseInfoMessageListener
            public void onMessage(CommandResponseInfo commandResponseInfo, ControlSocket controlSocket) {
                CLog.m21882i("sdk-device", "requestRunAppSave onMessage " + commandResponseInfo.command);
                if (commandResponseInfo.command.equals("runAppSave")) {
                    DdyDeviceCommandContract.Callback callback2 = callback;
                    if (callback2 != null) {
                        callback2.success(1);
                    }
                    controlSocket.m21538a();
                }
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onFailure(ControlSocket controlSocket, String str7) {
                CLog.m21883e("sdk-device", "requestRunAppSave onFailure");
                DdyDeviceCommandContract.Callback callback2 = callback;
                if (callback2 != null) {
                    callback2.failuer(DdyDeviceErrorConstants.DDE_App_Net_Failure, str7);
                }
                controlSocket.m21538a();
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onClosed(String str7) {
                CLog.m21882i("sdk-device", "requestRunAppSave onClosed");
            }
        });
    }

    /* renamed from: a */
    public void m21259a(String str, final String str2, final String str3, String str4, final DdyDeviceCommandContract.Callback<String> callback) {
        new ControlSocket(str, str4, new IControlSocketListener.OnCommandResponseInfoMessageListener() { // from class: com.cyjh.ddysdk.device.command.DeviceAppModule$3
            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onConnected(ControlSocket controlSocket) {
                CLog.m21882i("sdk-device", "requestAmStartApp onConnected");
                controlSocket.m21533a("amStart", C1123f.m21845a(new AmStartAppInfo(str2, str3)));
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onSended(ControlSocket controlSocket) {
                CLog.m21882i("sdk-device", "requestAmStartApp onSended");
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.OnCommandResponseInfoMessageListener
            public void onMessage(CommandResponseInfo commandResponseInfo, ControlSocket controlSocket) {
                CLog.m21882i("sdk-device", "requestAmStartApp onMessage " + commandResponseInfo.command);
                if (commandResponseInfo.command.equals("amStart")) {
                    DdyDeviceCommandContract.Callback callback2 = callback;
                    if (callback2 != null) {
                        callback2.success(commandResponseInfo.data);
                    }
                    controlSocket.m21538a();
                }
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onFailure(ControlSocket controlSocket, String str5) {
                CLog.m21883e("sdk-device", "requestRunApp onFailure");
                DdyDeviceCommandContract.Callback callback2 = callback;
                if (callback2 != null) {
                    callback2.failuer(DdyDeviceErrorConstants.DDE_App_Net_Failure, str5);
                }
                controlSocket.m21538a();
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onClosed(String str5) {
                CLog.m21882i("sdk-device", "requestAmStartApp onClosed");
            }
        });
    }

    /* renamed from: a */
    public void m21258a(String str, final String str2, final String str3, final String str4, String str5, final DdyDeviceCommandContract.Callback<String> callback) {
        new ControlSocket(str, str5, new IControlSocketListener.OnCommandResponseInfoMessageListener() { // from class: com.cyjh.ddysdk.device.command.DeviceAppModule$4
            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onConnected(ControlSocket controlSocket) {
                CLog.m21882i("sdk-device", "requestAmBroadcast onConnected");
                controlSocket.m21533a("amBroadcast", C1123f.m21845a(new AmBroadcastInfo(str2, str3, str4)));
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onSended(ControlSocket controlSocket) {
                CLog.m21882i("sdk-device", "requestAmBroadcast onSended");
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.OnCommandResponseInfoMessageListener
            public void onMessage(CommandResponseInfo commandResponseInfo, ControlSocket controlSocket) {
                CLog.m21882i("sdk-device", "requestAmBroadcast onMessage " + commandResponseInfo.command);
                if (commandResponseInfo.command.equals("amBroadcast")) {
                    DdyDeviceCommandContract.Callback callback2 = callback;
                    if (callback2 != null) {
                        callback2.success(commandResponseInfo.data);
                    }
                    controlSocket.m21538a();
                }
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onFailure(ControlSocket controlSocket, String str6) {
                CLog.m21883e("sdk-device", "requestAmBroadcast onFailure");
                DdyDeviceCommandContract.Callback callback2 = callback;
                if (callback2 != null) {
                    callback2.failuer(DdyDeviceErrorConstants.DDE_App_Net_Failure, str6);
                }
                controlSocket.m21538a();
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onClosed(String str6) {
                CLog.m21882i("sdk-device", "requestAmBroadcast onClosed");
            }
        });
    }

    /* renamed from: b */
    public void m21248b(String str, final String str2, final String str3, final String str4, String str5, final DdyDeviceCommandContract.Callback<String> callback) {
        new ControlSocket(str, str5, new IControlSocketListener.OnCommandResponseInfoMessageListener() { // from class: com.cyjh.ddysdk.device.command.DeviceAppModule$5
            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onConnected(ControlSocket controlSocket) {
                CLog.m21882i("sdk-device", "requestAmStartService onConnected");
                controlSocket.m21533a("amStartService", C1123f.m21845a(new AmBroadcastInfo(str2, str3, str4)));
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onSended(ControlSocket controlSocket) {
                CLog.m21882i("sdk-device", "requestAmStartService onSended");
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.OnCommandResponseInfoMessageListener
            public void onMessage(CommandResponseInfo commandResponseInfo, ControlSocket controlSocket) {
                CLog.m21882i("sdk-device", "requestAmStartService onMessage " + commandResponseInfo.command);
                if (commandResponseInfo.command.equals("amStartService")) {
                    DdyDeviceCommandContract.Callback callback2 = callback;
                    if (callback2 != null) {
                        callback2.success(commandResponseInfo.data);
                    }
                    controlSocket.m21538a();
                }
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onFailure(ControlSocket controlSocket, String str6) {
                CLog.m21883e("sdk-device", "requestAmStartService onFailure");
                DdyDeviceCommandContract.Callback callback2 = callback;
                if (callback2 != null) {
                    callback2.failuer(DdyDeviceErrorConstants.DDE_App_Net_Failure, str6);
                }
                controlSocket.m21538a();
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onClosed(String str6) {
                CLog.m21882i("sdk-device", "requestAmStartService onClosed");
            }
        });
    }

    /* renamed from: a */
    public void m21255a(String str, final String str2, final String str3, final String str4, final boolean z, final String str5, final Map<String, String> map, String str6, final DdyDeviceCommandContract.Callback<Integer> callback) {
        new ControlSocket(str, str6, new IControlSocketListener.OnCommandResponseInfoMessageListener() { // from class: com.cyjh.ddysdk.device.command.DeviceAppModule$6
            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onConnected(ControlSocket controlSocket) {
                CLog.m21882i("sdk-device", "requestInstallApp onConnected");
                controlSocket.m21533a("installApp", C1123f.m21845a(new InstallAppInfo(str2, str3, str4, z, str5, map)));
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onSended(ControlSocket controlSocket) {
                CLog.m21882i("sdk-device", "requestInstallApp onSended");
                if (callback == null) {
                    controlSocket.m21538a();
                }
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.OnCommandResponseInfoMessageListener
            public void onMessage(CommandResponseInfo commandResponseInfo, ControlSocket controlSocket) {
                CLog.m21882i("sdk-device", "requestInstallApp onMessage " + commandResponseInfo.command);
                if (commandResponseInfo.command.equals("installApp")) {
                    if (callback != null) {
                        if (commandResponseInfo.data.equals("2")) {
                            callback.failuer(DdyDeviceErrorConstants.DDE_App_Download_Failure, "");
                        } else if (commandResponseInfo.data.equals(DdyConstants.APP_INSTALL_ERROR)) {
                            callback.failuer(DdyDeviceErrorConstants.DDE_App_Install_Failure, "");
                        } else {
                            callback.success(1);
                        }
                    }
                    controlSocket.m21538a();
                }
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onFailure(ControlSocket controlSocket, String str7) {
                CLog.m21883e("sdk-device", "requestInstallApp onFailure");
                DdyDeviceCommandContract.Callback callback2 = callback;
                if (callback2 != null) {
                    callback2.failuer(DdyDeviceErrorConstants.DDE_App_Net_Failure, str7);
                }
                controlSocket.m21538a();
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onClosed(String str7) {
                CLog.m21882i("sdk-device", "requestInstallApp onClosed");
            }
        });
    }

    /* renamed from: b */
    public void m21247b(String str, final String str2, final String str3, final String str4, final boolean z, final String str5, final Map<String, String> map, String str6, final DdyDeviceCommandContract.Callback<Integer> callback) {
        new ControlSocket(str, str6, new IControlSocketListener.OnCommandResponseInfoMessageListener() { // from class: com.cyjh.ddysdk.device.command.DeviceAppModule$7
            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onConnected(ControlSocket controlSocket) {
                CLog.m21882i("sdk-device", "requestInstallAppTar onConnected");
                controlSocket.m21533a("installAppTar", C1123f.m21845a(new InstallAppInfo(str2, str3, str4, z, str5, map)));
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onSended(ControlSocket controlSocket) {
                CLog.m21882i("sdk-device", "requestInstallAppTar onSended");
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.OnCommandResponseInfoMessageListener
            public void onMessage(CommandResponseInfo commandResponseInfo, ControlSocket controlSocket) {
                CLog.m21882i("sdk-device", "requestInstallAppTar onMessage " + commandResponseInfo.command);
                if (commandResponseInfo.command.equals("installAppTar")) {
                    if (callback != null) {
                        if (commandResponseInfo.data.equals("2")) {
                            callback.failuer(DdyDeviceErrorConstants.DDE_App_Download_Failure, "");
                        } else if (commandResponseInfo.data.equals(DdyConstants.APP_INSTALL_ERROR)) {
                            callback.failuer(DdyDeviceErrorConstants.DDE_App_Install_Failure, "");
                        } else {
                            callback.success(1);
                        }
                    }
                    controlSocket.m21538a();
                }
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onFailure(ControlSocket controlSocket, String str7) {
                CLog.m21883e("sdk-device", "requestInstallAppTar onFailure");
                DdyDeviceCommandContract.Callback callback2 = callback;
                if (callback2 != null) {
                    callback2.failuer(DdyDeviceErrorConstants.DDE_App_Net_Failure, str7);
                }
                controlSocket.m21538a();
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onClosed(String str7) {
                CLog.m21882i("sdk-device", "requestInstallAppTar onClosed");
            }
        });
    }

    /* renamed from: a */
    public void m21260a(String str, final String str2, String str3, final DdyDeviceCommandContract.Callback<String> callback) {
        new ControlSocket(str, str3, new IControlSocketListener.OnCommandResponseInfoMessageListener() { // from class: com.cyjh.ddysdk.device.command.DeviceAppModule$8
            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onConnected(ControlSocket controlSocket) {
                CLog.m21882i("sdk-device", "requestGetInstallState onConnected");
                controlSocket.m21533a("getInstallState", str2);
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onSended(ControlSocket controlSocket) {
                CLog.m21882i("sdk-device", "requestGetInstallState onSended");
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.OnCommandResponseInfoMessageListener
            public void onMessage(CommandResponseInfo commandResponseInfo, ControlSocket controlSocket) {
                CLog.m21882i("sdk-device", "requestGetInstallState onMessage " + commandResponseInfo.command);
                if (commandResponseInfo.command.equals("getInstallState")) {
                    DdyDeviceCommandContract.Callback callback2 = callback;
                    if (callback2 != null) {
                        callback2.success(commandResponseInfo.data);
                    }
                    controlSocket.m21538a();
                }
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onFailure(ControlSocket controlSocket, String str4) {
                CLog.m21883e("sdk-device", "requestGetInstallState onFailure");
                DdyDeviceCommandContract.Callback callback2 = callback;
                if (callback2 != null) {
                    callback2.failuer(DdyDeviceErrorConstants.DDE_App_Net_Failure, str4);
                }
                controlSocket.m21538a();
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onClosed(String str4) {
                CLog.m21882i("sdk-device", "requestGetInstallState onClosed");
            }
        });
    }

    /* renamed from: a */
    public void m21253a(String str, final List<String> list, String str2, final DdyDeviceCommandContract.Callback<Integer> callback) {
        new ControlSocket(str, str2, new IControlSocketListener.OnCommandResponseInfoMessageListener() { // from class: com.cyjh.ddysdk.device.command.DeviceAppModule$9
            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onConnected(ControlSocket controlSocket) {
                CLog.m21882i("sdk-device", "requestUninstallApps onConnected");
                controlSocket.m21533a("uninstallApps", C1123f.m21845a(list));
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onSended(ControlSocket controlSocket) {
                CLog.m21882i("sdk-device", "requestUninstallApps onSended");
                if (callback == null) {
                    controlSocket.m21538a();
                }
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.OnCommandResponseInfoMessageListener
            public void onMessage(CommandResponseInfo commandResponseInfo, ControlSocket controlSocket) {
                CLog.m21882i("sdk-device", "requestUninstallApps onMessage " + commandResponseInfo.command);
                if (commandResponseInfo.command.equals("uninstallApps")) {
                    DdyDeviceCommandContract.Callback callback2 = callback;
                    if (callback2 != null) {
                        callback2.success(1);
                    }
                    controlSocket.m21538a();
                }
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onFailure(ControlSocket controlSocket, String str3) {
                CLog.m21882i("sdk-device", "requestUninstallApps onFailure");
                DdyDeviceCommandContract.Callback callback2 = callback;
                if (callback2 != null) {
                    callback2.failuer(DdyDeviceErrorConstants.DDE_App_Net_Failure, str3);
                }
                controlSocket.m21538a();
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onClosed(String str3) {
                CLog.m21882i("sdk-device", "requestUninstallApps onClosed");
            }
        });
    }

    /* renamed from: a */
    public void m21262a(String str, String str2, final DdyDeviceCommandContract.Callback<String> callback) {
        new ControlSocket(str, str2, new IControlSocketListener.OnCommandResponseInfoMessageListener() { // from class: com.cyjh.ddysdk.device.command.DeviceAppModule$10
            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onConnected(ControlSocket controlSocket) {
                CLog.m21882i("sdk-device", "requestHaveSysEnvironment onConnected");
                controlSocket.m21533a("professionModeHaveSys", "");
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onSended(ControlSocket controlSocket) {
                CLog.m21882i("sdk-device", "requestHaveSysEnvironment onSended");
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.OnCommandResponseInfoMessageListener
            public void onMessage(CommandResponseInfo commandResponseInfo, ControlSocket controlSocket) {
                CLog.m21882i("sdk-device", "requestHaveSysEnvironment onMessage " + commandResponseInfo.command);
                if (commandResponseInfo.command.equals("professionModeHaveSys")) {
                    DdyDeviceCommandContract.Callback callback2 = callback;
                    if (callback2 != null) {
                        callback2.success(commandResponseInfo.data);
                    }
                    controlSocket.m21538a();
                }
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onFailure(ControlSocket controlSocket, String str3) {
                CLog.m21882i("sdk-device", "requestHaveSysEnvironment onFailure");
                DdyDeviceCommandContract.Callback callback2 = callback;
                if (callback2 != null) {
                    callback2.failuer(DdyDeviceErrorConstants.DDE_App_Net_Failure, str3);
                }
                controlSocket.m21538a();
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onClosed(String str3) {
                CLog.m21882i("sdk-device", "requestHaveSysEnvironment onClosed");
            }
        });
    }

    /* renamed from: b */
    public void m21251b(String str, String str2, final DdyDeviceCommandContract.Callback<Integer> callback) {
        new ControlSocket(str, str2, new IControlSocketListener.OnCommandResponseInfoMessageListener() { // from class: com.cyjh.ddysdk.device.command.DeviceAppModule$11
            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onConnected(ControlSocket controlSocket) {
                CLog.m21882i("sdk-device", "requestInstallSysEnvironment onConnected");
                controlSocket.m21533a("professionModeInstallSys", "");
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onSended(ControlSocket controlSocket) {
                CLog.m21882i("sdk-device", "requestInstallSysEnvironment onSended");
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.OnCommandResponseInfoMessageListener
            public void onMessage(CommandResponseInfo commandResponseInfo, ControlSocket controlSocket) {
                CLog.m21882i("sdk-device", "requestInstallSysEnvironment onMessage " + commandResponseInfo.command);
                if (commandResponseInfo.command.equals("professionModeInstallSys")) {
                    if (callback != null) {
                        if ("success".equals(commandResponseInfo.data)) {
                            callback.success(1);
                        } else {
                            callback.failuer(DdyDeviceErrorConstants.DDE_File_Download_Failue, commandResponseInfo.data);
                        }
                    }
                    controlSocket.m21538a();
                }
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onFailure(ControlSocket controlSocket, String str3) {
                CLog.m21882i("sdk-device", "requestInstallSysEnvironment onFailure");
                DdyDeviceCommandContract.Callback callback2 = callback;
                if (callback2 != null) {
                    callback2.failuer(DdyDeviceErrorConstants.DDE_App_Net_Failure, str3);
                }
                controlSocket.m21538a();
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onClosed(String str3) {
                CLog.m21882i("sdk-device", "requestUninstallApps onClosed");
            }
        });
    }

    /* renamed from: b */
    public void m21246b(String str, final List<String> list, String str2, final DdyDeviceCommandContract.Callback<Integer> callback) {
        new ControlSocket(str, str2, new IControlSocketListener.OnCommandResponseInfoMessageListener() { // from class: com.cyjh.ddysdk.device.command.DeviceAppModule$12
            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onConnected(ControlSocket controlSocket) {
                CLog.m21882i("sdk-device", "requestStopApps onConnected");
                controlSocket.m21533a("stopApps", C1123f.m21845a(list));
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onSended(ControlSocket controlSocket) {
                CLog.m21882i("sdk-device", "requestStopApps onSended");
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.OnCommandResponseInfoMessageListener
            public void onMessage(CommandResponseInfo commandResponseInfo, ControlSocket controlSocket) {
                CLog.m21882i("sdk-device", "requestStopApps onMessage " + commandResponseInfo.command);
                if (commandResponseInfo.command.equals("stopApps")) {
                    DdyDeviceCommandContract.Callback callback2 = callback;
                    if (callback2 != null) {
                        callback2.success(1);
                    }
                    controlSocket.m21538a();
                }
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onFailure(ControlSocket controlSocket, String str3) {
                CLog.m21882i("sdk-device", "requestStopApps onFailure");
                DdyDeviceCommandContract.Callback callback2 = callback;
                if (callback2 != null) {
                    callback2.failuer(DdyDeviceErrorConstants.DDE_App_Net_Failure, str3);
                }
                controlSocket.m21538a();
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onClosed(String str3) {
                CLog.m21882i("sdk-device", "requestStopApps onClosed");
            }
        });
    }

    /* renamed from: c */
    public void m21243c(String str, final List<String> list, String str2, final DdyDeviceCommandContract.Callback<Integer> callback) {
        new ControlSocket(str, str2, new IControlSocketListener.OnCommandResponseInfoMessageListener() { // from class: com.cyjh.ddysdk.device.command.DeviceAppModule$13
            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onConnected(ControlSocket controlSocket) {
                CLog.m21882i("sdk-device", "requestClearApps onConnected");
                controlSocket.m21533a("clearApps", C1123f.m21845a(list));
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onSended(ControlSocket controlSocket) {
                CLog.m21882i("sdk-device", "requestClearApps onSended");
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.OnCommandResponseInfoMessageListener
            public void onMessage(CommandResponseInfo commandResponseInfo, ControlSocket controlSocket) {
                CLog.m21882i("sdk-device", "requestClearApps onMessage " + commandResponseInfo.command);
                if (commandResponseInfo.command.equals("clearApps")) {
                    DdyDeviceCommandContract.Callback callback2 = callback;
                    if (callback2 != null) {
                        callback2.success(1);
                    }
                    controlSocket.m21538a();
                }
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onFailure(ControlSocket controlSocket, String str3) {
                CLog.m21882i("sdk-device", "requestClearApps onFailure");
                DdyDeviceCommandContract.Callback callback2 = callback;
                if (callback2 != null) {
                    callback2.failuer(DdyDeviceErrorConstants.DDE_App_Net_Failure, str3);
                }
                controlSocket.m21538a();
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onClosed(String str3) {
                CLog.m21882i("sdk-device", "requestClearApps onClosed");
            }
        });
    }

    /* renamed from: c */
    public void m21245c(String str, String str2, final DdyDeviceCommandContract.Callback<List<AppInfo>> callback) {
        new ControlSocket(str, str2, new IControlSocketListener.OnCommandResponseInfoMessageListener() { // from class: com.cyjh.ddysdk.device.command.DeviceAppModule$14
            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onConnected(ControlSocket controlSocket) {
                CLog.m21882i("sdk-device", "requestGetAppsInfo onConnected");
                controlSocket.m21533a("getAppsInfo", "");
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onSended(ControlSocket controlSocket) {
                CLog.m21882i("sdk-device", "requestGetAppsInfo onSended");
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.OnCommandResponseInfoMessageListener
            public void onMessage(CommandResponseInfo commandResponseInfo, ControlSocket controlSocket) {
                CLog.m21882i("sdk-device", "requestGetAppsInfo onMessage " + commandResponseInfo.command);
                if (commandResponseInfo.command.equals("getAppsInfo")) {
                    List list = null;
                    if (commandResponseInfo.data != null) {
                        list = (List) C1123f.m21841a(commandResponseInfo.data, new TypeToken<List<AppInfo>>() { // from class: com.cyjh.ddysdk.device.command.DeviceAppModule$14.1
                        }.getType());
                    }
                    DdyDeviceCommandContract.Callback callback2 = callback;
                    if (callback2 != null) {
                        callback2.success(list);
                    }
                    controlSocket.m21538a();
                }
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onFailure(ControlSocket controlSocket, String str3) {
                CLog.m21882i("sdk-device", "requestGetAppsInfo onFailure");
                DdyDeviceCommandContract.Callback callback2 = callback;
                if (callback2 != null) {
                    callback2.failuer(DdyDeviceErrorConstants.DDE_App_Net_Failure, str3);
                }
                controlSocket.m21538a();
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onClosed(String str3) {
                CLog.m21882i("sdk-device", "requestGetAppsInfo onClosed");
            }
        });
    }

    /* renamed from: b */
    public void m21249b(String str, final String str2, String str3, final DdyDeviceCommandContract.Callback<Integer> callback) {
        new ControlSocket(str, str3, new IControlSocketListener.OnCommandResponseInfoMessageListener() { // from class: com.cyjh.ddysdk.device.command.DeviceAppModule$15
            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onConnected(ControlSocket controlSocket) {
                CLog.m21882i("sdk-device", "requestSetClipBoard onConnected");
                controlSocket.m21533a("setClipBoard", str2);
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onSended(ControlSocket controlSocket) {
                CLog.m21882i("sdk-device", "requestSetClipBoard onSended");
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.OnCommandResponseInfoMessageListener
            public void onMessage(CommandResponseInfo commandResponseInfo, ControlSocket controlSocket) {
                CLog.m21882i("sdk-device", "requestSetClipBoard onMessage " + commandResponseInfo.command);
                if (commandResponseInfo.command.equals("setClipBoard")) {
                    DdyDeviceCommandContract.Callback callback2 = callback;
                    if (callback2 != null) {
                        callback2.success(1);
                    }
                    controlSocket.m21538a();
                }
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onFailure(ControlSocket controlSocket, String str4) {
                CLog.m21882i("sdk-device", "requestSetClipBoard onFailure");
                DdyDeviceCommandContract.Callback callback2 = callback;
                if (callback2 != null) {
                    callback2.failuer(DdyDeviceErrorConstants.DDE_App_Net_Failure, str4);
                }
                controlSocket.m21538a();
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onClosed(String str4) {
                CLog.m21882i("sdk-device", "requestSetClipBoard onClosed");
            }
        });
    }

    /* renamed from: d */
    public void m21242d(String str, String str2, final DdyDeviceCommandContract.Callback<Boolean> callback) {
        new ControlSocket(str, str2, new IControlSocketListener.OnCommandResponseInfoMessageListener() { // from class: com.cyjh.ddysdk.device.command.DeviceAppModule$16
            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onConnected(ControlSocket controlSocket) {
                CLog.m21882i("sdk-device", "queryRootState onConnected");
                controlSocket.m21533a("queryRootState", "");
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onSended(ControlSocket controlSocket) {
                CLog.m21882i("sdk-device", "queryRootState onSended");
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.OnCommandResponseInfoMessageListener
            public void onMessage(CommandResponseInfo commandResponseInfo, ControlSocket controlSocket) {
                CLog.m21882i("sdk-device", "queryRootState onMessage " + commandResponseInfo.command);
                if (commandResponseInfo.command.equals("queryRootState")) {
                    String str3 = commandResponseInfo.data;
                    DdyDeviceCommandContract.Callback callback2 = callback;
                    if (callback2 != null) {
                        callback2.success(Boolean.valueOf(TextUtils.equals(str3, String.valueOf(true))));
                    }
                    controlSocket.m21538a();
                }
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onFailure(ControlSocket controlSocket, String str3) {
                CLog.m21882i("sdk-device", "queryRootState onFailure");
                DdyDeviceCommandContract.Callback callback2 = callback;
                if (callback2 != null) {
                    callback2.failuer(DdyDeviceErrorConstants.DDE_App_Net_Failure, str3);
                }
                controlSocket.m21538a();
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onClosed(String str3) {
                CLog.m21882i("sdk-device", "queryRootState onClosed");
            }
        });
    }

    /* renamed from: a */
    public void m21266a(String str, final int i, final String str2, final DdyDeviceCommandContract.Callback<Integer> callback) {
        new ControlSocket(str, str2, new IControlSocketListener.OnCommandResponseInfoMessageListener() { // from class: com.cyjh.ddysdk.device.command.DeviceAppModule$17
            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onConnected(ControlSocket controlSocket) {
                CLog.m21882i("sdk-device", "requestInputKeyEvent onConnected");
                controlSocket.m21534a(SocketHelper.m21520a("inputKeyEvent", String.valueOf(i), 3, str2));
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onSended(ControlSocket controlSocket) {
                CLog.m21882i("sdk-device", "requestInputKeyEvent onSended");
                DdyDeviceCommandContract.Callback callback2 = callback;
                if (callback2 != null) {
                    callback2.success(1);
                }
                controlSocket.m21538a();
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.OnCommandResponseInfoMessageListener
            public void onMessage(CommandResponseInfo commandResponseInfo, ControlSocket controlSocket) {
                CLog.m21882i("sdk-device", "requestInputKeyEvent onMessage " + commandResponseInfo.command);
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onFailure(ControlSocket controlSocket, String str3) {
                CLog.m21882i("sdk-device", "requestInputKeyEvent onFailure");
                DdyDeviceCommandContract.Callback callback2 = callback;
                if (callback2 != null) {
                    callback2.failuer(DdyDeviceErrorConstants.DDE_App_Net_Failure, str3);
                }
                controlSocket.m21538a();
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onClosed(String str3) {
                CLog.m21882i("sdk-device", "requestInputKeyEvent onClosed");
            }
        });
    }

    /* renamed from: c */
    public void m21244c(String str, final String str2, final String str3, final DdyDeviceCommandContract.Callback<Integer> callback) {
        new ControlSocket(str, str3, new IControlSocketListener.OnCommandResponseInfoMessageListener() { // from class: com.cyjh.ddysdk.device.command.DeviceAppModule$18
            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onConnected(ControlSocket controlSocket) {
                CLog.m21882i("sdk-device", "requestInputText onConnected");
                controlSocket.m21534a(SocketHelper.m21520a("inputText", str2, 3, str3));
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onSended(ControlSocket controlSocket) {
                CLog.m21882i("sdk-device", "requestInputText onSended");
                DdyDeviceCommandContract.Callback callback2 = callback;
                if (callback2 != null) {
                    callback2.success(1);
                }
                controlSocket.m21538a();
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.OnCommandResponseInfoMessageListener
            public void onMessage(CommandResponseInfo commandResponseInfo, ControlSocket controlSocket) {
                CLog.m21882i("sdk-device", "requestInputText onMessage " + commandResponseInfo.command);
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onFailure(ControlSocket controlSocket, String str4) {
                CLog.m21882i("sdk-device", "requestInputText onFailure");
                DdyDeviceCommandContract.Callback callback2 = callback;
                if (callback2 != null) {
                    callback2.failuer(DdyDeviceErrorConstants.DDE_App_Net_Failure, str4);
                }
                controlSocket.m21538a();
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onClosed(String str4) {
                CLog.m21882i("sdk-device", "requestInputText onClosed");
            }
        });
    }

    /* renamed from: a */
    public void m21261a(String str, String str2, String str3, ObsCert obsCert, String str4, DdyDeviceCommandContract.Callback<Integer> callback) {
        if (!com.xuhao.didi.socket.p089a.p090a.p097d.TextUtils.m15142a(str2) && FileUtils.m22229b(str2) && !com.xuhao.didi.socket.p089a.p090a.p097d.TextUtils.m15142a(FileUtils.m22173x(str2)) && !str2.endsWith("/") && !str2.endsWith("\\")) {
            new Thread(new DeviceAppModule$19(this, str2, str3, str, obsCert, callback, str4)).start();
        } else if (callback != null) {
            callback.failuer(DdyDeviceErrorConstants.DDE_File_Error, "input file error");
        }
    }

    /* renamed from: b */
    public void m21250b(String str, final String str2, String str3, final ObsCert obsCert, String str4, final DdyDeviceCommandContract.Callback<Integer> callback) {
        final String str5;
        if (!com.xuhao.didi.socket.p089a.p090a.p097d.TextUtils.m15142a(FileUtils.m22173x(str2)) && !str2.endsWith("/") && !str2.endsWith("\\")) {
            String x = FileUtils.m22173x(str2);
            if (com.xuhao.didi.socket.p089a.p090a.p097d.TextUtils.m15142a(str3)) {
                str3 = "/sdcard/";
            }
            if (str3.endsWith("/")) {
                str5 = str3 + x;
            } else {
                str5 = str3;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("sdkpull/");
            sb.append(EncryptUtils.m22365b(str + str2 + str5));
            sb.append("/");
            sb.append(x);
            final String sb2 = sb.toString();
            new ControlSocket(str, str4, new IControlSocketListener.OnCommandResponseInfoMessageListener() { // from class: com.cyjh.ddysdk.device.command.DeviceAppModule$20
                @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
                public void onConnected(ControlSocket controlSocket) {
                    CLog.m21882i("sdk-device", "requestPullFile onConnected");
                    controlSocket.m21533a("pullFile", C1123f.m21845a(new RemoteFileInfo(sb2, str2, obsCert)));
                }

                @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
                public void onSended(ControlSocket controlSocket) {
                    CLog.m21882i("sdk-device", "requestPullFile onSended");
                }

                @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.OnCommandResponseInfoMessageListener
                public void onMessage(CommandResponseInfo commandResponseInfo, ControlSocket controlSocket) {
                    CLog.m21882i("sdk-device", "requestPullFile onMessage " + commandResponseInfo.command);
                    if (commandResponseInfo.command.equals("pullFile")) {
                        if (commandResponseInfo.data.equals("success")) {
                            new Thread(new Runnable() { // from class: com.cyjh.ddysdk.device.command.DeviceAppModule$20.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    String a = HWYunManager.m21372b().m21377a(sb2, str5, (HWYunManager.UpdateDownloadedCallBack) null, obsCert);
                                    if (callback == null) {
                                        return;
                                    }
                                    if (a.equals("success")) {
                                        HWYunManager.m21372b().m21379a(sb2, obsCert, false);
                                        callback.success(1);
                                        return;
                                    }
                                    callback.failuer(DdyDeviceErrorConstants.DDE_File_Download_Failue, a);
                                }
                            }).start();
                        } else {
                            DdyDeviceCommandContract.Callback callback2 = callback;
                            if (callback2 != null) {
                                callback2.failuer(DdyDeviceErrorConstants.DDE_File_Upload_Failue, commandResponseInfo.data);
                            }
                        }
                        controlSocket.m21538a();
                    }
                }

                @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
                public void onFailure(ControlSocket controlSocket, String str6) {
                    CLog.m21882i("sdk-device", "requestPullFile onFailure");
                    DdyDeviceCommandContract.Callback callback2 = callback;
                    if (callback2 != null) {
                        callback2.failuer(DdyDeviceErrorConstants.DDE_App_Net_Failure, str6);
                    }
                    controlSocket.m21538a();
                }

                @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
                public void onClosed(String str6) {
                    CLog.m21882i("sdk-device", "requestPullFile onClosed");
                }
            });
        } else if (callback != null) {
            callback.failuer(DdyDeviceErrorConstants.DDE_File_Error, "input file error");
        }
    }

    /* renamed from: e */
    public void m21240e(String str, String str2, final DdyDeviceCommandContract.Callback<String> callback) {
        new ControlSocket(str, str2, new IControlSocketListener.OnCommandResponseInfoMessageListener() { // from class: com.cyjh.ddysdk.device.command.DeviceAppModule$21
            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onConnected(ControlSocket controlSocket) {
                CLog.m21882i("sdk-device", "requestGetTopApp onConnected");
                controlSocket.m21533a("gettopapp", "");
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onSended(ControlSocket controlSocket) {
                CLog.m21882i("sdk-device", "requestGetTopApp onSended");
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.OnCommandResponseInfoMessageListener
            public void onMessage(CommandResponseInfo commandResponseInfo, ControlSocket controlSocket) {
                CLog.m21882i("sdk-device", "requestGetTopApp onMessage " + commandResponseInfo.command);
                if (commandResponseInfo.command.equals("gettopapp")) {
                    DdyDeviceCommandContract.Callback callback2 = callback;
                    if (callback2 != null) {
                        callback2.success(commandResponseInfo.data);
                    }
                    controlSocket.m21538a();
                }
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onFailure(ControlSocket controlSocket, String str3) {
                CLog.m21882i("sdk-device", "requestGetTopApp onFailure");
                DdyDeviceCommandContract.Callback callback2 = callback;
                if (callback2 != null) {
                    callback2.failuer(DdyDeviceErrorConstants.DDE_App_Net_Failure, str3);
                }
                controlSocket.m21538a();
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onClosed(String str3) {
                CLog.m21882i("sdk-device", "requestGetTopApp onClosed");
            }
        });
    }

    /* renamed from: a */
    public void m21265a(String str, final long j, final long j2, final boolean z, String str2, final DdyDeviceCommandContract.Callback<String> callback) {
        new ControlSocket(str, str2, new IControlSocketListener.OnCommandResponseInfoMessageListener() { // from class: com.cyjh.ddysdk.device.command.DeviceAppModule$22
            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onConnected(ControlSocket controlSocket) {
                ScreenCapRequest screenCapRequest = new ScreenCapRequest();
                screenCapRequest.command = "screencap";
                screenCapRequest.data = String.format("%dx%d", Long.valueOf(j), Long.valueOf(j2));
                screenCapRequest.bUploadObs = z;
                screenCapRequest.time = System.currentTimeMillis();
                CLog.m21882i("sdk-device", "requestScreenCap onConnected");
                controlSocket.m21535a((ControlSocket) screenCapRequest, (Class<ControlSocket>) ScreenCapRequest.class);
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onSended(ControlSocket controlSocket) {
                CLog.m21882i("sdk-device", "requestScreenCap onSended");
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.OnCommandResponseInfoMessageListener
            public void onMessage(CommandResponseInfo commandResponseInfo, ControlSocket controlSocket) {
                CLog.m21882i("sdk-device", "requestScreenCap onMessage " + commandResponseInfo.command);
                if (commandResponseInfo.command.equals("screencap")) {
                    DdyDeviceCommandContract.Callback callback2 = callback;
                    if (callback2 != null) {
                        callback2.success(commandResponseInfo.data);
                    }
                    controlSocket.m21538a();
                }
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onFailure(ControlSocket controlSocket, String str3) {
                CLog.m21883e("sdk-device", "requestScreenCap onFailure " + str3);
                DdyDeviceCommandContract.Callback callback2 = callback;
                if (callback2 != null) {
                    callback2.failuer(DdyDeviceErrorConstants.DDE_Screencap_Net_Failure, str3);
                }
                controlSocket.m21538a();
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onClosed(String str3) {
                CLog.m21882i("sdk-device", "requestScreenCap onClosed");
            }
        });
    }

    /* renamed from: a */
    public void m21254a(String str, String str2, final boolean z, final DdyDeviceCommandContract.Callback<List<ResponseNoticeApps>> callback) {
        new ControlSocket(str, str2, new IControlSocketListener.OnCommandResponseInfoMessageListener() { // from class: com.cyjh.ddysdk.device.command.DeviceAppModule$23
            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onConnected(ControlSocket controlSocket) {
                CLog.m21882i("sdk-device", "requestAllApps onConnected");
                RequestNoticeApps requestNoticeApps = new RequestNoticeApps();
                requestNoticeApps.setIsNeedIcon(z);
                controlSocket.m21533a("getNoticeApps", C1123f.m21845a(requestNoticeApps));
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onSended(ControlSocket controlSocket) {
                CLog.m21882i("sdk-device", "requestAllApps onSended");
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.OnCommandResponseInfoMessageListener
            public void onMessage(CommandResponseInfo commandResponseInfo, ControlSocket controlSocket) {
                CLog.m21882i("sdk-device", "requestAllApps onMessage " + commandResponseInfo.command);
                if (commandResponseInfo.command.equals("getNoticeApps")) {
                    List list = null;
                    if (commandResponseInfo.data != null) {
                        list = (List) C1123f.m21841a(commandResponseInfo.data, new TypeToken<List<ResponseNoticeApps>>() { // from class: com.cyjh.ddysdk.device.command.DeviceAppModule$23.1
                        }.getType());
                    }
                    DdyDeviceCommandContract.Callback callback2 = callback;
                    if (callback2 != null) {
                        callback2.success(list);
                    }
                    controlSocket.m21538a();
                }
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onFailure(ControlSocket controlSocket, String str3) {
                CLog.m21882i("sdk-device", "requestAllApps onFailure");
                DdyDeviceCommandContract.Callback callback2 = callback;
                if (callback2 != null) {
                    callback2.failuer(DdyDeviceErrorConstants.DDE_App_Net_Failure, str3);
                }
                controlSocket.m21538a();
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onClosed(String str3) {
                CLog.m21882i("sdk-device", "requestUninstallApps onClosed");
            }
        });
    }

    /* renamed from: d */
    public void m21241d(String str, final List<String> list, String str2, final DdyDeviceCommandContract.Callback<Integer> callback) {
        new ControlSocket(str, str2, new IControlSocketListener.OnCommandResponseInfoMessageListener() { // from class: com.cyjh.ddysdk.device.command.DeviceAppModule$24
            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onConnected(ControlSocket controlSocket) {
                CLog.m21882i("sdk-device", "requestSetNotifyPackages onConnected");
                controlSocket.m21533a("setNotifyPackages", C1123f.m21845a(list));
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onSended(ControlSocket controlSocket) {
                CLog.m21882i("sdk-device", "requestSetNotifyPackages onSended");
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.OnCommandResponseInfoMessageListener
            public void onMessage(CommandResponseInfo commandResponseInfo, ControlSocket controlSocket) {
                CLog.m21882i("sdk-device", "requestSetNotifyPackages onMessage " + commandResponseInfo.command);
                if (commandResponseInfo.command.equals("setNotifyPackages")) {
                    DdyDeviceCommandContract.Callback callback2 = callback;
                    if (callback2 != null) {
                        callback2.success(1);
                    }
                    controlSocket.m21538a();
                }
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onFailure(ControlSocket controlSocket, String str3) {
                CLog.m21882i("sdk-device", "requestSetNotifyPackages onFailure");
                DdyDeviceCommandContract.Callback callback2 = callback;
                if (callback2 != null) {
                    callback2.failuer(DdyDeviceErrorConstants.DDE_App_Net_Failure, str3);
                }
                controlSocket.m21538a();
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onClosed(String str3) {
                CLog.m21882i("sdk-device", "requestSetNotifyPackages onClosed");
            }
        });
    }

    /* renamed from: e */
    public void m21239e(String str, final List<String> list, String str2, final DdyDeviceCommandContract.Callback<Integer> callback) {
        new ControlSocket(str, str2, new IControlSocketListener.OnCommandResponseInfoMessageListener() { // from class: com.cyjh.ddysdk.device.command.DeviceAppModule$25
            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onConnected(ControlSocket controlSocket) {
                CLog.m21882i("sdk-device", "requestAddNotifyPackages onConnected");
                controlSocket.m21533a("addNotifyPackages", C1123f.m21845a(list));
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onSended(ControlSocket controlSocket) {
                CLog.m21882i("sdk-device", "requestAddNotifyPackages onSended");
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.OnCommandResponseInfoMessageListener
            public void onMessage(CommandResponseInfo commandResponseInfo, ControlSocket controlSocket) {
                CLog.m21882i("sdk-device", "requestAddNotifyPackages onMessage " + commandResponseInfo.command);
                if (commandResponseInfo.command.equals("addNotifyPackages")) {
                    DdyDeviceCommandContract.Callback callback2 = callback;
                    if (callback2 != null) {
                        callback2.success(1);
                    }
                    controlSocket.m21538a();
                }
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onFailure(ControlSocket controlSocket, String str3) {
                CLog.m21882i("sdk-device", "requestAddNotifyPackages onFailure");
                DdyDeviceCommandContract.Callback callback2 = callback;
                if (callback2 != null) {
                    callback2.failuer(DdyDeviceErrorConstants.DDE_App_Net_Failure, str3);
                }
                controlSocket.m21538a();
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onClosed(String str3) {
                CLog.m21882i("sdk-device", "requestAddNotifyPackages onClosed");
            }
        });
    }

    /* renamed from: f */
    public void m21238f(String str, final List<String> list, String str2, final DdyDeviceCommandContract.Callback<Integer> callback) {
        new ControlSocket(str, str2, new IControlSocketListener.OnCommandResponseInfoMessageListener() { // from class: com.cyjh.ddysdk.device.command.DeviceAppModule$26
            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onConnected(ControlSocket controlSocket) {
                CLog.m21882i("sdk-device", "requestRemoveNotifyPackages onConnected");
                controlSocket.m21533a("removeNotifyPackages", C1123f.m21845a(list));
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onSended(ControlSocket controlSocket) {
                CLog.m21882i("sdk-device", "requestRemoveNotifyPackages onSended");
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.OnCommandResponseInfoMessageListener
            public void onMessage(CommandResponseInfo commandResponseInfo, ControlSocket controlSocket) {
                CLog.m21882i("sdk-device", "requestRemoveNotifyPackages onMessage " + commandResponseInfo.command);
                if (commandResponseInfo.command.equals("removeNotifyPackages")) {
                    DdyDeviceCommandContract.Callback callback2 = callback;
                    if (callback2 != null) {
                        callback2.success(1);
                    }
                    controlSocket.m21538a();
                }
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onFailure(ControlSocket controlSocket, String str3) {
                CLog.m21882i("sdk-device", "requestRemoveNotifyPackages onFailure");
                DdyDeviceCommandContract.Callback callback2 = callback;
                if (callback2 != null) {
                    callback2.failuer(DdyDeviceErrorConstants.DDE_App_Net_Failure, str3);
                }
                controlSocket.m21538a();
            }

            @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
            public void onClosed(String str3) {
                CLog.m21882i("sdk-device", "requestRemoveNotifyPackages onClosed");
            }
        });
    }

    /* renamed from: a */
    public void m21263a(String str, long j, String str2, String str3, DdyDeviceCommandContract.Callback<String> callback) {
        m21264a(str, j, new DeviceAppModule$27(this, callback, str2, str3, new ArrayList(), new ArrayList()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m21252a(ArrayList<Integer> arrayList, int i, ArrayList<Integer> arrayList2, int i2, DdyDeviceCommandContract.Callback<String> callback) {
        if (arrayList.size() == i && arrayList2.size() == i2) {
            int i3 = 0;
            for (int i4 = 0; i4 < arrayList.size(); i4++) {
                i3 += arrayList.get(i4).intValue();
            }
            boolean z = true;
            boolean z2 = i3 == i;
            int i5 = 0;
            for (int i6 = 0; i6 < arrayList2.size(); i6++) {
                i5 += arrayList2.get(i6).intValue();
            }
            if (i5 != i2) {
                z = false;
            }
            if (!z || !z2) {
                CLog.m21882i("sdk-device", "getPresetApp all fail");
                if (callback != null) {
                    callback.failuer(DdyDeviceErrorConstants.DDE_Preset_Exception, "fail");
                    return;
                }
                return;
            }
            CLog.m21882i("sdk-device", "getPresetApp all success");
            if (callback != null) {
                callback.success("success");
            }
        }
    }

    /* renamed from: a */
    private void m21264a(String str, long j, IUIDataListener bVar) {
        try {
            if (this.f7897a == null) {
                this.f7897a = new ActivityHttpHelper(new TypeToken<BaseResultWrapper<PresetAppInfo>>() { // from class: com.cyjh.ddysdk.device.command.DeviceAppModule$28
                });
            }
            PresetAppRequest presetAppRequest = new PresetAppRequest();
            presetAppRequest.OrderId = j;
            presetAppRequest.UCID = str;
            this.f7897a.m21435a(bVar);
            this.f7897a.m21431a(OrderConstants.f8108l, new BaseHttpReq().toMapPrames(presetAppRequest), 30000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
