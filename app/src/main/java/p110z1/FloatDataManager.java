package p110z1;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.nrzs.data.base.BaseResponse;
import com.nrzs.data.game.bean.TopicInfo;
import com.nrzs.data.p066ft.bean.AInfo;
import com.nrzs.data.p066ft.bean.AssistInfo;
import java.util.concurrent.Callable;
import p110z1.FtEvent;

/* renamed from: z1.anj */
/* loaded from: classes3.dex */
public class FloatDataManager {

    /* renamed from: a */
    public static final int f16743a = 1;

    /* renamed from: b */
    public static final int f16744b = 2;

    /* renamed from: c */
    public static final int f16745c = 3;

    /* renamed from: m */
    private static final Object f16746m = new Object();

    /* renamed from: n */
    private static FloatDataManager f16747n;

    /* renamed from: e */
    public Class f16749e;

    /* renamed from: f */
    private int[] f16750f;

    /* renamed from: g */
    private AssistInfo f16751g;

    /* renamed from: h */
    private TopicInfo f16752h;

    /* renamed from: i */
    private int f16753i;

    /* renamed from: j */
    private String f16754j;

    /* renamed from: d */
    public int f16748d = 1;

    /* renamed from: k */
    private int f16755k = -1;

    /* renamed from: l */
    private int f16756l = 0;

    /* renamed from: a */
    public int m12371a() {
        return this.f16756l;
    }

    /* renamed from: a */
    public void m12370a(int i) {
        Log.d("setCurrentStopType", "currentStopType:" + i, new Exception());
        this.f16756l = i;
    }

    /* renamed from: b */
    public String m12362b() {
        return this.f16754j;
    }

    /* renamed from: a */
    public void m12364a(String str) {
        this.f16754j = str;
    }

    /* renamed from: c */
    public int m12359c() {
        return this.f16755k;
    }

    /* renamed from: b */
    public void m12361b(int i) {
        this.f16755k = i;
    }

    /* renamed from: d */
    public int[] m12358d() {
        return this.f16750f;
    }

    /* renamed from: a */
    public void m12363a(int[] iArr) {
        this.f16750f = iArr;
    }

    /* renamed from: e */
    public AssistInfo m12357e() {
        return this.f16751g;
    }

    /* renamed from: a */
    public void m12367a(AssistInfo assistInfo) {
        this.f16751g = assistInfo;
    }

    /* renamed from: f */
    public TopicInfo m12356f() {
        return this.f16752h;
    }

    /* renamed from: a */
    public void m12366a(TopicInfo topicInfo) {
        this.f16752h = topicInfo;
    }

    /* renamed from: a */
    public void m12368a(Context context, TopicInfo topicInfo, AssistInfo assistInfo, Class cls, int i) {
        Log.i("YSGEW_FLOAT", "executeForFW");
        this.f16748d = 1;
        this.f16752h = topicInfo;
        this.f16751g = assistInfo;
        this.f16749e = cls;
        this.f16753i = i;
        if (!TextUtils.isEmpty(topicInfo.localAppPackage)) {
            PackageUtil.m11849b(context, topicInfo.localAppPackage);
        }
        if (i == 1) {
            FloatViewManager.m12346a(context).m12336b(context);
        } else {
            FloatViewManager.m12346a(context).m12343a(context, assistInfo);
        }
        FloatViewManager.m12346a(context).m12332c(context);
    }

    /* renamed from: a */
    public void m12365a(TopicInfo topicInfo, AssistInfo assistInfo, Class cls, int i) {
        Log.i("YSGEW_FLOAT", "initParamForVA");
        this.f16748d = 2;
        this.f16752h = topicInfo;
        this.f16751g = assistInfo;
        this.f16749e = cls;
        this.f16753i = i;
    }

    /* renamed from: a */
    public void m12369a(Context context) {
        FloatViewManager.m12346a(context).m12332c(context);
    }

    /* renamed from: g */
    public void m12355g() {
        this.f16748d = 3;
    }

    /* renamed from: b */
    public void m12360b(final String str) {
        Log.i("setRunPermCallback", "runPermJson:" + str);
        VUiKit.m11713a().mo3332a(new Callable<AInfo>() { // from class: z1.anj.3
            /* renamed from: a */
            public AInfo call() throws Exception {
                if (!TextUtils.isEmpty(str)) {
                    BaseResponse baseResponse = (BaseResponse) apa.m11877a(str, new TypeToken<BaseResponse<String>>() { // from class: z1.anj.3.1
                    });
                    if (baseResponse != null) {
                        AInfo aInfo = (AInfo) apa.m11877a((String) baseResponse.data, new TypeToken<AInfo>() { // from class: z1.anj.3.2
                        });
                        if (baseResponse.code == 1 && aInfo != null) {
                            return aInfo;
                        }
                        throw new RuntimeException();
                    }
                    throw new RuntimeException();
                }
                throw new RuntimeException();
            }
        }).mo3285a(new FailCallback<Throwable>() { // from class: z1.anj.2
            /* renamed from: a */
            public void onFail(Throwable th) {
                EventBus.m3448a().m3427d(new FtEvent.C3569g(null));
            }
        }).mo3282b(new DoneCallback<AInfo>() { // from class: z1.anj.1
            /* renamed from: a */
            public void onDone(AInfo aInfo) {
                Log.i("setRunPermCallback", "runPermJson:EventBus");
                EventBus.m3448a().m3427d(new FtEvent.C3569g(aInfo));
            }
        });
    }

    /* renamed from: h */
    public long m12354h() {
        TopicInfo topicInfo = this.f16752h;
        if (topicInfo != null) {
            return topicInfo.TopicID;
        }
        return -1L;
    }

    /* renamed from: i */
    public void m12353i() {
        m12351k();
    }

    public FloatDataManager() {
        m12351k();
    }

    /* renamed from: j */
    public static FloatDataManager m12352j() {
        FloatDataManager anjVar;
        synchronized (f16746m) {
            if (f16747n == null) {
                f16747n = new FloatDataManager();
            }
            anjVar = f16747n;
        }
        return anjVar;
    }

    /* renamed from: k */
    private void m12351k() {
        m12363a(new int[]{-1, -1});
    }
}
