package com.stripe.android.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.annotation.VisibleForTesting;
import android.support.design.widget.TextInputLayout;
import android.support.p003v4.graphics.drawable.DrawableCompat;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import com.stripe.android.C2364R;
import com.stripe.android.CardUtils;
import com.stripe.android.model.Card;
import com.stripe.android.view.CardInputListener;
import com.stripe.android.view.CardNumberEditText;
import com.stripe.android.view.ExpiryDateEditText;
import com.stripe.android.view.StripeEditText;
import java.math.BigDecimal;
import java.math.RoundingMode;

/* loaded from: classes2.dex */
public class CardMultilineWidget extends LinearLayout {

    /* renamed from: a */
    static final String f12443a = "CardMultilineView";

    /* renamed from: b */
    static final long f12444b = 120;

    /* renamed from: c */
    static final long f12445c = 90;
    @Nullable

    /* renamed from: d */
    private CardInputListener f12446d;

    /* renamed from: e */
    private CardNumberEditText f12447e;

    /* renamed from: f */
    private ExpiryDateEditText f12448f;

    /* renamed from: g */
    private StripeEditText f12449g;

    /* renamed from: h */
    private StripeEditText f12450h;

    /* renamed from: i */
    private TextInputLayout f12451i;

    /* renamed from: j */
    private TextInputLayout f12452j;

    /* renamed from: k */
    private TextInputLayout f12453k;

    /* renamed from: l */
    private TextInputLayout f12454l;

    /* renamed from: m */
    private boolean f12455m;

    /* renamed from: n */
    private boolean f12456n;

    /* renamed from: o */
    private boolean f12457o;

    /* renamed from: p */
    private String f12458p;
    @ColorInt

    /* renamed from: q */
    private int f12459q;

    public CardMultilineWidget(Context context) {
        super(context);
        m17402b((AttributeSet) null);
    }

    public CardMultilineWidget(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m17402b(attributeSet);
    }

    public CardMultilineWidget(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m17402b(attributeSet);
    }

    @VisibleForTesting
    CardMultilineWidget(Context context, boolean z) {
        super(context);
        this.f12456n = z;
        m17402b((AttributeSet) null);
    }

    /* renamed from: a */
    public void m17411a() {
        this.f12447e.setText("");
        this.f12448f.setText("");
        this.f12449g.setText("");
        this.f12450h.setText("");
        this.f12447e.setShouldShowError(false);
        this.f12448f.setShouldShowError(false);
        this.f12449g.setShouldShowError(false);
        this.f12450h.setShouldShowError(false);
        m17405a(Card.f12006h);
    }

    public void setCardInputListener(@Nullable CardInputListener bVar) {
        this.f12446d = bVar;
    }

    @Nullable
    public Card getCard() {
        if (!m17403b()) {
            return null;
        }
        String cardNumber = this.f12447e.getCardNumber();
        int[] validDateFields = this.f12448f.getValidDateFields();
        Card cVar = new Card(cardNumber, Integer.valueOf(validDateFields[0]), Integer.valueOf(validDateFields[1]), this.f12449g.getText().toString());
        if (this.f12456n) {
            cVar.m17879k(this.f12450h.getText().toString());
        }
        return cVar.m17893d(f12443a);
    }

    /* renamed from: b */
    public boolean m17403b() {
        boolean z;
        boolean b = CardUtils.m18075b(this.f12447e.getCardNumber());
        boolean z2 = this.f12448f.getValidDateFields() != null && this.f12448f.m17367a();
        boolean d = m17397d();
        this.f12447e.setShouldShowError(!b);
        this.f12448f.setShouldShowError(!z2);
        this.f12449g.setShouldShowError(!d);
        if (this.f12456n) {
            z = m17404a(true, this.f12450h.getText().toString());
            this.f12450h.setShouldShowError(!z);
        } else {
            z = true;
        }
        return b && z2 && d && z;
    }

