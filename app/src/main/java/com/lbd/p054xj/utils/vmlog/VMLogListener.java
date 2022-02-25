package com.lbd.p054xj.utils.vmlog;

import android.os.FileObserver;
import com.common.utils.log.LogUtils;
import java.io.ByteArrayOutputStream;
import java.io.RandomAccessFile;

/* renamed from: com.lbd.xj.utils.vmlog.a */
/* loaded from: classes.dex */
public class VMLogListener extends FileObserver {

    /* renamed from: a */
    public static int f9972a = 4040;

    /* renamed from: b */
    private String f9973b;

    @Override // android.os.FileObserver
    public void onEvent(int i, String str) {
    }

    public VMLogListener(String str) {
        super(str);
        this.f9973b = str;
    }

    /* renamed from: a */
    private synchronized void m19279a(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(this.f9973b + "/" + str, "r");
            if (randomAccessFile.length() > 0) {
                randomAccessFile.seek(0L);
            }
            byte[] bArr = new byte[1024];
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while (true) {
                int read = randomAccessFile.read(bArr);
                if (read == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, read);
            }
            stringBuffer.append(byteArrayOutputStream);
            LogUtils.m22036e("mylog", "seekPoint=" + randomAccessFile.length());
        } catch (Exception unused) {
            LogUtils.m22036e("mylog", "seekPoint=0 exception");
        }
    }

    @Override // android.os.FileObserver
    public void stopWatching() {
        super.stopWatching();
    }
}
