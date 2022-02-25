package com.tencent.smtt.sdk.p079b.p080a;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tencent.smtt.sdk.TbsConfig;
import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.p078a.MttLoader;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Map;

/* renamed from: com.tencent.smtt.sdk.b.a.a */
/* loaded from: classes2.dex */
public class BrowserListAdapter extends ArrayAdapter<BrowsingActivityInfo> implements View.OnClickListener, ListAdapter {

    /* renamed from: a */
    private ArrayList<BrowsingActivityInfo> f13095a;

    /* renamed from: c */
    private Intent f13097c;

    /* renamed from: d */
    private WeakReference<ListView> f13098d;

    /* renamed from: e */
    private WeakReference<TBSActivityPicker> f13099e;

    /* renamed from: f */
    private BrowsingActivityInfo f13100f;

    /* renamed from: g */
    private Handler f13101g;

    /* renamed from: h */
    private String[] f13102h;

    /* renamed from: j */
    private ValueCallback<String> f13104j;

    /* renamed from: k */
    private Map<String, Drawable> f13105k;

    /* renamed from: b */
    private BrowsingActivityInfo f13096b = null;

    /* renamed from: i */
    private BrowserListAdapter f13103i = this;

    /* renamed from: a */
    public BrowsingActivityInfo m16871a() {
        return this.f13096b;
    }

    /* renamed from: a */
    void m16865a(ListView listView) {
        this.f13098d = new WeakReference<>(listView);
    }

    /* renamed from: a */
    void m16862a(TBSActivityPicker cVar) {
        this.f13099e = new WeakReference<>(cVar);
    }

