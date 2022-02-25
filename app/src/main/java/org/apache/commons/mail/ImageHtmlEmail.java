package org.apache.commons.mail;

import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.activation.DataSource;

/* loaded from: classes2.dex */
public class ImageHtmlEmail extends HtmlEmail {
    private DataSourceResolver dataSourceResolver;
    public static final String REGEX_IMG_SRC = "(<[Ii][Mm][Gg]\\s*[^>]*?\\s+[Ss][Rr][Cc]\\s*=\\s*[\"'])([^\"']+?)([\"'])";
    private static final Pattern IMG_PATTERN = Pattern.compile(REGEX_IMG_SRC);
    public static final String REGEX_SCRIPT_SRC = "(<[Ss][Cc][Rr][Ii][Pp][Tt]\\s*.*?\\s+[Ss][Rr][Cc]\\s*=\\s*[\"'])([^\"']+?)([\"'])";
    private static final Pattern SCRIPT_PATTERN = Pattern.compile(REGEX_SCRIPT_SRC);

    public DataSourceResolver getDataSourceResolver() {
        return this.dataSourceResolver;
    }

    public void setDataSourceResolver(DataSourceResolver dataSourceResolver) {
        this.dataSourceResolver = dataSourceResolver;
    }

    @Override // org.apache.commons.mail.HtmlEmail, org.apache.commons.mail.MultiPartEmail, org.apache.commons.mail.Email
    public void buildMimeMessage() throws EmailException {
        try {
            setHtmlMsg(replacePattern(replacePattern(this.html, IMG_PATTERN), SCRIPT_PATTERN));
            super.buildMimeMessage();
        } catch (IOException e) {
            throw new EmailException("Building the MimeMessage failed", e);
        }
    }

    private String replacePattern(String str, Pattern pattern) throws EmailException, IOException {
        DataSource dataSource;
        StringBuffer stringBuffer = new StringBuffer();
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            String group = matcher.group(2);
            if (hashMap2.get(group) == null) {
                dataSource = getDataSourceResolver().resolve(group);
                if (dataSource != null) {
                    hashMap2.put(group, dataSource);
                }
            } else {
                dataSource = (DataSource) hashMap2.get(group);
            }
            if (dataSource != null) {
                String name = dataSource.getName();
                if (!EmailUtils.isEmpty(name)) {
                    group = name;
                }
                String str2 = (String) hashMap.get(group);
                if (str2 == null) {
                    str2 = embed(dataSource, group);
                    hashMap.put(group, str2);
                }
                matcher.appendReplacement(stringBuffer, Matcher.quoteReplacement(matcher.group(1) + "cid:" + str2 + matcher.group(3)));
            }
        }
        matcher.appendTail(stringBuffer);
        hashMap.clear();
        hashMap2.clear();
        return stringBuffer.toString();
    }
}
