package com.kaopu.download;

import com.kaopu.download.intf.IDownloadState;
import com.kaopu.download.state.DownloadCancelingState;
import com.kaopu.download.state.DownloadConnectingState;
import com.kaopu.download.state.DownloadFailedState;
import com.kaopu.download.state.DownloadNewState;
import com.kaopu.download.state.DownloadPausedState;
import com.kaopu.download.state.DownloadPausingState;
import com.kaopu.download.state.DownloadUnKnownState;
import com.kaopu.download.state.DownloadWaitState;
import com.kaopu.download.state.DownloadedState;
import com.kaopu.download.state.DownloadingState;

/* loaded from: classes.dex */
public class BaseDownloadStateFactory {
    private static DownloadCancelingState downloadCancelingState;
    private static DownloadConnectingState downloadConnectingState;
    private static DownloadFailedState downloadFailedState;
    private static DownloadNewState downloadNewState;
    private static DownloadPausedState downloadPausedState;
    private static DownloadPausingState downloadPausingState;
    private static DownloadUnKnownState downloadUnKnownState;
    private static DownloadWaitState downloadWaitState;
    private static DownloadedState downloadedState;
    private static DownloadingState downloadingState;

    /* loaded from: classes.dex */
    public enum State {
        DOWNLOAD_UNKNOWN(-1),
        DOWNLOAD_NEW(1),
        DOWNLOAD_WAIT(2),
        DOWNLOADING(3),
        DOWNLOADED(4),
        DOWNLOAD_PAUSED(5),
        DOWNLOAD_FAILED(6),
        DOWNLOAD_CANCELING(7),
        DOWNLOAD_PAUSEING(8),
        DOWNLOAD_CONNECTING(9);
        
        private int mIntValue;

        public static State mapIntToValue(int i) {
            State[] values;
            for (State state : values()) {
                if (i == state.getIntValue()) {
                    return state;
                }
            }
            return DOWNLOAD_UNKNOWN;
        }

        State(int i) {
            this.mIntValue = i;
        }

        public int getIntValue() {
            return this.mIntValue;
        }

        @Override // java.lang.Enum
        public String toString() {
            return String.valueOf(this.mIntValue);
        }
    }

    public static IDownloadState getDownloadState(State state) {
        switch (state) {
            case DOWNLOADED:
                return getDownloadedState();
            case DOWNLOADING:
                return getDownloadingState();
            case DOWNLOAD_CANCELING:
                return getDownloadCancelingState();
            case DOWNLOAD_CONNECTING:
                return getDownloadConnectingState();
            case DOWNLOAD_FAILED:
                return getDownloadFailedState();
            case DOWNLOAD_NEW:
                return getDownloadNewState();
            case DOWNLOAD_UNKNOWN:
                return getDownloadUnKnownState();
            case DOWNLOAD_PAUSED:
                return getDownloadPausedState();
            case DOWNLOAD_PAUSEING:
                return getDownloadPausingState();
            case DOWNLOAD_WAIT:
                return getDownloadWaitState();
            default:
                return getDownloadNewState();
        }
    }

    public static DownloadingState getDownloadingState() {
        if (downloadingState == null) {
            downloadingState = new DownloadingState();
        }
        return downloadingState;
    }

    public static DownloadNewState getDownloadNewState() {
        if (downloadNewState == null) {
            downloadNewState = new DownloadNewState();
        }
        return downloadNewState;
    }

    public static DownloadPausedState getDownloadPausedState() {
        if (downloadPausedState == null) {
            downloadPausedState = new DownloadPausedState();
        }
        return downloadPausedState;
    }

    public static DownloadPausingState getDownloadPausingState() {
        if (downloadPausingState == null) {
            downloadPausingState = new DownloadPausingState();
        }
        return downloadPausingState;
    }

    public static DownloadUnKnownState getDownloadUnKnownState() {
        if (downloadUnKnownState == null) {
            downloadUnKnownState = new DownloadUnKnownState();
        }
        return downloadUnKnownState;
    }

    public static DownloadWaitState getDownloadWaitState() {
        if (downloadWaitState == null) {
            downloadWaitState = new DownloadWaitState();
        }
        return downloadWaitState;
    }

    public static DownloadCancelingState getDownloadCancelingState() {
        if (downloadCancelingState == null) {
            downloadCancelingState = new DownloadCancelingState();
        }
        return downloadCancelingState;
    }

    public static DownloadConnectingState getDownloadConnectingState() {
        if (downloadConnectingState == null) {
            downloadConnectingState = new DownloadConnectingState();
        }
        return downloadConnectingState;
    }

    public static DownloadedState getDownloadedState() {
        if (downloadedState == null) {
            downloadedState = new DownloadedState();
        }
        return downloadedState;
    }

    public static DownloadFailedState getDownloadFailedState() {
        if (downloadFailedState == null) {
            downloadFailedState = new DownloadFailedState();
        }
        return downloadFailedState;
    }
}
