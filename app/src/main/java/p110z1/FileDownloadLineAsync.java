package p110z1;

import android.app.Notification;

/* renamed from: z1.afj */
/* loaded from: classes3.dex */
public class FileDownloadLineAsync {
    /* renamed from: a */
    public boolean m13711a(final int i, final Notification notification) {
        if (FileDownloader.m13627a().m13591j()) {
            FileDownloader.m13627a().m13625a(i, notification);
            return true;
        }
        FileDownloader.m13627a().m13619a(new Runnable() { // from class: z1.afj.1
            @Override // java.lang.Runnable
            public void run() {
                FileDownloader.m13627a().m13625a(i, notification);
            }
        });
        return false;
    }
}
