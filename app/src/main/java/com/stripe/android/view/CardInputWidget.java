package com.stripe.android.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.ColorInt;
import android.support.annotation.IdRes;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.text.InputFilter;
import android.text.Layout;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.Transformation;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.stripe.android.C2364R;
import com.stripe.android.model.Card;

import java.util.Locale;

/* loaded from: classes2.dex */
public class CardInputWidget extends LinearLayout {

    /* renamed from: a */
    static final String f12378a = "CardInputView";

    /* renamed from: b */
    private static final String f12379b = "4242";

    /* renamed from: c */
    private static final String f12380c = "88";

    /* renamed from: d */
    private static final String f12381d = "34343";

    /* renamed from: e */
    private static final String f12382e = "CVC";

    /* renamed from: f */
    private static final String f12383f = "2345";

    /* renamed from: g */
    private static final String f12384g = "3434 343434 ";

    /* renamed from: h */
    private static final String f12385h = "4242 4242 4242 ";

    /* renamed from: i */
    private static final String f12386i = "4242 4242 4242 4242";

    /* renamed from: j */
    private static final String f12387j = "MM/MM";

    /* renamed from: k */
    private static final String f12388k = "extra_card_viewed";

    /* renamed from: l */
    private static final String f12389l = "extra_super_state";
    @IdRes

    /* renamed from: m */
    private static final int f12390m = 42424242;

    /* renamed from: n */
    private static final long f12391n = 150;

    /* renamed from: A */
    private int f12392A;

    /* renamed from: B */
    private AbstractC2437b f12393B;

    /* renamed from: C */
    private C2438c f12394C;

    /* renamed from: o */
    private ImageView f12395o;
    @Nullable

    /* renamed from: p */
    private CardInputListener f12396p;

    /* renamed from: q */
    private CardNumberEditText f12397q;

    /* renamed from: r */
    private boolean f12398r = true;

    /* renamed from: s */
    private StripeEditText f12399s;

    /* renamed from: t */
    private ExpiryDateEditText f12400t;

    /* renamed from: u */
    private FrameLayout f12401u;

    /* renamed from: v */
    private String f12402v;
    @ColorInt

    /* renamed from: w */
    private int f12403w;
    @ColorInt

    /* renamed from: x */
    private int f12404x;

    /* renamed from: y */
    private boolean f12405y;

    /* renamed from: z */
    private boolean f12406z;

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    /* renamed from: com.stripe.android.view.CardInputWidget$b */
    /* loaded from: classes2.dex */
    public interface AbstractC2437b {
        /* renamed from: a */
        int m17413a();

        /* renamed from: a */
        int m17412a(@NonNull String str, @NonNull EditText editText);
    }

    public CardInputWidget(Context context) {
        super(context);
        m17439a((AttributeSet) null);
    }

    public CardInputWidget(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m17439a(attributeSet);
    }

    public CardInputWidget(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m17439a(attributeSet);
    }

    @Nullable
    public Card getCard() {
        String cardNumber = this.f12397q.getCardNumber();
        int[] validDateFields = this.f12400t.getValidDateFields();
        if (cardNumber == null || validDateFields == null || validDateFields.length != 2) {
            return null;
        }
        String trim = this.f12399s.getText().toString().trim();
        trim.length();
        if (!m17430b()) {
            return null;
        }
        return new Card(cardNumber, Integer.valueOf(validDateFields[0]), Integer.valueOf(validDateFields[1]), trim).m17893d(f12378a);
    }

    public void setCardInputListener(@Nullable CardInputListener bVar) {
        this.f12396p = bVar;
    }

    public void setCardNumber(String str) {
        this.f12397q.setText(str);
        setCardNumberIsViewed(!this.f12397q.m17386a());
    }

    /* renamed from: a */
    public void m17441a(@IntRange(from = 1, m25695to = 12) int i, @IntRange(from = 0, m25695to = 9999) int i2) {
        this.f12400t.setText(DateUtils.m17254b(i, i2));
    }

