package org.apache.tools.ant.taskdefs;

import org.apache.tools.ant.taskdefs.email.EmailTask;

/* loaded from: classes2.dex */
public class SendEmail extends EmailTask {
    public void setMailport(Integer num) {
        setMailport(num.intValue());
    }
}
