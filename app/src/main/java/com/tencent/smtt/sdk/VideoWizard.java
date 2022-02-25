package com.tencent.smtt.sdk;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.FrameLayout;
import com.tencent.smtt.export.external.DexLoader;
import dalvik.system.DexClassLoader;

/* renamed from: com.tencent.smtt.sdk.s */
/* loaded from: classes2.dex */
class VideoWizard {

    /* renamed from: a */
    protected DexLoader f13248a;

    public VideoWizard(DexLoader dexLoader) {
        this.f13248a = null;
        this.f13248a = dexLoader;
    }

    /* renamed from: a */
    public Object m16625a(Context context) {
        DexLoader dexLoader = this.f13248a;
        return dexLoader.newInstance("com.tencent.tbs.player.TbsPlayerProxy", new Class[]{Context.class, DexClassLoader.class}, context, dexLoader.getClassLoader());
    }

    /* renamed from: a */
    public boolean m16622a(Object obj, Bundle bundle, FrameLayout frameLayout, Object obj2) {
        Object obj3;
        if (obj2 != null) {
            obj3 = this.f13248a.invokeMethod(obj, "com.tencent.tbs.player.TbsPlayerProxy", "play", new Class[]{Bundle.class, FrameLayout.class, Object.class}, bundle, frameLayout, obj2);
        } else {
            obj3 = this.f13248a.invokeMethod(obj, "com.tencent.tbs.player.TbsPlayerProxy", "play", new Class[]{Bundle.class, FrameLayout.class}, bundle, frameLayout);
        }
        if (obj3 instanceof Boolean) {
            return ((Boolean) obj3).booleanValue();
        }
        return false;
    }

    /* renamed from: a */
    public void m16623a(Object obj, Activity activity, int i) {
        this.f13248a.invokeMethod(obj, "com.tencent.tbs.player.TbsPlayerProxy", "onActivity", new Class[]{Activity.class, Integer.TYPE}, activity, Integer.valueOf(i));
    }

    /* renamed from: a */
    public void m16624a(Object obj) {
        this.f13248a.invokeMethod(obj, "com.tencent.tbs.player.TbsPlayerProxy", "onUserStateChanged", new Class[0], new Object[0]);
    }
}
