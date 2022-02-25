package org.apache.tools.ant.util.optional;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.util.ReflectWrapper;
import org.apache.tools.ant.util.ScriptRunnerBase;

/* loaded from: classes2.dex */
public class JavaxScriptRunner extends ScriptRunnerBase {
    private ReflectWrapper engine;

    @Override // org.apache.tools.ant.util.ScriptRunnerBase
    public String getManagerName() {
        return "javax";
    }

    @Override // org.apache.tools.ant.util.ScriptRunnerBase
    public boolean supportsLanguage() {
        boolean z = true;
        if (this.engine != null) {
            return true;
        }
        checkLanguage();
        ClassLoader replaceContextLoader = replaceContextLoader();
        try {
            if (createEngine() == null) {
                z = false;
            }
            return z;
        } catch (Exception unused) {
            return false;
        } finally {
            restoreContextLoader(replaceContextLoader);
        }
    }

    @Override // org.apache.tools.ant.util.ScriptRunnerBase
    public void executeScript(String str) throws BuildException {
        evaluateScript(str);
    }

    @Override // org.apache.tools.ant.util.ScriptRunnerBase
    public Object evaluateScript(String str) throws BuildException {
        checkLanguage();
        ClassLoader replaceContextLoader = replaceContextLoader();
        try {
            try {
                ReflectWrapper createEngine = createEngine();
                if (createEngine != null) {
                    for (String str2 : getBeans().keySet()) {
                        Object obj = getBeans().get(str2);
                        if ("FX".equalsIgnoreCase(getLanguage())) {
                            createEngine.invoke("put", String.class, str2 + ":" + obj.getClass().getName(), Object.class, obj);
                        } else {
                            createEngine.invoke("put", String.class, str2, Object.class, obj);
                        }
                    }
                    return createEngine.invoke("eval", String.class, getScript());
                }
                throw new BuildException("Unable to create javax script engine for " + getLanguage());
            } catch (BuildException e) {
                throw unwrap(e);
            } catch (Exception e2) {
                e = e2;
                Throwable cause = e.getCause();
                if (cause != null) {
                    if (cause instanceof BuildException) {
                        throw ((BuildException) cause);
                    }
                    e = cause;
                }
                throw new BuildException(e);
            }
        } finally {
            restoreContextLoader(replaceContextLoader);
        }
    }

    private ReflectWrapper createEngine() throws Exception {
        ReflectWrapper reflectWrapper = this.engine;
        if (reflectWrapper != null) {
            return reflectWrapper;
        }
        Object invoke = new ReflectWrapper(getClass().getClassLoader(), "javax.script.ScriptEngineManager").invoke("getEngineByName", String.class, getLanguage());
        if (invoke == null) {
            return null;
        }
        ReflectWrapper reflectWrapper2 = new ReflectWrapper(invoke);
        if (getKeepEngine()) {
            this.engine = reflectWrapper2;
        }
        return reflectWrapper2;
    }

    private static BuildException unwrap(Throwable th) {
        BuildException buildException = th instanceof BuildException ? (BuildException) th : null;
        while (th.getCause() != null) {
            th = th.getCause();
            if (th instanceof BuildException) {
                buildException = (BuildException) th;
            }
        }
        return buildException;
    }
}
