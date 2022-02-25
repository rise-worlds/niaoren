package com.cyjh.ddysdk.ddyobs;

import com.cyjh.ddy.base.p033a.NoProGuard;
import com.cyjh.ddy.thirdlib.lib_hwobs.HWYunManager;
import com.cyjh.ddy.thirdlib.lib_hwobs.ObsCert;
import com.cyjh.ddy.thirdlib.lib_hwobs.OnUploadListener;
import com.cyjh.ddy.thirdlib.lib_hwobs.UploadApkInfo;
import com.cyjh.ddysdk.ddyobs.ObsContract;
import com.cyjh.ddysdk.ddyobs.bean.response.CreatCertResponse;
import com.cyjh.ddysdk.ddyobs.bean.response.FileSyncInfoResponse;
import com.cyjh.ddysdk.ddyobs.bean.response.FilesInfoResponse;
import com.cyjh.ddysdk.ddyobs.bean.response.UploadUserResponeInfo;
import com.cyjh.ddysdk.ddyobs.model.ObsFileModel;
import com.cyjh.ddysdk.ddyobs.p039a.ObsUtils;
import java.io.File;
import java.util.List;

/* loaded from: classes.dex */
public class ObsFileHelper implements NoProGuard {

    /* renamed from: a */
    private ObsFileModel f7597a;

    /* synthetic */ ObsFileHelper(C11711 r1) {
        this();
    }

    private ObsFileHelper() {
        this.f7597a = new ObsFileModel();
    }

    /* loaded from: classes.dex */
    private static class LazyHolder {

        /* renamed from: a */
        private static final ObsFileHelper f7614a = new ObsFileHelper(null);

        private LazyHolder() {
        }
    }

    public static ObsFileHelper getInstance() {
        return LazyHolder.f7614a;
    }

    /* renamed from: com.cyjh.ddysdk.ddyobs.ObsFileHelper$1 */
    /* loaded from: classes.dex */
    class C11711 implements ObsContract.Callback<CreatCertResponse> {

        /* renamed from: a */
        final /* synthetic */ String f7598a;

        /* renamed from: b */
        final /* synthetic */ ObsContract.Callback f7599b;

        C11711(String str, ObsContract.Callback callback) {
            this.f7598a = str;
            this.f7599b = callback;
        }

        public void onSuccess(final CreatCertResponse creatCertResponse) {
            ObsRequestHelper.m21331a().m21321b("", this.f7598a, new ObsContract.Callback<List<FilesInfoResponse>>() { // from class: com.cyjh.ddysdk.ddyobs.ObsFileHelper.1.1
                public void onSuccess(final List<FilesInfoResponse> list) {
                    new Thread(new Runnable() { // from class: com.cyjh.ddysdk.ddyobs.ObsFileHelper.1.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            ObsFileModel unused = ObsFileHelper.this.f7597a;
                            List<UploadApkInfo> a = ObsFileModel.m21309a(list, null, creatCertResponse);
                            if (C11711.this.f7599b != null) {
                                C11711.this.f7599b.onSuccess(a);
                            }
                        }
                    }).start();
                }

                @Override // com.cyjh.ddysdk.ddyobs.ObsContract.Callback
                public void onFail(int i, String str) {
                    C11711.this.f7599b.onFail(i, str);
                }
            });
        }

        @Override // com.cyjh.ddysdk.ddyobs.ObsContract.Callback
        public void onFail(int i, String str) {
            this.f7599b.onFail(i, str);
        }
    }

    public void obsFileList(String str, ObsContract.Callback<List<UploadApkInfo>> callback) {
        ObsRequestHelper.m21331a().m21329a(1, str, (ObsContract.Callback<CreatCertResponse>) new C11711(str, callback));
    }

    public long uploadFile(String str, String str2, ObsContract.UploadCallback<UploadApkInfo> uploadCallback) {
        File file = new File(str);
        if (!file.exists() || !file.canRead()) {
            if (uploadCallback != null) {
                uploadCallback.onFail(0L, 0, "file error");
            }
            return 0L;
        }
        long currentTimeMillis = System.currentTimeMillis();
        ObsRequestHelper.m21331a().m21324a(str2, new C11742(str2, str, currentTimeMillis, uploadCallback));
        return currentTimeMillis;
    }

    /* renamed from: com.cyjh.ddysdk.ddyobs.ObsFileHelper$2 */
    /* loaded from: classes.dex */
    class C11742 implements ObsContract.Callback<UploadUserResponeInfo> {

        /* renamed from: a */
        final /* synthetic */ String f7605a;

        /* renamed from: b */
        final /* synthetic */ String f7606b;

        /* renamed from: c */
        final /* synthetic */ long f7607c;

        /* renamed from: d */
        final /* synthetic */ ObsContract.UploadCallback f7608d;

        C11742(String str, String str2, long j, ObsContract.UploadCallback uploadCallback) {
            this.f7605a = str;
            this.f7606b = str2;
            this.f7607c = j;
            this.f7608d = uploadCallback;
        }

        public void onSuccess(final UploadUserResponeInfo uploadUserResponeInfo) {
            ObsRequestHelper.m21331a().m21329a(2, this.f7605a, new ObsContract.Callback<CreatCertResponse>() { // from class: com.cyjh.ddysdk.ddyobs.ObsFileHelper.2.1
                public void onSuccess(CreatCertResponse creatCertResponse) {
                    final ObsCert a = ObsUtils.m21315a(creatCertResponse);
                    new Thread(new Runnable() { // from class: com.cyjh.ddysdk.ddyobs.ObsFileHelper.2.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            ObsFileHelper.this.f7597a.m21312a(uploadUserResponeInfo, C11742.this.f7606b, C11742.this.f7607c, C11742.this.f7605a, a, C11742.this.f7608d);
                        }
                    }).start();
                }

                @Override // com.cyjh.ddysdk.ddyobs.ObsContract.Callback
                public void onFail(int i, String str) {
                    if (C11742.this.f7608d != null) {
                        C11742.this.f7608d.onFail(C11742.this.f7607c, i, str);
                    }
                }
            });
        }

        @Override // com.cyjh.ddysdk.ddyobs.ObsContract.Callback
        public void onFail(int i, String str) {
            ObsContract.UploadCallback uploadCallback = this.f7608d;
            if (uploadCallback != null) {
                uploadCallback.onFail(this.f7607c, i, str);
            }
        }
    }

    public void setOnUploadListener(OnUploadListener onUploadListener) {
        this.f7597a.setOnUploadListener(onUploadListener);
    }

    public void cancelUpload(long j) {
        HWYunManager.m21372b().m21387a(j);
    }

    public void deleteUploadedFile(String str, String str2, ObsContract.Callback<Integer> callback) {
        ObsRequestHelper.m21331a().m21323a(str, str2, callback);
    }

    public void obsFileSync(String str, String str2, ObsContract.Callback<FileSyncInfoResponse> callback) {
        ObsRequestHelper.m21331a().m21320c(str, str2, callback);
    }

    public void getFileSyncInfo(List<String> list, String str, ObsContract.Callback<List<FileSyncInfoResponse>> callback) {
        ObsRequestHelper.m21331a().m21322a(list, str, callback);
    }
}
