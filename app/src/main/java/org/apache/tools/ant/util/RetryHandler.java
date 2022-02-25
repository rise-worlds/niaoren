package org.apache.tools.ant.util;

import java.io.IOException;
import org.apache.tools.ant.Task;

/* loaded from: classes2.dex */
public class RetryHandler {
    private int retriesAllowed;
    private Task task;

    public RetryHandler(int i, Task task) {
        this.retriesAllowed = 0;
        this.retriesAllowed = i;
        this.task = task;
    }

    public void execute(Retryable retryable, String str) throws IOException {
        int i = 0;
        while (true) {
            try {
                retryable.execute();
                return;
            } catch (IOException e) {
                i++;
                int i2 = this.retriesAllowed;
                if (i <= i2 || i2 <= -1) {
                    Task task = this.task;
                    task.log("try #" + i + ": IO error (" + str + "), retrying", 1);
                } else {
                    Task task2 = this.task;
                    task2.log("try #" + i + ": IO error (" + str + "), number of maximum retries reached (" + this.retriesAllowed + "), giving up", 1);
                    throw e;
                }
            }
        }
    }
}
