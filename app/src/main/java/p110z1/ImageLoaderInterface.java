package p110z1;

import android.content.Context;
import android.view.View;
import java.io.Serializable;

/* renamed from: z1.arj */
/* loaded from: classes3.dex */
public interface ImageLoaderInterface<T extends View> extends Serializable {
    T createImageView(Context context);

    void displayImage(Context context, Object obj, T t);
}
