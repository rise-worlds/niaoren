package com.stripe.android.view;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.stripe.android.C2364R;
import com.stripe.android.model.C2395g;
import com.stripe.android.model.Customer;
import com.stripe.android.model.CustomerSource;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.stripe.android.view.g */
/* loaded from: classes2.dex */
class MaskedCardAdapter extends RecyclerView.Adapter<C2479a> {

    /* renamed from: a */
    private static final int f12611a = -1;

    /* renamed from: c */
    private int f12613c = -1;
    @NonNull

    /* renamed from: b */
    private List<CustomerSource> f12612b = new ArrayList();

    /* JADX INFO: Access modifiers changed from: package-private */
    public MaskedCardAdapter(@NonNull List<CustomerSource> list) {
        m17243a((CustomerSource[]) list.toArray(new CustomerSource[list.size()]));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m17244a(@NonNull List<CustomerSource> list) {
        this.f12612b.clear();
        this.f12612b = list;
        notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m17248a(@NonNull Customer dVar) {
        this.f12612b = dVar.m17810f();
        String d = dVar.m17812d();
        if (d == null) {
            m17250a(-1);
        } else {
            m17245a(d);
        }
        notifyDataSetChanged();
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.f12612b.size();
    }

    /* renamed from: a */
    public void onBindViewHolder(C2479a aVar, int i) {
        aVar.m17241a(this.f12612b.get(i));
        aVar.m17242a(i);
        aVar.m17240a(i == this.f12613c);
    }

    /* renamed from: a */
    public C2479a onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new C2479a((FrameLayout) LayoutInflater.from(viewGroup.getContext()).inflate(C2364R.layout.masked_card_row, viewGroup, false));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public boolean m17245a(@NonNull String str) {
        for (int i = 0; i < this.f12612b.size(); i++) {
            if (str.equals(this.f12612b.get(i).mo17592A())) {
                m17250a(i);
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    /* renamed from: a */
    public CustomerSource m17251a() {
        int i = this.f12613c;
        if (i == -1) {
            return null;
        }
        return this.f12612b.get(i);
    }

    /* renamed from: a */
    void m17243a(CustomerSource... eVarArr) {
        if (eVarArr != null) {
            for (CustomerSource eVar : eVarArr) {
                if (eVar.m17801f() != null || m17247a(eVar.m17803d())) {
                    this.f12612b.add(eVar);
                }
            }
            notifyDataSetChanged();
        }
    }

    /* renamed from: a */
    boolean m17247a(@Nullable C2395g gVar) {
        return gVar != null && "card".equals(gVar.m17757q());
    }

    /* renamed from: a */
    void m17250a(int i) {
        this.f12613c = i;
        notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: MaskedCardAdapter.java */
    /* renamed from: com.stripe.android.view.g$a */
    /* loaded from: classes2.dex */
    public class C2479a extends RecyclerView.ViewHolder {

        /* renamed from: a */
        MaskedCardView f12614a;

        /* renamed from: b */
        int f12615b;

        C2479a(FrameLayout frameLayout) {
            super(frameLayout);
            this.f12614a = (MaskedCardView) frameLayout.findViewById(C2364R.C2366id.masked_card_item);
            frameLayout.setOnClickListener(new View.OnClickListener() { // from class: com.stripe.android.view.g.a.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (!C2479a.this.f12614a.isSelected()) {
                        C2479a.this.f12614a.m17353a();
                        MaskedCardAdapter.this.m17250a(C2479a.this.f12615b);
                    }
                }
            });
        }

        /* renamed from: a */
        void m17241a(@NonNull CustomerSource eVar) {
            this.f12614a.setCustomerSource(eVar);
        }

        /* renamed from: a */
        void m17242a(int i) {
            this.f12615b = i;
        }

        /* renamed from: a */
        void m17240a(boolean z) {
            this.f12614a.setSelected(z);
        }
    }
}
