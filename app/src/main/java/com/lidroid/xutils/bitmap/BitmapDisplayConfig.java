package com.lidroid.xutils.bitmap;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.animation.Animation;
import com.lidroid.xutils.bitmap.core.BitmapSize;
import com.lidroid.xutils.bitmap.factory.BitmapFactory;
import com.lidroid.xutils.task.Priority;

/* loaded from: classes.dex */
public class BitmapDisplayConfig {
    private Animation animation;
    private BitmapFactory bitmapFactory;
    private BitmapSize bitmapMaxSize;
    private Drawable loadFailedDrawable;
    private Drawable loadingDrawable;
    private Priority priority;
    private boolean autoRotation = false;
    private boolean showOriginal = false;
    private Bitmap.Config bitmapConfig = Bitmap.Config.RGB_565;

    public BitmapSize getBitmapMaxSize() {
        BitmapSize bitmapSize = this.bitmapMaxSize;
        return bitmapSize == null ? BitmapSize.ZERO : bitmapSize;
    }

    public void setBitmapMaxSize(BitmapSize bitmapSize) {
        this.bitmapMaxSize = bitmapSize;
    }

    public Animation getAnimation() {
        return this.animation;
    }

    public void setAnimation(Animation animation) {
        this.animation = animation;
    }

    public Drawable getLoadingDrawable() {
        return this.loadingDrawable;
    }

    public void setLoadingDrawable(Drawable drawable) {
        this.loadingDrawable = drawable;
    }

    public Drawable getLoadFailedDrawable() {
        return this.loadFailedDrawable;
    }

    public void setLoadFailedDrawable(Drawable drawable) {
        this.loadFailedDrawable = drawable;
    }

    public boolean isAutoRotation() {
        return this.autoRotation;
    }

    public void setAutoRotation(boolean z) {
        this.autoRotation = z;
    }

    public boolean isShowOriginal() {
        return this.showOriginal;
    }

    public void setShowOriginal(boolean z) {
        this.showOriginal = z;
    }

    public Bitmap.Config getBitmapConfig() {
        return this.bitmapConfig;
    }

    public void setBitmapConfig(Bitmap.Config config) {
        this.bitmapConfig = config;
    }

    public BitmapFactory getBitmapFactory() {
        return this.bitmapFactory;
    }

    public void setBitmapFactory(BitmapFactory bitmapFactory) {
        this.bitmapFactory = bitmapFactory;
    }

    public Priority getPriority() {
        return this.priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(String.valueOf(isShowOriginal() ? "" : this.bitmapMaxSize.toString()));
        BitmapFactory bitmapFactory = this.bitmapFactory;
        sb.append(bitmapFactory == null ? "" : bitmapFactory.getClass().getName());
        return sb.toString();
    }

    public BitmapDisplayConfig cloneNew() {
        BitmapDisplayConfig bitmapDisplayConfig = new BitmapDisplayConfig();
        bitmapDisplayConfig.bitmapMaxSize = this.bitmapMaxSize;
        bitmapDisplayConfig.animation = this.animation;
        bitmapDisplayConfig.loadingDrawable = this.loadingDrawable;
        bitmapDisplayConfig.loadFailedDrawable = this.loadFailedDrawable;
        bitmapDisplayConfig.autoRotation = this.autoRotation;
        bitmapDisplayConfig.showOriginal = this.showOriginal;
        bitmapDisplayConfig.bitmapConfig = this.bitmapConfig;
        bitmapDisplayConfig.bitmapFactory = this.bitmapFactory;
        bitmapDisplayConfig.priority = this.priority;
        return bitmapDisplayConfig;
    }
}
