package com.tencent.smtt.sdk;

import com.tencent.smtt.export.external.interfaces.IX5WebBackForwardList;

/* loaded from: classes2.dex */
public class WebBackForwardList {

    /* renamed from: a */
    private IX5WebBackForwardList f13000a = null;

    /* renamed from: b */
    private android.webkit.WebBackForwardList f13001b = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static WebBackForwardList m16941a(IX5WebBackForwardList iX5WebBackForwardList) {
        if (iX5WebBackForwardList == null) {
            return null;
        }
        WebBackForwardList webBackForwardList = new WebBackForwardList();
        webBackForwardList.f13000a = iX5WebBackForwardList;
        return webBackForwardList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static WebBackForwardList m16942a(android.webkit.WebBackForwardList webBackForwardList) {
        if (webBackForwardList == null) {
            return null;
        }
        WebBackForwardList webBackForwardList2 = new WebBackForwardList();
        webBackForwardList2.f13001b = webBackForwardList;
        return webBackForwardList2;
    }

    public WebHistoryItem getCurrentItem() {
        IX5WebBackForwardList iX5WebBackForwardList = this.f13000a;
        if (iX5WebBackForwardList != null) {
            return WebHistoryItem.m16939a(iX5WebBackForwardList.getCurrentItem());
        }
        return WebHistoryItem.m16940a(this.f13001b.getCurrentItem());
    }

    public int getCurrentIndex() {
        IX5WebBackForwardList iX5WebBackForwardList = this.f13000a;
        if (iX5WebBackForwardList != null) {
            return iX5WebBackForwardList.getCurrentIndex();
        }
        return this.f13001b.getCurrentIndex();
    }

    public WebHistoryItem getItemAtIndex(int i) {
        IX5WebBackForwardList iX5WebBackForwardList = this.f13000a;
        if (iX5WebBackForwardList != null) {
            return WebHistoryItem.m16939a(iX5WebBackForwardList.getItemAtIndex(i));
        }
        return WebHistoryItem.m16940a(this.f13001b.getItemAtIndex(i));
    }

    public int getSize() {
        IX5WebBackForwardList iX5WebBackForwardList = this.f13000a;
        if (iX5WebBackForwardList != null) {
            return iX5WebBackForwardList.getSize();
        }
        return this.f13001b.getSize();
    }
}
