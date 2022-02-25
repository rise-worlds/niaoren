package com.cyjh.mobileanjian.ipc.p044ui;

import android.content.Context;
import android.support.p003v4.view.PagerAdapter;
import android.support.p003v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.cyjh.mqsdk.C1375R;

/* renamed from: com.cyjh.mobileanjian.ipc.ui.SlidingTabLayout */
/* loaded from: classes.dex */
public class SlidingTabLayout extends HorizontalScrollView {

    /* renamed from: b */
    private static final int f8428b = 24;

    /* renamed from: c */
    private static final int f8429c = 4;

    /* renamed from: d */
    private static final int f8430d = 12;

    /* renamed from: a */
    ViewPager f8431a;

    /* renamed from: e */
    private int f8432e;

    /* renamed from: f */
    private int f8433f;

    /* renamed from: g */
    private int f8434g;

    /* renamed from: h */
    private boolean f8435h;

    /* renamed from: i */
    private SparseArray<String> f8436i;

    /* renamed from: j */
    private ViewPager.OnPageChangeListener f8437j;

    /* renamed from: k */
    private final SlidingTabStrip f8438k;

    /* renamed from: com.cyjh.mobileanjian.ipc.ui.SlidingTabLayout$c */
    /* loaded from: classes.dex */
    public interface AbstractC1296c {
        /* renamed from: a */
        int mo20889a(int i);
    }

    public SlidingTabLayout(Context context) {
        this(context, null);
    }

