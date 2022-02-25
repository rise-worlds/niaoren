package org.apache.tools.ant.filters;

import java.io.IOException;
import java.io.Reader;
import java.util.Vector;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.Parameter;
import org.apache.tools.ant.types.RegularExpression;
import org.apache.tools.ant.util.regexp.RegexpUtil;

/* loaded from: classes2.dex */
public final class LineContainsRegExp extends BaseParamFilterReader implements ChainableReader {
    private static final String CS_KEY = "casesensitive";
    private static final String NEGATE_KEY = "negate";
    private static final String REGEXP_KEY = "regexp";
    private Vector<RegularExpression> regexps = new Vector<>();
    private String line = null;
    private boolean negate = false;
    private int regexpOptions = 0;

    public LineContainsRegExp() {
    }

    public LineContainsRegExp(Reader reader) {
        super(reader);
    }

    @Override // java.io.FilterReader, java.io.Reader
    public int read() throws IOException {
        boolean z;
        if (!getInitialized()) {
            initialize();
            setInitialized(true);
        }
        char c = 65535;
        String str = this.line;
        if (str != null) {
            c = str.charAt(0);
            if (this.line.length() == 1) {
                this.line = null;
            } else {
                this.line = this.line.substring(1);
            }
        } else {
            int size = this.regexps.size();
            do {
                this.line = readLine();
                if (this.line == null) {
                    break;
                }
                z = true;
                for (int i = 0; z && i < size; i++) {
                    z = this.regexps.elementAt(i).getRegexp(getProject()).matches(this.line, this.regexpOptions);
                }
            } while (!(z ^ isNegated()));
            if (this.line != null) {
                return read();
            }
        }
        return c;
    }

    public void addConfiguredRegexp(RegularExpression regularExpression) {
        this.regexps.addElement(regularExpression);
    }

    private void setRegexps(Vector<RegularExpression> vector) {
        this.regexps = vector;
    }

    private Vector<RegularExpression> getRegexps() {
        return this.regexps;
    }

    @Override // org.apache.tools.ant.filters.ChainableReader
    public Reader chain(Reader reader) {
        LineContainsRegExp lineContainsRegExp = new LineContainsRegExp(reader);
        lineContainsRegExp.setRegexps(getRegexps());
        lineContainsRegExp.setNegate(isNegated());
        lineContainsRegExp.setCaseSensitive(!RegexpUtil.hasFlag(this.regexpOptions, 256));
        return lineContainsRegExp;
    }

    public void setNegate(boolean z) {
        this.negate = z;
    }

    public void setCaseSensitive(boolean z) {
        this.regexpOptions = RegexpUtil.asOptions(z);
    }

    public boolean isNegated() {
        return this.negate;
    }

    private void initialize() {
        Parameter[] parameters = getParameters();
        if (parameters != null) {
            for (int i = 0; i < parameters.length; i++) {
                if ("regexp".equals(parameters[i].getType())) {
                    String value = parameters[i].getValue();
                    RegularExpression regularExpression = new RegularExpression();
                    regularExpression.setPattern(value);
                    this.regexps.addElement(regularExpression);
                } else if ("negate".equals(parameters[i].getType())) {
                    setNegate(Project.toBoolean(parameters[i].getValue()));
                } else if ("casesensitive".equals(parameters[i].getType())) {
                    setCaseSensitive(Project.toBoolean(parameters[i].getValue()));
                }
            }
        }
    }
}
