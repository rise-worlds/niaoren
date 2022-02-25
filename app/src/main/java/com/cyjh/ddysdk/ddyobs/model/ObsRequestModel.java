package com.cyjh.ddysdk.ddyobs.model;

import com.cyjh.ddy.net.bean.base.BaseHttpReq;
import com.cyjh.ddy.net.bean.base.BaseRequestInfo;
import com.cyjh.ddy.net.bean.base.BaseResultWrapper;
import com.cyjh.ddy.net.helper.ActivityHttpHelper;
import com.cyjh.ddy.net.inf.IUIDataListener;
import com.cyjh.ddysdk.ddyobs.bean.request.CreatCertRequestInfo;
import com.cyjh.ddysdk.ddyobs.bean.request.DeviceOrderRequest;
import com.cyjh.ddysdk.ddyobs.bean.request.FileRequestInfo;
import com.cyjh.ddysdk.ddyobs.bean.request.FileSyncInfoRequestInfo;
import com.cyjh.ddysdk.ddyobs.bean.request.FilesInfoRequestInfo;
import com.cyjh.ddysdk.ddyobs.bean.response.CreatCertResponse;
import com.cyjh.ddysdk.ddyobs.bean.response.DeviceOrderResponse;
import com.cyjh.ddysdk.ddyobs.bean.response.FileSyncInfoResponse;
import com.cyjh.ddysdk.ddyobs.bean.response.FilesInfoResponse;
import com.cyjh.ddysdk.ddyobs.bean.response.UploadUserResponeInfo;
import com.cyjh.ddysdk.ddyobs.constans.ObsHttpConstans;
import java.util.List;
import p110z1.TypeToken;

/* renamed from: com.cyjh.ddysdk.ddyobs.model.b */
/* loaded from: classes.dex */
public class ObsRequestModel {

    /* renamed from: a */
    private ActivityHttpHelper f7670a;

    /* renamed from: b */
    private ActivityHttpHelper f7671b;

    /* renamed from: c */
    private ActivityHttpHelper f7672c;

    /* renamed from: d */
    private ActivityHttpHelper f7673d;

    /* renamed from: e */
    private ActivityHttpHelper f7674e;

    /* renamed from: f */
    private ActivityHttpHelper f7675f;

    /* renamed from: g */
    private ActivityHttpHelper<BaseResultWrapper<UploadUserResponeInfo>> f7676g;

