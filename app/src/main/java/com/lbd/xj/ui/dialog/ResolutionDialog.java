package com.lbd.xj.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import com.blankj.utilcode.util.SPUtils;
import com.lbd.xj.C1467R;
import com.lbd.xj.app.XJApp;
import com.tencent.smtt.sdk.TbsMediaPlayer;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.lbd.xj.ui.dialog.d */
/* loaded from: classes.dex */
public class ResolutionDialog extends Dialog implements View.OnClickListener {

    /* renamed from: a */
    private ImageView f9737a;

    /* renamed from: b */
    private EditText f9738b;

    /* renamed from: c */
    private EditText f9739c;

    /* renamed from: d */
    private EditText f9740d;

    /* renamed from: e */
    private TextView f9741e;

    /* renamed from: f */
    private RadioButton f9742f;

    /* renamed from: g */
    private RadioButton f9743g;

    /* renamed from: h */
    private RadioButton f9744h;

    /* renamed from: i */
    private RadioButton f9745i;

    /* renamed from: j */
    private EnumC1605a f9746j;

    /* renamed from: m */
    private AbstractC1606b f9749m;

    /* renamed from: n */
    private int f9750n;

    /* renamed from: o */
    private Context f9751o;

    /* renamed from: k */
    private int f9747k = 1;

    /* renamed from: l */
    private List<RadioButton> f9748l = new ArrayList();

    /* renamed from: p */
    private int f9752p = 4;

