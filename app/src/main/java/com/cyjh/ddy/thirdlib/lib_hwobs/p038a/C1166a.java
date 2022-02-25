package com.cyjh.ddy.thirdlib.lib_hwobs.p038a;

import android.content.Context;
import com.blankj.utilcode.util.FileIOUtils;
import com.blankj.utilcode.util.SDCardUtils;
import com.obs.services.ObsClient;
import com.obs.services.model.ListObjectsRequest;
import com.obs.services.model.ObsObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.cyjh.ddy.thirdlib.lib_hwobs.a.a */
/* loaded from: classes.dex */
public class C1166a {

    /* renamed from: a */
    public static final String f7588a = "test.ifengwoo.com";

    /* renamed from: b */
    private ObsClient f7589b;

    /* renamed from: c */
    private void m21358c() {
        if (this.f7589b == null) {
            this.f7589b = new ObsClient("SNCUKCRQWBPARBCQUMXP", "SIVmFto6HKuR7DkjSsbGBfKVByHquZIahysShfVG", "obs.cn-east-2.myhwclouds.com");
        }
    }

    /* renamed from: d */
    private void m21357d() {
        ObsClient obsClient = this.f7589b;
        if (obsClient != null) {
            try {
                obsClient.close();
                this.f7589b = null;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    public String m21362a(Context context) {
        return SDCardUtils.m23409a() ? SDCardUtils.m23408b() : context.getFilesDir().getAbsolutePath();
    }

    /* renamed from: a */
    public String m21361a(Context context, String str) {
        String replace;
        ObsObject object;
        if (!str.endsWith(".tar") || (object = this.f7589b.getObject(f7588a, (replace = str.replace(".tar", ".txt")))) == null) {
            return "";
        }
        String str2 = m21362a(context) + "/" + replace;
        return FileIOUtils.m22285a(str2, object.getObjectContent()) ? FileIOUtils.m22274b(str2) : "";
    }

    /* renamed from: a */
    public List<String> m21360a(String str) {
        ArrayList arrayList = new ArrayList();
        ListObjectsRequest listObjectsRequest = new ListObjectsRequest(f7588a);
        listObjectsRequest.setPrefix(str);
        try {
            for (ObsObject obsObject : this.f7589b.listObjects(listObjectsRequest).getObjects()) {
                if (obsObject.getObjectKey().endsWith(".tar")) {
                    arrayList.add(obsObject.getObjectKey());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    /* renamed from: a */
    public void m21363a() {
        m21358c();
    }

    /* renamed from: b */
    public void m21359b() {
        m21357d();
    }
}
