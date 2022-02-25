package com.cyjh.ddysdk.ddyobs.model;

import android.text.TextUtils;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.FileUtils;
import com.cyjh.ddy.base.utils.Utils;
import com.cyjh.ddy.thirdlib.lib_hwobs.C1167b;
import com.cyjh.ddy.thirdlib.lib_hwobs.HWYunManager;
import com.cyjh.ddy.thirdlib.lib_hwobs.ObsCert;
import com.cyjh.ddy.thirdlib.lib_hwobs.OnUploadListener;
import com.cyjh.ddy.thirdlib.lib_hwobs.UploadApkInfo;
import com.cyjh.ddysdk.ddyobs.ObsContract;
import com.cyjh.ddysdk.ddyobs.ObsRequestHelper;
import com.cyjh.ddysdk.ddyobs.bean.response.CreatCertResponse;
import com.cyjh.ddysdk.ddyobs.bean.response.FileSyncInfoResponse;
import com.cyjh.ddysdk.ddyobs.bean.response.FilesInfoResponse;
import com.cyjh.ddysdk.ddyobs.bean.response.UploadUserResponeInfo;
import com.cyjh.ddysdk.ddyobs.p039a.ObsUtils;
import com.obs.services.ObsClient;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import p110z1.Consts;

/* renamed from: com.cyjh.ddysdk.ddyobs.model.a */
/* loaded from: classes.dex */
public class ObsFileModel {
    /* renamed from: a */
    public void m21312a(UploadUserResponeInfo uploadUserResponeInfo, String str, final long j, final String str2, final ObsCert obsCert, final ObsContract.UploadCallback<UploadApkInfo> uploadCallback) {
        final UploadApkInfo uploadApkInfo = new UploadApkInfo();
        uploadApkInfo.setUploadTime(System.currentTimeMillis());
        uploadApkInfo.setTaskID(j);
        uploadApkInfo.setPath(str);
        uploadApkInfo.setSize(new File(str).length());
        final String postFolderName = uploadUserResponeInfo.getPostFolderName();
        if (str.endsWith(".apk")) {
            AppUtils.C1013a a = ObsUtils.m21313a(str);
            if (a != null) {
                HWYunManager b = HWYunManager.m21372b();
                String a2 = b.m21380a(C1167b.m21352b(postFolderName) + a.m22879d() + ".apk", obsCert);
                uploadApkInfo.setName(a2);
                uploadApkInfo.setFileName(a2 + Consts.f23430h + FileUtils.m22170z(str));
                uploadApkInfo.setType(1);
                uploadApkInfo.setPackageName(a.m22881c());
                uploadApkInfo.setIcon(a.m22888a());
                uploadApkInfo.setVercode(a.m22876f());
                HWYunManager.m21372b().m21369b(postFolderName, uploadApkInfo, new HWYunManager.ResultCallback2() { // from class: com.cyjh.ddysdk.ddyobs.model.ObsFileModel$1
                    @Override // com.cyjh.ddy.thirdlib.lib_hwobs.HWYunManager.ResultCallback2
                    public void onResult(boolean z, String str3, String str4) {
                        if (z) {
                            uploadApkInfo.setIconUrl(HWYunManager.m21375a(str4, obsCert.endPoint, obsCert.bucketName));
                            HWYunManager.m21372b().m21378a(postFolderName, uploadApkInfo, new HWYunManager.ResultCallback2() { // from class: com.cyjh.ddysdk.ddyobs.model.ObsFileModel$1.1
                                @Override // com.cyjh.ddy.thirdlib.lib_hwobs.HWYunManager.ResultCallback2
                                public void onResult(boolean z2, String str5, String str6) {
                                    if (z2) {
                                        ObsFileModel.this.m21310a(str6, str2, uploadApkInfo, uploadCallback);
                                    } else if (uploadCallback == null) {
                                    } else {
                                        if (HWYunManager.m21372b().m21382a(Long.valueOf(j))) {
                                            uploadCallback.onCancel(j);
                                        } else {
                                            uploadCallback.onFail(j, -2, "apk upload failed");
                                        }
                                    }
                                }
                            }, obsCert);
                            return;
                        }
                        ObsContract.UploadCallback uploadCallback2 = uploadCallback;
                        if (uploadCallback2 != null) {
                            uploadCallback2.onFail(j, -3, "icon upload failed");
                        }
                    }
                }, obsCert);
            } else if (uploadCallback != null) {
                uploadCallback.onFail(j, -1, "apk info error");
            }
        } else {
            HWYunManager b2 = HWYunManager.m21372b();
            String a3 = b2.m21380a(C1167b.m21351c(postFolderName) + FileUtils.m22173x(str), obsCert);
            uploadApkInfo.setName(a3);
            uploadApkInfo.setFileName(a3 + Consts.f23430h + FileUtils.m22170z(str));
            uploadApkInfo.setType(2);
            uploadApkInfo.setPackageName(UploadApkInfo.FILE_PACKAGE_NAME);
            uploadApkInfo.setIconUrl("");
            uploadApkInfo.setIcon(null);
            HWYunManager.m21372b().m21378a(postFolderName, uploadApkInfo, new HWYunManager.ResultCallback2() { // from class: com.cyjh.ddysdk.ddyobs.model.ObsFileModel$2
                @Override // com.cyjh.ddy.thirdlib.lib_hwobs.HWYunManager.ResultCallback2
                public void onResult(boolean z, String str3, String str4) {
                    if (z) {
                        ObsFileModel.this.m21310a(str4, str2, uploadApkInfo, uploadCallback);
                    } else if (uploadCallback == null) {
                    } else {
                        if (HWYunManager.m21372b().m21382a(Long.valueOf(j))) {
                            uploadCallback.onCancel(j);
                        } else {
                            uploadCallback.onFail(j, -4, "file upload failed");
                        }
                    }
                }
            }, obsCert);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m21310a(String str, String str2, final UploadApkInfo uploadApkInfo, final ObsContract.UploadCallback<UploadApkInfo> uploadCallback) {
        uploadApkInfo.setObjectKey(str);
        ObsRequestHelper.m21331a().m21320c(str, str2, new ObsContract.Callback<FileSyncInfoResponse>() { // from class: com.cyjh.ddysdk.ddyobs.model.ObsFileModel$3
            public void onSuccess(FileSyncInfoResponse fileSyncInfoResponse) {
                uploadApkInfo.setFileId(fileSyncInfoResponse.FileId);
                uploadApkInfo.setSyncState(fileSyncInfoResponse.SyncState);
                ObsContract.UploadCallback uploadCallback2 = uploadCallback;
                if (uploadCallback2 != null) {
                    uploadCallback2.onSuccess(uploadApkInfo);
                }
            }

            @Override // com.cyjh.ddysdk.ddyobs.ObsContract.Callback
            public void onFail(int i, String str3) {
                ObsContract.UploadCallback uploadCallback2 = uploadCallback;
                if (uploadCallback2 != null) {
                    uploadCallback2.onFail(uploadApkInfo.getTaskID(), i, "file sync failed");
                }
            }
        });
    }

    /* renamed from: a */
    public static List<UploadApkInfo> m21309a(List<FilesInfoResponse> list, List<UploadApkInfo> list2, CreatCertResponse creatCertResponse) {
        ArrayList arrayList = new ArrayList();
        if (!Utils.m21725a((Collection) list)) {
            ObsCert a = ObsUtils.m21315a(creatCertResponse);
            ObsClient a2 = HWYunManager.m21372b().m21385a(a);
            for (FilesInfoResponse filesInfoResponse : list) {
                String name = FileUtils.m22242a(filesInfoResponse.FilePath).getParentFile().getName();
                UploadApkInfo uploadApkInfo = new UploadApkInfo();
                if (TextUtils.equals(name, "apk") || TextUtils.equals(name, "file")) {
                    if (HWYunManager.m21372b().m21384a(uploadApkInfo, filesInfoResponse.FilePath, a, a2)) {
                        uploadApkInfo.setState(UploadApkInfo.EState.UploadSuccess);
                        uploadApkInfo.setName(FileUtils.m22171y(filesInfoResponse.FileName));
                        uploadApkInfo.setSize(filesInfoResponse.FileSize);
                        uploadApkInfo.setFileName(filesInfoResponse.FileName);
                        uploadApkInfo.setSsize(C1167b.m21356a(filesInfoResponse.FileSize));
                        uploadApkInfo.setObjectKey(filesInfoResponse.FilePath);
                        uploadApkInfo.setFileId(filesInfoResponse.FileId);
                        uploadApkInfo.setSyncState(filesInfoResponse.SyncState);
                        if (list2 == null || filesInfoResponse.SyncState != 1) {
                            arrayList.add(uploadApkInfo);
                        } else {
                            uploadApkInfo.setState(UploadApkInfo.EState.Uploading);
                            list2.add(uploadApkInfo);
                        }
                    }
                }
            }
            HWYunManager.m21372b().m21383a(a2);
            HWYunManager.m21372b().m21373a(arrayList);
        }
        return arrayList;
    }

    public void setOnUploadListener(OnUploadListener onUploadListener) {
        HWYunManager.m21372b().setOnUploadListener(onUploadListener);
    }
}
