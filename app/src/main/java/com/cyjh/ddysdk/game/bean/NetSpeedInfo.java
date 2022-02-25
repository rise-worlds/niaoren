package com.cyjh.ddysdk.game.bean;

import com.blankj.utilcode.util.ConvertUtils;
import com.cyjh.ddy.base.p033a.NoProGuard;
import java.io.Serializable;
import p110z1.NotNull;

/* loaded from: classes.dex */
public class NetSpeedInfo implements NoProGuard, Serializable {

    /* renamed from: ip */
    public String f8024ip;
    public boolean isUpload;
    public long netSpeed;
    public long totalBytes;

    public NetSpeedInfo(String str, boolean z, long j, long j2) {
        this.f8024ip = str;
        this.isUpload = z;
        this.netSpeed = j;
        this.totalBytes = j2;
    }

    @NotNull
    public String toString() {
        String concat = "节点IP：".concat(this.f8024ip).concat(",类型：").concat(this.isUpload ? "上传" : "下载");
        String concat2 = concat.concat(",速度：" + this.netSpeed + "kb/s");
        String concat3 = concat2.concat("，带宽：" + ((this.netSpeed * 8) / 1024) + "M");
        return concat3.concat(",总bytes数:" + ConvertUtils.m22470a(this.totalBytes));
    }
}
