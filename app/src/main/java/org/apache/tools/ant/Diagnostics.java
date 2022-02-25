package org.apache.tools.ant;

import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.URL;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Properties;
import java.util.TimeZone;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import org.apache.commons.p105io.FilenameUtils;
import org.apache.commons.p105io.IOUtils;
import org.apache.tools.ant.launch.Launcher;
import org.apache.tools.ant.util.JAXPUtils;
import org.apache.tools.ant.util.JavaEnvUtils;
import org.apache.tools.ant.util.ProxySetup;
import p110z1.Typography;

/* loaded from: classes2.dex */
public final class Diagnostics {
    private static final int BIG_DRIFT_LIMIT = 10000;
    protected static final String ERROR_PROPERTY_ACCESS_BLOCKED = "Access to this property blocked by a security manager";
    private static final int JAVA_1_5_NUMBER = 15;
    private static final int KILOBYTE = 1024;
    private static final int MINUTES_PER_HOUR = 60;
    private static final int SECONDS_PER_MILLISECOND = 1000;
    private static final int SECONDS_PER_MINUTE = 60;
    private static final int TEST_FILE_SIZE = 32;

    private static void ignoreThrowable(Throwable th) {
    }

    public static boolean isOptionalAvailable() {
        return true;
    }

    public static void validateVersion() throws BuildException {
    }

    private Diagnostics() {
    }

    public static File[] listLibraries() {
        String property = System.getProperty(MagicNames.ANT_HOME);
        if (property == null) {
            return null;
        }
        return listJarFiles(new File(property, "lib"));
    }

    private static File[] listJarFiles(File file) {
        return file.listFiles(new FilenameFilter() { // from class: org.apache.tools.ant.Diagnostics.1
            @Override // java.io.FilenameFilter
            public boolean accept(File file2, String str) {
                return str.endsWith(".jar");
            }
        });
    }

    public static void main(String[] strArr) {
        doReport(System.out);
    }

    private static String getImplementationVersion(Class<?> cls) {
        return cls.getPackage().getImplementationVersion();
    }

    private static URL getClassLocation(Class<?> cls) {
        if (cls.getProtectionDomain().getCodeSource() == null) {
            return null;
        }
        return cls.getProtectionDomain().getCodeSource().getLocation();
    }

    private static String getXMLParserName() {
        SAXParser sAXParser = getSAXParser();
        return sAXParser == null ? "Could not create an XML Parser" : sAXParser.getClass().getName();
    }

    private static String getXSLTProcessorName() {
        Transformer xSLTProcessor = getXSLTProcessor();
        return xSLTProcessor == null ? "Could not create an XSLT Processor" : xSLTProcessor.getClass().getName();
    }

    private static SAXParser getSAXParser() {
        SAXParserFactory newInstance = SAXParserFactory.newInstance();
        if (newInstance == null) {
            return null;
        }
        try {
            return newInstance.newSAXParser();
        } catch (Exception e) {
            ignoreThrowable(e);
            return null;
        }
    }

    private static Transformer getXSLTProcessor() {
        TransformerFactory newInstance = TransformerFactory.newInstance();
        if (newInstance == null) {
            return null;
        }
        try {
            return newInstance.newTransformer();
        } catch (Exception e) {
            ignoreThrowable(e);
            return null;
        }
    }

    private static String getXMLParserLocation() {
        URL classLocation;
        SAXParser sAXParser = getSAXParser();
        if (sAXParser == null || (classLocation = getClassLocation(sAXParser.getClass())) == null) {
            return null;
        }
        return classLocation.toString();
    }

    private static String getNamespaceParserName() {
        try {
            return JAXPUtils.getNamespaceXMLReader().getClass().getName();
        } catch (BuildException e) {
            ignoreThrowable(e);
            return null;
        }
    }

    private static String getNamespaceParserLocation() {
        try {
            URL classLocation = getClassLocation(JAXPUtils.getNamespaceXMLReader().getClass());
            if (classLocation != null) {
                return classLocation.toString();
            }
            return null;
        } catch (BuildException e) {
            ignoreThrowable(e);
            return null;
        }
    }

    private static String getXSLTProcessorLocation() {
        URL classLocation;
        Transformer xSLTProcessor = getXSLTProcessor();
        if (xSLTProcessor == null || (classLocation = getClassLocation(xSLTProcessor.getClass())) == null) {
            return null;
        }
        return classLocation.toString();
    }

