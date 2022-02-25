package com.cyjh.ddysdk.device.bean;

/* loaded from: classes.dex */
public class PingData {
    public String avg;
    public String max;
    public String mdev;
    public String min;

    public PingData(String str) {
        String[] split = str.split("/");
        if (split.length == 4) {
            this.min = split[0];
            this.avg = split[1];
            this.max = split[2];
            this.mdev = split[3];
            return;
        }
        this.min = "?";
        this.avg = "?";
        this.max = "?";
        this.mdev = "?";
    }
}
