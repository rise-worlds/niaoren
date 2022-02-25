package org.apache.tools.ant.taskdefs.condition;

import java.util.Locale;
import org.apache.tools.ant.BuildException;
import p110z1.C4963cj;

/* renamed from: org.apache.tools.ant.taskdefs.condition.Os */
/* loaded from: classes2.dex */
public class C3209Os implements Condition {
    private static final String DARWIN = "darwin";
    public static final String FAMILY_9X = "win9x";
    public static final String FAMILY_DOS = "dos";
    public static final String FAMILY_MAC = "mac";
    public static final String FAMILY_NETWARE = "netware";
    public static final String FAMILY_NT = "winnt";
    public static final String FAMILY_OS2 = "os/2";
    public static final String FAMILY_OS400 = "os/400";
    public static final String FAMILY_TANDEM = "tandem";
    public static final String FAMILY_UNIX = "unix";
    public static final String FAMILY_VMS = "openvms";
    public static final String FAMILY_WINDOWS = "windows";
    public static final String FAMILY_ZOS = "z/os";
    private String arch;
    private String family;
    private String name;
    private String version;
    private static final String OS_NAME = System.getProperty("os.name").toLowerCase(Locale.ENGLISH);
    private static final String OS_ARCH = System.getProperty("os.arch").toLowerCase(Locale.ENGLISH);
    private static final String OS_VERSION = System.getProperty("os.version").toLowerCase(Locale.ENGLISH);
    private static final String PATH_SEP = System.getProperty("path.separator");

    public C3209Os() {
    }

    public C3209Os(String str) {
        setFamily(str);
    }

    public void setFamily(String str) {
        this.family = str.toLowerCase(Locale.ENGLISH);
    }

    public void setName(String str) {
        this.name = str.toLowerCase(Locale.ENGLISH);
    }

    public void setArch(String str) {
        this.arch = str.toLowerCase(Locale.ENGLISH);
    }

    public void setVersion(String str) {
        this.version = str.toLowerCase(Locale.ENGLISH);
    }

    @Override // org.apache.tools.ant.taskdefs.condition.Condition
    public boolean eval() throws BuildException {
        return isOs(this.family, this.name, this.arch, this.version);
    }

    public static boolean isFamily(String str) {
        return isOs(str, null, null, null);
    }

    public static boolean isName(String str) {
        return isOs(null, str, null, null);
    }

    public static boolean isArch(String str) {
        return isOs(null, null, str, null);
    }

    public static boolean isVersion(String str) {
        return isOs(null, null, null, str);
    }

    public static boolean isOs(String str, String str2, String str3, String str4) {
        boolean z;
        boolean z2;
        boolean z3;
        if (str == null && str2 == null && str3 == null && str4 == null) {
            return false;
        }
        if (str != null) {
            boolean z4 = OS_NAME.indexOf(FAMILY_WINDOWS) > -1;
            if (z4) {
                z3 = OS_NAME.indexOf("95") >= 0 || OS_NAME.indexOf("98") >= 0 || OS_NAME.indexOf("me") >= 0 || OS_NAME.indexOf("ce") >= 0;
                z2 = !z3;
            } else {
                z3 = false;
                z2 = false;
            }
            if (str.equals(FAMILY_WINDOWS)) {
                z = z4;
            } else if (str.equals(FAMILY_9X)) {
                z = z4 && z3;
            } else if (str.equals(FAMILY_NT)) {
                z = z4 && z2;
            } else if (str.equals(FAMILY_OS2)) {
                z = OS_NAME.indexOf(FAMILY_OS2) > -1;
            } else if (str.equals(FAMILY_NETWARE)) {
                z = OS_NAME.indexOf(FAMILY_NETWARE) > -1;
            } else if (str.equals(FAMILY_DOS)) {
                z = PATH_SEP.equals(C4963cj.f20745b) && !isFamily(FAMILY_NETWARE);
            } else if (str.equals(FAMILY_MAC)) {
                z = OS_NAME.indexOf(FAMILY_MAC) > -1 || OS_NAME.indexOf(DARWIN) > -1;
            } else if (str.equals(FAMILY_TANDEM)) {
                z = OS_NAME.indexOf("nonstop_kernel") > -1;
            } else if (str.equals(FAMILY_UNIX)) {
                z = PATH_SEP.equals(":") && !isFamily(FAMILY_VMS) && (!isFamily(FAMILY_MAC) || OS_NAME.endsWith("x") || OS_NAME.indexOf(DARWIN) > -1);
            } else if (str.equals(FAMILY_ZOS)) {
                z = OS_NAME.indexOf(FAMILY_ZOS) > -1 || OS_NAME.indexOf("os/390") > -1;
            } else if (str.equals(FAMILY_OS400)) {
                z = OS_NAME.indexOf(FAMILY_OS400) > -1;
            } else if (str.equals(FAMILY_VMS)) {
                z = OS_NAME.indexOf(FAMILY_VMS) > -1;
            } else {
                throw new BuildException("Don't know how to detect os family \"" + str + "\"");
            }
        } else {
            z = true;
        }
        return z && (str2 != null ? str2.equals(OS_NAME) : true) && (str3 != null ? str3.equals(OS_ARCH) : true) && (str4 != null ? str4.equals(OS_VERSION) : true);
    }
}