    public static void doReport(PrintStream printStream) {
        doReport(printStream, 2);
    }

    public static void doReport(PrintStream printStream, int i) {
        printStream.println("------- Ant diagnostics report -------");
        printStream.println(Main.getAntVersion());
        header(printStream, "Implementation Version");
        printStream.println("core tasks     : " + getImplementationVersion(Main.class) + " in " + getClassLocation(Main.class));
        header(printStream, "ANT PROPERTIES");
        doReportAntProperties(printStream);
        header(printStream, "ANT_HOME/lib jar listing");
        doReportAntHomeLibraries(printStream);
        header(printStream, "USER_HOME/.ant/lib jar listing");
        doReportUserHomeLibraries(printStream);
        header(printStream, "Tasks availability");
        doReportTasksAvailability(printStream);
        header(printStream, "org.apache.env.Which diagnostics");
        doReportWhich(printStream);
        header(printStream, "XML Parser information");
        doReportParserInfo(printStream);
        header(printStream, "XSLT Processor information");
        doReportXSLTProcessorInfo(printStream);
        header(printStream, "System properties");
        doReportSystemProperties(printStream);
        header(printStream, "Temp dir");
        doReportTempDir(printStream);
        header(printStream, "Locale information");
        doReportLocale(printStream);
        header(printStream, "Proxy information");
        doReportProxy(printStream);
        printStream.println();
    }

    private static void header(PrintStream printStream, String str) {
        printStream.println();
        printStream.println("-------------------------------------------");
        printStream.print(ExpandableTextView.f6958c);
        printStream.println(str);
        printStream.println("-------------------------------------------");
    }

    private static void doReportSystemProperties(PrintStream printStream) {
        try {
            Enumeration<?> propertyNames = System.getProperties().propertyNames();
            while (propertyNames.hasMoreElements()) {
                String str = (String) propertyNames.nextElement();
                String property = getProperty(str);
                printStream.println(str + " : " + property);
            }
        } catch (SecurityException e) {
            ignoreThrowable(e);
            printStream.println("Access to System.getProperties() blocked by a security manager");
        }
    }

    private static String getProperty(String str) {
        try {
            return System.getProperty(str);
        } catch (SecurityException unused) {
            return ERROR_PROPERTY_ACCESS_BLOCKED;
        }
    }

    private static void doReportAntProperties(PrintStream printStream) {
        Project project = new Project();
        project.initProperties();
        printStream.println("ant.version: " + project.getProperty(MagicNames.ANT_VERSION));
        printStream.println("ant.java.version: " + project.getProperty(MagicNames.ANT_JAVA_VERSION));
        StringBuilder sb = new StringBuilder();
        sb.append("Is this the Apache Harmony VM? ");
        sb.append(JavaEnvUtils.isApacheHarmony() ? "yes" : "no");
        printStream.println(sb.toString());
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Is this the Kaffe VM? ");
        sb2.append(JavaEnvUtils.isKaffe() ? "yes" : "no");
        printStream.println(sb2.toString());
        StringBuilder sb3 = new StringBuilder();
        sb3.append("Is this gij/gcj? ");
        sb3.append(JavaEnvUtils.isGij() ? "yes" : "no");
        printStream.println(sb3.toString());
        printStream.println("ant.core.lib: " + project.getProperty(MagicNames.ANT_LIB));
        printStream.println("ant.home: " + project.getProperty(MagicNames.ANT_HOME));
    }

    private static void doReportAntHomeLibraries(PrintStream printStream) {
        printStream.println("ant.home: " + System.getProperty(MagicNames.ANT_HOME));
        printLibraries(listLibraries(), printStream);
    }

    private static void doReportUserHomeLibraries(PrintStream printStream) {
        String property = System.getProperty("user.home");
        printStream.println("user.home: " + property);
        printLibraries(listJarFiles(new File(property, Launcher.USER_LIBDIR)), printStream);
    }

