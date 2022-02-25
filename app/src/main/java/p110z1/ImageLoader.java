package p110z1;

import android.content.Context;
import android.widget.ImageView;

/* renamed from: z1.ari */
/* loaded from: classes3.dex */
public abstract class ImageLoader implements ImageLoaderInterface<ImageView> {
    @Override // p110z1.ImageLoaderInterface
    public ImageView createImageView(Context context) {
        return new ImageView(context);
    }
}