    public SlidingTabLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SlidingTabLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f8436i = new SparseArray<>();
        setHorizontalScrollBarEnabled(false);
        setFillViewport(true);
        this.f8432e = (int) (getResources().getDisplayMetrics().density * 24.0f);
        this.f8438k = new SlidingTabStrip(context);
        addView(this.f8438k, -1, -2);
    }

    public void setCustomTabColorizer(AbstractC1296c cVar) {
        SlidingTabStrip dVar = this.f8438k;
        dVar.f8476a = cVar;
        dVar.invalidate();
    }

    public void setDistributeEvenly(boolean z) {
        this.f8435h = z;
    }

    public void setSelectedIndicatorColors(int... iArr) {
        SlidingTabStrip dVar = this.f8438k;
        dVar.f8476a = null;
        dVar.f8477b.f8484a = iArr;
        dVar.invalidate();
    }

    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.f8437j = onPageChangeListener;
    }

    /* renamed from: a */
    private void m20914a(int i, int i2) {
        this.f8433f = i;
        this.f8434g = i2;
    }

    public void setViewPager(ViewPager viewPager) {
        TextView textView;
        TextView textView2;
        this.f8438k.removeAllViews();
        this.f8431a = viewPager;
        if (viewPager != null) {
            viewPager.setOnPageChangeListener(new C1294a(this, (byte) 0));
            PagerAdapter adapter = this.f8431a.getAdapter();
            View$OnClickListenerC1295b bVar = new View$OnClickListenerC1295b(this, (byte) 0);
            for (int i = 0; i < adapter.getCount(); i++) {
                if (this.f8433f != 0) {
                    textView2 = LayoutInflater.from(getContext()).inflate(this.f8433f, (ViewGroup) this.f8438k, false);
                    textView = (TextView) textView2.findViewById(this.f8434g);
                } else {
                    textView2 = null;
                    textView = null;
                }
                if (textView2 == null) {
                    TextView textView3 = new TextView(getContext());
                    textView3.setGravity(17);
                    textView3.setTextSize(2, 12.0f);
                    textView3.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
                    TypedValue typedValue = new TypedValue();
                    getContext().getTheme().resolveAttribute(16843534, typedValue, true);
                    textView3.setBackgroundResource(typedValue.resourceId);
                    textView3.setAllCaps(true);
                    int i2 = (int) (getResources().getDisplayMetrics().density * 4.0f);
                    textView3.setPadding(i2, i2, i2, i2);
                    textView3.setSingleLine(true);
                    textView2 = textView3;
                }
                if (textView == null && TextView.class.isInstance(textView2)) {
                    textView = (TextView) textView2;
                }
                if (this.f8435h) {
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) textView2.getLayoutParams();
                    layoutParams.width = 0;
                    layoutParams.weight = 1.0f;
                }
                textView.setTextColor(getResources().getColorStateList(C1375R.color.selector_tab));
                textView.setTextSize(2, 14.0f);
                textView.setText(adapter.getPageTitle(i));
                textView.setAllCaps(false);
                textView2.setOnClickListener(bVar);
                String str = this.f8436i.get(i, null);
                if (str != null) {
                    textView2.setContentDescription(str);
                }
                this.f8438k.addView(textView2);
                if (i == this.f8431a.getCurrentItem()) {
                    textView2.setSelected(true);
                }
            }
        }
    }

    public ViewPager getViewPager() {
        return this.f8431a;
    }

    /* renamed from: a */
    private void m20911a(LinearLayout linearLayout, String str) {
        UiShowPagerAdapter lVar = (UiShowPagerAdapter) this.f8431a.getAdapter();
        lVar.f8602a.add(linearLayout);
        lVar.f8603b.add(str);
        lVar.notifyDataSetChanged();
        this.f8431a.setAdapter(lVar);
        setViewPager(this.f8431a);
    }

    /* renamed from: a */
    private TextView m20912a(Context context) {
        TextView textView = new TextView(context);
        textView.setGravity(17);
        textView.setTextSize(2, 12.0f);
        textView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        TypedValue typedValue = new TypedValue();
        getContext().getTheme().resolveAttribute(16843534, typedValue, true);
        textView.setBackgroundResource(typedValue.resourceId);
        textView.setAllCaps(true);
        int i = (int) (getResources().getDisplayMetrics().density * 4.0f);
        textView.setPadding(i, i, i, i);
        textView.setSingleLine(true);
        return textView;
    }

    /* renamed from: a */
    private void m20915a() {
        TextView textView;
        TextView textView2;
        PagerAdapter adapter = this.f8431a.getAdapter();
        View$OnClickListenerC1295b bVar = new View$OnClickListenerC1295b(this, (byte) 0);
        for (int i = 0; i < adapter.getCount(); i++) {
            if (this.f8433f != 0) {
                textView2 = LayoutInflater.from(getContext()).inflate(this.f8433f, (ViewGroup) this.f8438k, false);
                textView = (TextView) textView2.findViewById(this.f8434g);
            } else {
                textView2 = null;
                textView = null;
            }
            if (textView2 == null) {
                TextView textView3 = new TextView(getContext());
                textView3.setGravity(17);
                textView3.setTextSize(2, 12.0f);
                textView3.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
                TypedValue typedValue = new TypedValue();
                getContext().getTheme().resolveAttribute(16843534, typedValue, true);
                textView3.setBackgroundResource(typedValue.resourceId);
                textView3.setAllCaps(true);
                int i2 = (int) (getResources().getDisplayMetrics().density * 4.0f);
                textView3.setPadding(i2, i2, i2, i2);
                textView3.setSingleLine(true);
                textView2 = textView3;
            }
            if (textView == null && TextView.class.isInstance(textView2)) {
                textView = (TextView) textView2;
            }
            if (this.f8435h) {
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) textView2.getLayoutParams();
                layoutParams.width = 0;
                layoutParams.weight = 1.0f;
            }
            textView.setTextColor(getResources().getColorStateList(C1375R.color.selector_tab));
            textView.setTextSize(2, 14.0f);
            textView.setText(adapter.getPageTitle(i));
            textView.setAllCaps(false);
            textView2.setOnClickListener(bVar);
            String str = this.f8436i.get(i, null);
            if (str != null) {
                textView2.setContentDescription(str);
            }
            this.f8438k.addView(textView2);
            if (i == this.f8431a.getCurrentItem()) {
                textView2.setSelected(true);
            }
        }
    }

    /* renamed from: a */
    private void m20913a(int i, String str) {
        this.f8436i.put(i, str);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViewPager viewPager = this.f8431a;
        if (viewPager != null) {
            m20908b(viewPager.getCurrentItem(), 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m20908b(int i, int i2) {
        View childAt;
        int childCount = this.f8438k.getChildCount();
        if (childCount != 0 && i >= 0 && i < childCount && (childAt = this.f8438k.getChildAt(i)) != null) {
            int left = childAt.getLeft() + i2;
            if (i > 0 || i2 > 0) {
                left -= this.f8432e;
            }
            scrollTo(left, 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.cyjh.mobileanjian.ipc.ui.SlidingTabLayout$a */
    /* loaded from: classes.dex */
    public class C1294a implements ViewPager.OnPageChangeListener {

        /* renamed from: b */
        private int f8440b;

        private C1294a() {
        }

        /* synthetic */ C1294a(SlidingTabLayout slidingTabLayout, byte b) {
            this();
        }

        @Override // android.support.p003v4.view.ViewPager.OnPageChangeListener
        public final void onPageScrolled(int i, float f, int i2) {
            int childCount = SlidingTabLayout.this.f8438k.getChildCount();
            if (childCount != 0 && i >= 0 && i < childCount) {
                SlidingTabLayout.this.f8438k.m20893a(i, f);
                View childAt = SlidingTabLayout.this.f8438k.getChildAt(i);
                SlidingTabLayout.this.m20908b(i, childAt != null ? (int) (childAt.getWidth() * f) : 0);
                if (SlidingTabLayout.this.f8437j != null) {
                    SlidingTabLayout.this.f8437j.onPageScrolled(i, f, i2);
                }
            }
        }

        @Override // android.support.p003v4.view.ViewPager.OnPageChangeListener
        public final void onPageScrollStateChanged(int i) {
            this.f8440b = i;
            if (SlidingTabLayout.this.f8437j != null) {
                SlidingTabLayout.this.f8437j.onPageScrollStateChanged(i);
            }
        }

        @Override // android.support.p003v4.view.ViewPager.OnPageChangeListener
        public final void onPageSelected(int i) {
            if (this.f8440b == 0) {
                SlidingTabLayout.this.f8438k.m20893a(i, 0.0f);
                SlidingTabLayout.this.m20908b(i, 0);
            }
            int i2 = 0;
            while (i2 < SlidingTabLayout.this.f8438k.getChildCount()) {
                SlidingTabLayout.this.f8438k.getChildAt(i2).setSelected(i == i2);
                i2++;
            }
            if (SlidingTabLayout.this.f8437j != null) {
                SlidingTabLayout.this.f8437j.onPageSelected(i);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.cyjh.mobileanjian.ipc.ui.SlidingTabLayout$b */
    /* loaded from: classes.dex */
    public class View$OnClickListenerC1295b implements View.OnClickListener {
        private View$OnClickListenerC1295b() {
        }

        /* synthetic */ View$OnClickListenerC1295b(SlidingTabLayout slidingTabLayout, byte b) {
            this();
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            for (int i = 0; i < SlidingTabLayout.this.f8438k.getChildCount(); i++) {
                if (view == SlidingTabLayout.this.f8438k.getChildAt(i)) {
                    SlidingTabLayout.this.f8431a.setCurrentItem(i);
                    return;
                }
            }
        }
    }
}
