package com.stripe.android.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.AttributeSet;
import com.stripe.android.CardUtils;
import com.stripe.android.StripeTextUtils;
import com.stripe.android.model.Card;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes2.dex */
public class CardNumberEditText extends StripeEditText {

    /* renamed from: b */
    private static final int f12469b = 19;

    /* renamed from: c */
    private static final int f12470c = 17;

    /* renamed from: d */
    private static final Integer[] f12471d = {4, 9, 14};

    /* renamed from: e */
    private static final Set<Integer> f12472e = new HashSet(Arrays.asList(f12471d));

    /* renamed from: f */
    private static final Integer[] f12473f = {4, 11};

    /* renamed from: g */
    private static final Set<Integer> f12474g = new HashSet(Arrays.asList(f12473f));

    /* renamed from: h */
    private AbstractC2449a f12476h;

    /* renamed from: i */
    private AbstractC2450b f12477i;
    @VisibleForTesting

    /* renamed from: a */
    String f12475a = Card.f12006h;

    /* renamed from: j */
    private int f12478j = 19;

    /* renamed from: k */
    private boolean f12479k = false;

    /* renamed from: l */
    private boolean f12480l = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.stripe.android.view.CardNumberEditText$a */
    /* loaded from: classes2.dex */
    public interface AbstractC2449a {
        /* renamed from: a */
        void mo17372a(@NonNull String str);
    }

    /* renamed from: com.stripe.android.view.CardNumberEditText$b */
    /* loaded from: classes2.dex */
    interface AbstractC2450b {
        /* renamed from: a */
        void mo17371a();
    }

    public CardNumberEditText(Context context) {
        super(context);
        m17376c();
    }

    public CardNumberEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m17376c();
    }

    public CardNumberEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m17376c();
    }

    @NonNull
    public String getCardBrand() {
        return this.f12475a;
    }

    @Nullable
    public String getCardNumber() {
        if (this.f12480l) {
            return StripeTextUtils.m17201c(getText().toString());
        }
        return null;
    }

    public int getLengthMax() {
        return this.f12478j;
    }

    /* renamed from: a */
    public boolean m17386a() {
        return this.f12480l;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setCardNumberCompleteListener(@NonNull AbstractC2450b bVar) {
        this.f12477i = bVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setCardBrandChangeListener(@NonNull AbstractC2449a aVar) {
        this.f12476h = aVar;
        this.f12476h.mo17372a(this.f12475a);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public void m17380b() {
        setFilters(new InputFilter[]{new InputFilter.LengthFilter(this.f12478j)});
    }

    @VisibleForTesting
    /* renamed from: a */
    int m17385a(int i, int i2, int i3) {
        int i4 = 0;
        boolean z = false;
        for (Integer num : Card.f11999a.equals(this.f12475a) ? f12474g : f12472e) {
            if (i2 <= num.intValue() && i2 + i3 > num.intValue()) {
                i4++;
            }
            if (i3 == 0 && i2 == num.intValue() + 1) {
                z = true;
            }
        }
        int i5 = i2 + i3 + i4;
        if (z && i5 > 0) {
            i5--;
        }
        return i5 <= i ? i5 : i;
    }

    /* renamed from: c */
    private void m17376c() {
        addTextChangedListener(new TextWatcher() { // from class: com.stripe.android.view.CardNumberEditText.1

            /* renamed from: a */
            int f12481a;

            /* renamed from: b */
            int f12482b;

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (!CardNumberEditText.this.f12479k) {
                    this.f12481a = i;
                    this.f12482b = i3;
                }
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                String c;
                if (!CardNumberEditText.this.f12479k) {
                    if (i < 4) {
                        CardNumberEditText.this.m17377b(charSequence.toString());
                    }
                    if (i <= 16 && (c = StripeTextUtils.m17201c(charSequence.toString())) != null) {
                        String[] b = C2486n.m17208b(c, CardNumberEditText.this.f12475a);
                        StringBuilder sb = new StringBuilder();
                        for (int i4 = 0; i4 < b.length && b[i4] != null; i4++) {
                            if (i4 != 0) {
                                sb.append(' ');
                            }
                            sb.append(b[i4]);
                        }
                        String sb2 = sb.toString();
                        int a = CardNumberEditText.this.m17385a(sb2.length(), this.f12481a, this.f12482b);
                        CardNumberEditText.this.f12479k = true;
                        CardNumberEditText.this.setText(sb2);
                        CardNumberEditText.this.setSelection(a);
                        CardNumberEditText.this.f12479k = false;
                    }
                }
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                boolean z = true;
                if (editable.length() == CardNumberEditText.this.f12478j) {
                    boolean z2 = CardNumberEditText.this.f12480l;
                    CardNumberEditText.this.f12480l = CardUtils.m18075b(editable.toString());
                    CardNumberEditText cardNumberEditText = CardNumberEditText.this;
                    cardNumberEditText.setShouldShowError(!cardNumberEditText.f12480l);
                    if (!z2 && CardNumberEditText.this.f12480l && CardNumberEditText.this.f12477i != null) {
                        CardNumberEditText.this.f12477i.mo17371a();
                        return;
                    }
                    return;
                }
                CardNumberEditText cardNumberEditText2 = CardNumberEditText.this;
                if (cardNumberEditText2.getText() == null || !CardUtils.m18075b(CardNumberEditText.this.getText().toString())) {
                    z = false;
                }
                cardNumberEditText2.f12480l = z;
                CardNumberEditText.this.setShouldShowError(false);
            }
        });
    }

    /* renamed from: a */
    private void m17381a(@NonNull String str) {
        if (!this.f12475a.equals(str)) {
            this.f12475a = str;
            AbstractC2449a aVar = this.f12476h;
            if (aVar != null) {
                aVar.mo17372a(this.f12475a);
            }
            int i = this.f12478j;
            this.f12478j = m17374c(this.f12475a);
            if (i != this.f12478j) {
                m17380b();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m17377b(String str) {
        m17381a(CardUtils.m18078a(str));
    }

    /* renamed from: c */
    private static int m17374c(String str) {
        return (Card.f11999a.equals(str) || Card.f12002d.equals(str)) ? 17 : 19;
    }
}
