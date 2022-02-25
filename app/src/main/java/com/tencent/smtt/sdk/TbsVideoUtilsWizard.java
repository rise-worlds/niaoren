package com.tencent.smtt.sdk;

import android.content.Context;
import com.tencent.smtt.export.external.DexLoader;

/* renamed from: com.tencent.smtt.sdk.p */
/* loaded from: classes2.dex */
class TbsVideoUtilsWizard {

    /* renamed from: a */
    private DexLoader f13235a;

    public TbsVideoUtilsWizard(DexLoader dexLoader) {
        this.f13235a = null;
        this.f13235a = dexLoader;
    }

    /* renamed from: a */
    public void m16641a(Context context, String str) {
        Object newInstance;
        DexLoader dexLoader = this.f13235a;
        if (dexLoader != null && (newInstance = dexLoader.newInstance("com.tencent.tbs.utils.TbsVideoUtilsProxy", new Class[0], new Object[0])) != null) {
            this.f13235a.invokeMethod(newInstance, "com.tencent.tbs.utils.TbsVideoUtilsProxy", "deleteVideoCache", new Class[]{Context.class, String.class}, context, str);
        }
    }

    /* renamed from: a */
    public String m16642a(Context context) {
        Object newInstance;
        Object invokeMethod;
        DexLoader dexLoader = this.f13235a;
        return (dexLoader == null || (newInstance = dexLoader.newInstance("com.tencent.tbs.utils.TbsVideoUtilsProxy", new Class[0], new Object[0])) == null || (invokeMethod = this.f13235a.invokeMethod(newInstance, "com.tencent.tbs.utils.TbsVideoUtilsProxy", "getCurWDPDecodeType", new Class[]{Context.class}, context)) == null) ? "" : String.valueOf(invokeMethod);
    }
}
