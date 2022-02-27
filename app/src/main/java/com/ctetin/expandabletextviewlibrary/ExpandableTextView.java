package com.ctetin.expandabletextviewlibrary;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.PatternsCompat;
import android.support.v7.widget.AppCompatTextView;
import android.text.DynamicLayout;
import android.text.Layout;
import android.text.Selection;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.method.Touch;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import p110z1.ExpandableStatusFix;
import p110z1.FormatData;
import p110z1.LinkType;
import p110z1.StatusType;
import p110z1.UUIDUtils;

/* loaded from: classes.dex */
public class ExpandableTextView extends AppCompatTextView {

    /* renamed from: a */
    public static String f6956a = "收起";

    /* renamed from: b */
    public static String f6957b = "展开";

    /* renamed from: c */
    public static final String f6958c = " ";

    /* renamed from: g */
    public static final String f6962g = "                                                                                                                                                                                                                                                                                                                           ";

    /* renamed from: h */
    public static final String f6963h = "@[\\w\\p{InCJKUnifiedIdeographs}-]{1,26}";

    /* renamed from: i */
    public static final String f6964i = "\\[([^\\[]*)\\]\\(([^\\(]*)\\)";

    /* renamed from: l */
    private static final int f6965l = 4;

    /* renamed from: A */
    private boolean f6967A;

    /* renamed from: B */
    private boolean f6968B;

    /* renamed from: C */
    private boolean f6969C;

    /* renamed from: D */
    private boolean f6970D;

    /* renamed from: E */
    private boolean f6971E;

    /* renamed from: F */
    private boolean f6972F;

    /* renamed from: G */
    private boolean f6973G;

    /* renamed from: H */
    private int f6974H;

    /* renamed from: I */
    private CharSequence f6975I;

    /* renamed from: J */
    private int f6976J;

    /* renamed from: K */
    private int f6977K;

    /* renamed from: L */
    private int f6978L;

    /* renamed from: M */
    private int f6979M;

    /* renamed from: N */
    private int f6980N;

    /* renamed from: O */
    private String f6981O;

    /* renamed from: P */
    private String f6982P;

    /* renamed from: Q */
    private String f6983Q;

    /* renamed from: R */
    private int f6984R;

    /* renamed from: S */
    private boolean f6985S;

    /* renamed from: T */
    private AbstractC1108c f6986T;

    /* renamed from: j */
    boolean f6987j;

    /* renamed from: k */
    boolean f6988k;

    /* renamed from: n */
    private TextPaint f6989n;

    /* renamed from: o */
    private Context f6990o;

    /* renamed from: p */
    private ExpandableStatusFix f6991p;

    /* renamed from: q */
    private DynamicLayout f6992q;

    /* renamed from: r */
    private int f6993r;

    /* renamed from: s */
    private int f6994s;

    /* renamed from: t */
    private int f6995t;

    /* renamed from: u */
    private Drawable f6996u;

    /* renamed from: v */
    private AbstractC1109d f6997v;

    /* renamed from: w */
    private boolean f6998w;

    /* renamed from: x */
    private AbstractC1107b f6999x;

    /* renamed from: y */
    private boolean f7000y;

    /* renamed from: z */
    private FormatData f7001z;

    /* renamed from: e */
    public static final String f6960e = "图";

    /* renamed from: d */
    public static String f6959d = "网页链接";

    /* renamed from: f */
    public static final String f6961f = f6960e + f6959d;

    /* renamed from: m */
    private static int f6966m = 0;

    /* renamed from: com.ctetin.expandabletextviewlibrary.ExpandableTextView$b */
    /* loaded from: classes.dex */
    public interface AbstractC1107b {
        /* renamed from: a */
        void mo21985a(StatusType fqVar);
    }

    /* renamed from: com.ctetin.expandabletextviewlibrary.ExpandableTextView$c */
    /* loaded from: classes.dex */
    public interface AbstractC1108c {
        /* renamed from: a */
        void m21984a(int i, boolean z);
    }

    /* renamed from: com.ctetin.expandabletextviewlibrary.ExpandableTextView$d */
    /* loaded from: classes.dex */
    public interface AbstractC1109d {
        /* renamed from: a */
        void m21983a(LinkType fpVar, String str, String str2);
    }

    /* renamed from: h */
    static /* synthetic */ int m21999h() {
        int i = f6966m;
        f6966m = i + 1;
        return i;
    }

    public ExpandableTextView(Context context) {
        this(context, null);
    }

