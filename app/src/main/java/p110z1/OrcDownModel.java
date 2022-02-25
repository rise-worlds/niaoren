package p110z1;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.LogUtils;
import com.kaopu.download.BaseDownloadOperate;
import com.kaopu.download.BaseDownloadStateFactory;
import com.kaopu.download.BaseDownloadWorker;
import com.kaopu.download.kernel.BaseDownloadInfo;
import com.kaopu.receiver.BroadcastReceiver;
import com.nrzs.data.DataApp;

/* renamed from: z1.ajs */
/* loaded from: classes3.dex */
public class OrcDownModel {

    /* renamed from: a */
    private static OrcDownModel f16098a;

    /* renamed from: b */
    private Context f16099b;

    /* renamed from: c */
    private int f16100c = -1;

    /* renamed from: d */
    private BaseDownloadInfo f16101d;

    /* renamed from: e */
    private BroadcastReceiver f16102e;

    /* renamed from: f */
    private AbstractC3541b f16103f;

    /* compiled from: OrcDownModel.java */
    /* renamed from: z1.ajs$a */
    /* loaded from: classes3.dex */
    public interface AbstractC3540a {
        /* renamed from: a */
        void m12841a(boolean z, String str);
    }

    /* compiled from: OrcDownModel.java */
    /* renamed from: z1.ajs$b */
    /* loaded from: classes3.dex */
    public interface AbstractC3541b {
        /* renamed from: a */
        void mo12822a();

        /* renamed from: a */
        void mo12821a(int i);

        /* renamed from: a */
        void mo12820a(long j, long j2);

        /* renamed from: a */
        void mo12819a(String str);

        /* renamed from: b */
        void mo12818b(int i);
    }

    private OrcDownModel(Context context) {
        this.f16099b = context;
        m12851b(this.f16099b);
    }

    /* renamed from: a */
    public static OrcDownModel m12861a(Context context) {
        if (f16098a == null) {
            synchronized (OrcDownModel.class) {
                if (f16098a == null) {
                    f16098a = new OrcDownModel(context);
                }
            }
        }
        return f16098a;
    }

    /* renamed from: a */
    public void m12856a(AbstractC3541b bVar) {
        this.f16103f = bVar;
    }

    /* renamed from: a */
    public void m12863a() {
        m12853b();
    }

    /* renamed from: b */
    public void m12851b(Context context) {
        IntentFilter intentFilter = new IntentFilter(BaseDownloadWorker.NOTIFY_VIEW_ACTION);
        if (this.f16102e == null) {
            this.f16102e = new BroadcastReceiver() { // from class: z1.ajs.1
                @Override // com.kaopu.receiver.BroadcastReceiver, android.content.BroadcastReceiver
                public void onReceive(Context context2, Intent intent) {
                    BaseDownloadInfo baseDownloadInfo = (BaseDownloadInfo) intent.getParcelableExtra(BaseDownloadWorker.NOTIFY_VIEW_ACTION_EXTRA_INFO_KEY);
                    if (baseDownloadInfo != null && OrcDownModel.this.f16101d != null) {
                        if (TextUtils.isEmpty(baseDownloadInfo.getIdentification()) || !baseDownloadInfo.getIdentification().equals(OrcDownModel.this.f16101d.getIdentification())) {
                            LogUtils.m23742b("过滤其他下载广播", OrcDownModel.this.f16101d.getIdentification());
                            return;
                        }
                        switch (C35392.f16105a[baseDownloadInfo.getState().getState().ordinal()]) {
                            case 1:
                                LogUtils.m23742b("saveDir:" + baseDownloadInfo.getSaveDir());
                                LogUtils.m23742b("saveName:" + baseDownloadInfo.getSaveName());
                                OrcDownModel.this.m12843e();
                                return;
                            case 2:
                                OrcDownModel.this.m12858a(baseDownloadInfo.getIdentification(), baseDownloadInfo.getdSize(), baseDownloadInfo.getfSize(), baseDownloadInfo.getSpeed());
                                return;
                            case 3:
                                OrcDownModel.this.m12842f();
                                return;
                            default:
                                return;
                        }
                    }
                }
            };
        }
        this.f16102e.registerReceiver(context, intentFilter);
    }

