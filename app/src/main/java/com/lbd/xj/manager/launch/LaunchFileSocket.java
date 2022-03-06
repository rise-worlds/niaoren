package com.lbd.xj.manager.launch;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Handler;
import com.common.utils.DeviceUtil;
import com.common.utils.log.LogUtils;
import com.lbd.xj.app.XJApp;
import com.lbd.xj.socket.SocketCallBack;
import com.lbd.xj.socket.SocketManagerServer;
import com.lbd.xj.socket.SocketServerUtils;
import p110z1.SocketConstants;

/* renamed from: com.lbd.xj.manager.launch.a */
/* loaded from: classes.dex */
public class LaunchFileSocket {

    /* renamed from: a */
    Handler f9479a;

    /* renamed from: b */
    private SocketServerUtils f9480b;

    public LaunchFileSocket(Context context) {
        m19711a();
        m19710a(context);
    }

    /* renamed from: a */
    private void m19710a(Context context) {
        SocketManagerServer.m19692b().m19697a(SocketConstants.f15228a, context);
    }

    /* renamed from: a */
    private void m19711a() {
        this.f9479a = new Handler();
        this.f9480b = new SocketServerUtils(SocketConstants.f15239b);
        this.f9480b.m19628a(new SocketCallBack() { // from class: com.lbd.xj.manager.launch.a.1
            @Override // com.lbd.xj.socket.SocketCallBack
            /* renamed from: a */
            public void mo19666a() {
            }

            @Override // com.lbd.xj.socket.SocketCallBack
            /* renamed from: b */
            public void mo19664b() {
            }

            @Override // com.lbd.xj.socket.SocketCallBack
            /* renamed from: a */
            public void mo19665a(String str) {
                if (!LaunchFileSocket.this.m19707a(str)) {
                    char c = 65535;
                    int hashCode = str.hashCode();
                    if (hashCode != 242587193) {
                        if (hashCode == 643855345 && str.equals(SocketConstants.f15233ae)) {
                            c = 1;
                        }
                    } else if (str.equals(SocketConstants.f15234af)) {
                        c = 0;
                    }
                    switch (c) {
                        case 0:
                        default:
                            return;
                        case 1:
                            LaunchFileSocket.this.f9480b.m19625a(DeviceUtil.getUUID());
                            return;
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public boolean m19707a(String str) {
        boolean z;
        if (str.contains(SocketConstants.f15232ad)) {
            final String replace = str.replace(SocketConstants.f15232ad, "");
            this.f9479a.post(new Runnable() { // from class: com.lbd.xj.manager.launch.a.2
                @Override // java.lang.Runnable
                public void run() {
                    ((ClipboardManager) XJApp.getInstance().getApplicationContext().getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText(null, "1" + replace));
                }
            });
            z = true;
        } else {
            z = false;
        }
        if (!str.contains(SocketConstants.f15235ag)) {
            return z;
        }
        LogUtils.m22037e(str.replace(SocketConstants.f15235ag, ""));
        return true;
    }
}
