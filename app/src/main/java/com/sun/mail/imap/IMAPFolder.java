package com.sun.mail.imap;

import com.sun.mail.iap.BadCommandException;
import com.sun.mail.iap.CommandFailedException;
import com.sun.mail.iap.ConnectionException;
import com.sun.mail.iap.ProtocolException;
import com.sun.mail.iap.Response;
import com.sun.mail.iap.ResponseHandler;
import com.sun.mail.imap.protocol.FetchResponse;
import com.sun.mail.imap.protocol.IMAPProtocol;
import com.sun.mail.imap.protocol.IMAPResponse;
import com.sun.mail.imap.protocol.ListInfo;
import com.sun.mail.imap.protocol.MailboxInfo;
import com.sun.mail.imap.protocol.MessageSet;
import com.sun.mail.imap.protocol.Status;
import com.sun.mail.imap.protocol.UID;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Date;
import java.util.Hashtable;
import java.util.NoSuchElementException;
import java.util.Vector;
import javax.mail.FetchProfile;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.FolderClosedException;
import javax.mail.FolderNotFoundException;
import javax.mail.Message;
import javax.mail.MessageRemovedException;
import javax.mail.MessagingException;
import javax.mail.Quota;
import javax.mail.StoreClosedException;
import javax.mail.UIDFolder;
import javax.mail.internet.MimeMessage;
import javax.mail.search.FlagTerm;
import javax.mail.search.SearchException;
import javax.mail.search.SearchTerm;
import org.apache.commons.p105io.IOUtils;

/* loaded from: classes2.dex */
public class IMAPFolder extends Folder implements ResponseHandler, UIDFolder {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int ABORTING = 2;
    private static final int IDLE = 1;
    private static final int RUNNING = 0;
    protected static final char UNKNOWN_SEPARATOR = 65535;
    protected String[] attributes;
    protected Flags availableFlags;
    private Status cachedStatus;
    private long cachedStatusTime;
    private boolean connectionPoolDebug;
    private boolean debug;
    private boolean doExpungeNotification;
    protected boolean exists;
    protected String fullName;
    private int idleState;
    protected boolean isNamespace;
    protected Vector messageCache;
    protected Object messageCacheLock;
    protected String name;
    private boolean opened;
    private PrintStream out;
    protected Flags permanentFlags;
    protected IMAPProtocol protocol;
    private int realTotal;
    private boolean reallyClosed;
    private int recent;
    protected char separator;
    private int total;
    protected int type;
    protected Hashtable uidTable;
    private long uidnext;
    private long uidvalidity;

    /* loaded from: classes2.dex */
    public interface ProtocolCommand {
        Object doCommand(IMAPProtocol iMAPProtocol) throws ProtocolException;
    }

    /* loaded from: classes2.dex */
    public static class FetchProfileItem extends FetchProfile.Item {
        public static final FetchProfileItem HEADERS = new FetchProfileItem("HEADERS");
        public static final FetchProfileItem SIZE = new FetchProfileItem("SIZE");

