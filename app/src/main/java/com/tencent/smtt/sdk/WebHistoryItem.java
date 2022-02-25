package com.tencent.smtt.sdk;

import android.graphics.Bitmap;
import com.tencent.smtt.export.external.interfaces.IX5WebHistoryItem;

/* loaded from: classes2.dex */
public class WebHistoryItem {

    /* renamed from: a */
    private IX5WebHistoryItem f13002a = null;

    /* renamed from: b */
    private android.webkit.WebHistoryItem f13003b = null;

    private WebHistoryItem() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static WebHistoryItem m16939a(IX5WebHistoryItem iX5WebHistoryItem) {
        if (iX5WebHistoryItem == null) {
            return null;
        }
        WebHistoryItem webHistoryItem = new WebHistoryItem();
        webHistoryItem.f13002a = iX5WebHistoryItem;
        return webHistoryItem;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static WebHistoryItem m16940a(android.webkit.WebHistoryItem webHistoryItem) {
        if (webHistoryItem == null) {
            return null;
        }
        WebHistoryItem webHistoryItem2 = new WebHistoryItem();
        webHistoryItem2.f13003b = webHistoryItem;
        return webHistoryItem2;
    }

    public String getUrl() {
        IX5WebHistoryItem iX5WebHistoryItem = this.f13002a;
        if (iX5WebHistoryItem != null) {
            return iX5WebHistoryItem.getUrl();
        }
        return this.f13003b.getUrl();
    }

    public String getOriginalUrl() {
        IX5WebHistoryItem iX5WebHistoryItem = this.f13002a;
        if (iX5WebHistoryItem != null) {
            return iX5WebHistoryItem.getOriginalUrl();
        }
        return this.f13003b.getOriginalUrl();
    }

    public String getTitle() {
        IX5WebHistoryItem iX5WebHistoryItem = this.f13002a;
        if (iX5WebHistoryItem != null) {
            return iX5WebHistoryItem.getTitle();
        }
        return this.f13003b.getTitle();
    }

    public Bitmap getFavicon() {
        IX5WebHistoryItem iX5WebHistoryItem = this.f13002a;
        if (iX5WebHistoryItem != null) {
            return iX5WebHistoryItem.getFavicon();
        }
        return this.f13003b.getFavicon();
    }
}
