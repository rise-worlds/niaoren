package p110z1;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import com.cyjh.ddy.media.media.ActionCode;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.ipc.VActivityManager;
import com.nrzs.core.models.AppData;
import com.nrzs.core.models.PackageAppData;
import com.nrzs.game.C2029R;
import com.nrzs.va.VirtualCoreProxy;

/* renamed from: z1.aol */
/* loaded from: classes3.dex */
public class FloatLoadingAppDialog extends Dialog {

    /* renamed from: a */
    private static FloatLoadingAppDialog f16980a;

    /* renamed from: b */
    private PackageAppData f16981b;

    /* renamed from: c */
    private int f16982c;

    /* renamed from: d */
    private String f16983d;

    /* renamed from: e */
    private AppData f16984e;

    public FloatLoadingAppDialog(Context context, int i, String str, AppData aVar) {
        super(context);
        m12063a();
        this.f16982c = i;
        this.f16983d = str;
        this.f16984e = aVar;
    }

    /* renamed from: a */
    public void m12063a() {
        if (getWindow() == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 24) {
            getWindow().setType(2005);
        } else if (Build.VERSION.SDK_INT >= 26) {
            getWindow().setType(2038);
        } else {
            getWindow().setType(ActionCode.CtrlConnectRefuse_2002);
        }
    }

    /* renamed from: a */
    public static void m12062a(Context context, int i, String str, AppData aVar) {
        if (f16980a == null) {
            f16980a = new FloatLoadingAppDialog(context, i, str, aVar);
        } else {
            m12060b();
            f16980a = new FloatLoadingAppDialog(context, i, str, aVar);
        }
        if (!f16980a.isShowing()) {
            f16980a.show();
        }
    }

    /* renamed from: b */
    public static void m12060b() {
        FloatLoadingAppDialog aolVar = f16980a;
        if (aolVar != null) {
            aolVar.dismiss();
            f16980a = null;
        }
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setCancelable(false);
        m12059c();
        m12058d();
    }

    /* renamed from: c */
    public void m12059c() {
        setContentView(C2029R.layout.nrzs_game_dialog_va_loading);
    }

    /* renamed from: d */
    public void m12058d() {
        this.f16981b = PackageAppDataStorage.m12941a().m12937c(this.f16983d);
        final Intent launchIntent = VirtualCore.get().getLaunchIntent(this.f16983d, this.f16982c);
        if (launchIntent == null) {
            m12060b();
            return;
        }
        TencentSupport.f16013a.contains(this.f16984e.mo18958e());
        VUiKit.m11713a().mo3333a(new Runnable() { // from class: z1.-$$Lambda$aol$CuPu4nDERzP8C2gtZw63-o83zm8
            @Override // java.lang.Runnable
            public final void run() {
                FloatLoadingAppDialog.this.m12061a(launchIntent);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public /* synthetic */ void m12061a(Intent intent) {
        if (!this.f16981b.f10573d) {
            try {
                VirtualCoreProxy.preOpt(this.f16981b.f10570a);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        IntentToAssistService.m12801a(GameApp.getInstance().m13006b(), this.f16983d, this.f16982c);
        VActivityManager.get().startActivity(intent, this.f16982c);
        m12060b();
    }
}
