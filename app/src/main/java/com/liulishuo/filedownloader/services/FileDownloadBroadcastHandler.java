package com.liulishuo.filedownloader.services;

import android.content.Intent;
import com.liulishuo.filedownloader.model.FileDownloadModel;
import p110z1.FileDownloadHelper;
import p110z1.FileDownloadUtils;

/* renamed from: com.liulishuo.filedownloader.services.f */
/* loaded from: classes.dex */
public class FileDownloadBroadcastHandler {

    /* renamed from: a */
    public static final String f10431a = "filedownloader.intent.action.completed";

    /* renamed from: b */
    public static final String f10432b = "model";

    /* renamed from: a */
    public static FileDownloadModel m19050a(Intent intent) {
        if (f10431a.equals(intent.getAction())) {
            return (FileDownloadModel) intent.getParcelableExtra(f10432b);
        }
        throw new IllegalArgumentException(FileDownloadUtils.m13182a("can't recognize the intent with action %s, on the current version we only support action [%s]", intent.getAction(), f10431a));
    }

    /* renamed from: a */
    public static void m19049a(FileDownloadModel fileDownloadModel) {
        if (fileDownloadModel == null) {
            throw new IllegalArgumentException();
        } else if (fileDownloadModel.m19119f() == -3) {
            Intent intent = new Intent(f10431a);
            intent.putExtra(f10432b, fileDownloadModel);
            FileDownloadHelper.m13229a().sendBroadcast(intent);
        } else {
            throw new IllegalStateException();
        }
    }
}
