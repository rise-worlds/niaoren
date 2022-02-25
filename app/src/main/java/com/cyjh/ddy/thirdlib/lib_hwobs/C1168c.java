package com.cyjh.ddy.thirdlib.lib_hwobs;

import android.text.TextUtils;
import android.util.Base64;
import com.blankj.utilcode.util.FileUtils;
import com.cyjh.ddy.thirdlib.lib_hwobs.UploadApkInfo;
import com.obs.services.model.ObsObject;

/* renamed from: com.cyjh.ddy.thirdlib.lib_hwobs.c */
/* loaded from: classes.dex */
public class C1168c {

    /* renamed from: a */
    private ObsObject f7590a;

    public C1168c(ObsObject obsObject) {
        this.f7590a = obsObject;
    }

    /* renamed from: a */
    public UploadApkInfo m21347a(String str, String str2) {
        UploadApkInfo uploadApkInfo = new UploadApkInfo();
        uploadApkInfo.setState(UploadApkInfo.EState.UploadSuccess);
        uploadApkInfo.setName(FileUtils.m22171y(this.f7590a.getObjectKey()));
        uploadApkInfo.setPackageName((String) this.f7590a.getMetadata().getUserMetadata(HWYunManager.f7540a));
        uploadApkInfo.setVercode(Integer.valueOf((String) this.f7590a.getMetadata().getUserMetadata(HWYunManager.f7541b)).intValue());
        uploadApkInfo.setSize(this.f7590a.getMetadata().getContentLength().longValue());
        uploadApkInfo.setFileName(FileUtils.m22173x(this.f7590a.getObjectKey()));
        uploadApkInfo.setMd5(new String(Base64.decode(this.f7590a.getMetadata().getContentMd5(), 0)));
        uploadApkInfo.setIconUrl(HWYunManager.m21375a(C1167b.m21349e(this.f7590a.getObjectKey()), str, str2));
        uploadApkInfo.setSsize(C1167b.m21356a(this.f7590a.getMetadata().getContentLength().longValue()));
        String str3 = (String) this.f7590a.getMetadata().getUserMetadata(HWYunManager.f7542c);
        if (!TextUtils.isEmpty(str3)) {
            uploadApkInfo.setUploadTime(Long.valueOf(str3).longValue());
        }
        uploadApkInfo.setObjectKey(this.f7590a.getObjectKey());
        return uploadApkInfo;
    }

    /* renamed from: a */
    public String m21348a() {
        return this.f7590a.getObjectKey();
    }

    /* renamed from: b */
    public long m21346b() {
        return this.f7590a.getMetadata().getContentLength().longValue();
    }

    /* renamed from: c */
    public String m21345c() {
        return (String) this.f7590a.getMetadata().getUserMetadata(HWYunManager.f7540a);
    }

    /* renamed from: d */
    public String m21344d() {
        return this.f7590a.getObjectKey();
    }

    /* renamed from: e */
    public String m21343e() {
        return this.f7590a.getObjectKey();
    }
}
