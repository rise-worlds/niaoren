package com.cyjh.ddysdk.ddyobs.p039a;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.Utils;
import com.cyjh.ddy.thirdlib.lib_hwobs.ObsCert;
import com.cyjh.ddysdk.ddyobs.bean.response.CreatCertResponse;
import java.io.File;

/* renamed from: com.cyjh.ddysdk.ddyobs.a.a */
/* loaded from: classes.dex */
public class ObsUtils {
    /* renamed from: a */
    public static ObsCert m21315a(CreatCertResponse creatCertResponse) {
        ObsCert obsCert = new ObsCert();
        obsCert.f7557ak = creatCertResponse.Token.Access;
        obsCert.f7558sk = creatCertResponse.Token.Secret;
        obsCert.securityToken = creatCertResponse.Token.Securitytoken;
        obsCert.endPoint = creatCertResponse.EndPoint;
        obsCert.bucketName = creatCertResponse.BucketName;
        return obsCert;
    }

    /* renamed from: a */
    public static AppUtils.C1013a m21314a(File file) {
        if (file == null || !file.isFile() || !file.exists()) {
            return null;
        }
        return m21313a(file.getAbsolutePath());
    }

    /* renamed from: a */
    public static AppUtils.C1013a m21313a(String str) {
        PackageManager packageManager;
        PackageInfo packageArchiveInfo;
        if (StringUtils.m23226b(str) || (packageManager = Utils.m24103a().getPackageManager()) == null || (packageArchiveInfo = packageManager.getPackageArchiveInfo(str, 0)) == null) {
            return null;
        }
        ApplicationInfo applicationInfo = packageArchiveInfo.applicationInfo;
        applicationInfo.sourceDir = str;
        applicationInfo.publicSourceDir = str;
        return m21316a(packageManager, packageArchiveInfo);
    }

    /* renamed from: a */
    private static AppUtils.C1013a m21316a(PackageManager packageManager, PackageInfo packageInfo) {
        if (packageInfo == null) {
            return null;
        }
        ApplicationInfo applicationInfo = packageInfo.applicationInfo;
        return new AppUtils.C1013a(packageInfo.packageName, applicationInfo.loadLabel(packageManager).toString(), applicationInfo.loadIcon(packageManager), applicationInfo.sourceDir, packageInfo.versionName, packageInfo.versionCode, (applicationInfo.flags & 1) != 0);
    }
}
