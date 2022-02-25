package javax.activation;

import com.sun.activation.registries.LogSupport;
import com.sun.activation.registries.MailcapFile;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes2.dex */
public class MailcapCommandMap extends CommandMap {
    private static final int PROG = 0;
    private static MailcapFile defDB;

    /* renamed from: DB */
    private MailcapFile[] f14646DB;

    public MailcapCommandMap() {
        ArrayList arrayList = new ArrayList(5);
        arrayList.add(null);
        LogSupport.log("MailcapCommandMap: load HOME");
        try {
            String property = System.getProperty("user.home");
            if (property != null) {
                MailcapFile loadFile = loadFile(String.valueOf(property) + File.separator + ".mailcap");
                if (loadFile != null) {
                    arrayList.add(loadFile);
                }
            }
        } catch (SecurityException unused) {
        }
        LogSupport.log("MailcapCommandMap: load SYS");
        try {
            MailcapFile loadFile2 = loadFile(String.valueOf(System.getProperty("java.home")) + File.separator + "lib" + File.separator + "mailcap");
            if (loadFile2 != null) {
                arrayList.add(loadFile2);
            }
        } catch (SecurityException unused2) {
        }
        LogSupport.log("MailcapCommandMap: load JAR");
        loadAllResources(arrayList, "mailcap");
        LogSupport.log("MailcapCommandMap: load DEF");
        synchronized (MailcapCommandMap.class) {
            if (defDB == null) {
                defDB = loadResource("mailcap.default");
            }
        }
        MailcapFile mailcapFile = defDB;
        if (mailcapFile != null) {
            arrayList.add(mailcapFile);
        }
        this.f14646DB = new MailcapFile[arrayList.size()];
        this.f14646DB = (MailcapFile[]) arrayList.toArray(this.f14646DB);
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0044, code lost:
        if (r1 != null) goto L_0x0046;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0086, code lost:
        if (r1 != null) goto L_0x0046;
     */
    /* JADX WARN: Removed duplicated region for block: B:44:0x008d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.sun.activation.registries.MailcapFile loadResource(java.lang.String r6) {
        /*
            r5 = this;
            r0 = 0
            java.lang.Class r1 = r5.getClass()     // Catch: all -> 0x004e, SecurityException -> 0x0051, IOException -> 0x006d
            java.io.InputStream r1 = javax.activation.SecuritySupport.getResourceAsStream(r1, r6)     // Catch: all -> 0x004e, SecurityException -> 0x0051, IOException -> 0x006d
            if (r1 == 0) goto L_0x002d
            com.sun.activation.registries.MailcapFile r2 = new com.sun.activation.registries.MailcapFile     // Catch: SecurityException -> 0x004a, IOException -> 0x004c, all -> 0x008a
            r2.<init>(r1)     // Catch: SecurityException -> 0x004a, IOException -> 0x004c, all -> 0x008a
            boolean r3 = com.sun.activation.registries.LogSupport.isLoggable()     // Catch: SecurityException -> 0x004a, IOException -> 0x004c, all -> 0x008a
            if (r3 == 0) goto L_0x0027
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: SecurityException -> 0x004a, IOException -> 0x004c, all -> 0x008a
            java.lang.String r4 = "MailcapCommandMap: successfully loaded mailcap file: "
            r3.<init>(r4)     // Catch: SecurityException -> 0x004a, IOException -> 0x004c, all -> 0x008a
            r3.append(r6)     // Catch: SecurityException -> 0x004a, IOException -> 0x004c, all -> 0x008a
            java.lang.String r3 = r3.toString()     // Catch: SecurityException -> 0x004a, IOException -> 0x004c, all -> 0x008a
            com.sun.activation.registries.LogSupport.log(r3)     // Catch: SecurityException -> 0x004a, IOException -> 0x004c, all -> 0x008a
        L_0x0027:
            if (r1 == 0) goto L_0x002c
            r1.close()     // Catch: IOException -> 0x002c
        L_0x002c:
            return r2
        L_0x002d:
            boolean r2 = com.sun.activation.registries.LogSupport.isLoggable()     // Catch: SecurityException -> 0x004a, IOException -> 0x004c, all -> 0x008a
            if (r2 == 0) goto L_0x0044
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: SecurityException -> 0x004a, IOException -> 0x004c, all -> 0x008a
            java.lang.String r3 = "MailcapCommandMap: not loading mailcap file: "
            r2.<init>(r3)     // Catch: SecurityException -> 0x004a, IOException -> 0x004c, all -> 0x008a
            r2.append(r6)     // Catch: SecurityException -> 0x004a, IOException -> 0x004c, all -> 0x008a
            java.lang.String r2 = r2.toString()     // Catch: SecurityException -> 0x004a, IOException -> 0x004c, all -> 0x008a
            com.sun.activation.registries.LogSupport.log(r2)     // Catch: SecurityException -> 0x004a, IOException -> 0x004c, all -> 0x008a
        L_0x0044:
            if (r1 == 0) goto L_0x0089
        L_0x0046:
            r1.close()     // Catch: IOException -> 0x0089
            goto L_0x0089
        L_0x004a:
            r2 = move-exception
            goto L_0x0053
        L_0x004c:
            r2 = move-exception
            goto L_0x006f
        L_0x004e:
            r6 = move-exception
            r1 = r0
            goto L_0x008b
        L_0x0051:
            r2 = move-exception
            r1 = r0
        L_0x0053:
            boolean r3 = com.sun.activation.registries.LogSupport.isLoggable()     // Catch: all -> 0x008a
            if (r3 == 0) goto L_0x006a
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: all -> 0x008a
            java.lang.String r4 = "MailcapCommandMap: can't load "
            r3.<init>(r4)     // Catch: all -> 0x008a
            r3.append(r6)     // Catch: all -> 0x008a
            java.lang.String r6 = r3.toString()     // Catch: all -> 0x008a
            com.sun.activation.registries.LogSupport.log(r6, r2)     // Catch: all -> 0x008a
        L_0x006a:
            if (r1 == 0) goto L_0x0089
            goto L_0x0046
        L_0x006d:
            r2 = move-exception
            r1 = r0
        L_0x006f:
            boolean r3 = com.sun.activation.registries.LogSupport.isLoggable()     // Catch: all -> 0x008a
            if (r3 == 0) goto L_0x0086
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: all -> 0x008a
            java.lang.String r4 = "MailcapCommandMap: can't load "
            r3.<init>(r4)     // Catch: all -> 0x008a
            r3.append(r6)     // Catch: all -> 0x008a
            java.lang.String r6 = r3.toString()     // Catch: all -> 0x008a
            com.sun.activation.registries.LogSupport.log(r6, r2)     // Catch: all -> 0x008a
        L_0x0086:
            if (r1 == 0) goto L_0x0089
            goto L_0x0046
        L_0x0089:
            return r0
        L_0x008a:
            r6 = move-exception
        L_0x008b:
            if (r1 == 0) goto L_0x0090
            r1.close()     // Catch: IOException -> 0x0090
        L_0x0090:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: javax.activation.MailcapCommandMap.loadResource(java.lang.String):com.sun.activation.registries.MailcapFile");
    }

    /* JADX WARN: Removed duplicated region for block: B:62:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:86:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void loadAllResources(java.util.List r9, java.lang.String r10) {
        /*
            Method dump skipped, instructions count: 282
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: javax.activation.MailcapCommandMap.loadAllResources(java.util.List, java.lang.String):void");
    }

    private MailcapFile loadFile(String str) {
        try {
            return new MailcapFile(str);
        } catch (IOException unused) {
            return null;
        }
    }

    public MailcapCommandMap(String str) throws IOException {
        this();
        if (LogSupport.isLoggable()) {
            LogSupport.log("MailcapCommandMap: load PROG from " + str);
        }
        MailcapFile[] mailcapFileArr = this.f14646DB;
        if (mailcapFileArr[0] == null) {
            mailcapFileArr[0] = new MailcapFile(str);
        }
    }

    public MailcapCommandMap(InputStream inputStream) {
        this();
        LogSupport.log("MailcapCommandMap: load PROG");
        MailcapFile[] mailcapFileArr = this.f14646DB;
        if (mailcapFileArr[0] == null) {
            try {
                mailcapFileArr[0] = new MailcapFile(inputStream);
            } catch (IOException unused) {
            }
        }
    }

    @Override // javax.activation.CommandMap
    public synchronized CommandInfo[] getPreferredCommands(String str) {
        ArrayList arrayList;
        Map mailcapFallbackList;
        Map mailcapList;
        arrayList = new ArrayList();
        if (str != null) {
            str = str.toLowerCase(Locale.ENGLISH);
        }
        for (int i = 0; i < this.f14646DB.length; i++) {
            if (!(this.f14646DB[i] == null || (mailcapList = this.f14646DB[i].getMailcapList(str)) == null)) {
                appendPrefCmdsToList(mailcapList, arrayList);
            }
        }
        for (int i2 = 0; i2 < this.f14646DB.length; i2++) {
            if (!(this.f14646DB[i2] == null || (mailcapFallbackList = this.f14646DB[i2].getMailcapFallbackList(str)) == null)) {
                appendPrefCmdsToList(mailcapFallbackList, arrayList);
            }
        }
        return (CommandInfo[]) arrayList.toArray(new CommandInfo[arrayList.size()]);
    }

    private void appendPrefCmdsToList(Map map, List list) {
        for (String str : map.keySet()) {
            if (!checkForVerb(list, str)) {
                list.add(new CommandInfo(str, (String) ((List) map.get(str)).get(0)));
            }
        }
    }

    private boolean checkForVerb(List list, String str) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            if (((CommandInfo) it.next()).getCommandName().equals(str)) {
                return true;
            }
        }
        return false;
    }

    @Override // javax.activation.CommandMap
    public synchronized CommandInfo[] getAllCommands(String str) {
        ArrayList arrayList;
        Map mailcapFallbackList;
        Map mailcapList;
        arrayList = new ArrayList();
        if (str != null) {
            str = str.toLowerCase(Locale.ENGLISH);
        }
        for (int i = 0; i < this.f14646DB.length; i++) {
            if (!(this.f14646DB[i] == null || (mailcapList = this.f14646DB[i].getMailcapList(str)) == null)) {
                appendCmdsToList(mailcapList, arrayList);
            }
        }
        for (int i2 = 0; i2 < this.f14646DB.length; i2++) {
            if (!(this.f14646DB[i2] == null || (mailcapFallbackList = this.f14646DB[i2].getMailcapFallbackList(str)) == null)) {
                appendCmdsToList(mailcapFallbackList, arrayList);
            }
        }
        return (CommandInfo[]) arrayList.toArray(new CommandInfo[arrayList.size()]);
    }

    private void appendCmdsToList(Map map, List list) {
        for (String str : map.keySet()) {
            for (String str2 : (List) map.get(str)) {
                list.add(new CommandInfo(str, str2));
            }
        }
    }

    @Override // javax.activation.CommandMap
    public synchronized CommandInfo getCommand(String str, String str2) {
        Map mailcapFallbackList;
        List list;
        String str3;
        Map mailcapList;
        List list2;
        String str4;
        if (str != null) {
            try {
                str = str.toLowerCase(Locale.ENGLISH);
            } catch (Throwable th) {
                throw th;
            }
        }
        for (int i = 0; i < this.f14646DB.length; i++) {
            if (!(this.f14646DB[i] == null || (mailcapList = this.f14646DB[i].getMailcapList(str)) == null || (list2 = (List) mailcapList.get(str2)) == null || (str4 = (String) list2.get(0)) == null)) {
                return new CommandInfo(str2, str4);
            }
        }
        for (int i2 = 0; i2 < this.f14646DB.length; i2++) {
            if (!(this.f14646DB[i2] == null || (mailcapFallbackList = this.f14646DB[i2].getMailcapFallbackList(str)) == null || (list = (List) mailcapFallbackList.get(str2)) == null || (str3 = (String) list.get(0)) == null)) {
                return new CommandInfo(str2, str3);
            }
        }
        return null;
    }

    public synchronized void addMailcap(String str) {
        LogSupport.log("MailcapCommandMap: add to PROG");
        if (this.f14646DB[0] == null) {
            this.f14646DB[0] = new MailcapFile();
        }
        this.f14646DB[0].appendToMailcap(str);
    }

    @Override // javax.activation.CommandMap
    public synchronized DataContentHandler createDataContentHandler(String str) {
        List list;
        DataContentHandler dataContentHandler;
        List list2;
        DataContentHandler dataContentHandler2;
        if (LogSupport.isLoggable()) {
            LogSupport.log("MailcapCommandMap: createDataContentHandler for " + str);
        }
        if (str != null) {
            str = str.toLowerCase(Locale.ENGLISH);
        }
        for (int i = 0; i < this.f14646DB.length; i++) {
            if (this.f14646DB[i] != null) {
                if (LogSupport.isLoggable()) {
                    LogSupport.log("  search DB #" + i);
                }
                Map mailcapList = this.f14646DB[i].getMailcapList(str);
                if (!(mailcapList == null || (list2 = (List) mailcapList.get("content-handler")) == null || (dataContentHandler2 = getDataContentHandler((String) list2.get(0))) == null)) {
                    return dataContentHandler2;
                }
            }
        }
        for (int i2 = 0; i2 < this.f14646DB.length; i2++) {
            if (this.f14646DB[i2] != null) {
                if (LogSupport.isLoggable()) {
                    LogSupport.log("  search fallback DB #" + i2);
                }
                Map mailcapFallbackList = this.f14646DB[i2].getMailcapFallbackList(str);
                if (!(mailcapFallbackList == null || (list = (List) mailcapFallbackList.get("content-handler")) == null || (dataContentHandler = getDataContentHandler((String) list.get(0))) == null)) {
                    return dataContentHandler;
                }
            }
        }
        return null;
    }

    private DataContentHandler getDataContentHandler(String str) {
        Class<?> cls;
        if (LogSupport.isLoggable()) {
            LogSupport.log("    got content-handler");
        }
        if (LogSupport.isLoggable()) {
            LogSupport.log("      class " + str);
        }
        try {
            ClassLoader contextClassLoader = SecuritySupport.getContextClassLoader();
            if (contextClassLoader == null) {
                contextClassLoader = getClass().getClassLoader();
            }
            try {
                cls = contextClassLoader.loadClass(str);
            } catch (Exception unused) {
                cls = Class.forName(str);
            }
            if (cls != null) {
                return (DataContentHandler) cls.newInstance();
            }
            return null;
        } catch (ClassNotFoundException e) {
            if (!LogSupport.isLoggable()) {
                return null;
            }
            LogSupport.log("Can't load DCH " + str, e);
            return null;
        } catch (IllegalAccessException e2) {
            if (!LogSupport.isLoggable()) {
                return null;
            }
            LogSupport.log("Can't load DCH " + str, e2);
            return null;
        } catch (InstantiationException e3) {
            if (!LogSupport.isLoggable()) {
                return null;
            }
            LogSupport.log("Can't load DCH " + str, e3);
            return null;
        }
    }

    @Override // javax.activation.CommandMap
    public synchronized String[] getMimeTypes() {
        ArrayList arrayList;
        String[] mimeTypes;
        arrayList = new ArrayList();
        for (int i = 0; i < this.f14646DB.length; i++) {
            if (!(this.f14646DB[i] == null || (mimeTypes = this.f14646DB[i].getMimeTypes()) == null)) {
                for (int i2 = 0; i2 < mimeTypes.length; i2++) {
                    if (!arrayList.contains(mimeTypes[i2])) {
                        arrayList.add(mimeTypes[i2]);
                    }
                }
            }
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public synchronized String[] getNativeCommands(String str) {
        ArrayList arrayList;
        String[] nativeCommands;
        arrayList = new ArrayList();
        if (str != null) {
            str = str.toLowerCase(Locale.ENGLISH);
        }
        for (int i = 0; i < this.f14646DB.length; i++) {
            if (!(this.f14646DB[i] == null || (nativeCommands = this.f14646DB[i].getNativeCommands(str)) == null)) {
                for (int i2 = 0; i2 < nativeCommands.length; i2++) {
                    if (!arrayList.contains(nativeCommands[i2])) {
                        arrayList.add(nativeCommands[i2]);
                    }
                }
            }
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }
}
