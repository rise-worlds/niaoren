package com.sun.mail.imap;

import com.sun.mail.iap.ByteArray;
import com.sun.mail.iap.ConnectionException;
import com.sun.mail.iap.ProtocolException;
import com.sun.mail.imap.protocol.BODY;
import com.sun.mail.imap.protocol.IMAPProtocol;
import com.sun.mail.util.FolderClosedIOException;
import com.sun.mail.util.MessageRemovedIOException;
import java.io.IOException;
import java.io.InputStream;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.FolderClosedException;
import javax.mail.MessagingException;

/* loaded from: classes2.dex */
public class IMAPInputStream extends InputStream {
    private static final int slop = 64;
    private int blksize;
    private byte[] buf;
    private int bufcount;
    private int bufpos;
    private int max;
    private IMAPMessage msg;
    private boolean peek;
    private int pos = 0;
    private ByteArray readbuf;
    private String section;

    public IMAPInputStream(IMAPMessage iMAPMessage, String str, int i, boolean z) {
        this.msg = iMAPMessage;
        this.section = str;
        this.max = i;
        this.peek = z;
        this.blksize = iMAPMessage.getFetchBlockSize();
    }

    private void forceCheckExpunged() throws MessageRemovedIOException, FolderClosedIOException {
        synchronized (this.msg.getMessageCacheLock()) {
            try {
                try {
                    this.msg.getProtocol().noop();
                } catch (ConnectionException e) {
                    throw new FolderClosedIOException(this.msg.getFolder(), e.getMessage());
                }
            } catch (ProtocolException unused) {
            } catch (FolderClosedException e2) {
                throw new FolderClosedIOException(e2.getFolder(), e2.getMessage());
            }
        }
        if (this.msg.isExpunged()) {
            throw new MessageRemovedIOException();
        }
    }

    private void fill() throws IOException {
        BODY body;
        ByteArray byteArray;
        int i;
        int i2 = this.max;
        if (i2 == -1 || (i = this.pos) < i2) {
            if (this.readbuf == null) {
                this.readbuf = new ByteArray(this.blksize + 64);
            }
            synchronized (this.msg.getMessageCacheLock()) {
                try {
                    IMAPProtocol protocol = this.msg.getProtocol();
                    if (!this.msg.isExpunged()) {
                        int sequenceNumber = this.msg.getSequenceNumber();
                        int i3 = (this.max == -1 || this.pos + this.blksize <= this.max) ? this.blksize : this.max - this.pos;
                        if (this.peek) {
                            body = protocol.peekBody(sequenceNumber, this.section, this.pos, i3, this.readbuf);
                        } else {
                            body = protocol.fetchBody(sequenceNumber, this.section, this.pos, i3, this.readbuf);
                        }
                        if (body == null || (byteArray = body.getByteArray()) == null) {
                            forceCheckExpunged();
                            throw new IOException("No content");
                        }
                    } else {
                        throw new MessageRemovedIOException("No content for expunged message");
                    }
                } catch (ProtocolException e) {
                    forceCheckExpunged();
                    throw new IOException(e.getMessage());
                } catch (FolderClosedException e2) {
                    throw new FolderClosedIOException(e2.getFolder(), e2.getMessage());
                }
            }
            if (this.pos == 0) {
                checkSeen();
            }
            this.buf = byteArray.getBytes();
            this.bufpos = byteArray.getStart();
            int count = byteArray.getCount();
            this.bufcount = this.bufpos + count;
            this.pos += count;
            return;
        }
        if (i == 0) {
            checkSeen();
        }
        this.readbuf = null;
    }

    @Override // java.io.InputStream
    public synchronized int read() throws IOException {
        if (this.bufpos >= this.bufcount) {
            fill();
            if (this.bufpos >= this.bufcount) {
                return -1;
            }
        }
        byte[] bArr = this.buf;
        int i = this.bufpos;
        this.bufpos = i + 1;
        return bArr[i] & 255;
    }

    @Override // java.io.InputStream
    public synchronized int read(byte[] bArr, int i, int i2) throws IOException {
        int i3 = this.bufcount - this.bufpos;
        if (i3 <= 0) {
            fill();
            i3 = this.bufcount - this.bufpos;
            if (i3 <= 0) {
                return -1;
            }
        }
        if (i3 < i2) {
            i2 = i3;
        }
        System.arraycopy(this.buf, this.bufpos, bArr, i, i2);
        this.bufpos += i2;
        return i2;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.InputStream
    public synchronized int available() throws IOException {
        return this.bufcount - this.bufpos;
    }

    private void checkSeen() {
        if (!this.peek) {
            try {
                Folder folder = this.msg.getFolder();
                if (folder != null && folder.getMode() != 1 && !this.msg.isSet(Flags.Flag.SEEN)) {
                    this.msg.setFlag(Flags.Flag.SEEN, true);
                }
            } catch (MessagingException unused) {
            }
        }
    }
}
