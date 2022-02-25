package com.redwas.bean;

import java.io.Serializable;
import java.util.List;

/* loaded from: classes2.dex */
public class RedRequestResultInfo implements Serializable {
    private List<RedConfigResutlInfo> WeChatConfigs;

    public List<RedConfigResutlInfo> getWeChatConfigs() {
        return this.WeChatConfigs;
    }

    public void setWeChatConfigs(List<RedConfigResutlInfo> list) {
        this.WeChatConfigs = list;
    }
}
