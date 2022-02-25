package com.cyjh.ddysdk.device.room;

import com.cyjh.ddy.base.utils.C1123f;
import com.cyjh.ddy.base.utils.CLog;
import com.cyjh.ddy.base.utils.JsonUtil;
import com.cyjh.ddy.media.media.ActionCode;
import com.cyjh.ddy.media.media.listener.IHwyManager;
import com.cyjh.ddy.media.media.listener.IHwySDKListener;
import com.cyjh.ddy.net.bean.base.BaseResultWrapper;
import com.cyjh.ddy.net.inf.IUIDataListener;
import com.cyjh.ddysdk.device.base.constants.DdyDeviceErrorConstants;
import com.cyjh.ddysdk.device.media.DdyDeviceMediaContract;
import com.xuhao.didi.socket.p089a.p090a.p097d.TextUtils;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes.dex */
public class DeviceRoomProxy implements DdyDeviceMediaContract.IDefineRoom, DdyDeviceMediaContract.IRoom {

    /* renamed from: d */
    protected DdyDeviceMediaContract.Callback f7951d;

    /* renamed from: e */
    private IHwyManager f7952e;

    /* renamed from: f */
    private IHwySDKListener f7953f;

    /* renamed from: h */
    private long f7955h;

    /* renamed from: a */
    protected String f7948a = null;

    /* renamed from: b */
    protected String f7949b = null;

    /* renamed from: c */
    protected boolean f7950c = true;

    /* renamed from: g */
    private Set<String> f7954g = new HashSet();

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void m21189a(IHwyManager dVar, IHwySDKListener iHwySDKListener) {
        this.f7952e = dVar;
        this.f7953f = iHwySDKListener;
    }

    /* renamed from: a */
    private boolean m21190a() {
        return !TextUtils.m15142a(this.f7948a) && !TextUtils.m15142a(this.f7949b) && this.f7952e != null;
    }

    /* renamed from: b */
    private void m21185b() {
        this.f7948a = null;
        this.f7949b = null;
        this.f7950c = false;
        this.f7951d = null;
        this.f7952e = null;
    }

    @Override // com.cyjh.ddysdk.device.media.DdyDeviceMediaContract.IRoom
    public void createRoom(long j, String str, String str2, final String str3, final DdyDeviceMediaContract.Callback callback) {
        m21185b();
        new DdyRoomHttpModel().m21179a(j, str, str2, str3, new IUIDataListener() { // from class: com.cyjh.ddysdk.device.room.DeviceRoomProxy.1
            @Override // com.cyjh.ddy.net.inf.IUIDataListener
            public void uiDataSuccess(Object obj) {
                BaseResultWrapper baseResultWrapper = (BaseResultWrapper) obj;
                if (baseResultWrapper == null || baseResultWrapper.code != 1) {
                    callback.failuer(DdyDeviceErrorConstants.DDE_Exception, baseResultWrapper == null ? "" : baseResultWrapper.msg);
                    return;
                }
                RepCreateVideoRoot repCreateVideoRoot = (RepCreateVideoRoot) baseResultWrapper.data;
                DeviceRoomProxy.this.m21186a(repCreateVideoRoot.RoomKey, str3, true);
                callback.success(repCreateVideoRoot.RoomKey);
            }

            @Override // com.cyjh.ddy.net.inf.IUIDataListener
            public void uiDataError(Exception exc) {
                CLog.m21883e("sdk-device", "createRoom " + exc.getMessage());
                callback.failuer(DdyDeviceErrorConstants.DDE_Room_Net_Failure, exc.getMessage());
            }
        });
    }

    @Override // com.cyjh.ddysdk.device.media.DdyDeviceMediaContract.IDefineRoom
    public void createRoom(long j, String str, String str2, String str3, String str4, DdyDeviceMediaContract.Callback callback) {
        m21185b();
        if (TextUtils.m15142a(str3) || TextUtils.m15142a(str4)) {
            callback.failuer(DdyDeviceErrorConstants.DDE_Room_Net_Failure, "roomkey or peoplekey is empty.");
            return;
        }
        this.f7955h = j;
        m21186a(str3, str4, true);
        callback.success(str3);
    }

