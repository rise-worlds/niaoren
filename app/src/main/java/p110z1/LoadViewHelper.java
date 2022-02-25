package p110z1;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;

/* renamed from: z1.apo */
/* loaded from: classes3.dex */
public class LoadViewHelper implements IloadViewResult {

    /* renamed from: a */
    private View f17167a;

    /* renamed from: b */
    private View f17168b;

    /* renamed from: c */
    private View f17169c;

    /* renamed from: d */
    private View f17170d;

    /* renamed from: e */
    private Handler f17171e = new Handler(Looper.getMainLooper()) { // from class: z1.apo.1
        @Override // android.os.Handler
        public void dispatchMessage(Message message) {
            switch (message.what) {
                case 0:
                    LoadViewHelper.this.f17167a.setVisibility(0);
                    LoadViewHelper.this.f17168b.setVisibility(8);
                    LoadViewHelper.this.f17169c.setVisibility(8);
                    LoadViewHelper.this.f17170d.setVisibility(8);
                    return;
                case 1:
                    LoadViewHelper.this.f17167a.setVisibility(8);
                    LoadViewHelper.this.f17168b.setVisibility(8);
                    LoadViewHelper.this.f17169c.setVisibility(8);
                    LoadViewHelper.this.f17170d.setVisibility(0);
                    return;
                case 2:
                    LoadViewHelper.this.f17167a.setVisibility(8);
                    LoadViewHelper.this.f17168b.setVisibility(0);
                    LoadViewHelper.this.f17169c.setVisibility(8);
                    LoadViewHelper.this.f17170d.setVisibility(8);
                    return;
                case 3:
                    LoadViewHelper.this.f17167a.setVisibility(8);
                    LoadViewHelper.this.f17168b.setVisibility(8);
                    LoadViewHelper.this.f17169c.setVisibility(0);
                    LoadViewHelper.this.f17170d.setVisibility(8);
                    return;
                default:
                    return;
            }
        }
    };

    /* compiled from: LoadViewHelper.java */
    /* renamed from: z1.apo$a */
    /* loaded from: classes3.dex */
    public interface AbstractC3846a {

        /* renamed from: a */
        public static final int f17173a = 0;

        /* renamed from: b */
        public static final int f17174b = 1;

        /* renamed from: c */
        public static final int f17175c = 2;

        /* renamed from: d */
        public static final int f17176d = 3;

        /* renamed from: a */
        View mo11690a();

        /* renamed from: a */
        View mo11689a(View view);

        /* renamed from: b */
        View mo11688b(View view);

        /* renamed from: c */
        View mo11687c(View view);
    }

    public LoadViewHelper(AbstractC3846a aVar) {
        this.f17169c = aVar.mo11690a();
        this.f17167a = aVar.mo11689a(this.f17169c);
        this.f17168b = aVar.mo11688b(this.f17169c);
        this.f17170d = aVar.mo11687c(this.f17169c);
    }

    @Override // p110z1.IloadViewResult
    /* renamed from: k_ */
    public void mo11682k_() {
        this.f17171e.sendEmptyMessage(0);
    }

    @Override // p110z1.IloadViewResult
    /* renamed from: l_ */
    public void mo11681l_() {
        this.f17171e.sendEmptyMessage(2);
    }

    @Override // p110z1.IloadViewResult
    /* renamed from: m_ */
    public void mo11680m_() {
        this.f17171e.sendEmptyMessage(1);
    }

    @Override // p110z1.IloadViewResult
    /* renamed from: n_ */
    public void mo11679n_() {
        this.f17171e.sendEmptyMessage(3);
    }
}
