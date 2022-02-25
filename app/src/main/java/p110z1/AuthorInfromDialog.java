package p110z1;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import com.angel.nrzs.C0692R;

/* renamed from: z1.et */
/* loaded from: classes3.dex */
public class AuthorInfromDialog extends Dialog {

    /* renamed from: e */
    private static AuthorInfromDialog f21531e;

    /* renamed from: a */
    private final String f21532a;

    /* renamed from: b */
    private TextView f21533b;

    /* renamed from: c */
    private ImageView f21534c;

    /* renamed from: d */
    private TextView f21535d;

    public AuthorInfromDialog(@NonNull Context context, String str) {
        super(context);
        this.f21532a = str;
    }

    /* renamed from: a */
    public static void m3044a(Context context, String str) {
        if (f21531e == null) {
            f21531e = new AuthorInfromDialog(context, str);
        }
        if (!f21531e.isShowing()) {
            f21531e.show();
        }
    }

    /* renamed from: a */
    public static void m3045a() {
        AuthorInfromDialog etVar = f21531e;
        if (etVar != null) {
            etVar.dismiss();
            f21531e = null;
        }
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C0692R.layout.nrzs_authorinfrom_dialog);
        m3043b();
        m3042c();
        m3041d();
    }

    /* renamed from: c */
    private void m3042c() {
        this.f21535d = (TextView) findViewById(C0692R.C0694id.nrzs_app_tv_agree);
        this.f21534c = (ImageView) findViewById(C0692R.C0694id.nrzs_app_iv_close);
        this.f21533b = (TextView) findViewById(C0692R.C0694id.nrzs_app_tv_infrom);
        this.f21533b.setText(this.f21532a);
    }

    /* renamed from: d */
    private void m3041d() {
        this.f21534c.setOnClickListener(new View.OnClickListener() { // from class: z1.et.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AuthorInfromDialog.m3045a();
            }
        });
        this.f21535d.setOnClickListener(new View.OnClickListener() { // from class: z1.et.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AuthorInfromDialog.m3045a();
            }
        });
    }

    /* renamed from: b */
    public void m3043b() {
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        getWindow().setGravity(17);
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        getWindow().getDecorView().setBackgroundColor(getContext().getResources().getColor(C0692R.color.f1440f2));
        getWindow().setAttributes(attributes);
    }
}
