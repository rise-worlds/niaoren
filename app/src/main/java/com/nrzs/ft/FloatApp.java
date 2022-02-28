package com.nrzs.ft;

import android.app.Application;
import android.content.Context;
import com.blankj.utilcode.util.SizeUtils;
import com.cyjh.mobileanjian.ipc.stuff.AnalyseResultWrapper;
import com.goldcoast.sdk.domain.EntryPoint;
import p110z1.FloatAssistManager;
import p110z1.FloatUtils;
import p110z1.NRZSLocalConfig;

/* renamed from: com.nrzs.ft.b */
/* loaded from: classes2.dex */
public class FloatApp {

    /* renamed from: a */
    public int f10711a;

    /* renamed from: b */
    private Context f10712b;

    /* renamed from: a */
    public void m18900a(Context context) {
        this.f10712b = context;
        if (NRZSLocalConfig.m12513c()) {
            EntryPoint.init((Application) context, FloatUtils.m12117a(), AnalyseResultWrapper.resultBuilder(context), 180, (byte) 0);
        }
        FloatAssistManager.m12397i().m12424a(this.f10712b);
        this.f10711a = SizeUtils.m23262a(300.0f);
    }

    /* renamed from: b */
    public void m18898b(Context context) {
        this.f10712b = context;
    }

    /* renamed from: a */
    public Context m18901a() {
        return this.f10712b;
    }

    /* renamed from: c */
    public int m18897c(Context context) {
        if (context.getResources().getIdentifier("config_showNavigationBar", "bool", "android") == 0) {
            return 0;
        }
        return context.getResources().getDimensionPixelSize(context.getResources().getIdentifier("navigation_bar_height", "dimen", "android"));
    }

    private FloatApp() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: FloatApp.java */
    /* renamed from: com.nrzs.ft.b$a */
    /* loaded from: classes2.dex */
    public static class C2007a {

        /* renamed from: a */
        private static final FloatApp f10713a = new FloatApp();

        private C2007a() {
        }
    }

    /* renamed from: b */
    public static FloatApp m18899b() {
        return C2007a.f10713a;
    }
}
