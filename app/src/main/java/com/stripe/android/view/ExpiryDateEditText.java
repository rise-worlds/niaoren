package com.stripe.android.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.Size;
import android.support.annotation.VisibleForTesting;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import com.cyjh.ddy.media.p035a.ResultTypeConstant;

/* loaded from: classes2.dex */
public class ExpiryDateEditText extends StripeEditText {

    /* renamed from: a */
    static final int f12490a = -1;

    /* renamed from: b */
    private static final int f12491b = 5;

    /* renamed from: c */
    private AbstractC2455a f12492c;

    /* renamed from: d */
    private boolean f12493d;

    /* renamed from: com.stripe.android.view.ExpiryDateEditText$a */
    /* loaded from: classes2.dex */
    interface AbstractC2455a {
        /* renamed from: a */
        void mo17359a();
    }

    @VisibleForTesting
    /* renamed from: a */
    int m17366a(int i, int i2, int i3) {
        boolean z = true;
        int i4 = (i2 > 2 || i2 + i3 < 2) ? 0 : 1;
        if (!(i3 == 0 && i2 == 3)) {
            z = false;
        }
        int i5 = i2 + i3 + i4;
        if (z && i5 > 0) {
            i5--;
        }
        return i5 <= i ? i5 : i;
    }

    public ExpiryDateEditText(Context context) {
        super(context);
        m17361b();
    }

    public ExpiryDateEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m17361b();
    }

    public ExpiryDateEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m17361b();
    }

    /* renamed from: a */
    public boolean m17367a() {
        return this.f12493d;
    }

    @Size(2)
    @Nullable
    public int[] getValidDateFields() {
        if (!this.f12493d) {
            return null;
        }
        int[] iArr = new int[2];
        String[] b = DateUtils.m17253b(getText().toString().replaceAll("/", ""));
        try {
            iArr[0] = Integer.parseInt(b[0]);
            iArr[1] = DateUtils.m17259a(Integer.parseInt(b[1]));
            return iArr;
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    public void setExpiryDateEditListener(AbstractC2455a aVar) {
        this.f12492c = aVar;
    }

    /* renamed from: b */
    private void m17361b() {
        addTextChangedListener(new TextWatcher() { // from class: com.stripe.android.view.ExpiryDateEditText.1

            /* renamed from: b */
            int f12495b;

            /* renamed from: c */
            int f12496c;

            /* renamed from: a */
            boolean f12494a = false;

            /* renamed from: d */
            String[] f12497d = new String[2];

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (!this.f12494a) {
                    this.f12495b = i;
                    this.f12496c = i3;
                }
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (!this.f12494a) {
                    String replaceAll = charSequence.toString().replaceAll("/", "");
                    if (replaceAll.length() == 1 && this.f12495b == 0 && this.f12496c == 1) {
                        char charAt = replaceAll.charAt(0);
                        if (!(charAt == '0' || charAt == '1')) {
                            replaceAll = ResultTypeConstant.f7213z + replaceAll;
                            this.f12496c++;
                        }
                    } else if (replaceAll.length() == 2 && this.f12495b == 2 && this.f12496c == 0) {
                        replaceAll = replaceAll.substring(0, 1);
                    }
                    this.f12497d = DateUtils.m17253b(replaceAll);
                    boolean z = !DateUtils.m17255a(this.f12497d[0]);
                    StringBuilder sb = new StringBuilder();
                    sb.append(this.f12497d[0]);
                    if ((this.f12497d[0].length() == 2 && this.f12496c > 0 && !z) || replaceAll.length() > 2) {
                        sb.append("/");
                    }
                    sb.append(this.f12497d[1]);
                    String sb2 = sb.toString();
                    int a = ExpiryDateEditText.this.m17366a(sb2.length(), this.f12495b, this.f12496c);
                    this.f12494a = true;
                    ExpiryDateEditText.this.setText(sb2);
                    ExpiryDateEditText.this.setSelection(a);
                    this.f12494a = false;
                }
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                boolean z = this.f12497d[0].length() == 2 && !DateUtils.m17255a(this.f12497d[0]);
                if (this.f12497d[0].length() == 2 && this.f12497d[1].length() == 2) {
                    boolean z2 = ExpiryDateEditText.this.f12493d;
                    ExpiryDateEditText.this.m17362a(this.f12497d);
                    z = !ExpiryDateEditText.this.f12493d;
                    if (!z2 && ExpiryDateEditText.this.f12493d && ExpiryDateEditText.this.f12492c != null) {
                        ExpiryDateEditText.this.f12492c.mo17359a();
                    }
                } else {
                    ExpiryDateEditText.this.f12493d = false;
                }
                ExpiryDateEditText.this.setShouldShowError(z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m17362a(@Size(2) @NonNull String[] strArr) {
        int i;
        int i2 = -1;
        if (strArr[0].length() != 2) {
            i = -1;
        } else {
            try {
                i = Integer.parseInt(strArr[0]);
            } catch (NumberFormatException unused) {
                i = -1;
            }
        }
        if (strArr[1].length() == 2) {
            try {
                i2 = DateUtils.m17259a(Integer.parseInt(strArr[1]));
            } catch (NumberFormatException unused2) {
            }
        }
        this.f12493d = DateUtils.m17258a(i, i2);
    }
}
