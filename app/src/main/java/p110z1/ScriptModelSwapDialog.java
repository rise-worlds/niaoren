package p110z1;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.angel.nrzs.App;
import com.angel.nrzs.C0692R;
import com.nrzs.core.models.AppData;
import com.nrzs.data.DataApp;
import com.nrzs.game.model.TentcentDownModel;
import com.tencent.smtt.sdk.TbsConfig;

/* renamed from: z1.ez */
/* loaded from: classes3.dex */
public class ScriptModelSwapDialog extends Dialog {

    /* renamed from: a */
    private static AppDataSource f21594a;

    /* renamed from: c */
    private static ScriptModelSwapDialog f21595c;

    /* renamed from: b */
    private Activity f21596b;

    /* renamed from: d */
    private ImageView f21597d;

    /* renamed from: e */
    private ImageView f21598e;

    /* renamed from: f */
    private RelativeLayout f21599f;

    /* renamed from: g */
    private LinearLayout f21600g;

    /* renamed from: h */
    private CheckBox f21601h;

    /* renamed from: i */
    private CheckBox f21602i;

    /* renamed from: j */
    private boolean f21603j;

    /* renamed from: k */
    private boolean f21604k;

    /* renamed from: l */
    private boolean f21605l;

    /* renamed from: m */
    private boolean f21606m;

    /* renamed from: n */
    private RelativeLayout f21607n;

    /* renamed from: o */
    private LinearLayout f21608o;

    /* renamed from: p */
    private LinearLayout f21609p;

    /* renamed from: q */
    private LinearLayout f21610q;

    public ScriptModelSwapDialog(Context context) {
        super(context, C0692R.style.f4318es);
        this.f21596b = (Activity) context;
        f21594a = new AppRepository(GameApp.getInstance().m13006b());
        m2994b();
        m2991c();
        m2987e();
    }

    /* renamed from: a */
    public static void m2997a(Context context) {
        if (f21595c == null) {
            f21595c = new ScriptModelSwapDialog(context);
        } else {
            m2998a();
            f21595c = new ScriptModelSwapDialog(context);
        }
        if (!f21595c.isShowing()) {
            f21595c.show();
        }
    }

    /* renamed from: a */
    public static void m2998a() {
        ScriptModelSwapDialog ezVar = f21595c;
        if (ezVar != null) {
            ezVar.dismiss();
            f21595c = null;
        }
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        Activity activity = this.f21596b;
        if (activity != null && !activity.isFinishing()) {
            super.dismiss();
            if (this.f21606m != this.f21604k || this.f21605l != this.f21603j) {
                if (!this.f21604k) {
                    m2983h();
                }
                if (!this.f21603j) {
                    m2984g();
                }
                if (this.f21604k || this.f21603j) {
                    TentcentDownModel.m18803a().m18770m();
                }
            }
        }
    }

    /* renamed from: g */
    private static void m2984g() {
        f21594a.mo12951a(TbsConfig.APP_WX).mo3282b(new DoneCallback<AppData>() { // from class: z1.ez.1
            /* renamed from: a */
            public void onDone(AppData aVar) {
                if (aVar == null) {
                    return;
                }
                if (ScriptModelSwapDialog.f21594a.mo12950a(TbsConfig.APP_WX, 0)) {
                    Log.i("LBS_PXKJ", "isUnistall start 1");
                } else {
                    Log.i("LBS_PXKJ", "isUnistall start 2");
                }
            }
        });
    }

