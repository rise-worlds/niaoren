package p110z1;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;

/* renamed from: z1.aoz */
/* loaded from: classes3.dex */
public class GlideImageUtils {
    /* renamed from: a */
    public static void m11880a(ImageView imageView, Context context, int i, String str) {
        Glide.with(context).load(str).apply((BaseRequestOptions<?>) new RequestOptions().placeholder(i)).into(imageView);
    }

    /* renamed from: a */
    public static void m11881a(ImageView imageView, Context context, int i, Drawable drawable) {
        Glide.with(context).load(drawable).apply((BaseRequestOptions<?>) new RequestOptions().placeholder(i)).into(imageView);
    }
}
