package com.stripe.android.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.stripe.android.C2364R;
import com.stripe.android.model.Address;
import com.stripe.android.model.ShippingInformation;
import com.stripe.android.view.CountryAutoCompleteTextView;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* loaded from: classes2.dex */
public class ShippingInfoWidget extends LinearLayout {

    /* renamed from: a */
    public static final String f12558a = "address_line_one";

    /* renamed from: b */
    public static final String f12559b = "address_line_two";

    /* renamed from: c */
    public static final String f12560c = "city";

    /* renamed from: d */
    public static final String f12561d = "postal_code";

    /* renamed from: e */
    public static final String f12562e = "state";

    /* renamed from: f */
    public static final String f12563f = "phone";

    /* renamed from: g */
    private List<String> f12564g = new ArrayList();

    /* renamed from: h */
    private List<String> f12565h = new ArrayList();

    /* renamed from: i */
    private CountryAutoCompleteTextView f12566i;

    /* renamed from: j */
    private TextInputLayout f12567j;

    /* renamed from: k */
    private TextInputLayout f12568k;

    /* renamed from: l */
    private TextInputLayout f12569l;

    /* renamed from: m */
    private TextInputLayout f12570m;

    /* renamed from: n */
    private TextInputLayout f12571n;

    /* renamed from: o */
    private TextInputLayout f12572o;

    /* renamed from: p */
    private TextInputLayout f12573p;

    /* renamed from: q */
    private StripeEditText f12574q;

    /* renamed from: r */
    private StripeEditText f12575r;

    /* renamed from: s */
    private StripeEditText f12576s;

    /* renamed from: t */
    private StripeEditText f12577t;

    /* renamed from: u */
    private StripeEditText f12578u;

    /* renamed from: v */
    private StripeEditText f12579v;

    /* renamed from: w */
    private StripeEditText f12580w;

    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: com.stripe.android.view.ShippingInfoWidget$a */
    /* loaded from: classes2.dex */
    public @interface AbstractC2468a {
    }

    public ShippingInfoWidget(Context context) {
        super(context);
        m17291b();
    }

    public ShippingInfoWidget(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m17291b();
    }

    public ShippingInfoWidget(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m17291b();
    }

    public void setOptionalFields(@Nullable List<String> list) {
        if (list != null) {
            this.f12564g = list;
        } else {
            this.f12564g = new ArrayList();
        }
        m17289d();
        m17292a(this.f12566i.getSelectedCountryCode());
    }

    public void setHiddenFields(@Nullable List<String> list) {
        if (list != null) {
            this.f12565h = list;
        } else {
            this.f12565h = new ArrayList();
        }
        m17289d();
        m17292a(this.f12566i.getSelectedCountryCode());
    }

    public ShippingInformation getShippingInformation() {
        if (!m17296a()) {
            return null;
        }
        return new ShippingInformation(new Address.C2386a().m17951a(this.f12576s.getText().toString()).m17949b(this.f12566i.getSelectedCountryCode()).m17947c(this.f12574q.getText().toString()).m17945d(this.f12575r.getText().toString()).m17943e(this.f12578u.getText().toString()).m17941f(this.f12579v.getText().toString()).m17953a(), this.f12577t.getText().toString(), this.f12580w.getText().toString());
    }

    /* renamed from: a */
    public void m17295a(@Nullable ShippingInformation shippingInformation) {
        if (shippingInformation != null) {
            Address c = shippingInformation.m17939c();
            if (c != null) {
                this.f12576s.setText(c.m17966c());
                if (c.m17964d() != null && !c.m17964d().isEmpty()) {
                    this.f12566i.setCountrySelected(c.m17964d());
                }
                this.f12574q.setText(c.m17962e());
                this.f12575r.setText(c.m17960f());
                this.f12578u.setText(c.m17958g());
                this.f12579v.setText(c.m17956h());
            }
            this.f12577t.setText(shippingInformation.m17938d());
            this.f12580w.setText(shippingInformation.m17937e());
        }
    }

