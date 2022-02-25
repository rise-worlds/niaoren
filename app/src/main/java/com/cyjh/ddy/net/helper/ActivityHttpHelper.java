package com.cyjh.ddy.net.helper;

import android.os.Handler;
import android.os.Looper;
import com.blankj.utilcode.util.NetworkUtils;
import com.cyjh.ddy.base.utils.CLog;
import com.cyjh.ddy.base.utils.JsonUtil;
import com.cyjh.ddy.net.bean.base.BaseDataResult;
import com.cyjh.ddy.net.inf.IAnalysisJson;
import com.cyjh.ddy.net.inf.IUIDataListener;
import com.cyjh.ddy.net.utils.DomainUtils;
import java.util.Map;
import p110z1.Gson;
import p110z1.TypeToken;

/* renamed from: com.cyjh.ddy.net.helper.a */
/* loaded from: classes.dex */
public class ActivityHttpHelper<T> extends OkHttpClientHelper {

    /* renamed from: c */
    private IAnalysisJson f7525c;

    /* renamed from: d */
    private Gson f7526d;

    /* renamed from: b */
    private IUIDataListener f7524b = null;

    /* renamed from: e */
    private boolean f7527e = true;

    /* renamed from: a */
    Handler f7523a = new Handler(Looper.getMainLooper());

    public ActivityHttpHelper(final TypeToken<T> rdVar) {
        if (this.f7525c == null) {
            this.f7526d = new Gson();
            this.f7525c = new IAnalysisJson() { // from class: com.cyjh.ddy.net.helper.ActivityHttpHelper$1
                /* JADX WARN: Type inference failed for: r5v5, types: [T, java.lang.Object] */
                @Override // com.cyjh.ddy.net.inf.IAnalysisJson
                public T getData(String str) {
                    Gson oxVar;
                    try {
                        BaseDataResult baseDataResult = (BaseDataResult) JsonUtil.m21799b(str, BaseDataResult.class);
                        if (baseDataResult == null) {
                            return null;
                        }
                        baseDataResult.setData();
                        oxVar = ActivityHttpHelper.this.f7526d;
                        return oxVar.m1588a(baseDataResult.getJson(), rdVar.getType());
                    } catch (Exception e) {
                        CLog.m21883e("ActivityHttpHelper", "getData exception " + e.getMessage());
                        e.printStackTrace();
                        return null;
                    }
                }
            };
        }
    }

    public ActivityHttpHelper(IAnalysisJson aVar) {
        this.f7525c = aVar;
    }

    /* renamed from: a */
    public void m21435a(IUIDataListener bVar) {
        this.f7524b = bVar;
    }

    @Override // com.cyjh.ddy.net.helper.OkHttpClientHelper
    /* renamed from: a */
    public void mo21418a(Object obj) {
        try {
            if (this.f7524b != null) {
                if (this.f7527e && obj == null) {
                    DomainUtils.m21393a(true);
                }
                this.f7524b.uiDataSuccess(obj);
                return;
            }
            CLog.m21883e("ActivityHttpHelper", "onResponse mDataListener==null");
        } catch (Exception e) {
            mo21419a(e);
        }
    }

    @Override // com.cyjh.ddy.net.helper.OkHttpClientHelper
    /* renamed from: a */
    public void mo21419a(Exception exc) {
        if (this.f7524b != null) {
            if (this.f7527e) {
                DomainUtils.m21393a(true);
            }
            this.f7524b.uiDataError(exc);
            return;
        }
        CLog.m21883e("ActivityHttpHelper", "onErrorResponse mDataListener==null");
    }

    /* renamed from: a */
    private String m21434a(String str) {
        int i;
        if (str.startsWith("https://")) {
            i = 8;
        } else {
            i = str.startsWith("http://") ? 7 : 0;
        }
        return str.substring(0, i + str.substring(i).indexOf("/"));
    }

    /* renamed from: a */
    public void m21433a(String str, int i) {
        super.m21417a(m21434a(str), str, this.f7525c, i);
    }

    /* renamed from: a */
    public void m21431a(String str, Map<String, String> map, int i) {
        m21432a(m21434a(str), str, map, i);
    }

    /* renamed from: a */
    public void m21432a(String str, String str2, Map<String, String> map, int i) {
        if (!NetworkUtils.m23652b()) {
            this.f7523a.post(new Runnable() { // from class: com.cyjh.ddy.net.helper.ActivityHttpHelper$2
                @Override // java.lang.Runnable
                public void run() {
                    CLog.m21883e("ActivityHttpHelper", "sendPostRequest NetworkUtils not connected.");
                }
            });
            return;
        }
        super.m21414a(str, str2, map, this.f7525c, i);
    }

    /* renamed from: b */
    public void m21429b(String str, String str2, Map<String, String> map, int i) {
        super.m21415a(m21434a(str), str, str2, map, this.f7525c, i);
    }

    /* renamed from: a */
    public void m21437a() {
        if (!m21412c()) {
            m21413b();
        }
        this.f7524b = null;
        this.f7526d = null;
    }

    /* renamed from: a */
    public void m21430a(boolean z) {
        this.f7527e = z;
    }
}
