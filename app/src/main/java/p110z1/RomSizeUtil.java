package p110z1;

import android.util.Log;
import com.common.utils.StringUtil;
import com.lbd.xj.app.AppConfig;
import com.lbd.xj.manager.XJRenderActivityManager;
import com.lbd.xj.socket.model.ResolvingModle;

/* renamed from: z1.aen */
/* loaded from: classes3.dex */
public class RomSizeUtil {
    /* renamed from: a */
    public static int[] m13926a() {
        int[] iArr = new int[2];
        String str = (String) PreferencesUtil.m13937a().m13927b("RESOLVING", "");
        String str2 = AppConfig.f9434a;
        Log.d(str2, "RomSizeUtil:" + str);
        if (!StringUtil.isEmpty(str)) {
            ResolvingModle resolvingModle = (ResolvingModle) GsonUtil.m13967a(str, ResolvingModle.class);
            iArr[0] = resolvingModle.getWidth();
            iArr[1] = resolvingModle.getHeight();
        } else if (!(XJRenderActivityManager.INSTANCE.getW() == null || XJRenderActivityManager.INSTANCE.getH() == null)) {
            iArr[0] = Integer.valueOf(XJRenderActivityManager.INSTANCE.getH()).intValue();
            iArr[1] = Integer.valueOf(XJRenderActivityManager.INSTANCE.getW()).intValue();
        }
        return iArr;
    }
}
