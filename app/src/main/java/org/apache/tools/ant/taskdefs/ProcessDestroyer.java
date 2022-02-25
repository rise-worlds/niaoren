package org.apache.tools.ant.taskdefs;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Iterator;

/* loaded from: classes2.dex */
class ProcessDestroyer implements Runnable {
    private static final int THREAD_DIE_TIMEOUT = 20000;
    private Method addShutdownHookMethod;
    private Method removeShutdownHookMethod;
    private HashSet processes = new HashSet();
    private ProcessDestroyerImpl destroyProcessThread = null;
    private boolean added = false;
    private boolean running = false;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class ProcessDestroyerImpl extends Thread {
        private boolean shouldDestroy = true;

        public ProcessDestroyerImpl() {
            super("ProcessDestroyer Shutdown Hook");
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            if (this.shouldDestroy) {
                ProcessDestroyer.this.run();
            }
        }

        public void setShouldDestroy(boolean z) {
            this.shouldDestroy = z;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ProcessDestroyer() {
        try {
            Class[] clsArr = {Thread.class};
            this.addShutdownHookMethod = Runtime.class.getMethod("addShutdownHook", clsArr);
            this.removeShutdownHookMethod = Runtime.class.getMethod("removeShutdownHook", clsArr);
        } catch (NoSuchMethodException unused) {
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addShutdownHook() {
        if (this.addShutdownHookMethod != null && !this.running) {
            this.destroyProcessThread = new ProcessDestroyerImpl();
            try {
                this.addShutdownHookMethod.invoke(Runtime.getRuntime(), this.destroyProcessThread);
                this.added = true;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e2) {
                Throwable targetException = e2.getTargetException();
                if (targetException == null || targetException.getClass() != IllegalStateException.class) {
                    e2.printStackTrace();
                } else {
                    this.running = true;
                }
            }
        }
    }

    private void removeShutdownHook() {
        Method method = this.removeShutdownHookMethod;
        if (method != null && this.added && !this.running) {
            try {
                if (!((Boolean) method.invoke(Runtime.getRuntime(), this.destroyProcessThread)).booleanValue()) {
                    System.err.println("Could not remove shutdown hook");
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e2) {
                Throwable targetException = e2.getTargetException();
                if (targetException == null || targetException.getClass() != IllegalStateException.class) {
                    e2.printStackTrace();
                } else {
                    this.running = true;
                }
            }
            this.destroyProcessThread.setShouldDestroy(false);
            if (!this.destroyProcessThread.getThreadGroup().isDestroyed()) {
                this.destroyProcessThread.start();
            }
            try {
                this.destroyProcessThread.join(20000L);
            } catch (InterruptedException unused) {
            }
            this.destroyProcessThread = null;
            this.added = false;
        }
    }

    public boolean isAddedAsShutdownHook() {
        return this.added;
    }

    public boolean add(Process process) {
        boolean add;
        synchronized (this.processes) {
            if (this.processes.size() == 0) {
                addShutdownHook();
            }
            add = this.processes.add(process);
        }
        return add;
    }

    public boolean remove(Process process) {
        boolean remove;
        synchronized (this.processes) {
            remove = this.processes.remove(process);
            if (remove && this.processes.size() == 0) {
                removeShutdownHook();
            }
        }
        return remove;
    }

    @Override // java.lang.Runnable
    public void run() {
        synchronized (this.processes) {
            this.running = true;
            Iterator it = this.processes.iterator();
            while (it.hasNext()) {
                ((Process) it.next()).destroy();
            }
        }
    }
}
