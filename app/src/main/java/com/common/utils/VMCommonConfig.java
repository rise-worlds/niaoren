package com.common.utils;

import com.lbd.p054xj.app.XJApp;
import p110z1.ChannelUtil;
import p110z1.GeneralUtil;
import p110z1.aes;

/* loaded from: classes.dex */
public class VMCommonConfig {
    public static void initValue() {
        VMProperUtil.writeDateToLocalFile(VMProperUtil.ISBOOTED, "true");
        VMProperUtil.writeDateToLocalFile(VMProperUtil.VMUUID, DeviceUtil.getUUID());
        VMProperUtil.writeDateToLocalFile(VMProperUtil.APPCHANNEL, ChannelUtil.m14203a(XJApp.getInstance().getApplicationContext()));
        VMProperUtil.writeDateToLocalFile(VMProperUtil.APPVERSIONCODE, GeneralUtil.m13993i(XJApp.getInstance().getApplicationContext()) + "");
        VMProperUtil.writeDateToLocalFile(VMProperUtil.APPVERSIONNNAME, aes.m13847e(XJApp.getInstance().getApplicationContext()) + "");
        StringBuilder sb = new StringBuilder();
        sb.append("");
        VMProperUtil.writeDateToLocalFile(VMProperUtil.ROMVERSION, sb.toString());
        VMProperUtil.writeDateToLocalFile(VMProperUtil.APPISTESTREQUEST, "");
        VMProperUtil.writeDateToLocalFile(VMProperUtil.APPISTESTMODEL, "false");
        VMProperUtil.writeDateToLocalFile(VMProperUtil.APPACCESSTOKEN, "");
    }
}
