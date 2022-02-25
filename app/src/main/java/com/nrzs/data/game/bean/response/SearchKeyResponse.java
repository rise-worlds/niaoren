package com.nrzs.data.game.bean.response;

import com.nrzs.data.game.bean.DataInfoBean;
import com.nrzs.data.game.bean.HotKeyBean;
import java.util.List;

/* loaded from: classes2.dex */
public class SearchKeyResponse {
    private List<DataInfoBean> DataInfo;
    private List<HotKeyBean> rdata;

    public List<HotKeyBean> getRdata() {
        return this.rdata;
    }

    public void setRdata(List<HotKeyBean> list) {
        this.rdata = list;
    }

    public List<DataInfoBean> getDataInfo() {
        return this.DataInfo;
    }

    public void setDataInfo(List<DataInfoBean> list) {
        this.DataInfo = list;
    }
}
