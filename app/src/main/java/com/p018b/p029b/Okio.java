package com.p018b.p029b;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Logger;

/* renamed from: com.b.b.n */
/* loaded from: classes.dex */
public final class Okio {

    /* renamed from: a */
    static final Logger f6439a = Logger.getLogger(Okio.class.getName());

    private Okio() {
    }

    /* renamed from: a */
    public static BufferedSource m24305a(Source xVar) {
        return new RealBufferedSource(xVar);
    }

    /* renamed from: a */
    public static BufferedSink m24306a(Sink wVar) {
        return new RealBufferedSink(wVar);
    }

    /* renamed from: a */
    public static Sink m24303a(Socket socket) {
        if (socket != null) {
            AsyncTimeout c = m24301c(socket);
            OutputStream outputStream = socket.getOutputStream();
            if (outputStream != null) {
                return new C0917b(c, new C0920o(c, outputStream));
            }
            throw new IllegalArgumentException("out == null");
        }
        throw new IllegalArgumentException("socket == null");
    }

    /* renamed from: b */
    public static Source m24302b(Socket socket) {
        if (socket != null) {
            AsyncTimeout c = m24301c(socket);
            InputStream inputStream = socket.getInputStream();
            if (inputStream != null) {
                return new C0918c(c, new C0921p(c, inputStream));
            }
            throw new IllegalArgumentException("in == null");
        }
        throw new IllegalArgumentException("socket == null");
    }

    /* renamed from: c */
    private static AsyncTimeout m24301c(Socket socket) {
        return new C0922q(socket);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static boolean m24304a(AssertionError assertionError) {
        return (assertionError.getCause() == null || assertionError.getMessage() == null || !assertionError.getMessage().contains("getsockname failed")) ? false : true;
    }
}
