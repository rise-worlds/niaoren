package com.kaopu.download.abst;

import com.github.kevinsawicki.http.HttpRequest;
import com.kaopu.download.kernel.BaseDownloadInfo;
import com.kaopu.download.kernel.DownloadWorkerSupervisor;
import com.kaopu.download.util.DownloadHttpUtil;
import com.kaopu.download.util.DownloadThreadUtil;
import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.http.protocol.HTTP;

/* loaded from: classes.dex */
public abstract class ADownloadWorker<T extends BaseDownloadInfo> implements Runnable {
    public static final String POSTFIX_FILE_NAME = ".temp";
    private final String identification;
    protected final T mDownloadInfo;
    private int progress;
    private int requestType = 3;
    private volatile boolean running = false;
    private String saveDir;
    private String saveFile;
    private String saveName;
    private long totalSize;
    private String url;

    protected abstract void onDownloadCanceled(String str);

    protected abstract void onDownloadCanceling(String str);

    protected abstract void onDownloadCompleted(String str, String str2, long j);

    protected abstract void onDownloadConnecting(String str, long j);

    protected abstract void onDownloadFailed(String str);

    protected abstract void onDownloadPaused(String str);

    protected abstract void onDownloadPausing(String str);

    protected abstract void onDownloadWait(String str);

    protected abstract void onDownloadWorking(String str, long j, long j2, int i);

    public ADownloadWorker(T t) {
        this.url = DownloadHttpUtil.utf8URLencode(t.getUrl());
        this.saveDir = t.getSaveDir();
        this.saveName = t.getSaveName();
        this.identification = t.getIdentification();
        this.mDownloadInfo = t;
        maybeInitDir(this.saveDir);
    }

    protected void maybeInitDir(String str) {
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
        this.saveFile = new File(this.saveDir, this.saveName).getAbsolutePath();
    }

    @Override // java.lang.Runnable
    public void run() {
        download();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:144:0x0219 A[Catch: IOException -> 0x0215, TryCatch #1 {IOException -> 0x0215, blocks: (B:140:0x0211, B:144:0x0219, B:145:0x021c), top: B:181:0x0211 }] */
    /* JADX WARN: Removed duplicated region for block: B:154:0x023a A[Catch: IOException -> 0x0236, TryCatch #2 {IOException -> 0x0236, blocks: (B:150:0x0232, B:154:0x023a, B:155:0x023d), top: B:183:0x0232 }] */
    /* JADX WARN: Removed duplicated region for block: B:181:0x0211 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:183:0x0232 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r1v10, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v26 */
    /* JADX WARN: Type inference failed for: r1v28 */
    /* JADX WARN: Type inference failed for: r1v29 */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v9 */
    /* JADX WARN: Type inference failed for: r9v3 */
    /* JADX WARN: Type inference failed for: r9v4 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void download() {
        /*
            Method dump skipped, instructions count: 641
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kaopu.download.abst.ADownloadWorker.download():void");
    }

    private void renameFile(File file, String str) {
        File file2 = new File(str);
        if (!file2.exists()) {
            file.renameTo(file2);
        }
    }

    public void deleteTempFile() {
        File file = new File(this.saveFile);
        if (file.exists()) {
            file.delete();
        }
        if (!DownloadWorkerSupervisor.isDownloading(this.identification)) {
            try {
                File file2 = new File(this.saveFile + POSTFIX_FILE_NAME);
                if (file2.exists()) {
                    file2.delete();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static String getFileName(String str) {
        try {
            return str.substring(str.lastIndexOf(47) + 1);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private HttpURLConnection getConnection(String str) throws Exception {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(DownloadHttpUtil.utf8URLencode(str)).openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setDoInput(true);
        httpURLConnection.setConnectTimeout(30000);
        httpURLConnection.setReadTimeout(60000);
        httpURLConnection.setRequestProperty(HttpRequest.HEADER_ACCEPT, "image/gif, image/jpeg, image/pjpeg, image/pjpeg, application/x-shockwave-flash, application/xaml+xml, application/vnd.ms-xpsdocument, application/x-ms-xbap, application/x-ms-application, application/vnd.ms-excel, application/vnd.ms-powerpoint, application/msword, */*");
        httpURLConnection.setRequestProperty("Charset", "UTF-8");
        httpURLConnection.setRequestProperty(HTTP.CONN_DIRECTIVE, HTTP.CONN_KEEP_ALIVE);
        return httpURLConnection;
    }

    public final boolean pause() {
        onDownloadPausing(this.identification);
        this.requestType = 1;
        if (this.running) {
            Thread.currentThread().interrupt();
            this.running = false;
        } else {
            DownloadThreadUtil.remove(this);
            onDownloadPaused(this.identification);
        }
        return true;
    }

    public final boolean cancle() {
        onDownloadCanceling(this.identification);
        this.requestType = 2;
        if (this.running) {
            Thread.currentThread().interrupt();
            this.running = false;
            return true;
        }
        DownloadThreadUtil.remove(this);
        deleteTempFile();
        onDownloadCanceled(this.identification);
        return true;
    }

    public int getProgress() {
        return this.progress;
    }

    public void setSpecifyFileName(String str) {
        this.saveName = str;
    }

    public synchronized boolean start() {
        if (!DownloadWorkerSupervisor.add(this.identification, this)) {
            return false;
        }
        onDownloadWait(this.identification);
        DownloadThreadUtil.excuteDownload(this);
        return true;
    }

    public long getTotalSize() {
        return this.totalSize;
    }

    public String getUrl() {
        return this.url;
    }

    public void updateDownloadUrl(String str) {
        this.url = DownloadHttpUtil.utf8URLencode(str);
    }

    public T getBaseDownloadInfo() {
        return this.mDownloadInfo;
    }
}
