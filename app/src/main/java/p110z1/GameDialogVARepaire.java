package p110z1;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.p061os.VUserManager;
import com.lody.virtual.remote.InstalledAppInfo;
import com.nrzs.core.models.AppData;
import com.nrzs.core.models.AppInfo;
import com.nrzs.core.models.AppInfoLite;
import com.nrzs.core.models.MultiplePackageAppData;
import com.nrzs.core.models.PackageAppData;
import com.nrzs.game.C2029R;
import com.nrzs.p072va.VirtualCoreProxy;
import java.io.IOException;
import java.util.concurrent.Callable;
import p110z1.GameDialogVARepaire;

/* renamed from: z1.aoo */
/* loaded from: classes3.dex */
public class GameDialogVARepaire extends Dialog {

    /* renamed from: a */
    public AppRepository f16993a;

    /* renamed from: b */
    private String f16994b;

    public GameDialogVARepaire(Context context, String str) {
        super(context);
        this.f16994b = str;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setCancelable(false);
        m12053a();
        m12042b();
    }

    /* renamed from: a */
    private void m12053a() {
        setContentView(C2029R.layout.nrzs_game_dialog_va_repaire);
    }

    /* renamed from: b */
    private void m12042b() {
        this.f16993a = new AppRepository(getContext());
        m12038c();
    }

