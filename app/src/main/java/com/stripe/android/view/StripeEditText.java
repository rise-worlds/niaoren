package com.stripe.android.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.TextInputEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import com.stripe.android.C2364R;

/* loaded from: classes2.dex */
public class StripeEditText extends TextInputEditText {
    @Nullable

    /* renamed from: a */
    private AbstractC2472a f12582a;
    @Nullable

    /* renamed from: b */
    private AbstractC2473b f12583b;
    @Nullable

    /* renamed from: c */
    private ColorStateList f12584c;

    /* renamed from: d */
    private boolean f12585d;
    @ColorRes

    /* renamed from: e */
    private int f12586e;
    @ColorInt

    /* renamed from: f */
    private int f12587f;

    /* renamed from: g */
    private Handler f12588g;

    /* renamed from: h */
    private String f12589h;

    /* renamed from: i */
    private AbstractC2474c f12590i;

    /* renamed from: com.stripe.android.view.StripeEditText$a */
    /* loaded from: classes2.dex */
    interface AbstractC2472a {
        /* renamed from: a */
        void mo17275a(String str);
    }

    /* renamed from: com.stripe.android.view.StripeEditText$b */
    /* loaded from: classes2.dex */
    interface AbstractC2473b {
        /* renamed from: a */
        void mo17274a();
    }

    /* renamed from: com.stripe.android.view.StripeEditText$c */
    /* loaded from: classes2.dex */
    interface AbstractC2474c {
        /* renamed from: a */
        void mo17252a(@Nullable String str);
    }

    public StripeEditText(Context context) {
        super(context);
        m17282a();
    }

    public StripeEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m17282a();
    }

    public StripeEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m17282a();
    }

    @Nullable
    public ColorStateList getCachedColorStateList() {
        return this.f12584c;
    }

    public boolean getShouldShowError() {
        return this.f12585d;
    }

    @ColorInt
    public int getDefaultErrorColorInt() {
        m17279b();
        if (Build.VERSION.SDK_INT >= 23) {
            return getResources().getColor(this.f12586e, null);
        }
        return getResources().getColor(this.f12586e);
    }

    @Override // android.support.design.widget.TextInputEditText, android.support.v7.widget.AppCompatEditText, android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        if (super.onCreateInputConnection(editorInfo) == null) {
            return null;
        }
        return new C2475d(super.onCreateInputConnection(editorInfo), true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setAfterTextChangedListener(@Nullable AbstractC2472a aVar) {
        this.f12582a = aVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setDeleteEmptyListener(@Nullable AbstractC2473b bVar) {
        this.f12583b = bVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setErrorMessageListener(@Nullable AbstractC2474c cVar) {
        this.f12590i = cVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setErrorMessage(@Nullable String str) {
        this.f12589h = str;
    }

    public void setErrorColor(@ColorInt int i) {
        this.f12587f = i;
    }

    /* renamed from: a */
    public void m17281a(@StringRes final int i, long j) {
        this.f12588g.postDelayed(new Runnable() { // from class: com.stripe.android.view.StripeEditText.1
            @Override // java.lang.Runnable
            public void run() {
                StripeEditText.this.setHint(i);
            }
        }, j);
    }

    public void setShouldShowError(boolean z) {
        String str = this.f12589h;
        if (str == null || this.f12590i == null) {
            this.f12585d = z;
            if (this.f12585d) {
                setTextColor(this.f12587f);
            } else {
                setTextColor(this.f12584c);
            }
            refreshDrawableState();
            return;
        }
        if (!z) {
            str = null;
        }
        this.f12590i.mo17252a(str);
        this.f12585d = z;
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.f12588g.removeCallbacksAndMessages(null);
    }

    /* renamed from: a */
    private void m17282a() {
        this.f12588g = new Handler();
        m17277c();
        m17276d();
        m17279b();
        this.f12584c = getTextColors();
    }

    /* renamed from: b */
    private void m17279b() {
        this.f12584c = getTextColors();
        if (C2486n.m17210b(this.f12584c.getDefaultColor())) {
            this.f12586e = C2364R.color.error_text_light_theme;
        } else {
            this.f12586e = C2364R.color.error_text_dark_theme;
        }
    }

    /* renamed from: c */
    private void m17277c() {
        addTextChangedListener(new TextWatcher() { // from class: com.stripe.android.view.StripeEditText.2
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                if (StripeEditText.this.f12582a != null) {
                    StripeEditText.this.f12582a.mo17275a(editable.toString());
                }
            }
        });
    }

    /* renamed from: d */
    private void m17276d() {
        setOnKeyListener(new View.OnKeyListener() { // from class: com.stripe.android.view.StripeEditText.3
            @Override // android.view.View.OnKeyListener
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (i != 67 || keyEvent.getAction() != 0 || StripeEditText.this.f12583b == null || StripeEditText.this.length() != 0) {
                    return false;
                }
                StripeEditText.this.f12583b.mo17274a();
                return false;
            }
        });
    }

    /* renamed from: com.stripe.android.view.StripeEditText$d */
    /* loaded from: classes2.dex */
    private class C2475d extends InputConnectionWrapper {
        C2475d(InputConnection inputConnection, boolean z) {
            super(inputConnection, z);
        }

        @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
        public boolean deleteSurroundingText(int i, int i2) {
            if (getTextBeforeCursor(1, 0).length() == 0 && StripeEditText.this.f12583b != null) {
                StripeEditText.this.f12583b.mo17274a();
            }
            return super.deleteSurroundingText(i, i2);
        }
    }
}
