package com.bumptech.glide.request;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.CheckResult;
import android.support.annotation.DrawableRes;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Option;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy;

/* loaded from: classes.dex */
public class RequestOptions extends BaseRequestOptions<RequestOptions> {
    @Nullable
    private static RequestOptions centerCropOptions;
    @Nullable
    private static RequestOptions centerInsideOptions;
    @Nullable
    private static RequestOptions circleCropOptions;
    @Nullable
    private static RequestOptions fitCenterOptions;
    @Nullable
    private static RequestOptions noAnimationOptions;
    @Nullable
    private static RequestOptions noTransformOptions;
    @Nullable
    private static RequestOptions skipMemoryCacheFalseOptions;
    @Nullable
    private static RequestOptions skipMemoryCacheTrueOptions;

    @CheckResult
    @NonNull
    public static RequestOptions sizeMultiplierOf(@FloatRange(from = 0.0d, m25696to = 1.0d) float f) {
        return new RequestOptions().sizeMultiplier(f);
    }

    @CheckResult
    @NonNull
    public static RequestOptions diskCacheStrategyOf(@NonNull DiskCacheStrategy diskCacheStrategy) {
        return new RequestOptions().diskCacheStrategy(diskCacheStrategy);
    }

    @CheckResult
    @NonNull
    public static RequestOptions priorityOf(@NonNull Priority priority) {
        return new RequestOptions().priority(priority);
    }

    @CheckResult
    @NonNull
    public static RequestOptions placeholderOf(@Nullable Drawable drawable) {
        return new RequestOptions().placeholder(drawable);
    }

    @CheckResult
    @NonNull
    public static RequestOptions placeholderOf(@DrawableRes int i) {
        return new RequestOptions().placeholder(i);
    }

    @CheckResult
    @NonNull
    public static RequestOptions errorOf(@Nullable Drawable drawable) {
        return new RequestOptions().error(drawable);
    }

    @CheckResult
    @NonNull
    public static RequestOptions errorOf(@DrawableRes int i) {
        return new RequestOptions().error(i);
    }

    @CheckResult
    @NonNull
    public static RequestOptions skipMemoryCacheOf(boolean z) {
        if (z) {
            if (skipMemoryCacheTrueOptions == null) {
                skipMemoryCacheTrueOptions = new RequestOptions().skipMemoryCache(true).autoClone();
            }
            return skipMemoryCacheTrueOptions;
        }
        if (skipMemoryCacheFalseOptions == null) {
            skipMemoryCacheFalseOptions = new RequestOptions().skipMemoryCache(false).autoClone();
        }
        return skipMemoryCacheFalseOptions;
    }

    @CheckResult
    @NonNull
    public static RequestOptions overrideOf(@IntRange(from = 0) int i, @IntRange(from = 0) int i2) {
        return new RequestOptions().override(i, i2);
    }

    @CheckResult
    @NonNull
    public static RequestOptions overrideOf(@IntRange(from = 0) int i) {
        return overrideOf(i, i);
    }

    @CheckResult
    @NonNull
    public static RequestOptions signatureOf(@NonNull Key key) {
        return new RequestOptions().signature(key);
    }

    @CheckResult
    @NonNull
    public static RequestOptions fitCenterTransform() {
        if (fitCenterOptions == null) {
            fitCenterOptions = new RequestOptions().fitCenter().autoClone();
        }
        return fitCenterOptions;
    }

    @CheckResult
    @NonNull
    public static RequestOptions centerInsideTransform() {
        if (centerInsideOptions == null) {
            centerInsideOptions = new RequestOptions().centerInside().autoClone();
        }
        return centerInsideOptions;
    }

    @CheckResult
    @NonNull
    public static RequestOptions centerCropTransform() {
        if (centerCropOptions == null) {
            centerCropOptions = new RequestOptions().centerCrop().autoClone();
        }
        return centerCropOptions;
    }

    @CheckResult
    @NonNull
    public static RequestOptions circleCropTransform() {
        if (circleCropOptions == null) {
            circleCropOptions = new RequestOptions().circleCrop().autoClone();
        }
        return circleCropOptions;
    }

    @CheckResult
    @NonNull
    public static RequestOptions bitmapTransform(@NonNull Transformation<Bitmap> transformation) {
        return new RequestOptions().transform(transformation);
    }

    @CheckResult
    @NonNull
    public static RequestOptions noTransformation() {
        if (noTransformOptions == null) {
            noTransformOptions = new RequestOptions().dontTransform().autoClone();
        }
        return noTransformOptions;
    }

    @CheckResult
    @NonNull
    public static <T> RequestOptions option(@NonNull Option<T> option, @NonNull T t) {
        return new RequestOptions().set(option, t);
    }

    @CheckResult
    @NonNull
    public static RequestOptions decodeTypeOf(@NonNull Class<?> cls) {
        return new RequestOptions().decode(cls);
    }

    @CheckResult
    @NonNull
    public static RequestOptions formatOf(@NonNull DecodeFormat decodeFormat) {
        return new RequestOptions().format(decodeFormat);
    }

    @CheckResult
    @NonNull
    public static RequestOptions frameOf(@IntRange(from = 0) long j) {
        return new RequestOptions().frame(j);
    }

    @CheckResult
    @NonNull
    public static RequestOptions downsampleOf(@NonNull DownsampleStrategy downsampleStrategy) {
        return new RequestOptions().downsample(downsampleStrategy);
    }

    @CheckResult
    @NonNull
    public static RequestOptions timeoutOf(@IntRange(from = 0) int i) {
        return new RequestOptions().timeout(i);
    }

    @CheckResult
    @NonNull
    public static RequestOptions encodeQualityOf(@IntRange(from = 0, m25695to = 100) int i) {
        return new RequestOptions().encodeQuality(i);
    }

    @CheckResult
    @NonNull
    public static RequestOptions encodeFormatOf(@NonNull Bitmap.CompressFormat compressFormat) {
        return new RequestOptions().encodeFormat(compressFormat);
    }

    @CheckResult
    @NonNull
    public static RequestOptions noAnimation() {
        if (noAnimationOptions == null) {
            noAnimationOptions = new RequestOptions().dontAnimate().autoClone();
        }
        return noAnimationOptions;
    }
}
