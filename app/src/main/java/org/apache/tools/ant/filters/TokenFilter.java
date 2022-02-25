package org.apache.tools.ant.filters;

import java.io.IOException;
import java.io.Reader;
import java.util.Enumeration;
import java.util.Vector;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.ProjectComponent;
import org.apache.tools.ant.types.RegularExpression;
import org.apache.tools.ant.types.Substitution;
import org.apache.tools.ant.util.LineTokenizer;
import org.apache.tools.ant.util.StringUtils;
import org.apache.tools.ant.util.Tokenizer;
import org.apache.tools.ant.util.regexp.Regexp;
import org.apache.tools.ant.util.regexp.RegexpUtil;

/* loaded from: classes2.dex */
public class TokenFilter extends BaseFilterReader implements ChainableReader {
    private Vector<Filter> filters = new Vector<>();
    private Tokenizer tokenizer = null;
    private String delimOutput = null;
    private String line = null;
    private int linePos = 0;

    /* loaded from: classes2.dex */
    public static class FileTokenizer extends org.apache.tools.ant.util.FileTokenizer {
    }

    /* loaded from: classes2.dex */
    public interface Filter {
        String filter(String str);
    }

    /* loaded from: classes2.dex */
    public static class StringTokenizer extends org.apache.tools.ant.util.StringTokenizer {
    }

    public TokenFilter() {
    }

    public TokenFilter(Reader reader) {
        super(reader);
    }

    @Override // java.io.FilterReader, java.io.Reader
    public int read() throws IOException {
        if (this.tokenizer == null) {
            this.tokenizer = new LineTokenizer();
        }
        while (true) {
            String str = this.line;
            if (str == null || str.length() == 0) {
                this.line = this.tokenizer.getToken(this.in);
                if (this.line == null) {
                    return -1;
                }
                Enumeration<Filter> elements = this.filters.elements();
                while (elements.hasMoreElements()) {
                    this.line = elements.nextElement().filter(this.line);
                    if (this.line == null) {
                        break;
                    }
                }
                this.linePos = 0;
                if (!(this.line == null || this.tokenizer.getPostToken().length() == 0)) {
                    if (this.delimOutput != null) {
                        this.line += this.delimOutput;
                    } else {
                        this.line += this.tokenizer.getPostToken();
                    }
                }
            } else {
                char charAt = this.line.charAt(this.linePos);
                this.linePos++;
                if (this.linePos == this.line.length()) {
                    this.line = null;
                }
                return charAt;
            }
        }
    }

    @Override // org.apache.tools.ant.filters.ChainableReader
    public final Reader chain(Reader reader) {
        TokenFilter tokenFilter = new TokenFilter(reader);
        tokenFilter.filters = this.filters;
        tokenFilter.tokenizer = this.tokenizer;
        tokenFilter.delimOutput = this.delimOutput;
        tokenFilter.setProject(getProject());
        return tokenFilter;
    }

    public void setDelimOutput(String str) {
        this.delimOutput = resolveBackSlash(str);
    }

    public void addLineTokenizer(LineTokenizer lineTokenizer) {
        add(lineTokenizer);
    }

    public void addStringTokenizer(StringTokenizer stringTokenizer) {
        add(stringTokenizer);
    }

    public void addFileTokenizer(FileTokenizer fileTokenizer) {
        add(fileTokenizer);
    }

    public void add(Tokenizer tokenizer) {
        if (this.tokenizer == null) {
            this.tokenizer = tokenizer;
            return;
        }
        throw new BuildException("Only one tokenizer allowed");
    }

    public void addReplaceString(ReplaceString replaceString) {
        this.filters.addElement(replaceString);
    }

    public void addContainsString(ContainsString containsString) {
        this.filters.addElement(containsString);
    }

    public void addReplaceRegex(ReplaceRegex replaceRegex) {
        this.filters.addElement(replaceRegex);
    }

    public void addContainsRegex(ContainsRegex containsRegex) {
        this.filters.addElement(containsRegex);
    }

    public void addTrim(Trim trim) {
        this.filters.addElement(trim);
    }

    public void addIgnoreBlank(IgnoreBlank ignoreBlank) {
        this.filters.addElement(ignoreBlank);
    }

    public void addDeleteCharacters(DeleteCharacters deleteCharacters) {
        this.filters.addElement(deleteCharacters);
    }

    public void add(Filter filter) {
        this.filters.addElement(filter);
    }

    /* loaded from: classes2.dex */
    public static abstract class ChainableReaderFilter extends ProjectComponent implements ChainableReader, Filter {
        private boolean byLine = true;

        public void setByLine(boolean z) {
            this.byLine = z;
        }

        @Override // org.apache.tools.ant.filters.ChainableReader
        public Reader chain(Reader reader) {
            TokenFilter tokenFilter = new TokenFilter(reader);
            if (!this.byLine) {
                tokenFilter.add(new FileTokenizer());
            }
            tokenFilter.add(this);
            return tokenFilter;
        }
    }

    /* loaded from: classes2.dex */
    public static class ReplaceString extends ChainableReaderFilter {
        private String from;

        /* renamed from: to */
        private String f14742to;

        public void setFrom(String str) {
            this.from = str;
        }

        public void setTo(String str) {
            this.f14742to = str;
        }

        @Override // org.apache.tools.ant.filters.TokenFilter.Filter
        public String filter(String str) {
            if (this.from != null) {
                StringBuffer stringBuffer = new StringBuffer();
                int i = 0;
                int indexOf = str.indexOf(this.from);
                while (indexOf >= 0) {
                    if (indexOf > i) {
                        stringBuffer.append(str.substring(i, indexOf));
                    }
                    String str2 = this.f14742to;
                    if (str2 != null) {
                        stringBuffer.append(str2);
                    }
                    i = this.from.length() + indexOf;
                    indexOf = str.indexOf(this.from, i);
                }
                if (str.length() > i) {
                    stringBuffer.append(str.substring(i, str.length()));
                }
                return stringBuffer.toString();
            }
            throw new BuildException("Missing from in stringreplace");
        }
    }