    public void setCvcCode(String str) {
        this.f12399s.setText(str);
    }

    /* renamed from: a */
    public void m17443a() {
        if (this.f12397q.hasFocus() || this.f12400t.hasFocus() || this.f12399s.hasFocus() || hasFocus()) {
            this.f12397q.requestFocus();
        }
        this.f12399s.setText("");
        this.f12400t.setText("");
        this.f12397q.setText("");
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        this.f12397q.setEnabled(z);
        this.f12400t.setEnabled(z);
        this.f12399s.setEnabled(z);
    }

    public void setCardNumberTextWatcher(TextWatcher textWatcher) {
        this.f12397q.addTextChangedListener(textWatcher);
    }

    public void setExpiryDateTextWatcher(TextWatcher textWatcher) {
        this.f12400t.addTextChangedListener(textWatcher);
    }

    public void setCvcNumberTextWatcher(TextWatcher textWatcher) {
        this.f12399s.addTextChangedListener(textWatcher);
    }

    @Override // android.view.View
    public boolean isEnabled() {
        return this.f12397q.isEnabled() && this.f12400t.isEnabled() && this.f12399s.isEnabled();
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() != 0) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        StripeEditText a = m17442a((int) motionEvent.getX());
        if (a == null) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        a.requestFocus();
        return true;
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(f12389l, super.onSaveInstanceState());
        bundle.putBoolean(f12388k, this.f12398r);
        return bundle;
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        int i;
        int i2;
        int i3;
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            this.f12398r = bundle.getBoolean(f12388k, true);
            m17431a(this.f12398r);
            this.f12392A = getFrameWidth();
            if (this.f12398r) {
                i3 = 0;
                i2 = this.f12394C.f12432a + this.f12394C.f12435d;
                i = this.f12392A;
            } else {
                i3 = this.f12394C.f12433b * (-1);
                i2 = this.f12394C.f12434c + this.f12394C.f12435d;
                i = this.f12394C.f12436e + i2 + this.f12394C.f12437f;
            }
            m17440a(this.f12394C.f12432a, i3, this.f12397q);
            m17440a(this.f12394C.f12436e, i2, this.f12400t);
            m17440a(this.f12394C.f12438g, i, this.f12399s);
            super.onRestoreInstanceState(bundle.getParcelable(f12389l));
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    @VisibleForTesting
    @Nullable
    /* renamed from: a */
    StripeEditText m17442a(int i) {
        int left = this.f12401u.getLeft();
        if (this.f12398r) {
            if (i < left + this.f12394C.f12432a) {
                return null;
            }
            if (i < this.f12394C.f12439h) {
                return this.f12397q;
            }
            if (i < this.f12394C.f12440i) {
                return this.f12400t;
            }
            return null;
        } else if (i < left + this.f12394C.f12434c) {
            return null;
        } else {
            if (i < this.f12394C.f12439h) {
                return this.f12397q;
            }
            if (i < this.f12394C.f12440i) {
                return this.f12400t;
            }
            if (i < this.f12394C.f12440i + this.f12394C.f12436e) {
                return null;
            }
            if (i < this.f12394C.f12441j) {
                return this.f12400t;
            }
            if (i < this.f12394C.f12442k) {
                return this.f12399s;
            }
            return null;
        }
    }

    @VisibleForTesting
    void setDimensionOverrideSettings(AbstractC2437b bVar) {
        this.f12393B = bVar;
    }

    @VisibleForTesting
    void setCardNumberIsViewed(boolean z) {
        this.f12398r = z;
    }

    @VisibleForTesting
    @NonNull
    C2438c getPlacementParameters() {
        return this.f12394C;
    }

