package p110z1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.nrzs.data.base.BaseResponse;
import com.nrzs.data.game.bean.TopicInfo;
import com.nrzs.data.other.bean.request.GetTopicInfo;
import com.nrzs.data.p066ft.bean.AssistInfo;
import com.nrzs.game.p069ui.activity.GameScreenShotActivity;
import com.nrzs.http.ThreadCallback;
import com.nrzs.http.UICallback;
import p110z1.GameDialogVARunScriptCheck;

/* renamed from: z1.aiq */
/* loaded from: classes3.dex */
public class CKU {

    /* renamed from: a */
    private int f15970a;

    /* renamed from: c */
    private Context f15972c;

    /* renamed from: d */
    private Class f15973d;

    /* renamed from: e */
    private TopicInfo f15974e;

    /* renamed from: f */
    private boolean f15975f;

    /* renamed from: g */
    private AssistInfo f15976g;

    /* renamed from: h */
    private int f15977h;

    /* renamed from: b */
    private Object f15971b = new Object();

    /* renamed from: i */
    private UICallback f15978i = new UICallback() { // from class: z1.aiq.1
        @Override // com.nrzs.http.UICallback
        /* renamed from: a */
        public void mo3021a(Throwable th) {
            if (CKU.this.f15977h == 1) {
                CKU aiqVar = CKU.this;
                aiqVar.m13019c(aiqVar.f15972c, CKU.this.f15973d, CKU.this.f15974e, CKU.this.f15975f);
                return;
            }
            CKU aiqVar2 = CKU.this;
            aiqVar2.m13025b(aiqVar2.f15972c, CKU.this.f15973d, CKU.this.f15974e, CKU.this.f15976g);
        }

        @Override // com.nrzs.http.UICallback
        /* renamed from: a */
        public void mo3022a(Object obj) {
            TopicInfo topicInfo = (TopicInfo) obj;
            CKU.this.f15970a = topicInfo.BitSportCfg;
            CKU.this.f15974e.BitSportCfg = topicInfo.BitSportCfg;
            if (CKU.this.f15977h == 1) {
                CKU aiqVar = CKU.this;
                aiqVar.m13019c(aiqVar.f15972c, CKU.this.f15973d, CKU.this.f15974e, CKU.this.f15975f);
                return;
            }
            CKU aiqVar2 = CKU.this;
            aiqVar2.m13025b(aiqVar2.f15972c, CKU.this.f15973d, CKU.this.f15974e, CKU.this.f15976g);
        }
    };

    /* renamed from: j */
    private ThreadCallback f15979j = new ThreadCallback<TopicInfo, String>() { // from class: z1.aiq.2
        /* renamed from: a */
        public TopicInfo onResponse(String str) {
            BaseResponse baseResponse = (BaseResponse) apa.m11877a(str, new TypeToken<BaseResponse<TopicInfo>>() { // from class: z1.aiq.2.1
            });
            int i = baseResponse.code;
            if (!"失败".equals(baseResponse.msg) && baseResponse != null && (baseResponse.data instanceof TopicInfo)) {
                return (TopicInfo) baseResponse.data;
            }
            return null;
        }
    };

    /* renamed from: a */
    public void m13036a(long j) {
        GetTopicInfo getTopicInfo = new GetTopicInfo();
        getTopicInfo.TopicId = String.valueOf(j);
        try {
            new TopicInfoRepository().m12705a(getTopicInfo, this.f15978i, this.f15979j);
        } catch (Exception unused) {
        }
    }

    /* renamed from: a */
    public static void m13034a(Context context) {
        if (context instanceof Activity) {
            new Nr64AppDownDialog((Activity) context, PreSetListManager.m12116a().m12096t()).show();
        }
    }

    /* renamed from: b */
    public static void m13026b(Context context) {
        if (context instanceof Activity) {
            new Nr32AppDownDialog((Activity) context, PreSetListManager.m12116a().m12095u()).show();
        }
    }

    /* renamed from: a */
    public static AssistInfo m13035a(long j, String str, String str2, long j2) {
        AssistInfo assistInfo = new AssistInfo();
        assistInfo.ScriptAuthor = j2;
        assistInfo.ScriptID = j;
        assistInfo.OnlyID = str;
        assistInfo.ScriptName = str2;
        return assistInfo;
    }