    /* compiled from: ResolutionDialog.java */
    /* renamed from: com.lbd.xj.ui.dialog.d$b */
    /* loaded from: classes.dex */
    public interface AbstractC1606b {
        /* renamed from: a */
        void mo19443a(int i, int i2, int i3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ResolutionDialog.java */
    /* renamed from: com.lbd.xj.ui.dialog.d$a */
    /* loaded from: classes.dex */
    public enum EnumC1605a {
        r1080x1920(1080, 1920, 480),
        r720x1080(720, 1280, 320),
        r900x1600(TbsMediaPlayer.TbsMediaPlayerListener.MEDIA_INFO_TIMED_TEXT_ERROR, 1600, 320),
        CUSTOM(0, 0, 0);
        
        public int dpi;
        public int height;
        public int width;

        EnumC1605a(int i, int i2, int i3) {
            this.width = i;
            this.height = i2;
            this.dpi = i3;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.width + "X" + this.height;
        }
    }

    public ResolutionDialog(@NonNull Context context) {
        super(context, C1467R.style.XJ_DialogTheme);
        this.f9751o = context;
        m19446b();
    }

    /* renamed from: a */
    public void m19447a(AbstractC1606b bVar) {
        this.f9749m = bVar;
    }

    /* renamed from: b */
    private void m19446b() {
        setContentView(C1467R.layout.dialog_custom_layout);
        Window window = getWindow();
        getWindow().setLayout(-1, -1);
        window.setGravity(80);
        window.setLayout(-1, -2);
        this.f9742f = (RadioButton) findViewById(C1467R.C1469id.rl_resolution_1);
        this.f9743g = (RadioButton) findViewById(C1467R.C1469id.rl_resolution_2);
        this.f9744h = (RadioButton) findViewById(C1467R.C1469id.rl_resolution_3);
        this.f9745i = (RadioButton) findViewById(C1467R.C1469id.rl_resolution_custom);
        this.f9741e = (TextView) findViewById(C1467R.C1469id.submit_btn);
        this.f9737a = (ImageView) findViewById(C1467R.C1469id.close_img);
        this.f9738b = (EditText) findViewById(C1467R.C1469id.Custom_wight);
        this.f9739c = (EditText) findViewById(C1467R.C1469id.Custom_height);
        this.f9740d = (EditText) findViewById(C1467R.C1469id.Custom_dip);
        this.f9748l.add(this.f9742f);
        this.f9748l.add(this.f9743g);
        this.f9748l.add(this.f9744h);
        this.f9748l.add(this.f9745i);
        m19444c();
        m19445b(0);
        this.f9750n = SPUtils.m23341a().m23315c("last_click", 4);
        m19448a(this.f9750n);
        int i = this.f9750n;
        if (i == 4) {
            this.f9748l.get(1).setChecked(true);
        } else if (i == 3) {
            String b = SPUtils.m23341a().m23324b("customwidth");
            String b2 = SPUtils.m23341a().m23324b("customheight");
            String b3 = SPUtils.m23341a().m23324b("customdpi");
            this.f9738b.setText(b);
            this.f9739c.setText(b2);
            this.f9740d.setText(b3);
            this.f9748l.get(this.f9750n).setChecked(true);
        } else {
            this.f9748l.get(i).setChecked(true);
        }
    }

    /* renamed from: c */
    private void m19444c() {
        this.f9742f.setOnClickListener(this);
        this.f9743g.setOnClickListener(this);
        this.f9744h.setOnClickListener(this);
        this.f9745i.setOnClickListener(this);
        this.f9741e.setOnClickListener(this);
        this.f9737a.setOnClickListener(this);
        findViewById(C1467R.C1469id.rl_1080).setOnClickListener(this);
        findViewById(C1467R.C1469id.rl_720).setOnClickListener(this);
        findViewById(C1467R.C1469id.rl_custom).setOnClickListener(this);
    }

    /* renamed from: a */
    private void m19448a(int i) {
        switch (i) {
            case 0:
            case 4:
                this.f9746j = EnumC1605a.r1080x1920;
                this.f9747k = 1;
                return;
            case 1:
                this.f9746j = EnumC1605a.r720x1080;
                this.f9747k = 2;
                return;
            case 2:
                this.f9746j = EnumC1605a.r900x1600;
                return;
            case 3:
                this.f9746j = EnumC1605a.CUSTOM;
                this.f9747k = 1;
                return;
            default:
                return;
        }
    }

    /* renamed from: b */
    private void m19445b(int i) {
        for (RadioButton radioButton : this.f9748l) {
            if (radioButton.getId() == i) {
                radioButton.setChecked(true);
            } else {
                radioButton.setChecked(false);
            }
        }
    }

    /* renamed from: a */
    public void m19449a() {
        if (this.f9745i.isChecked()) {
            String trim = this.f9738b.getText().toString().trim();
            String trim2 = this.f9739c.getText().toString().trim();
            String trim3 = this.f9740d.getText().toString().trim();
            if (TextUtils.isEmpty(trim)) {
                trim = "1080";
            }
            if (TextUtils.isEmpty(trim2)) {
                trim2 = "1920";
            }
            if (TextUtils.isEmpty(trim3)) {
                trim3 = "480";
            }
            if (this.f9749m != null) {
                SPUtils.m23341a().m23332a("customwidth", trim);
                SPUtils.m23341a().m23332a("customheight", trim2);
                SPUtils.m23341a().m23332a("customdpi", trim3);
                SPUtils.m23341a().m23322b("last_click", 3);
                this.f9749m.mo19443a(Integer.valueOf(trim).intValue(), Integer.valueOf(trim2).intValue(), Integer.valueOf(trim3).intValue());
            }
        } else if (this.f9749m == null) {
        } else {
            if (this.f9750n == 0 && this.f9746j.width == 1080 && this.f9746j.height == 1920 && this.f9746j.dpi == 480) {
                Log.e("fbl", "1080");
                Toast.makeText(XJApp.getInstance().getApplicationContext(), "当前已经是该分辨率", 1).show();
            } else if (this.f9750n == 1 && this.f9746j.width == 720 && this.f9746j.height == 1280 && this.f9746j.dpi == 320) {
                Log.e("fbl", "720");
                Toast.makeText(XJApp.getInstance().getApplicationContext(), "当前已经是该分辨率", 1).show();
            } else {
                SPUtils.m23341a().m23322b("last_click", this.f9752p);
                this.f9749m.mo19443a(this.f9746j.width, this.f9746j.height, this.f9746j.dpi);
            }
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == C1467R.C1469id.rl_resolution_1 || id == C1467R.C1469id.rl_1080) {
            m19445b(this.f9742f.getId());
            this.f9746j = EnumC1605a.r1080x1920;
            this.f9752p = 0;
        } else if (id == C1467R.C1469id.rl_resolution_2 || id == C1467R.C1469id.rl_720) {
            m19445b(this.f9743g.getId());
            this.f9746j = EnumC1605a.r720x1080;
            this.f9752p = 1;
        } else if (id == C1467R.C1469id.rl_resolution_3) {
            m19445b(this.f9744h.getId());
            this.f9746j = EnumC1605a.r900x1600;
            this.f9752p = 2;
        } else if (id == C1467R.C1469id.rl_resolution_custom || id == C1467R.C1469id.rl_custom) {
            m19445b(this.f9745i.getId());
            this.f9746j = EnumC1605a.CUSTOM;
            this.f9752p = 3;
        } else if (id == C1467R.C1469id.submit_btn) {
            m19449a();
            dismiss();
        } else if (id == C1467R.C1469id.close_img) {
            dismiss();
        }
    }
}