    /* renamed from: a */
    public boolean m17296a() {
        boolean z;
        String selectedCountryCode = this.f12566i.getSelectedCountryCode();
        if (this.f12578u.getText().toString().isEmpty() && (this.f12564g.contains(f12561d) || this.f12565h.contains(f12561d))) {
            z = true;
        } else if (selectedCountryCode.equals(Locale.US.getCountry())) {
            z = CountryUtils.m17262b(this.f12578u.getText().toString().trim());
        } else if (selectedCountryCode.equals(Locale.UK.getCountry())) {
            z = CountryUtils.m17262b(this.f12578u.getText().toString().trim());
        } else if (selectedCountryCode.equals(Locale.CANADA.getCountry())) {
            z = CountryUtils.m17262b(this.f12578u.getText().toString().trim());
        } else {
            z = CountryUtils.m17263a(selectedCountryCode) ? !this.f12578u.getText().toString().isEmpty() : true;
        }
        this.f12578u.setShouldShowError(!z);
        boolean z2 = this.f12574q.getText().toString().isEmpty() && !this.f12564g.contains(f12558a) && !this.f12565h.contains(f12558a);
        this.f12574q.setShouldShowError(z2);
        boolean z3 = this.f12576s.getText().toString().isEmpty() && !this.f12564g.contains(f12560c) && !this.f12565h.contains(f12560c);
        this.f12576s.setShouldShowError(z3);
        boolean isEmpty = this.f12577t.getText().toString().isEmpty();
        this.f12577t.setShouldShowError(isEmpty);
        boolean z4 = this.f12579v.getText().toString().isEmpty() && !this.f12564g.contains(f12562e) && !this.f12565h.contains(f12562e);
        this.f12579v.setShouldShowError(z4);
        boolean z5 = this.f12580w.getText().toString().isEmpty() && !this.f12564g.contains(f12563f) && !this.f12565h.contains(f12563f);
        this.f12580w.setShouldShowError(z5);
        return z && !z2 && !z3 && !z4 && !isEmpty && !z5;
    }

    /* renamed from: b */
    private void m17291b() {
        setOrientation(1);
        inflate(getContext(), C2364R.layout.add_address_widget, this);
        this.f12566i = (CountryAutoCompleteTextView) findViewById(C2364R.C2366id.country_autocomplete_aaw);
        this.f12567j = (TextInputLayout) findViewById(C2364R.C2366id.tl_address_line1_aaw);
        this.f12568k = (TextInputLayout) findViewById(C2364R.C2366id.tl_address_line2_aaw);
        this.f12569l = (TextInputLayout) findViewById(C2364R.C2366id.tl_city_aaw);
        this.f12570m = (TextInputLayout) findViewById(C2364R.C2366id.tl_name_aaw);
        this.f12571n = (TextInputLayout) findViewById(C2364R.C2366id.tl_postal_code_aaw);
        this.f12572o = (TextInputLayout) findViewById(C2364R.C2366id.tl_state_aaw);
        this.f12574q = (StripeEditText) findViewById(C2364R.C2366id.et_address_line_one_aaw);
        this.f12575r = (StripeEditText) findViewById(C2364R.C2366id.et_address_line_two_aaw);
        this.f12576s = (StripeEditText) findViewById(C2364R.C2366id.et_city_aaw);
        this.f12577t = (StripeEditText) findViewById(C2364R.C2366id.et_name_aaw);
        this.f12578u = (StripeEditText) findViewById(C2364R.C2366id.et_postal_code_aaw);
        this.f12579v = (StripeEditText) findViewById(C2364R.C2366id.et_state_aaw);
        this.f12580w = (StripeEditText) findViewById(C2364R.C2366id.et_phone_number_aaw);
        this.f12573p = (TextInputLayout) findViewById(C2364R.C2366id.tl_phone_number_aaw);
        this.f12566i.setCountryChangeListener(new CountryAutoCompleteTextView.AbstractC2453a() { // from class: com.stripe.android.view.ShippingInfoWidget.1
            @Override // com.stripe.android.view.CountryAutoCompleteTextView.AbstractC2453a
            /* renamed from: a */
            public void mo17283a(String str) {
                ShippingInfoWidget shippingInfoWidget = ShippingInfoWidget.this;
                shippingInfoWidget.m17292a(shippingInfoWidget.f12566i.getSelectedCountryCode());
            }
        });
        this.f12580w.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
        m17290c();
        m17289d();
        m17292a(this.f12566i.getSelectedCountryCode());
    }