    @Override // android.view.View
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            m17405a(this.f12458p);
        }
    }

    public void setShouldShowPostalCode(boolean z) {
        this.f12456n = z;
        m17399c();
    }

    public void setCardNumberTextWatcher(TextWatcher textWatcher) {
        this.f12447e.addTextChangedListener(textWatcher);
    }

    public void setExpiryDateTextWatcher(TextWatcher textWatcher) {
        this.f12448f.addTextChangedListener(textWatcher);
    }

    public void setCvcNumberTextWatcher(TextWatcher textWatcher) {
        this.f12449g.addTextChangedListener(textWatcher);
    }

    public void setPostalCodeTextWatcher(TextWatcher textWatcher) {
        this.f12450h.addTextChangedListener(textWatcher);
    }

    @Override // android.view.View
    public boolean isEnabled() {
        return this.f12455m;
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        this.f12452j.setEnabled(z);
        this.f12451i.setEnabled(z);
        this.f12453k.setEnabled(z);
        this.f12454l.setEnabled(z);
        this.f12455m = z;
    }

    /* renamed from: c */
    void m17399c() {
        this.f12452j.setHint(getResources().getString(this.f12456n ? C2364R.string.expiry_label_short : C2364R.string.acc_label_expiry_date));
        int i = this.f12456n ? C2364R.C2366id.et_add_source_postal_ml : -1;
        this.f12449g.setNextFocusForwardId(i);
        this.f12449g.setNextFocusDownId(i);
        this.f12454l.setVisibility(this.f12456n ? 0 : 8);
        int dimensionPixelSize = this.f12456n ? getResources().getDimensionPixelSize(C2364R.dimen.add_card_expiry_middle_margin) : 0;
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.f12453k.getLayoutParams();
        layoutParams.setMargins(0, 0, dimensionPixelSize, 0);
        if (Build.VERSION.SDK_INT >= 17) {
            layoutParams.setMarginEnd(dimensionPixelSize);
        }
        this.f12453k.setLayoutParams(layoutParams);
    }

    /* renamed from: a */
    static boolean m17404a(boolean z, @Nullable String str) {
        return z && str != null && str.length() == 5;
    }

    /* renamed from: d */
    private boolean m17397d() {
        int length = this.f12449g.getText().toString().trim().length();
        return (TextUtils.equals(Card.f11999a, this.f12458p) && length == 4) || length == 3;
    }

    /* renamed from: a */
    private void m17408a(AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(attributeSet, C2364R.styleable.CardMultilineWidget, 0, 0);
            try {
                this.f12456n = obtainStyledAttributes.getBoolean(C2364R.styleable.CardMultilineWidget_shouldShowPostalCode, false);
            } finally {
                obtainStyledAttributes.recycle();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e */
    public void m17395e() {
        if (!C2486n.m17211a(this.f12458p, this.f12449g.getText().toString())) {
            m17410a(Card.f11999a.equals(this.f12458p) ? C2364R.C2365drawable.ic_cvc_amex : C2364R.C2365drawable.ic_cvc, true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @StringRes
    public int getCvcHelperText() {
        return Card.f11999a.equals(this.f12458p) ? C2364R.string.cvc_multiline_helper_amex : C2364R.string.cvc_multiline_helper;
    }

    private int getDynamicBufferInPixels() {
        return new BigDecimal(getResources().getDimension(C2364R.dimen.card_icon_multiline_padding_bottom)).setScale(0, RoundingMode.HALF_DOWN).intValue();
    }

    /* renamed from: b */
    private void m17402b(AttributeSet attributeSet) {
        setOrientation(1);
        inflate(getContext(), C2364R.layout.card_multiline_widget, this);
        this.f12447e = (CardNumberEditText) findViewById(C2364R.C2366id.et_add_source_card_number_ml);
        this.f12448f = (ExpiryDateEditText) findViewById(C2364R.C2366id.et_add_source_expiry_ml);
        this.f12449g = (StripeEditText) findViewById(C2364R.C2366id.et_add_source_cvc_ml);
        this.f12450h = (StripeEditText) findViewById(C2364R.C2366id.et_add_source_postal_ml);
        this.f12459q = this.f12447e.getHintTextColors().getDefaultColor();
        this.f12458p = Card.f12006h;
        m17408a(attributeSet);
        this.f12451i = (TextInputLayout) findViewById(C2364R.C2366id.tl_add_source_card_number_ml);
        this.f12452j = (TextInputLayout) findViewById(C2364R.C2366id.tl_add_source_expiry_ml);
        this.f12453k = (TextInputLayout) findViewById(C2364R.C2366id.tl_add_source_cvc_ml);
        this.f12454l = (TextInputLayout) findViewById(C2364R.C2366id.tl_add_source_postal_ml);
        if (this.f12456n) {
            this.f12452j.setHint(getResources().getString(C2364R.string.expiry_label_short));
        }
        m17409a(this.f12451i, this.f12452j, this.f12453k, this.f12454l);
        m17391g();
        m17389h();
        m17393f();
        this.f12447e.setCardBrandChangeListener(new CardNumberEditText.AbstractC2449a() { // from class: com.stripe.android.view.CardMultilineWidget.1
            @Override // com.stripe.android.view.CardNumberEditText.AbstractC2449a
            /* renamed from: a */
            public void mo17372a(@NonNull String str) {
                CardMultilineWidget.this.m17405a(str);
            }
        });
        this.f12447e.setCardNumberCompleteListener(new CardNumberEditText.AbstractC2450b() { // from class: com.stripe.android.view.CardMultilineWidget.2
            @Override // com.stripe.android.view.CardNumberEditText.AbstractC2450b
            /* renamed from: a */
            public void mo17371a() {
                CardMultilineWidget.this.f12448f.requestFocus();
                if (CardMultilineWidget.this.f12446d != null) {
                    CardMultilineWidget.this.f12446d.m17273a();
                }
            }
        });
        this.f12448f.setExpiryDateEditListener(new ExpiryDateEditText.AbstractC2455a() { // from class: com.stripe.android.view.CardMultilineWidget.3
            @Override // com.stripe.android.view.ExpiryDateEditText.AbstractC2455a
            /* renamed from: a */
            public void mo17359a() {
                CardMultilineWidget.this.f12449g.requestFocus();
                if (CardMultilineWidget.this.f12446d != null) {
                    CardMultilineWidget.this.f12446d.m17271b();
                }
            }
        });
        this.f12449g.setAfterTextChangedListener(new StripeEditText.AbstractC2472a() { // from class: com.stripe.android.view.CardMultilineWidget.4
            @Override // com.stripe.android.view.StripeEditText.AbstractC2472a
            /* renamed from: a */
            public void mo17275a(String str) {
                if (C2486n.m17211a(CardMultilineWidget.this.f12458p, str)) {
                    CardMultilineWidget cardMultilineWidget = CardMultilineWidget.this;
                    cardMultilineWidget.m17405a(cardMultilineWidget.f12458p);
                    if (CardMultilineWidget.this.f12456n) {
                        CardMultilineWidget.this.f12450h.requestFocus();
                    }
                    if (CardMultilineWidget.this.f12446d != null) {
                        CardMultilineWidget.this.f12446d.m17270c();
                    }
                } else {
                    CardMultilineWidget.this.m17395e();
                }
                CardMultilineWidget.this.f12449g.setShouldShowError(false);
            }
        });
        m17399c();
        this.f12450h.setAfterTextChangedListener(new StripeEditText.AbstractC2472a() { // from class: com.stripe.android.view.CardMultilineWidget.5
            @Override // com.stripe.android.view.StripeEditText.AbstractC2472a
            /* renamed from: a */
            public void mo17275a(String str) {
                if (CardMultilineWidget.m17404a(true, str) && CardMultilineWidget.this.f12446d != null) {
                    CardMultilineWidget.this.f12446d.m17269d();
                }
                CardMultilineWidget.this.f12450h.setShouldShowError(false);
            }
        });
        this.f12447e.m17380b();
        m17405a(Card.f12006h);
        setEnabled(true);
    }

    /* renamed from: f */
    private void m17393f() {
        this.f12448f.setDeleteEmptyListener(new BackUpFieldDeleteListener(this.f12447e));
        this.f12449g.setDeleteEmptyListener(new BackUpFieldDeleteListener(this.f12448f));
        StripeEditText stripeEditText = this.f12450h;
        if (stripeEditText != null) {
            stripeEditText.setDeleteEmptyListener(new BackUpFieldDeleteListener(this.f12449g));
        }
    }

    /* renamed from: g */
    private void m17391g() {
        this.f12447e.setErrorMessage(getContext().getString(C2364R.string.invalid_card_number));
        this.f12448f.setErrorMessage(getContext().getString(C2364R.string.invalid_expiry_year));
        this.f12449g.setErrorMessage(getContext().getString(C2364R.string.invalid_cvc));
        this.f12450h.setErrorMessage(getContext().getString(C2364R.string.invalid_zip));
    }

    /* renamed from: h */
    private void m17389h() {
        this.f12447e.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.stripe.android.view.CardMultilineWidget.6
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    CardMultilineWidget.this.f12447e.m17281a(C2364R.string.card_number_hint, CardMultilineWidget.f12444b);
                    if (CardMultilineWidget.this.f12446d != null) {
                        CardMultilineWidget.this.f12446d.m17272a(CardInputListener.AbstractC2476a.f12597a);
                        return;
                    }
                    return;
                }
                CardMultilineWidget.this.f12447e.setHint("");
            }
        });
        this.f12448f.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.stripe.android.view.CardMultilineWidget.7
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    CardMultilineWidget.this.f12448f.m17281a(C2364R.string.expiry_date_hint, CardMultilineWidget.f12445c);
                    if (CardMultilineWidget.this.f12446d != null) {
                        CardMultilineWidget.this.f12446d.m17272a(CardInputListener.AbstractC2476a.f12598b);
                        return;
                    }
                    return;
                }
                CardMultilineWidget.this.f12448f.setHint("");
            }
        });
        this.f12449g.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.stripe.android.view.CardMultilineWidget.8
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    CardMultilineWidget.this.m17395e();
                    CardMultilineWidget.this.f12449g.m17281a(CardMultilineWidget.this.getCvcHelperText(), CardMultilineWidget.f12445c);
                    if (CardMultilineWidget.this.f12446d != null) {
                        CardMultilineWidget.this.f12446d.m17272a(CardInputListener.AbstractC2476a.f12599c);
                        return;
                    }
                    return;
                }
                CardMultilineWidget cardMultilineWidget = CardMultilineWidget.this;
                cardMultilineWidget.m17405a(cardMultilineWidget.f12458p);
                CardMultilineWidget.this.f12449g.setHint("");
            }
        });
        StripeEditText stripeEditText = this.f12450h;
        if (stripeEditText != null) {
            stripeEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.stripe.android.view.CardMultilineWidget.9
                @Override // android.view.View.OnFocusChangeListener
                public void onFocusChange(View view, boolean z) {
                    if (CardMultilineWidget.this.f12456n) {
                        if (z) {
                            CardMultilineWidget.this.f12450h.m17281a(C2364R.string.zip_helper, CardMultilineWidget.f12445c);
                            if (CardMultilineWidget.this.f12446d != null) {
                                CardMultilineWidget.this.f12446d.m17272a(CardInputListener.AbstractC2476a.f12600d);
                                return;
                            }
                            return;
                        }
                        CardMultilineWidget.this.f12450h.setHint("");
                    }
                }
            });
        }
    }

    /* renamed from: a */
    private void m17409a(TextInputLayout textInputLayout, TextInputLayout textInputLayout2, TextInputLayout textInputLayout3, TextInputLayout textInputLayout4) {
        this.f12447e.setErrorMessageListener(new ErrorListener(textInputLayout));
        this.f12448f.setErrorMessageListener(new ErrorListener(textInputLayout2));
        this.f12449g.setErrorMessageListener(new ErrorListener(textInputLayout3));
        StripeEditText stripeEditText = this.f12450h;
        if (stripeEditText != null) {
            stripeEditText.setErrorMessageListener(new ErrorListener(textInputLayout4));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m17405a(@NonNull String str) {
        this.f12458p = str;
        m17400b(this.f12458p);
        m17410a(Card.f12013o.get(str).intValue(), Card.f12006h.equals(str));
    }

    /* renamed from: b */
    private void m17400b(@NonNull String str) {
        if (Card.f11999a.equals(str)) {
            this.f12449g.setFilters(new InputFilter[]{new InputFilter.LengthFilter(4)});
            this.f12453k.setHint(getResources().getString(C2364R.string.cvc_amex_hint));
            return;
        }
        this.f12449g.setFilters(new InputFilter[]{new InputFilter.LengthFilter(3)});
        this.f12453k.setHint(getResources().getString(C2364R.string.cvc_number_hint));
    }

    /* renamed from: a */
    private void m17410a(@DrawableRes int i, boolean z) {
        Drawable drawable;
        if (Build.VERSION.SDK_INT >= 21) {
            drawable = getResources().getDrawable(i, null);
        } else {
            drawable = getResources().getDrawable(i);
        }
        Drawable drawable2 = this.f12447e.getCompoundDrawables()[0];
        if (drawable2 != null) {
            Rect rect = new Rect();
            drawable2.copyBounds(rect);
            int compoundDrawablePadding = this.f12447e.getCompoundDrawablePadding();
            if (!this.f12457o) {
                rect.top -= getDynamicBufferInPixels();
                rect.bottom -= getDynamicBufferInPixels();
                this.f12457o = true;
            }
            drawable.setBounds(rect);
            Drawable wrap = DrawableCompat.wrap(drawable);
            if (z) {
                DrawableCompat.setTint(wrap.mutate(), this.f12459q);
            }
            this.f12447e.setCompoundDrawablePadding(compoundDrawablePadding);
            this.f12447e.setCompoundDrawables(wrap, null, null, null);
        }
    }
}
