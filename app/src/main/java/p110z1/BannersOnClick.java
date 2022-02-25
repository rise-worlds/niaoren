package p110z1;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.angel.nrzs.app.activity.NRZSWebviewActivity;
import com.nrzs.base.router.RouterUtils;
import com.nrzs.data.other.bean.AdResultInfoItem;
import org.apache.http.HttpHost;
import org.apache.http.HttpVersion;

/* renamed from: z1.dv */
/* loaded from: classes3.dex */
public class BannersOnClick implements IAdClick {
    /* renamed from: a */
    public void m3199a(Context context, AdResultInfoItem adResultInfoItem, int i) {
        m3198c(context, adResultInfoItem, i);
    }

    /* renamed from: c */
    private void m3198c(Context context, AdResultInfoItem adResultInfoItem, int i) {
        if (!TextUtils.isEmpty(adResultInfoItem.ExecArgs)) {
            if (adResultInfoItem.ExecCommand.equals(C5277dw.f21345a)) {
                mo3197a(context, adResultInfoItem.ExecArgs, i);
            } else if (adResultInfoItem.ExecCommand.equals(C5277dw.f21346b)) {
                mo3196b(context, adResultInfoItem, i);
            } else if (adResultInfoItem.ExecCommand.equals(C5277dw.f21347c)) {
                RouterUtils.toGameTopic(adResultInfoItem.AdName, Integer.valueOf(adResultInfoItem.ExecArgs).intValue());
            }
        }
    }

    @Override // p110z1.IAdClick
    /* renamed from: a */
    public void mo3197a(Context context, String str, int i) {
        if (str != null) {
            try {
                if (str.startsWith(HttpHost.DEFAULT_SCHEME_NAME) || str.startsWith(HttpVersion.HTTP)) {
                    Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
                    intent.addFlags(268435456);
                    context.startActivity(intent);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override // p110z1.IAdClick
    /* renamed from: b */
    public void mo3196b(Context context, AdResultInfoItem adResultInfoItem, int i) {
        if (i == 1) {
            NRZSWebviewActivity.m25004a(context, 99, 0, adResultInfoItem);
        } else {
            NRZSWebviewActivity.m25004a(context, 99, 1, adResultInfoItem);
        }
    }
}
