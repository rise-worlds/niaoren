package com.tencent.smtt.sdk.p079b.p080a;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.cyjh.ddysdk.device.base.constants.DdyConstants;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.TbsConfig;
import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.p078a.MttLoader;
import java.lang.ref.WeakReference;
import java.util.List;

/* renamed from: com.tencent.smtt.sdk.b.a.c */
/* loaded from: classes2.dex */
public class TBSActivityPicker extends Dialog {

    /* renamed from: a */
    static WeakReference<ValueCallback<String>> f13115a = null;

    /* renamed from: l */
    private static float f13116l = -1.0f;

    /* renamed from: b */
    private ListView f13117b;

    /* renamed from: c */
    private TextView f13118c;

    /* renamed from: d */
    private Button f13119d;

    /* renamed from: e */
    private Button f13120e;

    /* renamed from: f */
    private String f13121f;

    /* renamed from: g */
    private BrowserListAdapter f13122g;

    /* renamed from: h */
    private String f13123h;

    /* renamed from: i */
    private String f13124i;

    /* renamed from: j */
    private Intent f13125j;

    /* renamed from: k */
    private SharedPreferences f13126k;

    public TBSActivityPicker(Context context, String str, Intent intent, ValueCallback<String> valueCallback, String str2, String str3) {
        super(context, 16973835);
        this.f13123h = "*/*";
        this.f13126k = null;
        this.f13124i = str3;
        List<ResolveInfo> queryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 65536);
        if ((queryIntentActivities == null || queryIntentActivities.size() == 0) && MttLoader.m16874b(context)) {
            QbSdk.isDefaultDialog = true;
        }
        if ("com.tencent.rtxlite".equalsIgnoreCase(context.getApplicationContext().getPackageName()) && (queryIntentActivities == null || (queryIntentActivities != null && queryIntentActivities.size() == 0))) {
            QbSdk.isDefaultDialog = true;
        }
        m16843b(context.getApplicationContext());
        this.f13121f = str;
        this.f13125j = intent;
        f13115a = new WeakReference<>(valueCallback);
        this.f13126k = context.getSharedPreferences("tbs_file_open_dialog_config", 0);
        if (!TextUtils.isEmpty(str2)) {
            this.f13123h = str2;
        }
    }

    /* renamed from: a */
    public void m16846a(String str) {
        SharedPreferences sharedPreferences = this.f13126k;
        if (sharedPreferences != null) {
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putString("key_tbs_picked_default_browser_" + this.f13123h, str).commit();
        }
    }

    /* renamed from: a */
    public String m16850a() {
        SharedPreferences sharedPreferences = this.f13126k;
        if (sharedPreferences == null) {
            return null;
        }
        return sharedPreferences.getString("key_tbs_picked_default_browser_" + this.f13123h, null);
    }

    /* renamed from: b */
    public void m16844b() {
        setContentView(m16848a(getContext()));
        m16841c();
        this.f13119d.setOnClickListener(new View.OnClickListener() { // from class: com.tencent.smtt.sdk.b.a.c.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                BrowsingActivityInfo a = TBSActivityPicker.this.f13122g.m16871a();
                if (a != null && a.m16852b() != null) {
                    Intent intent = TBSActivityPicker.this.f13125j;
                    Context context = TBSActivityPicker.this.getContext();
                    String str = a.m16852b().activityInfo.packageName;
                    intent.setPackage(str);
                    if (TbsConfig.APP_QB.equals(str)) {
                        intent.putExtra(QbSdk.LOGIN_TYPE_KEY_PARTNER_ID, context.getApplicationContext().getPackageName());
                        intent.putExtra(QbSdk.LOGIN_TYPE_KEY_PARTNER_CALL_POS, DdyConstants.APP_INSTALL_DOWNLOADING);
                    }
                    if (context != null && context.getApplicationInfo().targetSdkVersion >= 24 && Build.VERSION.SDK_INT >= 24) {
                        intent.addFlags(1);
                    }
                    if (!TextUtils.isEmpty(TBSActivityPicker.this.f13124i)) {
                        intent.putExtra("big_brother_source_key", TBSActivityPicker.this.f13124i);
                    }
                    context.startActivity(intent);
                    if (TBSActivityPicker.f13115a.get() != null) {
                        TBSActivityPicker.f13115a.get().onReceiveValue("always");
                    }
                    TBSActivityPicker.this.m16846a(str);
                    TBSActivityPicker.this.dismiss();
                }
            }
        });
        this.f13120e.setOnClickListener(new View.OnClickListener() { // from class: com.tencent.smtt.sdk.b.a.c.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                BrowsingActivityInfo a = TBSActivityPicker.this.f13122g.m16871a();
                if (a != null && a.m16852b() != null) {
                    Intent intent = TBSActivityPicker.this.f13125j;
                    Context context = TBSActivityPicker.this.getContext();
                    String str = a.m16852b().activityInfo.packageName;
                    intent.setPackage(str);
                    if (TbsConfig.APP_QB.equals(str)) {
                        intent.putExtra(QbSdk.LOGIN_TYPE_KEY_PARTNER_ID, context.getApplicationContext().getPackageName());
                        intent.putExtra(QbSdk.LOGIN_TYPE_KEY_PARTNER_CALL_POS, DdyConstants.APP_INSTALL_DOWNLOADING);
                    }
                    if (context != null && context.getApplicationInfo().targetSdkVersion >= 24 && Build.VERSION.SDK_INT >= 24) {
                        intent.addFlags(1);
                    }
                    if (!TextUtils.isEmpty(TBSActivityPicker.this.f13124i)) {
                        intent.putExtra("big_brother_source_key", TBSActivityPicker.this.f13124i);
                    }
                    context.startActivity(intent);
                    if (TBSActivityPicker.f13115a.get() != null) {
                        TBSActivityPicker.f13115a.get().onReceiveValue("once");
                    }
                    TBSActivityPicker.this.dismiss();
                }
            }
        });
    }

    /* renamed from: a */
    private View m16848a(Context context) {
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.dimAmount = 0.5f;
        getWindow().setAttributes(attributes);
        FrameLayout frameLayout = new FrameLayout(context);
        LinearLayout linearLayout = new LinearLayout(context);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(m16849a(280.0f), -1);
        layoutParams.gravity = 17;
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setBackgroundColor(-1);
        linearLayout.setOrientation(1);
        this.f13118c = new TextView(context);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, m16849a(65.0f));
        layoutParams2.weight = 0.0f;
        this.f13118c.setGravity(16);
        this.f13118c.setLayoutParams(layoutParams2);
        this.f13118c.setPadding(m16849a(18.0f), 0, 0, 0);
        this.f13118c.setTextColor(Color.rgb(69, 192, 26));
        this.f13118c.setTextSize(1, 18.0f);
        this.f13118c.setText(this.f13121f);
        linearLayout.addView(this.f13118c);
        ImageView imageView = new ImageView(context);
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, 1);
        layoutParams3.weight = 0.0f;
        imageView.setLayoutParams(layoutParams3);
        imageView.setBackgroundColor(Color.argb(61, 0, 0, 0));
        linearLayout.addView(imageView);
        this.f13117b = new ListView(context);
        LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(-1, -1);
        layoutParams4.weight = 1.0f;
        layoutParams4.gravity = 16;
        this.f13117b.setLayoutParams(layoutParams4);
        this.f13117b.setDivider(new ColorDrawable(Color.argb(41, 0, 0, 0)));
        this.f13117b.setDividerHeight(1);
        linearLayout.addView(this.f13117b);
        ImageView imageView2 = new ImageView(context);
        LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(-1, 1);
        layoutParams5.weight = 0.0f;
        imageView2.setLayoutParams(layoutParams5);
        imageView2.setBackgroundColor(Color.argb(61, 0, 0, 0));
        linearLayout.addView(imageView2);
        LinearLayout linearLayout2 = new LinearLayout(context);
        LinearLayout.LayoutParams layoutParams6 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams6.weight = 0.0f;
        linearLayout2.setLayoutParams(layoutParams6);
        linearLayout2.setOrientation(0);
        linearLayout2.setContentDescription("x5_tbs_activity_picker_btn_container");
        this.f13119d = new Button(context);
        LinearLayout.LayoutParams layoutParams7 = new LinearLayout.LayoutParams(-1, m16849a(49.0f));
        layoutParams7.weight = 1.0f;
        this.f13119d.setLayoutParams(layoutParams7);
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{16842919}, new ColorDrawable(Color.argb(41, 0, 0, 0)));
        stateListDrawable.addState(new int[]{-16842919}, new ColorDrawable(0));
        this.f13119d.setBackgroundDrawable(stateListDrawable);
        this.f13119d.setText(TBSResources.m16838b("x5_tbs_wechat_activity_picker_label_always"));
        this.f13119d.setTextColor(Color.rgb(29, 29, 29));
        this.f13119d.setTextSize(1, 17.0f);
        linearLayout2.addView(this.f13119d);
        ImageView imageView3 = new ImageView(context);
        LinearLayout.LayoutParams layoutParams8 = new LinearLayout.LayoutParams(1, -1);
        layoutParams8.weight = 0.0f;
        imageView3.setLayoutParams(layoutParams8);
        imageView3.setBackgroundColor(Color.rgb(217, 217, 217));
        linearLayout2.addView(imageView3);
        this.f13120e = new Button(context);
        LinearLayout.LayoutParams layoutParams9 = new LinearLayout.LayoutParams(-1, m16849a(49.0f));
        layoutParams9.weight = 1.0f;
        this.f13120e.setLayoutParams(layoutParams9);
        StateListDrawable stateListDrawable2 = new StateListDrawable();
        stateListDrawable2.addState(new int[]{16842919}, new ColorDrawable(Color.argb(41, 0, 0, 0)));
        stateListDrawable2.addState(new int[]{-16842919}, new ColorDrawable(0));
        this.f13120e.setBackgroundDrawable(stateListDrawable2);
        this.f13120e.setText(TBSResources.m16838b("x5_tbs_wechat_activity_picker_label_once"));
        this.f13120e.setTextColor(Color.rgb(29, 29, 29));
        this.f13120e.setTextSize(1, 17.0f);
        linearLayout2.addView(this.f13120e);
        linearLayout.addView(linearLayout2);
        frameLayout.addView(linearLayout);
        return frameLayout;
    }

    /* renamed from: c */
    private void m16841c() {
        BrowserListAdapter aVar = this.f13122g;
        this.f13122g = new BrowserListAdapter(getContext(), this.f13125j, new BrowsingActivityInfo(getContext(), TBSResources.m16839a("application_icon"), "QQ浏览器", TbsConfig.APP_QB), aVar != null ? aVar.m16871a() : null, this, this.f13117b, f13115a.get());
        this.f13117b.setAdapter((ListAdapter) this.f13122g);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m16845a(boolean z) {
        Button button = this.f13120e;
        if (button != null) {
            button.setEnabled(z);
        }
        Button button2 = this.f13119d;
        if (button2 != null) {
            button2.setEnabled(z);
        }
    }

    @Override // android.app.Dialog
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        m16844b();
    }

    /* renamed from: b */
    private void m16843b(Context context) {
        if (f13116l < 0.0f) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            f13116l = displayMetrics.density;
        }
    }

    /* renamed from: a */
    public int m16849a(float f) {
        return (int) ((f * f13116l) + 0.5f);
    }
}
