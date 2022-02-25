package com.cyjh.mobileanjian.ipc.p042a;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import com.cyjh.mobileanjian.ipc.RootManager;
import com.cyjh.mobileanjian.ipc.share.proto.Ipc;
import com.cyjh.mobileanjian.ipc.share.proto.IpcRaw;
import com.cyjh.mqm.MQCompiler;

/* renamed from: com.cyjh.mobileanjian.ipc.a.c */
/* loaded from: classes.dex */
public final class RecordScriptStub {

    /* renamed from: a */
    private static String f8208a = null;

    /* renamed from: b */
    private static boolean f8209b = false;

    /* renamed from: c */
    private static OnRecordScriptHandler f8210c;

    /* renamed from: a */
    private static void m21056a(Handler handler) {
        f8210c = new OnRecordScriptHandler(handler);
        ScriptRecorder a = ScriptRecorder.m21052a();
        a.f8213b = f8210c;
        if (a.f8212a != null) {
            a.f8212a.f8812h = a.f8213b;
        }
        a.m21049b();
    }

    /* renamed from: a */
    private static void m21055a(boolean z) {
        ScriptRecorder.m21052a().m21050a(z);
    }

    /* renamed from: a */
    private static void m21058a() {
        Log.e(CommonConstants.f8202a, "startRecord...");
        ScriptRecorder a = ScriptRecorder.m21052a();
        if (a.f8212a == null) {
            RootManager.m21044a().f8225g = 2;
            RootManager.m21044a().m21039b();
            return;
        }
        a.f8212a.m20531a(Ipc.IpcMessage.newBuilder().setCmd(20).build());
    }

    /* renamed from: b */
    private static void m21054b() {
        ScriptRecorder a = ScriptRecorder.m21052a();
        if (a.f8212a != null) {
            a.f8212a.m20531a(IpcRaw.m20933a(22));
        }
    }

    /* renamed from: a */
    private static boolean m21057a(Context context, String str) {
        f8208a = "";
        String str2 = f8210c.f8206a;
        if (TextUtils.isEmpty(str2)) {
            return false;
        }
        if (!str.endsWith(".lc")) {
            str = str + ".lc";
        }
        String compile = MQCompiler.compile(str2, "", str, context.getFilesDir().getParent());
        f8208a = compile;
        return compile == "";
    }

    /* renamed from: c */
    private static String m21053c() {
        return f8208a;
    }
}
