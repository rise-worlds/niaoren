package p110z1;

import p110z1.XJEvent;
import p110z1.XJRenderActivityContract;

/* renamed from: z1.ack */
/* loaded from: classes3.dex */
public class XJRenderActivityPresenter implements XJRenderActivityContract.AbstractC3331a {

    /* renamed from: a */
    private XJRenderActivityContract.AbstractC3332b f15264a;

    public XJRenderActivityPresenter(XJRenderActivityContract.AbstractC3332b bVar) {
        this.f15264a = bVar;
        this.f15264a.mo14357a((XJRenderActivityContract.AbstractC3332b) this);
    }

    @Override // p110z1.IXJBasePresenter
    /* renamed from: a */
    public void mo14355a() {
        EventBus.m3448a().m3446a(this);
    }

    @Subscribe(m3389a = ThreadMode.MAIN)
    /* renamed from: a */
    public void m14354a(XJEvent.C3333a aVar) {
        if (aVar.f15274a == 2) {
            String str = aVar.f15275b;
            if (this.f15264a == null) {
                return;
            }
            if ("true".equals(str)) {
                this.f15264a.mo14356a(0);
            } else {
                this.f15264a.mo14356a(4);
            }
        }
    }

    @Override // p110z1.IXJBasePresenter
    /* renamed from: b */
    public void mo14353b() {
        EventBus.m3448a().m3430c(this);
        this.f15264a = null;
    }
}
