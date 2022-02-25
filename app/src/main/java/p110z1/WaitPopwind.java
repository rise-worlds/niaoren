package p110z1;

import android.app.Activity;
import android.content.Context;
import android.widget.PopupWindow;
import p110z1.WaitPopView;

/* renamed from: z1.apv */
/* loaded from: classes3.dex */
public class WaitPopwind {

    /* renamed from: a */
    private static WaitPopwind f17186a;

    /* renamed from: b */
    private PopupWindow f17187b;

    /* renamed from: c */
    private PopupWindow f17188c;

    private WaitPopwind() {
    }

    /* renamed from: a */
    public static WaitPopwind m11667a() {
        if (f17186a == null) {
            f17186a = new WaitPopwind();
        }
        return f17186a;
    }

    /* renamed from: a */
    public PopupWindow m11666a(Context context, String str, WaitPopView.AbstractC3848a aVar) {
        this.f17187b = new WaitPopView(context, str, aVar);
        this.f17187b.showAtLocation(((Activity) context).getWindow().getDecorView(), 17, 0, 0);
        return this.f17187b;
    }

    /* renamed from: b */
    public void m11665b() {
        PopupWindow popupWindow = this.f17188c;
        if (popupWindow != null && popupWindow.isShowing()) {
            this.f17188c.dismiss();
        }
        this.f17188c = null;
    }
}
