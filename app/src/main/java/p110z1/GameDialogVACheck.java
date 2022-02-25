package p110z1;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.nrzs.game.C2029R;
import com.nrzs.game.p069ui.activity.GameLocalActivity;

/* renamed from: z1.aom */
/* loaded from: classes3.dex */
public class GameDialogVACheck extends Dialog {

    /* renamed from: a */
    private TextView f16985a;

    /* renamed from: b */
    private ImageView f16986b;

    public GameDialogVACheck(Context context) {
        super(context);
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setCancelable(false);
        m12056b();
        m12057a();
    }

    /* renamed from: a */
    private void m12057a() {
        this.f16985a.setOnClickListener(new View.OnClickListener() { // from class: z1.aom.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Log.e("选择游戏", VAManager.m12070i().m12094a().TopicID + "");
                GameLocalActivity.m18704a(view.getContext(), VAManager.m12070i().m12094a());
                VAManager.m12070i().m12072g();
            }
        });
        this.f16986b.setOnClickListener(new View.OnClickListener() { // from class: z1.aom.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                VAManager.m12070i().m12072g();
            }
        });
    }

    /* renamed from: b */
    private void m12056b() {
        setContentView(C2029R.layout.nrzs_game_dialog_va_check);
        this.f16985a = (TextView) findViewById(C2029R.C2031id.nrzs_game_tv_check);
        this.f16986b = (ImageView) findViewById(C2029R.C2031id.nrzs_game_iv_close);
    }
}
