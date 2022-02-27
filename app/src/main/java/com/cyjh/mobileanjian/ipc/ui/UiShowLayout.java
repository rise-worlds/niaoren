package com.cyjh.mobileanjian.ipc.ui;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.cyjh.ddy.media.media.ActionCode;
import com.cyjh.mobileanjian.ipc.view.ExToast;
import com.cyjh.mqsdk.C1375R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/* renamed from: com.cyjh.mobileanjian.ipc.ui.k */
/* loaded from: classes.dex */
public final class UiShowLayout {

    /* renamed from: d */
    private static final int f8573d = 4096;

    /* renamed from: e */
    private static final String f8574e = "first_row_tag";

    /* renamed from: f */
    private static final String f8575f = "row_mark";

    /* renamed from: v */
    private static final int f8576v = 256;

    /* renamed from: x */
    private static int f8577x;

    /* renamed from: a */
    public View.OnClickListener f8578a;

    /* renamed from: b */
    public View.OnClickListener f8579b;

    /* renamed from: g */
    private WindowManager f8581g;

    /* renamed from: i */
    private ExToast f8583i;

    /* renamed from: j */
    private Context f8584j;

    /* renamed from: k */
    private LinearLayout f8585k;

    /* renamed from: l */
    private String f8586l;

    /* renamed from: q */
    private UiFactory f8591q;

    /* renamed from: r */
    private LinearLayout f8592r;

    /* renamed from: s */
    private LinearLayout f8593s;

    /* renamed from: t */
    private TextView f8594t;

    /* renamed from: m */
    private Set<String> f8587m = new HashSet();

    /* renamed from: n */
    private Set<String> f8588n = new HashSet();

    /* renamed from: o */
    private HashMap<String, String> f8589o = new HashMap<>();

    /* renamed from: p */
    private HashMap<String, String> f8590p = new HashMap<>();

    /* renamed from: c */
    public boolean f8580c = false;

    /* renamed from: u */
    private ArrayList<SlidingTabLayout> f8595u = new ArrayList<>();

