package com.cyjh.ddysdk.order.base.bean;

import p110z1.C4963cj;

/* loaded from: classes.dex */
public class DdyOrderStatusAlterRespone {
    public String AdbIntranetIp;
    public String AdbUrl;
    public String ChangeDeviceUrl;
    public String DeviceHost;
    public String DeviceSigner;
    public String DeviceTcpHost;
    public String HDIPArg;
    public String NodeId;
    public int OrderId;
    public String OtherParam;
    public String PhoneName;
    public String ProxyUrl;
    public String PullStreamParam;
    public String PullStreamTcpUrl;
    public String PullStreamUrl;
    public String SDIPArg;
    public int ServerType;
    public int TransportMode;
    public int YunDeviceType;
    public boolean isH265;

    public DdyOrderStatusAlterRespone() {
    }

    public DdyOrderStatusAlterRespone(String str) {
        if (str.contains(C4963cj.f20745b) && str.contains("ws:")) {
            this.DeviceHost = str.substring(0, str.indexOf(C4963cj.f20745b));
            this.PullStreamUrl = str.substring(str.indexOf(C4963cj.f20745b) + 1);
        }
    }

    public DdyOrderStatusAlterRespone(String str, String str2) {
        this.DeviceHost = str;
        this.PullStreamUrl = str2;
    }

    public DdyOrderStatusAlterRespone(DdyOrderStatusAlterRespone ddyOrderStatusAlterRespone) {
        this.DeviceHost = ddyOrderStatusAlterRespone.DeviceHost;
        this.PullStreamUrl = ddyOrderStatusAlterRespone.PullStreamUrl;
        this.AdbUrl = ddyOrderStatusAlterRespone.AdbUrl;
        this.DeviceTcpHost = ddyOrderStatusAlterRespone.DeviceTcpHost;
        this.PullStreamTcpUrl = ddyOrderStatusAlterRespone.PullStreamTcpUrl;
        this.NodeId = ddyOrderStatusAlterRespone.NodeId;
        this.YunDeviceType = ddyOrderStatusAlterRespone.YunDeviceType;
        this.AdbIntranetIp = ddyOrderStatusAlterRespone.AdbIntranetIp;
        this.OrderId = ddyOrderStatusAlterRespone.OrderId;
        this.PhoneName = ddyOrderStatusAlterRespone.PhoneName;
        this.DeviceSigner = ddyOrderStatusAlterRespone.DeviceSigner;
        this.HDIPArg = ddyOrderStatusAlterRespone.HDIPArg;
        this.SDIPArg = ddyOrderStatusAlterRespone.SDIPArg;
        this.isH265 = ddyOrderStatusAlterRespone.isH265;
        this.ProxyUrl = ddyOrderStatusAlterRespone.ProxyUrl;
        this.ChangeDeviceUrl = ddyOrderStatusAlterRespone.ChangeDeviceUrl;
    }
}
