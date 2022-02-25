package com.tencent.smtt.sdk;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.webkit.WebSettings;
import com.tencent.smtt.export.external.interfaces.IX5WebSettings;
import com.tencent.smtt.sdk.TbsListener;
import com.tencent.smtt.utils.ReflectionUtils;

/* loaded from: classes2.dex */
public class WebSettings {
    public static final int LOAD_CACHE_ELSE_NETWORK = 1;
    public static final int LOAD_CACHE_ONLY = 3;
    public static final int LOAD_DEFAULT = -1;
    public static final int LOAD_NORMAL = 0;
    public static final int LOAD_NO_CACHE = 2;

    /* renamed from: a */
    private IX5WebSettings f13009a;

    /* renamed from: b */
    private android.webkit.WebSettings f13010b;

    /* renamed from: c */
    private boolean f13011c;

    /* loaded from: classes2.dex */
    public enum LayoutAlgorithm {
        NORMAL,
        SINGLE_COLUMN,
        NARROW_COLUMNS
    }

    /* loaded from: classes2.dex */
    public enum PluginState {
        ON,
        ON_DEMAND,
        OFF
    }

    /* loaded from: classes2.dex */
    public enum RenderPriority {
        NORMAL,
        HIGH,
        LOW
    }

    /* loaded from: classes2.dex */
    public enum TextSize {
        SMALLEST(50),
        SMALLER(75),
        NORMAL(100),
        LARGER(TbsListener.ErrorCode.DOWNLOAD_THROWABLE),
        LARGEST(150);
        
        int value;

        TextSize(int i) {
            this.value = i;
        }
    }

    /* loaded from: classes2.dex */
    public enum ZoomDensity {
        FAR(150),
        MEDIUM(100),
        CLOSE(75);
        
        int value;

        ZoomDensity(int i) {
            this.value = i;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public WebSettings(IX5WebSettings iX5WebSettings) {
        this.f13009a = null;
        this.f13010b = null;
        this.f13011c = false;
        this.f13009a = iX5WebSettings;
        this.f13010b = null;
        this.f13011c = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public WebSettings(android.webkit.WebSettings webSettings) {
        this.f13009a = null;
        this.f13010b = null;
        this.f13011c = false;
        this.f13009a = null;
        this.f13010b = webSettings;
        this.f13011c = false;
    }

    public void setNavDump(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f13011c && (iX5WebSettings = this.f13009a) != null) {
            iX5WebSettings.setNavDump(z);
        } else if (!this.f13011c && (webSettings = this.f13010b) != null) {
            ReflectionUtils.m16404a(webSettings, "setNavDump", new Class[]{Boolean.TYPE}, Boolean.valueOf(z));
        }
    }

    public synchronized int getMixedContentMode() {
        int i = -1;
        if (this.f13011c && this.f13009a != null) {
            return this.f13009a.getMixedContentMode();
        } else if (Build.VERSION.SDK_INT < 21) {
            return -1;
        } else {
            Object a = ReflectionUtils.m16404a(this.f13010b, "getMixedContentMode", new Class[0], new Object[0]);
            if (a != null) {
                i = ((Integer) a).intValue();
            }
            return i;
        }
    }

    public boolean getNavDump() {
        android.webkit.WebSettings webSettings;
        Object a;
        IX5WebSettings iX5WebSettings;
        if (this.f13011c && (iX5WebSettings = this.f13009a) != null) {
            return iX5WebSettings.getNavDump();
        }
        if (this.f13011c || (webSettings = this.f13010b) == null || (a = ReflectionUtils.m16406a(webSettings, "getNavDump")) == null) {
            return false;
        }
        return ((Boolean) a).booleanValue();
    }

    public void setSupportZoom(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f13011c && (iX5WebSettings = this.f13009a) != null) {
            iX5WebSettings.setSupportZoom(z);
        } else if (!this.f13011c && (webSettings = this.f13010b) != null) {
            webSettings.setSupportZoom(z);
        }
    }

    public boolean supportZoom() {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f13011c && (iX5WebSettings = this.f13009a) != null) {
            return iX5WebSettings.supportZoom();
        }
        if (this.f13011c || (webSettings = this.f13010b) == null) {
            return false;
        }
        return webSettings.supportZoom();
    }

