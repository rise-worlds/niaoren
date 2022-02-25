package p110z1;

import com.common.utils.log.LogUtils;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* renamed from: z1.aex */
/* loaded from: classes3.dex */
public class UnZipThread extends Thread {

    /* renamed from: a */
    int f15481a = 0;

    /* renamed from: b */
    UnZipListener f15482b;

    /* renamed from: c */
    String f15483c;

    /* renamed from: d */
    String f15484d;

    /* renamed from: e */
    String f15485e;

    /* renamed from: f */
    String f15486f;

    public UnZipThread(String str, String str2, String str3, String str4, UnZipListener aewVar) {
        this.f15486f = str;
        this.f15483c = str2;
        this.f15482b = aewVar;
        this.f15485e = str3;
        this.f15484d = str4;
    }

    /* renamed from: a */
    private long m13825a(String str) {
        long j = 0;
        try {
            Enumeration<? extends ZipEntry> entries = new ZipFile(str).entries();
            while (entries.hasMoreElements()) {
                j += ((ZipEntry) entries.nextElement()).getSize();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return j;
    }

    /* JADX WARN: Removed duplicated region for block: B:78:0x01c5 A[Catch: Exception -> 0x01c1, TRY_LEAVE, TryCatch #8 {Exception -> 0x01c1, blocks: (B:74:0x01bd, B:78:0x01c5), top: B:84:0x01bd }] */
    /* JADX WARN: Removed duplicated region for block: B:84:0x01bd A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // java.lang.Thread, java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void run() {
        /*
            Method dump skipped, instructions count: 461
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.UnZipThread.run():void");
    }

    /* renamed from: a */
    private void m13826a(int i, UnZipListener aewVar) {
        if (i > this.f15481a) {
            LogUtils.m22036e("test", "解压进度 sumLength=" + i);
            this.f15481a = i;
            aewVar.unZipProgress(i);
        }
    }
}
