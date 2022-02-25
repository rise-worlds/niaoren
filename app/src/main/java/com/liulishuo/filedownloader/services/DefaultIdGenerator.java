package com.liulishuo.filedownloader.services;

import p110z1.FileDownloadHelper;
import p110z1.FileDownloadUtils;

/* renamed from: com.liulishuo.filedownloader.services.b */
/* loaded from: classes.dex */
public class DefaultIdGenerator implements FileDownloadHelper.AbstractC3480d {
    @Override // p110z1.FileDownloadHelper.AbstractC3480d
    /* renamed from: a */
    public int mo13221a(int i, String str, String str2, boolean z) {
        return mo13220a(str, str2, z);
    }

    @Override // p110z1.FileDownloadHelper.AbstractC3480d
    /* renamed from: a */
    public int mo13220a(String str, String str2, boolean z) {
        return z ? FileDownloadUtils.m13158f(FileDownloadUtils.m13182a("%sp%s@dir", str, str2)).hashCode() : FileDownloadUtils.m13158f(FileDownloadUtils.m13182a("%sp%s", str, str2)).hashCode();
    }
}
