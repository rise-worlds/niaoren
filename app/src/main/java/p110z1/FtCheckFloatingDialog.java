package p110z1;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.blankj.utilcode.util.SPUtils;
import com.nrzs.p067ft.C1990R;

/* renamed from: z1.anu */
/* loaded from: classes3.dex */
public class FtCheckFloatingDialog extends Dialog {

    /* renamed from: a */
    private RelativeLayout f16860a;

    /* renamed from: b */
    private RelativeLayout f16861b;

    /* renamed from: c */
    private TextView f16862c;

    /* renamed from: d */
    private TextView f16863d;

    /* renamed from: e */
    private CheckBox f16864e;

    /* renamed from: f */
    private TextView f16865f;

    /* renamed from: g */
    private int f16866g;

    /* renamed from: h */
    private boolean f16867h;

    public FtCheckFloatingDialog(@NonNull Context context, int i) {
        super(context);
        this.f16866g = i;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setCancelable(false);
        m12193c();
        m12197a();
        m12195b();
    }

    /* renamed from: a */
    private void m12197a() {
        this.f16867h = SPUtils.m23341a().m23318b(ShareVal.f16593c, false);
        this.f16864e.setChecked(this.f16867h);
    }

    /* renamed from: b */
    private void m12195b() {
        this.f16862c.setOnClickListener(new View.OnClickListener() { // from class: z1.anu.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (FtCheckFloatingDialog.this.f16866g == 1 || FtCheckFloatingDialog.this.f16866g == 2) {
                    FtCheckFloatingDialog.this.f16860a.setVisibility(8);
                    FtCheckFloatingDialog.this.f16861b.setVisibility(0);
                } else if (FtCheckFloatingDialog.this.f16866g == 4) {
                    FloatWindowPermissionChecker.m12292b(FtCheckFloatingDialog.this.getContext());
                } else {
                    FtCheckFloatingDialog.this.f16860a.setVisibility(8);
                    FtCheckFloatingDialog.this.f16861b.setVisibility(0);
                }
            }
        });
        this.f16865f.setOnClickListener(new View.OnClickListener() { // from class: z1.anu.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FtCheckFloatingDialog.this.dismiss();
            }
        });
        this.f16864e.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: z1.anu.3
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                SPUtils.m23341a().m23328a(ShareVal.f16593c, z);
            }
        });
        this.f16863d.setOnClickListener(new View.OnClickListener() { // from class: z1.anu.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FtCheckFloatingDialog.this.dismiss();
            }
        });
    }

    /* renamed from: c */
    private void m12193c() {
        setContentView(C1990R.layout.nrzs_ft_dialog_check_floating);
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        getWindow().setLayout(-1, -1);
        this.f16860a = (RelativeLayout) findViewById(C1990R.C1992id.nrzs_ft_rl1);
        this.f16861b = (RelativeLayout) findViewById(C1990R.C1992id.nrzs_ft_rl2);
        this.f16862c = (TextView) findViewById(C1990R.C1992id.nrzs_ft_tv_set);
        this.f16863d = (TextView) findViewById(C1990R.C1992id.nrzs_ft_tv_seted);
        this.f16864e = (CheckBox) findViewById(C1990R.C1992id.nrzs_ft_tv_notips);
        this.f16865f = (TextView) findViewById(C1990R.C1992id.nrzs_ft_tv_know);
    }
}
