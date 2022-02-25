package com.sun.mail.imap;

import com.sun.mail.iap.ProtocolException;
import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.imap.protocol.IMAPProtocol;
import com.sun.mail.imap.protocol.ListInfo;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.MethodNotSupportedException;
import p110z1.ciq;

/* loaded from: classes2.dex */
public class DefaultFolder extends IMAPFolder {
    @Override // com.sun.mail.imap.IMAPFolder, javax.mail.Folder
    public Folder getParent() {
        return null;
    }

    @Override // com.sun.mail.imap.IMAPFolder, javax.mail.Folder
    public boolean hasNewMessages() throws MessagingException {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public DefaultFolder(IMAPStore iMAPStore) {
        super("", ciq.f20716b, iMAPStore);
        this.exists = true;
        this.type = 2;
    }

    @Override // com.sun.mail.imap.IMAPFolder, javax.mail.Folder
    public String getName() {
        return this.fullName;
    }

    @Override // com.sun.mail.imap.IMAPFolder, javax.mail.Folder
    public Folder[] list(final String str) throws MessagingException {
        ListInfo[] listInfoArr = (ListInfo[]) doCommand(new IMAPFolder.ProtocolCommand() { // from class: com.sun.mail.imap.DefaultFolder.1
            @Override // com.sun.mail.imap.IMAPFolder.ProtocolCommand
            public Object doCommand(IMAPProtocol iMAPProtocol) throws ProtocolException {
                return iMAPProtocol.list("", str);
            }
        });
        if (listInfoArr == null) {
            return new Folder[0];
        }
        IMAPFolder[] iMAPFolderArr = new IMAPFolder[listInfoArr.length];
        for (int i = 0; i < iMAPFolderArr.length; i++) {
            iMAPFolderArr[i] = new IMAPFolder(listInfoArr[i], (IMAPStore) this.store);
        }
        return iMAPFolderArr;
    }

    @Override // com.sun.mail.imap.IMAPFolder, javax.mail.Folder
    public Folder[] listSubscribed(final String str) throws MessagingException {
        ListInfo[] listInfoArr = (ListInfo[]) doCommand(new IMAPFolder.ProtocolCommand() { // from class: com.sun.mail.imap.DefaultFolder.2
            @Override // com.sun.mail.imap.IMAPFolder.ProtocolCommand
            public Object doCommand(IMAPProtocol iMAPProtocol) throws ProtocolException {
                return iMAPProtocol.lsub("", str);
            }
        });
        if (listInfoArr == null) {
            return new Folder[0];
        }
        IMAPFolder[] iMAPFolderArr = new IMAPFolder[listInfoArr.length];
        for (int i = 0; i < iMAPFolderArr.length; i++) {
            iMAPFolderArr[i] = new IMAPFolder(listInfoArr[i], (IMAPStore) this.store);
        }
        return iMAPFolderArr;
    }

    @Override // com.sun.mail.imap.IMAPFolder, javax.mail.Folder
    public Folder getFolder(String str) throws MessagingException {
        return new IMAPFolder(str, ciq.f20716b, (IMAPStore) this.store);
    }

    @Override // com.sun.mail.imap.IMAPFolder, javax.mail.Folder
    public boolean delete(boolean z) throws MessagingException {
        throw new MethodNotSupportedException("Cannot delete Default Folder");
    }

    @Override // com.sun.mail.imap.IMAPFolder, javax.mail.Folder
    public boolean renameTo(Folder folder) throws MessagingException {
        throw new MethodNotSupportedException("Cannot rename Default Folder");
    }

    @Override // com.sun.mail.imap.IMAPFolder, javax.mail.Folder
    public void appendMessages(Message[] messageArr) throws MessagingException {
        throw new MethodNotSupportedException("Cannot append to Default Folder");
    }

    @Override // com.sun.mail.imap.IMAPFolder, javax.mail.Folder
    public Message[] expunge() throws MessagingException {
        throw new MethodNotSupportedException("Cannot expunge Default Folder");
    }
}
