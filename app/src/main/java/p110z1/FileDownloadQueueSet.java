package p110z1;

import java.util.ArrayList;
import java.util.List;
import p110z1.BaseDownloadTask;

/* renamed from: z1.afp */
/* loaded from: classes3.dex */
public class FileDownloadQueueSet {

    /* renamed from: a */
    private FileDownloadListener f15586a;

    /* renamed from: b */
    private boolean f15587b;

    /* renamed from: c */
    private List<BaseDownloadTask.AbstractC3403a> f15588c;

    /* renamed from: d */
    private Integer f15589d;

    /* renamed from: e */
    private Boolean f15590e;

    /* renamed from: f */
    private Boolean f15591f;

    /* renamed from: g */
    private Boolean f15592g;

    /* renamed from: h */
    private Integer f15593h;

    /* renamed from: i */
    private Integer f15594i;

    /* renamed from: j */
    private Object f15595j;

    /* renamed from: k */
    private String f15596k;

    /* renamed from: l */
    private BaseDownloadTask[] f15597l;

    public FileDownloadQueueSet(FileDownloadListener aflVar) {
        if (aflVar != null) {
            this.f15586a = aflVar;
            return;
        }
        throw new IllegalArgumentException("create FileDownloadQueueSet must with valid target!");
    }

    /* renamed from: a */
    public FileDownloadQueueSet m13663a(BaseDownloadTask... afaVarArr) {
        this.f15587b = false;
        this.f15597l = afaVarArr;
        return this;
    }

    /* renamed from: a */
    public FileDownloadQueueSet m13666a(List<BaseDownloadTask> list) {
        this.f15587b = false;
        this.f15597l = new BaseDownloadTask[list.size()];
        list.toArray(this.f15597l);
        return this;
    }

    /* renamed from: b */
    public FileDownloadQueueSet m13658b(BaseDownloadTask... afaVarArr) {
        this.f15587b = true;
        this.f15597l = afaVarArr;
        return this;
    }

    /* renamed from: b */
    public FileDownloadQueueSet m13660b(List<BaseDownloadTask> list) {
        this.f15587b = true;
        this.f15597l = new BaseDownloadTask[list.size()];
        list.toArray(this.f15597l);
        return this;
    }

    /* renamed from: a */
    public void m13670a() {
        for (BaseDownloadTask afaVar : this.f15597l) {
            afaVar.mo13765d();
        }
        m13662b();
    }

    /* renamed from: b */
    public void m13662b() {
        BaseDownloadTask[] afaVarArr;
        for (BaseDownloadTask afaVar : this.f15597l) {
            afaVar.mo13781a(this.f15586a);
            Integer num = this.f15589d;
            if (num != null) {
                afaVar.mo13764d(num.intValue());
            }
            Boolean bool = this.f15590e;
            if (bool != null) {
                afaVar.mo13771b(bool.booleanValue());
            }
            Boolean bool2 = this.f15591f;
            if (bool2 != null) {
                afaVar.mo13780a(bool2.booleanValue());
            }
            Integer num2 = this.f15593h;
            if (num2 != null) {
                afaVar.mo13775b(num2.intValue());
            }
            Integer num3 = this.f15594i;
            if (num3 != null) {
                afaVar.mo13769c(num3.intValue());
            }
            Object obj = this.f15595j;
            if (obj != null) {
                afaVar.mo13787a(obj);
            }
            List<BaseDownloadTask.AbstractC3403a> list = this.f15588c;
            if (list != null) {
                for (BaseDownloadTask.AbstractC3403a aVar : list) {
                    afaVar.mo13773b(aVar);
                }
            }
            String str = this.f15596k;
            if (str != null) {
                afaVar.mo13784a(str, true);
            }
            Boolean bool3 = this.f15592g;
            if (bool3 != null) {
                afaVar.mo13766c(bool3.booleanValue());
            }
            afaVar.mo13770c().mo13738a();
        }
        FileDownloader.m13627a().m13610a(this.f15586a, this.f15587b);
    }

    /* renamed from: a */
    public FileDownloadQueueSet m13667a(String str) {
        this.f15596k = str;
        return this;
    }

    /* renamed from: a */
    public FileDownloadQueueSet m13669a(int i) {
        this.f15589d = Integer.valueOf(i);
        return this;
    }

    /* renamed from: a */
    public FileDownloadQueueSet m13664a(boolean z) {
        this.f15590e = Boolean.valueOf(z);
        return this;
    }

    /* renamed from: b */
    public FileDownloadQueueSet m13659b(boolean z) {
        this.f15591f = Boolean.valueOf(z);
        return this;
    }

    /* renamed from: b */
    public FileDownloadQueueSet m13661b(int i) {
        this.f15593h = Integer.valueOf(i);
        return this;
    }

    /* renamed from: c */
    public FileDownloadQueueSet m13656c(int i) {
        this.f15594i = Integer.valueOf(i);
        return this;
    }

    /* renamed from: c */
    public FileDownloadQueueSet m13657c() {
        m13661b(-1);
        return this;
    }

    /* renamed from: d */
    public FileDownloadQueueSet m13654d() {
        return m13661b(0);
    }

    /* renamed from: a */
    public FileDownloadQueueSet m13668a(Object obj) {
        this.f15595j = obj;
        return this;
    }

    /* renamed from: a */
    public FileDownloadQueueSet m13665a(BaseDownloadTask.AbstractC3403a aVar) {
        if (this.f15588c == null) {
            this.f15588c = new ArrayList();
        }
        this.f15588c.add(aVar);
        return this;
    }

    /* renamed from: c */
    public FileDownloadQueueSet m13655c(boolean z) {
        this.f15592g = Boolean.valueOf(z);
        return this;
    }
}
