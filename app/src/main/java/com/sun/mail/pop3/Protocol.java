package com.sun.mail.pop3;

import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import com.sun.mail.util.LineInputStream;
import com.sun.mail.util.SocketFetcher;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import java.util.StringTokenizer;

/* loaded from: classes2.dex */
class Protocol {
    private static final String CRLF = "\r\n";
    private static final int POP3_PORT = 110;
    private static char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private String apopChallenge;
    private boolean debug;
    private DataInputStream input;
    private PrintStream out;
    private PrintWriter output;
    private Socket socket;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Protocol(String str, int i, boolean z, PrintStream printStream, Properties properties, String str2, boolean z2) throws IOException {
        boolean z3 = false;
        this.debug = false;
        this.apopChallenge = null;
        this.debug = z;
        this.out = printStream;
        String property = properties.getProperty(String.valueOf(str2) + ".apop.enable");
        if (property != null && property.equalsIgnoreCase("true")) {
            z3 = true;
        }
        i = i == -1 ? 110 : i;
        if (z) {
            try {
                printStream.println("DEBUG POP3: connecting to host \"" + str + "\", port " + i + ", isSSL " + z2);
            } catch (IOException e) {
                try {
                    this.socket.close();
                } catch (Throwable unused) {
                }
                throw e;
            }
        }
        this.socket = SocketFetcher.getSocket(str, i, properties, str2, z2);
        this.input = new DataInputStream(new BufferedInputStream(this.socket.getInputStream()));
        this.output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream(), "iso-8859-1")));
        Response simpleCommand = simpleCommand(null);
        if (!simpleCommand.f12662ok) {
            try {
                this.socket.close();
            } catch (Throwable unused2) {
            }
            throw new IOException("Connect failed");
        } else if (z3) {
            int indexOf = simpleCommand.data.indexOf(60);
            int indexOf2 = simpleCommand.data.indexOf(62, indexOf);
            if (!(indexOf == -1 || indexOf2 == -1)) {
                this.apopChallenge = simpleCommand.data.substring(indexOf, indexOf2 + 1);
            }
            if (z) {
                printStream.println("DEBUG POP3: APOP challenge: " + this.apopChallenge);
            }
        }
    }

    protected void finalize() throws Throwable {
        super.finalize();
        if (this.socket != null) {
            quit();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized String login(String str, String str2) throws IOException {
        Response response;
        String digest = this.apopChallenge != null ? getDigest(str2) : null;
        if (this.apopChallenge == null || digest == null) {
            Response simpleCommand = simpleCommand("USER " + str);
            if (simpleCommand.f12662ok) {
                response = simpleCommand("PASS " + str2);
            } else if (simpleCommand.data == null) {
                return "USER command failed";
            } else {
                return simpleCommand.data;
            }
        } else {
            response = simpleCommand("APOP " + str + ExpandableTextView.f6958c + digest);
        }
        if (response.f12662ok) {
            return null;
        }
        if (response.data == null) {
            return "login failed";
        }
        return response.data;
    }

    private String getDigest(String str) {
        try {
            return toHex(MessageDigest.getInstance("MD5").digest((String.valueOf(this.apopChallenge) + str).getBytes("iso-8859-1")));
        } catch (UnsupportedEncodingException unused) {
            return null;
        } catch (NoSuchAlgorithmException unused2) {
            return null;
        }
    }

    private static String toHex(byte[] bArr) {
        char[] cArr = new char[bArr.length * 2];
        int i = 0;
        for (byte b : bArr) {
            int i2 = b & 255;
            int i3 = i + 1;
            char[] cArr2 = digits;
            cArr[i] = cArr2[i2 >> 4];
            i = i3 + 1;
            cArr[i3] = cArr2[i2 & 15];
        }
        return new String(cArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized boolean quit() throws IOException {
        boolean z;
        z = simpleCommand("QUIT").f12662ok;
        this.socket.close();
        this.socket = null;
        this.input = null;
        this.output = null;
        return z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized Status stat() throws IOException {
        Status status;
        Response simpleCommand = simpleCommand("STAT");
        status = new Status();
        if (simpleCommand.f12662ok && simpleCommand.data != null) {
            try {
                StringTokenizer stringTokenizer = new StringTokenizer(simpleCommand.data);
                status.total = Integer.parseInt(stringTokenizer.nextToken());
                status.size = Integer.parseInt(stringTokenizer.nextToken());
            } catch (Exception unused) {
            }
        }
        return status;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized int list(int i) throws IOException {
        int i2;
        Response simpleCommand = simpleCommand("LIST " + i);
        i2 = -1;
        if (simpleCommand.f12662ok && simpleCommand.data != null) {
            try {
                StringTokenizer stringTokenizer = new StringTokenizer(simpleCommand.data);
                stringTokenizer.nextToken();
                i2 = Integer.parseInt(stringTokenizer.nextToken());
            } catch (Exception unused) {
            }
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized InputStream list() throws IOException {
        return multilineCommand("LIST", 128).bytes;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized InputStream retr(int i, int i2) throws IOException {
        return multilineCommand("RETR " + i, i2).bytes;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized InputStream top(int i, int i2) throws IOException {
        return multilineCommand("TOP " + i + ExpandableTextView.f6958c + i2, 0).bytes;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized boolean dele(int i) throws IOException {
        return simpleCommand("DELE " + i).f12662ok;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized String uidl(int i) throws IOException {
        Response simpleCommand = simpleCommand("UIDL " + i);
        if (!simpleCommand.f12662ok) {
            return null;
        }
        int indexOf = simpleCommand.data.indexOf(32);
        if (indexOf <= 0) {
            return null;
        }
        return simpleCommand.data.substring(indexOf + 1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized boolean uidl(String[] strArr) throws IOException {
        int parseInt;
        Response multilineCommand = multilineCommand("UIDL", strArr.length * 15);
        if (!multilineCommand.f12662ok) {
            return false;
        }
        LineInputStream lineInputStream = new LineInputStream(multilineCommand.bytes);
        while (true) {
            String readLine = lineInputStream.readLine();
            if (readLine == null) {
                return true;
            }
            int indexOf = readLine.indexOf(32);
            if (indexOf > 0 && indexOf < readLine.length() && (parseInt = Integer.parseInt(readLine.substring(0, indexOf))) > 0 && parseInt <= strArr.length) {
                strArr[parseInt - 1] = readLine.substring(indexOf + 1);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized boolean noop() throws IOException {
        return simpleCommand("NOOP").f12662ok;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized boolean rset() throws IOException {
        return simpleCommand("RSET").f12662ok;
    }

    private Response simpleCommand(String str) throws IOException {
        if (this.socket != null) {
            if (str != null) {
                if (this.debug) {
                    PrintStream printStream = this.out;
                    printStream.println("C: " + str);
                }
                this.output.print(String.valueOf(str) + "\r\n");
                this.output.flush();
            }
            String readLine = this.input.readLine();
            if (readLine == null) {
                if (this.debug) {
                    this.out.println("S: EOF");
                }
                throw new EOFException("EOF on socket");
            }
            if (this.debug) {
                PrintStream printStream2 = this.out;
                printStream2.println("S: " + readLine);
            }
            Response response = new Response();
            if (readLine.startsWith("+OK")) {
                response.f12662ok = true;
            } else if (readLine.startsWith("-ERR")) {
                response.f12662ok = false;
            } else {
                throw new IOException("Unexpected response: " + readLine);
            }
            int indexOf = readLine.indexOf(32);
            if (indexOf >= 0) {
                response.data = readLine.substring(indexOf + 1);
            }
            return response;
        }
        throw new IOException("Folder is closed");
    }

    private Response multilineCommand(String str, int i) throws IOException {
        int read;
        Response simpleCommand = simpleCommand(str);
        if (!simpleCommand.f12662ok) {
            return simpleCommand;
        }
        SharedByteArrayOutputStream sharedByteArrayOutputStream = new SharedByteArrayOutputStream(i);
        int i2 = 10;
        while (true) {
            read = this.input.read();
            if (read < 0) {
                break;
            }
            if (i2 == 10 && read == 46) {
                if (this.debug) {
                    this.out.write(read);
                }
                i2 = this.input.read();
                if (i2 == 13) {
                    if (this.debug) {
                        this.out.write(i2);
                    }
                    read = this.input.read();
                    if (this.debug) {
                        this.out.write(read);
                    }
                }
            } else {
                i2 = read;
            }
            sharedByteArrayOutputStream.write(i2);
            if (this.debug) {
                this.out.write(i2);
            }
        }
        if (read >= 0) {
            simpleCommand.bytes = sharedByteArrayOutputStream.toStream();
            return simpleCommand;
        }
        throw new EOFException("EOF on socket");
    }
}
