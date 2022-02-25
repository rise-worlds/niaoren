package com.lody.virtual.server.notification;

import android.annotation.TargetApi;
import android.app.Notification;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;
import android.widget.RemoteViews;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.helper.compat.BuildCompat;
import com.lody.virtual.helper.utils.BitmapUtils;
import com.lody.virtual.helper.utils.Reflect;
import java.util.ArrayList;
import org.apache.http.HttpHost;
import org.apache.tools.ant.types.selectors.SizeSelector;
import p110z1.R_Hide;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class NotificationFixer {
    private static final String TAG = NotificationCompat.TAG;
    private NotificationCompat mNotificationCompat;

    /* JADX INFO: Access modifiers changed from: package-private */
    public NotificationFixer(NotificationCompat notificationCompat) {
        this.mNotificationCompat = notificationCompat;
    }

    private static void fixNotificationIcon(Context context, Notification notification, Notification.Builder builder) {
        Bitmap drawableToBitmap;
        Bitmap drawableToBitmap2;
        if (Build.VERSION.SDK_INT < 23) {
            builder.setSmallIcon(notification.icon);
            builder.setLargeIcon(notification.largeIcon);
            return;
        }
        Icon smallIcon = notification.getSmallIcon();
        if (!(smallIcon == null || (drawableToBitmap2 = BitmapUtils.drawableToBitmap(smallIcon.loadDrawable(context))) == null)) {
            builder.setSmallIcon(Icon.createWithBitmap(drawableToBitmap2));
        }
        Icon largeIcon = notification.getLargeIcon();
        if (largeIcon != null && (drawableToBitmap = BitmapUtils.drawableToBitmap(largeIcon.loadDrawable(context))) != null) {
            builder.setLargeIcon(Icon.createWithBitmap(drawableToBitmap));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @TargetApi(23)
    public void fixIcon(Icon icon, Context context, boolean z) {
        if (icon == null || p110z1.Icon.mType.get(icon).intValue() != 2) {
            return;
        }
        if (z) {
            p110z1.Icon.mObj1.set(icon, context.getResources());
            p110z1.Icon.mString1.set(icon, context.getPackageName());
            return;
        }
        p110z1.Icon.mObj1.set(icon, BitmapUtils.drawableToBitmap(icon.loadDrawable(context)));
        p110z1.Icon.mString1.set(icon, null);
        p110z1.Icon.mType.set(icon, 1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @TargetApi(21)
    public void fixNotificationRemoteViews(Context context, Notification notification) {
        Notification.Builder builder;
        try {
            builder = (Notification.Builder) Reflect.m18999on((Class<?>) Notification.Builder.class).create(context, notification).get();
        } catch (Exception unused) {
            builder = null;
        }
        if (builder != null) {
            Notification build = builder.build();
            if (notification.tickerView == null) {
                notification.tickerView = build.tickerView;
            }
            if (notification.contentView == null) {
                notification.contentView = build.contentView;
            }
            if (notification.bigContentView == null) {
                notification.bigContentView = build.bigContentView;
            }
            if (notification.headsUpContentView == null) {
                notification.headsUpContentView = build.headsUpContentView;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean fixRemoteViewActions(Context context, boolean z, RemoteViews remoteViews) {
        boolean z2 = false;
        if (remoteViews != null) {
            int i = R_Hide.C5190b.icon.get();
            ArrayList<BitmapReflectionAction> arrayList = new ArrayList();
            ArrayList arrayList2 = (ArrayList) Reflect.m18998on(remoteViews).get("mActions");
            if (arrayList2 != null) {
                boolean z3 = false;
                for (int size = arrayList2.size() - 1; size >= 0; size--) {
                    Object obj = arrayList2.get(size);
                    if (obj != null) {
                        if (obj.getClass().getSimpleName().endsWith("TextViewDrawableAction")) {
                            arrayList2.remove(obj);
                        } else if (ReflectionActionCompat.isInstance(obj)) {
                            int intValue = ((Integer) Reflect.m18998on(obj).get("viewId")).intValue();
                            String str = (String) Reflect.m18998on(obj).get("methodName");
                            int intValue2 = ((Integer) Reflect.m18998on(obj).get("type")).intValue();
                            Object obj2 = Reflect.m18998on(obj).get(SizeSelector.SIZE_KEY);
                            if (!z3) {
                                z3 = intValue == i;
                                if (z3 && intValue2 == 4 && ((Integer) obj2).intValue() == 0) {
                                    z3 = false;
                                }
                            }
                            if (str.equals("setImageResource")) {
                                arrayList.add(new BitmapReflectionAction(intValue, "setImageBitmap", BitmapUtils.drawableToBitmap(context.getResources().getDrawable(((Integer) obj2).intValue()))));
                                arrayList2.remove(obj);
                            } else if (str.equals("setText") && intValue2 == 4) {
                                Reflect.m18998on(obj).set("type", 9);
                                Reflect.m18998on(obj).set(SizeSelector.SIZE_KEY, context.getResources().getString(((Integer) obj2).intValue()));
                            } else if (str.equals("setLabelFor")) {
                                arrayList2.remove(obj);
                            } else if (str.equals("setBackgroundResource")) {
                                arrayList2.remove(obj);
                            } else if (str.equals("setImageURI")) {
                                if (!((Uri) obj2).getScheme().startsWith(HttpHost.DEFAULT_SCHEME_NAME)) {
                                    arrayList2.remove(obj);
                                }
                            } else if (Build.VERSION.SDK_INT >= 23 && (obj2 instanceof Icon)) {
                                fixIcon((Icon) obj2, context, z);
                            }
                        }
                    }
                }
                for (BitmapReflectionAction bitmapReflectionAction : arrayList) {
                    remoteViews.setBitmap(bitmapReflectionAction.viewId, bitmapReflectionAction.methodName, bitmapReflectionAction.bitmap);
                }
                z2 = z3;
            }
            if (Build.VERSION.SDK_INT < 21) {
                p110z1.RemoteViews.mPackage.set(remoteViews, VirtualCore.get().getHostPkg());
            }
        }
        return z2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void fixIconImage(Resources resources, RemoteViews remoteViews, boolean z, Notification notification) {
        if (remoteViews != null && notification.icon != 0 && this.mNotificationCompat.isSystemLayout(remoteViews)) {
            try {
                int i = R_Hide.C5190b.icon.get();
                if (!z && notification.largeIcon == null) {
                    Bitmap bitmap = null;
                    try {
                        Drawable drawable = resources.getDrawable(notification.icon);
                        drawable.setLevel(notification.iconLevel);
                        bitmap = BitmapUtils.drawableToBitmap(drawable);
                    } catch (Throwable unused) {
                    }
                    remoteViews.setImageViewBitmap(i, bitmap);
                    if (BuildCompat.isEMUI() && notification.largeIcon == null) {
                        notification.largeIcon = bitmap;
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class BitmapReflectionAction {
        Bitmap bitmap;
        String methodName;
        int viewId;

        BitmapReflectionAction(int i, String str, Bitmap bitmap) {
            this.viewId = i;
            this.methodName = str;
            this.bitmap = bitmap;
        }
    }
}
