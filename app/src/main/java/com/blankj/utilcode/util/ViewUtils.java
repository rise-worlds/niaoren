package com.blankj.utilcode.util;

import android.view.View;
import android.view.ViewGroup;

/* renamed from: com.blankj.utilcode.util.bf */
/* loaded from: classes.dex */
public class ViewUtils {
    /* renamed from: a */
    public static void m22985a(View view, boolean z) {
        m22984a(view, z, null);
    }

    /* renamed from: a */
    public static void m22984a(View view, boolean z, View... viewArr) {
        if (view != null) {
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                int childCount = viewGroup.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    m22984a(viewGroup.getChildAt(i), z, viewArr);
                }
            }
            if (viewArr != null) {
                for (View view2 : viewArr) {
                    if (view == view2) {
                        return;
                    }
                }
            }
            view.setEnabled(z);
        }
    }
}
