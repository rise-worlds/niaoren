package com.cyjh.ddy.thirdlib.lib_hwobs;

import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.blankj.utilcode.util.FileUtils;
import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import com.cyjh.ddy.thirdlib.lib_hwobs.UploadFileInputStream;
import com.obs.services.ObsClient;
import com.obs.services.model.AccessControlList;
import com.obs.services.model.DeleteObjectResult;
import com.obs.services.model.ListObjectsRequest;
import com.obs.services.model.ObjectMetadata;
import com.obs.services.model.ObsObject;
import com.obs.services.model.PutObjectRequest;
import com.obs.services.model.PutObjectResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import p110z1.Consts;

/* loaded from: classes.dex */
public class HWYunManager {

    /* renamed from: a */
    public static final String f7540a = "package_name";

    /* renamed from: b */
    public static final String f7541b = "version_code";

    /* renamed from: c */
    public static final String f7542c = "upload_time";

    /* renamed from: d */
    public static final int f7543d = 0;

    /* renamed from: e */
    public static final int f7544e = 1;

    /* renamed from: f */
    public static final int f7545f = 2;

    /* renamed from: g */
    private Map<Long, UploadFileInputStream> f7546g;

    /* renamed from: h */
    private Set<Long> f7547h;

    /* renamed from: i */
    private OnUploadListener f7548i;

    /* renamed from: j */
    private Map<String, UploadFileInputStream> f7549j;

    /* loaded from: classes.dex */
    public interface ResultCallback {
        void onResult(PutObjectResult putObjectResult);
    }

    /* loaded from: classes.dex */
    public interface ResultCallback2 {
        void onResult(boolean z, String str, String str2);
    }

    /* loaded from: classes.dex */
    public interface UpdateDownloadedCallBack {
        void onUpdateDownloaded(long j, long j2, long j3);
    }

    /* loaded from: classes.dex */
    public interface UploadProgressCallback {
        void onProgress(int i, long j);
    }

    /* renamed from: com.cyjh.ddy.thirdlib.lib_hwobs.HWYunManager$a */
    /* loaded from: classes.dex */
    private static class C1163a {

        /* renamed from: a */
        private static final HWYunManager f7556a = new HWYunManager();
    }

    private HWYunManager() {
        this.f7546g = new HashMap();
        this.f7547h = new HashSet();
        this.f7549j = new HashMap();
    }

    /* renamed from: a */
    public static String m21375a(String str, String str2, String str3) {
        String str4 = "http://" + str3 + Consts.f23430h + str2 + "/";
        if (TextUtils.isEmpty(str)) {
            return str4;
        }
        return str4 + str;
    }

    /* renamed from: b */
    public static HWYunManager m21372b() {
        return C1163a.f7556a;
    }

    /* renamed from: a */
    public int m21376a(String str, String str2, UploadProgressCallback uploadProgressCallback, ObsCert obsCert) {
        return m21374a(str, str2, false, uploadProgressCallback, obsCert);
    }

