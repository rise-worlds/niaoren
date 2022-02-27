package com.youth.banner;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.youth.banner.view.BannerViewPager;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import p110z1.ImageLoaderInterface;
import p110z1.OnBannerClickListener;
import p110z1.OnBannerListener;

/* loaded from: classes2.dex */
public class Banner extends FrameLayout implements ViewPager.OnPageChangeListener {

    /* renamed from: A */
    private List<ImageView> f14493A;

    /* renamed from: B */
    private Context f14494B;

    /* renamed from: C */
    private BannerViewPager f14495C;

    /* renamed from: D */
    private TextView f14496D;

    /* renamed from: E */
    private TextView f14497E;

    /* renamed from: F */
    private TextView f14498F;

    /* renamed from: G */
    private LinearLayout f14499G;

    /* renamed from: H */
    private LinearLayout f14500H;

    /* renamed from: I */
    private LinearLayout f14501I;

    /* renamed from: J */
    private ImageView f14502J;

    /* renamed from: K */
    private ImageLoaderInterface f14503K;

    /* renamed from: L */
    private C3044a f14504L;

    /* renamed from: M */
    private ViewPager.OnPageChangeListener f14505M;

    /* renamed from: N */
    private BannerScroller f14506N;

    /* renamed from: O */
    private OnBannerClickListener f14507O;

    /* renamed from: P */
    private OnBannerListener f14508P;

    /* renamed from: Q */
    private DisplayMetrics f14509Q;

    /* renamed from: R */
    private WeakHandler f14510R;

    /* renamed from: S */
    private final Runnable f14511S;

    /* renamed from: a */
    public String f14512a;

    /* renamed from: b */
    private int f14513b;

    /* renamed from: c */
    private int f14514c;

    /* renamed from: d */
    private int f14515d;

    /* renamed from: e */
    private int f14516e;

    /* renamed from: f */
    private int f14517f;

    /* renamed from: g */
    private int f14518g;

    /* renamed from: h */
    private int f14519h;

    /* renamed from: i */
    private int f14520i;

    /* renamed from: j */
    private boolean f14521j;

    /* renamed from: k */
    private boolean f14522k;

    /* renamed from: l */
    private int f14523l;

    /* renamed from: m */
    private int f14524m;

    /* renamed from: n */
    private int f14525n;

    /* renamed from: o */
    private int f14526o;

    /* renamed from: p */
    private int f14527p;

    /* renamed from: q */
    private int f14528q;

    /* renamed from: r */
    private int f14529r;

    /* renamed from: s */
    private int f14530s;

    /* renamed from: t */
    private int f14531t;

    /* renamed from: u */
    private int f14532u;

    /* renamed from: v */
    private int f14533v;

    /* renamed from: w */
    private int f14534w;

    /* renamed from: x */
    private List<String> f14535x;

    /* renamed from: y */
    private List f14536y;

    /* renamed from: z */
    private List<View> f14537z;

    public Banner(Context context) {
        this(context, null);
    }

