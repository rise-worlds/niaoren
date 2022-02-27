package com.stripe.android.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.VisibleForTesting;
import android.support.v4.graphics.ColorUtils;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.TypefaceSpan;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.stripe.android.C2364R;
import com.stripe.android.model.C2395g;
import com.stripe.android.model.Card;
import com.stripe.android.model.CustomerSource;
import com.stripe.android.model.SourceCardData;
import java.util.HashMap;
import java.util.Map;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes2.dex */
public class MaskedCardView extends LinearLayout {

    /* renamed from: f */
    static final Map<String, Integer> f12507f = new HashMap();
    @ColorInt

    /* renamed from: a */
    int f12508a;
    @ColorInt

    /* renamed from: b */
    int f12509b;
    @ColorInt

    /* renamed from: c */
    int f12510c;
    @ColorInt

    /* renamed from: d */
    int f12511d;
    @ColorInt

    /* renamed from: e */
    int f12512e;

    /* renamed from: g */
    private String f12513g;

    /* renamed from: h */
    private String f12514h;

    /* renamed from: i */
    private boolean f12515i;

    /* renamed from: j */
    private AppCompatImageView f12516j;

    /* renamed from: k */
    private AppCompatTextView f12517k;

    /* renamed from: l */
    private AppCompatImageView f12518l;

    static {
        f12507f.put(Card.f11999a, Integer.valueOf(C2364R.C2365drawable.ic_amex_template_32));
        f12507f.put(Card.f12002d, Integer.valueOf(C2364R.C2365drawable.ic_diners_template_32));
        f12507f.put(Card.f12000b, Integer.valueOf(C2364R.C2365drawable.ic_discover_template_32));
        f12507f.put(Card.f12001c, Integer.valueOf(C2364R.C2365drawable.ic_jcb_template_32));
        f12507f.put(Card.f12004f, Integer.valueOf(C2364R.C2365drawable.ic_mastercard_template_32));
        f12507f.put(Card.f12003e, Integer.valueOf(C2364R.C2365drawable.ic_visa_template_32));
        f12507f.put(Card.f12005g, Integer.valueOf(C2364R.C2365drawable.ic_unionpay_template_32));
        f12507f.put(Card.f12006h, Integer.valueOf(C2364R.C2365drawable.ic_unknown));
    }

    public MaskedCardView(Context context) {
        super(context);
        m17351b();
    }

