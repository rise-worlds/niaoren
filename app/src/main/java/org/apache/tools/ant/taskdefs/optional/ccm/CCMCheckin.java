package org.apache.tools.ant.taskdefs.optional.ccm;

import java.util.Date;

/* loaded from: classes2.dex */
public class CCMCheckin extends CCMCheck {
    public CCMCheckin() {
        setCcmAction(Continuus.COMMAND_CHECKIN);
        setComment("Checkin " + new Date());
    }
}
