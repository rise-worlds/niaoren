package com.cyjh.ddysdk.game.model;

import com.cyjh.ddy.net.bean.base.BaseHttpReq;
import com.cyjh.ddy.net.bean.base.BaseResultWrapper;
import com.cyjh.ddy.net.helper.ActivityHttpHelper;
import com.cyjh.ddy.net.inf.IUIDataListener;
import com.cyjh.ddysdk.game.bean.ActivateAccountInfo;
import com.cyjh.ddysdk.game.bean.CloudAccountInfo;
import com.cyjh.ddysdk.game.bean.CloudAccountRequest;
import com.cyjh.ddysdk.game.bean.EncodeServerIP;
import com.cyjh.ddysdk.game.bean.GamePlayInfo;
import com.cyjh.ddysdk.game.bean.GetAuthCoderRequest;
import com.cyjh.ddysdk.game.bean.QueueHeartRequest;
import com.cyjh.ddysdk.game.bean.QuitGameRequest;
import com.cyjh.ddysdk.game.p040a.YunGameConstants;
import java.util.List;
import p110z1.TypeToken;

/* renamed from: com.cyjh.ddysdk.game.model.a */
/* loaded from: classes.dex */
public class DdyGameRequestModel {

    /* renamed from: a */
    private ActivityHttpHelper f8032a;

    /* renamed from: b */
    private ActivityHttpHelper f8033b;

    /* renamed from: c */
    private ActivityHttpHelper f8034c;

    /* renamed from: d */
    private ActivityHttpHelper f8035d;

    /* renamed from: e */
    private ActivityHttpHelper f8036e;

    /* renamed from: f */
    private ActivityHttpHelper f8037f;

    /* renamed from: g */
    private ActivityHttpHelper f8038g;

