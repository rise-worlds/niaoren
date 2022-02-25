package com.stripe.android.view;

import android.content.Context;
import android.support.annotation.RestrictTo;
import android.support.annotation.VisibleForTesting;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.FrameLayout;
import com.stripe.android.C2364R;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;

@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes2.dex */
public class CountryAutoCompleteTextView extends FrameLayout {

    /* renamed from: a */
    private AutoCompleteTextView f12484a;

    /* renamed from: b */
    private Map<String, String> f12485b;

    /* renamed from: c */
    private String f12486c;

    /* renamed from: d */
    private AbstractC2453a f12487d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.stripe.android.view.CountryAutoCompleteTextView$a */
    /* loaded from: classes2.dex */
    public interface AbstractC2453a {
        /* renamed from: a */
        void mo17283a(String str);
    }

    public CountryAutoCompleteTextView(Context context) {
        super(context);
        m17370a();
    }

    public CountryAutoCompleteTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m17370a();
    }

    public CountryAutoCompleteTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m17370a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getSelectedCountryCode() {
        return this.f12486c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setCountrySelected(String str) {
        m17368a(new Locale("", str).getDisplayCountry());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setCountryChangeListener(AbstractC2453a aVar) {
        this.f12487d = aVar;
    }

    /* renamed from: a */
    private void m17370a() {
        inflate(getContext(), C2364R.layout.country_autocomplete_textview, this);
        this.f12484a = (AutoCompleteTextView) findViewById(C2364R.C2366id.autocomplete_country_cat);
        this.f12485b = CountryUtils.m17264a();
        CountryAdapter cVar = new CountryAdapter(getContext(), new ArrayList(this.f12485b.keySet()));
        this.f12484a.setThreshold(0);
        this.f12484a.setAdapter(cVar);
        this.f12484a.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.stripe.android.view.CountryAutoCompleteTextView.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                CountryAutoCompleteTextView.this.m17368a(CountryAutoCompleteTextView.this.f12484a.getText().toString());
            }
        });
        String str = (String) cVar.getItem(0);
        m17368a(str);
        this.f12484a.setText(str);
        this.f12484a.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.stripe.android.view.CountryAutoCompleteTextView.2
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View view, boolean z) {
                String obj = CountryAutoCompleteTextView.this.f12484a.getText().toString();
                if (z) {
                    CountryAutoCompleteTextView.this.f12484a.showDropDown();
                } else {
                    CountryAutoCompleteTextView.this.m17368a(obj);
                }
            }
        });
    }

    @VisibleForTesting
    /* renamed from: a */
    void m17368a(String str) {
        String str2 = this.f12485b.get(str);
        if (str2 != null) {
            String str3 = this.f12486c;
            if (str3 == null || !str3.equals(str2)) {
                this.f12486c = str2;
                AbstractC2453a aVar = this.f12487d;
                if (aVar != null) {
                    aVar.mo17283a(this.f12486c);
                }
            }
            this.f12484a.setText(str);
            return;
        }
        this.f12484a.setText(new Locale("", this.f12486c).getDisplayCountry());
    }
}