        protected FetchProfileItem(String str) {
            super(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public IMAPFolder(String str, char c, IMAPStore iMAPStore) {
        super(iMAPStore);
        int indexOf;
        this.exists = false;
        this.isNamespace = false;
        this.opened = false;
        this.reallyClosed = true;
        this.idleState = 0;
        this.total = -1;
        this.recent = -1;
        this.realTotal = -1;
        this.uidvalidity = -1L;
        this.uidnext = -1L;
        this.doExpungeNotification = true;
        this.cachedStatus = null;
        this.cachedStatusTime = 0L;
        this.debug = false;
        if (str != null) {
            this.fullName = str;
            this.separator = c;
            this.messageCacheLock = new Object();
            this.debug = iMAPStore.getSession().getDebug();
            this.connectionPoolDebug = iMAPStore.getConnectionPoolDebug();
            this.out = iMAPStore.getSession().getDebugOut();
            if (this.out == null) {
                this.out = System.out;
            }
            this.isNamespace = false;
            if (c != 65535 && c != 0 && (indexOf = this.fullName.indexOf(c)) > 0 && indexOf == this.fullName.length() - 1) {
                this.fullName = this.fullName.substring(0, indexOf);
                this.isNamespace = true;
                return;
            }
            return;
        }
        throw new NullPointerException("Folder name is null");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public IMAPFolder(String str, char c, IMAPStore iMAPStore, boolean z) {
        this(str, c, iMAPStore);
        this.isNamespace = z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public IMAPFolder(ListInfo listInfo, IMAPStore iMAPStore) {
        this(listInfo.name, listInfo.separator, iMAPStore);
        if (listInfo.hasInferiors) {
            this.type |= 2;
        }
        if (listInfo.canOpen) {
            this.type |= 1;
        }
        this.exists = true;
        this.attributes = listInfo.attrs;
    }

    private void checkExists() throws MessagingException {
        if (!this.exists && !exists()) {
            throw new FolderNotFoundException(this, String.valueOf(this.fullName) + " not found");
        }
    }

    private void checkClosed() {
        if (this.opened) {
            throw new IllegalStateException("This operation is not allowed on an open folder");
        }
    }

    private void checkOpened() throws FolderClosedException {
        if (this.opened) {
            return;
        }
        if (this.reallyClosed) {
            throw new IllegalStateException("This operation is not allowed on a closed folder");
        }
        throw new FolderClosedException(this, "Lost folder connection to server");
    }

    private void checkRange(int i) throws MessagingException {
        if (i <= 0) {
            throw new IndexOutOfBoundsException();
        } else if (i > this.total) {
            synchronized (this.messageCacheLock) {
                try {
                    keepConnectionAlive(false);
                } catch (ConnectionException e) {
                    throw new FolderClosedException(this, e.getMessage());
                } catch (ProtocolException e2) {
                    throw new MessagingException(e2.getMessage(), e2);
                }
            }
            if (i > this.total) {
                throw new IndexOutOfBoundsException();
            }
        }
    }

    private void checkFlags(Flags flags) throws MessagingException {
        if (this.mode != 2) {
            throw new IllegalStateException("Cannot change flags on READ_ONLY folder: " + this.fullName);
        }
    }

    @Override // javax.mail.Folder
    public synchronized String getName() {
        if (this.name == null) {
            try {
                this.name = this.fullName.substring(this.fullName.lastIndexOf(getSeparator()) + 1);
            } catch (MessagingException unused) {
            }
        }
        return this.name;
    }

    @Override // javax.mail.Folder
    public synchronized String getFullName() {
        return this.fullName;
    }

    @Override // javax.mail.Folder
    public synchronized Folder getParent() throws MessagingException {
        char separator = getSeparator();
        int lastIndexOf = this.fullName.lastIndexOf(separator);
        if (lastIndexOf != -1) {
            return new IMAPFolder(this.fullName.substring(0, lastIndexOf), separator, (IMAPStore) this.store);
        }
        return new DefaultFolder((IMAPStore) this.store);
    }

    @Override // javax.mail.Folder
    public synchronized boolean exists() throws MessagingException {
        final String str;
        if (!this.isNamespace || this.separator == 0) {
            str = this.fullName;
        } else {
            str = String.valueOf(this.fullName) + this.separator;
        }
        ListInfo[] listInfoArr = (ListInfo[]) doCommand(new ProtocolCommand() { // from class: com.sun.mail.imap.IMAPFolder.1
            @Override // com.sun.mail.imap.IMAPFolder.ProtocolCommand
            public Object doCommand(IMAPProtocol iMAPProtocol) throws ProtocolException {
                return iMAPProtocol.list("", str);
            }
        });
        if (listInfoArr != null) {
            int findName = findName(listInfoArr, str);
            this.fullName = listInfoArr[findName].name;
            this.separator = listInfoArr[findName].separator;
            int length = this.fullName.length();
            if (this.separator != 0 && length > 0) {
                int i = length - 1;
                if (this.fullName.charAt(i) == this.separator) {
                    this.fullName = this.fullName.substring(0, i);
                }
            }
            this.type = 0;
            if (listInfoArr[findName].hasInferiors) {
                this.type |= 2;
            }
            if (listInfoArr[findName].canOpen) {
                this.type |= 1;
            }
            this.exists = true;
            this.attributes = listInfoArr[findName].attrs;
        } else {
            this.exists = this.opened;
            this.attributes = null;
        }
        return this.exists;
    }

    private int findName(ListInfo[] listInfoArr, String str) {
        int i = 0;
        while (i < listInfoArr.length && !listInfoArr[i].name.equals(str)) {
            i++;
        }
        if (i >= listInfoArr.length) {
            return 0;
        }
        return i;
    }

    @Override // javax.mail.Folder
    public Folder[] list(String str) throws MessagingException {
        return doList(str, false);
    }

    @Override // javax.mail.Folder
    public Folder[] listSubscribed(String str) throws MessagingException {
        return doList(str, true);
    }

    private synchronized Folder[] doList(final String str, final boolean z) throws MessagingException {
        checkExists();
        int i = 0;
        if (!isDirectory()) {
            return new Folder[0];
        }
        final char separator = getSeparator();
        ListInfo[] listInfoArr = (ListInfo[]) doCommandIgnoreFailure(new ProtocolCommand() { // from class: com.sun.mail.imap.IMAPFolder.2
            @Override // com.sun.mail.imap.IMAPFolder.ProtocolCommand
            public Object doCommand(IMAPProtocol iMAPProtocol) throws ProtocolException {
                if (z) {
                    return iMAPProtocol.lsub("", String.valueOf(IMAPFolder.this.fullName) + separator + str);
                }
                return iMAPProtocol.list("", String.valueOf(IMAPFolder.this.fullName) + separator + str);
            }
        });
        if (listInfoArr == null) {
            return new Folder[0];
        }
        if (listInfoArr.length > 0) {
            String str2 = listInfoArr[0].name;
            if (str2.equals(String.valueOf(this.fullName) + separator)) {
                i = 1;
            }
        }
        IMAPFolder[] iMAPFolderArr = new IMAPFolder[listInfoArr.length - i];
        for (int i2 = i; i2 < listInfoArr.length; i2++) {
            iMAPFolderArr[i2 - i] = new IMAPFolder(listInfoArr[i2], (IMAPStore) this.store);
        }
        return iMAPFolderArr;
    }

    @Override // javax.mail.Folder
    public synchronized char getSeparator() throws MessagingException {
        if (this.separator == 65535) {
            ListInfo[] listInfoArr = (ListInfo[]) doCommand(new ProtocolCommand() { // from class: com.sun.mail.imap.IMAPFolder.3
                @Override // com.sun.mail.imap.IMAPFolder.ProtocolCommand
                public Object doCommand(IMAPProtocol iMAPProtocol) throws ProtocolException {
                    if (iMAPProtocol.isREV1()) {
                        return iMAPProtocol.list(IMAPFolder.this.fullName, "");
                    }
                    return iMAPProtocol.list("", IMAPFolder.this.fullName);
                }
            });
            if (listInfoArr != null) {
                this.separator = listInfoArr[0].separator;
            } else {
                this.separator = IOUtils.DIR_SEPARATOR_UNIX;
            }
        }
        return this.separator;
    }

    @Override // javax.mail.Folder
    public synchronized int getType() throws MessagingException {
        if (!this.opened) {
            checkExists();
        } else if (this.attributes == null) {
            exists();
        }
        return this.type;
    }

    @Override // javax.mail.Folder
    public synchronized boolean isSubscribed() {
        final String str;
        ListInfo[] listInfoArr = null;
        if (!this.isNamespace || this.separator == 0) {
            str = this.fullName;
        } else {
            str = String.valueOf(this.fullName) + this.separator;
        }
        try {
            listInfoArr = (ListInfo[]) doProtocolCommand(new ProtocolCommand() { // from class: com.sun.mail.imap.IMAPFolder.4
                @Override // com.sun.mail.imap.IMAPFolder.ProtocolCommand
                public Object doCommand(IMAPProtocol iMAPProtocol) throws ProtocolException {
                    return iMAPProtocol.lsub("", str);
                }
            });
        } catch (ProtocolException unused) {
        }
        if (listInfoArr == null) {
            return false;
        }
        return listInfoArr[findName(listInfoArr, str)].canOpen;
    }

    @Override // javax.mail.Folder
    public synchronized void setSubscribed(final boolean z) throws MessagingException {
        doCommandIgnoreFailure(new ProtocolCommand() { // from class: com.sun.mail.imap.IMAPFolder.5
            @Override // com.sun.mail.imap.IMAPFolder.ProtocolCommand
            public Object doCommand(IMAPProtocol iMAPProtocol) throws ProtocolException {
                if (z) {
                    iMAPProtocol.subscribe(IMAPFolder.this.fullName);
                    return null;
                }
                iMAPProtocol.unsubscribe(IMAPFolder.this.fullName);
                return null;
            }
        });
    }

    @Override // javax.mail.Folder
    public synchronized boolean create(final int i) throws MessagingException {
        final char separator = (i & 1) == 0 ? getSeparator() : (char) 0;
        if (doCommandIgnoreFailure(new ProtocolCommand() { // from class: com.sun.mail.imap.IMAPFolder.6
            @Override // com.sun.mail.imap.IMAPFolder.ProtocolCommand
            public Object doCommand(IMAPProtocol iMAPProtocol) throws ProtocolException {
                ListInfo[] list;
                if ((i & 1) == 0) {
                    iMAPProtocol.create(String.valueOf(IMAPFolder.this.fullName) + separator);
                } else {
                    iMAPProtocol.create(IMAPFolder.this.fullName);
                    if (!((i & 2) == 0 || (list = iMAPProtocol.list("", IMAPFolder.this.fullName)) == null || list[0].hasInferiors)) {
                        iMAPProtocol.delete(IMAPFolder.this.fullName);
                        throw new ProtocolException("Unsupported type");
                    }
                }
                return Boolean.TRUE;
            }
        }) == null) {
            return false;
        }
        boolean exists = exists();
        if (exists) {
            notifyFolderListeners(1);
        }
        return exists;
    }

    @Override // javax.mail.Folder
    public synchronized boolean hasNewMessages() throws MessagingException {
        boolean z = false;
        if (this.opened) {
            synchronized (this.messageCacheLock) {
                try {
                    keepConnectionAlive(true);
                    if (this.recent > 0) {
                        z = true;
                    }
                } catch (ConnectionException e) {
                    throw new FolderClosedException(this, e.getMessage());
                } catch (ProtocolException e2) {
                    throw new MessagingException(e2.getMessage(), e2);
                }
            }
            return z;
        }
        checkExists();
        Boolean bool = (Boolean) doCommandIgnoreFailure(new ProtocolCommand() { // from class: com.sun.mail.imap.IMAPFolder.7
            @Override // com.sun.mail.imap.IMAPFolder.ProtocolCommand
            public Object doCommand(IMAPProtocol iMAPProtocol) throws ProtocolException {
                ListInfo[] list = iMAPProtocol.list("", IMAPFolder.this.fullName);
                if (list != null) {
                    if (list[0].changeState == 1) {
                        return Boolean.TRUE;
                    }
                    if (list[0].changeState == 2) {
                        return Boolean.FALSE;
                    }
                }
                if (IMAPFolder.this.getStatus().recent > 0) {
                    return Boolean.TRUE;
                }
                return Boolean.FALSE;
            }
        });
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }

    @Override // javax.mail.Folder
    public Folder getFolder(String str) throws MessagingException {
        if (this.attributes == null || isDirectory()) {
            char separator = getSeparator();
            return new IMAPFolder(String.valueOf(this.fullName) + separator + str, separator, (IMAPStore) this.store);
        }
        throw new MessagingException("Cannot contain subfolders");
    }

    @Override // javax.mail.Folder
    public synchronized boolean delete(boolean z) throws MessagingException {
        checkClosed();
        if (z) {
            for (Folder folder : list()) {
                folder.delete(z);
            }
        }
        if (doCommandIgnoreFailure(new ProtocolCommand() { // from class: com.sun.mail.imap.IMAPFolder.8
            @Override // com.sun.mail.imap.IMAPFolder.ProtocolCommand
            public Object doCommand(IMAPProtocol iMAPProtocol) throws ProtocolException {
                iMAPProtocol.delete(IMAPFolder.this.fullName);
                return Boolean.TRUE;
            }
        }) == null) {
            return false;
        }
        this.exists = false;
        this.attributes = null;
        notifyFolderListeners(2);
        return true;
    }

    @Override // javax.mail.Folder
    public synchronized boolean renameTo(final Folder folder) throws MessagingException {
        checkClosed();
        checkExists();
        if (folder.getStore() != this.store) {
            throw new MessagingException("Can't rename across Stores");
        } else if (doCommandIgnoreFailure(new ProtocolCommand() { // from class: com.sun.mail.imap.IMAPFolder.9
            @Override // com.sun.mail.imap.IMAPFolder.ProtocolCommand
            public Object doCommand(IMAPProtocol iMAPProtocol) throws ProtocolException {
                iMAPProtocol.rename(IMAPFolder.this.fullName, folder.getFullName());
                return Boolean.TRUE;
            }
        }) == null) {
            return false;
        } else {
            this.exists = false;
            this.attributes = null;
            notifyFolderRenamedListeners(folder);
            return true;
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: AttachTryCatchVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Null type added to not empty exception handler: ProtocolException -> 0x00a8
        	at jadx.core.dex.trycatch.ExceptionHandler.addCatchType(ExceptionHandler.java:54)
        	at jadx.core.dex.visitors.AttachTryCatchVisitor.createHandler(AttachTryCatchVisitor.java:136)
        	at jadx.core.dex.visitors.AttachTryCatchVisitor.convertToHandlers(AttachTryCatchVisitor.java:123)
        	at jadx.core.dex.visitors.AttachTryCatchVisitor.initTryCatches(AttachTryCatchVisitor.java:59)
        	at jadx.core.dex.visitors.AttachTryCatchVisitor.visit(AttachTryCatchVisitor.java:47)
        */
    @Override // javax.mail.Folder
    public synchronized void open(int r8) throws javax.mail.MessagingException {
        /*
            Method dump skipped, instructions count: 235
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.mail.imap.IMAPFolder.open(int):void");
    }

    @Override // javax.mail.Folder
    public synchronized void fetch(Message[] messageArr, FetchProfile fetchProfile) throws MessagingException {
        checkOpened();
        IMAPMessage.fetch(this, messageArr, fetchProfile);
    }

    @Override // javax.mail.Folder
    public synchronized void setFlags(Message[] messageArr, Flags flags, boolean z) throws MessagingException {
        checkOpened();
        checkFlags(flags);
        if (messageArr.length != 0) {
            synchronized (this.messageCacheLock) {
                try {
                    IMAPProtocol protocol = getProtocol();
                    MessageSet[] messageSet = Utility.toMessageSet(messageArr, null);
                    if (messageSet != null) {
                        protocol.storeFlags(messageSet, flags, z);
                    } else {
                        throw new MessageRemovedException("Messages have been removed");
                    }
                } catch (ConnectionException e) {
                    throw new FolderClosedException(this, e.getMessage());
                } catch (ProtocolException e2) {
                    throw new MessagingException(e2.getMessage(), e2);
                }
            }
        }
    }

    @Override // javax.mail.Folder
    public synchronized void close(boolean z) throws MessagingException {
        close(z, false);
    }

    public synchronized void forceClose() throws MessagingException {
        close(false, true);
    }

    private void close(boolean z, boolean z2) throws MessagingException {
        synchronized (this.messageCacheLock) {
            if (!this.opened && this.reallyClosed) {
                throw new IllegalStateException("This operation is not allowed on a closed folder");
            }
            this.reallyClosed = true;
            if (this.opened) {
                try {
                    waitIfIdle();
                    if (z2) {
                        if (this.debug) {
                            PrintStream printStream = this.out;
                            printStream.println("DEBUG: forcing folder " + this.fullName + " to close");
                        }
                        if (this.protocol != null) {
                            this.protocol.disconnect();
                        }
                    } else if (((IMAPStore) this.store).isConnectionPoolFull()) {
                        if (this.debug) {
                            this.out.println("DEBUG: pool is full, not adding an Authenticated connection");
                        }
                        if (z) {
                            this.protocol.close();
                        }
                        if (this.protocol != null) {
                            this.protocol.logout();
                        }
                    } else {
                        if (!z && this.mode == 2) {
                            try {
                                this.protocol.examine(this.fullName);
                            } catch (ProtocolException unused) {
                                if (this.protocol != null) {
                                    this.protocol.disconnect();
                                }
                            }
                        }
                        if (this.protocol != null) {
                            this.protocol.close();
                        }
                    }
                    if (this.opened) {
                        cleanup(true);
                    }
                } catch (ProtocolException e) {
                    throw new MessagingException(e.getMessage(), e);
                }
            }
        }
    }

    private void cleanup(boolean z) {
        releaseProtocol(z);
        this.protocol = null;
        this.messageCache = null;
        this.uidTable = null;
        this.exists = false;
        this.attributes = null;
        this.opened = false;
        this.idleState = 0;
        notifyConnectionListeners(3);
    }

    @Override // javax.mail.Folder
    public synchronized boolean isOpen() {
        synchronized (this.messageCacheLock) {
            if (this.opened) {
                try {
                    keepConnectionAlive(false);
                } catch (ProtocolException unused) {
                }
            }
        }
        return this.opened;
    }

    @Override // javax.mail.Folder
    public synchronized Flags getPermanentFlags() {
        return (Flags) this.permanentFlags.clone();
    }

    @Override // javax.mail.Folder
    public synchronized int getMessageCount() throws MessagingException {
        int i;
        if (!this.opened) {
            checkExists();
            try {
                try {
                    try {
                        return getStatus().total;
                    } catch (ProtocolException e) {
                        throw new MessagingException(e.getMessage(), e);
                    }
                } catch (BadCommandException unused) {
                    try {
                        IMAPProtocol storeProtocol = getStoreProtocol();
                        MailboxInfo examine = storeProtocol.examine(this.fullName);
                        storeProtocol.close();
                        int i2 = examine.total;
                        releaseStoreProtocol(storeProtocol);
                        return i2;
                    } catch (ProtocolException e2) {
                        throw new MessagingException(e2.getMessage(), e2);
                    }
                }
            } catch (ConnectionException e3) {
                throw new StoreClosedException(this.store, e3.getMessage());
            }
        } else {
            synchronized (this.messageCacheLock) {
                try {
                    keepConnectionAlive(true);
                    i = this.total;
                } catch (ConnectionException e4) {
                    throw new FolderClosedException(this, e4.getMessage());
                } catch (ProtocolException e5) {
                    throw new MessagingException(e5.getMessage(), e5);
                }
            }
            return i;
        }
    }

    @Override // javax.mail.Folder
    public synchronized int getNewMessageCount() throws MessagingException {
        int i;
        if (!this.opened) {
            checkExists();
            try {
                try {
                    try {
                        return getStatus().recent;
                    } catch (ProtocolException e) {
                        throw new MessagingException(e.getMessage(), e);
                    }
                } catch (BadCommandException unused) {
                    try {
                        IMAPProtocol storeProtocol = getStoreProtocol();
                        MailboxInfo examine = storeProtocol.examine(this.fullName);
                        storeProtocol.close();
                        int i2 = examine.recent;
                        releaseStoreProtocol(storeProtocol);
                        return i2;
                    } catch (ProtocolException e2) {
                        throw new MessagingException(e2.getMessage(), e2);
                    }
                }
            } catch (ConnectionException e3) {
                throw new StoreClosedException(this.store, e3.getMessage());
            }
        } else {
            synchronized (this.messageCacheLock) {
                try {
                    keepConnectionAlive(true);
                    i = this.recent;
                } catch (ConnectionException e4) {
                    throw new FolderClosedException(this, e4.getMessage());
                } catch (ProtocolException e5) {
                    throw new MessagingException(e5.getMessage(), e5);
                }
            }
            return i;
        }
    }

    @Override // javax.mail.Folder
    public synchronized int getUnreadMessageCount() throws MessagingException {
        int length;
        if (!this.opened) {
            checkExists();
            try {
                try {
                    return getStatus().unseen;
                } catch (ConnectionException e) {
                    throw new StoreClosedException(this.store, e.getMessage());
                }
            } catch (BadCommandException unused) {
                return -1;
            } catch (ProtocolException e2) {
                throw new MessagingException(e2.getMessage(), e2);
            }
        } else {
            Flags flags = new Flags();
            flags.add(Flags.Flag.SEEN);
            try {
                synchronized (this.messageCacheLock) {
                    length = getProtocol().search(new FlagTerm(flags, false)).length;
                }
                return length;
            } catch (ConnectionException e3) {
                throw new FolderClosedException(this, e3.getMessage());
            } catch (ProtocolException e4) {
                throw new MessagingException(e4.getMessage(), e4);
            }
        }
    }

    @Override // javax.mail.Folder
    public synchronized int getDeletedMessageCount() throws MessagingException {
        int length;
        if (!this.opened) {
            checkExists();
            return -1;
        }
        Flags flags = new Flags();
        flags.add(Flags.Flag.DELETED);
        try {
            synchronized (this.messageCacheLock) {
                length = getProtocol().search(new FlagTerm(flags, true)).length;
            }
            return length;
        } catch (ConnectionException e) {
            throw new FolderClosedException(this, e.getMessage());
        } catch (ProtocolException e2) {
            throw new MessagingException(e2.getMessage(), e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Status getStatus() throws ProtocolException {
        IMAPProtocol iMAPProtocol;
        Throwable th;
        int statusCacheTimeout = ((IMAPStore) this.store).getStatusCacheTimeout();
        if (statusCacheTimeout > 0 && this.cachedStatus != null && System.currentTimeMillis() - this.cachedStatusTime < statusCacheTimeout) {
            return this.cachedStatus;
        }
        try {
            iMAPProtocol = getStoreProtocol();
            try {
                Status status = iMAPProtocol.status(this.fullName, null);
                if (statusCacheTimeout > 0) {
                    this.cachedStatus = status;
                    this.cachedStatusTime = System.currentTimeMillis();
                }
                releaseStoreProtocol(iMAPProtocol);
                return status;
            } catch (Throwable th2) {
                th = th2;
                releaseStoreProtocol(iMAPProtocol);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            iMAPProtocol = null;
        }
    }

    @Override // javax.mail.Folder
    public synchronized Message getMessage(int i) throws MessagingException {
        checkOpened();
        checkRange(i);
        return (Message) this.messageCache.elementAt(i - 1);
    }

    @Override // javax.mail.Folder
    public synchronized void appendMessages(Message[] messageArr) throws MessagingException {
        checkExists();
        int appendBufferSize = ((IMAPStore) this.store).getAppendBufferSize();
        for (Message message : messageArr) {
            try {
                final MessageLiteral messageLiteral = new MessageLiteral(message, message.getSize() > appendBufferSize ? 0 : appendBufferSize);
                final Date receivedDate = message.getReceivedDate();
                if (receivedDate == null) {
                    receivedDate = message.getSentDate();
                }
                final Flags flags = message.getFlags();
                doCommand(new ProtocolCommand() { // from class: com.sun.mail.imap.IMAPFolder.10
                    @Override // com.sun.mail.imap.IMAPFolder.ProtocolCommand
                    public Object doCommand(IMAPProtocol iMAPProtocol) throws ProtocolException {
                        iMAPProtocol.append(IMAPFolder.this.fullName, flags, receivedDate, messageLiteral);
                        return null;
                    }
                });
            } catch (IOException e) {
                throw new MessagingException("IOException while appending messages", e);
            } catch (MessageRemovedException unused) {
            }
        }
    }

    public synchronized AppendUID[] appendUIDMessages(Message[] messageArr) throws MessagingException {
        AppendUID[] appendUIDArr;
        checkExists();
        int appendBufferSize = ((IMAPStore) this.store).getAppendBufferSize();
        appendUIDArr = new AppendUID[messageArr.length];
        for (int i = 0; i < messageArr.length; i++) {
            Message message = messageArr[i];
            try {
                final MessageLiteral messageLiteral = new MessageLiteral(message, message.getSize() > appendBufferSize ? 0 : appendBufferSize);
                final Date receivedDate = message.getReceivedDate();
                if (receivedDate == null) {
                    receivedDate = message.getSentDate();
                }
                final Flags flags = message.getFlags();
                appendUIDArr[i] = (AppendUID) doCommand(new ProtocolCommand() { // from class: com.sun.mail.imap.IMAPFolder.11
                    @Override // com.sun.mail.imap.IMAPFolder.ProtocolCommand
                    public Object doCommand(IMAPProtocol iMAPProtocol) throws ProtocolException {
                        return iMAPProtocol.appenduid(IMAPFolder.this.fullName, flags, receivedDate, messageLiteral);
                    }
                });
            } catch (IOException e) {
                throw new MessagingException("IOException while appending messages", e);
            } catch (MessageRemovedException unused) {
            }
        }
        return appendUIDArr;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public synchronized Message[] addMessages(Message[] messageArr) throws MessagingException {
        MimeMessage[] mimeMessageArr;
        checkOpened();
        mimeMessageArr = new MimeMessage[messageArr.length];
        AppendUID[] appendUIDMessages = appendUIDMessages(messageArr);
        for (int i = 0; i < appendUIDMessages.length; i++) {
            AppendUID appendUID = appendUIDMessages[i];
            if (appendUID != null && appendUID.uidvalidity == this.uidvalidity) {
                try {
                    mimeMessageArr[i] = getMessageByUID(appendUID.uid);
                } catch (MessagingException unused) {
                }
            }
        }
        return mimeMessageArr;
    }

    @Override // javax.mail.Folder
    public synchronized void copyMessages(Message[] messageArr, Folder folder) throws MessagingException {
        checkOpened();
        if (messageArr.length != 0) {
            if (folder.getStore() == this.store) {
                synchronized (this.messageCacheLock) {
                    try {
                        try {
                            try {
                                IMAPProtocol protocol = getProtocol();
                                MessageSet[] messageSet = Utility.toMessageSet(messageArr, null);
                                if (messageSet != null) {
                                    protocol.copy(messageSet, folder.getFullName());
                                } else {
                                    throw new MessageRemovedException("Messages have been removed");
                                }
                            } catch (CommandFailedException e) {
                                if (e.getMessage().indexOf("TRYCREATE") != -1) {
                                    throw new FolderNotFoundException(folder, String.valueOf(folder.getFullName()) + " does not exist");
                                }
                                throw new MessagingException(e.getMessage(), e);
                            }
                        } catch (ConnectionException e2) {
                            throw new FolderClosedException(this, e2.getMessage());
                        }
                    } catch (ProtocolException e3) {
                        throw new MessagingException(e3.getMessage(), e3);
                    }
                }
                return;
            }
            super.copyMessages(messageArr, folder);
        }
    }

    @Override // javax.mail.Folder
    public synchronized Message[] expunge() throws MessagingException {
        return expunge(null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0, types: [boolean] */
    /* JADX WARN: Type inference failed for: r2v7 */
    /* JADX WARN: Type inference failed for: r2v8, types: [int] */
    public synchronized Message[] expunge(Message[] messageArr) throws MessagingException {
        boolean z;
        Message[] messageArr2;
        checkOpened();
        Vector vector = new Vector();
        if (messageArr != null) {
            FetchProfile fetchProfile = new FetchProfile();
            fetchProfile.add(UIDFolder.FetchProfileItem.UID);
            fetch(messageArr, fetchProfile);
        }
        synchronized (this.messageCacheLock) {
            int i = 0;
            z = true;
            try {
                try {
                    try {
                        IMAPProtocol protocol = getProtocol();
                        if (messageArr != null) {
                            protocol.uidexpunge(Utility.toUIDSet(messageArr));
                        } else {
                            protocol.expunge();
                        }
                        while (i < this.messageCache.size()) {
                            IMAPMessage iMAPMessage = (IMAPMessage) this.messageCache.elementAt(i == true ? 1 : 0);
                            if (iMAPMessage.isExpunged()) {
                                vector.addElement(iMAPMessage);
                                this.messageCache.removeElementAt(i);
                                if (this.uidTable != null) {
                                    long uid = iMAPMessage.getUID();
                                    if (uid != -1) {
                                        this.uidTable.remove(new Long(uid));
                                    }
                                }
                            } else {
                                iMAPMessage.setMessageNumber(iMAPMessage.getSequenceNumber());
                                i++;
                            }
                        }
                    } catch (ConnectionException e) {
                        throw new FolderClosedException(this, e.getMessage());
                    }
                } catch (CommandFailedException e2) {
                    if (this.mode != 2) {
                        throw new IllegalStateException("Cannot expunge READ_ONLY folder: " + this.fullName);
                    }
                    throw new MessagingException(e2.getMessage(), e2);
                } catch (ProtocolException e3) {
                    throw new MessagingException(e3.getMessage(), e3);
                }
            } finally {
                this.doExpungeNotification = z;
            }
        }
        this.total = this.messageCache.size();
        messageArr2 = new Message[vector.size()];
        vector.copyInto(messageArr2);
        if (messageArr2.length > 0) {
            notifyMessageRemovedListeners(z, messageArr2);
        }
        return messageArr2;
    }

    @Override // javax.mail.Folder
    public synchronized Message[] search(SearchTerm searchTerm) throws MessagingException {
        IMAPMessage[] iMAPMessageArr;
        checkOpened();
        iMAPMessageArr = null;
        try {
            try {
                synchronized (this.messageCacheLock) {
                    int[] search = getProtocol().search(searchTerm);
                    if (search != null) {
                        iMAPMessageArr = new IMAPMessage[search.length];
                        for (int i = 0; i < search.length; i++) {
                            iMAPMessageArr[i] = getMessageBySeqNumber(search[i]);
                        }
                    }
                }
            } catch (CommandFailedException unused) {
                return super.search(searchTerm);
            } catch (ConnectionException e) {
                throw new FolderClosedException(this, e.getMessage());
            }
        } catch (ProtocolException e2) {
            throw new MessagingException(e2.getMessage(), e2);
        } catch (SearchException unused2) {
            return super.search(searchTerm);
        }
        return iMAPMessageArr;
    }

    @Override // javax.mail.Folder
    public synchronized Message[] search(SearchTerm searchTerm, Message[] messageArr) throws MessagingException {
        IMAPMessage[] iMAPMessageArr;
        checkOpened();
        if (messageArr.length == 0) {
            return messageArr;
        }
        try {
            try {
                try {
                    try {
                        synchronized (this.messageCacheLock) {
                            IMAPProtocol protocol = getProtocol();
                            iMAPMessageArr = null;
                            MessageSet[] messageSet = Utility.toMessageSet(messageArr, null);
                            if (messageSet != null) {
                                int[] search = protocol.search(messageSet, searchTerm);
                                if (search != null) {
                                    iMAPMessageArr = new IMAPMessage[search.length];
                                    for (int i = 0; i < search.length; i++) {
                                        iMAPMessageArr[i] = getMessageBySeqNumber(search[i]);
                                    }
                                }
                            } else {
                                throw new MessageRemovedException("Messages have been removed");
                            }
                        }
                        return iMAPMessageArr;
                    } catch (ProtocolException e) {
                        throw new MessagingException(e.getMessage(), e);
                    }
                } catch (CommandFailedException unused) {
                    return super.search(searchTerm, messageArr);
                }
            } catch (SearchException unused2) {
                return super.search(searchTerm, messageArr);
            }
        } catch (ConnectionException e2) {
            throw new FolderClosedException(this, e2.getMessage());
        }
    }

    @Override // javax.mail.UIDFolder
    public synchronized long getUIDValidity() throws MessagingException {
        Throwable th;
        BadCommandException e;
        ConnectionException e2;
        IMAPProtocol iMAPProtocol;
        ProtocolException e3;
        if (this.opened) {
            return this.uidvalidity;
        }
        Status status = null;
        try {
            try {
                iMAPProtocol = getStoreProtocol();
                try {
                    status = iMAPProtocol.status(this.fullName, new String[]{"UIDVALIDITY"});
                } catch (BadCommandException e4) {
                    e = e4;
                    throw new MessagingException("Cannot obtain UIDValidity", e);
                } catch (ConnectionException e5) {
                    e2 = e5;
                    throwClosedException(e2);
                    releaseStoreProtocol(iMAPProtocol);
                    return status.uidvalidity;
                } catch (ProtocolException e6) {
                    e3 = e6;
                    throw new MessagingException(e3.getMessage(), e3);
                }
            } catch (Throwable th2) {
                th = th2;
                releaseStoreProtocol(null);
                throw th;
            }
        } catch (BadCommandException e7) {
            e = e7;
        } catch (ConnectionException e8) {
            e2 = e8;
            iMAPProtocol = null;
        } catch (ProtocolException e9) {
            e3 = e9;
        } catch (Throwable th3) {
            th = th3;
            releaseStoreProtocol(null);
            throw th;
        }
        releaseStoreProtocol(iMAPProtocol);
        return status.uidvalidity;
    }

    public synchronized long getUIDNext() throws MessagingException {
        Throwable th;
        BadCommandException e;
        ConnectionException e2;
        IMAPProtocol iMAPProtocol;
        ProtocolException e3;
        if (this.opened) {
            return this.uidnext;
        }
        Status status = null;
        try {
            try {
                iMAPProtocol = getStoreProtocol();
                try {
                    status = iMAPProtocol.status(this.fullName, new String[]{"UIDNEXT"});
                } catch (BadCommandException e4) {
                    e = e4;
                    throw new MessagingException("Cannot obtain UIDNext", e);
                } catch (ConnectionException e5) {
                    e2 = e5;
                    throwClosedException(e2);
                    releaseStoreProtocol(iMAPProtocol);
                    return status.uidnext;
                } catch (ProtocolException e6) {
                    e3 = e6;
                    throw new MessagingException(e3.getMessage(), e3);
                }
            } catch (Throwable th2) {
                th = th2;
                releaseStoreProtocol(null);
                throw th;
            }
        } catch (BadCommandException e7) {
            e = e7;
        } catch (ConnectionException e8) {
            e2 = e8;
            iMAPProtocol = null;
        } catch (ProtocolException e9) {
            e3 = e9;
        } catch (Throwable th3) {
            th = th3;
            releaseStoreProtocol(null);
            throw th;
        }
        releaseStoreProtocol(iMAPProtocol);
        return status.uidnext;
    }

    @Override // javax.mail.UIDFolder
    public synchronized Message getMessageByUID(long j) throws MessagingException {
        checkOpened();
        IMAPMessage iMAPMessage = null;
        try {
            try {
                synchronized (this.messageCacheLock) {
                    Long l = new Long(j);
                    if (this.uidTable != null) {
                        iMAPMessage = (IMAPMessage) this.uidTable.get(l);
                        if (iMAPMessage != null) {
                            return iMAPMessage;
                        }
                    } else {
                        this.uidTable = new Hashtable();
                    }
                    UID fetchSequenceNumber = getProtocol().fetchSequenceNumber(j);
                    if (fetchSequenceNumber != null && fetchSequenceNumber.seqnum <= this.total) {
                        iMAPMessage = getMessageBySeqNumber(fetchSequenceNumber.seqnum);
                        iMAPMessage.setUID(fetchSequenceNumber.uid);
                        this.uidTable.put(l, iMAPMessage);
                    }
                    return iMAPMessage;
                }
            } catch (ConnectionException e) {
                throw new FolderClosedException(this, e.getMessage());
            }
        } catch (ProtocolException e2) {
            throw new MessagingException(e2.getMessage(), e2);
        }
    }

    @Override // javax.mail.UIDFolder
    public synchronized Message[] getMessagesByUID(long j, long j2) throws MessagingException {
        Message[] messageArr;
        checkOpened();
        try {
            synchronized (this.messageCacheLock) {
                if (this.uidTable == null) {
                    this.uidTable = new Hashtable();
                }
                UID[] fetchSequenceNumbers = getProtocol().fetchSequenceNumbers(j, j2);
                messageArr = new Message[fetchSequenceNumbers.length];
                for (int i = 0; i < fetchSequenceNumbers.length; i++) {
                    IMAPMessage messageBySeqNumber = getMessageBySeqNumber(fetchSequenceNumbers[i].seqnum);
                    messageBySeqNumber.setUID(fetchSequenceNumbers[i].uid);
                    messageArr[i] = messageBySeqNumber;
                    this.uidTable.put(new Long(fetchSequenceNumbers[i].uid), messageBySeqNumber);
                }
            }
        } catch (ConnectionException e) {
            throw new FolderClosedException(this, e.getMessage());
        } catch (ProtocolException e2) {
            throw new MessagingException(e2.getMessage(), e2);
        }
        return messageArr;
    }

    @Override // javax.mail.UIDFolder
    public synchronized Message[] getMessagesByUID(long[] jArr) throws MessagingException {
        long[] jArr2;
        Message[] messageArr;
        checkOpened();
        try {
            try {
                synchronized (this.messageCacheLock) {
                    if (this.uidTable != null) {
                        Vector vector = new Vector();
                        for (long j : jArr) {
                            Hashtable hashtable = this.uidTable;
                            Long l = new Long(j);
                            if (!hashtable.containsKey(l)) {
                                vector.addElement(l);
                            }
                        }
                        int size = vector.size();
                        jArr2 = new long[size];
                        for (int i = 0; i < size; i++) {
                            jArr2[i] = ((Long) vector.elementAt(i)).longValue();
                        }
                    } else {
                        this.uidTable = new Hashtable();
                        jArr2 = jArr;
                    }
                    if (jArr2.length > 0) {
                        UID[] fetchSequenceNumbers = getProtocol().fetchSequenceNumbers(jArr2);
                        for (int i2 = 0; i2 < fetchSequenceNumbers.length; i2++) {
                            IMAPMessage messageBySeqNumber = getMessageBySeqNumber(fetchSequenceNumbers[i2].seqnum);
                            messageBySeqNumber.setUID(fetchSequenceNumbers[i2].uid);
                            this.uidTable.put(new Long(fetchSequenceNumbers[i2].uid), messageBySeqNumber);
                        }
                    }
                    messageArr = new Message[jArr.length];
                    for (int i3 = 0; i3 < jArr.length; i3++) {
                        messageArr[i3] = (Message) this.uidTable.get(new Long(jArr[i3]));
                    }
                }
            } catch (ConnectionException e) {
                throw new FolderClosedException(this, e.getMessage());
            }
        } catch (ProtocolException e2) {
            throw new MessagingException(e2.getMessage(), e2);
        }
        return messageArr;
    }

    @Override // javax.mail.UIDFolder
    public synchronized long getUID(Message message) throws MessagingException {
        if (message.getFolder() == this) {
            checkOpened();
            IMAPMessage iMAPMessage = (IMAPMessage) message;
            long uid = iMAPMessage.getUID();
            if (uid != -1) {
                return uid;
            }
            synchronized (this.messageCacheLock) {
                try {
                    IMAPProtocol protocol = getProtocol();
                    iMAPMessage.checkExpunged();
                    UID fetchUID = protocol.fetchUID(iMAPMessage.getSequenceNumber());
                    if (fetchUID != null) {
                        uid = fetchUID.uid;
                        iMAPMessage.setUID(uid);
                        if (this.uidTable == null) {
                            this.uidTable = new Hashtable();
                        }
                        this.uidTable.put(new Long(uid), iMAPMessage);
                    }
                } catch (ConnectionException e) {
                    throw new FolderClosedException(this, e.getMessage());
                } catch (ProtocolException e2) {
                    throw new MessagingException(e2.getMessage(), e2);
                }
            }
            return uid;
        }
        throw new NoSuchElementException("Message does not belong to this folder");
    }

    public Quota[] getQuota() throws MessagingException {
        return (Quota[]) doOptionalCommand("QUOTA not supported", new ProtocolCommand() { // from class: com.sun.mail.imap.IMAPFolder.12
            @Override // com.sun.mail.imap.IMAPFolder.ProtocolCommand
            public Object doCommand(IMAPProtocol iMAPProtocol) throws ProtocolException {
                return iMAPProtocol.getQuotaRoot(IMAPFolder.this.fullName);
            }
        });
    }

    public void setQuota(final Quota quota) throws MessagingException {
        doOptionalCommand("QUOTA not supported", new ProtocolCommand() { // from class: com.sun.mail.imap.IMAPFolder.13
            @Override // com.sun.mail.imap.IMAPFolder.ProtocolCommand
            public Object doCommand(IMAPProtocol iMAPProtocol) throws ProtocolException {
                iMAPProtocol.setQuota(quota);
                return null;
            }
        });
    }

    public ACL[] getACL() throws MessagingException {
        return (ACL[]) doOptionalCommand("ACL not supported", new ProtocolCommand() { // from class: com.sun.mail.imap.IMAPFolder.14
            @Override // com.sun.mail.imap.IMAPFolder.ProtocolCommand
            public Object doCommand(IMAPProtocol iMAPProtocol) throws ProtocolException {
                return iMAPProtocol.getACL(IMAPFolder.this.fullName);
            }
        });
    }

    public void addACL(ACL acl) throws MessagingException {
        setACL(acl, (char) 0);
    }

    public void removeACL(final String str) throws MessagingException {
        doOptionalCommand("ACL not supported", new ProtocolCommand() { // from class: com.sun.mail.imap.IMAPFolder.15
            @Override // com.sun.mail.imap.IMAPFolder.ProtocolCommand
            public Object doCommand(IMAPProtocol iMAPProtocol) throws ProtocolException {
                iMAPProtocol.deleteACL(IMAPFolder.this.fullName, str);
                return null;
            }
        });
    }

    public void addRights(ACL acl) throws MessagingException {
        setACL(acl, '+');
    }

    public void removeRights(ACL acl) throws MessagingException {
        setACL(acl, '-');
    }

    public Rights[] listRights(final String str) throws MessagingException {
        return (Rights[]) doOptionalCommand("ACL not supported", new ProtocolCommand() { // from class: com.sun.mail.imap.IMAPFolder.16
            @Override // com.sun.mail.imap.IMAPFolder.ProtocolCommand
            public Object doCommand(IMAPProtocol iMAPProtocol) throws ProtocolException {
                return iMAPProtocol.listRights(IMAPFolder.this.fullName, str);
            }
        });
    }

    public Rights myRights() throws MessagingException {
        return (Rights) doOptionalCommand("ACL not supported", new ProtocolCommand() { // from class: com.sun.mail.imap.IMAPFolder.17
            @Override // com.sun.mail.imap.IMAPFolder.ProtocolCommand
            public Object doCommand(IMAPProtocol iMAPProtocol) throws ProtocolException {
                return iMAPProtocol.myRights(IMAPFolder.this.fullName);
            }
        });
    }

    private void setACL(final ACL acl, final char c) throws MessagingException {
        doOptionalCommand("ACL not supported", new ProtocolCommand() { // from class: com.sun.mail.imap.IMAPFolder.18
            @Override // com.sun.mail.imap.IMAPFolder.ProtocolCommand
            public Object doCommand(IMAPProtocol iMAPProtocol) throws ProtocolException {
                iMAPProtocol.setACL(IMAPFolder.this.fullName, c, acl);
                return null;
            }
        });
    }

    public String[] getAttributes() throws MessagingException {
        if (this.attributes == null) {
            exists();
        }
        return (String[]) this.attributes.clone();
    }

    public void idle() throws MessagingException {
        synchronized (this) {
            checkOpened();
            if (((Boolean) doOptionalCommand("IDLE not supported", new ProtocolCommand() { // from class: com.sun.mail.imap.IMAPFolder.19
                @Override // com.sun.mail.imap.IMAPFolder.ProtocolCommand
                public Object doCommand(IMAPProtocol iMAPProtocol) throws ProtocolException {
                    if (IMAPFolder.this.idleState == 0) {
                        iMAPProtocol.idleStart();
                        IMAPFolder.this.idleState = 1;
                        return Boolean.TRUE;
                    }
                    try {
                        IMAPFolder.this.messageCacheLock.wait();
                    } catch (InterruptedException unused) {
                    }
                    return Boolean.FALSE;
                }
            })).booleanValue()) {
                while (true) {
                    Response readIdleResponse = this.protocol.readIdleResponse();
                    try {
                        synchronized (this.messageCacheLock) {
                            if (!(readIdleResponse == null || this.protocol == null || !this.protocol.processIdleResponse(readIdleResponse))) {
                            }
                            this.idleState = 0;
                            this.messageCacheLock.notifyAll();
                        }
                    } catch (ConnectionException e) {
                        throwClosedException(e);
                    } catch (ProtocolException e2) {
                        throw new MessagingException(e2.getMessage(), e2);
                    }
                }
                int minIdleTime = ((IMAPStore) this.store).getMinIdleTime();
                if (minIdleTime > 0) {
                    try {
                        Thread.sleep(minIdleTime);
                    } catch (InterruptedException unused) {
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void waitIfIdle() throws ProtocolException {
        while (true) {
            int i = this.idleState;
            if (i != 0) {
                if (i == 1) {
                    this.protocol.idleAbort();
                    this.idleState = 2;
                }
                try {
                    this.messageCacheLock.wait();
                } catch (InterruptedException unused) {
                }
            } else {
                return;
            }
        }
    }

    @Override // com.sun.mail.iap.ResponseHandler
    public void handleResponse(Response response) {
        IMAPMessage messageBySeqNumber;
        if (response.isOK() || response.isNO() || response.isBAD() || response.isBYE()) {
            ((IMAPStore) this.store).handleResponseCode(response);
        }
        if (response.isBYE()) {
            if (this.opened) {
                cleanup(false);
            }
        } else if (!response.isOK() && response.isUnTagged()) {
            if (!(response instanceof IMAPResponse)) {
                this.out.println("UNEXPECTED RESPONSE : " + response.toString());
                this.out.println("CONTACT javamail@sun.com");
                return;
            }
            IMAPResponse iMAPResponse = (IMAPResponse) response;
            if (iMAPResponse.keyEquals("EXISTS")) {
                int number = iMAPResponse.getNumber();
                int i = this.realTotal;
                if (number > i) {
                    int i2 = number - i;
                    Message[] messageArr = new Message[i2];
                    for (int i3 = 0; i3 < i2; i3++) {
                        int i4 = this.total + 1;
                        this.total = i4;
                        int i5 = this.realTotal + 1;
                        this.realTotal = i5;
                        IMAPMessage iMAPMessage = new IMAPMessage(this, i4, i5);
                        messageArr[i3] = iMAPMessage;
                        this.messageCache.addElement(iMAPMessage);
                    }
                    notifyMessageAddedListeners(messageArr);
                }
            } else if (iMAPResponse.keyEquals("EXPUNGE")) {
                IMAPMessage messageBySeqNumber2 = getMessageBySeqNumber(iMAPResponse.getNumber());
                messageBySeqNumber2.setExpunged(true);
                for (int messageNumber = messageBySeqNumber2.getMessageNumber(); messageNumber < this.total; messageNumber++) {
                    IMAPMessage iMAPMessage2 = (IMAPMessage) this.messageCache.elementAt(messageNumber);
                    if (!iMAPMessage2.isExpunged()) {
                        iMAPMessage2.setSequenceNumber(iMAPMessage2.getSequenceNumber() - 1);
                    }
                }
                this.realTotal--;
                if (this.doExpungeNotification) {
                    notifyMessageRemovedListeners(false, new Message[]{messageBySeqNumber2});
                }
            } else if (iMAPResponse.keyEquals("FETCH")) {
                FetchResponse fetchResponse = (FetchResponse) iMAPResponse;
                Flags flags = (Flags) fetchResponse.getItem(Flags.class);
                if (!(flags == null || (messageBySeqNumber = getMessageBySeqNumber(fetchResponse.getNumber())) == null)) {
                    messageBySeqNumber._setFlags(flags);
                    notifyMessageChangedListeners(1, messageBySeqNumber);
                }
            } else if (iMAPResponse.keyEquals("RECENT")) {
                this.recent = iMAPResponse.getNumber();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void handleResponses(Response[] responseArr) {
        for (int i = 0; i < responseArr.length; i++) {
            if (responseArr[i] != null) {
                handleResponse(responseArr[i]);
            }
        }
    }

    protected synchronized IMAPProtocol getStoreProtocol() throws ProtocolException {
        if (this.connectionPoolDebug) {
            this.out.println("DEBUG: getStoreProtocol() - borrowing a connection");
        }
        return ((IMAPStore) this.store).getStoreProtocol();
    }

    private synchronized void throwClosedException(ConnectionException connectionException) throws FolderClosedException, StoreClosedException {
        if ((this.protocol == null || connectionException.getProtocol() != this.protocol) && (this.protocol != null || this.reallyClosed)) {
            throw new StoreClosedException(this.store, connectionException.getMessage());
        }
        throw new FolderClosedException(this, connectionException.getMessage());
    }

    private IMAPProtocol getProtocol() throws ProtocolException {
        waitIfIdle();
        return this.protocol;
    }

    public Object doCommand(ProtocolCommand protocolCommand) throws MessagingException {
        try {
            return doProtocolCommand(protocolCommand);
        } catch (ConnectionException e) {
            throwClosedException(e);
            return null;
        } catch (ProtocolException e2) {
            throw new MessagingException(e2.getMessage(), e2);
        }
    }

    public Object doOptionalCommand(String str, ProtocolCommand protocolCommand) throws MessagingException {
        try {
            return doProtocolCommand(protocolCommand);
        } catch (BadCommandException e) {
            throw new MessagingException(str, e);
        } catch (ConnectionException e2) {
            throwClosedException(e2);
            return null;
        } catch (ProtocolException e3) {
            throw new MessagingException(e3.getMessage(), e3);
        }
    }

    public Object doCommandIgnoreFailure(ProtocolCommand protocolCommand) throws MessagingException {
        try {
            return doProtocolCommand(protocolCommand);
        } catch (CommandFailedException unused) {
            return null;
        } catch (ConnectionException e) {
            throwClosedException(e);
            return null;
        } catch (ProtocolException e2) {
            throw new MessagingException(e2.getMessage(), e2);
        }
    }

    protected Object doProtocolCommand(ProtocolCommand protocolCommand) throws ProtocolException {
        Object doCommand;
        synchronized (this) {
            if (!this.opened || ((IMAPStore) this.store).hasSeparateStoreConnection()) {
                IMAPProtocol iMAPProtocol = null;
                try {
                    iMAPProtocol = getStoreProtocol();
                    return protocolCommand.doCommand(iMAPProtocol);
                } finally {
                    releaseStoreProtocol(iMAPProtocol);
                }
            } else {
                synchronized (this.messageCacheLock) {
                    doCommand = protocolCommand.doCommand(getProtocol());
                }
                return doCommand;
            }
        }
    }

    protected synchronized void releaseStoreProtocol(IMAPProtocol iMAPProtocol) {
        if (iMAPProtocol != this.protocol) {
            ((IMAPStore) this.store).releaseStoreProtocol(iMAPProtocol);
        }
    }

    private void releaseProtocol(boolean z) {
        IMAPProtocol iMAPProtocol = this.protocol;
        if (iMAPProtocol != null) {
            iMAPProtocol.removeResponseHandler(this);
            if (z) {
                ((IMAPStore) this.store).releaseProtocol(this, this.protocol);
            } else {
                ((IMAPStore) this.store).releaseProtocol(this, null);
            }
        }
    }

    private void keepConnectionAlive(boolean z) throws ProtocolException {
        if (System.currentTimeMillis() - this.protocol.getTimestamp() > 1000) {
            waitIfIdle();
            this.protocol.noop();
        }
        if (z && ((IMAPStore) this.store).hasSeparateStoreConnection()) {
            IMAPProtocol iMAPProtocol = null;
            try {
                iMAPProtocol = ((IMAPStore) this.store).getStoreProtocol();
                if (System.currentTimeMillis() - iMAPProtocol.getTimestamp() > 1000) {
                    iMAPProtocol.noop();
                }
            } finally {
                ((IMAPStore) this.store).releaseStoreProtocol(iMAPProtocol);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public IMAPMessage getMessageBySeqNumber(int i) {
        for (int i2 = i - 1; i2 < this.total; i2++) {
            IMAPMessage iMAPMessage = (IMAPMessage) this.messageCache.elementAt(i2);
            if (iMAPMessage.getSequenceNumber() == i) {
                return iMAPMessage;
            }
        }
        return null;
    }

    private boolean isDirectory() {
        return (this.type & 2) != 0;
    }
}