    /* loaded from: classes2.dex */
    public static class ContainsString extends ProjectComponent implements Filter {
        private String contains;

        public void setContains(String str) {
            this.contains = str;
        }

        @Override // org.apache.tools.ant.filters.TokenFilter.Filter
        public String filter(String str) {
            String str2 = this.contains;
            if (str2 == null) {
                throw new BuildException("Missing contains in containsstring");
            } else if (str.indexOf(str2) > -1) {
                return str;
            } else {
                return null;
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class ReplaceRegex extends ChainableReaderFilter {
        private String from;
        private int options;
        private Regexp regexp;
        private RegularExpression regularExpression;
        private Substitution substitution;

        /* renamed from: to */
        private String f14741to;
        private boolean initialized = false;
        private String flags = "";

        public void setPattern(String str) {
            this.from = str;
        }

        public void setReplace(String str) {
            this.f14741to = str;
        }

        public void setFlags(String str) {
            this.flags = str;
        }

        private void initialize() {
            if (!this.initialized) {
                this.options = TokenFilter.convertRegexOptions(this.flags);
                if (this.from != null) {
                    this.regularExpression = new RegularExpression();
                    this.regularExpression.setPattern(this.from);
                    this.regexp = this.regularExpression.getRegexp(getProject());
                    if (this.f14741to == null) {
                        this.f14741to = "";
                    }
                    this.substitution = new Substitution();
                    this.substitution.setExpression(this.f14741to);
                    return;
                }
                throw new BuildException("Missing pattern in replaceregex");
            }
        }

        @Override // org.apache.tools.ant.filters.TokenFilter.Filter
        public String filter(String str) {
            initialize();
            return !this.regexp.matches(str, this.options) ? str : this.regexp.substitute(str, this.substitution.getExpression(getProject()), this.options);
        }
    }

    /* loaded from: classes2.dex */
    public static class ContainsRegex extends ChainableReaderFilter {
        private String from;
        private int options;
        private Regexp regexp;
        private RegularExpression regularExpression;
        private Substitution substitution;

        /* renamed from: to */
        private String f14740to;
        private boolean initialized = false;
        private String flags = "";

        public void setPattern(String str) {
            this.from = str;
        }

        public void setReplace(String str) {
            this.f14740to = str;
        }

        public void setFlags(String str) {
            this.flags = str;
        }

        private void initialize() {
            if (!this.initialized) {
                this.options = TokenFilter.convertRegexOptions(this.flags);
                if (this.from != null) {
                    this.regularExpression = new RegularExpression();
                    this.regularExpression.setPattern(this.from);
                    this.regexp = this.regularExpression.getRegexp(getProject());
                    if (this.f14740to != null) {
                        this.substitution = new Substitution();
                        this.substitution.setExpression(this.f14740to);
                        return;
                    }
                    return;
                }
                throw new BuildException("Missing from in containsregex");
            }
        }

        @Override // org.apache.tools.ant.filters.TokenFilter.Filter
        public String filter(String str) {
            initialize();
            if (!this.regexp.matches(str, this.options)) {
                return null;
            }
            Substitution substitution = this.substitution;
            return substitution == null ? str : this.regexp.substitute(str, substitution.getExpression(getProject()), this.options);
        }
    }

    /* loaded from: classes2.dex */
    public static class Trim extends ChainableReaderFilter {
        @Override // org.apache.tools.ant.filters.TokenFilter.Filter
        public String filter(String str) {
            return str.trim();
        }
    }

    /* loaded from: classes2.dex */
    public static class IgnoreBlank extends ChainableReaderFilter {
        @Override // org.apache.tools.ant.filters.TokenFilter.Filter
        public String filter(String str) {
            if (str.trim().length() == 0) {
                return null;
            }
            return str;
        }
    }

    /* loaded from: classes2.dex */
    public static class DeleteCharacters extends ProjectComponent implements ChainableReader, Filter {
        private String deleteChars = "";

        public void setChars(String str) {
            this.deleteChars = TokenFilter.resolveBackSlash(str);
        }

        @Override // org.apache.tools.ant.filters.TokenFilter.Filter
        public String filter(String str) {
            StringBuffer stringBuffer = new StringBuffer(str.length());
            for (int i = 0; i < str.length(); i++) {
                char charAt = str.charAt(i);
                if (!isDeleteCharacter(charAt)) {
                    stringBuffer.append(charAt);
                }
            }
            return stringBuffer.toString();
        }

        @Override // org.apache.tools.ant.filters.ChainableReader
        public Reader chain(Reader reader) {
            return new BaseFilterReader(reader) { // from class: org.apache.tools.ant.filters.TokenFilter.DeleteCharacters.1
                @Override // java.io.FilterReader, java.io.Reader
                public int read() throws IOException {
                    int read;
                    do {
                        read = this.in.read();
                        if (read == -1) {
                            return read;
                        }
                    } while (DeleteCharacters.this.isDeleteCharacter((char) read));
                    return read;
                }
            };
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isDeleteCharacter(char c) {
            for (int i = 0; i < this.deleteChars.length(); i++) {
                if (this.deleteChars.charAt(i) == c) {
                    return true;
                }
            }
            return false;
        }
    }

    public static String resolveBackSlash(String str) {
        return StringUtils.resolveBackSlash(str);
    }

    public static int convertRegexOptions(String str) {
        return RegexpUtil.asOptions(str);
    }
}
