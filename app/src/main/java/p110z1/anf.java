package p110z1;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import android.util.Log;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.LogUtils;
import com.kaopu.download.BaseDownloadOperate;
import com.kaopu.download.BaseDownloadStateFactory;
import com.kaopu.download.BaseDownloadWorker;
import com.kaopu.download.kernel.BaseDownloadInfo;
import com.kaopu.download.kernel.DownloadServiceConnection;
import com.kaopu.receiver.BroadcastReceiver;
import com.nrzs.data.DataApp;

/* compiled from: OrcDownModel.java */
/* renamed from: z1.anf */
/* loaded from: classes3.dex */
public class anf {

    /* renamed from: a */
    private static anf f16687a;

    /* renamed from: b */
    private Context f16688b;

    /* renamed from: c */
    private int f16689c = -1;

    /* renamed from: d */
    private BaseDownloadInfo f16690d;

    /* renamed from: e */
    private BroadcastReceiver f16691e;

    /* renamed from: f */
    private AbstractC3686b f16692f;

    /* renamed from: g */
    private DownloadServiceConnection f16693g;

    /* compiled from: OrcDownModel.java */
    /* renamed from: z1.anf$a */
    /* loaded from: classes3.dex */
    public interface AbstractC3685a {
        /* renamed from: a */
        void m12466a(boolean z, String str);
    }

    /* compiled from: OrcDownModel.java */
    /* renamed from: z1.anf$b */
    /* loaded from: classes3.dex */
    public interface AbstractC3686b {
        /* renamed from: a */
        void mo12442a();

        /* renamed from: a */
        void mo12441a(int i);

        /* renamed from: a */
        void mo12440a(long j, long j2);

        /* renamed from: a */
        void mo12439a(String str);

        /* renamed from: b */
        void mo12438b(int i);
    }

    private anf(Context context) {
        this.f16688b = context;
        m12476b(this.f16688b);
        if (this.f16693g == null) {
            this.f16693g = new DownloadServiceConnection(context);
            this.f16693g.bindDownloadService(null);
        }
    }

    /* renamed from: a */
    public static anf m12486a(Context context) {
        if (f16687a == null) {
            synchronized (anf.class) {
                if (f16687a == null) {
                    f16687a = new anf(context);
                }
            }
        }
        return f16687a;
    }

    /* renamed from: a */
    public void m12481a(AbstractC3686b bVar) {
        this.f16692f = bVar;
    }

    /* renamed from: a */
    public void m12488a() {
        m12478b();
    }

    /* renamed from: b */
    public void m12476b(Context context) {
        IntentFilter intentFilter = new IntentFilter(BaseDownloadWorker.NOTIFY_VIEW_ACTION);
        if (this.f16691e == null) {
            this.f16691e = new BroadcastReceiver() { // from class: z1.anf.1
                @Override // com.kaopu.receiver.BroadcastReceiver, android.content.BroadcastReceiver
                public void onReceive(Context context2, Intent intent) {
                    Log.e("下载", "接收到下载的广播");
                    BaseDownloadInfo baseDownloadInfo = (BaseDownloadInfo) intent.getParcelableExtra(BaseDownloadWorker.NOTIFY_VIEW_ACTION_EXTRA_INFO_KEY);
                    if (baseDownloadInfo != null && anf.this.f16690d != null) {
                        if (TextUtils.isEmpty(baseDownloadInfo.getIdentification()) || !baseDownloadInfo.getIdentification().equals(anf.this.f16690d.getIdentification())) {
                            LogUtils.m23742b("过滤其他下载广播", anf.this.f16690d.getIdentification());
                            return;
                        }
                        switch (C36842.f16695a[baseDownloadInfo.getState().getState().ordinal()]) {
                            case 1:
                                LogUtils.m23742b("saveDir:" + baseDownloadInfo.getSaveDir());
                                LogUtils.m23742b("saveName:" + baseDownloadInfo.getSaveName());
                                anf.this.m12468e();
                                return;
                            case 2:
                                anf.this.m12483a(baseDownloadInfo.getIdentification(), baseDownloadInfo.getdSize(), baseDownloadInfo.getfSize(), baseDownloadInfo.getSpeed());
                                return;
                            case 3:
                                anf.this.m12467f();
                                return;
                            default:
                                return;
                        }
                    }
                }
            };
        }
        this.f16691e.registerReceiver(context, intentFilter);
    }

