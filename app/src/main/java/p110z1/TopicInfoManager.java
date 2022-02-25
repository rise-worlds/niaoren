package p110z1;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.text.TextUtils;
import android.util.Log;
import com.nrzs.data.database.AppDatabase;
import com.nrzs.data.game.bean.AuthorScriptBean;
import com.nrzs.data.game.bean.TopicInfo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;

/* renamed from: z1.akr */
/* loaded from: classes3.dex */
public class TopicInfoManager {

    /* renamed from: k */
    private static final Object f16224k = new Object();

    /* renamed from: l */
    private static TopicInfoManager f16225l;

    /* renamed from: h */
    private boolean f16233h;

    /* renamed from: j */
    private long f16235j;

    /* renamed from: a */
    private long f16226a = 0;

    /* renamed from: b */
    private List<TopicInfo> f16227b = new ArrayList();

    /* renamed from: c */
    private List<TopicInfo> f16228c = new ArrayList();

    /* renamed from: d */
    private List<TopicInfo> f16229d = new ArrayList();

    /* renamed from: e */
    private List<TopicInfo> f16230e = new ArrayList();

    /* renamed from: f */
    private List<TopicInfo> f16231f = new ArrayList();

    /* renamed from: g */
    private List<TopicInfo> f16232g = new ArrayList();

    /* renamed from: i */
    private Object f16234i = new Object();

    /* renamed from: a */
    public List<TopicInfo> m12738a() {
        return this.f16229d;
    }

    /* renamed from: b */
    public List<TopicInfo> m12729b() {
        return this.f16232g;
    }