    @TargetApi(3)
    public void setBuiltInZoomControls(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f13011c && (iX5WebSettings = this.f13009a) != null) {
            iX5WebSettings.setBuiltInZoomControls(z);
        } else if (!this.f13011c && (webSettings = this.f13010b) != null) {
            webSettings.setBuiltInZoomControls(z);
        }
    }

    @TargetApi(3)
    public boolean getBuiltInZoomControls() {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f13011c && (iX5WebSettings = this.f13009a) != null) {
            return iX5WebSettings.getBuiltInZoomControls();
        }
        if (this.f13011c || (webSettings = this.f13010b) == null) {
            return false;
        }
        return webSettings.getBuiltInZoomControls();
    }

    @TargetApi(11)
    public void setDisplayZoomControls(boolean z) {
        IX5WebSettings iX5WebSettings;
        if (this.f13011c && (iX5WebSettings = this.f13009a) != null) {
            iX5WebSettings.setDisplayZoomControls(z);
        } else if (!this.f13011c && this.f13010b != null && Build.VERSION.SDK_INT >= 11) {
            ReflectionUtils.m16404a(this.f13010b, "setDisplayZoomControls", new Class[]{Boolean.TYPE}, Boolean.valueOf(z));
        }
    }

    @TargetApi(11)
    public boolean getDisplayZoomControls() {
        Object a;
        IX5WebSettings iX5WebSettings;
        if (this.f13011c && (iX5WebSettings = this.f13009a) != null) {
            return iX5WebSettings.getDisplayZoomControls();
        }
        if (this.f13011c || this.f13010b == null || Build.VERSION.SDK_INT < 11 || (a = ReflectionUtils.m16406a(this.f13010b, "getDisplayZoomControls")) == null) {
            return false;
        }
        return ((Boolean) a).booleanValue();
    }

    @TargetApi(3)
    public void setAllowFileAccess(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f13011c && (iX5WebSettings = this.f13009a) != null) {
            iX5WebSettings.setAllowFileAccess(z);
        } else if (!this.f13011c && (webSettings = this.f13010b) != null) {
            webSettings.setAllowFileAccess(z);
        }
    }

    @TargetApi(3)
    public boolean getAllowFileAccess() {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f13011c && (iX5WebSettings = this.f13009a) != null) {
            return iX5WebSettings.getAllowFileAccess();
        }
        if (this.f13011c || (webSettings = this.f13010b) == null) {
            return false;
        }
        return webSettings.getAllowFileAccess();
    }

    @TargetApi(11)
    public void setAllowContentAccess(boolean z) {
        IX5WebSettings iX5WebSettings;
        if (this.f13011c && (iX5WebSettings = this.f13009a) != null) {
            iX5WebSettings.setAllowContentAccess(z);
        } else if (!this.f13011c && this.f13010b != null && Build.VERSION.SDK_INT >= 11) {
            ReflectionUtils.m16404a(this.f13010b, "setAllowContentAccess", new Class[]{Boolean.TYPE}, Boolean.valueOf(z));
        }
    }

    @TargetApi(21)
    public void setMixedContentMode(int i) {
        if ((!this.f13011c || this.f13009a == null) && !this.f13011c && this.f13010b != null && Build.VERSION.SDK_INT >= 21) {
            ReflectionUtils.m16404a(this.f13010b, "setMixedContentMode", new Class[]{Integer.TYPE}, Integer.valueOf(i));
        }
    }

    @TargetApi(11)
    public boolean getAllowContentAccess() {
        Object a;
        IX5WebSettings iX5WebSettings;
        if (this.f13011c && (iX5WebSettings = this.f13009a) != null) {
            return iX5WebSettings.getAllowContentAccess();
        }
        if (this.f13011c || this.f13010b == null || Build.VERSION.SDK_INT < 11 || (a = ReflectionUtils.m16406a(this.f13010b, "getAllowContentAccess")) == null) {
            return false;
        }
        return ((Boolean) a).booleanValue();
    }

    @TargetApi(7)
    public void setLoadWithOverviewMode(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f13011c && (iX5WebSettings = this.f13009a) != null) {
            iX5WebSettings.setLoadWithOverviewMode(z);
        } else if (!this.f13011c && (webSettings = this.f13010b) != null) {
            webSettings.setLoadWithOverviewMode(z);
        }
    }

    @TargetApi(7)
    public boolean getLoadWithOverviewMode() {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f13011c && (iX5WebSettings = this.f13009a) != null) {
            return iX5WebSettings.getLoadWithOverviewMode();
        }
        if (this.f13011c || (webSettings = this.f13010b) == null) {
            return false;
        }
        return webSettings.getLoadWithOverviewMode();
    }

    @TargetApi(11)
    @Deprecated
    public void setEnableSmoothTransition(boolean z) {
        IX5WebSettings iX5WebSettings;
        if (this.f13011c && (iX5WebSettings = this.f13009a) != null) {
            iX5WebSettings.setEnableSmoothTransition(z);
        } else if (!this.f13011c && this.f13010b != null && Build.VERSION.SDK_INT >= 11) {
            ReflectionUtils.m16404a(this.f13010b, "setEnableSmoothTransition", new Class[]{Boolean.TYPE}, Boolean.valueOf(z));
        }
    }

    @Deprecated
    public boolean enableSmoothTransition() {
        Object a;
        IX5WebSettings iX5WebSettings;
        if (this.f13011c && (iX5WebSettings = this.f13009a) != null) {
            return iX5WebSettings.enableSmoothTransition();
        }
        if (this.f13011c || this.f13010b == null || Build.VERSION.SDK_INT < 11 || (a = ReflectionUtils.m16406a(this.f13010b, "enableSmoothTransition")) == null) {
            return false;
        }
        return ((Boolean) a).booleanValue();
    }

    @Deprecated
    public void setUseWebViewBackgroundForOverscrollBackground(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f13011c && (iX5WebSettings = this.f13009a) != null) {
            iX5WebSettings.setUseWebViewBackgroundForOverscrollBackground(z);
        } else if (!this.f13011c && (webSettings = this.f13010b) != null) {
            ReflectionUtils.m16404a(webSettings, "setUseWebViewBackgroundForOverscrollBackground", new Class[]{Boolean.TYPE}, Boolean.valueOf(z));
        }
    }

    @Deprecated
    public boolean getUseWebViewBackgroundForOverscrollBackground() {
        android.webkit.WebSettings webSettings;
        Object a;
        IX5WebSettings iX5WebSettings;
        if (this.f13011c && (iX5WebSettings = this.f13009a) != null) {
            return iX5WebSettings.getUseWebViewBackgroundForOverscrollBackground();
        }
        if (this.f13011c || (webSettings = this.f13010b) == null || (a = ReflectionUtils.m16406a(webSettings, "getUseWebViewBackgroundForOverscrollBackground")) == null) {
            return false;
        }
        return ((Boolean) a).booleanValue();
    }

    public void setSaveFormData(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f13011c && (iX5WebSettings = this.f13009a) != null) {
            iX5WebSettings.setSaveFormData(z);
        } else if (!this.f13011c && (webSettings = this.f13010b) != null) {
            webSettings.setSaveFormData(z);
        }
    }

    public boolean getSaveFormData() {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f13011c && (iX5WebSettings = this.f13009a) != null) {
            return iX5WebSettings.getSaveFormData();
        }
        if (this.f13011c || (webSettings = this.f13010b) == null) {
            return false;
        }
        return webSettings.getSaveFormData();
    }

    public void setSavePassword(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f13011c && (iX5WebSettings = this.f13009a) != null) {
            iX5WebSettings.setSavePassword(z);
        } else if (!this.f13011c && (webSettings = this.f13010b) != null) {
            webSettings.setSavePassword(z);
        }
    }

    public boolean getSavePassword() {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f13011c && (iX5WebSettings = this.f13009a) != null) {
            return iX5WebSettings.getSavePassword();
        }
        if (this.f13011c || (webSettings = this.f13010b) == null) {
            return false;
        }
        return webSettings.getSavePassword();
    }

    @TargetApi(14)
    public synchronized void setTextZoom(int i) {
        if (this.f13011c && this.f13009a != null) {
            this.f13009a.setTextZoom(i);
        } else if (!this.f13011c && this.f13010b != null) {
            if (Build.VERSION.SDK_INT >= 14) {
                try {
                    this.f13010b.setTextZoom(i);
                } catch (Exception unused) {
                    ReflectionUtils.m16404a(this.f13010b, "setTextZoom", new Class[]{Integer.TYPE}, Integer.valueOf(i));
                }
            }
        }
    }

    @TargetApi(14)
    public synchronized int getTextZoom() {
        if (this.f13011c && this.f13009a != null) {
            return this.f13009a.getTextZoom();
        } else if (this.f13011c || this.f13010b == null) {
            return 0;
        } else {
            if (Build.VERSION.SDK_INT < 14) {
                return 0;
            }
            try {
                return this.f13010b.getTextZoom();
            } catch (Exception unused) {
                Object a = ReflectionUtils.m16406a(this.f13010b, "getTextZoom");
                if (a == null) {
                    return 0;
                }
                return ((Integer) a).intValue();
            }
        }
    }

    public void setTextSize(TextSize textSize) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f13011c && (iX5WebSettings = this.f13009a) != null) {
            iX5WebSettings.setTextSize(IX5WebSettings.TextSize.valueOf(textSize.name()));
        } else if (!this.f13011c && (webSettings = this.f13010b) != null) {
            webSettings.setTextSize(WebSettings.TextSize.valueOf(textSize.name()));
        }
    }

    public TextSize getTextSize() {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f13011c && (iX5WebSettings = this.f13009a) != null) {
            return TextSize.valueOf(iX5WebSettings.getTextSize().name());
        }
        if (this.f13011c || (webSettings = this.f13010b) == null) {
            return null;
        }
        return TextSize.valueOf(webSettings.getTextSize().name());
    }

    @TargetApi(7)
    public void setDefaultZoom(ZoomDensity zoomDensity) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f13011c && (iX5WebSettings = this.f13009a) != null) {
            iX5WebSettings.setDefaultZoom(IX5WebSettings.ZoomDensity.valueOf(zoomDensity.name()));
        } else if (!this.f13011c && (webSettings = this.f13010b) != null) {
            webSettings.setDefaultZoom(WebSettings.ZoomDensity.valueOf(zoomDensity.name()));
        }
    }

    @TargetApi(7)
    public ZoomDensity getDefaultZoom() {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f13011c && (iX5WebSettings = this.f13009a) != null) {
            return ZoomDensity.valueOf(iX5WebSettings.getDefaultZoom().name());
        }
        if (this.f13011c || (webSettings = this.f13010b) == null) {
            return null;
        }
        return ZoomDensity.valueOf(webSettings.getDefaultZoom().name());
    }

    public void setLightTouchEnabled(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f13011c && (iX5WebSettings = this.f13009a) != null) {
            iX5WebSettings.setLightTouchEnabled(z);
        } else if (!this.f13011c && (webSettings = this.f13010b) != null) {
            webSettings.setLightTouchEnabled(z);
        }
    }

    public boolean getLightTouchEnabled() {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f13011c && (iX5WebSettings = this.f13009a) != null) {
            return iX5WebSettings.getLightTouchEnabled();
        }
        if (this.f13011c || (webSettings = this.f13010b) == null) {
            return false;
        }
        return webSettings.getLightTouchEnabled();
    }

    public void setUserAgent(String str) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f13011c && (iX5WebSettings = this.f13009a) != null) {
            iX5WebSettings.setUserAgent(str);
        } else if (!this.f13011c && (webSettings = this.f13010b) != null) {
            webSettings.setUserAgentString(str);
        }
    }

    @TargetApi(3)
    public String getUserAgentString() {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (!this.f13011c || (iX5WebSettings = this.f13009a) == null) {
            return (this.f13011c || (webSettings = this.f13010b) == null) ? "" : webSettings.getUserAgentString();
        }
        return iX5WebSettings.getUserAgentString();
    }

    @TargetApi(3)
    public void setUserAgentString(String str) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f13011c && (iX5WebSettings = this.f13009a) != null) {
            iX5WebSettings.setUserAgentString(str);
        } else if (!this.f13011c && (webSettings = this.f13010b) != null) {
            webSettings.setUserAgentString(str);
        }
    }

    public void setUseWideViewPort(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f13011c && (iX5WebSettings = this.f13009a) != null) {
            iX5WebSettings.setUseWideViewPort(z);
        } else if (!this.f13011c && (webSettings = this.f13010b) != null) {
            webSettings.setUseWideViewPort(z);
        }
    }

    public synchronized boolean getUseWideViewPort() {
        if (this.f13011c && this.f13009a != null) {
            return this.f13009a.getUseWideViewPort();
        } else if (this.f13011c || this.f13010b == null) {
            return false;
        } else {
            return this.f13010b.getUseWideViewPort();
        }
    }

    public void setSupportMultipleWindows(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f13011c && (iX5WebSettings = this.f13009a) != null) {
            iX5WebSettings.setSupportMultipleWindows(z);
        } else if (!this.f13011c && (webSettings = this.f13010b) != null) {
            webSettings.setSupportMultipleWindows(z);
        }
    }

    public synchronized boolean supportMultipleWindows() {
        if (this.f13011c && this.f13009a != null) {
            return this.f13009a.supportMultipleWindows();
        } else if (this.f13011c || this.f13010b == null) {
            return false;
        } else {
            return this.f13010b.supportMultipleWindows();
        }
    }

    public void setLayoutAlgorithm(LayoutAlgorithm layoutAlgorithm) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f13011c && (iX5WebSettings = this.f13009a) != null) {
            iX5WebSettings.setLayoutAlgorithm(IX5WebSettings.LayoutAlgorithm.valueOf(layoutAlgorithm.name()));
        } else if (!this.f13011c && (webSettings = this.f13010b) != null) {
            webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.valueOf(layoutAlgorithm.name()));
        }
    }

    public synchronized LayoutAlgorithm getLayoutAlgorithm() {
        if (this.f13011c && this.f13009a != null) {
            return LayoutAlgorithm.valueOf(this.f13009a.getLayoutAlgorithm().name());
        } else if (this.f13011c || this.f13010b == null) {
            return null;
        } else {
            return LayoutAlgorithm.valueOf(this.f13010b.getLayoutAlgorithm().name());
        }
    }

    public synchronized void setStandardFontFamily(String str) {
        if (this.f13011c && this.f13009a != null) {
            this.f13009a.setStandardFontFamily(str);
        } else if (!this.f13011c && this.f13010b != null) {
            this.f13010b.setStandardFontFamily(str);
        }
    }

    public synchronized String getStandardFontFamily() {
        if (this.f13011c && this.f13009a != null) {
            return this.f13009a.getStandardFontFamily();
        } else if (this.f13011c || this.f13010b == null) {
            return "";
        } else {
            return this.f13010b.getStandardFontFamily();
        }
    }

    public synchronized void setFixedFontFamily(String str) {
        if (this.f13011c && this.f13009a != null) {
            this.f13009a.setFixedFontFamily(str);
        } else if (!this.f13011c && this.f13010b != null) {
            this.f13010b.setFixedFontFamily(str);
        }
    }

    public synchronized String getFixedFontFamily() {
        if (this.f13011c && this.f13009a != null) {
            return this.f13009a.getFixedFontFamily();
        } else if (this.f13011c || this.f13010b == null) {
            return "";
        } else {
            return this.f13010b.getFixedFontFamily();
        }
    }

    public synchronized void setSansSerifFontFamily(String str) {
        if (this.f13011c && this.f13009a != null) {
            this.f13009a.setSansSerifFontFamily(str);
        } else if (!this.f13011c && this.f13010b != null) {
            this.f13010b.setSansSerifFontFamily(str);
        }
    }

    public synchronized String getSansSerifFontFamily() {
        if (this.f13011c && this.f13009a != null) {
            return this.f13009a.getSansSerifFontFamily();
        } else if (this.f13011c || this.f13010b == null) {
            return "";
        } else {
            return this.f13010b.getSansSerifFontFamily();
        }
    }

    public synchronized void setSerifFontFamily(String str) {
        if (this.f13011c && this.f13009a != null) {
            this.f13009a.setSerifFontFamily(str);
        } else if (!this.f13011c && this.f13010b != null) {
            this.f13010b.setSerifFontFamily(str);
        }
    }

    public synchronized String getSerifFontFamily() {
        if (this.f13011c && this.f13009a != null) {
            return this.f13009a.getSerifFontFamily();
        } else if (this.f13011c || this.f13010b == null) {
            return "";
        } else {
            return this.f13010b.getSerifFontFamily();
        }
    }

    public synchronized void setCursiveFontFamily(String str) {
        if (this.f13011c && this.f13009a != null) {
            this.f13009a.setCursiveFontFamily(str);
        } else if (!this.f13011c && this.f13010b != null) {
            this.f13010b.setCursiveFontFamily(str);
        }
    }

    public synchronized String getCursiveFontFamily() {
        if (this.f13011c && this.f13009a != null) {
            return this.f13009a.getCursiveFontFamily();
        } else if (this.f13011c || this.f13010b == null) {
            return "";
        } else {
            return this.f13010b.getCursiveFontFamily();
        }
    }

    public synchronized void setFantasyFontFamily(String str) {
        if (this.f13011c && this.f13009a != null) {
            this.f13009a.setFantasyFontFamily(str);
        } else if (!this.f13011c && this.f13010b != null) {
            this.f13010b.setFantasyFontFamily(str);
        }
    }

    public synchronized String getFantasyFontFamily() {
        if (this.f13011c && this.f13009a != null) {
            return this.f13009a.getFantasyFontFamily();
        } else if (this.f13011c || this.f13010b == null) {
            return "";
        } else {
            return this.f13010b.getFantasyFontFamily();
        }
    }

    public synchronized void setMinimumFontSize(int i) {
        if (this.f13011c && this.f13009a != null) {
            this.f13009a.setMinimumFontSize(i);
        } else if (!this.f13011c && this.f13010b != null) {
            this.f13010b.setMinimumFontSize(i);
        }
    }

    public synchronized int getMinimumFontSize() {
        if (this.f13011c && this.f13009a != null) {
            return this.f13009a.getMinimumFontSize();
        } else if (this.f13011c || this.f13010b == null) {
            return 0;
        } else {
            return this.f13010b.getMinimumFontSize();
        }
    }

    public synchronized void setMinimumLogicalFontSize(int i) {
        if (this.f13011c && this.f13009a != null) {
            this.f13009a.setMinimumLogicalFontSize(i);
        } else if (!this.f13011c && this.f13010b != null) {
            this.f13010b.setMinimumLogicalFontSize(i);
        }
    }

    public synchronized int getMinimumLogicalFontSize() {
        if (this.f13011c && this.f13009a != null) {
            return this.f13009a.getMinimumLogicalFontSize();
        } else if (this.f13011c || this.f13010b == null) {
            return 0;
        } else {
            return this.f13010b.getMinimumLogicalFontSize();
        }
    }

    public synchronized void setDefaultFontSize(int i) {
        if (this.f13011c && this.f13009a != null) {
            this.f13009a.setDefaultFontSize(i);
        } else if (!this.f13011c && this.f13010b != null) {
            this.f13010b.setDefaultFontSize(i);
        }
    }

    public synchronized int getDefaultFontSize() {
        if (this.f13011c && this.f13009a != null) {
            return this.f13009a.getDefaultFontSize();
        } else if (this.f13011c || this.f13010b == null) {
            return 0;
        } else {
            return this.f13010b.getDefaultFontSize();
        }
    }

    public synchronized void setDefaultFixedFontSize(int i) {
        if (this.f13011c && this.f13009a != null) {
            this.f13009a.setDefaultFixedFontSize(i);
        } else if (!this.f13011c && this.f13010b != null) {
            this.f13010b.setDefaultFixedFontSize(i);
        }
    }

    public synchronized int getDefaultFixedFontSize() {
        if (this.f13011c && this.f13009a != null) {
            return this.f13009a.getDefaultFixedFontSize();
        } else if (this.f13011c || this.f13010b == null) {
            return 0;
        } else {
            return this.f13010b.getDefaultFixedFontSize();
        }
    }

    public void setLoadsImagesAutomatically(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f13011c && (iX5WebSettings = this.f13009a) != null) {
            iX5WebSettings.setLoadsImagesAutomatically(z);
        } else if (!this.f13011c && (webSettings = this.f13010b) != null) {
            webSettings.setLoadsImagesAutomatically(z);
        }
    }

    public synchronized boolean getLoadsImagesAutomatically() {
        if (this.f13011c && this.f13009a != null) {
            return this.f13009a.getLoadsImagesAutomatically();
        } else if (this.f13011c || this.f13010b == null) {
            return false;
        } else {
            return this.f13010b.getLoadsImagesAutomatically();
        }
    }

    public void setBlockNetworkImage(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f13011c && (iX5WebSettings = this.f13009a) != null) {
            iX5WebSettings.setBlockNetworkImage(z);
        } else if (!this.f13011c && (webSettings = this.f13010b) != null) {
            webSettings.setBlockNetworkImage(z);
        }
    }

    public synchronized boolean getBlockNetworkImage() {
        if (this.f13011c && this.f13009a != null) {
            return this.f13009a.getBlockNetworkImage();
        } else if (this.f13011c || this.f13010b == null) {
            return false;
        } else {
            return this.f13010b.getBlockNetworkImage();
        }
    }

    @TargetApi(8)
    public synchronized void setBlockNetworkLoads(boolean z) {
        if (this.f13011c && this.f13009a != null) {
            this.f13009a.setBlockNetworkLoads(z);
        } else if (!this.f13011c && this.f13010b != null) {
            if (Build.VERSION.SDK_INT >= 8) {
                this.f13010b.setBlockNetworkLoads(z);
            }
        }
    }

    @TargetApi(8)
    public synchronized boolean getBlockNetworkLoads() {
        if (this.f13011c && this.f13009a != null) {
            return this.f13009a.getBlockNetworkLoads();
        } else if (this.f13011c || this.f13010b == null) {
            return false;
        } else {
            if (Build.VERSION.SDK_INT < 8) {
                return false;
            }
            return this.f13010b.getBlockNetworkLoads();
        }
    }

    @Deprecated
    public void setJavaScriptEnabled(boolean z) {
        try {
            if (this.f13011c && this.f13009a != null) {
                this.f13009a.setJavaScriptEnabled(z);
            } else if (!this.f13011c && this.f13010b != null) {
                this.f13010b.setJavaScriptEnabled(z);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @TargetApi(16)
    public void setAllowUniversalAccessFromFileURLs(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f13011c && (iX5WebSettings = this.f13009a) != null) {
            iX5WebSettings.setAllowUniversalAccessFromFileURLs(z);
        } else if (!this.f13011c && (webSettings = this.f13010b) != null) {
            ReflectionUtils.m16404a(webSettings, "setAllowUniversalAccessFromFileURLs", new Class[]{Boolean.TYPE}, Boolean.valueOf(z));
        }
    }

    @TargetApi(16)
    public void setAllowFileAccessFromFileURLs(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f13011c && (iX5WebSettings = this.f13009a) != null) {
            iX5WebSettings.setAllowFileAccessFromFileURLs(z);
        } else if (!this.f13011c && (webSettings = this.f13010b) != null) {
            ReflectionUtils.m16404a(webSettings, "setAllowFileAccessFromFileURLs", new Class[]{Boolean.TYPE}, Boolean.valueOf(z));
        }
    }

    @Deprecated
    public void setPluginsEnabled(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f13011c && (iX5WebSettings = this.f13009a) != null) {
            iX5WebSettings.setPluginsEnabled(z);
        } else if (!this.f13011c && (webSettings = this.f13010b) != null) {
            ReflectionUtils.m16404a(webSettings, "setPluginsEnabled", new Class[]{Boolean.TYPE}, Boolean.valueOf(z));
        }
    }

    @TargetApi(8)
    @Deprecated
    public synchronized void setPluginState(PluginState pluginState) {
        if (this.f13011c && this.f13009a != null) {
            this.f13009a.setPluginState(IX5WebSettings.PluginState.valueOf(pluginState.name()));
        } else if (!this.f13011c && this.f13010b != null) {
            if (Build.VERSION.SDK_INT >= 8) {
                ReflectionUtils.m16404a(this.f13010b, "setPluginState", new Class[]{WebSettings.PluginState.class}, WebSettings.PluginState.valueOf(pluginState.name()));
            }
        }
    }

    @Deprecated
    public synchronized void setPluginsPath(String str) {
        if (this.f13011c && this.f13009a != null) {
            this.f13009a.setPluginsPath(str);
        } else if (!this.f13011c && this.f13010b != null) {
            ReflectionUtils.m16404a(this.f13010b, "setPluginsPath", new Class[]{String.class}, str);
        }
    }

    @TargetApi(5)
    @Deprecated
    public void setDatabasePath(String str) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f13011c && (iX5WebSettings = this.f13009a) != null) {
            iX5WebSettings.setDatabasePath(str);
        } else if (!this.f13011c && (webSettings = this.f13010b) != null) {
            ReflectionUtils.m16404a(webSettings, "setDatabasePath", new Class[]{String.class}, str);
        }
    }

    @TargetApi(5)
    public void setGeolocationDatabasePath(String str) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f13011c && (iX5WebSettings = this.f13009a) != null) {
            iX5WebSettings.setGeolocationDatabasePath(str);
        } else if (!this.f13011c && (webSettings = this.f13010b) != null) {
            webSettings.setGeolocationDatabasePath(str);
        }
    }

    @TargetApi(7)
    public void setAppCacheEnabled(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f13011c && (iX5WebSettings = this.f13009a) != null) {
            iX5WebSettings.setAppCacheEnabled(z);
        } else if (!this.f13011c && (webSettings = this.f13010b) != null) {
            webSettings.setAppCacheEnabled(z);
        }
    }

    @TargetApi(7)
    public void setAppCachePath(String str) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f13011c && (iX5WebSettings = this.f13009a) != null) {
            iX5WebSettings.setAppCachePath(str);
        } else if (!this.f13011c && (webSettings = this.f13010b) != null) {
            webSettings.setAppCachePath(str);
        }
    }

    @TargetApi(7)
    public void setAppCacheMaxSize(long j) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f13011c && (iX5WebSettings = this.f13009a) != null) {
            iX5WebSettings.setAppCacheMaxSize(j);
        } else if (!this.f13011c && (webSettings = this.f13010b) != null) {
            webSettings.setAppCacheMaxSize(j);
        }
    }

    @TargetApi(5)
    public void setDatabaseEnabled(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f13011c && (iX5WebSettings = this.f13009a) != null) {
            iX5WebSettings.setDatabaseEnabled(z);
        } else if (!this.f13011c && (webSettings = this.f13010b) != null) {
            webSettings.setDatabaseEnabled(z);
        }
    }

    @TargetApi(7)
    public void setDomStorageEnabled(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f13011c && (iX5WebSettings = this.f13009a) != null) {
            iX5WebSettings.setDomStorageEnabled(z);
        } else if (!this.f13011c && (webSettings = this.f13010b) != null) {
            webSettings.setDomStorageEnabled(z);
        }
    }

    @TargetApi(7)
    public synchronized boolean getDomStorageEnabled() {
        if (this.f13011c && this.f13009a != null) {
            return this.f13009a.getDomStorageEnabled();
        } else if (this.f13011c || this.f13010b == null) {
            return false;
        } else {
            return this.f13010b.getDomStorageEnabled();
        }
    }

    @TargetApi(5)
    public synchronized String getDatabasePath() {
        if (this.f13011c && this.f13009a != null) {
            return this.f13009a.getDatabasePath();
        } else if (this.f13011c || this.f13010b == null) {
            return "";
        } else {
            return this.f13010b.getDatabasePath();
        }
    }

    @TargetApi(5)
    public synchronized boolean getDatabaseEnabled() {
        if (this.f13011c && this.f13009a != null) {
            return this.f13009a.getDatabaseEnabled();
        } else if (this.f13011c || this.f13010b == null) {
            return false;
        } else {
            return this.f13010b.getDatabaseEnabled();
        }
    }

    @TargetApi(5)
    public void setGeolocationEnabled(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f13011c && (iX5WebSettings = this.f13009a) != null) {
            iX5WebSettings.setGeolocationEnabled(z);
        } else if (!this.f13011c && (webSettings = this.f13010b) != null) {
            webSettings.setGeolocationEnabled(z);
        }
    }

    public synchronized boolean getJavaScriptEnabled() {
        if (this.f13011c && this.f13009a != null) {
            return this.f13009a.getJavaScriptEnabled();
        } else if (this.f13011c || this.f13010b == null) {
            return false;
        } else {
            return this.f13010b.getJavaScriptEnabled();
        }
    }

    @TargetApi(8)
    @Deprecated
    public synchronized boolean getPluginsEnabled() {
        if (!this.f13011c || this.f13009a == null) {
            boolean z = false;
            if (this.f13011c || this.f13010b == null) {
                return false;
            }
            if (Build.VERSION.SDK_INT <= 17) {
                Object a = ReflectionUtils.m16406a(this.f13010b, "getPluginsEnabled");
                if (a != null) {
                    z = ((Boolean) a).booleanValue();
                }
                return z;
            } else if (Build.VERSION.SDK_INT != 18) {
                return false;
            } else {
                if (WebSettings.PluginState.ON == this.f13010b.getPluginState()) {
                    z = true;
                }
                return z;
            }
        } else {
            return this.f13009a.getPluginsEnabled();
        }
    }

    @TargetApi(8)
    @Deprecated
    public synchronized PluginState getPluginState() {
        if (this.f13011c && this.f13009a != null) {
            return PluginState.valueOf(this.f13009a.getPluginState().name());
        } else if (this.f13011c || this.f13010b == null) {
            return null;
        } else {
            if (Build.VERSION.SDK_INT < 8) {
                return null;
            }
            Object a = ReflectionUtils.m16406a(this.f13010b, "getPluginState");
            if (a == null) {
                return null;
            }
            return PluginState.valueOf(((WebSettings.PluginState) a).name());
        }
    }

    @Deprecated
    public synchronized String getPluginsPath() {
        if (this.f13011c && this.f13009a != null) {
            return this.f13009a.getPluginsPath();
        } else if (this.f13011c || this.f13010b == null) {
            return "";
        } else {
            if (Build.VERSION.SDK_INT > 17) {
                return "";
            }
            Object a = ReflectionUtils.m16406a(this.f13010b, "getPluginsPath");
            return a == null ? null : (String) a;
        }
    }

    public synchronized void setJavaScriptCanOpenWindowsAutomatically(boolean z) {
        if (this.f13011c && this.f13009a != null) {
            this.f13009a.setJavaScriptCanOpenWindowsAutomatically(z);
        } else if (!this.f13011c && this.f13010b != null) {
            this.f13010b.setJavaScriptCanOpenWindowsAutomatically(z);
        }
    }

    public synchronized boolean getJavaScriptCanOpenWindowsAutomatically() {
        if (this.f13011c && this.f13009a != null) {
            return this.f13009a.getJavaScriptCanOpenWindowsAutomatically();
        } else if (this.f13011c || this.f13010b == null) {
            return false;
        } else {
            return this.f13010b.getJavaScriptCanOpenWindowsAutomatically();
        }
    }

    public synchronized void setDefaultTextEncodingName(String str) {
        if (this.f13011c && this.f13009a != null) {
            this.f13009a.setDefaultTextEncodingName(str);
        } else if (!this.f13011c && this.f13010b != null) {
            this.f13010b.setDefaultTextEncodingName(str);
        }
    }

    public synchronized String getDefaultTextEncodingName() {
        if (this.f13011c && this.f13009a != null) {
            return this.f13009a.getDefaultTextEncodingName();
        } else if (this.f13011c || this.f13010b == null) {
            return "";
        } else {
            return this.f13010b.getDefaultTextEncodingName();
        }
    }

    @TargetApi(17)
    public static String getDefaultUserAgent(Context context) {
        Object a;
        if (X5CoreEngine.m16621a().m16618b()) {
            return X5CoreEngine.m16621a().m16616c().m16572i(context);
        }
        if (Build.VERSION.SDK_INT >= 17 && (a = ReflectionUtils.m16407a((Class<?>) android.webkit.WebSettings.class, "getDefaultUserAgent", (Class<?>[]) new Class[]{Context.class}, context)) != null) {
            return (String) a;
        }
        return null;
    }

    @TargetApi(17)
    public boolean getMediaPlaybackRequiresUserGesture() {
        Object a;
        IX5WebSettings iX5WebSettings;
        if (this.f13011c && (iX5WebSettings = this.f13009a) != null) {
            return iX5WebSettings.getMediaPlaybackRequiresUserGesture();
        }
        if (this.f13011c || this.f13010b == null || Build.VERSION.SDK_INT < 17 || (a = ReflectionUtils.m16406a(this.f13010b, "getMediaPlaybackRequiresUserGesture")) == null) {
            return false;
        }
        return ((Boolean) a).booleanValue();
    }

    @TargetApi(17)
    public void setMediaPlaybackRequiresUserGesture(boolean z) {
        IX5WebSettings iX5WebSettings;
        if (this.f13011c && (iX5WebSettings = this.f13009a) != null) {
            iX5WebSettings.setMediaPlaybackRequiresUserGesture(z);
        } else if (!this.f13011c && this.f13010b != null && Build.VERSION.SDK_INT >= 17) {
            ReflectionUtils.m16404a(this.f13010b, "setMediaPlaybackRequiresUserGesture", new Class[]{Boolean.TYPE}, Boolean.valueOf(z));
        }
    }

    public void setNeedInitialFocus(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f13011c && (iX5WebSettings = this.f13009a) != null) {
            iX5WebSettings.setNeedInitialFocus(z);
        } else if (!this.f13011c && (webSettings = this.f13010b) != null) {
            webSettings.setNeedInitialFocus(z);
        }
    }

    public void setRenderPriority(RenderPriority renderPriority) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f13011c && (iX5WebSettings = this.f13009a) != null) {
            iX5WebSettings.setRenderPriority(IX5WebSettings.RenderPriority.valueOf(renderPriority.name()));
        } else if (!this.f13011c && (webSettings = this.f13010b) != null) {
            webSettings.setRenderPriority(WebSettings.RenderPriority.valueOf(renderPriority.name()));
        }
    }

    public void setCacheMode(int i) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f13011c && (iX5WebSettings = this.f13009a) != null) {
            iX5WebSettings.setCacheMode(i);
        } else if (!this.f13011c && (webSettings = this.f13010b) != null) {
            webSettings.setCacheMode(i);
        }
    }

    public int getCacheMode() {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f13011c && (iX5WebSettings = this.f13009a) != null) {
            return iX5WebSettings.getCacheMode();
        }
        if (this.f13011c || (webSettings = this.f13010b) == null) {
            return 0;
        }
        return webSettings.getCacheMode();
    }
}
