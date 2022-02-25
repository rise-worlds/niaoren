package com.liulishuo.filedownloader.services;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import java.lang.ref.WeakReference;
import p110z1.FileDownloadHelper;
import p110z1.FileDownloadProperties;
import p110z1.FileDownloadUtils;

@SuppressLint({"Registered"})
/* loaded from: classes.dex */
public class FileDownloadService extends Service {

    /* renamed from: a */
    private IFileDownloadServiceHandler f10412a;

    /* loaded from: classes.dex */
    public static class SeparateProcessService extends FileDownloadService {
    }

    /* loaded from: classes.dex */
    public static class SharedMainProcessService extends FileDownloadService {
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        FileDownloadHelper.m13225a(this);
        try {
            FileDownloadUtils.m13194a(FileDownloadProperties.m13208a().f15857a);
            FileDownloadUtils.m13189a(FileDownloadProperties.m13208a().f15858b);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        FileDownloadManager gVar = new FileDownloadManager();
        if (FileDownloadProperties.m13208a().f15860d) {
            this.f10412a = new FDServiceSharedHandler(new WeakReference(this), gVar);
        } else {
            this.f10412a = new FDServiceSeparateHandler(new WeakReference(this), gVar);
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        this.f10412a.onStartCommand(intent, i, i2);
        return 1;
    }

    @Override // android.app.Service
    public void onDestroy() {
        this.f10412a.onDestroy();
        super.onDestroy();
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.f10412a.onBind(intent);
    }
}
