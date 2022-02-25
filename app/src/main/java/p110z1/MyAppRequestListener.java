package p110z1;

import android.content.Context;
import android.widget.Toast;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.ipc.VActivityManager;
import com.lody.virtual.helper.utils.VLog;
import com.nrzs.p072va.AppInstallOptions;
import com.nrzs.p072va.AppInstallResult;
import com.nrzs.p072va.VirtualCoreProxy;

/* renamed from: z1.aiy */
/* loaded from: classes3.dex */
public class MyAppRequestListener implements VirtualCore.AppRequestListener {

    /* renamed from: a */
    private final Context f16036a;

    public MyAppRequestListener(Context context) {
        this.f16036a = context;
    }

    @Override // com.lody.virtual.client.core.VirtualCore.AppRequestListener
    public void onRequestInstall(String str) {
        m12970a("Start installing: " + str);
        VirtualCoreProxy.installPackage(str, AppInstallOptions.makeOptions(false), $$Lambda$aiy$OC9LSXmpllen7AH_TXgl6e3_DI.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public static /* synthetic */ void m12971a(AppInstallResult appInstallResult) {
        if (appInstallResult.isSuccess) {
            m12970a("Install " + appInstallResult.packageName + " success.");
            boolean launchApp = VActivityManager.get().launchApp(0, appInstallResult.packageName);
            StringBuilder sb = new StringBuilder();
            sb.append("launch app ");
            sb.append(launchApp ? "success." : "fail.");
            m12970a(sb.toString());
            return;
        }
        m12970a("Install " + appInstallResult.packageName + " fail, reason: " + appInstallResult.error);
    }

    /* renamed from: a */
    private static void m12970a(String str) {
        VLog.m18992e("AppInstaller", str);
    }

    @Override // com.lody.virtual.client.core.VirtualCore.AppRequestListener
    public void onRequestUninstall(String str) {
        Context context = this.f16036a;
        Toast.makeText(context, "Intercept uninstall request: " + str, 0).show();
    }
}