    public ExpandableTextView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, -1);
    }

    public ExpandableTextView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f6996u = null;
        this.f6998w = true;
        this.f7000y = true;
        this.f6967A = true;
        this.f6968B = true;
        this.f6969C = true;
        this.f6970D = true;
        this.f6971E = false;
        this.f6972F = false;
        this.f6973G = true;
        this.f6988k = true;
        m22027a(context, attributeSet, i);
        setMovementMethod(C1106a.m21986a());
        addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: com.ctetin.expandabletextviewlibrary.ExpandableTextView.1
            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewDetachedFromWindow(View view) {
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewAttachedToWindow(View view) {
                if (!ExpandableTextView.this.f6985S) {
                    ExpandableTextView.this.m21997i();
                }
                ExpandableTextView.this.f6985S = true;
            }
        });
    }

    /* renamed from: a */
    private void m22027a(Context context, AttributeSet attributeSet, int i) {
        f6956a = context.getString(C1111R.string.social_contract);
        f6957b = context.getString(C1111R.string.social_expend);
        f6959d = context.getString(C1111R.string.social_text_target);
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, C1111R.styleable.ExpandableTextView, i, 0);
            this.f6993r = obtainStyledAttributes.getInt(C1111R.styleable.ExpandableTextView_ep_max_line, 4);
            this.f6967A = obtainStyledAttributes.getBoolean(C1111R.styleable.ExpandableTextView_ep_need_expand, true);
            this.f7000y = obtainStyledAttributes.getBoolean(C1111R.styleable.ExpandableTextView_ep_need_contract, false);
            this.f6973G = obtainStyledAttributes.getBoolean(C1111R.styleable.ExpandableTextView_ep_need_animation, true);
            this.f6971E = obtainStyledAttributes.getBoolean(C1111R.styleable.ExpandableTextView_ep_need_self, false);
            this.f6969C = obtainStyledAttributes.getBoolean(C1111R.styleable.ExpandableTextView_ep_need_mention, true);
            this.f6970D = obtainStyledAttributes.getBoolean(C1111R.styleable.ExpandableTextView_ep_need_link, true);
            this.f6972F = obtainStyledAttributes.getBoolean(C1111R.styleable.ExpandableTextView_ep_need_always_showright, false);
            this.f6968B = obtainStyledAttributes.getBoolean(C1111R.styleable.ExpandableTextView_ep_need_convert_url, true);
            this.f6982P = obtainStyledAttributes.getString(C1111R.styleable.ExpandableTextView_ep_contract_text);
            this.f6981O = obtainStyledAttributes.getString(C1111R.styleable.ExpandableTextView_ep_expand_text);
            if (TextUtils.isEmpty(this.f6981O)) {
                this.f6981O = f6957b;
            }
            if (TextUtils.isEmpty(this.f6982P)) {
                this.f6982P = f6956a;
            }
            this.f6976J = obtainStyledAttributes.getColor(C1111R.styleable.ExpandableTextView_ep_expand_color, Color.parseColor("#999999"));
            this.f6984R = obtainStyledAttributes.getColor(C1111R.styleable.ExpandableTextView_ep_expand_color, Color.parseColor("#999999"));
            this.f6980N = obtainStyledAttributes.getColor(C1111R.styleable.ExpandableTextView_ep_contract_color, Color.parseColor("#999999"));
            this.f6978L = obtainStyledAttributes.getColor(C1111R.styleable.ExpandableTextView_ep_link_color, Color.parseColor("#FF6200"));
            this.f6979M = obtainStyledAttributes.getColor(C1111R.styleable.ExpandableTextView_ep_self_color, Color.parseColor("#FF6200"));
            this.f6977K = obtainStyledAttributes.getColor(C1111R.styleable.ExpandableTextView_ep_mention_color, Color.parseColor("#FF6200"));
            this.f6996u = getResources().getDrawable(obtainStyledAttributes.getResourceId(C1111R.styleable.ExpandableTextView_ep_link_res, C1111R.mipmap.link));
            this.f6994s = this.f6993r;
            obtainStyledAttributes.recycle();
        } else {
            this.f6996u = context.getResources().getDrawable(C1111R.mipmap.link);
        }
        this.f6990o = context;
        this.f6989n = getPaint();
        this.f6989n.setStyle(Paint.Style.FILL_AND_STROKE);
        this.f6996u.setBounds(0, 0, 30, 30);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public SpannableStringBuilder m22019a(CharSequence charSequence) {
        this.f7001z = m22011b(charSequence);
        this.f6992q = new DynamicLayout(this.f7001z.m2830a(), this.f6989n, this.f6995t, Layout.Alignment.ALIGN_NORMAL, 1.2f, 0.0f, true);
        this.f6974H = this.f6992q.getLineCount();
        AbstractC1108c cVar = this.f6986T;
        if (cVar != null) {
            int i = this.f6974H;
            cVar.m21984a(i, i > this.f6993r);
        }
        if (!this.f6967A || this.f6974H <= this.f6993r) {
            return m22015a(this.f7001z, false);
        }
        return m22015a(this.f7001z, true);
    }

    public void setEndExpendContent(String str) {
        this.f6983Q = str;
    }

    public void setContent(String str) {
        this.f6975I = str;
        if (this.f6985S) {
            m21997i();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: i */
    public void m21997i() {
        if (this.f6975I != null) {
            this.f6994s = this.f6993r;
            if (this.f6995t <= 0 && getWidth() > 0) {
                this.f6995t = (getWidth() - getPaddingLeft()) - getPaddingRight();
            }
            if (this.f6995t <= 0) {
                if (f6966m > 10) {
                    setText(f6962g);
                }
                post(new Runnable() { // from class: com.ctetin.expandabletextviewlibrary.ExpandableTextView.2
                    @Override // java.lang.Runnable
                    public void run() {
                        ExpandableTextView.m21999h();
                        ExpandableTextView expandableTextView = ExpandableTextView.this;
                        expandableTextView.setContent(expandableTextView.f6975I.toString());
                    }
                });
                return;
            }
            m22019a(this.f6975I.toString());
        }
    }

    private String getExpandEndContent() {
        return TextUtils.isEmpty(this.f6983Q) ? String.format(Locale.getDefault(), "  %s", this.f6982P) : String.format(Locale.getDefault(), "  %s  %s", this.f6983Q, this.f6982P);
    }

    private String getHideEndContent() {
        if (TextUtils.isEmpty(this.f6983Q)) {
            return String.format(Locale.getDefault(), this.f6972F ? "  %s" : "...  %s", this.f6981O);
        }
        return String.format(Locale.getDefault(), this.f6972F ? "  %s  %s" : "...  %s  %s", this.f6983Q, this.f6981O);
    }

    /* renamed from: a */
    private SpannableStringBuilder m22015a(FormatData fsVar, boolean z) {
        boolean z2;
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        ExpandableStatusFix frVar = this.f6991p;
        if (!(frVar == null || frVar.m2832a() == null)) {
            if (this.f6991p.m2832a() != null) {
                z2 = this.f6991p.m2832a().equals(StatusType.STATUS_CONTRACT);
            } else {
                z2 = false;
            }
            if (z2) {
                int i = this.f6993r;
                this.f6994s = i + (this.f6974H - i);
            } else if (this.f7000y) {
                this.f6994s = this.f6993r;
            }
        }
        if (z) {
            int i2 = this.f6994s;
            if (i2 < this.f6974H) {
                int i3 = i2 - 1;
                int lineEnd = this.f6992q.getLineEnd(i3);
                int lineStart = this.f6992q.getLineStart(i3);
                float lineWidth = this.f6992q.getLineWidth(i3);
                String hideEndContent = getHideEndContent();
                String substring = fsVar.m2830a().substring(0, m22018a(hideEndContent, lineEnd, lineStart, lineWidth, this.f6989n.measureText(hideEndContent), 0.0f));
                if (substring.endsWith("\n")) {
                    substring = substring.substring(0, substring.length() - 1);
                }
                spannableStringBuilder.append((CharSequence) substring);
                if (this.f6972F) {
                    float f = 0.0f;
                    for (int i4 = 0; i4 < i3; i4++) {
                        f += this.f6992q.getLineWidth(i4);
                    }
                    float measureText = ((f / i3) - lineWidth) - this.f6989n.measureText(hideEndContent);
                    if (measureText > 0.0f) {
                        int i5 = 0;
                        while (i5 * this.f6989n.measureText(f6958c) < measureText) {
                            i5++;
                        }
                        int i6 = i5 - 1;
                        for (int i7 = 0; i7 < i6; i7++) {
                            spannableStringBuilder.append(f6958c);
                        }
                    }
                }
                spannableStringBuilder.append((CharSequence) hideEndContent);
                spannableStringBuilder.setSpan(new ClickableSpan() { // from class: com.ctetin.expandabletextviewlibrary.ExpandableTextView.3
                    @Override // android.text.style.ClickableSpan
                    public void onClick(View view) {
                        if (ExpandableTextView.this.f6998w) {
                            if (ExpandableTextView.this.f6991p != null) {
                                ExpandableTextView.this.f6991p.m2831a(StatusType.STATUS_CONTRACT);
                                ExpandableTextView expandableTextView = ExpandableTextView.this;
                                expandableTextView.m22017a(expandableTextView.f6991p.m2832a());
                            } else {
                                ExpandableTextView.this.m21995j();
                            }
                        }
                        if (ExpandableTextView.this.f6999x != null) {
                            ExpandableTextView.this.f6999x.mo21985a(StatusType.STATUS_EXPAND);
                        }
                    }

                    @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
                    public void updateDrawState(TextPaint textPaint) {
                        super.updateDrawState(textPaint);
                        textPaint.setColor(ExpandableTextView.this.f6976J);
                        textPaint.setUnderlineText(false);
                    }
                }, (spannableStringBuilder.length() - this.f6981O.length()) - (TextUtils.isEmpty(this.f6983Q) ? 0 : this.f6983Q.length() + 2), spannableStringBuilder.length(), 17);
            } else {
                spannableStringBuilder.append(fsVar.m2830a());
                if (this.f7000y) {
                    String expandEndContent = getExpandEndContent();
                    if (this.f6972F) {
                        int lineCount = this.f6992q.getLineCount() - 1;
                        float lineWidth2 = this.f6992q.getLineWidth(lineCount);
                        float f2 = 0.0f;
                        for (int i8 = 0; i8 < lineCount; i8++) {
                            f2 += this.f6992q.getLineWidth(i8);
                        }
                        float measureText2 = ((f2 / lineCount) - lineWidth2) - this.f6989n.measureText(expandEndContent);
                        if (measureText2 > 0.0f) {
                            int i9 = 0;
                            while (i9 * this.f6989n.measureText(f6958c) < measureText2) {
                                i9++;
                            }
                            int i10 = i9 - 1;
                            for (int i11 = 0; i11 < i10; i11++) {
                                spannableStringBuilder.append(f6958c);
                            }
                        }
                    }
                    spannableStringBuilder.append((CharSequence) expandEndContent);
                    spannableStringBuilder.setSpan(new ClickableSpan() { // from class: com.ctetin.expandabletextviewlibrary.ExpandableTextView.4
                        @Override // android.text.style.ClickableSpan
                        public void onClick(View view) {
                            if (ExpandableTextView.this.f6991p != null) {
                                ExpandableTextView.this.f6991p.m2831a(StatusType.STATUS_EXPAND);
                                ExpandableTextView expandableTextView = ExpandableTextView.this;
                                expandableTextView.m22017a(expandableTextView.f6991p.m2832a());
                            } else {
                                ExpandableTextView.this.m21995j();
                            }
                            if (ExpandableTextView.this.f6999x != null) {
                                ExpandableTextView.this.f6999x.mo21985a(StatusType.STATUS_CONTRACT);
                            }
                        }

                        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
                        public void updateDrawState(TextPaint textPaint) {
                            super.updateDrawState(textPaint);
                            textPaint.setColor(ExpandableTextView.this.f6980N);
                            textPaint.setUnderlineText(false);
                        }
                    }, (spannableStringBuilder.length() - this.f6982P.length()) - (TextUtils.isEmpty(this.f6983Q) ? 0 : this.f6983Q.length() + 2), spannableStringBuilder.length(), 17);
                } else if (!TextUtils.isEmpty(this.f6983Q)) {
                    spannableStringBuilder.append(this.f6983Q);
                    spannableStringBuilder.setSpan(new ForegroundColorSpan(this.f6984R), spannableStringBuilder.length() - this.f6983Q.length(), spannableStringBuilder.length(), 17);
                }
            }
        } else {
            spannableStringBuilder.append(fsVar.m2830a());
            if (!TextUtils.isEmpty(this.f6983Q)) {
                spannableStringBuilder.append(this.f6983Q);
                spannableStringBuilder.setSpan(new ForegroundColorSpan(this.f6984R), spannableStringBuilder.length() - this.f6983Q.length(), spannableStringBuilder.length(), 17);
            }
        }
        for (FormatData.C5358a aVar : fsVar.m2827b()) {
            if (spannableStringBuilder.length() >= aVar.m2815f()) {
                if (aVar.m2819c().equals(LinkType.LINK_TYPE)) {
                    if (!this.f6967A || !z) {
                        spannableStringBuilder.setSpan(new C1110e(this.f6996u, 1), aVar.m2816e(), aVar.m2816e() + 1, 18);
                        m22009c(spannableStringBuilder, aVar, aVar.m2815f());
                    } else {
                        int length = spannableStringBuilder.length() - getHideEndContent().length();
                        if (aVar.m2816e() < length) {
                            spannableStringBuilder.setSpan(new C1110e(this.f6996u, 1), aVar.m2816e(), aVar.m2816e() + 1, 18);
                            int f3 = aVar.m2815f();
                            if (this.f6994s < this.f6974H && length > aVar.m2816e() + 1 && length < aVar.m2815f()) {
                                f3 = length;
                            }
                            if (aVar.m2816e() + 1 < length) {
                                m22009c(spannableStringBuilder, aVar, f3);
                            }
                        }
                    }
                } else if (aVar.m2819c().equals(LinkType.MENTION_TYPE)) {
                    if (!this.f6967A || !z) {
                        m22013b(spannableStringBuilder, aVar, aVar.m2815f());
                    } else {
                        int length2 = spannableStringBuilder.length() - getHideEndContent().length();
                        if (aVar.m2816e() < length2) {
                            int f4 = aVar.m2815f();
                            if (this.f6994s >= this.f6974H || length2 >= aVar.m2815f()) {
                                length2 = f4;
                            }
                            m22013b(spannableStringBuilder, aVar, length2);
                        }
                    }
                } else if (aVar.m2819c().equals(LinkType.SELF)) {
                    if (!this.f6967A || !z) {
                        m22026a(spannableStringBuilder, aVar, aVar.m2815f());
                    } else {
                        int length3 = spannableStringBuilder.length() - getHideEndContent().length();
                        if (aVar.m2816e() < length3) {
                            int f5 = aVar.m2815f();
                            if (this.f6994s >= this.f6974H || length3 >= aVar.m2815f()) {
                                length3 = f5;
                            }
                            m22026a(spannableStringBuilder, aVar, length3);
                        }
                    }
                }
            }
        }
        setHighlightColor(0);
        setText(spannableStringBuilder);
        return spannableStringBuilder;
    }

    /* renamed from: a */
    private int m22028a(float f, float f2) {
        int i = 0;
        while ((i * this.f6989n.measureText(f6958c)) + f2 < f) {
            i++;
        }
        return i - 1;
    }

    /* renamed from: a */
    private void m22026a(SpannableStringBuilder spannableStringBuilder, final FormatData.C5358a aVar, int i) {
        spannableStringBuilder.setSpan(new ClickableSpan() { // from class: com.ctetin.expandabletextviewlibrary.ExpandableTextView.5
            @Override // android.text.style.ClickableSpan
            public void onClick(View view) {
                if (ExpandableTextView.this.f6997v != null) {
                    ExpandableTextView.this.f6997v.m21983a(LinkType.SELF, aVar.m2826a(), aVar.m2822b());
                }
            }

            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(TextPaint textPaint) {
                textPaint.setColor(ExpandableTextView.this.f6979M);
                textPaint.setUnderlineText(false);
            }
        }, aVar.m2816e(), i, 17);
    }

    /* renamed from: b */
    private void m22013b(SpannableStringBuilder spannableStringBuilder, final FormatData.C5358a aVar, int i) {
        spannableStringBuilder.setSpan(new ClickableSpan() { // from class: com.ctetin.expandabletextviewlibrary.ExpandableTextView.6
            @Override // android.text.style.ClickableSpan
            public void onClick(View view) {
                if (ExpandableTextView.this.f6997v != null) {
                    ExpandableTextView.this.f6997v.m21983a(LinkType.MENTION_TYPE, aVar.m2817d(), null);
                }
            }

            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(TextPaint textPaint) {
                textPaint.setColor(ExpandableTextView.this.f6977K);
                textPaint.setUnderlineText(false);
            }
        }, aVar.m2816e(), i, 17);
    }

    /* renamed from: c */
    private void m22009c(SpannableStringBuilder spannableStringBuilder, final FormatData.C5358a aVar, int i) {
        spannableStringBuilder.setSpan(new ClickableSpan() { // from class: com.ctetin.expandabletextviewlibrary.ExpandableTextView.7
            @Override // android.text.style.ClickableSpan
            public void onClick(View view) {
                if (ExpandableTextView.this.f6997v != null) {
                    ExpandableTextView.this.f6997v.m21983a(LinkType.LINK_TYPE, aVar.m2817d(), null);
                    return;
                }
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.setFlags(268435456);
                intent.setData(Uri.parse(aVar.m2817d()));
                ExpandableTextView.this.f6990o.startActivity(intent);
            }

            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(TextPaint textPaint) {
                textPaint.setColor(ExpandableTextView.this.f6978L);
                textPaint.setUnderlineText(false);
            }
        }, aVar.m2816e() + 1, i, 17);
    }

    public void setCurrStatus(StatusType fqVar) {
        m22017a(fqVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: j */
    public void m21995j() {
        m22017a((StatusType) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m22017a(StatusType fqVar) {
        final boolean z = this.f6994s < this.f6974H;
        if (fqVar != null) {
            this.f6973G = false;
        }
        if (this.f6973G) {
            ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
            ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.ctetin.expandabletextviewlibrary.ExpandableTextView.8
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    Float f = (Float) valueAnimator.getAnimatedValue();
                    if (z) {
                        ExpandableTextView expandableTextView = ExpandableTextView.this;
                        expandableTextView.f6994s = expandableTextView.f6993r + ((int) ((ExpandableTextView.this.f6974H - ExpandableTextView.this.f6993r) * f.floatValue()));
                    } else if (ExpandableTextView.this.f7000y) {
                        ExpandableTextView expandableTextView2 = ExpandableTextView.this;
                        expandableTextView2.f6994s = expandableTextView2.f6993r + ((int) ((ExpandableTextView.this.f6974H - ExpandableTextView.this.f6993r) * (1.0f - f.floatValue())));
                    }
                    ExpandableTextView expandableTextView3 = ExpandableTextView.this;
                    expandableTextView3.setText(expandableTextView3.m22019a(expandableTextView3.f6975I));
                }
            });
            ofFloat.setDuration(100L);
            ofFloat.start();
            return;
        }
        if (z) {
            int i = this.f6993r;
            this.f6994s = i + (this.f6974H - i);
        } else if (this.f7000y) {
            this.f6994s = this.f6993r;
        }
        setText(m22019a(this.f6975I));
    }

    /* renamed from: a */
    private int m22018a(String str, int i, int i2, float f, float f2, float f3) {
        int i3 = (int) (((f - (f2 + f3)) * (i - i2)) / f);
        if (i3 <= str.length()) {
            return i;
        }
        int i4 = i3 + i2;
        return this.f6989n.measureText(this.f7001z.m2830a().substring(i2, i4)) <= f - f2 ? i4 : m22018a(str, i, i2, f, f2, f3 + this.f6989n.measureText(f6958c));
    }

    /* renamed from: b */
    private FormatData m22011b(CharSequence charSequence) {
        int i;
        int i2;
        FormatData fsVar = new FormatData();
        ArrayList arrayList = new ArrayList();
        Matcher matcher = Pattern.compile(f6964i, 2).matcher(charSequence);
        StringBuffer stringBuffer = new StringBuffer();
        HashMap hashMap = new HashMap();
        if (this.f6971E) {
            ArrayList arrayList2 = new ArrayList();
            i = 0;
            int i3 = 0;
            while (matcher.find()) {
                int start = matcher.start();
                int end = matcher.end();
                stringBuffer.append(charSequence.toString().substring(i3, start));
                String group = matcher.group();
                if (!TextUtils.isEmpty(group)) {
                    String substring = group.substring(group.indexOf("[") + 1, group.indexOf("]"));
                    String substring2 = group.substring(group.indexOf("(") + 1, group.indexOf(")"));
                    String a = UUIDUtils.m2813a(substring.length());
                    arrayList2.add(new FormatData.C5358a(stringBuffer.length() + 1, stringBuffer.length() + 2 + substring.length(), substring, substring2, LinkType.SELF));
                    hashMap.put(a, substring);
                    stringBuffer.append(f6958c + a + f6958c);
                    i3 = end;
                }
                i = end;
            }
            arrayList.addAll(arrayList2);
        } else {
            i = 0;
        }
        stringBuffer.append(charSequence.toString().substring(i, charSequence.toString().length()));
        String stringBuffer2 = stringBuffer.toString();
        StringBuffer stringBuffer3 = new StringBuffer();
        if (this.f6970D) {
            Matcher matcher2 = PatternsCompat.AUTOLINK_WEB_URL.matcher(stringBuffer2);
            i2 = 0;
            int i4 = 0;
            while (matcher2.find()) {
                int start2 = matcher2.start();
                int end2 = matcher2.end();
                stringBuffer3.append(stringBuffer2.toString().substring(i4, start2));
                if (this.f6968B) {
                    arrayList.add(new FormatData.C5358a(stringBuffer3.length() + 1, stringBuffer3.length() + 2 + f6961f.length(), matcher2.group(), LinkType.LINK_TYPE));
                    stringBuffer3.append(f6958c + f6961f + f6958c);
                } else {
                    String group2 = matcher2.group();
                    String a2 = UUIDUtils.m2813a(group2.length());
                    arrayList.add(new FormatData.C5358a(stringBuffer3.length(), stringBuffer3.length() + 2 + a2.length(), group2, LinkType.LINK_TYPE));
                    hashMap.put(a2, group2);
                    stringBuffer3.append(f6958c + a2 + f6958c);
                }
                i2 = end2;
                i4 = i2;
            }
        } else {
            i2 = 0;
        }
        stringBuffer3.append(stringBuffer2.toString().substring(i2, stringBuffer2.toString().length()));
        if (this.f6969C) {
            Matcher matcher3 = Pattern.compile(f6963h, 2).matcher(stringBuffer3.toString());
            ArrayList arrayList3 = new ArrayList();
            while (matcher3.find()) {
                arrayList3.add(new FormatData.C5358a(matcher3.start(), matcher3.end(), matcher3.group(), LinkType.MENTION_TYPE));
            }
            arrayList.addAll(0, arrayList3);
        }
        if (!hashMap.isEmpty()) {
            String stringBuffer4 = stringBuffer3.toString();
            for (Map.Entry entry : hashMap.entrySet()) {
                stringBuffer4 = stringBuffer4.replaceAll((String) entry.getKey(), (String) entry.getValue());
            }
            stringBuffer3 = new StringBuffer(stringBuffer4);
        }
        fsVar.m2829a(stringBuffer3.toString());
        fsVar.m2828a(arrayList);
        return fsVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.ctetin.expandabletextviewlibrary.ExpandableTextView$e */
    /* loaded from: classes.dex */
    public class C1110e extends ImageSpan {

        /* renamed from: b */
        private Drawable f7016b;

        public C1110e(Drawable drawable, int i) {
            super(drawable, i);
            this.f7016b = drawable;
        }

        @Override // android.text.style.ImageSpan, android.text.style.DynamicDrawableSpan
        public Drawable getDrawable() {
            return this.f7016b;
        }

        @Override // android.text.style.DynamicDrawableSpan, android.text.style.ReplacementSpan
        public void draw(@NonNull Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, @NonNull Paint paint) {
            Drawable drawable = getDrawable();
            Paint.FontMetricsInt fontMetricsInt = paint.getFontMetricsInt();
            canvas.save();
            canvas.translate(f, ((((fontMetricsInt.descent + i4) + i4) + fontMetricsInt.ascent) / 2) - (drawable.getBounds().bottom / 2));
            drawable.draw(canvas);
            canvas.restore();
        }
    }

    /* renamed from: a */
    public void m22016a(ExpandableStatusFix frVar) {
        this.f6991p = frVar;
    }

    /* renamed from: com.ctetin.expandabletextviewlibrary.ExpandableTextView$a */
    /* loaded from: classes.dex */
    public static class C1106a extends LinkMovementMethod {

        /* renamed from: a */
        static C1106a f7014a;

        /* renamed from: a */
        public static C1106a m21986a() {
            if (f7014a == null) {
                f7014a = new C1106a();
            }
            return f7014a;
        }

        @Override // android.text.method.LinkMovementMethod, android.text.method.ScrollingMovementMethod, android.text.method.BaseMovementMethod, android.text.method.MovementMethod
        public boolean onTouchEvent(TextView textView, Spannable spannable, MotionEvent motionEvent) {
            int action = motionEvent.getAction();
            if (action != 1 && action != 0) {
                return Touch.onTouchEvent(textView, spannable, motionEvent);
            }
            int x = ((int) motionEvent.getX()) - textView.getTotalPaddingLeft();
            int y = ((int) motionEvent.getY()) - textView.getTotalPaddingTop();
            int scrollX = x + textView.getScrollX();
            int scrollY = y + textView.getScrollY();
            Layout layout = textView.getLayout();
            int offsetForHorizontal = layout.getOffsetForHorizontal(layout.getLineForVertical(scrollY), scrollX);
            Object[] objArr = (ClickableSpan[]) spannable.getSpans(offsetForHorizontal, offsetForHorizontal, ClickableSpan.class);
            if (objArr.length != 0) {
                if (action == 1) {
                    objArr[0].onClick(textView);
                } else if (action == 0) {
                    Selection.setSelection(spannable, spannable.getSpanStart(objArr[0]), spannable.getSpanEnd(objArr[0]));
                }
                if (textView instanceof ExpandableTextView) {
                    ((ExpandableTextView) textView).f6987j = true;
                }
                return true;
            }
            Selection.removeSelection(spannable);
            Touch.onTouchEvent(textView, spannable, motionEvent);
            return false;
        }
    }

    @Override // android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        this.f6987j = false;
        boolean onTouchEvent = super.onTouchEvent(motionEvent);
        if (this.f6988k) {
            return this.f6987j;
        }
        if (action == 1) {
            setTextIsSelectable(false);
        }
        return onTouchEvent;
    }

    public AbstractC1108c getOnGetLineCountListener() {
        return this.f6986T;
    }

    public void setOnGetLineCountListener(AbstractC1108c cVar) {
        this.f6986T = cVar;
    }

    public AbstractC1109d getLinkClickListener() {
        return this.f6997v;
    }

    public void setLinkClickListener(AbstractC1109d dVar) {
        this.f6997v = dVar;
    }

    /* renamed from: a */
    public boolean m22029a() {
        return this.f6969C;
    }

    public void setNeedMention(boolean z) {
        this.f6969C = z;
    }

    public Drawable getLinkDrawable() {
        return this.f6996u;
    }

    public void setLinkDrawable(Drawable drawable) {
        this.f6996u = drawable;
    }

    /* renamed from: b */
    public boolean m22014b() {
        return this.f7000y;
    }

    public void setNeedContract(boolean z) {
        this.f7000y = z;
    }

    /* renamed from: c */
    public boolean m22010c() {
        return this.f6967A;
    }

    public void setNeedExpend(boolean z) {
        this.f6967A = z;
    }

    /* renamed from: d */
    public boolean m22007d() {
        return this.f6973G;
    }

    public void setNeedAnimation(boolean z) {
        this.f6973G = z;
    }

    public int getExpandableLineCount() {
        return this.f6974H;
    }

    public void setExpandableLineCount(int i) {
        this.f6974H = i;
    }

    public int getExpandTextColor() {
        return this.f6976J;
    }

    public void setExpandTextColor(int i) {
        this.f6976J = i;
    }

    public int getExpandableLinkTextColor() {
        return this.f6978L;
    }

    public void setExpandableLinkTextColor(int i) {
        this.f6978L = i;
    }

    public int getContractTextColor() {
        return this.f6980N;
    }

    public void setContractTextColor(int i) {
        this.f6980N = i;
    }

    public String getExpandString() {
        return this.f6981O;
    }

    public void setExpandString(String str) {
        this.f6981O = str;
    }

    public String getContractString() {
        return this.f6982P;
    }

    public void setContractString(String str) {
        this.f6982P = str;
    }

    public int getEndExpandTextColor() {
        return this.f6984R;
    }

    public void setEndExpandTextColor(int i) {
        this.f6984R = i;
    }

    /* renamed from: e */
    public boolean m22005e() {
        return this.f6970D;
    }

    public void setNeedLink(boolean z) {
        this.f6970D = z;
    }

    public int getSelfTextColor() {
        return this.f6979M;
    }

    public void setSelfTextColor(int i) {
        this.f6979M = i;
    }

    /* renamed from: f */
    public boolean m22003f() {
        return this.f6971E;
    }

    public void setNeedSelf(boolean z) {
        this.f6971E = z;
    }

    /* renamed from: g */
    public boolean m22001g() {
        return this.f6972F;
    }

    public void setNeedAlwaysShowRight(boolean z) {
        this.f6972F = z;
    }

    public AbstractC1107b getExpandOrContractClickListener() {
        return this.f6999x;
    }

    public void setExpandOrContractClickListener(AbstractC1107b bVar) {
        this.f6999x = bVar;
    }

    /* renamed from: a */
    public void m22025a(AbstractC1107b bVar, boolean z) {
        this.f6999x = bVar;
        this.f6998w = z;
    }
}
