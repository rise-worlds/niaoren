package p110z1;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;
import com.nrzs.data.DataApp;
import com.nrzs.data.base.BaseCollectInfo;
import com.nrzs.data.base.EventCollectBodyInfo;
import com.nrzs.data.base.EventCollectCommandListInfo;
import com.nrzs.data.base.EventCollectCommandParamEntity;
import com.nrzs.data.base.EventCollectHeadInfo;
import com.nrzs.data.base.EventCollectInfo;
import com.nrzs.http.ThreadCallback;
import com.nrzs.http.UICallback;
import java.util.ArrayList;
import java.util.List;

/* renamed from: z1.alc */
/* loaded from: classes3.dex */
public class EventCollectManager {

    /* renamed from: a */
    private static final String f16314a = "alc";

    /* renamed from: b */
    private static volatile EventCollectManager f16315b;

    /* renamed from: c */
    private EventCollectInfo f16316c;

    /* renamed from: d */
    private final int f16317d = 5;

    /* renamed from: e */
    private final String f16318e = "save_collect_data";

    private EventCollectManager() {
        m12633c();
    }

    /* renamed from: c */
    private void m12633c() {
        if (this.f16316c == null) {
            String b = apf.m11837b(DataApp.m18939d().m18947a(), ShareVal.f16591a, "save_collect_data", "");
            if (!TextUtils.isEmpty(b)) {
                try {
                    this.f16316c = (EventCollectInfo) apa.m11876b(b, EventCollectInfo.class);
                } catch (Exception e) {
                    e.printStackTrace();
                    m12632d();
                    this.f16316c = new EventCollectInfo();
                    this.f16316c.MsgBody = new ArrayList();
                }
            } else {
                this.f16316c = new EventCollectInfo();
                this.f16316c.MsgBody = new ArrayList();
            }
        }
    }

    /* renamed from: a */
    public static EventCollectManager m12642a() {
        EventCollectManager alcVar = f16315b;
        if (f16315b == null) {
            synchronized (EventCollectManager.class) {
                alcVar = f16315b;
                if (alcVar == null) {
                    alcVar = new EventCollectManager();
                    f16315b = alcVar;
                }
            }
        }
        return alcVar;
    }

    /* renamed from: a */
    public void m12637a(UICallback oVar, ThreadCallback nVar, String str, long j, int i) {
        new EventRepository().m12584a(oVar, nVar, str, j, i);
    }

