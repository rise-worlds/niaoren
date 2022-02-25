package p110z1;

import android.media.Image;
import android.util.Log;
import com.lody.virtual.helper.utils.Reflect;
import com.lody.virtual.helper.utils.VLog;
import java.nio.ByteBuffer;

/* renamed from: z1.ajh */
/* loaded from: classes3.dex */
public class Hook_getScreenSize {

    /* renamed from: a */
    public static String f16049a = "com.cyjh.mobileanjian.screencap.ScreenShoterV3";

    /* renamed from: b */
    public static String f16050b = "convertImageToScreenShotImage";

    /* renamed from: c */
    public static String f16051c = "(Landroid/media/Image;)Lcom/cyjh/mobileanjian/screencap/ScreenShotImage;";

    /* compiled from: Hook_getScreenSize.java */
    /* renamed from: z1.ajh$a */
    /* loaded from: classes3.dex */
    public class C3523a {

        /* renamed from: a */
        public byte[] f16052a;

        /* renamed from: b */
        public int f16053b;

        /* renamed from: c */
        public int f16054c;

        /* renamed from: d */
        public int f16055d;

        /* renamed from: e */
        public int f16056e;

        /* renamed from: f */
        public int f16057f;

        public C3523a() {
        }
    }

    /* renamed from: a */
    public static Object m12935a(Object obj) {
        try {
            byte[] bArr = (byte[]) Reflect.m18998on(obj).get("data");
            int intValue = ((Integer) Reflect.m18998on(obj).get("width")).intValue();
            ((Integer) Reflect.m18998on(obj).get("height")).intValue();
            int intValue2 = ((Integer) Reflect.m18998on(obj).get("pixelStride")).intValue();
            int intValue3 = ((Integer) Reflect.m18998on(obj).get("rowStride")).intValue();
            int intValue4 = ((Integer) Reflect.m18998on(obj).get("rotation")).intValue();
            Object newInstance = Reflect.m18998on(obj).get().getClass().newInstance();
            Reflect.m18998on(newInstance).set("width", Integer.valueOf(intValue));
            int i = (intValue * 16) / 9;
            Reflect.m18998on(newInstance).set("height", Integer.valueOf(i));
            Reflect.m18998on(newInstance).set("rowStride", Integer.valueOf(intValue3));
            Reflect.m18998on(newInstance).set("pixelStride", Integer.valueOf(intValue2));
            Reflect.m18998on(newInstance).set("rotation", Integer.valueOf(intValue4));
            byte[] bArr2 = new byte[intValue * i * 4];
            Reflect.m18998on(newInstance).set("data", bArr2);
            ByteBuffer wrap = ByteBuffer.wrap(bArr2);
            for (int i2 = 0; i2 < i; i2++) {
                wrap.put(bArr, intValue3 * i2, intValue * 4);
            }
            return newInstance;
        } catch (Exception e) {
            if (VLog.OPEN_LOG) {
                e.printStackTrace();
            }
            return obj;
        }
    }

    /* renamed from: b */
    public static Object m12933b(Object obj) {
        try {
            byte[] bArr = (byte[]) Reflect.m18998on(obj).get("data");
            ((Integer) Reflect.m18998on(obj).get("width")).intValue();
            int intValue = ((Integer) Reflect.m18998on(obj).get("height")).intValue();
            int intValue2 = ((Integer) Reflect.m18998on(obj).get("pixelStride")).intValue();
            int intValue3 = ((Integer) Reflect.m18998on(obj).get("rowStride")).intValue();
            int intValue4 = ((Integer) Reflect.m18998on(obj).get("rotation")).intValue();
            Object newInstance = Reflect.m18998on(obj).get().getClass().newInstance();
            int i = (intValue * 16) / 9;
            Reflect.m18998on(newInstance).set("width", Integer.valueOf(i));
            Reflect.m18998on(newInstance).set("height", Integer.valueOf(intValue));
            int i2 = i * 4;
            Reflect.m18998on(newInstance).set("rowStride", Integer.valueOf(i2));
            Reflect.m18998on(newInstance).set("pixelStride", Integer.valueOf(intValue2));
            Reflect.m18998on(newInstance).set("rotation", Integer.valueOf(intValue4));
            byte[] bArr2 = new byte[i * intValue * 4];
            Reflect.m18998on(newInstance).set("data", bArr2);
            ByteBuffer wrap = ByteBuffer.wrap(bArr2);
            for (int i3 = 0; i3 < intValue; i3++) {
                wrap.put(bArr, intValue3 * i3, i2);
            }
            return newInstance;
        } catch (Exception e) {
            e.printStackTrace();
            return obj;
        }
    }

    /* renamed from: c */
    private static Object m12931c(Object obj) {
        if (((Integer) Reflect.m18998on(obj).get("width")).intValue() > ((Integer) Reflect.m18998on(obj).get("height")).intValue()) {
            return m12933b(obj);
        }
        return m12935a(obj);
    }

    /* renamed from: a */
    public static Object m12934a(Object obj, Image image) {
        return m12931c(m12932b(obj, image));
    }

    /* renamed from: b */
    public static Object m12932b(Object obj, Image image) {
        try {
            Log.w("TianTian", "load should not be here");
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