    /* renamed from: h */
    private static void m2983h() {
        f21594a.mo12951a(TbsConfig.APP_QQ).mo3282b(new DoneCallback<AppData>() { // from class: z1.ez.2
            /* renamed from: a */
            public void onDone(AppData aVar) {
                if (aVar == null) {
                    return;
                }
                if (ScriptModelSwapDialog.f21594a.mo12950a(TbsConfig.APP_QQ, 0)) {
                    Log.i("LBS_PXKJ", "isUnistall qq 1");
                } else {
                    Log.i("LBS_PXKJ", "isUnistall qq 2");
                }
            }
        });
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    /* renamed from: b */
    public void m2994b() {
        setContentView(C0692R.layout.dialog_script_model_swap_layout);
        this.f21599f = (RelativeLayout) findViewById(C0692R.C0694id.nrzs_app_sctript_model_lacal);
        this.f21607n = (RelativeLayout) findViewById(C0692R.C0694id.nrzs_app_sctript_model_pxkj);
        this.f21597d = (ImageView) findViewById(C0692R.C0694id.nrzs_app_sctript_model_img_lacal);
        this.f21598e = (ImageView) findViewById(C0692R.C0694id.nrzs_app_sctript_model_img_pxkj);
        this.f21600g = (LinearLayout) findViewById(C0692R.C0694id.nrzs_app_sctript_model_linner_lacal);
        this.f21608o = (LinearLayout) findViewById(C0692R.C0694id.nrzs_app_sctript_model_linner_pxkj);
        this.f21609p = (LinearLayout) findViewById(C0692R.C0694id.nrzs_app_sctript_va_screen_model);
        this.f21610q = (LinearLayout) findViewById(C0692R.C0694id.nrzs_app_sctript_mobile_screen_model);
        this.f21601h = (CheckBox) findViewById(C0692R.C0694id.install_wx);
        this.f21602i = (CheckBox) findViewById(C0692R.C0694id.insatall_qq);
    }

    /* renamed from: c */
    public void m2991c() {
        int a = ScriptModeManager.m3097f().m3103a();
        if (a == 1) {
            this.f21597d.setVisibility(0);
            this.f21598e.setVisibility(8);
            this.f21600g.setBackground(this.f21596b.getResources().getDrawable(C0692R.C0693drawable.f1997b8));
            this.f21608o.setBackground(this.f21596b.getResources().getDrawable(C0692R.C0693drawable.f1998b9));
        } else if (a == 2) {
            this.f21597d.setVisibility(8);
            this.f21598e.setVisibility(0);
            this.f21600g.setBackground(this.f21596b.getResources().getDrawable(C0692R.C0693drawable.f1998b9));
            this.f21608o.setBackground(this.f21596b.getResources().getDrawable(C0692R.C0693drawable.f1997b8));
        }
        if (apf.m11838b(this.f21596b.getApplicationContext(), "common_shared_file", ShareVal.f16606p, 0) == 0) {
            this.f21609p.setVisibility(0);
            this.f21610q.setVisibility(8);
        } else {
            this.f21609p.setVisibility(8);
            this.f21610q.setVisibility(0);
        }
        m2989d();
    }

    /* renamed from: d */
    public void m2989d() {
        this.f21603j = apf.m11836b(DataApp.m18939d().m18947a(), ShareVal.f16591a, ShareVal.f16599i, true);
        this.f21604k = apf.m11836b(DataApp.m18939d().m18947a(), ShareVal.f16591a, ShareVal.f16600j, true);
        this.f21602i.setChecked(this.f21604k);
        this.f21601h.setChecked(this.f21603j);
        this.f21605l = this.f21603j;
        this.f21606m = this.f21604k;
    }

    /* renamed from: e */
    public void m2987e() {
        this.f21601h.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: z1.ez.3
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                ScriptModelSwapDialog.this.f21603j = z;
                apf.m11841a(DataApp.m18939d().m18947a(), ShareVal.f16591a, ShareVal.f16599i, ScriptModelSwapDialog.this.f21603j);
            }
        });
        this.f21602i.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: z1.ez.4
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                ScriptModelSwapDialog.this.f21604k = z;
                apf.m11841a(DataApp.m18939d().m18947a(), ShareVal.f16591a, ShareVal.f16600j, ScriptModelSwapDialog.this.f21604k);
            }
        });
        this.f21609p.setOnClickListener(new View.OnClickListener() { // from class: z1.ez.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                apf.m11843a(ScriptModelSwapDialog.this.f21596b.getApplicationContext(), "common_shared_file", ShareVal.f16606p, 1);
                ScriptModelSwapDialog.this.f21609p.setVisibility(8);
                ScriptModelSwapDialog.this.f21610q.setVisibility(0);
            }
        });
        this.f21610q.setOnClickListener(new View.OnClickListener() { // from class: z1.ez.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                apf.m11843a(ScriptModelSwapDialog.this.f21596b.getApplicationContext(), "common_shared_file", ShareVal.f16606p, 0);
                ScriptModelSwapDialog.this.f21609p.setVisibility(0);
                ScriptModelSwapDialog.this.f21610q.setVisibility(8);
            }
        });
        this.f21599f.setOnClickListener(new View.OnClickListener() { // from class: z1.ez.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int a = ScriptModeManager.m3097f().m3103a();
                if (a == 1) {
                    ScriptModelSwapDialog.this.dismiss();
                } else if (a == 2) {
                    ScriptModeManager.m3097f().m3102a(1);
                    EventBus.m3448a().m3427d(new CommonEvent.C3553a());
                    ScriptModelSwapDialog.m2998a();
                }
            }
        });
        this.f21607n.setOnClickListener(new View.OnClickListener() { // from class: z1.ez.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int a = ScriptModeManager.m3097f().m3103a();
                if (a == 1) {
                    if (ScriptModeManager.m3097f().m3100c()) {
                        ScriptModeManager.m3097f().m3102a(2);
                        EventBus.m3448a().m3427d(new CommonEvent.C3553a());
                        ScriptModelSwapDialog.m2998a();
                        return;
                    }
                    aqg.m11586a(App.m25213a(), "当前设备不支持【通用模式】");
                } else if (a == 2) {
                    ScriptModelSwapDialog.this.dismiss();
                }
            }
        });
    }
}
