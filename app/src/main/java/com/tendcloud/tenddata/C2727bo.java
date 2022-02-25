package com.tendcloud.tenddata;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Base64OutputStream;
import android.util.DisplayMetrics;
import android.util.JsonWriter;
import android.util.LruCache;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import com.liulishuo.filedownloader.model.ConnectionModel;
import com.tencent.smtt.sdk.TbsListener;
import com.tendcloud.tenddata.C2699bd;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONObject;
import p110z1.C4963cj;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: td */
@TargetApi(16)
/* renamed from: com.tendcloud.tenddata.bo */
/* loaded from: classes2.dex */
public class C2727bo {

    /* renamed from: f */
    private static String f13608f = "";

    /* renamed from: g */
    private static final int f13609g = 255;

    /* renamed from: b */
    private final List f13611b;

    /* renamed from: e */
    private final AbstractC2713bj f13614e;

    /* renamed from: d */
    private final Handler f13613d = new Handler(Looper.getMainLooper());

    /* renamed from: a */
    private final CallableC2730c f13610a = new CallableC2730c();

    /* renamed from: c */
    private final C2729b f13612c = new C2729b(255);

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2727bo(List list, AbstractC2713bj bjVar) {
        this.f13611b = list;
        this.f13614e = bjVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m16228a(C2696bb bbVar, OutputStream outputStream) {
        List list;
        this.f13610a.findInActivities(bbVar);
        FutureTask futureTask = new FutureTask(this.f13610a);
        this.f13613d.post(futureTask);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
        List emptyList = Collections.emptyList();
        outputStreamWriter.write("[");
        try {
            list = (List) futureTask.get(1L, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException unused) {
            list = emptyList;
        }
        System.currentTimeMillis();
        int size = list.size();
        int i = 0;
        while (true) {
            if (i < (size == 0 ? 0 : 1)) {
                if (i > 0) {
                    outputStreamWriter.write(",");
                }
                C2731d dVar = (C2731d) list.get(i);
                outputStreamWriter.write("{");
                outputStreamWriter.write("\"activity\":");
                outputStreamWriter.write(JSONObject.quote(dVar.activityName));
                outputStreamWriter.write(",");
                outputStreamWriter.write("\"scale\":");
                outputStreamWriter.write(String.format("%s", Float.valueOf(dVar.scale)));
                outputStreamWriter.write(",");
                outputStreamWriter.write("\"serialized_objects\":");
                JsonWriter jsonWriter = new JsonWriter(outputStreamWriter);
                jsonWriter.beginObject();
                jsonWriter.name("rootObject").value(dVar.rootView.hashCode());
                jsonWriter.name("objects");
                m16229a(jsonWriter, dVar.rootView);
                jsonWriter.endObject();
                jsonWriter.flush();
                outputStreamWriter.write(",");
                outputStreamWriter.write("\"screenshot\":");
                outputStreamWriter.flush();
                dVar.screenshot.writeBitmapJSON(Bitmap.CompressFormat.JPEG, 60, outputStream);
                outputStreamWriter.write(",");
                outputStreamWriter.write("\"image_hash\":");
                outputStreamWriter.write(JSONObject.quote(f13608f));
                outputStreamWriter.write(C4963cj.f20747d);
                i++;
            } else {
                outputStreamWriter.write("]");
                outputStreamWriter.flush();
                return;
            }
        }
    }

    /* renamed from: a */
    List m16231a() {
        return this.f13611b;
    }

    /* renamed from: a */
    private void m16229a(JsonWriter jsonWriter, View view) {
        jsonWriter.beginArray();
        m16225b(jsonWriter, view);
        jsonWriter.endArray();
    }

    /* renamed from: b */
    private void m16225b(JsonWriter jsonWriter, View view) {
        float f;
        View childAt;
        int id = view.getId();
        String nameForId = -1 == id ? null : this.f13614e.nameForId(id);
        jsonWriter.beginObject();
        jsonWriter.name("hashCode").value(view.hashCode());
        jsonWriter.name(ConnectionModel.f10389a).value(id);
        jsonWriter.name("id_name").value(nameForId);
        CharSequence contentDescription = view.getContentDescription();
        if (contentDescription == null) {
            jsonWriter.name("contentDescription").nullValue();
        } else {
            jsonWriter.name("contentDescription").value(contentDescription.toString());
        }
        Object tag = view.getTag();
        if (tag == null) {
            jsonWriter.name("tag").nullValue();
        } else if (tag instanceof CharSequence) {
            jsonWriter.name("tag").value(tag.toString());
        }
        jsonWriter.name("top").value(view.getTop());
        jsonWriter.name("left").value(view.getLeft());
        jsonWriter.name("width").value(view.getWidth());
        jsonWriter.name("height").value(view.getHeight());
        jsonWriter.name("scrollX").value(view.getScrollX());
        jsonWriter.name("scrollY").value(view.getScrollY());
        jsonWriter.name("visibility").value(view.getVisibility());
        float f2 = 0.0f;
        if (Build.VERSION.SDK_INT >= 11) {
            f2 = view.getTranslationX();
            f = view.getTranslationY();
        } else {
            f = 0.0f;
        }
        jsonWriter.name("translationX").value(f2);
        jsonWriter.name("translationY").value(f);
        jsonWriter.name("classes");
        jsonWriter.beginArray();
        Class<?> cls = view.getClass();
        do {
            jsonWriter.value((String) this.f13612c.get(cls));
            cls = cls.getSuperclass();
            if (cls == Object.class) {
                break;
            }
        } while (cls != null);
        jsonWriter.endArray();
        m16224c(jsonWriter, view);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof RelativeLayout.LayoutParams) {
            int[] rules = ((RelativeLayout.LayoutParams) layoutParams).getRules();
            jsonWriter.name("layoutRules");
            jsonWriter.beginArray();
            for (int i : rules) {
                jsonWriter.value(i);
            }
            jsonWriter.endArray();
        }
        jsonWriter.name("subviews");
        jsonWriter.beginArray();
        boolean z = view instanceof ViewGroup;
        if (z) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                if (viewGroup.getChildAt(i2) != null) {
                    jsonWriter.value(childAt.hashCode());
                }
            }
        }
        jsonWriter.endArray();
        jsonWriter.endObject();
        if (z) {
            ViewGroup viewGroup2 = (ViewGroup) view;
            int childCount2 = viewGroup2.getChildCount();
            for (int i3 = 0; i3 < childCount2; i3++) {
                View childAt2 = viewGroup2.getChildAt(i3);
                if (childAt2 != null) {
                    m16225b(jsonWriter, childAt2);
                }
            }
        }
    }

    /* renamed from: c */
    private void m16224c(JsonWriter jsonWriter, View view) {
        Object a;
        try {
            Class<?> cls = view.getClass();
            for (C2699bd.C2701b bVar : this.f13611b) {
                if (bVar.targetClass.isAssignableFrom(cls) && bVar.accessor != null && !(view instanceof WebView) && (a = bVar.accessor.m16257a(view)) != null) {
                    if (a instanceof Number) {
                        jsonWriter.name(bVar.name).value((Number) a);
                    } else if (a instanceof Boolean) {
                        jsonWriter.name(bVar.name).value(((Boolean) a).booleanValue());
                    } else if (a instanceof ColorStateList) {
                        jsonWriter.name(bVar.name).value(Integer.valueOf(((ColorStateList) a).getDefaultColor()));
                    } else if (a instanceof Drawable) {
                        Drawable drawable = (Drawable) a;
                        Rect bounds = drawable.getBounds();
                        jsonWriter.name(bVar.name);
                        jsonWriter.beginObject();
                        jsonWriter.name("classes");
                        jsonWriter.beginArray();
                        for (Class<?> cls2 = drawable.getClass(); cls2 != Object.class; cls2 = cls2.getSuperclass()) {
                            jsonWriter.value(cls2.getCanonicalName());
                        }
                        jsonWriter.endArray();
                        jsonWriter.name("dimensions");
                        jsonWriter.beginObject();
                        jsonWriter.name("left").value(bounds.left);
                        jsonWriter.name("right").value(bounds.right);
                        jsonWriter.name("top").value(bounds.top);
                        jsonWriter.name("bottom").value(bounds.bottom);
                        jsonWriter.endObject();
                        if (drawable instanceof ColorDrawable) {
                            jsonWriter.name("color").value(((ColorDrawable) drawable).getColor());
                        }
                        jsonWriter.endObject();
                    } else {
                        jsonWriter.name(bVar.name).value(a.toString());
                    }
                }
            }
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.bo$b */
    /* loaded from: classes2.dex */
    public static class C2729b extends LruCache {
        C2729b(int i) {
            super(i);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public String create(Class cls) {
            return cls.getCanonicalName();
        }
    }

    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.bo$c */
    /* loaded from: classes2.dex */
    static class CallableC2730c implements Callable {
        private C2696bb mLiveActivities;
        private final int mClientDensity = TbsListener.ErrorCode.STARTDOWNLOAD_1;
        private final DisplayMetrics mDisplayMetrics = new DisplayMetrics();
        private final List mRootViews = new ArrayList();
        private final C2728a mCachedBitmap = new C2728a();

        CallableC2730c() {
        }

        void findInActivities(C2696bb bbVar) {
            this.mLiveActivities = bbVar;
        }

        @Override // java.util.concurrent.Callable
        public List call() {
            try {
                this.mRootViews.clear();
                for (Activity activity : this.mLiveActivities.m16280a()) {
                    String canonicalName = activity.getClass().getCanonicalName();
                    View rootView = activity.getWindow().getDecorView().getRootView();
                    activity.getWindowManager().getDefaultDisplay().getMetrics(this.mDisplayMetrics);
                    this.mRootViews.add(new C2731d(canonicalName, rootView));
                }
                int size = this.mRootViews.size();
                for (int i = 0; i < size; i++) {
                    takeScreenshot((C2731d) this.mRootViews.get(i));
                }
            } catch (Throwable unused) {
            }
            return this.mRootViews;
        }

        /* JADX WARN: Removed duplicated region for block: B:13:0x0058 A[Catch: Throwable -> 0x00a0, TRY_ENTER, TryCatch #3 {Throwable -> 0x00a0, blocks: (B:2:0x0000, B:4:0x0005, B:6:0x003b, B:9:0x0041, B:13:0x0058, B:15:0x005e, B:16:0x0062, B:21:0x0088, B:23:0x0091, B:25:0x0097, B:26:0x009a), top: B:33:0x0000, inners: #4 }] */
        /* JADX WARN: Removed duplicated region for block: B:23:0x0091 A[Catch: Throwable -> 0x00a0, TryCatch #3 {Throwable -> 0x00a0, blocks: (B:2:0x0000, B:4:0x0005, B:6:0x003b, B:9:0x0041, B:13:0x0058, B:15:0x005e, B:16:0x0062, B:21:0x0088, B:23:0x0091, B:25:0x0097, B:26:0x009a), top: B:33:0x0000, inners: #4 }] */
        /* JADX WARN: Removed duplicated region for block: B:31:0x0041 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private void takeScreenshot(com.tendcloud.tenddata.C2727bo.C2731d r14) {
            /*
                r13 = this;
                android.view.View r0 = r14.rootView     // Catch: Throwable -> 0x00a0
                r1 = 0
                r2 = 0
                r3 = 1
                java.lang.Class<android.view.View> r4 = android.view.View.class
                java.lang.String r5 = "createSnapshot"
                r6 = 3
                java.lang.Class[] r7 = new java.lang.Class[r6]     // Catch: NoSuchMethodException -> 0x003a, Exception -> 0x003e, Throwable -> 0x00a0
                java.lang.Class<android.graphics.Bitmap$Config> r8 = android.graphics.Bitmap.Config.class
                r7[r2] = r8     // Catch: NoSuchMethodException -> 0x003a, Exception -> 0x003e, Throwable -> 0x00a0
                java.lang.Class r8 = java.lang.Integer.TYPE     // Catch: NoSuchMethodException -> 0x003a, Exception -> 0x003e, Throwable -> 0x00a0
                r7[r3] = r8     // Catch: NoSuchMethodException -> 0x003a, Exception -> 0x003e, Throwable -> 0x00a0
                java.lang.Class r8 = java.lang.Boolean.TYPE     // Catch: NoSuchMethodException -> 0x003a, Exception -> 0x003e, Throwable -> 0x00a0
                r9 = 2
                r7[r9] = r8     // Catch: NoSuchMethodException -> 0x003a, Exception -> 0x003e, Throwable -> 0x00a0
                java.lang.reflect.Method r4 = r4.getDeclaredMethod(r5, r7)     // Catch: NoSuchMethodException -> 0x003a, Exception -> 0x003e, Throwable -> 0x00a0
                r4.setAccessible(r3)     // Catch: NoSuchMethodException -> 0x003a, Exception -> 0x003e, Throwable -> 0x00a0
                java.lang.Object[] r5 = new java.lang.Object[r6]     // Catch: NoSuchMethodException -> 0x003a, Exception -> 0x003e, Throwable -> 0x00a0
                android.graphics.Bitmap$Config r6 = android.graphics.Bitmap.Config.RGB_565     // Catch: NoSuchMethodException -> 0x003a, Exception -> 0x003e, Throwable -> 0x00a0
                r5[r2] = r6     // Catch: NoSuchMethodException -> 0x003a, Exception -> 0x003e, Throwable -> 0x00a0
                r6 = -1
                java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch: NoSuchMethodException -> 0x003a, Exception -> 0x003e, Throwable -> 0x00a0
                r5[r3] = r6     // Catch: NoSuchMethodException -> 0x003a, Exception -> 0x003e, Throwable -> 0x00a0
                java.lang.Boolean r6 = java.lang.Boolean.valueOf(r2)     // Catch: NoSuchMethodException -> 0x003a, Exception -> 0x003e, Throwable -> 0x00a0
                r5[r9] = r6     // Catch: NoSuchMethodException -> 0x003a, Exception -> 0x003e, Throwable -> 0x00a0
                java.lang.Object r4 = r4.invoke(r0, r5)     // Catch: NoSuchMethodException -> 0x003a, Exception -> 0x003e, Throwable -> 0x00a0
                android.graphics.Bitmap r4 = (android.graphics.Bitmap) r4     // Catch: NoSuchMethodException -> 0x003a, Exception -> 0x003e, Throwable -> 0x00a0
                goto L_0x003f
            L_0x003a:
                r4 = move-exception
                com.tendcloud.tenddata.C2933hb.postSDKError(r4)     // Catch: Throwable -> 0x00a0
            L_0x003e:
                r4 = r1
            L_0x003f:
                if (r4 != 0) goto L_0x0054
                boolean r5 = r0.isDrawingCacheEnabled()     // Catch: RuntimeException -> 0x0054, Throwable -> 0x00a0
                java.lang.Boolean r1 = java.lang.Boolean.valueOf(r5)     // Catch: RuntimeException -> 0x0054, Throwable -> 0x00a0
                r0.setDrawingCacheEnabled(r3)     // Catch: RuntimeException -> 0x0054, Throwable -> 0x00a0
                r0.buildDrawingCache(r3)     // Catch: RuntimeException -> 0x0054, Throwable -> 0x00a0
                android.graphics.Bitmap r3 = r0.getDrawingCache()     // Catch: RuntimeException -> 0x0054, Throwable -> 0x00a0
                r4 = r3
            L_0x0054:
                r3 = 1065353216(0x3f800000, float:1.0)
                if (r4 == 0) goto L_0x008f
                int r5 = r4.getDensity()     // Catch: Throwable -> 0x00a0
                if (r5 == 0) goto L_0x0062
                r3 = 1126170624(0x43200000, float:160.0)
                float r5 = (float) r5     // Catch: Throwable -> 0x00a0
                float r3 = r3 / r5
            L_0x0062:
                int r5 = r4.getWidth()     // Catch: Throwable -> 0x00a0
                int r6 = r4.getHeight()     // Catch: Throwable -> 0x00a0
                int r7 = r4.getWidth()     // Catch: Throwable -> 0x00a0
                float r7 = (float) r7     // Catch: Throwable -> 0x00a0
                float r7 = r7 * r3
                double r7 = (double) r7     // Catch: Throwable -> 0x00a0
                r9 = 4602678819172646912(0x3fe0000000000000, double:0.5)
                double r7 = r7 + r9
                int r7 = (int) r7     // Catch: Throwable -> 0x00a0
                int r8 = r4.getHeight()     // Catch: Throwable -> 0x00a0
                float r8 = (float) r8     // Catch: Throwable -> 0x00a0
                float r8 = r8 * r3
                double r11 = (double) r8     // Catch: Throwable -> 0x00a0
                double r11 = r11 + r9
                int r8 = (int) r11     // Catch: Throwable -> 0x00a0
                if (r5 <= 0) goto L_0x008f
                if (r6 <= 0) goto L_0x008f
                if (r7 <= 0) goto L_0x008f
                if (r8 <= 0) goto L_0x008f
                com.tendcloud.tenddata.bo$a r5 = r13.mCachedBitmap     // Catch: Throwable -> 0x00a0
                r6 = 160(0xa0, float:2.24E-43)
                r5.recreate(r7, r8, r6, r4)     // Catch: Throwable -> 0x00a0
            L_0x008f:
                if (r1 == 0) goto L_0x009a
                boolean r1 = r1.booleanValue()     // Catch: Throwable -> 0x00a0
                if (r1 != 0) goto L_0x009a
                r0.setDrawingCacheEnabled(r2)     // Catch: Throwable -> 0x00a0
            L_0x009a:
                r14.scale = r3     // Catch: Throwable -> 0x00a0
                com.tendcloud.tenddata.bo$a r0 = r13.mCachedBitmap     // Catch: Throwable -> 0x00a0
                r14.screenshot = r0     // Catch: Throwable -> 0x00a0
            L_0x00a0:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tendcloud.tenddata.C2727bo.CallableC2730c.takeScreenshot(com.tendcloud.tenddata.bo$d):void");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.bo$a */
    /* loaded from: classes2.dex */
    public static class C2728a {
        private final Paint mPaint = new Paint(2);
        private Bitmap mCached = null;

        C2728a() {
        }

        synchronized void recreate(int i, int i2, int i3, Bitmap bitmap) {
            if (!(this.mCached != null && this.mCached.getWidth() == i && this.mCached.getHeight() == i2)) {
                try {
                    this.mCached = Bitmap.createBitmap(i, i2, Bitmap.Config.RGB_565);
                } catch (OutOfMemoryError unused) {
                    this.mCached = null;
                }
                if (this.mCached != null) {
                    this.mCached.setDensity(i3);
                }
            }
            if (this.mCached != null) {
                new Canvas(this.mCached).drawBitmap(bitmap, 0.0f, 0.0f, this.mPaint);
            }
        }

        synchronized String writeBitmapJSON(Bitmap.CompressFormat compressFormat, int i, OutputStream outputStream) {
            if (!(this.mCached == null || this.mCached.getWidth() == 0 || this.mCached.getHeight() == 0)) {
                outputStream.write(34);
                String a = C2727bo.m16230a(this.mCached);
                if (a != null && !a.equals(C2727bo.f13608f)) {
                    String unused = C2727bo.f13608f = a;
                    Base64OutputStream base64OutputStream = new Base64OutputStream(outputStream, 2);
                    this.mCached.compress(compressFormat, i, base64OutputStream);
                    base64OutputStream.flush();
                }
                outputStream.write(34);
                return C2727bo.m16230a(this.mCached);
            }
            outputStream.write("null".getBytes());
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.bo$d */
    /* loaded from: classes2.dex */
    public static class C2731d {
        final String activityName;
        final View rootView;
        C2728a screenshot = null;
        float scale = 1.0f;

        C2731d(String str, View view) {
            this.activityName = str;
            this.rootView = view;
        }
    }

    /* renamed from: a */
    static String m16230a(Bitmap bitmap) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            return C2855es.m15798a(MessageDigest.getInstance("MD5").digest(byteArrayOutputStream.toByteArray()));
        } catch (Throwable unused) {
            return "";
        }
    }
}