    public Banner(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public Banner(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f14512a = "banner";
        this.f14513b = 5;
        this.f14518g = 1;
        this.f14519h = 2000;
        this.f14520i = 800;
        this.f14521j = true;
        this.f14522k = true;
        this.f14523l = C3047R.C3048drawable.gray_radius;
        this.f14524m = C3047R.C3048drawable.white_radius;
        this.f14525n = C3047R.layout.banner;
        this.f14530s = 0;
        this.f14532u = -1;
        this.f14533v = 1;
        this.f14534w = 1;
        this.f14510R = new WeakHandler();
        this.f14511S = new Runnable() { // from class: com.youth.banner.Banner.1
            @Override // java.lang.Runnable
            public void run() {
                if (Banner.this.f14530s > 1 && Banner.this.f14521j) {
                    Banner banner = Banner.this;
                    banner.f14531t = (banner.f14531t % (Banner.this.f14530s + 1)) + 1;
                    if (Banner.this.f14531t == 1) {
                        Banner.this.f14495C.setCurrentItem(Banner.this.f14531t, false);
                        Banner.this.f14510R.m14918a(Banner.this.f14511S);
                        return;
                    }
                    Banner.this.f14495C.setCurrentItem(Banner.this.f14531t);
                    Banner.this.f14510R.m14908b(Banner.this.f14511S, Banner.this.f14519h);
                }
            }
        };
        this.f14494B = context;
        this.f14535x = new ArrayList();
        this.f14536y = new ArrayList();
        this.f14537z = new ArrayList();
        this.f14493A = new ArrayList();
        this.f14509Q = context.getResources().getDisplayMetrics();
        this.f14516e = this.f14509Q.widthPixels / 80;
        m14964a(context, attributeSet);
    }

    /* renamed from: a */
    private void m14964a(Context context, AttributeSet attributeSet) {
        this.f14537z.clear();
        m14951b(context, attributeSet);
        View inflate = LayoutInflater.from(context).inflate(this.f14525n, (ViewGroup) this, true);
        this.f14502J = (ImageView) inflate.findViewById(C3047R.C3049id.bannerDefaultImage);
        this.f14495C = (BannerViewPager) inflate.findViewById(C3047R.C3049id.bannerViewPager);
        this.f14501I = (LinearLayout) inflate.findViewById(C3047R.C3049id.titleView);
        this.f14499G = (LinearLayout) inflate.findViewById(C3047R.C3049id.circleIndicator);
        this.f14500H = (LinearLayout) inflate.findViewById(C3047R.C3049id.indicatorInside);
        this.f14496D = (TextView) inflate.findViewById(C3047R.C3049id.bannerTitle);
        this.f14498F = (TextView) inflate.findViewById(C3047R.C3049id.numIndicator);
        this.f14497E = (TextView) inflate.findViewById(C3047R.C3049id.numIndicatorInside);
        this.f14502J.setImageResource(this.f14517f);
        m14940e();
    }

    /* renamed from: b */
    private void m14951b(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C3047R.styleable.Banner);
            this.f14514c = obtainStyledAttributes.getDimensionPixelSize(C3047R.styleable.Banner_indicator_width, this.f14516e);
            this.f14515d = obtainStyledAttributes.getDimensionPixelSize(C3047R.styleable.Banner_indicator_height, this.f14516e);
            this.f14513b = obtainStyledAttributes.getDimensionPixelSize(C3047R.styleable.Banner_indicator_margin, 5);
            this.f14523l = obtainStyledAttributes.getResourceId(C3047R.styleable.Banner_indicator_drawable_selected, C3047R.C3048drawable.gray_radius);
            this.f14524m = obtainStyledAttributes.getResourceId(C3047R.styleable.Banner_indicator_drawable_unselected, C3047R.C3048drawable.white_radius);
            this.f14534w = obtainStyledAttributes.getInt(C3047R.styleable.Banner_image_scale_type, this.f14534w);
            this.f14519h = obtainStyledAttributes.getInt(C3047R.styleable.Banner_delay_time, 2000);
            this.f14520i = obtainStyledAttributes.getInt(C3047R.styleable.Banner_scroll_time, 800);
            this.f14521j = obtainStyledAttributes.getBoolean(C3047R.styleable.Banner_is_auto_play, true);
            this.f14527p = obtainStyledAttributes.getColor(C3047R.styleable.Banner_title_background, -1);
            this.f14526o = obtainStyledAttributes.getDimensionPixelSize(C3047R.styleable.Banner_title_height, -1);
            this.f14528q = obtainStyledAttributes.getColor(C3047R.styleable.Banner_title_textcolor, -1);
            this.f14529r = obtainStyledAttributes.getDimensionPixelSize(C3047R.styleable.Banner_title_textsize, -1);
            this.f14525n = obtainStyledAttributes.getResourceId(C3047R.styleable.Banner_banner_layout, this.f14525n);
            this.f14517f = obtainStyledAttributes.getResourceId(C3047R.styleable.Banner_banner_default_image, C3047R.C3048drawable.no_banner);
            obtainStyledAttributes.recycle();
        }
    }

    /* renamed from: e */
    private void m14940e() {
        try {
            Field declaredField = ViewPager.class.getDeclaredField("mScroller");
            declaredField.setAccessible(true);
            this.f14506N = new BannerScroller(this.f14495C.getContext());
            this.f14506N.m14926a(this.f14520i);
            declaredField.set(this.f14495C, this.f14506N);
        } catch (Exception e) {
            Log.e(this.f14512a, e.getMessage());
        }
    }

    /* renamed from: a */
    public Banner m14955a(boolean z) {
        this.f14521j = z;
        return this;
    }

    /* renamed from: a */
    public Banner m14956a(ImageLoaderInterface arjVar) {
        this.f14503K = arjVar;
        return this;
    }

    /* renamed from: a */
    public Banner m14965a(int i) {
        this.f14519h = i;
        return this;
    }

    /* renamed from: b */
    public Banner m14952b(int i) {
        switch (i) {
            case 5:
                this.f14532u = 19;
                break;
            case 6:
                this.f14532u = 17;
                break;
            case 7:
                this.f14532u = 21;
                break;
        }
        return this;
    }

    /* renamed from: a */
    public Banner m14961a(Class<? extends ViewPager.PageTransformer> cls) {
        try {
            m14954a(true, (ViewPager.PageTransformer) cls.newInstance());
        } catch (Exception unused) {
            Log.e(this.f14512a, "Please set the PageTransformer class");
        }
        return this;
    }

    /* renamed from: c */
    public Banner m14946c(int i) {
        BannerViewPager bannerViewPager = this.f14495C;
        if (bannerViewPager != null) {
            bannerViewPager.setOffscreenPageLimit(i);
        }
        return this;
    }

    /* renamed from: a */
    public Banner m14954a(boolean z, ViewPager.PageTransformer pageTransformer) {
        this.f14495C.setPageTransformer(z, pageTransformer);
        return this;
    }

    /* renamed from: a */
    public Banner m14960a(List<String> list) {
        this.f14535x = list;
        return this;
    }

    /* renamed from: d */
    public Banner m14942d(int i) {
        this.f14518g = i;
        return this;
    }

    /* renamed from: b */
    public Banner m14948b(boolean z) {
        this.f14522k = z;
        return this;
    }

    /* renamed from: b */
    public Banner m14949b(List<?> list) {
        this.f14536y = list;
        this.f14530s = list.size();
        return this;
    }

    /* renamed from: a */
    public void m14959a(List<?> list, List<String> list2) {
        this.f14535x.clear();
        this.f14535x.addAll(list2);
        m14944c(list);
    }

    /* renamed from: c */
    public void m14944c(List<?> list) {
        this.f14536y.clear();
        this.f14537z.clear();
        this.f14493A.clear();
        this.f14536y.addAll(list);
        this.f14530s = this.f14536y.size();
        m14966a();
    }

    /* renamed from: e */
    public void m14939e(int i) {
        this.f14499G.setVisibility(8);
        this.f14498F.setVisibility(8);
        this.f14497E.setVisibility(8);
        this.f14500H.setVisibility(8);
        this.f14496D.setVisibility(8);
        this.f14501I.setVisibility(8);
        this.f14518g = i;
        m14966a();
    }

    /* renamed from: a */
    public Banner m14966a() {
        m14934g();
        setImageList(this.f14536y);
        m14928j();
        return this;
    }

    /* renamed from: f */
    private void m14937f() {
        if (this.f14535x.size() == this.f14536y.size()) {
            int i = this.f14527p;
            if (i != -1) {
                this.f14501I.setBackgroundColor(i);
            }
            int i2 = this.f14526o;
            if (i2 != -1) {
                this.f14501I.setLayoutParams(new RelativeLayout.LayoutParams(-1, i2));
            }
            int i3 = this.f14528q;
            if (i3 != -1) {
                this.f14496D.setTextColor(i3);
            }
            int i4 = this.f14529r;
            if (i4 != -1) {
                this.f14496D.setTextSize(0, i4);
            }
            List<String> list = this.f14535x;
            if (list != null && list.size() > 0) {
                this.f14496D.setText(this.f14535x.get(0));
                this.f14496D.setVisibility(0);
                this.f14501I.setVisibility(0);
                return;
            }
            return;
        }
        throw new RuntimeException("[Banner] --> The number of titles and images is different");
    }

    /* renamed from: g */
    private void m14934g() {
        int i = this.f14530s > 1 ? 0 : 8;
        switch (this.f14518g) {
            case 1:
                this.f14499G.setVisibility(i);
                return;
            case 2:
                this.f14498F.setVisibility(i);
                return;
            case 3:
                this.f14497E.setVisibility(i);
                m14937f();
                return;
            case 4:
                this.f14499G.setVisibility(i);
                m14937f();
                return;
            case 5:
                this.f14500H.setVisibility(i);
                m14937f();
                return;
            default:
                return;
        }
    }

    /* renamed from: h */
    private void m14932h() {
        this.f14537z.clear();
        int i = this.f14518g;
        if (i == 1 || i == 4 || i == 5) {
            m14930i();
        } else if (i == 3) {
            TextView textView = this.f14497E;
            textView.setText("1/" + this.f14530s);
        } else if (i == 2) {
            TextView textView2 = this.f14498F;
            textView2.setText("1/" + this.f14530s);
        }
    }

    private void setImageList(List<?> list) {
        Object obj;
        if (list == null || list.size() <= 0) {
            this.f14502J.setVisibility(0);
            Log.e(this.f14512a, "The image data set is empty.");
            return;
        }
        this.f14502J.setVisibility(8);
        m14932h();
        for (int i = 0; i <= this.f14530s + 1; i++) {
            View view = null;
            ImageLoaderInterface arjVar = this.f14503K;
            if (arjVar != null) {
                view = arjVar.createImageView(this.f14494B);
            }
            if (view == null) {
                view = new ImageView(this.f14494B);
            }
            setScaleType(view);
            if (i == 0) {
                obj = list.get(this.f14530s - 1);
            } else if (i == this.f14530s + 1) {
                obj = list.get(0);
            } else {
                obj = list.get(i - 1);
            }
            this.f14537z.add(view);
            ImageLoaderInterface arjVar2 = this.f14503K;
            if (arjVar2 != null) {
                arjVar2.displayImage(this.f14494B, obj, view);
            } else {
                Log.e(this.f14512a, "Please set images loader.");
            }
        }
    }

    private void setScaleType(View view) {
        if (view instanceof ImageView) {
            ImageView imageView = (ImageView) view;
            switch (this.f14534w) {
                case 0:
                    imageView.setScaleType(ImageView.ScaleType.CENTER);
                    return;
                case 1:
                    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    return;
                case 2:
                    imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                    return;
                case 3:
                    imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    return;
                case 4:
                    imageView.setScaleType(ImageView.ScaleType.FIT_END);
                    return;
                case 5:
                    imageView.setScaleType(ImageView.ScaleType.FIT_START);
                    return;
                case 6:
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    return;
                case 7:
                    imageView.setScaleType(ImageView.ScaleType.MATRIX);
                    return;
                default:
                    return;
            }
        }
    }

    /* renamed from: i */
    private void m14930i() {
        this.f14493A.clear();
        this.f14499G.removeAllViews();
        this.f14500H.removeAllViews();
        for (int i = 0; i < this.f14530s; i++) {
            ImageView imageView = new ImageView(this.f14494B);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(this.f14514c, this.f14515d);
            int i2 = this.f14513b;
            layoutParams.leftMargin = i2;
            layoutParams.rightMargin = i2;
            if (i == 0) {
                imageView.setImageResource(this.f14523l);
            } else {
                imageView.setImageResource(this.f14524m);
            }
            this.f14493A.add(imageView);
            int i3 = this.f14518g;
            if (i3 == 1 || i3 == 4) {
                this.f14499G.addView(imageView, layoutParams);
            } else if (i3 == 5) {
                this.f14500H.addView(imageView, layoutParams);
            }
        }
    }

    /* renamed from: j */
    private void m14928j() {
        this.f14531t = 1;
        if (this.f14504L == null) {
            this.f14504L = new C3044a();
            this.f14495C.addOnPageChangeListener(this);
        }
        this.f14495C.setAdapter(this.f14504L);
        this.f14495C.setFocusable(true);
        this.f14495C.setCurrentItem(1);
        int i = this.f14532u;
        if (i != -1) {
            this.f14499G.setGravity(i);
        }
        if (!this.f14522k || this.f14530s <= 1) {
            this.f14495C.setScrollable(false);
        } else {
            this.f14495C.setScrollable(true);
        }
        if (this.f14521j) {
            m14953b();
        }
    }

    /* renamed from: b */
    public void m14953b() {
        this.f14510R.m14906c(this.f14511S);
        this.f14510R.m14908b(this.f14511S, this.f14519h);
    }

    /* renamed from: c */
    public void m14947c() {
        this.f14510R.m14906c(this.f14511S);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.f14521j) {
            int action = motionEvent.getAction();
            if (action == 1 || action == 3 || action == 4) {
                m14953b();
            } else if (action == 0) {
                m14947c();
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    /* renamed from: f */
    public int m14936f(int i) {
        int i2 = this.f14530s;
        int i3 = (i - 1) % i2;
        return i3 < 0 ? i3 + i2 : i3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.youth.banner.Banner$a */
    /* loaded from: classes2.dex */
    public class C3044a extends PagerAdapter {
        @Override // android.support.v4.view.PagerAdapter
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        C3044a() {
        }

        @Override // android.support.v4.view.PagerAdapter
        public int getCount() {
            return Banner.this.f14537z.size();
        }

        @Override // android.support.v4.view.PagerAdapter
        public Object instantiateItem(ViewGroup viewGroup, final int i) {
            viewGroup.addView((View) Banner.this.f14537z.get(i));
            View view = (View) Banner.this.f14537z.get(i);
            if (Banner.this.f14507O != null) {
                view.setOnClickListener(new View.OnClickListener() { // from class: com.youth.banner.Banner.a.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        Log.e(Banner.this.f14512a, "你正在使用旧版点击事件接口，下标是从1开始，为了体验请更换为setOnBannerListener，下标从0开始计算");
                        Banner.this.f14507O.m11420a(i);
                    }
                });
            }
            if (Banner.this.f14508P != null) {
                view.setOnClickListener(new View.OnClickListener() { // from class: com.youth.banner.Banner.a.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        Banner.this.f14508P.mo11419a(Banner.this.m14936f(i));
                    }
                });
            }
            return view;
        }

        @Override // android.support.v4.view.PagerAdapter
        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            viewGroup.removeView((View) obj);
        }
    }

    @Override // android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i) {
        ViewPager.OnPageChangeListener onPageChangeListener = this.f14505M;
        if (onPageChangeListener != null) {
            onPageChangeListener.onPageScrollStateChanged(i);
        }
        switch (i) {
            case 0:
                int i2 = this.f14531t;
                if (i2 == 0) {
                    this.f14495C.setCurrentItem(this.f14530s, false);
                    return;
                } else if (i2 == this.f14530s + 1) {
                    this.f14495C.setCurrentItem(1, false);
                    return;
                } else {
                    return;
                }
            case 1:
                int i3 = this.f14531t;
                int i4 = this.f14530s;
                if (i3 == i4 + 1) {
                    this.f14495C.setCurrentItem(1, false);
                    return;
                } else if (i3 == 0) {
                    this.f14495C.setCurrentItem(i4, false);
                    return;
                } else {
                    return;
                }
            default:
                return;
        }
    }

    @Override // android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i, float f, int i2) {
        ViewPager.OnPageChangeListener onPageChangeListener = this.f14505M;
        if (onPageChangeListener != null) {
            onPageChangeListener.onPageScrolled(m14936f(i), f, i2);
        }
    }

    @Override // android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
        this.f14531t = i;
        ViewPager.OnPageChangeListener onPageChangeListener = this.f14505M;
        if (onPageChangeListener != null) {
            onPageChangeListener.onPageSelected(m14936f(i));
        }
        int i2 = this.f14518g;
        if (i2 == 1 || i2 == 4 || i2 == 5) {
            List<ImageView> list = this.f14493A;
            int i3 = this.f14530s;
            list.get(((this.f14533v - 1) + i3) % i3).setImageResource(this.f14524m);
            List<ImageView> list2 = this.f14493A;
            int i4 = this.f14530s;
            list2.get(((i - 1) + i4) % i4).setImageResource(this.f14523l);
            this.f14533v = i;
        }
        if (i == 0) {
            i = this.f14530s;
        }
        if (i > this.f14530s) {
            i = 1;
        }
        switch (this.f14518g) {
            case 1:
            default:
                return;
            case 2:
                TextView textView = this.f14498F;
                textView.setText(i + "/" + this.f14530s);
                return;
            case 3:
                TextView textView2 = this.f14497E;
                textView2.setText(i + "/" + this.f14530s);
                this.f14496D.setText(this.f14535x.get(i - 1));
                return;
            case 4:
                this.f14496D.setText(this.f14535x.get(i - 1));
                return;
            case 5:
                this.f14496D.setText(this.f14535x.get(i - 1));
                return;
        }
    }

    @Deprecated
    /* renamed from: a */
    public Banner m14958a(OnBannerClickListener argVar) {
        this.f14507O = argVar;
        return this;
    }

    /* renamed from: a */
    public Banner m14957a(OnBannerListener arhVar) {
        this.f14508P = arhVar;
        return this;
    }

    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.f14505M = onPageChangeListener;
    }

    /* renamed from: d */
    public void m14943d() {
        this.f14510R.m14919a((Object) null);
    }
}
