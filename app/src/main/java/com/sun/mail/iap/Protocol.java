package com.sun.mail.iap;

import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import com.sun.mail.util.SocketFetcher;
import com.sun.mail.util.TraceInputStream;
import com.sun.mail.util.TraceOutputStream;
import com.tendcloud.tenddata.C2771ci;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Properties;
import java.util.Vector;
import org.apache.tools.mail.MailMessage;

/* loaded from: classes2.dex */
public class Protocol {
    private static final byte[] CRLF = {C2771ci.f13672f, 10};
    private boolean connected;
    protected boolean debug;
    private volatile Vector handlers;
    protected String host;
    private volatile ResponseInputStream input;
    protected PrintStream out;
    private volatile DataOutputStream output;
    protected String prefix;
    protected Properties props;
    protected boolean quote;
    private Socket socket;
    private int tagCounter;
    private volatile long timestamp;
    private TraceInputStream traceInput;
    private TraceOutputStream traceOutput;

    public ByteArray getResponseBuffer() {
        return null;
    }

    public Protocol(String str, int i, boolean z, PrintStream printStream, Properties properties, String str2, boolean z2) throws IOException, ProtocolException {
        boolean z3 = false;
        this.connected = false;
        this.tagCounter = 0;
        this.handlers = null;
        try {
            this.host = str;
            this.debug = z;
            this.out = printStream;
            this.props = properties;
            this.prefix = str2;
            this.socket = SocketFetcher.getSocket(str, i, properties, str2, z2);
            String property = properties.getProperty("mail.debug.quote");
            if (property != null && property.equalsIgnoreCase("true")) {
                z3 = true;
            }
            this.quote = z3;
            initStreams(printStream);
            processGreeting(readResponse());
            this.timestamp = System.currentTimeMillis();
            this.connected = true;
        } finally {
            if (!this.connected) {
                disconnect();
            }
        }
    }

    private void initStreams(PrintStream printStream) throws IOException {
        this.traceInput = new TraceInputStream(this.socket.getInputStream(), printStream);
        this.traceInput.setTrace(this.debug);
        this.traceInput.setQuote(this.quote);
        this.input = new ResponseInputStream(this.traceInput);
        this.traceOutput = new TraceOutputStream(this.socket.getOutputStream(), printStream);
        this.traceOutput.setTrace(this.debug);
        this.traceOutput.setQuote(this.quote);
        this.output = new DataOutputStream(new BufferedOutputStream(this.traceOutput));
    }

    public Protocol(InputStream inputStream, OutputStream outputStream, boolean z) throws IOException {
        this.connected = false;
        this.tagCounter = 0;
        this.handlers = null;
        this.host = MailMessage.DEFAULT_HOST;
        this.debug = z;
        this.quote = false;
        this.out = System.out;
        this.traceInput = new TraceInputStream(inputStream, System.out);
        this.traceInput.setTrace(z);
        this.traceInput.setQuote(this.quote);
        this.input = new ResponseInputStream(this.traceInput);
        this.traceOutput = new TraceOutputStream(outputStream, System.out);
        this.traceOutput.setTrace(z);
        this.traceOutput.setQuote(this.quote);
        this.output = new DataOutputStream(new BufferedOutputStream(this.traceOutput));
        this.timestamp = System.currentTimeMillis();
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public synchronized void addResponseHandler(ResponseHandler responseHandler) {
        if (this.handlers == null) {
            this.handlers = new Vector();
        }
        this.handlers.addElement(responseHandler);
    }

    public synchronized void removeResponseHandler(ResponseHandler responseHandler) {
        if (this.handlers != null) {
            this.handlers.removeElement(responseHandler);
        }
    }

    public void notifyResponseHandlers(Response[] responseArr) {
        if (this.handlers != null) {
            for (Response response : responseArr) {
                if (response != null) {
                    int size = this.handlers.size();
                    if (size != 0) {
                        Object[] objArr = new Object[size];
                        this.handlers.copyInto(objArr);
                        for (int i = 0; i < size; i++) {
                            ((ResponseHandler) objArr[i]).handleResponse(response);
                        }
                    } else {
                        return;
                    }
                }
            }
        }
    }

    public void processGreeting(Response response) throws ProtocolException {
        if (response.isBYE()) {
            throw new ConnectionException(this, response);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ResponseInputStream getInputStream() {
        return this.input;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public OutputStream getOutputStream() {
        return this.output;
    }

    public synchronized boolean supportsNonSyncLiterals() {
        return false;
    }

    public Response readResponse() throws IOException, ProtocolException {
        return new Response(this);
    }

    public String writeCommand(String str, Argument argument) throws IOException, ProtocolException {
        StringBuilder sb = new StringBuilder("A");
        int i = this.tagCounter;
        this.tagCounter = i + 1;
        sb.append(Integer.toString(i, 10));
        String sb2 = sb.toString();
        DataOutputStream dataOutputStream = this.output;
        dataOutputStream.writeBytes(String.valueOf(sb2) + ExpandableTextView.f6958c + str);
        if (argument != null) {
            this.output.write(32);
            argument.write(this);
        }
        this.output.write(CRLF);
        this.output.flush();
        return sb2;
    }

    public synchronized Response[] command(String str, Argument argument) {
        Response[] responseArr;
        Response response;
        Vector vector = new Vector();
        boolean z = false;
        String str2 = null;
        try {
            str2 = writeCommand(str, argument);
        } catch (LiteralException e) {
            vector.addElement(e.getResponse());
            z = true;
        } catch (Exception e2) {
            vector.addElement(Response.byeResponse(e2));
            z = true;
        }
        while (!z) {
            try {
                response = readResponse();
            } catch (ProtocolException unused) {
            } catch (IOException e3) {
                response = Response.byeResponse(e3);
            }
            vector.addElement(response);
            if (response.isBYE()) {
                z = true;
            }
            if (response.isTagged() && response.getTag().equals(str2)) {
                z = true;
            }
        }
        responseArr = new Response[vector.size()];
        vector.copyInto(responseArr);
        this.timestamp = System.currentTimeMillis();
        return responseArr;
    }

    public void handleResult(Response response) throws ProtocolException {
        if (!response.isOK()) {
            if (response.isNO()) {
                throw new CommandFailedException(response);
            } else if (response.isBAD()) {
                throw new BadCommandException(response);
            } else if (response.isBYE()) {
                disconnect();
                throw new ConnectionException(this, response);
            }
        }
    }

    public void simpleCommand(String str, Argument argument) throws ProtocolException {
        Response[] command = command(str, argument);
        notifyResponseHandlers(command);
        handleResult(command[command.length - 1]);
    }

    public synchronized void startTLS(String str) throws IOException, ProtocolException {
        simpleCommand(str, null);
        this.socket = SocketFetcher.startTLS(this.socket, this.props, this.prefix);
        initStreams(this.out);
    }

    public synchronized void disconnect() {
        if (this.socket != null) {
            try {
                this.socket.close();
            } catch (IOException unused) {
            }
            this.socket = null;
        }
    }

    protected void finalize() throws Throwable {
        super.finalize();
        disconnect();
    }
}
