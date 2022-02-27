package com.angel.nrzs.adapter;

import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import p110z1.NotNull;

/* loaded from: classes.dex */
public class MainFragmentAdapter extends FragmentStatePagerAdapter {

    /* renamed from: a */
    private FragmentManager f5162a;

    /* renamed from: b */
    private List<Fragment> f5163b = new ArrayList();

    @Override // android.support.v4.app.FragmentStatePagerAdapter, android.support.v4.view.PagerAdapter
    public Parcelable saveState() {
        return null;
    }

    public MainFragmentAdapter(FragmentManager fragmentManager, List<Fragment> list) {
        super(fragmentManager);
        this.f5162a = fragmentManager;
        if (list != null) {
            this.f5163b.addAll(list);
        }
    }

    /* renamed from: a */
    public void m25183a(List<Fragment> list) {
        if (list != null) {
            this.f5163b.clear();
            this.f5163b.addAll(list);
            notifyDataSetChanged();
        }
    }

    @Override // android.support.v4.app.FragmentStatePagerAdapter
    public Fragment getItem(int i) {
        return this.f5163b.get(i);
    }

    @Override // android.support.v4.view.PagerAdapter
    public int getCount() {
        return this.f5163b.size();
    }

    @Override // android.support.v4.view.PagerAdapter
    public int getItemPosition(Object obj) {
        if (!((Fragment) obj).isAdded() || !this.f5163b.contains(obj)) {
            return -2;
        }
        return this.f5163b.indexOf(obj);
    }

    @Override // android.support.v4.app.FragmentStatePagerAdapter, android.support.v4.view.PagerAdapter
    @NotNull
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        Fragment fragment = (Fragment) super.instantiateItem(viewGroup, i);
        Fragment fragment2 = this.f5163b.get(i);
        if (fragment == fragment2) {
            return fragment;
        }
        this.f5162a.beginTransaction().add(viewGroup.getId(), fragment2).commitNowAllowingStateLoss();
        return fragment2;
    }

    @Override // android.support.v4.app.FragmentStatePagerAdapter, android.support.v4.view.PagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        Fragment fragment = (Fragment) obj;
        if (this.f5163b.contains(fragment)) {
            super.destroyItem(viewGroup, i, (Object) fragment);
        } else {
            this.f5162a.beginTransaction().remove(fragment).commitNowAllowingStateLoss();
        }
    }
}
