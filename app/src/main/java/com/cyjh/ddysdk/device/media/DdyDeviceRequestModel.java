package com.cyjh.ddysdk.device.media;

import com.cyjh.ddy.net.bean.base.BaseHttpReq;
import com.cyjh.ddy.net.bean.base.BaseResultWrapper;
import com.cyjh.ddy.net.helper.ActivityHttpHelper;
import com.cyjh.ddy.net.inf.IUIDataListener;
import com.cyjh.ddy.net.utils.DomainUtils;
import com.cyjh.ddysdk.device.bean.AddClientStatDataRequest;
import com.cyjh.ddysdk.device.bean.BlockFrameNumbersBean;
import java.util.List;
import p110z1.TypeToken;

/* renamed from: com.cyjh.ddysdk.device.media.a */
/* loaded from: classes.dex */
public class DdyDeviceRequestModel {

    /* renamed from: a */
    public final String f7943a = DomainUtils.m21397a() + "PlaySDK/AddClientStatData";

    /* renamed from: b */
    private ActivityHttpHelper f7944b;

    /* renamed from: a */
    public void m21191a(String str, String str2, int i, int i2, String str3, long j, int i3, long j2, int i4, List<BlockFrameNumbersBean> list, List<AddClientStatDataRequest.BitRatesBean> list2, int i5, float f, IUIDataListener bVar) {
        try {
            if (this.f7944b == null) {
                this.f7944b = new ActivityHttpHelper(new TypeToken<BaseResultWrapper<Object>>() { // from class: com.cyjh.ddysdk.device.media.DdyDeviceRequestModel$1
                });
            }
            AddClientStatDataRequest addClientStatDataRequest = new AddClientStatDataRequest();
            addClientStatDataRequest.UserToken = str2;
            addClientStatDataRequest.AuthCode = str;
            addClientStatDataRequest.UCID = str3;
            addClientStatDataRequest.OrderId = j;
            addClientStatDataRequest.StartDelay = i3;
            addClientStatDataRequest.TrialDuration = j2;
            addClientStatDataRequest.BlockFrameNumber = i4;
            addClientStatDataRequest.BitRates = list2;
            addClientStatDataRequest.BlockFrameNumbers = list;
            addClientStatDataRequest.AvgBitRate = i5;
            addClientStatDataRequest.AvgFrameRate = i2;
            addClientStatDataRequest.EncodeType = i;
            addClientStatDataRequest.BlockFrameRate = f;
            BaseHttpReq baseHttpReq = new BaseHttpReq();
            this.f7944b.m21435a(bVar);
            this.f7944b.m21431a(this.f7943a, baseHttpReq.toMapPrames(addClientStatDataRequest), 5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
