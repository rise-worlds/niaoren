package p110z1;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.LinearLayout;
import com.cyjh.ddy.media.bean.DdyUserInfo;
import com.cyjh.ddy.media.media.listener.IHwySDKListener;
import com.cyjh.ddy.media.p035a.ResultTypeConstant;
import com.cyjh.ddysdk.device.base.constants.DdyDeviceErrorConstants;
import com.cyjh.ddysdk.device.extendcommand.DdyDeviceExCommandContract;
import com.cyjh.ddysdk.device.extendcommand.DdyDeviceExCommandHelper;
import com.cyjh.ddysdk.device.media.DdyDeviceMediaContract;
import com.cyjh.ddysdk.device.media.DdyDeviceMediaHelper;
import com.cyjh.ddysdk.order.base.bean.NoticeSyncInfo;

/* renamed from: z1.ahz */
/* loaded from: classes3.dex */
public class VirsualSdk {

    /* renamed from: a */
    private static final String f15888a = "ThirdSDK";

    /* renamed from: b */
    private DdyDeviceMediaHelper f15889b;

    /* renamed from: d */
    private DdyDeviceExCommandContract.IPresenter f15891d;

    /* renamed from: f */
    private boolean f15893f;

    /* renamed from: c */
    private String f15890c = "B87C69E65B0FB500";

    /* renamed from: e */
    private DdyDeviceExCommandHelper f15892e = new DdyDeviceExCommandHelper();

    /* renamed from: a */
    public void m13139a(Context context, String str, LinearLayout linearLayout) {
        this.f15893f = true;
        if (this.f15889b == null) {
            this.f15889b = new DdyDeviceMediaHelper(context);
        }
        if (TextUtils.isEmpty(str)) {
            str = ResultTypeConstant.f7213z;
        }
        this.f15889b.init(m13140a(Long.parseLong(str), this.f15890c), "", "", new IHwySDKListener() { // from class: z1.ahz.1
            @Override // com.cyjh.ddy.media.media.listener.IHwySDKListener
            public void actionCodeCallback(int i, String str2) {
            }

            @Override // com.cyjh.ddy.media.media.listener.IHwySDKListener
            public void autoRotateScreen(int i) {
            }

            @Override // com.cyjh.ddy.media.media.listener.IHwySDKListener
            public void upClipboard(String str2) {
            }

            @Override // com.cyjh.ddy.media.media.listener.IHwySDKListener
            public void upFrameTime(long j) {
            }

            @Override // com.cyjh.ddy.media.media.listener.IHwySDKListener
            public void upNoticeSyncInfo(NoticeSyncInfo noticeSyncInfo) {
            }

            @Override // com.cyjh.ddy.media.media.listener.IHwySDKListener
            public void upTraffic(long j, long j2) {
            }

            @Override // com.cyjh.ddy.media.media.listener.IHwySDKListener
            public void upFps(String str2) {
                Log.d(VirsualSdk.f15888a, "upFps()".concat(" fps:" + str2));
            }

            @Override // com.cyjh.ddy.media.media.listener.IHwySDKListener
            public void upPing(String str2) {
                Log.d(VirsualSdk.f15888a, "upPing()".concat(" pingRtt:" + str2));
            }
        }, linearLayout, false);
    }

    /* renamed from: a */
    public void m13138a(String str, String str2) {
        this.f15889b.setPullStreamRate(str2, str2);
        this.f15889b.setReConnect(true);
        this.f15889b.playMedia(Long.valueOf(str).longValue(), this.f15890c, "", new DdyDeviceMediaContract.Callback() { // from class: z1.ahz.2
            @Override // com.cyjh.ddysdk.device.media.DdyDeviceMediaContract.Callback
            public void failuer(DdyDeviceErrorConstants ddyDeviceErrorConstants, String str3) {
            }

            @Override // com.cyjh.ddysdk.device.media.DdyDeviceMediaContract.Callback
            public void success(Object obj) {
            }
        });
    }

    /* renamed from: a */
    public void m13141a(int i, String str) {
        DdyDeviceMediaHelper ddyDeviceMediaHelper = this.f15889b;
        if (ddyDeviceMediaHelper != null) {
            if (i == 0) {
                ddyDeviceMediaHelper.setPullStreamRate(EventConstants.f16417U, null);
            } else if (i == 1) {
                ddyDeviceMediaHelper.setPullStreamRate("3000", null);
            }
            this.f15889b.changeMedia(Long.valueOf(str).longValue(), this.f15890c, null, new DdyDeviceMediaContract.Callback() { // from class: z1.ahz.3
                @Override // com.cyjh.ddysdk.device.media.DdyDeviceMediaContract.Callback
                public void failuer(DdyDeviceErrorConstants ddyDeviceErrorConstants, String str2) {
                }

                @Override // com.cyjh.ddysdk.device.media.DdyDeviceMediaContract.Callback
                public void success(Object obj) {
                }
            });
        }
    }

    /* renamed from: a */
    public DdyUserInfo m13140a(long j, String str) {
        DdyUserInfo ddyUserInfo = new DdyUserInfo();
        ddyUserInfo.OrderId = j;
        ddyUserInfo.UCID = str;
        return ddyUserInfo;
    }
}
