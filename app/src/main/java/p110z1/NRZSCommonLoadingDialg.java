package p110z1;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;
import com.nrzs.moudleui.C2202R;

/* renamed from: z1.apk */
/* loaded from: classes3.dex */
public class NRZSCommonLoadingDialg extends Dialog {

    /* renamed from: a */
    private String f17157a;

    /* renamed from: b */
    private TextView f17158b;

    /* renamed from: b */
    private void m11701b() {
    }

    public NRZSCommonLoadingDialg(Context context) {
        super(context);
    }

    public NRZSCommonLoadingDialg(Context context, String str) {
        super(context);
        this.f17157a = str;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setCancelable(false);
        m11702a();
        m11701b();
    }

    /* renamed from: a */
    private void m11702a() {
        setContentView(C2202R.layout.bird_common_dialog_loading);
        this.f17158b = (TextView) findViewById(C2202R.C2204id.nrzs_comm_tv_tips);
        if (!TextUtils.isEmpty(this.f17157a)) {
            this.f17158b.setText(this.f17157a);
        }
    }
}
