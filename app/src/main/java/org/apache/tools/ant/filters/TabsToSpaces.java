package org.apache.tools.ant.filters;

import java.io.IOException;
import java.io.Reader;
import org.apache.tools.ant.types.Parameter;

/* loaded from: classes2.dex */
public final class TabsToSpaces extends BaseParamFilterReader implements ChainableReader {
    private static final int DEFAULT_TAB_LENGTH = 8;
    private static final String TAB_LENGTH_KEY = "tablength";
    private int tabLength = 8;
    private int spacesRemaining = 0;

    public TabsToSpaces() {
    }

    public TabsToSpaces(Reader reader) {
        super(reader);
    }

    @Override // java.io.FilterReader, java.io.Reader
    public int read() throws IOException {
        if (!getInitialized()) {
            initialize();
            setInitialized(true);
        }
        int i = this.spacesRemaining;
        if (i > 0) {
            this.spacesRemaining = i - 1;
            return 32;
        }
        int read = this.in.read();
        if (read != 9) {
            return read;
        }
        this.spacesRemaining = this.tabLength - 1;
        return 32;
    }

    public void setTablength(int i) {
        this.tabLength = i;
    }

    private int getTablength() {
        return this.tabLength;
    }

    @Override // org.apache.tools.ant.filters.ChainableReader
    public Reader chain(Reader reader) {
        TabsToSpaces tabsToSpaces = new TabsToSpaces(reader);
        tabsToSpaces.setTablength(getTablength());
        tabsToSpaces.setInitialized(true);
        return tabsToSpaces;
    }

    private void initialize() {
        Parameter[] parameters = getParameters();
        if (parameters != null) {
            for (int i = 0; i < parameters.length; i++) {
                if (parameters[i] != null && TAB_LENGTH_KEY.equals(parameters[i].getName())) {
                    this.tabLength = Integer.parseInt(parameters[i].getValue());
                    return;
                }
            }
        }
    }
}
