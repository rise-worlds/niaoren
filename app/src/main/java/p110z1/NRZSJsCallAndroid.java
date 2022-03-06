package p110z1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.webkit.JavascriptInterface;
import com.angel.nrzs.ui;
import com.angel.nrzs.ui.activity.MainActivity;
import com.blankj.utilcode.util.LogUtils;
import com.nrzs.data.base.GetSignRequest;
import com.nrzs.moudlepay.AliPayManeger;
import java.util.Random;

/* renamed from: z1.fj */
/* loaded from: classes3.dex */
public class NRZSJsCallAndroid {

    /* renamed from: a */
    public static final String f21633a = "JsCallAndroid";

    /* renamed from: b */
    private Context f21634b;

    public NRZSJsCallAndroid(Context context) {
        this.f21634b = context;
    }

    @JavascriptInterface
    public void isHookLogin() {
        try {
            if (!LoginManager.m12620d().m12606r()) {
                IntentToAssistService.m12813a(App.getInstance());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @JavascriptInterface
    public String getAppSigner(String str, int i) {
        int a;
        GetSignRequest getSignRequest = new GetSignRequest();
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (i == 1) {
            str = str + getSignRequest.tojsPrames();
        } else {
            if (i == 2) {
                str = str + C4745bt.f20071b + getSignRequest.tojsPrames();
            }
            String str2 = str + "&Sign=" + new CJU().m12902b(str, a) + "&R=" + m2836a();
            LogUtils.m23734c("TEMPORARY", str2);
            return str2;
        }
        String str22 = str + "&Sign=" + new CJU().m12902b(str, a) + "&R=" + m2836a();
        LogUtils.m23734c("TEMPORARY", str22);
        return str22;
    }

    @JavascriptInterface
    public String getUserInfo() {
        try {
            if (!LoginManager.m12620d().m12606r()) {
                return "";
            }
            return LoginManager.m12620d().m12618f();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /* renamed from: a */
    public int m2836a() {
        return new Random().nextInt(8);
    }

    @JavascriptInterface
    public void toHookLogin(int i, String str) {
        try {
            LoginManager.m12620d().m12606r();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @JavascriptInterface
    public void StpiptPay(final String str, final String str2, final String str3) {
        Log.e("paysueesee", "发送");
        new Thread(new Runnable() { // from class: z1.fj.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    AliPayManeger.m18507a().m18502a(str, str2, (Activity) NRZSJsCallAndroid.this.f21634b, str3);
                } catch (Exception unused) {
                }
            }
        }).start();
    }

    /* renamed from: a */
    public static boolean m2835a(Context context) {
        return new Intent("android.intent.action.VIEW", Uri.parse("alipays://platformapi/startApp")).resolveActivity(context.getPackageManager()) != null;
    }

    @JavascriptInterface
    public void bysuccess() {
        EventBus.m3448a().m3427d(new BuysuccessEvent.C3552a());
    }

    @JavascriptInterface
    public void showToast(String str) {
        aqg.m11586a(App.getInstance(), str);
    }

    @JavascriptInterface
    public void JumpToHomePage() {
        Context context = this.f21634b;
        if (context != null) {
            MainActivity.m25033a(context);
            Context context2 = this.f21634b;
            if (context2 instanceof Activity) {
                ((Activity) context2).finish();
            }
        }
    }
}
