package p110z1;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.p003v4.widget.PopupWindowCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import com.angel.nrzs.C0692R;

/* renamed from: z1.fa */
/* loaded from: classes3.dex */
public class TipsPopWindow extends PopupWindow {

    /* renamed from: a */
    private static TipsPopWindow f21617a;

    public TipsPopWindow(Context context) {
        super(context);
        setHeight(-2);
        setWidth(-2);
        setOutsideTouchable(true);
        setFocusable(true);
        setBackgroundDrawable(new ColorDrawable(0));
        setContentView(LayoutInflater.from(context).inflate(C0692R.layout.nrzs_pop_brid_nb_tips, (ViewGroup) null, false));
    }

    /* renamed from: a */
    public static void m2980a(Context context, View view) {
        if (f21617a == null) {
            f21617a = new TipsPopWindow(context);
        }
        PopupWindowCompat.showAsDropDown(f21617a, view, -C5352fh.m2862a(context, 65.0f), 5, 80);
    }
}
