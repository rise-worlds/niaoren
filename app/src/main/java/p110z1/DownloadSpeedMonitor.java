package p110z1;

import android.os.SystemClock;
import p110z1.IDownloadSpeed;

/* renamed from: z1.afc */
/* loaded from: classes3.dex */
public class DownloadSpeedMonitor implements IDownloadSpeed.AbstractC3433a, IDownloadSpeed.AbstractC3434b {

    /* renamed from: a */
    private long f15499a;

    /* renamed from: b */
    private long f15500b;

    /* renamed from: c */
    private long f15501c;

    /* renamed from: d */
    private long f15502d;

    /* renamed from: e */
    private int f15503e;

    /* renamed from: f */
    private long f15504f;

    /* renamed from: g */
    private int f15505g = 1000;

    @Override // p110z1.IDownloadSpeed.AbstractC3434b
    /* renamed from: a */
    public void mo13582a(long j) {
        this.f15502d = SystemClock.uptimeMillis();
        this.f15501c = j;
    }

    @Override // p110z1.IDownloadSpeed.AbstractC3434b
    /* renamed from: b */
    public void mo13581b(long j) {
        if (this.f15502d > 0) {
            long j2 = j - this.f15501c;
            this.f15499a = 0L;
            long uptimeMillis = SystemClock.uptimeMillis() - this.f15502d;
            if (uptimeMillis <= 0) {
                this.f15503e = (int) j2;
            } else {
                this.f15503e = (int) (j2 / uptimeMillis);
            }
        }
    }

    @Override // p110z1.IDownloadSpeed.AbstractC3434b
    /* renamed from: c */
    public void mo13580c(long j) {
        if (this.f15505g > 0) {
            boolean z = true;
            if (this.f15499a != 0) {
                long uptimeMillis = SystemClock.uptimeMillis() - this.f15499a;
                if (uptimeMillis >= this.f15505g || (this.f15503e == 0 && uptimeMillis > 0)) {
                    this.f15503e = (int) ((j - this.f15500b) / uptimeMillis);
                    this.f15503e = Math.max(0, this.f15503e);
                } else {
                    z = false;
                }
            }
            if (z) {
                this.f15500b = j;
                this.f15499a = SystemClock.uptimeMillis();
            }
        }
    }

    @Override // p110z1.IDownloadSpeed.AbstractC3434b
    /* renamed from: a */
    public void mo13583a() {
        this.f15503e = 0;
        this.f15499a = 0L;
    }

    @Override // p110z1.IDownloadSpeed.AbstractC3433a
    /* renamed from: b */
    public int mo13584b() {
        return this.f15503e;
    }

    @Override // p110z1.IDownloadSpeed.AbstractC3433a
    /* renamed from: a */
    public void mo13585a(int i) {
        this.f15505g = i;
    }
}
