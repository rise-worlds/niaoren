package com.cyjh.ddysdk.device.bean;

import com.cyjh.ddy.thirdlib.lib_hwobs.ObsCert;

/* loaded from: classes.dex */
public class RemoteFileInfo {
    public ObsCert obsCert;
    public String obsKey;
    public String remoteFilePath;

    public RemoteFileInfo(String str, String str2, ObsCert obsCert) {
        this.obsKey = str;
        this.remoteFilePath = str2;
        this.obsCert = obsCert;
    }
}
