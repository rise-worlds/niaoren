package p110z1;

import android.support.annotation.Nullable;
import com.common.utils.log.LogUtils;
import com.lbd.xj.app.XJApp;
import com.lbd.xj.socket.C1545f;
import com.lbd.xj.socket.model.AppInfo;
import com.nrzs.data.DataApp;
import com.nrzs.data.game.bean.TopicInfo;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/* renamed from: z1.adi */
/* loaded from: classes3.dex */
public class GetAppItemTask extends C1545f.AbstractRunnableC1552d {
    @Override // com.lbd.xj.socket.C1545f.AbstractRunnableC1552d
    public void onCancel() {
    }

    @Override // com.lbd.xj.socket.C1545f.AbstractRunnableC1552d
    public void onFail(Throwable th) {
    }

    @Override // com.lbd.xj.socket.C1545f.AbstractRunnableC1552d
    public void onSuccess(@Nullable Object obj) {
    }

    @Override // com.lbd.xj.socket.C1545f.AbstractRunnableC1552d
    @Nullable
    public Object doInBackground() {
        AppInfo a;
        try {
            String a2 = GsonUtil.m13969a(aee.m14187a(XJApp.getInstance().getApplicationContext(), XJApp.getInstance().getApplicationContext().getPackageManager().getInstalledPackages(4096), true, true));
            FileWriteUtils.m14077a(a2);
            PreferencesUtil.m13937a().m13931a(acf.f15188m, (Object) a2);
            String a3 = GsonUtil.m13969a(aee.m14178c(XJApp.getInstance().getApplicationContext()));
            FileWriteUtils.m14070c(a3);
            PreferencesUtil.m13937a().m13931a(acf.f15189n, (Object) a3);
            LogUtils.m22037e("加载应用及APK数据完成");
            FileWriteUtils.m14069d(apf.m11837b(DataApp.m18939d().m18947a(), ShareVal.f16591a, ShareVal.f16592b, ""));
            List<TopicInfo> a4 = TopicInfoManager.m12726c().m12734a(DataApp.m18939d().m18947a());
            ArrayList arrayList = new ArrayList();
            if (a4 != null) {
                for (TopicInfo topicInfo : a4) {
                    if (topicInfo.SportBackGround == 1 && (a = aee.m14183a(topicInfo.localAppPackage)) != null) {
                        arrayList.add(a);
                    }
                }
            }
            FileWriteUtils.m14072b(GsonUtil.m13969a(arrayList));
            return null;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
