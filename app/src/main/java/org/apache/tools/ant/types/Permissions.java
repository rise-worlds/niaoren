package org.apache.tools.ant.types;

import java.net.SocketPermission;
import java.security.UnresolvedPermission;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PropertyPermission;
import java.util.Set;
import java.util.StringTokenizer;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.ExitException;
import org.slf4j.Marker;

/* loaded from: classes2.dex */
public class Permissions {
    private static final Class<?>[] PARAMS = {String.class, String.class};
    private boolean active;
    private final boolean delegateToOldSM;
    private java.security.Permissions granted;
    private final List<Permission> grantedPermissions;
    private SecurityManager origSm;
    private final List<Permission> revokedPermissions;

    public Permissions() {
        this(false);
    }

    public Permissions(boolean z) {
        this.grantedPermissions = new LinkedList();
        this.revokedPermissions = new LinkedList();
        this.granted = null;
        this.origSm = null;
        this.active = false;
        this.delegateToOldSM = z;
    }

    public void addConfiguredGrant(Permission permission) {
        this.grantedPermissions.add(permission);
    }

    public void addConfiguredRevoke(Permission permission) {
        this.revokedPermissions.add(permission);
    }

    public synchronized void setSecurityManager() throws BuildException {
        this.origSm = System.getSecurityManager();
        init();
        System.setSecurityManager(new MySM());
        this.active = true;
    }

    private void init() throws BuildException {
        this.granted = new java.security.Permissions();
        for (Permission permission : this.revokedPermissions) {
            if (permission.getClassName() == null) {
                throw new BuildException("Revoked permission " + permission + " does not contain a class.");
            }
        }
        for (Permission permission2 : this.grantedPermissions) {
            if (permission2.getClassName() != null) {
                this.granted.add(createPermission(permission2));
            } else {
                throw new BuildException("Granted permission " + permission2 + " does not contain a class.");
            }
        }
        this.granted.add(new SocketPermission("localhost:1024-", "listen"));
        this.granted.add(new PropertyPermission("java.version", "read"));
        this.granted.add(new PropertyPermission("java.vendor", "read"));
        this.granted.add(new PropertyPermission("java.vendor.url", "read"));
        this.granted.add(new PropertyPermission("java.class.version", "read"));
        this.granted.add(new PropertyPermission("os.name", "read"));
        this.granted.add(new PropertyPermission("os.version", "read"));
        this.granted.add(new PropertyPermission("os.arch", "read"));
        this.granted.add(new PropertyPermission("file.encoding", "read"));
        this.granted.add(new PropertyPermission("file.separator", "read"));
        this.granted.add(new PropertyPermission("path.separator", "read"));
        this.granted.add(new PropertyPermission("line.separator", "read"));
        this.granted.add(new PropertyPermission("java.specification.version", "read"));
        this.granted.add(new PropertyPermission("java.specification.vendor", "read"));
        this.granted.add(new PropertyPermission("java.specification.name", "read"));
        this.granted.add(new PropertyPermission("java.vm.specification.version", "read"));
        this.granted.add(new PropertyPermission("java.vm.specification.vendor", "read"));
        this.granted.add(new PropertyPermission("java.vm.specification.name", "read"));
        this.granted.add(new PropertyPermission("java.vm.version", "read"));
        this.granted.add(new PropertyPermission("java.vm.vendor", "read"));
        this.granted.add(new PropertyPermission("java.vm.name", "read"));
    }

    private java.security.Permission createPermission(Permission permission) {
        try {
            return (java.security.Permission) Class.forName(permission.getClassName()).asSubclass(java.security.Permission.class).getConstructor(PARAMS).newInstance(permission.getName(), permission.getActions());
        } catch (Exception unused) {
            return new UnresolvedPermission(permission.getClassName(), permission.getName(), permission.getActions(), null);
        }
    }

    public synchronized void restoreSecurityManager() {
        this.active = false;
        System.setSecurityManager(this.origSm);
    }

    /* loaded from: classes2.dex */
    private class MySM extends SecurityManager {
        private MySM() {
        }

        @Override // java.lang.SecurityManager
        public void checkExit(int i) {
            try {
                checkPermission(new RuntimePermission("exitVM", null));
            } catch (SecurityException e) {
                throw new ExitException(e.getMessage(), i);
            }
        }

        @Override // java.lang.SecurityManager
        public void checkPermission(java.security.Permission permission) {
            if (!Permissions.this.active) {
                return;
            }
            if (Permissions.this.delegateToOldSM && !permission.getName().equals("exitVM")) {
                boolean implies = Permissions.this.granted.implies(permission);
                checkRevoked(permission);
                if (!implies && Permissions.this.origSm != null) {
                    Permissions.this.origSm.checkPermission(permission);
                }
            } else if (Permissions.this.granted.implies(permission)) {
                checkRevoked(permission);
            } else {
                throw new SecurityException("Permission " + permission + " was not granted.");
            }
        }

        private void checkRevoked(java.security.Permission permission) {
            for (Permission permission2 : Permissions.this.revokedPermissions) {
                if (permission2.matches(permission)) {
                    throw new SecurityException("Permission " + permission + " was revoked.");
                }
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class Permission {
        private String actionString;
        private Set<String> actions;
        private String className;
        private String name;

        public void setClass(String str) {
            this.className = str.trim();
        }

        public String getClassName() {
            return this.className;
        }

        public void setName(String str) {
            this.name = str.trim();
        }

        public String getName() {
            return this.name;
        }

        public void setActions(String str) {
            this.actionString = str;
            if (str.length() > 0) {
                this.actions = parseActions(str);
            }
        }

        public String getActions() {
            return this.actionString;
        }

        boolean matches(java.security.Permission permission) {
            if (!this.className.equals(permission.getClass().getName())) {
                return false;
            }
            String str = this.name;
            if (str != null) {
                if (str.endsWith(Marker.ANY_MARKER)) {
                    String name = permission.getName();
                    String str2 = this.name;
                    if (!name.startsWith(str2.substring(0, str2.length() - 1))) {
                        return false;
                    }
                } else if (!this.name.equals(permission.getName())) {
                    return false;
                }
            }
            if (this.actions != null) {
                Set<String> parseActions = parseActions(permission.getActions());
                int size = parseActions.size();
                parseActions.removeAll(this.actions);
                if (parseActions.size() == size) {
                    return false;
                }
            }
            return true;
        }

        private Set<String> parseActions(String str) {
            HashSet hashSet = new HashSet();
            StringTokenizer stringTokenizer = new StringTokenizer(str, ",");
            while (stringTokenizer.hasMoreTokens()) {
                String trim = stringTokenizer.nextToken().trim();
                if (!trim.equals("")) {
                    hashSet.add(trim);
                }
            }
            return hashSet;
        }

        public String toString() {
            return "Permission: " + this.className + " (\"" + this.name + "\", \"" + this.actions + "\")";
        }
    }
}
