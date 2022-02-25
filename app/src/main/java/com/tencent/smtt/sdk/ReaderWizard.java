package com.tencent.smtt.sdk;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import com.tencent.smtt.export.external.DexLoader;
import com.tencent.smtt.sdk.TbsReaderView;

/* loaded from: classes2.dex */
public class ReaderWizard {

    /* renamed from: a */
    private DexLoader f12830a;

    /* renamed from: b */
    private TbsReaderView.ReaderCallback f12831b;

    public static boolean isSupportCurrentPlatform(Context context) {
        DexLoader a = m17043a();
        if (a == null) {
            return false;
        }
        Object invokeStaticMethod = a.invokeStaticMethod("com.tencent.tbs.reader.TbsReader", "isSupportCurrentPlatform", new Class[]{Context.class}, context);
        if (invokeStaticMethod instanceof Boolean) {
            return ((Boolean) invokeStaticMethod).booleanValue();
        }
        return false;
    }

    public static boolean isSupportExt(String str) {
        DexLoader a = m17043a();
        if (a == null) {
            return false;
        }
        Object invokeStaticMethod = a.invokeStaticMethod("com.tencent.tbs.reader.TbsReader", "isSupportExt", new Class[]{String.class}, str);
        if (invokeStaticMethod instanceof Boolean) {
            return ((Boolean) invokeStaticMethod).booleanValue();
        }
        return false;
    }

    public static Drawable getResDrawable(int i) {
        DexLoader a = m17043a();
        if (a != null) {
            Object invokeStaticMethod = a.invokeStaticMethod("com.tencent.tbs.reader.TbsReader", "getResDrawable", new Class[]{Integer.class}, Integer.valueOf(i));
            if (invokeStaticMethod instanceof Drawable) {
                return (Drawable) invokeStaticMethod;
            }
        }
        return null;
    }

    public static String getResString(int i) {
        DexLoader a = m17043a();
        if (a == null) {
            return "";
        }
        Object invokeStaticMethod = a.invokeStaticMethod("com.tencent.tbs.reader.TbsReader", "getResString", new Class[]{Integer.class}, Integer.valueOf(i));
        if (invokeStaticMethod instanceof String) {
            return (String) invokeStaticMethod;
        }
        return "";
    }

    public ReaderWizard(TbsReaderView.ReaderCallback readerCallback) {
        this.f12830a = null;
        this.f12831b = null;
        this.f12830a = m17043a();
        this.f12831b = readerCallback;
    }

    /* renamed from: a */
    private static DexLoader m17043a() {
        TbsWizard c = SDKEngine.m16828a(true).m16824c();
        if (c != null) {
            return c.m16629b();
        }
        return null;
    }

    public Object getTbsReader() {
        return this.f12830a.newInstance("com.tencent.tbs.reader.TbsReader", new Class[0], new Object[0]);
    }

    public boolean initTbsReader(Object obj, Context context) {
        DexLoader dexLoader = this.f12830a;
        if (dexLoader == null || obj == null) {
            Log.e("ReaderWizard", "initTbsReader:Unexpect null object!");
            return false;
        }
        Object invokeMethod = dexLoader.invokeMethod(obj, "com.tencent.tbs.reader.TbsReader", "init", new Class[]{Context.class, DexLoader.class, Object.class}, context, dexLoader, this);
        if (invokeMethod instanceof Boolean) {
            return ((Boolean) invokeMethod).booleanValue();
        }
        Log.e("ReaderWizard", "Unexpect return value type of call initTbsReader!");
        return false;
    }

    public boolean checkPlugin(Object obj, Context context, String str, boolean z) {
        DexLoader dexLoader = this.f12830a;
        if (dexLoader == null) {
            Log.e("ReaderWizard", "checkPlugin:Unexpect null object!");
            return false;
        }
        Object invokeMethod = dexLoader.invokeMethod(obj, "com.tencent.tbs.reader.TbsReader", "checkPlugin", new Class[]{Context.class, String.class, Boolean.class}, context, str, Boolean.valueOf(z));
        if (invokeMethod instanceof Boolean) {
            return ((Boolean) invokeMethod).booleanValue();
        }
        Log.e("ReaderWizard", "Unexpect return value type of call checkPlugin!");
        return false;
    }

    public boolean openFile(Object obj, Context context, Bundle bundle, FrameLayout frameLayout) {
        DexLoader dexLoader = this.f12830a;
        if (dexLoader == null) {
            Log.e("ReaderWizard", "openFile:Unexpect null object!");
            return false;
        }
        Object invokeMethod = dexLoader.invokeMethod(obj, "com.tencent.tbs.reader.TbsReader", "openFile", new Class[]{Context.class, Bundle.class, FrameLayout.class}, context, bundle, frameLayout);
        if (invokeMethod instanceof Boolean) {
            return ((Boolean) invokeMethod).booleanValue();
        }
        Log.e("ReaderWizard", "Unexpect return value type of call openFile!");
        return false;
    }

    public void onSizeChanged(Object obj, int i, int i2) {
        DexLoader dexLoader = this.f12830a;
        if (dexLoader == null) {
            Log.e("ReaderWizard", "onSizeChanged:Unexpect null object!");
        } else {
            dexLoader.invokeMethod(obj, "com.tencent.tbs.reader.TbsReader", "onSizeChanged", new Class[]{Integer.class, Integer.class}, new Integer(i), new Integer(i2));
        }
    }

    public void onCallBackAction(Integer num, Object obj, Object obj2) {
        TbsReaderView.ReaderCallback readerCallback = this.f12831b;
        if (readerCallback != null) {
            readerCallback.onCallBackAction(num, obj, obj2);
        }
    }

    public void doCommand(Object obj, Integer num, Object obj2, Object obj3) {
        DexLoader dexLoader = this.f12830a;
        if (dexLoader == null) {
            Log.e("ReaderWizard", "doCommand:Unexpect null object!");
        } else {
            dexLoader.invokeMethod(obj, "com.tencent.tbs.reader.TbsReader", "doCommand", new Class[]{Integer.class, Object.class, Object.class}, new Integer(num.intValue()), obj2, obj3);
        }
    }

    public void destroy(Object obj) {
        this.f12831b = null;
        DexLoader dexLoader = this.f12830a;
        if (dexLoader == null || obj == null) {
            Log.e("ReaderWizard", "destroy:Unexpect null object!");
        } else {
            dexLoader.invokeMethod(obj, "com.tencent.tbs.reader.TbsReader", "destroy", new Class[0], new Object[0]);
        }
    }

    public void userStatistics(Object obj, String str) {
        DexLoader dexLoader = this.f12830a;
        if (dexLoader == null) {
            Log.e("ReaderWizard", "userStatistics:Unexpect null object!");
        } else {
            dexLoader.invokeMethod(obj, "com.tencent.tbs.reader.TbsReader", "userStatistics", new Class[]{String.class}, str);
        }
    }
}
