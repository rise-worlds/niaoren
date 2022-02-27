package com.cyjh.mobileanjian.ipc.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.graphics.drawable.PathInterpolatorCompat;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import com.cyjh.mqsdk.C1375R;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;

/* renamed from: com.cyjh.mobileanjian.ipc.ui.g */
/* loaded from: classes.dex */
public final class UiFactory {

    /* renamed from: a */
    LayoutInflater f8496a;

    /* renamed from: b */
    private Context f8497b;

    private UiFactory(Context context) {
        this.f8497b = context;
        this.f8496a = LayoutInflater.from(this.f8497b);
    }

    /* renamed from: a */
    private int m20886a(int i) {
        return (int) (i * (this.f8497b.getResources().getDisplayMetrics().densityDpi / 160.0f));
    }

    /* renamed from: a */
    private LinearLayout.LayoutParams m20885a(int i, int i2) {
        return m20871b(i, i2);
    }

    /* renamed from: b */
    private LinearLayout.LayoutParams m20871b(int i, int i2) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        if (!(i == -2 || i2 == -2)) {
            layoutParams.width = i;
            layoutParams.height = i2;
        }
        layoutParams.setMargins(0, 0, m20886a(8), 0);
        return layoutParams;
    }

    /* renamed from: a */
    private int m20887a(float f) {
        return (int) ((this.f8497b.getResources().getDisplayMetrics().density * f) + 0.5d);
    }

    /* renamed from: b */
    private int m20872b(float f) {
        return (int) ((f / this.f8497b.getResources().getDisplayMetrics().density) + 0.5d);
    }

    /* renamed from: a */
    public static UiFactory m20882a(Context context) {
        return new UiFactory(context);
    }

    /* renamed from: a */
    private UiShowLayout m20881a(String str) {
        return m20880a(str, -1, -2);
    }

    /* renamed from: a */
    public final UiShowLayout m20880a(String str, int i, int i2) {
        return new UiShowLayout(this.f8497b, str, i, i2);
    }

    /* renamed from: b */
    public final LinearLayout m20870b(String str, int i, int i2) {
        LinearLayout linearLayout = new LinearLayout(this.f8497b);
        linearLayout.setTag(str);
        linearLayout.setOrientation(1);
        linearLayout.setBackgroundColor(-16777216);
        if (i == -1) {
            i = PathInterpolatorCompat.MAX_NUM_POINTS;
        }
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(i, i2));
        return linearLayout;
    }

    /* renamed from: c */
    public final LinearLayout m20867c(String str, int i, int i2) {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(this.f8497b).inflate(C1375R.layout.ui_show_row, (ViewGroup) null);
        linearLayout.setOrientation(0);
        linearLayout.setTag(str);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(i, i2));
        return linearLayout;
    }

    /* renamed from: a */
    private TextView m20877a(String str, String str2) {
        return m20876a(str, str2, -2, -2);
    }

    /* renamed from: e */
    private TextView m20863e(String str, String str2, int i, int i2) {
        TextView textView = (TextView) this.f8496a.inflate(C1375R.layout.ui_show_text_view, (ViewGroup) null);
        textView.setTag(str);
        textView.setText(str2);
        textView.setLayoutParams(m20871b(i, i2));
        return textView;
    }

    /* renamed from: f */
    private TextView m20862f(String str, String str2, int i, int i2) {
        TextView textView = (TextView) this.f8496a.inflate(C1375R.layout.ui_float_text_view, (ViewGroup) null);
        textView.setTag(str);
        textView.setText(str2);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        if (!(i == -2 || i2 == -2)) {
            layoutParams.width = i;
            layoutParams.height = i2;
        }
        textView.setLayoutParams(layoutParams);
        return textView;
    }

    /* renamed from: b */
    private Button m20869b(String str, String str2) {
        return m20868b(str, str2, -2, -2);
    }

    /* renamed from: g */
    private Button m20861g(String str, String str2, int i, int i2) {
        Button button = (Button) this.f8496a.inflate(C1375R.layout.ui_show_button, (ViewGroup) null);
        button.setTag(str);
        button.setText(str2);
        button.setAllCaps(false);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(i, i2);
        layoutParams.setMargins(0, 0, m20886a(8), 0);
        button.setLayoutParams(layoutParams);
        button.setBackgroundResource(C1375R.C1376drawable.bg_default_button);
        return button;
    }

    /* renamed from: h */
    private Button m20860h(String str, String str2, int i, int i2) {
        RelativeLayout.LayoutParams layoutParams;
        Button button = (Button) this.f8496a.inflate(C1375R.layout.ui_float_button, (ViewGroup) null);
        button.setTag(str);
        button.setText(str2);
        button.setAllCaps(false);
        if (i == 0 && i2 == 0) {
            layoutParams = new RelativeLayout.LayoutParams(-1, 100);
        } else {
            layoutParams = new RelativeLayout.LayoutParams(i, i2);
        }
        button.setLayoutParams(layoutParams);
        return button;
    }

    /* renamed from: c */
    public final ImageView m20865c(String str, String str2, int i, int i2) {
        Bitmap bitmap = null;
        ImageView imageView = (ImageView) this.f8496a.inflate(C1375R.layout.ui_show_image_view, (ViewGroup) null);
        imageView.setTag(str);
        try {
            bitmap = BitmapFactory.decodeStream(new FileInputStream(str2));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        }
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        if (!(i == -2 || i2 == -2)) {
            layoutParams.width = i;
            layoutParams.height = i2;
        }
        imageView.setLayoutParams(layoutParams);
        return imageView;
    }

    /* renamed from: c */
    private EditText m20866c(String str, String str2) {
        return m20864d(str, str2, -2, -2);
    }

    /* renamed from: d */
    public final EditText m20864d(String str, String str2, int i, int i2) {
        EditText editText = (EditText) this.f8496a.inflate(C1375R.layout.ui_show_edit_text, (ViewGroup) null);
        editText.setTag(str);
        editText.setText(str2);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(i, i2);
        layoutParams.setMargins(0, 0, m20886a(8), 0);
        editText.setLayoutParams(layoutParams);
        editText.setBackgroundResource(C1375R.C1376drawable.bg_edit_text);
        return editText;
    }

    /* renamed from: a */
    public final CheckBox m20875a(String str, String str2, int i, int i2, boolean z) {
        CheckBox checkBox = (CheckBox) LayoutInflater.from(this.f8497b).inflate(C1375R.layout.ui_show_checkbox, (ViewGroup) null);
        checkBox.setTag(str);
        checkBox.setText(str2);
        checkBox.setChecked(z);
        checkBox.setLayoutParams(m20871b(i, i2));
        checkBox.setPadding(0, 0, 0, 0);
        return checkBox;
    }

    /* renamed from: a */
    public final RadioGroup m20873a(String str, List<String> list, int i, int i2, int i3) {
        RadioGroup radioGroup = new RadioGroup(this.f8497b);
        radioGroup.setTag(str);
        radioGroup.setLayoutParams(m20871b(i2, i3));
        int i4 = 0;
        for (int i5 = 0; i5 < list.size(); i5++) {
            RadioButton radioButton = (RadioButton) this.f8496a.inflate(C1375R.layout.ui_show_radio_button, (ViewGroup) null);
            radioButton.setId(i5);
            radioButton.setText(list.get(i5));
            radioButton.setLayoutParams(m20871b(-2, -2));
            radioButton.setPadding(0, m20886a(5), 0, m20886a(5));
            radioGroup.addView(radioButton);
            if (i5 >= 100) {
                break;
            }
        }
        radioGroup.clearCheck();
        if (i < radioGroup.getChildCount()) {
            i4 = i;
        }
        radioGroup.check(i4);
        return radioGroup;
    }

    /* renamed from: a */
    public final WebView m20878a(String str, int i, int i2, String str2) {
        if (!str2.startsWith("http://") && !str2.startsWith("https://")) {
            str2 = "http://" + str2;
        }
        WebView webView = new WebView(this.f8497b);
        webView.setTag(str);
        webView.setLayoutParams(new LinearLayout.LayoutParams(i, i2));
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(str2);
        webView.setWebViewClient(new WebViewClient() { // from class: com.cyjh.mobileanjian.ipc.ui.g.1
            @Override // android.webkit.WebViewClient
            public final boolean shouldOverrideUrlLoading(WebView webView2, String str3) {
                webView2.loadUrl(str3);
                return true;
            }
        });
        return webView;
    }

    /* renamed from: a */
    public final ImageView m20883a(int i, String str, int i2, int i3, String str2) {
        ImageView imageView = new ImageView(this.f8497b);
        imageView.setId(i);
        imageView.setTag(str);
        imageView.setLayoutParams(m20871b(i2, i3));
        imageView.setImageURI(Uri.fromFile(new File(str2)));
        return imageView;
    }

    /* renamed from: a */
    public final Spinner m20874a(String str, List<String> list, int i) {
        Spinner spinner = (Spinner) this.f8496a.inflate(C1375R.layout.ui_show_spinner, (ViewGroup) null);
        spinner.setTag(str);
        spinner.setLayoutParams(m20871b(-2, -2));
        ArrayAdapter arrayAdapter = new ArrayAdapter(this.f8497b, C1375R.layout.ui_show_spinner_dropdown_item, list);
        spinner.setAdapter((SpinnerAdapter) arrayAdapter);
        if (i >= arrayAdapter.getCount()) {
            i = 0;
        }
        spinner.setSelection(i);
        return spinner;
    }

    /* renamed from: a */
    private LinearLayout m20884a(int i, String str) {
        LinearLayout linearLayout = (LinearLayout) this.f8496a.inflate(C1375R.layout.ui_show_tab_layout, (ViewGroup) null);
        linearLayout.setId(i);
        linearLayout.setTag(str);
        return linearLayout;
    }

    /* renamed from: a */
    public final UiShowFloat m20879a(String str, int i, int i2, int i3, int i4) {
        return new UiShowFloat(this.f8497b, str, i, i2, i3, i4);
    }

    /* renamed from: a */
    public final TextView m20876a(String str, String str2, int i, int i2) {
        TextView textView = (TextView) this.f8496a.inflate(C1375R.layout.ui_show_text_view, (ViewGroup) null);
        textView.setTag(str);
        textView.setText(str2);
        textView.setLayoutParams(m20871b(i, i2));
        return textView;
    }

    /* renamed from: b */
    public final Button m20868b(String str, String str2, int i, int i2) {
        Button button = (Button) this.f8496a.inflate(C1375R.layout.ui_show_button, (ViewGroup) null);
        button.setTag(str);
        button.setText(str2);
        button.setAllCaps(false);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(i, i2);
        layoutParams.setMargins(0, 0, m20886a(8), 0);
        button.setLayoutParams(layoutParams);
        button.setBackgroundResource(C1375R.C1376drawable.bg_default_button);
        return button;
    }
}