    @VisibleForTesting
    /* renamed from: a */
    void m17431a(boolean z) {
        int frameWidth = getFrameWidth();
        int left = this.f12401u.getLeft();
        if (frameWidth != 0) {
            this.f12394C.f12432a = m17433a(f12386i, this.f12397q);
            this.f12394C.f12436e = m17433a(f12387j, this.f12400t);
            String cardBrand = this.f12397q.getCardBrand();
            this.f12394C.f12433b = m17433a(m17434a(cardBrand), this.f12397q);
            this.f12394C.f12438g = m17433a(m17427b(cardBrand), this.f12399s);
            this.f12394C.f12434c = m17433a(m17422c(cardBrand), this.f12397q);
            if (z) {
                C2438c cVar = this.f12394C;
                cVar.f12435d = (frameWidth - cVar.f12432a) - this.f12394C.f12436e;
                C2438c cVar2 = this.f12394C;
                cVar2.f12439h = cVar2.f12432a + left + (this.f12394C.f12435d / 2);
                C2438c cVar3 = this.f12394C;
                cVar3.f12440i = left + cVar3.f12432a + this.f12394C.f12435d;
                return;
            }
            C2438c cVar4 = this.f12394C;
            cVar4.f12435d = ((frameWidth / 2) - cVar4.f12434c) - (this.f12394C.f12436e / 2);
            C2438c cVar5 = this.f12394C;
            cVar5.f12437f = (((frameWidth - cVar5.f12434c) - this.f12394C.f12435d) - this.f12394C.f12436e) - this.f12394C.f12438g;
            C2438c cVar6 = this.f12394C;
            cVar6.f12439h = cVar6.f12434c + left + (this.f12394C.f12435d / 2);
            C2438c cVar7 = this.f12394C;
            cVar7.f12440i = left + cVar7.f12434c + this.f12394C.f12435d;
            C2438c cVar8 = this.f12394C;
            cVar8.f12441j = cVar8.f12440i + this.f12394C.f12436e + (this.f12394C.f12437f / 2);
            C2438c cVar9 = this.f12394C;
            cVar9.f12442k = cVar9.f12440i + this.f12394C.f12436e + this.f12394C.f12437f;
        }
    }

    /* renamed from: b */
    private boolean m17430b() {
        int length = this.f12399s.getText().toString().trim().length();
        return (this.f12405y && length == 4) || length == 3;
    }

