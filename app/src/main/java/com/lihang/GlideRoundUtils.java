package com.lihang;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.View;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

/* renamed from: com.lihang.c */
/* loaded from: classes.dex */
class GlideRoundUtils {
    GlideRoundUtils() {
    }

    /* renamed from: a */
    public static void m19184a(final View view, final Drawable drawable, final float f) {
        if (f == 0.0f) {
            if (view.getMeasuredWidth() == 0 && view.getMeasuredHeight() == 0) {
                view.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: com.lihang.c.1
                    @Override // android.view.View.OnLayoutChangeListener
                    public void onLayoutChange(View view2, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                        view.removeOnLayoutChangeListener(this);
                        Glide.with(view).asDrawable().load(drawable).transform(new CenterCrop()).override(view.getMeasuredWidth(), view.getMeasuredHeight()).into((RequestBuilder) new CustomTarget<Drawable>() { // from class: com.lihang.c.1.1
                            @Override // com.bumptech.glide.request.target.Target
                            public void onLoadCleared(@Nullable Drawable drawable2) {
                            }

                            /* renamed from: a */
                            public void onResourceReady(@NonNull Drawable drawable2, @Nullable Transition<? super Drawable> transition) {
                                if (Build.VERSION.SDK_INT <= 16) {
                                    view.setBackgroundDrawable(drawable2);
                                } else {
                                    view.setBackground(drawable2);
                                }
                            }
                        });
                    }
                });
            } else {
                Glide.with(view).asDrawable().load(drawable).transform(new CenterCrop()).override(view.getMeasuredWidth(), view.getMeasuredHeight()).into((RequestBuilder) new CustomTarget<Drawable>() { // from class: com.lihang.c.2
                    @Override // com.bumptech.glide.request.target.Target
                    public void onLoadCleared(@Nullable Drawable drawable2) {
                    }

                    @RequiresApi(api = 16)
                    /* renamed from: a */
                    public void onResourceReady(@NonNull Drawable drawable2, @Nullable Transition<? super Drawable> transition) {
                        if (Build.VERSION.SDK_INT <= 16) {
                            view.setBackgroundDrawable(drawable2);
                        } else {
                            view.setBackground(drawable2);
                        }
                    }
                });
            }
        } else if (view.getMeasuredWidth() == 0 && view.getMeasuredHeight() == 0) {
            view.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: com.lihang.c.3
                @Override // android.view.View.OnLayoutChangeListener
                public void onLayoutChange(View view2, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                    view.removeOnLayoutChangeListener(this);
                    Glide.with(view).load(drawable).transform(new CenterCrop(), new RoundedCorners((int) f)).override(view.getMeasuredWidth(), view.getMeasuredHeight()).into((RequestBuilder) new CustomTarget<Drawable>() { // from class: com.lihang.c.3.1
                        @Override // com.bumptech.glide.request.target.Target
                        public void onLoadCleared(@Nullable Drawable drawable2) {
                        }

                        @RequiresApi(api = 16)
                        /* renamed from: a */
                        public void onResourceReady(@NonNull Drawable drawable2, @Nullable Transition<? super Drawable> transition) {
                            if (Build.VERSION.SDK_INT <= 16) {
                                view.setBackgroundDrawable(drawable2);
                            } else {
                                view.setBackground(drawable2);
                            }
                        }
                    });
                }
            });
        } else {
            Glide.with(view).load(drawable).transform(new CenterCrop(), new RoundedCorners((int) f)).override(view.getMeasuredWidth(), view.getMeasuredHeight()).into((RequestBuilder) new CustomTarget<Drawable>() { // from class: com.lihang.c.4
                @Override // com.bumptech.glide.request.target.Target
                public void onLoadCleared(@Nullable Drawable drawable2) {
                }

                @RequiresApi(api = 16)
                /* renamed from: a */
                public void onResourceReady(@NonNull Drawable drawable2, @Nullable Transition<? super Drawable> transition) {
                    if (Build.VERSION.SDK_INT <= 16) {
                        view.setBackgroundDrawable(drawable2);
                    } else {
                        view.setBackground(drawable2);
                    }
                }
            });
        }
    }

    /* renamed from: a */
    public static void m19183a(final View view, final Drawable drawable, final float f, final float f2, final float f3, final float f4) {
        if (f == 0.0f && f2 == 0.0f && f3 == 0.0f && f4 == 0.0f) {
            if (view.getMeasuredWidth() == 0 && view.getMeasuredHeight() == 0) {
                view.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: com.lihang.c.5
                    @Override // android.view.View.OnLayoutChangeListener
                    public void onLayoutChange(View view2, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                        view.removeOnLayoutChangeListener(this);
                        Glide.with(view).load(drawable).override(view.getMeasuredWidth(), view.getMeasuredHeight()).into((RequestBuilder) new CustomTarget<Drawable>() { // from class: com.lihang.c.5.1
                            @Override // com.bumptech.glide.request.target.Target
                            public void onLoadCleared(@Nullable Drawable drawable2) {
                            }

                            @RequiresApi(api = 16)
                            /* renamed from: a */
                            public void onResourceReady(@NonNull Drawable drawable2, @Nullable Transition<? super Drawable> transition) {
                                if (Build.VERSION.SDK_INT <= 16) {
                                    view.setBackgroundDrawable(drawable2);
                                } else {
                                    view.setBackground(drawable2);
                                }
                            }
                        });
                    }
                });
            } else {
                Glide.with(view).load(drawable).override(view.getMeasuredWidth(), view.getMeasuredHeight()).into((RequestBuilder) new CustomTarget<Drawable>() { // from class: com.lihang.c.6
                    @Override // com.bumptech.glide.request.target.Target
                    public void onLoadCleared(@Nullable Drawable drawable2) {
                    }

                    @RequiresApi(api = 16)
                    /* renamed from: a */
                    public void onResourceReady(@NonNull Drawable drawable2, @Nullable Transition<? super Drawable> transition) {
                        if (Build.VERSION.SDK_INT <= 16) {
                            view.setBackgroundDrawable(drawable2);
                        } else {
                            view.setBackground(drawable2);
                        }
                    }
                });
            }
        } else if (view.getMeasuredWidth() == 0 && view.getMeasuredHeight() == 0) {
            view.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: com.lihang.c.7
                @Override // android.view.View.OnLayoutChangeListener
                public void onLayoutChange(View view2, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                    view.removeOnLayoutChangeListener(this);
                    Glide.with(view).load(drawable).transform(new GlideRoundTransform(view.getContext(), f, f2, f3, f4)).override(view.getMeasuredWidth(), view.getMeasuredHeight()).into((RequestBuilder) new CustomTarget<Drawable>() { // from class: com.lihang.c.7.1
                        @Override // com.bumptech.glide.request.target.Target
                        public void onLoadCleared(@Nullable Drawable drawable2) {
                        }

                        @RequiresApi(api = 16)
                        /* renamed from: a */
                        public void onResourceReady(@NonNull Drawable drawable2, @Nullable Transition<? super Drawable> transition) {
                            if (Build.VERSION.SDK_INT <= 16) {
                                view.setBackgroundDrawable(drawable2);
                            } else {
                                view.setBackground(drawable2);
                            }
                        }
                    });
                }
            });
        } else {
            Glide.with(view).load(drawable).transform(new GlideRoundTransform(view.getContext(), f, f2, f3, f4)).override(view.getMeasuredWidth(), view.getMeasuredHeight()).into((RequestBuilder) new CustomTarget<Drawable>() { // from class: com.lihang.c.8
                @Override // com.bumptech.glide.request.target.Target
                public void onLoadCleared(@Nullable Drawable drawable2) {
                }

                @RequiresApi(api = 16)
                /* renamed from: a */
                public void onResourceReady(@NonNull Drawable drawable2, @Nullable Transition<? super Drawable> transition) {
                    if (Build.VERSION.SDK_INT <= 16) {
                        view.setBackgroundDrawable(drawable2);
                    } else {
                        view.setBackground(drawable2);
                    }
                }
            });
        }
    }
}
