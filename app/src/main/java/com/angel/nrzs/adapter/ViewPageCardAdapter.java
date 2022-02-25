package com.angel.nrzs.adapter;

import android.content.Context;
import android.support.p003v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import com.angel.nrzs.ddy.view.DdyHookview;
import com.nrzs.data.ddy.bean.respond.OrderDaileInfo;
import java.util.List;

/* loaded from: classes.dex */
public class ViewPageCardAdapter extends PagerAdapter {

    /* renamed from: a */
    private List<OrderDaileInfo> f5217a;

    /* renamed from: b */
    private Context f5218b;

    /* renamed from: a */
    public void m25151a(OrderDaileInfo orderDaileInfo, int i) {
    }

    @Override // android.support.p003v4.view.PagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public ViewPageCardAdapter(Context context, List<OrderDaileInfo> list) {
        this.f5218b = context;
        this.f5217a = list;
    }

    /* renamed from: a */
    public void m25150a(List<OrderDaileInfo> list) {
        this.f5217a = list;
    }

    @Override // android.support.p003v4.view.PagerAdapter
    public int getCount() {
        List<OrderDaileInfo> list = this.f5217a;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    /* renamed from: a */
    public int m25152a(OrderDaileInfo orderDaileInfo) {
        int size = this.f5217a.size();
        for (int i = 0; i < size; i++) {
            OrderDaileInfo orderDaileInfo2 = this.f5217a.get(i);
            if ((orderDaileInfo2 instanceof OrderDaileInfo) && orderDaileInfo2.DDYOrderId == orderDaileInfo.DDYOrderId) {
                return i;
            }
        }
        return -1;
    }

    @Override // android.support.p003v4.view.PagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        if (!(this.f5217a.get(i) instanceof OrderDaileInfo)) {
            return null;
        }
        DdyHookview ddyHookview = new DdyHookview(this.f5218b, this.f5217a.get(i));
        viewGroup.addView(ddyHookview);
        return ddyHookview;
    }

    @Override // android.support.p003v4.view.PagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) obj);
    }
}