    public BrowserListAdapter(Context context, Intent intent, BrowsingActivityInfo bVar, BrowsingActivityInfo bVar2, TBSActivityPicker cVar, ListView listView, ValueCallback<String> valueCallback) {
        super(context, 0);
        this.f13097c = null;
        this.f13102h = null;
        m16862a(cVar);
        m16865a(listView);
        this.f13097c = intent;
        if ("com.tencent.rtxlite".equalsIgnoreCase(context.getApplicationContext().getPackageName()) || MttLoader.m16874b(context)) {
            this.f13100f = null;
        } else {
            this.f13100f = bVar;
        }
        this.f13104j = valueCallback;
        this.f13101g = new Handler() { // from class: com.tencent.smtt.sdk.b.a.a.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message.what == 1) {
                    BrowserListAdapter.this.m16860b();
                }
            }
        };
        this.f13102h = new String[2];
        this.f13102h[0] = TBSResources.m16838b("x5_tbs_activity_picker_recommend_to_trim");
        this.f13102h[1] = TBSResources.m16838b("x5_tbs_activity_picker_recommend_with_chinese_brace_to_trim");
        m16867a(context, bVar2);
    }

    /* renamed from: a */
    void m16867a(Context context, BrowsingActivityInfo bVar) {
        BrowsingActivityInfo bVar2;
        this.f13095a = new ArrayList<>();
        boolean z = false;
        boolean z2 = false;
        for (ResolveInfo resolveInfo : context.getPackageManager().queryIntentActivities(this.f13097c, 65536)) {
            if (BrowsingActivityInfo.m16855a(context, resolveInfo.activityInfo.packageName) != null || resolveInfo.loadIcon(context.getPackageManager()) != null) {
                BrowsingActivityInfo bVar3 = new BrowsingActivityInfo(context, resolveInfo);
                BrowsingActivityInfo bVar4 = this.f13100f;
                if (bVar4 != null && bVar4.m16851c().equals(resolveInfo.activityInfo.packageName)) {
                    this.f13095a.add(0, bVar3);
                    z = true;
                } else if (TbsConfig.APP_QB.equals(resolveInfo.activityInfo.packageName)) {
                    this.f13095a.add(0, bVar3);
                } else {
                    this.f13095a.add(bVar3);
                }
                if (!z2 && bVar != null && bVar3.m16851c().equals(bVar.m16851c())) {
                    m16859b(context, bVar3);
                    z2 = true;
                }
            }
        }
        if (!z && (bVar2 = this.f13100f) != null) {
            this.f13095a.add(0, bVar2);
        }
        if (!z2 && this.f13095a.size() > 0) {
            m16859b(context, this.f13095a.get(0));
        }
    }

    /* renamed from: a */
    public BrowsingActivityInfo getItem(int i) {
        if (i < 0 || i >= this.f13095a.size()) {
            return null;
        }
        return this.f13095a.get(i);
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public int getCount() {
        return this.f13095a.size();
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        BrowsingActivityInfo a = getItem(i);
        if (a == null) {
            return null;
        }
        if (view == null) {
            view = m16868a(getContext());
        }
        m16863a(a, view);
        return view;
    }

    /* renamed from: a */
    private View m16868a(Context context) {
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setLayoutParams(new AbsListView.LayoutParams(-1, -2));
        linearLayout.setOrientation(1);
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{16842919}, new ColorDrawable(Color.argb(41, 0, 0, 0)));
        stateListDrawable.addState(new int[]{-16842919}, new ColorDrawable(0));
        linearLayout.setBackgroundDrawable(stateListDrawable);
        RelativeLayout relativeLayout = new RelativeLayout(context);
        relativeLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, m16870a(64.0f)));
        ImageView imageView = new ImageView(context);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(m16870a(48.0f), m16870a(48.0f));
        layoutParams.addRule(9);
        layoutParams.addRule(15);
        int a = m16870a(8.0f);
        layoutParams.setMargins(a, a, a, a);
        imageView.setLayoutParams(layoutParams);
        imageView.setId(101);
        relativeLayout.addView(imageView);
        LinearLayout linearLayout2 = new LinearLayout(context);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(15);
        layoutParams2.addRule(1, 101);
        linearLayout2.setLayoutParams(layoutParams2);
        linearLayout2.setOrientation(1);
        TextView textView = new TextView(context);
        textView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        textView.setMaxLines(1);
        textView.setTextColor(Color.rgb(29, 29, 29));
        textView.setTextSize(1, 17.0f);
        textView.setId(102);
        linearLayout2.addView(textView);
        TextView textView2 = new TextView(context);
        textView2.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        textView2.setText(TBSResources.m16838b("x5_tbs_wechat_activity_picker_label_recommend"));
        textView2.setTextColor(Color.rgb(217, 217, 217));
        textView2.setTextSize(1, 14.0f);
        textView2.setId(103);
        linearLayout2.addView(textView2);
        relativeLayout.addView(linearLayout2);
        ImageView imageView2 = new ImageView(context);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(m16870a(24.0f), m16870a(24.0f));
        layoutParams3.addRule(11);
        layoutParams3.addRule(15);
        layoutParams3.setMargins(0, 0, m16870a(14.0f), 0);
        imageView2.setLayoutParams(layoutParams3);
        imageView2.setImageDrawable(TBSResources.m16839a("x5_tbs_activity_picker_check"));
        imageView2.setId(104);
        relativeLayout.addView(imageView2);
        Button button = new Button(context);
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-2, m16870a(31.0f));
        layoutParams4.addRule(11);
        layoutParams4.addRule(15);
        layoutParams4.setMargins(0, 0, m16870a(8.0f), 0);
        button.setLayoutParams(layoutParams4);
        button.setTextColor(Color.rgb(50, 50, 50));
        button.setTextSize(1, 14.0f);
        StateListDrawable stateListDrawable2 = new StateListDrawable();
        float a2 = m16870a(2.0f);
        RoundRectShape roundRectShape = new RoundRectShape(new float[]{a2, a2, a2, a2, a2, a2, a2, a2}, null, null);
        ShapeDrawable shapeDrawable = new ShapeDrawable(roundRectShape);
        shapeDrawable.getPaint().setColor(Color.argb(128, 69, 192, 26));
        stateListDrawable2.addState(new int[]{-16842910}, shapeDrawable);
        ShapeDrawable shapeDrawable2 = new ShapeDrawable(roundRectShape);
        shapeDrawable2.getPaint().setColor(Color.rgb(41, 132, 9));
        stateListDrawable2.addState(new int[]{16842919}, shapeDrawable2);
        ShapeDrawable shapeDrawable3 = new ShapeDrawable(roundRectShape);
        shapeDrawable3.getPaint().setColor(Color.rgb(69, 192, 26));
        stateListDrawable2.addState(new int[]{-16842919}, shapeDrawable3);
        button.setBackgroundDrawable(stateListDrawable2);
        button.setId(107);
        button.setPadding(m16870a(8.0f), 0, m16870a(8.0f), 0);
        relativeLayout.addView(button);
        relativeLayout.setId(105);
        linearLayout.addView(relativeLayout);
        return linearLayout;
    }

    /* renamed from: a */
    private int m16870a(float f) {
        TBSActivityPicker cVar = this.f13099e.get();
        return cVar == null ? (int) f : cVar.m16849a(f);
    }

    /* renamed from: b */
    void m16860b() {
        View findViewWithTag;
        if (Looper.myLooper() != Looper.getMainLooper()) {
            this.f13101g.obtainMessage(1).sendToTarget();
            return;
        }
        ListView listView = this.f13098d.get();
        if (listView != null && (findViewWithTag = listView.findViewWithTag(this.f13100f)) != null) {
            m16863a(this.f13100f, findViewWithTag);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v13, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r3v17 */
    /* JADX WARN: Type inference failed for: r3v18 */
    /* JADX WARN: Unknown variable types count: 1 */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void m16863a(com.tencent.smtt.sdk.p079b.p080a.BrowsingActivityInfo r18, android.view.View r19) {
        /*
            Method dump skipped, instructions count: 325
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.p079b.p080a.BrowserListAdapter.m16863a(com.tencent.smtt.sdk.b.a.b, android.view.View):void");
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        BrowsingActivityInfo bVar;
        Object tag = view.getTag();
        if (tag != null && (tag instanceof BrowsingActivityInfo) && (bVar = (BrowsingActivityInfo) tag) != this.f13096b) {
            ViewParent parent = view.getParent();
            View view2 = null;
            if (parent instanceof View) {
                view2 = (View) parent;
            }
            BrowsingActivityInfo bVar2 = this.f13096b;
            m16859b(view.getContext(), bVar);
            m16863a(bVar2, view2.findViewWithTag(bVar2));
            m16863a(this.f13096b, view);
        }
    }

    /* renamed from: b */
    private void m16859b(Context context, BrowsingActivityInfo bVar) {
        this.f13096b = bVar;
        m16861a(this.f13096b != null && m16866a(context, bVar.m16851c()));
    }

    /* renamed from: a */
    private void m16861a(boolean z) {
        TBSActivityPicker cVar;
        WeakReference<TBSActivityPicker> weakReference = this.f13099e;
        if (weakReference != null && (cVar = weakReference.get()) != null) {
            cVar.m16845a(z);
        }
    }

    /* renamed from: a */
    public static boolean m16866a(Context context, String str) {
        if (str == null || "".equals(str)) {
            return false;
        }
        try {
            context.getPackageManager().getApplicationInfo(str, 8192);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }
}
