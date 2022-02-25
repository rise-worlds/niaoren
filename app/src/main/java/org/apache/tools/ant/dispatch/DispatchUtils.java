package org.apache.tools.ant.dispatch;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.UnknownElement;

/* loaded from: classes2.dex */
public class DispatchUtils {
    public static final void execute(Object obj) throws BuildException {
        Dispatchable dispatchable;
        String str;
        Object realThing;
        try {
            try {
                if (obj instanceof Dispatchable) {
                    dispatchable = (Dispatchable) obj;
                } else {
                    dispatchable = (!(obj instanceof UnknownElement) || (realThing = ((UnknownElement) obj).getRealThing()) == null || !(realThing instanceof Dispatchable) || !(realThing instanceof Task)) ? null : (Dispatchable) realThing;
                }
                if (dispatchable != null) {
                    try {
                        String actionParameterName = dispatchable.getActionParameterName();
                        if (actionParameterName == null || actionParameterName.trim().length() <= 0) {
                            throw new BuildException("Action Parameter Name must not be empty for Dispatchable Task.");
                        }
                        str = "get" + actionParameterName.trim().substring(0, 1).toUpperCase();
                        try {
                            if (actionParameterName.length() > 1) {
                                str = str + actionParameterName.substring(1);
                            }
                            Method method = dispatchable.getClass().getMethod(str, new Class[0]);
                            if (method != null) {
                                Object invoke = method.invoke(dispatchable, null);
                                if (invoke != null) {
                                    String obj2 = invoke.toString();
                                    if (obj2 == null || obj2.trim().length() <= 0) {
                                        throw new BuildException("Dispatchable Task attribute '" + actionParameterName.trim() + "' not set or value is empty.");
                                    }
                                    String trim = obj2.trim();
                                    Method method2 = dispatchable.getClass().getMethod(trim, new Class[0]);
                                    if (method2 != null) {
                                        method2.invoke(dispatchable, null);
                                        if (obj instanceof UnknownElement) {
                                            ((UnknownElement) obj).setRealThing(null);
                                            return;
                                        }
                                        return;
                                    }
                                    throw new BuildException("No public " + trim + "() in " + dispatchable.getClass());
                                }
                                throw new BuildException("Dispatchable Task attribute '" + actionParameterName.trim() + "' not set or value is empty.");
                            }
                        } catch (NoSuchMethodException unused) {
                            throw new BuildException("No public " + str + "() in " + obj.getClass());
                        }
                    } catch (NoSuchMethodException unused2) {
                        str = null;
                    }
                } else {
                    Method method3 = obj.getClass().getMethod("execute", new Class[0]);
                    if (method3 != null) {
                        method3.invoke(obj, null);
                        if (obj instanceof UnknownElement) {
                            ((UnknownElement) obj).setRealThing(null);
                            return;
                        }
                        return;
                    }
                    throw new BuildException("No public execute() in " + obj.getClass());
                }
            } catch (NoSuchMethodException e) {
                throw new BuildException(e);
            }
        } catch (IllegalAccessException e2) {
            throw new BuildException(e2);
        } catch (InvocationTargetException e3) {
            Throwable targetException = e3.getTargetException();
            if (targetException instanceof BuildException) {
                throw ((BuildException) targetException);
            }
            throw new BuildException(targetException);
        }
    }
}
