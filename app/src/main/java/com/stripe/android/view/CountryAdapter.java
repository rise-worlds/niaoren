package com.stripe.android.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;
import com.stripe.android.C2364R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

/* renamed from: com.stripe.android.view.c */
/* loaded from: classes2.dex */
class CountryAdapter extends ArrayAdapter {
    @VisibleForTesting

    /* renamed from: a */
    List<String> f12601a;
    @VisibleForTesting

    /* renamed from: b */
    List<String> f12602b;

    /* renamed from: c */
    private Filter f12603c = new Filter() { // from class: com.stripe.android.view.c.1
        @Override // android.widget.Filter
        protected Filter.FilterResults performFiltering(CharSequence charSequence) {
            Filter.FilterResults filterResults = new Filter.FilterResults();
            List arrayList = new ArrayList();
            if (charSequence == null) {
                filterResults.values = CountryAdapter.this.f12601a;
                return filterResults;
            }
            String lowerCase = charSequence.toString().toLowerCase();
            for (String str : CountryAdapter.this.f12601a) {
                if (str.toLowerCase().startsWith(lowerCase)) {
                    arrayList.add(str);
                }
            }
            if (arrayList.size() == 0 || (arrayList.size() == 1 && ((String) arrayList.get(0)).equals(charSequence))) {
                arrayList = CountryAdapter.this.f12601a;
            }
            filterResults.values = arrayList;
            return filterResults;
        }

        @Override // android.widget.Filter
        protected void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
            CountryAdapter cVar = CountryAdapter.this;
            cVar.f12602b = (List) filterResults.values;
            cVar.notifyDataSetChanged();
        }
    };

    /* renamed from: d */
    private Context f12604d;

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CountryAdapter(Context context, List<String> list) {
        super(context, C2364R.layout.menu_text_view);
        this.f12604d = context;
        this.f12601a = m17266a(list);
        this.f12602b = this.f12601a;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public int getCount() {
        return this.f12602b.size();
    }

    /* renamed from: a */
    public String getItem(int i) {
        return this.f12602b.get(i);
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null || !(view instanceof TextView)) {
            TextView textView = (TextView) LayoutInflater.from(this.f12604d).inflate(C2364R.layout.menu_text_view, viewGroup, false);
            textView.setText(getItem(i));
            return textView;
        }
        ((TextView) view).setText(getItem(i));
        return view;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Filterable
    @NonNull
    public Filter getFilter() {
        return this.f12603c;
    }

    @VisibleForTesting
    /* renamed from: a */
    List m17266a(List<String> list) {
        Collections.sort(list, new Comparator<String>() { // from class: com.stripe.android.view.c.2
            /* renamed from: a */
            public int compare(String str, String str2) {
                return str.toLowerCase().compareTo(str2.toLowerCase());
            }
        });
        list.remove(m17268a().getDisplayCountry());
        list.add(0, m17268a().getDisplayCountry());
        return list;
    }

    @VisibleForTesting
    /* renamed from: a */
    Locale m17268a() {
        if (Build.VERSION.SDK_INT >= 24) {
            return this.f12604d.getResources().getConfiguration().getLocales().get(0);
        }
        return this.f12604d.getResources().getConfiguration().locale;
    }
}
