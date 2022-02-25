package com.p007a.p008a.p009a;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/* renamed from: com.a.a.a.c */
/* loaded from: classes.dex */
public final class StreamGobbler extends Thread {

    /* renamed from: a */
    private final BufferedReader f115a;

    /* renamed from: b */
    private List<String> f116b;

    /* renamed from: c */
    private AbstractC0579d f117c;

    public StreamGobbler(InputStream inputStream, List<String> list) {
        this.f115a = new BufferedReader(new InputStreamReader(inputStream));
        this.f116b = list;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        while (true) {
            try {
                String readLine = this.f115a.readLine();
                if (readLine != null) {
                    if (this.f116b != null) {
                        this.f116b.add(readLine);
                    }
                    AbstractC0579d dVar = this.f117c;
                }
            } catch (IOException unused) {
            }
            try {
                this.f115a.close();
                return;
            } catch (IOException unused2) {
                return;
            }
        }
    }
}