    /* renamed from: a */
    public List<TopicInfo> m12734a(Context context) {
        ArrayList arrayList;
        synchronized (this.f16234i) {
            try {
                try {
                    this.f16232g.clear();
                    this.f16229d.clear();
                    this.f16229d.addAll(this.f16228c);
                    this.f16232g.addAll(m12733a(context, this.f16229d));
                    arrayList = new ArrayList();
                    arrayList.addAll(this.f16232g);
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    private List<TopicInfo> m12737a(int i, List<TopicInfo> list) {
        List<TopicInfo> a = AppDatabase.m18933e().mo18932a().mo12752a();
        if (a != null && !a.isEmpty()) {
            if (list.isEmpty()) {
                for (TopicInfo topicInfo : a) {
                    AppDatabase.m18933e().mo18932a().mo12750a(topicInfo);
                }
                return list;
            }
            for (TopicInfo topicInfo2 : a) {
                boolean z = false;
                Iterator<TopicInfo> it = list.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    TopicInfo next = it.next();
                    if (next.localAppPackage.equals(topicInfo2.localAppPackage) && topicInfo2.MatchType == i) {
                        m12732a(next, topicInfo2);
                        z = true;
                        break;
                    }
                }
                if (!z) {
                    AppDatabase.m18933e().mo18932a().mo12750a(topicInfo2);
                }
            }
        }
        return list;
    }

    /* renamed from: a */
    private void m12732a(TopicInfo topicInfo, TopicInfo topicInfo2) {
        topicInfo.tid = topicInfo2.tid;
        topicInfo.localAppPackage = topicInfo2.localAppPackage;
        topicInfo.localAppName = topicInfo2.localAppName;
        topicInfo.sportModel = topicInfo2.sportModel;
        topicInfo.isTop = topicInfo2.isTop;
        if (!TextUtils.isEmpty(topicInfo2.localVersionName)) {
            topicInfo.localVersionName = topicInfo2.localVersionName;
        }
    }

    /* renamed from: a */
    public List<TopicInfo> m12733a(Context context, List<TopicInfo> list) {
        TopicInfo a;
        List<PackageInfo> a2 = PackageUtil.m11851a(context);
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            for (PackageInfo packageInfo : a2) {
                if (!context.getPackageName().equals(packageInfo.packageName) && (a = m12730a(list, packageInfo.packageName, packageInfo.applicationInfo.loadLabel(context.getPackageManager()).toString())) != null) {
                    a.localAppPackage = packageInfo.packageName;
                    a.localAppName = packageInfo.applicationInfo.loadLabel(context.getPackageManager()).toString();
                    a.localAppIcon = packageInfo.applicationInfo.loadIcon(context.getPackageManager());
                    a.localVersionName = packageInfo.versionName;
                    a.time = packageInfo.lastUpdateTime;
                    arrayList.add(a);
                }
            }
            list.removeAll(arrayList);
        }
        return arrayList;
    }

    /* renamed from: a */
    private TopicInfo m12730a(List<TopicInfo> list, String str, String str2) {
        if (list == null) {
            return null;
        }
        for (TopicInfo topicInfo : list) {
            if (!TextUtils.isEmpty(topicInfo.PackageNames) && !TextUtils.isEmpty(topicInfo.Package)) {
                for (String str3 : topicInfo.PackageNames.split("\\|")) {
                    if (str.contains(str3)) {
                        for (String str4 : topicInfo.Package.split("\\|")) {
                            if (str2.contains(str4)) {
                                try {
                                    return (TopicInfo) topicInfo.clone();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    return topicInfo;
                                }
                            }
                        }
                        continue;
                    }
                }
                continue;
            }
        }
        return null;
    }

    /* renamed from: a */
    public void m12735a(final long j, final FailCallback<Throwable> datVar, final DoneCallback<TopicInfo> daqVar) {
        VUiKit.m11713a().mo3332a(new Callable<TopicInfo>() { // from class: z1.akr.3
            /* renamed from: a */
            public TopicInfo call() throws Exception {
                List<TopicInfo> a = AppDatabase.m18933e().mo18932a().mo12751a(j);
                if (a != null && a.size() > 0) {
                    return a.get(0);
                }
                throw new RuntimeException("暂无数据");
            }
        }).mo3285a(new FailCallback<Throwable>() { // from class: z1.akr.2
            /* renamed from: a */
            public void onFail(Throwable th) {
                datVar.onFail(th);
            }
        }).mo3282b(new DoneCallback<TopicInfo>() { // from class: z1.akr.1
            /* renamed from: a */
            public void onDone(TopicInfo topicInfo) {
                daqVar.onDone(topicInfo);
            }
        });
    }

    /* renamed from: a */
    public TopicInfo m12736a(long j) {
        List<TopicInfo> list = this.f16232g;
        if (list == null) {
            return null;
        }
        for (TopicInfo topicInfo : list) {
            if (j == topicInfo.TopicID) {
                return topicInfo;
            }
        }
        return null;
    }

    /* renamed from: c */
    public static TopicInfoManager m12726c() {
        TopicInfoManager akrVar;
        synchronized (f16224k) {
            if (f16225l == null) {
                f16225l = new TopicInfoManager();
            }
            akrVar = f16225l;
        }
        return akrVar;
    }

    /* renamed from: d */
    public List<TopicInfo> m12724d() {
        return this.f16228c;
    }

    /* renamed from: e */
    public List<TopicInfo> m12723e() {
        return this.f16227b;
    }

    /* renamed from: f */
    public long m12722f() {
        return this.f16226a;
    }

    /* renamed from: a */
    public List<AuthorScriptBean> m12731a(List<AuthorScriptBean> list) {
        synchronized (this.f16234i) {
            List<TopicInfo> b = m12726c().m12729b();
            Log.i("TopicInfoManager", "localMatchInfos:" + b.size());
            if (!(b == null || b.size() == 0)) {
                ArrayList arrayList = new ArrayList();
                for (TopicInfo topicInfo : b) {
                    for (AuthorScriptBean authorScriptBean : list) {
                        if (topicInfo.TopicID == authorScriptBean.getTopicId()) {
                            arrayList.add(authorScriptBean);
                        }
                    }
                }
                list.removeAll(arrayList);
                arrayList.addAll(list);
                return arrayList;
            }
            return list;
        }
    }

    /* renamed from: b */
    public void m12727b(List<TopicInfo> list) {
        this.f16228c = list;
    }

    /* renamed from: c */
    public void m12725c(List<TopicInfo> list) {
        this.f16227b = list;
    }

    /* renamed from: b */
    public void m12728b(long j) {
        this.f16226a = j;
    }
}
