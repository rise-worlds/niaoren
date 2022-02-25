package com.cyjh.ddysdk.device.command;

import com.cyjh.ddy.base.utils.C1123f;
import com.cyjh.ddy.base.utils.CLog;
import com.cyjh.ddy.media.bean.CommandResponseInfo;
import com.cyjh.ddy.media.oksocket.ControlSocket;
import com.cyjh.ddy.media.oksocket.IControlSocketListener;
import com.cyjh.ddysdk.device.base.constants.DdyDeviceErrorConstants;
import com.cyjh.ddysdk.device.bean.KeyValueInfo;
import com.cyjh.ddysdk.device.command.DdyDeviceCommandContract;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.mail.EmailConstants;

/* renamed from: com.cyjh.ddysdk.device.command.b */
/* loaded from: classes.dex */
class DeviceToolModule {

    /* renamed from: a */
    private Map<String, ControlSocket> f7898a = new HashMap();

    /* renamed from: a */
    public void m21235a(final String str, final String str2, String str3, final DdyDeviceCommandContract.Callback<String> callback) {
        if (!this.f7898a.containsKey(str)) {
            this.f7898a.put(str, new ControlSocket(str, str3, new IControlSocketListener.OnCommandResponseInfoMessageListener() { // from class: com.cyjh.ddysdk.device.command.DeviceToolModule$1
                @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
                public void onClosed(String str4) {
                }

                @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
                public void onSended(ControlSocket controlSocket) {
                }

                @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
                public void onConnected(ControlSocket controlSocket) {
                    try {
                        controlSocket.m21533a("execremotetar", URLEncoder.encode(String.format("{\"remoteurl\":\"%s\",\"localdir\":\"%s\",\"localname\":\"%s\",\"shellname\":\"%s\",\"shellparam\":\"%s\"}", "http://test.ifengwoo.com.obs.myhwclouds.com/DevOps/App/android/JUnit4/new/deviceEnvirCheck.tar", "/data/local/tmp/deviceEnvirCheck", "deviceEnvirCheck.tar", "runTest.sh", str2), EmailConstants.UTF_8));
                    } catch (UnsupportedEncodingException e) {
                        CLog.m21883e("DeviceToolModule", "checkDevice URLEncoder " + e.getMessage());
                    }
                }

                @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.OnCommandResponseInfoMessageListener
                public void onMessage(CommandResponseInfo commandResponseInfo, ControlSocket controlSocket) {
                    Map map;
                    Map map2;
                    Map map3;
                    if (commandResponseInfo.command.equals("execremotetar")) {
                        try {
                            String decode = URLDecoder.decode(commandResponseInfo.data, EmailConstants.UTF_8);
                            CLog.m21882i("DeviceToolModule", "checkDevice " + decode);
                            if (callback != null) {
                                callback.success(decode);
                            }
                        } catch (UnsupportedEncodingException unused) {
                            DdyDeviceCommandContract.Callback callback2 = callback;
                            if (callback2 != null) {
                                callback2.failuer(DdyDeviceErrorConstants.DDE_Tool_Decode_Failue, "decode fail");
                            }
                        }
                        map = DeviceToolModule.this.f7898a;
                        if (map.containsKey(str)) {
                            map2 = DeviceToolModule.this.f7898a;
                            ((ControlSocket) map2.get(str)).m21538a();
                            map3 = DeviceToolModule.this.f7898a;
                            map3.remove(str);
                        }
                    }
                }

                @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
                public void onFailure(ControlSocket controlSocket, String str4) {
                    Map map;
                    Map map2;
                    Map map3;
                    CLog.m21882i("DeviceToolModule", "checkDevice onFailure " + str4);
                    if (!str4.contains("EBADF")) {
                        DdyDeviceCommandContract.Callback callback2 = callback;
                        if (callback2 != null) {
                            callback2.failuer(DdyDeviceErrorConstants.DDE_Tool_Run_Failue, str4);
                        }
                    } else {
                        DdyDeviceCommandContract.Callback callback3 = callback;
                        if (callback3 != null) {
                            callback3.failuer(DdyDeviceErrorConstants.DDE_App_Net_Failure, str4);
                        }
                    }
                    map = DeviceToolModule.this.f7898a;
                    if (map.containsKey(str)) {
                        map2 = DeviceToolModule.this.f7898a;
                        ((ControlSocket) map2.get(str)).m21538a();
                        map3 = DeviceToolModule.this.f7898a;
                        map3.remove(str);
                    }
                }
            }));
        } else if (callback != null) {
            callback.failuer(DdyDeviceErrorConstants.DDE_Tool_Runing, "tool running");
        }
    }