    /* renamed from: c */
    private void m12038c() {
        if (TextUtils.isEmpty(this.f16994b)) {
            ToastUtils.m23030a("无需修复");
            dismiss();
            return;
        }
        VUiKit.m11713a().mo3332a(new Callable() { // from class: z1.-$$Lambda$aoo$wIuibIeykXjNsUWCCTsAOfwvMt4
            @Override // java.util.concurrent.Callable
            public final Object call() {
                Boolean g;
                g = GameDialogVARepaire.this.m12032g();
                return g;
            }
        }).mo3282b(new C37792()).mo3285a(new FailCallback<Throwable>() { // from class: z1.aoo.1
            /* renamed from: a */
            public void onFail(Throwable th) {
                GameDialogVARepaire.this.m12033f();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: GameDialogVARepaire.java */
    /* renamed from: z1.aoo$2 */
    /* loaded from: classes3.dex */
    public class C37792 implements DoneCallback<Boolean> {
        C37792() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: GameDialogVARepaire.java */
        /* renamed from: z1.aoo$2$2 */
        /* loaded from: classes3.dex */
        public class C37812 implements DoneCallback<AppData> {
            C37812() {
            }

            /* renamed from: a */
            public void onDone(final AppData aVar) {
                VUiKit.m11713a().mo3332a(new Callable<Boolean>() { // from class: z1.aoo.2.2.3
                    /* renamed from: a */
                    public Boolean call() throws Exception {
                        if (aVar != null) {
                            boolean a = GameDialogVARepaire.this.m12050a(aVar);
                            if (a) {
                                aoy.m11885a(NRZSFileConfig.f16552j, aVar.mo18958e());
                                return Boolean.valueOf(a);
                            }
                            throw new IllegalStateException();
                        }
                        throw new IllegalStateException();
                    }
                }).mo3282b(new DoneCallback<Boolean>() { // from class: z1.aoo.2.2.2
                    /* renamed from: a */
                    public void onDone(Boolean bool) {
                        if (TencentSupport.f16014b.contains(GameDialogVARepaire.this.f16994b)) {
                            VUiKit.m11713a().mo3332a(new Callable<AppInfoLite>() { // from class: z1.aoo.2.2.2.3
                                /* renamed from: a */
                                public AppInfoLite call() throws Exception {
                                    AppInfo a = GameDialogVARepaire.this.f16993a.mo12955a(GameDialogVARepaire.this.getContext().getApplicationContext(), GameDialogVARepaire.this.f16994b, true);
                                    if (a == null) {
                                        return null;
                                    }
                                    return new AppInfoLite(a.f10550a, a.f10551b, a.f10552c, a.f10556g);
                                }
                            }).mo3282b(new DoneCallback<AppInfoLite>() { // from class: z1.aoo.2.2.2.2
                                /* renamed from: a */
                                public void onDone(AppInfoLite appInfoLite) {
                                    if (appInfoLite == null) {
                                        ToastUtils.m23030a("未检测到可运行的游戏");
                                    } else {
                                        GameDialogVARepaire.this.m12052a(appInfoLite);
                                    }
                                }
                            }).mo3285a(new FailCallback<Throwable>() { // from class: z1.aoo.2.2.2.1
                                /* renamed from: a */
                                public void onFail(Throwable th) {
                                    GameDialogVARepaire.this.m12033f();
                                }
                            });
                        } else {
                            GameDialogVARepaire.this.m12034e();
                        }
                    }
                }).mo3285a(new FailCallback<Throwable>() { // from class: z1.aoo.2.2.1
                    /* renamed from: a */
                    public void onFail(Throwable th) {
                        GameDialogVARepaire.this.m12033f();
                    }
                });
            }
        }

        /* renamed from: a */
        public void onDone(Boolean bool) {
            if (bool.booleanValue()) {
                GameDialogVARepaire.this.f16993a.mo12951a(GameDialogVARepaire.this.f16994b).mo3282b(new C37812()).mo3285a(new FailCallback<Throwable>() { // from class: z1.aoo.2.1
                    /* renamed from: a */
                    public void onFail(Throwable th) {
                        GameDialogVARepaire.this.m12033f();
                    }
                });
                return;
            }
            GameDialogVARepaire.this.m12036d();
            ToastUtils.m23030a("游戏未添加");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: g */
    public /* synthetic */ Boolean m12032g() throws Exception {
        return Boolean.valueOf(VirtualCore.get().isAppInstalled(this.f16994b));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d */
    public void m12036d() {
        VAManager.m12070i().m12071h();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public boolean m12050a(AppData aVar) {
        if (aVar instanceof PackageAppData) {
            return this.f16993a.mo12950a(((PackageAppData) aVar).f10570a, 0);
        }
        MultiplePackageAppData cVar = (MultiplePackageAppData) aVar;
        return this.f16993a.mo12950a(cVar.f10558a.packageName, cVar.f10559b);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: GameDialogVARepaire.java */
    /* renamed from: z1.aoo$a */
    /* loaded from: classes3.dex */
    public class C3788a {

        /* renamed from: b */
        private PackageAppData f17007b;

        /* renamed from: c */
        private int f17008c;

        /* renamed from: d */
        private boolean f17009d;

        C3788a() {
        }
    }

    /* renamed from: a */
    protected void m12052a(final AppInfoLite appInfoLite) {
        final C3788a aVar = new C3788a();
        VUiKit.m11713a().mo3333a(new Runnable() { // from class: z1.-$$Lambda$aoo$6ZH7qJwWfnC7bEVdnHBKX8exw1g
            @Override // java.lang.Runnable
            public final void run() {
                GameDialogVARepaire.this.m12051a(appInfoLite, aVar);
            }
        }).mo3285a(new FailCallback() { // from class: z1.-$$Lambda$aoo$JEvzHhTLPeo_K9542NPoc_pCM4M
            @Override // p110z1.FailCallback
            public final void onFail(Object obj) {
                GameDialogVARepaire.this.m12041b((Throwable) obj);
            }
        }).mo3294a(new DoneCallback() { // from class: z1.-$$Lambda$aoo$IDo96GuPc4C6PbQbtwrW3qzb9vE
            @Override // p110z1.DoneCallback
            public final void onDone(Object obj) {
                GameDialogVARepaire.m12040b(GameDialogVARepaire.C3788a.this, appInfoLite, (Void) obj);
            }
        }).mo3282b(new DoneCallback() { // from class: z1.-$$Lambda$aoo$WqCP1xonp9DBCNgaU7eLCRWh4Is
            @Override // p110z1.DoneCallback
            public final void onDone(Object obj) {
                GameDialogVARepaire.this.m12046a(aVar, appInfoLite, (Void) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public /* synthetic */ void m12051a(AppInfoLite appInfoLite, C3788a aVar) {
        int i = 0;
        InstalledAppInfo installedAppInfo = VirtualCore.get().getInstalledAppInfo(appInfoLite.f10533d, 0);
        aVar.f17009d = installedAppInfo != null;
        if (aVar.f17009d) {
            int[] installedUsers = installedAppInfo.getInstalledUsers();
            int length = installedUsers.length;
            while (true) {
                if (i >= installedUsers.length) {
                    break;
                } else if (installedUsers[i] != i) {
                    length = i;
                    break;
                } else {
                    i++;
                }
            }
            aVar.f17008c = length;
            if (VUserManager.get().getUserInfo(length) == null) {
                if (VUserManager.get().createUser("Space " + (length + 1), 2) == null) {
                    throw new IllegalStateException();
                }
            }
            if (!VirtualCore.get().installPackageAsUser(length, appInfoLite.f10533d)) {
                throw new IllegalStateException();
            }
        } else if (!this.f16993a.mo12952a(appInfoLite).isSuccess) {
            throw new IllegalStateException();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public /* synthetic */ void m12041b(Throwable th) {
        m12033f();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public static /* synthetic */ void m12040b(C3788a aVar, AppInfoLite appInfoLite, Void r2) {
        aVar.f17007b = PackageAppDataStorage.m12941a().m12937c(appInfoLite.f10533d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public /* synthetic */ void m12046a(C3788a aVar, AppInfoLite appInfoLite, Void r6) {
        if (!(aVar.f17009d && aVar.f17008c != 0)) {
            PackageAppData dVar = aVar.f17007b;
            dVar.f10575f = true;
            dVar.mo18965a(appInfoLite.f10536g);
            m12049a((AppData) dVar, appInfoLite.f10533d, true);
            return;
        }
        MultiplePackageAppData cVar = new MultiplePackageAppData(aVar.f17007b, aVar.f17008c);
        cVar.f10561d = true;
        cVar.mo18965a(appInfoLite.f10536g);
        m12049a((AppData) cVar, appInfoLite.f10533d, false);
    }

    /* renamed from: a */
    protected void m12049a(final AppData aVar, final String str, final boolean z) {
        VUiKit.m11713a().mo3333a(new Runnable() { // from class: z1.-$$Lambda$aoo$QbSI586l55zfCK_L8E8LR4mhvBw
            @Override // java.lang.Runnable
            public final void run() {
                GameDialogVARepaire.m12043a(z, str);
            }
        }).mo3285a(new FailCallback() { // from class: z1.-$$Lambda$aoo$yEA8gg4LFTODApyRBu2zfOuGhMw
            @Override // p110z1.FailCallback
            public final void onFail(Object obj) {
                GameDialogVARepaire.this.m12047a((Throwable) obj);
            }
        }).mo3282b(new DoneCallback() { // from class: z1.-$$Lambda$aoo$aUoMDUmBb8cdTpYTZdIR-bbCXvI
            @Override // p110z1.DoneCallback
            public final void onDone(Object obj) {
                GameDialogVARepaire.this.m12048a(aVar, (Void) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public static /* synthetic */ void m12043a(boolean z, String str) {
        long currentTimeMillis = System.currentTimeMillis();
        if (z) {
            try {
                VirtualCoreProxy.preOpt(str);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
        if (currentTimeMillis2 < 1500) {
            try {
                Thread.sleep(1500 - currentTimeMillis2);
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public /* synthetic */ void m12047a(Throwable th) {
        m12033f();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public /* synthetic */ void m12048a(AppData aVar, Void r4) {
        if (aVar instanceof PackageAppData) {
            PackageAppData dVar = (PackageAppData) aVar;
            dVar.f10575f = false;
            dVar.f10574e = true;
        } else if (aVar instanceof MultiplePackageAppData) {
            MultiplePackageAppData cVar = (MultiplePackageAppData) aVar;
            cVar.f10561d = false;
            cVar.f10560c = true;
        }
        m12034e();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e */
    public void m12034e() {
        m12036d();
        ToastUtils.m23030a("已修复完成，请重新运行游戏");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: f */
    public void m12033f() {
        m12036d();
        ToastUtils.m23030a("修复失败，请重新修复");
    }
}
