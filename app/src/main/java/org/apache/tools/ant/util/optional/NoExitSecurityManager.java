package org.apache.tools.ant.util.optional;

import java.security.Permission;
import org.apache.tools.ant.ExitException;

/* loaded from: classes2.dex */
public class NoExitSecurityManager extends SecurityManager {
    @Override // java.lang.SecurityManager
    public void checkPermission(Permission permission) {
    }

    @Override // java.lang.SecurityManager
    public void checkExit(int i) {
        throw new ExitException(i);
    }
}