    /* renamed from: a */
    private void m17440a(int i, int i2, @NonNull StripeEditText stripeEditText) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) stripeEditText.getLayoutParams();
        layoutParams.width = i;
        layoutParams.leftMargin = i2;
        stripeEditText.setLayoutParams(layoutParams);
    }

    /* renamed from: a */
    private int m17433a(@NonNull String str, @NonNull StripeEditText stripeEditText) {
        AbstractC2437b bVar = this.f12393B;
        if (bVar == null) {
            return (int) Layout.getDesiredWidth(str, stripeEditText.getPaint());
        }
        return bVar.m17412a(str, stripeEditText);
    }

    private int getFrameWidth() {
        AbstractC2437b bVar = this.f12393B;
        if (bVar == null) {
            return this.f12401u.getWidth();
        }
        return bVar.m17413a();
    }

    /* renamed from: a */
    private void m17439a(AttributeSet attributeSet) {
        inflate(getContext(), C2364R.layout.card_input_widget, this);
        if (getId() == -1) {
            setId(f12390m);
        }
        setOrientation(0);
        setMinimumWidth(getResources().getDimensionPixelSize(C2364R.dimen.card_widget_min_width));
        this.f12394C = new C2438c();
        this.f12395o = (ImageView) findViewById(C2364R.C2366id.iv_card_icon);
        this.f12397q = (CardNumberEditText) findViewById(C2364R.C2366id.et_card_number);
        this.f12400t = (ExpiryDateEditText) findViewById(C2364R.C2366id.et_expiry_date);
        this.f12399s = (StripeEditText) findViewById(C2364R.C2366id.et_cvc_number);
        this.f12398r = true;
        this.f12401u = (FrameLayout) findViewById(C2364R.C2366id.frame_container);
        this.f12403w = this.f12397q.getDefaultErrorColorInt();
        this.f12404x = this.f12397q.getHintTextColors().getDefaultColor();
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(attributeSet, C2364R.styleable.CardInputView, 0, 0);
            try {
                this.f12403w = obtainStyledAttributes.getColor(C2364R.styleable.CardInputView_cardTextErrorColor, this.f12403w);
                this.f12404x = obtainStyledAttributes.getColor(C2364R.styleable.CardInputView_cardTint, this.f12404x);
                this.f12402v = obtainStyledAttributes.getString(C2364R.styleable.CardInputView_cardHintText);
            } finally {
                obtainStyledAttributes.recycle();
            }
        }
        String str = this.f12402v;
        if (str != null) {
            this.f12397q.setHint(str);
        }
        this.f12397q.setErrorColor(this.f12403w);
        this.f12400t.setErrorColor(this.f12403w);
        this.f12399s.setErrorColor(this.f12403w);
        this.f12397q.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.stripe.android.view.CardInputWidget.1
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    CardInputWidget.this.m17424c();
                    if (CardInputWidget.this.f12396p != null) {
                        CardInputWidget.this.f12396p.m17272a(CardInputListener.AbstractC2476a.f12597a);
                    }
                }
            }
        });
        this.f12400t.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.stripe.android.view.CardInputWidget.8
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    CardInputWidget.this.m17420d();
                    if (CardInputWidget.this.f12396p != null) {
                        CardInputWidget.this.f12396p.m17272a(CardInputListener.AbstractC2476a.f12598b);
                    }
                }
            }
        });
        this.f12400t.setDeleteEmptyListener(new BackUpFieldDeleteListener(this.f12397q));
        this.f12399s.setDeleteEmptyListener(new BackUpFieldDeleteListener(this.f12400t));
        this.f12399s.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.stripe.android.view.CardInputWidget.9
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    CardInputWidget.this.m17420d();
                    if (CardInputWidget.this.f12396p != null) {
                        CardInputWidget.this.f12396p.m17272a(CardInputListener.AbstractC2476a.f12599c);
                    }
                }
                CardInputWidget cardInputWidget = CardInputWidget.this;
                cardInputWidget.m17426b(cardInputWidget.f12397q.getCardBrand(), z, CardInputWidget.this.f12399s.getText().toString());
            }
        });
        this.f12399s.setAfterTextChangedListener(new StripeEditText.AbstractC2472a() { // from class: com.stripe.android.view.CardInputWidget.10
            @Override // com.stripe.android.view.StripeEditText.AbstractC2472a
            /* renamed from: a */
            public void mo17275a(String str2) {
                if (CardInputWidget.this.f12396p != null && C2486n.m17211a(CardInputWidget.this.f12397q.getCardBrand(), str2)) {
                    CardInputWidget.this.f12396p.m17270c();
                }
                CardInputWidget cardInputWidget = CardInputWidget.this;
                cardInputWidget.m17426b(cardInputWidget.f12397q.getCardBrand(), CardInputWidget.this.f12399s.hasFocus(), str2);
            }
        });
        this.f12397q.setCardNumberCompleteListener(new CardNumberEditText.AbstractC2450b() { // from class: com.stripe.android.view.CardInputWidget.11
            @Override // com.stripe.android.view.CardNumberEditText.AbstractC2450b
            /* renamed from: a */
            public void mo17371a() {
                CardInputWidget.this.m17420d();
                if (CardInputWidget.this.f12396p != null) {
                    CardInputWidget.this.f12396p.m17273a();
                }
            }
        });
        this.f12397q.setCardBrandChangeListener(new CardNumberEditText.AbstractC2449a() { // from class: com.stripe.android.view.CardInputWidget.12
            @Override // com.stripe.android.view.CardNumberEditText.AbstractC2449a
            /* renamed from: a */
            public void mo17372a(@NonNull String str2) {
                CardInputWidget.this.f12405y = Card.f11999a.equals(str2);
                CardInputWidget.this.m17416e(str2);
                CardInputWidget.this.m17418d(str2);
            }
        });
        this.f12400t.setExpiryDateEditListener(new ExpiryDateEditText.AbstractC2455a() { // from class: com.stripe.android.view.CardInputWidget.13
            @Override // com.stripe.android.view.ExpiryDateEditText.AbstractC2455a
            /* renamed from: a */
            public void mo17359a() {
                CardInputWidget.this.f12399s.requestFocus();
                if (CardInputWidget.this.f12396p != null) {
                    CardInputWidget.this.f12396p.m17271b();
                }
            }
        });
        this.f12397q.requestFocus();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public void m17424c() {
        if (!this.f12398r && this.f12406z) {
            final int i = this.f12394C.f12434c + this.f12394C.f12435d;
            final int i2 = this.f12394C.f12436e + i + this.f12394C.f12437f;
            m17431a(true);
            final int i3 = ((FrameLayout.LayoutParams) this.f12397q.getLayoutParams()).leftMargin;
            Animation animation = new Animation() { // from class: com.stripe.android.view.CardInputWidget.14
                @Override // android.view.animation.Animation
                protected void applyTransformation(float f, Transformation transformation) {
                    super.applyTransformation(f, transformation);
                    FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) CardInputWidget.this.f12397q.getLayoutParams();
                    layoutParams.leftMargin = (int) (i3 * (1.0f - f));
                    CardInputWidget.this.f12397q.setLayoutParams(layoutParams);
                }
            };
            final int i4 = this.f12394C.f12432a + this.f12394C.f12435d;
            Animation animation2 = new Animation() { // from class: com.stripe.android.view.CardInputWidget.15
                @Override // android.view.animation.Animation
                protected void applyTransformation(float f, Transformation transformation) {
                    super.applyTransformation(f, transformation);
                    int i5 = (int) ((i4 * f) + ((1.0f - f) * i));
                    FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) CardInputWidget.this.f12400t.getLayoutParams();
                    layoutParams.leftMargin = i5;
                    CardInputWidget.this.f12400t.setLayoutParams(layoutParams);
                }
            };
            final int i5 = (i4 - i) + i2;
            Animation animation3 = new Animation() { // from class: com.stripe.android.view.CardInputWidget.2
                @Override // android.view.animation.Animation
                protected void applyTransformation(float f, Transformation transformation) {
                    super.applyTransformation(f, transformation);
                    int i6 = (int) ((i5 * f) + ((1.0f - f) * i2));
                    FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) CardInputWidget.this.f12399s.getLayoutParams();
                    layoutParams.leftMargin = i6;
                    layoutParams.rightMargin = 0;
                    layoutParams.width = CardInputWidget.this.f12394C.f12438g;
                    CardInputWidget.this.f12399s.setLayoutParams(layoutParams);
                }
            };
            animation.setAnimationListener(new AbstractanimationAnimation$AnimationListenerC2436a() { // from class: com.stripe.android.view.CardInputWidget.3
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation4) {
                    CardInputWidget.this.f12397q.requestFocus();
                }
            });
            animation.setDuration(150L);
            animation2.setDuration(150L);
            animation3.setDuration(150L);
            AnimationSet animationSet = new AnimationSet(true);
            animationSet.addAnimation(animation);
            animationSet.addAnimation(animation2);
            animationSet.addAnimation(animation3);
            this.f12401u.startAnimation(animationSet);
            this.f12398r = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d */
    public void m17420d() {
        if (this.f12398r && this.f12406z) {
            final int i = this.f12394C.f12432a + this.f12394C.f12435d;
            m17431a(false);
            Animation animation = new Animation() { // from class: com.stripe.android.view.CardInputWidget.4
                @Override // android.view.animation.Animation
                protected void applyTransformation(float f, Transformation transformation) {
                    super.applyTransformation(f, transformation);
                    FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) CardInputWidget.this.f12397q.getLayoutParams();
                    layoutParams.leftMargin = (int) (CardInputWidget.this.f12394C.f12433b * (-1) * f);
                    CardInputWidget.this.f12397q.setLayoutParams(layoutParams);
                }
            };
            final int i2 = this.f12394C.f12434c + this.f12394C.f12435d;
            Animation animation2 = new Animation() { // from class: com.stripe.android.view.CardInputWidget.5
                @Override // android.view.animation.Animation
                protected void applyTransformation(float f, Transformation transformation) {
                    super.applyTransformation(f, transformation);
                    int i3 = (int) ((i2 * f) + ((1.0f - f) * i));
                    FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) CardInputWidget.this.f12400t.getLayoutParams();
                    layoutParams.leftMargin = i3;
                    CardInputWidget.this.f12400t.setLayoutParams(layoutParams);
                }
            };
            final int i3 = this.f12394C.f12434c + this.f12394C.f12435d + this.f12394C.f12436e + this.f12394C.f12437f;
            final int i4 = (i - i2) + i3;
            Animation animation3 = new Animation() { // from class: com.stripe.android.view.CardInputWidget.6
                @Override // android.view.animation.Animation
                protected void applyTransformation(float f, Transformation transformation) {
                    super.applyTransformation(f, transformation);
                    int i5 = (int) ((i3 * f) + ((1.0f - f) * i4));
                    FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) CardInputWidget.this.f12399s.getLayoutParams();
                    layoutParams.leftMargin = i5;
                    layoutParams.rightMargin = 0;
                    layoutParams.width = CardInputWidget.this.f12394C.f12438g;
                    CardInputWidget.this.f12399s.setLayoutParams(layoutParams);
                }
            };
            animation.setDuration(150L);
            animation2.setDuration(150L);
            animation3.setDuration(150L);
            animation.setAnimationListener(new AbstractanimationAnimation$AnimationListenerC2436a() { // from class: com.stripe.android.view.CardInputWidget.7
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation4) {
                    CardInputWidget.this.f12400t.requestFocus();
                }
            });
            AnimationSet animationSet = new AnimationSet(true);
            animationSet.addAnimation(animation);
            animationSet.addAnimation(animation2);
            animationSet.addAnimation(animation3);
            this.f12401u.startAnimation(animationSet);
            this.f12398r = false;
        }
    }

    @Override // android.view.View
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            m17425b(false);
        }
    }

    @VisibleForTesting
    /* renamed from: a */
    static boolean m17432a(@NonNull String str, boolean z, @Nullable String str2) {
        if (!z) {
            return true;
        }
        return C2486n.m17211a(str, str2);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (!this.f12406z && getWidth() != 0) {
            this.f12406z = true;
            this.f12392A = getFrameWidth();
            m17431a(this.f12398r);
            m17440a(this.f12394C.f12432a, this.f12398r ? 0 : this.f12394C.f12433b * (-1), this.f12397q);
            m17440a(this.f12394C.f12436e, (this.f12398r ? this.f12394C.f12432a : this.f12394C.f12434c) + this.f12394C.f12435d, this.f12400t);
            m17440a(this.f12394C.f12438g, this.f12398r ? this.f12392A : this.f12394C.f12434c + this.f12394C.f12435d + this.f12394C.f12436e + this.f12394C.f12437f, this.f12399s);
        }
    }

    @NonNull
    /* renamed from: a */
    private String m17434a(@NonNull String str) {
        return Card.f11999a.equals(str) ? f12384g : f12385h;
    }

    @NonNull
    /* renamed from: b */
    private String m17427b(@NonNull String str) {
        return Card.f11999a.equals(str) ? f12383f : f12382e;
    }

    @NonNull
    /* renamed from: c */
    private String m17422c(@NonNull String str) {
        return Card.f11999a.equals(str) ? f12381d : Card.f12002d.equals(str) ? f12380c : f12379b;
    }

    /* renamed from: b */
    private void m17425b(boolean z) {
        if (z || Card.f12006h.equals(this.f12397q.getCardBrand())) {
            Drawable wrap = DrawableCompat.wrap(this.f12395o.getDrawable());
            DrawableCompat.setTint(wrap.mutate(), this.f12404x);
            this.f12395o.setImageDrawable(DrawableCompat.unwrap(wrap));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d */
    public void m17418d(@NonNull String str) {
        if (Card.f11999a.equals(str)) {
            this.f12399s.setFilters(new InputFilter[]{new InputFilter.LengthFilter(4)});
            this.f12399s.setHint(C2364R.string.cvc_amex_hint);
            return;
        }
        this.f12399s.setFilters(new InputFilter[]{new InputFilter.LengthFilter(3)});
        this.f12399s.setHint(C2364R.string.cvc_number_hint);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e */
    public void m17416e(@NonNull String str) {
        if (Card.f12006h.equals(str)) {
            this.f12395o.setImageDrawable(getResources().getDrawable(C2364R.C2365drawable.ic_unknown));
            m17425b(false);
            return;
        }
        this.f12395o.setImageResource(Card.f12013o.get(str).intValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m17426b(@NonNull String str, boolean z, @Nullable String str2) {
        if (m17432a(str, z, str2)) {
            m17416e(str);
        } else {
            m17421c(Card.f11999a.equals(str));
        }
    }

    /* renamed from: c */
    private void m17421c(boolean z) {
        if (z) {
            this.f12395o.setImageResource(C2364R.C2365drawable.ic_cvc_amex);
        } else {
            this.f12395o.setImageResource(C2364R.C2365drawable.ic_cvc);
        }
        m17425b(true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.stripe.android.view.CardInputWidget$c */
    /* loaded from: classes2.dex */
    public static class C2438c {

        /* renamed from: a */
        int f12432a;

        /* renamed from: b */
        int f12433b;

        /* renamed from: c */
        int f12434c;

        /* renamed from: d */
        int f12435d;

        /* renamed from: e */
        int f12436e;

        /* renamed from: f */
        int f12437f;

        /* renamed from: g */
        int f12438g;

        /* renamed from: h */
        int f12439h;

        /* renamed from: i */
        int f12440i;

        /* renamed from: j */
        int f12441j;

        /* renamed from: k */
        int f12442k;

        C2438c() {
        }

        public String toString() {
            String format = String.format(Locale.ENGLISH, "Touch Buffer Data:\nCardTouchBufferLimit = %d\nDateStartPosition = %d\nDateRightTouchBufferLimit = %d\nCvcStartPosition = %d", Integer.valueOf(this.f12439h), Integer.valueOf(this.f12440i), Integer.valueOf(this.f12441j), Integer.valueOf(this.f12442k));
            String format2 = String.format(Locale.ENGLISH, "CardWidth = %d\nHiddenCardWidth = %d\nPeekCardWidth = %d\nCardDateSeparation = %d\nDateWidth = %d\nDateCvcSeparation = %d\nCvcWidth = %d\n", Integer.valueOf(this.f12432a), Integer.valueOf(this.f12433b), Integer.valueOf(this.f12434c), Integer.valueOf(this.f12435d), Integer.valueOf(this.f12436e), Integer.valueOf(this.f12437f), Integer.valueOf(this.f12438g));
            return format2 + format;
        }
    }

    /* renamed from: com.stripe.android.view.CardInputWidget$a  reason: invalid class name */
    /* loaded from: classes2.dex */
    private abstract class AbstractanimationAnimation$AnimationListenerC2436a implements Animation.AnimationListener {
        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }

        private AbstractanimationAnimation$AnimationListenerC2436a() {
        }
    }
}
