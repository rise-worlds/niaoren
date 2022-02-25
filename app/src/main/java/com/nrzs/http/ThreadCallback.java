package com.nrzs.http;

import java.lang.String;

/* renamed from: com.nrzs.http.n */
/* loaded from: classes2.dex */
public interface ThreadCallback<T, D extends String> {
    T onResponse(D d);
}