    /* renamed from: c */
    private void m17290c() {
        this.f12574q.setErrorMessageListener(new ErrorListener(this.f12567j));
        this.f12576s.setErrorMessageListener(new ErrorListener(this.f12569l));
        this.f12577t.setErrorMessageListener(new ErrorListener(this.f12570m));
        this.f12578u.setErrorMessageListener(new ErrorListener(this.f12571n));
        this.f12579v.setErrorMessageListener(new ErrorListener(this.f12572o));
        this.f12580w.setErrorMessageListener(new ErrorListener(this.f12573p));
        this.f12574q.setErrorMessage(getResources().getString(C2364R.string.address_required));
        this.f12576s.setErrorMessage(getResources().getString(C2364R.string.address_city_required));
        this.f12577t.setErrorMessage(getResources().getString(C2364R.string.address_name_required));
        this.f12580w.setErrorMessage(getResources().getString(C2364R.string.address_phone_number_required));
    }

    /* renamed from: d */
    private void m17289d() {
        this.f12570m.setHint(getResources().getString(C2364R.string.address_label_name));
        if (this.f12564g.contains(f12560c)) {
            this.f12569l.setHint(getResources().getString(C2364R.string.address_label_city_optional));
        } else {
            this.f12569l.setHint(getResources().getString(C2364R.string.address_label_city));
        }
        if (this.f12564g.contains(f12563f)) {
            this.f12573p.setHint(getResources().getString(C2364R.string.address_label_phone_number_optional));
        } else {
            this.f12573p.setHint(getResources().getString(C2364R.string.address_label_phone_number));
        }
        m17288e();
    }