    public MaskedCardView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m17351b();
    }

    public MaskedCardView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m17351b();
    }

    @Override // android.view.View
    public boolean isSelected() {
        return this.f12515i;
    }

    @Override // android.view.View
    public void setSelected(boolean z) {
        this.f12515i = z;
        m17347f();
        m17349d();
        m17348e();
    }

    void setCard(@NonNull Card cVar) {
        this.f12513g = cVar.m17863w();
        this.f12514h = cVar.m17865u();
        m17349d();
        m17348e();
    }

    void setSourceCardData(@NonNull SourceCardData hVar) {
        this.f12513g = hVar.m17745e();
        this.f12514h = hVar.m17731l();
        m17349d();
        m17348e();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setCustomerSource(@NonNull CustomerSource eVar) {
        C2395g d = eVar.m17803d();
        if (d == null || d.m17758p() == null || !"card".equals(d.m17757q()) || !(d.m17758p() instanceof SourceCardData)) {
            Card f = eVar.m17801f();
            if (f != null) {
                setCard(f);
                return;
            }
            return;
        }
        setSourceCardData((SourceCardData) d.m17758p());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m17353a() {
        setSelected(!this.f12515i);
    }

    @VisibleForTesting
    int[] getTextColorValues() {
        return new int[]{this.f12509b, this.f12508a, this.f12512e, this.f12511d};
    }

    @VisibleForTesting
    String getCardBrand() {
        return this.f12513g;
    }

    @VisibleForTesting
    String getLast4() {
        return this.f12514h;
    }

    /* renamed from: b */
    void m17351b() {
        inflate(getContext(), C2364R.layout.masked_card_view, this);
        setOrientation(0);
        setMinimumWidth(getResources().getDimensionPixelSize(C2364R.dimen.card_widget_min_width));
        int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(C2364R.dimen.masked_card_vertical_padding);
        setPadding(0, dimensionPixelSize, 0, dimensionPixelSize);
        this.f12516j = (AppCompatImageView) findViewById(C2364R.C2366id.masked_icon_view);
        this.f12517k = (AppCompatTextView) findViewById(C2364R.C2366id.masked_card_info_view);
        this.f12518l = (AppCompatImageView) findViewById(C2364R.C2366id.masked_check_icon);
        this.f12509b = C2486n.m17215a(getContext()).data;
        this.f12510c = C2486n.m17209b(getContext()).data;
        this.f12512e = C2486n.m17207c(getContext()).data;
        m17346g();
        m17345h();
        m17350c();
        m17347f();
    }

    /* renamed from: c */
    private void m17350c() {
        m17352a(C2364R.C2365drawable.ic_checkmark, this.f12518l, true);
    }

    /* renamed from: d */
    private void m17349d() {
        m17352a(f12507f.get(this.f12513g).intValue(), this.f12516j, false);
    }

    /* renamed from: a */
    private void m17352a(@DrawableRes int i, @NonNull ImageView imageView, boolean z) {
        Drawable drawable;
        if (Build.VERSION.SDK_INT >= 21) {
            drawable = getResources().getDrawable(i, imageView.getContext().getTheme());
        } else {
            drawable = getResources().getDrawable(i);
        }
        int i2 = (this.f12515i || z) ? this.f12509b : this.f12510c;
        Drawable wrap = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(wrap.mutate(), i2);
        imageView.setImageDrawable(wrap);
    }

    /* renamed from: e */
    private void m17348e() {
        String string = Card.f11999a.equals(this.f12513g) ? getResources().getString(C2364R.string.amex_short) : this.f12513g;
        String string2 = getResources().getString(C2364R.string.ending_in);
        int length = string.length();
        int length2 = string2.length();
        int length3 = this.f12514h.length();
        int i = this.f12515i ? this.f12509b : this.f12512e;
        int i2 = this.f12515i ? this.f12508a : this.f12511d;
        SpannableString spannableString = new SpannableString(string + string2 + this.f12514h);
        spannableString.setSpan(new TypefaceSpan("sans-serif-medium"), 0, length, 33);
        spannableString.setSpan(new ForegroundColorSpan(i), 0, length, 33);
        int i3 = length2 + length;
        spannableString.setSpan(new ForegroundColorSpan(i2), length, i3, 33);
        int i4 = length3 + i3;
        spannableString.setSpan(new TypefaceSpan("sans-serif-medium"), i3, i4, 33);
        spannableString.setSpan(new ForegroundColorSpan(i), i3, i4, 33);
        this.f12517k.setText(spannableString);
    }

    /* renamed from: f */
    private void m17347f() {
        if (this.f12515i) {
            this.f12518l.setVisibility(0);
        } else {
            this.f12518l.setVisibility(4);
        }
    }

    /* renamed from: g */
    private void m17346g() {
        Resources resources = getResources();
        Context context = getContext();
        if (Build.VERSION.SDK_INT >= 23) {
            this.f12509b = C2486n.m17216a(this.f12509b) ? resources.getColor(C2364R.color.accent_color_default, context.getTheme()) : this.f12509b;
            this.f12510c = C2486n.m17216a(this.f12510c) ? resources.getColor(C2364R.color.control_normal_color_default, context.getTheme()) : this.f12510c;
            this.f12512e = C2486n.m17216a(this.f12512e) ? resources.getColor(C2364R.color.color_text_secondary_default, context.getTheme()) : this.f12512e;
            return;
        }
        this.f12509b = C2486n.m17216a(this.f12509b) ? resources.getColor(C2364R.color.accent_color_default) : this.f12509b;
        this.f12510c = C2486n.m17216a(this.f12510c) ? resources.getColor(C2364R.color.control_normal_color_default) : this.f12510c;
        this.f12512e = C2486n.m17216a(this.f12512e) ? resources.getColor(C2364R.color.color_text_secondary_default) : this.f12512e;
    }

    /* renamed from: h */
    private void m17345h() {
        this.f12508a = ColorUtils.setAlphaComponent(this.f12509b, getResources().getInteger(C2364R.integer.light_text_alpha_hex));
        this.f12511d = ColorUtils.setAlphaComponent(this.f12512e, getResources().getInteger(C2364R.integer.light_text_alpha_hex));
    }
}