    /* renamed from: a */
    public void m21308a(int i, int i2, String str, String str2, IUIDataListener bVar) {
        try {
            if (this.f7670a == null) {
                this.f7670a = new ActivityHttpHelper(new TypeToken<BaseResultWrapper<CreatCertResponse>>() { // from class: com.cyjh.ddysdk.ddyobs.model.ObsRequestModel$1
                });
            }
            CreatCertRequestInfo creatCertRequestInfo = new CreatCertRequestInfo();
            creatCertRequestInfo.UCID = str2;
            creatCertRequestInfo.OpType = i;
            creatCertRequestInfo.DeviceRegion = i2;
            creatCertRequestInfo.FileName = str;
            BaseHttpReq baseHttpReq = new BaseHttpReq();
            this.f7670a.m21435a(bVar);
            this.f7670a.m21431a(new ObsHttpConstans().f7640b, baseHttpReq.toMapPrames(creatCertRequestInfo), 30000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public void m21305a(String str, String str2, IUIDataListener bVar) {
        try {
            if (this.f7671b == null) {
                this.f7671b = new ActivityHttpHelper(new TypeToken<BaseResultWrapper>() { // from class: com.cyjh.ddysdk.ddyobs.model.ObsRequestModel$2
                });
            }
            FileRequestInfo fileRequestInfo = new FileRequestInfo();
            fileRequestInfo.UCID = str2;
            fileRequestInfo.FileName = str;
            BaseHttpReq baseHttpReq = new BaseHttpReq();
            this.f7671b.m21435a(bVar);
            this.f7671b.m21431a(new ObsHttpConstans().f7641c, baseHttpReq.toMapPrames(fileRequestInfo), 30000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: b */
    public void m21303b(String str, String str2, IUIDataListener bVar) {
        try {
            if (this.f7672c == null) {
                this.f7672c = new ActivityHttpHelper(new TypeToken<BaseResultWrapper<List<FilesInfoResponse>>>() { // from class: com.cyjh.ddysdk.ddyobs.model.ObsRequestModel$3
                });
            }
            FilesInfoRequestInfo filesInfoRequestInfo = new FilesInfoRequestInfo();
            filesInfoRequestInfo.UCID = str2;
            filesInfoRequestInfo.DirPath = str;
            BaseHttpReq baseHttpReq = new BaseHttpReq();
            this.f7672c.m21435a(bVar);
            this.f7672c.m21431a(new ObsHttpConstans().f7642d, baseHttpReq.toMapPrames(filesInfoRequestInfo), 30000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: c */
    public void m21302c(String str, String str2, IUIDataListener bVar) {
        try {
            if (this.f7673d == null) {
                this.f7673d = new ActivityHttpHelper(new TypeToken<BaseResultWrapper<FileSyncInfoResponse>>() { // from class: com.cyjh.ddysdk.ddyobs.model.ObsRequestModel$4
                });
            }
            FileRequestInfo fileRequestInfo = new FileRequestInfo();
            fileRequestInfo.UCID = str2;
            fileRequestInfo.FileName = str;
            BaseHttpReq baseHttpReq = new BaseHttpReq();
            this.f7673d.m21435a(bVar);
            this.f7673d.m21431a(new ObsHttpConstans().f7643e, baseHttpReq.toMapPrames(fileRequestInfo), 30000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public void m21304a(List<String> list, String str, IUIDataListener bVar) {
        try {
            if (this.f7674e == null) {
                this.f7674e = new ActivityHttpHelper(new TypeToken<BaseResultWrapper<List<FileSyncInfoResponse>>>() { // from class: com.cyjh.ddysdk.ddyobs.model.ObsRequestModel$5
                });
            }
            FileSyncInfoRequestInfo fileSyncInfoRequestInfo = new FileSyncInfoRequestInfo();
            fileSyncInfoRequestInfo.UCID = str;
            fileSyncInfoRequestInfo.FileId = list;
            BaseHttpReq baseHttpReq = new BaseHttpReq();
            this.f7674e.m21435a(bVar);
            this.f7674e.m21431a(new ObsHttpConstans().f7644f, baseHttpReq.toMapPrames(fileSyncInfoRequestInfo), 30000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public void m21307a(DeviceOrderRequest deviceOrderRequest, IUIDataListener bVar) {
        try {
            if (this.f7675f == null) {
                this.f7675f = new ActivityHttpHelper(new TypeToken<BaseResultWrapper<List<DeviceOrderResponse>>>() { // from class: com.cyjh.ddysdk.ddyobs.model.ObsRequestModel$6
                });
            }
            BaseHttpReq baseHttpReq = new BaseHttpReq();
            this.f7675f.m21435a(bVar);
            this.f7675f.m21431a(new ObsHttpConstans().f7645g, baseHttpReq.toMapPrames(deviceOrderRequest), 30000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public void m21306a(String str, IUIDataListener bVar) {
        try {
            if (this.f7676g == null) {
                this.f7676g = new ActivityHttpHelper<>(new TypeToken<BaseResultWrapper<UploadUserResponeInfo>>() { // from class: com.cyjh.ddysdk.ddyobs.model.ObsRequestModel$7
                });
            }
            BaseRequestInfo baseRequestInfo = new BaseRequestInfo();
            baseRequestInfo.UCID = str;
            BaseHttpReq baseHttpReq = new BaseHttpReq();
            this.f7676g.m21435a(bVar);
            this.f7676g.m21431a(new ObsHttpConstans().f7646h, baseHttpReq.toMapPrames(baseRequestInfo), 30000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
