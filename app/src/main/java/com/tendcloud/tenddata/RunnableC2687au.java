package com.tendcloud.tenddata;

import java.io.RandomAccessFile;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.au */
/* loaded from: classes2.dex */
public final class RunnableC2687au implements Runnable {
    @Override // java.lang.Runnable
    public void run() {
        String str;
        boolean z;
        String str2;
        RandomAccessFile randomAccessFile;
        RandomAccessFile randomAccessFile2;
        RandomAccessFile randomAccessFile3;
        try {
            str2 = C2685at.f13548h;
            C2832ea.getFileLock(str2);
            randomAccessFile = C2685at.f13547g;
            if (randomAccessFile.length() > 0) {
                randomAccessFile2 = C2685at.f13547g;
                randomAccessFile2.seek(0L);
                randomAccessFile3 = C2685at.f13547g;
                boolean unused = C2685at.f13542b = randomAccessFile3.readBoolean();
            }
        } catch (Throwable unused2) {
        }
        str = C2685at.f13548h;
        C2832ea.releaseFileLock(str);
        z = C2685at.f13542b;
        if (z) {
            C2685at.m16285n();
            return;
        }
        try {
            if (C2685at.f13545e != null) {
                C2685at.f13545e.close();
            }
        } catch (Throwable unused3) {
        }
    }
}
