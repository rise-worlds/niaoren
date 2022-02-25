package com.lody.virtual.server.notification;

import android.app.PendingIntent;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.TextView;
import com.lody.virtual.helper.utils.Reflect;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
class PendIntentCompat {
    private Map<Integer, PendingIntent> clickIntents;
    private RemoteViews mRemoteViews;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PendIntentCompat(RemoteViews remoteViews) {
        this.mRemoteViews = remoteViews;
    }

    public int findPendIntents() {
        if (this.clickIntents == null) {
            this.clickIntents = getClickIntents(this.mRemoteViews);
        }
        return this.clickIntents.size();
    }

    public void setPendIntent(RemoteViews remoteViews, View view, View view2) {
        if (findPendIntents() > 0) {
            ArrayList arrayList = new ArrayList();
            int i = 0;
            for (Map.Entry<Integer, PendingIntent> entry : this.clickIntents.entrySet()) {
                View findViewById = view2.findViewById(entry.getKey().intValue());
                if (findViewById != null) {
                    arrayList.add(new RectInfo(getRect(findViewById), entry.getValue(), i));
                    i++;
                }
            }
            if (view instanceof ViewGroup) {
                setIntentByViewGroup(remoteViews, (ViewGroup) view, arrayList);
            }
        }
    }

    private Rect getRect(View view) {
        Rect rect = new Rect();
        rect.top = view.getTop();
        rect.left = view.getLeft();
        rect.right = view.getRight();
        rect.bottom = view.getBottom();
        ViewParent parent = view.getParent();
        if (parent != null && (parent instanceof ViewGroup)) {
            Rect rect2 = getRect((ViewGroup) parent);
            rect.top += rect2.top;
            rect.left += rect2.left;
            rect.right += rect2.left;
            rect.bottom += rect2.top;
        }
        return rect;
    }

    private void setIntentByViewGroup(RemoteViews remoteViews, ViewGroup viewGroup, List<RectInfo> list) {
        RectInfo findIntent;
        int childCount = viewGroup.getChildCount();
        viewGroup.getHitRect(new Rect());
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt instanceof ViewGroup) {
                setIntentByViewGroup(remoteViews, (ViewGroup) childAt, list);
            } else if (((childAt instanceof TextView) || (childAt instanceof ImageView)) && (findIntent = findIntent(getRect(childAt), list)) != null) {
                remoteViews.setOnClickPendingIntent(childAt.getId(), findIntent.mPendingIntent);
            }
        }
    }

    private RectInfo findIntent(Rect rect, List<RectInfo> list) {
        int i = 0;
        RectInfo rectInfo = null;
        for (RectInfo rectInfo2 : list) {
            int overlapArea = getOverlapArea(rect, rectInfo2.rect);
            if (overlapArea > i) {
                if (overlapArea == 0) {
                    Log.w("PendingIntentCompat", "find two:" + rectInfo2.rect);
                }
                rectInfo = rectInfo2;
                i = overlapArea;
            }
        }
        return rectInfo;
    }

    private int getOverlapArea(Rect rect, Rect rect2) {
        Rect rect3 = new Rect();
        rect3.left = Math.max(rect.left, rect2.left);
        rect3.top = Math.max(rect.top, rect2.top);
        rect3.right = Math.min(rect.right, rect2.right);
        rect3.bottom = Math.min(rect.bottom, rect2.bottom);
        if (rect3.left >= rect3.right || rect3.top >= rect3.bottom) {
            return 0;
        }
        return (rect3.right - rect3.left) * (rect3.bottom - rect3.top);
    }

    private Map<Integer, PendingIntent> getClickIntents(RemoteViews remoteViews) {
        String str;
        HashMap hashMap = new HashMap();
        if (remoteViews == null) {
            return hashMap;
        }
        Object obj = null;
        try {
            obj = Reflect.m18998on(remoteViews).get("mActions");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (obj != null && (obj instanceof Collection)) {
            for (Object obj2 : (Collection) obj) {
                if (obj2 != null) {
                    try {
                        str = (String) Reflect.m18998on(obj2).call("getActionName").get();
                    } catch (Exception unused) {
                        str = obj2.getClass().getSimpleName();
                    }
                    if ("SetOnClickPendingIntent".equalsIgnoreCase(str)) {
                        hashMap.put(Integer.valueOf(((Integer) Reflect.m18998on(obj2).get("viewId")).intValue()), (PendingIntent) Reflect.m18998on(obj2).get("pendingIntent"));
                    }
                }
            }
        }
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class RectInfo {
        int index;
        PendingIntent mPendingIntent;
        Rect rect;

        public RectInfo(Rect rect, PendingIntent pendingIntent, int i) {
            this.rect = rect;
            this.mPendingIntent = pendingIntent;
            this.index = i;
        }

        public String toString() {
            return "RectInfo{rect=" + this.rect + '}';
        }
    }
}
