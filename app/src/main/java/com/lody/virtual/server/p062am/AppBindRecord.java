package com.lody.virtual.server.p062am;

import com.lody.virtual.server.p062am.ServiceRecord;
import java.util.HashSet;

/* renamed from: com.lody.virtual.server.am.AppBindRecord */
/* loaded from: classes.dex */
final class AppBindRecord {
    final ProcessRecord client;
    final HashSet<ConnectionRecord> connections = new HashSet<>();
    final ServiceRecord.IntentBindRecord intent;
    final ServiceRecord service;

    AppBindRecord(ServiceRecord serviceRecord, ServiceRecord.IntentBindRecord intentBindRecord, ProcessRecord processRecord) {
        this.service = serviceRecord;
        this.intent = intentBindRecord;
        this.client = processRecord;
    }
}