    /* renamed from: a */
    public void m21236a(final String str, String str2, final DdyDeviceCommandContract.Callback<String> callback) {
        if (!this.f7898a.containsKey(str)) {
            this.f7898a.put(str, new ControlSocket(str, str2, new IControlSocketListener.OnCommandResponseInfoMessageListener() { // from class: com.cyjh.ddysdk.device.command.DeviceToolModule$2
                @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
                public void onClosed(String str3) {
                }

                @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
                public void onSended(ControlSocket controlSocket) {
                }

                @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
                public void onConnected(ControlSocket controlSocket) {
                    controlSocket.m21533a("logcat", "");
                }

                @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.OnCommandResponseInfoMessageListener
                public void onMessage(CommandResponseInfo commandResponseInfo, ControlSocket controlSocket) {
                    Map map;
                    Map map2;
                    Map map3;
                    if (commandResponseInfo.command.equals("logcat")) {
                        String replace = commandResponseInfo.data.replace("down.ifengwoo.com", "log.ifengwoo.com");
                        DdyDeviceCommandContract.Callback callback2 = callback;
                        if (callback2 != null) {
                            callback2.success(replace);
                        }
                        map = DeviceToolModule.this.f7898a;
                        if (map.containsKey(str)) {
                            map2 = DeviceToolModule.this.f7898a;
                            ((ControlSocket) map2.get(str)).m21538a();
                            map3 = DeviceToolModule.this.f7898a;
                            map3.remove(str);
                        }
                    }
                }

                @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
                public void onFailure(ControlSocket controlSocket, String str3) {
                    Map map;
                    Map map2;
                    Map map3;
                    CLog.m21882i("DeviceToolModule", "logcatDevice onFailure " + str3);
                    DdyDeviceCommandContract.Callback callback2 = callback;
                    if (callback2 != null) {
                        callback2.failuer(DdyDeviceErrorConstants.DDE_App_Net_Failure, str3);
                    }
                    map = DeviceToolModule.this.f7898a;
                    if (map.containsKey(str)) {
                        map2 = DeviceToolModule.this.f7898a;
                        ((ControlSocket) map2.get(str)).m21538a();
                        map3 = DeviceToolModule.this.f7898a;
                        map3.remove(str);
                    }
                }
            }));
        } else if (callback != null) {
            callback.failuer(DdyDeviceErrorConstants.DDE_Tool_Runing, "tool running");
        }
    }

    /* renamed from: a */
    public void m21234a(final String str, String str2, final Map<String, String> map, final DdyDeviceCommandContract.Callback<String> callback) {
        if (!this.f7898a.containsKey(str)) {
            this.f7898a.put(str, new ControlSocket(str, str2, new IControlSocketListener.OnCommandResponseInfoMessageListener() { // from class: com.cyjh.ddysdk.device.command.DeviceToolModule$3
                @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
                public void onClosed(String str3) {
                }

                @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
                public void onSended(ControlSocket controlSocket) {
                }

                @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
                public void onConnected(ControlSocket controlSocket) {
                    ArrayList arrayList = new ArrayList();
                    for (String str3 : map.keySet()) {
                        KeyValueInfo keyValueInfo = new KeyValueInfo();
                        keyValueInfo.key = str3;
                        keyValueInfo.value = (String) map.get(str3);
                        arrayList.add(keyValueInfo);
                    }
                    controlSocket.m21533a("setprop", C1123f.m21845a(arrayList));
                }

                @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.OnCommandResponseInfoMessageListener
                public void onMessage(CommandResponseInfo commandResponseInfo, ControlSocket controlSocket) {
                    Map map2;
                    Map map3;
                    Map map4;
                    if (commandResponseInfo.command.equals("setprop")) {
                        DdyDeviceCommandContract.Callback callback2 = callback;
                        if (callback2 != null) {
                            callback2.success(commandResponseInfo.data);
                        }
                        map2 = DeviceToolModule.this.f7898a;
                        if (map2.containsKey(str)) {
                            map3 = DeviceToolModule.this.f7898a;
                            ((ControlSocket) map3.get(str)).m21538a();
                            map4 = DeviceToolModule.this.f7898a;
                            map4.remove(str);
                        }
                    }
                }

                @Override // com.cyjh.ddy.media.oksocket.IControlSocketListener.IOnMessageListener
                public void onFailure(ControlSocket controlSocket, String str3) {
                    Map map2;
                    Map map3;
                    Map map4;
                    CLog.m21882i("DeviceToolModule", "AlterDevice onFailure " + str3);
                    DdyDeviceCommandContract.Callback callback2 = callback;
                    if (callback2 != null) {
                        callback2.failuer(DdyDeviceErrorConstants.DDE_App_Net_Failure, str3);
                    }
                    map2 = DeviceToolModule.this.f7898a;
                    if (map2.containsKey(str)) {
                        map3 = DeviceToolModule.this.f7898a;
                        ((ControlSocket) map3.get(str)).m21538a();
                        map4 = DeviceToolModule.this.f7898a;
                        map4.remove(str);
                    }
                }
            }));
        } else if (callback != null) {
            callback.failuer(DdyDeviceErrorConstants.DDE_Tool_Runing, "tool running");
        }
    }
}
