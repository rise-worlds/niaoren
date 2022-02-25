package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Metadata(m8864a = 2, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u001a\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a&\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0004H\u0087\b¢\u0006\u0002\u0010\u0005\u001a&\u0010\u0006\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00072\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0004H\u0087\b¢\u0006\u0002\u0010\b\u001a&\u0010\t\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0004H\u0087\b¢\u0006\u0002\u0010\u0005¨\u0006\n"}, m8860e = {"read", TessBaseAPI.f9204e, "Ljava/util/concurrent/locks/ReentrantReadWriteLock;", "action", "Lkotlin/Function0;", "(Ljava/util/concurrent/locks/ReentrantReadWriteLock;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "withLock", "Ljava/util/concurrent/locks/Lock;", "(Ljava/util/concurrent/locks/Lock;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "write", "kotlin-stdlib"})
@cgo(m5270a = "LocksKt")
/* renamed from: z1.cca */
/* loaded from: classes3.dex */
public final class Locks {
    @cey
    /* renamed from: a */
    private static final <T> T m5681a(@NotNull Lock lock, chc<? extends T> chcVar) {
        lock.lock();
        try {
            return (T) chcVar.invoke();
        } finally {
            InlineMarker.m5201b(1);
            lock.unlock();
            InlineMarker.m5200c(1);
        }
    }

    @cey
    /* renamed from: a */
    private static final <T> T m5680a(@NotNull ReentrantReadWriteLock reentrantReadWriteLock, chc<? extends T> chcVar) {
        ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
        readLock.lock();
        try {
            return (T) chcVar.invoke();
        } finally {
            InlineMarker.m5201b(1);
            readLock.unlock();
            InlineMarker.m5200c(1);
        }
    }

    @cey
    /* renamed from: b */
    private static final <T> T m5679b(@NotNull ReentrantReadWriteLock reentrantReadWriteLock, chc<? extends T> chcVar) {
        ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
        int i = 0;
        int readHoldCount = reentrantReadWriteLock.getWriteHoldCount() == 0 ? reentrantReadWriteLock.getReadHoldCount() : 0;
        for (int i2 = 0; i2 < readHoldCount; i2++) {
            readLock.unlock();
        }
        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
        writeLock.lock();
        try {
            return (T) chcVar.invoke();
        } finally {
            InlineMarker.m5201b(1);
            while (i < readHoldCount) {
                readLock.lock();
                i++;
            }
            writeLock.unlock();
            InlineMarker.m5200c(1);
        }
    }
}
