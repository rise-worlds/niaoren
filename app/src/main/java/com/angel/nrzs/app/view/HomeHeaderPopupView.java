package com.angel.nrzs.app.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.p003v4.widget.PopupWindowCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.angel.nrzs.C0692R;

/* renamed from: com.angel.nrzs.ui.view.a */
/* loaded from: classes.dex */
public class HomeHeaderPopupView extends PopupWindow {

    /* renamed from: a */
    private static HomeHeaderPopupView f5642a;

    /* renamed from: b */
    private View f5643b;

    /* renamed from: c */
    private ImageView f5644c;

    /* renamed from: d */
    private TextView f5645d;

    public HomeHeaderPopupView(Context context) {
        super(context);
        setHeight(-2);
        setWidth(-2);
        setOutsideTouchable(true);
        setFocusable(true);
        setBackgroundDrawable(new ColorDrawable(0));
        this.f5643b = LayoutInflater.from(context).inflate(C0692R.layout.nrzs_home_header_pop_layout, (ViewGroup) null, false);
        this.f5645d = (TextView) this.f5643b.findViewById(C0692R.C0694id.f3169yv);
        setContentView(this.f5643b);
    }

    /* renamed from: a */
    private void m24863a(int i) {
        this.f5645d.setText(i);
    }

    /* renamed from: a */
    public static void m24862a(Context context, View view) {
        if (f5642a == null) {
            f5642a = new HomeHeaderPopupView(context);
        }
        f5642a.m24863a(C0692R.string.f3758fp);
        PopupWindowCompat.showAsDropDown(f5642a, view, 0, -((view.getHeight() * 2) + 10), 80);
    }

    /* renamed from: b */
    public static void m24861b(Context context, View view) {
        if (f5642a == null) {
            f5642a = new HomeHeaderPopupView(context);
        }
        f5642a.m24863a(C0692R.string.f3986lv);
        PopupWindowCompat.showAsDropDown(f5642a, view, 0, 0, 80);
    }
}