    @Override // com.cyjh.ddysdk.device.media.DdyDeviceMediaContract.IRoom
    public void visitRoom(String str, final String str2, final DdyDeviceMediaContract.Callback callback) {
        m21185b();
        new DdyRoomHttpModel().m21177b(str, str2, new IUIDataListener() { // from class: com.cyjh.ddysdk.device.room.DeviceRoomProxy.2
            @Override // com.cyjh.ddy.net.inf.IUIDataListener
            public void uiDataSuccess(Object obj) {
                BaseResultWrapper baseResultWrapper = (BaseResultWrapper) obj;
                if (baseResultWrapper == null || baseResultWrapper.code != 1) {
                    callback.failuer(DdyDeviceErrorConstants.DDE_Room_Net_Failure, baseResultWrapper == null ? "" : baseResultWrapper.msg);
                    return;
                }
                RepCreateVideoRoot repCreateVideoRoot = (RepCreateVideoRoot) baseResultWrapper.data;
                DeviceRoomProxy.this.m21186a(repCreateVideoRoot.RoomKey, str2, repCreateVideoRoot.IsRoomMgr);
                callback.success(repCreateVideoRoot);
            }

            @Override // com.cyjh.ddy.net.inf.IUIDataListener
            public void uiDataError(Exception exc) {
                CLog.m21883e("sdk-device", "createRoom " + exc.getMessage());
                callback.failuer(DdyDeviceErrorConstants.DDE_Room_Net_Failure, exc.getMessage());
            }
        });
    }

    @Override // com.cyjh.ddysdk.device.media.DdyDeviceMediaContract.IDefineRoom
    public void visitRoom(String str, String str2, boolean z, DdyDeviceMediaContract.Callback callback) {
        m21185b();
        if (TextUtils.m15142a(str) || TextUtils.m15142a(str2)) {
            callback.failuer(DdyDeviceErrorConstants.DDE_Room_Net_Failure, "roomkey or peoplekey is empty.");
            return;
        }
        m21186a(str, str2, z);
        RepCreateVideoRoot repCreateVideoRoot = new RepCreateVideoRoot();
        repCreateVideoRoot.IsRoomMgr = z;
        repCreateVideoRoot.RoomKey = str;
        repCreateVideoRoot.PeopleKey = str2;
        repCreateVideoRoot.OrderId = this.f7955h;
        callback.success(repCreateVideoRoot);
    }

    @Override // com.cyjh.ddysdk.device.media.DdyDeviceMediaContract.IRoom
    public void destoryRoom(String str, String str2, final DdyDeviceMediaContract.Callback callback) {
        if (this.f7950c) {
            new DdyRoomHttpModel().m21178a(str, str2, new IUIDataListener() { // from class: com.cyjh.ddysdk.device.room.DeviceRoomProxy.3
                @Override // com.cyjh.ddy.net.inf.IUIDataListener
                public void uiDataSuccess(Object obj) {
                    BaseResultWrapper baseResultWrapper = (BaseResultWrapper) obj;
                    if (baseResultWrapper == null || baseResultWrapper.code != 1) {
                        callback.failuer(DdyDeviceErrorConstants.DDE_Room_Net_Failure, baseResultWrapper == null ? "" : baseResultWrapper.msg);
                    } else {
                        callback.success(baseResultWrapper.data);
                    }
                }

                @Override // com.cyjh.ddy.net.inf.IUIDataListener
                public void uiDataError(Exception exc) {
                    CLog.m21883e("sdk-device", "destoryRoom " + exc.getMessage());
                    callback.failuer(DdyDeviceErrorConstants.DDE_Room_Net_Failure, exc.getMessage());
                }
            });
        } else {
            CLog.m21883e("sdk-device", "destoryRoom:" + str2 + "不是房主");
        }
        RoomEventData roomEventData = new RoomEventData();
        roomEventData.f23839org = this.f7949b;
        roomEventData.cmd = "room_destory";
        IHwyManager dVar = this.f7952e;
        if (dVar != null) {
            dVar.mo21658a("msgbroadcast", C1123f.m21845a(roomEventData));
        }
        m21185b();
    }

