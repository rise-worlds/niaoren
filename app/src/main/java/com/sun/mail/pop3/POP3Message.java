package com.sun.mail.pop3;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.FolderClosedException;
import javax.mail.IllegalWriteException;
import javax.mail.MessageRemovedException;
import javax.mail.MessagingException;
import javax.mail.internet.InternetHeaders;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.SharedInputStream;

/* loaded from: classes2.dex */
public class POP3Message extends MimeMessage {
    static final String UNKNOWN = "UNKNOWN";
    private POP3Folder folder;
    private int hdrSize = -1;
    private int msgSize = -1;
    String uid = UNKNOWN;

    public POP3Message(Folder folder, int i) throws MessagingException {
        super(folder, i);
        this.folder = (POP3Folder) folder;
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Message
    public void setFlags(Flags flags, boolean z) throws MessagingException {
        super.setFlags(flags, z);
        if (!this.flags.equals((Flags) this.flags.clone())) {
            this.folder.notifyMessageChangedListeners(1, this);
        }
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Part
    public int getSize() throws MessagingException {
        try {
            synchronized (this) {
                if (this.msgSize >= 0) {
                    return this.msgSize;
                }
                if (this.msgSize < 0) {
                    if (this.headers == null) {
                        loadHeaders();
                    }
                    if (this.contentStream != null) {
                        this.msgSize = this.contentStream.available();
                    } else {
                        this.msgSize = this.folder.getProtocol().list(this.msgnum) - this.hdrSize;
                    }
                }
                return this.msgSize;
            }
        } catch (EOFException e) {
            this.folder.close(false);
            throw new FolderClosedException(this.folder, e.toString());
        } catch (IOException e2) {
            throw new MessagingException("error getting size", e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // javax.mail.internet.MimeMessage
    public InputStream getContentStream() throws MessagingException {
        int i;
        try {
            synchronized (this) {
                if (this.contentStream == null) {
                    InputStream retr = this.folder.getProtocol().retr(this.msgnum, this.msgSize > 0 ? this.msgSize + this.hdrSize : 0);
                    if (retr != null) {
                        if (this.headers != null && !((POP3Store) this.folder.getStore()).forgetTopHeaders) {
                            do {
                                i = 0;
                                while (true) {
                                    int read = retr.read();
                                    if (read < 0 || read == 10) {
                                        break;
                                    } else if (read != 13) {
                                        i++;
                                    } else if (retr.available() > 0) {
                                        retr.mark(1);
                                        if (retr.read() != 10) {
                                            retr.reset();
                                        }
                                    }
                                }
                                if (retr.available() == 0) {
                                    break;
                                }
                            } while (i != 0);
                            this.hdrSize = (int) ((SharedInputStream) retr).getPosition();
                            this.contentStream = ((SharedInputStream) retr).newStream(this.hdrSize, -1L);
                        }
                        this.headers = new InternetHeaders(retr);
                        this.hdrSize = (int) ((SharedInputStream) retr).getPosition();
                        this.contentStream = ((SharedInputStream) retr).newStream(this.hdrSize, -1L);
                    } else {
                        this.expunged = true;
                        throw new MessageRemovedException();
                    }
                }
            }
            return super.getContentStream();
        } catch (EOFException e) {
            this.folder.close(false);
            throw new FolderClosedException(this.folder, e.toString());
        } catch (IOException e2) {
            throw new MessagingException("error fetching POP3 content", e2);
        }
    }

    public synchronized void invalidate(boolean z) {
        this.content = null;
        this.contentStream = null;
        this.msgSize = -1;
        if (z) {
            this.headers = null;
            this.hdrSize = -1;
        }
    }

    public InputStream top(int i) throws MessagingException {
        InputStream pVar;
        try {
            synchronized (this) {
                pVar = this.folder.getProtocol().top(this.msgnum, i);
            }
            return pVar;
        } catch (EOFException e) {
            this.folder.close(false);
            throw new FolderClosedException(this.folder, e.toString());
        } catch (IOException e2) {
            throw new MessagingException("error getting size", e2);
        }
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Part
    public String[] getHeader(String str) throws MessagingException {
        if (this.headers == null) {
            loadHeaders();
        }
        return this.headers.getHeader(str);
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.internet.MimePart
    public String getHeader(String str, String str2) throws MessagingException {
        if (this.headers == null) {
            loadHeaders();
        }
        return this.headers.getHeader(str, str2);
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Part
    public void setHeader(String str, String str2) throws MessagingException {
        throw new IllegalWriteException("POP3 messages are read-only");
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Part
    public void addHeader(String str, String str2) throws MessagingException {
        throw new IllegalWriteException("POP3 messages are read-only");
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Part
    public void removeHeader(String str) throws MessagingException {
        throw new IllegalWriteException("POP3 messages are read-only");
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Part
    public Enumeration getAllHeaders() throws MessagingException {
        if (this.headers == null) {
            loadHeaders();
        }
        return this.headers.getAllHeaders();
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Part
    public Enumeration getMatchingHeaders(String[] strArr) throws MessagingException {
        if (this.headers == null) {
            loadHeaders();
        }
        return this.headers.getMatchingHeaders(strArr);
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Part
    public Enumeration getNonMatchingHeaders(String[] strArr) throws MessagingException {
        if (this.headers == null) {
            loadHeaders();
        }
        return this.headers.getNonMatchingHeaders(strArr);
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.internet.MimePart
    public void addHeaderLine(String str) throws MessagingException {
        throw new IllegalWriteException("POP3 messages are read-only");
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.internet.MimePart
    public Enumeration getAllHeaderLines() throws MessagingException {
        if (this.headers == null) {
            loadHeaders();
        }
        return this.headers.getAllHeaderLines();
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.internet.MimePart
    public Enumeration getMatchingHeaderLines(String[] strArr) throws MessagingException {
        if (this.headers == null) {
            loadHeaders();
        }
        return this.headers.getMatchingHeaderLines(strArr);
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.internet.MimePart
    public Enumeration getNonMatchingHeaderLines(String[] strArr) throws MessagingException {
        if (this.headers == null) {
            loadHeaders();
        }
        return this.headers.getNonMatchingHeaderLines(strArr);
    }

    @Override // javax.mail.internet.MimeMessage, javax.mail.Message
    public void saveChanges() throws MessagingException {
        throw new IllegalWriteException("POP3 messages are read-only");
    }

    private void loadHeaders() throws MessagingException {
        InputStream pVar;
        try {
            synchronized (this) {
                if (this.headers == null) {
                    if (!((POP3Store) this.folder.getStore()).disableTop && (pVar = this.folder.getProtocol().top(this.msgnum, 0)) != null) {
                        this.hdrSize = pVar.available();
                        this.headers = new InternetHeaders(pVar);
                    }
                    getContentStream().close();
                }
            }
        } catch (EOFException e) {
            this.folder.close(false);
            throw new FolderClosedException(this.folder, e.toString());
        } catch (IOException e2) {
            throw new MessagingException("error loading POP3 headers", e2);
        }
    }
}