    /* renamed from: a */
    public void m21169a(long j, String str, int i, List<Integer> list, String str2, String str3, IUIDataListener bVar) {
        try {
            if (this.f8032a == null) {
                this.f8032a = new ActivityHttpHelper(new TypeToken<BaseResultWrapper<GamePlayInfo>>() { // from class: com.cyjh.ddysdk.game.model.DdyGameRequestModel$1
                });
            }
            GetAuthCoderRequest getAuthCoderRequest = new GetAuthCoderRequest();
            getAuthCoderRequest.UCID = str2;
            getAuthCoderRequest.GameId = j;
            getAuthCoderRequest.AuthCode = str;
            getAuthCoderRequest.UserToken = str3;
            getAuthCoderRequest.DeviceRegion = i;
            getAuthCoderRequest.DeviceRegions = list;
            BaseHttpReq baseHttpReq = new BaseHttpReq();
            this.f8032a.m21435a(bVar);
            this.f8032a.m21431a(new YunGameConstants().f8015c, baseHttpReq.toMapPrames(getAuthCoderRequest), 5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public void m21164a(String str, String str2, String str3, IUIDataListener bVar) {
        try {
            if (this.f8033b == null) {
                this.f8033b = new ActivityHttpHelper(new TypeToken<BaseResultWrapper<GamePlayInfo>>() { // from class: com.cyjh.ddysdk.game.model.DdyGameRequestModel$2
                });
            }
            QueueHeartRequest queueHeartRequest = new QueueHeartRequest();
            queueHeartRequest.UCID = str2;
            queueHeartRequest.QueueId = str;
            queueHeartRequest.UserToken = str3;
            BaseHttpReq baseHttpReq = new BaseHttpReq();
            this.f8033b.m21435a(bVar);
            this.f8033b.m21431a(new YunGameConstants().f8016d, baseHttpReq.toMapPrames(queueHeartRequest), 30000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: b */
    public void m21163b(String str, String str2, String str3, IUIDataListener bVar) {
        try {
            if (this.f8034c == null) {
                this.f8034c = new ActivityHttpHelper(new TypeToken<BaseResultWrapper>() { // from class: com.cyjh.ddysdk.game.model.DdyGameRequestModel$3
                });
            }
            QueueHeartRequest queueHeartRequest = new QueueHeartRequest();
            queueHeartRequest.UCID = str2;
            queueHeartRequest.QueueId = str;
            queueHeartRequest.UserToken = str3;
            BaseHttpReq baseHttpReq = new BaseHttpReq();
            this.f8034c.m21435a(bVar);
            this.f8034c.m21431a(new YunGameConstants().f8017e, baseHttpReq.toMapPrames(queueHeartRequest), 30000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public void m21168a(String str, long j, long j2, long j3, String str2, String str3, IUIDataListener bVar) {
        try {
            if (this.f8035d == null) {
                this.f8035d = new ActivityHttpHelper(new TypeToken<BaseResultWrapper>() { // from class: com.cyjh.ddysdk.game.model.DdyGameRequestModel$4
                });
            }
            QuitGameRequest quitGameRequest = new QuitGameRequest();
            quitGameRequest.UCID = str2;
            quitGameRequest.AuthCode = str;
            quitGameRequest.FirstFrameTime = j;
            quitGameRequest.AvgPing = j2;
            quitGameRequest.MaxPing = j3;
            BaseHttpReq baseHttpReq = new BaseHttpReq();
            this.f8035d.m21435a(bVar);
            this.f8035d.m21431a(str3, baseHttpReq.toMapPrames(quitGameRequest), 30000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public void m21166a(String str, IUIDataListener bVar) {
        try {
            if (this.f8036e == null) {
                this.f8036e = new ActivityHttpHelper(new TypeToken<BaseResultWrapper<List<EncodeServerIP>>>() { // from class: com.cyjh.ddysdk.game.model.DdyGameRequestModel$5
                });
            }
            QuitGameRequest quitGameRequest = new QuitGameRequest();
            quitGameRequest.UCID = str;
            BaseHttpReq baseHttpReq = new BaseHttpReq();
            this.f8036e.m21435a(bVar);
            this.f8036e.m21431a(new YunGameConstants().f8020h, baseHttpReq.toMapPrames(quitGameRequest), 5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public void m21167a(String str, long j, String str2, String str3, int i, IUIDataListener bVar) {
        try {
            if (this.f8037f == null) {
                this.f8037f = new ActivityHttpHelper(new TypeToken<BaseResultWrapper<CloudAccountInfo>>() { // from class: com.cyjh.ddysdk.game.model.DdyGameRequestModel$6
                });
            }
            CloudAccountRequest cloudAccountRequest = new CloudAccountRequest();
            cloudAccountRequest.UCID = str;
            cloudAccountRequest.OrderId = j;
            cloudAccountRequest.DeviceToken = str3;
            cloudAccountRequest.GamePackageName = str2;
            cloudAccountRequest.TimeoutSeconds = i;
            BaseHttpReq baseHttpReq = new BaseHttpReq();
            this.f8037f.m21435a(bVar);
            this.f8037f.m21431a(new YunGameConstants().f8021i, baseHttpReq.toMapPrames(cloudAccountRequest), 5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public void m21165a(String str, String str2, IUIDataListener bVar) {
        try {
            if (this.f8038g == null) {
                this.f8038g = new ActivityHttpHelper(new TypeToken<BaseResultWrapper<ActivateAccountInfo>>() { // from class: com.cyjh.ddysdk.game.model.DdyGameRequestModel$7
                });
            }
            CloudAccountRequest cloudAccountRequest = new CloudAccountRequest();
            cloudAccountRequest.UCID = str;
            cloudAccountRequest.GamePackageName = str2;
            BaseHttpReq baseHttpReq = new BaseHttpReq();
            this.f8038g.m21435a(bVar);
            this.f8038g.m21431a(new YunGameConstants().f8022j, baseHttpReq.toMapPrames(cloudAccountRequest), 5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
