package org.apache.tools.ant.taskdefs;

import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Reader;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.Vector;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.types.EnumeratedAttribute;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.Resource;
import org.apache.tools.ant.types.ResourceCollection;
import org.apache.tools.ant.types.resources.Appendable;
import org.apache.tools.ant.types.resources.FileProvider;
import org.apache.tools.ant.types.resources.FileResource;
import org.apache.tools.ant.types.resources.Union;
import org.apache.tools.ant.util.FileUtils;
import org.apache.tools.ant.util.KeepAliveOutputStream;
import org.apache.tools.ant.util.StringUtils;
import p110z1.C4963cj;

/* loaded from: classes2.dex */
public class SQLExec extends JDBCTask {
    private boolean rawBlobs;
    private Union resources;
    private int goodSql = 0;
    private int totalSql = 0;
    private Connection conn = null;
    private Statement statement = null;
    private File srcFile = null;
    private String sqlCommand = "";
    private Vector transactions = new Vector();
    private String delimiter = C4963cj.f20745b;
    private String delimiterType = DelimiterType.NORMAL;
    private boolean print = false;
    private boolean showheaders = true;
    private boolean showtrailers = true;
    private Resource output = null;
    private String outputEncoding = null;
    private String onError = "abort";
    private String encoding = null;
    private boolean append = false;
    private boolean keepformat = false;
    private boolean escapeProcessing = true;
    private boolean expandProperties = true;
    private boolean strictDelimiterMatching = true;
    private boolean showWarnings = false;
    private String csvColumnSep = ",";
    private String csvQuoteChar = null;
    private boolean treatWarningsAsErrors = false;
    private String errorProperty = null;
    private String warningProperty = null;
    private String rowCountProperty = null;

    /* loaded from: classes2.dex */
    public static class DelimiterType extends EnumeratedAttribute {
        public static final String NORMAL = "normal";
        public static final String ROW = "row";

        @Override // org.apache.tools.ant.types.EnumeratedAttribute
        public String[] getValues() {
            return new String[]{NORMAL, ROW};
        }
    }

    public void setSrc(File file) {
        this.srcFile = file;
    }

    public void setExpandProperties(boolean z) {
        this.expandProperties = z;
    }

    public boolean getExpandProperties() {
        return this.expandProperties;
    }

    public void addText(String str) {
        this.sqlCommand += str;
    }

    public void addFileset(FileSet fileSet) {
        add(fileSet);
    }

    public void add(ResourceCollection resourceCollection) {
        if (resourceCollection != null) {
            synchronized (this) {
                if (this.resources == null) {
                    this.resources = new Union();
                }
            }
            this.resources.add(resourceCollection);
            return;
        }
        throw new BuildException("Cannot add null ResourceCollection");
    }

    public Transaction createTransaction() {
        Transaction transaction = new Transaction();
        this.transactions.addElement(transaction);
        return transaction;
    }

    public void setEncoding(String str) {
        this.encoding = str;
    }

    public void setDelimiter(String str) {
        this.delimiter = str;
    }

    public void setDelimiterType(DelimiterType delimiterType) {
        this.delimiterType = delimiterType.getValue();
    }

    public void setPrint(boolean z) {
        this.print = z;
    }

    public void setShowheaders(boolean z) {
        this.showheaders = z;
    }

    public void setShowtrailers(boolean z) {
        this.showtrailers = z;
    }

    public void setOutput(File file) {
        setOutput(new FileResource(getProject(), file));
    }

    public void setOutput(Resource resource) {
        this.output = resource;
    }

    public void setOutputEncoding(String str) {
        this.outputEncoding = str;
    }

    public void setAppend(boolean z) {
        this.append = z;
    }

    public void setOnerror(OnError onError) {
        this.onError = onError.getValue();
    }

    public void setKeepformat(boolean z) {
        this.keepformat = z;
    }

    public void setEscapeProcessing(boolean z) {
        this.escapeProcessing = z;
    }

    public void setRawBlobs(boolean z) {
        this.rawBlobs = z;
    }

