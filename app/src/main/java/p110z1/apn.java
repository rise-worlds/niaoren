package p110z1;

import p110z1.LoadViewHelper;

/* compiled from: HttpHelper.java */
/* renamed from: z1.apn */
/* loaded from: classes3.dex */
public class apn implements ILazyLoad, IloadViewResult {

    /* renamed from: a */
    private boolean f17164a = true;

    /* renamed from: b */
    private ILoadData f17165b;

    /* renamed from: c */
    private LoadViewHelper f17166c;

    @Override // p110z1.ILazyLoad
    /* renamed from: f */
    public void mo11684f() {
    }

    public apn(LoadViewHelper.AbstractC3846a aVar, ILoadData aprVar) {
        this.f17166c = new LoadViewHelper(aVar);
        this.f17165b = aprVar;
    }

    @Override // p110z1.ILazyLoad
    /* renamed from: a */
    public boolean mo11686a() {
        if (!this.f17164a) {
            return false;
        }
        this.f17166c.mo11682k_();
        ILoadData aprVar = this.f17165b;
        if (aprVar != null) {
            aprVar.mo11683a(1);
        }
        return true;
    }

    @Override // p110z1.ILazyLoad
    /* renamed from: a */
    public void mo11685a(int i) {
        ILoadData aprVar = this.f17165b;
        if (aprVar != null) {
            aprVar.mo11683a(i);
        }
    }

    @Override // p110z1.IloadViewResult
    /* renamed from: k_ */
    public void mo11682k_() {
        this.f17164a = true;
        this.f17166c.mo11682k_();
    }

    @Override // p110z1.IloadViewResult
    /* renamed from: l_ */
    public void mo11681l_() {
        this.f17164a = true;
        this.f17166c.mo11681l_();
    }

    @Override // p110z1.IloadViewResult
    /* renamed from: m_ */
    public void mo11680m_() {
        this.f17164a = true;
        this.f17166c.mo11680m_();
    }

    @Override // p110z1.IloadViewResult
    /* renamed from: n_ */
    public void mo11679n_() {
        this.f17164a = false;
        this.f17166c.mo11679n_();
    }

    /* renamed from: a */
    public void m11695a(boolean z) {
        this.f17164a = z;
    }
}