    /* renamed from: a */
    public void m12640a(Context context, String str, String str2, String str3) {
        Integer.valueOf(str3);
        try {
            m12638a(m12639a((BaseCollectInfo) null, str, str2, str3, EventConstants.f16435c));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public void m12641a(Context context) {
        try {
            EventCollectInfo eventCollectInfo = new EventCollectInfo();
            eventCollectInfo.MsgHead = m12631e();
            eventCollectInfo.MsgBody = new ArrayList();
            eventCollectInfo.MsgBody.add(m12634b(context));
            new EventRepository().m12583a(apa.m11879a(eventCollectInfo));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: b */
    private EventCollectBodyInfo m12634b(Context context) {
        PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> a = PackageUtil.m11851a(context);
        EventCollectBodyInfo eventCollectBodyInfo = new EventCollectBodyInfo();
        ArrayList arrayList = new ArrayList();
        for (PackageInfo packageInfo : a) {
            ArrayList arrayList2 = new ArrayList();
            String charSequence = packageInfo.applicationInfo.loadLabel(packageManager).toString();
            String str = packageInfo.applicationInfo.packageName;
            arrayList2.add(m12636a("UserLocalAppName", charSequence));
            arrayList2.add(m12636a("UserLocalAppPackageName", str));
            EventCollectCommandListInfo eventCollectCommandListInfo = new EventCollectCommandListInfo();
            eventCollectCommandListInfo.CommandParam = arrayList2;
            arrayList.add(eventCollectCommandListInfo);
        }
        eventCollectBodyInfo.CommandList = arrayList;
        eventCollectBodyInfo.EventCode = EventConstants.f16436d;
        eventCollectBodyInfo.PositionCode = EventConstants.f16436d;
        return eventCollectBodyInfo;
    }

    /* renamed from: a */
    private EventCollectCommandParamEntity m12636a(String str, String str2) {
        EventCollectCommandParamEntity eventCollectCommandParamEntity = new EventCollectCommandParamEntity();
        eventCollectCommandParamEntity.Key = str;
        eventCollectCommandParamEntity.Value = str2;
        return eventCollectCommandParamEntity;
    }

    /* renamed from: a */
    private EventCollectBodyInfo m12639a(BaseCollectInfo baseCollectInfo, String str, String str2, String str3, String str4) throws Exception {
        EventCollectCommandListInfo eventCollectCommandListInfo = new EventCollectCommandListInfo();
        if (baseCollectInfo == null) {
            eventCollectCommandListInfo.CommandParam = new ArrayList();
        } else {
            eventCollectCommandListInfo.CommandParam = baseCollectInfo.getAttributesInfo();
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(eventCollectCommandListInfo);
        EventCollectBodyInfo eventCollectBodyInfo = new EventCollectBodyInfo();
        eventCollectBodyInfo.CommandList = arrayList;
        eventCollectBodyInfo.EventCode = str3;
        eventCollectBodyInfo.PositionCode = str4;
        eventCollectBodyInfo.SourceName = str;
        eventCollectBodyInfo.SourceKey = str2;
        return eventCollectBodyInfo;
    }

    /* renamed from: a */
    private void m12638a(EventCollectBodyInfo eventCollectBodyInfo) {
        EventCollectInfo eventCollectInfo = this.f16316c;
        if (eventCollectInfo != null) {
            eventCollectInfo.MsgBody.add(eventCollectBodyInfo);
            String a = apa.m11879a(this.f16316c);
            if (this.f16316c.MsgBody.size() >= 5) {
                this.f16316c.MsgHead = m12631e();
                new EventRepository().m12583a(a);
                m12632d();
                return;
            }
            apf.m11842a(DataApp.m18939d().m18947a(), ShareVal.f16591a, "save_collect_data", a);
        }
    }

    /* renamed from: d */
    private void m12632d() {
        EventCollectInfo eventCollectInfo = this.f16316c;
        if (eventCollectInfo != null && eventCollectInfo.MsgBody != null) {
            this.f16316c.MsgBody.clear();
            apf.m11844a(DataApp.m18939d().m18947a(), ShareVal.f16591a, "save_collect_data");
        }
    }

    /* renamed from: b */
    public void m12635b() {
        EventCollectInfo eventCollectInfo = this.f16316c;
        if (eventCollectInfo != null && eventCollectInfo.MsgBody.size() > 0) {
            this.f16316c.MsgHead = m12631e();
            new EventRepository().m12583a(apa.m11879a(this.f16316c));
            m12632d();
        }
    }

    /* renamed from: e */
    private EventCollectHeadInfo m12631e() {
        EventCollectHeadInfo eventCollectHeadInfo = new EventCollectHeadInfo();
        eventCollectHeadInfo.AppCode = ShareVal.f16591a;
        eventCollectHeadInfo.AppVersion = Config.m12524b();
        eventCollectHeadInfo.Channel = DataApp.m18939d().m18944b();
        eventCollectHeadInfo.UserKey = String.valueOf(LoginManager.m12620d().m12614j());
        eventCollectHeadInfo.DeviceKey = Config.m12527a();
        eventCollectHeadInfo.HardwareType = Build.MODEL;
        eventCollectHeadInfo.HardwareVendor = Build.MANUFACTURER.toLowerCase();
        eventCollectHeadInfo.HardwareOS = Build.VERSION.SDK + "," + Build.VERSION.RELEASE;
        return eventCollectHeadInfo;
    }
}
