package com.stripe.android.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import com.stripe.android.model.ShippingMethod;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.stripe.android.view.k */
/* loaded from: classes2.dex */
class ShippingMethodAdapter extends RecyclerView.Adapter<C2481a> {
    @NonNull

    /* renamed from: a */
    private List<ShippingMethod> f12627a = new ArrayList();
    @NonNull

    /* renamed from: b */
    private int f12628b = 0;

    @Override // android.support.p006v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.f12627a.size();
    }

    @Override // android.support.p006v7.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        return super.getItemId(i);
    }

    /* renamed from: a */
    public C2481a onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new C2481a(new ShippingMethodView(viewGroup.getContext()));
    }

    /* renamed from: a */
    public void onBindViewHolder(C2481a aVar, int i) {
        aVar.m17226a(this.f12627a.get(i));
        aVar.m17227a(i);
        aVar.m17225a(i == this.f12628b);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public ShippingMethod m17232a() {
        return this.f12627a.get(this.f12628b);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m17228a(List<ShippingMethod> list, ShippingMethod shippingMethod) {
        if (list != null) {
            this.f12627a = list;
        }
        if (shippingMethod == null) {
            this.f12628b = 0;
        } else {
            this.f12628b = this.f12627a.indexOf(shippingMethod);
        }
        notifyDataSetChanged();
    }

    /* renamed from: a */
    void m17231a(int i) {
        this.f12628b = i;
        notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ShippingMethodAdapter.java */
    /* renamed from: com.stripe.android.view.k$a */
    /* loaded from: classes2.dex */
    public class C2481a extends RecyclerView.ViewHolder {

        /* renamed from: a */
        ShippingMethodView f12629a;

        /* renamed from: b */
        int f12630b;

        C2481a(ShippingMethodView lVar) {
            super(lVar);
            this.f12629a = lVar;
            lVar.setOnClickListener(new View.OnClickListener() { // from class: com.stripe.android.view.k.a.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    ShippingMethodAdapter.this.m17231a(C2481a.this.f12630b);
                }
            });
        }

        /* renamed from: a */
        void m17226a(ShippingMethod shippingMethod) {
            this.f12629a.m17223a(shippingMethod);
        }

        /* renamed from: a */
        void m17225a(boolean z) {
            this.f12629a.setSelected(z);
        }

        /* renamed from: a */
        void m17227a(int i) {
            this.f12630b = i;
        }
    }
}
