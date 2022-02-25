package com.cyjh.ddysdk.device.bean;

import com.cyjh.ddy.net.bean.base.BaseRequestInfo;
import java.util.List;

/* loaded from: classes.dex */
public class AddClientStatDataRequest extends BaseRequestInfo {
    public String AuthCode;
    public int AvgBitRate;
    public int AvgFrameRate;
    public List<BitRatesBean> BitRates;
    public int BlockFrameNumber;
    public List<BlockFrameNumbersBean> BlockFrameNumbers;
    public float BlockFrameRate;
    public int EncodeType;
    public long OrderId;
    public int StartDelay;
    public long TrialDuration;

    /* loaded from: classes.dex */
    public static class BitRatesBean {
        public int BitRate;
        public long Duration;
    }
}
