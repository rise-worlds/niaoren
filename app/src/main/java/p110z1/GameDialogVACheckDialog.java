package p110z1;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.nrzs.game.C2029R;
import com.nrzs.game.ui.activity.GameLocalActivity;

/* renamed from: z1.aon */
/* loaded from: classes3.dex */
public class GameDialogVACheckDialog extends Dialog {

    /* renamed from: a */
    private TextView f16989a;

    /* renamed from: b */
    private ImageView f16990b;

    public GameDialogVACheckDialog(Context context) {
        super(context);
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setCancelable(false);
        m12054b();
        m12055a();
    }

    /* renamed from: a */
    private void m12055a() {
        this.f16989a.setOnClickListener(new View.OnClickListener() { // from class: z1.aon.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                GameLocalActivity.m18704a(view.getContext(), VAManager.m12070i().m12094a());
                VAManager.m12070i().m12072g();
            }
        });
        this.f16990b.setOnClickListener(new View.OnClickListener() { // from class: z1.aon.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                VAManager.m12070i().m12072g();
            }
        });
    }

    /* renamed from: b */
    private void m12054b() {
        setContentView(C2029R.layout.nrzs_game_dialog_va_check);
        this.f16989a = (TextView) findViewById(C2029R.C2031id.nrzs_game_tv_check);
        this.f16990b = (ImageView) findViewById(C2029R.C2031id.nrzs_game_iv_close);
    }
}