    /* renamed from: w */
    private Handler f8596w = new Handler() { // from class: com.cyjh.mobileanjian.ipc.ui.k.3
        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            if (message.what == 256) {
                UiShowLayout.this.f8585k.invalidate();
            }
        }
    };

    /* renamed from: h */
    private WindowManager.LayoutParams f8582h = new WindowManager.LayoutParams();

    public UiShowLayout(Context context, String str, int i, int i2) {
        this.f8586l = null;
        this.f8584j = context;
        this.f8591q = UiFactory.m20882a(context);
        this.f8581g = (WindowManager) this.f8584j.getSystemService("window");
        if (Build.VERSION.SDK_INT >= 26) {
            this.f8582h.type = 2038;
        } else if (Build.VERSION.SDK_INT < 19 || Build.VERSION.SDK_INT >= 24) {
            this.f8582h.type = ActionCode.CtrlConnectRefuse_2002;
        } else {
            this.f8582h.type = 2005;
        }
        WindowManager.LayoutParams layoutParams = this.f8582h;
        layoutParams.format = 1;
        layoutParams.gravity = 16;
        layoutParams.x = 0;
        layoutParams.y = 0;
        layoutParams.alpha = 1.0f;
        layoutParams.width = -1;
        layoutParams.height = -1;
        DisplayMetrics displayMetrics = this.f8584j.getResources().getDisplayMetrics();
        if (m20730c(40) + i >= displayMetrics.widthPixels) {
            i = -1;
        } else if (i < -2 || (i >= 0 && i < m20730c(200))) {
            i = m20730c(200);
        }
        if (m20730c(40) + i2 >= displayMetrics.heightPixels) {
            i2 = -2;
        } else if (i2 < -2 || (i2 >= 0 && i2 < m20730c(200))) {
            i2 = m20730c(200);
        }
        LayoutInflater from = LayoutInflater.from(this.f8584j);
        this.f8593s = (LinearLayout) from.inflate(C1375R.layout.layout_ui_show, (ViewGroup) null);
        LinearLayout linearLayout = (LinearLayout) from.inflate(C1375R.layout.layout_display, (ViewGroup) null);
        linearLayout.setLayoutParams(new RelativeLayout.LayoutParams(i, i2));
        this.f8593s.setGravity(17);
        this.f8593s.addView(linearLayout);
        this.f8594t = (TextView) this.f8593s.findViewById(C1375R.C1377id.title);
        this.f8585k = (LinearLayout) this.f8593s.findViewById(C1375R.C1377id.layout_user);
        this.f8593s.findViewById(C1375R.C1377id.exit).setOnClickListener(new View$OnClickListenerC13191());
        this.f8593s.findViewById(C1375R.C1377id.next).setOnClickListener(new View$OnClickListenerC13202());
        this.f8586l = str;
        m20739a(str);
        m20737a(f8575f, str, -1, -2);
    }

    /* renamed from: a */
    public final void m20743a(Typeface typeface, int i) {
        this.f8594t.setTypeface(typeface);
        this.f8594t.setTextSize(2, i);
    }

    /* renamed from: a */
    public final void m20739a(String str) {
        this.f8594t.setText(str);
    }

    /* renamed from: a */
    public final String m20747a() {
        return this.f8594t.getText().toString();
    }

    /* renamed from: a */
    public final void m20746a(int i) {
        this.f8594t.setTextColor(i);
    }

    /* renamed from: b */
    public final void m20735b(int i) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(i);
        gradientDrawable.setCornerRadii(new float[]{m20730c(4), m20730c(4), m20730c(4), m20730c(4), 0.0f, 0.0f, 0.0f, 0.0f});
        this.f8594t.setBackground(gradientDrawable);
    }

    /* renamed from: f */
    private String m20725f() {
        return this.f8586l;
    }

    /* renamed from: c */
    private void m20728c(String str) {
        this.f8586l = str;
        m20739a(str);
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0040, code lost:
        continue;
     */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.view.View m20732b(java.lang.String r5) {
        /*
            r4 = this;
            android.widget.LinearLayout r0 = r4.f8585k
            android.view.View r0 = r0.findViewWithTag(r5)
            if (r0 != 0) goto L_0x0042
            java.util.ArrayList<com.cyjh.mobileanjian.ipc.ui.SlidingTabLayout> r1 = r4.f8595u
            java.util.Iterator r1 = r1.iterator()
        L_0x000e:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0042
            java.lang.Object r0 = r1.next()
            com.cyjh.mobileanjian.ipc.ui.SlidingTabLayout r0 = (com.cyjh.mobileanjian.ipc.ui.SlidingTabLayout) r0
            android.support.v4.view.ViewPager r0 = r0.getViewPager()
            android.support.v4.view.PagerAdapter r0 = r0.getAdapter()
            com.cyjh.mobileanjian.ipc.ui.l r0 = (com.cyjh.mobileanjian.ipc.ui.UiShowPagerAdapter) r0
            r2 = 0
            java.util.List<android.widget.LinearLayout> r0 = r0.f8602a
            java.util.Iterator r0 = r0.iterator()
        L_0x002b:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L_0x003f
            java.lang.Object r2 = r0.next()
            android.widget.LinearLayout r2 = (android.widget.LinearLayout) r2
            android.view.View r2 = r2.findViewWithTag(r5)
            if (r2 == 0) goto L_0x002b
            r0 = r2
            goto L_0x0040
        L_0x003f:
            r0 = r2
        L_0x0040:
            if (r0 == 0) goto L_0x000e
        L_0x0042:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cyjh.mobileanjian.ipc.ui.UiShowLayout.m20732b(java.lang.String):android.view.View");
    }

    /* renamed from: a */
    private void m20745a(int i, int i2) {
        DisplayMetrics displayMetrics = this.f8584j.getResources().getDisplayMetrics();
        if (m20730c(40) + i >= displayMetrics.widthPixels) {
            i = -1;
        } else if (i < -2 || (i >= 0 && i < m20730c(200))) {
            i = m20730c(200);
        }
        if (m20730c(40) + i2 >= displayMetrics.heightPixels) {
            i2 = -2;
        } else if (i2 < -2 || (i2 >= 0 && i2 < m20730c(200))) {
            i2 = m20730c(200);
        }
        LayoutInflater from = LayoutInflater.from(this.f8584j);
        this.f8593s = (LinearLayout) from.inflate(C1375R.layout.layout_ui_show, (ViewGroup) null);
        LinearLayout linearLayout = (LinearLayout) from.inflate(C1375R.layout.layout_display, (ViewGroup) null);
        linearLayout.setLayoutParams(new RelativeLayout.LayoutParams(i, i2));
        this.f8593s.setGravity(17);
        this.f8593s.addView(linearLayout);
        this.f8594t = (TextView) this.f8593s.findViewById(C1375R.C1377id.title);
        this.f8585k = (LinearLayout) this.f8593s.findViewById(C1375R.C1377id.layout_user);
        this.f8593s.findViewById(C1375R.C1377id.exit).setOnClickListener(new View$OnClickListenerC13191());
        this.f8593s.findViewById(C1375R.C1377id.next).setOnClickListener(new View$OnClickListenerC13202());
    }

    /* compiled from: UiShowLayout.java */
    /* renamed from: com.cyjh.mobileanjian.ipc.ui.k$1 */
    /* loaded from: classes.dex */
    final class View$OnClickListenerC13191 implements View.OnClickListener {
        View$OnClickListenerC13191() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            if (UiShowLayout.this.f8578a != null) {
                UiShowLayout.this.f8578a.onClick(view);
            }
        }
    }

    /* compiled from: UiShowLayout.java */
    /* renamed from: com.cyjh.mobileanjian.ipc.ui.k$2 */
    /* loaded from: classes.dex */
    final class View$OnClickListenerC13202 implements View.OnClickListener {
        View$OnClickListenerC13202() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            if (UiShowLayout.this.f8579b != null) {
                UiShowLayout.this.f8579b.onClick(view);
            }
        }
    }

    /* renamed from: g */
    private void m20724g() {
        this.f8581g = (WindowManager) this.f8584j.getSystemService("window");
        this.f8582h = new WindowManager.LayoutParams();
        if (Build.VERSION.SDK_INT >= 26) {
            this.f8582h.type = 2038;
        } else if (Build.VERSION.SDK_INT < 19 || Build.VERSION.SDK_INT >= 24) {
            this.f8582h.type = ActionCode.CtrlConnectRefuse_2002;
        } else {
            this.f8582h.type = 2005;
        }
        WindowManager.LayoutParams layoutParams = this.f8582h;
        layoutParams.format = 1;
        layoutParams.gravity = 16;
        layoutParams.x = 0;
        layoutParams.y = 0;
        layoutParams.alpha = 1.0f;
        layoutParams.width = -1;
        layoutParams.height = -1;
    }

    /* renamed from: b */
    public final void m20736b() {
        this.f8596w.sendEmptyMessageDelayed(256, 10L);
    }

    /* renamed from: c */
    public final void m20731c() {
        this.f8580c = true;
        this.f8581g.addView(this.f8593s, this.f8582h);
    }

    /* renamed from: d */
    public final boolean m20727d() {
        return this.f8580c;
    }

    /* renamed from: e */
    public final void m20726e() {
        this.f8580c = false;
        this.f8581g.removeView(this.f8593s);
    }

    /* renamed from: c */
    private int m20730c(int i) {
        return (int) (i * (this.f8584j.getResources().getDisplayMetrics().densityDpi / 160.0f));
    }

    /* renamed from: a */
    public final void m20741a(View view, String str) {
        new StringBuilder("row tag: ").append(this.f8589o.get(str));
        if (str.equals(this.f8586l)) {
            this.f8592r.addView(view);
        } else if (this.f8587m.contains(str)) {
            ((UiShowPagerAdapter) ((SlidingTabLayout) this.f8585k.findViewWithTag(this.f8590p.get(str))).getViewPager().getAdapter()).m20721a(view, str, this.f8589o.get(str));
        }
    }

    /* renamed from: a */
    public final void m20738a(String str, int i) {
        this.f8588n.add(str);
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(this.f8584j).inflate(C1375R.layout.ui_show_sliding_layout, (ViewGroup) null);
        final SlidingTabLayout slidingTabLayout = (SlidingTabLayout) linearLayout.findViewById(C1375R.C1377id.slidingTabLayout);
        slidingTabLayout.setTag(str);
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setSelectedIndicatorColors(this.f8584j.getResources().getColor(C1375R.color.ui_show_blue));
        slidingTabLayout.setBackgroundResource(C1375R.C1376drawable.bg_tab_host);
        slidingTabLayout.setOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.cyjh.mobileanjian.ipc.ui.k.4
            @Override // android.support.v4.view.ViewPager.OnPageChangeListener
            public final void onPageScrollStateChanged(int i2) {
            }

            @Override // android.support.v4.view.ViewPager.OnPageChangeListener
            public final void onPageScrolled(int i2, float f, int i3) {
            }

            @Override // android.support.v4.view.ViewPager.OnPageChangeListener
            public final void onPageSelected(int i2) {
                slidingTabLayout.setFocusable(true);
                slidingTabLayout.setFocusableInTouchMode(true);
                slidingTabLayout.getViewPager().requestFocus();
            }
        });
        if (i == -2) {
            i = m20730c(200);
        }
        ViewPager viewPager = (ViewPager) linearLayout.findViewById(C1375R.C1377id.viewPager);
        viewPager.setLayoutParams(new LinearLayout.LayoutParams(-1, i));
        viewPager.setAdapter(new UiShowPagerAdapter(new ArrayList(), new ArrayList()));
        slidingTabLayout.setViewPager(viewPager);
        this.f8585k.addView(linearLayout);
        this.f8595u.add(slidingTabLayout);
    }

    /* renamed from: h */
    private static String m20723h() {
        StringBuilder sb = new StringBuilder("first_row_");
        int i = f8577x;
        f8577x = i + 1;
        sb.append(i);
        return sb.toString();
    }

    /* renamed from: a */
    public final boolean m20744a(int i, String str, String str2, String str3) {
        if (!this.f8588n.contains(str2)) {
            return false;
        }
        StringBuilder sb = new StringBuilder("first_row_");
        int i2 = f8577x;
        f8577x = i2 + 1;
        sb.append(i2);
        String sb2 = sb.toString();
        this.f8587m.add(str);
        this.f8589o.put(str, sb2);
        this.f8590p.put(str, str2);
        SlidingTabLayout slidingTabLayout = (SlidingTabLayout) this.f8585k.findViewWithTag(str2);
        LinearLayout linearLayout = (LinearLayout) this.f8591q.f8496a.inflate(C1375R.layout.ui_show_tab_layout, (ViewGroup) null);
        linearLayout.setId(i);
        linearLayout.setTag(str);
        linearLayout.setId(i);
        this.f8592r = this.f8591q.m20867c(sb2, -1, -2);
        linearLayout.addView(this.f8592r);
        UiShowPagerAdapter lVar = (UiShowPagerAdapter) slidingTabLayout.f8431a.getAdapter();
        lVar.f8602a.add(linearLayout);
        lVar.f8603b.add(str3);
        lVar.notifyDataSetChanged();
        slidingTabLayout.f8431a.setAdapter(lVar);
        slidingTabLayout.setViewPager(slidingTabLayout.f8431a);
        return true;
    }

    /* renamed from: a */
    public final void m20742a(View.OnClickListener onClickListener) {
        this.f8578a = onClickListener;
    }

    /* renamed from: b */
    public final void m20734b(View.OnClickListener onClickListener) {
        this.f8579b = onClickListener;
    }

    /* renamed from: a */
    public final void m20737a(String str, String str2, int i, int i2) {
        if (str2.equals(this.f8586l)) {
            this.f8592r = this.f8591q.m20867c(f8575f, i, i2);
            this.f8585k.addView(this.f8592r);
            this.f8589o.put(str2, str);
        } else if (this.f8587m.contains(str2)) {
            for (LinearLayout linearLayout : ((UiShowPagerAdapter) ((SlidingTabLayout) this.f8585k.findViewWithTag(this.f8590p.get(str2))).getViewPager().getAdapter()).f8602a) {
                if (linearLayout.getTag().equals(str2)) {
                    linearLayout.addView(UiFactory.m20882a(linearLayout.getContext()).m20867c(str, i, i2));
                }
            }
            this.f8589o.put(str2, str);
        }
    }
}
