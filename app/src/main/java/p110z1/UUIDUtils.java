package p110z1;

import com.cyjh.ddy.media.p035a.ResultTypeConstant;
import com.cyjh.ddysdk.device.base.constants.DdyConstants;
import com.cyjh.mobileanjian.ipc.utils.RpcError;
import com.googlecode.tesseract.android.TessBaseAPI;
import java.util.UUID;

/* renamed from: z1.ft */
/* loaded from: classes3.dex */
public class UUIDUtils {

    /* renamed from: a */
    public static String[] f21701a = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", RpcError.f8691a, "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", ResultTypeConstant.f7213z, "1", "2", DdyConstants.APP_INSTALL_ERROR, DdyConstants.APP_INSTALL_DOWNLOADING, DdyConstants.APP_INSTALL_INSTALLING, DdyConstants.APP_INSTALL_UNINSTALL, DdyConstants.APP_INSTALL_INSTALLED, "8", "9", "A", "B", "C", "D", "E", TessBaseAPI.f9205f, "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", TessBaseAPI.f9204e, "U", "V", "W", "X", "Y", "Z"};

    /* renamed from: a */
    public static String m2814a() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /* renamed from: a */
    public static String m2813a(int i) {
        StringBuilder sb = new StringBuilder(UUID.randomUUID().toString());
        while (sb.length() < i) {
            sb.append(UUID.randomUUID().toString());
        }
        return sb.substring(0, i);
    }
}
