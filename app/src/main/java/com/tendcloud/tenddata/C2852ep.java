package com.tendcloud.tenddata;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.ep */
/* loaded from: classes2.dex */
public final class C2852ep {

    /* renamed from: a */
    public static final ThreadPoolExecutor f13904a;

    /* renamed from: e */
    private static final int f13908e = 30;

    /* renamed from: b */
    private static final int f13905b = Runtime.getRuntime().availableProcessors();

    /* renamed from: c */
    private static final int f13906c = Math.max(2, Math.min(f13905b - 1, 4));

    /* renamed from: d */
    private static final int f13907d = (f13905b * 2) + 1;

    /* renamed from: f */
    private static final ThreadFactory f13909f = new ThreadFactoryC2853eq();

    /* renamed from: g */
    private static final BlockingQueue f13910g = new LinkedBlockingQueue(128);

    static {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(f13906c, f13907d, 30L, TimeUnit.SECONDS, f13910g, f13909f);
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        f13904a = threadPoolExecutor;
    }
}
