package p110z1;

import android.content.Context;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import com.nrzs.moudleui.C2202R;

/* renamed from: z1.apu */
/* loaded from: classes3.dex */
public class WaitPopView extends PopupWindow {

    /* renamed from: a */
    private AbstractC3848a f17184a;

    /* compiled from: WaitPopView.java */
    /* renamed from: z1.apu$a */
    /* loaded from: classes3.dex */
    public interface AbstractC3848a {
        /* renamed from: a */
        void m11668a();
    }

    public WaitPopView(Context context, String str, AbstractC3848a aVar) {
        m11670a(context, str, aVar);
    }

    /* renamed from: a */
    private void m11670a(Context context, String str, AbstractC3848a aVar) {
        this.f17184a = aVar;
        View inflate = LayoutInflater.from(context).inflate(C2202R.layout.bird_all_pop, (ViewGroup) null);
        setContentView(inflate);
        setOutsideTouchable(false);
        setFocusable(true);
        inflate.setFocusable(true);
        inflate.setFocusableInTouchMode(true);
        if (aVar != null) {
            inflate.setOnKeyListener(new View.OnKeyListener() { // from class: z1.apu.1
                @Override // android.view.View.OnKeyListener
                public boolean onKey(View view, int i, KeyEvent keyEvent) {
                    if (i != 4) {
                        return false;
                    }
                    if (WaitPopView.this.f17184a != null) {
                        WaitPopView.this.f17184a.m11668a();
                    }
                    WaitPopView.this.dismiss();
                    return true;
                }
            });
        }
    }
}
