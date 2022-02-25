package p110z1;

import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* compiled from: UnZipThread.java */
/* renamed from: z1.amt */
/* loaded from: classes3.dex */
public class amt extends Thread {

    /* renamed from: a */
    int f16652a = 0;

    /* renamed from: b */
    ams f16653b;

    /* renamed from: c */
    String f16654c;

    /* renamed from: d */
    String f16655d;

    /* renamed from: e */
    String f16656e;

    /* renamed from: f */
    String f16657f;

    public amt(String str, String str2, String str3, String str4, ams amsVar) {
        this.f16657f = str;
        this.f16654c = str2;
        this.f16653b = amsVar;
        this.f16656e = str3;
        this.f16655d = str4;
    }

    /* renamed from: a */
    private long m12501a(String str) {
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

    /* JADX WARN: Removed duplicated region for block: B:75:0x0188 A[Catch: Exception -> 0x0184, TRY_LEAVE, TryCatch #6 {Exception -> 0x0184, blocks: (B:71:0x0180, B:75:0x0188), top: B:81:0x0180 }] */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0180 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // java.lang.Thread, java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void run() {
        /*
            Method dump skipped, instructions count: 400
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.amt.run():void");
    }

    /* renamed from: a */
    private void m12502a(int i, ams amsVar) {
        if (i > this.f16652a) {
            this.f16652a = i;
            amsVar.mo12445a(i);
        }
    }
}
