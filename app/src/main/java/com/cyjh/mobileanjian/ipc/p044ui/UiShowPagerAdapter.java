package com.cyjh.mobileanjian.ipc.p044ui;

import android.support.p003v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.cyjh.mobileanjian.ipc.ui.l */
/* loaded from: classes.dex */
public final class UiShowPagerAdapter extends PagerAdapter {

    /* renamed from: a */
    List<LinearLayout> f8602a;

    /* renamed from: b */
    List<String> f8603b;

    @Override // android.support.p003v4.view.PagerAdapter
    public final boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public UiShowPagerAdapter(List<LinearLayout> list, List<String> list2) {
        this.f8602a = list;
        this.f8603b = list2;
    }

    @Override // android.support.p003v4.view.PagerAdapter
    public final int getCount() {
        return this.f8602a.size();
    }

    @Override // android.support.p003v4.view.PagerAdapter
    public final Object instantiateItem(ViewGroup viewGroup, int i) {
        LinearLayout linearLayout = this.f8602a.get(i);
        viewGroup.addView(linearLayout);
        return linearLayout;
    }

    @Override // android.support.p003v4.view.PagerAdapter
    public final void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView(this.f8602a.get(i));
    }

    @Override // android.support.p003v4.view.PagerAdapter
    public final CharSequence getPageTitle(int i) {
        return this.f8603b.get(i);
    }

    /* renamed from: a */
    private View m20722a(int i) {
        return this.f8602a.get(i);
    }

    /* renamed from: a */
    private void m20720a(LinearLayout linearLayout, String str) {
        this.f8602a.add(linearLayout);
        this.f8603b.add(str);
        notifyDataSetChanged();
    }

    /* renamed from: a */
    public final void m20721a(View view, String str, String str2) {
        for (LinearLayout linearLayout : this.f8602a) {
            if (linearLayout.getTag().equals(str)) {
                ((LinearLayout) linearLayout.findViewWithTag(str2)).addView(view);
            }
        }
    }

    /* renamed from: a */
    private void m20718a(String str, String str2, int i, int i2) {
        for (LinearLayout linearLayout : this.f8602a) {
            if (linearLayout.getTag().equals(str2)) {
                linearLayout.addView(UiFactory.m20882a(linearLayout.getContext()).m20867c(str, i, i2));
            }
        }
    }

    /* renamed from: a */
    private View m20719a(String str) {
        Iterator<LinearLayout> it = this.f8602a.iterator();
        View view = null;
        while (it.hasNext() && (view = it.next().findViewWithTag(str)) == null) {
        }
        return view;
    }
}
