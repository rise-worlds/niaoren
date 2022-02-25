package com.sun.mail.util;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Properties;
import java.util.StringTokenizer;
import javax.net.SocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/* loaded from: classes2.dex */
public class SocketFetcher {
    private SocketFetcher() {
    }

    /* JADX WARN: Can't wrap try/catch for region: R(13:31|(12:74|33|(1:36)(1:37)|38|84|39|40|(1:61)|(2:78|63)|(1:65)|66|67)|34|(0)(0)|38|84|39|40|(0)|(0)|(0)|66|67) */
    /* JADX WARN: Can't wrap try/catch for region: R(4:(15:80|10|11|13|(1:15)(1:16)|17|(9:76|19|20|22|(1:27)(1:26)|28|82|29|(13:31|(12:74|33|(1:36)(1:37)|38|84|39|40|(1:61)|(2:78|63)|(1:65)|66|67)|34|(0)(0)|38|84|39|40|(0)|(0)|(0)|66|67)(7:43|59|(0)|(0)|(0)|66|67))|21|22|(1:24)|27|28|82|29|(0)(0))|82|29|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0102, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0103, code lost:
        r5 = r17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0109, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x010a, code lost:
        r3 = r11;
        r5 = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x010c, code lost:
        if (r16 == false) goto L_0x010e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0110, code lost:
        if ((r0 instanceof java.lang.reflect.InvocationTargetException) != false) goto L_0x0112;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0112, code lost:
        r1 = ((java.lang.reflect.InvocationTargetException) r0).getTargetException();
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x011b, code lost:
        if ((r1 instanceof java.lang.Exception) != false) goto L_0x011d;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x011d, code lost:
        r0 = (java.lang.Exception) r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0122, code lost:
        if ((r0 instanceof java.io.IOException) != false) goto L_0x0124;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0126, code lost:
        throw ((java.io.IOException) r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0127, code lost:
        r1 = new java.io.IOException("Couldn't connect using \"" + r3 + "\" socket factory to host, port: " + r18 + ", " + r5 + "; Exception: " + r0);
        r1.initCause(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0157, code lost:
        throw r1;
     */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00cb A[Catch: Exception -> 0x0109, SocketTimeoutException -> 0x017a, TRY_LEAVE, TryCatch #4 {Exception -> 0x0109, blocks: (B:29:0x00c5, B:31:0x00cb, B:33:0x00e3), top: B:82:0x00c5 }] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00ee  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0106  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x015d  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0173  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0082 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:78:0x016d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.net.Socket getSocket(java.lang.String r18, int r19, java.util.Properties r20, java.lang.String r21, boolean r22) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 380
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.mail.util.SocketFetcher.getSocket(java.lang.String, int, java.util.Properties, java.lang.String, boolean):java.net.Socket");
    }

    public static Socket getSocket(String str, int i, Properties properties, String str2) throws IOException {
        return getSocket(str, i, properties, str2, false);
    }

    private static Socket createSocket(InetAddress inetAddress, int i, String str, int i2, int i3, SocketFactory socketFactory, boolean z) throws IOException {
        Socket socket;
        if (socketFactory != null) {
            socket = socketFactory.createSocket();
        } else if (z) {
            socket = SSLSocketFactory.getDefault().createSocket();
        } else {
            socket = new Socket();
        }
        if (inetAddress != null) {
            socket.bind(new InetSocketAddress(inetAddress, i));
        }
        if (i3 >= 0) {
            socket.connect(new InetSocketAddress(str, i2), i3);
        } else {
            socket.connect(new InetSocketAddress(str, i2));
        }
        return socket;
    }

    private static SocketFactory getSocketFactory(String str) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Class<?> cls = null;
        if (str == null || str.length() == 0) {
            return null;
        }
        ClassLoader contextClassLoader = getContextClassLoader();
        if (contextClassLoader != null) {
            try {
                cls = contextClassLoader.loadClass(str);
            } catch (ClassNotFoundException unused) {
            }
        }
        if (cls == null) {
            cls = Class.forName(str);
        }
        return (SocketFactory) cls.getMethod("getDefault", new Class[0]).invoke(new Object(), new Object[0]);
    }

    public static Socket startTLS(Socket socket) throws IOException {
        return startTLS(socket, new Properties(), "socket");
    }

    public static Socket startTLS(Socket socket, Properties properties, String str) throws IOException {
        SSLSocketFactory sSLSocketFactory;
        String hostName = socket.getInetAddress().getHostName();
        int port = socket.getPort();
        try {
            SocketFactory socketFactory = getSocketFactory(properties.getProperty(String.valueOf(str) + ".socketFactory.class", null));
            if (socketFactory == null || !(socketFactory instanceof SSLSocketFactory)) {
                sSLSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            } else {
                sSLSocketFactory = (SSLSocketFactory) socketFactory;
            }
            Socket createSocket = sSLSocketFactory.createSocket(socket, hostName, port, true);
            configureSSLSocket(createSocket, properties, str);
            return createSocket;
        } catch (Exception e) {
            e = e;
            if (e instanceof InvocationTargetException) {
                Throwable targetException = ((InvocationTargetException) e).getTargetException();
                if (targetException instanceof Exception) {
                    e = (Exception) targetException;
                }
            }
            if (e instanceof IOException) {
                throw ((IOException) e);
            }
            IOException iOException = new IOException("Exception in startTLS: host " + hostName + ", port " + port + "; Exception: " + e);
            iOException.initCause(e);
            throw iOException;
        }
    }

    private static void configureSSLSocket(Socket socket, Properties properties, String str) {
        if (socket instanceof SSLSocket) {
            SSLSocket sSLSocket = (SSLSocket) socket;
            String property = properties.getProperty(String.valueOf(str) + ".ssl.protocols", null);
            if (property != null) {
                sSLSocket.setEnabledProtocols(stringArray(property));
            } else {
                sSLSocket.setEnabledProtocols(new String[]{"TLSv1"});
            }
            String property2 = properties.getProperty(String.valueOf(str) + ".ssl.ciphersuites", null);
            if (property2 != null) {
                sSLSocket.setEnabledCipherSuites(stringArray(property2));
            }
        }
    }

    private static String[] stringArray(String str) {
        StringTokenizer stringTokenizer = new StringTokenizer(str);
        ArrayList arrayList = new ArrayList();
        while (stringTokenizer.hasMoreTokens()) {
            arrayList.add(stringTokenizer.nextToken());
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    private static ClassLoader getContextClassLoader() {
        return (ClassLoader) AccessController.doPrivileged(new PrivilegedAction() { // from class: com.sun.mail.util.SocketFetcher.1
            @Override // java.security.PrivilegedAction
            public Object run() {
                try {
                    return Thread.currentThread().getContextClassLoader();
                } catch (SecurityException unused) {
                    return null;
                }
            }
        });
    }
}