    @Override // com.cyjh.ddysdk.device.media.DdyDeviceMediaContract.IDefineRoom
    public void destoryRoom(String str, DdyDeviceMediaContract.Callback callback) {
        callback.success("");
        RoomEventData roomEventData = new RoomEventData();
        roomEventData.f23839org = this.f7949b;
        roomEventData.cmd = "room_destory";
        IHwyManager dVar = this.f7952e;
        if (dVar != null) {
            dVar.mo21658a("msgbroadcast", C1123f.m21845a(roomEventData));
        }
        m21185b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m21186a(String str, final String str2, boolean z) {
        this.f7948a = str;
        this.f7949b = str2;
        this.f7950c = z;
        this.f7951d = new DdyDeviceMediaContract.Callback() { // from class: com.cyjh.ddysdk.device.room.DeviceRoomProxy.4
            @Override // com.cyjh.ddysdk.device.media.DdyDeviceMediaContract.Callback
            public void failuer(DdyDeviceErrorConstants ddyDeviceErrorConstants, String str3) {
            }

            @Override // com.cyjh.ddysdk.device.media.DdyDeviceMediaContract.Callback
            public void success(Object obj) {
                String str3 = (String) obj;
                RoomEventData roomEventData = (RoomEventData) JsonUtil.m21799b(str3, RoomEventData.class);
                if (roomEventData != null) {
                    if (DeviceRoomProxy.this.f7950c) {
                        m21182a(roomEventData, str3);
                    } else {
                        m21181b(roomEventData, str3);
                    }
                }
            }

            /* renamed from: a */
            private void m21182a(RoomEventData roomEventData, String str3) {
                if ("room_update".equals(roomEventData.cmd)) {
                    DeviceRoomProxy.this.notifyRoomResUpdate();
                } else if ("room_response_update".equals(roomEventData.cmd)) {
                    DeviceRoomProxy.this.f7954g.add(roomEventData.f23839org);
                } else if ("room_join".equals(roomEventData.cmd)) {
                    DeviceRoomProxy.this.f7954g.add(roomEventData.f23839org);
                    DeviceRoomProxy.this.f7953f.actionCodeCallback(ActionCode.RoomNotice_PEOPLE_CHANGE, str3);
                } else if ("room_exit".equals(roomEventData.cmd)) {
                    DeviceRoomProxy.this.f7954g.remove(roomEventData.f23839org);
                    DeviceRoomProxy.this.f7953f.actionCodeCallback(ActionCode.RoomNotice_PEOPLE_CHANGE, str3);
                } else if ("room_kickwho".equals(roomEventData.cmd)) {
                    DeviceRoomProxy.this.f7954g.remove(roomEventData.dst);
                } else if ("room_authorize".equals(roomEventData.cmd)) {
                    boolean equals = roomEventData.dst.equals(str2);
                    DeviceRoomProxy.this.f7952e.mo21650e(equals);
                    DeviceRoomProxy.this.f7953f.actionCodeCallback(equals ? ActionCode.RoomNotice_CTRL_ON : ActionCode.RoomNotice_CTRL_OFF, str3);
                } else if ("room_authorize_apply".equals(roomEventData.cmd)) {
                    DeviceRoomProxy.this.f7953f.actionCodeCallback(ActionCode.RoomNotice_CTRL_APPLY, roomEventData.f23839org);
                } else if ("room_message".equals(roomEventData.cmd)) {
                    DeviceRoomProxy.this.f7953f.actionCodeCallback(ActionCode.RoomNotice_RECEIVE_MSG, roomEventData.value);
                }
            }

            /* renamed from: b */
            private void m21181b(RoomEventData roomEventData, String str3) {
                if ("room_update".equals(roomEventData.cmd)) {
                    DeviceRoomProxy.this.notifyRoomResUpdate();
                } else if ("room_response_update".equals(roomEventData.cmd)) {
                    DeviceRoomProxy.this.f7954g.add(roomEventData.f23839org);
                } else if ("room_join".equals(roomEventData.cmd)) {
                    DeviceRoomProxy.this.f7954g.add(roomEventData.f23839org);
                    DeviceRoomProxy.this.f7953f.actionCodeCallback(ActionCode.RoomNotice_PEOPLE_CHANGE, str3);
                } else if ("room_exit".equals(roomEventData.cmd)) {
                    DeviceRoomProxy.this.f7954g.remove(roomEventData.f23839org);
                    DeviceRoomProxy.this.f7953f.actionCodeCallback(ActionCode.RoomNotice_PEOPLE_CHANGE, str3);
                } else if ("room_destory".equals(roomEventData.cmd)) {
                    DeviceRoomProxy.this.f7953f.actionCodeCallback(ActionCode.RoomNotice_DESTORY, str3);
                } else if ("room_kickwho".equals(roomEventData.cmd)) {
                    if (roomEventData.dst.equals(str2)) {
                        DeviceRoomProxy.this.f7953f.actionCodeCallback(ActionCode.RoomNotice_KICK, str3);
                    }
                } else if ("room_authorize".equals(roomEventData.cmd)) {
                    DeviceRoomProxy.this.f7952e.mo21650e(roomEventData.dst.equals(str2));
                } else if ("room_message".equals(roomEventData.cmd)) {
                    DeviceRoomProxy.this.f7953f.actionCodeCallback(ActionCode.RoomNotice_RECEIVE_MSG, roomEventData.value);
                }
            }
        };
    }

    @Override // com.cyjh.ddysdk.device.media.DdyDeviceMediaContract.IRoom
    public Set<String> getRoomDetail(boolean z) {
        if (!z) {
            return this.f7954g;
        }
        this.f7954g.clear();
        notifyRoomUpdate();
        return this.f7954g;
    }

    @Override // com.cyjh.ddysdk.device.media.DdyDeviceMediaContract.IRoom
    public void setRoomCtrl(String str) {
        if (!m21190a()) {
            CLog.m21883e("sdk-device", "setRoomCtrl , cannot in room.");
            return;
        }
        RoomEventData roomEventData = new RoomEventData();
        roomEventData.f23839org = this.f7949b;
        roomEventData.dst = str;
        roomEventData.cmd = "room_authorize";
        this.f7952e.mo21658a("msgbroadcast", C1123f.m21845a(roomEventData));
    }

    @Override // com.cyjh.ddysdk.device.media.DdyDeviceMediaContract.IRoom
    public void applyRoomCtrl() {
        if (!m21190a()) {
            CLog.m21883e("sdk-device", "applyRoomCtrl , cannot in room.");
            return;
        }
        RoomEventData roomEventData = new RoomEventData();
        roomEventData.f23839org = this.f7949b;
        roomEventData.dst = "";
        roomEventData.cmd = "room_authorize_apply";
        this.f7952e.mo21658a("msgbroadcast", C1123f.m21845a(roomEventData));
    }

    @Override // com.cyjh.ddysdk.device.media.DdyDeviceMediaContract.IRoom
    public void sendRoomMsg(String str) {
        if (!m21190a()) {
            CLog.m21883e("sdk-device", "sendRoomMsg , cannot in room.");
            return;
        }
        RoomEventData roomEventData = new RoomEventData();
        roomEventData.value = str;
        roomEventData.f23839org = this.f7949b;
        roomEventData.dst = "";
        roomEventData.cmd = "room_message";
        this.f7952e.mo21658a("msgbroadcast", C1123f.m21845a(roomEventData));
    }

    @Override // com.cyjh.ddysdk.device.media.DdyDeviceMediaContract.IRoom
    public void setRoomQuit(String str) {
        if (!m21190a()) {
            CLog.m21883e("sdk-device", "setRoomQuit , cannot in room.");
            return;
        }
        RoomEventData roomEventData = new RoomEventData();
        roomEventData.f23839org = this.f7949b;
        roomEventData.dst = str;
        roomEventData.cmd = "room_kickwho";
        this.f7952e.mo21658a("msgbroadcast", C1123f.m21845a(roomEventData));
    }

    public void notifyRoomJoin() {
        if (!m21190a()) {
            CLog.m21883e("sdk-device", "notifyRoomJoin , cannot in room.");
            return;
        }
        RoomEventData roomEventData = new RoomEventData();
        roomEventData.f23839org = this.f7949b;
        roomEventData.cmd = "room_join";
        this.f7952e.mo21658a("msgbroadcast", C1123f.m21845a(roomEventData));
    }

    public void notifyRoomExit() {
        if (!m21190a()) {
            CLog.m21883e("sdk-device", "notifyRoomExit , cannot in room.");
            return;
        }
        RoomEventData roomEventData = new RoomEventData();
        roomEventData.f23839org = this.f7949b;
        roomEventData.cmd = "room_exit";
        this.f7952e.mo21658a("msgbroadcast", C1123f.m21845a(roomEventData));
    }

    public void notifyRoomUpdate() {
        if (!m21190a()) {
            CLog.m21883e("sdk-device", "notifyRoomUpdate , cannot in room.");
            return;
        }
        RoomEventData roomEventData = new RoomEventData();
        roomEventData.f23839org = this.f7949b;
        roomEventData.cmd = "room_update";
        this.f7952e.mo21658a("msgbroadcast", C1123f.m21845a(roomEventData));
    }

    public void notifyRoomResUpdate() {
        if (!m21190a()) {
            CLog.m21883e("sdk-device", "notifyRoomResUpdate , cannot in room.");
            return;
        }
        RoomEventData roomEventData = new RoomEventData();
        roomEventData.f23839org = this.f7949b;
        roomEventData.cmd = "room_response_update";
        this.f7952e.mo21658a("msgbroadcast", C1123f.m21845a(roomEventData));
    }
}
