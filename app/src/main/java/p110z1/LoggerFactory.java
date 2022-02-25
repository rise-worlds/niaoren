package p110z1;

import org.apache.commons.logging.LogFactory;
import p110z1.Log;

/* renamed from: z1.uj */
/* loaded from: classes3.dex */
public class LoggerFactory {

    /* renamed from: a */
    public static final String f23460a = "com.j256.ormlite.logger.type";

    /* renamed from: b */
    private static EnumC5572a f23461b;

    private LoggerFactory() {
    }

    /* renamed from: a */
    public static C5570ui m545a(Class<?> cls) {
        return m544a(cls.getName());
    }

    /* renamed from: a */
    public static C5570ui m544a(String str) {
        if (f23461b == null) {
            f23461b = m546a();
        }
        return new C5570ui(f23461b.createLog(str));
    }

    /* renamed from: b */
    public static String m543b(String str) {
        String[] split = str.split("\\.");
        return split.length <= 1 ? str : split[split.length - 1];
    }

    /* renamed from: a */
    private static EnumC5572a m546a() {
        EnumC5572a[] values;
        String property = System.getProperty(f23460a);
        if (property != null) {
            try {
                return EnumC5572a.valueOf(property);
            } catch (IllegalArgumentException unused) {
                new LocalLog(LoggerFactory.class.getName()).mo622a(Log.EnumC5569a.WARNING, "Could not find valid log-type from system property 'com.j256.ormlite.logger.type', value '" + property + "'");
            }
        }
        for (EnumC5572a aVar : EnumC5572a.values()) {
            if (aVar.isAvailable()) {
                return aVar;
            }
        }
        return EnumC5572a.LOCAL;
    }

    /* compiled from: LoggerFactory.java */
    /* renamed from: z1.uj$a */
    /* loaded from: classes3.dex */
    public enum EnumC5572a {
        ANDROID("android.util.Log", "com.j256.ormlite.android.AndroidLog"),
        SLF4J("org.slf4j.LoggerFactory", "com.j256.ormlite.logger.Slf4jLoggingLog"),
        COMMONS_LOGGING(LogFactory.FACTORY_PROPERTY, "com.j256.ormlite.logger.CommonsLoggingLog"),
        LOG4J2("org.apache.logging.log4j.LogManager", "com.j256.ormlite.logger.Log4j2Log"),
        LOG4J("org.apache.log4j.Logger", "com.j256.ormlite.logger.Log4jLog"),
        LOCAL(LocalLog.class.getName(), LocalLog.class.getName()) { // from class: z1.uj.a.1
            @Override // p110z1.LoggerFactory.EnumC5572a
            public boolean isAvailable() {
                return true;
            }

            @Override // p110z1.LoggerFactory.EnumC5572a
            public Log createLog(String str) {
                return new LocalLog(str);
            }
        };
        
        private final String detectClassName;
        private final String logClassName;

        EnumC5572a(String str, String str2) {
            this.detectClassName = str;
            this.logClassName = str2;
        }

        public Log createLog(String str) {
            try {
                return createLogFromClassName(str);
            } catch (Exception e) {
                LocalLog ugVar = new LocalLog(str);
                Log.EnumC5569a aVar = Log.EnumC5569a.WARNING;
                ugVar.mo622a(aVar, "Unable to call constructor with single String argument for class " + this.logClassName + ", so had to use local log: " + e.getMessage());
                return ugVar;
            }
        }

        public boolean isAvailable() {
            if (!isAvailableTestClass()) {
                return false;
            }
            try {
                createLogFromClassName(getClass().getName()).mo623a(Log.EnumC5569a.INFO);
                return true;
            } catch (Exception unused) {
                return false;
            }
        }

        Log createLogFromClassName(String str) throws Exception {
            return (Log) Class.forName(this.logClassName).getConstructor(String.class).newInstance(str);
        }

        boolean isAvailableTestClass() {
            try {
                Class.forName(this.detectClassName);
                return true;
            } catch (Exception unused) {
                return false;
            }
        }
    }
}
