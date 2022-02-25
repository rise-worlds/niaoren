package com.cyjh.ddy.media.bean;

import com.cyjh.ddy.base.utils.SdkKeyUtil;

/* loaded from: classes.dex */
public class MultiVideoParams {
    public String apptype = SdkKeyUtil.getInstance().getSdkType();
    public int isroommgr;
    public String param;
    public String roomkey;
    public String servermsg;

    public MultiVideoParams(String str, String str2, String str3, int i) {
        this.servermsg = str;
        this.param = str2;
        this.roomkey = str3;
        this.isroommgr = i;
    }
}
