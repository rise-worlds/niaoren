package org.apache.http.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;
import org.apache.commons.p105io.FilenameUtils;
import org.apache.commons.p105io.IOUtils;

@Deprecated
/* loaded from: assets/org.apache.http.legacy.boot */
public class VersionInfo {
    public static final String PROPERTY_MODULE = "info.module";
    public static final String PROPERTY_RELEASE = "info.release";
    public static final String PROPERTY_TIMESTAMP = "info.timestamp";
    public static final String UNAVAILABLE = "UNAVAILABLE";
    public static final String VERSION_PROPERTY_FILE = "version.properties";
    private final String infoClassloader;
    private final String infoModule;
    private final String infoPackage;
    private final String infoRelease;
    private final String infoTimestamp;

    protected VersionInfo(String pckg, String module, String release, String time, String clsldr) {
        if (pckg != null) {
            this.infoPackage = pckg;
            this.infoModule = module != null ? module : UNAVAILABLE;
            this.infoRelease = release != null ? release : UNAVAILABLE;
            this.infoTimestamp = time != null ? time : UNAVAILABLE;
            this.infoClassloader = clsldr != null ? clsldr : UNAVAILABLE;
            return;
        }
        throw new IllegalArgumentException("Package identifier must not be null.");
    }

    public final String getPackage() {
        return this.infoPackage;
    }

    public final String getModule() {
        return this.infoModule;
    }

    public final String getRelease() {
        return this.infoRelease;
    }

    public final String getTimestamp() {
        return this.infoTimestamp;
    }

    public final String getClassloader() {
        return this.infoClassloader;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(20 + this.infoPackage.length() + this.infoModule.length() + this.infoRelease.length() + this.infoTimestamp.length() + this.infoClassloader.length());
        sb.append("VersionInfo(");
        sb.append(this.infoPackage);
        sb.append(':');
        sb.append(this.infoModule);
        if (!UNAVAILABLE.equals(this.infoRelease)) {
            sb.append(':');
            sb.append(this.infoRelease);
        }
        if (!UNAVAILABLE.equals(this.infoTimestamp)) {
            sb.append(':');
            sb.append(this.infoTimestamp);
        }
        sb.append(')');
        if (!UNAVAILABLE.equals(this.infoClassloader)) {
            sb.append('@');
            sb.append(this.infoClassloader);
        }
        return sb.toString();
    }

    public static final VersionInfo[] loadVersionInfo(String[] pckgs, ClassLoader clsldr) {
        if (pckgs != null) {
            ArrayList vil = new ArrayList(pckgs.length);
            for (String str : pckgs) {
                VersionInfo vi = loadVersionInfo(str, clsldr);
                if (vi != null) {
                    vil.add(vi);
                }
            }
            int i = vil.size();
            return (VersionInfo[]) vil.toArray(new VersionInfo[i]);
        }
        throw new IllegalArgumentException("Package identifier list must not be null.");
    }

    public static final VersionInfo loadVersionInfo(String pckg, ClassLoader clsldr) {
        if (pckg != null) {
            if (clsldr == null) {
                clsldr = Thread.currentThread().getContextClassLoader();
            }
            Properties vip = null;
            try {
                InputStream is = clsldr.getResourceAsStream(pckg.replace(FilenameUtils.EXTENSION_SEPARATOR, IOUtils.DIR_SEPARATOR_UNIX) + "/" + VERSION_PROPERTY_FILE);
                if (is != null) {
                    Properties props = new Properties();
                    props.load(is);
                    vip = props;
                    is.close();
                }
            } catch (IOException e) {
            }
            if (vip == null) {
                return null;
            }
            VersionInfo result = fromMap(pckg, vip, clsldr);
            return result;
        }
        throw new IllegalArgumentException("Package identifier must not be null.");
    }

    protected static final VersionInfo fromMap(String pckg, Map info, ClassLoader clsldr) {
        if (pckg != null) {
            String module = null;
            String release = null;
            String timestamp = null;
            if (info != null) {
                module = (String) info.get(PROPERTY_MODULE);
                if (module != null && module.length() < 1) {
                    module = null;
                }
                release = (String) info.get(PROPERTY_RELEASE);
                if (release != null && (release.length() < 1 || release.equals("${pom.version}"))) {
                    release = null;
                }
                timestamp = (String) info.get(PROPERTY_TIMESTAMP);
                if (timestamp != null && (timestamp.length() < 1 || timestamp.equals("${mvn.timestamp}"))) {
                    timestamp = null;
                }
            }
            String clsldrstr = null;
            if (clsldr != null) {
                clsldrstr = clsldr.toString();
            }
            return new VersionInfo(pckg, module, release, timestamp, clsldrstr);
        }
        throw new IllegalArgumentException("Package identifier must not be null.");
    }
}
