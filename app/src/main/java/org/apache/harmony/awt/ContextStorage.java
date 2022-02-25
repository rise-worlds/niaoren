package org.apache.harmony.awt;

import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import org.apache.harmony.awt.datatransfer.DTK;

/* loaded from: classes2.dex */
public final class ContextStorage {
    private static final ContextStorage globalContext = new ContextStorage();
    private DTK dtk;
    private GraphicsEnvironment graphicsEnvironment;
    private Toolkit toolkit;
    private volatile boolean shutdownPending = false;
    private final Object contextLock = new ContextLock(this, null);

    final void shutdown() {
    }

    /* loaded from: classes2.dex */
    private class ContextLock {
        private ContextLock() {
        }

        /* synthetic */ ContextLock(ContextStorage contextStorage, ContextLock contextLock) {
            this();
        }
    }

    public static void setDefaultToolkit(Toolkit toolkit) {
        getCurrentContext().toolkit = toolkit;
    }

    public static Toolkit getDefaultToolkit() {
        return getCurrentContext().toolkit;
    }

    public static void setDTK(DTK dtk) {
        getCurrentContext().dtk = dtk;
    }

    public static DTK getDTK() {
        return getCurrentContext().dtk;
    }

    public static Object getContextLock() {
        return getCurrentContext().contextLock;
    }

    public static GraphicsEnvironment getGraphicsEnvironment() {
        return getCurrentContext().graphicsEnvironment;
    }

    public static void setGraphicsEnvironment(GraphicsEnvironment graphicsEnvironment) {
        getCurrentContext().graphicsEnvironment = graphicsEnvironment;
    }

    private static ContextStorage getCurrentContext() {
        return globalContext;
    }

    public static boolean shutdownPending() {
        return getCurrentContext().shutdownPending;
    }
}