    /* compiled from: OrcDownModel.java */
    /* renamed from: z1.anf$2 */
    /* loaded from: classes3.dex */
    static /* synthetic */ class C36842 {

        /* renamed from: a */
        static final /* synthetic */ int[] f16695a = new int[BaseDownloadStateFactory.State.values().length];

        static {
            try {
                f16695a[BaseDownloadStateFactory.State.DOWNLOADED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f16695a[BaseDownloadStateFactory.State.DOWNLOADING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f16695a[BaseDownloadStateFactory.State.DOWNLOAD_FAILED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m12483a(String str, long j, long j2, long j3) {
        AbstractC3686b bVar = this.f16692f;
        if (bVar != null) {
            bVar.mo12440a(j, j2);
        }
    }

    /* renamed from: b */
    public void m12478b() {
        BroadcastReceiver broadcastReceiver = this.f16691e;
        if (broadcastReceiver != null) {
            broadcastReceiver.unregisterReceiver();
        }
        this.f16692f = null;
    }

    /* renamed from: a */
    private boolean m12485a(BaseDownloadInfo baseDownloadInfo) {
        StringBuilder sb = new StringBuilder();
        sb.append(baseDownloadInfo.getSaveDir());
        sb.append(baseDownloadInfo.getSaveName());
        return !FileUtils.m22229b(sb.toString());
    }

    /* renamed from: a */
    public void m12484a(String str) {
        m12474b(str);
    }

    /* renamed from: b */
    private void m12474b(String str) {
        String b = apf.m11837b(DataApp.m18939d().m18947a(), ShareVal.f16591a, ShareVal.f16585P, "");
        LogUtils.m23742b("TAG", "startDownloadWx" + b);
        BaseDownloadInfo a = m12482a(b, str);
        this.f16690d = a;
        if (a != null && a.isDownLoaded()) {
            m12468e();
        } else if (a == null) {
            m12467f();
        } else {
            m12487a(6);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e */
    public void m12468e() {
        m12487a(1);
        AbstractC3686b bVar = this.f16692f;
        if (bVar != null) {
            bVar.mo12439a(this.f16690d.getSavePath());
        }
    }

    /* renamed from: c */
    public int m12471c() {
        return this.f16689c;
    }

    /* renamed from: a */
    public void m12487a(int i) {
        this.f16689c = i;
        AbstractC3686b bVar = this.f16692f;
        if (bVar != null) {
            bVar.mo12438b(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: f */
    public void m12467f() {
        m12487a(2);
        AbstractC3686b bVar = this.f16692f;
        if (bVar != null) {
            bVar.mo12442a();
        }
    }

    /* renamed from: b */
    private void m12477b(int i) {
        AbstractC3686b bVar = this.f16692f;
        if (bVar != null) {
            bVar.mo12441a(i);
        }
    }

    /* renamed from: d */
    public void m12469d() {
        this.f16692f = null;
    }

    /* renamed from: a */
    private BaseDownloadInfo m12482a(String str, String str2) {
        BaseDownloadInfo downloadInfo = BaseDownloadOperate.getDownloadInfo(this.f16688b, str2);
        if (downloadInfo == null) {
            downloadInfo = m12473b(str, str2);
            if (m12485a(downloadInfo)) {
                m12475b(downloadInfo);
            }
        }
        return downloadInfo;
    }

    /* renamed from: b */
    private BaseDownloadInfo m12473b(String str, String str2) {
        String b = apf.m11837b(DataApp.m18939d().m18947a(), ShareVal.f16591a, ShareVal.f16581L, "");
        if (TextUtils.isEmpty(b)) {
            b = "Android";
        }
        BaseDownloadInfo baseDownloadInfo = new BaseDownloadInfo();
        baseDownloadInfo.setIdentification(str2);
        baseDownloadInfo.setSaveDir(str);
        String str3 = b + ".zip";
        if (TextUtils.isEmpty(str3)) {
            str3 = "Android.zip";
        }
        baseDownloadInfo.setSaveName(str3);
        baseDownloadInfo.setUrl(str2);
        return baseDownloadInfo;
    }

    /* renamed from: b */
    private void m12475b(BaseDownloadInfo baseDownloadInfo) {
        BaseDownloadOperate.addNewDownloadTask(this.f16688b, baseDownloadInfo);
    }
}
