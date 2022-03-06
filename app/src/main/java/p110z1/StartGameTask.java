package p110z1;

import android.support.annotation.Nullable;
import com.lbd.xj.socket.C1545f;
import com.lbd.xj.socket.SocketManagerServer;
import com.lbd.xj.socket.model.AppInfo;

/* renamed from: z1.adj */
/* loaded from: classes3.dex */
public class StartGameTask extends C1545f.AbstractC1551c {

    /* renamed from: a */
    private AppInfo f15295a;

    @Override // com.lbd.xj.socket.C1545f.AbstractRunnableC1552d
    public void onSuccess(@Nullable Object obj) {
    }

    public StartGameTask(AppInfo appInfo) {
        this.f15295a = appInfo;
    }

    @Override // com.lbd.xj.socket.C1545f.AbstractRunnableC1552d
    @Nullable
    public Object doInBackground() throws Throwable {
        AppInfo appInfo = this.f15295a;
        if (appInfo == null) {
            return null;
        }
        appInfo.appIcon = "";
        String a = apa.m11879a(appInfo);
        FileWriteUtils.m14068e(a);
        SocketManagerServer.m19692b().m19695a(a, SocketConstants.f15256s);
        return null;
    }
}
