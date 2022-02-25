package com.lidroid.xutils.task;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.AbstractQueue;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/* loaded from: classes.dex */
public class PriorityObjectBlockingQueue<E> extends AbstractQueue<E> implements Serializable, BlockingQueue<E> {
    private static final long serialVersionUID = -6903933977591709194L;
    private final int capacity;
    private final AtomicInteger count;
    transient Node<E> head;
    private transient Node<E> last;
    private final Condition notEmpty;
    private final Condition notFull;
    private final ReentrantLock putLock;
    private final ReentrantLock takeLock;

    private void signalNotEmpty() {
        ReentrantLock reentrantLock = this.takeLock;
        reentrantLock.lock();
        try {
            this.notEmpty.signal();
        } finally {
            reentrantLock.unlock();
        }
    }

    private void signalNotFull() {
        ReentrantLock reentrantLock = this.putLock;
        reentrantLock.lock();
        try {
            this.notFull.signal();
        } finally {
            reentrantLock.unlock();
        }
    }

    private synchronized E opQueue(Node<E> node) {
        if (node == null) {
            return _dequeue();
        }
        _enqueue(node);
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void _enqueue(Node<E> node) {
        boolean z;
        Node node2 = this.head;
        while (true) {
            if (node2.next == null) {
                z = false;
                break;
            }
            Node<T> node3 = node2.next;
            if (node3.getPriority().ordinal() > node.getPriority().ordinal()) {
                node2.next = node;
                node.next = node3;
                z = true;
                break;
            }
            node2 = node2.next;
        }
        if (!z) {
            this.last.next = node;
            this.last = node;
        }
    }

    private E _dequeue() {
        Node node = (Node<E>) this.head;
        Node<E> node2 = (Node<E>) node.next;
        node.next = node;
        this.head = node2;
        E value = node2.getValue();
        node2.setValue(null);
        return value;
    }

    void fullyLock() {
        this.putLock.lock();
        this.takeLock.lock();
    }

    void fullyUnlock() {
        this.takeLock.unlock();
        this.putLock.unlock();
    }

    public PriorityObjectBlockingQueue() {
        this(Integer.MAX_VALUE);
    }

    public PriorityObjectBlockingQueue(int i) {
        this.count = new AtomicInteger();
        this.takeLock = new ReentrantLock();
        this.notEmpty = this.takeLock.newCondition();
        this.putLock = new ReentrantLock();
        this.notFull = this.putLock.newCondition();
        if (i > 0) {
            this.capacity = i;
            Node<E> node = new Node<>(null);
            this.head = node;
            this.last = node;
            return;
        }
        throw new IllegalArgumentException();
    }

    public PriorityObjectBlockingQueue(Collection<? extends E> collection) {
        this(Integer.MAX_VALUE);
        ReentrantLock reentrantLock = this.putLock;
        reentrantLock.lock();
        int i = 0;
        try {
            for (Object obj : collection) {
                if (obj == null) {
                    throw new NullPointerException();
                } else if (i != this.capacity) {
                    opQueue(new Node<>(obj));
                    i++;
                } else {
                    throw new IllegalStateException("Queue full");
                }
            }
            this.count.set(i);
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public int size() {
        return this.count.get();
    }

    @Override // java.util.concurrent.BlockingQueue
    public int remainingCapacity() {
        return this.capacity - this.count.get();
    }

    @Override // java.util.concurrent.BlockingQueue
    public void put(E e) throws InterruptedException {
        if (e != null) {
            Node<E> node = new Node<>(e);
            ReentrantLock reentrantLock = this.putLock;
            AtomicInteger atomicInteger = this.count;
            reentrantLock.lockInterruptibly();
            while (atomicInteger.get() == this.capacity) {
                try {
                    this.notFull.await();
                } finally {
                    reentrantLock.unlock();
                }
            }
            opQueue(node);
            int andIncrement = atomicInteger.getAndIncrement();
            if (andIncrement + 1 < this.capacity) {
                this.notFull.signal();
            }
            if (andIncrement == 0) {
                signalNotEmpty();
                return;
            }
            return;
        }
        throw new NullPointerException();
    }

    @Override // java.util.concurrent.BlockingQueue
    public boolean offer(E e, long j, TimeUnit timeUnit) throws InterruptedException {
        if (e != null) {
            long nanos = timeUnit.toNanos(j);
            ReentrantLock reentrantLock = this.putLock;
            AtomicInteger atomicInteger = this.count;
            reentrantLock.lockInterruptibly();
            while (atomicInteger.get() == this.capacity) {
                try {
                    if (nanos <= 0) {
                        reentrantLock.unlock();
                        return false;
                    }
                    nanos = this.notFull.awaitNanos(nanos);
                } finally {
                    reentrantLock.unlock();
                }
            }
            opQueue(new Node<>(e));
            int andIncrement = atomicInteger.getAndIncrement();
            if (andIncrement + 1 < this.capacity) {
                this.notFull.signal();
            }
            if (andIncrement != 0) {
                return true;
            }
            signalNotEmpty();
            return true;
        }
        throw new NullPointerException();
    }

    @Override // java.util.Queue, java.util.concurrent.BlockingQueue
    public boolean offer(E e) {
        if (e != null) {
            AtomicInteger atomicInteger = this.count;
            if (atomicInteger.get() == this.capacity) {
                return false;
            }
            int i = -1;
            Node<E> node = new Node<>(e);
            ReentrantLock reentrantLock = this.putLock;
            reentrantLock.lock();
            try {
                if (atomicInteger.get() < this.capacity) {
                    opQueue(node);
                    i = atomicInteger.getAndIncrement();
                    if (i + 1 < this.capacity) {
                        this.notFull.signal();
                    }
                }
                if (i == 0) {
                    signalNotEmpty();
                }
                return i >= 0;
            } finally {
                reentrantLock.unlock();
            }
        } else {
            throw new NullPointerException();
        }
    }

    /* JADX WARN: Finally extract failed */
    @Override // java.util.concurrent.BlockingQueue
    public E take() throws InterruptedException {
        AtomicInteger atomicInteger = this.count;
        ReentrantLock reentrantLock = this.takeLock;
        reentrantLock.lockInterruptibly();
        while (atomicInteger.get() == 0) {
            try {
                this.notEmpty.await();
            } catch (Throwable th) {
                reentrantLock.unlock();
                throw th;
            }
        }
        E opQueue = opQueue(null);
        int andDecrement = atomicInteger.getAndDecrement();
        if (andDecrement > 1) {
            this.notEmpty.signal();
        }
        reentrantLock.unlock();
        if (andDecrement == this.capacity) {
            signalNotFull();
        }
        return opQueue;
    }

    /* JADX WARN: Finally extract failed */
    @Override // java.util.concurrent.BlockingQueue
    public E poll(long j, TimeUnit timeUnit) throws InterruptedException {
        long nanos = timeUnit.toNanos(j);
        AtomicInteger atomicInteger = this.count;
        ReentrantLock reentrantLock = this.takeLock;
        reentrantLock.lockInterruptibly();
        while (atomicInteger.get() == 0) {
            try {
                if (nanos <= 0) {
                    reentrantLock.unlock();
                    return null;
                }
                nanos = this.notEmpty.awaitNanos(nanos);
            } catch (Throwable th) {
                reentrantLock.unlock();
                throw th;
            }
        }
        E opQueue = opQueue(null);
        int andDecrement = atomicInteger.getAndDecrement();
        if (andDecrement > 1) {
            this.notEmpty.signal();
        }
        reentrantLock.unlock();
        if (andDecrement == this.capacity) {
            signalNotFull();
        }
        return opQueue;
    }

    /* JADX WARN: Finally extract failed */
    @Override // java.util.Queue
    public E poll() {
        AtomicInteger atomicInteger = this.count;
        E e = null;
        if (atomicInteger.get() == 0) {
            return null;
        }
        int i = -1;
        ReentrantLock reentrantLock = this.takeLock;
        reentrantLock.lock();
        try {
            if (atomicInteger.get() > 0) {
                e = opQueue(null);
                i = atomicInteger.getAndDecrement();
                if (i > 1) {
                    this.notEmpty.signal();
                }
            }
            reentrantLock.unlock();
            if (i == this.capacity) {
                signalNotFull();
            }
            return e;
        } catch (Throwable th) {
            reentrantLock.unlock();
            throw th;
        }
    }

    @Override // java.util.Queue
    public E peek() {
        if (this.count.get() == 0) {
            return null;
        }
        ReentrantLock reentrantLock = this.takeLock;
        reentrantLock.lock();
        try {
            Node<E> node = this.head.next;
            if (node == null) {
                return null;
            }
            return node.getValue();
        } finally {
            reentrantLock.unlock();
        }
    }

    void unlink(Node<E> node, Node<E> node2) {
        node.setValue(null);
        node2.next = (Node<E>) node.next;
        if (this.last == node) {
            this.last = node2;
        }
        if (this.count.getAndDecrement() == this.capacity) {
            this.notFull.signal();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.concurrent.BlockingQueue
    public boolean remove(Object obj) {
        if (obj == null) {
            return false;
        }
        fullyLock();
        try {
            Node<E> node = this.head;
            Node<E> node2 = node;
            for (Node<E> node3 = node.next; node3 != null; node3 = node3.next) {
                if (obj.equals(node3.getValue())) {
                    unlink(node3, node2);
                    fullyUnlock();
                    return true;
                }
                node2 = node3;
            }
            return false;
        } finally {
            fullyUnlock();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.concurrent.BlockingQueue
    public boolean contains(Object obj) {
        if (obj == null) {
            return false;
        }
        fullyLock();
        try {
            Node node = this.head;
            do {
                node = node.next;
                if (node == null) {
                    return false;
                }
            } while (!obj.equals(node.getValue()));
            fullyUnlock();
            return true;
        } finally {
            fullyUnlock();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public Object[] toArray() {
        fullyLock();
        try {
            Object[] objArr = new Object[this.count.get()];
            int i = 0;
            Node node = this.head.next;
            while (node != null) {
                i++;
                objArr[i] = node.getValue();
                node = node.next;
            }
            return objArr;
        } finally {
            fullyUnlock();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractCollection, java.util.Collection
    public <T> T[] toArray(T[] tArr) {
        fullyLock();
        try {
            int i = this.count.get();
            if (tArr.length < i) {
                tArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), i));
            }
            int i2 = 0;
            Node node = this.head.next;
            while (node != null) {
                i2++;
                tArr[i2] = node.getValue();
                node = node.next;
            }
            if (tArr.length > i2) {
                tArr[i2] = null;
            }
            return tArr;
        } finally {
            fullyUnlock();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractQueue, java.util.AbstractCollection, java.util.Collection
    public void clear() {
        fullyLock();
        try {
            Node node = (Node<E>) this.head;
            while (true) {
                Node node2 = node.next;
                if (node2 == null) {
                    break;
                }
                node.next = node;
                node2.setValue(null);
                node = (Node<E>) node2;
            }
            this.head = this.last;
            if (this.count.getAndSet(0) == this.capacity) {
                this.notFull.signal();
            }
        } finally {
            fullyUnlock();
        }
    }

    @Override // java.util.concurrent.BlockingQueue
    public int drainTo(Collection<? super E> collection) {
        return drainTo(collection, Integer.MAX_VALUE);
    }

    @Override // java.util.concurrent.BlockingQueue
    public int drainTo(Collection<? super E> collection, int i) {
        if (collection == null) {
            throw new NullPointerException();
        } else if (collection != this) {
            boolean z = false;
            if (i <= 0) {
                return 0;
            }
            ReentrantLock reentrantLock = this.takeLock;
            reentrantLock.lock();
            try {
                int min = Math.min(i, this.count.get());
                Node<E> node = this.head;
                int i2 = 0;
                while (i2 < min) {
                    Node<E> node2 = node.next;
                    collection.add((Object) node2.getValue());
                    node2.setValue(null);
                    node.next = (Node<T>) node;
                    i2++;
                    node = node2;
                }
                if (i2 > 0) {
                    this.head = node;
                    if (this.count.getAndAdd(-i2) == this.capacity) {
                        z = true;
                    }
                }
                return min;
            } finally {
                reentrantLock.unlock();
                if (0 != 0) {
                    signalNotFull();
                }
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public Iterator<E> iterator() {
        return new Itr();
    }

    /* loaded from: classes.dex */
    private class Itr implements Iterator<E> {
        private Node<E> current;
        private E currentElement;
        private Node<E> lastRet;

        Itr() {
            PriorityObjectBlockingQueue.this.fullyLock();
            try {
                this.current = PriorityObjectBlockingQueue.this.head.next;
                if (this.current != null) {
                    this.currentElement = this.current.getValue();
                }
            } finally {
                PriorityObjectBlockingQueue.this.fullyUnlock();
            }
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.current != null;
        }

        private Node<E> nextNode(Node<E> node) {
            Node<E> node2;
            while (true) {
                node2 = node.next;
                if (node2 == node) {
                    return PriorityObjectBlockingQueue.this.head.next;
                }
                if (node2 == null || node2.getValue() != null) {
                    break;
                }
                node = node2;
            }
            return node2;
        }

        @Override // java.util.Iterator
        public E next() {
            PriorityObjectBlockingQueue.this.fullyLock();
            try {
                if (this.current != null) {
                    E e = this.currentElement;
                    this.lastRet = this.current;
                    this.current = nextNode(this.current);
                    this.currentElement = this.current == null ? null : this.current.getValue();
                    return e;
                }
                throw new NoSuchElementException();
            } finally {
                PriorityObjectBlockingQueue.this.fullyUnlock();
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:9:0x001c, code lost:
            r4.this$0.unlink(r1, r2);
         */
        @Override // java.util.Iterator
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void remove() {
            /*
                r4 = this;
                com.lidroid.xutils.task.Node<E> r0 = r4.lastRet
                if (r0 == 0) goto L_0x0034
                com.lidroid.xutils.task.PriorityObjectBlockingQueue r0 = com.lidroid.xutils.task.PriorityObjectBlockingQueue.this
                r0.fullyLock()
                com.lidroid.xutils.task.Node<E> r0 = r4.lastRet     // Catch: all -> 0x002d
                r1 = 0
                r4.lastRet = r1     // Catch: all -> 0x002d
                com.lidroid.xutils.task.PriorityObjectBlockingQueue r1 = com.lidroid.xutils.task.PriorityObjectBlockingQueue.this     // Catch: all -> 0x002d
                com.lidroid.xutils.task.Node<E> r1 = r1.head     // Catch: all -> 0x002d
                com.lidroid.xutils.task.Node<T> r2 = r1.next     // Catch: all -> 0x002d
                r3 = r2
                r2 = r1
                r1 = r3
            L_0x0017:
                if (r1 != 0) goto L_0x001a
                goto L_0x0021
            L_0x001a:
                if (r1 != r0) goto L_0x0027
                com.lidroid.xutils.task.PriorityObjectBlockingQueue r0 = com.lidroid.xutils.task.PriorityObjectBlockingQueue.this     // Catch: all -> 0x002d
                r0.unlink(r1, r2)     // Catch: all -> 0x002d
            L_0x0021:
                com.lidroid.xutils.task.PriorityObjectBlockingQueue r0 = com.lidroid.xutils.task.PriorityObjectBlockingQueue.this
                r0.fullyUnlock()
                return
            L_0x0027:
                com.lidroid.xutils.task.Node<T> r2 = r1.next     // Catch: all -> 0x002d
                r3 = r2
                r2 = r1
                r1 = r3
                goto L_0x0017
            L_0x002d:
                r0 = move-exception
                com.lidroid.xutils.task.PriorityObjectBlockingQueue r1 = com.lidroid.xutils.task.PriorityObjectBlockingQueue.this
                r1.fullyUnlock()
                throw r0
            L_0x0034:
                java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
                r0.<init>()
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.lidroid.xutils.task.PriorityObjectBlockingQueue.Itr.remove():void");
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        fullyLock();
        try {
            objectOutputStream.defaultWriteObject();
            Node node = this.head;
            while (true) {
                node = node.next;
                if (node == null) {
                    objectOutputStream.writeObject(null);
                    return;
                }
                objectOutputStream.writeObject(node.getValue());
            }
        } finally {
            fullyUnlock();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.count.set(0);
        Node<E> node = new Node<>(null);
        this.head = node;
        this.last = node;
        while (true) {
            Object readObject = objectInputStream.readObject();
            if (readObject != null) {
                add(readObject);
            } else {
                return;
            }
        }
    }
}
