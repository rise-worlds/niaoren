package com.cyjh.ddysdk.game.bean;

import com.cyjh.ddy.net.bean.base.BaseRequestInfo;
import java.util.List;

/* loaded from: classes.dex */
public class GetAuthCoderRequest extends BaseRequestInfo {
    public String AuthCode;
    public int DeviceRegion;
    public List<Integer> DeviceRegions;
    public long GameId;
}
