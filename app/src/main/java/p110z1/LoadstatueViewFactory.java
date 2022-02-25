package p110z1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.nrzs.moudleui.C2202R;

/* renamed from: z1.apt */
/* loaded from: classes3.dex */
public class LoadstatueViewFactory {
    /* renamed from: a */
    private static void m11676a(View view, View view2) {
        View view3 = (View) view2.getParent();
        if (view3 == null) {
            return;
        }
        if (view3 instanceof RelativeLayout) {
            ((RelativeLayout) view3).addView(view);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(view.getLayoutParams());
            layoutParams.addRule(13, -1);
            layoutParams.width = -1;
            layoutParams.height = -1;
            view.setLayoutParams(layoutParams);
        } else if (view3 instanceof LinearLayout) {
            ((LinearLayout) view3).addView(view);
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(view.getLayoutParams());
            layoutParams2.width = -1;
            layoutParams2.height = -1;
            layoutParams2.gravity = 17;
            view.setLayoutParams(layoutParams2);
        } else {
            throw new IllegalArgumentException("ParentView must be a RelativeLayout or LinearLayout!!!");
        }
    }

    /* renamed from: a */
    public static View m11677a(Context context, View view, View.OnClickListener onClickListener) {
        View inflate = LayoutInflater.from(context).inflate(C2202R.layout.bird_no_data, (ViewGroup) null);
        inflate.setOnClickListener(onClickListener);
        inflate.setVisibility(8);
        m11676a(inflate, view);
        return inflate;
    }

    /* renamed from: b */
    public static View m11674b(Context context, View view, View.OnClickListener onClickListener) {
        View inflate = LayoutInflater.from(context).inflate(C2202R.layout.bird_no_network, (ViewGroup) null);
        if (onClickListener != null) {
            inflate.setOnClickListener(onClickListener);
        }
        inflate.setVisibility(8);
        m11676a(inflate, view);
        return inflate;
    }

    /* renamed from: a */
    public static View m11678a(Context context, View view) {
        View inflate = LayoutInflater.from(context).inflate(C2202R.layout.bird_network_loading, (ViewGroup) null);
        inflate.setVisibility(8);
        m11676a(inflate, view);
        return inflate;
    }

    /* renamed from: b */
    public static View m11675b(Context context, View view) {
        View inflate = LayoutInflater.from(context).inflate(C2202R.layout.bird_network_pay_loading, (ViewGroup) null);
        inflate.setVisibility(8);
        m11676a(inflate, view);
        return inflate;
    }

    /* renamed from: c */
    public static View m11673c(Context context, View view) {
        View inflate = LayoutInflater.from(context).inflate(C2202R.layout.nrzs_ft_layout_assist_empty, (ViewGroup) null);
        inflate.setVisibility(8);
        m11676a(inflate, view);
        return inflate;
    }

    /* renamed from: d */
    public static View m11672d(Context context, View view) {
        View inflate = LayoutInflater.from(context).inflate(C2202R.layout.nrzs_ft_layout_assist_empty, (ViewGroup) null);
        ((TextView) inflate.findViewById(C2202R.C2204id.nrzs_ft_tips)).setText(C2202R.string.nrzs_ff_assist_empty_tips);
        inflate.setVisibility(8);
        m11676a(inflate, view);
        return inflate;
    }

    /* renamed from: e */
    public static View m11671e(Context context, View view) {
        View inflate = LayoutInflater.from(context).inflate(C2202R.layout.nrzs_ft_layout_assist_empty, (ViewGroup) null);
        ((TextView) inflate.findViewById(C2202R.C2204id.nrzs_ft_tips)).setText(C2202R.string.nrzs_ff_assist_fail_tips);
        inflate.setVisibility(8);
        m11676a(inflate, view);
        return inflate;
    }
}