    /* renamed from: a */
    public int m21374a(String str, String str2, boolean z, final UploadProgressCallback uploadProgressCallback, ObsCert obsCert) {
        boolean z2;
        long currentThreadTimeMillis = SystemClock.currentThreadTimeMillis();
        final long t = FileUtils.m22181t(str2);
        ObsClient obsClient = new ObsClient(obsCert.f7557ak, obsCert.f7558sk, obsCert.securityToken, obsCert.endPoint);
        PutObjectResult putObjectResult = null;
        try {
            try {
                UploadFileInputStream uploadFileInputStream = new UploadFileInputStream(new File(str2));
                if (uploadProgressCallback != null) {
                    uploadFileInputStream.setReadListener(new UploadFileInputStream.OnReadListener() { // from class: com.cyjh.ddy.thirdlib.lib_hwobs.HWYunManager.2
                        @Override // com.cyjh.ddy.thirdlib.lib_hwobs.UploadFileInputStream.OnReadListener
                        public void onRead(long j, long j2) {
                            uploadProgressCallback.onProgress((int) ((j / t) * 100.0d), j2);
                        }
                    });
                }
                this.f7549j.put(str, uploadFileInputStream);
                PutObjectRequest putObjectRequest = new PutObjectRequest();
                putObjectRequest.setBucketName(obsCert.bucketName);
                putObjectRequest.setObjectKey(str);
                putObjectRequest.setInput(uploadFileInputStream);
                if (z) {
                    putObjectRequest.setAcl(AccessControlList.REST_CANNED_PUBLIC_READ);
                }
                putObjectResult = obsClient.putObject(putObjectRequest);
                long currentThreadTimeMillis2 = SystemClock.currentThreadTimeMillis();
                Log.e("HWYunManager", "uploadApk " + str + " time: " + (currentThreadTimeMillis2 - currentThreadTimeMillis));
                this.f7549j.remove(str);
                m21383a(obsClient);
                z2 = false;
            } catch (Exception e) {
                e.printStackTrace();
                if (!TextUtils.isEmpty(e.getMessage())) {
                    if (e.getMessage().contains("java.nio.channels.ClosedChannelException")) {
                        z2 = true;
                        this.f7549j.remove(str);
                        m21383a(obsClient);
                    }
                }
                z2 = false;
                this.f7549j.remove(str);
                m21383a(obsClient);
            }
            if (z2) {
                return 2;
            }
            return putObjectResult != null ? 1 : 0;
        } catch (Throwable th) {
            this.f7549j.remove(str);
            m21383a(obsClient);
            throw th;
        }
    }

    /* renamed from: a */
    public ObsClient m21385a(ObsCert obsCert) {
        return new ObsClient(obsCert.f7557ak, obsCert.f7558sk, obsCert.securityToken, obsCert.endPoint);
    }