    public void setStrictDelimiterMatching(boolean z) {
        this.strictDelimiterMatching = z;
    }

    public void setShowWarnings(boolean z) {
        this.showWarnings = z;
    }

    public void setTreatWarningsAsErrors(boolean z) {
        this.treatWarningsAsErrors = z;
    }

    public void setCsvColumnSeparator(String str) {
        this.csvColumnSep = str;
    }

    public void setCsvQuoteCharacter(String str) {
        if (str == null || str.length() <= 1) {
            this.csvQuoteChar = str;
            return;
        }
        throw new BuildException("The quote character must be a single character.");
    }

    public void setErrorProperty(String str) {
        this.errorProperty = str;
    }

    public void setWarningProperty(String str) {
        this.warningProperty = str;
    }

    public void setRowCountProperty(String str) {
        this.rowCountProperty = str;
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        Connection connection;
        Appendable appendable;
        Vector vector = (Vector) this.transactions.clone();
        String str = this.sqlCommand;
        this.sqlCommand = str.trim();
        try {
            if (this.srcFile == null && this.sqlCommand.length() == 0 && this.resources == null && this.transactions.size() == 0) {
                throw new BuildException("Source file or resource collection, transactions or sql statement must be set!", getLocation());
            }
            if (this.srcFile != null && !this.srcFile.isFile()) {
                throw new BuildException("Source file " + this.srcFile + " is not a file!", getLocation());
            }
            if (this.resources != null) {
                Iterator<Resource> it = this.resources.iterator();
                while (it.hasNext()) {
                    createTransaction().setSrcResource(it.next());
                }
            }
            Transaction createTransaction = createTransaction();
            createTransaction.setSrc(this.srcFile);
            createTransaction.addText(this.sqlCommand);
            if (getConnection() != null) {
                try {
                    PrintStream wrapSystemOut = KeepAliveOutputStream.wrapSystemOut();
                    try {
                        if (this.output != null) {
                            log("Opening PrintStream to output Resource " + this.output, 3);
                            OutputStream outputStream = null;
                            FileProvider fileProvider = (FileProvider) this.output.mo14823as(FileProvider.class);
                            if (fileProvider != null) {
                                outputStream = new FileOutputStream(fileProvider.getFile(), this.append);
                            } else {
                                if (this.append && (appendable = (Appendable) this.output.mo14823as(Appendable.class)) != null) {
                                    outputStream = appendable.getAppendOutputStream();
                                }
                                if (outputStream == null) {
                                    outputStream = this.output.getOutputStream();
                                    if (this.append) {
                                        log("Ignoring append=true for non-appendable resource " + this.output, 1);
                                    }
                                }
                            }
                            if (this.outputEncoding != null) {
                                wrapSystemOut = new PrintStream(new BufferedOutputStream(outputStream), false, this.outputEncoding);
                            } else {
                                wrapSystemOut = new PrintStream(new BufferedOutputStream(outputStream));
                            }
                        }
                        Enumeration elements = this.transactions.elements();
                        while (elements.hasMoreElements()) {
                            ((Transaction) elements.nextElement()).runTransaction(wrapSystemOut);
                            if (!isAutocommit()) {
                                log("Committing transaction", 3);
                                getConnection().commit();
                            }
                        }
                        try {
                            if (getStatement() != null) {
                                getStatement().close();
                            }
                        } catch (SQLException unused) {
                        }
                    } finally {
                        FileUtils.close(wrapSystemOut);
                    }
                } catch (IOException e) {
                    closeQuietly();
                    setErrorProperty();
                    if (!this.onError.equals("abort")) {
                        try {
                            if (getStatement() != null) {
                                getStatement().close();
                            }
                        } catch (SQLException unused2) {
                        }
                        if (getConnection() != null) {
                            connection = getConnection();
                        }
                    } else {
                        throw new BuildException(e, getLocation());
                    }
                } catch (SQLException e2) {
                    closeQuietly();
                    setErrorProperty();
                    if (!this.onError.equals("abort")) {
                        try {
                            if (getStatement() != null) {
                                getStatement().close();
                            }
                        } catch (SQLException unused3) {
                        }
                        if (getConnection() != null) {
                            connection = getConnection();
                        }
                    } else {
                        throw new BuildException(e2, getLocation());
                    }
                }
                if (getConnection() != null) {
                    connection = getConnection();
                    connection.close();
                }
                log(this.goodSql + " of " + this.totalSql + " SQL statements executed successfully");
            }
        } finally {
            this.transactions = vector;
            this.sqlCommand = str;
        }
    }

