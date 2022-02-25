package org.apache.tools.ant.taskdefs;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;
import p110z1.Consts;

/* loaded from: classes2.dex */
public class HostInfo extends Task {
    private static final String ADDR4 = "ADDR4";
    private static final String ADDR6 = "ADDR6";
    private static final String DEF_DOMAIN = "localdomain";
    private static final String DEF_LOCAL_ADDR4 = "127.0.0.1";
    private static final String DEF_LOCAL_ADDR6 = "::1";
    private static final String DEF_LOCAL_NAME = "localhost";
    private static final String DEF_REM_ADDR4 = "0.0.0.0";
    private static final String DEF_REM_ADDR6 = "::";
    private static final String DOMAIN = "DOMAIN";
    private static final String NAME = "NAME";
    private InetAddress best4;
    private InetAddress best6;
    private String host;
    private List<InetAddress> inetAddrs;
    private InetAddress nameAddr;
    private String prefix = "";

    public void setPrefix(String str) {
        this.prefix = str;
        if (!this.prefix.endsWith(Consts.f23430h)) {
            this.prefix += Consts.f23430h;
        }
    }

    public void setHost(String str) {
        this.host = str;
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        String str = this.host;
        if (str == null || "".equals(str)) {
            executeLocal();
        } else {
            executeRemote();
        }
    }

    private void executeLocal() {
        try {
            this.inetAddrs = new LinkedList();
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    this.inetAddrs.add(inetAddresses.nextElement());
                }
            }
            selectAddresses();
            if (this.nameAddr == null || !hasHostName(this.nameAddr)) {
                setProperty(DOMAIN, DEF_DOMAIN);
                setProperty(NAME, "localhost");
            } else {
                setDomainAndName(this.nameAddr.getCanonicalHostName());
            }
            if (this.best4 != null) {
                setProperty(ADDR4, this.best4.getHostAddress());
            } else {
                setProperty(ADDR4, DEF_LOCAL_ADDR4);
            }
            if (this.best6 != null) {
                setProperty(ADDR6, this.best6.getHostAddress());
            } else {
                setProperty(ADDR6, DEF_LOCAL_ADDR6);
            }
        } catch (Exception e) {
            log("Error retrieving local host information", e, 1);
            setProperty(DOMAIN, DEF_DOMAIN);
            setProperty(NAME, "localhost");
            setProperty(ADDR4, DEF_LOCAL_ADDR4);
            setProperty(ADDR6, DEF_LOCAL_ADDR6);
        }
    }

    private boolean hasHostName(InetAddress inetAddress) {
        return !inetAddress.getHostAddress().equals(inetAddress.getCanonicalHostName());
    }

    private void selectAddresses() {
        for (InetAddress inetAddress : this.inetAddrs) {
            if (!inetAddress.isMulticastAddress()) {
                if (inetAddress instanceof Inet4Address) {
                    this.best4 = selectBestAddress(this.best4, inetAddress);
                } else if (inetAddress instanceof Inet6Address) {
                    this.best6 = selectBestAddress(this.best6, inetAddress);
                }
            }
        }
        this.nameAddr = selectBestAddress(this.best4, this.best6);
    }

    private InetAddress selectBestAddress(InetAddress inetAddress, InetAddress inetAddress2) {
        if (inetAddress != null) {
            if (inetAddress2 == null || inetAddress2.isLoopbackAddress()) {
                return inetAddress;
            }
            if (inetAddress2.isLinkLocalAddress()) {
                if (!inetAddress.isLoopbackAddress()) {
                    return inetAddress;
                }
            } else if (inetAddress2.isSiteLocalAddress()) {
                if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress() && (!inetAddress.isSiteLocalAddress() || hasHostName(inetAddress))) {
                    return inetAddress;
                }
            } else if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress() && !inetAddress.isSiteLocalAddress() && hasHostName(inetAddress)) {
                return inetAddress;
            }
        }
        return inetAddress2;
    }

    private void executeRemote() {
        try {
            this.inetAddrs = Arrays.asList(InetAddress.getAllByName(this.host));
            selectAddresses();
            if (this.nameAddr == null || !hasHostName(this.nameAddr)) {
                setDomainAndName(this.host);
            } else {
                setDomainAndName(this.nameAddr.getCanonicalHostName());
            }
            if (this.best4 != null) {
                setProperty(ADDR4, this.best4.getHostAddress());
            } else {
                setProperty(ADDR4, DEF_REM_ADDR4);
            }
            if (this.best6 != null) {
                setProperty(ADDR6, this.best6.getHostAddress());
            } else {
                setProperty(ADDR6, DEF_REM_ADDR6);
            }
        } catch (Exception e) {
            log("Error retrieving remote host information for host:" + this.host + Consts.f23430h, e, 1);
            setDomainAndName(this.host);
            setProperty(ADDR4, DEF_REM_ADDR4);
            setProperty(ADDR6, DEF_REM_ADDR6);
        }
    }

    private void setDomainAndName(String str) {
        int indexOf = str.indexOf(46);
        if (indexOf > 0) {
            setProperty(NAME, str.substring(0, indexOf));
            setProperty(DOMAIN, str.substring(indexOf + 1));
            return;
        }
        setProperty(NAME, str);
        setProperty(DOMAIN, DEF_DOMAIN);
    }

    private void setProperty(String str, String str2) {
        Project project = getProject();
        project.setNewProperty(this.prefix + str, str2);
    }
}