    /* renamed from: a */
    public String m21380a(String str, ObsCert obsCert) {
        String str2;
        ObsClient obsClient = new ObsClient(obsCert.f7557ak, obsCert.f7558sk, obsCert.securityToken, obsCert.endPoint);
        String y = FileUtils.m22171y(str);
        try {
            try {
                ListObjectsRequest listObjectsRequest = new ListObjectsRequest(obsCert.bucketName);
                String w = FileUtils.m22175w(str);
                String y2 = FileUtils.m22171y(str);
                String z = FileUtils.m22170z(str);
                listObjectsRequest.setPrefix(w + y2);
                List objects = obsClient.listObjects(listObjectsRequest).getObjects();
                str2 = y;
                boolean z2 = true;
                int i = 1;
                while (z2) {
                    z2 = false;
                    try {
                        Iterator it = objects.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            String objectKey = ((ObsObject) it.next()).getObjectKey();
                            if (TextUtils.equals(objectKey, w + str2 + Consts.f23430h + z)) {
                                z2 = true;
                                break;
                            }
                        }
                        if (z2) {
                            i++;
                            str2 = C1167b.m21354a(y2, i);
                        }
                    } catch (Exception unused) {
                    }
                }
            } finally {
                m21383a(obsClient);
            }
        } catch (Exception unused2) {
            str2 = y;
        }
        return str2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x007e, code lost:
        r8 = r8 + r8;
        r16 = java.lang.System.currentTimeMillis() - r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x008b, code lost:
        if (r16 <= 0) goto L_0x0096;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x008d, code lost:
        r25 = (1000 * r8) / r16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0096, code lost:
        r25 = 0;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String m21377a(java.lang.String r28, java.lang.String r29, com.cyjh.ddy.thirdlib.lib_hwobs.HWYunManager.UpdateDownloadedCallBack r30, com.cyjh.ddy.thirdlib.lib_hwobs.ObsCert r31) {
        /*
            Method dump skipped, instructions count: 254
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cyjh.ddy.thirdlib.lib_hwobs.HWYunManager.m21377a(java.lang.String, java.lang.String, com.cyjh.ddy.thirdlib.lib_hwobs.HWYunManager$UpdateDownloadedCallBack, com.cyjh.ddy.thirdlib.lib_hwobs.ObsCert):java.lang.String");
    }

    /* renamed from: a */
    public void m21388a() {
        for (Long l : this.f7546g.keySet()) {
            try {
                this.f7546g.get(l).close();
                this.f7547h.add(l);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.f7546g.clear();
    }

    /* renamed from: a */
    public void m21387a(long j) {
        try {
            UploadFileInputStream uploadFileInputStream = this.f7546g.get(Long.valueOf(j));
            if (uploadFileInputStream != null) {
                uploadFileInputStream.close();
                this.f7546g.remove(Long.valueOf(j));
                this.f7547h.add(Long.valueOf(j));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public void m21383a(ObsClient obsClient) {
        if (obsClient != null) {
            try {
                obsClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    public void m21381a(String str) {
        try {
            UploadFileInputStream uploadFileInputStream = this.f7549j.get(str);
            if (uploadFileInputStream != null) {
                uploadFileInputStream.close();
                this.f7549j.remove(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:32:? A[RETURN, SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void m21378a(java.lang.String r23, final com.cyjh.ddy.thirdlib.lib_hwobs.UploadApkInfo r24, com.cyjh.ddy.thirdlib.lib_hwobs.HWYunManager.ResultCallback2 r25, com.cyjh.ddy.thirdlib.lib_hwobs.ObsCert r26) {
        /*
            r22 = this;
            r1 = r22
            r2 = r25
            r0 = r26
            java.util.Set<java.lang.Long> r3 = r1.f7547h
            long r4 = r24.getTaskID()
            java.lang.Long r4 = java.lang.Long.valueOf(r4)
            r3.remove(r4)
            long r3 = android.os.SystemClock.currentThreadTimeMillis()
            int r5 = r24.getType()
            r6 = 1
            if (r5 != r6) goto L_0x0023
            java.lang.String r5 = com.cyjh.ddy.thirdlib.lib_hwobs.C1167b.m21352b(r23)
            goto L_0x0027
        L_0x0023:
            java.lang.String r5 = com.cyjh.ddy.thirdlib.lib_hwobs.C1167b.m21351c(r23)
        L_0x0027:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            r7.append(r5)
            java.lang.String r5 = r24.getFileName()
            r7.append(r5)
            java.lang.String r5 = r7.toString()
            com.obs.services.ObsClient r7 = new com.obs.services.ObsClient
            java.lang.String r8 = r0.f7557ak
            java.lang.String r9 = r0.f7558sk
            java.lang.String r10 = r0.securityToken
            java.lang.String r11 = r0.endPoint
            r7.<init>(r8, r9, r10, r11)
            com.obs.services.model.ObjectMetadata r8 = new com.obs.services.model.ObjectMetadata
            r8.<init>()
            java.lang.String r9 = "package_name"
            java.lang.String r10 = r24.getPackageName()
            r8.addUserMetadata(r9, r10)
            java.lang.String r9 = "version_code"
            int r10 = r24.getVercode()
            java.lang.String r10 = java.lang.String.valueOf(r10)
            r8.addUserMetadata(r9, r10)
            java.lang.String r9 = "upload_time"
            long r10 = r24.getUploadTime()
            java.lang.String r10 = java.lang.String.valueOf(r10)
            r8.addUserMetadata(r9, r10)
            r9 = 0
            com.cyjh.ddy.thirdlib.lib_hwobs.UploadFileInputStream r10 = new com.cyjh.ddy.thirdlib.lib_hwobs.UploadFileInputStream     // Catch: all -> 0x00da, Exception -> 0x00dc
            java.io.File r11 = new java.io.File     // Catch: all -> 0x00da, Exception -> 0x00dc
            java.lang.String r12 = r24.getPath()     // Catch: all -> 0x00da, Exception -> 0x00dc
            r11.<init>(r12)     // Catch: all -> 0x00da, Exception -> 0x00dc
            r10.<init>(r11)     // Catch: all -> 0x00da, Exception -> 0x00dc
            com.cyjh.ddy.thirdlib.lib_hwobs.HWYunManager$1 r11 = new com.cyjh.ddy.thirdlib.lib_hwobs.HWYunManager$1     // Catch: all -> 0x00da, Exception -> 0x00dc
            r12 = r24
            r11.<init>()     // Catch: Exception -> 0x00d8, all -> 0x00da
            r10.setReadListener(r11)     // Catch: Exception -> 0x00d8, all -> 0x00da
            java.util.Map<java.lang.Long, com.cyjh.ddy.thirdlib.lib_hwobs.UploadFileInputStream> r11 = r1.f7546g     // Catch: Exception -> 0x00d8, all -> 0x00da
            long r13 = r24.getTaskID()     // Catch: Exception -> 0x00d8, all -> 0x00da
            java.lang.Long r13 = java.lang.Long.valueOf(r13)     // Catch: Exception -> 0x00d8, all -> 0x00da
            r11.put(r13, r10)     // Catch: Exception -> 0x00d8, all -> 0x00da
            java.lang.String r0 = r0.bucketName     // Catch: Exception -> 0x00d8, all -> 0x00da
            com.obs.services.model.PutObjectResult r9 = r7.putObject(r0, r5, r10, r8)     // Catch: Exception -> 0x00d8, all -> 0x00da
            long r10 = android.os.SystemClock.currentThreadTimeMillis()     // Catch: Exception -> 0x00d8, all -> 0x00da
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: Exception -> 0x00d8, all -> 0x00da
            r0.<init>()     // Catch: Exception -> 0x00d8, all -> 0x00da
            java.lang.String r8 = "uploadApk "
            r0.append(r8)     // Catch: Exception -> 0x00d8, all -> 0x00da
            r0.append(r5)     // Catch: Exception -> 0x00d8, all -> 0x00da
            java.lang.String r8 = " time: "
            r0.append(r8)     // Catch: Exception -> 0x00d8, all -> 0x00da
            long r10 = r10 - r3
            r0.append(r10)     // Catch: Exception -> 0x00d8, all -> 0x00da
            java.lang.String r0 = r0.toString()     // Catch: Exception -> 0x00d8, all -> 0x00da
            java.lang.String r3 = "HWYunManager"
            android.util.Log.e(r3, r0)     // Catch: Exception -> 0x00d8, all -> 0x00da
            if (r9 == 0) goto L_0x00e2
            com.cyjh.ddy.thirdlib.lib_hwobs.OnUploadListener r0 = r1.f7548i     // Catch: Exception -> 0x00d8, all -> 0x00da
            if (r0 == 0) goto L_0x00e2
            com.cyjh.ddy.thirdlib.lib_hwobs.OnUploadListener r13 = r1.f7548i     // Catch: Exception -> 0x00d8, all -> 0x00da
            long r14 = r24.getTaskID()     // Catch: Exception -> 0x00d8, all -> 0x00da
            long r16 = r24.getSize()     // Catch: Exception -> 0x00d8, all -> 0x00da
            long r18 = r24.getSize()     // Catch: Exception -> 0x00d8, all -> 0x00da
            r20 = 0
            r13.progress(r14, r16, r18, r20)     // Catch: Exception -> 0x00d8, all -> 0x00da
            goto L_0x00e2
        L_0x00d8:
            r0 = move-exception
            goto L_0x00df
        L_0x00da:
            r0 = move-exception
            goto L_0x00f3
        L_0x00dc:
            r0 = move-exception
            r12 = r24
        L_0x00df:
            r0.printStackTrace()     // Catch: all -> 0x00da
        L_0x00e2:
            r1.m21383a(r7)
            if (r2 == 0) goto L_0x00f2
            if (r9 == 0) goto L_0x00ea
            goto L_0x00eb
        L_0x00ea:
            r6 = 0
        L_0x00eb:
            java.lang.String r0 = r24.getPath()
            r2.onResult(r6, r0, r5)
        L_0x00f2:
            return
        L_0x00f3:
            r1.m21383a(r7)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cyjh.ddy.thirdlib.lib_hwobs.HWYunManager.m21378a(java.lang.String, com.cyjh.ddy.thirdlib.lib_hwobs.UploadApkInfo, com.cyjh.ddy.thirdlib.lib_hwobs.HWYunManager$ResultCallback2, com.cyjh.ddy.thirdlib.lib_hwobs.ObsCert):void");
    }

    /* renamed from: a */
    public void m21373a(List<UploadApkInfo> list) {
        Collections.sort(list, new Comparator<UploadApkInfo>() { // from class: com.cyjh.ddy.thirdlib.lib_hwobs.HWYunManager.3
            /* renamed from: a */
            public int compare(UploadApkInfo uploadApkInfo, UploadApkInfo uploadApkInfo2) {
                if (uploadApkInfo.getUploadTime() < uploadApkInfo2.getUploadTime()) {
                    return 1;
                }
                return uploadApkInfo.getUploadTime() > uploadApkInfo2.getUploadTime() ? -1 : 0;
            }
        });
    }

    /* renamed from: a */
    public boolean m21384a(UploadApkInfo uploadApkInfo, String str, ObsCert obsCert, ObsClient obsClient) {
        try {
            ObjectMetadata objectMetadata = obsClient.getObjectMetadata(obsCert.bucketName, str);
            if (objectMetadata != null && !TextUtils.isEmpty((String) objectMetadata.getUserMetadata(f7540a))) {
                uploadApkInfo.setPackageName((String) objectMetadata.getUserMetadata(f7540a));
                uploadApkInfo.setMd5(new String(Base64.decode(objectMetadata.getContentMd5(), 0)));
                uploadApkInfo.setIconUrl(m21375a(C1167b.m21349e(str), obsCert.endPoint, obsCert.bucketName));
                String str2 = (String) objectMetadata.getUserMetadata(f7542c);
                if (TextUtils.isEmpty(str2)) {
                    return true;
                }
                uploadApkInfo.setUploadTime(Long.valueOf(str2).longValue());
                return true;
            }
        } catch (Exception unused) {
        }
        return false;
    }

    /* renamed from: a */
    public boolean m21382a(Long l) {
        return this.f7547h.contains(l);
    }

    /* renamed from: a */
    public boolean m21379a(String str, ObsCert obsCert, boolean z) {
        ObsClient obsClient = new ObsClient(obsCert.f7557ak, obsCert.f7558sk, obsCert.securityToken, obsCert.endPoint);
        boolean z2 = false;
        try {
            try {
                DeleteObjectResult deleteObject = obsClient.deleteObject(obsCert.bucketName, str);
                Log.e("HWYunManager", "deleteObject " + str + ExpandableTextView.f6958c + deleteObject.toString());
                z2 = true;
                if (z) {
                    obsClient.deleteObject(obsCert.bucketName, C1167b.m21349e(str));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return z2;
        } finally {
            m21383a(obsClient);
        }
    }

    /* renamed from: b */
    public UploadFileInputStream m21371b(long j) {
        return this.f7546g.get(Long.valueOf(j));
    }

    /* JADX WARN: Finally extract failed */
    /* renamed from: b */
    public List<UploadApkInfo> m21370b(String str, ObsCert obsCert) {
        ArrayList arrayList = new ArrayList();
        ListObjectsRequest listObjectsRequest = new ListObjectsRequest(obsCert.bucketName);
        if (!TextUtils.isEmpty(str)) {
            listObjectsRequest.setPrefix(str);
        }
        ObsClient obsClient = new ObsClient(obsCert.f7557ak, obsCert.f7558sk, obsCert.securityToken, obsCert.endPoint);
        try {
            try {
                for (ObsObject obsObject : obsClient.listObjects(listObjectsRequest).getObjects()) {
                    String name = FileUtils.m22242a(obsObject.getObjectKey()).getParentFile().getName();
                    if (TextUtils.equals(name, "apk") || TextUtils.equals(name, "file")) {
                        ObjectMetadata objectMetadata = obsClient.getObjectMetadata(obsCert.bucketName, obsObject.getObjectKey());
                        if (objectMetadata != null && !TextUtils.isEmpty((String) objectMetadata.getUserMetadata(f7540a))) {
                            obsObject.setMetadata(objectMetadata);
                            arrayList.add(new C1168c(obsObject).m21347a(obsCert.endPoint, obsCert.bucketName));
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            m21383a(obsClient);
            m21373a(arrayList);
            return arrayList;
        } catch (Throwable th) {
            m21383a(obsClient);
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:24:? A[RETURN, SYNTHETIC] */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void m21369b(java.lang.String r8, com.cyjh.ddy.thirdlib.lib_hwobs.UploadApkInfo r9, com.cyjh.ddy.thirdlib.lib_hwobs.HWYunManager.ResultCallback2 r10, com.cyjh.ddy.thirdlib.lib_hwobs.ObsCert r11) {
        /*
            r7 = this;
            long r0 = android.os.SystemClock.currentThreadTimeMillis()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r8 = com.cyjh.ddy.thirdlib.lib_hwobs.C1167b.m21350d(r8)
            r2.append(r8)
            java.lang.String r8 = r9.getFileName()
            r2.append(r8)
            java.lang.String r8 = ".png"
            r2.append(r8)
            java.lang.String r8 = r2.toString()
            com.obs.services.ObsClient r2 = new com.obs.services.ObsClient
            java.lang.String r3 = r11.f7557ak
            java.lang.String r4 = r11.f7558sk
            java.lang.String r5 = r11.securityToken
            java.lang.String r6 = r11.endPoint
            r2.<init>(r3, r4, r5, r6)
            com.obs.services.model.PutObjectRequest r3 = new com.obs.services.model.PutObjectRequest
            r3.<init>()
            java.lang.String r11 = r11.bucketName
            r3.setBucketName(r11)
            r3.setObjectKey(r8)
            android.graphics.drawable.Drawable r11 = r9.getIcon()
            android.graphics.Bitmap$CompressFormat r4 = android.graphics.Bitmap.CompressFormat.PNG
            r5 = 100
            byte[] r11 = com.cyjh.ddy.thirdlib.lib_hwobs.C1169d.m21339a(r11, r4, r5)
            java.io.InputStream r11 = com.blankj.utilcode.util.ConvertUtils.m22442d(r11)
            r3.setInput(r11)
            com.obs.services.model.ObjectMetadata r11 = new com.obs.services.model.ObjectMetadata
            r11.<init>()
            java.lang.String r4 = "image/png"
            r11.setContentType(r4)
            r3.setMetadata(r11)
            com.obs.services.model.AccessControlList r11 = com.obs.services.model.AccessControlList.REST_CANNED_PUBLIC_READ
            r3.setAcl(r11)
            com.obs.services.model.PutObjectResult r11 = r2.putObject(r3)     // Catch: all -> 0x0089, Exception -> 0x008b
            long r3 = android.os.SystemClock.currentThreadTimeMillis()     // Catch: Exception -> 0x0087, all -> 0x0089
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: Exception -> 0x0087, all -> 0x0089
            r5.<init>()     // Catch: Exception -> 0x0087, all -> 0x0089
            java.lang.String r6 = "uploadApkIcon "
            r5.append(r6)     // Catch: Exception -> 0x0087, all -> 0x0089
            r5.append(r8)     // Catch: Exception -> 0x0087, all -> 0x0089
            java.lang.String r6 = " time: "
            r5.append(r6)     // Catch: Exception -> 0x0087, all -> 0x0089
            long r3 = r3 - r0
            r5.append(r3)     // Catch: Exception -> 0x0087, all -> 0x0089
            java.lang.String r0 = r5.toString()     // Catch: Exception -> 0x0087, all -> 0x0089
            java.lang.String r1 = "HWYunManager"
            android.util.Log.e(r1, r0)     // Catch: Exception -> 0x0087, all -> 0x0089
            goto L_0x0090
        L_0x0087:
            r0 = move-exception
            goto L_0x008d
        L_0x0089:
            r8 = move-exception
            goto L_0x00a2
        L_0x008b:
            r0 = move-exception
            r11 = 0
        L_0x008d:
            r0.printStackTrace()     // Catch: all -> 0x0089
        L_0x0090:
            r7.m21383a(r2)
            if (r10 == 0) goto L_0x00a1
            if (r11 == 0) goto L_0x0099
            r11 = 1
            goto L_0x009a
        L_0x0099:
            r11 = 0
        L_0x009a:
            java.lang.String r9 = r9.getPath()
            r10.onResult(r11, r9, r8)
        L_0x00a1:
            return
        L_0x00a2:
            r7.m21383a(r2)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cyjh.ddy.thirdlib.lib_hwobs.HWYunManager.m21369b(java.lang.String, com.cyjh.ddy.thirdlib.lib_hwobs.UploadApkInfo, com.cyjh.ddy.thirdlib.lib_hwobs.HWYunManager$ResultCallback2, com.cyjh.ddy.thirdlib.lib_hwobs.ObsCert):void");
    }

    public void setOnUploadListener(OnUploadListener onUploadListener) {
        this.f7548i = onUploadListener;
    }
}
