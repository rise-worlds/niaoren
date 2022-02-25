package org.apache.http;

@Deprecated
/* loaded from: assets/org.apache.http.legacy.boot */
public interface Header {
    HeaderElement[] getElements() throws ParseException;

    String getName();

    String getValue();
}