    /* renamed from: e */
    private void m17288e() {
        if (this.f12565h.contains(f12558a)) {
            this.f12567j.setVisibility(8);
        }
        if (this.f12565h.contains(f12559b)) {
            this.f12568k.setVisibility(8);
        }
        if (this.f12565h.contains(f12562e)) {
            this.f12572o.setVisibility(8);
        }
        if (this.f12565h.contains(f12560c)) {
            this.f12569l.setVisibility(8);
        }
        if (this.f12565h.contains(f12561d)) {
            this.f12571n.setVisibility(8);
        }
        if (this.f12565h.contains(f12563f)) {
            this.f12573p.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m17292a(String str) {
        if (str.equals(Locale.US.getCountry())) {
            m17287f();
        } else if (str.equals(Locale.UK.getCountry())) {
            m17286g();
        } else if (str.equals(Locale.CANADA.getCountry())) {
            m17285h();
        } else {
            m17284i();
        }
        if (!CountryUtils.m17263a(str) || this.f12565h.contains(f12561d)) {
            this.f12571n.setVisibility(8);
        } else {
            this.f12571n.setVisibility(0);
        }
    }

    /* renamed from: f */
    private void m17287f() {
        if (this.f12564g.contains(f12558a)) {
            this.f12567j.setHint(getResources().getString(C2364R.string.address_label_address_optional));
        } else {
            this.f12567j.setHint(getResources().getString(C2364R.string.address_label_address));
        }
        this.f12568k.setHint(getResources().getString(C2364R.string.address_label_apt_optional));
        if (this.f12564g.contains(f12561d)) {
            this.f12571n.setHint(getResources().getString(C2364R.string.address_label_zip_code_optional));
        } else {
            this.f12571n.setHint(getResources().getString(C2364R.string.address_label_zip_code));
        }
        if (this.f12564g.contains(f12562e)) {
            this.f12572o.setHint(getResources().getString(C2364R.string.address_label_state_optional));
        } else {
            this.f12572o.setHint(getResources().getString(C2364R.string.address_label_state));
        }
        this.f12578u.setErrorMessage(getResources().getString(C2364R.string.address_zip_invalid));
        this.f12579v.setErrorMessage(getResources().getString(C2364R.string.address_state_required));
    }

    /* renamed from: g */
    private void m17286g() {
        if (this.f12564g.contains(f12558a)) {
            this.f12567j.setHint(getResources().getString(C2364R.string.address_label_address_line1_optional));
        } else {
            this.f12567j.setHint(getResources().getString(C2364R.string.address_label_address_line1));
        }
        this.f12568k.setHint(getResources().getString(C2364R.string.address_label_address_line2_optional));
        if (this.f12564g.contains(f12561d)) {
            this.f12571n.setHint(getResources().getString(C2364R.string.address_label_postcode_optional));
        } else {
            this.f12571n.setHint(getResources().getString(C2364R.string.address_label_postcode));
        }
        if (this.f12564g.contains(f12562e)) {
            this.f12572o.setHint(getResources().getString(C2364R.string.address_label_county_optional));
        } else {
            this.f12572o.setHint(getResources().getString(C2364R.string.address_label_county));
        }
        this.f12578u.setErrorMessage(getResources().getString(C2364R.string.address_postcode_invalid));
        this.f12579v.setErrorMessage(getResources().getString(C2364R.string.address_county_required));
    }

    /* renamed from: h */
    private void m17285h() {
        if (this.f12564g.contains(f12558a)) {
            this.f12567j.setHint(getResources().getString(C2364R.string.address_label_address_optional));
        } else {
            this.f12567j.setHint(getResources().getString(C2364R.string.address_label_address));
        }
        this.f12568k.setHint(getResources().getString(C2364R.string.address_label_apt_optional));
        if (this.f12564g.contains(f12561d)) {
            this.f12571n.setHint(getResources().getString(C2364R.string.address_label_postal_code_optional));
        } else {
            this.f12571n.setHint(getResources().getString(C2364R.string.address_label_postal_code));
        }
        if (this.f12564g.contains(f12562e)) {
            this.f12572o.setHint(getResources().getString(C2364R.string.address_label_province_optional));
        } else {
            this.f12572o.setHint(getResources().getString(C2364R.string.address_label_province));
        }
        this.f12578u.setErrorMessage(getResources().getString(C2364R.string.address_postal_code_invalid));
        this.f12579v.setErrorMessage(getResources().getString(C2364R.string.address_province_required));
    }

    /* renamed from: i */
    private void m17284i() {
        if (this.f12564g.contains(f12558a)) {
            this.f12567j.setHint(getResources().getString(C2364R.string.address_label_address_line1_optional));
        } else {
            this.f12567j.setHint(getResources().getString(C2364R.string.address_label_address_line1));
        }
        this.f12568k.setHint(getResources().getString(C2364R.string.address_label_address_line2_optional));
        if (this.f12564g.contains(f12561d)) {
            this.f12571n.setHint(getResources().getString(C2364R.string.address_label_zip_postal_code_optional));
        } else {
            this.f12571n.setHint(getResources().getString(C2364R.string.address_label_zip_postal_code));
        }
        if (this.f12564g.contains(f12562e)) {
            this.f12572o.setHint(getResources().getString(C2364R.string.address_label_region_generic_optional));
        } else {
            this.f12572o.setHint(getResources().getString(C2364R.string.address_label_region_generic));
        }
        this.f12578u.setErrorMessage(getResources().getString(C2364R.string.address_zip_postal_invalid));
        this.f12579v.setErrorMessage(getResources().getString(C2364R.string.address_region_generic_required));
    }
}
