package com.tencent.smtt.sdk;

import android.content.Context;
import android.util.Log;
import com.tencent.smtt.export.external.DexLoader;

/* loaded from: classes2.dex */
public class TbsMediaFactory {

    /* renamed from: a */
    private Context f12948a;

    /* renamed from: b */
    private TbsWizard f12949b = null;

    /* renamed from: c */
    private DexLoader f12950c = null;

    public TbsMediaFactory(Context context) {
        this.f12948a = null;
        this.f12948a = context.getApplicationContext();
        m16975a();
    }

    /* renamed from: a */
    private void m16975a() {
        if (this.f12948a == null) {
            Log.e("TbsVideo", "TbsVideo needs context !!");
            return;
        }
        if (this.f12949b == null) {
            SDKEngine.m16828a(true).m16830a(this.f12948a, false, false);
            this.f12949b = SDKEngine.m16828a(true).m16832a();
            TbsWizard rVar = this.f12949b;
            if (rVar != null) {
                this.f12950c = rVar.m16629b();
            }
        }
        if (this.f12949b == null || this.f12950c == null) {
            throw new RuntimeException("tbs core dex(s) load failure !!!");
        }
    }

    public TbsMediaPlayer createPlayer() {
        DexLoader dexLoader;
        if (this.f12949b != null && (dexLoader = this.f12950c) != null) {
            return new TbsMediaPlayer(new TbsMediaPlayerWizard(dexLoader, this.f12948a));
        }
        throw new RuntimeException("tbs core dex(s) did not loaded !!!");
    }
}
