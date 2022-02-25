package p110z1;

import android.content.Context;
import android.graphics.Point;
import android.view.InputDevice;
import android.view.WindowManager;

/* compiled from: DeviceUtils.java */
/* renamed from: z1.dca */
/* loaded from: classes3.dex */
public class dca {

    /* renamed from: a */
    private static int f21291a = -1;

    /* renamed from: a */
    public static int m3243a() {
        if (f21291a == -1) {
            int[] deviceIds = InputDevice.getDeviceIds();
            if (deviceIds != null) {
                int length = deviceIds.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        break;
                    }
                    InputDevice device = InputDevice.getDevice(deviceIds[i]);
                    if (device.supportsSource(2)) {
                        f21291a = device.getId();
                        break;
                    }
                    i++;
                }
            }
            int i2 = f21291a;
            if (i2 != -1) {
                return i2;
            }
            f21291a = 0;
        }
        return f21291a;
    }

    /* renamed from: a */
    public static Point m3242a(Context context) {
        WindowManager windowManager;
        Point point = new Point();
        if (!(context == null || (windowManager = (WindowManager) context.getSystemService("window")) == null)) {
            windowManager.getDefaultDisplay().getRealSize(point);
        }
        return point;
    }
}