    /* renamed from: a */
    public void m13031a(final Context context, final Class cls, final TopicInfo topicInfo, final boolean z) {
        this.f15972c = context;
        this.f15973d = cls;
        this.f15974e = topicInfo;
        this.f15975f = z;
        if (!LoginManager.m12620d().m12606r()) {
            IntentToAssistService.m12813a(context);
        } else if (SPUtils.m23341a().m23318b(ShareVal.f16593c, false)) {
            m13024b(context, cls, topicInfo, z);
        } else {
            FloatWindowManager.m12301b(context, new Observer<Integer>() { // from class: z1.aiq.3
                @Override // p110z1.Observer
                public void onComplete() {
                }

                @Override // p110z1.Observer
                public void onError(Throwable th) {
                }

                @Override // p110z1.Observer
                public void onSubscribe(Disposable atrVar) {
                }

                /* renamed from: a */
                public void onNext(Integer num) {
                    int intValue = num.intValue();
                    if (intValue == 1) {
                        CKU.this.m13024b(context, cls, topicInfo, z);
                    } else if (intValue == 4) {
                        new FtCheckFloatingDialog(context, 4).show();
                    } else if (intValue == -1) {
                        new FtCheckFloatingDialog(context, -1).show();
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m13024b(Context context, Class cls, TopicInfo topicInfo, boolean z) {
        int a = aly.m12531a(1);
        aoy.m11884a("/sdcard/nrzs/port/1.txt", "" + a, false);
        if (a == 1) {
            IntentToAssistService.m12803a(context, cls, topicInfo, null, 1, 2);
            return;
        }
        VAManager.m12070i().m12092a(context);
        VAManager.m12070i().m12086a(topicInfo);
        VAManager.m12070i().m12085a(cls);
        VAManager.m12070i().m12087a((AssistInfo) null);
        VAManager.m12070i().m12093a(1);
        LogUtils.m23734c("check_32_64", "弹窗之前");
        this.f15970a = topicInfo.BitSportCfg;
        this.f15977h = 1;
        m13036a(topicInfo.TopicID);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public void m13019c(final Context context, Class cls, TopicInfo topicInfo, boolean z) {
        if (m13033a(context, topicInfo.BitSportCfg, topicInfo.TopicID)) {
            LogUtils.m23734c("check_32_64", "弹窗之后");
            if (z) {
                LogUtils.m23734c("CKUttt", "免root 模式 - 1");
                ((Activity) context).startActivity(new Intent(context, GameScreenShotActivity.class));
                return;
            }
            VAManager.m12070i().m12089a(context, new GameDialogVARunScriptCheck.AbstractC3792a() { // from class: z1.aiq.4
                @Override // p110z1.GameDialogVARunScriptCheck.AbstractC3792a
                /* renamed from: a */
                public void mo12007a(TopicInfo topicInfo2) {
                    Context context2 = context;
                    ((Activity) context2).startActivity(new Intent(context2, GameScreenShotActivity.class));
                }

                @Override // p110z1.GameDialogVARunScriptCheck.AbstractC3792a
                /* renamed from: a */
                public void mo12008a() {
                    VAManager.m12070i().m12080b(context);
                }
            }, topicInfo.TopicID);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m13025b(final Context context, Class cls, TopicInfo topicInfo, AssistInfo assistInfo) {
        if (m13033a(context, topicInfo.BitSportCfg, topicInfo.TopicID)) {
            VAManager.m12070i().m12092a(context);
            VAManager.m12070i().m12086a(topicInfo);
            VAManager.m12070i().m12093a(2);
            VAManager.m12070i().m12085a(cls);
            VAManager.m12070i().m12087a(assistInfo);
            VAManager.m12070i().m12089a(context, new GameDialogVARunScriptCheck.AbstractC3792a() { // from class: z1.aiq.5
                @Override // p110z1.GameDialogVARunScriptCheck.AbstractC3792a
                /* renamed from: a */
                public void mo12007a(TopicInfo topicInfo2) {
                    Context context2 = context;
                    ((Activity) context2).startActivity(new Intent(context2, GameScreenShotActivity.class));
                }

                @Override // p110z1.GameDialogVARunScriptCheck.AbstractC3792a
                /* renamed from: a */
                public void mo12008a() {
                    VAManager.m12070i().m12080b(context);
                }
            }, topicInfo.TopicID);
        }
    }

    /* renamed from: a */
    private boolean m13033a(Context context, int i, long j) {
        Log.i("TAG", "checkEnginMatchs isSupport32Bit: " + NRZSLocalConfig.m12513c());
        Log.i("TAG", "checkEnginMatchs isSupport64Bit: " + NRZSLocalConfig.m12514b());
        Log.i("TAG", "checkEnginMatchs BitSportCfg: " + i);
        if (NRZSLocalConfig.m12513c() && this.f15970a == 2) {
            m13034a(context);
            LogUtils.m23734c("check_32_64", "64Dialog");
            return false;
        } else if (!NRZSLocalConfig.m12514b() || this.f15970a != 1) {
            return true;
        } else {
            m13026b(context);
            LogUtils.m23734c("check_32_64", "32Dialog");
            return false;
        }
    }

    /* renamed from: a */
    public void m13032a(final Context context, final Class cls, final TopicInfo topicInfo, final AssistInfo assistInfo) {
        this.f15972c = context;
        this.f15973d = cls;
        this.f15974e = topicInfo;
        this.f15976g = assistInfo;
        if (!LoginManager.m12620d().m12606r()) {
            IntentToAssistService.m12813a(context);
        } else if (SPUtils.m23341a().m23318b(ShareVal.f16593c, false)) {
            m13020c(context, cls, topicInfo, assistInfo);
        } else {
            FloatWindowManager.m12301b(context, new Observer<Integer>() { // from class: z1.aiq.6
                @Override // p110z1.Observer
                public void onComplete() {
                }

                @Override // p110z1.Observer
                public void onError(Throwable th) {
                }

                @Override // p110z1.Observer
                public void onSubscribe(Disposable atrVar) {
                }

                /* renamed from: a */
                public void onNext(Integer num) {
                    int intValue = num.intValue();
                    if (intValue == 1) {
                        CKU.this.m13020c(context, cls, topicInfo, assistInfo);
                    } else if (intValue == 4) {
                        new FtCheckFloatingDialog(context, 4).show();
                    } else if (intValue == -1) {
                        new FtCheckFloatingDialog(context, -1).show();
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public void m13020c(Context context, Class cls, TopicInfo topicInfo, AssistInfo assistInfo) {
        int a = aly.m12531a(1);
        aoy.m11884a("/sdcard/nrzs/port/1.txt", "" + a, false);
        this.f15970a = topicInfo.BitSportCfg;
        if (a == 1) {
            IntentToAssistService.m12803a(context, cls, topicInfo, assistInfo, 2, 2);
            return;
        }
        this.f15977h = 2;
        m13036a(topicInfo.TopicID);
    }
}
