package com.cyjh.mobileanjian.ipc.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import com.cyjh.ddy.media.media.ActionCode;
import com.cyjh.mqsdk.C1375R;
import java.io.File;
import java.io.FileInputStream;

/* renamed from: com.cyjh.mobileanjian.ipc.view.b */
/* loaded from: classes.dex */
public final class ShowTapPic {

    /* renamed from: a */
    WindowManager f8753a;

    /* renamed from: b */
    View f8754b;

    /* renamed from: c */
    private Handler f8755c;

    /* renamed from: d */
    private Context f8756d;

    /* renamed from: e */
    private Handler.Callback f8757e = new Handler.Callback() { // from class: com.cyjh.mobileanjian.ipc.view.b.1
        @Override // android.os.Handler.Callback
        public final boolean handleMessage(Message message) {
            ShowTapPic.this.f8753a.removeView(ShowTapPic.this.f8754b);
            return false;
        }
    };

    public ShowTapPic(Context context) {
        this.f8756d = context;
        this.f8753a = (WindowManager) context.getSystemService("window");
    }

    /* renamed from: a */
    public final void m20586a(int i, int i2, String str) {
        Bitmap decodeStream;
        try {
            if (new File(str).exists() && (decodeStream = BitmapFactory.decodeStream(new FileInputStream(str))) != null) {
                int width = decodeStream.getWidth();
                int height = decodeStream.getHeight();
                WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                if (Build.VERSION.SDK_INT >= 26) {
                    layoutParams.type = 2038;
                } else {
                    layoutParams.type = ActionCode.CtrlConnectRefuse_2002;
                }
                layoutParams.format = 1;
                layoutParams.gravity = 51;
                layoutParams.flags = 792;
                layoutParams.width = width;
                layoutParams.height = height;
                layoutParams.x = i - (width / 2);
                layoutParams.y = i2 - (height / 2);
                this.f8755c = new Handler(this.f8756d.getMainLooper(), this.f8757e);
                this.f8754b = LayoutInflater.from(this.f8756d).inflate(C1375R.layout.ui_show_image, (ViewGroup) null);
                ((ImageView) this.f8754b.findViewById(C1375R.C1377id.tap_imageview)).setImageBitmap(decodeStream);
                this.f8753a.addView(this.f8754b, layoutParams);
                this.f8755c.sendEmptyMessageDelayed(0, 1000L);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
