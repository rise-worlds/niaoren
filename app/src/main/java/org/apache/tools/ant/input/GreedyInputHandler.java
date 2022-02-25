package org.apache.tools.ant.input;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.taskdefs.StreamPumper;
import org.apache.tools.ant.util.FileUtils;

/* loaded from: classes2.dex */
public class GreedyInputHandler extends DefaultInputHandler {
    @Override // org.apache.tools.ant.input.DefaultInputHandler, org.apache.tools.ant.input.InputHandler
    public void handleInput(InputRequest inputRequest) throws BuildException {
        Throwable th;
        InputStream inputStream;
        String prompt = getPrompt(inputRequest);
        try {
            inputStream = getInputStream();
            try {
                System.err.println(prompt);
                System.err.flush();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                StreamPumper streamPumper = new StreamPumper(inputStream, byteArrayOutputStream);
                Thread thread = new Thread(streamPumper);
                thread.start();
                try {
                    try {
                        thread.join();
                    } catch (InterruptedException unused) {
                    }
                } catch (InterruptedException unused2) {
                    thread.join();
                }
                inputRequest.setInput(new String(byteArrayOutputStream.toByteArray()));
                if (!inputRequest.isInputValid()) {
                    throw new BuildException("Received invalid console input");
                } else if (streamPumper.getException() == null) {
                    FileUtils.close(inputStream);
                } else {
                    throw new BuildException("Failed to read input from console", streamPumper.getException());
                }
            } catch (Throwable th2) {
                th = th2;
                FileUtils.close(inputStream);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            inputStream = null;
        }
    }
}
