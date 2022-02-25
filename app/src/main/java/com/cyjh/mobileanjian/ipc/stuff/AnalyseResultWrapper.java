package com.cyjh.mobileanjian.ipc.stuff;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;
import com.goldcoast.sdk.domain.AnalyseResult;
import p110z1.C4985cm;

/* loaded from: classes.dex */
public class AnalyseResultWrapper {

    /* renamed from: a */
    private static Context f8416a;

    /* renamed from: b */
    private static Handler f8417b = new Handler() { // from class: com.cyjh.mobileanjian.ipc.stuff.AnalyseResultWrapper.1
        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            super.handleMessage(message);
            String string = message.getData().getString(C4985cm.f20833c);
            int i = message.getData().getInt("status");
            Log.i("MyApp", string);
            if (i == 1) {
                Toast.makeText(AnalyseResultWrapper.f8416a, string, 0).show();
            }
        }
    };

    public static AnalyseResult resultBuilder(Context context) {
        f8416a = context;
        return new AnalyseResult() { // from class: com.cyjh.mobileanjian.ipc.stuff.AnalyseResultWrapper.2
            @Override // com.goldcoast.sdk.domain.AnalyseResult
            public final void onSuccess(String str) {
                AnalyseResultWrapper.m20929a(str, 0);
            }

            @Override // com.goldcoast.sdk.domain.AnalyseResult
            public final void onFailed(String str) {
                AnalyseResultWrapper.m20929a(str, 1);
            }

            @Override // com.goldcoast.sdk.domain.AnalyseResult
            public final void onProgress(String str) {
                AnalyseResultWrapper.m20929a(str, 2);
            }

            @Override // com.goldcoast.sdk.domain.AnalyseResult
            public final void onException(String str) {
                AnalyseResultWrapper.m20929a(str, 3);
            }
        };
    }

    public static AnalyseResult resultBuilder(Context context, Handler handler) {
        f8416a = context;
        f8417b = null;
        f8417b = handler;
        return resultBuilder(context);
    }

    /* renamed from: b */
    private static void m20928b(String str, int i) {
        Handler handler = f8417b;
        if (handler != null) {
            Message obtainMessage = handler.obtainMessage();
            Bundle bundle = new Bundle();
            bundle.putString(C4985cm.f20833c, str);
            bundle.putInt("status", i);
            obtainMessage.setData(bundle);
            f8417b.sendMessage(obtainMessage);
        }
    }

    /* renamed from: a */
    static /* synthetic */ void m20929a(String str, int i) {
        Handler handler = f8417b;
        if (handler != null) {
            Message obtainMessage = handler.obtainMessage();
            Bundle bundle = new Bundle();
            bundle.putString(C4985cm.f20833c, str);
            bundle.putInt("status", i);
            obtainMessage.setData(bundle);
            f8417b.sendMessage(obtainMessage);
        }
    }
}
