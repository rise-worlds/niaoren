package org.apache.tools.ant.taskdefs.rmic;

import org.apache.tools.ant.taskdefs.Rmic;

/* loaded from: classes2.dex */
public class WLRmic extends DefaultRmicAdapter {
    public static final String COMPILER_NAME = "weblogic";
    public static final String ERROR_NO_WLRMIC_ON_CLASSPATH = "Cannot use WebLogic rmic, as it is not available. Add it to Ant's classpath with the -lib option";
    public static final String ERROR_WLRMIC_FAILED = "Error starting WebLogic rmic: ";
    public static final String UNSUPPORTED_STUB_OPTION = "Unsupported stub option: ";
    public static final String WLRMIC_CLASSNAME = "weblogic.rmic";
    public static final String WL_RMI_SKEL_SUFFIX = "_WLSkel";
    public static final String WL_RMI_STUB_SUFFIX = "_WLStub";

    @Override // org.apache.tools.ant.taskdefs.rmic.DefaultRmicAdapter
    public String getSkelClassSuffix() {
        return WL_RMI_SKEL_SUFFIX;
    }

    @Override // org.apache.tools.ant.taskdefs.rmic.DefaultRmicAdapter
    public String getStubClassSuffix() {
        return WL_RMI_STUB_SUFFIX;
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x007a A[Catch: all -> 0x0073, TryCatch #3 {all -> 0x0073, blocks: (B:3:0x0015, B:5:0x0020, B:6:0x0028, B:25:0x0076, B:27:0x007a, B:28:0x007c, B:29:0x007d, B:30:0x008c, B:31:0x008d, B:32:0x009c), top: B:37:0x0015 }] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x007d A[Catch: all -> 0x0073, TryCatch #3 {all -> 0x0073, blocks: (B:3:0x0015, B:5:0x0020, B:6:0x0028, B:25:0x0076, B:27:0x007a, B:28:0x007c, B:29:0x007d, B:30:0x008c, B:31:0x008d, B:32:0x009c), top: B:37:0x0015 }] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x009f  */
    @Override // org.apache.tools.ant.taskdefs.rmic.RmicAdapter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean execute() throws org.apache.tools.ant.BuildException {
        /*
            r10 = this;
            org.apache.tools.ant.taskdefs.Rmic r0 = r10.getRmic()
            java.lang.String r1 = "Using WebLogic rmic"
            r2 = 3
            r0.log(r1, r2)
            java.lang.String r0 = "-noexit"
            java.lang.String[] r0 = new java.lang.String[]{r0}
            org.apache.tools.ant.types.Commandline r0 = r10.setupRmicCommand(r0)
            r1 = 0
            org.apache.tools.ant.taskdefs.Rmic r2 = r10.getRmic()     // Catch: all -> 0x0073, Exception -> 0x0075, ClassNotFoundException -> 0x008d
            org.apache.tools.ant.types.Path r2 = r2.getClasspath()     // Catch: all -> 0x0073, Exception -> 0x0075, ClassNotFoundException -> 0x008d
            r3 = 1
            if (r2 != 0) goto L_0x0028
            java.lang.String r2 = "weblogic.rmic"
            java.lang.Class r2 = java.lang.Class.forName(r2)     // Catch: all -> 0x0073, Exception -> 0x0075, ClassNotFoundException -> 0x008d
            r4 = r1
            goto L_0x0045
        L_0x0028:
            org.apache.tools.ant.taskdefs.Rmic r2 = r10.getRmic()     // Catch: all -> 0x0073, Exception -> 0x0075, ClassNotFoundException -> 0x008d
            org.apache.tools.ant.Project r2 = r2.getProject()     // Catch: all -> 0x0073, Exception -> 0x0075, ClassNotFoundException -> 0x008d
            org.apache.tools.ant.taskdefs.Rmic r4 = r10.getRmic()     // Catch: all -> 0x0073, Exception -> 0x0075, ClassNotFoundException -> 0x008d
            org.apache.tools.ant.types.Path r4 = r4.getClasspath()     // Catch: all -> 0x0073, Exception -> 0x0075, ClassNotFoundException -> 0x008d
            org.apache.tools.ant.AntClassLoader r2 = r2.createClassLoader(r4)     // Catch: all -> 0x0073, Exception -> 0x0075, ClassNotFoundException -> 0x008d
            java.lang.String r4 = "weblogic.rmic"
            java.lang.Class r4 = java.lang.Class.forName(r4, r3, r2)     // Catch: all -> 0x006b, Exception -> 0x006e, ClassNotFoundException -> 0x0071
            r9 = r4
            r4 = r2
            r2 = r9
        L_0x0045:
            java.lang.String r5 = "main"
            java.lang.Class[] r6 = new java.lang.Class[r3]     // Catch: all -> 0x0063, Exception -> 0x0066, ClassNotFoundException -> 0x0069
            java.lang.Class<java.lang.String[]> r7 = java.lang.String[].class
            r8 = 0
            r6[r8] = r7     // Catch: all -> 0x0063, Exception -> 0x0066, ClassNotFoundException -> 0x0069
            java.lang.reflect.Method r2 = r2.getMethod(r5, r6)     // Catch: all -> 0x0063, Exception -> 0x0066, ClassNotFoundException -> 0x0069
            java.lang.Object[] r5 = new java.lang.Object[r3]     // Catch: all -> 0x0063, Exception -> 0x0066, ClassNotFoundException -> 0x0069
            java.lang.String[] r0 = r0.getArguments()     // Catch: all -> 0x0063, Exception -> 0x0066, ClassNotFoundException -> 0x0069
            r5[r8] = r0     // Catch: all -> 0x0063, Exception -> 0x0066, ClassNotFoundException -> 0x0069
            r2.invoke(r1, r5)     // Catch: all -> 0x0063, Exception -> 0x0066, ClassNotFoundException -> 0x0069
            if (r4 == 0) goto L_0x0062
            r4.cleanup()
        L_0x0062:
            return r3
        L_0x0063:
            r0 = move-exception
            r1 = r4
            goto L_0x009d
        L_0x0066:
            r0 = move-exception
            r1 = r4
            goto L_0x0076
        L_0x0069:
            r1 = r4
            goto L_0x008d
        L_0x006b:
            r0 = move-exception
            r1 = r2
            goto L_0x009d
        L_0x006e:
            r0 = move-exception
            r1 = r2
            goto L_0x0076
        L_0x0071:
            r1 = r2
            goto L_0x008d
        L_0x0073:
            r0 = move-exception
            goto L_0x009d
        L_0x0075:
            r0 = move-exception
        L_0x0076:
            boolean r2 = r0 instanceof org.apache.tools.ant.BuildException     // Catch: all -> 0x0073
            if (r2 == 0) goto L_0x007d
            org.apache.tools.ant.BuildException r0 = (org.apache.tools.ant.BuildException) r0     // Catch: all -> 0x0073
            throw r0     // Catch: all -> 0x0073
        L_0x007d:
            org.apache.tools.ant.BuildException r2 = new org.apache.tools.ant.BuildException     // Catch: all -> 0x0073
            java.lang.String r3 = "Error starting WebLogic rmic: "
            org.apache.tools.ant.taskdefs.Rmic r4 = r10.getRmic()     // Catch: all -> 0x0073
            org.apache.tools.ant.Location r4 = r4.getLocation()     // Catch: all -> 0x0073
            r2.<init>(r3, r0, r4)     // Catch: all -> 0x0073
            throw r2     // Catch: all -> 0x0073
        L_0x008d:
            org.apache.tools.ant.BuildException r0 = new org.apache.tools.ant.BuildException     // Catch: all -> 0x0073
            java.lang.String r2 = "Cannot use WebLogic rmic, as it is not available. Add it to Ant's classpath with the -lib option"
            org.apache.tools.ant.taskdefs.Rmic r3 = r10.getRmic()     // Catch: all -> 0x0073
            org.apache.tools.ant.Location r3 = r3.getLocation()     // Catch: all -> 0x0073
            r0.<init>(r2, r3)     // Catch: all -> 0x0073
            throw r0     // Catch: all -> 0x0073
        L_0x009d:
            if (r1 == 0) goto L_0x00a2
            r1.cleanup()
        L_0x00a2:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.taskdefs.rmic.WLRmic.execute():boolean");
    }

    @Override // org.apache.tools.ant.taskdefs.rmic.DefaultRmicAdapter
    protected String[] preprocessCompilerArgs(String[] strArr) {
        return filterJvmCompilerArgs(strArr);
    }

    @Override // org.apache.tools.ant.taskdefs.rmic.DefaultRmicAdapter
    protected String addStubVersionOptions() {
        String stubVersion = getRmic().getStubVersion();
        if (stubVersion == null) {
            return null;
        }
        Rmic rmic = getRmic();
        rmic.log(UNSUPPORTED_STUB_OPTION + stubVersion, 1);
        return null;
    }
}
