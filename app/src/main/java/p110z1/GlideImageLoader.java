package p110z1;

import android.content.Context;
import android.widget.ImageView;
import com.angel.nrzs.C0692R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;

/* renamed from: z1.fe */
/* loaded from: classes3.dex */
public class GlideImageLoader extends ImageLoader {
    public void displayImage(Context context, Object obj, ImageView imageView) {
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(context).load(obj).apply((BaseRequestOptions<?>) new RequestOptions().placeholder(C0692R.C0693drawable.f2399ep)).into(imageView);
    }
}
