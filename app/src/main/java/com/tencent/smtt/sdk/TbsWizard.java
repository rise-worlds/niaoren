package com.tencent.smtt.sdk;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.tencent.smtt.export.external.DexLoader;
import com.tencent.smtt.export.external.TbsCoreSettings;
import com.tencent.smtt.export.external.libwebp;
import com.tencent.smtt.sdk.TbsListener;
import com.tencent.smtt.utils.TbsLog;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.tencent.smtt.sdk.r */
/* loaded from: classes2.dex */
public class TbsWizard {

    /* renamed from: a */
    private Context f13241a;

    /* renamed from: b */
    private Context f13242b;

    /* renamed from: c */
    private String f13243c;

    /* renamed from: d */
    private String[] f13244d;

    /* renamed from: e */
    private DexLoader f13245e;

    /* renamed from: f */
    private String f13246f;

    /* renamed from: g */
    private String f13247g = null;

    public TbsWizard(Context context, Context context2, String str, String str2, String[] strArr, String str3) throws Exception {
        boolean z;
        boolean z2;
        this.f13241a = null;
        this.f13242b = null;
        this.f13243c = null;
        this.f13244d = null;
        this.f13245e = null;
        this.f13246f = "TbsDexOpt";
        TbsLog.m16531i("TbsWizard", "construction start...");
        if (context == null || ((context2 == null && TbsShareManager.getHostCorePathAppDefined() == null) || TextUtils.isEmpty(str) || strArr == null || strArr.length == 0)) {
            throw new Exception("TbsWizard paramter error:-1callerContext:" + context + "hostcontext" + context2 + "isEmpty" + TextUtils.isEmpty(str) + "dexfileList" + strArr);
        }
        this.f13241a = context.getApplicationContext();
        if (context2.getApplicationContext() != null) {
            this.f13242b = context2.getApplicationContext();
        } else {
            this.f13242b = context2;
        }
        this.f13243c = str;
        this.f13244d = strArr;
        this.f13246f = str2;
        for (int i = 0; i < this.f13244d.length; i++) {
            TbsLog.m16531i("TbsWizard", "#2 mDexFileList[" + i + "]: " + this.f13244d[i]);
        }
        TbsLog.m16531i("TbsWizard", "new DexLoader #2 libraryPath is " + str3 + " mCallerAppContext is " + this.f13241a + " dexOutPutDir is " + str2);
        this.f13245e = new DexLoader(str3, this.f13241a, this.f13244d, str2, QbSdk.f12802n);
        System.currentTimeMillis();
        m16632a(context);
        libwebp.loadWepLibraryIfNeed(context2, this.f13243c);
        if ("com.nd.android.pandahome2".equals(this.f13241a.getApplicationInfo().packageName)) {
            this.f13245e.invokeStaticMethod("com.tencent.tbs.common.beacon.X5CoreBeaconUploader", "getInstance", new Class[]{Context.class}, this.f13241a);
        }
        if (QbSdk.f12802n != null) {
            try {
                z = TbsPVConfig.getInstance(this.f13241a).getTbsCoreSandboxModeEnable();
            } catch (Throwable unused) {
                z = false;
            }
            try {
                z2 = "true".equals(String.valueOf(QbSdk.f12802n.get(TbsCoreSettings.TBS_SETTINGS_USE_SANDBOX)));
            } catch (Throwable th) {
                th.printStackTrace();
                z2 = false;
            }
            QbSdk.f12802n.put(TbsCoreSettings.TBS_SETTINGS_USE_SANDBOX, Boolean.valueOf(z && z2));
            this.f13245e.invokeStaticMethod("com.tencent.tbs.tbsshell.TBSShell", "initTbsSettings", new Class[]{Map.class}, QbSdk.f12802n);
        }
        int b = m16628b(context);
        if (b >= 0) {
            TbsLog.m16531i("TbsWizard", "construction end...");
            return;
        }
        throw new Exception("TbsWizard init error: " + b + "; msg: " + this.f13247g);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0018  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    void m16632a(android.content.Context r3) {
        /*
            r2 = this;
            java.util.Map<java.lang.String, java.lang.Object> r0 = com.tencent.smtt.sdk.QbSdk.f12802n
            if (r0 == 0) goto L_0x0015
            java.lang.String r1 = "check_tbs_validity"
            java.lang.Object r0 = r0.get(r1)
            boolean r1 = r0 instanceof java.lang.Boolean
            if (r1 == 0) goto L_0x0015
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            goto L_0x0016
        L_0x0015:
            r0 = 0
        L_0x0016:
            if (r0 == 0) goto L_0x001b
            com.tencent.smtt.utils.TbsCheckUtils.m16391b(r3)
        L_0x001b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsWizard.m16632a(android.content.Context):void");
    }

    /* renamed from: a */
    public void m16631a(Context context, Context context2, String str, String str2, String[] strArr, String str3) throws Exception {
        this.f13241a = context.getApplicationContext();
        if (this.f13242b.getApplicationContext() != null) {
            this.f13242b = this.f13242b.getApplicationContext();
        }
        this.f13243c = str;
        this.f13244d = strArr;
        this.f13246f = str2;
        libwebp.loadWepLibraryIfNeed(context2, this.f13243c);
        if (QbSdk.f12802n != null) {
            this.f13245e.invokeStaticMethod("com.tencent.tbs.tbsshell.TBSShell", "initTbsSettings", new Class[]{Map.class}, QbSdk.f12802n);
        }
        int b = m16628b(context);
        if (b < 0) {
            throw new Exception("continueInit init error: " + b + "; msg: " + this.f13247g);
        }
    }

    /* renamed from: b */
    private int m16628b(Context context) {
        Object obj;
        int i;
        if (this.f13242b != null || TbsShareManager.getHostCorePathAppDefined() == null) {
            TbsLog.m16531i("TbsWizard", "initTesRuntimeEnvironment callerContext is " + context + " mHostContext is " + this.f13242b + " mDexLoader is " + this.f13245e + " mtbsInstallLocation is " + this.f13243c + " mDexOptPath is " + this.f13246f);
            obj = this.f13245e.invokeStaticMethod("com.tencent.tbs.tbsshell.TBSShell", "initTesRuntimeEnvironment", new Class[]{Context.class, Context.class, DexLoader.class, String.class, String.class, String.class, Integer.TYPE, String.class}, context, this.f13242b, this.f13245e, this.f13243c, this.f13246f, TbsConfig.TBS_SDK_VERSIONNAME, 43697, QbSdk.m17061a());
        } else {
            obj = this.f13245e.invokeStaticMethod("com.tencent.tbs.tbsshell.TBSShell", "initTesRuntimeEnvironment", new Class[]{Context.class, Context.class, DexLoader.class, String.class, String.class, String.class, Integer.TYPE, String.class, String.class}, context, this.f13242b, this.f13245e, this.f13243c, this.f13246f, TbsConfig.TBS_SDK_VERSIONNAME, 43697, QbSdk.m17061a(), TbsShareManager.getHostCorePathAppDefined());
        }
        if (obj == null) {
            m16627c();
            m16626d();
            DexLoader dexLoader = this.f13245e;
            obj = dexLoader.invokeStaticMethod("com.tencent.tbs.tbsshell.TBSShell", "initTesRuntimeEnvironment", new Class[]{Context.class, Context.class, DexLoader.class, String.class, String.class}, context, this.f13242b, dexLoader, this.f13243c, this.f13246f);
        }
        if (obj == null) {
            i = -3;
        } else if (obj instanceof Integer) {
            i = ((Integer) obj).intValue();
        } else if (obj instanceof Throwable) {
            TbsCoreLoadStat.getInstance().m17035a(this.f13241a, TbsListener.ErrorCode.THROWABLE_INITTESRUNTIMEENVIRONMENT, (Throwable) obj);
            i = -5;
        } else {
            i = -4;
        }
        if (i < 0) {
            Object invokeStaticMethod = this.f13245e.invokeStaticMethod("com.tencent.tbs.tbsshell.TBSShell", "getLoadFailureDetails", new Class[0], new Object[0]);
            if (invokeStaticMethod instanceof Throwable) {
                Throwable th = (Throwable) invokeStaticMethod;
                this.f13247g = "#" + th.getMessage() + "; cause: " + th.getCause() + "; th: " + th;
            }
            if (invokeStaticMethod instanceof String) {
                this.f13247g = (String) invokeStaticMethod;
            }
        } else {
            this.f13247g = null;
        }
        return i;
    }

    /* renamed from: c */
    private void m16627c() {
        this.f13245e.invokeStaticMethod("com.tencent.tbs.tbsshell.TBSShell", "setTesSdkVersionName", new Class[]{String.class}, TbsConfig.TBS_SDK_VERSIONNAME);
    }

    /* renamed from: d */
    private void m16626d() {
        this.f13245e.setStaticField("com.tencent.tbs.tbsshell.TBSShell", "VERSION", 43697);
    }

    /* renamed from: a */
    public String m16633a() {
        String str = null;
        Object invokeStaticMethod = this.f13245e.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "invokeStaticMethod", new Class[]{Boolean.TYPE, String.class, String.class, Class[].class, Object[].class}, true, "com.tencent.smtt.util.CrashTracker", "getCrashExtraInfo", null, new Object[0]);
        if (invokeStaticMethod == null) {
            invokeStaticMethod = this.f13245e.invokeStaticMethod("com.tencent.smtt.util.CrashTracker", "getCrashExtraInfo", null, new Object[0]);
        }
        if (invokeStaticMethod != null) {
            str = String.valueOf(invokeStaticMethod) + " ReaderPackName=" + TbsReaderView.gReaderPackName + " ReaderPackVersion=" + TbsReaderView.gReaderPackVersion;
        }
        return str == null ? "X5 core get nothing..." : str;
    }

    /* renamed from: a */
    public boolean m16630a(Context context, String str, String str2, Bundle bundle) {
        Object invokeStaticMethod = this.f13245e.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "installLocalQbApk", new Class[]{Context.class, String.class, String.class, Bundle.class}, context, str, str2, bundle);
        if (invokeStaticMethod == null) {
            return false;
        }
        return ((Boolean) invokeStaticMethod).booleanValue();
    }

    /* renamed from: b */
    public DexLoader m16629b() {
        return this.f13245e;
    }
}