    protected void runStatements(Reader reader, PrintStream printStream) throws SQLException, IOException {
        StringBuffer stringBuffer = new StringBuffer();
        BufferedReader bufferedReader = new BufferedReader(reader);
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine == null) {
                break;
            }
            if (!this.keepformat) {
                readLine = readLine.trim();
            }
            if (this.expandProperties) {
                readLine = getProject().replaceProperties(readLine);
            }
            if (!this.keepformat) {
                if (!readLine.startsWith("//") && !readLine.startsWith("--")) {
                    StringTokenizer stringTokenizer = new StringTokenizer(readLine);
                    if (stringTokenizer.hasMoreTokens() && "REM".equalsIgnoreCase(stringTokenizer.nextToken())) {
                    }
                }
            }
            stringBuffer.append(this.keepformat ? "\n" : ExpandableTextView.f6958c);
            stringBuffer.append(readLine);
            if (!this.keepformat && readLine.indexOf("--") >= 0) {
                stringBuffer.append("\n");
            }
            int lastDelimiterPosition = lastDelimiterPosition(stringBuffer, readLine);
            if (lastDelimiterPosition > -1) {
                execSQL(stringBuffer.substring(0, lastDelimiterPosition), printStream);
                stringBuffer.replace(0, stringBuffer.length(), "");
            }
        }
        if (stringBuffer.length() > 0) {
            execSQL(stringBuffer.toString(), printStream);
        }
    }

    protected void execSQL(String str, PrintStream printStream) throws SQLException {
        Throwable th;
        SQLException e;
        ResultSet resultSet;
        if (!"".equals(str.trim())) {
            ResultSet resultSet2 = null;
            try {
                try {
                    try {
                        this.totalSql++;
                        log("SQL: " + str, 3);
                        boolean execute = getStatement().execute(str);
                        int updateCount = getStatement().getUpdateCount();
                        resultSet2 = null;
                        int i = 0;
                        while (true) {
                            if (updateCount != -1) {
                                i += updateCount;
                            }
                            if (execute) {
                                try {
                                    resultSet = getStatement().getResultSet();
                                } catch (SQLException e2) {
                                    e = e2;
                                } catch (Throwable th2) {
                                    th = th2;
                                }
                                try {
                                    printWarnings(resultSet.getWarnings(), false);
                                    resultSet.clearWarnings();
                                    if (this.print) {
                                        printResults(resultSet, printStream);
                                    }
                                    resultSet2 = resultSet;
                                } catch (SQLException e3) {
                                    e = e3;
                                    resultSet2 = resultSet;
                                    log("Failed to execute: " + str, 0);
                                    setErrorProperty();
                                    if (!this.onError.equals("abort")) {
                                        log(e.toString(), 0);
                                    }
                                    if (!this.onError.equals("continue")) {
                                        throw e;
                                    } else if (resultSet2 != null) {
                                        resultSet2.close();
                                    } else {
                                        return;
                                    }
                                } catch (Throwable th3) {
                                    th = th3;
                                    resultSet2 = resultSet;
                                    if (resultSet2 != null) {
                                        try {
                                            resultSet2.close();
                                        } catch (SQLException unused) {
                                        }
                                    }
                                    throw th;
                                }
                            }
                            execute = getStatement().getMoreResults();
                            updateCount = getStatement().getUpdateCount();
                            if (!execute && updateCount == -1) {
                                break;
                            }
                        }
                        printWarnings(getStatement().getWarnings(), false);
                        getStatement().clearWarnings();
                        log(i + " rows affected", 3);
                        if (i != -1) {
                            setRowCountProperty(i);
                        }
                        if (this.print && this.showtrailers) {
                            printStream.println(i + " rows affected");
                        }
                        printWarnings(getConnection().getWarnings(), true);
                        getConnection().clearWarnings();
                        this.goodSql++;
                    } catch (Throwable th4) {
                        th = th4;
                        resultSet2 = null;
                    }
                } catch (SQLException e4) {
                    e = e4;
                }
                if (resultSet2 != null) {
                    resultSet2.close();
                }
            } catch (SQLException unused2) {
            }
        }
    }

    @Deprecated
    protected void printResults(PrintStream printStream) throws SQLException {
        ResultSet resultSet = getStatement().getResultSet();
        try {
            printResults(resultSet, printStream);
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
        }
    }

    protected void printResults(ResultSet resultSet, PrintStream printStream) throws SQLException {
        if (resultSet != null) {
            log("Processing new result set.", 3);
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            if (columnCount > 0) {
                if (this.showheaders) {
                    printStream.print(metaData.getColumnName(1));
                    for (int i = 2; i <= columnCount; i++) {
                        printStream.print(this.csvColumnSep);
                        printStream.print(maybeQuote(metaData.getColumnName(i)));
                    }
                    printStream.println();
                }
                while (resultSet.next()) {
                    printValue(resultSet, 1, printStream);
                    for (int i2 = 2; i2 <= columnCount; i2++) {
                        printStream.print(this.csvColumnSep);
                        printValue(resultSet, i2, printStream);
                    }
                    printStream.println();
                    printWarnings(resultSet.getWarnings(), false);
                }
            }
        }
        printStream.println();
    }

    private void printValue(ResultSet resultSet, int i, PrintStream printStream) throws SQLException {
        if (!this.rawBlobs || resultSet.getMetaData().getColumnType(i) != 2004) {
            printStream.print(maybeQuote(resultSet.getString(i)));
        } else if (resultSet.getBlob(i) != null) {
            new StreamPumper(resultSet.getBlob(i).getBinaryStream(), printStream).run();
        }
    }

    private String maybeQuote(String str) {
        if (this.csvQuoteChar == null || str == null || (str.indexOf(this.csvColumnSep) == -1 && str.indexOf(this.csvQuoteChar) == -1)) {
            return str;
        }
        StringBuffer stringBuffer = new StringBuffer(this.csvQuoteChar);
        int length = str.length();
        char charAt = this.csvQuoteChar.charAt(0);
        for (int i = 0; i < length; i++) {
            char charAt2 = str.charAt(i);
            if (charAt2 == charAt) {
                stringBuffer.append(charAt);
            }
            stringBuffer.append(charAt2);
        }
        stringBuffer.append(this.csvQuoteChar);
        return stringBuffer.toString();
    }

    private void closeQuietly() {
        if (!isAutocommit() && getConnection() != null && this.onError.equals("abort")) {
            try {
                getConnection().rollback();
            } catch (SQLException unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.tools.ant.taskdefs.JDBCTask
    public Connection getConnection() {
        if (this.conn == null) {
            this.conn = super.getConnection();
            if (!isValidRdbms(this.conn)) {
                this.conn = null;
            }
        }
        return this.conn;
    }

    protected Statement getStatement() throws SQLException {
        if (this.statement == null) {
            this.statement = getConnection().createStatement();
            this.statement.setEscapeProcessing(this.escapeProcessing);
        }
        return this.statement;
    }

    /* loaded from: classes2.dex */
    public static class OnError extends EnumeratedAttribute {
        @Override // org.apache.tools.ant.types.EnumeratedAttribute
        public String[] getValues() {
            return new String[]{"continue", "stop", "abort"};
        }
    }

    /* loaded from: classes2.dex */
    public class Transaction {
        private Resource tSrcResource = null;
        private String tSqlCommand = "";

        public Transaction() {
        }

        public void setSrc(File file) {
            if (file != null) {
                setSrcResource(new FileResource(file));
            }
        }

        public void setSrcResource(Resource resource) {
            if (this.tSrcResource == null) {
                this.tSrcResource = resource;
                return;
            }
            throw new BuildException("only one resource per transaction");
        }

        public void addText(String str) {
            if (str != null) {
                this.tSqlCommand += str;
            }
        }

        public void addConfigured(ResourceCollection resourceCollection) {
            if (resourceCollection.size() == 1) {
                setSrcResource(resourceCollection.iterator().next());
                return;
            }
            throw new BuildException("only single argument resource collections are supported.");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void runTransaction(PrintStream printStream) throws IOException, SQLException {
            Throwable th;
            InputStream inputStream;
            if (this.tSqlCommand.length() != 0) {
                SQLExec.this.log("Executing commands", 2);
                SQLExec.this.runStatements(new StringReader(this.tSqlCommand), printStream);
            }
            if (this.tSrcResource != null) {
                SQLExec.this.log("Executing resource: " + this.tSrcResource.toString(), 2);
                InputStreamReader inputStreamReader = null;
                try {
                    inputStream = this.tSrcResource.getInputStream();
                    try {
                        inputStreamReader = SQLExec.this.encoding == null ? new InputStreamReader(inputStream) : new InputStreamReader(inputStream, SQLExec.this.encoding);
                        SQLExec.this.runStatements(inputStreamReader, printStream);
                        FileUtils.close(inputStream);
                        FileUtils.close(inputStreamReader);
                    } catch (Throwable th2) {
                        th = th2;
                        FileUtils.close(inputStream);
                        FileUtils.close(inputStreamReader);
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    inputStream = null;
                }
            }
        }
    }

    public int lastDelimiterPosition(StringBuffer stringBuffer, String str) {
        if (!this.strictDelimiterMatching) {
            String lowerCase = this.delimiter.trim().toLowerCase(Locale.ENGLISH);
            if (this.delimiterType.equals(DelimiterType.NORMAL)) {
                int length = this.delimiter.length() - 1;
                int length2 = stringBuffer.length() - 1;
                while (length2 >= 0 && Character.isWhitespace(stringBuffer.charAt(length2))) {
                    length2--;
                }
                if (length2 < length) {
                    return -1;
                }
                while (length >= 0) {
                    if (stringBuffer.substring(length2, length2 + 1).toLowerCase(Locale.ENGLISH).charAt(0) != lowerCase.charAt(length)) {
                        return -1;
                    }
                    length2--;
                    length--;
                }
                return length2 + 1;
            } else if (str.trim().toLowerCase(Locale.ENGLISH).equals(lowerCase)) {
                return stringBuffer.length() - str.length();
            } else {
                return -1;
            }
        } else if ((!this.delimiterType.equals(DelimiterType.NORMAL) || !StringUtils.endsWith(stringBuffer, this.delimiter)) && (!this.delimiterType.equals(DelimiterType.ROW) || !str.equals(this.delimiter))) {
            return -1;
        } else {
            return stringBuffer.length() - this.delimiter.length();
        }
    }

    private void printWarnings(SQLWarning sQLWarning, boolean z) throws SQLException {
        if (this.showWarnings || z) {
            for (SQLWarning sQLWarning2 = sQLWarning; sQLWarning2 != null; sQLWarning2 = sQLWarning2.getNextWarning()) {
                log(sQLWarning2 + " sql warning", this.showWarnings ? 1 : 3);
            }
        }
        if (sQLWarning != null) {
            setWarningProperty();
        }
        if (this.treatWarningsAsErrors && sQLWarning != null) {
            throw sQLWarning;
        }
    }

    protected final void setErrorProperty() {
        setProperty(this.errorProperty, "true");
    }

    protected final void setWarningProperty() {
        setProperty(this.warningProperty, "true");
    }

    protected final void setRowCountProperty(int i) {
        setProperty(this.rowCountProperty, Integer.toString(i));
    }

    private void setProperty(String str, String str2) {
        if (str != null) {
            getProject().setNewProperty(str, str2);
        }
    }
}
