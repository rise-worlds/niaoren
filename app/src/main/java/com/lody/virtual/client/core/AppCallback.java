package com.lody.virtual.client.core;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

/* loaded from: classes.dex */
public interface AppCallback {
    public static final AppCallback EMPTY = new AppCallback() { // from class: com.lody.virtual.client.core.AppCallback.1
        @Override // com.lody.virtual.client.core.AppCallback
        public void afterActivityCreate(Activity activity) {
        }

        @Override // com.lody.virtual.client.core.AppCallback
        public void afterActivityResume(Activity activity) {
        }

        @Override // com.lody.virtual.client.core.AppCallback
        public void afterApplicationCreate(String str, String str2, Application application) {
        }

        @Override // com.lody.virtual.client.core.AppCallback
        public void beforeActivityCreate(Activity activity) {
        }

        @Override // com.lody.virtual.client.core.AppCallback
        public void beforeApplicationCreate(String str, String str2, Application application) {
        }

        @Override // com.lody.virtual.client.core.AppCallback
        public void beforeStartApplication(String str, String str2, Context context) {
        }
    };

    void afterActivityCreate(Activity activity);

    void afterActivityResume(Activity activity);

    void afterApplicationCreate(String str, String str2, Application application);

    void beforeActivityCreate(Activity activity);

    void beforeApplicationCreate(String str, String str2, Application application);

    void beforeStartApplication(String str, String str2, Context context);
}
