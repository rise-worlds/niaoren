package com.cyjh.ddysdk.device.room;

import com.cyjh.ddy.net.bean.base.BaseHttpReq;
import com.cyjh.ddy.net.bean.base.BaseResultWrapper;
import com.cyjh.ddy.net.helper.ActivityHttpHelper;
import com.cyjh.ddy.net.inf.IUIDataListener;
import p110z1.TypeToken;

/* renamed from: com.cyjh.ddysdk.device.room.a */
/* loaded from: classes.dex */
public class DdyRoomHttpModel {

    /* renamed from: a */
    public static final int f7967a = 30000;

    /* renamed from: b */
    private ActivityHttpHelper<BaseResultWrapper<Object>> f7968b;

    /* renamed from: c */
    private ActivityHttpHelper<BaseResultWrapper<RepCreateVideoRoot>> f7969c;

    /* renamed from: d */
    private ActivityHttpHelper<BaseResultWrapper<RepCreateVideoRoot>> f7970d;

    /* renamed from: a */
    public void m21180a() {
        ActivityHttpHelper<BaseResultWrapper<Object>> aVar = this.f7968b;
        if (aVar != null) {
            aVar.m21437a();
        }
        ActivityHttpHelper<BaseResultWrapper<RepCreateVideoRoot>> aVar2 = this.f7969c;
        if (aVar2 != null) {
            aVar2.m21437a();
        }
        ActivityHttpHelper<BaseResultWrapper<RepCreateVideoRoot>> aVar3 = this.f7970d;
        if (aVar3 != null) {
            aVar3.m21437a();
        }
    }

    /* renamed from: a */
    public void m21178a(String str, String str2, IUIDataListener bVar) {
        try {
            if (this.f7968b == null) {
                this.f7968b = new ActivityHttpHelper<>(new TypeToken<BaseResultWrapper<Object>>() { // from class: com.cyjh.ddysdk.device.room.DdyRoomHttpModel$1
                });
            }
            ReqDisMissVideoRoomInfo reqDisMissVideoRoomInfo = new ReqDisMissVideoRoomInfo();
            reqDisMissVideoRoomInfo.RoomKey = str;
            reqDisMissVideoRoomInfo.PeopleKey = str2;
            BaseHttpReq baseHttpReq = new BaseHttpReq();
            this.f7968b.m21435a(bVar);
            this.f7968b.m21431a(new RoomHttpConstants().f7972b, baseHttpReq.toMapPrames(reqDisMissVideoRoomInfo), 30000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public void m21179a(long j, String str, String str2, String str3, IUIDataListener bVar) {
        try {
            if (this.f7969c == null) {
                this.f7969c = new ActivityHttpHelper<>(new TypeToken<BaseResultWrapper<RepCreateVideoRoot>>() { // from class: com.cyjh.ddysdk.device.room.DdyRoomHttpModel$2
                });
            }
            ReqCreateVideoRoomInfo reqCreateVideoRoomInfo = new ReqCreateVideoRoomInfo();
            reqCreateVideoRoomInfo.DevToken = str2;
            reqCreateVideoRoomInfo.OrderID = j;
            reqCreateVideoRoomInfo.UCID = str;
            reqCreateVideoRoomInfo.PeopleKey = str3;
            BaseHttpReq baseHttpReq = new BaseHttpReq();
            this.f7969c.m21435a(bVar);
            this.f7969c.m21431a(new RoomHttpConstants().f7971a, baseHttpReq.toMapPrames(reqCreateVideoRoomInfo), 30000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: b */
    public void m21177b(String str, String str2, IUIDataListener bVar) {
        try {
            if (this.f7970d == null) {
                this.f7970d = new ActivityHttpHelper<>(new TypeToken<BaseResultWrapper<RepCreateVideoRoot>>() { // from class: com.cyjh.ddysdk.device.room.DdyRoomHttpModel$3
                });
            }
            ReqEnterVideoRoomInfo reqEnterVideoRoomInfo = new ReqEnterVideoRoomInfo();
            reqEnterVideoRoomInfo.RoomKey = str;
            reqEnterVideoRoomInfo.PeopleKey = str2;
            BaseHttpReq baseHttpReq = new BaseHttpReq();
            this.f7970d.m21435a(bVar);
            this.f7970d.m21431a(new RoomHttpConstants().f7973c, baseHttpReq.toMapPrames(reqEnterVideoRoomInfo), 30000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
