package p110z1;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.nrzs.data.DataApp;
import com.nrzs.user.C2222R;

/* renamed from: z1.apx */
/* loaded from: classes3.dex */
public class AllExticDialog extends Dialog {

    /* renamed from: a */
    private TextView f17189a;

    /* renamed from: b */
    private TextView f17190b;

    /* renamed from: c */
    private CheckBox f17191c;

    /* renamed from: d */
    private ImageView f17192d;

    /* renamed from: e */
    private AbstractC3853a f17193e;

    /* compiled from: AllExticDialog.java */
    /* renamed from: z1.apx$a */
    /* loaded from: classes3.dex */
    public interface AbstractC3853a {
        /* renamed from: a */
        void mo11659a();
    }

    /* renamed from: c */
    protected void m11661c() {
    }

    public AllExticDialog(@NonNull Context context, AbstractC3853a aVar) {
        super(context);
        this.f17193e = aVar;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().requestFeature(1);
        setContentView(C2222R.layout.dialog_allexitc_layout);
        setCancelable(false);
        m11660d();
        m11664a();
        m11662b();
    }

    /* renamed from: a */
    protected void m11664a() {
        this.f17189a = (TextView) findViewById(C2222R.C2224id.close_all);
        this.f17190b = (TextView) findViewById(C2222R.C2224id.ok_btn);
        this.f17191c = (CheckBox) findViewById(C2222R.C2224id.extic_chekbox);
        this.f17192d = (ImageView) findViewById(C2222R.C2224id.close);
    }

    /* renamed from: b */
    protected void m11662b() {
        this.f17192d.setOnClickListener(new View.OnClickListener() { // from class: z1.apx.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AllExticDialog.this.dismiss();
            }
        });
        this.f17191c.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: z1.apx.2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    apf.m11841a(DataApp.m18939d().m18947a(), ShareVal.f16591a, ShareVal.f16588S, true);
                } else {
                    apf.m11841a(DataApp.m18939d().m18947a(), ShareVal.f16591a, ShareVal.f16588S, false);
                }
            }
        });
        this.f17189a.setOnClickListener(new View.OnClickListener() { // from class: z1.apx.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AllExticDialog.this.dismiss();
            }
        });
        this.f17190b.setOnClickListener(new View.OnClickListener() { // from class: z1.apx.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AllExticDialog.this.f17193e.mo11659a();
                AllExticDialog.this.dismiss();
            }
        });
    }

    /* renamed from: d */
    public void m11660d() {
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        getWindow().setGravity(17);
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        getWindow().getDecorView().setBackgroundColor(getContext().getResources().getColor(C2222R.color.transparent));
        getWindow().setAttributes(attributes);
    }
}