    private static void printLibraries(File[] fileArr, PrintStream printStream) {
        if (fileArr == null) {
            printStream.println("No such directory.");
            return;
        }
        for (int i = 0; i < fileArr.length; i++) {
            printStream.println(fileArr[i].getName() + " (" + fileArr[i].length() + " bytes)");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.reflect.InvocationTargetException] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Unknown variable types count: 1 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void doReportWhich(java.io.PrintStream r7) {
        /*
            r0 = 0
            java.lang.String r1 = "org.apache.env.Which"
            java.lang.Class r1 = java.lang.Class.forName(r1)     // Catch: Throwable -> 0x001f, InvocationTargetException -> 0x0021, ClassNotFoundException -> 0x002e
            java.lang.String r2 = "main"
            r3 = 1
            java.lang.Class[] r4 = new java.lang.Class[r3]     // Catch: Throwable -> 0x001f, InvocationTargetException -> 0x0021, ClassNotFoundException -> 0x002e
            java.lang.Class<java.lang.String[]> r5 = java.lang.String[].class
            r6 = 0
            r4[r6] = r5     // Catch: Throwable -> 0x001f, InvocationTargetException -> 0x0021, ClassNotFoundException -> 0x002e
            java.lang.reflect.Method r1 = r1.getMethod(r2, r4)     // Catch: Throwable -> 0x001f, InvocationTargetException -> 0x0021, ClassNotFoundException -> 0x002e
            java.lang.Object[] r2 = new java.lang.Object[r3]     // Catch: Throwable -> 0x001f, InvocationTargetException -> 0x0021, ClassNotFoundException -> 0x002e
            java.lang.String[] r3 = new java.lang.String[r6]     // Catch: Throwable -> 0x001f, InvocationTargetException -> 0x0021, ClassNotFoundException -> 0x002e
            r2[r6] = r3     // Catch: Throwable -> 0x001f, InvocationTargetException -> 0x0021, ClassNotFoundException -> 0x002e
            r1.invoke(r0, r2)     // Catch: Throwable -> 0x001f, InvocationTargetException -> 0x0021, ClassNotFoundException -> 0x002e
            goto L_0x0038
        L_0x001f:
            r0 = move-exception
            goto L_0x0038
        L_0x0021:
            r0 = move-exception
            java.lang.Throwable r1 = r0.getTargetException()
            if (r1 != 0) goto L_0x0029
            goto L_0x0038
        L_0x0029:
            java.lang.Throwable r0 = r0.getTargetException()
            goto L_0x0038
        L_0x002e:
            java.lang.String r1 = "Not available."
            r7.println(r1)
            java.lang.String r1 = "Download it at http://xml.apache.org/commons/"
            r7.println(r1)
        L_0x0038:
            if (r0 == 0) goto L_0x0042
            java.lang.String r1 = "Error while running org.apache.env.Which"
            r7.println(r1)
            r0.printStackTrace()
        L_0x0042:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.Diagnostics.doReportWhich(java.io.PrintStream):void");
    }

    private static void doReportTasksAvailability(PrintStream printStream) {
        InputStream resourceAsStream = Main.class.getResourceAsStream(MagicNames.TASKDEF_PROPERTIES_RESOURCE);
        if (resourceAsStream == null) {
            printStream.println("None available");
            return;
        }
        Properties properties = new Properties();
        try {
            properties.load(resourceAsStream);
            Enumeration keys = properties.keys();
            while (keys.hasMoreElements()) {
                String str = (String) keys.nextElement();
                try {
                    try {
                        Class.forName(properties.getProperty(str));
                        properties.remove(str);
                    } catch (NoClassDefFoundError e) {
                        String replace = e.getMessage().replace(IOUtils.DIR_SEPARATOR_UNIX, FilenameUtils.EXTENSION_SEPARATOR);
                        printStream.println(str + " : Missing dependency " + replace);
                    }
                } catch (ClassNotFoundException unused) {
                    printStream.println(str + " : Not Available (the implementation class is not present)");
                } catch (LinkageError unused2) {
                    printStream.println(str + " : Initialization error");
                }
            }
            if (properties.size() == 0) {
                printStream.println("All defined tasks are available");
            } else {
                printStream.println("A task being missing/unavailable should only matter if you are trying to use it");
            }
        } catch (IOException e2) {
            printStream.println(e2.getMessage());
        }
    }

    private static void doReportParserInfo(PrintStream printStream) {
        printParserInfo(printStream, "XML Parser", getXMLParserName(), getXMLParserLocation());
        printParserInfo(printStream, "Namespace-aware parser", getNamespaceParserName(), getNamespaceParserLocation());
    }

    private static void doReportXSLTProcessorInfo(PrintStream printStream) {
        printParserInfo(printStream, "XSLT Processor", getXSLTProcessorName(), getXSLTProcessorLocation());
    }

    private static void printParserInfo(PrintStream printStream, String str, String str2, String str3) {
        if (str2 == null) {
            str2 = "unknown";
        }
        if (str3 == null) {
            str3 = "unknown";
        }
        printStream.println(str + " : " + str2);
        printStream.println(str + " Location: " + str3);
    }

    /* JADX WARN: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: TypeSearchVarInfo not found in map for var: r1v8 java.lang.Object
    	at jadx.core.dex.visitors.typeinference.TypeSearchState.getVarInfo(TypeSearchState.java:34)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.lambda$resolveIndependentVariables$1(TypeSearch.java:174)
    	at java.base/java.util.stream.MatchOps$1MatchSink.accept(Unknown Source)
    	at java.base/java.util.ArrayList$ArrayListSpliterator.tryAdvance(Unknown Source)
    	at java.base/java.util.stream.ReferencePipeline$7$1.accept(Unknown Source)
    	at java.base/java.util.ArrayList$ArrayListSpliterator.tryAdvance(Unknown Source)
    	at java.base/java.util.stream.ReferencePipeline.forEachWithCancel(Unknown Source)
    	at java.base/java.util.stream.AbstractPipeline.copyIntoWithCancel(Unknown Source)
    	at java.base/java.util.stream.AbstractPipeline.copyInto(Unknown Source)
    	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(Unknown Source)
    	at java.base/java.util.stream.MatchOps$MatchOp.evaluateSequential(Unknown Source)
     */
    /* JADX WARN: Not initialized variable reg: 5, insn: 0x012d: MOVE  (r1 I:??[OBJECT, ARRAY]) = (r5 I:??[OBJECT, ARRAY]), block:B:63:0x012c */
    /* JADX WARN: Removed duplicated region for block: B:78:0x017f  */
    /* JADX WARN: Type inference failed for: r5v3 */
    /* JADX WARN: Unknown variable types count: 1 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void doReportTempDir(java.io.PrintStream r12) {
        /*
            Method dump skipped, instructions count: 393
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.Diagnostics.doReportTempDir(java.io.PrintStream):void");
    }

    private static void doReportLocale(PrintStream printStream) {
        Calendar instance = Calendar.getInstance();
        TimeZone timeZone = instance.getTimeZone();
        printStream.println("Timezone " + timeZone.getDisplayName() + " offset=" + timeZone.getOffset(instance.get(0), instance.get(1), instance.get(2), instance.get(5), instance.get(7), (((((instance.get(11) * 60) + instance.get(12)) * 60) + instance.get(13)) * 1000) + instance.get(14)));
    }

    private static void printProperty(PrintStream printStream, String str) {
        String property = getProperty(str);
        if (property != null) {
            printStream.print(str);
            printStream.print(" = ");
            printStream.print(Typography.f21049a);
            printStream.print(property);
            printStream.println(Typography.f21049a);
        }
    }

    private static void doReportProxy(PrintStream printStream) {
        printProperty(printStream, ProxySetup.HTTP_PROXY_HOST);
        printProperty(printStream, ProxySetup.HTTP_PROXY_PORT);
        printProperty(printStream, ProxySetup.HTTP_PROXY_USERNAME);
        printProperty(printStream, ProxySetup.HTTP_PROXY_PASSWORD);
        printProperty(printStream, ProxySetup.HTTP_NON_PROXY_HOSTS);
        printProperty(printStream, ProxySetup.HTTPS_PROXY_HOST);
        printProperty(printStream, ProxySetup.HTTPS_PROXY_PORT);
        printProperty(printStream, ProxySetup.HTTPS_NON_PROXY_HOSTS);
        printProperty(printStream, ProxySetup.FTP_PROXY_HOST);
        printProperty(printStream, ProxySetup.FTP_PROXY_PORT);
        printProperty(printStream, ProxySetup.FTP_NON_PROXY_HOSTS);
        printProperty(printStream, ProxySetup.SOCKS_PROXY_HOST);
        printProperty(printStream, ProxySetup.SOCKS_PROXY_PORT);
        printProperty(printStream, ProxySetup.SOCKS_PROXY_USERNAME);
        printProperty(printStream, ProxySetup.SOCKS_PROXY_PASSWORD);
        if (JavaEnvUtils.getJavaVersionNumber() >= 15) {
            printProperty(printStream, ProxySetup.USE_SYSTEM_PROXIES);
            try {
                Object newInstance = Class.forName("org.apache.tools.ant.util.java15.ProxyDiagnostics").newInstance();
                printStream.println("Java1.5+ proxy settings:");
                printStream.println(newInstance.toString());
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoClassDefFoundError unused) {
            }
        }
    }
}
