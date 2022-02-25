package com.sun.mail.imap;

import com.tencent.smtt.utils.TbsLog;
import javax.mail.Session;
import javax.mail.URLName;

/* loaded from: classes2.dex */
public class IMAPSSLStore extends IMAPStore {
    public IMAPSSLStore(Session session, URLName uRLName) {
        super(session, uRLName, "imaps", TbsLog.TBSLOG_CODE_SDK_CONFLICT_X5CORE, true);
    }
}
