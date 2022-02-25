package org.apache.tools.ant.filters;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import java.util.SortedMap;
import java.util.TreeMap;
import org.apache.tools.ant.types.Parameter;
import org.apache.tools.ant.types.Resource;
import org.apache.tools.ant.types.resources.FileResource;
import org.apache.tools.ant.util.FileUtils;

/* loaded from: classes2.dex */
public final class ReplaceTokens extends BaseParamFilterReader implements ChainableReader {
    private static final String DEFAULT_BEGIN_TOKEN = "@";
    private static final String DEFAULT_END_TOKEN = "@";
    private Hashtable<String, String> hash = new Hashtable<>();
    private final TreeMap<String, String> resolvedTokens = new TreeMap<>();
    private boolean resolvedTokensBuilt = false;
    private String readBuffer = "";
    private String replaceData = null;
    private int replaceIndex = -1;
    private String beginToken = "@";
    private String endToken = "@";

    public ReplaceTokens() {
    }

    public ReplaceTokens(Reader reader) {
        super(reader);
    }

    @Override // java.io.FilterReader, java.io.Reader
    public int read() throws IOException {
        int read;
        if (!getInitialized()) {
            initialize();
            setInitialized(true);
        }
        if (!this.resolvedTokensBuilt) {
            for (String str : this.hash.keySet()) {
                this.resolvedTokens.put(this.beginToken + str + this.endToken, this.hash.get(str));
            }
            this.resolvedTokensBuilt = true;
        }
        String str2 = this.replaceData;
        if (str2 != null) {
            if (this.replaceIndex < str2.length()) {
                String str3 = this.replaceData;
                int i = this.replaceIndex;
                this.replaceIndex = i + 1;
                return str3.charAt(i);
            }
            this.replaceData = null;
        }
        if (this.readBuffer.length() == 0) {
            int read2 = this.in.read();
            if (read2 == -1) {
                return read2;
            }
            this.readBuffer += ((char) read2);
        }
        while (true) {
            SortedMap<String, String> tailMap = this.resolvedTokens.tailMap(this.readBuffer);
            if (tailMap.isEmpty() || !tailMap.firstKey().startsWith(this.readBuffer)) {
                break;
            } else if (this.readBuffer.equals(tailMap.firstKey())) {
                this.replaceData = this.resolvedTokens.get(this.readBuffer);
                this.replaceIndex = 0;
                this.readBuffer = "";
                return read();
            } else {
                if (this.in.read() == -1) {
                    return getFirstCharacterFromReadBuffer();
                }
                this.readBuffer += ((char) read);
            }
        }
        return getFirstCharacterFromReadBuffer();
    }

    private int getFirstCharacterFromReadBuffer() {
        if (this.readBuffer.length() <= 0) {
            return -1;
        }
        char charAt = this.readBuffer.charAt(0);
        this.readBuffer = this.readBuffer.substring(1);
        return charAt;
    }

    public void setBeginToken(String str) {
        this.beginToken = str;
    }

    private String getBeginToken() {
        return this.beginToken;
    }

    public void setEndToken(String str) {
        this.endToken = str;
    }

    private String getEndToken() {
        return this.endToken;
    }

    public void setPropertiesResource(Resource resource) {
        makeTokensFromProperties(resource);
    }

    public void addConfiguredToken(Token token) {
        this.hash.put(token.getKey(), token.getValue());
        this.resolvedTokensBuilt = false;
    }

    private Properties getProperties(Resource resource) {
        Properties properties = new Properties();
        InputStream inputStream = null;
        try {
            try {
                inputStream = resource.getInputStream();
                properties.load(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return properties;
        } finally {
            FileUtils.close(inputStream);
        }
    }

    private void setTokens(Hashtable<String, String> hashtable) {
        this.hash = hashtable;
    }

    private Hashtable<String, String> getTokens() {
        return this.hash;
    }

    @Override // org.apache.tools.ant.filters.ChainableReader
    public Reader chain(Reader reader) {
        ReplaceTokens replaceTokens = new ReplaceTokens(reader);
        replaceTokens.setBeginToken(getBeginToken());
        replaceTokens.setEndToken(getEndToken());
        replaceTokens.setTokens(getTokens());
        replaceTokens.setInitialized(true);
        return replaceTokens;
    }

    private void initialize() {
        Parameter[] parameters = getParameters();
        if (parameters != null) {
            for (Parameter parameter : parameters) {
                if (parameter != null) {
                    String type = parameter.getType();
                    if ("tokenchar".equals(type)) {
                        String name = parameter.getName();
                        if ("begintoken".equals(name)) {
                            this.beginToken = parameter.getValue();
                        } else if ("endtoken".equals(name)) {
                            this.endToken = parameter.getValue();
                        }
                    } else if ("token".equals(type)) {
                        this.hash.put(parameter.getName(), parameter.getValue());
                    } else if ("propertiesfile".equals(type)) {
                        makeTokensFromProperties(new FileResource(new File(parameter.getValue())));
                    }
                }
            }
        }
    }

    private void makeTokensFromProperties(Resource resource) {
        Properties properties = getProperties(resource);
        Enumeration keys = properties.keys();
        while (keys.hasMoreElements()) {
            String str = (String) keys.nextElement();
            this.hash.put(str, properties.getProperty(str));
        }
    }

    /* loaded from: classes2.dex */
    public static class Token {
        private String key;
        private String value;

        public final void setKey(String str) {
            this.key = str;
        }

        public final void setValue(String str) {
            this.value = str;
        }

        public final String getKey() {
            return this.key;
        }

        public final String getValue() {
            return this.value;
        }
    }
}
