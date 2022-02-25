package org.apache.tools.ant.filters;

import java.io.IOException;
import java.io.Reader;
import java.util.Vector;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.Parameter;

/* loaded from: classes2.dex */
public final class LineContains extends BaseParamFilterReader implements ChainableReader {
    private static final String CONTAINS_KEY = "contains";
    private static final String NEGATE_KEY = "negate";
    private Vector<String> contains = new Vector<>();
    private String line = null;
    private boolean negate = false;

    public LineContains() {
    }

    public LineContains(Reader reader) {
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
            int size = this.contains.size();
            do {
                this.line = readLine();
                if (this.line == null) {
                    break;
                }
                z = true;
                for (int i = 0; z && i < size; i++) {
                    z = this.line.indexOf(this.contains.elementAt(i)) >= 0;
                }
            } while (!(z ^ isNegated()));
            if (this.line != null) {
                return read();
            }
        }
        return c;
    }

    public void addConfiguredContains(Contains contains) {
        this.contains.addElement(contains.getValue());
    }

    public void setNegate(boolean z) {
        this.negate = z;
    }

    public boolean isNegated() {
        return this.negate;
    }

    private void setContains(Vector<String> vector) {
        this.contains = vector;
    }

    private Vector<String> getContains() {
        return this.contains;
    }

    @Override // org.apache.tools.ant.filters.ChainableReader
    public Reader chain(Reader reader) {
        LineContains lineContains = new LineContains(reader);
        lineContains.setContains(getContains());
        lineContains.setNegate(isNegated());
        return lineContains;
    }

    private void initialize() {
        Parameter[] parameters = getParameters();
        if (parameters != null) {
            for (int i = 0; i < parameters.length; i++) {
                if (CONTAINS_KEY.equals(parameters[i].getType())) {
                    this.contains.addElement(parameters[i].getValue());
                } else if ("negate".equals(parameters[i].getType())) {
                    setNegate(Project.toBoolean(parameters[i].getValue()));
                }
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class Contains {
        private String value;

        public final void setValue(String str) {
            this.value = str;
        }

        public final String getValue() {
            return this.value;
        }
    }
}
