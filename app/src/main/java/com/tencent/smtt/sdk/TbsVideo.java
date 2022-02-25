package com.tencent.smtt.sdk;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.tencent.tbs.video.interfaces.IUserStateHolder;

/* loaded from: classes2.dex */
public class TbsVideo {
    public static void openVideo(Context context, String str) {
        openVideo(context, str, null);
    }

    public static void openVideo(Context context, String str, Bundle bundle) {
        if (TextUtils.isEmpty(str)) {
            Log.e("TbsVideo", "videoUrl is empty!");
            return;
        }
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putString("videoUrl", str);
        Intent intent = new Intent("com.tencent.smtt.tbs.video.PLAY");
        intent.setFlags(268435456);
        intent.setPackage(context.getPackageName());
        intent.putExtra("extraData", bundle);
        context.startActivity(intent);
    }

    public static boolean openYunboVideo(Context context, String str, Bundle bundle, IUserStateHolder aVar) {
        if (canUseYunbo(context)) {
            return TbsVideoPlayer.m16644a(context).m16643a(str, bundle, aVar);
        }
        return false;
    }

    public static boolean canUseTbsPlayer(Context context) {
        return TbsVideoPlayer.m16644a(context).m16647a();
    }

    public static boolean canUseYunbo(Context context) {
        return TbsVideoPlayer.m16644a(context).m16647a() && QbSdk.canUseVideoFeatrue(context, 1);
    }
}
