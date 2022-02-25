package org.apache.commons.mail;

import java.net.URL;

/* loaded from: classes2.dex */
public class EmailAttachment {
    public static final String ATTACHMENT = "attachment";
    public static final String INLINE = "inline";
    private URL url;
    private String name = "";
    private String description = "";
    private String path = "";
    private String disposition = "attachment";

    public String getDescription() {
        return this.description;
    }

    public String getName() {
        return this.name;
    }

    public String getPath() {
        return this.path;
    }

    public URL getURL() {
        return this.url;
    }

    public String getDisposition() {
        return this.disposition;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setPath(String str) {
        this.path = str;
    }

    public void setURL(URL url) {
        this.url = url;
    }

    public void setDisposition(String str) {
        this.disposition = str;
    }
}