    /* compiled from: OrcDownModel.java */
    /* renamed from: z1.ajs$2 */
    /* loaded from: classes3.dex */
    static /* synthetic */ class C35392 {

        /* renamed from: a */
        static final /* synthetic */ int[] f16105a = new int[BaseDownloadStateFactory.State.values().length];

        static {
            try {
                f16105a[BaseDownloadStateFactory.State.DOWNLOADED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f16105a[BaseDownloadStateFactory.State.DOWNLOADING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f16105a[BaseDownloadStateFactory.State.DOWNLOAD_FAILED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m12858a(String str, long j, long j2, long j3) {
        AbstractC3541b bVar = this.f16103f;
        if (bVar != null) {
            bVar.mo12820a(j, j2);
        }
    }

    /* renamed from: b */
    public void m12853b() {
        BroadcastReceiver broadcastReceiver = this.f16102e;
        if (broadcastReceiver != null && broadcastReceiver.isRegistered()) {
            this.f16102e.unregisterReceiver();
        }
        this.f16103f = null;
    }

    /* renamed from: a */
    private boolean m12860a(BaseDownloadInfo baseDownloadInfo) {
        StringBuilder sb = new StringBuilder();
        sb.append(baseDownloadInfo.getSaveDir());
        sb.append(baseDownloadInfo.getSaveName());
        return !FileUtils.m22229b(sb.toString());
    }

    /* renamed from: a */
    public void m12859a(String str) {
        m12849b(str);
    }

    /* renamed from: b */
    private void m12849b(String str) {
        LogUtils.m23742b("TAG", "startDownloadWx");
        BaseDownloadInfo a = m12857a(NRZSFileConfig.f16565w, str);
        this.f16101d = a;
        if (a != null && a.isDownLoaded()) {
            m12843e();
        } else if (a == null) {
            m12842f();
        } else {
            m12862a(6);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e */
    public void m12843e() {
        m12862a(1);
        AbstractC3541b bVar = this.f16103f;
        if (bVar != null) {
            bVar.mo12819a(this.f16101d.getSavePath());
        }
    }

    /* renamed from: c */
    public int m12846c() {
        return this.f16100c;
    }

    /* renamed from: a */
    public void m12862a(int i) {
        this.f16100c = i;
        AbstractC3541b bVar = this.f16103f;
        if (bVar != null) {
            bVar.mo12818b(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: f */
    public void m12842f() {
        m12862a(2);
        AbstractC3541b bVar = this.f16103f;
        if (bVar != null) {
            bVar.mo12822a();
        }
    }

    /* renamed from: b */
    private void m12852b(int i) {
        AbstractC3541b bVar = this.f16103f;
        if (bVar != null) {
            bVar.mo12821a(i);
        }
    }

    /* renamed from: d */
    public void m12844d() {
        this.f16103f = null;
    }

    /* renamed from: a */
    private BaseDownloadInfo m12857a(String str, String str2) {
        BaseDownloadInfo downloadInfo = BaseDownloadOperate.getDownloadInfo(this.f16099b, str2);
        if (downloadInfo == null) {
            downloadInfo = m12848b(str, str2);
            if (m12860a(downloadInfo)) {
                m12850b(downloadInfo);
            }
        }
        return downloadInfo;
    }

    /* renamed from: b */
    private BaseDownloadInfo m12848b(String str, String str2) {
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
    private void m12850b(BaseDownloadInfo baseDownloadInfo) {
        BaseDownloadOperate.addNewDownloadTask(this.f16099b, baseDownloadInfo);
    }
}
