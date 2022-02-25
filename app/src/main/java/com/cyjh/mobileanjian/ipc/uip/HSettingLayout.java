package com.cyjh.mobileanjian.ipc.uip;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TableLayout;
import com.cyjh.mqsdk.C1375R;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.cyjh.mobileanjian.ipc.uip.c */
/* loaded from: classes.dex */
public final class HSettingLayout extends LinearLayout implements RadioGroup.OnCheckedChangeListener {

    /* renamed from: a */
    static final int[] f8661a = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};

    /* renamed from: h */
    private static final int f8662h = 32;

    /* renamed from: b */
    RadioGroup f8663b;

    /* renamed from: c */
    List<ScrollView> f8664c;

    /* renamed from: d */
    int f8665d;

    /* renamed from: e */
    LinearLayout f8666e;

    /* renamed from: f */
    ScrollView f8667f;

    /* renamed from: g */
    int f8668g;

    /* renamed from: i */
    private FrameLayout f8669i;

    /* renamed from: j */
    private int f8670j;

    public HSettingLayout(Context context) {
        this(context, (byte) 0);
    }

    private HSettingLayout(Context context, byte b) {
        this(context, (char) 0);
    }

    private HSettingLayout(Context context, char c) {
        super(context, null, 0);
        this.f8668g = 0;
        this.f8665d = (int) TypedValue.applyDimension(1, 10.0f, context.getResources().getDisplayMetrics());
        setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        setOrientation(0);
        LinearLayout linearLayout = new LinearLayout(context);
        TableLayout.LayoutParams layoutParams = new TableLayout.LayoutParams(0, -1);
        layoutParams.weight = 7.5f;
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setGravity(16);
        linearLayout.setOrientation(1);
        ScrollView scrollView = new ScrollView(context);
        this.f8663b = new RadioGroup(context);
        this.f8663b.setOrientation(1);
        RadioGroup radioGroup = this.f8663b;
        int i = this.f8665d;
        radioGroup.setPadding(i, 0, 0, -i);
        scrollView.addView(this.f8663b);
        linearLayout.addView(scrollView);
        addView(linearLayout);
        View view = new View(context);
        view.setLayoutParams(new LinearLayout.LayoutParams((int) TypedValue.applyDimension(1, 1.0f, context.getResources().getDisplayMetrics()), -1));
        view.setBackgroundColor(-16777216);
        addView(view);
        this.f8669i = new FrameLayout(context);
        TableLayout.LayoutParams layoutParams2 = new TableLayout.LayoutParams(0, -1);
        layoutParams2.weight = 2.5f;
        this.f8669i.setLayoutParams(layoutParams2);
        FrameLayout frameLayout = this.f8669i;
        int i2 = this.f8665d;
        frameLayout.setPadding(i2, 0, i2, 0);
        addView(this.f8669i);
        this.f8664c = new ArrayList(32);
        this.f8663b.setOnCheckedChangeListener(this);
    }

    public final List<ScrollView> getScrollViewList() {
        return this.f8664c;
    }

    /* renamed from: a */
    private void m20680a(String str) {
        RadioButton radioButton = new RadioButton(getContext());
        radioButton.setId(f8661a[this.f8668g]);
        radioButton.setPadding(0, 0, 0, this.f8665d);
        radioButton.setButtonDrawable(new BitmapDrawable((Bitmap) null));
        radioButton.setTextColor(getResources().getColorStateList(C1375R.color.selector_tab));
        radioButton.setText(str);
        radioButton.setLines(1);
        radioButton.setEllipsize(TextUtils.TruncateAt.END);
        this.f8663b.addView(radioButton);
        this.f8667f = new ScrollView(getContext());
        this.f8667f.setDescendantFocusability(131072);
        this.f8667f.setFocusable(true);
        this.f8667f.setFocusableInTouchMode(true);
        this.f8667f.setOnTouchListener(new View$OnTouchListenerC13331());
        this.f8667f.setTag(str);
        this.f8667f.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(1);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        this.f8666e = linearLayout;
        this.f8667f.addView(this.f8666e);
    }

    /* compiled from: HSettingLayout.java */
    /* renamed from: com.cyjh.mobileanjian.ipc.uip.c$1 */
    /* loaded from: classes.dex */
    final class View$OnTouchListenerC13331 implements View.OnTouchListener {
        /* JADX INFO: Access modifiers changed from: package-private */
        public View$OnTouchListenerC13331() {
        }

        @Override // android.view.View.OnTouchListener
        public final boolean onTouch(View view, MotionEvent motionEvent) {
            view.requestFocusFromTouch();
            return false;
        }
    }

    /* renamed from: a */
    public final void m20681a(View view) {
        this.f8666e.addView(view);
    }

    /* renamed from: a */
    private void m20682a() {
        this.f8664c.add(this.f8667f);
        this.f8668g++;
    }

    /* renamed from: b */
    private LinearLayout m20678b() {
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(1);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        return linearLayout;
    }

    /* renamed from: a */
    public final void m20679a(List<ScrollView> list) {
        this.f8664c = list;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            RadioButton radioButton = new RadioButton(getContext());
            radioButton.setId(f8661a[i]);
            radioButton.setPadding(0, 0, 0, this.f8665d);
            radioButton.setButtonDrawable(new BitmapDrawable((Bitmap) null));
            radioButton.setTextColor(getResources().getColorStateList(C1375R.color.selector_tab));
            radioButton.setText((String) list.get(i).getTag());
            radioButton.setLines(1);
            radioButton.setEllipsize(TextUtils.TruncateAt.END);
            this.f8663b.addView(radioButton);
        }
    }

    public final int getCurrentItem() {
        return this.f8670j;
    }

    public final void setSelectTab(int i) {
        if (this.f8664c.size() != 0) {
            if (i < 0 || i >= 32) {
                i = 0;
            }
            this.f8670j = i;
            this.f8663b.check(i);
            this.f8669i.removeAllViews();
            this.f8669i.addView(this.f8664c.get(i));
        }
    }

    @Override // android.widget.RadioGroup.OnCheckedChangeListener
    public final void onCheckedChanged(RadioGroup radioGroup, int i) {
        setSelectTab(i);
    }
}
